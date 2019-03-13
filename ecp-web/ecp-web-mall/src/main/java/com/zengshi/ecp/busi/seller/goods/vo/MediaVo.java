package com.zengshi.ecp.busi.seller.goods.vo;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**
 * 媒体管理  (参数)
 * @author zhanbh
 * 2015.9.6
 * @since JDK 1.6
 */
public class MediaVo extends EcpBasePageReqVO {

	/**
     * serialVersionUID:TODO(媒体管理). 
     * @since JDK 1.6 
	 */
	private static final long serialVersionUID = -6798756914155372153L;

	
	private Long id;

    private Long  shopId;

    private String mediaType;
    
    @NotNull(message="{goods.MediaVO.meidaName.null.error}")
    @Length(min=0,max=128, message="{goods.MediaVO.mediaName.length.error}")
    private String mediaName;//媒体名称

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

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
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
		this.mediaName = mediaName;
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
		this.picCatgCode = picCatgCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	
}//end of class MediaVo
