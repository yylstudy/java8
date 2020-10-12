package com.yyl.java8;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: stream 函数式数据处理
 * 1)filter 过滤
 * 2)distinct 去重
 * 3)limit 截取前n个元素
 * 4)skip 跳过前n个元素
 * 5)count 统计流中元素的个数
 * 6)empty 流是否为空
 * 6)map menu.stream().map(Dish::getName).map(String::length).collect(Collectors.toList());
 *   映射，这个可以多个map，这样就可以使用方法引用了
 * 7)flatmap 可以把流中的每个元素映射成一个流，然后把所有的流连接起来成为一个流
 *   流的扁平化 words.stream().flatMap(word->Arrays.stream(word.split(""))).distinct().forEach(System.out::println);
 * 8)anyMatch stream中是否有一个匹配
 * 9)allMatch stream中所有元素都匹配
 * 10)noneMatch stream中没有元素匹配
 * 11)findAny 返回stream中任意的元素
 *    可利用 filter和findAny结合实现查找元素的目的  menu.stream().filter(d->d.isVegetarian()==true).findAny().isPresent();
 * 12)findFirst 返回stream中的第一个元素
 * 13)reduce 规约操作 numbers.stream().reduce(0,(a,b)->a+b); 计算集合中的元素值总和
 *   numbers.stream().reduce(Integer::max); 集合中元素最大值
 *
 *   数值流
 * 14)IntStream  使用IntStream可以避免装箱操作
 *    菜单中的热量总和 menu.stream().mapToInt(Dish::getCalories).sum();
 *    boxed 转换为 Stream<Integer> 的对象流
 *    IntStream.range(1,100) 生成1到100(不包含100 rangeClose方法则包含100)的数值流
 *    mapToObj IntStream直接转换为对象流
 * 15)DoubleStream
 * 16)LongStream
 * 17)Files.line(Path.get("")) 从文件中获取流 注意这里会获取每一行 如果不需要空 就需要filter 空字符串
 * 18)Stream.iterate(0,i->i+2).limit(10) 生成无限流再截取前10个元素 根据前一个元素生成后面的元素
 * 19)Stream.generate() 生成流
 *    collect 规约和汇总
 * 20)查找流中的最大值和最小值 menu.stream().collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)))
 * 21)流中元素求和 menu.stream().collect(Collectors.summingInt(Dish::getCalories))
 * 22)链接字符串 menu.stream().map(Dish::getName).collect(Collectors.joining(","));
 *    广义上的规约
 * 23)Collector.reducing  menu.stream().collect(Collectors.reducing((d1,d2)->d1.getCalories()>=d2.getCalories()?d2:d1))
 *    reducing 有三种重载方法
 *    reducing(BinaryOperator<T> op)
 *    reducing(T identity, BinaryOperator<T> op) 接受初始值
 *    reducing(U identity,Function<? super T, ? extends U> mapper,BinaryOperator<U> op) 接受初始值要转化的值 可以理解为map
 *    分组
 * 24)groupingBy menu.stream().collect(Collectors.groupingBy(Dish::getType)); 单级分组
 *    根据子组收集数据 menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparing(Dish::getCalories))));
 * 25)Collectors.collectingAndThen 把收集器的结果转换为另一种类型
 *   menu.stream().collect(Collectors.groupingBy(Dish::getType,
 *                 Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)));
 *
 * 26)和groupingBy常常组合的一种收集器 Collectors.mapping 这个方法接受两个参数 第一个参数对流中的元素做变换，第二个参数是将变换的结果收集起来
 * 27) Collectors.partitioningBy 特殊的分组器，只包含两组 一组是true,一组是false  menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian))
 *
 * @Author: yang.yonglian
 * @CreateDate: 2019/10/10 20:16
 * @Version: 1.0
 */
public class MyTest3 {
    public static void main(String[] args) {

    }

    static List<Integer> numbers = Arrays.asList( 1,2,5,3,6 );
    static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Type.MEAT),
            new Dish("beef", false, 700, Type.MEAT),
            new Dish("chicken", false, 400, Type.MEAT),
            new Dish("french fries", true, 530, Type.OTHER),
            new Dish("rice", true, 350, Type.OTHER),
            new Dish("season fruit", true, 120, Type.OTHER),
            new Dish("pizza", true, 550, Type.OTHER),
            new Dish("prawns", false, 300, Type.FISH),
            new Dish("salmon", false, 450,Type.FISH) );

    @Setter
    @Getter
    @ToString
    @AllArgsConstructor
    public static class Dish {
        private final String name;
        private final boolean vegetarian;
        private final int calories;
        private final Type type;


    }

    public enum Type { MEAT, FISH, OTHER }
}
