package com.zengshi.ecp.prom.test.service.busi.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.drools.core.command.assertion.AssertEquals;
import org.junit.Test;

import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromCalRuleDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuLimitDTO;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromSV;
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
public class PromQuerySVImplTest extends EcpServicesTest {

    @Resource
    private IPromQuerySV promQuerySV;
  
 /*  @Test
    public void testQueryGiftNUll() throws BusinessException {
        
        List<PromGiftDTO> l=promQuerySV.queryPromGift(new Long(-10));
        Assert.assertNull(l);
        
    }
    @Test
    public void testQueryGiftNUll1() throws BusinessException {
        
        List<PromGiftDTO> l=promQuerySV.queryPromGift(null);
        Assert.assertNull(l);
        
    }
    @Test
    public void testQueryGift() throws BusinessException {
        
        List<PromGiftDTO> l=promQuerySV.queryPromGift(new Long(1));
        Assert.assertEquals(1, l.size());
    }
    
    @Test
    public void testPresentNull() throws BusinessException {
        
        List<PromPresentDTO> l=promQuerySV.promPresent(null);
        Assert.assertNull(l);
        
    }
    @Test
    public void testPresentNull1() throws BusinessException {
        
        List<PromPresentDTO> l=promQuerySV.promPresent(new Long(-1));
        Assert.assertNull(l);
        
    }
    @Test
    public void testPresent() throws BusinessException {
        
        List<PromPresentDTO> l=promQuerySV.promPresent(new Long(30));
        Assert.assertNull(l);
        
    }
    @Test
    public void testqueryNUll() throws BusinessException {
        PromConstraintDTO p=  promQuerySV.queryPromConstraint(new Long(-100));
        Assert.assertNull(p);
        
    }
    @Test
    public void testquery() throws BusinessException {
        PromConstraintDTO p=  promQuerySV.queryPromConstraint(new Long(30));
        Assert.assertNull(p);
        
    }
    @Test
    public void testqueryNull() throws BusinessException {
        PromConstraintDTO p=  promQuerySV.queryPromConstraint(null);
        Assert.assertNull(p);
        
    }
    */
    
    @Test
    public void testQueryGiftNUll1() throws BusinessException {
        
        List<PromGiftDTO> l=promQuerySV.queryPromGift(new Long(13003));
        List<PromGiftDTO> l1=promQuerySV.queryPromGift(new Long(13009));
        System.out.println(l.get(0));
        System.out.println(l1.get(0));
        //Assert.assertNull(l);
        
    }
    
    @Test
    public void testqueryNUll() throws BusinessException {
        PromCalRuleDTO p=  promQuerySV.queryPromCalRule(new Long(-100));
        Assert.assertNull(p);
        
    }
    @Test
    public void testquery() throws BusinessException {
        PromCalRuleDTO p=  promQuerySV.queryPromCalRule(new Long(30));
        Assert.assertNull(p);
        
    }
    @Test
    public void testqueryNull() throws BusinessException {
        PromCalRuleDTO p=  promQuerySV.queryPromCalRule(null);
        Assert.assertNull(p);
        
    }
    @Test
    public void testquerySkuLimit() throws BusinessException {
        PromSkuLimitDTO promSkuLimitDTO=new PromSkuLimitDTO();
        promSkuLimitDTO.setPromId(new Long(78));
        List<PromSkuLimitDTO> p=   promQuerySV.listPromotionSkuLimit(promSkuLimitDTO);
        Assert.assertNull(p);
        
    }
 
}
