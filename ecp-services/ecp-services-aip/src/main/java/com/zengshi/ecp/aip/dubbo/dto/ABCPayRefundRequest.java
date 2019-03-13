package com.zengshi.ecp.aip.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-aip-server <br>
 * Description: 农行退款请求<br>
 * Date:2015年12月4日下午8:23:23  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class ABCPayRefundRequest extends BaseInfo{

    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -7005885221321742637L;

	private String orderDate;
	private String orderTime;
	private String merRefundAccountNo;
	private String merRefundAccountName;
    private String orderNo;
    private String newOrderNo;
    private String currencyCode;
    private String trxAmount;
    private String merchantRemarks;
    private boolean connectionFlag;
    private String merchantID;
    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public String getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
    public String getMerRefundAccountNo() {
        return merRefundAccountNo;
    }
    public void setMerRefundAccountNo(String merRefundAccountNo) {
        this.merRefundAccountNo = merRefundAccountNo;
    }
    public String getMerRefundAccountName() {
        return merRefundAccountName;
    }
    public void setMerRefundAccountName(String merRefundAccountName) {
        this.merRefundAccountName = merRefundAccountName;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getNewOrderNo() {
        return newOrderNo;
    }
    public void setNewOrderNo(String newOrderNo) {
        this.newOrderNo = newOrderNo;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public String getTrxAmount() {
        return trxAmount;
    }
    public void setTrxAmount(String trxAmount) {
        this.trxAmount = trxAmount;
    }
    public String getMerchantRemarks() {
        return merchantRemarks;
    }
    public void setMerchantRemarks(String merchantRemarks) {
        this.merchantRemarks = merchantRemarks;
    }
    public boolean getConnectionFlag() {
        return connectionFlag;
    }
    public void setConnectionFlag(boolean connectionFlag) {
        this.connectionFlag = connectionFlag;
    }
    public String getMerchantID() {
        return merchantID;
    }
    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }
    
	
}

