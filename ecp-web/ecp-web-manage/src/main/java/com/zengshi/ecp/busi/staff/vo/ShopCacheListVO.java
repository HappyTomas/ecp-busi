package com.zengshi.ecp.busi.staff.vo;

import java.io.Serializable;

public class ShopCacheListVO implements Serializable{/** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    private Long id;
    private String shopName;
    private String shopFullName;
    private Long companyID;
    private String shopGrade;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopFullName() {
		return shopFullName;
	}
	public void setShopFullName(String shopFullName) {
		this.shopFullName = shopFullName;
	}
	public Long getCompanyID() {
		return companyID;
	}
	public void setCompanyID(Long companyID) {
		this.companyID = companyID;
	}
	public String getShopGrade() {
		return shopGrade;
	}
	public void setShopGrade(String shopGrade) {
		this.shopGrade = shopGrade;
	}
    
    
}



