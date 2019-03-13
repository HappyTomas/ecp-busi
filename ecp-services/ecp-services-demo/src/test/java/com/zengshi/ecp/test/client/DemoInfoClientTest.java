/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoClientTest.java 
 * Package Name:com.zengshi.ecp.test.client 
 * Date:2015-8-3下午6:55:12 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test.client;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.demo.dubbo.dto.DemoInfoDTO;
import com.zengshi.ecp.demo.dubbo.interfaces.IDemoInfoRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ThreadId;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-8-3下午6:55:12  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dubbo/client/dubbo-test-demo.xml")
public class DemoInfoClientTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private IDemoInfoRSV demoInfoRSV;
    
    /**
     * Test method for {@link com.zengshi.ecp.demo.dubbo.impl.DemoInfoRSVImpl#saveInfo(com.zengshi.ecp.demo.dubbo.dto.DemoInfoDTO)}.
     */
    @Test
    public void testSaveInfo() {
        DemoInfoDTO dto = new DemoInfoDTO();
        dto.setThreadId(ThreadId.getThreadId());
        dto.setName("测试的 12221-----长度长一点测试回滚-长度长一点测试回滚-长度长一点测试回滚-长度长一点测试回滚-长度长一点测试回滚");
        dto.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        try{
            demoInfoRSV.saveInfo(dto);
        } catch(BusinessException err){
            System.out.println(err.getErrorCode()+err.getErrorMessage());
        }
        
    }

}

