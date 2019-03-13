package com.zengshi.ecp.busi.cms.hotSearch.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月21日下午5:49:47  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxm9
 * @version  
 * @since JDK 1.6 
 */  
public class CmsHotSearchVO extends EcpBasePageReqVO implements Serializable{
    
	/*--------------------------以下为model后添加的字段 start--------------------------*/

    /**
     * 站点名称
     */
    private String siteName;
    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 内容位置名称
     */
    private String placeName;
    /**
     * 状态翻译
     */
    private String statusZH;
    
    /*--------------------------以下为model后添加的字段 end------------------------*/

    private Long id;

    @NotNull(message="{cms.hotSearch.hotSearchName.null.error}")
    @Length(max=10, message="{cms.hotSearch.hotSearchName.length.error}")
    private String hotSearchName;

//    @NotNull(message="{cms.hotSearch.linkUrl.null.error}")
//    @Length(min=6, max=256, message="{cms.hotSearch.linkUrl.length.error}")
//    private String linkUrl;

    private String status;

    private Integer sortNo;

    private Long siteId;

//    private Long templateId;
//
//    @NotNull(message="{cms.hotSearch.placeId.null.error}")
//    private Long placeId;

    private Long createStaff;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    private Long updateStaff;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotSearchName() {
        return hotSearchName;
    }

    public void setHotSearchName(String hotSearchName) {
        this.hotSearchName = hotSearchName == null ? null : hotSearchName.trim();
    }

//    public String getLinkUrl() {
//        return linkUrl;
//    }
//
//    public void setLinkUrl(String linkUrl) {
//        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

//    public Long getTemplateId() {
//        return templateId;
//    }
//
//    public void setTemplateId(Long templateId) {
//        this.templateId = templateId;
//    }
//
//    public Long getPlaceId() {
//        return placeId;
//    }
//
//    public void setPlaceId(Long placeId) {
//        this.placeId = placeId;
//    }

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
    
    public String getStatusZH() {
        return statusZH;
    }

    public void setStatusZH(String statusZH) {
        this.statusZH = statusZH;
    }
    
    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName == null ? null : placeName.trim();
    }
}

