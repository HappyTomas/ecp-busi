package com.zengshi.ecp.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.goods.dao.model.GdsGuessYL;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLRespDTO;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsGuessYLSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.DateUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description:猜你喜欢测试类 <br>
 * Date:2015年9月12日下午2:08:52  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public class GdsGuessYLSVImplTest  extends EcpServicesTest{
	
	
	
	
	@Resource
	private IGdsGuessYLSV gdsGuessYLSV;
	
	
	@Test
	public void testQueryGuessYL(){
		GdsGuessYLReqDTO gdsGuessYLReqDTO=new GdsGuessYLReqDTO();
		gdsGuessYLReqDTO.setPageSize(30);
		gdsGuessYLReqDTO.setPageNo(1);
		PageResponseDTO<GdsGuessYLRespDTO> page=gdsGuessYLSV.queryGdsGuessYLRespDTOPaging(gdsGuessYLReqDTO);
		System.out.println(page.getCount());
	}
	
	
	
	@Test
	public void testQueryGuessYLByPK(){
		GdsGuessYLReqDTO gdsGuessYLReqDTO=new GdsGuessYLReqDTO();
		gdsGuessYLReqDTO.setPageSize(30);
		gdsGuessYLReqDTO.setPageNo(1);
		GdsGuessYLRespDTO page=gdsGuessYLSV.queryGdsGuessYLByPK(22L);
		System.out.println(page);
	}
	
	
	
	@Test
	public void testSaveGuessYL(){
		GdsGuessYL gdsGuessYL=new GdsGuessYL();
		gdsGuessYL.setSkuId(74172L);
		gdsGuessYL.setGdsId(37132L);
		gdsGuessYL.setCreateStaff(1234L);
		gdsGuessYL.setUpdateStaff(1234L);
		gdsGuessYL.setSortNo(1);
		gdsGuessYL.setCatgCode("112");
		gdsGuessYL.setCreateTime(DateUtil.getSysDate());
		gdsGuessYL.setUpdateTime(DateUtil.getSysDate());
		gdsGuessYL.setStatus(GdsConstants.Commons.STATUS_VALID);
		gdsGuessYLSV.saveGdsGuessYL(gdsGuessYL);
	}
	
	
	

}

