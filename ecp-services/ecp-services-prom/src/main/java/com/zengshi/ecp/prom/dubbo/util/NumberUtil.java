package com.zengshi.ecp.prom.dubbo.util;

import java.math.BigDecimal;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-9-27 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class NumberUtil {
    
    /**
     * 传入value  四舍五入 返回没有小数点数据  默认
     * @param value
     * @return
     * @author huangjx
     */
    public static BigDecimal tranlateNum(BigDecimal value) {
        
        if(value==null){
            return value;
        }
        //BigDecimal.ROUND_HALF_UP; BigDecimal.ROUND_CEILING
        //BigDecimal.ROUND_HALF_DOWN
        BigDecimal reValue=  value.setScale(0, BigDecimal.ROUND_HALF_UP);
        return reValue;
    }
    /**
     * 传入value  四舍五入 返回没有小数点数据  默认
     * @param value
     * @return
     * @author huangjx
     */
    public static BigDecimal divideNum(BigDecimal value,BigDecimal v100) {
        
        if(value==null){
            return value;
        }
        //BigDecimal.ROUND_HALF_UP; BigDecimal.ROUND_CEILING
        //BigDecimal.ROUND_HALF_DOWN
        if(v100==null){
            v100=new BigDecimal(100);
        }
        
        BigDecimal reValue=  value.divide(v100).setScale(2, BigDecimal.ROUND_HALF_UP);
        return reValue;
    }
}
