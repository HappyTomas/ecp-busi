package com.zengshi.ecp.busi.pageConfig.utils.vo;

import com.zengshi.ecp.busi.goods.common.vo.BaseTreeVO;

public class CmsLinkGdsCategoryTreeVO extends BaseTreeVO{

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
	
	private String siteId;
	
	private String catgCode;

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

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }
	
	
}
