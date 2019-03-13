package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsListReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsListRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsListRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsListSV;
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
 * @author huangxm9
 * @version
 * @since JDK 1.6
 */
public class CmsListRSVImpl implements ICmsListRSV {

	@Resource
	private ICmsListSV sv;

	// 定义个常量，用于表示模块名称，可以使用：当前类的类名
	private static final String MODULE = CmsListRSVImpl.class.getName();

	/**
	 * TODO 新增排行榜.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsListRSV#saveCmsList(com.zengshi.ecp.cms.dubbo.dto.CmsListReqDTO)
	 */
	@Override
	public CmsListRespDTO addCmsList(CmsListReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用新增排行榜开始，入参：{dto=" + dto.toString() + "}");
		CmsListRespDTO respDTO = new CmsListRespDTO();
		/* 1.验证前店入参 */
		if (StringUtil.isEmpty(dto.getGdsId())) {
			LogUtil.error(MODULE, "入参GdsId为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "GdsId";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isBlank(dto.getListClass())) {
			LogUtil.error(MODULE, "入参listClass为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "listClass";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		/* 2.调service层接口 */
		try {
			respDTO = sv.addCmsList(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "新增排行榜失败:", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_LIST_500101);
		}

		return respDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsListRSV#updateCmsList(com.zengshi.ecp.cms.dubbo.dto.CmsListReqDTO)
	 */
	@Override
	public CmsListRespDTO updateCmsList(CmsListReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用更新排行榜开始，入参：{dto=" + dto.toString() + "}");
		CmsListRespDTO respDTO = new CmsListRespDTO();
		/* 1.验证前店入参 */
		if (StringUtil.isEmpty(dto.getId())) {
			LogUtil.error(MODULE, "入参Id为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "Id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isEmpty(dto.getGdsId())) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.调service层接口 */
		try {
			respDTO = sv.updateCmsList(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "更新排行榜失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_LIST_500102);
		}

		return respDTO;
	}

	/**
	 * TODO 查询排行榜，分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsListRSV#queryCmsListPage(com.zengshi.ecp.cms.dubbo.dto.CmsListReqDTO)
	 */
	@Override
	public PageResponseDTO<CmsListRespDTO> queryCmsListPage(CmsListReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用分页查询排行榜开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */

		/* 2.根据条件检索排行榜 */
		PageResponseDTO<CmsListRespDTO> pageInfo = PageResponseDTO
				.buildByBaseInfo(dto, CmsListRespDTO.class);
		try {
			pageInfo = sv.queryCmsListPage(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "查询排行榜失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_LIST_500103);
		}
		return pageInfo;
	}

	/**
	 * TODO 查询排行榜 无分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO)
	 */
	@Override
	public List<CmsListRespDTO> queryCmsListList(CmsListReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用查询排行榜开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */

		/* 2.根据条件检索排行榜 */
		List<CmsListRespDTO> list = new ArrayList<CmsListRespDTO>();
		try {
			list = sv.queryCmsListList(dto);
		} catch (Exception e) {
			LogUtil.error(MODULE, "查询排行榜出现异常！", e);
			throw new BusinessException(CmsConstants.MsgCode.CMS_LIST_500103);
		}

		return list;
	}

	/**
	 * TODO 查询单个排行榜（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsListRSV#queryCmsList(com.zengshi.ecp.cms.dubbo.dto.CmsListReqDTO)
	 */
	@Override
	public CmsListRespDTO queryCmsList(CmsListReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用查询排行榜开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */
		if (StringUtil.isEmpty(dto.getId())) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.根据条件检索排行榜 */
		CmsListRespDTO cmsListRespDTO = new CmsListRespDTO();
		try {
			cmsListRespDTO = sv.queryCmsList(dto);
		} catch (Exception e) {
			LogUtil.error(MODULE, "根据ID查询排行榜出现异常！", e);
			throw new BusinessException(CmsConstants.MsgCode.CMS_LIST_500103);
		}

		return cmsListRespDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsListRSV#deleteCmsList(java.lang.Long)
	 */
	@Override
	public void deleteCmsList(String id) throws BusinessException {
		LogUtil.info(MODULE, "调用删除排行榜开始，入参：{id=" + id + "}");
		/* 1.验证前店入参 */
		if (StringUtil.isBlank(id)) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.逻辑删除排行榜 */
		try {
			sv.deleteCmsList(id);
		} catch (Exception err) {
			LogUtil.error(MODULE, "删除排行榜失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_LIST_500101);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsListRSV#deleteCmsListBatch(java.util.List)
	 */
	@Override
	public void deleteCmsListBatch(List<String> list) throws BusinessException {
		LogUtil.info(MODULE, "调用批量删除排行榜开始，入参：{list=" + list.toArray() + "}");
		/* 1.验证前店入参 */
		if (CollectionUtils.isEmpty(list)) {
			LogUtil.error(MODULE, "入参list为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "list";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.逻辑批量删除排行榜 */
		try {
			sv.deleteCmsListBatch(list);
		} catch (Exception err) {
			LogUtil.error(MODULE, "批量删除排行榜失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_LIST_500104);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsListRSV#changeStatusCmsList(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsList(String id, String status)
			throws BusinessException {
		LogUtil.info(MODULE, "调用批量删除排行榜开始，入参：{id=" + id + ",status=" + status
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
		/* 2.更新排行榜状态 */
		try {
			sv.changeStatusCmsList(id, status);
		} catch (Exception err) {
			LogUtil.error(MODULE, "更新排行榜状态失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_LIST_500106);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsListRSV#changeStatusCmsListBatch(java.util.List,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsListBatch(List<String> list, String status)
			throws BusinessException {
		LogUtil.info(MODULE, "调用批量删除排行榜开始，入参：{list=" + list.toArray()
				+ ",status=" + status + "}");
		/* 1.验证前店入参 */
		if (CollectionUtils.isEmpty(list)) {
			LogUtil.error(MODULE, "入参list为空！");
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

		/* 2.更新排行榜状态 */
		try {
			sv.changeStatusCmsListBatch(list, status);
		} catch (Exception err) {
			LogUtil.error(MODULE, "批量更新排行榜状态失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_LIST_500107);
		}
	}
}
