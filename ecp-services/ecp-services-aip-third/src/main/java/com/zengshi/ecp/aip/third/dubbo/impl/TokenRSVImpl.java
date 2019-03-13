package com.zengshi.ecp.aip.third.dubbo.impl;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.TokenRespDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.ITokenRSV;
import com.zengshi.ecp.aip.third.service.busi.token.interfaces.ITokenSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

import javax.annotation.Resource;
/**
 * 
 * fetchToken:获得token <br/> 
 * @author huangjx
 * @return 
 * @since JDK 1.7
 */
public class TokenRSVImpl extends AipAbstractRSVImpl implements ITokenRSV {
	
	@Resource
	private ITokenSV defaultTokenSV;
	
    /**
     * 
     * token:获得当前店铺的token <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
	@Override
    public String fetchShopToken(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		return defaultTokenSV.fetchShopToken(baseShopAuthReqDTO);
	}
    
    /**
     * 
     * token:获得当前店铺的token json <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
	@Override
    public String fetchShopTokenJson(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		return defaultTokenSV.fetchShopTokenJson(baseShopAuthReqDTO);
	}
    
    /**
     * 
     * token:平台token获得 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
	@Override
    public TokenRespDTO fetchToken(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		return defaultTokenSV.fetchToken(baseShopAuthReqDTO);
	}
    
    /**
     * 
     * token:平台token刷新 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
	@Override
    public String refreshToken(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
		return defaultTokenSV.refreshToken(baseShopAuthReqDTO);
	}
 
}

