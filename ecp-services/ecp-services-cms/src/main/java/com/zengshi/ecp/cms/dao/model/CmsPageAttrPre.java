package com.zengshi.ecp.cms.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CmsPageAttrPre implements Serializable {
    private Long id;

    private Long pageId;

    private String backgroundColor;

    private String backgroundPic;

    private String showBackFlag;

    private String status;

    private String backgroupShowType;

    private String matchingColour;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor == null ? null : backgroundColor.trim();
    }

    public String getBackgroundPic() {
        return backgroundPic;
    }

    public void setBackgroundPic(String backgroundPic) {
        this.backgroundPic = backgroundPic == null ? null : backgroundPic.trim();
    }

    public String getShowBackFlag() {
        return showBackFlag;
    }

    public void setShowBackFlag(String showBackFlag) {
        this.showBackFlag = showBackFlag == null ? null : showBackFlag.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getBackgroupShowType() {
        return backgroupShowType;
    }

    public void setBackgroupShowType(String backgroupShowType) {
        this.backgroupShowType = backgroupShowType == null ? null : backgroupShowType.trim();
    }

    public String getMatchingColour() {
        return matchingColour;
    }

    public void setMatchingColour(String matchingColour) {
        this.matchingColour = matchingColour == null ? null : matchingColour.trim();
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
        sb.append(", pageId=").append(pageId);
        sb.append(", backgroundColor=").append(backgroundColor);
        sb.append(", backgroundPic=").append(backgroundPic);
        sb.append(", showBackFlag=").append(showBackFlag);
        sb.append(", status=").append(status);
        sb.append(", backgroupShowType=").append(backgroupShowType);
        sb.append(", matchingColour=").append(matchingColour);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
