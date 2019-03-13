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

import com.zengshi.ecp.order.dubbo.dto.pay.PayIntfReqLogDTO;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayIntfReqLogSV;
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

public class PayIntfReqLogSVImplTest extends EcpServicesTest {
    
    @Resource
    private IPayIntfReqLogSV payIntfReqLogSV;

    @Test
    public void testAddPayIntfReqLog() {
        PayIntfReqLogDTO info = new PayIntfReqLogDTO();
//        info.setOrderId("59999999");
        info.setPayWay("9012");
        info.setTypeCode("08");
        info.setStaffId(null);
//        info.setRlRequestNo(111111L);
        info.setRequestMsg("我们都是好孩子");
        info.setResponseMsg("我们都是好孩子aaaaaaaa");
        info.setRequestTime(DateUtil.getSysDate());
        info.setResponseTime(DateUtil.getSysDate());
        payIntfReqLogSV.addPayIntfReqLog(info);
    }
    
}

