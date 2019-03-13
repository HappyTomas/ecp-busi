package com.zengshi.ecp.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongListRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsMessageUtil;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatlog2ShopSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopSelectReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.LogUtil;

public class TestStockCatgChangeSV extends EcpServicesTest {
	@Resource
	IGdsCatlog2ShopSV gdsCatlog2ShopSV;
	@Resource
	IShopInfoRSV shopRSV;

	@Test
	public final void testSendChangeMessage() {
		StockInfoReqDTO stockInfoReqDTO = new StockInfoReqDTO();
		stockInfoReqDTO.setGdsId(37487l);
		stockInfoReqDTO.setSkuId(74777l);
		stockInfoReqDTO.setShopId(100l);
		stockInfoReqDTO.setCatgCode("1198");
		stockInfoReqDTO.setCompanyId(1l);
		GdsMessageUtil.sendStockCatgChangeMessage(stockInfoReqDTO);
		while (true) {
			try {
				Thread.currentThread();
				Thread.sleep(3L);
			} catch (Exception e) {
				LogUtil.error(this.getClass().getName(), "exception", e);
			}
		}
	}

	@Test
	public void testInsertCatgLogShop() {
		ShopSelectReqDTO reqDTO = new ShopSelectReqDTO(); 
		reqDTO.setPageNo(1);
		reqDTO.setPageSize(Integer.MAX_VALUE);
		PageResponseDTO<ShopInfoResDTO> list = shopRSV.listShopInfoByCond(reqDTO);
		for(ShopInfoResDTO infoResDTO:list.getResult()){
			GdsCatlog2ShopReqDTO catlog2ShopReqDTO = new GdsCatlog2ShopReqDTO(); 
			catlog2ShopReqDTO.setShopId(infoResDTO.getId());
			catlog2ShopReqDTO.setStatus("1");
			LongListRespDTO listRespDTO =   gdsCatlog2ShopSV.queryRelationedGdsCatlogIdByShopId(catlog2ShopReqDTO);
		if(listRespDTO == null){
			GdsCatlog2ShopReqDTO catlog2ShopReqDTO2 = new GdsCatlog2ShopReqDTO();
			catlog2ShopReqDTO2.setShopId(infoResDTO.getId());
			catlog2ShopReqDTO2.setCatlogId(1l);
			catlog2ShopReqDTO2.setStatus("1");
			gdsCatlog2ShopSV.saveGdsCatlog2Shop(catlog2ShopReqDTO2);
		}
		}

	}
}
