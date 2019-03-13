package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.general.order.dto.ROrdCartChangeRequest;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ROrdCartChgResponse extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1915029953162400657L;

    /** 
     * rOrdCartChangeRequest:购物车信息修改,组装请求促销域的入参对象. 
     * @since JDK 1.6 
     */ 
    private ROrdCartChangeRequest rOrdCartChangeRequest;

    public ROrdCartChangeRequest getrOrdCartChangeRequest() {
        return rOrdCartChangeRequest;
    }

    public void setrOrdCartChangeRequest(ROrdCartChangeRequest rOrdCartChangeRequest) {
        this.rOrdCartChangeRequest = rOrdCartChangeRequest;
    }
    
    
}

