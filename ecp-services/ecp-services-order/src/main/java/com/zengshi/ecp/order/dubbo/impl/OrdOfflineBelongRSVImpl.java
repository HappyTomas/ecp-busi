package com.zengshi.ecp.order.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdOfflineBelongRSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdOfflineBelongSV;

public class OrdOfflineBelongRSVImpl implements IOrdOfflineBelongRSV {

	public static final String MODULE = OrdOfflineBelongRSVImpl.class.getName();

	@Resource
	private IOrdOfflineBelongSV ordOfflineBelongSV;

	@Override
	public void dealOrdOffline() {
		// TODO Auto-generated method stub
		ordOfflineBelongSV.dealOrdOffline();
	}

	@Override
	public long updateOrdSubShopIdx(RGoodSaleRequest rGoodSaleRequest) {
		// TODO Auto-generated method stub
		return ordOfflineBelongSV.updateOrdSubShopIdx(rGoodSaleRequest);
	}

	@Override
	public long updateAllOrdSubShopIdx(RGoodSaleRequest rGoodSaleRequest) {
		// TODO Auto-generated method stub
		return ordOfflineBelongSV.updateAllOrdSubShopIdx(rGoodSaleRequest);
	}



}
