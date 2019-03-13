package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsGdsCategoryRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsGdsCategorySV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class CmsGdsCategoryRSVImpl implements ICmsGdsCategoryRSV{
	@Resource
	private ICmsGdsCategorySV sv;

	// 定义个常量，用于表示模块名称，可以使用：当前类的类名
	private static final String MODULE = CmsGdsCategoryRSVImpl.class.getName();

	
	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsGdsCategoryRSV#addCmsGdsCategory(com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO)
	 */
	@Override
	public CmsGdsCategoryRespDTO addCmsGdsCategory(CmsGdsCategoryReqDTO dto)
			throws BusinessException {
		LogUtil.info(MODULE, "调用商品分类开始，入参：{dto=" + dto.toString() + "}");
		CmsGdsCategoryRespDTO respDTO = new CmsGdsCategoryRespDTO();
		/* 1.验证前店入参 */
		if (StringUtil.isBlank(dto.getCatgName())) {
			LogUtil.error(MODULE, "入参CatgName为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "CatgName";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		
	
		/* 2.调service层接口 */
		try {
			respDTO = sv.addCmsGdsCategory(dto);
		} catch (Exception err) {
			LogUtil.error(MODULE, "新增商品分类失败:", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_GDSCATEGORY_500101);
		}

		return respDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsGdsCategoryRSV#updateCmsGdsCategory(com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO)
	 */
	@Override
	public CmsGdsCategoryRespDTO updateCmsGdsCategory(CmsGdsCategoryReqDTO dto) {
		LogUtil.info(MODULE, "调用更新商品分类开始，入参：{dto=" + dto.toString() + "}");
		CmsGdsCategoryRespDTO respDTO = new CmsGdsCategoryRespDTO();
		/* 1.验证前店入参 */
		if (StringUtil.isEmpty(dto.getId())) {
			LogUtil.error(MODULE, "入参Id为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "Id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		if (StringUtil.isBlank(dto.getCatgName())) {
			LogUtil.error(MODULE, "入参CatgName为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "CatgName";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}
		
		/* 2.调service层接口 */
		try {
			respDTO = sv.updateCmsGdsCategory(dto);
		}catch (Exception err) {
			LogUtil.error(MODULE, "更新商品分类失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_GDSCATEGORY_500102);
		}

		return respDTO;
	}

	/**
	 * 
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsGdsCategoryRSV#deleteCmsGdsCategory(com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO)
	 */
	@Override
	public void deleteCmsGdsCategory(String id) throws BusinessException {
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
			sv.deleteCmsGdsCategory(id);
		} catch (Exception err) {
			LogUtil.error(MODULE, "删除商品分类失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_GDSCATEGORY_500105);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV#deleteCmsAdvertiseBatch(java.util.List)
	 */
	@Override
	public void deleteCmsGdsCategoryBatch(List<String> list) throws BusinessException {
		LogUtil.info(MODULE, "调用批量删除商品分类开始，入参：{list=" + list.toArray() + "}");
		/* 1.验证前店入参 */
		if (CollectionUtils.isEmpty(list)) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "list";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.逻辑批量删除商品分类 */
		try {
			sv.deleteCmsGdsCategoryBatch(list);
		} catch (Exception err) {
			LogUtil.error(MODULE, "批量删除商品分类失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_GDSCATEGORY_500104);
		}
	}
	
	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV#changeStatusCmsFloor(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsGdsCategory(String id, String status)
			throws BusinessException {
		LogUtil.info(MODULE, "调用批量删除商品分类开始，入参：{id=" + id + ",status=" + status
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
		/* 2.更新状态 */
		try {
			sv.changeStatusCmsGdsCategory(id, status);
		} catch (Exception err) {
			LogUtil.error(MODULE, "更新商品分类状态失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_GDSCATEGORY_500106);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV#changeStatusCmsFloorBatch(java.util.List,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsGdsCategoryBatch(List<String> list, String status)
			throws BusinessException {
		LogUtil.info(MODULE, "调用批量删商品分类层开始，入参：{list=" + list.toArray()
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
			sv.changeStatusCmsGdsCategoryBatch(list, status);
		} catch (Exception err) {
			LogUtil.error(MODULE, "批量更新商品分类状态失败！", err);
			throw new BusinessException(CmsConstants.MsgCode.CMS_GDSCATEGORY_500107);
		}
	}
	
	/**
	 * TODO 查询单个商品分类（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsGdsCategoryRSV#queryCmsGdsCategory(com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO)
	 */
	@Override
	public CmsGdsCategoryRespDTO queryCmsGdsCategory(CmsGdsCategoryReqDTO dto)
			throws BusinessException {

		LogUtil.info(MODULE, "调用查询商品分类开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */
		if (StringUtil.isEmpty(dto.getId())) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.根据条件检索商品分类 */
		CmsGdsCategoryRespDTO cmsGdsCategoryRespDTO = new CmsGdsCategoryRespDTO();
		try {
			cmsGdsCategoryRespDTO = sv.queryCmsGdsCategory(dto);
		} catch (Exception e) {
			LogUtil.error(MODULE, "查询商品分类出现异常！", e);
			throw new BusinessException(CmsConstants.MsgCode.CMS_GDSCATEGORY_500103);
		}

		return cmsGdsCategoryRespDTO;
	}
	/**
     * 
     * queryCmsGdsCategory:(查询指定分类 及其指定层级指定状态的子分类). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param dto   分类条件  其中id必传
     * @param level  子分类层级  -1或null 查该分类的全部子分类
     * @param status 子分类状态
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	@Override
    public CmsGdsCategoryRespDTO queryCmsGdsCategory(CmsGdsCategoryReqDTO dto, Short level,
            String status) throws BusinessException {
	    LogUtil.info(MODULE, "调用查询指定分类 及其指定层级指定状态的子分类开始，入参：{dto=" + dto.toString() + "}");
        /* 1.验证前店入参 */
        if (StringUtil.isEmpty(dto.getId())) {
            LogUtil.error(MODULE, "入参ID为空！");
            String[] keyInfos = new String[1];
            keyInfos[0] = "id";
            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
                    keyInfos);
        }

        /* 2.根据条件检索商品分类 */
        CmsGdsCategoryRespDTO cmsGdsCategoryRespDTO = null;
        try {
            cmsGdsCategoryRespDTO = sv.queryCmsGdsCategory(dto,level,status);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询指定分类 及其指定层级指定状态的子分类出现异常！", e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_GDSCATEGORY_500103);
        }

        return cmsGdsCategoryRespDTO;
    }
	/**
	 * TODO 查询单个商品分类（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsGdsCategoryRSV#queryCmsCategorySons
	 * (com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO)
	 */
	@Override
	public List<CmsGdsCategoryRespDTO> queryCmsCategorySons(
			CmsGdsCategoryReqDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
		LogUtil.info(MODULE, "调用查询商品分类子节点开始，入参：{dto=" + dto.toString() + "}");
		/* 1.验证前店入参 */
		if (StringUtil.isEmpty(dto.getId())) {
			LogUtil.error(MODULE, "入参ID为空！");
			String[] keyInfos = new String[1];
			keyInfos[0] = "id";
			throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,
					keyInfos);
		}

		/* 2.根据条件检索商品分类 */
		List<CmsGdsCategoryRespDTO> cmsGdsCategoryRespDTOList = new ArrayList<CmsGdsCategoryRespDTO>();
		try {
			cmsGdsCategoryRespDTOList = sv.queryCmsCategorySons(dto);
		} catch (Exception e) {
			LogUtil.error(MODULE, "查询商品分类子节点出现异常！", e);
			throw new BusinessException(CmsConstants.MsgCode.CMS_GDSCATEGORY_500103);
		}

		return cmsGdsCategoryRespDTOList;
	}
	
	/**
	 * queryCmsGdsCategoryInit:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author huangxm9
	 * @param dto
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	@Override
	public List<CmsGdsCategoryRespDTO> queryCmsGdsCategoryInit() throws BusinessException
	{
		// TODO Auto-generated method stub
				LogUtil.info(MODULE, "调用查询商品原始节点开始，");
				/* 1.验证前店入参 */
			
				/* 2.根据条件检索商品分类 */
				List<CmsGdsCategoryRespDTO> cmsGdsCategoryRespDTOList = new ArrayList<CmsGdsCategoryRespDTO>();
				try {
					cmsGdsCategoryRespDTOList = sv.queryCmsGdsCategoryInit();
				} catch (Exception e) {
					LogUtil.error(MODULE, "查询商品分类原始节点出现异常！", e);
					throw new BusinessException(CmsConstants.MsgCode.CMS_GDSCATEGORY_500103);
				}

				return cmsGdsCategoryRespDTOList;
	}
	
	/**
	 * 根据条件获取信息 add by gongxq
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsGdsCategoryRSV#queryCmsCategoryInfo(com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO)
	 */
    @Override
    public List<CmsGdsCategoryRespDTO> queryCmsCategoryInfo(CmsGdsCategoryReqDTO dto)
            throws BusinessException {
        try {
            return sv.queryCmsCategoryInfo(dto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询商品分类出现异常！", e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_GDSCATEGORY_500103);
        }
    }

}
