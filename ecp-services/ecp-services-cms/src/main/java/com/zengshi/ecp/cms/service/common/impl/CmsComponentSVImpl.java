package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsComponentMapper;
import com.zengshi.ecp.cms.dao.model.CmsComponent;
import com.zengshi.ecp.cms.dao.model.CmsComponentCriteria;
import com.zengshi.ecp.cms.dao.model.CmsComponentCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsComponentReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsComponentRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsComponentSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
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
@Service("cmsComponentSV")
public class CmsComponentSVImpl extends GeneralSQLSVImpl implements ICmsComponentSV {

    @Resource(name = "SEQ_CMS_COMPONENT")
    private PaasSequence seqCmsComponent;
    
    @Resource
    private CmsComponentMapper cmsComponentMapper;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsComponentSV#saveCmsComponent(com.zengshi.ecp.cms.dao.model.CmsComponent)
     */
    @Override
    public CmsComponentRespDTO addCmsComponent(CmsComponentReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsComponent bo = new CmsComponent();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsComponent.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsComponentMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsComponentRespDTO respDTO = new CmsComponentRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsComponentSV#updateCmsComponent(com.zengshi.ecp.cms.dao.model.CmsComponent)
     */
    @Override
    public CmsComponentRespDTO updateCmsComponent(CmsComponentReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsComponent bo = new CmsComponent();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsComponent(bo);
    }
    
    /** 
     * updateCmsComponent:(更新楼层的原子方法). <br/> 
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
    public CmsComponentRespDTO updateCmsComponent(CmsComponent bo) throws BusinessException {
        cmsComponentMapper.updateByPrimaryKeySelective(bo);
        CmsComponentRespDTO respDTO = new CmsComponentRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsComponentSV#deleteCmsComponent(java.lang.Long)
     */
    @Override
    public void deleteCmsComponent(String id) throws BusinessException {
        CmsComponent bo = new CmsComponent();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsComponent(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsComponentSV#deleteCmsComponentBatch(java.util.List)
     */
    @Override
    public void deleteCmsComponentBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsComponent(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsComponentSV#changeStatusCmsComponent(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsComponent(String id, String status) throws BusinessException {
        CmsComponent bo = new CmsComponent();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsComponent(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsComponentSV#changeStatusCmsComponentBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsComponentBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsComponent(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsComponentSV#queryCmsComponent(com.zengshi.ecp.cms.dubbo.dto.CmsComponentReqDTO)
     */
    @Override
    public CmsComponentRespDTO queryCmsComponent(CmsComponentReqDTO dto) throws BusinessException {
        CmsComponentRespDTO cmsComponentRespDTO = new CmsComponentRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsComponent bo = cmsComponentMapper.selectByPrimaryKey(dto.getId());
            cmsComponentRespDTO = conversionObject(bo);
        }
        
        return cmsComponentRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsComponentSV#queryCmsComponentList(com.zengshi.ecp.cms.dubbo.dto.CmsComponentReqDTO)
     */
    @Override
    public List<CmsComponentRespDTO> queryCmsComponentList(CmsComponentReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsComponentCriteria cmsComponentCriteria = new CmsComponentCriteria();
        Criteria criteria = cmsComponentCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getComponentName())) {
            criteria.andComponentNameLike("%"+dto.getComponentName()+"%");
        }
        if (StringUtil.isNotBlank(dto.getComponentClass())) {
            criteria.andComponentClassEqualTo(dto.getComponentClass());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        cmsComponentCriteria.setOrderByClause("CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsComponentRespDTO> cmsComponentRespDTOList =  new ArrayList<CmsComponentRespDTO>();
        List<CmsComponent> cmsComponentList = cmsComponentMapper.selectByExample(cmsComponentCriteria);
        if(CollectionUtils.isNotEmpty(cmsComponentList)){
            for(CmsComponent bo :cmsComponentList){
                CmsComponentRespDTO cmsComponentRespDTO = conversionObject(bo);
                cmsComponentRespDTOList.add(cmsComponentRespDTO);
            }
        }
        
        return cmsComponentRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsComponentSV#queryCmsComponentPage(com.zengshi.ecp.cms.dubbo.dto.CmsComponentReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsComponentRespDTO> queryCmsComponentPage(CmsComponentReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsComponentCriteria cmsComponentCriteria = new CmsComponentCriteria();
        Criteria criteria = cmsComponentCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getComponentName())) {
            criteria.andComponentNameLike("%"+dto.getComponentName()+"%");
        }
        if (StringUtil.isNotBlank(dto.getComponentClass())) {
            criteria.andComponentClassEqualTo(dto.getComponentClass());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        cmsComponentCriteria.setOrderByClause("CREATE_TIME DESC");
        cmsComponentCriteria.setLimitClauseCount(dto.getPageSize());
        cmsComponentCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsComponentCriteria,false,new PaginationCallback<CmsComponent, CmsComponentRespDTO>(){

            @Override
            public List<CmsComponent> queryDB(BaseCriteria criteria) {
                return cmsComponentMapper.selectByExample((CmsComponentCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsComponentMapper.countByExample((CmsComponentCriteria)criteria);
            }

            @Override
            public CmsComponentRespDTO warpReturnObject(CmsComponent bo) {
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
    private CmsComponentRespDTO conversionObject(CmsComponent bo){
        CmsComponentRespDTO dto = new CmsComponentRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        //1.遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String componentClassZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_COMPONENT_CLASS, dto.getComponentClass());
        dto.setComponentClassZH(componentClassZH);
        
        return dto;
    }

}
