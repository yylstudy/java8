//package com.yyl;
//
//import java.util.*;
//import java.util.concurrent.CompletableFuture;
//import java.util.stream.Collectors;
//
///**
// * @Author yang.yonglian
// * @ClassName: com.yyl
// * @Description: TODO(这里描述)
// * @Date 2019/7/1 0001
// */
//public class MyTest5 {
//    public static void main(String[] args){
//        long start = System.currentTimeMillis();
//        System.out.println(findPrices("myPhone27S"));
//        long duration = (System.currentTimeMillis() - start);
//        System.out.println("Done in " + duration + " msecs");
//    }
//
//    public static List<String> findPrices(String product){
//        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
//                new Shop("LetsSaveBig"),
//                new Shop("MyFavoriteShop"),
//                new Shop("MyFavoriteShop1"),
////                new Shop("MyFavoriteShop2"),
//                new Shop("BuyItAll"));
//        return shops.stream()
//                .parallel()
//                .map(shop->String.format("%s price is %.2f",
//                shop.getName(),shop.getPrice(product))).collect(Collectors.toList());
//    }
//
//    public static List<String> findPricesAsync(String product){
//        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
//                new Shop("LetsSaveBig"),
//                new Shop("MyFavoriteShop"),
//                new Shop("BuyItAll"));
//        List<CompletableFuture<String>>  list1 = shops.stream()
//                .map(shop->CompletableFuture.supplyAsync(()->String.format("%s price is %.2f",
//                        shop.getName(),shop.getPrice(product)))).collect(Collectors.toList());
//        return list1.stream().map(CompletableFuture::join).collect(Collectors.toList());
//    }
//
//
//
//}
