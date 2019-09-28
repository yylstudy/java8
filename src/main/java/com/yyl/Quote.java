package com.yyl;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/7/9 0009
 */
@AllArgsConstructor
@Getter
public class Quote {
    private final String shopName;
    private final double price;
    private final Discount.Code discountCode;
    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }
}
