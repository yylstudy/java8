package com.test.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Description: LocalTime和LocalDateTime的使用
 * @Author: yang.yonglian
 * @CreateDate: 2019/11/2 17:42
 * @Version: 1.0
 */
public class MyTest8 {
    public static void main(String[] args) {
        //创建一个时间对象
        LocalTime time = LocalTime.of(11,46,03);
        LocalDate localDate = LocalDate.of(2018,11,12);
        //字符串转时间对象
        System.out.println(LocalTime.parse("08:09:10"));
        //获取小时
        System.out.println(time.getHour());
        //获取分钟
        System.out.println(time.getMinute());
        //获取秒数
        System.out.println(time.getSecond());
        //新建一个LocalDateTime对象
        System.out.println(LocalDateTime.of(2018,11,12,13,14,15));

        //时间对象合并日期对象
        System.out.println(time.atDate(localDate));
        System.out.println(localDate.atTime(time));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

    }
}
