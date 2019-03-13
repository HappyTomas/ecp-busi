/**
 * CmsParamsRenderController.java	  V1.0   2016年4月26日 上午11:13:16
 *
 * Copyright 2016 AsiaInfoData Technology Co.,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.zengshi.ecp.busi.pageConfig.pageConfig.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPreRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularParaPropRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPreRSV;

/**
 * 功能描述：参数动态读取
 *
 * @author  曾海沥(Terry)
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
@Controller
@RequestMapping(value="/paramsrender")
public class CmsParamsRenderController extends EcpBaseController {
	@Resource(name = "cmsPageAttrPreRSV")
    private ICmsPageAttrPreRSV cmsPageAttrPreRSV;
	
	@Resource(name = "cmsItemPropPreRSV")
    private ICmsItemPropPreRSV cmsItemPropPreRSV;
	
	@Resource(name = "cmsModularParaPropRSV")
	private ICmsModularParaPropRSV cmsModularParaPropRSV;
	/**
	 * 
	 * 功能描述：初始化
	 *
	 * @author  曾海沥(Terry)
	 * <p>创建日期 ：2016年4月26日 上午11:15:30</p>
	 *
	 * @param model
	 * @param pageId
	 * @return
	 * @throws Exception
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/init")
    public String init(Model model, Long pageId,Long modularId,Long itemId) throws Exception{
		CmsModularParaPropReqDTO prop = new CmsModularParaPropReqDTO();
		prop.setModularId(modularId);
		List<CmsModularParaPropRespDTO> attrs = cmsModularParaPropRSV.queryCmsModularParaPropList(prop);
		model.addAttribute("attrs", attrs);
		model.addAttribute("pageId", pageId);
		model.addAttribute("modularId", modularId);
		model.addAttribute("itemId", itemId);
		return "/pageConfig/pageConfig/edit/paramsrender/params-render";
	}
}
