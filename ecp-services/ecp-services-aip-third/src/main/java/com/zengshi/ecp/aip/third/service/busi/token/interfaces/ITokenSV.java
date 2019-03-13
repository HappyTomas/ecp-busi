package com.zengshi.ecp.aip.third.service.busi.token.interfaces;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.TokenRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


public interface ITokenSV {

    /**
     * 
     * token:获得当前店铺的token <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public String fetchShopToken(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException;
    
    /**
     * 
     * token:获得当前店铺的token json <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public String fetchShopTokenJson(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException;
    
    /**
     * 
     * token:平台token获得 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public TokenRespDTO fetchToken(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException;
    
    /**
     * 
     * token:平台token刷新 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public String refreshToken(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException;
}

