package com.zengshi.ecp.prom.test.service.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.zengshi.ecp.prom.dubbo.dto.BindDTO;
import com.zengshi.ecp.prom.dubbo.dto.BuyXSendYDTO;
import com.zengshi.ecp.prom.dubbo.dto.CommPromTypeDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-13 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class test {

    public static <T> void test1(Class<T> t){
       
        try {
             T b=t.newInstance();
            System.out.println( t.newInstance().getClass().getName());
            try {
                
                Class.forName(t.newInstance().getClass().getName()).getClass();
                
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static List<?> aa(){
        List<BindDTO> l=new ArrayList<BindDTO>();
        BindDTO bindPromTypeDTO=new BindDTO();
        bindPromTypeDTO.setBindPrice(new BigDecimal(1));
        l.add(bindPromTypeDTO);
        return l;
    }

    /**
     * json转bean
     * 
     * @param jsonStr
     * @param t
     * @return
     * @throws Exception
     * @author huangjx
     */
    protected static <T> T readJson2Bean(String jsonStr, Class<T> t) throws BusinessException {
        T b = null;
        try {
            b = t.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            // e.printStackTrace();
            String[] earr = new String[1];
            earr[0] = t.getName();
            throw new BusinessException("prom.400050", earr);
            // throw new BusinessException(e.toString());
        }
        b = JSON.parseObject(jsonStr, t);
        return b;
    }
    /**
     * @param args
     * @author huangjx
     */
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/ecp-application-context-prom.xml");
        HashMap o=  (HashMap) context.getBean("commPromTypeDTOHashMap");
        CommPromTypeDTO o1=  (CommPromTypeDTO) context.getBean("commPromTypeDTO");
        CommPromTypeDTO uu=o1.translate("buyXSendYPromTypeDTO");
        String json = "{\"buyX\":\"100\",\"sendY\":\"2\"}";
        //json = "{\"orderMoney\":\"100\",\"sendAmount\":\"2\"}";
        BuyXSendYDTO aa=new BuyXSendYDTO();
        aa.setBuyX(new BigDecimal(1111111));
        aa.setSendY(new BigDecimal(222));
        BeanUtils.copyProperties(aa, uu);
        System.out.println( JSON.toJSON(uu));
        
        CommPromTypeDTO uu1= readJson2Bean(json,uu.getClass());
       System.out.println( JSON.toJSON(uu1));
        //BuyXSendYPromTypeDTO bb=(BuyXSendYPromTypeDTO) o1.getPromTypeMap().get("buyXSendYPromTypeDTO");
      //  CommPromTypeDTO commPromTypeDTO=new CommPromTypeDTO();
    /*  BuyXSendYPromTypeDTO b=new BuyXSendYPromTypeDTO();
      b.setBuyX(new BigDecimal(1));
      BeanUtils.copyProperties(b, o);
      System.out.println(o.getClass());*/
      test1(context.getBean("buyXSendYPromTypeDTO").getClass());
      
      HashMap m=new HashMap();
      
     m.put("buyX", new BigDecimal(1));
      //BeanUtils.copyProperties(m, o);
     // System.out.println(o.getClass());
      
         /*  List l= aa();
           System.out.println(l.size());*/
    }

}
