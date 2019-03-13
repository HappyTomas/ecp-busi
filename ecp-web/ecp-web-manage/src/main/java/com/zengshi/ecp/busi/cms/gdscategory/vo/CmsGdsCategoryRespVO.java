package com.zengshi.ecp.busi.cms.gdscategory.vo;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

public class CmsGdsCategoryRespVO extends EcpBaseResponseVO {

    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -7534622248823403307L;

	private CmsGdsCategoryVO cmsGdsCategoryVO;

	public CmsGdsCategoryVO getCmsCategoryVO() {
		return cmsGdsCategoryVO;
	}

	public void setCmsGdsCategoryVO(CmsGdsCategoryVO cmsGdsCategoryVO) {
		this.cmsGdsCategoryVO = cmsGdsCategoryVO;
	}
}
