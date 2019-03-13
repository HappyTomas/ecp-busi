package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CmsGdsCategoryReqDTO extends BaseInfo {

    private String id;

    private String catgName;

    private Short catgLevel;

    private Integer sortNo;

    private String catgParent;

    private String catgCode;

    private String showCatgId;

    private String catgUrl;

    private String ifLeaf;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String mediaUuid;

    private Long siteId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCatgName() {
        return catgName;
    }

    public void setCatgName(String catgName) {
        this.catgName = catgName == null ? null : catgName.trim();
    }

    public Short getCatgLevel() {
        return catgLevel;
    }

    public void setCatgLevel(Short catgLevel) {
        this.catgLevel = catgLevel;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getCatgParent() {
        return catgParent;
    }

    public void setCatgParent(String catgParent) {
        this.catgParent = catgParent == null ? null : catgParent.trim();
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public String getShowCatgId() {
        return showCatgId;
    }

    public void setShowCatgId(String showCatgId) {
        this.showCatgId = showCatgId == null ? null : showCatgId.trim();
    }

    public String getCatgUrl() {
        return catgUrl;
    }

    public void setCatgUrl(String catgUrl) {
        this.catgUrl = catgUrl == null ? null : catgUrl.trim();
    }

    public String getIfLeaf() {
        return ifLeaf;
    }

    public void setIfLeaf(String ifLeaf) {
        this.ifLeaf = ifLeaf == null ? null : ifLeaf.trim();
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

    public String getMediaUuid() {
        return mediaUuid;
    }

    public void setMediaUuid(String mediaUuid) {
        this.mediaUuid = mediaUuid == null ? null : mediaUuid.trim();
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", catgName=").append(catgName);
        sb.append(", catgLevel=").append(catgLevel);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", catgParent=").append(catgParent);
        sb.append(", catgCode=").append(catgCode);
        sb.append(", showCatgId=").append(showCatgId);
        sb.append(", catgUrl=").append(catgUrl);
        sb.append(", ifLeaf=").append(ifLeaf);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", mediaUuid=").append(mediaUuid);
        sb.append(", siteId=").append(siteId);
        sb.append("]");
        return sb.toString();
    }

}
