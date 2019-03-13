package com.zengshi.ecp.aip.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-aip-server <br>
 * Description: 农行对账响应类<br>
 * Date:2015年12月4日下午8:23:23  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class ABCPaySettleResponse extends BaseInfo{

    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -7005885221321742637L;

	private String returnCode;
	private String errorMessage;
	private String trxType;
	private String settleDate;
	private String numOfPayments;
	private String sumOfPayAmount;
	private String numOfRefunds;
	private String sumOfRefundAmount;
	private String detailRecords;
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
    public String getTrxType() {
        return trxType;
    }
    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }
    public String getSettleDate() {
        return settleDate;
    }
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }
    public String getNumOfPayments() {
        return numOfPayments;
    }
    public void setNumOfPayments(String numOfPayments) {
        this.numOfPayments = numOfPayments;
    }
    public String getSumOfPayAmount() {
        return sumOfPayAmount;
    }
    public void setSumOfPayAmount(String sumOfPayAmount) {
        this.sumOfPayAmount = sumOfPayAmount;
    }
    public String getNumOfRefunds() {
        return numOfRefunds;
    }
    public void setNumOfRefunds(String numOfRefunds) {
        this.numOfRefunds = numOfRefunds;
    }
    public String getSumOfRefundAmount() {
        return sumOfRefundAmount;
    }
    public void setSumOfRefundAmount(String sumOfRefundAmount) {
        this.sumOfRefundAmount = sumOfRefundAmount;
    }
    public String getDetailRecords() {
        return detailRecords;
    }
    public void setDetailRecords(String detailRecords) {
        this.detailRecords = detailRecords;
    }
	
	
}

