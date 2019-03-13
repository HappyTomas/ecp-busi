package com.zengshi.ecp.order.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ROrdMainResponse extends BaseResponseDTO {
    private String id;

    private String orderCode;

    private Long orderAmount;

    private Long orderMoney;

    private Long realMoney;

    private Long realExpressFee;

    private Long shopId;

    private String shopName;

    private Long staffId;

    private Timestamp orderTime;

    private Timestamp payTime;

    private Timestamp deliverDate;

    private String status;

    private String orderTwoStatus;

    private String orderType;

    private String payType;

    private String payFlag;

    private String dispatchType;

    private String source;

    private String payLock;

    private String buyerMsg;

    private Timestamp createTime;

    private String createStaff;

    private Timestamp updateTime;

    private String updateStaff;
    
    private List<ROrdSubResponse> ordSubList;
    
    private String payWay;
    
    private Long siteId;

    private String sysType;

    private Long orderScore;

    private String invoiceType;

    private String contactName;

    private String contactPhone;

    private String chnlAddress;

    private String postCode;

    private String contactNumber;

    private String province;

    private String cityCode;

    private String countyCode;

    private String bringName;

    private String cardType;

    private String cardId;

    private String bringNumber;

    private String bringPhone;

    private String billingFlag;

    private String sendInvoiceType;

    private String invoiceExpressNo;

    private Long basicMoney;

    private String isInAuditFile;
    
    /** 
     * staffName:会员名. 
     * @since JDK 1.6 
     */ 
    private String staffName;

    private String staffCode;

    private Long companyId;

    private String companyName;

    private Timestamp startTime;

    private Timestamp endTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Long orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Long getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(Long realMoney) {
        this.realMoney = realMoney;
    }

    public Long getRealExpressFee() {
        return realExpressFee;
    }

    public void setRealExpressFee(Long realExpressFee) {
        this.realExpressFee = realExpressFee;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public Timestamp getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Timestamp deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getOrderTwoStatus() {
        return orderTwoStatus;
    }

    public void setOrderTwoStatus(String orderTwoStatus) {
        this.orderTwoStatus = orderTwoStatus == null ? null : orderTwoStatus.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(String payFlag) {
        this.payFlag = payFlag == null ? null : payFlag.trim();
    }

    public String getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType == null ? null : dispatchType.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getPayLock() {
        return payLock;
    }

    public void setPayLock(String payLock) {
        this.payLock = payLock == null ? null : payLock.trim();
    }

    public String getBuyerMsg() {
        return buyerMsg;
    }

    public void setBuyerMsg(String buyerMsg) {
        this.buyerMsg = buyerMsg == null ? null : buyerMsg.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff == null ? null : updateStaff.trim();
    }

    public List<ROrdSubResponse> getOrdSubList() {
        return ordSubList;
    }

    public void setOrdSubList(List<ROrdSubResponse> ordSubList) {
        this.ordSubList = ordSubList;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
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

    public Long getOrderScore() {
        return orderScore;
    }

    public void setOrderScore(Long orderScore) {
        this.orderScore = orderScore;
    }

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

    public String getChnlAddress() {
        return chnlAddress;
    }

    public void setChnlAddress(String chnlAddress) {
        this.chnlAddress = chnlAddress;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getBringName() {
        return bringName;
    }

    public void setBringName(String bringName) {
        this.bringName = bringName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getBringNumber() {
        return bringNumber;
    }

    public void setBringNumber(String bringNumber) {
        this.bringNumber = bringNumber;
    }

    public String getBringPhone() {
        return bringPhone;
    }

    public void setBringPhone(String bringPhone) {
        this.bringPhone = bringPhone;
    }

    public String getBillingFlag() {
        return billingFlag;
    }

    public void setBillingFlag(String billingFlag) {
        this.billingFlag = billingFlag;
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

    public Long getBasicMoney() {
        return basicMoney;
    }

    public void setBasicMoney(Long basicMoney) {
        this.basicMoney = basicMoney;
    }

    public String getIsInAuditFile() {
        return isInAuditFile;
    }

    public void setIsInAuditFile(String isInAuditFile) {
        this.isInAuditFile = isInAuditFile;
    }

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}