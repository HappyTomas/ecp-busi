package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsModularPropMapper;
import com.zengshi.ecp.cms.dao.model.CmsModularProp;
import com.zengshi.ecp.cms.dao.model.CmsModularPropCriteria;
import com.zengshi.ecp.cms.dao.model.CmsModularPropCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularPropReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularPropRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsModularParaPropSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropSV;
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
@Service("cmsModularPropSV")
public class CmsModularPropSVImpl extends GeneralSQLSVImpl implements ICmsModularPropSV {

    @Resource(name = "SEQ_CMS_MODULAR_PROP")
    private PaasSequence seqCmsModularProp;
    @Resource
    private CmsModularPropMapper cmsModularPropMapper;
    @Resource
    private ICmsModularParaPropSV cmsModularParaPropSV;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropSV#saveCmsModularProp(com.zengshi.ecp.cms.dao.model.CmsModularProp)
     */
    @Override
    public CmsModularPropRespDTO addCmsModularProp(CmsModularPropReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsModularProp bo = new CmsModularProp();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsModularProp.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsModularPropMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsModularPropRespDTO respDTO = new CmsModularPropRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropSV#updateCmsModularProp(com.zengshi.ecp.cms.dao.model.CmsModularProp)
     */
    @Override
    public CmsModularPropRespDTO updateCmsModularProp(CmsModularPropReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsModularProp bo = new CmsModularProp();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新模块与属性关系的原子方法*/
        return this.updateCmsModularProp(bo);
    }
    
    /** 
     * updateCmsModularProp:(更新模块与属性关系的原子方法). <br/> 
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
    public CmsModularPropRespDTO updateCmsModularProp(CmsModularProp bo) throws BusinessException {
        cmsModularPropMapper.updateByPrimaryKeySelective(bo);
        CmsModularPropRespDTO respDTO = new CmsModularPropRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropSV#deleteCmsModularProp(java.lang.Long)
     */
    @Override
    public void deleteCmsModularProp(String id) throws BusinessException {
        CmsModularProp bo = new CmsModularProp();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsModularProp(bo);
    }
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropSV#deleteCmsModularProp(java.lang.Long)
     */
    @Override
    public void deleteCmsModularPropByModularId(String modularId) throws BusinessException {
    	CmsModularPropReqDTO req=new CmsModularPropReqDTO();
    	req.setModularId(Long.valueOf(modularId));
    	List<CmsModularPropRespDTO> list=this.queryCmsModularPropList(req);
    	for (CmsModularPropRespDTO resp:list) {
			this.deleteCmsModularProp(resp.getId().toString());
		}
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropSV#deleteCmsModularPropBatch(java.util.List)
     */
    @Override
    public void deleteCmsModularPropBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsModularProp(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropSV#changeStatusCmsModularProp(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsModularProp(String id, String status) throws BusinessException {
        CmsModularProp bo = new CmsModularProp();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsModularProp(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropSV#changeStatusCmsModularPropBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsModularPropBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsModularProp(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropSV#queryCmsModularProp(com.zengshi.ecp.cms.dubbo.dto.CmsModularPropReqDTO)
     */
    @Override
    public CmsModularPropRespDTO queryCmsModularProp(CmsModularPropReqDTO dto) throws BusinessException {
        CmsModularPropRespDTO cmsModularPropRespDTO = new CmsModularPropRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsModularProp bo = cmsModularPropMapper.selectByPrimaryKey(dto.getId());
            cmsModularPropRespDTO = conversionObject(bo);
        }
        
        return cmsModularPropRespDTO;
    }
    
    /** 
     * queryCmsModularParaPropList:(查询模块下的属性列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    @Override
    public List<CmsModularParaPropRespDTO> queryCmsModularParaPropList(CmsModularPropReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsModularPropCriteria cmsModularPropCriteria = new CmsModularPropCriteria();
        Criteria criteria = cmsModularPropCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPropId())) {
            criteria.andPropIdEqualTo(dto.getPropId());
        }
        if (StringUtil.isNotEmpty(dto.getModularId())) {
            criteria.andModularIdEqualTo(dto.getModularId());
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
        cmsModularPropCriteria.setOrderByClause("SORT_NO ASC,CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsModularParaPropRespDTO> cmsModularParaPropRespDTOList =  new ArrayList<CmsModularParaPropRespDTO>();//属性LIST
        List<CmsModularProp> cmsModularPropList = cmsModularPropMapper.selectByExample(cmsModularPropCriteria);//模块与属性LIST
        if(CollectionUtils.isNotEmpty(cmsModularPropList)){
            for(CmsModularProp modularProp:cmsModularPropList){
                if(modularProp != null && StringUtil.isNotEmpty(modularProp.getPropId())){
                    CmsModularParaPropReqDTO modularParaPropReqDTO = new CmsModularParaPropReqDTO();
                    modularParaPropReqDTO.setId(modularProp.getPropId());
                    modularParaPropReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                    CmsModularParaPropRespDTO modularParaPropRespDTO = cmsModularParaPropSV.queryCmsModularParaProp(modularParaPropReqDTO);
                    cmsModularParaPropRespDTOList.add(modularParaPropRespDTO);
                }
            }
        }
        
        return cmsModularParaPropRespDTOList;

    }

    
    /**
     * TODO 查询模块与属性关系列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropSV#queryCmsModularPropList(com.zengshi.ecp.cms.dubbo.dto.CmsModularPropReqDTO)
     */
    @Override
    public List<CmsModularPropRespDTO> queryCmsModularPropList(CmsModularPropReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsModularPropCriteria cmsModularPropCriteria = new CmsModularPropCriteria();
        Criteria criteria = cmsModularPropCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPropId())) {
            criteria.andPropIdEqualTo(dto.getPropId());
        }
        if (StringUtil.isNotEmpty(dto.getModularId())) {
            criteria.andModularIdEqualTo(dto.getModularId());
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
        cmsModularPropCriteria.setOrderByClause("SORT_NO ASC,CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsModularPropRespDTO> cmsModularPropRespDTOList =  new ArrayList<CmsModularPropRespDTO>();
        List<CmsModularProp> cmsModularPropList = cmsModularPropMapper.selectByExample(cmsModularPropCriteria);
        if(CollectionUtils.isNotEmpty(cmsModularPropList)){
            for(CmsModularProp bo :cmsModularPropList){
                CmsModularPropRespDTO cmsModularPropRespDTO = conversionObject(bo);
                cmsModularPropRespDTOList.add(cmsModularPropRespDTO);
            }
        }
        
        return cmsModularPropRespDTOList;

    }


    /** 
     * TODO 查询模块与属性关系，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropSV#queryCmsModularPropPage(com.zengshi.ecp.cms.dubbo.dto.CmsModularPropReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsModularPropRespDTO> queryCmsModularPropPage(CmsModularPropReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索模块与属性关系 */
        CmsModularPropCriteria cmsModularPropCriteria = new CmsModularPropCriteria();
        Criteria criteria = cmsModularPropCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPropId())) {
            criteria.andPropIdEqualTo(dto.getPropId());
        }
        if (StringUtil.isNotEmpty(dto.getModularId())) {
            criteria.andModularIdEqualTo(dto.getModularId());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
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
        cmsModularPropCriteria.setOrderByClause("SORT_NO ASC,CREATE_TIME ASC");
        cmsModularPropCriteria.setLimitClauseCount(dto.getPageSize());
        cmsModularPropCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsModularPropCriteria,false,new PaginationCallback<CmsModularProp, CmsModularPropRespDTO>(){

            @Override
            public List<CmsModularProp> queryDB(BaseCriteria criteria) {
                return cmsModularPropMapper.selectByExample((CmsModularPropCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsModularPropMapper.countByExample((CmsModularPropCriteria)criteria);
            }

            @Override
            public CmsModularPropRespDTO warpReturnObject(CmsModularProp bo) {
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
    private CmsModularPropRespDTO conversionObject(CmsModularProp bo){
        CmsModularPropRespDTO dto = new CmsModularPropRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        
        // 2.遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        
        return dto;
    }
    
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV#deleteCmsLayoutTypeSelective(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO)
     */
    @Override
    public void deleteCmsModularPropSelective(CmsModularPropReqDTO dto) throws BusinessException {
        /* 1.根据条件检索模块与属性关系 */
        CmsModularPropCriteria cmsModularPropCriteria = new CmsModularPropCriteria();
        Criteria criteria = cmsModularPropCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPropId())) {
            criteria.andPropIdEqualTo(dto.getPropId());
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
        CmsModularProp bo = new CmsModularProp();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        
        cmsModularPropMapper.updateByExampleSelective(bo, cmsModularPropCriteria);
    }

}
