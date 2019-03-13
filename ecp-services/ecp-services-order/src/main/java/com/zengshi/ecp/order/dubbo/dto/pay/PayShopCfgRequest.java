package com.zengshi.ecp.order.dubbo.dto.pay;

import java.sql.Timestamp;

import net.sf.json.JSONObject;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class PayShopCfgRequest extends BaseInfo {
    
    private Long shopId;

    private String payWay;
    
    private String payWayName;

    private String mercCode;

    private String branchId;

    private String cisNo;

    private String shopAccount;

    private String payeeAccount;

    private String payeeName;

    private String posId;

    private String cerName;

    private String keyName;

    private String cerPassword;

    private String useFlag;

    private String createStaff;

    private Timestamp createTime;

    private String updateStaff;

    private Timestamp updateTime;

    private static final long serialVersionUID = 1L;

    public String getPayWayName() {
        return payWayName;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName == null ? null : payWayName.trim();
    }

    public String getMercCode() {
        return mercCode;
    }

    public void setMercCode(String mercCode) {
        this.mercCode = mercCode == null ? null : mercCode.trim();
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId == null ? null : branchId.trim();
    }

    public String getCisNo() {
        return cisNo;
    }

    public void setCisNo(String cisNo) {
        this.cisNo = cisNo == null ? null : cisNo.trim();
    }

    public String getShopAccount() {
        return shopAccount;
    }

    public void setShopAccount(String shopAccount) {
        this.shopAccount = shopAccount == null ? null : shopAccount.trim();
    }

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount == null ? null : payeeAccount.trim();
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId == null ? null : posId.trim();
    }

    public String getCerName() {
        return cerName;
    }

    public void setCerName(String cerName) {
        this.cerName = cerName == null ? null : cerName.trim();
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName == null ? null : keyName.trim();
    }

    public String getCerPassword() {
        return cerPassword;
    }

    public void setCerPassword(String cerPassword) {
        this.cerPassword = cerPassword == null ? null : cerPassword.trim();
    }

    public String getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(String useFlag) {
        this.useFlag = useFlag == null ? null : useFlag.trim();
    }

    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff == null ? null : updateStaff.trim();
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    @Override
    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
}