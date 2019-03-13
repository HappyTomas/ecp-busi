/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoRSVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015-8-3下午6:51:05 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.pay;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.order.dao.model.PayResult;
import com.zengshi.ecp.order.dubbo.dto.pay.PayResultDTO;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayResultSV;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.DateUtil;

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

public class PayResultSVImplTest extends EcpServicesTest {
    
    @Resource
    private IPayResultSV payResultSV;

    @Test
    public void testAddPayResult() {
        PayResultDTO payResult = new PayResultDTO();
        payResult.setOrderId("59000000");
        payResult.setPayWay("9012");
        payResult.setPayWayName("沃支付");
        payResult.setPayTransNo("1111111111111");
        payResult.setPayment(100000L);
        payResult.setPayStatus("00");
        payResult.setPayDesc("支付成功");
        payResult.setPayTime(DateUtil.getSysDate());
        payResult.setMerchAcct("99999");
        payResult.setPayeeName("weijq");
        payResult.setPayeeAcct("88888");
        payResultSV.addPayResult(payResult);
    }
    
    @Test
    public void testGetPayResultByOrderId() {
        List<PayResult> r = payResultSV.getPayResultByOrderId("59000000");
        System.out.println(r);
    }
    
    
   
}

