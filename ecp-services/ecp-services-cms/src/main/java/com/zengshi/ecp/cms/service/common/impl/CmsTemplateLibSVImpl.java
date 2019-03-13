package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsTemplateLibMapper;
import com.zengshi.ecp.cms.dao.model.CmsTemplateLib;
import com.zengshi.ecp.cms.dao.model.CmsTemplateLibCriteria;
import com.zengshi.ecp.cms.dao.model.CmsTemplateLibCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutItemSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLibSV;
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
@Service("cmsTemplateLibSV")
public class CmsTemplateLibSVImpl extends GeneralSQLSVImpl implements ICmsTemplateLibSV {

    @Resource(name = "SEQ_CMS_TEMPLATE_LIB")
    private PaasSequence seqCmsTemplateLib;
    @Resource
    private CmsTemplateLibMapper cmsTemplateLibMapper;
    @Resource
    private ICmsTemplateLayoutSV templateLayoutSV;
    @Resource
    private ICmsTemplateLayoutItemSV templateLayoutItemSV;
    @Resource
    private ICmsLayoutTypeSV cmsLayoutTypeSV;
    @Resource
    private ICmsTemplateLayoutSV cmsTemplateLayoutSV;
    @Resource
    private ICmsSiteSV sv;
    /**
     * TODO 是否存在模块.
     * 存在：true
     * 不存在：false
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageTypeSV#queryCmsPageTypeList(com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO)
     */
    @Override
    public boolean isExistTemplateLib(CmsTemplateLibReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsTemplateLibCriteria cmsTemplateLibCriteria = new CmsTemplateLibCriteria();
        Criteria criteria = cmsTemplateLibCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdNotEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getTemplateName())) {
            criteria.andTemplateNameEqualTo(dto.getTemplateName());
        }
        if (dto.getStatusSet() == null) {
            dto.setStatusSet(new HashSet<String>());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            dto.getStatusSet().add(dto.getStatus());
        }
        if (CollectionUtils.isNotEmpty(dto.getStatusSet())) {
            criteria.andStatusIn(new ArrayList<String>(dto.getStatusSet()));
        }
        
        cmsTemplateLibCriteria.setOrderByClause("CREATE_TIME DESC");
        
        boolean isExist = false;//不存在
        /*2.迭代查询结果*/
        List<CmsTemplateLib> cmsTemplateLibList = cmsTemplateLibMapper.selectByExample(cmsTemplateLibCriteria);
        if(cmsTemplateLibList != null && cmsTemplateLibList.size()>0){
            isExist = true;
        }
        return isExist;
    }


    /**
     * TODO 新增模板.
     * 如果平台类型为WAP，则同时生成模板布局
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLibSV#saveCmsTemplateLib(com.zengshi.ecp.cms.dao.model.CmsTemplateLib)
     */
    @Override
    public CmsTemplateLibRespDTO addCmsTemplateLib(CmsTemplateLibReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsTemplateLib bo = new CmsTemplateLib();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsTemplateLib.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsTemplateLibMapper.insertSelective(bo);
        
        /*3.如果平台类型为移动端，则自动生成布局*/
        if(CmsConstants.PlatformType.CMS_PLATFORMTYPE_03.equals(dto.getPlatformType())){
            //根据页面类型获取该页面类型下的布局类型
            CmsLayoutTypeReqDTO layoutTypeReqDTO = new CmsLayoutTypeReqDTO();
            layoutTypeReqDTO.setPageTypeId(dto.getPageTypeId());
            List<CmsLayoutTypeRespDTO> layoutTypeRespDTOList = cmsLayoutTypeSV.queryCmsLayoutTypeList(layoutTypeReqDTO);
            if(CollectionUtils.isNotEmpty(layoutTypeRespDTOList) && layoutTypeRespDTOList.get(0) != null){
                CmsTemplateLayoutReqDTO templateLayoutReqDTO = new CmsTemplateLayoutReqDTO();
                templateLayoutReqDTO.setTemplateId(id);
                templateLayoutReqDTO.setLayoutTypeId(layoutTypeRespDTOList.get(0).getId());
                templateLayoutReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                templateLayoutReqDTO.setShowOrder(1);
                cmsTemplateLayoutSV.addCmsTemplateLayout(templateLayoutReqDTO);
            }
        }
        
        /*4.将结果返回*/
        CmsTemplateLibRespDTO respDTO = new CmsTemplateLibRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }
    
    /**
     * TODO 新增模板但不生成模板布局.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLibSV#saveCmsTemplateLib(com.zengshi.ecp.cms.dao.model.CmsTemplateLib)
     */
    @Override
    public CmsTemplateLibRespDTO addCmsTemplateLibNoLayout(CmsTemplateLibReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsTemplateLib bo = new CmsTemplateLib();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsTemplateLib.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsTemplateLibMapper.insertSelective(bo);
        
        /*4.将结果返回*/
        CmsTemplateLibRespDTO respDTO = new CmsTemplateLibRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLibSV#updateCmsTemplateLib(com.zengshi.ecp.cms.dao.model.CmsTemplateLib)
     */
    @Override
    public CmsTemplateLibRespDTO updateCmsTemplateLib(CmsTemplateLibReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsTemplateLib bo = new CmsTemplateLib();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsTemplateLib(bo);
    }
    
    /** 
     * updateCmsTemplateLib:(更新楼层的原子方法). <br/> 
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
    public CmsTemplateLibRespDTO updateCmsTemplateLib(CmsTemplateLib bo) throws BusinessException {
        cmsTemplateLibMapper.updateByPrimaryKeySelective(bo);
        CmsTemplateLibRespDTO respDTO = new CmsTemplateLibRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }
    
    /** 
     * updateTemplateLibStatusByExample:(根据条件更新模板及附件表状态). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param conditionDTO  查询条件DTO
     * @param resultDTO 结果DTO
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    @Override
    public void updateTemplateLibStatusByExample(CmsTemplateLibReqDTO conditionDTO,CmsTemplateLibReqDTO resultDTO) throws BusinessException {
        //1、将模板失效
        CmsTemplateLib bo = new CmsTemplateLib();
        bo.setId(resultDTO.getId());
        bo.setStatus(resultDTO.getStatus());
        this.updateCmsTemplateLib(bo);
        
        //2、将模板布局失效
        CmsTemplateLayoutReqDTO conditionDTO1 = new CmsTemplateLayoutReqDTO();
        conditionDTO1.setStatus(conditionDTO.getStatus());
        conditionDTO1.setTemplateId(conditionDTO.getId());
        CmsTemplateLayoutReqDTO resultDTO1 = new CmsTemplateLayoutReqDTO();
        resultDTO1.setStatus(resultDTO.getStatus());
        resultDTO1.setTemplateId(resultDTO.getId());
        templateLayoutSV.updateTemplateLayoutByExample(conditionDTO1, resultDTO1);
        
        //3、将模板布局项失效
        CmsTemplateLayoutItemReqDTO conditionDTO2 = new CmsTemplateLayoutItemReqDTO();
        conditionDTO2.setStatus(conditionDTO.getStatus());
        conditionDTO2.setTemplateId(conditionDTO.getId());
        CmsTemplateLayoutItemReqDTO resultDTO2 = new CmsTemplateLayoutItemReqDTO();
        resultDTO2.setStatus(resultDTO.getStatus());
        resultDTO2.setTemplateId(resultDTO.getId());
        templateLayoutItemSV.updateTemplateLayoutItemByExample(conditionDTO2, resultDTO2);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLibSV#deleteCmsTemplateLib(java.lang.Long)
     */
    @Override
    public void deleteCmsTemplateLib(String id) throws BusinessException {
        CmsTemplateLib bo = new CmsTemplateLib();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsTemplateLib(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLibSV#deleteCmsTemplateLibBatch(java.util.List)
     */
    @Override
    public void deleteCmsTemplateLibBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsTemplateLib(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLibSV#changeStatusCmsTemplateLib(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsTemplateLib(String id, String status) throws BusinessException {
        CmsTemplateLib bo = new CmsTemplateLib();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsTemplateLib(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLibSV#changeStatusCmsTemplateLibBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsTemplateLibBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsTemplateLib(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLibSV#queryCmsTemplateLib(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO)
     */
    @Override
    public CmsTemplateLibRespDTO queryCmsTemplateLib(CmsTemplateLibReqDTO dto) throws BusinessException {
        CmsTemplateLibRespDTO cmsTemplateLibRespDTO = new CmsTemplateLibRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsTemplateLib bo = cmsTemplateLibMapper.selectByPrimaryKey(dto.getId());
            cmsTemplateLibRespDTO = conversionObject(bo);
        }
        
        return cmsTemplateLibRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLibSV#queryCmsTemplateLibList(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO)
     */
    @Override
    public List<CmsTemplateLibRespDTO> queryCmsTemplateLibList(CmsTemplateLibReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsTemplateLibCriteria cmsTemplateLibCriteria = new CmsTemplateLibCriteria();
        Criteria criteria = cmsTemplateLibCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getTemplateName())) {
            criteria.andTemplateNameLike("%"+dto.getTemplateName()+"%");
        }
        if (StringUtil.isNotEmpty(dto.getTemplateType())) {
            criteria.andTemplateTypeEqualTo(dto.getTemplateType());
        }
        if (StringUtil.isNotBlank(dto.getPlatformType())) {
            criteria.andPlatformTypeEqualTo(dto.getPlatformType());
        }
        if (StringUtil.isNotEmpty(dto.getPageTypeId())) {
            criteria.andPageTypeIdEqualTo(dto.getPageTypeId());
        }
        /*状态查询 begin*/
        if (dto.getStatusSet() == null) {
            dto.setStatusSet(new HashSet<String>());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            dto.getStatusSet().add(dto.getStatus());
        }
        if (CollectionUtils.isNotEmpty(dto.getStatusSet())) {
            criteria.andStatusIn(new ArrayList<String>(dto.getStatusSet()));
        }
        /*状态查询 end*/
        cmsTemplateLibCriteria.setOrderByClause("UPDATE_TIME DESC,CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsTemplateLibRespDTO> cmsTemplateLibRespDTOList =  new ArrayList<CmsTemplateLibRespDTO>();
        List<CmsTemplateLib> cmsTemplateLibList = cmsTemplateLibMapper.selectByExample(cmsTemplateLibCriteria);
        if(CollectionUtils.isNotEmpty(cmsTemplateLibList)){
            for(CmsTemplateLib bo :cmsTemplateLibList){
                CmsTemplateLibRespDTO cmsTemplateLibRespDTO = conversionObject(bo);
                cmsTemplateLibRespDTOList.add(cmsTemplateLibRespDTO);
            }
        }
        
        return cmsTemplateLibRespDTOList;

    }
    /**
     * TODO 精确查询模板列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLibSV#queryCmsTemplateLibList(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO)
     */
    @Override
    public List<CmsTemplateLibRespDTO> queryExactCmsTemplateLibList(CmsTemplateLibReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsTemplateLibCriteria cmsTemplateLibCriteria = new CmsTemplateLibCriteria();
        Criteria criteria = cmsTemplateLibCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getTemplateName())) {
            criteria.andTemplateNameEqualTo(dto.getTemplateName());
        }
        if (StringUtil.isNotEmpty(dto.getTemplateType())) {
            criteria.andTemplateTypeEqualTo(dto.getTemplateType());
        }
        if (StringUtil.isNotBlank(dto.getPlatformType())) {
            criteria.andPlatformTypeEqualTo(dto.getPlatformType());
        }
        if (StringUtil.isNotEmpty(dto.getPageTypeId())) {
            criteria.andPageTypeIdEqualTo(dto.getPageTypeId());
        }
        /*状态查询 begin*/
        if (dto.getStatusSet() == null) {
            dto.setStatusSet(new HashSet<String>());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            dto.getStatusSet().add(dto.getStatus());
        }
        if (CollectionUtils.isNotEmpty(dto.getStatusSet())) {
            criteria.andStatusIn(new ArrayList<String>(dto.getStatusSet()));
        }
        /*状态查询 end*/
        cmsTemplateLibCriteria.setOrderByClause("UPDATE_TIME DESC,CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsTemplateLibRespDTO> cmsTemplateLibRespDTOList =  new ArrayList<CmsTemplateLibRespDTO>();
        List<CmsTemplateLib> cmsTemplateLibList = cmsTemplateLibMapper.selectByExample(cmsTemplateLibCriteria);
        if(CollectionUtils.isNotEmpty(cmsTemplateLibList)){
            for(CmsTemplateLib bo :cmsTemplateLibList){
                CmsTemplateLibRespDTO cmsTemplateLibRespDTO = conversionObject(bo);
                cmsTemplateLibRespDTOList.add(cmsTemplateLibRespDTO);
            }
        }
        
        return cmsTemplateLibRespDTOList;

    }

    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLibSV#queryCmsTemplateLibPage(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsTemplateLibRespDTO> queryCmsTemplateLibPage(CmsTemplateLibReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsTemplateLibCriteria cmsTemplateLibCriteria = new CmsTemplateLibCriteria();
        Criteria criteria = cmsTemplateLibCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getTemplateName())) {
            criteria.andTemplateNameLike("%"+dto.getTemplateName()+"%");
        }
        if (StringUtil.isNotEmpty(dto.getTemplateType())) {
            criteria.andTemplateTypeEqualTo(dto.getTemplateType());
        }
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
        	criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if (StringUtil.isNotEmpty(dto.getPageTypeId())) {
            criteria.andPageTypeIdEqualTo(dto.getPageTypeId());
        }
        if (StringUtil.isNotBlank(dto.getPlatformType())) {
            criteria.andPlatformTypeEqualTo(dto.getPlatformType());
        }
        if (StringUtil.isNotEmpty(dto.getIsDefTemplate())) {
        	criteria.andIsDefTemplateEqualTo(dto.getIsDefTemplate());
        }
        /*状态查询 begin*/
        if (dto.getStatusSet() == null) {
            dto.setStatusSet(new HashSet<String>());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            dto.getStatusSet().add(dto.getStatus());
        }
        if (CollectionUtils.isNotEmpty(dto.getStatusSet())) {
            criteria.andStatusIn(new ArrayList<String>(dto.getStatusSet()));
        }
        /*状态查询 end*/
        cmsTemplateLibCriteria.setOrderByClause("CREATE_TIME DESC");//UPDATE_TIME DESC,
        cmsTemplateLibCriteria.setLimitClauseCount(dto.getPageSize());
        cmsTemplateLibCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsTemplateLibCriteria,false,new PaginationCallback<CmsTemplateLib, CmsTemplateLibRespDTO>(){

            @Override
            public List<CmsTemplateLib> queryDB(BaseCriteria criteria) {
                return cmsTemplateLibMapper.selectByExample((CmsTemplateLibCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsTemplateLibMapper.countByExample((CmsTemplateLibCriteria)criteria);
            }

            @Override
            public CmsTemplateLibRespDTO warpReturnObject(CmsTemplateLib bo) {
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
    private CmsTemplateLibRespDTO conversionObject(CmsTemplateLib bo){
        CmsTemplateLibRespDTO dto = new CmsTemplateLibRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        CmsSiteReqDTO siteReqDTO = new CmsSiteReqDTO();
        siteReqDTO.setId(dto.getSiteId());
        CmsSiteRespDTO siteRespDTO= sv.queryCmsSite(siteReqDTO);
        dto.setSiteName(siteRespDTO.getSiteName());
        // 2.遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String isDefTemplateZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.PUBLIC_PARAM_ISNOT, dto.getIsDefTemplate());
        dto.setIsDefTemplateZH(isDefTemplateZH);
        String templateTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_TEMPLATE_TYPE, dto.getTemplateType());
        dto.setTemplateTypeZH(templateTypeZH);
        String platformTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_PLATFORM_TYPE, dto.getPlatformType());
        dto.setPlatformTypeZH(platformTypeZH);
        
        return dto;
    }

}
