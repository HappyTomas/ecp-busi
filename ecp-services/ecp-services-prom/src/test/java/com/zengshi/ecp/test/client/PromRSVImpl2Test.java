package com.zengshi.ecp.test.client;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromListDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromStockLimitDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.DateUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-6 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dubbo/client/dubbo-test-prom.xml")
public class PromRSVImpl2Test extends AbstractJUnit4SpringContextTests {
    @Resource
    private IPromRSV promRSV;
    
    final static private long THREAD_MAX_RUNTIME = 1000;

    final static private long THREAD_WAITTIME = 200;
    
    @Test
    public  void testRun() throws InterruptedException {
        MyThread myThread1 = new MyThread();
        myThread1.start();
      /*  
        MyThread myThread2 = new MyThread();
        myThread2.start();
        
        MyThread myThread3 = new MyThread();
        myThread3.start();
        
        MyThread myThread4 = new MyThread();
        myThread4.start();
        
        myThread1.join();
        myThread2.join();
        myThread3.join();
        myThread4.join();*/
       /*Thread tc = Thread.currentThread();
    synchronized (tc) {

    while(myThread1.isAlive()) {

     tc.wait(THREAD_WAITTIME);

     }

      tc.notify();*/
      /*  try {
            Thread.sleep(THREAD_WAITTIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    //}
    }
    public class MyThread extends Thread {
        public void run() {
            try{ 
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
               r.setPromId(new Long(3036));
                r.setShopId(new Long(69));
                r.setShopName("shopName");
                r.setSource("1");
                r.setStaffId(new Long(104));
                
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
                item.setCreateStaff(new Long(104));
                item.setCreateTime(DateUtil.getSysDate());
                item.setCurrentSiteId(r.getCurrentSiteId());
                item.setGdsId(new Long(37312));
                item.setGdsName("gdsName");
                item.setGdsType(new Long(1));
                item.setGroupDetail("setGroupDetail");
                item.setGroupType("setGroupType");
                item.setId(new Long(1));
                item.setOrderAmount(new Long(1));
                item.setOrderId(null);
                item.setOrderSubId(null);
               // item.setOrdPromId(new Long(2088));
                item.setPriceType("1");
                item.setPromId(new Long(3038));
                item.setShopId(new Long(69));
                item.setShopName("shopName");
                item.setSkuId(new Long(74560));
                item.setSkuInfo("sadfsdf");
                item.setStaffId(r.getStaffId());
                promRSV.promOrdSave(rOrdCartsCommRequest);
            }catch(Exception ex){
              //  ex.printStackTrace();
                System.out.println(ex);
            }
        }
    }
}
