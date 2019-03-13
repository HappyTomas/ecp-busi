package com.zengshi.ecp.aip.third.service.busi.token.impl;
import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.TokenRespDTO;
import com.zengshi.ecp.aip.third.service.busi.token.interfaces.ITokenSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class YouzanTokenSVImpl implements ITokenSV{
    
    public static final String MODULE = YouzanTokenSVImpl.class.getName();
    
    @Override
    public TokenRespDTO fetchToken(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
    	return null;
    }
	
	//获得当前shop的token
	@Override
	public String fetchShopToken(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		return "";
    }
	//获得当前shop的token json
	@Override
	public String fetchShopTokenJson(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		 return "";
    }
    @Override
	public String refreshToken(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		 return "";
	 }
}

