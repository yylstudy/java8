package com.yyl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/7/18 0018
 */
public class MyTest8 {
    public static void main(String[] args) throws Exception{
        Map map = new HashMap();
        List<Map.Entry> list = Files.lines(Paths.get("D:\\2.txt")).map(line->{
            String[] ss = line.split("->");
            int start =  ss[0].indexOf("\"");
            int end =  ss[0].lastIndexOf("\"");
            String key = ss[0].substring(start+1,end);
            String value = ss[1];
            return new HashMap.SimpleEntry(key,value.trim());
        }).collect(Collectors.toList());
        List<Map.Entry> list2 = Files.lines(Paths.get("D:\\1.txt"))
                .skip(2)
                .filter(line->line.split("=").length>1)
                .map(line->{
                String[] ss = line.split("=");
                String key = ss[0].split(":")[0];
                String value = ss[1];
                value = value.replaceAll("\\[", "");
                value = value.replaceAll("\\]", "");
                value = value.replaceAll(";", "");
                return new HashMap.SimpleEntry(key,value.trim());
        }).collect(Collectors.toList());
        for(Map.Entry entry2:list2){
            boolean isSome = false;
            for(Map.Entry entry1:list){
                if(entry1.getKey().equals(entry2.getKey())&&entry1.getValue().equals(entry2.getValue())){
                    isSome = true;
                    break;
                }
            }
            if(!isSome){
                System.out.println(entry2.getKey());
            }
        }


    }
}
