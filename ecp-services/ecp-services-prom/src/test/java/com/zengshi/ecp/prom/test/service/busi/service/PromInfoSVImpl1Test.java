package com.zengshi.ecp.prom.test.service.busi.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;

import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromStockLimitDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromSV;
import com.zengshi.ecp.prom.test.service.busi.service.testMultMain.MyThread;
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
public class PromInfoSVImpl1Test extends EcpServicesTest {
    @Resource
    private IPromSV promSV;
    
    final static private long THREAD_MAX_RUNTIME = 1000;

    final static private long THREAD_WAITTIME = 200;
    
    @Test
    public  void testRun() throws InterruptedException {
        MyThread myThread1 = new MyThread();
        myThread1.start();
        myThread1.join();
/*       Thread tc = Thread.currentThread();
    synchronized (tc) {

    while(myThread1.isAlive()) {

     tc.wait(THREAD_WAITTIME);

     }

      tc.notify();
   
    }*/
    }
    public class MyThread extends Thread {
        public void run() {
            OrdPromDTO ordPromDTO = new OrdPromDTO();
            ordPromDTO.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            ordPromDTO.setCalDate(new Date());
            ordPromDTO.setCartType("1");
            ordPromDTO.setCreateStaff(new Long(1));
            ordPromDTO.setId(new Long(1));
           // ordPromDTO.setOrdPromDetailDTOList(ordPromDetailDTOList);
            //ordPromDTO.setPromId(null);
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
            ordPromDetailDTO.setGdsId(new Long(36979));
            ordPromDetailDTO.setGdsName("hahah");
            ordPromDetailDTO.setId(new Long(1));
            ordPromDetailDTO.setOrderAmount(new Long(4));
            ordPromDetailDTO.setBasePrice(new Long(100));
            ordPromDetailDTO.setBuyPrice(new Long(80));
           // ordPromDetailDTO.setPromId(null);
            ordPromDetailDTO.setPromInfoDTOList(null);
            ordPromDetailDTO.setPromTypeMsgResponseDTO(null);
            ordPromDetailDTO.setShopId(new Long(1234));
            ordPromDetailDTO.setSkuId(new Long(73898));
            ordPromDetailDTO.setStaffId("1");
            
            PromInfoDTO promInfoDTO =new PromInfoDTO();
            promInfoDTO.setId(new Long(117));
            ordPromDetailDTO.setPromInfoDTO(promInfoDTO);
            ordPromDetailDTO.setOrdPromId(new Long(159));
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
            
            PromInfoDTO promInfoDTO2=new PromInfoDTO();
            promInfoDTO2.setId(new Long(159));
            ordPromDTO.setPromInfoDTO(promInfoDTO2);
            //赠品
            PromGiftDTO dto=new PromGiftDTO();
            dto.setPromId(new Long(159));
            dto.setGiftId(new Long(1));
            dto.setPresentCnt(new Long(1));
            List<PromGiftDTO> promGiftDTOList=new ArrayList<PromGiftDTO>();
            promGiftDTOList.add(dto);
            ordPromDetailDTO.setPromGiftDTOList(promGiftDTOList);
            System.out.println("test1");
            promSV.savePromOrd(ordPromDTO,PromConstants.PromSys.doAction_SAVE);
            //   promSV.savePromOrd(ordPromDTO,PromConstants.PromSys.doAction_ROLLBACK);
            System.out.println("test2");

        }
    }
}
