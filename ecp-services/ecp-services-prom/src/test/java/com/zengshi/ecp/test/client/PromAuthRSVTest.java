/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoClientTest.java 
 * Package Name:com.zengshi.ecp.test.client 
 * Date:2015-8-3下午6:55:12 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.test.client;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.httpclient.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromType4ShopDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromType4ShopResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.QueryPromType4ShopDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromAuthRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dubbo/client/dubbo-test-prom.xml")
public class PromAuthRSVTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private IPromAuthRSV promAuthRSV;
 
   /* @Test
    public void testNullSave() {
            List<PromType4ShopDTO> promType4ShopDTOList=new ArrayList<PromType4ShopDTO>();
            promAuthRSV.savePromType4Shop(promType4ShopDTOList);
            System.out.println("test");
    }
    @Test
    public void testUpdateStatus() {
        PromType4ShopDTO promType4ShopDTO=new PromType4ShopDTO();
        promType4ShopDTO.setUpdateStaff(new Long(1));
        promType4ShopDTO.setUpdateTime(new Date());
        promType4ShopDTO.setEndTime(new Date());
        promType4ShopDTO.setPromTypeCode("code");
        promType4ShopDTO.setShopId(new Long(1));
        promType4ShopDTO.setStartTime(new Date());
        promType4ShopDTO.setId(new Long(1));
        promType4ShopDTO.setStatus("1");
        promAuthRSV.updatePromType4ShopById(promType4ShopDTO);
    }
     
    @Test
    public void testUpdate() {
        PromType4ShopDTO promType4ShopDTO=new PromType4ShopDTO();
        promType4ShopDTO.setUpdateStaff(new Long(221));
        promType4ShopDTO.setUpdateTime(new Date());
        promType4ShopDTO.setEndTime(new Date());
        promType4ShopDTO.setPromTypeCode("code");
        promType4ShopDTO.setShopId(new Long(1));
        promType4ShopDTO.setStartTime(new Date());
        promType4ShopDTO.setId(new Long(2));
        promAuthRSV.updatePromType4ShopById(promType4ShopDTO);
    }
    */
    @Test
    public void testSave() {
            List<PromType4ShopDTO> promType4ShopDTOList=new ArrayList<PromType4ShopDTO>();
            PromType4ShopDTO promType4ShopDTO=new PromType4ShopDTO();
           // promType4ShopDTO.setCreateStaff(new Long(1));
           // promType4ShopDTO.setCreateTime(new Date());
            try {
                promType4ShopDTO.setEndTime(DateUtil.parseDate("20151101"));
            } catch (DateParseException e) {
                e.printStackTrace();
            }
            promType4ShopDTO.setPromTypeCode("1000000001");
            promType4ShopDTO.setShopId(new Long(1));
            try {
                promType4ShopDTO.setStartTime(DateUtil.parseDate("20150101"));
            } catch (DateParseException e) {
                e.printStackTrace();
            }
            promType4ShopDTO.setStatus("1");
            promType4ShopDTOList.add(promType4ShopDTO);
            
             
            PromType4ShopDTO promType4ShopDTO1=new PromType4ShopDTO();
           // promType4ShopDTO1.setCreateStaff(new Long(1));
           // promType4ShopDTO1.setCreateTime(new Date());
            try {
                promType4ShopDTO1.setEndTime(DateUtil.parseDate("20151101"));
            } catch (DateParseException e1) {
                e1.printStackTrace();
            }
            promType4ShopDTO1.setPromTypeCode("1000000001");
            promType4ShopDTO1.setShopId(new Long(1));
            try {
                promType4ShopDTO1.setStartTime(DateUtil.parseDate("20150101"));
            } catch (DateParseException e) {
                e.printStackTrace();
            }
            promType4ShopDTO1.setStatus("1");
            promType4ShopDTOList.add(promType4ShopDTO1);
            promAuthRSV.savePromType4Shop(promType4ShopDTOList);
            
            System.out.println("test");
    }
    
    @Test
    public void testQuery() {
        
            QueryPromType4ShopDTO queryPromType4ShopDTO=new QueryPromType4ShopDTO();
            queryPromType4ShopDTO.setPromTypeCode("1000000001");
            queryPromType4ShopDTO.setShopId(new Long(1));
            queryPromType4ShopDTO.setStatus("1");
           // queryPromType4ShopDTO.setEffectiveDate(new Date());
            queryPromType4ShopDTO.setPageSize(8);
            PageResponseDTO<PromType4ShopResponseDTO> p=promAuthRSV.queryPromType4ShopForPage(queryPromType4ShopDTO);
            
            System.out.println("test");
    }
    
}
