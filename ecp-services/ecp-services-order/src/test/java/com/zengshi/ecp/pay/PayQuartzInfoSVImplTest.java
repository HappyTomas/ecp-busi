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

import com.zengshi.ecp.order.dubbo.dto.ROrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdReceiptRequest;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayQuartzInfoRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayQuartzInfoResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdReceiptRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPayQuartzInfoForOrderRSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayQuartzInfoSV;
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

public class PayQuartzInfoSVImplTest extends EcpServicesTest {
    
    @Resource
    private IPayQuartzInfoSV payQuartzInfoSV;
    
    @Resource
    private IPayQuartzInfoForOrderRSV payQuartzInfoForOrderRSV;
    
    @Resource
    private IOrdMainRSV  ordMainRSV;
    
    @Resource
    private IOrdReceiptRSV ordReceiptRSV;
    
    @Test
    public void testPayZYDigitalSV() throws Exception {
        payQuartzInfoSV.addErrorTimes(1145L);
        
    }
    
    @Test
    public void queryNotDealScoreOrder(){
        RPayQuartzInfoRequest rPayQuartzInfoRequest = new RPayQuartzInfoRequest();
        rPayQuartzInfoRequest.setCount(2);
        List<RPayQuartzInfoResponse> list = payQuartzInfoForOrderRSV.queryNotDealScoreOrder(rPayQuartzInfoRequest);
        System.out.println(list);
    }
    
    @Test
    public void queryNotDealCoupOrder(){
        RPayQuartzInfoRequest rPayQuartzInfoRequest = new RPayQuartzInfoRequest();
        rPayQuartzInfoRequest.setCount(2);
        List<RPayQuartzInfoResponse> list = payQuartzInfoForOrderRSV.queryNotDealCoupOrder(rPayQuartzInfoRequest);
        System.out.println(list);
    }
    
    @Test
    public void queryNotDealZYDigitalOrder(){
        RPayQuartzInfoRequest rPayQuartzInfoRequest = new RPayQuartzInfoRequest();
        rPayQuartzInfoRequest.setCount(2);
//        List<RPayQuartzInfoResponse> list = payQuartzInfoForOrderRSV.queryNotDealZYDigitalOrder(rPayQuartzInfoRequest);
//        System.out.println(list);
    }
    
    @Test
    public void queryNotDealZYExaminationOrder(){
        RPayQuartzInfoRequest rPayQuartzInfoRequest = new RPayQuartzInfoRequest();
        rPayQuartzInfoRequest.setCount(2);
//        List<RPayQuartzInfoResponse> list = payQuartzInfoForOrderRSV.queryNotDealZYExaminationOrder(rPayQuartzInfoRequest);
//        System.out.println(list);
        
        //出现没用提示语
    }
    
    @Test
    public void queryNeedCancelOrder(){
        RQueryOrderRequest queryOrderRequest = new RQueryOrderRequest();
        queryOrderRequest.setCount(10);
        queryOrderRequest.setTableIndex(0);
        List<ROrdMainResponse> list = ordMainRSV.queryNeedCancelOrder(queryOrderRequest);
        System.out.println(list);
    }
    
    @Test
    public void queryNotReceiptOrder(){
        ROrdReceiptRequest rOrdReceiptRequest = new ROrdReceiptRequest();
        rOrdReceiptRequest.setCount(10);
        rOrdReceiptRequest.setTableIndex(0);
        List<ROrdMainResponse> list = ordReceiptRSV.queryNotReceiptOrder(rOrdReceiptRequest);
        System.out.println(list);
    }
    
    @Test
    public void dealScoreOrder(){
        RPayQuartzInfoRequest rPayQuartzInfoRequest= new RPayQuartzInfoRequest();
        rPayQuartzInfoRequest.setOrderId("RW15112200002016");
        rPayQuartzInfoRequest.setStaffId(1000L);
        payQuartzInfoForOrderRSV.dealScoreOrder(rPayQuartzInfoRequest);
    }
    
    @Test
    public void dealCoupOrder(){
        RPayQuartzInfoRequest rPayQuartzInfoRequest= new RPayQuartzInfoRequest();
        rPayQuartzInfoRequest.setOrderId("");
        rPayQuartzInfoRequest.setStaffId(1000L);
        payQuartzInfoForOrderRSV.dealCoupOrder(rPayQuartzInfoRequest);
    }
    
//    @Test
//    public void dealZYDigitalOrder(){
//        RPayQuartzInfoRequest rPayQuartzInfoRequest= new RPayQuartzInfoRequest();
//        rPayQuartzInfoRequest.setOrderId("RW15111300001756");
//        rPayQuartzInfoRequest.setStaffId(1000L);
//        payQuartzInfoForOrderRSV.dealZYDigitalOrder(rPayQuartzInfoRequest);
//    }
//    
//    @Test
//    public void dealZYExaminationOrder(){
//        RPayQuartzInfoRequest rPayQuartzInfoRequest= new RPayQuartzInfoRequest();
//        rPayQuartzInfoRequest.setOrderId("RW15111300001756");
//        rPayQuartzInfoRequest.setStaffId(1000L);
//        payQuartzInfoForOrderRSV.dealZYExaminationOrder(rPayQuartzInfoRequest);
//    }
    
}

