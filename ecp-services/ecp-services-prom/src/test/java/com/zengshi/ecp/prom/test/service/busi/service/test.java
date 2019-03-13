package com.zengshi.ecp.prom.test.service.busi.service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.drools.core.util.StringUtils;

import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-29 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class test {
    /* 
    * 16进制数字字符集 
    */ 
    private static String hexString="0123456789ABCDEF"; 
    /* 
    * 将字符串编码成16进制数字,适用于所有字符（包括中文） 
    */ 
    public static String encode(String str) 
    { 
    //根据默认编码获取字节数组 
    byte[] bytes=str.getBytes(); 
    StringBuilder sb=new StringBuilder(bytes.length*2); 
    //将字节数组中每个字节拆解成2位16进制整数 
    for(int i=0;i<bytes.length;i++) 
    { 
    sb.append(hexString.charAt((bytes[i]&0xf0)>>4)); 
    sb.append(hexString.charAt((bytes[i]&0x0f)>>0)); 
    } 
    return sb.toString(); 
    } 
    /* 
    * 将16进制数字解码成字符串,适用于所有字符（包括中文） 
    */ 
    public static String decode(String bytes) 
    { 
    ByteArrayOutputStream baos=new ByteArrayOutputStream(bytes.length()/2); 
    //将每2位16进制整数组装成一个字节 
    for(int i=0;i<bytes.length();i+=2) 
    baos.write((hexString.indexOf(bytes.charAt(i))<<4 |hexString.indexOf(bytes.charAt(i+1)))); 
    return new String(baos.toByteArray()); 
    } 

    /**
     * @param args
     * @author huangjx
     */
    public static void main(String[] args) {
        
        test t=new test();
        System.out.println(t.encode("isbn"));
        
        System.out.println(t.decode(t.encode("isbn")));
        
        
        /*
        if(Long.valueOf(2).equals(new Long(2))){
            System.out.println("true");
            
        }
        BigDecimal value=new BigDecimal(1234.5678).multiply(new BigDecimal(100));
        BigDecimal value1= value.setScale(0, BigDecimal.ROUND_CEILING);
        System.out.println(value1);
        
        OrdPromDTO d=new OrdPromDTO();
        
        
        String msg="asdf%s啥的礼服 ";
        String pp=String.format(msg, null);
        System.out.println(pp);
        BigDecimal aaa=BigDecimal.ZERO;
        System.out.println(aaa);
        PromGroupDTO aa=new PromGroupDTO();
        System.out.println(aa.getSiteId());
        aa.setSiteId(null);
        System.out.println(aa.getSiteId());
        System.out.println(aa);
        
        Long a = null;
        System.out.println(a);*/
        
      /*  PromRuleCheckDTO  promRuleCheckDTO=new PromRuleCheckDTO();
        promRuleCheckDTO.setAreaValue("1");
        promRuleCheckDTO.setSkuId(new Long(1));
        
        PromRuleCheckDTO  promRuleCheckDTO1=new PromRuleCheckDTO();
        
        ObjectCopyUtil.copyObjValue(promRuleCheckDTO, promRuleCheckDTO1, "", true);
        System.out.println(promRuleCheckDTO1.getSkuId());
        System.out.println(promRuleCheckDTO1.getAreaValue());
        
        System.out.println(org.apache.commons.lang.StringUtils.contains("112", "112"));
        System.out.println(org.apache.commons.lang.StringUtils.contains("33", "33"));
        System.out.println(org.apache.commons.lang.StringUtils.contains("33", "33"));
        System.out.println(org.apache.commons.lang.StringUtils.contains(null, "33"));
        System.out.println(org.apache.commons.lang.StringUtils.contains("1", null));
        System.out.println(org.apache.commons.lang.StringUtils.contains("1", ""));
        System.out.println(org.apache.commons.lang.StringUtils.contains("", ""));
        System.out.println(org.apache.commons.lang.StringUtils.contains(null, null));*/
        
    }

    
    
    
}
