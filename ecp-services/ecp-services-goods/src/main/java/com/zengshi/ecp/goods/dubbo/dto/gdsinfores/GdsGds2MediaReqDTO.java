package com.zengshi.ecp.goods.dubbo.dto.gdsinfores;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;
public class GdsGds2MediaReqDTO extends BaseInfo    {
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -959078747971372943L;

	private Long gdsId;

    private Long  shopId;

    private Long mediaId;

    private String mediaType;

    private String mediaUuid;

    private String mediaRtype;

    private Integer sortNo;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String oldMediaUuid;
    
    /**
     * 备注
     */
    private String remark;

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

    public String getMediaRtype() {
		return mediaRtype;
	}

	public void setMediaRtype(String mediaRtype) {
		this.mediaRtype = mediaRtype;
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

	public String getOldMediaUuid() {
		return oldMediaUuid;
	}

	public void setOldMediaUuid(String oldMediaUuid) {
		this.oldMediaUuid = oldMediaUuid;
	}

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    

}
