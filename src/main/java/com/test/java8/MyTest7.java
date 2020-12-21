package com.test.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

/**
 * @Description: LocalDate使用
 * @Author: yang.yonglian
 * @CreateDate: 2019/11/2 17:05
 * @Version: 1.0
 */
public class MyTest7 {
    public static void main(String[] args) {
        //新建一个日期对象，2019-11-11
        LocalDate localDate = LocalDate.of(2019,11,11);
        //字符串转日期
        System.out.println(LocalDate.parse("2018-08-08"));

        //当前时间属于一年中的第多少天
        System.out.println(localDate.getDayOfYear());
        //获取年份 2019
        System.out.println(localDate.getYear());
        //获取月份 11
        System.out.println(localDate.getMonthValue());
        //获取天 11（月）
        System.out.println(localDate.getDayOfMonth());
        //获取天 （周）
        System.out.println(localDate.getDayOfWeek().getValue());
        //是否是闰年
        System.out.println(localDate.isLeapYear());
        //当前月多少天
        System.out.println(localDate.lengthOfMonth());
        //当天年多少天
        System.out.println(localDate.lengthOfYear());
        //获取当月第一天
        System.out.println(localDate.with(TemporalAdjusters.firstDayOfMonth()));
        //获取当月最后一天
        System.out.println(localDate.with(TemporalAdjusters.lastDayOfMonth()));

        //获取当月第一天 00:00:00
        LocalDateTime localDateTime = LocalDate.now().withDayOfMonth(1).atTime(LocalTime.of(0,0,0));
        System.out.println(localDateTime);

        //根据当前LocalDate修改年份    with方法都是修改LocalDate的某个属性使用的
        LocalDate newLocalDate = localDate.withYear(2018);
        System.out.println(newLocalDate);

    }
}
