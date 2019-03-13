package com.zengshi.ecp.aip.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-aip-server <br>
 * Description: 农行退款响应类<br>
 * Date:2015年12月4日下午8:23:23  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class ABCPayRefundResponse extends BaseInfo{

    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -7005885221321742637L;

	private String returnCode;
	private String errorMessage;
	private String orderNo;
	private String newOrderNo;
	private String trxAmount;
    private String batchNo;
    private String voucherNo;
    private String hostDate;
    private String hostTime;
    private String iRspRef;
    public String getReturnCode() {
        return returnCode;
    }
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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
    public String getTrxAmount() {
        return trxAmount;
    }
    public void setTrxAmount(String trxAmount) {
        this.trxAmount = trxAmount;
    }
    public String getBatchNo() {
        return batchNo;
    }
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
    public String getVoucherNo() {
        return voucherNo;
    }
    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }
    public String getHostDate() {
        return hostDate;
    }
    public void setHostDate(String hostDate) {
        this.hostDate = hostDate;
    }
    public String getHostTime() {
        return hostTime;
    }
    public void setHostTime(String hostTime) {
        this.hostTime = hostTime;
    }
    public String getiRspRef() {
        return iRspRef;
    }
    public void setiRspRef(String iRspRef) {
        this.iRspRef = iRspRef;
    }
    
    
	
}

