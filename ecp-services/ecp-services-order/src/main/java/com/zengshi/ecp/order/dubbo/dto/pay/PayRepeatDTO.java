package com.zengshi.ecp.order.dubbo.dto.pay;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class PayRepeatDTO extends BaseInfo {
    private Long id;

    private String orderId;

    private String payWay;

    private String payWayName;

    private String payTransNo;

    private Long payment;

    private String payStatus;

    private String payDesc;

    private Timestamp payTime;

    private String merchAcct;

    private String payeeName;

    private String payeeAcct;

    private Timestamp createTime;

    private Timestamp updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    public String getPayWayName() {
        return payWayName;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName == null ? null : payWayName.trim();
    }

    public String getPayTransNo() {
        return payTransNo;
    }

    public void setPayTransNo(String payTransNo) {
        this.payTransNo = payTransNo == null ? null : payTransNo.trim();
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getPayDesc() {
        return payDesc;
    }

    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc == null ? null : payDesc.trim();
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public String getMerchAcct() {
        return merchAcct;
    }

    public void setMerchAcct(String merchAcct) {
        this.merchAcct = merchAcct == null ? null : merchAcct.trim();
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    public String getPayeeAcct() {
        return payeeAcct;
    }

    public void setPayeeAcct(String payeeAcct) {
        this.payeeAcct = payeeAcct == null ? null : payeeAcct.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", payWay=").append(payWay);
        sb.append(", payWayName=").append(payWayName);
        sb.append(", payTransNo=").append(payTransNo);
        sb.append(", payment=").append(payment);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", payDesc=").append(payDesc);
        sb.append(", payTime=").append(payTime);
        sb.append(", merchAcct=").append(merchAcct);
        sb.append(", payeeName=").append(payeeName);
        sb.append(", payeeAcct=").append(payeeAcct);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}