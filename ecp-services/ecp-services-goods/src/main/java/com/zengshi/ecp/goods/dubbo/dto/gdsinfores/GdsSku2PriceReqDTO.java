package com.zengshi.ecp.goods.dubbo.dto.gdsinfores;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class GdsSku2PriceReqDTO extends BaseInfo {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 1L;

	private Long priceId;

	private Long skuId;

	private Long gdsId;

	private Long  shopId;

	private Long priceTypeId;

	private Long priceTypeLevel;
	
	/**
	 * 价格关系类型 1为单品价格 2为商品价格
	 */
	private String rType;
	
	private String priceTypeCode;

	private String status;

	private Timestamp createTime;

	private Long createStaff;

	private Timestamp updateTime;

	private Long updateStaff;
	
	private BaseInfo price;
	
	private Map<String, Object> params=new HashMap<String, Object>();
	
	private String priceType;
	
	/**
	 * 查询单品/商品价格关系  是否包含价格信息  如果为false，则只查询关系表，否则还会查询价格表
	 */
	private boolean ifPrice;

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
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

	public Long getPriceTypeId() {
		return priceTypeId;
	}

	public void setPriceTypeId(Long priceTypeId) {
		this.priceTypeId = priceTypeId;
	}

	public Long getPriceTypeLevel() {
		return priceTypeLevel;
	}

	public void setPriceTypeLevel(Long priceTypeLevel) {
		this.priceTypeLevel = priceTypeLevel;
	}

	public String getPriceTypeCode() {
		return priceTypeCode;
	}

	public void setPriceTypeCode(String priceTypeCode) {
		this.priceTypeCode = priceTypeCode;
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

	public BaseInfo getPrice() {
		return price;
	}

	public void setPrice(BaseInfo price) {
		this.price = price;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", gdsId=").append(gdsId);
		sb.append(", shopId=").append(shopId);
		sb.append(", priceTypeId=").append(priceTypeId);
		sb.append(", priceTypeLevel=").append(priceTypeLevel);
		sb.append(", status=").append(status);
		sb.append(", createTime=").append(createTime);
		sb.append(", createStaff=").append(createStaff);
		sb.append(", updateTime=").append(updateTime);
		sb.append(", updateStaff=").append(updateStaff);
		sb.append("]");
		return sb.toString();
	}

	public boolean getIfPrice() {
		return ifPrice;
	}

	public void setIfPrice(boolean ifPrice) {
		this.ifPrice = ifPrice;
	}

	public String getrType() {
		return rType;
	}

	public void setrType(String rType) {
		this.rType = rType;
	}
}
