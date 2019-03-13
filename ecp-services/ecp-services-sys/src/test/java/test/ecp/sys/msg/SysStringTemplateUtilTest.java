/** 
 * Project Name:ecp-services-sys 
 * File Name:SysStringTemplateUtilTest.java 
 * Package Name:test.ecp.sys.msg 
 * Date:2016年3月15日上午11:18:13 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package test.ecp.sys.msg;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.zengshi.ecp.sys.dubbo.util.SysStringTemplateUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月15日上午11:18:13  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class SysStringTemplateUtilTest {

    /**
     * Test method for {@link com.zengshi.ecp.sys.dubbo.util.SysStringTemplateUtil#genString(java.lang.String, java.util.Map)}.
     */
    @Test
    public void testGenString() {
        
        String template = "select * from $table$";
        //String template = "";
        Map<String,String> params = new HashMap<>();
        
        params.put("table","T_DEPT");
        
        System.out.println(SysStringTemplateUtil.genString(template, params));
    }
    
    @Test
    public void testGenStringBy(){
        String template = "select * from $table$";
        Map<String,String> params = new HashMap<>();
        
        //params.put("table","T_DEPT");
        
        System.out.println(SysStringTemplateUtil.genString(template, params));
    }
}

