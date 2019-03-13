/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsCategorySVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015年8月13日下午5:09:04 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
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
public class GdsInfoQueryRSVImplTest extends EcpServicesTest{
   
	@Resource
	private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
	@Test
	public void testCountGdsInfoByShopIDAndStatus(){
		GdsInfoReqDTO query = new GdsInfoReqDTO();
		query.setShopId(69L);
		query.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
		Long count = gdsInfoQueryRSV.countGdsInfoByShopIDAndStatus(query);
		System.out.println("=============count="+count+"==================");
	}
	
	
	
}
