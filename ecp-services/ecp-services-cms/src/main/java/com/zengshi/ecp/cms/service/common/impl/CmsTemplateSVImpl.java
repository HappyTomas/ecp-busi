package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsTemplateMapper;
import com.zengshi.ecp.cms.dao.model.CmsTemplate;
import com.zengshi.ecp.cms.dao.model.CmsTemplateCriteria;
import com.zengshi.ecp.cms.dao.model.CmsTemplateCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
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
@Service("cmsTemplateSV")
public class CmsTemplateSVImpl extends GeneralSQLSVImpl implements ICmsTemplateSV {

    @Resource(name = "SEQ_CMS_TEMPLATE")
    private PaasSequence seqCmsTemplate;
    
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    @Resource
    private ICmsTemplateSV cmsTemplateSV;
    
    @Resource
    private ICmsSiteSV cmsSiteSV;
    
    @Resource
    private CmsTemplateMapper cmsTemplateMapper;
    

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateSV#saveCmsTemplate(com.zengshi.ecp.cms.dao.model.CmsTemplate)
     */
    @Override
    public CmsTemplateRespDTO addCmsTemplate(CmsTemplateReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsTemplate bo = new CmsTemplate();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsTemplate.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsTemplateMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsTemplateRespDTO respDTO = new CmsTemplateRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateSV#updateCmsTemplate(com.zengshi.ecp.cms.dao.model.CmsTemplate)
     */
    @Override
    public CmsTemplateRespDTO updateCmsTemplate(CmsTemplateReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsTemplate bo = new CmsTemplate();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsTemplate(bo);
    }
    
    /** 
     * updateCmsTemplate:(更新楼层的原子方法). <br/> 
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
    public CmsTemplateRespDTO updateCmsTemplate(CmsTemplate bo) throws BusinessException {
        cmsTemplateMapper.updateByPrimaryKeySelective(bo);
        CmsTemplateRespDTO respDTO = new CmsTemplateRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateSV#deleteCmsTemplate(java.lang.Long)
     */
    @Override
    public void deleteCmsTemplate(String id) throws BusinessException {
        CmsTemplate bo = new CmsTemplate();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsTemplate(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateSV#deleteCmsTemplateBatch(java.util.List)
     */
    @Override
    public void deleteCmsTemplateBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsTemplate(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateSV#changeStatusCmsTemplate(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsTemplate(String id, String status) throws BusinessException {
        CmsTemplate bo = new CmsTemplate();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsTemplate(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateSV#changeStatusCmsTemplateBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsTemplateBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsTemplate(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateSV#queryCmsTemplate(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO)
     */
    @Override
    public CmsTemplateRespDTO queryCmsTemplate(CmsTemplateReqDTO dto) throws BusinessException {
        CmsTemplateRespDTO cmsTemplateRespDTO = new CmsTemplateRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsTemplate bo = cmsTemplateMapper.selectByPrimaryKey(dto.getId());
            cmsTemplateRespDTO = conversionObject(bo);
        }
        
        return cmsTemplateRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateSV#queryCmsTemplateList(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO)
     */
    @Override
    public List<CmsTemplateRespDTO> queryCmsTemplateList(CmsTemplateReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsTemplateCriteria cmsTemplateCriteria = new CmsTemplateCriteria();
        Criteria criteria = cmsTemplateCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if (StringUtil.isNotBlank(dto.getTemplateName())) {
            criteria.andTemplateNameLike("%"+dto.getTemplateName()+"%");
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtil.isNotBlank(dto.getTemplateClass())) {
            criteria.andTemplateClassEqualTo(dto.getTemplateClass());
        }
        cmsTemplateCriteria.setOrderByClause("CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsTemplateRespDTO> cmsTemplateRespDTOList =  new ArrayList<CmsTemplateRespDTO>();
        List<CmsTemplate> cmsTemplateList = cmsTemplateMapper.selectByExample(cmsTemplateCriteria);
        if(CollectionUtils.isNotEmpty(cmsTemplateList)){
            for(CmsTemplate bo :cmsTemplateList){
                CmsTemplateRespDTO cmsTemplateRespDTO = conversionObject(bo);
                cmsTemplateRespDTOList.add(cmsTemplateRespDTO);
            }
        }
        
        return cmsTemplateRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateSV#queryCmsTemplatePage(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsTemplateRespDTO> queryCmsTemplatePage(CmsTemplateReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsTemplateCriteria cmsTemplateCriteria = new CmsTemplateCriteria();
        Criteria criteria = cmsTemplateCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        
        if (StringUtil.isNotBlank(dto.getTemplateName())) {
            criteria.andTemplateNameLike("%"+dto.getTemplateName()+"%");
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtil.isNotBlank(dto.getTemplateClass())) {
            criteria.andTemplateClassEqualTo(dto.getTemplateClass());
        }
        cmsTemplateCriteria.setOrderByClause("CREATE_TIME DESC");
        cmsTemplateCriteria.setLimitClauseCount(dto.getPageSize());
        cmsTemplateCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsTemplateCriteria,false,new PaginationCallback<CmsTemplate, CmsTemplateRespDTO>(){

            @Override
            public List<CmsTemplate> queryDB(BaseCriteria criteria) {
                return cmsTemplateMapper.selectByExample((CmsTemplateCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsTemplateMapper.countByExample((CmsTemplateCriteria)criteria);
            }

            @Override
            public CmsTemplateRespDTO warpReturnObject(CmsTemplate bo) {
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
    private CmsTemplateRespDTO conversionObject(CmsTemplate bo){
        CmsTemplateRespDTO dto = new CmsTemplateRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询站点服务，获取站点对应的名称
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            CmsSiteReqDTO cmsSiteReqDTO = new CmsSiteReqDTO();
            cmsSiteReqDTO.setId(bo.getSiteId());
            CmsSiteRespDTO cmsSiteRespDTO = cmsSiteSV.queryCmsSite(cmsSiteReqDTO);
            if(cmsSiteRespDTO != null){
                dto.setSiteZH(cmsSiteRespDTO.getSiteName());
            }
        }
        
        // 2.遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String templateClassZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_TEMPLATE_CLASS, dto.getTemplateClass());
        dto.setTemplateClassZH(templateClassZH);
        
        return dto;
    }

}
