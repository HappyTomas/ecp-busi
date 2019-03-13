package com.zengshi.ecp.order.dubbo.dto.pay;

import java.sql.Timestamp;

import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.PayShopCfg;
import com.zengshi.ecp.order.dao.model.PayWay;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付请求参数包装类<br>
 * Date:2015年10月19日下午2:53:23 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version
 * @since JDK 1.6
 */
public class PayParamVO {
    private String orderId;

    private long StaffId;

    private long shopId;

    private long payment;

    private long realExpressFee;

    private String payFlag;

    private Timestamp orderTime;

    private String limtCancelTime;

    private String orderType;

    private String merchAcct;

    private PayWay payWay;
    
    private PayShopCfg payShopCfg;
    
    private OrdMain ordMain;
    
    private CustInfoResDTO staffInfo;
    
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public long getPayment() {
        return payment;
    }

    public void setPayment(long payment) {
        this.payment = payment;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public String getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(String payFlag) {
        this.payFlag = payFlag;
    }

    public long getRealExpressFee() {
        return realExpressFee;
    }

    public void setRealExpressFee(long realExpressFee) {
        this.realExpressFee = realExpressFee;
    }

    public String getLimtCancelTime() {
        return limtCancelTime;
    }

    public void setLimtCancelTime(String limtCancelTime) {
        this.limtCancelTime = limtCancelTime;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getMerchAcct() {
        return merchAcct;
    }

    public void setMerchAcct(String merchAcct) {
        this.merchAcct = merchAcct;
    }

    public PayWay getPayWay() {
        return payWay;
    }

    public void setPayWay(PayWay payWay) {
        this.payWay = payWay;
    }

    public long getStaffId() {
        return StaffId;
    }

    public void setStaffId(long staffId) {
        StaffId = staffId;
    }

    public PayShopCfg getPayShopCfg() {
        return payShopCfg;
    }

    public void setPayShopCfg(PayShopCfg payShopCfg) {
        this.payShopCfg = payShopCfg;
    }

    public OrdMain getOrdMain() {
        return ordMain;
    }

    public void setOrdMain(OrdMain ordMain) {
        this.ordMain = ordMain;
    }

    public CustInfoResDTO getStaffInfo() {
        return staffInfo;
    }

    public void setStaffInfo(CustInfoResDTO staffInfo) {
        this.staffInfo = staffInfo;
    }

}
