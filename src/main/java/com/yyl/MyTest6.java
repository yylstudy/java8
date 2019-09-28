package com.yyl;

import lombok.Getter;
import lombok.ToString;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/7/2 0002
 */
public class MyTest6 {
    public static void main(String[] args) throws Exception{
        int ss = Files.lines(Paths.get("D:\\success.log"))
                .mapToInt(line->Integer.parseInt(line.split(",")[1].split(":")[1]))
                .sum();
        Optional<Long> startTime = Files.lines(Paths.get("D:\\success.log")).findFirst()
                .map(line->Long.parseLong(line.split(",")[2].split(":")[1]));
        System.out.println(startTime.get());
        int length = Files.lines(Paths.get("D:\\success.log")).collect(Collectors.toList()).size();
        List<Long> endTime = Files.lines(Paths.get("D:\\success.log")).skip(length-1)
                .map(line->Long.parseLong(line.split(",")[2].split(":")[1])).collect(Collectors.toList());
        System.out.println(ss/((endTime.get(0)-startTime.get())/1000));
    }


}
