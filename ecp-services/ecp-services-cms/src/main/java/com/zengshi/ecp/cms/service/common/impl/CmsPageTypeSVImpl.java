package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsPageTypeMapper;
import com.zengshi.ecp.cms.dao.model.CmsPageType;
import com.zengshi.ecp.cms.dao.model.CmsPageTypeCriteria;
import com.zengshi.ecp.cms.dao.model.CmsPageTypeCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV;
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
@Service("cmsPageTypeSV")
public class CmsPageTypeSVImpl extends GeneralSQLSVImpl implements ICmsPageTypeSV {

    @Resource(name = "SEQ_CMS_PAGE_TYPE")
    private PaasSequence seqCmsPageType;
    @Resource
    private CmsPageTypeMapper cmsPageTypeMapper;
    @Resource
    private ICmsLayoutTypeSV cmsLayoutTypeSV;

    /**
     * TODO 是否存在页面类型.
     * 存在：true
     * 不存在：false
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageTypeSV#queryCmsPageTypeList(com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO)
     */
    @Override
    public boolean isExistPageType(CmsPageTypeReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsPageTypeCriteria cmsPageTypeCriteria = new CmsPageTypeCriteria();
        Criteria criteria = cmsPageTypeCriteria.createCriteria();
        if (StringUtil.isNotBlank(dto.getPageTypeName())) {
            criteria.andPageTypeNameEqualTo(dto.getPageTypeName());
        }
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdNotEqualTo(dto.getId());
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
        
        cmsPageTypeCriteria.setOrderByClause("CREATE_TIME DESC");
        
        boolean isExist = false;//不存在
        /*2.迭代查询结果*/
        List<CmsPageType> cmsPageTypeList = cmsPageTypeMapper.selectByExample(cmsPageTypeCriteria);
        if(cmsPageTypeList != null && cmsPageTypeList.size()>0){
            isExist = true;
        }
        return isExist;
    }
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageTypeSV#saveCmsPageType(com.zengshi.ecp.cms.dao.model.CmsPageType)
     */
    @Override
    public CmsPageTypeRespDTO addCmsPageType(CmsPageTypeReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPageType bo = new CmsPageType();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsPageType.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsPageTypeMapper.insertSelective(bo);
        /*3.调楼层模板内容位置dao层接口 */
        if(CollectionUtils.isNotEmpty(dto.getLayoutTypeReqDTOList())){
            for(CmsLayoutTypeReqDTO layoutTypeReqDTO : dto.getLayoutTypeReqDTOList()){
                layoutTypeReqDTO.setPageTypeId(id);;
                cmsLayoutTypeSV.addCmsLayoutType(layoutTypeReqDTO);
            }
        }
        
        /*4.将结果返回*/
        CmsPageTypeRespDTO respDTO = new CmsPageTypeRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageTypeSV#updateCmsPageType(com.zengshi.ecp.cms.dao.model.CmsPageType)
     */
    @Override
    public CmsPageTypeRespDTO updateCmsPageType(CmsPageTypeReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPageType bo = new CmsPageType();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        if(StringUtil.isNotEmpty(dto.getId())){
            /*2.先将所有有关布局类型失效*/
            CmsLayoutTypeReqDTO layoutTypeReqDTO = new CmsLayoutTypeReqDTO();
            layoutTypeReqDTO.setPageTypeId(dto.getId());
            cmsLayoutTypeSV.deleteCmsLayoutTypeSelective(layoutTypeReqDTO);
            layoutTypeReqDTO = null;
            /* 3.更新布局类型
             * (1)先判断布局类型ID是否存在，如果存在，则直接更新
             * (2)不存在，则添加新的记录。
             */
            List<CmsLayoutTypeReqDTO> layoutTypeReqDTOList = dto.getLayoutTypeReqDTOList();
            if(CollectionUtils.isNotEmpty(layoutTypeReqDTOList)){
                for(int i=0;i<layoutTypeReqDTOList.size();i++){
                    CmsLayoutTypeReqDTO layoutTypeReqDTOTemp = layoutTypeReqDTOList.get(i);
                    if(layoutTypeReqDTOTemp != null){
                        //更新
                        if(StringUtil.isNotEmpty(layoutTypeReqDTOTemp.getId())){
                            cmsLayoutTypeSV.updateCmsLayoutType(layoutTypeReqDTOTemp);
                        }else{//新增
                            layoutTypeReqDTOTemp.setPageTypeId(dto.getId());
                            cmsLayoutTypeSV.addCmsLayoutType(layoutTypeReqDTOTemp);
                        } 
                    }
                }
            }
        }
        
        /*4.更新布局类型的原子方法*/
        return this.updateCmsPageType(bo);
    }
    
    /** 
     * updateCmsPageType:(更新楼层的原子方法). <br/> 
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
    public CmsPageTypeRespDTO updateCmsPageType(CmsPageType bo) throws BusinessException {
        cmsPageTypeMapper.updateByPrimaryKeySelective(bo);
        CmsPageTypeRespDTO respDTO = new CmsPageTypeRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageTypeSV#deleteCmsPageType(java.lang.Long)
     */
    @Override
    public void deleteCmsPageType(String id) throws BusinessException {
        CmsPageType bo = new CmsPageType();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsPageType(bo);
        
        //使失效页面类型时，需要更新布局类型状态（使失效）
        /* 1.查询已发布布局类型 */
        CmsLayoutTypeReqDTO dto = new CmsLayoutTypeReqDTO();
        dto.setPageTypeId(Long.parseLong(id));
        dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        List<CmsLayoutTypeRespDTO> layoutTypeRespDTOList= cmsLayoutTypeSV.queryCmsLayoutTypeList(dto);
        
        /* 2.更新布局类型 */
        for(CmsLayoutTypeRespDTO layoutTypeRespDTO : layoutTypeRespDTOList){
            CmsLayoutTypeReqDTO layoutTypeReqDTO = new CmsLayoutTypeReqDTO();
            layoutTypeReqDTO.setId(layoutTypeRespDTO.getId());
            layoutTypeReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
            cmsLayoutTypeSV.updateCmsLayoutType(layoutTypeReqDTO);
        }
        
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageTypeSV#deleteCmsPageTypeBatch(java.util.List)
     */
    @Override
    public void deleteCmsPageTypeBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsPageType(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageTypeSV#changeStatusCmsPageType(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPageType(String id, String status) throws BusinessException {
        CmsPageType bo = new CmsPageType();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsPageType(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageTypeSV#changeStatusCmsPageTypeBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPageTypeBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsPageType(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageTypeSV#queryCmsPageType(com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO)
     */
    @Override
    public CmsPageTypeRespDTO queryCmsPageType(CmsPageTypeReqDTO dto) throws BusinessException {
        CmsPageTypeRespDTO cmsPageTypeRespDTO = new CmsPageTypeRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsPageType bo = cmsPageTypeMapper.selectByPrimaryKey(dto.getId());
            cmsPageTypeRespDTO = conversionObject(bo);
            
            /* 2.查询布局类型 */
            CmsLayoutTypeReqDTO layoutTypeReqDTO  = new CmsLayoutTypeReqDTO();
            layoutTypeReqDTO.setPageTypeId(dto.getId());
            //由于布局类型只有（1已发布，2失效）状态，因此根据页面类型状态查询。
            if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2.equals(dto.getStatus())){
                layoutTypeReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
            }else{
                layoutTypeReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            }
            List<CmsLayoutTypeRespDTO> layoutTypeRespDTOList = cmsLayoutTypeSV.queryCmsLayoutTypeList(layoutTypeReqDTO);
            cmsPageTypeRespDTO.setLayoutTypeRespDTOList(layoutTypeRespDTOList);
        }
        
        return cmsPageTypeRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageTypeSV#queryCmsPageTypeList(com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO)
     */
    @Override
    public List<CmsPageTypeRespDTO> queryCmsPageTypeList(CmsPageTypeReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsPageTypeCriteria cmsPageTypeCriteria = new CmsPageTypeCriteria();
        Criteria criteria = cmsPageTypeCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getPageTypeName())) {
            criteria.andPageTypeNameLike("%"+dto.getPageTypeName()+"%");
        }
        if (StringUtil.isNotBlank(dto.getPlatformType())) {
        	criteria.andPlatformTypeEqualTo(dto.getPlatformType().trim());
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
        cmsPageTypeCriteria.setOrderByClause("CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsPageTypeRespDTO> cmsPageTypeRespDTOList =  new ArrayList<CmsPageTypeRespDTO>();
        List<CmsPageType> cmsPageTypeList = cmsPageTypeMapper.selectByExample(cmsPageTypeCriteria);
        if(CollectionUtils.isNotEmpty(cmsPageTypeList)){
            for(CmsPageType bo :cmsPageTypeList){
                CmsPageTypeRespDTO cmsPageTypeRespDTO = conversionObject(bo);
                cmsPageTypeRespDTOList.add(cmsPageTypeRespDTO);
            }
        }
        
        return cmsPageTypeRespDTOList;
    }
    
    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageTypeSV#queryCmsPageTypePage(com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsPageTypeRespDTO> queryCmsPageTypePage(CmsPageTypeReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsPageTypeCriteria cmsPageTypeCriteria = new CmsPageTypeCriteria();
        Criteria criteria = cmsPageTypeCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getPageTypeName())) {
            criteria.andPageTypeNameLike("%"+dto.getPageTypeName()+"%");
        }
        if (StringUtil.isNotBlank(dto.getPlatformType())) {
            criteria.andPlatformTypeEqualTo(dto.getPlatformType().trim());
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
        cmsPageTypeCriteria.setOrderByClause("CREATE_TIME DESC");
        cmsPageTypeCriteria.setLimitClauseCount(dto.getPageSize());
        cmsPageTypeCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsPageTypeCriteria,false,new PaginationCallback<CmsPageType, CmsPageTypeRespDTO>(){

            @Override
            public List<CmsPageType> queryDB(BaseCriteria criteria) {
                return cmsPageTypeMapper.selectByExample((CmsPageTypeCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsPageTypeMapper.countByExample((CmsPageTypeCriteria)criteria);
            }

            @Override
            public CmsPageTypeRespDTO warpReturnObject(CmsPageType bo) {
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
    private CmsPageTypeRespDTO conversionObject(CmsPageType bo){
        CmsPageTypeRespDTO dto = new CmsPageTypeRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        
        // 2.遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String platformTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_PLATFORM_TYPE, dto.getPlatformType());
        dto.setPlatformTypeZH(platformTypeZH);
        return dto;
    }

}
