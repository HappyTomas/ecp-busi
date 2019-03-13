package com.zengshi.ecp.staff.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ScoreDetailSelResDTO extends BaseResponseDTO {
    private Long id;

    private Long staffId;
    
    private String staffCode;

    private String orderId;
    
    private String scoreTypeOrig;
    
    private String scoreTypeOrigName;

    private String scoreType;

    private String scoreTypeName;
    
    private Timestamp createTime;

    private Long consumeScore;

    private Long modifyAddScore;

    private Long exchangeScore;

    private Long modifyReduceScore;
    
    private String remark;
    
    private Long siteId;
    
    private String siteUrl;

    private static final long serialVersionUID = 1L;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType == null ? null : scoreType.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getConsumeScore() {
        return consumeScore;
    }

    public void setConsumeScore(Long consumeScore) {
        this.consumeScore = consumeScore;
    }

    public Long getModifyAddScore() {
        return modifyAddScore;
    }

    public void setModifyAddScore(Long modifyAddScore) {
        this.modifyAddScore = modifyAddScore;
    }

    public Long getExchangeScore() {
        return exchangeScore;
    }

    public void setExchangeScore(Long exchangeScore) {
        this.exchangeScore = exchangeScore;
    }

    public Long getModifyReduceScore() {
        return modifyReduceScore;
    }

    public void setModifyReduceScore(Long modifyReduceScore) {
        this.modifyReduceScore = modifyReduceScore;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getScoreTypeName() {
        return scoreTypeName;
    }

    public void setScoreTypeName(String scoreTypeName) {
        this.scoreTypeName = scoreTypeName;
    }

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getScoreTypeOrig() {
        return scoreTypeOrig;
    }

    public void setScoreTypeOrig(String scoreTypeOrig) {
        this.scoreTypeOrig = scoreTypeOrig;
    }

    public String getScoreTypeOrigName() {
        return scoreTypeOrigName;
    }

    public void setScoreTypeOrigName(String scoreTypeOrigName) {
        this.scoreTypeOrigName = scoreTypeOrigName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", staffId=").append(staffId);
        sb.append(", staffCode=").append(staffCode);
        sb.append(", orderId=").append(orderId);
        sb.append(", scoreType=").append(scoreType);
        sb.append(", scoreTypeOrig=").append(scoreTypeOrig);
        sb.append(", scoreTypeOrigName=").append(scoreTypeOrigName);
        sb.append(", scoreTypeName=").append(scoreTypeName);
        sb.append(", createTime=").append(createTime);
        sb.append(", consumeScore=").append(consumeScore);
        sb.append(", modifyAddScore=").append(modifyAddScore);
        sb.append(", exchangeScore=").append(exchangeScore);
        sb.append(", modifyReduceScore=").append(modifyReduceScore);
        sb.append(", remark=").append(remark);
        sb.append(", siteId=").append(siteId);
        sb.append(", siteUrl=").append(siteUrl);
        sb.append("]");
        return sb.toString();
    }
}