package com.zengshi.ecp.general.order.interfaces;



import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdCartsChkRSV {
    /**
     * 
     * checkOrdCart:(购物车去结算校验方法). <br/> 
     * @author linwei
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ROrdCartsChkResponse checkOrdCart(ROrdCartsCommRequest info) throws BusinessException; 

}

