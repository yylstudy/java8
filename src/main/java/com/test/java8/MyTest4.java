package com.test.java8;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author yang.yonglian
 * @ClassName: com.test
 * @Description: 根据List下表分组
 * @Date 2019/7/1 0001
 */
public class MyTest4 {
    public static void main(String[] args) throws Exception{
        List<Integer> list = Arrays.asList(4,3,6,7,1,9,8);
        Map<Integer,List<Integer>> map = IntStream.range(0,list.size()).mapToObj(i->new AbstractMap.SimpleEntry<>(i,list.get(i)))
                .collect(Collectors.groupingBy(entry->entry.getKey()/3,Collectors.mapping(AbstractMap.SimpleEntry::getValue,Collectors.toList())));
        System.out.println(map);
    }
}
