package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsFloorTemplateMapper;
import com.zengshi.ecp.cms.dao.model.CmsFloorTemplate;
import com.zengshi.ecp.cms.dao.model.CmsFloorTemplateCriteria;
import com.zengshi.ecp.cms.dao.model.CmsFloorTemplateCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorPlaceSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTemplateSV;
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

@Service("cmsFloorTemplateSV")
public class CmsFloorTemplateSVImpl extends GeneralSQLSVImpl implements ICmsFloorTemplateSV{

	@Resource(name = "SEQ_CMS_FLOOR_TEMPLATE")
	private PaasSequence seqCmsFloorTemplate;
	@Resource
    private CmsFloorTemplateMapper cmsFloorTemplateMapper;
	@Resource
    private ICmsFloorPlaceSV cmsFloorPlaceSV;

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTemplateSV#addCmsFloorTemplate(com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateReqDTO)
	 */
	@Override
	public CmsFloorTemplateRespDTO addCmsFloorTemplate(CmsFloorTemplateReqDTO dto)
			throws BusinessException {
		/* 1.将入参组装成bo */
		CmsFloorTemplate bo = new CmsFloorTemplate();
		if (dto != null) {
			ObjectCopyUtil.copyObjValue(dto, bo, null, false);
		}
		long id = seqCmsFloorTemplate.nextValue();
		bo.setId(id);
		bo.setCreateTime(DateUtil.getSysDate());
		bo.setCreateStaff(dto.getStaff().getId());
		/* 2.调楼层模板dao层接口 */
        cmsFloorTemplateMapper.insertSelective(bo);
        /* 3.调楼层模板内容位置dao层接口 */
		if(CollectionUtils.isNotEmpty(dto.getFloorPlaceReqDTOList())){
		    for(CmsFloorPlaceReqDTO floorPlaceReqDTO : dto.getFloorPlaceReqDTOList()){
		        floorPlaceReqDTO.setFloorTemplateId(id);
		        cmsFloorPlaceSV.addCmsFloorPlace(floorPlaceReqDTO);
		    }
		}

		/* 4.将结果返回 */
		CmsFloorTemplateRespDTO respDTO = new CmsFloorTemplateRespDTO();
		if (bo != null) {
			ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
		}
		return respDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTemplateSV#updateCmsFloorTemplate(com.zengshi.ecp.cms.dao.model.CmsFloorTemplate)
	 */
	@Override
	public CmsFloorTemplateRespDTO updateCmsFloorTemplate(CmsFloorTemplateReqDTO dto)
			throws BusinessException {
		/* 1.将入参组装成bo */
		CmsFloorTemplate bo = new CmsFloorTemplate();
		if (dto != null) {
			ObjectCopyUtil.copyObjValue(dto, bo, null, false);
		}
		bo.setUpdateStaff(dto.getStaff().getId());
		bo.setUpdateTime(DateUtil.getSysDate());
		
		/* 2.更新楼层内容位置
		 * (1)先判断楼层内容位置ID是否存在，如果存在，则直接更新
		 * (2)不存在，则先将原先的记录失效，再添加新的记录。
		 */
		List<CmsFloorPlaceReqDTO> floorPlaceReqDTOList = dto.getFloorPlaceReqDTOList();
        if(CollectionUtils.isNotEmpty(floorPlaceReqDTOList)){
            for(int i=0;i<floorPlaceReqDTOList.size();i++){
                CmsFloorPlaceReqDTO floorPlaceReqDTO = floorPlaceReqDTOList.get(i);
                if(floorPlaceReqDTO != null){
                    //更新
                    if(StringUtil.isNotEmpty(floorPlaceReqDTO.getId())){
                        cmsFloorPlaceSV.updateCmsFloorPlace(floorPlaceReqDTO);
                    }else{//新增（先将原先的记录失效，再添加新的记录。）
                        if(i == 0){
                            /* 2.查询楼层内容位置 */
                            CmsFloorPlaceReqDTO reqDTO = new CmsFloorPlaceReqDTO();
                            reqDTO.setFloorTemplateId(dto.getId());
                            reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                            List<CmsFloorPlaceRespDTO> floorPlaceRespDTOList= cmsFloorPlaceSV.queryCmsFloorPlaceList(reqDTO);
                            
                            /* 3.更新楼层内容位置 */
                            for(CmsFloorPlaceRespDTO respDTOTemp : floorPlaceRespDTOList){
                                CmsFloorPlaceReqDTO reqDTOTemp = new CmsFloorPlaceReqDTO();
                                reqDTOTemp.setId(respDTOTemp.getId());
                                reqDTOTemp.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
                                cmsFloorPlaceSV.updateCmsFloorPlace(reqDTOTemp);
                            }
                        }
                        floorPlaceReqDTO.setFloorTemplateId(dto.getId());
                        cmsFloorPlaceSV.addCmsFloorPlace(floorPlaceReqDTO);
                    } 
                }
            }
        }

		/* 3.更新楼层内容位置的原子方法 */
		return this.updateCmsFloorTemplate(bo);
	}

	/**
	 * updateCmsFloorTemplate:(更新楼层内容位置的原子方法). <br/>
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
	public CmsFloorTemplateRespDTO updateCmsFloorTemplate(CmsFloorTemplate bo)
			throws BusinessException {
	    /* 1.更新楼层模板 */
		cmsFloorTemplateMapper.updateByPrimaryKeySelective(bo);
	    
		/* 2.将结果返回 */
		CmsFloorTemplateRespDTO respDTO = new CmsFloorTemplateRespDTO();
		if (bo != null) {
			ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
		}
		return respDTO;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTemplateSV#deleteCmsFloorTemplate(java.lang.Long)
	 */
	@Override
	public void deleteCmsFloorTemplate(String id) throws BusinessException {
	    /* 1.更新楼层模板 */
	    CmsFloorTemplate bo = new CmsFloorTemplate();
		bo.setId(Long.parseLong(id));
		bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
		this.updateCmsFloorTemplate(bo);
		
		//使失效楼层模板时，需要更新楼层内容位置状态（使失效）
		/* 2.查询楼层内容位置 */
		CmsFloorPlaceReqDTO dto = new CmsFloorPlaceReqDTO();
		dto.setFloorTemplateId(Long.parseLong(id));
		dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
		List<CmsFloorPlaceRespDTO> floorPlaceRespDTOList= cmsFloorPlaceSV.queryCmsFloorPlaceList(dto);
		
		/* 3.更新楼层内容位置 */
		for(CmsFloorPlaceRespDTO floorPlaceRespDTO : floorPlaceRespDTOList){
		    CmsFloorPlaceReqDTO floorPlaceReqDTO = new CmsFloorPlaceReqDTO();
	        floorPlaceReqDTO.setId(floorPlaceRespDTO.getId());
	        floorPlaceReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
	        cmsFloorPlaceSV.updateCmsFloorPlace(floorPlaceReqDTO);
		}
		
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTemplateSV#deleteCmsFloorTemplateBatch(java.util.List)
	 */
	@Override
	public void deleteCmsFloorTemplateBatch(List<String> list)
			throws BusinessException {
		for (int i = 0; i < list.size(); i++) {
			String id = list.get(i);
			if (StringUtil.isNotBlank(id)) {
				this.deleteCmsFloorTemplate(id);
			}
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTemplateSV#changeStatusCmsFloorTemplate(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsFloorTemplate(String id, String status)
			throws BusinessException {
	    
	    /* 1.更新楼层模板 */
		CmsFloorTemplate bo = new CmsFloorTemplate();
		bo.setId(Long.parseLong(id));
		bo.setStatus(status);
		this.updateCmsFloorTemplate(bo);
		
		//发布楼层模板或者撤销模板时，不需要更新楼层内容位置状态
		/* 2.查询楼层内容位置 */
        /*CmsFloorPlaceReqDTO dto = new CmsFloorPlaceReqDTO();
        dto.setFloorTemplateId(Long.parseLong(id));
        List<CmsFloorPlaceRespDTO> floorPlaceRespDTOList= cmsFloorPlaceSV.queryCmsFloorPlaceList(dto);*/
        
        /* 3.更新楼层内容位置 */
        /*for(CmsFloorPlaceRespDTO floorPlaceRespDTO : floorPlaceRespDTOList){
            CmsFloorPlaceReqDTO floorPlaceReqDTO = new CmsFloorPlaceReqDTO();
            floorPlaceReqDTO.setId(floorPlaceRespDTO.getId());
            floorPlaceReqDTO.setStatus(status);
            cmsFloorPlaceSV.updateCmsFloorPlace(floorPlaceReqDTO);
        }*/
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTemplateSV#changeStatusCmsFloorTemplateBatch(java.util.List,
	 *      java.lang.String)
	 */
	@Override
	public void changeStatusCmsFloorTemplateBatch(List<String> list, String status)
			throws BusinessException {
		for (int i = 0; i < list.size(); i++) {
			String id = list.get(i);
			if (StringUtil.isNotBlank(id)) {
				this.changeStatusCmsFloorTemplate(id, status);
			}
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTemplateSV#queryCmsFloorTemplate(com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateReqDTO)
	 */
	@Override
	public CmsFloorTemplateRespDTO queryCmsFloorTemplate(CmsFloorTemplateReqDTO dto)
			throws BusinessException {
		CmsFloorTemplateRespDTO cmsFloorTemplateRespDTO = new CmsFloorTemplateRespDTO();
		if (StringUtil.isNotEmpty(dto.getId())) {
			/* 1.查询楼层模板 */
			CmsFloorTemplate bo = cmsFloorTemplateMapper.selectByPrimaryKey(dto.getId());
			cmsFloorTemplateRespDTO = conversionObject(bo);
			
			/* 2.查询楼层模板的内容位置 */
			CmsFloorPlaceReqDTO floorPlaceReqDTO  = new CmsFloorPlaceReqDTO();
			floorPlaceReqDTO.setFloorTemplateId(dto.getId());
			floorPlaceReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
			List<CmsFloorPlaceRespDTO> floorPlaceRespDTOList = cmsFloorPlaceSV.queryCmsFloorPlaceList(floorPlaceReqDTO);
			cmsFloorTemplateRespDTO.setFloorPlaceRespDTOList(floorPlaceRespDTOList);
		}

		return cmsFloorTemplateRespDTO;
	}

	/**
	 * TODO 查询楼层模板列表，无分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTemplateSV#queryCmsFloorTemplateList(com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateReqDTO)
	 */
	@Override
	public List<CmsFloorTemplateRespDTO> queryCmsFloorTemplateList(CmsFloorTemplateReqDTO dto)
			throws BusinessException {

		/* 1.组装查询条件 */
		CmsFloorTemplateCriteria cmsFloorTemplateCriteria = new CmsFloorTemplateCriteria();
		Criteria criteria = cmsFloorTemplateCriteria.createCriteria();
		if (StringUtil.isNotEmpty(dto.getId())) {
			criteria.andIdEqualTo(dto.getId());
		}
		if (StringUtil.isNotBlank(dto.getTemplateName())) {
			criteria.andTemplateNameLike("%" + dto.getTemplateName() + "%");
		}
		if (StringUtil.isNotBlank(dto.getStatus())) {
			criteria.andStatusEqualTo(dto.getStatus());
		}
		if (StringUtil.isNotEmpty(dto.getTemplateNo())) {
            criteria.andTemplateNoEqualTo(dto.getTemplateNo());
        }
		if (StringUtil.isNotEmpty(dto.getFloorPlaceNum())){
            criteria.andFloorPlaceNumEqualTo(dto.getFloorPlaceNum());
        }
		cmsFloorTemplateCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");

		/* 2.迭代查询结果 */
		List<CmsFloorTemplateRespDTO> cmsFloorTemplateRespDTOList = new ArrayList<CmsFloorTemplateRespDTO>();
		List<CmsFloorTemplate> cmsFloorTemplateList = cmsFloorTemplateMapper.selectByExample(cmsFloorTemplateCriteria);
		if (CollectionUtils.isNotEmpty(cmsFloorTemplateList)) {
			for (CmsFloorTemplate bo : cmsFloorTemplateList) {
				CmsFloorTemplateRespDTO cmsFloorTemplateRespDTO = conversionObject(bo);
				cmsFloorTemplateRespDTOList.add(cmsFloorTemplateRespDTO);
			}
		}
		return cmsFloorTemplateRespDTOList;
	}

	/**
	 * TODO 查询楼层模板，分页（可选）.
	 * 
	 * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTemplateSV#queryCmsFloorTemplatePage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateReqDTO)
	 */
	@Override
	public PageResponseDTO<CmsFloorTemplateRespDTO> queryCmsFloorTemplatePage(
			CmsFloorTemplateReqDTO dto) throws BusinessException {

		/* 1.根据条件检索楼层内容位置 */
		CmsFloorTemplateCriteria cmsFloorTemplateCriteria = new CmsFloorTemplateCriteria();
		Criteria criteria = cmsFloorTemplateCriteria.createCriteria();
		if (StringUtil.isNotEmpty(dto.getId())) {
			criteria.andIdEqualTo(dto.getId());
		}
        if (StringUtil.isNotBlank(dto.getTemplateName())) {
            criteria.andTemplateNameLike("%" + dto.getTemplateName() + "%");
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtil.isNotEmpty(dto.getTemplateNo())) {
            criteria.andTemplateNoEqualTo(dto.getTemplateNo());
        }
        if (StringUtil.isNotEmpty(dto.getFloorPlaceNum())){
            criteria.andFloorPlaceNumEqualTo(dto.getFloorPlaceNum());
        }
		cmsFloorTemplateCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
		cmsFloorTemplateCriteria.setLimitClauseCount(dto.getPageSize());
		cmsFloorTemplateCriteria.setLimitClauseStart(dto.getStartRowIndex());

		return super.queryByPagination(dto, cmsFloorTemplateCriteria, false,
				new PaginationCallback<CmsFloorTemplate, CmsFloorTemplateRespDTO>() {

					@Override
					public List<CmsFloorTemplate> queryDB(BaseCriteria criteria) {
						return cmsFloorTemplateMapper
								.selectByExample((CmsFloorTemplateCriteria) criteria);
					}

					@Override
					public long queryTotal(BaseCriteria criteria) {
						return cmsFloorTemplateMapper
								.countByExample((CmsFloorTemplateCriteria) criteria);
					}

					@Override
					public CmsFloorTemplateRespDTO warpReturnObject(CmsFloorTemplate bo) {
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
	private CmsFloorTemplateRespDTO conversionObject(CmsFloorTemplate bo) {
		CmsFloorTemplateRespDTO dto = new CmsFloorTemplateRespDTO();
		if (bo != null) {
			ObjectCopyUtil.copyObjValue(bo, dto, null, false);
		}
		// 1.遍历将编码转中文
		String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
		dto.setStatusZH(statusZH);
		return dto;
	}
}
