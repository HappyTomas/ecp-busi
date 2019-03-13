/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoLogSVImplTest.java 
 * Package Name:com.zengshi.ecp.demo.test 
 * Date:2015-8-5下午7:48:32 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.demo.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.demo.dao.model.DemoLog;
import com.zengshi.ecp.demo.service.busi.interfaces.IDemoLogSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-8-5下午7:48:32  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class DemoLogSVImplTest extends EcpServicesTest {
    
    @Resource(name="demoLogSV")
    private IDemoLogSV demoLogSV;

    /**
     * Test method for {@link com.zengshi.ecp.demo.service.busi.impl.DemoLogSVImpl#find(long)}.
     */
    @Test
    public void testFind() {
        long id = 32L;
        try{
            DemoLog log = demoLogSV.find(id);
            if(log  == null){
                System.out.println("===================空=============");
            }
            System.out.println(log);
        } catch(Exception err){
            err.printStackTrace();
        }
        
    }

    /**
     * Test method for {@link com.zengshi.ecp.demo.service.busi.impl.DemoLogSVImpl#select(long)}.
     */
    @Test
    public void testSelect() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testSave(){
        DemoLog log = new DemoLog();
        log.setDbCode("4");
        log.setLogInfo("说明");
        this.demoLogSV.saveLog(log);
    }
    
    @Test
    public void testFindByCodeAndId(){
        try{
            DemoLog log = demoLogSV.findByCodeAndId(32, "4");
            System.out.println(log.toString());
        } catch(Exception err){
            err.printStackTrace();
        }
        
        
    }

}

