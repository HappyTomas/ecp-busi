package com.zengshi.ecp.test.client.stock;

import javax.annotation.Resource;

import com.zengshi.ecp.server.test.EcpServicesTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.stock.GdsStockReqDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.catg.IUnpfCatgRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.stock.IUnpfStockRSV;

/**
* @author  huangjx: 
* @date 创建时间：2016年11月25日 下午2:29:20 
* @version 1.0 
**/
public class UnpfStockTest extends EcpServicesTest{

	@Resource
	private IUnpfStockRSV unpfStockRSV;
	
	@Test
	public void updateStock(){
		GdsStockReqDTO gdsStockReqDTO=new GdsStockReqDTO();
		gdsStockReqDTO.setGdsId(21098L);
		gdsStockReqDTO.setShopId(35L);
		gdsStockReqDTO.setPlatType("youzan");
		gdsStockReqDTO.setQuanties(5555L);
		unpfStockRSV.updateStock(gdsStockReqDTO);
	}
}


