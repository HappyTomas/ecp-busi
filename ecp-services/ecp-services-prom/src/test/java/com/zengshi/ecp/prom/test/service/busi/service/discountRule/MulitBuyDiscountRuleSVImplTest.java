package com.zengshi.ecp.prom.test.service.busi.service.discountRule;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.prom.dubbo.dto.PromPresentDTO;
import com.zengshi.ecp.prom.service.busi.discountRule.interfaces.IPromDiscountRuleSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.alibaba.fastjson.JSON;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-9-8 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class MulitBuyDiscountRuleSVImplTest extends EcpServicesTest {

    @Resource
    private IPromDiscountRuleSV mulitBuyDiscountRuleSV;
  
   @Test
   public void testvalidNull() throws BusinessException { 
       mulitBuyDiscountRuleSV.valid(null, null);
  }
   @Test
   public void testvalidErr() throws BusinessException { 
       mulitBuyDiscountRuleSV.valid("asdf", null);
  }
   @Test
   public void testvalidSucc() throws BusinessException { 
       HashMap map=new HashMap();
       map.put("-2", "-50");
       map.put("3", "0");
       map.put("4", "450");
       map.put("5", "550");
       mulitBuyDiscountRuleSV.valid(JSON.toJSON(map).toString(), null);
  }
}
