package com.zengshi.ecp.order.dubbo.interfaces;

import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdDetailsRSV {
    /** 
     * queryOrderDetails:订单详情查询. <br/> 
     * @author cbl 
     * @param rOrderDetailsRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public ROrderDetailsResponse queryOrderDetails(ROrderDetailsRequest rOrderDetailsRequest) throws BusinessException;
}

