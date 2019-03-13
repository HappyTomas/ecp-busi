package com.zengshi.ecp.busi.order.sub.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class RConfirmDeliveReqVO extends EcpBasePageReqVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7023117530868224098L;
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    /** 
     * staffId:店铺ID. 
     * @since JDK 1.6 
     */ 
    private Long staffId;
    /** 
     * shopId:买家ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    /** 
     * sendName:发货人姓名. 
     * @since JDK 1.6 
     */ 
    private Long expressId;
    private String expressNo;
    private String remark;
    private Boolean isSendAll;
	private List<RConfirmSubInfoVO> rConfirmSubInfo;


    private String deliveryType;
	
	public List<RConfirmSubInfoVO> getrConfirmSubInfo() {
		return rConfirmSubInfo;
	}
	public void setrConfirmSubInfo(List<RConfirmSubInfoVO> orderSubInfo) {
		this.rConfirmSubInfo = orderSubInfo;
	}
	public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Long getStaffId() {
        return staffId;
    }
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    public Long getExpressId() {
        return expressId;
    }
    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }
    public String getExpressNo() {
        return expressNo;
    }
    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public Boolean getIsSendAll() {
        return isSendAll;
    }
    public void setIsSendAll(Boolean isSendAll) {
        this.isSendAll = isSendAll;
    }
    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

}
