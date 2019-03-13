package com.zengshi.ecp.cms.dubbo.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsArticleReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsArticleRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsArticleRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsArticleSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV;
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
public class CmsArticleRSVImpl implements ICmsArticleRSV {

	@Resource
	private ICmsArticleSV sv;
	@Resource
	private ICmsSiteSV cmsSiteSV;

	// 定义个常量，用于表示模块名称，可以使用：当前类的类名
	private static final String MODULE = CmsArticleRSVImpl.class.getName();

	/** 
     * inportOldWenToArticle:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(将旧官网数据导入到新官网文章表).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @throws BusinessException
     * @throws UnsupportedEncodingException 
     * @since JDK 1.6 
     */ 
    @Override
    public void inportOldWenToArticle() throws BusinessException, UnsupportedEncodingException{
        /* 2.调service层接口 */
        try {
            sv.inportOldWenToArticle();
        } catch (Exception err) {
            LogUtil.error(MODULE, "导入旧官网数据失败:", err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_ARTICLE_500108);
        }
        
    }
	
	/**
	 * TODO 新增文章.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsArticleRSV#saveCmsArticle(com.zengshi.ecp.cms.dubbo.dto.CmsArticleReqDTO)
	 */
	@Override
	public CmsArticleRespDTO addCmsArticle(CmsArticleReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用新增文章开始，入参：{dto=" + dto.toString() + "}");
		CmsArticleRespDTO respDTO = new CmsArticleRespDTO();
		/* 1.验证前店入参 */
		if (StringUtil.isBlank(dto.getArticleTitle())) {
			LogUtil.error(MODULE, "入参ArticleTitle为空！");
			String[] keyInfos = { "ArticleTitle" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isEmpty(dto.getSiteId())) {
			LogUtil.error(MODULE, "入参siteId为空！");
			String[] keyInfos = { "siteId" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
//		if (StringUtils.isBlank(dto.getVfsId())) {
//			LogUtil.error(MODULE, "入参vfsId为空！");
//			String[] keyInfos = { "vfsId" };
//			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
//					keyInfos);
//		}
		if (StringUtil.isEmpty(dto.getPubTime())) {
            LogUtil.error(MODULE, "入参pubTime为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="pubTime";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if (StringUtil.isEmpty(dto.getLostTime())) {
            LogUtil.error(MODULE, "入参lostTime为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="lostTime";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if (StringUtil.isBlank(dto.getStaticId())) {
            LogUtil.error(MODULE, "入参StaticId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="staticId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if (StringUtil.isEmpty(dto.getChannelId())){
        	LogUtil.error(MODULE, "入参channelId为空！");
			String[] keyInfos = { "siteId" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
        }
//        if (StringUtil.isEmpty(dto.getThumbnail())){
//        	LogUtil.error(MODULE, "入参thumbnail为空！");
//			String[] keyInfos = { "thumbnail" };
//			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
//					keyInfos);
//        }
        if (StringUtil.isEmpty(dto.getIstop())){
        	LogUtil.error(MODULE, "入参istop为空！");
			String[] keyInfos = { "istop" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
        }
        if (StringUtil.isEmpty(dto.getHomepageIsShow())){
            LogUtil.error(MODULE, "入参homepageIsShow为空！");
            String[] keyInfos = { "homepageIsShow" };
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
                    keyInfos);
        }
		/* 2.调service层接口 */
		try {
			respDTO = sv.addCmsArticle(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "新增文章失败:", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_ARTICLE_500101);
		}
		return respDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsArticleRSV#updateCmsArticle(com.zengshi.ecp.cms.dubbo.dto.CmsArticleReqDTO)
	 */
	@Override
	public CmsArticleRespDTO updateCmsArticle(CmsArticleReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用更新文章开始，入参：{dto=" + dto.toString() + "}");
		CmsArticleRespDTO respDTO = new CmsArticleRespDTO();
		/* 1.验证前店入参 */
		if (StringUtil.isEmpty(dto.getId())) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = { "id" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isBlank(dto.getArticleTitle())) {
			LogUtil.error(MODULE, "入参ArticleTitle为空！");
			String[] keyInfos = { "ArticleTitle" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isEmpty(dto.getSiteId())) {
			LogUtil.error(MODULE, "入参siteId为空！");
			String[] keyInfos = { "siteId" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
//		if (StringUtils.isBlank(dto.getVfsId())) {
//			LogUtil.error(MODULE, "入参vfsId为空！");
//			String[] keyInfos = { "vfsId" };
//			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
//					keyInfos);
//		}
		if (StringUtil.isEmpty(dto.getPubTime())) {
            LogUtil.error(MODULE, "入参pubTime为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="pubTime";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if (StringUtil.isEmpty(dto.getLostTime())) {
            LogUtil.error(MODULE, "入参lostTime为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="lostTime";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if (StringUtil.isBlank(dto.getStaticId())) {
            LogUtil.error(MODULE, "入参StaticId为空！");
            String[] keyInfos = new String[1];
            keyInfos[0]="staticId";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if (StringUtil.isEmpty(dto.getChannelId())){
        	LogUtil.error(MODULE, "入参channelId为空！");
			String[] keyInfos = { "channelId" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
        }
//        if (StringUtil.isEmpty(dto.getThumbnail())){
//        	LogUtil.error(MODULE, "入参thumbnail为空！");
//			String[] keyInfos = { "thumbnail" };
//			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
//					keyInfos);
//        }
        if (StringUtil.isEmpty(dto.getIstop())){
        	LogUtil.error(MODULE, "入参istop为空！");
			String[] keyInfos = { "istop" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
        }
        if (StringUtil.isEmpty(dto.getHomepageIsShow())){
            LogUtil.error(MODULE, "入参homepageIsShow为空！");
            String[] keyInfos = { "homepageIsShow" };
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
                    keyInfos);
        }

		/* 2.调service层接口 */
		try {
			respDTO = sv.updateCmsArticle(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "更新文章失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_ARTICLE_500102);
		}
		return respDTO;
	}

	/**
	 * TODO 查询文章，分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsArticleRSV#queryCmsArticlePage(com.zengshi.ecp.cms.dubbo.dto.CmsArticleReqDTO)
	 */
	@Override
	public PageResponseDTO<CmsArticleRespDTO> queryCmsArticlePage(
			CmsArticleReqDTO dto) throws BusinessException {
		LogUtil.info(MODULE, "调用分页查询文章开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */

		/* 2.根据条件检索文章 */
		PageResponseDTO<CmsArticleRespDTO> pageInfo = PageResponseDTO
				.buildByBaseInfo(dto, CmsArticleRespDTO.class);
		try {
			pageInfo = sv.queryCmsArticlePage(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "查询文章失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_ARTICLE_500103);
		}
		return pageInfo;
	}

	/**
	 * TODO 查询单个文章（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsArticleRSV#queryCmsArticle(com.zengshi.ecp.cms.dubbo.dto.CmsArticleReqDTO)
	 */
	@Override
	public CmsArticleRespDTO queryCmsArticle(CmsArticleReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用查询文章开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */
		if (StringUtil.isEmpty(dto.getId())) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = { "id" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.根据条件检索文章 */
		CmsArticleRespDTO cmsArticleRespDTO = new CmsArticleRespDTO();
		try {
			cmsArticleRespDTO = sv.queryCmsArticle(dto);
		} catch (Exception e) {
			LogUtil.error(MODULE, "根据ID查询文章出现异常！", e);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_ARTICLE_500103);
		}
		return cmsArticleRespDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsArticleRSV#deleteCmsArticle(java.lang.Long)
	 */
	@Override
	public void deleteCmsArticle(String id) throws BusinessException {
		LogUtil.info(MODULE, "调用删除文章开始，入参：{id=" + id + "}");
		/* 1.验证前店入参 */
		if (StringUtils.isBlank(id)) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = { "id" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.逻辑删除文章 */
		try {
			sv.deleteCmsArticle(id);
		} catch (Exception err) {
			LogUtil.error(MODULE, "删除文章失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_ARTICLE_500104);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsArticleRSV#deleteCmsArticleBatch(java.util.List)
	 */
	@Override
	public void deleteCmsArticleBatch(List<String> list)
			throws BusinessException {
		LogUtil.info(MODULE, "调用批量删除文章开始，入参：{list=" + list.toArray() + "}");
		/* 1.验证前店入参 */
		if (CollectionUtils.isEmpty(list)) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = { "list" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.逻辑批量删除文章 */
		try {
			sv.deleteCmsArticleBatch(list);
		} catch (Exception err) {
			LogUtil.error(MODULE, "批量删除文章失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_ARTICLE_500105);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsArticleRSV#changeStatusCmsArticle(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsArticle(String id, String status)
			throws BusinessException {
		LogUtil.info(MODULE, "调用批量删除文章开始，入参：{id=" + id + ",status=" + status
				+ "}");
		/* 1.验证前店入参 */
		if (StringUtils.isBlank(id)) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = { "id" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtils.isBlank(status)) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = { "status" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		/* 2.更新文章状态 */
		try {
			sv.changeStatusCmsArticle(id, status);
		} catch (Exception err) {
			LogUtil.error(MODULE, "更新文章状态失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_ARTICLE_500106);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsArticleRSV#changeStatusCmsArticleBatch(java.util.List,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsArticleBatch(List<String> list, String status)
			throws BusinessException {
		LogUtil.info(MODULE, "调用批量删除文章开始，入参：{list=" + list.toArray()
				+ ",status=" + status + "}");
		/* 1.验证前店入参 */
		if (CollectionUtils.isEmpty(list)) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = { "list" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtils.isBlank(status)) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = { "status" };
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.更新文章状态 */
		try {
			sv.changeStatusCmsArticleBatch(list, status);
		} catch (Exception err) {
			LogUtil.error(MODULE, "批量更新文章状态失败！", err);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_ARTICLE_500107);
		}
	}

	/**
	 * TODO 查询文章 无分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO)
	 */
	@Override
	public List<CmsArticleRespDTO> queryCmsArticleList(
			CmsArticleReqDTO dto) throws BusinessException {
		LogUtil.info(MODULE, "调用查询文章开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */

		/* 2.根据条件检索楼层文章 */
		List<CmsArticleRespDTO> list = new ArrayList<CmsArticleRespDTO>();
		try {
			list = sv.queryCmsArticleList(dto);
		} catch (Exception e) {
			LogUtil.error(MODULE, "查询文章出现异常！", e);
			throw new BusinessException(
					CmsConstants.MsgCode.CMS_ARTICLE_500103);
		}

		return list;
	}

}
