package com.zengshi.ecp.goods.dubbo.dto.price;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class GdsPriceLadderReqDTO extends BaseInfo {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 3927489754385695827L;

	private Long id;

	private Long gdsId;

	private Long skuId;

	private Long  shopId;

	private Long startAmount;

	private Long price;

	private Long currencysId;

	private Integer sortNo;

	private String status;

	private Timestamp createTime;

	private Long createStaff;

	private Timestamp updateTime;

	private Long updateStaff;

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long  getShopId() {
		return shopId;
	}

	public void setShopId(Long  shopId) {
		this.shopId = shopId;
	}

	public Long getStartAmount() {
		return startAmount;
	}

	public void setStartAmount(Long startAmount) {
		this.startAmount = startAmount;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getCurrencysId() {
		return currencysId;
	}

	public void setCurrencysId(Long currencysId) {
		this.currencysId = currencysId;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", skuId=").append(skuId);
		sb.append(", shopId=").append(shopId);
		sb.append(", startAmount=").append(startAmount);
		sb.append(", price=").append(price);
		sb.append(", currencysId=").append(currencysId);
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
