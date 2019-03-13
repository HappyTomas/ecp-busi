package com.zengshi.ecp.order.dubbo.dto;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class SOrderSubIdx extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -5412380799442318511L;
    
    /** 
     * orderSubId:子订单号. 
     * @since JDK 1.6 
     */ 
    private String orderSubId;

    public String getOrderSubId() {
        return orderSubId;
    }

    public void setOrderSubId(String orderSubId) {
        this.orderSubId = orderSubId;
    }
    

}

