package com.zengshi.ecp.busi.order.bill.vo;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class ROrdInvoiceReqVO extends EcpBasePageReqVO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -142273862666940248L;

    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    /** 
     * sendInvoiceType:寄票方式. 
     * @since JDK 1.6 
     */ 
    private String sendInvoiceType;
    /** 
     * invoiceExpressNo:寄票快递单号. 
     * @since JDK 1.6 
     */ 
    private String invoiceExpressNo;
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getSendInvoiceType() {
        return sendInvoiceType;
    }
    public void setSendInvoiceType(String sendInvoiceType) {
        this.sendInvoiceType = sendInvoiceType;
    }
    public String getInvoiceExpressNo() {
        return invoiceExpressNo;
    }
    public void setInvoiceExpressNo(String invoiceExpressNo) {
        this.invoiceExpressNo = invoiceExpressNo;
    }

}   

