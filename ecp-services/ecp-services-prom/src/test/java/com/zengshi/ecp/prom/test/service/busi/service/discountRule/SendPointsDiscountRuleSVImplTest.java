package com.zengshi.ecp.prom.test.service.busi.service.discountRule;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.prom.dubbo.dto.PromPresentDTO;
import com.zengshi.ecp.prom.service.busi.discountRule.interfaces.IPromDiscountRuleSV;
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
public class SendPointsDiscountRuleSVImplTest extends EcpServicesTest {

    @Resource
    private IPromDiscountRuleSV sendPointsDiscountRuleSV;
 
   @Test
    public void testPrompresent() throws BusinessException { 
       PromPresentDTO dto=   sendPointsDiscountRuleSV.promPresent(new Long(150),null);
       System.out.println(dto);
   }
   @Test
   public void testPrompresent149() throws BusinessException { 
      PromPresentDTO dto=   sendPointsDiscountRuleSV.promPresent(new Long(149),null);
      System.out.println(dto);
  }
   @Test
   public void testPrompresentNUll() throws BusinessException { 
       PromPresentDTO dto=  sendPointsDiscountRuleSV.promPresent(new Long(-1),null);
      System.out.println(dto);
  }
   @Test
   public void testPrompresentParmNUll() throws BusinessException { 
       PromPresentDTO dto=   sendPointsDiscountRuleSV.promPresent(null,null);
      System.out.println(dto);
  }
}