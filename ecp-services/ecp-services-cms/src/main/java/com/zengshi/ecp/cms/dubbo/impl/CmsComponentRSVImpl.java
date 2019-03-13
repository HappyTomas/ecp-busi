package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsComponentReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsComponentRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsComponentRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsComponentSV;
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
public class CmsComponentRSVImpl implements ICmsComponentRSV {

	@Resource
	private ICmsComponentSV sv;
	@Resource
	private ICmsPlaceSV cmsPlaceSV;

	// 定义个常量，用于表示模块名称，可以使用：当前类的类名
	private static final String MODULE = CmsComponentRSVImpl.class.getName();

	/**
	 * TODO 新增组件.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsComponentRSV#saveCmsComponent(com.zengshi.ecp.cms.dubbo.dto.CmsComponentReqDTO)
	 */
	@Override
	public CmsComponentRespDTO addCmsComponent(CmsComponentReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用新增组件开始，入参：{dto=" + dto.toString() + "}");
		CmsComponentRespDTO respDTO = new CmsComponentRespDTO();
		/* 1.验证前店入参 */
		if (StringUtil.isBlank(dto.getComponentName())) {
			LogUtil.error(MODULE, "入参ComponentName为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "ComponentName";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isBlank(dto.getComponentClass())) {
			LogUtil.error(MODULE, "入参ComponentClass为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "ComponentClass";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.调service层接口 */
		try {
			respDTO = sv.addCmsComponent(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "新增组件失败:", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_COMPONENT_500101);
		}

		return respDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsComponentRSV#updateCmsComponent(com.zengshi.ecp.cms.dubbo.dto.CmsComponentReqDTO)
	 */
	@Override
	public CmsComponentRespDTO updateCmsComponent(CmsComponentReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用更新组件开始，入参：{dto=" + dto.toString() + "}");
		CmsComponentRespDTO respDTO = new CmsComponentRespDTO();
		/* 1.验证前店入参 */
		if (StringUtil.isEmpty(dto.getId())) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isBlank(dto.getComponentName())) {
			LogUtil.error(MODULE, "入参ComponentName为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "ComponentName";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isBlank(dto.getComponentClass())) {
			LogUtil.error(MODULE, "入参ComponentClass为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "ComponentClass";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.调service层接口 */
		try {
			respDTO = sv.updateCmsComponent(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "更新组件失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_COMPONENT_500102);
		}

		return respDTO;
	}

	/**
	 * TODO 查询组件，分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsComponentRSV#queryCmsComponentPage(com.zengshi.ecp.cms.dubbo.dto.CmsComponentReqDTO)
	 */
	@Override
	public PageResponseDTO<CmsComponentRespDTO> queryCmsComponentPage(
			CmsComponentReqDTO dto) throws BusinessException {
		LogUtil.info(MODULE, "调用分页查询组件开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */

		/* 2.根据条件检索组件 */
		PageResponseDTO<CmsComponentRespDTO> pageInfo = PageResponseDTO
				.buildByBaseInfo(dto, CmsComponentRespDTO.class);
		try {
			pageInfo = sv.queryCmsComponentPage(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "查询组件失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_COMPONENT_500103);
		}
		return pageInfo;
	}

	/**
	 * TODO 查询组件 无分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO)
	 */
	@Override
	public List<CmsComponentRespDTO> queryCmsComponentList(
			CmsComponentReqDTO dto) throws BusinessException {
		LogUtil.info(MODULE, "调用查询组件开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */

		/* 2.根据条件检索组件 */
		List<CmsComponentRespDTO> list = new ArrayList<CmsComponentRespDTO>();
		try {
			list = sv.queryCmsComponentList(dto);
		} catch (Exception e) {
			LogUtil.error(MODULE, "查询组件出现异常！", e);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_COMPONENT_500103);
		}

		return list;
	}

	/**
	 * TODO 查询单个组件（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsComponentRSV#queryCmsComponent(com.zengshi.ecp.cms.dubbo.dto.CmsComponentReqDTO)
	 */
	@Override
	public CmsComponentRespDTO queryCmsComponent(CmsComponentReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用查询组件开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */
		if (StringUtil.isEmpty(dto.getId())) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.根据条件检索组件 */
		CmsComponentRespDTO cmsComponentRespDTO = new CmsComponentRespDTO();
		try {
			cmsComponentRespDTO = sv.queryCmsComponent(dto);
		} catch (Exception e) {
			LogUtil.error(MODULE, "根据ID查询组件出现异常！", e);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_COMPONENT_500103);
		}

		return cmsComponentRespDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsComponentRSV#deleteCmsComponent(java.lang.Long)
	 */
	@Override
	public void deleteCmsComponent(String id) throws BusinessException {
		LogUtil.info(MODULE, "调用删除组件开始，入参：{id=" + id + "}");
		/* 1.验证前店入参 */
		if (StringUtil.isBlank(id)) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.逻辑删除组件 */
		try {
			sv.deleteCmsComponent(id);
		} catch (Exception err) {
			LogUtil.error(MODULE, "删除组件失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_COMPONENT_500101);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsComponentRSV#deleteCmsComponentBatch(java.util.List)
	 */
	@Override
	public void deleteCmsComponentBatch(List<String> list)
			throws BusinessException {
		LogUtil.info(MODULE, "调用批量删除组件开始，入参：{list=" + list.toArray() + "}");
		/* 1.验证前店入参 */
		if (CollectionUtils.isEmpty(list)) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "list";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.逻辑批量删除组件 */
		try {
			sv.deleteCmsComponentBatch(list);
		} catch (Exception err) {
			LogUtil.error(MODULE, "批量删除组件失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_COMPONENT_500104);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsComponentRSV#changeStatusCmsComponent(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsComponent(String id, String status)
			throws BusinessException {
		LogUtil.info(MODULE, "调用批量删除组件开始，入参：{id=" + id + ",status=" + status
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
		/* 2.更新组件状态 */
		try {
			sv.changeStatusCmsComponent(id, status);
		} catch (Exception err) {
			LogUtil.error(MODULE, "更新组件状态失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_COMPONENT_500106);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsComponentRSV#changeStatusCmsComponentBatch(java.util.List,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsComponentBatch(List<String> list, String status)
			throws BusinessException {
		LogUtil.info(MODULE, "调用批量删除组件开始，入参：{list=" + list.toArray()
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

		/* 2.更新组件状态 */
		try {
			sv.changeStatusCmsComponentBatch(list, status);
		} catch (Exception err) {
			LogUtil.error(MODULE, "批量更新组件状态失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_COMPONENT_500107);
		}
	}

}
