package com.yyl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/1 0001
 */
public class MyTest91 {
    private static ExecutorService pool = Executors.newFixedThreadPool(5);
    public static void main(String[] args) throws Exception{
        List<CompletableFuture<String>> list = IntStream.range(0, 100).mapToObj(i->{
            System.out.println(i);
            CompletableFuture<String> future = CompletableFuture.supplyAsync(()->new Random().nextInt(100)+""+i, pool);
            return future;
        }).collect(Collectors.toList());
        System.out.println(list.stream().map(CompletableFuture::join).collect(Collectors.toList()));
    }

}
