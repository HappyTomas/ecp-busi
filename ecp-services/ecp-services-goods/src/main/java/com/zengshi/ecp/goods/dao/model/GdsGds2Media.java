package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GdsGds2Media implements Serializable {
    /**
     * 商品ID
     */
    private Long gdsId;

    /**
     * 单品归属店铺
     */
    private Long shopId;

    /**
     * 媒体ID
     */
    private Long mediaId;

    /**
     * 媒体类型 1为图片，2为视频 3为音频
     */
    private String mediaType;

    /**
     * 1为媒体库引用，2为直接上传
     */
    private String mediaRtype;

    /**
     * 文件UUID
     */
    private String mediaUuid;

    /**
     * 商品媒体文件位置排序，从左到右 依次是1，每个递增1
     */
    private Integer sortNo;

    /**
     * 0表示无效，1表示有效
     */
    private String status;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 创建人
     */
    private Long createStaff;

    /**
     * 最后更新时间
     */
    private Timestamp updateTime;

    /**
     * 最后更新人
     */
    private Long updateStaff;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType == null ? null : mediaType.trim();
    }

    public String getMediaRtype() {
        return mediaRtype;
    }

    public void setMediaRtype(String mediaRtype) {
        this.mediaRtype = mediaRtype == null ? null : mediaRtype.trim();
    }

    public String getMediaUuid() {
        return mediaUuid;
    }

    public void setMediaUuid(String mediaUuid) {
        this.mediaUuid = mediaUuid == null ? null : mediaUuid.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", gdsId=").append(gdsId);
        sb.append(", shopId=").append(shopId);
        sb.append(", mediaId=").append(mediaId);
        sb.append(", mediaType=").append(mediaType);
        sb.append(", mediaRtype=").append(mediaRtype);
        sb.append(", mediaUuid=").append(mediaUuid);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}
