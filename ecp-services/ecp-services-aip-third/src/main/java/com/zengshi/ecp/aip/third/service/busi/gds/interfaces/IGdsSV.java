package com.zengshi.ecp.aip.third.service.busi.gds.interfaces;

import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.ProductThirdReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


public interface IGdsSV {

    /**
     * 
     * 商品推送规则 xml<br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public String getGdsRule(ProductThirdReqDTO productReqDTO)throws BusinessException;
    
    /**
     * 
     * 商品添加<br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public String GdsAdd(GdsThirdReqDTO gdsThirdReqDTO)throws BusinessException;
}

