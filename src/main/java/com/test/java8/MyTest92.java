package com.test.java8;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/2/25 20:58
 * @Version: 1.0
 */
public class MyTest92 {
    public static void main(String[] args) throws Exception{
        List<User> userList = new ArrayList();
        userList.add(new User(1));
        userList.add(new User(1));
        //添加元素
        userList =  userList.stream().filter(distinctByKey(user->user.getUserId())).collect(Collectors.toList());
        System.out.println(userList.size());
    }


    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> {
            boolean b = seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
            System.out.println(b);
            return b;
        };
    }
    @Getter
    @Setter
    @AllArgsConstructor
    static class User{
        private Integer userId;
    }

}
