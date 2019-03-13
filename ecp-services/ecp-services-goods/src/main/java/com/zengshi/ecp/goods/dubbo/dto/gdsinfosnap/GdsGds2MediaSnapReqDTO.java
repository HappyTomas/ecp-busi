package com.zengshi.ecp.goods.dubbo.dto.gdsinfosnap;

import com.zengshi.ecp.server.front.dto.BaseInfo;
@SuppressWarnings("rawtypes")
public class GdsGds2MediaSnapReqDTO extends BaseInfo    {
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 7496888116260743985L;

	private Long snapId;

    private Long gdsId;

    private Long  shopId;

    private Long mediaId;

    private String mediaType;

    private String mediaUuid;

    private String meidaRtype;

    private Integer sortNo;

    private String status;


    public Long getSnapId() {
        return snapId;
    }

    public void setSnapId(Long snapId) {
        this.snapId = snapId;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long  getShopId() {
        return shopId;
    }

    public void setShopId(Long  shopId) {
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
        this.mediaType = mediaType;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", snapId=").append(snapId);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", shopId=").append(shopId);
        sb.append(", mediaId=").append(mediaId);
        sb.append(", mediaType=").append(mediaType);
        sb.append(", mediaUuid=").append(mediaUuid);
        sb.append(", meidaRtype=").append(meidaRtype);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}
