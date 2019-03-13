/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoSVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015-7-31下午5:17:01 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.demo.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.demo.dao.model.DemoInfo;
import com.zengshi.ecp.demo.service.busi.interfaces.IDemoInfoSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-7-31下午5:17:01  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */

public class DemoInfoSVImplTest extends EcpServicesTest{

    /**
     * Test method for {@link com.zengshi.ecp.demo.service.common.impl.DemoInfoSVImpl#saveInfo(com.zengshi.ecp.demo.dao.model.DemoInfo)}.
     */
    @Autowired
    private IDemoInfoSV demoInfoSV;
    
    @Test
    public void testSaveInfo() {
        for(int i=0;i<100;i++){
            DemoInfo info = new DemoInfo();
            info.setValue(123L+i);
            info.setDemoInfo("测试"+i);
            long id = demoInfoSV.saveInfo(info);
            System.out.println("id is :" + id);
        }
       
    }

}

