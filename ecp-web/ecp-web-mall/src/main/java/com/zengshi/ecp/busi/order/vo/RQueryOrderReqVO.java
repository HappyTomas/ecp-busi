package com.zengshi.ecp.busi.order.vo;

import java.sql.Date;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.paas.utils.DateUtil;

public class RQueryOrderReqVO extends EcpBasePageReqVO {
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    private Date begDate;

    private Date endDate;

    private Long staffId;

    private String status;

    private Long siteId;
    
    private Long shopId;

    private String sysType;

    private String billingFlag;

    private String orderId;

    public String getBillingFlag() {
        return billingFlag;
    }

    public void setBillingFlag(String billingFlag) {
        this.billingFlag = billingFlag;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getBegDate() {
        return begDate;
    }

    public void setBegDate(Date begDate) {
        this.begDate = begDate;
    }

    public Date getEndDate() {
        if(endDate!=null){
            return new java.sql.Date(DateUtil.addDays(endDate,1).getTime());
        }
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

}

