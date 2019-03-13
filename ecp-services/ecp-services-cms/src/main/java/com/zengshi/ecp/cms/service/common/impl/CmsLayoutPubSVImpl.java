package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsLayoutPubMapper;
import com.zengshi.ecp.cms.dao.model.CmsLayoutPub;
import com.zengshi.ecp.cms.dao.model.CmsLayoutPubCriteria;
import com.zengshi.ecp.cms.dao.model.CmsLayoutPubCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPubRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPubSV;
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
@Service("cmsLayoutPubSV")
public class CmsLayoutPubSVImpl extends GeneralSQLSVImpl implements ICmsLayoutPubSV {

    @Resource(name = "SEQ_CMS_LAYOUT_PUB")
    private PaasSequence seqCmsLayoutPub;
    @Resource
    private CmsLayoutPubMapper cmsLayoutPubMapper;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPubSV#saveCmsLayoutPub(com.zengshi.ecp.cms.dao.model.CmsLayoutPub)
     */
    @Override
    public CmsLayoutPubRespDTO addCmsLayoutPub(CmsLayoutPubReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsLayoutPub bo = new CmsLayoutPub();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsLayoutPub.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsLayoutPubMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsLayoutPubRespDTO respDTO = new CmsLayoutPubRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPubSV#updateCmsLayoutPub(com.zengshi.ecp.cms.dao.model.CmsLayoutPub)
     */
    @Override
    public CmsLayoutPubRespDTO updateCmsLayoutPub(CmsLayoutPubReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsLayoutPub bo = new CmsLayoutPub();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsLayoutPub(bo);
    }
    
    /** 
     * updateCmsLayoutPub:(更新楼层的原子方法). <br/> 
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
    public CmsLayoutPubRespDTO updateCmsLayoutPub(CmsLayoutPub bo) throws BusinessException {
        cmsLayoutPubMapper.updateByPrimaryKeySelective(bo);
        CmsLayoutPubRespDTO respDTO = new CmsLayoutPubRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPubSV#deleteCmsLayoutPub(java.lang.Long)
     */
    @Override
    public void deleteCmsLayoutPub(String id) throws BusinessException {
        CmsLayoutPub bo = new CmsLayoutPub();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsLayoutPub(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPubSV#deleteCmsLayoutPubBatch(java.util.List)
     */
    @Override
    public void deleteCmsLayoutPubBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsLayoutPub(id);
            }
        }
    }

    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPubSV#deleteCmsLayoutPubByExample(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPubReqDTO)
     */
    @Override
    public int deleteCmsLayoutPubByExample(CmsLayoutPubReqDTO dto) throws BusinessException {
        /*1.组装删除bo*/
        CmsLayoutPub bo = new CmsLayoutPub();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /* 2.查询条件 */
        CmsLayoutPubCriteria cmsLayoutPubCriteria = new CmsLayoutPubCriteria();
        Criteria criteria = cmsLayoutPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutId())) {
            criteria.andLayoutIdEqualTo(dto.getLayoutId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutTypeId())) {
            criteria.andLayoutTypeIdEqualTo(dto.getLayoutTypeId());
        }
        if (StringUtil.isNotEmpty(dto.getShowOrder())) {
            criteria.andShowOrderEqualTo(dto.getShowOrder());
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
        
        return cmsLayoutPubMapper.updateByExampleSelective(bo, cmsLayoutPubCriteria);
    }
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPubSV#changeStatusCmsLayoutPub(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsLayoutPub(String id, String status) throws BusinessException {
        CmsLayoutPub bo = new CmsLayoutPub();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsLayoutPub(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPubSV#changeStatusCmsLayoutPubBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsLayoutPubBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsLayoutPub(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPubSV#queryCmsLayoutPub(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPubReqDTO)
     */
    @Override
    public CmsLayoutPubRespDTO queryCmsLayoutPub(CmsLayoutPubReqDTO dto) throws BusinessException {
        CmsLayoutPubRespDTO cmsLayoutPubRespDTO = new CmsLayoutPubRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsLayoutPub bo = cmsLayoutPubMapper.selectByPrimaryKey(dto.getId());
            cmsLayoutPubRespDTO = conversionObject(bo);
        }
        
        return cmsLayoutPubRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPubSV#queryCmsLayoutPubList(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPubReqDTO)
     */
    @Override
    public List<CmsLayoutPubRespDTO> queryCmsLayoutPubList(CmsLayoutPubReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsLayoutPubCriteria cmsLayoutPubCriteria = new CmsLayoutPubCriteria();
        Criteria criteria = cmsLayoutPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutId())) {
            criteria.andLayoutIdEqualTo(dto.getLayoutId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutTypeId())) {
            criteria.andLayoutTypeIdEqualTo(dto.getLayoutTypeId());
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
        
        cmsLayoutPubCriteria.setOrderByClause("SHOW_ORDER ASC,CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsLayoutPubRespDTO> cmsLayoutPubRespDTOList =  new ArrayList<CmsLayoutPubRespDTO>();
        List<CmsLayoutPub> cmsLayoutPubList = cmsLayoutPubMapper.selectByExample(cmsLayoutPubCriteria);
        if(CollectionUtils.isNotEmpty(cmsLayoutPubList)){
            for(CmsLayoutPub bo :cmsLayoutPubList){
                CmsLayoutPubRespDTO cmsLayoutPubRespDTO = conversionObject(bo);
                cmsLayoutPubRespDTOList.add(cmsLayoutPubRespDTO);
            }
        }
        
        return cmsLayoutPubRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPubSV#queryCmsLayoutPubPage(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPubReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsLayoutPubRespDTO> queryCmsLayoutPubPage(CmsLayoutPubReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsLayoutPubCriteria cmsLayoutPubCriteria = new CmsLayoutPubCriteria();
        Criteria criteria = cmsLayoutPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutId())) {
            criteria.andLayoutIdEqualTo(dto.getLayoutId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutTypeId())) {
            criteria.andLayoutTypeIdEqualTo(dto.getLayoutTypeId());
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
        
        cmsLayoutPubCriteria.setOrderByClause("SHOW_ORDER ASC,CREATE_TIME ASC");
        cmsLayoutPubCriteria.setLimitClauseCount(dto.getPageSize());
        cmsLayoutPubCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsLayoutPubCriteria,false,new PaginationCallback<CmsLayoutPub, CmsLayoutPubRespDTO>(){

            @Override
            public List<CmsLayoutPub> queryDB(BaseCriteria criteria) {
                return cmsLayoutPubMapper.selectByExample((CmsLayoutPubCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsLayoutPubMapper.countByExample((CmsLayoutPubCriteria)criteria);
            }

            @Override
            public CmsLayoutPubRespDTO warpReturnObject(CmsLayoutPub bo) {
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
    private CmsLayoutPubRespDTO conversionObject(CmsLayoutPub bo){
        CmsLayoutPubRespDTO dto = new CmsLayoutPubRespDTO();
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
