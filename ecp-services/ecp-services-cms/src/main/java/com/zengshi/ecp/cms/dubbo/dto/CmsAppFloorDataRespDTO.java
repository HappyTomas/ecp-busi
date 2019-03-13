package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CmsAppFloorDataRespDTO extends BaseResponseDTO {
    
    /*--------------------------以下为model后添加的字段 start--------------------------*/
    /**
     * 状态翻译
     */
    private String statusZH;
    /**
     * app楼层名称
     */
    private String appFloorName;
    /**
     * 链接类型翻译
     */
    private String linkTypeZH;
    /**
     * 广告图片URL
     */
    private String vfsUrl;

    /*--------------------------以下为model后添加的字段 end------------------------*/
    
    private Long id;

    private Long appFloorId;

    private Long floorPlaceId;

    private String name;

    private String vfsId;

    private String linkType;

    private String linkUrl;

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

    public Long getAppFloorId() {
        return appFloorId;
    }

    public void setAppFloorId(Long appFloorId) {
        this.appFloorId = appFloorId;
    }

    public Long getFloorPlaceId() {
        return floorPlaceId;
    }

    public void setFloorPlaceId(Long floorPlaceId) {
        this.floorPlaceId = floorPlaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getVfsId() {
        return vfsId;
    }

    public void setVfsId(String vfsId) {
        this.vfsId = vfsId == null ? null : vfsId.trim();
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType == null ? null : linkType.trim();
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
        sb.append(", appFloorId=").append(appFloorId);
        sb.append(", floorPlaceId=").append(floorPlaceId);
        sb.append(", name=").append(name);
        sb.append(", vfsId=").append(vfsId);
        sb.append(", linkType=").append(linkType);
        sb.append(", linkUrl=").append(linkUrl);
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

    public String getAppFloorName() {
        return appFloorName;
    }

    public void setAppFloorName(String appFloorName) {
        this.appFloorName = appFloorName;
    }

    public String getLinkTypeZH() {
        return linkTypeZH;
    }

    public void setLinkTypeZH(String linkTypeZH) {
        this.linkTypeZH = linkTypeZH;
    }

    public String getVfsUrl() {
        return vfsUrl;
    }

    public void setVfsUrl(String vfsUrl) {
        this.vfsUrl = vfsUrl;
    }

}
