package com.zengshi.ecp.order.dubbo.dto;

import java.util.Date;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月18日下午3:11:00  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public class RShopOrderResponse extends BaseResponseDTO{
    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3527559046763453703L;
    //订单编号
    private String orderId;
    //订单类型
    private String orderType;
    //下单日期
    private Date orderDate;
    //下单时间 
    private Date orderTime;
    //支付时间 
    private Date payTime;
    //店铺名称
    private String shopName;
    //买家id
    private Long staffId;
    
    //订单状态
    private String status;
    
    //订单二级状态
    private String orderTwoStatus;
    
    //买家昵称
    private String staffName;
    
    //实付金额
    private Long realMoney;
    //订单总积分
    private Long orderScore;
    //剩余发货数量
    private Long remainAmounts;
    //订购数量
    private Long orderAmounts;
    //已发货数量
    private Long deliverAmounts;
    //买家留言
    private String buyerMsg;
    //联系人
    private String contactName;
    //固定电话
    private String contactNumber;
    //手机号码
    private String contactPhone;
    //邮编
    private String postCode;
    //地址
    private String chnlAddress;
    //提货方式
    private String dispatchType;
    //自提人姓名
    private String bringName;
    //自提人证件类型
    private String cardType;
    //自提人证件号码
    private String cardId;
    //自提人固定电话
    private String bringTel;
    //自提人手机号
    private String bringPhone;
    //支付方式
    private String payType;
    
    //支付状态
    private String payFlag;
    
    //站点
    private Long siteId;
    
    //发票类型
    private String invoiceType;
    
    //发货时间
    private Date deliverDate;
    
    //支付通道
    private String payWay;
    
    //订单普通发票相关字段
    private SOrderDetailsComm sOrderDetailsComm;
    //订单增值税发票相关字段 
    private SOrderDetailsTax sOrderDetailsTax;

    //合并支付订单号
    private String joinOrderid;

    public String getJoinOrderid() {
        return joinOrderid;
    }

    public void setJoinOrderid(String joinOrderid) {
        this.joinOrderid = joinOrderid;
    }

    public String getOrderTwoStatus() {
        return orderTwoStatus;
    }
    public void setOrderTwoStatus(String orderTwoStatus) {
        this.orderTwoStatus = orderTwoStatus;
    }
    public String getPayFlag() {
        return payFlag;
    }
    public void setPayFlag(String payFlag) {
        this.payFlag = payFlag;
    }
    public String getPayWay() {
        return payWay;
    }
    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }
    public Date getDeliverDate() {
        return deliverDate;
    }
    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }
    public String getInvoiceType() {
        return invoiceType;
    }
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }
    public SOrderDetailsComm getsOrderDetailsComm() {
        return sOrderDetailsComm;
    }
    public void setsOrderDetailsComm(SOrderDetailsComm sOrderDetailsComm) {
        this.sOrderDetailsComm = sOrderDetailsComm;
    }
    public SOrderDetailsTax getsOrderDetailsTax() {
        return sOrderDetailsTax;
    }
    public void setsOrderDetailsTax(SOrderDetailsTax sOrderDetailsTax) {
        this.sOrderDetailsTax = sOrderDetailsTax;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStaffName() {
        return staffName;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    public Long getSiteId() {
        return siteId;
    }
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
    public Long getOrderScore() {
        return orderScore;
    }
    public void setOrderScore(Long orderScore) {
        this.orderScore = orderScore;
    }
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getPostCode() {
        return postCode;
    }
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    public Long getStaffId() {
        return staffId;
    }
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
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
    public String getBringTel() {
        return bringTel;
    }
    public void setBringTel(String bringTel) {
        this.bringTel = bringTel;
    }
    public String getBringPhone() {
        return bringPhone;
    }
    public void setBringPhone(String bringPhone) {
        this.bringPhone = bringPhone;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public Date getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
    public Date getPayTime() {
        return payTime;
    }
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public Long getRealMoney() {
        return realMoney;
    }
    public void setRealMoney(Long realMoney) {
        this.realMoney = realMoney;
    }
    public Long getRemainAmounts() {
        return remainAmounts;
    }
    public void setRemainAmounts(Long remainAmounts) {
        this.remainAmounts = remainAmounts;
    }
    public Long getOrderAmounts() {
        return orderAmounts;
    }
    public void setOrderAmounts(Long orderAmounts) {
        this.orderAmounts = orderAmounts;
    }
    public Long getDeliverAmounts() {
        return deliverAmounts;
    }
    public void setDeliverAmounts(Long deliverAmounts) {
        this.deliverAmounts = deliverAmounts;
    }
    public String getBuyerMsg() {
        return buyerMsg;
    }
    public void setBuyerMsg(String buyerMsg) {
        this.buyerMsg = buyerMsg;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public String getContactPhone() {
        return contactPhone;
    }
    public String getPayType() {
        return payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
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
    public String getDispatchType() {
        return dispatchType;
    }
    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType;
    }
    

}

