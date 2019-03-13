package com.zengshi.ecp.aip.third.service.busi.seller.interfaces;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.SellerRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


public interface ISellerSV {

    /**
     * 
     * ifSeller:判断天猫店铺 淘宝店铺 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public SellerRespDTO getSellerInfo(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException;
    
}

