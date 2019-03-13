package com.zengshi.ecp.busi.order.vo;

import java.sql.Timestamp;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class ROrderBackReqVO extends EcpBasePageReqVO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -7224146266120095042L;
    /** 
     * begDate:查询开始时间. 
     * @since JDK 1.6 
     */ 
    private Timestamp begDate;
    
    /** 
     * endDate:查询结束时间. 
     * @since JDK 1.6 
     */ 
    private Timestamp endDate;
    /** 
     * siteId:所属站点. 
     * @since JDK 1.6 
     */ 
    private Long siteId;
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    
    /** 
     * tabFlag:查询状态. 
     * @since JDK 1.6 
     */ 
    private String tabFlag;
    
    /** 
     * backId:退货申请单. 
     * @since JDK 1.6 
     */ 
    private Long backId;
    
    /** 
     * applyType:申请类型. 
     * @since JDK 1.6 
     */ 
    private String applyType;
    
    
    /** 
     * shopId:卖家ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    
    

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId = backId;
    }

    public Timestamp getBegDate() {
        return begDate;
    }

    public void setBegDate(Timestamp begDate) {
        this.begDate = begDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTabFlag() {
        return tabFlag;
    }

    public void setTabFlag(String tabFlag) {
        this.tabFlag = tabFlag;
    }
    
    
}

