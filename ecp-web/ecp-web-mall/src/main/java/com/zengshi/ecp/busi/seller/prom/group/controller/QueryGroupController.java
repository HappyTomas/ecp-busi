/** 
 * Project Name:ecp-web-manage 
 * File Name:DemoController.java 
 * Package Name:com.zengshi.ecp.busi.demo.controller 
 * Date:2015-8-5下午2:51:38 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.busi.seller.prom.group.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.seller.prom.group.vo.PromGroupVO;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupResponseDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015-8-14下午2:51:38 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version
 * @since JDK 1.7
 */

@Controller
@RequestMapping(value = "/seller/querygroup")
public class QueryGroupController extends EcpBaseController {
	/**
	 * 平台促销组功能
	 */
	private static String MODULE = QueryGroupController.class.getName();

	@Resource
	private IPromGroupRSV promGroupRSV;

	@Resource
	private IScoreInfoRSV scoreInfoRSV;

	/**
	 * 
	 * init:主题促销页面初始化
	 * 
	 * @author lisp
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping()
	public String init(Model model, @ModelAttribute("promGroupVO") PromGroupVO vo) {

		LogUtil.info(MODULE, "主题促销查询初始化");
		// 站点编码
		model.addAttribute("siteId", vo.getSiteId());

		// 1.封装后场入参对象
	/*	PromGroupDTO promGroupDTO = vo.toBaseInfo(PromGroupDTO.class);
		ObjectCopyUtil.copyObjValue(vo, promGroupDTO, "", false);
		promGroupDTO.setStatus(PromConstants.PromGroup.STATUS_1);
		promGroupDTO.setCalDate(new Date(45544545L));// DateUtil.getSysDate());
		PageResponseDTO<PromGroupResponseDTO> groupPage = promGroupRSV.queryPromGroupList(promGroupDTO);
		model.addAttribute("groupPage", groupPage);*/
		return "/seller/prom/group/query/select-group-grid";
	}

	/**
	 * 
	 * 主题促销页面查询和分页
	 * 
	 * @author huangjx
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/grouplist")
	public String groupList(Model model, PromGroupVO vo) throws BusinessException {
		
		// 1.封装后场入参对象
		PromGroupDTO promGroupDTO = vo.toBaseInfo(PromGroupDTO.class);
		ObjectCopyUtil.copyObjValue(vo, promGroupDTO, "", false);

		PageResponseDTO<PromGroupResponseDTO> groupPage = promGroupRSV.queryPromGroupList(promGroupDTO);
		model.addAttribute("groupPage", groupPage);
		return "/seller/prom/group/query/group-list";
	}

}