package com.yyl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.org.apache.xalan.internal.utils.FeatureManager;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/9/10 0010
 */
public class MyTest93 {
    public static void main(String[] args) throws Exception {
        String row = "||||||";
        String[] strArr = row.split("\\|",-1);
        System.out.println(strArr.length);
        System.out.println("".equals(strArr[0]));
        System.out.println(strArr[0]+"------------");
    }
    @Setter
    @Getter
    @ToString
    static class Animal{
        private String name;
        private String zge;
        private java.util.Date date1 ;
        private java.sql.Date date2 ;
    }

}
