package com.zengshi.ecp.order.dubbo.dto;

import java.io.Serializable;

public class SOrderDetailsComm implements Serializable{
    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -6622093674004896115L;

    /** 
     * invoiceTitle:发票抬头. 
     * @since JDK 1.6 
     */ 
    private String invoiceTitle;

    /** 
     * invoiceContent:发票内容. 
     * @since JDK 1.6 
     */ 
    private String invoiceContent;

    /** 
     * invoiceType:发票类型. 
     * @since JDK 1.6 
     */ 
    private String invoiceType;

    /** 
     * takerPhone:收票人手机. 
     * @since JDK 1.6 
     */ 
    private String takerPhone;

    /** 
     * takerEmail:收票人邮箱. 
     * @since JDK 1.6 
     */ 
    private String takerEmail;
    
    /** 
     * detailFlag:是否附加明细. 
     * @since JDK 1.6 
     */ 
    private String detailFlag;    

    private String taxpayerNo;
    
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getTakerPhone() {
        return takerPhone;
    }

    public void setTakerPhone(String takerPhone) {
        this.takerPhone = takerPhone;
    }

    public String getTakerEmail() {
        return takerEmail;
    }

    public void setTakerEmail(String takerEmail) {
        this.takerEmail = takerEmail;
    }

    public String getDetailFlag() {
        return detailFlag;
    }

    public void setDetailFlag(String detailFlag) {
        this.detailFlag = detailFlag;
    }

	public String getTaxpayerNo() {
		return taxpayerNo;
	}

	public void setTaxpayerNo(String taxpayerNo) {
		this.taxpayerNo = taxpayerNo;
	}
    
}

