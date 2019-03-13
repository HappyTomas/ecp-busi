/** 
 * Project Name:ecp-services-sys 
 * File Name:SysStringTemplateUtil.java 
 * Package Name:com.zengshi.ecp.sys.dubbo.util 
 * Date:2016年3月15日上午11:12:30 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.dubbo.util;

import java.util.Map;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateErrorListener;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: 使用字符串模板生成字符串<br>
 * Date:2016年3月15日上午11:12:30  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class SysStringTemplateUtil {
    
    public static String genString(String template, Map<String,String> params){
        
        /*ST st = new ST(template);
        if(params == null || params.isEmpty()){
            
        } else {
            for(String key : params.keySet()){
                st.add(key, params.get(key));
            }
        }
        return st.render();*/
        
        StringTemplate st  = new StringTemplate(template);
       
        st.setAttributes(params);
        return st.toString();
        
    }
}

