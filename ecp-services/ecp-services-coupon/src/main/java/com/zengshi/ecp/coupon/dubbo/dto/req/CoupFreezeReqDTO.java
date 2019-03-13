package com.zengshi.ecp.coupon.dubbo.dto.req;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CoupFreezeReqDTO extends BaseInfo {
	
	private static final long serialVersionUID = 1L;
	
	  	private Long id;

	    private Long applyId;

	    private String orderId;

	    private String orderSubId;

	    private Long staffId;

	    private Long coupDetailId;

	    private Long coupId;

	    private Long shopId;

	    private String freezeType;

	    private String status;

	    private String coupNo;

	    private String remark;

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

	    public Long getApplyId() {
	        return applyId;
	    }

	    public void setApplyId(Long applyId) {
	        this.applyId = applyId;
	    }

	    public String getOrderId() {
	        return orderId;
	    }

	    public void setOrderId(String orderId) {
	        this.orderId = orderId == null ? null : orderId.trim();
	    }

	    public String getOrderSubId() {
	        return orderSubId;
	    }

	    public void setOrderSubId(String orderSubId) {
	        this.orderSubId = orderSubId == null ? null : orderSubId.trim();
	    }

	    public Long getStaffId() {
	        return staffId;
	    }

	    public void setStaffId(Long staffId) {
	        this.staffId = staffId;
	    }

	    public Long getCoupDetailId() {
	        return coupDetailId;
	    }

	    public void setCoupDetailId(Long coupDetailId) {
	        this.coupDetailId = coupDetailId;
	    }

	    public Long getCoupId() {
	        return coupId;
	    }

	    public void setCoupId(Long coupId) {
	        this.coupId = coupId;
	    }

	    public Long getShopId() {
	        return shopId;
	    }

	    public void setShopId(Long shopId) {
	        this.shopId = shopId;
	    }

	    public String getFreezeType() {
	        return freezeType;
	    }

	    public void setFreezeType(String freezeType) {
	        this.freezeType = freezeType == null ? null : freezeType.trim();
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status == null ? null : status.trim();
	    }

	    public String getCoupNo() {
	        return coupNo;
	    }

	    public void setCoupNo(String coupNo) {
	        this.coupNo = coupNo == null ? null : coupNo.trim();
	    }

	    public String getRemark() {
	        return remark;
	    }

	    public void setRemark(String remark) {
	        this.remark = remark == null ? null : remark.trim();
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
	        sb.append(", id=").append(id);
	        sb.append(", applyId=").append(applyId);
	        sb.append(", orderId=").append(orderId);
	        sb.append(", orderSubId=").append(orderSubId);
	        sb.append(", staffId=").append(staffId);
	        sb.append(", coupDetailId=").append(coupDetailId);
	        sb.append(", coupId=").append(coupId);
	        sb.append(", shopId=").append(shopId);
	        sb.append(", freezeType=").append(freezeType);
	        sb.append(", status=").append(status);
	        sb.append(", coupNo=").append(coupNo);
	        sb.append(", remark=").append(remark);
	        sb.append(", createTime=").append(createTime);
	        sb.append(", createStaff=").append(createStaff);
	        sb.append(", updateTime=").append(updateTime);
	        sb.append(", updateStaff=").append(updateStaff);
	        sb.append("]");
	        return sb.toString();
	    }
	}
