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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromType4ShopDTO;
import com.zengshi.ecp.prom.dubbo.dto.QueryPromType4ShopDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromAuthRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupRSV;
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
public class PromGroupRSVTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private IPromGroupRSV promGroupRSV;
 
 /*  @Test
    public void testNullSave() {
            promGroupRSV.savePromGroup(null);
            System.out.println("test");
    }*/
   
   @Test
    public void testSave() {
            PromGroupDTO promGroupDTO=new PromGroupDTO();
            promGroupDTO.setCreateStaff(new Long(1));
            promGroupDTO.setCreateTime(new Date());
            promGroupDTO.setPromContent("conteste");
            promGroupDTO.setPromTheme("theme");
            promGroupDTO.setPromUrl("www.baidu.com");
            promGroupDTO.setShowEndTime(new Date());
            promGroupDTO.setShowStartTime(new Date());
            promGroupDTO.setSiteId(new Long(2));
            //promGroupDTO.setStatus("1");
            promGroupRSV.savePromGroup(promGroupDTO);
            System.out.println("test");
    }
   
   
   @Test
    public void testEditSave() {
            PromGroupDTO promGroupDTO=new PromGroupDTO();
            promGroupDTO.setCreateStaff(new Long(111));
            promGroupDTO.setCreateTime(new Date());
            promGroupDTO.setPromContent("conteste11");
            promGroupDTO.setPromTheme("theme11");
            promGroupDTO.setPromUrl("www.baidu.com11");
            promGroupDTO.setShowEndTime(new Date());
            promGroupDTO.setShowStartTime(new Date());
            promGroupDTO.setId(new Long(1));
            promGroupDTO.setUpdateStaff(new Long(1));
            promGroupDTO.setUpdateTime(new Date());
            //promGroupDTO.setStatus("1");
            promGroupRSV.saveEditPromGroup(promGroupDTO);
            System.out.println("test");
    } 
      
   

   @Test
    public void testCreate() {
            PromGroupDTO promGroupDTO=new PromGroupDTO();
            promGroupDTO.setCreateStaff(new Long(1));
            promGroupDTO.setCreateTime(new Date());
            promGroupDTO.setPromContent("contestetestCreate");
            promGroupDTO.setPromTheme("themetestCreate");
            promGroupDTO.setPromUrl("www.baidu.comtestCreate");
            promGroupDTO.setShowEndTime(new Date());
            promGroupDTO.setShowStartTime(new Date());
            promGroupDTO.setSiteId(new Long(3));
            promGroupRSV.createPromGroup(promGroupDTO);
            System.out.println("test");
    }
   
   
   @Test
    public void testEditCreate() {
            PromGroupDTO promGroupDTO=new PromGroupDTO();
          /*  promGroupDTO.setCreateStaff(new Long(111));
            promGroupDTO.setCreateTime(new Date());
            promGroupDTO.setPromContent("conteste11edit");
            promGroupDTO.setPromTheme("theme11edit");
            promGroupDTO.setPromUrl("www.baidu.com11edit");
            promGroupDTO.setShowEndTime(new Date());
            promGroupDTO.setShowStartTime(new Date());*/
            promGroupDTO.setId(new Long(75));
            promGroupDTO.setUpdateStaff(new Long(1));
            promGroupDTO.setUpdateTime(new Date());
            promGroupDTO.setStatus("9");
            promGroupRSV.saveEditPromGroup(promGroupDTO);
            System.out.println("test");
    } 
   @Test
   public void testqueryById() {
       PromGroupDTO promGroupDTO=new PromGroupDTO();
       promGroupDTO.setId(new Long(7));
       PromGroupResponseDTO a=promGroupRSV.queryPromGroupById(promGroupDTO);
       System.out.println(a);
   }
    @Test
    public void testqueryGroup() {
        PromGroupDTO promGroupDTO=new PromGroupDTO();
        PageResponseDTO<PromGroupResponseDTO> l=promGroupRSV.queryPromGroupList(promGroupDTO);
        System.out.println(l.getCount());
    }
   @Test
   public void testquery4ShopList() {
       Long groupId=new Long(1);
       PromInfoDTO promInfoDTO=new PromInfoDTO();
       PageResponseDTO<PromInfoResponseDTO> l=promGroupRSV.queryPromGroup4Shop(groupId,promInfoDTO);
       System.out.println(l.getCount());
   }
}
