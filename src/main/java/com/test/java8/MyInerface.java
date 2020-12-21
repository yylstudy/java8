package com.test.java8;

import java.util.Arrays;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/19 15:22
 * @Version: 1.0
 */
public interface MyInerface {
    default void test1(){
        Arrays.stream(this.getClass().getEnumConstants()).forEach(System.out::println);
    }
}
