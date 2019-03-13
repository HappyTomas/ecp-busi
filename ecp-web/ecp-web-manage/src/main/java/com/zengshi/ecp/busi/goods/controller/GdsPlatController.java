package com.zengshi.ecp.busi.goods.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.vo.GdsGuessVO;
import com.zengshi.ecp.busi.goods.vo.GdsShopVO;
import com.zengshi.ecp.busi.goods.vo.GdsSkuInfo;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPlatRecomRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 平台推荐<br>
 * Date:2015年9月14日上午11:17:20 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author tongkai
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping("/gdsplat")
public class GdsPlatController extends EcpBaseController {
	private static String MODULE = GdsPlatController.class.getName();
	private static String URL = "/goods/gdsPlat";
	@Resource(name = "gdsPlatRecomRSV")
	private IGdsPlatRecomRSV gdsPlatRecomRSV;

	@Resource(name = "gdsInfoQueryRSV")
	private IGdsInfoQueryRSV gdsInfoQueryRSV;

	// 初始化平台推荐
	@RequestMapping()
	public String init() {
		return URL + "/plat-grid";
	}

	// 新增平台推荐
	@RequestMapping(value = "/platadd")
	public String platAdd(Model model,GdsShopVO gsShopVO) {
		model.addAttribute("shopId", gsShopVO.getShopId());
		return URL + "/plat-add";
	}

	// 批量删除
	@RequestMapping(value = "/gdsbatchremove")
	@ResponseBody
	public EcpBaseResponseVO gdsBatchRemove(GdsGuessVO gdsVo) {
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		GdsPlatRecomReqDTO dto = new GdsPlatRecomReqDTO();
		String[] idList = gdsVo.getOperateId().split(",");
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < idList.length; i++) {
			if (StringUtil.isNotBlank(idList[i])) {
				ids.add(Long.parseLong(idList[i]));
			}
		}
		dto.setIds(ids);
		try {
			gdsPlatRecomRSV.executeBatchDeleteGdsPlatRecom(dto);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				vo.setResultMsg(be.getErrorMessage());
			} else {
				vo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "批量删除错误！", be);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		}
		return vo;
	}

	// 显示列表
	@RequestMapping(value = "/platlist")
	@ResponseBody
	public Model platList(Model model, @Valid GdsGuessVO vo) throws Exception {
		// / 后场服务所需要的DTO；

		GdsPlatRecomReqDTO dto = vo.toBaseInfo(GdsPlatRecomReqDTO.class);
		// 组织参数
		ObjectCopyUtil.copyObjValue(vo, dto, null, false);
		PageResponseDTO<GdsPlatRecomRespDTO> t = gdsPlatRecomRSV
				.queryGdsPlatRecomRespDTOPaging(dto);
		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		if (t.getResult() == null) {
			t.setResult(new ArrayList<GdsPlatRecomRespDTO>());
		}
		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO
				.buildByPageResponseDTO(t);

		return super.addPageToModel(model, respVO);
	}

	// 显示新增界面列表
	@RequestMapping(value = "/addlist")
	@ResponseBody
	public Model addList(Model model, GdsSkuInfo vo) throws Exception {
		GdsInfoReqDTO dto = vo.toBaseInfo(GdsInfoReqDTO.class);
		ObjectCopyUtil.copyObjValue(vo, dto, null, false);
		List<String> status=new ArrayList<String>();
		status.add(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
		dto.setGdsStatusArr(status);
		dto.setIfScoreGds(GdsConstants.Commons.STATUS_INVALID);
		PageResponseDTO<GdsInfoRespDTO> t = gdsInfoQueryRSV
				.queryGdsInfoListPage(dto);
		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		if (t.getResult() == null) {
			t.setResult(new ArrayList<GdsInfoRespDTO>());
		}
		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO
				.buildByPageResponseDTO(t);

		return super.addPageToModel(model, respVO);
	}

	// 编辑
	@RequestMapping(value = "/gdsedit")
	public String gdsEdit(Model model, @RequestParam("id") String id ,GdsShopVO gsShopVO) {

		GdsPlatRecomReqDTO reqDto = new GdsPlatRecomReqDTO();
		reqDto.setId(Long.parseLong(id));
		GdsPlatRecomRespDTO dto = gdsPlatRecomRSV.queryGdsPlatRecomByPK(reqDto);
		model.addAttribute("plat", dto);
		model.addAttribute("shopId", gsShopVO.getShopId());
		return URL + "/plat-edit";
	}

	// 新增保存
	@RequestMapping(value = "/save")
	@ResponseBody
	public EcpBaseResponseVO save(@Valid GdsGuessVO VO) throws Exception {
		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(VO));
		GdsPlatRecomReqDTO reqDTO = new GdsPlatRecomReqDTO();
		VO.setCatgCode(VO.getRelatedCatgCode());
		VO.setSkuName(VO.getRelatedName());
		ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
		reqDTO.setGdsName(VO.getRelatedName());
		// 如果排序为空，则默认赋值为1.
		if (reqDTO.getSortNo() == null) {
			reqDTO.setSortNo(1);
		}

		try {
			gdsPlatRecomRSV.saveGdsPlatRecom(reqDTO);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (BusinessException err) {
			LogUtil.error(MODULE, "保存错误！！", err);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			respVo.setResultMsg(err.getErrorMessage());
		}

		return respVo;
	}

	// 编辑保存
	@RequestMapping(value = "/editsave")
	@ResponseBody
	public EcpBaseResponseVO editSave(@Valid GdsGuessVO VO) throws Exception {
		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(VO));
		GdsPlatRecomReqDTO reqDTO = new GdsPlatRecomReqDTO();
		ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
		reqDTO.setCatgCode(VO.getRelatedCatgCode());
		reqDTO.setGdsName(VO.getRelatedName());
		reqDTO.setId(Long.parseLong(VO.getOperateId()));
		if(reqDTO.getCatgCode()==null)
			reqDTO.setCatgCode("");

		// 如果排序为空，则默认赋值为1.
		if (reqDTO.getSortNo() == null) {
			reqDTO.setSortNo(1);
		}

		try {
			gdsPlatRecomRSV.editGdsPlatRecom(reqDTO);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (BusinessException err) {
			LogUtil.error(MODULE, "保存错误！！", err);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			respVo.setResultMsg(err.getErrorMessage());
		}

		return respVo;
	}

}
