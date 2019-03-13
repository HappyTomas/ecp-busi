package com.zengshi.ecp.test;

import java.util.List;

import javassist.runtime.DotClass;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceCategoryReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceCategoryRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceCategoryRSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015年8月5日下午10:25:12  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxm9
 * @version  
 * @since JDK 1.6 
 */ 
public class CmsPlaceCategoryRSVImplTest extends EcpServicesTest{

	 @Resource
	 private ICmsPlaceCategoryRSV cmsPlaceCategoryRSV;
	
	 /** 
	     * queryCmsPlaceTest:(测试内容位置查询). <br/> 
	     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	     * 
	     * @author   
	     * @since JDK 1.6 
	     */ 
	    @Test
	    public void queryCmsPlaceCategoryTest() {
	        CmsPlaceCategoryReqDTO dto = new CmsPlaceCategoryReqDTO();
	        dto.setPlaceId(11L);
	        List<CmsPlaceCategoryRespDTO> list= cmsPlaceCategoryRSV.queryCmsPlaceCategory(dto);
	        return;
	    }
	    
//	    @Test
	  public void addCmsPlaceCategoryTest() {
		  CmsPlaceCategoryReqDTO dto = new CmsPlaceCategoryReqDTO();
		  dto.setPlaceId(411L);
		  dto.setCatgId("201");
		  cmsPlaceCategoryRSV.addCmsPlaceCategory(dto);
	}
	    
	    @Test
	    public void updateCmsPlaceCategoryTest() {
	    	 CmsPlaceCategoryReqDTO dto = new CmsPlaceCategoryReqDTO();
	    	 dto.setId(10000L);
	    	 dto.setCatgId("201");
	    	 dto.setPlaceId(8L);
	    	 cmsPlaceCategoryRSV.updateCmsPlaceCategory(dto);
		}
}
