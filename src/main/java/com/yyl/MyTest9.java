package com.yyl;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/7/19 0019
 */
public class MyTest9 {
    static Test ss = new Test("3");
    public static void main(String[] args) throws Exception{
        new Thread(()->{
            try{
                Test s = ss;
                Thread.sleep(1000);
                System.out.println(s.toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try{
                Thread.sleep(10);
                ss = null;
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();;
    }
    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    static class Test{
        private String name;
    }

}
