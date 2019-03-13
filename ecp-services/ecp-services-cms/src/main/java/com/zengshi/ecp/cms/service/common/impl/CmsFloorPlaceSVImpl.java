package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsFloorPlaceMapper;
import com.zengshi.ecp.cms.dao.model.CmsFloorPlace;
import com.zengshi.ecp.cms.dao.model.CmsFloorPlaceCriteria;
import com.zengshi.ecp.cms.dao.model.CmsFloorPlaceCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorPlaceSV;
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

@Service("cmsFloorPlaceSV")
public class CmsFloorPlaceSVImpl extends GeneralSQLSVImpl implements ICmsFloorPlaceSV{

	@Resource(name = "SEQ_CMS_FLOOR_PLACE")
	private PaasSequence seqCmsFloorPlace;

	@Resource
	private CmsFloorPlaceMapper cmsFloorPlaceMapper;

	@Resource
	private ICmsSiteSV cmsSiteSV;

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorPlaceSV#addCmsFloorPlace(com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceReqDTO)
	 */
	@Override
	public CmsFloorPlaceRespDTO addCmsFloorPlace(CmsFloorPlaceReqDTO dto)
			throws BusinessException {
		/* 1.将入参组装成bo */
		CmsFloorPlace bo = new CmsFloorPlace();
		if (dto != null) {
			ObjectCopyUtil.copyObjValue(dto, bo, null, false);
		}
		long id = seqCmsFloorPlace.nextValue();
		bo.setId(id);
		bo.setCreateTime(DateUtil.getSysDate());
		bo.setCreateStaff(dto.getStaff().getId());

		/* 2.调dao层接口 */
		cmsFloorPlaceMapper.insertSelective(bo);

		/* 3.将结果返回 */
		CmsFloorPlaceRespDTO respDTO = new CmsFloorPlaceRespDTO();
		if (bo != null) {
			ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
		}
		return respDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorPlaceSV#updateCmsFloorPlace(com.zengshi.ecp.cms.dao.model.CmsFloorPlace)
	 */
	@Override
	public CmsFloorPlaceRespDTO updateCmsFloorPlace(CmsFloorPlaceReqDTO dto)
			throws BusinessException {
		/* 1.将入参组装成bo */
		CmsFloorPlace bo = new CmsFloorPlace();
		if (dto != null) {
			ObjectCopyUtil.copyObjValue(dto, bo, null, false);
		}
		bo.setUpdateStaff(dto.getStaff().getId());
		bo.setUpdateTime(DateUtil.getSysDate());

		/* 2.更新楼层内容位置的原子方法 */
		return this.updateCmsFloorPlace(bo);
	}

	/**
	 * updateCmsFloorPlace:(更新楼层内容位置的原子方法). <br/>
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
	public CmsFloorPlaceRespDTO updateCmsFloorPlace(CmsFloorPlace bo)
			throws BusinessException {
		cmsFloorPlaceMapper.updateByPrimaryKeySelective(bo);
		/* 3.将结果返回 */
		CmsFloorPlaceRespDTO respDTO = new CmsFloorPlaceRespDTO();
		if (bo != null) {
			ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
		}
		return respDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorPlaceSV#deleteCmsFloorPlace(java.lang.Long)
	 */
	@Override
	public void deleteCmsFloorPlace(String id) throws BusinessException {
		CmsFloorPlace bo = new CmsFloorPlace();
		bo.setId(Long.parseLong(id));
		bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
		this.updateCmsFloorPlace(bo);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorPlaceSV#deleteCmsFloorPlaceBatch(java.util.List)
	 */
	@Override
	public void deleteCmsFloorPlaceBatch(List<String> list)
			throws BusinessException {
		for (int i = 0; i < list.size(); i++) {
			String id = list.get(i);
			if (StringUtil.isNotBlank(id)) {
				this.deleteCmsFloorPlace(id);
			}
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorPlaceSV#changeStatusCmsFloorPlace(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsFloorPlace(String id, String status)
			throws BusinessException {
		CmsFloorPlace bo = new CmsFloorPlace();
		bo.setId(Long.parseLong(id));
		bo.setStatus(status);
		this.updateCmsFloorPlace(bo);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorPlaceSV#changeStatusCmsFloorPlaceBatch(java.util.List,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsFloorPlaceBatch(List<String> list, String status)
			throws BusinessException {
		for (int i = 0; i < list.size(); i++) {
			String id = list.get(i);
			if (StringUtil.isNotBlank(id)) {
				this.changeStatusCmsFloorPlace(id, status);
			}
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorPlaceSV#queryCmsFloorPlace(com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceReqDTO)
	 */
	@Override
	public CmsFloorPlaceRespDTO queryCmsFloorPlace(CmsFloorPlaceReqDTO dto)
			throws BusinessException {
		CmsFloorPlaceRespDTO cmsFloorPlaceRespDTO = new CmsFloorPlaceRespDTO();
		if (StringUtil.isNotEmpty(dto.getId())) {
			/* 1.查询 */
			CmsFloorPlace bo = cmsFloorPlaceMapper.selectByPrimaryKey(dto.getId());
			cmsFloorPlaceRespDTO = conversionObject(bo);
		}

		return cmsFloorPlaceRespDTO;
	}

	/**
	 * TODO 查询楼层内容位置列表，无分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorPlaceSV#queryCmsFloorPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceReqDTO)
	 */
	@Override
	public List<CmsFloorPlaceRespDTO> queryCmsFloorPlaceList(CmsFloorPlaceReqDTO dto)
			throws BusinessException {

		/* 1.组装查询条件 */
		CmsFloorPlaceCriteria cmsFloorPlaceCriteria = new CmsFloorPlaceCriteria();
		Criteria criteria = cmsFloorPlaceCriteria.createCriteria();
		if (StringUtil.isNotEmpty(dto.getId())) {
			criteria.andIdEqualTo(dto.getId());
		}
		if (StringUtil.isNotBlank(dto.getStatus())) {
			criteria.andStatusEqualTo(dto.getStatus());
		}
		if (StringUtil.isNotEmpty(dto.getFloorTemplateId())) {
            criteria.andFloorTemplateIdEqualTo(dto.getFloorTemplateId());
        }
		cmsFloorPlaceCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");

		/* 2.迭代查询结果 */
		List<CmsFloorPlaceRespDTO> cmsFloorPlaceRespDTOList = new ArrayList<CmsFloorPlaceRespDTO>();
		List<CmsFloorPlace> cmsFloorPlaceList = cmsFloorPlaceMapper.selectByExample(cmsFloorPlaceCriteria);
		if (CollectionUtils.isNotEmpty(cmsFloorPlaceList)) {
			for (CmsFloorPlace bo : cmsFloorPlaceList) {
				CmsFloorPlaceRespDTO cmsFloorPlaceRespDTO = conversionObject(bo);
				cmsFloorPlaceRespDTOList.add(cmsFloorPlaceRespDTO);
			}
		}
		return cmsFloorPlaceRespDTOList;
	}

	/**
	 * TODO 查询楼层内容位置，分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorPlaceSV#queryCmsFloorPlacePage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceReqDTO)
	 */
	@Override
	public PageResponseDTO<CmsFloorPlaceRespDTO> queryCmsFloorPlacePage(
			CmsFloorPlaceReqDTO dto) throws BusinessException {

		/* 1.根据条件检索楼层内容位置 */
		CmsFloorPlaceCriteria cmsFloorPlaceCriteria = new CmsFloorPlaceCriteria();
		Criteria criteria = cmsFloorPlaceCriteria.createCriteria();
		if (StringUtil.isNotEmpty(dto.getId())) {
			criteria.andIdEqualTo(dto.getId());
		}
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtil.isNotEmpty(dto.getFloorTemplateId())) {
            criteria.andFloorTemplateIdEqualTo(dto.getFloorTemplateId());
        }
		cmsFloorPlaceCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
		cmsFloorPlaceCriteria.setLimitClauseCount(dto.getPageSize());
		cmsFloorPlaceCriteria.setLimitClauseStart(dto.getStartRowIndex());

		return super.queryByPagination(dto, cmsFloorPlaceCriteria, false,
				new PaginationCallback<CmsFloorPlace, CmsFloorPlaceRespDTO>() {

					@Override
					public List<CmsFloorPlace> queryDB(BaseCriteria criteria) {
						return cmsFloorPlaceMapper
								.selectByExample((CmsFloorPlaceCriteria) criteria);
					}

					@Override
					public long queryTotal(BaseCriteria criteria) {
						return cmsFloorPlaceMapper
								.countByExample((CmsFloorPlaceCriteria) criteria);
					}

					@Override
					public CmsFloorPlaceRespDTO warpReturnObject(CmsFloorPlace bo) {
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
	private CmsFloorPlaceRespDTO conversionObject(CmsFloorPlace bo) {
		CmsFloorPlaceRespDTO dto = new CmsFloorPlaceRespDTO();
		if (bo != null) {
			ObjectCopyUtil.copyObjValue(bo, dto, null, false);
		}
		// 1.遍历将编码转中文
		String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
		dto.setStatusZH(statusZH);
		return dto;
	}
}
