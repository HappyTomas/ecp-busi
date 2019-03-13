package com.zengshi.ecp.order.dubbo.interfaces;

import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;

public interface IOrdOfflineBelongRSV {

	 void dealOrdOffline();
	 
	 long updateOrdSubShopIdx(RGoodSaleRequest rGoodSaleRequest);
	 
	 public long updateAllOrdSubShopIdx(RGoodSaleRequest rGoodSaleRequest);
}
