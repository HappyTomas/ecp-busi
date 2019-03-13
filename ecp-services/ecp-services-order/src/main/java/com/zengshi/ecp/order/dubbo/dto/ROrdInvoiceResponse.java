package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ROrdInvoiceResponse extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -4001749875832126183L;
    /** 
     * orderId:订单编号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    /** 
     * realMoney:订单金额. 
     * @since JDK 1.6 
     */ 
    private Long   realMoney;
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
     * detailFlag:是否附加明细. 
     * @since JDK 1.6 
     */ 
    private String detailFlag;
    /** 
     * billingFlag:开票状态. 
     * @since JDK 1.6 
     */ 
    private String billingFlag;
    
    private String taxpayerNo;
    /** 
     * siteId:站点ID. 
     * @since JDK 1.6 
     */ 
    private String siteId;
    
    /** 
     * orderAmount:订单数量. 
     * @since JDK 1.7
     */ 
    private Long orderAmount;
    
    
    
    public String getSiteId() {
        return siteId;
    }
    public void setSiteId(String siteId) {
        this.siteId = siteId;
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
    public String getDetailFlag() {
        return detailFlag;
    }
    public void setDetailFlag(String detailFlag) {
        this.detailFlag = detailFlag;
    }
    public String getBillingFlag() {
        return billingFlag;
    }
    public void setBillingFlag(String billingFlag) {
        this.billingFlag = billingFlag;
    }
	public String getTaxpayerNo() {
		return taxpayerNo;
	}
	public void setTaxpayerNo(String taxpayerNo) {
		this.taxpayerNo = taxpayerNo;
	}
	public Long getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}
    
    
}

