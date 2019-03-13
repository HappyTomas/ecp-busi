package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceCategoryReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceCategoryRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceCategoryRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceCategorySV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class CmsPlaceCategoryRSVImpl implements ICmsPlaceCategoryRSV{

	@Resource
	private ICmsPlaceCategorySV sv;

	// 定义个常量，用于表示模块名称，可以使用：当前类的类名
	private static final String MODULE = CmsPlaceCategoryRSVImpl.class.getName();

	
	@Override
	public List<CmsPlaceCategoryRespDTO> queryCmsPlaceCategory(
			CmsPlaceCategoryReqDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
		LogUtil.info(MODULE, "调用查询内容位置开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */

		/* 2.根据条件检索内容位置 */
		List<CmsPlaceCategoryRespDTO> cmsPlaceCategoryRespDTOList = new ArrayList<CmsPlaceCategoryRespDTO>();
		try {
			cmsPlaceCategoryRespDTOList = sv.queryCmsPlaceCategory(dto);
		} catch (Exception e) {
			LogUtil.error(MODULE, "查询placeCategory出现异常！", e);
			throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECATEGORY_500103);
		}
		
		return cmsPlaceCategoryRespDTOList;
	}


	@Override
	public CmsPlaceCategoryRespDTO addCmsPlaceCategory(
			CmsPlaceCategoryReqDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
		LogUtil.info(MODULE, "调用内容位置与商品分类关系开始，入参：{dto=" + dto.toString() + "}");
		CmsPlaceCategoryRespDTO respDTO = new CmsPlaceCategoryRespDTO();
		/* 1.验证前店入参 */
		if(StringUtil.isEmpty(dto.getPlaceId())){
			LogUtil.error(MODULE, "入参PlaceId为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "PlaceId";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		
		if(StringUtil.isEmpty(dto.getCatgId()))
		{
			LogUtil.error(MODULE, "入参CatgId为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "CatgId";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		/* 2.调service层接口 */
		try {
			respDTO = sv.addCmsPlaceCategory(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "新增内容位置与商品分类关系失败:", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECATEGORY_500101);
		}

		return respDTO;
	}


	@Override
	public CmsPlaceCategoryRespDTO updateCmsPlaceCategory(
			CmsPlaceCategoryReqDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
		LogUtil.info(MODULE, "调用内容位置与商品分类关系开始，入参：{dto=" + dto.toString() + "}");
		CmsPlaceCategoryRespDTO respDTO = new CmsPlaceCategoryRespDTO();
		/* 1.验证前店入参 */
		/* 1.验证前店入参 */
		if (StringUtil.isEmpty(dto.getId())) {
			LogUtil.error(MODULE, "入参Id为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "Id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if(StringUtil.isEmpty(dto.getPlaceId())){
			LogUtil.error(MODULE, "入参PlaceId为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "PlaceId";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		
		if(StringUtil.isEmpty(dto.getCatgId()))
		{
			LogUtil.error(MODULE, "入参CatgId为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "CatgId";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		/* 2.调service层接口 */
		try {
			respDTO = sv.updateCmsPlaceCategory(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "新增内容位置与商品分类关系失败:", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECATEGORY_500101);
		}

		return respDTO;
	}


	/** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceCategoryRSV#changeStatusCmsPlaceCategory(java.lang.Long, java.lang.String) 
     */
    @Override
    public void changeStatusCmsPlaceCategory(String id,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除内容位置与商品分类关系开始，入参：{id="+id+",status="+status+"}");
        /* 1.验证前店入参 */
        if(StringUtils.isBlank(id)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"id"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtils.isBlank(status)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"status"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        /* 2.更新内容位置与商品分类关系状态  */
        try{
            sv.changeStatusCmsPlaceCategory(id, status);
        } catch(Exception err){
            LogUtil.error(MODULE, "更新内容位置与商品分类关系状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECATEGORY_500106);
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceCategoryRSV#changeStatusCmsPlaceCategoryBatch(java.util.List, java.lang.String) 
     */
    @Override
    public void changeStatusCmsPlaceCategoryBatch(List<String> list,String status) throws BusinessException {
        LogUtil.info(MODULE, "调用批量删除内容位置与商品分类关系开始，入参：{list="+list.toArray()+",status="+status+"}");
        /* 1.验证前店入参 */
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"list"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        if(StringUtils.isBlank(status)){
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = {"status"};
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
        }
        
        /* 2.更新内容位置与商品分类关系状态  */
        try{
            sv.changeStatusCmsPlaceCategoryBatch(list,status);
        } catch(Exception err){
            LogUtil.error(MODULE, "批量更新内容位置与商品分类关系状态失败！",err);
            throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECATEGORY_500107);
        }
    }
    
	@Override
	public CmsPlaceCategoryRespDTO queryCmsPlaceCategoryById(
			CmsPlaceCategoryReqDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
		LogUtil.info(MODULE, "调用查询内容位置与商品分类关系开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */
		if (StringUtil.isEmpty(dto.getId())) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.根据条件检索内容位置 */
		CmsPlaceCategoryRespDTO cmsPlaceCategoryRespDTO = new CmsPlaceCategoryRespDTO();
		try {
			cmsPlaceCategoryRespDTO = sv.queryCmsPlaceCategoryById(dto);
		} catch (Exception e) {
			LogUtil.error(MODULE, "查询内容位置与商品分类出现异常！", e);
			throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECATEGORY_500103);
		}

		return cmsPlaceCategoryRespDTO;
	}


	@Override
	public PageResponseDTO<CmsPlaceCategoryRespDTO> queryCmsPlaceCategoryPage(
			CmsPlaceCategoryReqDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
		LogUtil.info(MODULE, "调用查询内容位置与商品分类开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */

		/* 2.根据条件检索内容位置 */
		PageResponseDTO<CmsPlaceCategoryRespDTO> pageInfo = PageResponseDTO
				.buildByBaseInfo(dto, CmsPlaceCategoryRespDTO.class);
		try {
			pageInfo = sv.queryCmsPlaceCategoryPage(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "查询内容位置与商品分类失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECATEGORY_500103);
		}
		return pageInfo;
	}


	@Override
	public void deleteCmsPlaceCategory(String id) throws BusinessException {
		// TODO Auto-generated method stub
		LogUtil.info(MODULE, "调用删除商品分类开始，入参：{id=" + id + "}");
		/* 1.验证前店入参 */
		if (StringUtil.isBlank(id)) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.逻辑删除商品分类 */
		try {
			sv.deleteCmsPlaceCategory(id);
		} catch (Exception err) {
			LogUtil.error(MODULE, "删除内容位置与商品分类关系失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECATEGORY_500104);
		}
	}


	@Override
	public void deleteCmsPlaceCategoryBatch(List<String> list)
			throws BusinessException {
		// TODO Auto-generated method stub
		LogUtil.info(MODULE, "调用批量删除内容位置与商品分类关系开始，入参：{list=" + list.toArray() + "}");
		/* 1.验证前店入参 */
		if (CollectionUtils.isEmpty(list)) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "list";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.逻辑批量删除 */
		try {
			sv.deleteCmsPlaceCategoryBatch(list);
		} catch (Exception err) {
			LogUtil.error(MODULE, "批量删除内容位置与商品分类失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_PLACECATEGORY_500104);
		}
	}


}
