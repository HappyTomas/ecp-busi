package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsPlaceMapper;
import com.zengshi.ecp.cms.dao.model.CmsPlace;
import com.zengshi.ecp.cms.dao.model.CmsPlaceCriteria;
import com.zengshi.ecp.cms.dao.model.CmsPlaceCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV;
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
@Service("cmsPlaceSV")
public class CmsPlaceSVImpl extends GeneralSQLSVImpl implements ICmsPlaceSV {

    @Resource
    private CmsPlaceMapper mapper;

    @Resource(name = "SEQ_CMS_PLACE")
    private PaasSequence seq;
    
    @Resource
    private ICmsSiteSV cmsSiteSV;
    
    @Resource
    private ICmsTemplateRSV cmsTemplateRSV;

    @Override
    public CmsPlaceRespDTO addCmsPlace(CmsPlaceReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPlace bo = new CmsPlace();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seq.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        mapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsPlaceRespDTO respDTO = new CmsPlaceRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV#deleteCmsPlace(java.lang.String) 
     */
    @Override
    public void deleteCmsPlace(String id) throws BusinessException {
        CmsPlace bo = new CmsPlace();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        mapper.updateByPrimaryKeySelective(bo);
    }
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAdvertiseSV#deleteCmsAdvertiseBatch(java.util.List)
     */
    @Override
    public void deleteCmsPlaceBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsPlace(id);
            }
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV#updateCmsPlace(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public CmsPlaceRespDTO updateCmsPlace(CmsPlaceReqDTO dto) throws BusinessException {
    
        /*1.将入参组装成bo*/
        CmsPlace bo = new CmsPlace();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新内容位置方法*/
        mapper.updateByPrimaryKeySelective(bo);
        
        /*3.将结果返回*/
        CmsPlaceRespDTO respDTO = new CmsPlaceRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        
        return respDTO;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public List<CmsPlaceRespDTO> queryCmsPlaceList(CmsPlaceReqDTO dto) throws BusinessException {
        
        /*1.组装查询条件*/
        CmsPlaceCriteria cmsPlaceCriteria = new CmsPlaceCriteria();
        CmsPlaceCriteria.Criteria criteria = cmsPlaceCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getPlaceName())) {
            criteria.andPlaceNameLike("%"+dto.getPlaceName()+"%");
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtil.isNotEmpty(dto.getTemplateId())) {
            criteria.andTemplateIdEqualTo(dto.getTemplateId());
        }
        if (StringUtil.isNotBlank(dto.getPlaceType())) {
            criteria.andPlaceTypeEqualTo(dto.getPlaceType());
        }
        cmsPlaceCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        
        /* 2.根据条件检索楼层  */
        List<CmsPlaceRespDTO> cmsPlaceRespDTOList =  new ArrayList<CmsPlaceRespDTO>();
        List<CmsPlace> cmsPlaceList = mapper.selectByExample(cmsPlaceCriteria);
        if(CollectionUtils.isNotEmpty(cmsPlaceList)){
            for(CmsPlace bo :cmsPlaceList){
                CmsPlaceRespDTO cmsPlaceRespDTO = conversionObject(bo);
                cmsPlaceRespDTOList.add(cmsPlaceRespDTO);
            }
        }
        
        return cmsPlaceRespDTOList;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV#queryCmsPlacePage(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    public PageResponseDTO<CmsPlaceRespDTO> queryCmsPlacePage(CmsPlaceReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索内容位置*/
        CmsPlaceCriteria cmsPlaceCriteria = new CmsPlaceCriteria();
        Criteria criteria = cmsPlaceCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getPlaceName())) {
            criteria.andPlaceNameLike("%" + dto.getPlaceName() + "%");
        }
        if (dto.getSiteId()!= null){
        	criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if(dto.getTemplateId()!=null)
        {
        	criteria.andTemplateIdEqualTo(dto.getTemplateId());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        
        cmsPlaceCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        cmsPlaceCriteria.setLimitClauseCount(dto.getPageSize());
        cmsPlaceCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsPlaceCriteria,false,new PaginationCallback<CmsPlace, CmsPlaceRespDTO>(){

            @Override
            public List<CmsPlace> queryDB(BaseCriteria criteria) {
                return mapper.selectByExample((CmsPlaceCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return mapper.countByExample((CmsPlaceCriteria)criteria);
            }

            @Override
            public CmsPlaceRespDTO warpReturnObject(CmsPlace bo) {
                return conversionObject(bo);
            }
        
        });

    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV#queryCmsPlace(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
     */
    @Override
    public CmsPlaceRespDTO queryCmsPlace(CmsPlaceReqDTO dto) throws BusinessException {
        CmsPlaceRespDTO cmsPlaceRespDTO = new CmsPlaceRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsPlace bo = mapper.selectByPrimaryKey(dto.getId());
            cmsPlaceRespDTO = conversionObject(bo);
        }
        return cmsPlaceRespDTO;
    }
    
    /** 
     * updateCmsFloor:(更新楼层的原子方法). <br/> 
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
    public CmsPlaceRespDTO updateCmsPlace(CmsPlace bo) throws BusinessException {
        mapper.updateByPrimaryKeySelective(bo);
        CmsPlaceRespDTO respDTO = new CmsPlaceRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV#changeStatusCmsFloor(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPlace(String id, String status) throws BusinessException {
        CmsPlace bo = new CmsPlace();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsPlace(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV#changeStatusCmsFloorBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPlaceBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsPlace(id, status);
            }
        }
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
    private CmsPlaceRespDTO conversionObject(CmsPlace bo){
        CmsPlaceRespDTO dto = new CmsPlaceRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询站点服务，获取站点对应的名称
        if (dto.getSiteId()!=null) {
            CmsSiteReqDTO cmsSiteReqDTO = new CmsSiteReqDTO();
            cmsSiteReqDTO.setId(dto.getSiteId());
            CmsSiteRespDTO cmsSiteRespDTO = cmsSiteSV.queryCmsSite(cmsSiteReqDTO);
            if (cmsSiteRespDTO != null) {
                dto.setSiteZH(cmsSiteRespDTO.getSiteName());
            }
        }
        // 2 根据模板服务，获取模板对应名称。
        if (dto.getTemplateId()!= null) {
            CmsTemplateReqDTO cmsTemplateReqDTO = new CmsTemplateReqDTO();
            cmsTemplateReqDTO.setId(dto.getTemplateId());
            CmsTemplateRespDTO templateRespDTO = cmsTemplateRSV.queryCmsTemplate(cmsTemplateReqDTO);
            if(templateRespDTO != null){
                dto.setTemplateZH(templateRespDTO.getTemplateName());
            }
        }
        
        // 3. 遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String placeTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_PLACE_TYPE, dto.getPlaceType());
        dto.setPlaceTypeZH(placeTypeZH);
        String isscrollZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.PUBLIC_PARAM_ISNOT, dto.getIsscroll());
        dto.setIsscrollZH(isscrollZH);
        
        return dto;
    }

}
