package com.zengshi.ecp.staff.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ScoreSourceReqDTO extends BaseInfo  {
    private Long id;

    private String sourceType;

    private String status;

    private Long staffId;

    private Long score;

    private String subOrderId;

    private String orderId;

    private Timestamp inureTime;

    private Long totalScore;

    private Long freezeScore;

    private Long usedScore;

    private Long balanceScore;

    private String remark;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private String optType;
    
    private Timestamp selDateFrom;
    
    private Timestamp selDateEnd;
    
    private Long siteId;
    
    private String backFlag;
    
    private String isbnCode;
    
    private String bookCode;
    
    private static final long serialVersionUID = 1L;
    
    
    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Timestamp getSelDateFrom() {
        return selDateFrom;
    }

    public void setSelDateFrom(Timestamp selDateFrom) {
        this.selDateFrom = selDateFrom;
    }

    public Timestamp getSelDateEnd() {
        return selDateEnd;
    }

    public void setSelDateEnd(Timestamp selDateEnd) {
        this.selDateEnd = selDateEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(String subOrderId) {
        this.subOrderId = subOrderId == null ? null : subOrderId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Timestamp getInureTime() {
        return inureTime;
    }

    public void setInureTime(Timestamp inureTime) {
        this.inureTime = inureTime;
    }

    public Long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }

    public Long getFreezeScore() {
        return freezeScore;
    }

    public void setFreezeScore(Long freezeScore) {
        this.freezeScore = freezeScore;
    }

    public Long getUsedScore() {
        return usedScore;
    }

    public void setUsedScore(Long usedScore) {
        this.usedScore = usedScore;
    }

    public Long getBalanceScore() {
        return balanceScore;
    }

    public void setBalanceScore(Long balanceScore) {
        this.balanceScore = balanceScore;
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

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }
    
    public String getBackFlag() {
        return backFlag;
    }

    public void setBackFlag(String backFlag) {
        this.backFlag = backFlag;
    }

    public String getIsbnCode() {
        return isbnCode;
    }

    public void setIsbnCode(String isbnCode) {
        this.isbnCode = isbnCode;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sourceType=").append(sourceType);
        sb.append(", status=").append(status);
        sb.append(", staffId=").append(staffId);
        sb.append(", score=").append(score);
        sb.append(", subOrderId=").append(subOrderId);
        sb.append(", orderId=").append(orderId);
        sb.append(", inureTime=").append(inureTime);
        sb.append(", totalScore=").append(totalScore);
        sb.append(", freezeScore=").append(freezeScore);
        sb.append(", usedScore=").append(usedScore);
        sb.append(", balanceScore=").append(balanceScore);
        sb.append(", remark=").append(remark);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", optType=").append(optType);
        sb.append(", selDateFrom=").append(selDateFrom);
        sb.append(", selDateEnd=").append(selDateEnd);
        sb.append(", siteId=").append(siteId);
        sb.append(", backFlag=").append(backFlag);
        sb.append(", isbnCode=").append(isbnCode);
        sb.append(", bookCode=").append(bookCode);
        sb.append("]");
        return sb.toString();
    }
}