package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsLayoutAttrPubMapper;
import com.zengshi.ecp.cms.dao.model.CmsLayoutAttrPub;
import com.zengshi.ecp.cms.dao.model.CmsLayoutAttrPubCriteria;
import com.zengshi.ecp.cms.dao.model.CmsLayoutAttrPubCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPubRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPubSV;
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
@Service("cmsLayoutAttrPubSV")
public class CmsLayoutAttrPubSVImpl extends GeneralSQLSVImpl implements ICmsLayoutAttrPubSV {

    @Resource(name = "SEQ_CMS_LAYOUT_ATTR_PUB")
    private PaasSequence seqCmsLayoutAttrPub;
    @Resource
    private CmsLayoutAttrPubMapper cmsLayoutAttrPubMapper;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPubSV#saveCmsLayoutAttrPub(com.zengshi.ecp.cms.dao.model.CmsLayoutAttrPub)
     */
    @Override
    public CmsLayoutAttrPubRespDTO addCmsLayoutAttrPub(CmsLayoutAttrPubReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsLayoutAttrPub bo = new CmsLayoutAttrPub();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsLayoutAttrPub.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsLayoutAttrPubMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsLayoutAttrPubRespDTO respDTO = new CmsLayoutAttrPubRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPubSV#updateCmsLayoutAttrPub(com.zengshi.ecp.cms.dao.model.CmsLayoutAttrPub)
     */
    @Override
    public CmsLayoutAttrPubRespDTO updateCmsLayoutAttrPub(CmsLayoutAttrPubReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsLayoutAttrPub bo = new CmsLayoutAttrPub();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsLayoutAttrPub(bo);
    }
    
    /** 
     * updateCmsLayoutAttrPub:(更新楼层的原子方法). <br/> 
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
    public CmsLayoutAttrPubRespDTO updateCmsLayoutAttrPub(CmsLayoutAttrPub bo) throws BusinessException {
        cmsLayoutAttrPubMapper.updateByPrimaryKeySelective(bo);
        CmsLayoutAttrPubRespDTO respDTO = new CmsLayoutAttrPubRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPubSV#deleteCmsLayoutAttrPub(java.lang.Long)
     */
    @Override
    public void deleteCmsLayoutAttrPub(String id) throws BusinessException {
        CmsLayoutAttrPub bo = new CmsLayoutAttrPub();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsLayoutAttrPub(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPubSV#deleteCmsLayoutAttrPubBatch(java.util.List)
     */
    @Override
    public void deleteCmsLayoutAttrPubBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsLayoutAttrPub(id);
            }
        }
    }

    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPubSV#deleteCmsLayoutAttrPubByExample(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPubReqDTO)
     */
    @Override
    public int deleteCmsLayoutAttrPubByExample(CmsLayoutAttrPubReqDTO dto) throws BusinessException {
        /*1.组装删除bo*/
        CmsLayoutAttrPub bo = new CmsLayoutAttrPub();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /* 2.查询条件 */
        CmsLayoutAttrPubCriteria cmsLayoutAttrPubCriteria = new CmsLayoutAttrPubCriteria();
        Criteria criteria = cmsLayoutAttrPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutId())) {
            criteria.andLayoutIdEqualTo(dto.getLayoutId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutAttrId())) {
            criteria.andLayoutAttrIdEqualTo(dto.getLayoutAttrId());
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
        return cmsLayoutAttrPubMapper.updateByExampleSelective(bo, cmsLayoutAttrPubCriteria);
    }
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPubSV#changeStatusCmsLayoutAttrPub(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsLayoutAttrPub(String id, String status) throws BusinessException {
        CmsLayoutAttrPub bo = new CmsLayoutAttrPub();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsLayoutAttrPub(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPubSV#changeStatusCmsLayoutAttrPubBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsLayoutAttrPubBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsLayoutAttrPub(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPubSV#queryCmsLayoutAttrPub(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPubReqDTO)
     */
    @Override
    public CmsLayoutAttrPubRespDTO queryCmsLayoutAttrPub(CmsLayoutAttrPubReqDTO dto) throws BusinessException {
        CmsLayoutAttrPubRespDTO cmsLayoutAttrPubRespDTO = new CmsLayoutAttrPubRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsLayoutAttrPub bo = cmsLayoutAttrPubMapper.selectByPrimaryKey(dto.getId());
            cmsLayoutAttrPubRespDTO = conversionObject(bo);
        }
        
        return cmsLayoutAttrPubRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPubSV#queryCmsLayoutAttrPubList(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPubReqDTO)
     */
    @Override
    public List<CmsLayoutAttrPubRespDTO> queryCmsLayoutAttrPubList(CmsLayoutAttrPubReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsLayoutAttrPubCriteria cmsLayoutAttrPubCriteria = new CmsLayoutAttrPubCriteria();
        Criteria criteria = cmsLayoutAttrPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutId())) {
            criteria.andLayoutIdEqualTo(dto.getLayoutId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutAttrId())) {
            criteria.andLayoutAttrIdEqualTo(dto.getLayoutAttrId());
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
        
        cmsLayoutAttrPubCriteria.setOrderByClause("CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsLayoutAttrPubRespDTO> cmsLayoutAttrPubRespDTOList =  new ArrayList<CmsLayoutAttrPubRespDTO>();
        List<CmsLayoutAttrPub> cmsLayoutAttrPubList = cmsLayoutAttrPubMapper.selectByExample(cmsLayoutAttrPubCriteria);
        if(CollectionUtils.isNotEmpty(cmsLayoutAttrPubList)){
            for(CmsLayoutAttrPub bo :cmsLayoutAttrPubList){
                CmsLayoutAttrPubRespDTO cmsLayoutAttrPubRespDTO = conversionObject(bo);
                cmsLayoutAttrPubRespDTOList.add(cmsLayoutAttrPubRespDTO);
            }
        }
        
        return cmsLayoutAttrPubRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPubSV#queryCmsLayoutAttrPubPage(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPubReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsLayoutAttrPubRespDTO> queryCmsLayoutAttrPubPage(CmsLayoutAttrPubReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsLayoutAttrPubCriteria cmsLayoutAttrPubCriteria = new CmsLayoutAttrPubCriteria();
        Criteria criteria = cmsLayoutAttrPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutId())) {
            criteria.andLayoutIdEqualTo(dto.getLayoutId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutAttrId())) {
            criteria.andLayoutAttrIdEqualTo(dto.getLayoutAttrId());
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
        
        cmsLayoutAttrPubCriteria.setOrderByClause("CREATE_TIME ASC");
        cmsLayoutAttrPubCriteria.setLimitClauseCount(dto.getPageSize());
        cmsLayoutAttrPubCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsLayoutAttrPubCriteria,false,new PaginationCallback<CmsLayoutAttrPub, CmsLayoutAttrPubRespDTO>(){

            @Override
            public List<CmsLayoutAttrPub> queryDB(BaseCriteria criteria) {
                return cmsLayoutAttrPubMapper.selectByExample((CmsLayoutAttrPubCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsLayoutAttrPubMapper.countByExample((CmsLayoutAttrPubCriteria)criteria);
            }

            @Override
            public CmsLayoutAttrPubRespDTO warpReturnObject(CmsLayoutAttrPub bo) {
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
    private CmsLayoutAttrPubRespDTO conversionObject(CmsLayoutAttrPub bo){
        CmsLayoutAttrPubRespDTO dto = new CmsLayoutAttrPubRespDTO();
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
