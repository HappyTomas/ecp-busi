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

import com.zengshi.ecp.order.dao.model.PayRepeat;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRepeatDTO;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayRepeatSV;
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

public class PayRepeatSVImplTest extends EcpServicesTest {
    
    @Resource
    private IPayRepeatSV payRepeatSV;

    @Test
    public void testAddPayRepeat() {
        PayRepeatDTO payRepeat = new PayRepeatDTO();
        payRepeat.setOrderId("59000000");
        payRepeat.setPayWay("9012");
        payRepeat.setPayWayName("沃支付");
        payRepeat.setPayTransNo("1111111111111");
        payRepeat.setPayment(100000L);
        payRepeat.setPayStatus("00");
        payRepeat.setPayDesc("支付成功");
        payRepeat.setPayTime(DateUtil.getSysDate());
        payRepeat.setMerchAcct("99999");
        payRepeat.setPayeeName("weijq");
        payRepeat.setPayeeAcct("88888");
        payRepeatSV.addPayRepeat(payRepeat);
    }
    
    @Test
    public void testGetPayRepeatByOrderIdPayWayTransNo() {
        PayRepeat r = payRepeatSV.getPayRepeatByPayWayTransNo("9012", "11111111111111");
        System.out.println(r);
    }
    
    
   
}

