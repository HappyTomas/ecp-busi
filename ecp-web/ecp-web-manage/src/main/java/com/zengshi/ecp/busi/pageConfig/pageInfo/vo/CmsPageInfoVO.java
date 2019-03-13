package com.zengshi.ecp.busi.pageConfig.pageInfo.vo;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

public class CmsPageInfoVO extends EcpBasePageReqVO implements Serializable{
    private Long id;

    @NotNull(message="{cms.pageInfo.siteId.null.error}")
    private Long siteId;

    @NotNull(message="{cms.pageInfo.pageTypeId.null.error}")
    private Long pageTypeId;

    @NotNull(message="{cms.pageInfo.pageName.null.error}")
    private String pageName;

    private String siteUrl;

    private Long shopId;

    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date timingTime;

    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date validTime;

    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date invalidTime;

    private Long templateId;

    @NotNull(message="{cms.pageInfo.platformType.null.error}")
    private String platformType;

    private String status;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private String isUseTemplate;

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

    public Long getPageTypeId() {
        return pageTypeId;
    }

    public void setPageTypeId(Long pageTypeId) {
        this.pageTypeId = pageTypeId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName == null ? null : pageName.trim();
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl == null ? null : siteUrl.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Date getTimingTime() {
        return timingTime;
    }

    public void setTimingTime(Date timingTime) {
        this.timingTime = timingTime;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType == null ? null : platformType.trim();
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

    public String getIsUseTemplate() {
        return isUseTemplate;
    }

    public void setIsUseTemplate(String isUseTemplate) {
        this.isUseTemplate = isUseTemplate == null ? null : isUseTemplate.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", siteId=").append(siteId);
        sb.append(", pageTypeId=").append(pageTypeId);
        sb.append(", pageName=").append(pageName);
        sb.append(", siteUrl=").append(siteUrl);
        sb.append(", shopId=").append(shopId);
        sb.append(", timingTime=").append(timingTime);
        sb.append(", validTime=").append(validTime);
        sb.append(", invalidTime=").append(invalidTime);
        sb.append(", templateId=").append(templateId);
        sb.append(", platformType=").append(platformType);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isUseTemplate=").append(isUseTemplate);
        sb.append("]");
        return sb.toString();
    }
    
}