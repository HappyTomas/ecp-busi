/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoRSVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015-8-3下午6:51:05 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.demo.test;


import java.sql.Timestamp;
import java.util.Calendar;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.demo.dubbo.dto.DemoInfoDTO;
import com.zengshi.ecp.demo.dubbo.interfaces.IDemoInfoRSV;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.ThreadId;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-8-3下午6:51:05  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */

public class DemoInfoRSVImplTest extends EcpServicesTest {
    
    @Resource
    private IDemoInfoRSV demoInfoRSV;

    /**
     * Test method for {@link com.zengshi.ecp.demo.dubbo.impl.DemoInfoRSVImpl#saveInfo(com.zengshi.ecp.demo.dubbo.dto.DemoInfoDTO)}.
     */
    @Test
    public void testSaveInfo() {
        DemoInfoDTO dto = new DemoInfoDTO();
        dto.setThreadId(ThreadId.getThreadId());
//        dto.setName("测试的");
        dto.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        demoInfoRSV.saveInfo(dto);
    }

}

