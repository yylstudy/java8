package com.yyl.java8.test2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-09-18
 */
public class Car extends Vehicle{
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User(1,2));
        list.add(new User(3,1));
        list.add(new User(3,2));
        list.add(new User(2,3));
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if(o1.getAge()>o2.getAge()){
                    return 1;
                }else if(o1.getAge()==o2.getAge()){
                    if(o1.getHeight()>o2.getHeight()){
                        return -1;
                    }
                    return 1;
                }
                return -1;
            }
        });
        System.out.println(list);
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    static class User{
        private int age;
        private int height;
    }
}
