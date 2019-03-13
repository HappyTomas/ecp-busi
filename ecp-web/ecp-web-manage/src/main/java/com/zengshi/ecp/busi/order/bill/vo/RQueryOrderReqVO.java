package com.zengshi.ecp.busi.order.bill.vo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

public class RQueryOrderReqVO extends EcpBasePageReqVO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3688069269232461712L;
    
    /**
     * 开始时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message="{order.orderdate.null.error}")
    private Date begDate;

    /**
     * 截止时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message="{order.orderdate.null.error}")
    private Date endDate;
    
    //买家ID
    private Long staffId;
    
    //卖家ID
    private Long shopId;
    
    //查询的订单状态
    private String status;

    //所属站点
    private Long siteId;
    
    //所属系统
    private String sysType;
    
    //订单号
    private String orderId;
    
    //收货人
    private String contactName;
    
    //收货人电话
    private String contactPhone;
    
    /** 
     * invoiceType:是否开票为2时为不开票，其它为开票. 
     * @since JDK 1.6 
     */ 
    private String invoiceType;
    
    /** 
     * dispatchType:配送方式. 
     * @since JDK 1.6 
     */ 
    private String dispatchType;
    
    /** 
     * billingFlag:是否已开票. 
     * @since JDK 1.6 
     */ 
    private String billingFlag;
    
    /**
     * 对账类型名称
     */
    private String auditTypeName;

	public String getAuditTypeName() {
		return auditTypeName;
	}

	public void setAuditTypeName(String auditTypeName) {
		this.auditTypeName = auditTypeName;
	} 
    
    public String getBillingFlag() {
        return billingFlag;
    }

    public void setBillingFlag(String billingFlag) {
        this.billingFlag = billingFlag;
    }

    public String getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType;
    }

    private List<String> categoryCodes;

    
    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public List<String> getCategoryCodes() {
        return categoryCodes;
    }

    public void setCategoryCodes(List<String> categoryCodes) {
        this.categoryCodes = categoryCodes;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }  

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    public Date getBegDate() {
        return begDate;
    }

    public void setBegDate(Date begDate) {
        this.begDate = begDate;
    }

    public Date getEndDate() {
        return DateUtils.addDays(endDate, 1);
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}   

