package com.yyl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/6/21 0021
 */
public class MyTest4 {
    public static void main(String[] args) throws Exception{
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        //找出2011年发生的所有交易，并按交易额排序（从高到低）。
        List<Transaction> list1 = transactions.stream()
                .filter(t->t.getYear()==2011)
                .sorted(Comparator.comparing(Transaction::getValue).reversed())
                .collect(Collectors.toList());
        System.out.println(list1);
        //交易员都在哪些不同的城市工作过？
        transactions.stream().map(t->t.getTrader().getCity()).distinct().forEach(System.out::println);
        //查找所有来自于剑桥的交易员，并按姓名排序。
        transactions.stream().map(Transaction::getTrader)
                .filter(t->"Cambridge".equals(t.getCity()))
                .sorted(Comparator.comparing(Trader::getName)).forEach(System.out::println);
        //返回所有交易员的姓名字符串，按字母顺序排序。
        transactions.stream().map(t->t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(","));
        //有没有交易员是在米兰工作的？
        transactions.stream().anyMatch(t->"Milan".equals(t.getTrader().getCity()));
        //打印生活在剑桥的交易员的所有交易额。
        transactions.stream().filter(t->"Cambridge".equals(t.getTrader().getCity()));
        //所有交易中，最高的交易额是多少？
        transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        //找到交易额最小的交易。
        Optional<Transaction> optional = transactions.stream().sorted(Comparator.comparing(Transaction::getValue)).findFirst();
    }

}
