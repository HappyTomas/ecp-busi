package com.zengshi.ecp.search.dubbo.search.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class PatternMatcher {

    public static boolean match(String regex, String input) {
        if (StringUtils.equals(regex, input)) {
            return true;
        }

        // 把含有通配符的字符串转化为标准正则表达式
        regex = regex.replace("*", ".*");
        regex = regex.replace("?", ".?");
        regex = regex.replace("+", ".+");
        
//        System.out.println("通配符的字符串转化为标准正则表达式结果:"+regex);

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // 完全匹配
        return matcher != null && matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(match("discountPriceOfLevel", "discountPriceOfLevel"));
        System.out.println(match("discountPriceOfLevel*", "discountPriceOfLevel0"));
        System.out.println(match("discountPriceOfLevel*", "discountPriceOfLevel01"));
        System.out.println(match("discountPriceOfLevel*", "discountPriceOfLeve01"));

    }

}
