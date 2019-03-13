package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsRecommendReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsRecommendRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsRecommendRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsRecommendSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class CmsRecommendRSVImpl implements ICmsRecommendRSV {

	@Resource
	private ICmsRecommendSV sv;

	// 定义个常量，用于表示模块名称，可以使用：当前类的类名
	private static final String MODULE = CmsRecommendRSVImpl.class.getName();

	/**
	 * TODO 新增专家推荐.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsRecommendRSV#saveCmsExpertRecommend(com.zengshi.ecp.cms.dubbo.dto.CmsRecommendReqDTO)
	 */
	@Override
	public CmsRecommendRespDTO addCmsRecommend(CmsRecommendReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用新增专家推荐开始，入参：{dto=" + dto.toString() + "}");
		CmsRecommendRespDTO respDTO = new CmsRecommendRespDTO();
		/* 1.验证前店入参 */
		// if (dto.getRecommendType() == null)
		if (StringUtil.isEmpty(dto.getRecommendType())) {
			LogUtil.error(MODULE, "入参recommendType为空");
			String[] keyInfos = new String[1];
			keyInfos[0] = "advertiseTitle";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isBlank(dto.getAuthorName())) {
			LogUtil.error(MODULE, "入参authorName为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "authorName";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isBlank(dto.getAuthorImage())) {
			LogUtil.error(MODULE, "入参authorImage为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "authorImage";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isBlank(dto.getAuthorIntroduction())) {
			LogUtil.error(MODULE, "入参authorIntroduction为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "authorIntroduction";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		// if (dto.getRecommendGdsId() == null)
		if (StringUtil.isEmpty(dto.getRecommendGdsId())) {
			LogUtil.error(MODULE, "入参recommendGdsId为空");
			String[] keyInfos = new String[1];
			keyInfos[0] = "recommendGdsId";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.调service层接口 */
		try {
			respDTO = sv.addCmsRecommend(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "新增专家推荐失败:", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_RECOMMEND_500101);
		}
		return respDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsRecommendRSV#updateCmsExpertRecommend(com.zengshi.ecp.cms.dubbo.dto.CmsRecommendReqDTO)
	 */
	@Override
	public CmsRecommendRespDTO updateCmsRecommend(CmsRecommendReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用更新专家推荐开始，入参：{dto=" + dto.toString() + "}");
		CmsRecommendRespDTO respDTO = new CmsRecommendRespDTO();
		/* 1.验证前店入参 */
		if (StringUtil.isEmpty(dto.getId())) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isEmpty(dto.getRecommendType())) {
			LogUtil.error(MODULE, "入参recommendType为空");
			String[] keyInfos = new String[1];
			keyInfos[0] = "recommendType";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isBlank(dto.getAuthorName())) {
			LogUtil.error(MODULE, "入参authorName为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "authorName";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isBlank(dto.getAuthorImage())) {
			LogUtil.error(MODULE, "入参authorImage为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "authorImage";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isBlank(dto.getAuthorIntroduction())) {
			LogUtil.error(MODULE, "入参authorIntroduction为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "authorIntroduction";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isEmpty(dto.getRecommendGdsId())) {
			LogUtil.error(MODULE, "入参recommendGdsId为空");
			String[] keyInfos = new String[1];
			keyInfos[0] = "recommendGdsId";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		/* 2.调service层接口 */
		try {
			respDTO = sv.updateCmsRecommend(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "更新专家推荐失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_RECOMMEND_500102);
		}
		return respDTO;
	}

	/**
	 * TODO 查询专家推荐，分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsRecommendRSV#queryCmsExpertRecommendPage(com.zengshi.ecp.cms.dubbo.dto.CmsRecommendReqDTO)
	 */
	@Override
	public PageResponseDTO<CmsRecommendRespDTO> queryCmsRecommendPage(
			CmsRecommendReqDTO dto) throws BusinessException {
		LogUtil.info(MODULE, "调用分页查询专家推荐开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */

		/* 2.根据条件检索专家推荐 */
		PageResponseDTO<CmsRecommendRespDTO> pageInfo = PageResponseDTO
				.buildByBaseInfo(dto, CmsRecommendRespDTO.class);
		try {
			pageInfo = sv.queryCmsRecommendPage(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "查询专家推荐失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_RECOMMEND_500103);
		}
		return pageInfo;
	}

	/**
	 * TODO 查询单个专家推荐（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsRecommendRSV#queryCmsExpertRecommend(com.zengshi.ecp.cms.dubbo.dto.CmsRecommendReqDTO)
	 */
	@Override
	public CmsRecommendRespDTO queryCmsRecommend(CmsRecommendReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用查询专家推荐开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */
		if (StringUtil.isEmpty(dto.getId())) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.根据条件检索专家推荐 */
		CmsRecommendRespDTO cmsExpertRecommendRespDTO = new CmsRecommendRespDTO();
		try {
			cmsExpertRecommendRespDTO = sv.queryCmsRecommend(dto);
		} catch (Exception e) {
			LogUtil.error(MODULE, "根据ID查询专家推荐出现异常！", e);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_RECOMMEND_500103);
		}

		return cmsExpertRecommendRespDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsRecommendRSV#deleteCmsExpertRecommend(java.lang.Long)
	 */
	@Override
	public void deleteCmsRecommend(String id) throws BusinessException {
		LogUtil.info(MODULE, "调用删除专家推荐开始，入参：{id=" + id + "}");
		/* 1.验证前店入参 */
		if (StringUtil.isBlank(id)) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.逻辑删除专家推荐 */
		try {
			sv.deleteCmsRecommend(id);
		} catch (Exception err) {
			LogUtil.error(MODULE, "删除专家推荐失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_RECOMMEND_500101);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsRecommendRSV#deleteCmsExpertRecommendBatch(java.util.List)
	 */
	@Override
	public void deleteCmsRecommendBatch(List<String> list)
			throws BusinessException {
		LogUtil.info(MODULE, "调用批量删除专家推荐开始，入参：{list=" + list.toArray() + "}");
		/* 1.验证前店入参 */
		if (CollectionUtils.isEmpty(list)) {
			LogUtil.error(MODULE, "入参list为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "list";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.逻辑批量删除专家推荐 */
		try {
			sv.deleteCmsRecommendBatch(list);
		} catch (Exception err) {
			LogUtil.error(MODULE, "批量删除专家推荐失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_RECOMMEND_500104);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsRecommendRSV#changeStatusCmsExpertRecommend(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsRecommend(String id, String status)
			throws BusinessException {
		LogUtil.info(MODULE, "调用批量删除专家推荐开始，入参：{id=" + id + ",status=" + status
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
		/* 2.更新专家推荐状态 */
		try {
			sv.changeStatusCmsRecommend(id, status);
		} catch (Exception err) {
			LogUtil.error(MODULE, "更新专家推荐状态失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_RECOMMEND_500106);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsRecommendRSV#changeStatusCmsExpertRecommendBatch(java.util.List,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsRecommendBatch(List<String> list, String status)
			throws BusinessException {
		LogUtil.info(MODULE, "调用批量删除专家推荐开始，入参：{list=" + list.toArray()
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

		/* 2.更新专家推荐状态 */
		try {
			sv.changeStatusCmsRecommendBatch(list, status);
		} catch (Exception err) {
			LogUtil.error(MODULE, "批量更新专家推荐状态失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_RECOMMEND_500107);
		}
	}

	/**
	 * TODO 查询专家推荐 无分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO)
	 */
	@Override
	public List<CmsRecommendRespDTO> queryCmsRecommendList(
			CmsRecommendReqDTO dto) throws BusinessException {
		LogUtil.info(MODULE, "调用查询专家推荐开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */

		/* 2.根据条件检索楼层专家推荐 */
		List<CmsRecommendRespDTO> list = new ArrayList<CmsRecommendRespDTO>();
		try {
			list = sv.queryCmsRecommendList(dto);
		} catch (Exception e) {
			LogUtil.error(MODULE, "查询专家推荐出现异常！", e);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_RECOMMEND_500103);
		}

		return list;
	}
}
