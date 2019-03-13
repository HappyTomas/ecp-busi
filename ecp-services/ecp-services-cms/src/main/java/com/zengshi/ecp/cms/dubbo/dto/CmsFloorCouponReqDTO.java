package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CmsFloorCouponReqDTO extends BaseInfo{
    /**
     * 状态SET，用于查询
     */
        private Set<String> statusSet = new HashSet<String>();
 //-----------------------------------------------------------//
	
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
	    
	  //--------------------------------------------------//

	    public Set<String> getStatusSet() {
	        return statusSet;
	    }

	    public void setStatusSet(Set<String> statusSet) {
	        this.statusSet = statusSet;
	    }
}
