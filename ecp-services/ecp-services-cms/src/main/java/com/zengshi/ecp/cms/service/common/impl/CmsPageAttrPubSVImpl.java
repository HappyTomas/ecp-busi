package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsPageAttrPubMapper;
import com.zengshi.ecp.cms.dao.model.CmsPageAttrPub;
import com.zengshi.ecp.cms.dao.model.CmsPageAttrPubCriteria;
import com.zengshi.ecp.cms.dao.model.CmsPageAttrPubCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPubRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPubSV;
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
@Service("cmsPageAttrPubSV")
public class CmsPageAttrPubSVImpl extends GeneralSQLSVImpl implements ICmsPageAttrPubSV {

    @Resource(name = "SEQ_CMS_PAGE_ATTR_PUB")
    private PaasSequence seqCmsPageAttrPub;
    @Resource
    private CmsPageAttrPubMapper cmsPageAttrPubMapper;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPubSV#saveCmsPageAttrPub(com.zengshi.ecp.cms.dao.model.CmsPageAttrPub)
     */
    @Override
    public CmsPageAttrPubRespDTO addCmsPageAttrPub(CmsPageAttrPubReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPageAttrPub bo = new CmsPageAttrPub();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsPageAttrPub.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsPageAttrPubMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsPageAttrPubRespDTO respDTO = new CmsPageAttrPubRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPubSV#updateCmsPageAttrPub(com.zengshi.ecp.cms.dao.model.CmsPageAttrPub)
     */
    @Override
    public CmsPageAttrPubRespDTO updateCmsPageAttrPub(CmsPageAttrPubReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPageAttrPub bo = new CmsPageAttrPub();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsPageAttrPub(bo);
    }
    
    /** 
     * updateCmsPageAttrPub:(更新楼层的原子方法). <br/> 
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
    public CmsPageAttrPubRespDTO updateCmsPageAttrPub(CmsPageAttrPub bo) throws BusinessException {
        cmsPageAttrPubMapper.updateByPrimaryKeySelective(bo);
        CmsPageAttrPubRespDTO respDTO = new CmsPageAttrPubRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPubSV#deleteCmsPageAttrPub(java.lang.Long)
     */
    @Override
    public void deleteCmsPageAttrPub(String id) throws BusinessException {
        CmsPageAttrPub bo = new CmsPageAttrPub();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsPageAttrPub(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPubSV#deleteCmsPageAttrPubBatch(java.util.List)
     */
    @Override
    public void deleteCmsPageAttrPubBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsPageAttrPub(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPubSV#changeStatusCmsPageAttrPub(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPageAttrPub(String id, String status) throws BusinessException {
        CmsPageAttrPub bo = new CmsPageAttrPub();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsPageAttrPub(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPubSV#changeStatusCmsPageAttrPubBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPageAttrPubBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsPageAttrPub(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPubSV#queryCmsPageAttrPub(com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPubReqDTO)
     */
    @Override
    public CmsPageAttrPubRespDTO queryCmsPageAttrPub(CmsPageAttrPubReqDTO dto) throws BusinessException {
        CmsPageAttrPubRespDTO cmsAttrPubRespDTO = new CmsPageAttrPubRespDTO();
        /* 1.查询  */
        CmsPageAttrPubCriteria cmsAttrPubCriteria = new CmsPageAttrPubCriteria();
        Criteria criteria = cmsAttrPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getPageAttrId())) {
            criteria.andPageAttrIdEqualTo(dto.getPageAttrId());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        cmsAttrPubCriteria.setOrderByClause("CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsPageAttrPub> cmsAttrPubList = cmsPageAttrPubMapper.selectByExample(cmsAttrPubCriteria);
        if(CollectionUtils.isNotEmpty(cmsAttrPubList)){
            cmsAttrPubRespDTO = conversionObject(cmsAttrPubList.get(0));
            if(StringUtil.isBlank(cmsAttrPubRespDTO.getBackgroupShowType())){//空则设置为不平铺
                cmsAttrPubRespDTO.setBackgroupShowType(CmsConstants.BackgroupShowType.CMS_BACKGROUPSHOWTYPE_02);
            }
        }
        return cmsAttrPubRespDTO;
    }
    
    /** 
     * updateCmsPageAttrPubByExample:(更新页面属性原子方法). <br/> 
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
    public CmsPageAttrPubRespDTO updateCmsPageAttrPubByExample(CmsPageAttrPubReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPageAttrPub bo = new CmsPageAttrPub();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /* 2.根据条件检索楼层商品 */
        CmsPageAttrPubCriteria cmsPageAttrPubCriteria = new CmsPageAttrPubCriteria();
        Criteria criteria = cmsPageAttrPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(bo.getPageId())) {
            criteria.andPageIdEqualTo(bo.getPageId());
        }
        if (StringUtil.isNotEmpty(bo.getPageAttrId())) {
            criteria.andPageAttrIdEqualTo(bo.getPageAttrId());
        }
        if (StringUtil.isNotEmpty(bo.getStatus())) {
            criteria.andStatusEqualTo(bo.getStatus());
        }
        cmsPageAttrPubMapper.updateByExampleSelective(bo, cmsPageAttrPubCriteria);
        
        /*3.将结果返回*/
        CmsPageAttrPubRespDTO respDTO = new CmsPageAttrPubRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPubSV#queryCmsPageAttrPubList(com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPubReqDTO)
     */
    @Override
    public List<CmsPageAttrPubRespDTO> queryCmsPageAttrPubList(CmsPageAttrPubReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsPageAttrPubCriteria cmsAttrPubCriteria = new CmsPageAttrPubCriteria();
        Criteria criteria = cmsAttrPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getPageAttrId())) {
            criteria.andPageAttrIdEqualTo(dto.getPageAttrId());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        cmsAttrPubCriteria.setOrderByClause("CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsPageAttrPubRespDTO> cmsAttrPubRespDTOList =  new ArrayList<CmsPageAttrPubRespDTO>();
        List<CmsPageAttrPub> cmsAttrPubList = cmsPageAttrPubMapper.selectByExample(cmsAttrPubCriteria);
        if(CollectionUtils.isNotEmpty(cmsAttrPubList)){
            for(CmsPageAttrPub bo :cmsAttrPubList){
                CmsPageAttrPubRespDTO cmsAttrPubRespDTO = conversionObject(bo);
                cmsAttrPubRespDTOList.add(cmsAttrPubRespDTO);
            }
        }
        
        return cmsAttrPubRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPubSV#queryCmsPageAttrPubPage(com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPubReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsPageAttrPubRespDTO> queryCmsPageAttrPubPage(CmsPageAttrPubReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsPageAttrPubCriteria cmsAttrPubCriteria = new CmsPageAttrPubCriteria();
        Criteria criteria = cmsAttrPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getPageAttrId())) {
            criteria.andPageAttrIdEqualTo(dto.getPageAttrId());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        cmsAttrPubCriteria.setOrderByClause("CREATE_TIME ASC");
        cmsAttrPubCriteria.setLimitClauseCount(dto.getPageSize());
        cmsAttrPubCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsAttrPubCriteria,false,new PaginationCallback<CmsPageAttrPub, CmsPageAttrPubRespDTO>(){

            @Override
            public List<CmsPageAttrPub> queryDB(BaseCriteria criteria) {
                return cmsPageAttrPubMapper.selectByExample((CmsPageAttrPubCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsPageAttrPubMapper.countByExample((CmsPageAttrPubCriteria)criteria);
            }

            @Override
            public CmsPageAttrPubRespDTO warpReturnObject(CmsPageAttrPub bo) {
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
    private CmsPageAttrPubRespDTO conversionObject(CmsPageAttrPub bo){
        CmsPageAttrPubRespDTO dto = new CmsPageAttrPubRespDTO();
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
     * deleteCmsPageAttrPubByExample:(更新页面属性原子方法). <br/> 
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
    public int deleteCmsPageAttrPubByExample(CmsPageAttrPubReqDTO dto) throws BusinessException {
        /*1.组装删除bo*/
        CmsPageAttrPub bo = new CmsPageAttrPub();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /* 2.查询条件 */
        CmsPageAttrPubCriteria cmsPageAttrPubCriteria = new CmsPageAttrPubCriteria();
        Criteria criteria = cmsPageAttrPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getPageAttrId())) {
            criteria.andPageAttrIdEqualTo(dto.getPageAttrId());
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
        return cmsPageAttrPubMapper.updateByExampleSelective(bo, cmsPageAttrPubCriteria);
    }
}
