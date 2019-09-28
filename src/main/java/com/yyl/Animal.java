package com.yyl;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/8/17 0017
 */
public interface Animal {
    default void say(){
        System.out.println("animal say hahaha");
    }
}
