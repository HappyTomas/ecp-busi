/** 
 * Project Name:ecp-services-demo-server 
 * File Name:MixDbSVTest.java 
 * Package Name:com.zengshi.ecp.demo.test 
 * Date:2015年11月9日上午11:35:38 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
*/
package com.zengshi.ecp.demo.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.demo.dao.model.DemoInfo;
import com.zengshi.ecp.demo.dao.model.DemoLog;
import com.zengshi.ecp.demo.dao.model.User;
import com.zengshi.ecp.demo.facade.IMixDbSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

/** 
 * Title: ECP <br>
 * Project Name:ecp-services-demo-server <br>
 * Description: <br>
 * Date:2015年11月9日上午11:35:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangysh
 * @since JDK 1.6 
 * @see       
 */
public class MixDbSVTest extends EcpServicesTest{

    @Resource
    private IMixDbSV mixDBSV;
    
    @Test
    public void doTrans(){
        DemoLog log=new DemoLog();
        log.setDbCode("0");
        log.setLogId(999999L);
        log.setLogInfo("事务测试内容");
        
        User user=new User();
        user.setUserId("10000");
        user.setUserClass("09");
        user.setUserName("事务测试user");
        
        DemoInfo info=new DemoInfo();
        info.setId(10000L);
        info.setDemoInfo("事务");
        info.setValue(99L);
        try {
            mixDBSV.saveDemo(log, info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

