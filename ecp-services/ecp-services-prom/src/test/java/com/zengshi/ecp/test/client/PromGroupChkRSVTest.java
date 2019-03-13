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

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.prom.dubbo.dto.PromChkRespPageDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromChkResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.QueryPromGroupChkDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupChkRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dubbo/client/dubbo-test-prom.xml")
public class PromGroupChkRSVTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private IPromGroupChkRSV promGroupChkRSV;
   
    @Test
    public void testqueryGroup() {
        QueryPromGroupChkDTO queryPromGroupChkDTO=new QueryPromGroupChkDTO();
        queryPromGroupChkDTO.setPageNo(1);
        queryPromGroupChkDTO.setPageSize(10);
        PageResponseDTO<PromChkRespPageDTO> page=promGroupChkRSV.listPromInfoByPromGroup(queryPromGroupChkDTO);
         System.out.println(page.getCount());
    }
   
}
