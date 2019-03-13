package com.zengshi.ecp.busi.seller.order.vo;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class ROfflineQueryReqVO  extends EcpBasePageReqVO{

    /**
     * 
     */
    private static final long serialVersionUID = 8923358076454966206L;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date begDate;

    /**
     * 截止时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    private String orderId;
    private String staffId;
    private Long shopId;
    private Long siteId;
    private String applyType;
    private String status;
    

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Date getBegDate() {
        return begDate;
    }

    public void setBegDate(Date begDate) {
        this.begDate = begDate;
    }

    public Date getEndDate() {
        if(endDate!=null){
            return DateUtils.addDays(endDate, 1);
        }
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }
}

