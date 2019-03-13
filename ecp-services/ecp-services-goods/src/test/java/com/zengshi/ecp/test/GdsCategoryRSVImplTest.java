/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsCategorySVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015年8月13日下午5:09:04 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月13日下午5:09:04  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsCategoryRSVImplTest extends EcpServicesTest{
   
	@Resource
	private IGdsCategoryRSV gdsCategoryRSV;
    
	@Test
	public void testQuerySubCategoryConnectByCatgParent(){
		GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
		reqDTO.setCatgParent("1115");
		List<GdsCategoryRespDTO> lst = gdsCategoryRSV.querySubCategoryConnectByCatgParent(reqDTO);
		for(GdsCategoryRespDTO respDTO : lst){
			System.out.println("=============catgCode:"+respDTO.getCatgCode()+"  catgName:"+respDTO.getCatgName());
		}
	}
	
	
	
}
