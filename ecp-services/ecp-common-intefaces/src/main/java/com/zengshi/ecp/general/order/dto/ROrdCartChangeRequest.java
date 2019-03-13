package com.zengshi.ecp.general.order.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrdCartChangeRequest extends BaseInfo{

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -6280210958015575257L;
    
    
    /** 
     * rOrdCartCommRequest:勾选的商品信息. 
     * @since JDK 1.6 
     */ 
    private ROrdCartCommRequest rOrdCartCommRequest;
    
    
    /** 
     * rOrdCartItemCommRequest:变更的商品明细. 
     * @since JDK 1.6 
     */ 
    private ROrdCartItemCommRequest rOrdCartItemCommRequest;


    public ROrdCartCommRequest getrOrdCartCommRequest() {
        return rOrdCartCommRequest;
    }


    public void setrOrdCartCommRequest(ROrdCartCommRequest rOrdCartCommRequest) {
        this.rOrdCartCommRequest = rOrdCartCommRequest;
    }


    public ROrdCartItemCommRequest getrOrdCartItemCommRequest() {
        return rOrdCartItemCommRequest;
    }


    public void setrOrdCartItemCommRequest(ROrdCartItemCommRequest rOrdCartItemCommRequest) {
        this.rOrdCartItemCommRequest = rOrdCartItemCommRequest;
    }
    
    
    

}

