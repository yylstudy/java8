package com.yyl;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/7/18 0018
 */
public class MyTest7 {
    public static void main(String[] args){
        ConcurrentHashMap<Long,Object> lock1 = new ConcurrentHashMap<>();
        long s = 38888;
        lock1.put(s,"ss");
        long ss = 38888;
        System.out.println(lock1.get(ss));
    }
}
