package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015年8月7日下午5:07:27  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
public class CmsPlaceRespDTO extends BaseResponseDTO {
    
    /*--------------------------以下为model后添加的字段 start--------------------------*/

    /**
     * 状态翻译
     */
    private String statusZH;
    /**
     * 模板名称
     */
    private String templateZH;
    /**
     * 站点名称
     */
    private String siteZH;
    /**
     * 位置类型翻译
     */
    private String placeTypeZH;
    /**
     * 是否滚动翻译
     */
    private String isscrollZH;
    
    /*--------------------------以下为model后添加的字段 end------------------------*/

    private Long id;

    private Long siteId;

    private Long templateId;

    private String placeName;

    private String placeType;

    private String linkUrl;

    private Integer placeCount;

    private String placeBgcolor;

    private Long placeWidth;

    private Long placeSize;

    private Long placeHeight;

    private String isscroll;

    private Integer sortNo;

    private String status;

    private String remark;

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

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName == null ? null : placeName.trim();
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType == null ? null : placeType.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public Integer getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(Integer placeCount) {
        this.placeCount = placeCount;
    }

    public String getPlaceBgcolor() {
        return placeBgcolor;
    }

    public void setPlaceBgcolor(String placeBgcolor) {
        this.placeBgcolor = placeBgcolor == null ? null : placeBgcolor.trim();
    }

    public Long getPlaceWidth() {
        return placeWidth;
    }

    public void setPlaceWidth(Long placeWidth) {
        this.placeWidth = placeWidth;
    }

    public Long getPlaceSize() {
        return placeSize;
    }

    public void setPlaceSize(Long placeSize) {
        this.placeSize = placeSize;
    }

    public Long getPlaceHeight() {
        return placeHeight;
    }

    public void setPlaceHeight(Long placeHeight) {
        this.placeHeight = placeHeight;
    }

    public String getIsscroll() {
        return isscroll;
    }

    public void setIsscroll(String isscroll) {
        this.isscroll = isscroll == null ? null : isscroll.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", siteId=").append(siteId);
        sb.append(", templateId=").append(templateId);
        sb.append(", placeName=").append(placeName);
        sb.append(", placeType=").append(placeType);
        sb.append(", linkUrl=").append(linkUrl);
        sb.append(", placeCount=").append(placeCount);
        sb.append(", placeBgcolor=").append(placeBgcolor);
        sb.append(", placeWidth=").append(placeWidth);
        sb.append(", placeSize=").append(placeSize);
        sb.append(", placeHeight=").append(placeHeight);
        sb.append(", isscroll=").append(isscroll);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
    
    public String getStatusZH() {
        return statusZH;
    }

    public void setStatusZH(String statusZH) {
        this.statusZH = statusZH;
    }

    public String getTemplateZH() {
        return templateZH;
    }

    public void setTemplateZH(String templateZH) {
        this.templateZH = templateZH;
    }

    public String getSiteZH() {
        return siteZH;
    }

    public void setSiteZH(String siteZH) {
        this.siteZH = siteZH;
    }
    
    public String getPlaceTypeZH() {
        return placeTypeZH;
    }

    public void setPlaceTypeZH(String placeTypeZH) {
        this.placeTypeZH = placeTypeZH;
    }
    
    public String getIsscrollZH() {
        return isscrollZH;
    }

    public void setIsscrollZH(String isscrollZH) {
        this.isscrollZH = isscrollZH;
    }
    
}

