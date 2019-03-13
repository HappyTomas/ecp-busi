package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CmsAppFloorRespDTO extends BaseResponseDTO {
    
    /*--------------------------以下为model后添加的字段 start--------------------------*/
    /**
     * 站点名称
     */
    private String siteName;
    /**
     * 模板名称
     */
    private String floorTemplateName;
    /**
     * 状态翻译
     */
    private String statusZH;
    
    /**
     * app楼层数据
     */
    List<CmsAppFloorDataRespDTO> cmsAppFloorDataRespDtoList;
    /*--------------------------以下为model后添加的字段 end------------------------*/
    
    private Long id;

    private String floorName;

    private String linkUrl;

    private String status;

    private Integer sortNo;

    private String remark;

    private Long siteId;

    private Long floorTemplateId;

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

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName == null ? null : floorName.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getFloorTemplateId() {
        return floorTemplateId;
    }

    public void setFloorTemplateId(Long floorTemplateId) {
        this.floorTemplateId = floorTemplateId;
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
        sb.append(", floorName=").append(floorName);
        sb.append(", linkUrl=").append(linkUrl);
        sb.append(", status=").append(status);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", remark=").append(remark);
        sb.append(", siteId=").append(siteId);
        sb.append(", floorTemplateId=").append(floorTemplateId);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
    
    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getFloorTemplateName() {
        return floorTemplateName;
    }

    public void setFloorTemplateName(String floorTemplateName) {
        this.floorTemplateName = floorTemplateName;
    }

    public String getStatusZH() {
        return statusZH;
    }

    public void setStatusZH(String statusZH) {
        this.statusZH = statusZH;
    }

    public List<CmsAppFloorDataRespDTO> getCmsAppFloorDataRespDtoList() {
        return cmsAppFloorDataRespDtoList;
    }

    public void setCmsAppFloorDataRespDtoList(List<CmsAppFloorDataRespDTO> cmsAppFloorDataRespDtoList) {
        this.cmsAppFloorDataRespDtoList = cmsAppFloorDataRespDtoList;
    }

}
