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

import com.zengshi.ecp.order.dubbo.dto.pay.PayBindDTO;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayBindSV;
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

public class PayBindSVImplTest extends EcpServicesTest {
    
    @Resource
    private IPayBindSV payBindSV;

    @Test
    public void testDealPayBind() {
        PayBindDTO payBind = new PayBindDTO();
        payBind.setPayWay("9012");
        payBind.setStaffId(11L);
        payBind.setBindBankAcct("11111111");
        payBind.setBindCustName("weijq");
        payBind.setBindCustPhone("13455555555");
        payBind.setBindStatus("2");
        payBindSV.dealPayBind(payBind);
    }
    
    
   
}

