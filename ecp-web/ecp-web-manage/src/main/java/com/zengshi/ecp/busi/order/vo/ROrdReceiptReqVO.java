package com.zengshi.ecp.busi.order.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class ROrdReceiptReqVO extends EcpBasePageReqVO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4772148785333954775L;
	/** 
     * shopId:卖家ID
     * @since JDK 1.6 
     */ 
	@NotNull(message="{order.shopid.null.error}")
    private Long shopId;
    /** 
     * staffId:买家ID. 
     * @since JDK 1.6 
     */ 
	@NotNull(message="{order.staffid.null.error}")
    private Long staffId;
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
	@NotNull(message="{order.orderid.null.error}")
    private String orderId;
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    public Long getStaffId() {
        return staffId;
    }
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
