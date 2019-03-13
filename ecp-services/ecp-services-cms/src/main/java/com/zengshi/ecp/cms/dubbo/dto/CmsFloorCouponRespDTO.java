package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CmsFloorCouponRespDTO extends BaseResponseDTO{

    /*--------------------------以下为model后添加的字段 start--------------------------*/
    /**
     * 内容位置名称
     */
    private String placeName;
    /**
     * 楼层名称
     */
    private String floorName;
    /**
     * 状态
     */
    private String statusZH;
    /**
     * 优惠券名称
     */
    private String couponName;
    /*--------------------------以下为model后添加的字段 end------------------------*/

 	private Long id;

    private Long couponId;

    private String status;

    private Integer sortNo;

    private String remark;

    private Long placeId;

    private Long floorId;

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

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
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

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
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

	public String getPlaceName() {
		return placeName;
	}

	public String getFloorName() {
		return floorName;
	}

	public String getStatusZH() {
		return statusZH;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public void setStatusZH(String statusZH) {
		this.statusZH = statusZH;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

}
