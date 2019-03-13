package com.zengshi.ecp.cms.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CmsLink implements Serializable {
    private Long id;

    private String linkName;

    private String linkUrl;

    private String linkType;

    private Long siteId;

    private Integer sortNo;

    private String status;

    private Timestamp createTime;

    private String createStaff;

    private Timestamp updateTime;

    private String updateStaff;

    private Long linkParent;

    private Long linkLevel;

    private Long ifLeaf;

    private String mediaUuid;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName == null ? null : linkName.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType == null ? null : linkType.trim();
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff == null ? null : updateStaff.trim();
    }

    public Long getLinkParent() {
        return linkParent;
    }

    public void setLinkParent(Long linkParent) {
        this.linkParent = linkParent;
    }

    public Long getLinkLevel() {
        return linkLevel;
    }

    public void setLinkLevel(Long linkLevel) {
        this.linkLevel = linkLevel;
    }

    public Long getIfLeaf() {
        return ifLeaf;
    }

    public void setIfLeaf(Long ifLeaf) {
        this.ifLeaf = ifLeaf;
    }

    public String getMediaUuid() {
        return mediaUuid;
    }

    public void setMediaUuid(String mediaUuid) {
        this.mediaUuid = mediaUuid == null ? null : mediaUuid.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", linkName=").append(linkName);
        sb.append(", linkUrl=").append(linkUrl);
        sb.append(", linkType=").append(linkType);
        sb.append(", siteId=").append(siteId);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", linkParent=").append(linkParent);
        sb.append(", linkLevel=").append(linkLevel);
        sb.append(", ifLeaf=").append(ifLeaf);
        sb.append(", mediaUuid=").append(mediaUuid);
        sb.append("]");
        return sb.toString();
    }
}
