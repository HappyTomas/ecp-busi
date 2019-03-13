package com.zengshi.ecp.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPlatRecomRSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class GdsPlatRecomRSVTest extends EcpServicesTest{

	@Resource
	private IGdsPlatRecomRSV gdsPlatRecomRSV;
	
	@Test
	public void testQueryGdsSkuInfoByCatgCode() {
		GdsPlatRecomReqDTO reqDTO = new GdsPlatRecomReqDTO();
		reqDTO.setCatgCode("1197");
		List<GdsInfoDetailRespDTO> lst = gdsPlatRecomRSV.queryGdsInfoDetailByCatgCode(reqDTO);
		System.out.println("@@@@@@@@@@@@@@@"+lst);
	}

}

