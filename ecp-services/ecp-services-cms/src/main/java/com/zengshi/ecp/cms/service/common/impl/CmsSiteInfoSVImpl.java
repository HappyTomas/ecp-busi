package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsSiteInfoMapper;
import com.zengshi.ecp.cms.dao.model.CmsSiteInfo;
import com.zengshi.ecp.cms.dao.model.CmsSiteInfoCriteria;
import com.zengshi.ecp.cms.dao.model.CmsSiteInfoCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelResDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsChannelSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteInfoSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015年8月7日下午5:11:59 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version
 * @since JDK 1.6
 */
@Service("cmsSiteInfoSV")
public class CmsSiteInfoSVImpl extends GeneralSQLSVImpl implements
		ICmsSiteInfoSV {

	@Resource(name = "SEQ_CMS_SITE_INFO")
	private PaasSequence seqCmsSiteInfo;

	@Resource
	private CmsSiteInfoMapper cmsSiteInfoMapper;

	@Resource
	private ICmsSiteSV cmsSiteSV;

	@Resource
    private ICmsChannelSV cmsChannelSV;
	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteInfoSV#addCmsSiteInfo(com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoReqDTO)
	 */
	@Override
	public CmsSiteInfoRespDTO addCmsSiteInfo(CmsSiteInfoReqDTO dto)
			throws BusinessException {
		/* 1.将入参组装成bo */
		CmsSiteInfo bo = new CmsSiteInfo();
		if (dto != null) {
			ObjectCopyUtil.copyObjValue(dto, bo, null, false);
		}
		long id = seqCmsSiteInfo.nextValue();
		bo.setId(id);
		bo.setCreateTime(DateUtil.getSysDate());
		bo.setCreateStaff(String.valueOf(dto.getStaff().getId()));
		if(null == bo.getParent()){
		    bo.setParent(CmsConstants.SiteInfoRoot.CMS_SITE_INFO_ROOT_ID);
		}
		/* 2.调dao层接口 */
		cmsSiteInfoMapper.insertSelective(bo);

		/* 3.将结果返回 */
		CmsSiteInfoRespDTO respDTO = new CmsSiteInfoRespDTO();
		if (bo != null) {
			ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
		}
		return respDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteInfoSV#updateCmsSiteInfo(com.zengshi.ecp.cms.dao.model.CmsSiteInfo)
	 */
	@Override
	public CmsSiteInfoRespDTO updateCmsSiteInfo(CmsSiteInfoReqDTO dto)
			throws BusinessException {
		/* 1.将入参组装成bo */
		CmsSiteInfo bo = new CmsSiteInfo();
		if (dto != null) {
			ObjectCopyUtil.copyObjValue(dto, bo, null, false);
		}
		bo.setUpdateStaff(String.valueOf(dto.getStaff().getId()));
		bo.setUpdateTime(DateUtil.getSysDate());

		/* 2.更新广告的原子方法 */
		return this.updateCmsSiteInfo(bo);
	}

	/**
	 * updateCmsSiteInfo:(更新广告的原子方法). <br/>
	 * 
	 * @author jiangzh
	 * @param bo
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public CmsSiteInfoRespDTO updateCmsSiteInfo(CmsSiteInfo bo)
			throws BusinessException {
		cmsSiteInfoMapper.updateByPrimaryKeySelective(bo);
		/* 3.将结果返回 */
		CmsSiteInfoRespDTO respDTO = new CmsSiteInfoRespDTO();
		if (bo != null) {
			ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
		}
		return respDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteInfoSV#deleteCmsSiteInfo(java.lang.Long)
	 */
	@Override
	public void deleteCmsSiteInfo(String id) throws BusinessException {
		CmsSiteInfo bo = new CmsSiteInfo();
		bo.setId(Long.parseLong(id));
		bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
		this.updateCmsSiteInfo(bo);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteInfoSV#deleteCmsSiteInfoBatch(java.util.List)
	 */
	@Override
	public void deleteCmsSiteInfoBatch(List<String> list)
			throws BusinessException {
		for (int i = 0; i < list.size(); i++) {
			String id = list.get(i);
			if (StringUtil.isNotBlank(id)) {
				this.deleteCmsSiteInfo(id);
			}
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteInfoSV#changeStatusCmsSiteInfo(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsSiteInfo(String id, String status)
			throws BusinessException {
		CmsSiteInfo bo = new CmsSiteInfo();
		bo.setId(Long.parseLong(id));
		bo.setStatus(status);
		this.updateCmsSiteInfo(bo);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteInfoSV#changeStatusCmsSiteInfoBatch(java.util.List,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsSiteInfoBatch(List<String> list, String status)
			throws BusinessException {
		for (int i = 0; i < list.size(); i++) {
			String id = list.get(i);
			if (StringUtil.isNotBlank(id)) {
				this.changeStatusCmsSiteInfo(id, status);
			}
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteInfoSV#queryCmsSiteInfo(com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoReqDTO)
	 */
	@Override
	public CmsSiteInfoRespDTO queryCmsSiteInfo(CmsSiteInfoReqDTO dto)
			throws BusinessException {
		CmsSiteInfoRespDTO cmsSiteInfoRespDTO = new CmsSiteInfoRespDTO();
		if (StringUtil.isNotEmpty(dto.getId())) {
			/* 1.查询 */
			CmsSiteInfo bo = cmsSiteInfoMapper.selectByPrimaryKey(dto.getId());
			cmsSiteInfoRespDTO = conversionObject(bo);
		}

		return cmsSiteInfoRespDTO;
	}

	/**
	 * TODO 查询广告列表，无分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteInfoSV#queryCmsSiteInfoList(com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoReqDTO)
	 */
	@Override
	public List<CmsSiteInfoRespDTO> queryCmsSiteInfoList(CmsSiteInfoReqDTO dto)
			throws BusinessException {

		/* 1.组装查询条件 */
		CmsSiteInfoCriteria cmsSiteInfoCriteria = new CmsSiteInfoCriteria();
		Criteria criteria = cmsSiteInfoCriteria.createCriteria();
		initCriteria(criteria, dto);
		cmsSiteInfoCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");

		/* 2.迭代查询结果 */
		List<CmsSiteInfoRespDTO> cmsSiteInfoRespDTOList = new ArrayList<CmsSiteInfoRespDTO>();
		List<CmsSiteInfo> cmsSiteInfoList = cmsSiteInfoMapper
				.selectByExample(cmsSiteInfoCriteria);
		if (CollectionUtils.isNotEmpty(cmsSiteInfoList)) {
			for (CmsSiteInfo bo : cmsSiteInfoList) {
				CmsSiteInfoRespDTO cmsSiteInfoRespDTO = conversionObject(bo);
				cmsSiteInfoRespDTOList.add(cmsSiteInfoRespDTO);
			}
		}
		return cmsSiteInfoRespDTOList;
	}

	/**
	 * TODO 查询广告，分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteInfoSV#queryCmsSiteInfoPage(com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoReqDTO)
	 */
	@Override
	public PageResponseDTO<CmsSiteInfoRespDTO> queryCmsSiteInfoPage(
			CmsSiteInfoReqDTO dto) throws BusinessException {

		/* 1.根据条件检索广告 */
		CmsSiteInfoCriteria cmsSiteInfoCriteria = new CmsSiteInfoCriteria();
		Criteria criteria = cmsSiteInfoCriteria.createCriteria();
		initCriteria(criteria, dto);
		cmsSiteInfoCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
		cmsSiteInfoCriteria.setLimitClauseCount(dto.getPageSize());
		cmsSiteInfoCriteria.setLimitClauseStart(dto.getStartRowIndex());

		return super.queryByPagination(dto, cmsSiteInfoCriteria, false,
				new PaginationCallback<CmsSiteInfo, CmsSiteInfoRespDTO>() {

					@Override
					public List<CmsSiteInfo> queryDB(BaseCriteria criteria) {
						return cmsSiteInfoMapper
								.selectByExample((CmsSiteInfoCriteria) criteria);
					}

					@Override
					public long queryTotal(BaseCriteria criteria) {
						return cmsSiteInfoMapper
								.countByExample((CmsSiteInfoCriteria) criteria);
					}

					@Override
					public CmsSiteInfoRespDTO warpReturnObject(CmsSiteInfo bo) {
						return conversionObject(bo);
					}

				});

	}

	/**
	 * 
	 * initCriteria:(根据reqDto字段初始化条件). <br/> 
	 * 
	 * @author zhanbh 
	 * @param criteria
	 * @param dto
	 * @return 
	 * @since JDK 1.6
	 */
	private Criteria initCriteria(Criteria criteria,CmsSiteInfoReqDTO dto){
	    if(null == dto){
	        return criteria;
	    }
	    if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getSiteInfoName())) {
            criteria.andSiteInfoNameLike("%" + dto.getSiteInfoName() + "%");
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if (StringUtil.isNotBlank(dto.getSiteInfoType())) {
            criteria.andSiteInfoTypeEqualTo(dto.getSiteInfoType());
        }
        if (StringUtil.isNotBlank(dto.getChannelId())) {
            criteria.andChannelIdEqualTo(dto.getChannelId());
        }
        if (StringUtil.isNotEmpty(dto.getParent())) {
            criteria.andParentEqualTo(dto.getParent());
        }
        return criteria;
	}
	/**
	 * conversionObject:(将bo转换成DTO，同时翻译有关字段). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author jiangzh
	 * @param bo
	 * @return
	 * @since JDK 1.6
	 */
	private CmsSiteInfoRespDTO conversionObject(CmsSiteInfo bo) {
		CmsSiteInfoRespDTO dto = new CmsSiteInfoRespDTO();
		if (bo != null) {
			ObjectCopyUtil.copyObjValue(bo, dto, null, false);
		}

		// 1 查询站点服务，获取站点对应的名称
		if (StringUtil.isNotEmpty(dto.getSiteId())) {
			CmsSiteReqDTO reqDTO = new CmsSiteReqDTO();
			reqDTO.setId(bo.getSiteId());
			CmsSiteRespDTO respDTO = cmsSiteSV.queryCmsSite(reqDTO);
			if (respDTO != null) {
				dto.setSiteZH(respDTO.getSiteName());
			}
		}
		
		// 2 查询栏目服务，获取栏目对应的名称
        if (StringUtil.isNotEmpty(dto.getChannelId())) {
            CmsChannelReqDTO reqDTO = new CmsChannelReqDTO();
            reqDTO.setId(Long.valueOf(bo.getChannelId()));
            CmsChannelResDTO respDTO = cmsChannelSV.find(reqDTO);
            if(respDTO != null){
                dto.setChannelZH(respDTO.getChannelName());
            }
        }
        // 3查询网站信息 ，获取父节点名称
        if(null == dto.getParent() || CmsConstants.SiteInfoRoot.CMS_SITE_INFO_ROOT_ID.equals(dto.getParent())){
            dto.setParent(CmsConstants.SiteInfoRoot.CMS_SITE_INFO_ROOT_ID);
            dto.setParentName(CmsConstants.SiteInfoRoot.CMS_SITE_INFO_ROOT_NAME);
        }else{
            CmsSiteInfo parentBo = cmsSiteInfoMapper.selectByPrimaryKey(dto.getParent());
            if(null != parentBo){
                dto.setParentName(parentBo.getSiteInfoName());
            }
        }
		// 4.遍历将编码转中文
		String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
		dto.setStatusZH(statusZH);
		String siteInfoTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_SITE_INFO_TYPE, dto.getSiteInfoType());
        dto.setSiteInfoTypeZH(siteInfoTypeZH);

		return dto;
	}

    @Override
    public List<CmsSiteInfoRespDTO> queryTopSiteInfoBySiteId(Long siteId) throws BusinessException {
        List<CmsSiteInfoRespDTO> cmsSiteInfoRespDTOList = new ArrayList<CmsSiteInfoRespDTO>();
        if(StringUtil.isEmpty(siteId)){
            return cmsSiteInfoRespDTOList;
        }
        CmsSiteInfoCriteria cmsSiteInfoCriteria = new CmsSiteInfoCriteria();
        Criteria criteria = cmsSiteInfoCriteria.createCriteria();
        criteria.andStatusEqualTo(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        criteria.andSiteIdEqualTo(siteId);
        criteria.andParentEqualTo(CmsConstants.SiteInfoRoot.CMS_SITE_INFO_ROOT_ID);
        cmsSiteInfoCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        
        List<CmsSiteInfo> cmsSiteInfoList = cmsSiteInfoMapper.selectByExample(cmsSiteInfoCriteria);
        if (CollectionUtils.isNotEmpty(cmsSiteInfoList)) {
            for (CmsSiteInfo bo : cmsSiteInfoList) {
                CmsSiteInfoRespDTO cmsSiteInfoRespDTO = conversionObject(bo);
                cmsSiteInfoRespDTOList.add(cmsSiteInfoRespDTO);
            }
        }
        return cmsSiteInfoRespDTOList;
    }
}
