package com.yyl;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流主要方法：
 * 1)filter 过滤
 * 2)distinct 去重,基于元素的equals和hashcode实现
 * 3)limit 截断
 * 4)skip(n) 扔掉前n个元素的流，如果流中的元素不足n个，则返回一个空流
 * 5)map 映射生成一个新的流
 * 6)数组生成流 Arrays.stream()
 * 7)flatMap 把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个新流
 * 8)anyMatch 至少有一个匹配
 * 9)allMatch 每个元素都匹配
 * 10)noneMatch 没有一个元素匹配
 * 11)findAny 返回当前流中的任意元素
 * 12)findFirst 查找第一个元素
 * 13)reduce 归约 List<Integer>.stream().reduce(0,Integer::sum)   (Integer::max、Integer::min、Integer::)
 * 14)count 统计流中元素的个数
 * 15)mapToInt
 * 16)mapToDouble  生成对应的原始流类型  IntStream、DoubleStream、LongStream  这三种流对应的 max、min、sum、average
 * 17)mapToLong
 * 18)IntStream、DoubleStream、LongStream 的boxed方法 从原始类型流转为对象流
 * 19)Files.lines(Paths.get("")) 读取文件每行
 * 20)Stream.iterate(0,n->n+2) 创建一个无限流，后面的值基于前面计算的值
 * 21)collect 下的方法
 * 22)collect(Collectors.maxBy(Comparator.comparing()))  获取集合中根据排序条件的最大值元素
 * 23)collect(Collectors.summingInt()) 集合中某个元素求和
 * 24)collect(Collectors.join(",")) 集合中的字符串根据 ,拼接
 * 25)collect(Collectors.reducing(arg1,arg2,arg3)) 有三个参数 第一个参数是计算的起始值，第二个参数是要计算的值是哪个，第三个是具体的计算方式
 * 26)collect(Collectors.groupingBy())
 * 27)collect(Collectors.groupingBy(Dish::getType,Collectors.couting()))
 * 28)
 * 29)
 * 30)
 *
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/6/19 0019
 */
public class MyTest2 {
    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );

    @Test
    public void test1() throws Exception{
//        menu.stream().filter(dish->dish.getCalories()>300)
//                .limit(3).map(Dish::getName).collect(Collectors.toList());
//        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
//        someNumbers.stream().reduce(Integer::max);
//        someNumbers.stream().reduce(0,Integer::sum);
//        String[] arrayOfWords = {"Goodbye", "World"};
//        Arrays.stream(arrayOfWords)
//                .flatMap(word->Arrays.stream(word.split("")))
//                .distinct().forEach(System.out::println);
//        Files.lines(Paths.get("D:\\1.txt"))
//                .flatMap(line->Arrays.stream(line.split("")))
//                .distinct().forEach(System.out::println);
//        Stream.iterate(0, n->n+2).limit(10).forEach(System.out::println);
//        Stream.iterate(new Integer[]{0,1}, s->new Integer[]{s[0]+s[1],s[0]+s[1]+s[1]})
//                .limit(10)
//                .flatMap(Arrays::stream).forEach(System.out::println);
//        System.out.println(menu.stream().collect(Collectors.counting()));
//        menu.stream().collect(Collectors
//                .maxBy(Comparator.comparing(Dish::getCalories)));
//        menu.stream().collect(Collectors.summingInt(Dish::getCalories));
//        menu.stream().map(Dish::getName).collect(Collectors.joining(","));
//        menu.stream().collect(Collectors.reducing("",Dish::getName,String::join));
//        System.out.println(menu.stream().collect(Collectors.reducing(0,Dish::getCalories,Integer::sum)));
//        System.out.println(menu.stream().map(Dish::getCalories).reduce(0,Integer::sum));
//        menu.stream().collect(Collectors.groupingBy(Dish::getType))
//                .forEach((k,v)-> System.out.println("key:"+k+",value:"+v));
//        menu.stream().collect(Collectors.groupingBy(dish->{
//            if(dish.getCalories()<400){
//                return "diet";
//            }else if(dish.getCalories()>700){
//                return "fat";
//            }
//            return "normal";
//        })).forEach((k,v)-> System.out.println("key:"+k+",value:"+v));
//        menu.stream().collect(Collectors.groupingBy(dish->{
//            if(dish.getCalories()<400){
//                return "diet";
//            }else if(dish.getCalories()>700){
//                return "fat";
//            }
//            return "normal";
//        }));
//        menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.counting()));
//        long t1 = System.currentTimeMillis();
//        Stream.iterate(0, i->{
//            try{
//                Thread.sleep(1);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return i+1;
//        }).limit(10000).reduce(Integer::sum);
//        System.out.println(System.currentTimeMillis()-t1);
        String[] ss = {"11","22","33"};
        Map<String,List<String>> map = new HashMap();
        map.put("11", Arrays.asList("55","66"));
        map.put("22", Arrays.asList("77","88"));
        map.put("33", Arrays.asList("99","00"));
        List<String> list = Arrays.stream(ss).flatMap(s->map.get(s).stream()).collect(Collectors.toList());
        System.out.println(list);
    }

}
