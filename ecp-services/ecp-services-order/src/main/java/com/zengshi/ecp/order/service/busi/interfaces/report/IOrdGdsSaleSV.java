package com.zengshi.ecp.order.service.busi.interfaces.report;

import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdGdsSaleSV {
    /** 
     * querySumBuyNumByShopId:店铺商品销售数量--支付过就算. <br/> 
     * @author cbl 
     * @param rGoodSaleRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public Long querySumBuyNumByShopId(RGoodSaleRequest rGoodSaleRequest) throws BusinessException;
}

