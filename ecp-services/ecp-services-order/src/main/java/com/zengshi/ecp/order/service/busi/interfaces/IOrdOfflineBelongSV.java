package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;

public interface IOrdOfflineBelongSV {
	
	 void dealOrdOffline();
	 
	 long updateOrdSubShopIdx(RGoodSaleRequest rGoodSaleRequest);
	 
	 public long updateAllOrdSubShopIdx(RGoodSaleRequest rGoodSaleRequest);
}
