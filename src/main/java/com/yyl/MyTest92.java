package com.yyl;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/17 0017
 */
public class MyTest92 {
    public static void main(String[] args) throws Exception{
        Map<String,Object> locks = new ConcurrentHashMap();
        Set<String> rollBackResult = new HashSet();
        CountDownLatch count = new CountDownLatch(1);
        CountDownLatch count2 = new CountDownLatch(20);
        User user = new User("yyl29", "hahah");
        for(int i=0;i<20;i++){
             new Thread(()-> {
                 try {
                     String key = user.getPassword()+user.getUsername();
                     count.await();
                     if(!rollBackResult.contains(key)){
                         System.out.println("11111111111111111");
                         locks.putIfAbsent(key, new Object());
                         System.out.println("22222222222222222");
                         synchronized (locks.get(key)) {
                             System.out.println("3333333333333333333");
                             if (!rollBackResult.contains(key)) {
                                 System.out.println("444444444444444");
                                 rollBackResult.add(key);
                             }
                         }
                     }
                 } catch (Exception e) {
                     throw new RuntimeException(e);
                 }
             }).start();
        }
        count.countDown();
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    static class User{
        private String username;
        private String password;
    }
}
