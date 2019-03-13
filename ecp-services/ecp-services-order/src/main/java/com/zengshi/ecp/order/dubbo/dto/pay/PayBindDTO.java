package com.zengshi.ecp.order.dubbo.dto.pay;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class PayBindDTO extends BaseInfo {
    private Long id;

    private String payWay;

    private Long staffId;

    private String bindBankAcct;

    private String bindStatus;

    private String bindCustName;

    private String bindCustPhone;

    private Timestamp createTime;

    private Timestamp updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getBindBankAcct() {
        return bindBankAcct;
    }

    public void setBindBankAcct(String bindBankAcct) {
        this.bindBankAcct = bindBankAcct == null ? null : bindBankAcct.trim();
    }

    public String getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(String bindStatus) {
        this.bindStatus = bindStatus == null ? null : bindStatus.trim();
    }

    public String getBindCustName() {
        return bindCustName;
    }

    public void setBindCustName(String bindCustName) {
        this.bindCustName = bindCustName == null ? null : bindCustName.trim();
    }

    public String getBindCustPhone() {
        return bindCustPhone;
    }

    public void setBindCustPhone(String bindCustPhone) {
        this.bindCustPhone = bindCustPhone == null ? null : bindCustPhone.trim();
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
        sb.append(", payWay=").append(payWay);
        sb.append(", staffId=").append(staffId);
        sb.append(", bindBankAcct=").append(bindBankAcct);
        sb.append(", bindStatus=").append(bindStatus);
        sb.append(", bindCustName=").append(bindCustName);
        sb.append(", bindCustPhone=").append(bindCustPhone);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}