package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CmsPicHotPubReqDTO extends BaseInfo{
    
    /*--------------------------以下为model后添加的字段 start----------------------*/ 
    /**
     * 状态SET，用于查询
     */
    private Set<String> statusSet = new HashSet<String>();
    /*--------------------------以下为model后添加的字段 end----------------------*/
    
    private Long id;

    private Long hotId;

    private String picId;

    private Long pageId;

    private Long itemPropId;

    private String relativeCoord;

    private String urlInfo;

    private String showTitle;

    private String status;

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

    public Long getHotId() {
        return hotId;
    }

    public void setHotId(Long hotId) {
        this.hotId = hotId;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId == null ? null : picId.trim();
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Long getItemPropId() {
        return itemPropId;
    }

    public void setItemPropId(Long itemPropId) {
        this.itemPropId = itemPropId;
    }

    public String getRelativeCoord() {
        return relativeCoord;
    }

    public void setRelativeCoord(String relativeCoord) {
        this.relativeCoord = relativeCoord == null ? null : relativeCoord.trim();
    }

    public String getUrlInfo() {
        return urlInfo;
    }

    public void setUrlInfo(String urlInfo) {
        this.urlInfo = urlInfo == null ? null : urlInfo.trim();
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle == null ? null : showTitle.trim();
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
        sb.append(", hotId=").append(hotId);
        sb.append(", picId=").append(picId);
        sb.append(", pageId=").append(pageId);
        sb.append(", itemPropId=").append(itemPropId);
        sb.append(", relativeCoord=").append(relativeCoord);
        sb.append(", urlInfo=").append(urlInfo);
        sb.append(", showTitle=").append(showTitle);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
    
    public Set<String> getStatusSet() {
        return statusSet;
    }

    public void setStatusSet(Set<String> statusSet) {
        this.statusSet = statusSet;
    }
    
}
