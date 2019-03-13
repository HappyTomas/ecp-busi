package com.zengshi.ecp.busi.goods.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.common.GdsBaseController;
import com.zengshi.ecp.busi.goods.vo.GdsLabelVO;
import com.zengshi.ecp.goods.dubbo.dto.GdsLabelReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsLabelRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsLabelRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 标签管理，查询标签，新增标签，删除标签， <br>
 * Date:2015年9月16日下午7:45:00 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/gdslabel")
public class GdsLabelController extends GdsBaseController {

	private String MODULE = GdsLabelController.class.getName();
	private static String URL = "/goods/gdsLabel";

	@Resource
	private IGdsLabelRSV iGdsLabelRSV;

	/**
	 * 
	 * init:(标签管理初始化). <br/>
	 * 
	 * @author zhanbh 2015.9.17
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping()
	public String init() {
		return URL + "/label-grid";
	}// end of method init

	/**
	 * 
	 * gridList:(标签列表获取). <br/>
	 * 
	 * @author zhanbh 2015.9.17
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "gridlist")
	private Model gridList(Model model, GdsLabelVO reqVo) {
		// 将VO 转化为 DTO 前台参数获取
		GdsLabelReqDTO reqDto = reqVo.toBaseInfo(GdsLabelReqDTO.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);

		EcpBasePageRespVO<Map> pageRespVo = null;

		try {// 查询
			PageResponseDTO<GdsLabelRespDTO> pageRespDto = iGdsLabelRSV
					.queryGdsLabelRespDTOPaging(reqDto);
			if (pageRespDto.getResult() == null) {
				pageRespDto.setResult(new ArrayList<GdsLabelRespDTO>());
			}
			// 转化标签编码为中文
			for (GdsLabelRespDTO respDto : pageRespDto.getResult()) {
				respDto.setLabelType(BaseParamUtil.fetchParamValue(
						"GDS_LABEL_TYPE", respDto.getLabelType()));
			}
			// 将DTO 转化为 VO 用于前台显示
			pageRespVo = EcpBasePageRespVO.buildByPageResponseDTO(pageRespDto);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.info(MODULE, "获取标签列表失败！", e);
		}

		return super.addPageToModel(model, pageRespVo);
	}// end of method gridList

	/**
	 * 
	 * labelAdd:(跳到添加媒体页面). <br/>
	 * 
	 * @author zhanbh 2015.9.17
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/labeladd")
	private String labelAdd() {
		return URL + "/open/label-add";
	}// end of method labelAdd

	/**
	 * 
	 * saveLabel:(添加标签). <br/>
	 * 
	 * @author zhanbh
	 * @param GdsLabelVO
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/savelabel")
	@ResponseBody
	private EcpBaseResponseVO saveLabel(@Valid GdsLabelVO reqVo) {
		// 将 VO 转为 DTO 获取页面参数
		GdsLabelReqDTO reqDto = reqVo.toBaseInfo(GdsLabelReqDTO.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		try {
			iGdsLabelRSV.saveGdsLabel(reqDto);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				respVo.setResultMsg(be.getErrorMessage());
			} else {
				respVo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "报错了啦", be);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			return respVo;
		}
		return respVo;
	}// end of method saveLabel

	/**
	 * 
	 * labelEdit:(跳转到编辑页面). <br/>
	 * 
	 * @author zhanbh
	 * @param GdsLabelVO
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/labeledit")
	private String labelEdit(Model model, GdsLabelVO reqVo) {
		// 将VO转化为DTO 获取页面参数
		GdsLabelReqDTO reqDto = reqVo.toBaseInfo(GdsLabelReqDTO.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
		// 这些参数不能为空
		reqDto.setPageNo(1);
		reqDto.setPageSize(1);

		GdsLabelRespDTO respDto = null;
		PageResponseDTO<GdsLabelRespDTO> pageRespDto = null;
		try {
			pageRespDto = iGdsLabelRSV.queryGdsLabelRespDTOPaging(reqDto);
			respDto = pageRespDto.getResult().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.info(MODULE, "获取标签失败！", e);
		}
		model.addAttribute("labelInfo", respDto);
		return URL + "/open/label-edit";
	}// end of method laberEdit

	/**
	 * 
	 * switchStat:(修改标签转态). <br/>
	 * 
	 * @author zhanbh
	 * @param GdsLabelVO
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/switchstat")
	@ResponseBody
	private EcpBaseResponseVO switchStat(GdsLabelVO reqVo) {
		// 将VO转化为DTO 获取页面参数
		GdsLabelReqDTO reqDto = reqVo.toBaseInfo(GdsLabelReqDTO.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);

		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		try {
			if ("0".equals(reqDto.getStatus())) {// 启用
				iGdsLabelRSV.executeEnableGdsLabel(reqDto);
			} else {
				iGdsLabelRSV.executeDisableGdsLabel(reqDto);
			}
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				respVo.setResultMsg(be.getErrorMessage());
			} else {
				respVo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "报错了啦", be);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			return respVo;
		}

		return respVo;
	}// end of method laberEdit

	/**
	 * saveEditLabel:(添加标签). <br/>
	 * 
	 * @author zhanbh
	 * @param GdsLabelVO
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/saveeditlabel")
	@ResponseBody
	private EcpBaseResponseVO saveEditLabel(@Valid GdsLabelVO reqVo) {
		// 将 VO 转为 DTO 获取页面参数
		GdsLabelReqDTO reqDto = reqVo.toBaseInfo(GdsLabelReqDTO.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);

		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		try {
			iGdsLabelRSV.editGdsLabel(reqDto);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				respVo.setResultMsg(be.getErrorMessage());
			} else {
				respVo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "报错了啦", be);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			return respVo;
		}
		return respVo;
	}// end of method saveEditLabel

	@RequestMapping(value = "batchoper")
	@ResponseBody
	private EcpBaseResponseVO batchOper(GdsLabelVO reqVo) {
		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		String[] strIds = new String[0];
		long[] ids = new long[0];// 待操作id数组
		int operCount = 0; // 操作成功条数累计
		String flag = reqVo.getFlag();
		// 将 VO 转为 DTO 获取页面参数
		GdsLabelReqDTO reqDto = reqVo.toBaseInfo(GdsLabelReqDTO.class);
		// 获取批量操作对应收藏ID串
		String operateId = reqVo.getOperateId();
		// 将ID串分割为字符串数组
		if (StringUtil.isBlank(operateId)) {
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			return respVo;
		} else {
			strIds = operateId.split(",");
		}
		// 将ID字符串数组转化为long类型数组
		if (strIds.length == 0) {
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			return respVo;
		} else {
			// 将ID串分割为长整型数组
			ids = new long[strIds.length];
			int i = 0;
			for (String id : strIds) {
				ids[i] = Long.parseLong(id);
				i++;
			}// end of for
		}

		// 批量操作
		try {
			if ("0".equals(flag)) {// 禁用
				for (long id : ids) {
					reqDto.setId(id);
					operCount += iGdsLabelRSV.executeDisableGdsLabel(reqDto);
				}
			} else if ("1".equals(flag)) {// 启用
				for (long id : ids) {
					reqDto.setId(id);
					operCount += iGdsLabelRSV.executeEnableGdsLabel(reqDto);
				}
			}// end of if

			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				respVo.setResultMsg(be.getErrorMessage());
			} else {
				respVo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "报错了啦", be);
			if (operCount > 0) {
				respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			} else {
				respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			}
			return respVo;
		}
		// 判断操作成功条数
		if (operCount == 0) {
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		} else if (operCount < ids.length) {
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
		} else {
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		}
		return respVo;
	}// end of method batchOper
}// end of class GdsLabelController
