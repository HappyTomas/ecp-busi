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

import java.math.BigDecimal;
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

import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.general.order.interfaces.IOrdCartsChkRSV;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromListDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromStockLimitDTO;
import com.zengshi.ecp.prom.dubbo.dto.QueryPromDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
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
public class PromCartChkRSVTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private IOrdCartsChkRSV promCartChkRSV;
  
  
  @Test
  public void testNull() {
      ROrdCartsCommRequest rOrdCartsCommRequest=new ROrdCartsCommRequest();
      rOrdCartsCommRequest.setCurrentSiteId(new Long(1));
      rOrdCartsCommRequest.setSource("1");//渠道来源
      rOrdCartsCommRequest.setStaffId(new Long(1));
      
      List<ROrdCartCommRequest> ordCartsCommList=new ArrayList<ROrdCartCommRequest> ();
      
      ROrdCartCommRequest r=new ROrdCartCommRequest();
      ordCartsCommList.add(r);
      r.setCartType("1");
      r.setCurrentSiteId(rOrdCartsCommRequest.getCurrentSiteId());
      r.setId(new Long(1));
      r.setOrderId("orderId1");
     // r.setPromId(new Long(2088));
      r.setShopId(new Long(35));
      r.setShopName("shopName");
      r.setSource("1");
      r.setStaffId(new Long(0));
      
      r.setAreaValue("1");
      r.setChannelValue("1");
      r.setCustGroupValue("1");
      r.setCustLevelValue("1");
      
      
      List<ROrdCartItemCommRequest> ordCartItemCommList =new ArrayList<ROrdCartItemCommRequest>();
      ROrdCartItemCommRequest rd=new ROrdCartItemCommRequest();
      
      r.setOrdCartItemCommList(ordCartItemCommList);
      
      rOrdCartsCommRequest.setOrdCartsCommList(ordCartsCommList);
      
      
      ROrdCartItemCommRequest  item=new ROrdCartItemCommRequest();
      
      ordCartItemCommList.add(item);
      
      item.setAddTime(DateUtil.getSysDate());
      item.setAppName("1");
      item.setBasePrice(new Long(10023));
      item.setBuyPrice(new Long(10000));
      item.setCartId(new Long(1));
      item.setCartType("1");
      item.setCategoryCode("111");
      item.setCreateStaff(new Long(1));
      item.setCreateTime(DateUtil.getSysDate());
      item.setCurrentSiteId(r.getCurrentSiteId());
      item.setGdsId(new Long(1));
      item.setGdsName("gdsName");
      item.setGdsType(new Long(1));
      item.setGroupDetail("setGroupDetail");
      item.setGroupType("setGroupType");
      item.setId(new Long(1));
      item.setOrderAmount(new Long(100));
      item.setOrderId(null);
      item.setOrderSubId(null);
     // item.setOrdPromId(new Long(2088));
      item.setPriceType("1");
      //item.setPromId(new Long(2108));
      item.setShopId(new Long(35));
      item.setShopName("shopName");
      item.setSkuId(new Long(1));
      item.setSkuInfo("sadfsdf");
      item.setStaffId(r.getStaffId());
      ROrdCartsChkResponse dto=promCartChkRSV.checkOrdCart(rOrdCartsCommRequest);
  }

  @Test
  public void testItemSuccess() {
      ROrdCartsCommRequest rOrdCartsCommRequest=new ROrdCartsCommRequest();
      rOrdCartsCommRequest.setCurrentSiteId(new Long(1));
      rOrdCartsCommRequest.setSource("1");//渠道来源
      rOrdCartsCommRequest.setStaffId(new Long(1));
      
      List<ROrdCartCommRequest> ordCartsCommList=new ArrayList<ROrdCartCommRequest> ();
      
      ROrdCartCommRequest r=new ROrdCartCommRequest();
      ordCartsCommList.add(r);
      r.setCartType("1");
      r.setCurrentSiteId(rOrdCartsCommRequest.getCurrentSiteId());
      r.setId(new Long(1));
      r.setOrderId("orderId1");
      r.setPromId(new Long(2088));
      r.setShopId(new Long(35));
      r.setShopName("shopName");
      r.setSource("1");
      r.setStaffId(new Long(0));
      
      r.setAreaValue("1");
      r.setChannelValue("1");
      r.setCustGroupValue("1");
      r.setCustLevelValue("1");
      
      
      List<ROrdCartItemCommRequest> ordCartItemCommList =new ArrayList<ROrdCartItemCommRequest>();
      ROrdCartItemCommRequest rd=new ROrdCartItemCommRequest();
      
      r.setOrdCartItemCommList(ordCartItemCommList);
      
      rOrdCartsCommRequest.setOrdCartsCommList(ordCartsCommList);
      
      
      ROrdCartItemCommRequest  item=new ROrdCartItemCommRequest();
      
      ordCartItemCommList.add(item);
      
      item.setAddTime(DateUtil.getSysDate());
      item.setAppName("1");
      item.setBasePrice(new Long(10023));
      item.setBuyPrice(new Long(10000));
      item.setCartId(new Long(1));
      item.setCartType("1");
      item.setCategoryCode("111");
      item.setCreateStaff(new Long(1));
      item.setCreateTime(DateUtil.getSysDate());
      item.setCurrentSiteId(r.getCurrentSiteId());
      item.setGdsId(new Long(1));
      item.setGdsName("gdsName");
      item.setGdsType(new Long(1));
      item.setGroupDetail("setGroupDetail");
      item.setGroupType("setGroupType");
      item.setId(new Long(1));
      item.setOrderAmount(new Long(100));
      item.setOrderId(null);
      item.setOrderSubId(null);
     // item.setOrdPromId(new Long(2088));
      item.setPriceType("1");
      item.setPromId(new Long(2108));
      item.setShopId(new Long(35));
      item.setShopName("shopName");
      item.setSkuId(new Long(1));
      item.setSkuInfo("sadfsdf");
      item.setStaffId(r.getStaffId());
      ROrdCartsChkResponse dto=promCartChkRSV.checkOrdCart(rOrdCartsCommRequest);
  }
  

  @Test
  public void testItemerror() {
      ROrdCartsCommRequest rOrdCartsCommRequest=new ROrdCartsCommRequest();
      rOrdCartsCommRequest.setCurrentSiteId(new Long(1));
      rOrdCartsCommRequest.setSource("1");//渠道来源
      rOrdCartsCommRequest.setStaffId(new Long(1));
      
      List<ROrdCartCommRequest> ordCartsCommList=new ArrayList<ROrdCartCommRequest> ();
      
      ROrdCartCommRequest r=new ROrdCartCommRequest();
      ordCartsCommList.add(r);
      r.setCartType("1");
      r.setCurrentSiteId(rOrdCartsCommRequest.getCurrentSiteId());
      r.setId(new Long(1));
      r.setOrderId("orderId1");
      r.setPromId(new Long(2090));
      r.setShopId(new Long(35));
      r.setShopName("shopName");
      r.setSource("1");
      r.setStaffId(new Long(0));
      
      r.setAreaValue("1");
      r.setChannelValue("1");
      r.setCustGroupValue("1");
      r.setCustLevelValue("1");
      
      
      List<ROrdCartItemCommRequest> ordCartItemCommList =new ArrayList<ROrdCartItemCommRequest>();
      ROrdCartItemCommRequest rd=new ROrdCartItemCommRequest();
      
      r.setOrdCartItemCommList(ordCartItemCommList);
      
      rOrdCartsCommRequest.setOrdCartsCommList(ordCartsCommList);
      
      
      ROrdCartItemCommRequest  item=new ROrdCartItemCommRequest();
      
      ordCartItemCommList.add(item);
      
      item.setAddTime(DateUtil.getSysDate());
      item.setAppName("1");
      item.setBasePrice(new Long(1));
      item.setBuyPrice(new Long(1));
      item.setCartId(new Long(1));
      item.setCartType("1");
      item.setCategoryCode("111");
      item.setCreateStaff(new Long(1));
      item.setCreateTime(DateUtil.getSysDate());
      item.setCurrentSiteId(r.getCurrentSiteId());
      item.setGdsId(new Long(1));
      item.setGdsName("gdsName");
      item.setGdsType(new Long(1));
      item.setGroupDetail("setGroupDetail");
      item.setGroupType("setGroupType");
      item.setId(new Long(1));
      item.setOrderAmount(new Long(2));
      item.setOrderId(null);
      item.setOrderSubId(null);
       item.setOrdPromId(new Long(2090));
      item.setPriceType("1");
      item.setPromId(new Long(2108));
      item.setShopId(new Long(35));
      item.setShopName("shopName");
      item.setSkuId(new Long(1));
      item.setSkuInfo("sadfsdf");
      item.setStaffId(r.getStaffId());
      ROrdCartsChkResponse dto=promCartChkRSV.checkOrdCart(rOrdCartsCommRequest);
  }
}
