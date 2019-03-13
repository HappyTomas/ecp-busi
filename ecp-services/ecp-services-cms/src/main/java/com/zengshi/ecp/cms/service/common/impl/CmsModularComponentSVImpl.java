package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsModularComponentMapper;
import com.zengshi.ecp.cms.dao.model.CmsModularComponent;
import com.zengshi.ecp.cms.dao.model.CmsModularComponentCriteria;
import com.zengshi.ecp.cms.dao.model.CmsModularComponentCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsComponentReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsComponentRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularComponentReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularComponentRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsComponentSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsModularComponentSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsModularSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageTypeSV;
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
@Service("cmsModularComponentSV")
public class CmsModularComponentSVImpl extends GeneralSQLSVImpl implements ICmsModularComponentSV {

    @Resource(name = "SEQ_CMS_MODULAR_COMPONENT")
    private PaasSequence seqCmsModularComponent;
    @Resource
    private CmsModularComponentMapper cmsModularComponentMapper;
    @Resource
    private ICmsComponentSV sv;
    @Resource
    private ICmsModularSV modularSv;
    @Resource
    private ICmsPageTypeSV pageTypeSv;

    /**
     * TODO 是否存在模块.
     * 存在：true
     * 不存在：false
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageTypeSV#queryCmsPageTypeList(com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO)
     */
    @Override
    public boolean isExistModularComponent(CmsModularComponentReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsModularComponentCriteria cmsModularComponentCriteria = new CmsModularComponentCriteria();
        Criteria criteria = cmsModularComponentCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdNotEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getModularId())) {
        	criteria.andModularIdEqualTo(dto.getModularId());
        }
        if (StringUtil.isNotEmpty(dto.getApplyPageType())) {
        	criteria.andApplyPageTypeEqualTo(dto.getApplyPageType());
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
        
        cmsModularComponentCriteria.setOrderByClause("CREATE_TIME DESC");
        
        boolean isExist = false;//不存在
        /*2.迭代查询结果*/
        List<CmsModularComponent> cmsModularComponentList = cmsModularComponentMapper.selectByExample(cmsModularComponentCriteria);
        if(cmsModularComponentList != null && cmsModularComponentList.size()>0){
            isExist = true;
        }
        return isExist;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularComponentSV#saveCmsModularComponent(com.zengshi.ecp.cms.dao.model.CmsModularComponent)
     */
    @Override
    public CmsModularComponentRespDTO addCmsModularComponent(CmsModularComponentReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsModularComponent bo = new CmsModularComponent();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsModularComponent.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsModularComponentMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsModularComponentRespDTO respDTO = new CmsModularComponentRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularComponentSV#updateCmsModularComponent(com.zengshi.ecp.cms.dao.model.CmsModularComponent)
     */
    @Override
    public CmsModularComponentRespDTO updateCmsModularComponent(CmsModularComponentReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsModularComponent bo = new CmsModularComponent();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新模块的原子方法*/
        return this.updateCmsModularComponent(bo);
    }
    
    /** 
     * updateCmsModularComponent:(更新模块的原子方法). <br/> 
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
    public CmsModularComponentRespDTO updateCmsModularComponent(CmsModularComponent bo) throws BusinessException {
        cmsModularComponentMapper.updateByPrimaryKeySelective(bo);
        CmsModularComponentRespDTO respDTO = new CmsModularComponentRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularComponentSV#deleteCmsModularComponent(java.lang.Long)
     */
    @Override
    public void deleteCmsModularComponent(String id) throws BusinessException {
        CmsModularComponent bo = new CmsModularComponent();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsModularComponent(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularComponentSV#deleteCmsModularComponentBatch(java.util.List)
     */
    @Override
    public void deleteCmsModularComponentBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsModularComponent(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularComponentSV#changeStatusCmsModularComponent(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsModularComponent(String id, String status) throws BusinessException {
        CmsModularComponent bo = new CmsModularComponent();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsModularComponent(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularComponentSV#changeStatusCmsModularComponentBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsModularComponentBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsModularComponent(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularComponentSV#queryCmsModularComponent(com.zengshi.ecp.cms.dubbo.dto.CmsModularComponentReqDTO)
     */
    @Override
    public CmsModularComponentRespDTO queryCmsModularComponent(CmsModularComponentReqDTO dto) throws BusinessException {
        CmsModularComponentRespDTO cmsModularComponentRespDTO = new CmsModularComponentRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsModularComponent bo = cmsModularComponentMapper.selectByPrimaryKey(dto.getId());
            cmsModularComponentRespDTO = conversionObject(bo);
        }
        
        return cmsModularComponentRespDTO;
    }
    
    /**
     * TODO 查询模块列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularComponentSV#queryCmsModularComponentList(com.zengshi.ecp.cms.dubbo.dto.CmsModularComponentReqDTO)
     */
    @Override
    public List<CmsModularComponentRespDTO> queryCmsModularComponentList(CmsModularComponentReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsModularComponentCriteria cmsModularComponentCriteria = new CmsModularComponentCriteria();
        Criteria criteria = cmsModularComponentCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdNotEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getModularId())) {
        	criteria.andModularIdEqualTo(dto.getModularId());
        }
        if (StringUtil.isNotEmpty(dto.getModularClass())) {
            criteria.andModularClassLike("%"+dto.getModularClass()+"%");
        }
        //由于数据库字段以|1001|格式保存数据，因此查询两侧添加|
        /*if (StringUtil.isNotEmpty(dto.getApplyPageType())) {
            criteria.andApplyPageTypeLike("%|"+dto.getApplyPageType()+"|%");
        }*/
        //由于数据库字段以|1001|格式保存数据，因此查询两侧添加|
        if (StringUtil.isNotEmpty(dto.getApplyItemSize())) {
            criteria.andApplyItemSizeLike("%|"+dto.getApplyItemSize()+"|%");
        }
        if (StringUtil.isNotEmpty(dto.getComponentId())) {
        	criteria.andComponentIdEqualTo(dto.getComponentId());
        }
        if (StringUtil.isNotEmpty(dto.getApplyPageType())) {
        	criteria.andApplyPageTypeEqualTo(dto.getApplyPageType());
        }
        //状态查询 begin
        if (dto.getStatusSet() == null) {
            dto.setStatusSet(new HashSet<String>());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            dto.getStatusSet().add(dto.getStatus());
        }
        if (CollectionUtils.isNotEmpty(dto.getStatusSet())) {
            criteria.andStatusIn(new ArrayList<String>(dto.getStatusSet()));
        }
        //状态查询 end
        cmsModularComponentCriteria.setOrderByClause("CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsModularComponentRespDTO> cmsModularComponentRespDTOList =  new ArrayList<CmsModularComponentRespDTO>();
        List<CmsModularComponent> cmsModularComponentList = cmsModularComponentMapper.selectByExample(cmsModularComponentCriteria);
        if(CollectionUtils.isNotEmpty(cmsModularComponentList)){
            for(CmsModularComponent bo :cmsModularComponentList){
                CmsModularComponentRespDTO cmsModularComponentRespDTO = conversionObject(bo);
                cmsModularComponentRespDTOList.add(cmsModularComponentRespDTO);
            }
        }
        
        return cmsModularComponentRespDTOList;

    }


    /** 
     * TODO 查询模块，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularComponentSV#queryCmsModularComponentPage(com.zengshi.ecp.cms.dubbo.dto.CmsModularComponentReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsModularComponentRespDTO> queryCmsModularComponentPage(CmsModularComponentReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索模块 */
        CmsModularComponentCriteria cmsModularComponentCriteria = new CmsModularComponentCriteria();
        Criteria criteria = cmsModularComponentCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getModularId())) {
        	criteria.andModularIdEqualTo(dto.getModularId());
        }
        if (StringUtil.isNotEmpty(dto.getModularClass())) {
            criteria.andModularClassLike("%"+dto.getModularClass()+"%");
        }
        //由于数据库字段以|1001|格式保存数据，因此查询两侧添加|
        /*if (StringUtil.isNotEmpty(dto.getApplyPageType())) {
            criteria.andApplyPageTypeLike("%|"+dto.getApplyPageType()+"|%");
        }*/
        //由于数据库字段以|1001|格式保存数据，因此查询两侧添加|
        if (StringUtil.isNotEmpty(dto.getApplyItemSize())) {
            criteria.andApplyItemSizeLike("%|"+dto.getApplyItemSize()+"|%");
        }
        if (StringUtil.isNotEmpty(dto.getComponentId())) {
        	criteria.andComponentIdEqualTo(dto.getComponentId());
        }
        if (StringUtil.isNotEmpty(dto.getApplyPageType())) {
        	criteria.andApplyPageTypeEqualTo(dto.getApplyPageType());
        }
        //状态查询 begin
        if (dto.getStatusSet() == null) {
            dto.setStatusSet(new HashSet<String>());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            dto.getStatusSet().add(dto.getStatus());
        }
        if (CollectionUtils.isNotEmpty(dto.getStatusSet())) {
            criteria.andStatusIn(new ArrayList<String>(dto.getStatusSet()));
        }
        //状态查询 end
        cmsModularComponentCriteria.setOrderByClause("CREATE_TIME DESC");
        cmsModularComponentCriteria.setLimitClauseCount(dto.getPageSize());
        cmsModularComponentCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsModularComponentCriteria,false,new PaginationCallback<CmsModularComponent, CmsModularComponentRespDTO>(){

            @Override
            public List<CmsModularComponent> queryDB(BaseCriteria criteria) {
                return cmsModularComponentMapper.selectByExample((CmsModularComponentCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsModularComponentMapper.countByExample((CmsModularComponentCriteria)criteria);
            }

            @Override
            public CmsModularComponentRespDTO warpReturnObject(CmsModularComponent bo) {
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
    private CmsModularComponentRespDTO conversionObject(CmsModularComponent bo){
        CmsModularComponentRespDTO dto = new CmsModularComponentRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询组件服务，获取组件
        if(StringUtil.isNotEmpty(dto.getComponentId())){
            CmsComponentReqDTO cmsComponentReqDTO= new CmsComponentReqDTO();
            cmsComponentReqDTO.setId(Long.valueOf(dto.getComponentId()));
            CmsComponentRespDTO cmsComponentRespDTO = sv.queryCmsComponent(cmsComponentReqDTO);
            if(cmsComponentRespDTO != null){
            	dto.setComponentRespDTO(cmsComponentRespDTO);
            }
        }
        // 2.遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        
        CmsPageTypeReqDTO cmsPageTypeReqDTO=new CmsPageTypeReqDTO();
        cmsPageTypeReqDTO.setId(Long.valueOf(bo.getApplyPageType()));
        CmsPageTypeRespDTO cmsPageTypeRespDTO= pageTypeSv.queryCmsPageType(cmsPageTypeReqDTO);
        dto.setApplyPageTypeZH(cmsPageTypeRespDTO.getPageTypeName());
        return dto;
    }

}
