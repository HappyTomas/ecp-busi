package com.zengshi.ecp.order.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RQueryOrderRequest extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3688069269232461712L;
    
    //查询开始时间 
    private Timestamp begDate;
    
    //查询结束时间
    private Timestamp endDate;
    
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
     * 支付方式
     */
    private String payType;
    
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
     * count:要获取的订单数量
     * @since JDK 1.6 
     */
    private int count;
    
    /**
     * tableIndex:指定分库
     * @since JDK 1.6 
     */
    private int tableIndex;
    
    /** 
     * billingFlag:是否已开票. 
     * @since JDK 1.6 
     */ 
    private String billingFlag;
    
    /** 
     * payFlag:支付状态. 
     * @since JDK 1.6 
     */ 
    private String payFlag;
    
    /** 
     * payWay:支付通道. 
     * @since JDK 1.6 
     */ 
    private String payWay;
    
    /** 
     * payTranNo:商户订单号. 
     * @since JDK 1.6 
     */
    private String payTranNo;

    /**
     * 订单状态列表
     */
    private List<String> statusList;

    /**
     * 导出文件名称
     */
    private String exportType;

    /**
     * 导出文件关联KEY
     */
    private Long exportKey;
     
    public String getPayTranNo() {
        return payTranNo;
    }

    public void setPayTranNo(String payTranNo) {
        this.payTranNo = payTranNo;
    }
    
    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(String payFlag) {
        this.payFlag = payFlag;
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTableIndex() {
        return tableIndex;
    }

    public void setTableIndex(int tableIndex) {
        this.tableIndex = tableIndex;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getExportType() {
        return exportType;
    }

    public void setExportType(String exportType) {
        this.exportType = exportType;
    }

    public Long getExportKey() {
        return exportKey;
    }

    public void setExportKey(Long exportKey) {
        this.exportKey = exportKey;
    }
}

