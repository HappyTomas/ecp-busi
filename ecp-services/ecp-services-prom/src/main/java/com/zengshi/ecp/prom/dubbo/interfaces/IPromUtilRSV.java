package com.zengshi.ecp.prom.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.prom.dubbo.dto.CommPromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromStockLimitDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromUtilRSV {
 
    /**
     * 根据beanid获得context bean
     * @param beanId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CommPromTypeDTO utilGetCommPromTypeDTO(String beanId) throws BusinessException;
    
    /**
     * 
     * queryPromStockLimit:(根据促销id、商品id查询商品购买情况). <br/> 
     * @author PJieWin 
     * @param promId
     * @param skuId
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PromStockLimitDTO queryPromStockLimit(String promId, String skuId) throws BusinessException;
 
}
