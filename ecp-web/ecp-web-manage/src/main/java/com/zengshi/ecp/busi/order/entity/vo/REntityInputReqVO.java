package com.zengshi.ecp.busi.order.entity.vo;

import java.io.Serializable;
import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class REntityInputReqVO  extends EcpBasePageReqVO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7336899126415341708L;
	
	private String orderId;
    private String orderSubId;
    private Long shopId;
    private Long staffId;
    private Long sendNum;
    /** 
     * entitys:实体编号列表. 
     * @since JDK 1.6 
     */ 
    private List<String> entitys;
    public List<String> getEntitys() {
        return entitys;
    }
    public void setEntitys(List<String> entitys) {
        this.entitys = entitys;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
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
    public Long getSendNum() {
        return sendNum;
    }
    public void setSendNum(Long sendNum) {
        this.sendNum = sendNum;
    }
    public String getOrderSubId() {
        return orderSubId;
    }
    public void setOrderSubId(String orderSubId) {
        this.orderSubId = orderSubId;
    }
}
