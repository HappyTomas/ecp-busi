/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoRSVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015-8-3下午6:51:05 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.pay;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.order.dubbo.dto.pay.PayRequestDTO;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayRequestSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: <br>
 * Date:2015年10月8日下午3:43:39  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */

public class PayRequestSVImplTest extends EcpServicesTest {
    
    @Resource
    private IPayRequestSV payRequestSV;

    @Test
    public void testAddPayRequest() {
        PayRequestDTO payRequest = new PayRequestDTO();
        payRequest.setCreateStaff(11L);
        payRequest.setOrderId("59000000000");
        payRequest.setPayment(10000L);
        payRequest.setPayWay("9012");
        payRequest.setShopId(1000L);
        payRequest.setStaffId(1L);
        payRequestSV.addPayRequest(payRequest);
    }
    
    
   
}

