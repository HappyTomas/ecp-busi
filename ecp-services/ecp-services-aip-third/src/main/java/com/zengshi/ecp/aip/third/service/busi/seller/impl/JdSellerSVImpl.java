package com.zengshi.ecp.aip.third.service.busi.seller.impl;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.SellerRespDTO;
import com.zengshi.ecp.aip.third.service.busi.seller.interfaces.ISellerSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class JdSellerSVImpl implements ISellerSV{
    
    public static final String MODULE = JdSellerSVImpl.class.getName();

    @Override
    public SellerRespDTO getSellerInfo(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
    	return null;
    }
	
}
