package com.zengshi.ecp.im.dao.model;

import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineResDTO;
import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class ImStaffHotline implements Serializable {
    private Long id;

    private Long staffId;

    private String staffCode;

    private String ofStaffCode;

    private String staffClass;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private Integer serviceCount = 0;

    private Integer lineCount = 0;

    private Integer lineStatus;

    private Long hotlineId;

    private Long reqTime;

    private String custLevel;

    private String csaCode;

    private Short receptionCount;

    public Short getReceptionCount() {
        return receptionCount;
    }

    public void setReceptionCount(Short receptionCount) {
        this.receptionCount = receptionCount;
    }

    public String getCsaCode() {
        return csaCode;
    }

    public void setCsaCode(String csaCode) {
        this.csaCode = csaCode;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public Long getReqTime() {
        return reqTime;
    }

    public void setReqTime(Long reqTime) {
        this.reqTime = reqTime;
    }

    public Long getHotlineId() {
        return hotlineId;
    }

    public void setHotlineId(Long hotlineId) {
        this.hotlineId = hotlineId;
    }

    public Integer getLineCount() {
        return lineCount;
    }

    public void setLineCount(Integer lineCount) {
        this.lineCount = lineCount;
    }

    public Integer getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(Integer serviceCount) {
        this.serviceCount = serviceCount;
    }

    public Integer getLineStatus() {
        return lineStatus;
    }

    public void setLineStatus(Integer lineStatus) {
        this.lineStatus = lineStatus;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getOfStaffCode() {
        return ofStaffCode;
    }

    public void setOfStaffCode(String ofStaffCode) {
        this.ofStaffCode = ofStaffCode == null ? null : ofStaffCode.trim();
    }

    public String getStaffClass() {
        return staffClass;
    }

    public void setStaffClass(String staffClass) {
        this.staffClass = staffClass == null ? null : staffClass.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    public int hashCode() {
        return Objects.hashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ImStaffHotline)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ImStaffHotline other = (ImStaffHotline) obj;
        return this.getCsaCode().equals(other.getCsaCode());
    }

    @Override
    public String toString() {
        String jsonStr = JSON.toJSONString(this);
        return jsonStr;
    }

 /*   public ImStaffHotline() {
        setLineStatus(ImConstants.OFFLINE);
        setServiceCount(0);

    }
    public ImStaffHotline(ImStaffHotlineReqDTO dto) {
        this.setCsaCode(dto.getCsaCode());

    }
*/
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", staffId=").append(staffId);
//        sb.append(", staffCode=").append(staffCode);
//        sb.append(", ofStaffCode=").append(ofStaffCode);
//        sb.append(", staffClass=").append(staffClass);
//        sb.append(", status=").append(status);
//        sb.append(", createTime=").append(createTime);
//        sb.append(", createStaff=").append(createStaff);
//        sb.append(", updateTime=").append(updateTime);
//        sb.append(", updateStaff=").append(updateStaff);
//        sb.append("]");
//        return sb.toString();
//    }
}