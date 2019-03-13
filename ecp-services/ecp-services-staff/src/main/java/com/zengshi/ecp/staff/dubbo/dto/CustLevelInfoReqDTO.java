package com.zengshi.ecp.staff.dubbo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CustLevelInfoReqDTO extends BaseResponseDTO implements Serializable {
    private String custLevelCode;

    private String custLevelName;

    private BigDecimal custLevelSn;

    private Long groupId;

    private String custLevelPic;

    private String custLevelRemark;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String custCardNum;
    
    public String getCustCardNum() {
        return custCardNum;
    }

    public void setCustCardNum(String custCardNum) {
        this.custCardNum = custCardNum;
    }

    public Long getGapValue() {
        return gapValue;
    }

    public void setGapValue(Long gapValue) {
        this.gapValue = gapValue;
    }

    private Long gapValue;//与上一等级的差距值

    private static final long serialVersionUID = 1L;

    public String getCustLevelCode() {
        return custLevelCode;
    }

    public void setCustLevelCode(String custLevelCode) {
        this.custLevelCode = custLevelCode == null ? null : custLevelCode.trim();
    }

    public String getCustLevelName() {
        return custLevelName;
    }

    public void setCustLevelName(String custLevelName) {
        this.custLevelName = custLevelName == null ? null : custLevelName.trim();
    }

    public BigDecimal getCustLevelSn() {
        return custLevelSn;
    }

    public void setCustLevelSn(BigDecimal custLevelSn) {
        this.custLevelSn = custLevelSn;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getCustLevelPic() {
        return custLevelPic;
    }

    public void setCustLevelPic(String custLevelPic) {
        this.custLevelPic = custLevelPic == null ? null : custLevelPic.trim();
    }

    public String getCustLevelRemark() {
        return custLevelRemark;
    }

    public void setCustLevelRemark(String custLevelRemark) {
        this.custLevelRemark = custLevelRemark == null ? null : custLevelRemark.trim();
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
        sb.append(", custLevelCode=").append(custLevelCode);
        sb.append(", custLevelName=").append(custLevelName);
        sb.append(", custLevelSn=").append(custLevelSn);
        sb.append(", groupId=").append(groupId);
        sb.append(", custLevelPic=").append(custLevelPic);
        sb.append(", custLevelRemark=").append(custLevelRemark);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}