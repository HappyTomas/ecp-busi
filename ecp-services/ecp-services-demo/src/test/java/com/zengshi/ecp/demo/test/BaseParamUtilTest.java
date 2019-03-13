/** 
 * Project Name:ecp-services-demo 
 * File Name:BaseParamUtilTest.java 
 * Package Name:com.zengshi.ecp.demo.test 
 * Date:2015-8-27下午4:02:59 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.demo.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-8-27下午4:02:59  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class BaseParamUtilTest extends EcpServicesTest {

    /**
     * Test method for {@link com.zengshi.ecp.sys.dubbo.util.BaseParamUtil#fetchParamList(java.lang.String)}.
     */
    @Test
    public void testFetchParamListString() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.zengshi.ecp.sys.dubbo.util.BaseParamUtil#fetchParamList(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testFetchParamListStringString() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.zengshi.ecp.sys.dubbo.util.BaseParamUtil#fetchParamValue(java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testFetchParamValue() {
        String key = "SYS_MENU_TYPE";
        String code = "2";
        System.out.println(BaseParamUtil.fetchParamValue(key, code));
    }

    /**
     * Test method for {@link com.zengshi.ecp.sys.dubbo.util.BaseParamUtil#fetchParamDTO(java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testFetchParamDTO() {
        fail("Not yet implemented");
    }

}

