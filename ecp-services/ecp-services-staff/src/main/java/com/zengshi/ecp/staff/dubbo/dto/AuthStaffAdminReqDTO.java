package com.zengshi.ecp.staff.dubbo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class AuthStaffAdminReqDTO extends BaseInfo implements Serializable {
    //authstaff begin
    private String staffClass;

    private String staffFlag;

    private String staffPasswd;
    
    private Timestamp invalidTime;

    private Timestamp startDate;

    private Timestamp lastLoginTime;

    private BigDecimal loginSuccessCnt;

    private BigDecimal loginFailureCntToday;

    private Timestamp lastLoginFailureTime;
    //authstaff end
    
    private Long id;

    private String staffCode;

    private String aliasName;

    private String staffName;

    private String staffEmail;

    private String serialNumber;

    private String createFrom;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    //staffauth begin
    private String staffRole;
    
    private String staffRoleName;
    
    private String staffGroup;
    
    private String staffGroupName;
    
    private String staffRoleEdit;
    
    private String staffGroupEdit;
    
    private String sysCode;
    
    private String thirdSysCode;
    //staffauth end
    
    private static final long serialVersionUID = 1L;

    public String getThirdSysCode() {
        return thirdSysCode;
    }

    public void setThirdSysCode(String thirdSysCode) {
        this.thirdSysCode = thirdSysCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName == null ? null : aliasName.trim();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail == null ? null : staffEmail.trim();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getCreateFrom() {
        return createFrom;
    }

    public void setCreateFrom(String createFrom) {
        this.createFrom = createFrom == null ? null : createFrom.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", staffCode=").append(staffCode);
        sb.append(", aliasName=").append(aliasName);
        sb.append(", staffName=").append(staffName);
        sb.append(", staffEmail=").append(staffEmail);
        sb.append(", serialNumber=").append(serialNumber);
        sb.append(", createFrom=").append(createFrom);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", staffRole=").append(staffRole);
        sb.append(", staffRoleName=").append(staffRoleName);
        sb.append(", staffGroup=").append(staffGroup);
        sb.append(", staffGroupName=").append(staffGroupName);
        sb.append(", staffRoleEdit=").append(staffRoleEdit);
        sb.append(", staffGroupEdit=").append(staffGroupEdit);
        sb.append("]");
        return sb.toString();
    }

    public String getStaffClass() {
        return staffClass;
    }

    public void setStaffClass(String staffClass) {
        this.staffClass = staffClass;
    }

    public String getStaffFlag() {
        return staffFlag;
    }

    public void setStaffFlag(String staffFlag) {
        this.staffFlag = staffFlag;
    }

    public String getStaffPasswd() {
        return staffPasswd;
    }

    public void setStaffPasswd(String staffPasswd) {
        this.staffPasswd = staffPasswd;
    }

    public Timestamp getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Timestamp invalidTime) {
        this.invalidTime = invalidTime;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public BigDecimal getLoginSuccessCnt() {
        return loginSuccessCnt;
    }

    public void setLoginSuccessCnt(BigDecimal loginSuccessCnt) {
        this.loginSuccessCnt = loginSuccessCnt;
    }

    public BigDecimal getLoginFailureCntToday() {
        return loginFailureCntToday;
    }

    public void setLoginFailureCntToday(BigDecimal loginFailureCntToday) {
        this.loginFailureCntToday = loginFailureCntToday;
    }

    public Timestamp getLastLoginFailureTime() {
        return lastLoginFailureTime;
    }

    public void setLastLoginFailureTime(Timestamp lastLoginFailureTime) {
        this.lastLoginFailureTime = lastLoginFailureTime;
    }

    public String getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }

    public String getStaffRoleName() {
        return staffRoleName;
    }

    public void setStaffRoleName(String staffRoleName) {
        this.staffRoleName = staffRoleName;
    }

    public String getStaffGroup() {
        return staffGroup;
    }

    public void setStaffGroup(String staffGroup) {
        this.staffGroup = staffGroup;
    }

    public String getStaffGroupName() {
        return staffGroupName;
    }

    public void setStaffGroupName(String staffGroupName) {
        this.staffGroupName = staffGroupName;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getStaffRoleEdit() {
        return staffRoleEdit;
    }

    public void setStaffRoleEdit(String staffRoleEdit) {
        this.staffRoleEdit = staffRoleEdit;
    }

    public String getStaffGroupEdit() {
        return staffGroupEdit;
    }

    public void setStaffGroupEdit(String staffGroupEdit) {
        this.staffGroupEdit = staffGroupEdit;
    }
    
}