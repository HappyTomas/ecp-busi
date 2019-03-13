package com.zengshi.ecp.prom.test.service.busi.service.constraint;

import java.util.Date;
import javax.annotation.Resource;
import org.junit.Test;
import com.zengshi.ecp.prom.dubbo.dto.PromUserLimitDTO;
import com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromUserLimitSV;
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
public class PromUserLimitSVImplTest extends EcpServicesTest {

    @Resource
    private IPromUserLimitSV promUserLimitSV;
 
 /*  @Test
    public void testInsert() throws BusinessException { 
       
       PromUserLimitDTO promUserLimitDTO=new PromUserLimitDTO();
       promUserLimitDTO.setBuyCnt(new Long(1));
       promUserLimitDTO.setCreateStaff(new Long(1));
       promUserLimitDTO.setCreateTime(new Date());
       promUserLimitDTO.setLimitType("1");
       promUserLimitDTO.setLimitTypeValue("2");
       promUserLimitDTO.setPromCntLimit(new Long(1));
       promUserLimitDTO.setPromId(new Long(79));
       promUserLimitDTO.setRemaindCnt(new Long(1));
       promUserLimitDTO.setStaffId(new Long(1));
       promUserLimitSV.insert(promUserLimitDTO);
   }*/
   @Test
   public void testUpdate() throws BusinessException { 
      
      PromUserLimitDTO promUserLimitDTO=new PromUserLimitDTO();
      promUserLimitDTO.setBuyCnt(new Long(1));
      promUserLimitDTO.setCreateStaff(new Long(1));
      promUserLimitDTO.setCreateTime(new Date());
      promUserLimitDTO.setLimitType("1");
      promUserLimitDTO.setLimitTypeValue("2");
      promUserLimitDTO.setPromCntLimit(new Long(1));
      promUserLimitDTO.setPromId(new Long(795));
      promUserLimitDTO.setRemaindCnt(new Long(1));
      promUserLimitDTO.setStaffId(new Long(1));
      promUserLimitSV.update(promUserLimitDTO);
  }
}
