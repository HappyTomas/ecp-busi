package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsModularPropValMapper;
import com.zengshi.ecp.cms.dao.model.CmsModularPropVal;
import com.zengshi.ecp.cms.dao.model.CmsModularPropValCriteria;
import com.zengshi.ecp.cms.dao.model.CmsModularPropValCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularPropValReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularPropValRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropValSV;
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
@Service("cmsModularPropValSV")
public class CmsModularPropValSVImpl extends GeneralSQLSVImpl implements ICmsModularPropValSV {

    @Resource(name = "SEQ_CMS_MODULAR_PROP_VAL")
    private PaasSequence seqCmsModularPropVal;
    @Resource
    private CmsModularPropValMapper cmsModularPropValMapper;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropValSV#saveCmsModularPropVal(com.zengshi.ecp.cms.dao.model.CmsModularPropVal)
     */
    @Override
    public CmsModularPropValRespDTO addCmsModularPropVal(CmsModularPropValReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsModularPropVal bo = new CmsModularPropVal();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsModularPropVal.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsModularPropValMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsModularPropValRespDTO respDTO = new CmsModularPropValRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropValSV#updateCmsModularPropVal(com.zengshi.ecp.cms.dao.model.CmsModularPropVal)
     */
    @Override
    public CmsModularPropValRespDTO updateCmsModularPropVal(CmsModularPropValReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsModularPropVal bo = new CmsModularPropVal();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsModularPropVal(bo);
    }
    
    /** 
     * updateCmsModularPropVal:(更新楼层的原子方法). <br/> 
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
    public CmsModularPropValRespDTO updateCmsModularPropVal(CmsModularPropVal bo) throws BusinessException {
        cmsModularPropValMapper.updateByPrimaryKeySelective(bo);
        CmsModularPropValRespDTO respDTO = new CmsModularPropValRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropValSV#deleteCmsModularPropVal(java.lang.Long)
     */
    @Override
    public void deleteCmsModularPropVal(String id) throws BusinessException {
        CmsModularPropVal bo = new CmsModularPropVal();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsModularPropVal(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropValSV#deleteCmsModularPropValBatch(java.util.List)
     */
    @Override
    public void deleteCmsModularPropValBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsModularPropVal(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropValSV#changeStatusCmsModularPropVal(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsModularPropVal(String id, String status) throws BusinessException {
        CmsModularPropVal bo = new CmsModularPropVal();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsModularPropVal(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropValSV#changeStatusCmsModularPropValBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsModularPropValBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsModularPropVal(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropValSV#queryCmsModularPropVal(com.zengshi.ecp.cms.dubbo.dto.CmsModularPropValReqDTO)
     */
    @Override
    public CmsModularPropValRespDTO queryCmsModularPropVal(CmsModularPropValReqDTO dto) throws BusinessException {
        CmsModularPropValRespDTO cmsModularPropValRespDTO = new CmsModularPropValRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsModularPropVal bo = cmsModularPropValMapper.selectByPrimaryKey(dto.getId());
            cmsModularPropValRespDTO = conversionObject(bo);
        }
        
        return cmsModularPropValRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropValSV#queryCmsModularPropValList(com.zengshi.ecp.cms.dubbo.dto.CmsModularPropValReqDTO)
     */
    @Override
    public List<CmsModularPropValRespDTO> queryCmsModularPropValList(CmsModularPropValReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsModularPropValCriteria cmsModularPropValCriteria = new CmsModularPropValCriteria();
        Criteria criteria = cmsModularPropValCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPropId())) {
            criteria.andPropIdEqualTo(dto.getPropId());
        }
        if (StringUtil.isNotEmpty(dto.getPropValue())) {
            criteria.andPropValueEqualTo(dto.getPropValue());
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
        cmsModularPropValCriteria.setOrderByClause("SORT_NO ASC,CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsModularPropValRespDTO> cmsModularPropValRespDTOList =  new ArrayList<CmsModularPropValRespDTO>();
        List<CmsModularPropVal> cmsModularPropValList = cmsModularPropValMapper.selectByExample(cmsModularPropValCriteria);
        if(CollectionUtils.isNotEmpty(cmsModularPropValList)){
            for(CmsModularPropVal bo :cmsModularPropValList){
                CmsModularPropValRespDTO cmsModularPropValRespDTO = conversionObject(bo);
                cmsModularPropValRespDTOList.add(cmsModularPropValRespDTO);
            }
        }
        
        return cmsModularPropValRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropValSV#queryCmsModularPropValPage(com.zengshi.ecp.cms.dubbo.dto.CmsModularPropValReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsModularPropValRespDTO> queryCmsModularPropValPage(CmsModularPropValReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsModularPropValCriteria cmsModularPropValCriteria = new CmsModularPropValCriteria();
        Criteria criteria = cmsModularPropValCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPropId())) {
            criteria.andPropIdEqualTo(dto.getPropId());
        }
        if (StringUtil.isNotEmpty(dto.getPropValue())) {
            criteria.andPropValueEqualTo(dto.getPropValue());
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
        cmsModularPropValCriteria.setOrderByClause("SORT_NO ASC,CREATE_TIME ASC");
        cmsModularPropValCriteria.setLimitClauseCount(dto.getPageSize());
        cmsModularPropValCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsModularPropValCriteria,false,new PaginationCallback<CmsModularPropVal, CmsModularPropValRespDTO>(){

            @Override
            public List<CmsModularPropVal> queryDB(BaseCriteria criteria) {
                return cmsModularPropValMapper.selectByExample((CmsModularPropValCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsModularPropValMapper.countByExample((CmsModularPropValCriteria)criteria);
            }

            @Override
            public CmsModularPropValRespDTO warpReturnObject(CmsModularPropVal bo) {
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
    private CmsModularPropValRespDTO conversionObject(CmsModularPropVal bo){
        CmsModularPropValRespDTO dto = new CmsModularPropValRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        
        // 2.遍历将编码转中文 
        /*String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);*/
        
        return dto;
    }
    
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV#deleteCmsLayoutTypeSelective(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO)
     */
    @Override
    public void deleteCmsModularPropValSelective(CmsModularPropValReqDTO dto) throws BusinessException {
        /* 1.根据条件检索楼层 */
        CmsModularPropValCriteria cmsModularPropValCriteria = new CmsModularPropValCriteria();
        Criteria criteria = cmsModularPropValCriteria.createCriteria();
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
        CmsModularPropVal bo = new CmsModularPropVal();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        
        cmsModularPropValMapper.updateByExampleSelective(bo, cmsModularPropValCriteria);
    }

}
