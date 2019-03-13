package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrdMain implements Serializable {
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

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private Long siteId;

    private String sysType;

    private String payWay;

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

    private String staffCode;

    private String staffName;

    private Long companyId;

    private String companyName;

    private Timestamp startTime;

    private Timestamp endTime;

    private String payTranNo;

    private String sellerMsg;

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

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
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
        this.sysType = sysType == null ? null : sysType.trim();
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
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
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getChnlAddress() {
        return chnlAddress;
    }

    public void setChnlAddress(String chnlAddress) {
        this.chnlAddress = chnlAddress == null ? null : chnlAddress.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber == null ? null : contactNumber.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode == null ? null : countyCode.trim();
    }

    public String getBringName() {
        return bringName;
    }

    public void setBringName(String bringName) {
        this.bringName = bringName == null ? null : bringName.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getBringNumber() {
        return bringNumber;
    }

    public void setBringNumber(String bringNumber) {
        this.bringNumber = bringNumber == null ? null : bringNumber.trim();
    }

    public String getBringPhone() {
        return bringPhone;
    }

    public void setBringPhone(String bringPhone) {
        this.bringPhone = bringPhone == null ? null : bringPhone.trim();
    }

    public String getBillingFlag() {
        return billingFlag;
    }

    public void setBillingFlag(String billingFlag) {
        this.billingFlag = billingFlag == null ? null : billingFlag.trim();
    }

    public String getSendInvoiceType() {
        return sendInvoiceType;
    }

    public void setSendInvoiceType(String sendInvoiceType) {
        this.sendInvoiceType = sendInvoiceType == null ? null : sendInvoiceType.trim();
    }

    public String getInvoiceExpressNo() {
        return invoiceExpressNo;
    }

    public void setInvoiceExpressNo(String invoiceExpressNo) {
        this.invoiceExpressNo = invoiceExpressNo == null ? null : invoiceExpressNo.trim();
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
        this.isInAuditFile = isInAuditFile == null ? null : isInAuditFile.trim();
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
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
        this.companyName = companyName == null ? null : companyName.trim();
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

    public String getPayTranNo() {
        return payTranNo;
    }

    public void setPayTranNo(String payTranNo) {
        this.payTranNo = payTranNo == null ? null : payTranNo.trim();
    }

    public String getSellerMsg() {
        return sellerMsg;
    }

    public void setSellerMsg(String sellerMsg) {
        this.sellerMsg = sellerMsg == null ? null : sellerMsg.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", orderMoney=").append(orderMoney);
        sb.append(", realMoney=").append(realMoney);
        sb.append(", realExpressFee=").append(realExpressFee);
        sb.append(", shopId=").append(shopId);
        sb.append(", shopName=").append(shopName);
        sb.append(", staffId=").append(staffId);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", deliverDate=").append(deliverDate);
        sb.append(", status=").append(status);
        sb.append(", orderTwoStatus=").append(orderTwoStatus);
        sb.append(", orderType=").append(orderType);
        sb.append(", payType=").append(payType);
        sb.append(", payFlag=").append(payFlag);
        sb.append(", dispatchType=").append(dispatchType);
        sb.append(", source=").append(source);
        sb.append(", payLock=").append(payLock);
        sb.append(", buyerMsg=").append(buyerMsg);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", siteId=").append(siteId);
        sb.append(", sysType=").append(sysType);
        sb.append(", payWay=").append(payWay);
        sb.append(", orderScore=").append(orderScore);
        sb.append(", invoiceType=").append(invoiceType);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", chnlAddress=").append(chnlAddress);
        sb.append(", postCode=").append(postCode);
        sb.append(", contactNumber=").append(contactNumber);
        sb.append(", province=").append(province);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", countyCode=").append(countyCode);
        sb.append(", bringName=").append(bringName);
        sb.append(", cardType=").append(cardType);
        sb.append(", cardId=").append(cardId);
        sb.append(", bringNumber=").append(bringNumber);
        sb.append(", bringPhone=").append(bringPhone);
        sb.append(", billingFlag=").append(billingFlag);
        sb.append(", sendInvoiceType=").append(sendInvoiceType);
        sb.append(", invoiceExpressNo=").append(invoiceExpressNo);
        sb.append(", basicMoney=").append(basicMoney);
        sb.append(", isInAuditFile=").append(isInAuditFile);
        sb.append(", staffCode=").append(staffCode);
        sb.append(", staffName=").append(staffName);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", payTranNo=").append(payTranNo);
        sb.append(", sellerMsg=").append(sellerMsg);
        sb.append("]");
        return sb.toString();
    }
}