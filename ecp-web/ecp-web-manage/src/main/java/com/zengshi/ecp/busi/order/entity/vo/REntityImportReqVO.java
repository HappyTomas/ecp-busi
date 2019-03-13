package com.zengshi.ecp.busi.order.entity.vo;

import java.io.Serializable;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class REntityImportReqVO extends EcpBasePageReqVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3205663687429280825L;

    
    /** 
     * shopId:卖家ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    /** 
     * importNo:批次号. 
     * @since JDK 1.6 
     */ 
    private Long importNo;
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Long getImportNo() {
        return importNo;
    }
    public void setImportNo(Long importNo) {
        this.importNo = importNo;
    }
}
