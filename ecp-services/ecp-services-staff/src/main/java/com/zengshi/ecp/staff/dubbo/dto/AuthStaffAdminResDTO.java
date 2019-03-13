package com.zengshi.ecp.staff.dubbo.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class AuthStaffAdminResDTO extends BaseResponseDTO implements Serializable {
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
    
    private String staffFlag;

    private String staffPasswd;
    
    private String staffRole;
    
    private String staffRoleName;
    
    private String staffGroup;
    
    private String staffGroupName;
    
    private String thirdSysCode;
    
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
        sb.append(", staffFlag=").append(staffFlag);
        sb.append(", staffPasswd=").append(staffPasswd);
        sb.append(", staffRole=").append(staffRole);
        sb.append(", staffRoleName=").append(staffRoleName);
        sb.append(", staffGroup=").append(staffGroup);
        sb.append(", staffGroupName=").append(staffGroupName);
        sb.append(", thirdSysCode=").append(thirdSysCode);
        sb.append("]");
        return sb.toString();
    }
}