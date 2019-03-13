package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ImOrdBelong implements Serializable {
    private String ordId;

    private Long realMoney;

    private String contactName;

    private String mobileNumber;

    private Long staffId;

    private String staffCode;

    private Timestamp ordTime;

    private Timestamp payTime;

    private String belongSerCode;

    private String belongSerName;

    private static final long serialVersionUID = 1L;

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId == null ? null : ordId.trim();
    }

    public Long getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(Long realMoney) {
        this.realMoney = realMoney;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber == null ? null : mobileNumber.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public Timestamp getOrdTime() {
        return ordTime;
    }

    public void setOrdTime(Timestamp ordTime) {
        this.ordTime = ordTime;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public String getBelongSerCode() {
        return belongSerCode;
    }

    public void setBelongSerCode(String belongSerCode) {
        this.belongSerCode = belongSerCode == null ? null : belongSerCode.trim();
    }

    public String getBelongSerName() {
        return belongSerName;
    }

    public void setBelongSerName(String belongSerName) {
        this.belongSerName = belongSerName == null ? null : belongSerName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ordId=").append(ordId);
        sb.append(", realMoney=").append(realMoney);
        sb.append(", contactName=").append(contactName);
        sb.append(", mobileNumber=").append(mobileNumber);
        sb.append(", staffId=").append(staffId);
        sb.append(", staffCode=").append(staffCode);
        sb.append(", ordTime=").append(ordTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", belongSerCode=").append(belongSerCode);
        sb.append(", belongSerName=").append(belongSerName);
        sb.append("]");
        return sb.toString();
    }
}