package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsLinkMapper;
import com.zengshi.ecp.cms.dao.model.CmsLink;
import com.zengshi.ecp.cms.dao.model.CmsLinkCriteria;
import com.zengshi.ecp.cms.dao.model.CmsLinkCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsLinkReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLinkRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLinkSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

@Service("cmsLinkSV")
public class CmsLinkSVImpl extends GeneralSQLSVImpl implements ICmsLinkSV{
	
	  //定义个常量，用于表示模块名称，可以使用：当前类的类名
    private static final String MODULE = CmsChannelSVImpl.class.getName();

	@Resource(name = "SEQ_CMS_LINK")
	private PaasSequence seqCmsLink;

	@Resource
	private CmsLinkMapper cmsLinkMapper;

	@Resource
	private ICmsSiteSV cmsSiteSV;

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLinkSV#addCmsLink(com.zengshi.ecp.cms.dubbo.dto.CmsLinkReqDTO)
	 */
	@Override
	public CmsLinkRespDTO addCmsLink(CmsLinkReqDTO dto)
			throws BusinessException {
		/* 1.将入参组装成bo */
		CmsLink bo = new CmsLink();
		if (dto != null) {
			ObjectCopyUtil.copyObjValue(dto, bo, null, false);
		}
		long id = seqCmsLink.nextValue();
		bo.setId(id);
		bo.setCreateTime(DateUtil.getSysDate());
		bo.setCreateStaff(String.valueOf(dto.getStaff().getId()));

		/* 2.调dao层接口 */
		cmsLinkMapper.insertSelective(bo);

		/* 3.将结果返回 */
		CmsLinkRespDTO respDTO = new CmsLinkRespDTO();
		if (bo != null) {
			ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
		}
		return respDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLinkSV#updateCmsLink(com.zengshi.ecp.cms.dao.model.CmsLink)
	 */
	@Override
	public CmsLinkRespDTO updateCmsLink(CmsLinkReqDTO dto)
			throws BusinessException {
		/* 1.将入参组装成bo */
		CmsLink bo = new CmsLink();
		if (dto != null) {
			ObjectCopyUtil.copyObjValue(dto, bo, null, false);
		}
		bo.setUpdateStaff(String.valueOf(dto.getStaff().getId()));
		bo.setUpdateTime(DateUtil.getSysDate());

		/* 2.更新广告的原子方法 */
		return this.updateCmsLink(bo);
	}

	/**
	 * updateCmsLink:(更新广告的原子方法). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author jiangzh
	 * @param bo
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public CmsLinkRespDTO updateCmsLink(CmsLink bo)
			throws BusinessException {
		cmsLinkMapper.updateByPrimaryKeySelective(bo);
		/* 3.将结果返回 */
		CmsLinkRespDTO respDTO = new CmsLinkRespDTO();
		if (bo != null) {
			ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
		}
		return respDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLinkSV#deleteCmsLink(java.lang.Long)
	 */
	@Override
	public void deleteCmsLink(String id) throws BusinessException {
		//判断该栏目下是否存在子栏目
        if(exitsSubLink(Long.valueOf(id)))
        {
            throw new BusinessException(CmsConstants.MsgCode.CMS_LINK_500108);
        }
		CmsLink bo = new CmsLink();
		bo.setId(Long.parseLong(id));
		bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
		try {
			this.updateCmsLink(bo);
		} catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException(CmsConstants.MsgCode.CMS_LINK_500104);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLinkSV#deleteCmsLinkBatch(java.util.List)
	 */
	@Override
	public void deleteCmsLinkBatch(List<String> list)
			throws BusinessException {
		for (int i = 0; i < list.size(); i++) {
			String id = list.get(i);
			if (StringUtil.isNotBlank(id)) {
				this.deleteCmsLink(id);
			}
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLinkSV#changeStatusCmsLink(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsLink(String id, String status)
			throws BusinessException {
		CmsLink bo = new CmsLink();
		bo.setId(Long.parseLong(id));
		bo.setStatus(status);
		this.updateCmsLink(bo);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLinkSV#changeStatusCmsLinkBatch(java.util.List,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsLinkBatch(List<String> list, String status)
			throws BusinessException {
		for (int i = 0; i < list.size(); i++) {
			String id = list.get(i);
			if (StringUtil.isNotBlank(id)) {
				this.changeStatusCmsLink(id, status);
			}
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLinkSV#queryCmsLink(com.zengshi.ecp.cms.dubbo.dto.CmsLinkReqDTO)
	 */
	@Override
	public CmsLinkRespDTO queryCmsLink(CmsLinkReqDTO dto)
			throws BusinessException {
		CmsLinkRespDTO cmsLinkRespDTO = new CmsLinkRespDTO();
		if (StringUtil.isNotEmpty(dto.getId())) {
			/* 1.查询 */
			CmsLink bo = cmsLinkMapper.selectByPrimaryKey(dto.getId());
			cmsLinkRespDTO = conversionObject(bo);
		}

		return cmsLinkRespDTO;
	}

	/**
	 * TODO 查询广告列表，无分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLinkSV#queryCmsLinkList(com.zengshi.ecp.cms.dubbo.dto.CmsLinkReqDTO)
	 */
	@Override
	public List<CmsLinkRespDTO> queryCmsLinkList(CmsLinkReqDTO dto)
			throws BusinessException {

		/* 1.组装查询条件 */
		CmsLinkCriteria cmsLinkCriteria = new CmsLinkCriteria();
		Criteria criteria = cmsLinkCriteria.createCriteria();
		if (StringUtil.isNotEmpty(dto.getId())) {
			criteria.andIdEqualTo(dto.getId());
		}
		if (StringUtil.isNotBlank(dto.getLinkName())) {
			criteria.andLinkNameLike("%" + dto.getLinkName() + "%");
		}
		if (StringUtil.isNotBlank(dto.getStatus())) {
			criteria.andStatusEqualTo(dto.getStatus());
		}
		if (StringUtil.isNotEmpty(dto.getSiteId())) {
			criteria.andSiteIdEqualTo(dto.getSiteId());
		}
		if (StringUtil.isNotEmpty(dto.getLinkType())) {
			criteria.andLinkTypeEqualTo(dto.getLinkType());
		}
		if (StringUtil.isNotEmpty(dto.getLinkUrl())) {
			criteria.andLinkUrlEqualTo(dto.getLinkUrl());
		}
		if (StringUtil.isNotEmpty(dto.getLinkParent())) {
            criteria.andLinkParentEqualTo(dto.getLinkParent());
        }
		cmsLinkCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");

		/* 2.迭代查询结果 */
		List<CmsLinkRespDTO> cmsLinkRespDTOList = new ArrayList<CmsLinkRespDTO>();
		List<CmsLink> cmsLinkList = cmsLinkMapper
				.selectByExample(cmsLinkCriteria);
		if (CollectionUtils.isNotEmpty(cmsLinkList)) {
			for (CmsLink bo : cmsLinkList) {
				CmsLinkRespDTO cmsLinkRespDTO = conversionObject(bo);
				cmsLinkRespDTOList.add(cmsLinkRespDTO);
			}
		}
		return cmsLinkRespDTOList;
	}

	/**
	 * TODO 查询广告，分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLinkSV#queryCmsLinkPage(com.zengshi.ecp.cms.dubbo.dto.CmsLinkReqDTO)
	 */
	@Override
	public PageResponseDTO<CmsLinkRespDTO> queryCmsLinkPage(
			CmsLinkReqDTO dto) throws BusinessException {

		/* 1.根据条件检索广告 */
		CmsLinkCriteria cmsLinkCriteria = new CmsLinkCriteria();
		Criteria criteria = cmsLinkCriteria.createCriteria();
		if (StringUtil.isNotEmpty(dto.getId())) {
			criteria.andIdEqualTo(dto.getId());
		}
		if (StringUtil.isNotBlank(dto.getLinkName())) {
			criteria.andLinkNameLike("%" + dto.getLinkName() + "%");
		}
		if (StringUtil.isNotBlank(dto.getStatus())) {
			criteria.andStatusEqualTo(dto.getStatus());
		}
		if (StringUtil.isNotEmpty(dto.getSiteId())) {
			criteria.andSiteIdEqualTo(dto.getSiteId());
		}
		if (StringUtil.isNotEmpty(dto.getLinkType())) {
			criteria.andLinkTypeEqualTo(dto.getLinkType());
		}
		if (StringUtil.isNotEmpty(dto.getLinkUrl())) {
			criteria.andLinkUrlEqualTo(dto.getLinkUrl());
		}
		cmsLinkCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
		cmsLinkCriteria.setLimitClauseCount(dto.getPageSize());
		cmsLinkCriteria.setLimitClauseStart(dto.getStartRowIndex());

		return super.queryByPagination(dto, cmsLinkCriteria, false,
				new PaginationCallback<CmsLink, CmsLinkRespDTO>() {

					@Override
					public List<CmsLink> queryDB(BaseCriteria criteria) {
						return cmsLinkMapper
								.selectByExample((CmsLinkCriteria) criteria);
					}

					@Override
					public long queryTotal(BaseCriteria criteria) {
						return cmsLinkMapper
								.countByExample((CmsLinkCriteria) criteria);
					}

					@Override
					public CmsLinkRespDTO warpReturnObject(CmsLink bo) {
						return conversionObject(bo);
					}

				});

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
	private CmsLinkRespDTO conversionObject(CmsLink bo) {
		CmsLinkRespDTO dto = new CmsLinkRespDTO();
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

		// 1.遍历将编码转中文
		String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
		String linkTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_LINK_TYPE, dto.getLinkType());
		dto.setStatusZH(statusZH);
		dto.setLinkTypeZH(linkTypeZH);
		return dto;
	}
	
	 /**
     * 
     * haveSubChannel:(判断该该栏目下是否存在子栏目). <br/> 
     * 
     * @author PJieWin 
     * @param channelId
     * @return 
     * @since JDK 1.6
     */
    private boolean exitsSubLink(Long linkId)
    {
        long count = 0;
        
        CmsLinkCriteria criteria = new CmsLinkCriteria();
        Criteria sql =  criteria.createCriteria();
        sql.andLinkParentEqualTo(linkId).andStatusEqualTo(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        try {
            count = cmsLinkMapper.countByExample(criteria);
        } catch (Exception e) {
            LogUtil.error(MODULE, "判断该栏目下是否有子栏目出现异常:",e);
            throw new BusinessException(CmsConstants.MsgCode.CMS_CHANNEL_500101);
        }
        
        return count>0?true:false;
    }
    
}
