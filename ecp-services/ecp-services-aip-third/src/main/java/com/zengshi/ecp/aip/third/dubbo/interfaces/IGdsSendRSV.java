package com.zengshi.ecp.aip.third.dubbo.interfaces;

import java.util.HashMap;

import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsSendThirdReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsSendRSV {
    
    /**
     * 
     * send:商品推送 <br/> 
     * @author huangjx
     * @param sendParams
     * @return 
     * @since JDK 1.7
     */
    public HashMap send(GdsSendThirdReqDTO gdsSendReqDTO)
            throws BusinessException;
   
}

