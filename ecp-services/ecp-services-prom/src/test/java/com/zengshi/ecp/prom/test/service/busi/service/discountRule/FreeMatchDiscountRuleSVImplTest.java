package com.zengshi.ecp.prom.test.service.busi.service.discountRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
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
public class FreeMatchDiscountRuleSVImplTest extends EcpServicesTest {

    @Resource
    private IPromDiscountRuleSV freeMatchDiscountRuleSV;
  
   @Test
   public void testvalidNull() throws BusinessException { 
       freeMatchDiscountRuleSV.valid(null, null);
  }
   @Test
   public void testvalidErr() throws BusinessException { 
       freeMatchDiscountRuleSV.valid("asdf", null);
  }
   @Test
   public void testvalidSucc() throws BusinessException { 
       
       PromMatchSkuDTO dto=new PromMatchSkuDTO();
       
       dto.setGdsId(new Long(1));
       dto.setMatchType("1");
       dto.setPrice(new Long(100));
       dto.setPromCnt(new Long(10));
       dto.setSkuId(new Long(1));
       dto.setStatus("1");
       
        PromMatchSkuDTO dto1=new PromMatchSkuDTO();
           
        dto1.setGdsId(new Long(1));
        dto1.setMatchType("1");
        dto1.setPrice(new Long(100));
        dto1.setPromCnt(new Long(10));
        dto1.setSkuId(new Long(1));
        dto1.setStatus("1");
       
        List<PromMatchSkuDTO> l=new ArrayList<PromMatchSkuDTO>();
        l.add(dto1);
        l.add(dto);
       PromDTO promDTO=new PromDTO();
       promDTO.setMatchSkuDTOList(l);
       freeMatchDiscountRuleSV.valid("", promDTO);
  }
   @Test
   public void testvalidErr1() throws BusinessException { 
       
       PromMatchSkuDTO dto=new PromMatchSkuDTO();
       
       dto.setGdsId(new Long(1));
       dto.setPromCnt(new Long(10));
       dto.setSkuId(new Long(1));
       dto.setStatus("1");
       
        PromMatchSkuDTO dto1=new PromMatchSkuDTO();
           
        dto1.setGdsId(new Long(10102));
        dto1.setMatchType("1");
        dto1.setPrice(new Long(100));
        dto1.setPromCnt(new Long(10));
        dto1.setSkuId(new Long(10118));
        dto1.setStatus("11");
       
        List<PromMatchSkuDTO> l=new ArrayList<PromMatchSkuDTO>();
        l.add(dto1);
        //l.add(dto);
       PromDTO promDTO=new PromDTO();
       promDTO.setMatchSkuDTOList(l);
       
       ArrayList<String> al=new ArrayList<String>();
       al.add("10002");
       al.add("10003");
       promDTO.setCatgList(al);
       freeMatchDiscountRuleSV.valid("", promDTO);
  }
}
