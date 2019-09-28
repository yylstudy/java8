package com.yyl;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/7/9 0009
 */
@AllArgsConstructor
@Getter
public class Shop {
    private String name;
    public static List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));

    public static void main(String[] args){
        long t1 = System.currentTimeMillis();
//        List<String> list = shops.stream().map(shop->shop.getPriceMany("hahaha"))
//                .map(Quote::parse).map(Discount::applyDiscount)
//                .collect(Collectors.toList());
//        System.out.println("time "+(System.currentTimeMillis()-t1));
//        System.out.println(list);
        findPriceStream("hhaha").map(future->future.thenAccept(System.out::println));
        System.out.println("time "+(System.currentTimeMillis()-t1));
    }

    public static Stream<CompletableFuture<String>> findPriceStream(String product){
        Executor pool = Executors.newFixedThreadPool(30);
        Stream<CompletableFuture<String>> stream = shops.stream()
                .map(shop->CompletableFuture.supplyAsync(()->shop.getPriceMany("hahaha"),pool))
                .map(future->future.thenApply(Quote::parse))
                .map(future->future.thenCompose(quote->CompletableFuture.supplyAsync(()->Discount.applyDiscount(quote),pool)));
        return stream;
    }

    public static List<String> findPrices(String product){
        return shops.parallelStream().map(shop->String.format("%s price is %.2f",
                shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
    }

    public static List<String> findPricesAsync(String product){
        Executor pool = Executors.newFixedThreadPool(20);
        List<CompletableFuture<String>> list = shops.stream().map(shop->CompletableFuture.supplyAsync(()->
                String.format(("%s price is %.2f"),shop.getName(), shop.getPrice(product)),pool))
                .collect(Collectors.toList());
        return list.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
    public  String getPriceMany(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[
                new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", product, price, code);
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }
    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> future = new CompletableFuture();
        new Thread(()->{
            double price = calculatePrice(product);
            future.complete(price);
        }).start();
        return future;
    }
    public Future<Double> getPriceAsync2(String product){
        return CompletableFuture.supplyAsync(()->calculatePrice(product));
    }

}
