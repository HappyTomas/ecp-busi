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

import com.zengshi.ecp.order.dubbo.dto.pay.PayIntfNotifyLogDTO;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayIntfNotifyLogSV;
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

public class PayIntfNotifyLogSVImplTest extends EcpServicesTest {
    
    @Resource
    private IPayIntfNotifyLogSV payIntfNotifyLogSV;

    @Test
    public void testAddPayNotifyLog() {
        PayIntfNotifyLogDTO log = new PayIntfNotifyLogDTO();
        log.setPayWay("9012");
        log.setTypeCode("01");
        log.setOrderId("890000");
        log.setStaffId(11L);
        log.setPaywayRequestNo("111111111");
        log.setRequestTime(DateUtil.getSysDate());
        log.setRequestMsg("qqqqqqqqqqq");
        log.setResponseTime(DateUtil.getSysDate());
        log.setResponseMsg("dddddddddd");
        payIntfNotifyLogSV.addPayNotifyLog(log);
    }
    
    
   
}

