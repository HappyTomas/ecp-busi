package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsAdaptReqDTO extends BaseInfo {
	
	
	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 1840100312476805203L;

	private Long id;

	private Long gdsId;

	private Long  shopId;

	private String adaptType;

	private String adaptTarget;

	private String adaptStrategy;

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

	public String getAdaptType() {
		return adaptType;
	}

	public void setAdaptType(String adaptType) {
		this.adaptType = adaptType == null ? null : adaptType.trim();
	}

	public String getAdaptTarget() {
		return adaptTarget;
	}

	public void setAdaptTarget(String adaptTarget) {
		this.adaptTarget = adaptTarget == null ? null : adaptTarget.trim();
	}

	public String getAdaptStrategy() {
		return adaptStrategy;
	}

	public void setAdaptStrategy(String adaptStrategy) {
		this.adaptStrategy = adaptStrategy == null ? null : adaptStrategy
				.trim();
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
		sb.append(", id=").append(id);
		sb.append(", gdsId=").append(gdsId);
		sb.append(", shopId=").append(shopId);
		sb.append(", adaptType=").append(adaptType);
		sb.append(", adaptTarget=").append(adaptTarget);
		sb.append(", adaptStrategy=").append(adaptStrategy);
		sb.append(", status=").append(status);
		sb.append(", createTime=").append(createTime);
		sb.append(", createStaff=").append(createStaff);
		sb.append(", updateTime=").append(updateTime);
		sb.append(", updateStaff=").append(updateStaff);
		sb.append("]");
		return sb.toString();
	}
}
