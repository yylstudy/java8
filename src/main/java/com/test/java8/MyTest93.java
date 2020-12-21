package com.test.java8;


import com.alibaba.fastjson.JSONObject;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/3/12 16:17
 * @Version: 1.0
 */
public class MyTest93 {

    public static void main(String[] args) throws Exception{
        List<String> list = Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");
        list = list.stream().skip(2).limit(5).collect(Collectors.toList());
        System.out.println(list);
    }

}

