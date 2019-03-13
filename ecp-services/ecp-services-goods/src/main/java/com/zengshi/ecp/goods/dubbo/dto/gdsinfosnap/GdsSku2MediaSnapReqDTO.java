package com.zengshi.ecp.goods.dubbo.dto.gdsinfosnap;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;
@SuppressWarnings("rawtypes")
public class GdsSku2MediaSnapReqDTO extends BaseInfo    {
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -1268292888154291216L;

	private Long sanpId;

    private Long skuId;

    private Long mediaId;

    private String mediaType;

    private Long  shopId;

    private String mediaUuid;

    private String meidaRtype;

    private Integer sortNo;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;


    public Long getSanpId() {
        return sanpId;
    }

    public void setSanpId(Long sanpId) {
        this.sanpId = sanpId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
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

    public Long  getShopId() {
        return shopId;
    }

    public void setShopId(Long  shopId) {
        this.shopId = shopId;
    }

    public String getMediaUuid() {
        return mediaUuid;
    }

    public void setMediaUuid(String mediaUuid) {
        this.mediaUuid = mediaUuid == null ? null : mediaUuid.trim();
    }

    public String getMeidaRtype() {
        return meidaRtype;
    }

    public void setMeidaRtype(String meidaRtype) {
        this.meidaRtype = meidaRtype == null ? null : meidaRtype.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sanpId=").append(sanpId);
        sb.append(", skuId=").append(skuId);
        sb.append(", mediaId=").append(mediaId);
        sb.append(", mediaType=").append(mediaType);
        sb.append(", shopId=").append(shopId);
        sb.append(", mediaUuid=").append(mediaUuid);
        sb.append(", meidaRtype=").append(meidaRtype);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
