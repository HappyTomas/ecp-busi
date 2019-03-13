package com.zengshi.ecp.aip.third.test.service.busi.order.detail;

import javax.annotation.Resource;
import org.junit.Test;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsSendThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.OrderRespDTO;
import com.zengshi.ecp.aip.third.service.busi.gds.interfaces.IGdsSendSV;
import com.zengshi.ecp.aip.third.service.busi.order.detail.interfaces.IOrderDetailSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-6 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class OrderDetailSVImplTest extends EcpServicesTest {

    @Resource
    private IOrderDetailSV defaultOrderDetailSV;
    
    //京东调用测试
    @Test
    public void testJd() throws BusinessException { }
    //淘宝调用测试
    
    @Test
    public void testTaobaoquerySimpleOrderDetail() throws BusinessException {
    	OrderReqDTO orderReqDTO=new OrderReqDTO();
    	orderReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	orderReqDTO.setAppkey("1023521935");
    	orderReqDTO.setAppscret("sandbox75de5da394607a610715e572a");
    	orderReqDTO.setServerUrl("https://gw.api.tbsandbox.com/router/rest");
    	orderReqDTO.setAccessToken("620083070eddd78d1d9d7473ccb18e7e3082dcegd46a5122054631247");
    	orderReqDTO.setOrderId("194546403081084");//194546403081084  //194546402111084
    	orderReqDTO.setShopId(100L);
    	orderReqDTO.setAuthId(12L);
    	//req.setTid(123456789L);
    	OrderRespDTO value=defaultOrderDetailSV.querySimpleOrderDetail(orderReqDTO);
    	System.out.println(value);
    }
    
    //现网测试哦   不要乱跑哦
    @Test
    public void testTaobaoquerySimpleOrderDetail_Prd() throws BusinessException {
    	OrderReqDTO orderReqDTO=new OrderReqDTO();
    	orderReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	orderReqDTO.setAppkey("23521935");
    	
    	orderReqDTO.setAppscret("bedf75375de5da394607a610715e572a");
    	
    	orderReqDTO.setServerUrl("https://eco.taobao.com/router/rest");
    	orderReqDTO.setAccessToken("6200a295f2b4f71c36689147d16f1cc290c21ZZ7b1a9fbe1125014918");
    	orderReqDTO.setOrderId("2317187583378246");//2320545579480642
    	orderReqDTO.setShopId(100L);
    	OrderRespDTO value=defaultOrderDetailSV.querySimpleOrderDetail(orderReqDTO);
    	System.out.println(value);
    }
    //现网测试哦   不要乱跑哦
    @Test
    public void testTaobaoqueryOrderDetail_PRD() throws BusinessException {
    	OrderReqDTO orderReqDTO=new OrderReqDTO();
    	orderReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
        orderReqDTO.setAppkey("23521935");
    	orderReqDTO.setAppscret("bedf75375de5da394607a610715e572a");
    	orderReqDTO.setServerUrl("https://eco.taobao.com/router/rest");
    	orderReqDTO.setAccessToken("6200a295f2b4f71c36689147d16f1cc290c21ZZ7b1a9fbe1125014918");
    	orderReqDTO.setOrderId("2317187583378246");//2320545579480642
    	orderReqDTO.setShopId(100L);
    	OrderRespDTO value=defaultOrderDetailSV.queryOrderDetail(orderReqDTO);
    	System.out.println(value);
    }

    @Test
    public void testTaobaoqueryOrderDetail() throws BusinessException {
    	OrderReqDTO orderReqDTO=new OrderReqDTO();
    	orderReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	orderReqDTO.setAppkey("1023521935");
    	orderReqDTO.setAppscret("sandbox75de5da394607a610715e572a");
    	orderReqDTO.setServerUrl("https://gw.api.tbsandbox.com/router/rest");
    	orderReqDTO.setAccessToken("62015307e1f8dea5818e80ec7404941c3dbfb9ZZ7a044c02074082786");
    	orderReqDTO.setOrderId("194572019371084");
    	orderReqDTO.setShopId(100L);
    	OrderRespDTO value=defaultOrderDetailSV.queryOrderDetail(orderReqDTO);
    	System.out.println(value);
    }
    //有赞调用测试
    @Test
    public void testYouzan() throws BusinessException { }
}
