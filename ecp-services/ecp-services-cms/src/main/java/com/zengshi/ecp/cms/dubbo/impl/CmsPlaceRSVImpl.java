package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015年8月7日下午5:11:10 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version
 * @since JDK 1.6
 */
public class CmsPlaceRSVImpl implements ICmsPlaceRSV {

	@Resource
	private ICmsPlaceSV sv;

	// 定义个常量，用于表示模块名称，可以使用：当前类的类名
	private static final String MODULE = CmsPlaceRSVImpl.class.getName();

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#addCmsPlace(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO)
	 */
	@Override
	public CmsPlaceRespDTO addCmsPlace(CmsPlaceReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用内容位置开始，入参：{dto=" + dto.toString() + "}");
		CmsPlaceRespDTO respDTO = new CmsPlaceRespDTO();
		/* 1.验证前店入参 */
		if (StringUtil.isBlank(dto.getPlaceName())) {
			LogUtil.error(MODULE, "入参placeName为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "placeName";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isEmpty(dto.getSiteId())) {
			LogUtil.error(MODULE, "入参SiteId为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "SiteId";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isEmpty(dto.getTemplateId())) {
			LogUtil.error(MODULE, "入参TemplateId为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "TemplateId";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isBlank(dto.getPlaceType())) {
			LogUtil.error(MODULE, "入参PlaceType为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "PlaceType";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		/* 2.调service层接口 */
		try {
			respDTO = sv.addCmsPlace(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "新增内容位置失败:", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_PLACE_500101);
		}

		return respDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#updateCmsPlace(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO)
	 */
	@Override
	public CmsPlaceRespDTO updateCmsPlace(CmsPlaceReqDTO dto) {
		LogUtil.info(MODULE, "调用更新内容位置开始，入参：{dto=" + dto.toString() + "}");
		CmsPlaceRespDTO respDTO = new CmsPlaceRespDTO();
		/* 1.验证前店入参 */
		if (StringUtil.isEmpty(dto.getId())) {
			LogUtil.error(MODULE, "入参Id为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "Id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isBlank(dto.getPlaceName())) {
			LogUtil.error(MODULE, "入参placeName为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "placeName";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isEmpty(dto.getSiteId())) {
			LogUtil.error(MODULE, "入参SiteId为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "SiteId";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isEmpty(dto.getTemplateId())) {
			LogUtil.error(MODULE, "入参TemplateId为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "TemplateId";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isBlank(dto.getPlaceType())) {
			LogUtil.error(MODULE, "入参PlaceType为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "PlaceType";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.调service层接口 */
		try {
			respDTO = sv.updateCmsPlace(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "更新内容位置失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_PLACE_500102);
		}

		return respDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#deleteCmsPlace(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO)
	 */
	@Override
	public void deleteCmsPlace(String id) throws BusinessException {
		LogUtil.info(MODULE, "调用删除内容位置开始，入参：{id=" + id + "}");
		/* 1.验证前店入参 */
		if (StringUtil.isBlank(id)) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.逻辑删除内容位置 */
		try {
			sv.deleteCmsPlace(id);
		} catch (Exception err) {
			LogUtil.error(MODULE, "删除内容位置失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_PLACE_500101);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV#deleteCmsAdvertiseBatch(java.util.List)
	 */
	@Override
	public void deleteCmsPlaceBatch(List<String> list) throws BusinessException {
		LogUtil.info(MODULE, "调用批量删除楼层开始，入参：{list=" + list.toArray() + "}");
		/* 1.验证前店入参 */
		if (CollectionUtils.isEmpty(list)) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "list";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.逻辑批量删除内容位置 */
		try {
			sv.deleteCmsPlaceBatch(list);
		} catch (Exception err) {
			LogUtil.error(MODULE, "批量删除楼层失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_PLACE_500104);
		}
	}

	/**
	 * TODO 查询单个内容位置（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlace(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO)
	 */
	@Override
	public CmsPlaceRespDTO queryCmsPlace(CmsPlaceReqDTO dto)
			throws BusinessException {

		LogUtil.info(MODULE, "调用查询内容位置开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */
		if (StringUtil.isEmpty(dto.getId())) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.根据条件检索内容位置 */
		CmsPlaceRespDTO cmsPlaceRespDTO = new CmsPlaceRespDTO();
		try {
			cmsPlaceRespDTO = sv.queryCmsPlace(dto);
		} catch (Exception e) {
			LogUtil.error(MODULE, "查询内容位置出现异常！", e);
			throw new BusinessException(CmsConstants.MsgCode.CMS_PLACE_500103);
		}

		return cmsPlaceRespDTO;
	}

	/**
	 * TODO 查询内容位置 无分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO)
	 */
	@Override
	public List<CmsPlaceRespDTO> queryCmsPlaceList(CmsPlaceReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用查询内容位置开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */

		/* 2.根据条件检索内容位置 */
		List<CmsPlaceRespDTO> cmsPlaceRespDTOList = new ArrayList<CmsPlaceRespDTO>();
		try {
			cmsPlaceRespDTOList = sv.queryCmsPlaceList(dto);
		} catch (Exception e) {
			LogUtil.error(MODULE, "查询内容位置出现异常！", e);
			throw new BusinessException(CmsConstants.MsgCode.CMS_PLACE_500103);
		}

		return cmsPlaceRespDTOList;
	}

	/**
	 * TODO 查询内容位置，分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV#queryCmsAdvertisePage(com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO)
	 */
	@Override
	public PageResponseDTO<CmsPlaceRespDTO> queryCmsPlacePage(CmsPlaceReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用查询内容位置开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */

		/* 2.根据条件检索内容位置 */
		PageResponseDTO<CmsPlaceRespDTO> pageInfo = PageResponseDTO
				.buildByBaseInfo(dto, CmsPlaceRespDTO.class);
		try {
			pageInfo = sv.queryCmsPlacePage(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "查询内容位置失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_PLACE_500103);
		}
		return pageInfo;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV#changeStatusCmsFloor(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsPlace(String id, String status)
			throws BusinessException {
		LogUtil.info(MODULE, "调用批量删除楼层开始，入参：{id=" + id + ",status=" + status
				+ "}");
		/* 1.验证前店入参 */
		if (StringUtil.isBlank(id)) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isBlank(status)) {
			LogUtil.error(MODULE, "入参status为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "status";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		/* 2.更新楼层状态 */
		try {
			sv.changeStatusCmsPlace(id, status);
		} catch (Exception err) {
			LogUtil.error(MODULE, "更新楼层状态失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_FLOOR_500106);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV#changeStatusCmsFloorBatch(java.util.List,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsPlaceBatch(List<String> list, String status)
			throws BusinessException {
		LogUtil.info(MODULE, "调用批量删除楼层开始，入参：{list=" + list.toArray()
				+ ",status=" + status + "}");
		/* 1.验证前店入参 */
		if (CollectionUtils.isEmpty(list)) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "list";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isBlank(status)) {
			LogUtil.error(MODULE, "入参status为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "status";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.更新楼层状态 */
		try {
			sv.changeStatusCmsPlaceBatch(list, status);
		} catch (Exception err) {
			LogUtil.error(MODULE, "批量更新楼层状态失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_FLOOR_500107);
		}
	}

}
