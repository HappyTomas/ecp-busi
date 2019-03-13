package com.zengshi.ecp.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.goods.dao.model.GdsPlatRecom;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomRespDTO;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPlatRecomSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.DateUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 平推推荐测试类<br>
 * Date:2015年9月12日下午2:08:39  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public class GdsPlatRecomSVImplTest  extends EcpServicesTest{
	
	@Resource
	private IGdsPlatRecomSV gdsPlatRecomSV;
	
	
	@Test
	public void testQueryPlatRecom(){
		GdsPlatRecomReqDTO gdsPlatRecomReqDTO=new GdsPlatRecomReqDTO();
		gdsPlatRecomReqDTO.setPageSize(30);
		gdsPlatRecomReqDTO.setPageNo(1);
		PageResponseDTO<GdsPlatRecomRespDTO> page=gdsPlatRecomSV.queryGdsPlatRecomRespDTOPaging(gdsPlatRecomReqDTO);
		System.out.println(page.getCount());
	}
	
	
	
	@Test
	public void testQueryPlatRecomByPK(){
		GdsPlatRecomReqDTO gdsPlatRecomReqDTO=new GdsPlatRecomReqDTO();
		gdsPlatRecomReqDTO.setPageSize(30);
		gdsPlatRecomReqDTO.setPageNo(1);
		GdsPlatRecomRespDTO page=gdsPlatRecomSV.queryGdsPlatRecomByPK(28L);
		System.out.println(page);
	}
	
	
	
	@Test
	public void testSavePlatRecom(){
		GdsPlatRecom gdsPlatRecom=new GdsPlatRecom();
		gdsPlatRecom.setSkuId(74172L);
		gdsPlatRecom.setGdsId(37132L);
		gdsPlatRecom.setCreateStaff(1234L);
		gdsPlatRecom.setUpdateStaff(1234L);
		gdsPlatRecom.setSortNo(1);
		gdsPlatRecom.setCatgCode("111");
		gdsPlatRecom.setCreateTime(DateUtil.getSysDate());
		gdsPlatRecom.setUpdateTime(DateUtil.getSysDate());
		gdsPlatRecom.setStatus(GdsConstants.Commons.STATUS_VALID);
		gdsPlatRecomSV.saveGdsPlatRecom(gdsPlatRecom);
	}
	
	
	
	
	
	
	
	

}

