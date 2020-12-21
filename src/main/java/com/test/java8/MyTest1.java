package com.test.java8;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author yang.yonglian
 * @ClassName: com.test
 * @Description: 集合排序
 * @Date 2019/6/19 0019
 */
public class MyTest1 {
    public static void main(String[] args) throws Exception{
        Animal a1 = new Animal();
        List<String> list = new ArrayList<>();
        List<Animal> list2 = new ArrayList<>();
        //基础类型排序
        list.sort(String::compareTo);
        //按照对象的字段进行排序 比较器链   先按照name降序 再按照age升序
        list2.sort(Comparator.comparing(Animal::getName).reversed().
                thenComparing(Animal::getAge));
    }
    @Getter
    @Setter
    static class Animal{
        private String name;
        private String age;
    }
}
