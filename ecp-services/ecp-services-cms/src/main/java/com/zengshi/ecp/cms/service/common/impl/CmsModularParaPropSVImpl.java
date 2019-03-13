package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsModularParaPropMapper;
import com.zengshi.ecp.cms.dao.model.CmsModularParaProp;
import com.zengshi.ecp.cms.dao.model.CmsModularParaPropCriteria;
import com.zengshi.ecp.cms.dao.model.CmsModularParaPropCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularPropValReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularPropValRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsModularParaPropSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsModularPropValSV;
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
@Service("cmsModularParaPropSV")
public class CmsModularParaPropSVImpl extends GeneralSQLSVImpl implements ICmsModularParaPropSV {

    @Resource(name = "SEQ_CMS_MODULAR_PARA_PROP")
    private PaasSequence seqCmsModularParaProp;
    @Resource
    private CmsModularParaPropMapper cmsModularParaPropMapper;
    @Resource
    private ICmsModularPropValSV cmsModularPropValSV;

    /**
     * TODO 是否存在模块属性.
     * 存在：true
     * 不存在：false
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageTypeSV#queryCmsPageTypeList(com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO)
     */
    @Override
    public boolean isExistModularParaProp(CmsModularParaPropReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsModularParaPropCriteria cmsModularParaPropCriteria = new CmsModularParaPropCriteria();
        Criteria criteria = cmsModularParaPropCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdNotEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getModularId())) {
            criteria.andModularIdEqualTo(dto.getModularId());
        }
        if (StringUtil.isNotBlank(dto.getPropName())) {
            criteria.andPropNameEqualTo(dto.getPropName());
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
        
        cmsModularParaPropCriteria.setOrderByClause("CREATE_TIME ASC");
        
        boolean isExist = false;//不存在
        /*2.迭代查询结果*/
        List<CmsModularParaProp> cmsModularList = cmsModularParaPropMapper.selectByExample(cmsModularParaPropCriteria);
        if(cmsModularList != null && cmsModularList.size()>0){
            isExist = true;
        }
        return isExist;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularParaPropSV#saveCmsModularParaProp(com.zengshi.ecp.cms.dao.model.CmsModularParaProp)
     */
    @Override
    public CmsModularParaPropRespDTO addCmsModularParaProp(CmsModularParaPropReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsModularParaProp bo = new CmsModularParaProp();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsModularParaProp.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsModularParaPropMapper.insertSelective(bo);
        
        /*3.调模块属性模块属性值dao层接口 */
        if(CollectionUtils.isNotEmpty(dto.getModularPropValReqDTOList())){
            for(CmsModularPropValReqDTO modularPropValReqDTO : dto.getModularPropValReqDTOList()){
                modularPropValReqDTO.setPropId(id);
                cmsModularPropValSV.addCmsModularPropVal(modularPropValReqDTO);
            }
        }
        
        /*3.将结果返回*/
        CmsModularParaPropRespDTO respDTO = new CmsModularParaPropRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularParaPropSV#updateCmsModularParaProp(com.zengshi.ecp.cms.dao.model.CmsModularParaProp)
     */
    @Override
    public CmsModularParaPropRespDTO updateCmsModularParaProp(CmsModularParaPropReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsModularParaProp bo = new CmsModularParaProp();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        if(StringUtil.isNotEmpty(dto.getId())){
            //先将所有有关属性值失效
            CmsModularPropValReqDTO modularPropValReqDTO = new CmsModularPropValReqDTO();
            modularPropValReqDTO.setPropId(dto.getId());
            cmsModularPropValSV.deleteCmsModularPropValSelective(modularPropValReqDTO);
            modularPropValReqDTO = null;
            /* 2.更新属性值
             * (1)先判断属性值ID是否存在，如果存在，则直接更新
             * (2)不存在，则添加新的记录。
             */
            List<CmsModularPropValReqDTO> modularPropValReqDTOList = dto.getModularPropValReqDTOList();
            if(CollectionUtils.isNotEmpty(modularPropValReqDTOList)){
                for(int i=0;i<modularPropValReqDTOList.size();i++){
                    CmsModularPropValReqDTO modularPropValReqDTOTemp = modularPropValReqDTOList.get(i);
                    if(modularPropValReqDTOTemp != null){
                        //更新
                        if(StringUtil.isNotEmpty(modularPropValReqDTOTemp.getId())){
                            cmsModularPropValSV.updateCmsModularPropVal(modularPropValReqDTOTemp);
                        }else{//新增
                            modularPropValReqDTOTemp.setPropId(dto.getId());
                            cmsModularPropValSV.addCmsModularPropVal(modularPropValReqDTOTemp);
                        } 
                    }
                }
            }
        }
        
        /*2.更新属性值的原子方法*/
        cmsModularParaPropMapper.updateByPrimaryKey(bo);
        CmsModularParaPropRespDTO respDTO = new CmsModularParaPropRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }
    
    /** 
     * updateCmsModularParaProp:(更新模块属性的原子方法). <br/> 
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
    public CmsModularParaPropRespDTO updateCmsModularParaProp(CmsModularParaProp bo) throws BusinessException {
        cmsModularParaPropMapper.updateByPrimaryKeySelective(bo);
        CmsModularParaPropRespDTO respDTO = new CmsModularParaPropRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularParaPropSV#deleteCmsModularParaProp(java.lang.Long)
     */
    @Override
    public void deleteCmsModularParaProp(String id) throws BusinessException {
        CmsModularParaProp bo = new CmsModularParaProp();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsModularParaProp(bo);
        
        //使失效属性时，需要更新属性值状态（使失效）
        /* 1.查询已发布属性值 */
        CmsModularPropValReqDTO dto = new CmsModularPropValReqDTO();
        dto.setPropId(Long.parseLong(id));
        dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        List<CmsModularPropValRespDTO> modularPropValRespDTOList= cmsModularPropValSV.queryCmsModularPropValList(dto);
        
        /* 2.更新属性值  */
        for(CmsModularPropValRespDTO modularPropValRespDTO : modularPropValRespDTOList){
            CmsModularPropValReqDTO modularPropValReqDTO = new CmsModularPropValReqDTO();
            modularPropValReqDTO.setId(modularPropValRespDTO.getId());
            modularPropValReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
            cmsModularPropValSV.updateCmsModularPropVal(modularPropValReqDTO);
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularParaPropSV#deleteCmsModularParaPropBatch(java.util.List)
     */
    @Override
    public void deleteCmsModularParaPropBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsModularParaProp(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularParaPropSV#changeStatusCmsModularParaProp(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsModularParaProp(String id, String status) throws BusinessException {
        CmsModularParaProp bo = new CmsModularParaProp();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsModularParaProp(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularParaPropSV#changeStatusCmsModularParaPropBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsModularParaPropBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsModularParaProp(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularParaPropSV#queryCmsModularParaProp(com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropReqDTO)
     */
    @Override
    public CmsModularParaPropRespDTO queryCmsModularParaProp(CmsModularParaPropReqDTO dto) throws BusinessException {
        CmsModularParaPropRespDTO cmsModularParaPropRespDTO = new CmsModularParaPropRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsModularParaProp bo = cmsModularParaPropMapper.selectByPrimaryKey(dto.getId());
            cmsModularParaPropRespDTO = conversionObject(bo);
            
            /* 2.查询属性值 */
            CmsModularPropValReqDTO modularPropValReqDTO  = new CmsModularPropValReqDTO();
            modularPropValReqDTO.setPropId(dto.getId());
            //由于布局类型只有（1已发布，2失效）状态，因此根据页面类型状态查询。
            if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2.equals(dto.getStatus())){
                modularPropValReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
            }else{
                modularPropValReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            }
            List<CmsModularPropValRespDTO> modularPropValRespDTOList = cmsModularPropValSV.queryCmsModularPropValList(modularPropValReqDTO);
            cmsModularParaPropRespDTO.setModularPropValRespDTOList(modularPropValRespDTOList);
        }
        
        return cmsModularParaPropRespDTO;
    }
    
    /**
     * TODO 查询模块属性列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularParaPropSV#queryCmsModularParaPropList(com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropReqDTO)
     */
    @Override
    public List<CmsModularParaPropRespDTO> queryCmsModularParaPropList(CmsModularParaPropReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsModularParaPropCriteria cmsModularParaPropCriteria = new CmsModularParaPropCriteria();
        Criteria criteria = cmsModularParaPropCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPropName())) {
            criteria.andPropNameLike("%"+dto.getPropName()+"%");
        }
        if (StringUtil.isNotEmpty(dto.getModularId())) {
            criteria.andModularIdEqualTo(dto.getModularId());
        }
        if (StringUtil.isNotEmpty(dto.getIfHaveto())) {
            criteria.andIfHavetoEqualTo(dto.getIfHaveto());
        }
        if (StringUtil.isNotEmpty(dto.getPropValueType())) {
            criteria.andPropValueTypeEqualTo(dto.getPropValueType());
        }
        if (StringUtil.isNotEmpty(dto.getControlPropId())) {
            criteria.andControlPropIdEqualTo(dto.getControlPropId());
        }
        if (StringUtil.isNotEmpty(dto.getIsAutobuild())) {
            criteria.andIsAutobuildEqualTo(dto.getIsAutobuild());
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
        cmsModularParaPropCriteria.setOrderByClause("SORT_NO ASC,CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsModularParaPropRespDTO> cmsModularParaPropRespDTOList =  new ArrayList<CmsModularParaPropRespDTO>();
        List<CmsModularParaProp> cmsModularParaPropList = cmsModularParaPropMapper.selectByExample(cmsModularParaPropCriteria);
        if(CollectionUtils.isNotEmpty(cmsModularParaPropList)){
            for(CmsModularParaProp bo :cmsModularParaPropList){
                CmsModularParaPropRespDTO cmsModularParaPropRespDTO = conversionObject(bo);
                cmsModularParaPropRespDTOList.add(cmsModularParaPropRespDTO);
                
                /* 2.1查询属性值 */
                CmsModularPropValReqDTO modularPropValReqDTO  = new CmsModularPropValReqDTO();
                modularPropValReqDTO.setPropId(bo.getId());
                modularPropValReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                List<CmsModularPropValRespDTO> modularPropValRespDTOList = cmsModularPropValSV.queryCmsModularPropValList(modularPropValReqDTO);
                cmsModularParaPropRespDTO.setModularPropValRespDTOList(modularPropValRespDTOList);
            }
        }
        
        return cmsModularParaPropRespDTOList;

    }


    /** 
     * TODO 查询模块属性，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsModularParaPropSV#queryCmsModularParaPropPage(com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsModularParaPropRespDTO> queryCmsModularParaPropPage(CmsModularParaPropReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索模块属性 */
        CmsModularParaPropCriteria cmsModularParaPropCriteria = new CmsModularParaPropCriteria();
        Criteria criteria = cmsModularParaPropCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPropName())) {
            criteria.andPropNameLike("%"+dto.getPropName()+"%");
        }
        if (StringUtil.isNotEmpty(dto.getModularId())) {
            criteria.andModularIdEqualTo(dto.getModularId());
        }
        if (StringUtil.isNotEmpty(dto.getIfHaveto())) {
            criteria.andIfHavetoEqualTo(dto.getIfHaveto());
        }
        if (StringUtil.isNotEmpty(dto.getPropValueType())) {
            criteria.andPropValueTypeEqualTo(dto.getPropValueType());
        }
        if (StringUtil.isNotEmpty(dto.getControlPropId())) {
            criteria.andControlPropIdEqualTo(dto.getControlPropId());
        }
        if (StringUtil.isNotEmpty(dto.getIsAutobuild())) {
            criteria.andIsAutobuildEqualTo(dto.getIsAutobuild());
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
        cmsModularParaPropCriteria.setOrderByClause("SORT_NO ASC,CREATE_TIME ASC");
        cmsModularParaPropCriteria.setLimitClauseCount(dto.getPageSize());
        cmsModularParaPropCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsModularParaPropCriteria,false,new PaginationCallback<CmsModularParaProp, CmsModularParaPropRespDTO>(){

            @Override
            public List<CmsModularParaProp> queryDB(BaseCriteria criteria) {
                return cmsModularParaPropMapper.selectByExample((CmsModularParaPropCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsModularParaPropMapper.countByExample((CmsModularParaPropCriteria)criteria);
            }

            @Override
            public CmsModularParaPropRespDTO warpReturnObject(CmsModularParaProp bo) {
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
    private CmsModularParaPropRespDTO conversionObject(CmsModularParaProp bo){
        CmsModularParaPropRespDTO dto = new CmsModularParaPropRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        
        // 2.遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String ifHavetoZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.PUBLIC_PARAM_ISNOT, dto.getIfHaveto());
        dto.setIfHavetoZH(ifHavetoZH);
        String propValueTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_PROP_VALUE_TYPE, dto.getPropValueType());
        dto.setPropValueTypeZH(propValueTypeZH);
        String isAutobuildZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.PUBLIC_PARAM_ISNOT, dto.getIsAutobuild());
        dto.setIsAutobuildZH(isAutobuildZH);
        
        return dto;
    }

}
