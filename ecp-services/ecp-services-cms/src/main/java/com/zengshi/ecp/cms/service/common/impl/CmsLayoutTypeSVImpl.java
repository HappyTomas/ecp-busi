package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsLayoutTypeMapper;
import com.zengshi.ecp.cms.dao.model.CmsLayoutType;
import com.zengshi.ecp.cms.dao.model.CmsLayoutTypeCriteria;
import com.zengshi.ecp.cms.dao.model.CmsLayoutTypeCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV;
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
@Service("cmsLayoutTypeSV")
public class CmsLayoutTypeSVImpl extends GeneralSQLSVImpl implements ICmsLayoutTypeSV {

    @Resource(name = "SEQ_CMS_LAYOUT_TYPE")
    private PaasSequence seqCmsLayoutType;
    @Resource
    private CmsLayoutTypeMapper cmsLayoutTypeMapper;
    
    /**
     * TODO 是否存在页面类型.
     * 存在：true
     * 不存在：false
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageTypeSV#queryCmsPageTypeList(com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO)
     */
    @Override
    public boolean isExistLayoutType(CmsLayoutTypeReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsLayoutTypeCriteria cmsLayoutTypeCriteria = new CmsLayoutTypeCriteria();
        Criteria criteria = cmsLayoutTypeCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdNotEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getLayoutTypeName())) {
            criteria.andLayoutTypeNameEqualTo(dto.getLayoutTypeName());
        }
        if (StringUtil.isNotEmpty(dto.getPageTypeId())) {
            criteria.andPageTypeIdEqualTo(dto.getPageTypeId());
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
        
        cmsLayoutTypeCriteria.setOrderByClause("CREATE_TIME DESC");
        
        boolean isExist = false;//不存在
        /*2.迭代查询结果*/
        List<CmsLayoutType> cmsLayoutTypeList = cmsLayoutTypeMapper.selectByExample(cmsLayoutTypeCriteria);
        if(cmsLayoutTypeList != null && cmsLayoutTypeList.size()>0){
            isExist = true;
        }
        return isExist;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV#saveCmsLayoutType(com.zengshi.ecp.cms.dao.model.CmsLayoutType)
     */
    @Override
    public CmsLayoutTypeRespDTO addCmsLayoutType(CmsLayoutTypeReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsLayoutType bo = new CmsLayoutType();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsLayoutType.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsLayoutTypeMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsLayoutTypeRespDTO respDTO = new CmsLayoutTypeRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV#updateCmsLayoutType(com.zengshi.ecp.cms.dao.model.CmsLayoutType)
     */
    @Override
    public CmsLayoutTypeRespDTO updateCmsLayoutType(CmsLayoutTypeReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsLayoutType bo = new CmsLayoutType();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsLayoutType(bo);
    }
    
    /** 
     * updateCmsLayoutType:(更新楼层的原子方法). <br/> 
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
    public CmsLayoutTypeRespDTO updateCmsLayoutType(CmsLayoutType bo) throws BusinessException {
        cmsLayoutTypeMapper.updateByPrimaryKeySelective(bo);
        CmsLayoutTypeRespDTO respDTO = new CmsLayoutTypeRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV#deleteCmsLayoutType(java.lang.Long)
     */
    @Override
    public void deleteCmsLayoutType(String id) throws BusinessException {
        CmsLayoutType bo = new CmsLayoutType();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsLayoutType(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV#deleteCmsLayoutTypeBatch(java.util.List)
     */
    @Override
    public void deleteCmsLayoutTypeBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsLayoutType(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV#changeStatusCmsLayoutType(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsLayoutType(String id, String status) throws BusinessException {
        CmsLayoutType bo = new CmsLayoutType();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsLayoutType(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV#changeStatusCmsLayoutTypeBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsLayoutTypeBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsLayoutType(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV#queryCmsLayoutType(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO)
     */
    @Override
    public CmsLayoutTypeRespDTO queryCmsLayoutType(CmsLayoutTypeReqDTO dto) throws BusinessException {
        CmsLayoutTypeRespDTO cmsLayoutTypeRespDTO = new CmsLayoutTypeRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsLayoutType bo = cmsLayoutTypeMapper.selectByPrimaryKey(dto.getId());
            cmsLayoutTypeRespDTO = conversionObject(bo);
        }
        
        return cmsLayoutTypeRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV#queryCmsLayoutTypeList(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO)
     */
    @Override
    public List<CmsLayoutTypeRespDTO> queryCmsLayoutTypeList(CmsLayoutTypeReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsLayoutTypeCriteria cmsLayoutTypeCriteria = new CmsLayoutTypeCriteria();
        Criteria criteria = cmsLayoutTypeCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPageTypeId())) {
            criteria.andPageTypeIdEqualTo(dto.getPageTypeId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutTypeName())) {
            criteria.andLayoutTypeNameLike("%"+dto.getLayoutTypeName()+"%");
        }
        if (StringUtil.isNotEmpty(dto.getLayoutItemSize())) {
            criteria.andLayoutItemSizeLike("%"+dto.getLayoutItemSize()+"%");
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
        cmsLayoutTypeCriteria.setOrderByClause("CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsLayoutTypeRespDTO> cmsLayoutTypeRespDTOList =  new ArrayList<CmsLayoutTypeRespDTO>();
        List<CmsLayoutType> cmsLayoutTypeList = cmsLayoutTypeMapper.selectByExample(cmsLayoutTypeCriteria);
        if(CollectionUtils.isNotEmpty(cmsLayoutTypeList)){
            for(CmsLayoutType bo :cmsLayoutTypeList){
                CmsLayoutTypeRespDTO cmsLayoutTypeRespDTO = conversionObject(bo);
                cmsLayoutTypeRespDTOList.add(cmsLayoutTypeRespDTO);
            }
        }
        
        return cmsLayoutTypeRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV#queryCmsLayoutTypePage(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsLayoutTypeRespDTO> queryCmsLayoutTypePage(CmsLayoutTypeReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsLayoutTypeCriteria cmsLayoutTypeCriteria = new CmsLayoutTypeCriteria();
        Criteria criteria = cmsLayoutTypeCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPageTypeId())) {
            criteria.andPageTypeIdEqualTo(dto.getPageTypeId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutShowType())) {
            criteria.andLayoutShowTypeEqualTo(dto.getLayoutShowType());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutTypeName())) {
            criteria.andLayoutTypeNameLike("%"+dto.getLayoutTypeName()+"%");
        }
        if (StringUtil.isNotEmpty(dto.getLayoutItemSize())) {
            criteria.andLayoutItemSizeLike("%"+dto.getLayoutItemSize()+"%");
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
        
        cmsLayoutTypeCriteria.setOrderByClause("CREATE_TIME ASC");
        cmsLayoutTypeCriteria.setLimitClauseCount(dto.getPageSize());
        cmsLayoutTypeCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsLayoutTypeCriteria,false,new PaginationCallback<CmsLayoutType, CmsLayoutTypeRespDTO>(){

            @Override
            public List<CmsLayoutType> queryDB(BaseCriteria criteria) {
                return cmsLayoutTypeMapper.selectByExample((CmsLayoutTypeCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsLayoutTypeMapper.countByExample((CmsLayoutTypeCriteria)criteria);
            }

            @Override
            public CmsLayoutTypeRespDTO warpReturnObject(CmsLayoutType bo) {
                return conversionObject(bo);
            }
        
        });

    }
    
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV#deleteCmsLayoutTypeSelective(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO)
     */
    @Override
    public void deleteCmsLayoutTypeSelective(CmsLayoutTypeReqDTO dto) throws BusinessException {
        /* 1.根据条件检索楼层 */
        CmsLayoutTypeCriteria cmsLayoutTypeCriteria = new CmsLayoutTypeCriteria();
        Criteria criteria = cmsLayoutTypeCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPageTypeId())) {
            criteria.andPageTypeIdEqualTo(dto.getPageTypeId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutShowType())) {
            criteria.andLayoutShowTypeEqualTo(dto.getLayoutShowType());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutTypeName())) {
            criteria.andLayoutTypeNameLike("%"+dto.getLayoutTypeName()+"%");
        }
        if (StringUtil.isNotEmpty(dto.getLayoutItemSize())) {
            criteria.andLayoutItemSizeLike("%"+dto.getLayoutItemSize()+"%");
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
        CmsLayoutType bo = new CmsLayoutType();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        
        cmsLayoutTypeMapper.updateByExampleSelective(bo, cmsLayoutTypeCriteria);
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
    private CmsLayoutTypeRespDTO conversionObject(CmsLayoutType bo){
        CmsLayoutTypeRespDTO dto = new CmsLayoutTypeRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        
        // 2.遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String layoutShowTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_LAYOUT_SHOW_TYPE, dto.getLayoutShowType());
        dto.setLayoutShowTypeZH(layoutShowTypeZH);
        
        return dto;
    }


}
