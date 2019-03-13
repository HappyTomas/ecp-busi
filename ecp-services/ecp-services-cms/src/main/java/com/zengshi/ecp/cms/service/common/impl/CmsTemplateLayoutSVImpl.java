package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsTemplateLayoutMapper;
import com.zengshi.ecp.cms.dao.model.CmsTemplateLayout;
import com.zengshi.ecp.cms.dao.model.CmsTemplateLayoutCriteria;
import com.zengshi.ecp.cms.dao.model.CmsTemplateLayoutCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutItemSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
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
@Service("cmsTemplateLayoutSV")
public class CmsTemplateLayoutSVImpl extends GeneralSQLSVImpl implements ICmsTemplateLayoutSV {

    @Resource(name = "SEQ_CMS_TEMPLATE_LAYOUT")
    private PaasSequence seqCmsTemplateLayout;
    @Resource
    private CmsTemplateLayoutMapper cmsTemplateLayoutMapper;
    @Resource
    private ICmsTemplateLayoutItemSV templateLayoutItemSV;

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutSV#saveCmsTemplateLayout(com.zengshi.ecp.cms.dao.model.CmsTemplateLayout)
     */
    @Override
    public CmsTemplateLayoutRespDTO addCmsTemplateLayout(CmsTemplateLayoutReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsTemplateLayout bo = new CmsTemplateLayout();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsTemplateLayout.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsTemplateLayoutMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsTemplateLayoutRespDTO respDTO = new CmsTemplateLayoutRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        
        respDTO.setTemplateLayoutItemRespDTOList(new ArrayList<CmsTemplateLayoutItemRespDTO>());
        if(CollectionUtils.isNotEmpty(dto.getTemplateLayoutItemReqDTOList())){
            for(CmsTemplateLayoutItemReqDTO itemDTO : dto.getTemplateLayoutItemReqDTOList()){
                itemDTO.setLayoutId(id);
                respDTO.getTemplateLayoutItemRespDTOList().add(this.templateLayoutItemSV.addCmsTemplateLayoutItem(itemDTO));
            }
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutSV#updateCmsTemplateLayout(com.zengshi.ecp.cms.dao.model.CmsTemplateLayout)
     */
    @Override
    public CmsTemplateLayoutRespDTO updateCmsTemplateLayout(CmsTemplateLayoutReqDTO dto) throws BusinessException {
        /*1.更新模板布局表*/
        CmsTemplateLayout bo = new CmsTemplateLayout();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        CmsTemplateLayoutRespDTO templateLayoutRespDTO = this.updateCmsTemplateLayout(bo);
        
        if(CollectionUtils.isNotEmpty(dto.getTemplateLayoutItemReqDTOList())){
            /*2.根据布局ID将其布局项失效*/
            updateCmsTemplateLayoutAndOther(dto);
            
            /*3.新增相应布局项*/
            List<CmsTemplateLayoutItemRespDTO> items = new ArrayList<CmsTemplateLayoutItemRespDTO>();
            for(CmsTemplateLayoutItemReqDTO templateLayoutItemReqDTO : dto.getTemplateLayoutItemReqDTOList()){
                if(templateLayoutItemReqDTO != null){
                    templateLayoutItemReqDTO.setLayoutId(dto.getId());
                    templateLayoutItemReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
                    items.add(templateLayoutItemSV.addCmsTemplateLayoutItem(templateLayoutItemReqDTO));
                }
            }
            templateLayoutRespDTO.setTemplateLayoutItemRespDTOList(items);
        }
        
        /*2.更新楼层的原子方法*/
        return templateLayoutRespDTO;
    }
    
    /** 
     * updateCmsTemplateLayoutAndOther. <br/> 
     * TODO(根据布局ID更新布局及附表).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param layoutPreReqDTO
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void updateCmsTemplateLayoutAndOther(CmsTemplateLayoutReqDTO templateLayoutReqDTO) throws BusinessException {
        CmsTemplateLayoutItemReqDTO templatelayoutItemPreReqDTO = new CmsTemplateLayoutItemReqDTO();
        templatelayoutItemPreReqDTO.setLayoutId(templateLayoutReqDTO.getId());
        //3.1 将布局项失效
        templateLayoutItemSV.deleteCmsTemplateLayoutItemByExample(templatelayoutItemPreReqDTO);
    }
    
    /** 
     * updateCmsTemplateLayout:(更新楼层的原子方法). <br/> 
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
    public CmsTemplateLayoutRespDTO updateCmsTemplateLayout(CmsTemplateLayout bo) throws BusinessException {
        cmsTemplateLayoutMapper.updateByPrimaryKeySelective(bo);
        CmsTemplateLayoutRespDTO respDTO = new CmsTemplateLayoutRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutSV#deleteCmsTemplateLayout(java.lang.Long)
     */
    @Override
    public void deleteCmsTemplateLayout(String id) throws BusinessException {
        CmsTemplateLayout bo = new CmsTemplateLayout();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsTemplateLayout(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutSV#deleteCmsTemplateLayoutBatch(java.util.List)
     */
    @Override
    public void deleteCmsTemplateLayoutBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsTemplateLayout(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutSV#changeStatusCmsTemplateLayout(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsTemplateLayout(String id, String status) throws BusinessException {
        CmsTemplateLayout bo = new CmsTemplateLayout();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsTemplateLayout(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutSV#changeStatusCmsTemplateLayoutBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsTemplateLayoutBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsTemplateLayout(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutSV#queryCmsTemplateLayout(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutReqDTO)
     */
    @Override
    public CmsTemplateLayoutRespDTO queryCmsTemplateLayout(CmsTemplateLayoutReqDTO dto) throws BusinessException {
        CmsTemplateLayoutRespDTO cmsTemplateLayoutRespDTO = new CmsTemplateLayoutRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsTemplateLayout bo = cmsTemplateLayoutMapper.selectByPrimaryKey(dto.getId());
            cmsTemplateLayoutRespDTO = conversionObject(bo);
        }
        
        return cmsTemplateLayoutRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutSV#queryCmsTemplateLayoutList(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutReqDTO)
     */
    @Override
    public List<CmsTemplateLayoutRespDTO> queryCmsTemplateLayoutList(CmsTemplateLayoutReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsTemplateLayoutCriteria cmsTemplateLayoutCriteria = new CmsTemplateLayoutCriteria();
        Criteria criteria = cmsTemplateLayoutCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutTypeId())) {
            criteria.andLayoutTypeIdEqualTo(dto.getLayoutTypeId());
        }
        if (StringUtil.isNotEmpty(dto.getTemplateId())) {
            criteria.andTemplateIdEqualTo(dto.getTemplateId());
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
        cmsTemplateLayoutCriteria.setOrderByClause("SHOW_ORDER ASC,CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsTemplateLayoutRespDTO> cmsTemplateLayoutRespDTOList =  new ArrayList<CmsTemplateLayoutRespDTO>();
        List<CmsTemplateLayout> cmsTemplateLayoutList = cmsTemplateLayoutMapper.selectByExample(cmsTemplateLayoutCriteria);
        if(CollectionUtils.isNotEmpty(cmsTemplateLayoutList)){
            for(CmsTemplateLayout bo :cmsTemplateLayoutList){
                CmsTemplateLayoutRespDTO cmsTemplateLayoutRespDTO = conversionObject(bo);
                cmsTemplateLayoutRespDTOList.add(cmsTemplateLayoutRespDTO);
            }
        }
        
        return cmsTemplateLayoutRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutSV#queryCmsTemplateLayoutPage(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsTemplateLayoutRespDTO> queryCmsTemplateLayoutPage(CmsTemplateLayoutReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsTemplateLayoutCriteria cmsTemplateLayoutCriteria = new CmsTemplateLayoutCriteria();
        Criteria criteria = cmsTemplateLayoutCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutTypeId())) {
            criteria.andLayoutTypeIdEqualTo(dto.getLayoutTypeId());
        }
        if (StringUtil.isNotEmpty(dto.getTemplateId())) {
            criteria.andTemplateIdEqualTo(dto.getTemplateId());
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
        cmsTemplateLayoutCriteria.setOrderByClause("SHOW_ORDER ASC,CREATE_TIME ASC");
        cmsTemplateLayoutCriteria.setLimitClauseCount(dto.getPageSize());
        cmsTemplateLayoutCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsTemplateLayoutCriteria,false,new PaginationCallback<CmsTemplateLayout, CmsTemplateLayoutRespDTO>(){

            @Override
            public List<CmsTemplateLayout> queryDB(BaseCriteria criteria) {
                return cmsTemplateLayoutMapper.selectByExample((CmsTemplateLayoutCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsTemplateLayoutMapper.countByExample((CmsTemplateLayoutCriteria)criteria);
            }

            @Override
            public CmsTemplateLayoutRespDTO warpReturnObject(CmsTemplateLayout bo) {
                return conversionObject(bo);
            }
        
        });

    }
    
    /** 
     * updateCmsTemplateLayoutBatch. <br/> 
     * 批量更新布局(删除布局、对布局重新排序)
     * 当状态为2时：
     * TODO(1、更新布局).<br/> 
     * TODO(2、根据布局更新布局项).<br/> 
     * 当状态为0时：
     * TODO(1、更新布局，更新排序。).<br/> 
     * @author jiangzh 
     * @param bo
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void updateCmsTemplateLayoutBatch(List<CmsTemplateLayoutReqDTO> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            CmsTemplateLayoutReqDTO dto = list.get(i);
            if(dto != null && StringUtil.isNotBlank(dto.getStatus())){
                
                //1 更新布局表，更新状态及排序。
                CmsTemplateLayout bo = new CmsTemplateLayout();
                if(dto != null){
                    ObjectCopyUtil.copyObjValue(dto, bo, null, false);
                }
                bo.setUpdateStaff(dto.getStaff().getId());
                bo.setUpdateTime(DateUtil.getSysDate());
                this.updateCmsTemplateLayout(bo);
                
                //2 当状态为2时，将布局失效.
                if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2.equals(dto.getStatus())){//删除
                    updateCmsTemplateLayoutAndOther(dto);
                }
            }
        }
    }
    
    /** 
     * updateTemplateLayoutByExample:(根据条件更新结果). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param conditionDTO  查询条件DTO
     * @param resultDTO  结果DTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    @Override
    public int updateTemplateLayoutByExample(CmsTemplateLayoutReqDTO conditionDTO,CmsTemplateLayoutReqDTO resultDTO) throws BusinessException {
        /*1.组装删除bo*/
        CmsTemplateLayout bo = new CmsTemplateLayout();
        bo.setStatus(resultDTO.getStatus());
        bo.setUpdateStaff(conditionDTO.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /* 2.查询条件 */
        CmsTemplateLayoutCriteria cmsTemplateLayoutCriteria = new CmsTemplateLayoutCriteria();
        Criteria criteria = cmsTemplateLayoutCriteria.createCriteria();
        if (StringUtil.isNotEmpty(conditionDTO.getLayoutTypeId())) {
            criteria.andLayoutTypeIdEqualTo(conditionDTO.getLayoutTypeId());
        }
        if (StringUtil.isNotEmpty(conditionDTO.getTemplateId())) {
            criteria.andTemplateIdEqualTo(conditionDTO.getTemplateId());
        }
        if (StringUtil.isNotEmpty(conditionDTO.getId())) {
            criteria.andIdEqualTo(conditionDTO.getId());
        }
        /*状态查询 begin*/
        if (conditionDTO.getStatusSet() == null) {
            conditionDTO.setStatusSet(new HashSet<String>());
        }
        if (StringUtil.isNotBlank(conditionDTO.getStatus())) {
            conditionDTO.getStatusSet().add(conditionDTO.getStatus());
        }
        if (CollectionUtils.isNotEmpty(conditionDTO.getStatusSet())) {
            criteria.andStatusIn(new ArrayList<String>(conditionDTO.getStatusSet()));
        }
        /*状态查询 end*/
        
        return cmsTemplateLayoutMapper.updateByExampleSelective(bo, cmsTemplateLayoutCriteria);
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
    private CmsTemplateLayoutRespDTO conversionObject(CmsTemplateLayout bo){
        CmsTemplateLayoutRespDTO dto = new CmsTemplateLayoutRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        
        // 2.遍历将编码转中文 
        /*String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);*/
        
        return dto;
    }

}
