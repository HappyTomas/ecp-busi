package com.zengshi.ecp.busi.staff.buyer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.buyer.vo.FavGoodsVO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 买家 我的收藏 查询收藏 删除收藏<br>
 * Date:2015年9月22日上午09:45:00 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "favgoods")
public class FavGoodsController extends EcpBaseController {

	private String MODULE = FavGoodsController.class.getName();
	private static String URL = "/staff/buyer";

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
	public String init(Model model, FavGoodsVO reqVo) {
		//初始化分页
		reqVo.setPageNumber(1);
		reqVo.setPageSize(5);;
		
		GdsCollectReqDTO reqDto = reqVo.toBaseInfo(GdsCollectReqDTO.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
		EcpBasePageRespVO<Map> pageRespVo = null;
		PageResponseDTO<GdsCollectRespDTO> pageRespDto = null;
		// 查询数据
		pageRespDto = getCollData(reqDto);
		
		// 查询出错处理
		if (pageRespDto == null) {
			String msg = "获取关注商品列表失败，稍后再来！";
			model.addAttribute("msg", msg);
			pageRespVo = new EcpBasePageRespVO<Map>();
			pageRespVo.setPageNumber(0);
			pageRespVo.setPageSize(reqVo.getPageSize());
			pageRespVo.setTotalPage(0);
			pageRespVo.setTotalRow(0);
			super.addPageToModel(model, pageRespVo);
		} else {

			// 如果返回空集 创建一个
			if (pageRespDto.getResult() == null) {
				pageRespDto.setResult(new ArrayList<GdsCollectRespDTO>());
			} else {
				// 转化参数 增加reduce字段值
				ArrayList<Long> reduceList = new ArrayList<Long>();
				for (GdsCollectRespDTO respDto : pageRespDto.getResult()) {
					if (respDto.getGdsPrice() == null) {
						respDto.setGdsPrice(0l);
					}
					if (respDto.getNowPrice() == null) {
						respDto.setNowPrice(0l);
					}
					reduceList.add(respDto.getGdsPrice()
							- respDto.getNowPrice());
				}

				model.addAttribute("reduceList", reduceList);
			}
			// 将DTO转化为页面参数对象VO
			try {
				pageRespVo = EcpBasePageRespVO
						.buildByPageResponseDTO(pageRespDto);
			} catch (Exception e) {
				String msg = "数据转换不成功，请联系管理员！";
				
				pageRespVo = new EcpBasePageRespVO<Map>();
				pageRespVo.setPageNumber(0);
				pageRespVo.setPageSize(reqVo.getPageSize());
				pageRespVo.setTotalPage(0);
				pageRespVo.setTotalRow(0);
				
				model.addAttribute("msg", msg);
				super.addPageToModel(model, pageRespVo);
			}
			super.addPageToModel(model, pageRespVo);
		}

		return URL + "/favGoods/member-favGoods";
	}// end of method init

	/**
	 * 
	 * gridList:(买家我的收藏列表获取). <br/>
	 * 
	 * @author zhanbh 2015.9.22
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "gridlist")
	public String gridList(Model model, FavGoodsVO reqVo) {
		// 将页面参数对象VO转化为DTO
		GdsCollectReqDTO reqDto = reqVo.toBaseInfo(GdsCollectReqDTO.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
		EcpBasePageRespVO<Map> pageRespVo = null;
		reqDto.setStaffId(reqDto.getStaff().getId());
		PageResponseDTO<GdsCollectRespDTO> pageRespDto = null;

		// 查询数据
		pageRespDto = getCollData(reqDto);
		// 查询出错处理
		if (pageRespDto == null) {
			String msg = "获取关注商品列表失败，稍后再来！";
			model.addAttribute("msg", msg);
			pageRespVo = new EcpBasePageRespVO<Map>();
			pageRespVo.setPageNumber(0);
			pageRespVo.setPageSize(reqVo.getPageSize());
			pageRespVo.setTotalPage(0);
			pageRespVo.setTotalRow(0);
			super.addPageToModel(model, pageRespVo);
		} else {

			// 如果返回空集 创建一个
			if (pageRespDto.getResult() == null) {
				pageRespDto.setResult(new ArrayList<GdsCollectRespDTO>());
			} else {
				// 转化参数 增加reduce字段值
				ArrayList<Long> reduceList = new ArrayList<Long>();
				for (GdsCollectRespDTO respDto : pageRespDto.getResult()) {
					if (respDto.getGdsPrice() == null) {
						respDto.setGdsPrice(0l);
					}
					if (respDto.getNowPrice() == null) {
						respDto.setNowPrice(0l);
					}
					reduceList.add(respDto.getGdsPrice()
							- respDto.getNowPrice());
				}

				model.addAttribute("reduceList", reduceList);
			}
			// 将DTO转化为页面参数对象VO
			try {
				pageRespVo = EcpBasePageRespVO
						.buildByPageResponseDTO(pageRespDto);
			} catch (Exception e) {
				String msg = "数据转换不成功，请联系管理员！";
				model.addAttribute("msg", msg);
				pageRespVo = new EcpBasePageRespVO<Map>();
				pageRespVo.setPageNumber(0);
				pageRespVo.setPageSize(reqVo.getPageSize());
				pageRespVo.setTotalPage(0);
				pageRespVo.setTotalRow(0);
				super.addPageToModel(model, pageRespVo);
			}
			super.addPageToModel(model, pageRespVo);
		}

		return URL + "/favGoods/div/favGoodsDiv";
	}// end of method gridList

	
	
	/**
	 * getCollData:获取收藏商品数据
	 * 
	 * @author zhanbh 2015.10.16
	 * @since JDK 1.6 2015.9.11
	 */
	private PageResponseDTO<GdsCollectRespDTO> getCollData(
			GdsCollectReqDTO reqDto) {

		PageResponseDTO<GdsCollectRespDTO> pageRespDto = null;
		try {
			// 查询数据
			pageRespDto = iGdsCollectRSV.queryGdsCollectRespDTOPagingByStaff(reqDto);
			
			if(CollectionUtils.isNotEmpty(pageRespDto.getResult())){
				List<GdsCollectRespDTO> result=pageRespDto.getResult();
				for (GdsCollectRespDTO gdsCollectRespDTO : result) {
					String imageUrl=getImageUrl(gdsCollectRespDTO.getSkuMainPic(),"70x70!");
					gdsCollectRespDTO.setSkuMainPic(imageUrl);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.info(MODULE, "获取买家我的收藏列表失败！", e);
			pageRespDto = null;
		}

		return pageRespDto;
	}// end of method getCollData

	/**
	 * 
	 * add:(买家添加收藏). <br/>
	 * 
	 * @author zhanbh 2015.10.30
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public EcpBaseResponseVO add(FavGoodsVO reqVo) {
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
	public EcpBaseResponseVO collRemove(FavGoodsVO reqVo) {
		// 转化页面参数对象VO成DTO
		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		GdsCollectReqDTO reqDto = reqVo.toBaseInfo(GdsCollectReqDTO.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
		// 设置额外参数
		reqDto.setStaffId(reqDto.getStaff().getId());
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
	public EcpBaseResponseVO collBatchRemove(Model model, FavGoodsVO reqVo) {
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
     * 根据上传到mongoDB的图片ID 从mongoDB上获取图片路径
     * @param vfsId
     * @param param
     * @return
     * @author gxq
     */
    private String getImageUrl(String vfsId,String param){
        StringBuilder sb=new StringBuilder();
        sb.append(vfsId);
        if(!StringUtil.isBlank(param)){
            sb.append("_");
            sb.append(param);
        }
        return ImageUtil.getImageUrl(sb.toString());
    }
}// end of class GdsCollStaffController

