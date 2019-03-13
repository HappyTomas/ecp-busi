package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class AuthStaff implements Serializable {
    private Long id;

    private String staffCode;

    private String staffClass;

    private String staffFlag;

    private String staffPasswd;

    private Timestamp invalidTime;

    private Timestamp startDate;

    private Timestamp lastLoginTime;

    private BigDecimal loginSuccessCnt;

    private BigDecimal loginFailureCntToday;

    private Timestamp lastLoginFailureTime;

    private String createFrom;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private static final long serialVersionUID = 1L;

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

    public String getStaffClass() {
        return staffClass;
    }

    public void setStaffClass(String staffClass) {
        this.staffClass = staffClass == null ? null : staffClass.trim();
    }

    public String getStaffFlag() {
        return staffFlag;
    }

    public void setStaffFlag(String staffFlag) {
        this.staffFlag = staffFlag == null ? null : staffFlag.trim();
    }

    public String getStaffPasswd() {
        return staffPasswd;
    }

    public void setStaffPasswd(String staffPasswd) {
        this.staffPasswd = staffPasswd == null ? null : staffPasswd.trim();
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
        sb.append(", staffClass=").append(staffClass);
        sb.append(", staffFlag=").append(staffFlag);
        sb.append(", staffPasswd=").append(staffPasswd);
        sb.append(", invalidTime=").append(invalidTime);
        sb.append(", startDate=").append(startDate);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", loginSuccessCnt=").append(loginSuccessCnt);
        sb.append(", loginFailureCntToday=").append(loginFailureCntToday);
        sb.append(", lastLoginFailureTime=").append(lastLoginFailureTime);
        sb.append(", createFrom=").append(createFrom);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}