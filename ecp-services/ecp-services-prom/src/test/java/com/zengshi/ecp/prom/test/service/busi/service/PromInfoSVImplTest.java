package com.zengshi.ecp.prom.test.service.busi.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.prom.dao.mapper.common.PromTypeMapper;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromStockLimitDTO;
import com.zengshi.ecp.prom.dubbo.dto.sort.ComparatorPromInfoDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
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
public class PromInfoSVImplTest extends EcpServicesTest {

    @Resource
    private IPromInfoSV promInfoSV;
    
    @Resource
    private PromTypeMapper promtypemapper;
    
    @Test
    public void testPlatQuery() throws BusinessException {
        
        // poromInfoDTO 平台查询 设置ifPlat==1
        PromInfoDTO promInfoDTO=new PromInfoDTO();
        promInfoDTO.setIfPlatQuery(PromConstants.PromSys.IF_PLAT_QUERY);
        promInfoDTO.setPageSize(8);
        promInfoDTO.setShowStartTime(new Timestamp(new Date().getTime()));
        promInfoDTO.setShowEndTime(new Timestamp(new Date().getTime()));
        PageResponseDTO<PromInfoResponseDTO> promInfoResponseDTOList =  promInfoSV
                .queryPromInfoForPage(promInfoDTO,"");
        System.out.println(promInfoResponseDTOList.getCount());
    }
    @Test
    public void testSortList() throws BusinessException {
        
        // poromInfoDTO 平台查询 设置ifPlat==1
        PromInfoDTO promInfoDTO=new PromInfoDTO();
       // promInfoDTO.setIfPlatQuery(PromConstants.PromSys.IF_PLAT_QUERY);
        promInfoDTO.setPageSize(8);
       // promInfoDTO.setShowStartTime(new Timestamp(new Date().getTime()));
        //promInfoDTO.setShowEndTime(new Timestamp(new Date().getTime()));
        PageResponseDTO<PromInfoResponseDTO> promInfoResponseDTOList =  promInfoSV
                .queryPromInfoForPage(promInfoDTO,"");
        
        List<PromInfoDTO> list=new ArrayList<PromInfoDTO>();
        
        if(promInfoResponseDTOList!=null && promInfoResponseDTOList.getCount()>0 ){
            for(PromInfoResponseDTO r:promInfoResponseDTOList.getResult()){
                
                PromInfoDTO o=new PromInfoDTO();
                o.setId(r.getId());
                o.setPriority(r.getPriority());
                o.setCreateTime(new Timestamp(r.getCreateTime().getTime()));
                list.add(o);
            }
           
        }
        if(!CollectionUtils.isEmpty(list)){
            ComparatorPromInfoDTO comparator = new ComparatorPromInfoDTO();
            Collections.sort(list, comparator);
        }
        
        System.out.println(promInfoResponseDTOList.getCount());
    }
    
 
/*
    @Test
    public void testPlatQuery() throws BusinessException {
        
        // poromInfoDTO 平台查询 设置ifPlat==1
        PromInfoDTO promInfoDTO=new PromInfoDTO();
        promInfoDTO.setIfPlatQuery(PromConstants.PromSys.IF_PLAT_QUERY);
        promInfoDTO.setPageSize(8);
        PageResponseDTO<PromInfoResponseDTO> promInfoResponseDTOList =  promInfoSV
                .queryPromInfoForPage(promInfoDTO);
        System.out.println(promInfoResponseDTOList.getCount());
    }
    @Test
    public void testQueryGroupIdsNull() throws BusinessException {
        
        // poromInfoDTO 平台查询 设置ifPlat==1
        PromInfoDTO promInfoDTO=new PromInfoDTO();
        promInfoDTO.setPageSize(8);
        PageResponseDTO<PromInfoResponseDTO> promInfoResponseDTOList =  promInfoSV.queryPromInfoForPage(promInfoDTO, null);
        System.out.println(promInfoResponseDTOList.getCount());
    }
    @Test
    public void testQueryGroupIdsNotNull() throws BusinessException {
        
        // poromInfoDTO 平台查询 设置ifPlat==1
        PromInfoDTO promInfoDTO=new PromInfoDTO();
        promInfoDTO.setPageSize(8);
       List<Long> l=new ArrayList<Long>();
       l.add(new Long(30));
        PageResponseDTO<PromInfoResponseDTO> promInfoResponseDTOList =  promInfoSV.queryPromInfoForPage(promInfoDTO, l);
        System.out.println(promInfoResponseDTOList.getCount());
    }
    @Test
    public void testQueryGroupIds1() throws BusinessException {
        
        // poromInfoDTO 平台查询 设置ifPlat==1
        PromInfoDTO promInfoDTO=new PromInfoDTO();
        promInfoDTO.setPageSize(8);
       List<Long> l=new ArrayList<Long>();
       l.add(new Long(-30));
        PageResponseDTO<PromInfoResponseDTO> promInfoResponseDTOList =  promInfoSV.queryPromInfoForPage(promInfoDTO, l);
        System.out.println(promInfoResponseDTOList.getCount());
    }*/
    
/*    @Test
    public void testupdateDeduce() throws BusinessException {
        PromStockLimitDTO promStockLimitDTO=new PromStockLimitDTO();
        promStockLimitDTO.setBuyCnt(new Long(1));
        promStockLimitDTO.setGdsId(new BigDecimal(10000));
        promStockLimitDTO.setPromId(new Long(81));
        promStockLimitDTO.setSkuId(new BigDecimal(11122));
        int k=promInfoSV.updatePromStockLimitDeduce(promStockLimitDTO);
        assertEquals(1, k);
    }*/
    
    @Test
    public void testupdateAdd() throws BusinessException {
        PromStockLimitDTO promStockLimitDTO=new PromStockLimitDTO();
        promStockLimitDTO.setBuyCnt(new Long(1));
        promStockLimitDTO.setGdsId(new Long(10000));
        promStockLimitDTO.setPromId(new Long(81));
        promStockLimitDTO.setSkuId(new Long(11122));
        int k=promInfoSV.updatePromStockLimitAdd(promStockLimitDTO);
        assertEquals(1, k);
    }
    
    @Test
    public void queryPromInfoDTOById() throws BusinessException {
        long t=System.currentTimeMillis();
       PromInfoDTO d= promInfoSV.queryPromInfoDTOById(Long.valueOf(3304));
        System.out.println((System.currentTimeMillis()-t)+"毫秒");
    }
    
    @Test
    public void queryTotalByProm() throws BusinessException {
        long t=System.currentTimeMillis();
         Long d= promInfoSV.queryTotalByProm(null);
        System.out.println((System.currentTimeMillis()-t)+"毫秒");
    }
    @Test
    public void testcommon() throws BusinessException {
        promtypemapper.selectByPrimaryKey("test");
    }
}
