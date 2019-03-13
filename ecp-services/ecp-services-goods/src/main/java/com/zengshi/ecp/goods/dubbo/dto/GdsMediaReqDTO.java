package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;
@SuppressWarnings("rawtypes")
public class GdsMediaReqDTO extends BaseInfo    {
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
    private static final long serialVersionUID = -1L;

	private Long id;

    private Long  shopId;

    private String mediaType;

    private String mediaName;
    
    private long mediaSize;

    private String mediaUuid;

    private Long mediaLibId;

    private Integer sortNo;

    private String picCatgCode;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long  getShopId() {
        return shopId;
    }

    public void setShopId(Long  shopId) {
        this.shopId = shopId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName == null ? null : mediaName.trim();
    }
    
    public long getMediaSize() {
        return mediaSize;
    }

    public void setMediaSize(long mediaSize) {
        this.mediaSize = mediaSize;
    }

    public String getMediaUuid() {
		return mediaUuid;
	}

	public void setMediaUuid(String mediaUuid) {
		this.mediaUuid = mediaUuid;
	}


    public Long getMediaLibId() {
        return mediaLibId;
    }

    public void setMediaLibId(Long mediaLibId) {
        this.mediaLibId = mediaLibId;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getPicCatgCode() {
        return picCatgCode;
    }

    public void setPicCatgCode(String picCatgCode) {
        this.picCatgCode = picCatgCode == null ? null : picCatgCode.trim();
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
}
