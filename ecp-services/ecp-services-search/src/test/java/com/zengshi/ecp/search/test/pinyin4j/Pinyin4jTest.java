package com.zengshi.ecp.search.test.pinyin4j;

import org.junit.Test;

import com.zengshi.ecp.search.index.util.Pinyin4jUtil;

public class Pinyin4jTest {
    
    @Test
    public void test() {
        String str = "长沙市长huang端feng";

//        List<String> result = Pinyin4jUtil.converterToSpell(str);
//        System.out.println(str + " pin yin ：" + result);
//
//        result = Pinyin4jUtil.converterToFirstSpell(str);
//        System.out.println(str + " short pin yin ：" + result);
        
        System.out.println(Pinyin4jUtil.getPinYin(str));
        System.out.println(Pinyin4jUtil.getPinYinHeadChar(str));
    }

}

