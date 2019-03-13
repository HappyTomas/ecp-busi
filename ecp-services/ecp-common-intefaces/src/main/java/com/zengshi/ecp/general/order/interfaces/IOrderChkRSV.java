package com.zengshi.ecp.general.order.interfaces;

import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrderChkRSV {
    /** 
     * checkOrdSub:提交订单校验公共方法. <br/> 
     * @author cbl 
     * @param rOrdCartsChkRequest
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public ROrdCartsChkResponse checkOrder(ROrdCartsCommRequest rOrdCartsCommRequest) throws BusinessException; 
}

