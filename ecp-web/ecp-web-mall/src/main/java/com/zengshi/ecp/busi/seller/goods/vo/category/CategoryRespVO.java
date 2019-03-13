package com.zengshi.ecp.busi.seller.goods.vo.category;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

public class CategoryRespVO extends EcpBaseResponseVO {
	

    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -7534622248823403307L;

	private CategoryVO categoryVO;
	
	private CategoryVO parentVO;

	public CategoryVO getCategoryVO() {
		return categoryVO;
	}

	public void setCategoryVO(CategoryVO categoryVO) {
		this.categoryVO = categoryVO;
	}

	public CategoryVO getParentVO() {
		return parentVO;
	}

	public void setParentVO(CategoryVO parentVO) {
		this.parentVO = parentVO;
	}
	
	

}

