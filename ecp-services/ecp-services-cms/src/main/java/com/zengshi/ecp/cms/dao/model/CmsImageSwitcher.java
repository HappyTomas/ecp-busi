package com.zengshi.ecp.cms.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CmsImageSwitcher implements Serializable {
    private Long id;

    private String imName;

    private String describeInfo;

    private String remark;

    private String onePic;

    private String twicePic;

    private Integer sortNo;

    private String status;

    private Long siteId;

    private Long templateId;

    private Long placeId;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private String twicePicName;

    private String onePicName;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImName() {
        return imName;
    }

    public void setImName(String imName) {
        this.imName = imName == null ? null : imName.trim();
    }

    public String getDescribeInfo() {
        return describeInfo;
    }

    public void setDescribeInfo(String describeInfo) {
        this.describeInfo = describeInfo == null ? null : describeInfo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOnePic() {
        return onePic;
    }

    public void setOnePic(String onePic) {
        this.onePic = onePic == null ? null : onePic.trim();
    }

    public String getTwicePic() {
        return twicePic;
    }

    public void setTwicePic(String twicePic) {
        this.twicePic = twicePic == null ? null : twicePic.trim();
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

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
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

    public String getTwicePicName() {
        return twicePicName;
    }

    public void setTwicePicName(String twicePicName) {
        this.twicePicName = twicePicName == null ? null : twicePicName.trim();
    }

    public String getOnePicName() {
        return onePicName;
    }

    public void setOnePicName(String onePicName) {
        this.onePicName = onePicName == null ? null : onePicName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", imName=").append(imName);
        sb.append(", describeInfo=").append(describeInfo);
        sb.append(", remark=").append(remark);
        sb.append(", onePic=").append(onePic);
        sb.append(", twicePic=").append(twicePic);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", status=").append(status);
        sb.append(", siteId=").append(siteId);
        sb.append(", templateId=").append(templateId);
        sb.append(", placeId=").append(placeId);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", twicePicName=").append(twicePicName);
        sb.append(", onePicName=").append(onePicName);
        sb.append("]");
        return sb.toString();
    }
}
