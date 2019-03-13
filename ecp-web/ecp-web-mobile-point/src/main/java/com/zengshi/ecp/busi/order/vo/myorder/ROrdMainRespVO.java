package com.zengshi.ecp.busi.order.vo.myorder;

/**
 * Created by wang on 16/8/2.
 */
public class ROrdMainRespVO {
    private String shopName;

    private Long shopId;

    private String status;

    private String statusName;

    private String orderId;

    private Long realMoney;

    private String showMoney;

    private String chtml;

    private Long scores;
    
    private String dispatchType;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(Long realMoney) {
        this.realMoney = realMoney;
    }

    public String getShowMoney() {
        return showMoney;
    }

    public void setShowMoney(String showMoney) {
        this.showMoney = showMoney;
    }

    public String getChtml() {
        return chtml;
    }

    public void setChtml(String chtml) {
        this.chtml = chtml;
    }

    public Long getScores() {
        return scores;
    }

    public void setScores(Long scores) {
        this.scores = scores;
    }

	public String getDispatchType() {
		return dispatchType;
	}

	public void setDispatchType(String dispatchType) {
		this.dispatchType = dispatchType;
	}
}
