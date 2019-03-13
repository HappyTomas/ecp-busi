package com.zengshi.ecp.busi.goods.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.common.GdsBaseController;
import com.zengshi.ecp.busi.goods.vo.GdsCollectionVO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 买家 我的收藏 查询收藏 删除收藏<br>
 * Date:2015年9月11日上午09:45:00 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "gdscollstaff")
public class GdsCollStaffController extends GdsBaseController {

	private String MODULE = GdsCollStaffController.class.getName();
	private static String URL = "/goods/gdsCollStaff";

	@Resource
	private IGdsCollectRSV iGdsCollectRSV;

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
	 * gridList:(买家我的收藏列表获取). <br/>
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
		// reqDto.setStaffId(1234l);
		// reqDto.setGdsId(134l);
		// reqDto.setSkuId(124l);
		// reqDto.setShopId(1234l);
		// reqDto.setGdsName("测试ghj2");
		// reqDto.setGdsPrice(12l);
		// reqDto.setStatus("1");
		PageResponseDTO<GdsCollectRespDTO> pageRespDto = null;
		try {
			// 先插入几个数据做测试
			// iGdsCollectRSV.addGdsCollect(reqDto);
			// 查询数据
			pageRespDto = iGdsCollectRSV
					.queryGdsCollectRespDTOPagingByStaff(reqDto);
			// 如果返回空集 创建一个
			if (pageRespDto.getResult() == null) {
				pageRespDto.setResult(new ArrayList<GdsCollectRespDTO>());
			}
			// 将DTO转化为页面参数对象VO
			pageRespVO = EcpBasePageRespVO.buildByPageResponseDTO(pageRespDto);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.info(MODULE, "获取买家我的收藏列表失败！", e);
		}

		return super.addPageToModel(model, pageRespVO);
	}// end of method gridList

	/**
	 * 
	 * collRemove:(删除单条收藏记录). <br/>
	 * 
	 * @author zhanbh
	 * @param GdsCollectionVO
	 * @return EcpBaseResponseVO
	 * @since JDK 1.6 2015.9.11
	 */
	@RequestMapping(value = "collremove")
	@ResponseBody
	public EcpBaseResponseVO collRemove(GdsCollectionVO reqVo) {
		// 转化页面参数对象VO成DTO
		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		GdsCollectReqDTO reqDto = reqVo.toBaseInfo(GdsCollectReqDTO.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
		// 设置额外参数
		// reqDto.setStaffId(1234l);
		try {
			int delete = iGdsCollectRSV.deleteGdsCollectByPK(reqDto);
			if (delete > 0) {
				respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
			} else {
				respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			}

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
	}// end of method collRemove

	/**
	 * 
	 * collBatchRemove:(批量删除单条收藏记录). <br/>
	 * 
	 * @author zhanbh
	 * @param GdsCollectionVO
	 * @return EcpBaseResponseVO
	 * @since JDK 1.6 2015.9.11
	 */
	@RequestMapping(value = "/collbatchremove")
	@ResponseBody
	public EcpBaseResponseVO collBatchRemove(Model model, GdsCollectionVO reqVo) {
		GdsCollectReqDTO reqDto = reqVo.toBaseInfo(GdsCollectReqDTO.class);
		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		String[] strIds = new String[0];
		int delCount = 0; // 删除条数累计
		long[] ids = new long[0];

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

		// 批量删除
		try {
			for (long id : ids) {
				reqDto.setId(id);
				delCount += iGdsCollectRSV.deleteGdsCollectByPK(reqDto);
			}

		} catch (Exception e) {
			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				respVo.setResultMsg(be.getErrorMessage());
			} else {
				respVo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "报错了啦", be);
			if (delCount > 0) {
				respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			} else {
				respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			}
			return respVo;
		}
		// 判断是否删除成功
		if (delCount == 0) {
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		} else if (delCount < ids.length) {
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
		} else {
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		}
		return respVo;
	}// end of method collBatchRemove

	/**
	 * add:(新增收藏). <br/>
	 * 
	 * @author linwb3
	 * @param gdsVo
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public EcpBaseResponseVO add(GdsCollectionVO reqVo) {
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		try {
			GdsCollectReqDTO dto = new GdsCollectReqDTO();
			ObjectCopyUtil.copyObjValue(reqVo, dto, null, true);
			iGdsCollectRSV.addGdsCollect(dto);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				vo.setResultMsg(be.getErrorMessage());
			} else {
				vo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "添加收藏错误！！", be);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		}
		return vo;
	}

}// end of class GdsCollStaffController
