/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoCfgClientTest.java 
 * Package Name:com.zengshi.ecp.test.client 
 * Date:2015-8-7下午5:42:18 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test.client;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.demo.dubbo.dto.DemoCfgReqDTO;
import com.zengshi.ecp.demo.dubbo.dto.DemoCfgRespDTO;
import com.zengshi.ecp.demo.dubbo.interfaces.IDemoCfgRSV;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-8-7下午5:42:18  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dubbo/client/dubbo-test-demo.xml")
public class DemoCfgClientTest extends AbstractJUnit4SpringContextTests {
    
    @Resource(name="demoCfgRSV")
    private IDemoCfgRSV demoCfgRSV;
    
    @Test
    public void saveTest(){
        
        DemoCfgReqDTO dto = new DemoCfgReqDTO();
        dto.setCode("A101");
        dto.setCreateTime(Calendar.getInstance().getTime());
        dto.setInfo("测试说明-00");
        try{
            demoCfgRSV.saveDemoCfg(dto);
        } catch(Exception err){
            err.printStackTrace();
        }
        
    }
    
    @Test
    public void saveQueryList(){
      
        BaseInfo info = new BaseInfo();
        PageResponseDTO<DemoCfgRespDTO> pageInfo = demoCfgRSV.listDemoCfg(info);
        
        for(DemoCfgRespDTO dto : pageInfo.getResult()){
            System.out.println(dto.getCode() + dto.getCreateTime() + dto.getId() + dto.getInfo());
        }
    }

}

