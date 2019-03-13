package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsModularMapper;
import com.zengshi.ecp.cms.dao.model.CmsModular;
import com.zengshi.ecp.cms.dao.model.CmsModularCriteria;
import com.zengshi.ecp.cms.dao.model.CmsModularCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsComponentSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsModularSV;
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
@Service("cmsModularSV")
public class CmsModularSVImpl extends GeneralSQLSVImpl implements ICmsModularSV {

    @Resource(name = "SEQ_CMS_MODULAR")
    private PaasSequence seqCmsModular;
    @Resource
    private CmsModularMapper cmsModularMapper;
    @Resource
    private ICmsComponentSV sv;

    /**
     * TODO 是否存在模块.
     * 存在：true
     * 不存在：false
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageTypeSV#queryCmsPageTypeList(com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO)
     */
    @Override
    public boolean isExistModular(CmsModularReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsModularCriteria cmsModularCriteria = new CmsModularCriteria();
        Criteria criteria = cmsModularCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdNotEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getModularName())) {
            criteria.andModularNameEqualTo(dto.getModularName());
        }
        if (StringUtil.isNotBlank(dto.getPlatformType())) {
            criteria.andPlatformTypeEqualTo(dto.getPlatformType());
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
        
        cmsModularCriteria.setOrderByClause("CREATE_TIME DESC");
        
        boolean isExist = false;//不存在
        /*2.迭代查询结果*/
        List<CmsModular> cmsModularList = cmsModularMapper.selectByExample(cmsModularCriteria);
        if(cmsModularList != null && cmsModularList.size()>0){
            isExist = true;
        }
        return isExist;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularSV#saveCmsModular(com.zengshi.ecp.cms.dao.model.CmsModular)
     */
    @Override
    public CmsModularRespDTO addCmsModular(CmsModularReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsModular bo = new CmsModular();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsModular.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsModularMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsModularRespDTO respDTO = new CmsModularRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularSV#updateCmsModular(com.zengshi.ecp.cms.dao.model.CmsModular)
     */
    @Override
    public CmsModularRespDTO updateCmsModular(CmsModularReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsModular bo = new CmsModular();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新模块的原子方法*/
        return this.updateCmsModular(bo);
    }
    
    /** 
     * updateCmsModular:(更新模块的原子方法). <br/> 
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
    public CmsModularRespDTO updateCmsModular(CmsModular bo) throws BusinessException {
        cmsModularMapper.updateByPrimaryKeySelective(bo);
        CmsModularRespDTO respDTO = new CmsModularRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularSV#deleteCmsModular(java.lang.Long)
     */
    @Override
    public void deleteCmsModular(String id) throws BusinessException {
        CmsModular bo = new CmsModular();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsModular(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularSV#deleteCmsModularBatch(java.util.List)
     */
    @Override
    public void deleteCmsModularBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsModular(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularSV#changeStatusCmsModular(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsModular(String id, String status) throws BusinessException {
        CmsModular bo = new CmsModular();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsModular(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularSV#changeStatusCmsModularBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsModularBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsModular(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularSV#queryCmsModular(com.zengshi.ecp.cms.dubbo.dto.CmsModularReqDTO)
     */
    @Override
    public CmsModularRespDTO queryCmsModular(CmsModularReqDTO dto) throws BusinessException {
        CmsModularRespDTO cmsModularRespDTO = new CmsModularRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsModular bo = cmsModularMapper.selectByPrimaryKey(dto.getId());
            cmsModularRespDTO = conversionObject(bo);
        }
        
        return cmsModularRespDTO;
    }
    
    /**
     * TODO 查询模块列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularSV#queryCmsModularList(com.zengshi.ecp.cms.dubbo.dto.CmsModularReqDTO)
     */
    @Override
    public List<CmsModularRespDTO> queryCmsModularList(CmsModularReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsModularCriteria cmsModularCriteria = new CmsModularCriteria();
        Criteria criteria = cmsModularCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getModularName())) {
            criteria.andModularNameLike("%"+dto.getModularName()+"%");
        }
        if (StringUtil.isNotBlank(dto.getModularType())) {
            criteria.andModularTypeEqualTo(dto.getModularType());
        }
        if (StringUtil.isNotBlank(dto.getPlatformType())) {
            criteria.andPlatformTypeEqualTo(dto.getPlatformType());
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
        cmsModularCriteria.setOrderByClause("CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsModularRespDTO> cmsModularRespDTOList =  new ArrayList<CmsModularRespDTO>();
        List<CmsModular> cmsModularList = cmsModularMapper.selectByExample(cmsModularCriteria);
        if(CollectionUtils.isNotEmpty(cmsModularList)){
            for(CmsModular bo :cmsModularList){
                CmsModularRespDTO cmsModularRespDTO = conversionObject(bo);
                cmsModularRespDTOList.add(cmsModularRespDTO);
            }
        }
        
        return cmsModularRespDTOList;

    }


    /** 
     * TODO 查询模块，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularSV#queryCmsModularPage(com.zengshi.ecp.cms.dubbo.dto.CmsModularReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsModularRespDTO> queryCmsModularPage(CmsModularReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索模块 */
        CmsModularCriteria cmsModularCriteria = new CmsModularCriteria();
        Criteria criteria = cmsModularCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getModularName())) {
            criteria.andModularNameLike("%"+dto.getModularName()+"%");
        }
        if (StringUtil.isNotBlank(dto.getModularType())) {
            criteria.andModularTypeEqualTo(dto.getModularType());
        }
        if (StringUtil.isNotBlank(dto.getPlatformType())) {
            criteria.andPlatformTypeEqualTo(dto.getPlatformType());
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
        cmsModularCriteria.setOrderByClause("CREATE_TIME DESC");
        cmsModularCriteria.setLimitClauseCount(dto.getPageSize());
        cmsModularCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsModularCriteria,false,new PaginationCallback<CmsModular, CmsModularRespDTO>(){

            @Override
            public List<CmsModular> queryDB(BaseCriteria criteria) {
                return cmsModularMapper.selectByExample((CmsModularCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsModularMapper.countByExample((CmsModularCriteria)criteria);
            }

            @Override
            public CmsModularRespDTO warpReturnObject(CmsModular bo) {
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
    private CmsModularRespDTO conversionObject(CmsModular bo){
        CmsModularRespDTO dto = new CmsModularRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        
        // 2.遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String modularTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_MODULAR_TYPE, dto.getModularType());
        dto.setModularTypeZH(modularTypeZH);
        String platformTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_PLATFORM_TYPE, dto.getPlatformType());
        dto.setPlatformTypeZH(platformTypeZH);
        return dto;
    }

}
