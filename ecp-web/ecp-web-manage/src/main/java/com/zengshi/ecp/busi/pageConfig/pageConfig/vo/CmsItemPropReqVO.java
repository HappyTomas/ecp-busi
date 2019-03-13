/**
 * CmsItemPropReqVO.java	  V1.0   2016年5月3日 下午3:55:07
 *
 * Copyright 2016 AsiaInfoData Technology Co.,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.zengshi.ecp.busi.pageConfig.pageConfig.vo;

import java.io.Serializable;
import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO;

/**
 * 功能描述：布局项设置表单数据模型
 *
 * @author  曾海沥(Terry)
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class CmsItemPropReqVO extends EcpBasePageReqVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -706174599740866967L;
	/**
	 * 表单数据项内容
	 */
	private List<CmsItemPropPreReqDTO> forms;

	public List<CmsItemPropPreReqDTO> getForms() {
		return forms;
	}

	public void setForms(List<CmsItemPropPreReqDTO> forms) {
		this.forms = forms;
	}
	
	
}
