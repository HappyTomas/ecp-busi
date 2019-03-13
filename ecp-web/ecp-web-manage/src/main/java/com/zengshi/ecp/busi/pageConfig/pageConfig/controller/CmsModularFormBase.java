/**
 * CmsModularFormBase.java	  V1.0   2016年5月1日 下午8:56:19
 *
 * Copyright 2016 AsiaInfoData Technology Co.,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.zengshi.ecp.busi.pageConfig.pageConfig.controller;

import java.util.List;

import org.springframework.ui.Model;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.vo.CmsItemPropReqVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO;
import com.zengshi.butterfly.core.web.BaseController;

/**
 * 功能描述：获得动态属性基础信息（抽象类）
 *
 * @author  曾海沥(Terry)
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public abstract class CmsModularFormBase extends BaseController {
	/**
	 * 
	 * 功能描述：动态表单保存，用于在获取通用表单内容后，再按需处理数据，并进行数据保存（需要被继承的方法）
	 *
	 * @author  曾海沥(Terry)
	 * <p>创建日期 ：2016年5月1日 下午8:58:57</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	protected abstract EcpBaseResponseVO modularFormSave(Model model,List<CmsItemPropPreReqDTO> items) throws Exception;
	/**
	 * 
	 * 功能描述：获得表单数据，并转换成CmsItemPropPre的列表数据模型，以供后续操作
	 *
	 * @author  曾海沥(Terry)
	 * <p>创建日期 ：2016年5月1日 下午9:18:21</p>
	 *
	 * @param model
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	protected List<CmsItemPropPreReqDTO> getFormData(Model model,CmsItemPropReqVO forms) throws Exception {
		return forms.getForms();
	}
	/**
	 * 
	 * 功能描述：执行表单保存（需要被显示执行的方法）
	 *
	 * @author  曾海沥(Terry)
	 * <p>创建日期 ：2016年5月1日 下午9:38:53</p>
	 *
	 * @param model
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	protected EcpBaseResponseVO doDynamicFormSave(Model model,CmsItemPropReqVO forms) throws Exception{
		List<CmsItemPropPreReqDTO> items = this.getFormData(model,forms);
		return this.modularFormSave(model,items);
	}
}
