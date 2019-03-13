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
public class PromRSVImpl1Test extends AbstractJUnit4SpringContextTests {
    @Resource
    private IPromRSV promRSV;
    
    final static private long THREAD_MAX_RUNTIME = 1000;

    final static private long THREAD_WAITTIME = 200;
    
    @Test
    public  void testRun() throws InterruptedException {
        MyThread myThread1 = new MyThread();
        myThread1.start();
        
        MyThread myThread2 = new MyThread();
        myThread2.start();
        
        MyThread myThread3 = new MyThread();
        myThread3.start();
        
        MyThread myThread4 = new MyThread();
        myThread4.start();
        
        myThread1.join();
        myThread2.join();
        myThread3.join();
        myThread4.join();
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
            
            OrdPromDTO ordPromDTO = new OrdPromDTO();
        ordPromDTO.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        ordPromDTO.setCalDate(new Date());
        ordPromDTO.setCartType("1");
        ordPromDTO.setCreateStaff(new Long(1));
        ordPromDTO.setId(new Long(1));
       // ordPromDTO.setOrdPromDetailDTOList(ordPromDetailDTOList);
        //ordPromDTO.setPromId(null);//是否传入促销编码
        ordPromDTO.setPromInfoDTOList(null);
        ordPromDTO.setPromTypeMsgResponseDTO(null);
        ordPromDTO.setShopId(new Long(1234));
        ordPromDTO.setStaffId("1");
        
        OrdPromDetailDTO ordPromDetailDTO=new OrdPromDetailDTO();
        ordPromDetailDTO.setAddTime(new Date());
        ordPromDetailDTO.setCartId(new Long(1));
        ordPromDetailDTO.setCartType("1");
        ordPromDetailDTO.setCreateStaff("1");
        ordPromDetailDTO.setCreateTime(new Date());
        ordPromDetailDTO.setGdsId(new Long(1));
        ordPromDetailDTO.setGdsName("hahah");
        ordPromDetailDTO.setId(new Long(1));
        ordPromDetailDTO.setOrderAmount(new Long(4));
        ordPromDetailDTO.setBasePrice(new Long(100));
        ordPromDetailDTO.setBuyPrice(new Long(90));
        //ordPromDetailDTO.setPromId(null);
        ordPromDetailDTO.setPromInfoDTOList(null);
        ordPromDetailDTO.setPromTypeMsgResponseDTO(null);
        ordPromDetailDTO.setShopId(new Long(1234));
        ordPromDetailDTO.setSkuId(new Long(4));
        ordPromDetailDTO.setStaffId("1");
        //ordPromDetailDTO.setPromId(new Long(79));
        PromInfoDTO promInfoDTO =new PromInfoDTO();
        promInfoDTO.setId(new Long(2002));
        ordPromDetailDTO.setPromInfoDTO(promInfoDTO);
        ordPromDetailDTO.setOrdPromId(new Long(2002));
        List<OrdPromDetailDTO> ordPromDetailDTOList=new ArrayList<OrdPromDetailDTO>();
        ordPromDetailDTOList.add(ordPromDetailDTO);
        
        ordPromDTO.setOrdPromDetailDTOList(ordPromDetailDTOList);
        PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
       // promRuleCheckDTO.setBuyCnt("1");
        promRuleCheckDTO.setAreaValue("1");
        promRuleCheckDTO.setCalDate(new Date());
        promRuleCheckDTO.setChannelValue("2");
        promRuleCheckDTO.setCustGroupValue("1");
        promRuleCheckDTO.setCustLevelValue("1");
        ordPromDTO.setPromRuleCheckDTO(promRuleCheckDTO);
        //ordPromDTO.setPromId(new Long(90));
        
        PromInfoDTO promInfoDTO3=new PromInfoDTO();
        promInfoDTO3.setId(new Long(90));
        ordPromDTO.setPromInfoDTO(promInfoDTO3);
        
        //赠品
        PromGiftDTO dto=new PromGiftDTO();
        dto.setPromId(new Long(2002));
        dto.setGiftId(new Long(1001));
        dto.setPresentCnt(new Long(1));
        List<PromGiftDTO> promGiftDTOList=new ArrayList<PromGiftDTO>();
        promGiftDTOList.add(dto);
        ordPromDetailDTO.setPromGiftDTOList(promGiftDTOList);
        List<OrdPromDTO> l=new ArrayList<OrdPromDTO>();
        l.add(ordPromDTO);
        OrdPromListDTO ordPromListDTO=new OrdPromListDTO();
        ordPromListDTO.setOrdPromDTOList(l);
         promRSV.promOrdSave(ordPromListDTO);
       //promRSV.promOrdSaveRollBack(ordPromListDTO);
        System.out.println("test");
            }catch(Exception ex){
              //  ex.printStackTrace();
                System.out.println(ex);
            }
        }
    }
}
