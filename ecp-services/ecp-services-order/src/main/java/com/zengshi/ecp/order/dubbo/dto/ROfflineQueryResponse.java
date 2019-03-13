package com.zengshi.ecp.order.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: <br>
 * Date:2015年10月3日下午5:06:32  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public class ROfflineQueryResponse extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 2412257741625915908L;

    /** 
     * staffId:买家ID. 
     * @since JDK 1.6 
     */ 
    private Long staffId;
    
    /**
     * staffName:买家昵称.
     * @since JDK 1.6
     */
    private String staffName;
    /**
     * staffName:买家昵称.
     * @since JDK 1.6
     */
    private String adminName;
    /** 
     * offlineNo:线下支付流水. 
     * @since JDK 1.6 
     */ 
    private Long offlineNo;
    
    /** 
     * applyType:申请类型. 
     * @since JDK 1.6 
     */ 
    private String applyType;
    /** 
     * remark:买家提交申请备注.
     * @since JDK 1.6 
     */ 
    private String remark;
    /**
     * 管理员审核意见
     */
    private String checkCont;
    /** 
     * creJateTime:提交时间. 
     * @since JDK 1.6 
     */ 
    private Timestamp orderTime;
    
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    /** 
     * orderType:订单类型. 
     * @since JDK 1.6 
     */ 
    private String orderType;
    /** 
     * realMoney:实付金额. 
     * @since JDK 1.6 
     */ 
    private Long realMoney;
    /** 
     * siteId:站点. 
     * @since JDK 1.6 
     */ 
    private Long siteId;
    /** 
     * annex:附件. 
     * @since JDK 1.6 
     */ 
    List<SOfflinePic> annex;

    /**
     * 操作人员
     */
    private Long updateStaff;

    /**
     * 审核状态
     */
    private String status;
    
    public String getStaffName() {
        return staffName;
    }
    public String getAdminName() {
        return adminName;
    }
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    public Long getSiteId() {
        return siteId;
    }
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public Long getRealMoney() {
        return realMoney;
    }
    public void setRealMoney(Long realMoney) {
        this.realMoney = realMoney;
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
    public Long getOfflineNo() {
        return offlineNo;
    }
    public void setOfflineNo(Long offlineNo) {
        this.offlineNo = offlineNo;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getCheckCont() {
        return checkCont;
    }
    public void setCheckCont(String checkCont) {
        this.checkCont = checkCont;
    }
    public List<SOfflinePic> getAnnex() {
        return annex;
    }
    public void setAnnex(List<SOfflinePic> annex) {
        this.annex = annex;
    }
    public String getApplyType() {
        return applyType;
    }
    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }
    public Timestamp getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }
    public Long getUpdateStaff() {
        return updateStaff;
    }
    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

