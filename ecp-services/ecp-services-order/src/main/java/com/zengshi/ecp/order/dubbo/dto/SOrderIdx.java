package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class SOrderIdx extends BaseResponseDTO {
    /** 
     * serialVersionUID: 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 8217154681864532145L;
    /** 
     * orderId:订单号
     * @since JDK 1.6 
     */ 
    private String orderId;
    
    /** 
     * orderAmount:订单数量. 
     * @since JDK 1.7
     */ 
    private Long orderAmount;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

	public Long getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}
    
    
}

