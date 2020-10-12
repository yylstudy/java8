package com.yyl.java8;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description: Optional类使用说明
 * @Author: yang.yonglian
 * @CreateDate: 2019/10/21 19:25
 * @Version: 1.0
 */
public class MyTest5 {
    public static void main(String[] args) throws Exception{
        //声明一个null的Optional对象的引用
        Optional<Person> op = Optional.empty();
        //声明一个非空对象的Optional
        Optional<Person> op1 = Optional.of(new Person(null));
        //声明一个可为空对象的Optional
        Optional<Person> op2 = Optional.ofNullable(getPerson());
        //使用map方法从Optional中提取和转换值
        Insurance insurance = new Insurance("ss");
        Optional.ofNullable(insurance).map(Insurance::getName);
        //使用flatmap方法从Optional中提取和转换值,flatmap和map的区别只是map方法中对传入的对象进行了Optional.ofNullable的包装
        String ss = Optional.ofNullable(getPerson()).flatMap(Person::getCar).flatMap(Car::getInsurance)
                .map(Insurance::getName).orElse("");
        //获取Optional中的值，值为空时抛出异常
        op.get();
        //为空时返回参数对象
        op.orElse(getPerson());
        //orElse的延迟调用版本 因为orElse会先执行参数的方法，
        // orElseGet这样就避免了如果为空就无需执行参数方法（如果参数是个方法的话）
        op.orElseGet(MyTest5::getPerson);
        //为空就抛出异常，这个可以自定义异常
        op.orElseThrow(()->new RuntimeException("为空，报错"));
        //包含的值是否为空
        op.isPresent();
        //如果包含的值不为空，那么执行方法中的lambda
        op.ifPresent(Person::getCar);
        //判断person对象的car属性是否为空
        op.filter(person->person.getCar()!=null).isPresent();
        List<String> rows = Files.lines(Paths.get("D:\\acct_balance2.txt")).filter(StringUtils::isNotBlank).collect(Collectors.toList());
        rows.stream().findFirst().filter(str->str.contains("表头")).orElseThrow(()->new RuntimeException("导入数据第一行必须有表头行"));
    }



    static Person getPerson(){
        Insurance insurance = new Insurance("s");
        Car car = new Car(Optional.ofNullable(insurance),"mycar");
        Person p = new Person(Optional.ofNullable(car));
        return p;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class Person{
        private Optional<Car> car;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    static class Car{
        private Optional<Insurance> insurance;
        private String name;
    }
    @Setter
    @Getter
    @AllArgsConstructor
    static class Insurance{
        private String name;
    }
}
