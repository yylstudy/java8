package com.yyl.java8;


import com.yyl.sourcecode.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/3/12 16:17
 * @Version: 1.0
 */
public class MyTest93 {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User user = new User("yyl1",1);
        list.add(null);
        list.add(user);
        user = new User("yyl2",2);
        list.add(user);
        user = new User("yyl1",1);
        list.add(user);
        user = new User("yyl2",2);
        list.add(user);
        Map<String, User> map = list.stream().collect(Collectors.groupingBy(User::getUsername,
                Collectors.collectingAndThen(Collectors.reducing((o1, o2)->{
                    o1.setPrice(o1.getPrice()+o2.getPrice());
                    return o1;
                }),Optional::get)));
        System.out.println(map);
    }

    @Data
    @AllArgsConstructor
    static class User{
        private String username;
        private Integer price;
    }

}

