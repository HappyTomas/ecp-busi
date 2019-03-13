package com.zengshi.ecp.staff.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ScoreExchangeResDTO extends BaseResponseDTO {
    private Long id;

    private String exchangeMode;

    private Long staffId;

    private String orderId;

    private String subOrderId;

    private Long scoreUsing;

    private String remark;

    private Long createStaff;

    private Timestamp createTime;
    
    private String optType;
    
    private String scoreType;
    
    private String scoreTypeName;
    
    private Long siteId;
    
    private String siteUrl;
    
    private String staffCode;
    
    private String backFlag;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExchangeMode() {
        return exchangeMode;
    }

    public void setExchangeMode(String exchangeMode) {
        this.exchangeMode = exchangeMode == null ? null : exchangeMode.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(String subOrderId) {
        this.subOrderId = subOrderId == null ? null : subOrderId.trim();
    }

    public Long getScoreUsing() {
        return scoreUsing;
    }

    public void setScoreUsing(Long scoreUsing) {
        this.scoreUsing = scoreUsing;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public String getScoreTypeName() {
        return scoreTypeName;
    }

    public void setScoreTypeName(String scoreTypeName) {
        this.scoreTypeName = scoreTypeName;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }
    

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
    

    public String getBackFlag() {
        return backFlag;
    }

    public void setBackFlag(String backFlag) {
        this.backFlag = backFlag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", exchangeMode=").append(exchangeMode);
        sb.append(", staffId=").append(staffId);
        sb.append(", orderId=").append(orderId);
        sb.append(", subOrderId=").append(subOrderId);
        sb.append(", scoreUsing=").append(scoreUsing);
        sb.append(", remark=").append(remark);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", optType=").append(optType);
        sb.append(", scoreType=").append(scoreType);
        sb.append(", scoreTypeName=").append(scoreTypeName);
        sb.append(", siteId=").append(siteId);
        sb.append(", siteUrl=").append(siteUrl);
        sb.append(", staffCode=").append(staffCode);
        sb.append(", backFlag=").append(backFlag);
        sb.append("]");
        return sb.toString();
    }
}