package com.zengshi.ecp.busi.seller.goods.vo.mediacategory;

import com.zengshi.ecp.busi.seller.goods.vo.category.BaseTreeVO;



public class MediaCategoryTreeVO extends BaseTreeVO{

	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -8263919135040751613L;
	private Short catgLevel;
	
	private String shopId;
	
	private Boolean isRoot;

	public Short getCatgLevel() {
		return catgLevel;
	}

	public void setCatgLevel(Short catgLevel) {
		this.catgLevel = catgLevel;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public Boolean getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}

    

}

