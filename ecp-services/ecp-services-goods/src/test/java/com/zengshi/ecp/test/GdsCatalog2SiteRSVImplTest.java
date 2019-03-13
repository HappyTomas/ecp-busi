/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsCategorySVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015年8月13日下午5:09:04 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test;

import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.zengshi.ecp.goods.dao.model.GdsCategory;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatg2PropSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropSV;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.alibaba.fastjson.JSONObject;

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
public class GdsCatalog2SiteRSVImplTest extends EcpServicesTest{
    
	@Resource
    private IGdsCatalog2SiteRSV gdsCatalog2SiteRSV;

   
	@Test
	public void testQueryRelationBySiteId(){
		GdsCatalog2SiteReqDTO query = new GdsCatalog2SiteReqDTO();
		query.setSiteId(1l);
		List<GdsCatalogRespDTO> lst = gdsCatalog2SiteRSV.queryRelationBySiteId(query);
		for(GdsCatalogRespDTO respDTO : lst){
			System.out.println(ToStringBuilder.reflectionToString(respDTO));
			
		}
	}
	
	
}
