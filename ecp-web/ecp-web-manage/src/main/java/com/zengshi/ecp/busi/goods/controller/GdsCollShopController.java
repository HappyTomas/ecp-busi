package com.zengshi.ecp.busi.goods.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.goods.common.GdsBaseController;
import com.zengshi.ecp.busi.goods.util.GdsParamsTool;
import com.zengshi.ecp.busi.goods.vo.GdsCollectionVO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 卖家 我的收藏 查询收藏 删除收藏<br>
 * Date:2015年9月11日下午4:45:00 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "gdscollshop")
public class GdsCollShopController extends GdsBaseController {

	private String MODULE = GdsCollShopController.class.getName();
	private static String URL = "/goods/gdsCollShop";

	@Resource
	private IGdsCollectRSV iGdsCollectRSV;

	@Resource
	private ICustInfoRSV iCustInfoRSV;

	/**
	 * 
	 * init:(买家我的收藏 初始化). <br/>
	 * 
	 * @author zhanbh 2015.9.11
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping()
	public String init() {
		return URL + "/collection-grid";
	}// end of method init

	/**
	 * 
	 * gridList:(卖家的收藏列表获取). <br/>
	 * 
	 * @author zhanbh 2015.9.11
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "gridlist")
	public Model gridList(Model model, GdsCollectionVO reqVo) {
		// 将页面参数对象VO转化为DTO
		GdsCollectReqDTO reqDto = reqVo.toBaseInfo(GdsCollectReqDTO.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
		EcpBasePageRespVO<Map> pageRespVO = null;
		PageResponseDTO<GdsCollectRespDTO> pageRespDto = null;
		try {
			reqDto.setIncludeStaffCount(true);
			pageRespDto = iGdsCollectRSV
					.queryGdsCollectRespDTOPagingByShop(reqDto);
			// 如果返回空集 创建一个
			if (pageRespDto.getResult() == null) {
				pageRespDto.setResult(new ArrayList<GdsCollectRespDTO>());
			}
			// 将DTO转化为页面参数对象VO
			pageRespVO = EcpBasePageRespVO.buildByPageResponseDTO(pageRespDto);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.info(MODULE, "获取卖家我的收藏列表失败！", e);
		}

		return super.addPageToModel(model, GdsParamsTool.batchGdsDetailUrl(pageRespVO));
	}// end of method gridList

	/**
	 * 
	 * showCollStaff:(显示收藏用户列表页面). <br/>
	 * 
	 * @author zhanbh 2015.10.9
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/showcollstaff")
	public String showCollStaff(Model model, GdsCollectionVO reqVo) {
		model.addAttribute("gdsInfo", reqVo);
		return URL + "/open/collector-grid";
	}

	/**
	 * 
	 * collectorlist:(获取收藏用户列表). <br/>
	 * 
	 * @author zhanbh 2015.10.9
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/collectorlist")
	public Model collectorList(Model model, GdsCollectionVO reqVo) {
		// 将页面参数对象VO转化为DTO
		GdsCollectReqDTO reqDto = reqVo.toBaseInfo(GdsCollectReqDTO.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
		EcpBasePageRespVO<Map> pageRespVO = null;
		PageResponseDTO<GdsCollectRespDTO> pageRespDto = null;
		try {
			pageRespDto = iGdsCollectRSV
					.queryGdsCollectRespDTOPagingByGds(reqDto);
			// 如果返回空集 创建一个
			if (pageRespDto.getResult() == null) {
				pageRespDto.setResult(new ArrayList<GdsCollectRespDTO>());
			}
//			// 转化staffid
//			CustInfoReqDTO cusReqDto = new CustInfoReqDTO();
//			CustInfoResDTO cusResDto = null;
//			for (GdsCollectRespDTO respDto : pageRespDto.getResult()) {
//				cusReqDto.setId(respDto.getStaffId());
//				cusResDto = iCustInfoRSV.getCustInfoById(cusReqDto);
//				respDto.setStaffName(cusResDto.getCustName());
//			}
			// 将DTO转化为页面参数对象VO
			pageRespVO = EcpBasePageRespVO.buildByPageResponseDTO(pageRespDto);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.info(MODULE, "获取收藏人员失败！", e);
		}

		return super.addPageToModel(model, pageRespVO);
	}
}// end of class GdsCollShopController
