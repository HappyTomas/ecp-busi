package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RGroupChgRequest extends BaseInfo{

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3902809102506263999L;
    //来源
    private String source;

    /** 
     * rOrdCartItemRequests:组合商品信息. 
     * @since JDK 1.6 
     */ 
    private List<ROrdCartItemRequest>  rOrdCartItemRequests;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<ROrdCartItemRequest> getrOrdCartItemRequests() {
        return rOrdCartItemRequests;
    }

    public void setrOrdCartItemRequests(List<ROrdCartItemRequest> rOrdCartItemRequests) {
        this.rOrdCartItemRequests = rOrdCartItemRequests;
    }
    
    
}

