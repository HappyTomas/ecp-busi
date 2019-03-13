package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月18日下午3:11:29  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public class RConfirmDeliveRequest extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 7535033260370453487L;
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    /** 
     * staffId:买家ID. 
     * @since JDK 1.6 
     */ 
    private Long staffId;
    /** 
     * shopId:店铺ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    /** 
     * expressId:物流公司编号. 
     * @since JDK 1.6 
     */ 
    private Long expressId;
    /** 
     * expressNo:物流单号. 
     * @since JDK 1.6 
     */ 
    private String expressNo;
    /** 
     * remark:备注. 
     * @since JDK 1.6 
     */ 
    private String remark;
    /** 
     * isSendAll:订单是否全部发货. 
     * @since JDK 1.6 
     */ 
    private Boolean isSendAll;
    
    /** 
     * importNo:本次发货批次号. 
     * @since JDK 1.6 
     */ 
    private Long batchId;
    
    /** 
     * rConfirmSubInfo:子订单对象信息. 
     * @since JDK 1.6 
     */ 
    private List<RConfirmSubInfo> rConfirmSubInfo;
    /** 
     * deliveryType:配送方式. 
     * @since JDK 1.6 
     */ 
    private String deliveryType;    
    
    public Long getBatchId() {
        return batchId;
    }
    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }
    public List<RConfirmSubInfo> getrConfirmSubInfo() {
        return rConfirmSubInfo;
    }
    public void setrConfirmSubInfo(List<RConfirmSubInfo> rConfirmSubInfo) {
        this.rConfirmSubInfo = rConfirmSubInfo;
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

