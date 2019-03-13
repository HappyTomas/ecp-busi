/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoClientTest.java 
 * Package Name:com.zengshi.ecp.test.client 
 * Date:2015-8-3下午6:55:12 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.test.client;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromSolrRSV;

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
public class PromSolrRSVTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private IPromSolrRSV promSolrRSV;
  
  @Test
  public void test(){ 
     // Prom2SolrReqDTO prom2SolrReqDTO=new Prom2SolrReqDTO();
    /*  prom2SolrReqDTO.setSiteId(1l);
      prom2SolrReqDTO.setShopId(35l);
      prom2SolrReqDTO.setPageSize(100);*/
      promSolrRSV.sendData(null);
 
  }
}
