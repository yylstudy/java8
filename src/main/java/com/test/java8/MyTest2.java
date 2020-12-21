package com.test.java8;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Author yang.yonglian
 * @ClassName: com.test
 * @Description: 符合lambda表达式
 * @Date 2019/6/21 0021
 */
public class MyTest2 {
    public static void main(String[] args) throws Exception{
        List<Apple> list = getApple();
        //红苹果的Predicate
        Predicate<Apple> redApplePredicate = a->a.getColor().equals("red");
        //非红苹果的Predicate且重量大于2的苹果 （或者颜色等于白色的苹果）
        //这里的执行顺序是从左向右确定优先级的  a or b and c 可以看做 (a||b)&&c
        //谓词符合的好处就是代码可读性强
        Predicate<Apple> resultPredicate = redApplePredicate.negate()
                .and(a->a.getHeight()>2).or(a->a.getColor().equals("yellow"));
        list.stream().filter(resultPredicate).forEach(System.out::println);

        //针对Function的函数复合  andThen用法
        Function<String,String> writeLetter = MyTest2::addHeader;
        writeLetter.andThen(MyTest2::addFooter);
    }

    public static String addHeader(String text){
        return "From test: "+text;
    }

    public static String addFooter(String text){
        return text + " Kind regards";
    }


    public static List<Apple> getApple(){
        List<Apple> list = new ArrayList<>();
        Apple apple1 = new Apple("red",2);
        list.add(apple1);
        apple1 = new Apple("yellow",1);
        list.add(apple1);
        apple1 = new Apple("greed",4);
        list.add(apple1);
        apple1 = new Apple("white",3);
        list.add(apple1);
        return list;
    }
    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    static class Apple{
        private String color;
        private int height;
    }

}
