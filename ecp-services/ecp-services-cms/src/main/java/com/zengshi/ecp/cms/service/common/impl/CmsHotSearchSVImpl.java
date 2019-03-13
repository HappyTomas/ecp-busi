package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsHotSearchMapper;
import com.zengshi.ecp.cms.dao.model.CmsHotSearch;
import com.zengshi.ecp.cms.dao.model.CmsHotSearchCriteria;
import com.zengshi.ecp.cms.dao.model.CmsHotSearchCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsHotSearchSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateSV;
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
@Service("cmsHotSearchSV")
public class CmsHotSearchSVImpl extends GeneralSQLSVImpl implements ICmsHotSearchSV {

    @Resource(name = "SEQ_CMS_HOT_SEARCH")
    private PaasSequence seqCmsHotSearch;
    
    @Resource
    private CmsHotSearchMapper cmsHotSearchMapper;
    
    @Resource
    private ICmsPlaceSV cmsPlaceSV;

    @Resource
    private ICmsSiteSV cmsSiteSV;
    
    @Resource
    private ICmsTemplateSV cmsTemplateSV;
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsHotSearchSV#addCmsHotSearch(com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchReqDTO) 
     */
    @Override
    public CmsHotSearchRespDTO addCmsHotSearch(CmsHotSearchReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsHotSearch bo = new CmsHotSearch();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsHotSearch.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsHotSearchMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsHotSearchRespDTO respDTO = new CmsHotSearchRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsHotSearchSV#updateCmsHotSearch(com.zengshi.ecp.cms.dao.model.CmsHotSearch)
     */
    @Override
    public CmsHotSearchRespDTO updateCmsHotSearch(CmsHotSearchReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsHotSearch bo = new CmsHotSearch();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新广告的原子方法*/
        return this.updateCmsHotSearch(bo);
    }
    
    /** 
     * updateCmsHotSearch:(更新广告的原子方法). <br/> 
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
    public CmsHotSearchRespDTO updateCmsHotSearch(CmsHotSearch bo) throws BusinessException {
        cmsHotSearchMapper.updateByPrimaryKeySelective(bo);
        /*3.将结果返回*/
        CmsHotSearchRespDTO respDTO = new CmsHotSearchRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsHotSearchSV#deleteCmsHotSearch(java.lang.Long)
     */
    @Override
    public void deleteCmsHotSearch(String id) throws BusinessException {
        CmsHotSearch bo = new CmsHotSearch();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsHotSearch(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsHotSearchSV#deleteCmsHotSearchBatch(java.util.List)
     */
    @Override
    public void deleteCmsHotSearchBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsHotSearch(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsHotSearchSV#changeStatusCmsHotSearch(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsHotSearch(String id, String status) throws BusinessException {
        CmsHotSearch bo = new CmsHotSearch();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsHotSearch(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsHotSearchSV#changeStatusCmsHotSearchBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsHotSearchBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsHotSearch(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsHotSearchSV#queryCmsHotSearch(com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchReqDTO)
     */
    @Override
    public CmsHotSearchRespDTO queryCmsHotSearch(CmsHotSearchReqDTO dto) throws BusinessException {
        CmsHotSearchRespDTO cmsHotSearchRespDTO = new CmsHotSearchRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsHotSearch bo = cmsHotSearchMapper.selectByPrimaryKey(dto.getId());
            cmsHotSearchRespDTO = conversionObject(bo);
        }
        
        return cmsHotSearchRespDTO;
    }
    
    /**
     * TODO 查询广告列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsHotSearchSV#queryCmsHotSearchList(com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchReqDTO)
     */
    @Override
    public List<CmsHotSearchRespDTO> queryCmsHotSearchList(CmsHotSearchReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsHotSearchCriteria cmsHotSearchCriteria = new CmsHotSearchCriteria();
        Criteria criteria = cmsHotSearchCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getHotSearchName())) {
            criteria.andHotSearchNameLike("%" + dto.getHotSearchName() + "%");
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
       
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if (StringUtil.isNotEmpty(dto.getTemplateId())) {
            criteria.andTemplateIdEqualTo(dto.getTemplateId());
        }
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
       
        cmsHotSearchCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsHotSearchRespDTO> cmsHotSearchRespDTOList =  new ArrayList<CmsHotSearchRespDTO>();
        List<CmsHotSearch> cmsHotSearchList = cmsHotSearchMapper.selectByExample(cmsHotSearchCriteria);
        if(CollectionUtils.isNotEmpty(cmsHotSearchList)){
            for(CmsHotSearch bo:cmsHotSearchList){
                CmsHotSearchRespDTO cmsHotSearchRespDTO = conversionObject(bo);
                cmsHotSearchRespDTOList.add(cmsHotSearchRespDTO);
            }
        }
        return cmsHotSearchRespDTOList;
    }


    /** 
     * TODO 查询广告，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsHotSearchSV#queryCmsHotSearchPage(com.zengshi.ecp.cms.dubbo.dto.CmsHotSearchReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsHotSearchRespDTO> queryCmsHotSearchPage(CmsHotSearchReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索广告 */
        CmsHotSearchCriteria cmsHotSearchCriteria = new CmsHotSearchCriteria();
        Criteria criteria = cmsHotSearchCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getHotSearchName())) {
            criteria.andHotSearchNameLike("%" + dto.getHotSearchName() + "%");
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
       
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if (StringUtil.isNotEmpty(dto.getTemplateId())) {
            criteria.andTemplateIdEqualTo(dto.getTemplateId());
        }
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
       
        
        cmsHotSearchCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        cmsHotSearchCriteria.setLimitClauseCount(dto.getPageSize());
        cmsHotSearchCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsHotSearchCriteria,false,new PaginationCallback<CmsHotSearch, CmsHotSearchRespDTO>(){

            @Override
            public List<CmsHotSearch> queryDB(BaseCriteria criteria) {
                return cmsHotSearchMapper.selectByExample((CmsHotSearchCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsHotSearchMapper.countByExample((CmsHotSearchCriteria)criteria);
            }

            @Override
            public CmsHotSearchRespDTO warpReturnObject(CmsHotSearch bo) {
                return  conversionObject(bo);
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
    private CmsHotSearchRespDTO conversionObject(CmsHotSearch bo){
        CmsHotSearchRespDTO dto = new CmsHotSearchRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            CmsPlaceReqDTO cmsPlaceDTO = new CmsPlaceReqDTO();
            cmsPlaceDTO.setId(dto.getPlaceId());
            CmsPlaceRespDTO cmsPlaceRespDTO = cmsPlaceSV.queryCmsPlace(cmsPlaceDTO);
            if (cmsPlaceRespDTO != null) {
                dto.setPlaceName(cmsPlaceRespDTO.getPlaceName());
            }
        }
        // 2 查询站点服务，获取站点对应的名称
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            CmsSiteReqDTO reqDTO = new CmsSiteReqDTO();
            reqDTO.setId(bo.getSiteId());
            CmsSiteRespDTO respDTO = cmsSiteSV.queryCmsSite(reqDTO);
            if(respDTO != null){
                dto.setSiteName(respDTO.getSiteName());
            }
        }
        
        // 3 查询模板服务，获取模板对应的名称
        if (StringUtil.isNotEmpty(dto.getTemplateId())) {
            CmsTemplateReqDTO reqDTO = new CmsTemplateReqDTO();
            reqDTO.setId(bo.getTemplateId());
            CmsTemplateRespDTO respDTO = cmsTemplateSV.queryCmsTemplate(reqDTO);
            if(respDTO != null){
                dto.setTemplateName(respDTO.getTemplateName());
            }
        }
        
        //4.遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        
        return dto;
    }

}
