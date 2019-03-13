package com.zengshi.ecp.prom.test.service.busi.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.prom.dubbo.dto.CartPromRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuLimitDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.alibaba.fastjson.JSON;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-6 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromSVImplTest extends EcpServicesTest {

    @Resource
    private IPromSV promSV;
 
    
   @Test
    public void testSave() throws BusinessException {

        PromDTO promDTO = new PromDTO();
        // promDTO.setStatus("10");
        promDTO.setCreateStaff(new Long(1));
        promDTO.setCreateTime(new Date());
        promDTO.setPromTypeShow("满减");
        promDTO.setPromImg("asdf");
        promDTO.setPromUrl("www.baidu.com");
        promDTO.setShowStartTime(new Date());
        promDTO.setStartTime(new Date());
        promDTO.setShopId(new Long(1));
        promDTO.setPromTypeShow("满减");
        promDTO.setPromTypeCode("1000000001");
        promDTO.setPromTheme("主题");
        promDTO.setPromContent("内容");
        promDTO.setPromClass("20");// 10 order 20 product
        promDTO.setPriority(new Long(0));
        promDTO.setIfBlacklist("1");

        ArrayList<PromSkuLimitDTO> l = new ArrayList<PromSkuLimitDTO>();
        PromSkuLimitDTO promSkuLimitDTO = new PromSkuLimitDTO();
        promSkuLimitDTO.setGdsId(new Long(1));
        promSkuLimitDTO.setSkuId(new Long(1));
        promSkuLimitDTO.setCreateTime(new Date());
        promSkuLimitDTO.setCreateStaff(promDTO.getCreateStaff());

        PromSkuLimitDTO promSkuLimitDTO1 = new PromSkuLimitDTO();
        promSkuLimitDTO1.setGdsId(new Long(1));
        promSkuLimitDTO1.setSkuId(new Long(1));
        promSkuLimitDTO1.setCreateTime(new Date());
        promSkuLimitDTO1.setCreateStaff(promDTO.getCreateStaff());

        PromSkuLimitDTO promSkuLimitDTO2 = new PromSkuLimitDTO();
        promSkuLimitDTO2.setGdsId(new Long(1));
        promSkuLimitDTO2.setSkuId(new Long(1));
        promSkuLimitDTO2.setCreateTime(new Date());
        promSkuLimitDTO2.setCreateStaff(promDTO.getCreateStaff());

        l.add(promSkuLimitDTO);
        l.add(promSkuLimitDTO1);
        l.add(promSkuLimitDTO2);
        promDTO.setBlackSkuList(l);
        promDTO.setJoinRange("1");// 1 全场 0 部分
        promDTO.setIfShow("1");
        promDTO.setIfMatch("1");
        // promDTO.setId(new Long(11));
        promDTO.setEndTime(new Date());
        promDTO.setShowEndTime(new Date());
        promDTO.setCreateTime(new Date());
        promDTO.setCreateStaff(new Long(1100000));

        PromSkuDTO promSkuDTO = new PromSkuDTO();
        promSkuDTO.setCreateStaff(promDTO.getCreateStaff());
        promSkuDTO.setCreateTime(new Date());
        promSkuDTO.setEndTime(promDTO.getEndTime());
        promSkuDTO.setGdsId(new Long(10000));
        promSkuDTO.setIfShow("1");
        promSkuDTO.setMatchType("1");
        promSkuDTO.setPriority(new Long(0));
        promSkuDTO.setPromCnt(new Long(100));
        promSkuDTO.setSkuId(new Long(111));
        promSkuDTO.setStartTime(promDTO.getStartTime());
        promSkuDTO.setStatus("1");

        PromSkuDTO promSkuDTO1 = new PromSkuDTO();
        promSkuDTO1.setCreateStaff(promDTO.getCreateStaff());
        promSkuDTO1.setCreateTime(new Date());
        promSkuDTO1.setEndTime(promDTO.getEndTime());
        promSkuDTO1.setGdsId(new Long(10000));
        promSkuDTO1.setIfShow("1");
        promSkuDTO1.setMatchType("1");
        promSkuDTO1.setPriority(new Long(0));
        promSkuDTO1.setPromCnt(new Long(100));
        promSkuDTO1.setSkuId(new Long(11122));
        promSkuDTO1.setStartTime(promDTO.getStartTime());
        promSkuDTO1.setStatus("1");

        ArrayList<PromSkuDTO> promSkuDTOList = new ArrayList<PromSkuDTO>();
        promSkuDTOList.add(promSkuDTO1);
        promSkuDTOList.add(promSkuDTO);
        promDTO.setSkuList(promSkuDTOList);

        PromConstraintDTO promConstraintDTO = new PromConstraintDTO();
        promConstraintDTO.setArea("1");
        promConstraintDTO.setAreaValue("50");
        promConstraintDTO.setChannel("0");
        promConstraintDTO.setCustLevel("1");
        promConstraintDTO.setCustLevelValue("1");
        promDTO.setPromConstaintDTO(promConstraintDTO);

        // 获得json条件
         String json = "{\"buyX\":\"100\",\"sendY\":\"2\"}";
       // String json = "{\"orderMoney\":\"100\",\"sendAmount\":\"2\",\"coupId\":\"2111\"}";
        promDTO.setDiscountRule(json);
        promSV.saveProm(promDTO);

    }
   
    @Test
    public void testeditSave() throws BusinessException {

        PromDTO promDTO = new PromDTO();
        // promDTO.setStatus("10");
        promDTO.setId(new Long(12));
        promDTO.setCreateStaff(new Long(1));
        promDTO.setCreateTime(new Date());
        promDTO.setPromTypeShow("a");
        promDTO.setPromImg("asdf");
        promDTO.setPromUrl("asdf");
        promDTO.setShowStartTime(new Date());
        promDTO.setStartTime(new Date());
        promDTO.setShopId(new Long(11));
        promDTO.setPromTypeShow("满减");
        promDTO.setPromTypeCode("1000000001");
        promDTO.setPromTheme("主题");
        promDTO.setPromContent("内容");
        promDTO.setPromClass("20");// 10 order 20 product
        promDTO.setPriority(new Long(500));
        promDTO.setIfBlacklist("1");

        ArrayList<PromSkuLimitDTO> l = new ArrayList<PromSkuLimitDTO>();
        PromSkuLimitDTO promSkuLimitDTO = new PromSkuLimitDTO();
        promSkuLimitDTO.setGdsId(new Long(1));
        promSkuLimitDTO.setSkuId(new Long(1));
        promSkuLimitDTO.setCreateTime(new Date());
        promSkuLimitDTO.setCreateStaff(promDTO.getCreateStaff());

        PromSkuLimitDTO promSkuLimitDTO1 = new PromSkuLimitDTO();
        promSkuLimitDTO1.setGdsId(new Long(1));
        promSkuLimitDTO1.setSkuId(new Long(1));
        promSkuLimitDTO1.setCreateTime(new Date());
        promSkuLimitDTO1.setCreateStaff(promDTO.getCreateStaff());

        PromSkuLimitDTO promSkuLimitDTO2 = new PromSkuLimitDTO();
        promSkuLimitDTO2.setGdsId(new Long(1));
        promSkuLimitDTO2.setSkuId(new Long(1));
        promSkuLimitDTO2.setCreateTime(new Date());
        promSkuLimitDTO2.setCreateStaff(promDTO.getCreateStaff());

        l.add(promSkuLimitDTO);
        l.add(promSkuLimitDTO1);
        l.add(promSkuLimitDTO2);
        promDTO.setBlackSkuList(l);
        promDTO.setJoinRange("1");// 1 全场 0 部分
        promDTO.setIfShow("1");
        // promDTO.setId(new Long(11));
        promDTO.setEndTime(new Date());
        promDTO.setShowEndTime(new Date());
        promDTO.setCreateTime(new Date());
        promDTO.setCreateStaff(new Long(1100000));
        promDTO.setIfMatch("1");
        PromSkuDTO promSkuDTO = new PromSkuDTO();
        promSkuDTO.setCreateStaff(promDTO.getCreateStaff());
        promSkuDTO.setCreateTime(new Date());
        promSkuDTO.setEndTime(promDTO.getEndTime());
        promSkuDTO.setGdsId(new Long(10000));
        promSkuDTO.setIfShow("1");
        promSkuDTO.setMatchType("1");
        promSkuDTO.setPriority(new Long(0));
        promSkuDTO.setPromCnt(new Long(100));
        promSkuDTO.setSkuId(new Long(111));
        promSkuDTO.setStartTime(promDTO.getStartTime());
        promSkuDTO.setStatus("1");

        PromSkuDTO promSkuDTO1 = new PromSkuDTO();
        promSkuDTO1.setCreateStaff(promDTO.getCreateStaff());
        promSkuDTO1.setCreateTime(new Date());
        promSkuDTO1.setEndTime(promDTO.getEndTime());
        promSkuDTO1.setGdsId(new Long(10000));
        promSkuDTO1.setIfShow("1");
        promSkuDTO1.setMatchType("1");
        promSkuDTO1.setPriority(new Long(0));
        promSkuDTO1.setPromCnt(new Long(100));
        promSkuDTO1.setSkuId(new Long(11122));
        promSkuDTO1.setStartTime(promDTO.getStartTime());
        promSkuDTO1.setStatus("1");

        ArrayList<PromSkuDTO> promSkuDTOList = new ArrayList<PromSkuDTO>();
        promSkuDTOList.add(promSkuDTO1);
        promSkuDTOList.add(promSkuDTO);
        promDTO.setSkuList(promSkuDTOList);

        PromConstraintDTO promConstraintDTO = new PromConstraintDTO();
        promConstraintDTO.setArea("1");
        promConstraintDTO.setAreaValue("50");
        promConstraintDTO.setChannel("0");
        promConstraintDTO.setCustLevel("1");
        promConstraintDTO.setCustLevelValue("1");
        promDTO.setPromConstaintDTO(promConstraintDTO);

        // 获得json条件
        //String json = "{\"buyX\":\"100\",\"sendY\":\"2\"}";
        String json = "{\"orderMoney\":\"100\",\"sendAmount\":\"2\",\"coupId\":\"2111\"}";
        promDTO.setDiscountRule(json);
        promSV.saveProm(promDTO);

    }

    @Test
    public void testCreate() throws BusinessException {

        PromDTO promDTO = new PromDTO();
        // promDTO.setStatus("10");
        promDTO.setCreateStaff(new Long(1));
        promDTO.setCreateTime(new Date());
        promDTO.setPromTypeShow("a");
        promDTO.setPromImg("asdf");
        promDTO.setPromUrl("asdf");
        promDTO.setShowStartTime(new Date());
        promDTO.setStartTime(new Date());
        promDTO.setShopId(new Long(11));
        promDTO.setPromTypeShow("满减");
        promDTO.setPromTypeCode("1000000001");
        promDTO.setPromTheme("主题");
        promDTO.setPromContent("内容");
        promDTO.setPromClass("20");// 10 order 20 product
        promDTO.setPriority(new Long(500));
        promDTO.setIfBlacklist("1");

        ArrayList<PromSkuLimitDTO> l = new ArrayList<PromSkuLimitDTO>();
        PromSkuLimitDTO promSkuLimitDTO = new PromSkuLimitDTO();
        promSkuLimitDTO.setGdsId(new Long(1));
        promSkuLimitDTO.setSkuId(new Long(1));
        promSkuLimitDTO.setCreateTime(new Date());
        promSkuLimitDTO.setCreateStaff(promDTO.getCreateStaff());

        PromSkuLimitDTO promSkuLimitDTO1 = new PromSkuLimitDTO();
        promSkuLimitDTO1.setGdsId(new Long(1));
        promSkuLimitDTO1.setSkuId(new Long(1));
        promSkuLimitDTO1.setCreateTime(new Date());
        promSkuLimitDTO1.setCreateStaff(promDTO.getCreateStaff());

        PromSkuLimitDTO promSkuLimitDTO2 = new PromSkuLimitDTO();
        promSkuLimitDTO2.setGdsId(new Long(1));
        promSkuLimitDTO2.setSkuId(new Long(1));
        promSkuLimitDTO2.setCreateTime(new Date());
        promSkuLimitDTO2.setCreateStaff(promDTO.getCreateStaff());

        l.add(promSkuLimitDTO);
        l.add(promSkuLimitDTO1);
        l.add(promSkuLimitDTO2);
        promDTO.setBlackSkuList(l);
        promDTO.setJoinRange("1");// 1 全场 0 部分
        promDTO.setIfShow("1");
        // promDTO.setId(new Long(11));
        promDTO.setEndTime(new Date());
        promDTO.setShowEndTime(new Date());
        promDTO.setCreateTime(new Date());
        promDTO.setCreateStaff(new Long(1100000));
        promDTO.setIfMatch("1");
        PromSkuDTO promSkuDTO = new PromSkuDTO();
        promSkuDTO.setCreateStaff(promDTO.getCreateStaff());
        promSkuDTO.setCreateTime(new Date());
        promSkuDTO.setEndTime(promDTO.getEndTime());
        promSkuDTO.setGdsId(new Long(10000));
        promSkuDTO.setIfShow("1");
        promSkuDTO.setMatchType("1");
        promSkuDTO.setPriority(new Long(0));
        promSkuDTO.setPromCnt(new Long(100));
        promSkuDTO.setSkuId(new Long(111));
        promSkuDTO.setStartTime(promDTO.getStartTime());
        promSkuDTO.setStatus("1");

        PromSkuDTO promSkuDTO1 = new PromSkuDTO();
        promSkuDTO1.setCreateStaff(promDTO.getCreateStaff());
        promSkuDTO1.setCreateTime(new Date());
        promSkuDTO1.setEndTime(promDTO.getEndTime());
        promSkuDTO1.setGdsId(new Long(10000));
        promSkuDTO1.setIfShow("1");
        promSkuDTO1.setMatchType("1");
        promSkuDTO1.setPriority(new Long(0));
        promSkuDTO1.setPromCnt(new Long(100));
        promSkuDTO1.setSkuId(new Long(11122));
        promSkuDTO1.setStartTime(promDTO.getStartTime());
        promSkuDTO1.setStatus("1");

        ArrayList<PromSkuDTO> promSkuDTOList = new ArrayList<PromSkuDTO>();
        promSkuDTOList.add(promSkuDTO1);
        promSkuDTOList.add(promSkuDTO);
        promDTO.setSkuList(promSkuDTOList);

        PromConstraintDTO promConstraintDTO = new PromConstraintDTO();
        promConstraintDTO.setArea("1");
        promConstraintDTO.setAreaValue("50");
        promConstraintDTO.setChannel("0");
        promConstraintDTO.setCustLevel("1");
        promConstraintDTO.setCustLevelValue("1");
        promDTO.setPromConstaintDTO(promConstraintDTO);

        // 获得json条件
        //String json = "{\"buyX\":\"100\",\"sendY\":\"2\"}";
        String json = "{\"orderMoney\":\"100\",\"sendAmount\":\"2\",\"coupId\":\"2111\"}";
        promDTO.setDiscountRule(json);
        promSV.saveProm(promDTO);

    }

    @Test
    public void testeditCreate() throws BusinessException {

        PromDTO promDTO = new PromDTO();
        // promDTO.setStatus("10");
        promDTO.setId(new Long(12));
        promDTO.setCreateStaff(new Long(1));
        promDTO.setCreateTime(new Date());
        promDTO.setPromTypeShow("a");
        promDTO.setPromImg("asdf");
        promDTO.setPromUrl("asdf");
        promDTO.setShowStartTime(new Date());
        promDTO.setStartTime(new Date());
        promDTO.setShopId(new Long(11));
        promDTO.setPromTypeShow("满减");
        promDTO.setPromTypeCode("1000000001");
        promDTO.setPromTheme("主题");
        promDTO.setPromContent("内容");
        promDTO.setPromClass("20");// 10 order 20 product
        promDTO.setPriority(new Long(500));
        promDTO.setIfBlacklist("1");
        promDTO.setIfMatch("1");
        ArrayList<PromSkuLimitDTO> l = new ArrayList<PromSkuLimitDTO>();
        PromSkuLimitDTO promSkuLimitDTO = new PromSkuLimitDTO();
        promSkuLimitDTO.setGdsId(new Long(1));
        promSkuLimitDTO.setSkuId(new Long(1));
        promSkuLimitDTO.setCreateTime(new Date());
        promSkuLimitDTO.setCreateStaff(promDTO.getCreateStaff());

        PromSkuLimitDTO promSkuLimitDTO1 = new PromSkuLimitDTO();
        promSkuLimitDTO1.setGdsId(new Long(1));
        promSkuLimitDTO1.setSkuId(new Long(1));
        promSkuLimitDTO1.setCreateTime(new Date());
        promSkuLimitDTO1.setCreateStaff(promDTO.getCreateStaff());

        PromSkuLimitDTO promSkuLimitDTO2 = new PromSkuLimitDTO();
        promSkuLimitDTO2.setGdsId(new Long(1));
        promSkuLimitDTO2.setSkuId(new Long(1));
        promSkuLimitDTO2.setCreateTime(new Date());
        promSkuLimitDTO2.setCreateStaff(promDTO.getCreateStaff());

        l.add(promSkuLimitDTO);
        l.add(promSkuLimitDTO1);
        l.add(promSkuLimitDTO2);
        promDTO.setBlackSkuList(l);
        promDTO.setJoinRange("1");// 1 全场 0 部分
        promDTO.setIfShow("1");
        // promDTO.setId(new Long(11));
        promDTO.setEndTime(new Date());
        promDTO.setShowEndTime(new Date());
        promDTO.setCreateTime(new Date());
        promDTO.setCreateStaff(new Long(1100000));

        PromSkuDTO promSkuDTO = new PromSkuDTO();
        promSkuDTO.setCreateStaff(promDTO.getCreateStaff());
        promSkuDTO.setCreateTime(new Date());
        promSkuDTO.setEndTime(promDTO.getEndTime());
        promSkuDTO.setGdsId(new Long(10000));
        promSkuDTO.setIfShow("1");
        promSkuDTO.setMatchType("1");
        promSkuDTO.setPriority(new Long(0));
        promSkuDTO.setPromCnt(new Long(100));
        promSkuDTO.setSkuId(new Long(111));
        promSkuDTO.setStartTime(promDTO.getStartTime());
        promSkuDTO.setStatus("1");

        PromSkuDTO promSkuDTO1 = new PromSkuDTO();
        promSkuDTO1.setCreateStaff(promDTO.getCreateStaff());
        promSkuDTO1.setCreateTime(new Date());
        promSkuDTO1.setEndTime(promDTO.getEndTime());
        promSkuDTO1.setGdsId(new Long(10000));
        promSkuDTO1.setIfShow("1");
        promSkuDTO1.setMatchType("1");
        promSkuDTO1.setPriority(new Long(0));
        promSkuDTO1.setPromCnt(new Long(100));
        promSkuDTO1.setSkuId(new Long(11122));
        promSkuDTO1.setStartTime(promDTO.getStartTime());
        promSkuDTO1.setStatus("1");

        ArrayList<PromSkuDTO> promSkuDTOList = new ArrayList<PromSkuDTO>();
        promSkuDTOList.add(promSkuDTO1);
        promSkuDTOList.add(promSkuDTO);
        promDTO.setSkuList(promSkuDTOList);

        PromConstraintDTO promConstraintDTO = new PromConstraintDTO();
        promConstraintDTO.setArea("1");
        promConstraintDTO.setAreaValue("50");
        promConstraintDTO.setChannel("0");
        promConstraintDTO.setCustLevel("1");
        promConstraintDTO.setCustLevelValue("1");
        promDTO.setPromConstaintDTO(promConstraintDTO);

        // 获得json条件
        String json = "{\"buyX\":\"100\",\"sendY\":\"2\"}";
        //String json = "{\"orderMoney\":\"100\",\"sendAmount\":\"2\",\"coupId\":\"2111\"}";
        promDTO.setDiscountRule(json);
        
        List<PromGiftDTO> giftList=new ArrayList<PromGiftDTO>();
        PromGiftDTO giftDto=new PromGiftDTO();
        giftDto.setGdsId(new Long(1));
        giftDto.setGiftId(new Long(1));
        giftDto.setPresentAllCnt(new Long(111));
        giftDto.setSkuId(new Long(1));
        giftList.add(giftDto);
        promDTO.setGiftList(giftList);
        promSV.createProm(promDTO);

    }
 /*
    @Test
    public void testMatchList() throws BusinessException {
        Long shopId=new Long(1);
        Long gdsId=new Long(1);
        Long skuId=new Long(1);
        Date calDate=new Date();
        List<PromMatchSkuResponseDTO>  l= promSV.queryMatchList(shopId, gdsId, skuId, calDate,null);
        assertEquals(8,l.size());
    }*/
   
    
    @Test
    public void testGdsPromList() throws BusinessException {
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
        ordPromDTO.setShopId(new Long(1));
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
        ordPromDetailDTO.setOrderAmount(new Long(1000));
        ordPromDetailDTO.setBasePrice(new Long(100));
        ordPromDetailDTO.setBuyPrice(new Long(80));
        //ordPromDetailDTO.setPromId(null);
        ordPromDetailDTO.setPromInfoDTOList(null);
        ordPromDetailDTO.setPromTypeMsgResponseDTO(null);
        ordPromDetailDTO.setShopId(new Long(1));
        ordPromDetailDTO.setSkuId(new Long(1));
        ordPromDetailDTO.setStaffId("1");
        List<OrdPromDetailDTO> ordPromDetailDTOList=new ArrayList<OrdPromDetailDTO>();
        ordPromDetailDTOList.add(ordPromDetailDTO);
        
        ordPromDTO.setOrdPromDetailDTOList(ordPromDetailDTOList);
        
        promSV.initOrdPromByGds(ordPromDTO);
        System.out.println("over");
        
        
    }
    @Test
    public void testOrdList() throws BusinessException {
       
        OrdPromDTO ordPromDTO = new OrdPromDTO();
        ordPromDTO.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        ordPromDTO.setCalDate(new Date());
        ordPromDTO.setCartType("1");
        ordPromDTO.setCreateStaff(new Long(1));
        ordPromDTO.setId(new Long(1));
       // ordPromDTO.setOrdPromDetailDTOList(ordPromDetailDTOList);
       // ordPromDTO.setPromId(null);
        ordPromDTO.setPromInfoDTOList(null);
        ordPromDTO.setPromTypeMsgResponseDTO(null);
        ordPromDTO.setShopId(new Long(1));
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
        ordPromDetailDTO.setBuyPrice(new Long(80));
        //ordPromDetailDTO.setPromId(null);
        ordPromDetailDTO.setPromInfoDTOList(null);
        ordPromDetailDTO.setPromTypeMsgResponseDTO(null);
        ordPromDetailDTO.setShopId(new Long(1));
        ordPromDetailDTO.setSkuId(new Long(4));
        ordPromDetailDTO.setStaffId("1");
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
        promSV.initOrdPromByGds(ordPromDTO);
        promSV.initOrdPromByOrder(ordPromDTO);
        System.out.println("test");
    }
    
    @Test
      public void testcheckSave() {
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
              ordPromDTO.setShopId(new Long(1));
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
              ordPromDetailDTO.setBuyPrice(new Long(80));
             // ordPromDetailDTO.setPromId(null);
              ordPromDetailDTO.setPromInfoDTOList(null);
              ordPromDetailDTO.setPromTypeMsgResponseDTO(null);
              ordPromDetailDTO.setShopId(new Long(1));
              ordPromDetailDTO.setSkuId(new Long(4));
              ordPromDetailDTO.setStaffId("1");
              PromInfoDTO promInfoDTO =new PromInfoDTO();
              promInfoDTO.setId(new Long(79));
              ordPromDetailDTO.setPromInfoDTO(promInfoDTO);
              ordPromDetailDTO.setOrdPromId(new Long(90));
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
              PromInfoDTO promInfoDTO1=new PromInfoDTO();
              promInfoDTO1.setId(new Long(90));
              ordPromDTO.setPromInfoDTO(promInfoDTO1);
               promSV.checkPromOrd(promRuleCheckDTO, ordPromDTO);
              System.out.println("test");

      }

    @Test
      public void testordPromDTO() {
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
              dto.setGiftId(new Long(2));
              dto.setPresentCnt(new Long(1));
              List<PromGiftDTO> promGiftDTOList=new ArrayList<PromGiftDTO>();
              promGiftDTOList.add(dto);
              ordPromDetailDTO.setPromGiftDTOList(promGiftDTOList);
           //  promSV.savePromOrd(ordPromDTO,PromConstants.PromSys.doAction_SAVE);
                 promSV.savePromOrd(ordPromDTO,PromConstants.PromSys.doAction_ROLLBACK);
              System.out.println("test");

      }
    
    private void listPromByGds(){
    }
}
