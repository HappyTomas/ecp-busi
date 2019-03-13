/**
 * DemoController.java	  V1.0   2016年6月3日 下午5:37:06
 *
 * Copyright 2016 AsiaInfo Data Technology Co.,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.zengshi.ecp.busi.mobiledemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;

/**
 * 功能描述：示例Controller
 *
 * @author  曾海沥(Terry)
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
@Controller
@RequestMapping(value = "/mobile-demo")
public class DemoController extends EcpBaseController {
	/**
	 * 
	 * 功能描述：初始化
	 *
	 * @author  曾海沥(Terry)
	 * <p>创建日期 ：2016年6月3日 下午5:42:47</p>
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping()
	public String init(Model model) throws Exception{
		return "/demo/demo-init";
	}
}