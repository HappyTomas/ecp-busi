package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CmsFloorPlaceReqDTO extends BaseInfo{
    private Long id;

    private Long floorTemplateId;

    private Long placeWidth;

    private Long placeHeight;

    private Long placeSize;

    private Integer sortNo;

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

    public Long getFloorTemplateId() {
        return floorTemplateId;
    }

    public void setFloorTemplateId(Long floorTemplateId) {
        this.floorTemplateId = floorTemplateId;
    }

    public Long getPlaceWidth() {
        return placeWidth;
    }

    public void setPlaceWidth(Long placeWidth) {
        this.placeWidth = placeWidth;
    }

    public Long getPlaceHeight() {
        return placeHeight;
    }

    public void setPlaceHeight(Long placeHeight) {
        this.placeHeight = placeHeight;
    }

    public Long getPlaceSize() {
        return placeSize;
    }

    public void setPlaceSize(Long placeSize) {
        this.placeSize = placeSize;
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
        sb.append(", floorTemplateId=").append(floorTemplateId);
        sb.append(", placeWidth=").append(placeWidth);
        sb.append(", placeHeight=").append(placeHeight);
        sb.append(", placeSize=").append(placeSize);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
