/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoDistributedSVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015-8-3下午2:57:45 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.demo.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.demo.dao.model.DemoDistributed;
import com.zengshi.ecp.demo.service.busi.interfaces.IDemoDistributedSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.alibaba.fastjson.JSON;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-8-3下午2:57:45  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class DemoDistributedSVImplTest extends EcpServicesTest {
    
    @Resource
    private IDemoDistributedSV sv;
    /**
     * Test method for {@link com.zengshi.ecp.demo.service.busi.impl.DemoDistributedSVImpl#saveInfo(com.zengshi.ecp.demo.dao.model.DemoDistributed)}.
     */
//    @Test
    public void testSaveInfo() {
        for(int i=0;i<10;i++){
            DemoDistributed info = new DemoDistributed();
            info.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            info.setName("测试："+ i);
            try{
                System.out.println(sv.saveInfo(info));
            } catch(BusinessException err){
                err.printStackTrace();
            }
        }

    }
    
    @Test
    public void testQueryInfoById(){
        
       DemoDistributed info = new DemoDistributed();
        info.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        info.setName("测试："+ 1000);
        
        long id = sv.saveInfo(info);
        System.out.println("######################  id:"+id);
        DemoDistributed info2 = sv.queryInfoById(id); 
        System.out.println("================"+info2);
        /*long id = 4032;
        DemoDistributed info = this.sv.queryInfoById(id);
        System.out.println(info);*/
    }
    
    @Test
    public void testSaveResult(){
        for(int i=12;i<15;i++){
            DemoDistributed info = new DemoDistributed();
            info.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            info.setName("测试："+ i);
            System.out.println("==================info==前"+info);
            try{
                System.out.println("==================info==后"+JSON.toJSONString(sv.saveResult(info)));
            } catch(BusinessException err){
                err.printStackTrace();
            }
        }
    }

}

