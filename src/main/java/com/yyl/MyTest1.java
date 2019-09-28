package com.yyl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/6/19 0019
 */
public class MyTest1 {
    public static void main(String[] args) throws Exception{
        double s = Files.lines(Paths.get("D:\\tmp\\wlw\\wlw_adjust\\Media_IOTBILL.ADJ.201905.001.991"))
                .skip(1)
                .map(str->Double.parseDouble(str.split("\\|")[3]))
                .reduce(0.00, (a,b)->{
                    System.out.println(Math.abs(a));
                    System.out.println(Math.abs(b));
                    return (Math.abs(a)+Math.abs(b));})*0.01;
        System.out.println(s);
    }
}
