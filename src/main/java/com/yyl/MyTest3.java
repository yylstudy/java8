package com.yyl;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: 根据下标分组
 * @Date 2019/7/1 0001
 */
public class MyTest3 {
    public static void main(String[] args) throws Exception{
        List<Integer> list = Arrays.asList(4,2,9,7,5,6,1,8,3);
        Map<Integer,List<Integer>> map = IntStream.range(0,list.size())
                .mapToObj(i->new AbstractMap.SimpleEntry<Integer,Integer>(i,list.get(i)))
                .collect(Collectors.groupingBy(enrty->enrty.getKey()/10,
                        Collectors.mapping(AbstractMap.SimpleEntry::getValue,Collectors.toList())));
        map.forEach((k,v)->{
            System.out.println("key:"+k+",value:"+v);
        });
    }
}
