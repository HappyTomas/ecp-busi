package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsCollectReqDTO extends BaseInfo {
	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 4978128605561002089L;

	private Long id;

	private Long gdsId;

	private Long skuId;

	private Long shopId;

	private String gdsName;

	private Long gdsPrice;

	private Long staffId;
	
	private String staffName;

	private Timestamp collectionTime;

	private String remark;

	private String status;

	private Timestamp createTime;

	private Long createStaff;

	private Timestamp updateTime;

	private Long updateStaff;

	//是否返回收藏用户统计数
	private boolean includeStaffCount=false;

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

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getGdsName() {
		return gdsName;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName == null ? null : gdsName.trim();
	}

	public Long getGdsPrice() {
		return gdsPrice;
	}

	public void setGdsPrice(Long gdsPrice) {
		this.gdsPrice = gdsPrice;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Timestamp getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(Timestamp collectionTime) {
		this.collectionTime = collectionTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
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
	
	public boolean isIncludeStaffCount() {
        return includeStaffCount;
    }

    public void setIncludeStaffCount(boolean includeStaffCount) {
        this.includeStaffCount = includeStaffCount;
    }

    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", gdsId=").append(gdsId);
		sb.append(", skuId=").append(skuId);
		sb.append(", shopId=").append(shopId);
		sb.append(", gdsName=").append(gdsName);
		sb.append(", gdsPrice=").append(gdsPrice);
		sb.append(", staffId=").append(staffId);
		sb.append(", collectionTime=").append(collectionTime);
		sb.append(", remark=").append(remark);
		sb.append(", status=").append(status);
		sb.append(", createTime=").append(createTime);
		sb.append(", createStaff=").append(createStaff);
		sb.append(", updateTime=").append(updateTime);
		sb.append(", updateStaff=").append(updateStaff);
		sb.append("]");
		return sb.toString();
	}
}
