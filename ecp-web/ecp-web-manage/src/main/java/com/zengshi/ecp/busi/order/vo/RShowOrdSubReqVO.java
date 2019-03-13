package com.zengshi.ecp.busi.order.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class RShowOrdSubReqVO extends EcpBasePageReqVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2760909753103437421L;
	
	@NotNull(message="{order.orderid.null.error}")
	private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
