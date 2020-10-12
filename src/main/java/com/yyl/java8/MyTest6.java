package com.yyl.java8;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @Description: CompletableFuture类使用
 * @Author: yang.yonglian
 * @CreateDate: 2019/10/29 11:42
 * @Version: 1.0
 */
public class MyTest6 {
    static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("AppleShop"),
            new Shop("Apple2Shop"));

    static Shop shopp = new Shop("AppleShop");


    public static void main(String[] args) throws Exception{
        test5();
    }

    /**
     * 顺序流&并行流
     */
    public static void test1(){
        long t1 = System.currentTimeMillis();
        String product = "apple";
        shops.stream().map(shop->"shop "+shop.getName()+" price is "+shop.getPrice(product))
//                .parallel()
                .forEach(System.out::println);
        System.out.println("time is "+(System.currentTimeMillis()-t1));
    }

    /**
     * 使用CompletableFuture来实现价格的异步查询，使用默认的CompletableFuture.supplyAsync
     * 四个shop查询即如果为2s多，猜测其线程池中的数量默认不是 4导致的 ，后面自定义线程池 可以达到1s多
     */
    public static void test2(){
        long t1 = System.currentTimeMillis();
        String product = "apple";
        List<CompletableFuture> list = shops.stream().map(shop->CompletableFuture.supplyAsync(()->"shop "+shop.getName()+" price is "
                +shop.getPrice(product))).collect(Collectors.toList());
        //注意这里需要使用两个stream而不能放在一个stream中，因为如果放在一个stream中的话，每个操作都要同步的执行join方法，所以整个
        //stream的遍历其实还是同步的
        list.stream().map(future->future.join()).forEach(System.out::println);
//        shops.stream().map(shop->CompletableFuture.supplyAsync(()->"shop "+shop.getName()+" price is "
//                +shop.getPrice(product))).map(CompletableFuture::join).forEach(System.out::println);
        System.out.println("time is "+(System.currentTimeMillis()-t1));
    }

    /**
     * 多个同步任务调用
     */
    public static void test3(){
        long t1 = System.currentTimeMillis();
        shops.stream().map(shop->shop.getPriceStr("apple"))
                .map(Quote::parse).map(Discount::applyDiscount).forEach(System.out::println);
        System.out.println("time is "+(System.currentTimeMillis()-t1));

    }
    private static ExecutorService pool = Executors.newFixedThreadPool(10,r->{
        Thread thread = new Thread(r,"test thread");
        thread.setDaemon(true);
        return thread;
    });

    /**
     * 多个异步任务调用
     */
    public static void test4(){
        long t1 = System.currentTimeMillis();
        List<CompletableFuture<String>> list = shops.stream().map(shop->CompletableFuture.supplyAsync(()->shop.getPriceStr("apple"),pool))
                //使用thenApply方法将Completable<String>转化为Completable<Quote>对象，但是这个方法不会阻塞
                .map(completableFuture->completableFuture.thenApply(Quote::parse))
                //使用thenCompose方法对两个异步任务进行流水线，第一个异步任务完成时，将其结果作为参数传递给第二个异步任务
                //同CompletableFuture中的其它方法一样 CompletableFuture也提供了thenComposeAsync版本
                //通常而言，名称中不带Async的方法和他的前一个任务一样在同一个线程中运行，而名称以Async结尾的方法将后续任务提交到一个线程池
                //所以每个任务是由不同的线程处理的
                .map(completableFuture->completableFuture.thenCompose(quote->CompletableFuture.supplyAsync(()->Discount.applyDiscount(quote),pool)))
                .collect(Collectors.toList());
        list.stream().map(CompletableFuture::join).forEach(System.out::println);
        System.out.println("time is "+(System.currentTimeMillis()-t1));
    }

    /**
     * 将两个异步任务合并
     */
    public static void test5(){
        long t1 = System.currentTimeMillis();
        List<CompletableFuture<Double>> list = shops.stream().map(shop->CompletableFuture.supplyAsync(()->shop.getPrice("apple"),pool))
                //使用thenCombine将两个异步任务的的结果合并，可以看到如果线程池中的个数为9那么执行时间为2s，如果为10执行时间为1s
                //可以说明第一个异步任务和第二个异步任务是同步的
                .map(future->future.thenCombine(CompletableFuture.supplyAsync(()->Shop.getRate(),pool),(d1,d2)->d1*d2))
                .collect(Collectors.toList());
        list.stream().map(CompletableFuture::join).forEach(System.out::println);
        System.out.println("time is "+(System.currentTimeMillis()-t1));
    }

    /**
     * 响应CompletableFuture的完成事件，这个也就是避免同步调用CompletableFuture的join方法同步获取返回结果
     * 这个方法就是哪一个异步任务先执行完成，就先获取这个结果，有点类似Java7的CompletionService的效果
     */
    public static void test6() throws Exception{

        long t2 = System.currentTimeMillis();
        //这个是验证
        List<Integer> result = new ArrayList<>();
        CompletableFuture[] completableFutures = shops.stream().map(shop->CompletableFuture.supplyAsync(Shop::delayRandom,pool))
                .map(future->future.thenAccept(delay->{
                    System.out.println(delay);
                    result.add(delay);
                }))
                //这里要调用stream的终端操作不然不会执行对应的map操作
                .toArray(i->new CompletableFuture[i]);
        //等待completableFutures所有的任务执行完毕，这里一定要调用join方法，不然CompletableFuture的结果未计算完成就输出结果集了
        CompletableFuture.allOf(completableFutures).join();
        System.out.println("time is "+(System.currentTimeMillis()-t2));
        System.out.println(result);
    }


    @Getter
    @Setter
    @AllArgsConstructor
    static class Shop{
        private String name;

        public Future<Double> getPriceAsyncBetter(String product){
            return CompletableFuture.supplyAsync(()->calculatePrice(product));
        }
        /**
         * 异步获取价格 这里创建线程并使用 CompletableFuture 的complete方法来包装结果
         * @param product
         * @return
         */
        public Future<Double> getPriceAsync(String product){
            CompletableFuture<Double> future = new CompletableFuture<>();
            new Thread(()->{
                double result = calculatePrice(product);
                future.complete(result);
            }).start();
            return future;
        }

        public String getPriceStr(String product){
            double price = calculatePrice(product);
            Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
            return String.format("%s:%.2f:%s", name, price, code);
        }

        /**
         * 同步获取价格
         * @param product
         * @return
         */
        public double getPrice(String product){
            return calculatePrice(product);
        }

        private double calculatePrice(String product){
            delay();
            return new Random().nextDouble()*product.charAt(0)+product.charAt(1);
        }

        private static double getRate(){
            delay();
            return new Random().nextDouble();
        }
        public static int delayRandom(){
            try{
                int delay=  new Random().nextInt(2000);
                Thread.sleep(delay);
                return delay;
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }

    }
    public static void delay(){
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }



    static class Discount{
        public enum Code{
            NONE(0),
            SILVER(5),
            GOLD(10),
            PLATINUM(15),
            DIAMOND(20);
            private final int percentage;
            Code(int percentage) {
                this.percentage = percentage;
            }
        }

        public static String applyDiscount(Quote quote){
            return quote.getShopName()+"price is "+apply(quote.getPrice(),quote.getCode());
        }

        private static double apply(double price,Code code){
            delay();
            return price * (100 - code.percentage) / 100;
        }

    }

    static class Quote{
        private String shopName;
        private double price;
        private Discount.Code code;

        public Quote(String shopName,double price,Discount.Code code){
            this.shopName = shopName;
            this.price = price;
            this.code = code;
        }
        public static Quote parse(String priceStr){
            String[] split = priceStr.split(":");
            String shopName = split[0];
            double price = Double.parseDouble(split[1]);
            Discount.Code discountCode = Discount.Code.valueOf(split[2]);
            return new Quote(shopName, price, discountCode);
        }


        public String getShopName() {
            return shopName;
        }

        public double getPrice() {
            return price;
        }

        public Discount.Code getCode() {
            return code;
        }
    }
}
