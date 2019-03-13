/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoClientTest.java 
 * Package Name:com.zengshi.ecp.test.client 
 * Date:2015-8-3下午6:55:12 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.test.client;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.general.order.dto.ROrdCartChangeRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.prom.dubbo.dto.CartPromBeanRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromListDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPostDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPostRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromShipDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromShipRSV;
import com.zengshi.paas.utils.DateUtil;

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
public class PromShipRSVTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private IPromShipRSV promShipRSV;
  
  @Test
  public void test(){ 
      PromShipDTO promShipDTO=new PromShipDTO();
     // promShipDTO.setAreaCode("32110011");
      promShipDTO.setCityCode("11");
      promShipDTO.setProvinceCode("22");
      promShipDTO.setCountryCode("33");
      promShipDTO.setMoney(22222222222200l);
      promShipDTO.setShopId(69l);
      promShipRSV.qryPromShip(promShipDTO);
  }
  @Test
  public void test1(){ 
	  PromPostDTO promPostDTO=new PromPostDTO();
      PromPostRespDTO a=promShipRSV.checkIfFreePost(promPostDTO);
      
      PromPostRespDTO a1=promShipRSV.checkIfFreePost(null);
      
      List l=new ArrayList();
      l.add(new Long(1));
      l.add(new Long(2));
      promPostDTO.setPromIds(l);
      PromPostRespDTO a3=promShipRSV.checkIfFreePost(promPostDTO);
      
      
      List l1=new ArrayList();
      l1.add(2014L);
      l1.add(3157L);
      l1.add(31571L);
      promPostDTO.setPromIds(l1);
      PromPostRespDTO a4=promShipRSV.checkIfFreePost(promPostDTO);
      
      System.out.println(a4);
  }
}
