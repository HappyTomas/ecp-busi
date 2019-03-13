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
import com.zengshi.ecp.busi.goods.vo.GdsCatlogVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalogRSV;
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
 * Description: 目录管理<br>
 * Date:2015年9月17日下午17:21:20 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author tongkai
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping("/gdscatalog")
public class GdsCatalogController extends EcpBaseController {
	private static String MODULE = GdsCatalogController.class.getName();
	private static String URL = "/goods/gdsCatalog";

	@Resource(name = "gdsCatalogRSV")
	private IGdsCatalogRSV gdsCatalogRSV;

	@Resource(name = "gdsCatalog2SiteRSV")
	private IGdsCatalog2SiteRSV gdsCatalog2SiteRSV;
	/**
	 * 
	 * init:(初始化跳转到猜你喜欢列表页面). <br/>
	 * 
	 * @author tongkai
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping()
	public String init() {
		return URL + "/catalog-grid";
	}

	// 新增目录
	@RequestMapping(value = "/catalogadd")
	public String catalogAdd() {
		return URL + "/open/catalog-add";
	}

	// 编辑目录
	@RequestMapping(value = "/catalogedit")
	public String catalogEdit(Model model, @RequestParam("id") String id) {

		GdsCatalogReqDTO reqDto = new GdsCatalogReqDTO();
		reqDto.setId(Long.parseLong(id));
		GdsCatalogRespDTO dto = gdsCatalogRSV.queryGdsCatalogByPK(reqDto);
		model.addAttribute("respDto", dto);
		return URL + "/open/catalog-edit";
	}

	// 显示目录列表
	@RequestMapping(value = "/cataloglist")
	@ResponseBody
	public Model catalogList(Model model, GdsCatlogVO vo) throws Exception {
		// / 后场服务所需要的DTO；
		GdsCatalogReqDTO dto = vo.toBaseInfo(GdsCatalogReqDTO.class);
		// 组织参数
		ObjectCopyUtil.copyObjValue(vo, dto, null, false);
		PageResponseDTO<GdsCatalogRespDTO> t = gdsCatalogRSV
				.queryGdsCatalogRespDTOPaging(dto);
		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		if (t.getResult() == null) {
			t.setResult(new ArrayList<GdsCatalogRespDTO>());
		}
		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO
				.buildByPageResponseDTO(t);

		return super.addPageToModel(model, respVO);
	}

	// 批量删除
	@RequestMapping(value = "/gdsbatchremove")
	@ResponseBody
	public EcpBaseResponseVO gdsBatchRemove(GdsCatlogVO gdsVo) {
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		GdsCatalogReqDTO dto = new GdsCatalogReqDTO();
		String[] idList = gdsVo.getOperateId().split(",");
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < idList.length; i++) {
			if (StringUtil.isNotBlank(idList[i])) {
				ids.add(Long.parseLong(idList[i]));
			}
		}
		dto.setIds(ids);
		try {
			gdsCatalogRSV.batchDelete(dto);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {

			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				vo.setResultMsg(be.getErrorMessage());
			} else {
				vo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "删除出错！", be);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		}
		return vo;
	}
	
	// 批量启用
	@RequestMapping(value = "/gdsenable")
	@ResponseBody
	public EcpBaseResponseVO gdsEnable(GdsCatlogVO gdsVo) {
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		GdsCatalogReqDTO dto = new GdsCatalogReqDTO();
		String[] idList = gdsVo.getOperateId().split(",");
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < idList.length; i++) {
			if (StringUtil.isNotBlank(idList[i])) {
				ids.add(Long.parseLong(idList[i]));
			}
		}
		dto.setIds(ids);
		try {
			// 启用
			gdsCatalogRSV.batchEnable(dto);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {

			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				vo.setResultMsg(be.getErrorMessage());
			} else {
				vo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "启用出错！", be);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		}
		return vo;
	}

	
	// 设置默认
		@RequestMapping(value = "/gdsDefault")
		@ResponseBody
		public EcpBaseResponseVO gdsDefault(GdsCatlogVO gdsVo) {
			EcpBaseResponseVO vo = new EcpBaseResponseVO();
			GdsCatalogReqDTO dto = new GdsCatalogReqDTO();
			String[] idList = gdsVo.getOperateId().split(",");
			List<Long> ids = new ArrayList<Long>();
			for (int i = 0; i < idList.length; i++) {
				if (StringUtil.isNotBlank(idList[i])) {
					ids.add(Long.parseLong(idList[i]));
				}
			}
			dto.setId(ids.get(0));
			try {
				// 设置默认
				gdsCatalogRSV.updateDefaultCatalog(dto);
				vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
			} catch (Exception e) {

				BusinessException be = (BusinessException) e;
				if (e instanceof BusinessException) {
					vo.setResultMsg(be.getErrorMessage());
				} else {
					vo.setResultMsg(e.getMessage());
				}
				LogUtil.error(MODULE, "设置默认出错！", be);
				vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			}
			return vo;
		}
	// 新增保存
	@RequestMapping(value = "/save")
	@ResponseBody
	public EcpBaseResponseVO save(@Valid GdsCatlogVO VO) throws Exception {
		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(VO));
		GdsCatalogReqDTO reqDTO = new GdsCatalogReqDTO();
		ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);

		try {
			GdsCatalogReqDTO cata = new GdsCatalogReqDTO();
			cata.setCatlogCode(reqDTO.getCatlogCode());
			if(gdsCatalogRSV.queryExist(cata)){
				throw new BusinessException("目录编码重复!"); 
			}
			gdsCatalogRSV.saveGdsCatalog(reqDTO);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (BusinessException err) {
			LogUtil.error(MODULE, "保存错误！！", err);
			respVo.setResultMsg(err.getErrorMessage());
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		}

		return respVo;
	}

	// 编辑保存
	@RequestMapping(value = "/editsave")
	@ResponseBody
	public EcpBaseResponseVO editSave(@Valid GdsCatlogVO VO) throws Exception {
		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(VO));
		GdsCatalogReqDTO reqDTO = new GdsCatalogReqDTO();
		ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
		if (VO.getId() != null) {
			try {
				GdsCatalogReqDTO cata = new GdsCatalogReqDTO();
				cata.setCatlogCode(reqDTO.getCatlogCode());
				
				if(!(gdsCatalogRSV.queryGdsCatalogByPK(reqDTO).getCatlogCode().equals(VO.getCatlogCode())))
					if(gdsCatalogRSV.queryExist(cata)){
						throw new BusinessException("目录编码重复!"); 
					}
				gdsCatalogRSV.editGdsCatalog(reqDTO);
				respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
			} catch (BusinessException err) {
				LogUtil.error(MODULE, "保存错误！！", err);
				respVo.setResultMsg(err.getErrorMessage());
				respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			}

		}
		return respVo;
	}
	
    @RequestMapping("/removeCheck")
    public EcpBaseResponseVO removeCheck(GdsCatlogVO VO) throws Exception{
    	EcpBaseResponseVO respVO = new EcpBaseResponseVO();
    	GdsCatalog2SiteReqDTO condition = new GdsCatalog2SiteReqDTO();
    	condition.setCatalogStatus(GdsConstants.Commons.STATUS_VALID);
    	condition.setCatlogId(VO.getId());
    	
    	try{
    	    boolean isInUse = gdsCatalog2SiteRSV.queryIsRelationWithSite(condition);
    	    respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
    	    if(isInUse){
    	    	respVO.setResultMsg("true");
    	    }else{
    	    	respVO.setResultMsg("false");
    	    }
    	}catch (BusinessException e) {
			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			respVO.setResultMsg(e.getErrorMessage());
		}
    	return respVO;
    }

}
