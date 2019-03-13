package com.zengshi.ecp.prom.test.service.busi.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;


import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.zengshi.ecp.prom.dubbo.dto.PromMatchDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromMatchSV;
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
public class PromMatchSVImplTest extends EcpServicesTest {

    @Resource
    private IPromMatchSV promMatchSV;
   
    @Test
    public void testqueryNUll() throws BusinessException {
        //PromMatchSkuDTO promMatchSkuDTO=new PromMatchSkuDTO();
        PageResponseDTO<PromMatchSkuRespDTO> page  =  promMatchSV.queryPromMatchSkuForPage(null);
        Assert.assertNull(page);
        
    }
    @Test
    public void testquery() throws BusinessException {
        PromMatchSkuDTO promMatchSkuDTO=new PromMatchSkuDTO();
        PageResponseDTO<PromMatchSkuRespDTO> page  =  promMatchSV.queryPromMatchSkuForPage(promMatchSkuDTO);
        Assert.assertNull(page);
        
    }
    @Test
    public void testqueryByPromId() throws BusinessException {
        PromMatchSkuDTO promMatchSkuDTO=new PromMatchSkuDTO();
        promMatchSkuDTO.setPromId(new Long(170));
        PageResponseDTO<PromMatchSkuRespDTO> page  =  promMatchSV.queryPromMatchSkuForPage(promMatchSkuDTO);
        Assert.assertNull(page);
        
    }
    /*
     * 验证单品 是否有自由搭配商品
     */
    @Test
    public void testqueryMatchListFree() throws BusinessException {
        try{
        PromRuleCheckDTO promRuleCheckDTO =new PromRuleCheckDTO();
        promRuleCheckDTO.setGdsId(new Long(37310));
        promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));
        promRuleCheckDTO.setSkuId(new Long(74508));
        promRuleCheckDTO.setShopId(new Long(69));
        promRuleCheckDTO.setSiteId(new Long(1));
       // promRuleCheckDTO.setStaffId("11");
        promRuleCheckDTO.setMatchType(PromConstants.PromMatchSku.MATCH_TYPE_1);
        List<PromMatchDTO>  l =  promMatchSV.queryMatchList(promRuleCheckDTO);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    /*
     * 验证单品 是否有固定搭配商品
     */
    @Test
    public void testqueryMatchListFixed() throws BusinessException {
        try{
        PromRuleCheckDTO promRuleCheckDTO =new PromRuleCheckDTO();
        promRuleCheckDTO.setGdsId(new Long(37287));
        promRuleCheckDTO.setCalDate(new Date());
        promRuleCheckDTO.setSkuId(new Long(74508));
        promRuleCheckDTO.setShopId(new Long(35));
        promRuleCheckDTO.setSiteId(new Long(1000));
        promRuleCheckDTO.setMatchType(PromConstants.PromMatchSku.MATCH_TYPE_2);
        List<PromMatchDTO>  l =  promMatchSV.queryMatchList(promRuleCheckDTO);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
