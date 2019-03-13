package com.zengshi.ecp.search.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class SecHotKeywordReqDTO extends BaseInfo{

    /** 
     * serialVersionUID:
     */ 
    private static final long serialVersionUID = 3219240832339375490L;
    
    private Long id;

    private Short hotkeywordRank;

    private Long hotkeywordScore;

    private String keyword;

    private String status;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getHotkeywordRank() {
        return hotkeywordRank;
    }

    public void setHotkeywordRank(Short hotkeywordRank) {
        this.hotkeywordRank = hotkeywordRank;
    }

    public Long getHotkeywordScore() {
        return hotkeywordScore;
    }

    public void setHotkeywordScore(Long hotkeywordScore) {
        this.hotkeywordScore = hotkeywordScore;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", hotkeywordRank=").append(hotkeywordRank);
        sb.append(", hotkeywordScore=").append(hotkeywordScore);
        sb.append(", keyword=").append(keyword);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

}

