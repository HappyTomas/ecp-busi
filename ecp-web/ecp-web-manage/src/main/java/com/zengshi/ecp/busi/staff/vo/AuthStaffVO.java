package com.zengshi.ecp.busi.staff.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class AuthStaffVO extends EcpBasePageReqVO implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

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
        this.staffCode = staffCode;
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

    public String getCreateFrom() {
        return createFrom;
    }

    public void setCreateFrom(String createFrom) {
        this.createFrom = createFrom;
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
}

