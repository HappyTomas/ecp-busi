package com.zengshi.ecp.busi.goods.gdscategory.vo;

import com.zengshi.ecp.busi.goods.common.vo.BaseTreeVO;


public class CategoryTreeVO extends BaseTreeVO{

	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -7036373984413541896L;
	
	private Short catgLevel;
	/**
	 * 是否目录.
	 */
	private Boolean isRoot;
	
	private String catlogId;
	
	private String shopId;

	public Short getCatgLevel() {
		return catgLevel;
	}

	public void setCatgLevel(Short catgLevel) {
		this.catgLevel = catgLevel;
	}

	public Boolean getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}

	public String getCatlogId() {
		return catlogId;
	}

	public void setCatlogId(String catlogId) {
		this.catlogId = catlogId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	
	
	

}

