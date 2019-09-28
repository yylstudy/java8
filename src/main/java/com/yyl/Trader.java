package com.yyl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/6/20 0020
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Trader {
    private final String name;
    private final String city;
}
