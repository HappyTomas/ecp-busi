package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsRecommendMapper;
import com.zengshi.ecp.cms.dao.model.CmsRecommend;
import com.zengshi.ecp.cms.dao.model.CmsRecommendCriteria;
import com.zengshi.ecp.cms.dao.model.CmsRecommendCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsRecommendReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsRecommendRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsRecommendSV;
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
 * @author huangxm9
 * @version
 * @since JDK 1.6
 */
@Service("cmsRecommendSV")
public class CmsRecommendSVImpl extends GeneralSQLSVImpl implements ICmsRecommendSV {

    @Resource(name = "SEQ_CMS_RECOMMEND")
    private PaasSequence seqCmsRecommend;
    
    @Resource
    private CmsRecommendMapper cmsRecommendMapper;
    
   /**
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsRecommendSV#addCmsExpertRecommend(com.zengshi.ecp.cms.dubbo.dto.CmsRecommendReqDTO) 
     */
    @Override
    public CmsRecommendRespDTO addCmsRecommend(CmsRecommendReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsRecommend bo = new CmsRecommend();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsRecommend.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsRecommendMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsRecommendRespDTO respDTO = new CmsRecommendRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsRecommendSV#updateCmsExpertRecommend(com.zengshi.ecp.cms.dao.model.CmsExpertRecommend)
     */
    @Override
    public CmsRecommendRespDTO updateCmsRecommend(CmsRecommendReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsRecommend bo = new CmsRecommend();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新推荐的原子方法*/
        return this.updateCmsRecommend(bo);
    }
    
    /** 
     * updateCmsExpertRecommend:(更新推荐的原子方法). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param bo
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public CmsRecommendRespDTO updateCmsRecommend(CmsRecommend bo) throws BusinessException {
        cmsRecommendMapper.updateByPrimaryKeySelective(bo);
        /*3.将结果返回*/
        CmsRecommendRespDTO respDTO = new CmsRecommendRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsRecommendSV#updateCmsExpertRecommend(com.zengshi.ecp.cms.dao.model.CmsExpertRecommend)
     */
    @Override
    public CmsRecommendRespDTO updateCmsRecommendByExample(CmsRecommendReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsRecommend bo = new CmsRecommend();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.组装查询条件*/
        CmsRecommendCriteria cmsRecommendCriteria = new CmsRecommendCriteria();
        Criteria criteria = cmsRecommendCriteria.createCriteria();
        if (StringUtil.isNotEmpty(bo.getRecommendGdsId())) {
            criteria.andRecommendGdsIdEqualTo(bo.getRecommendGdsId());
        }
        cmsRecommendMapper.updateByExampleSelective(bo, cmsRecommendCriteria);
        
        /*3.将结果返回*/
        CmsRecommendRespDTO respDTO = new CmsRecommendRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsRecommendSV#deleteCmsExpertRecommend(java.lang.Long)
     */
    @Override
    public void deleteCmsRecommend(String id) throws BusinessException {
        CmsRecommend bo = new CmsRecommend();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsRecommend(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsRecommendSV#deleteCmsExpertRecommendBatch(java.util.List)
     */
    @Override
    public void deleteCmsRecommendBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsRecommend(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsRecommendSV#changeStatusCmsExpertRecommend(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsRecommend(String id, String status) throws BusinessException {
        CmsRecommend bo = new CmsRecommend();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsRecommend(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsRecommendSV#changeStatusCmsExpertRecommendBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsRecommendBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsRecommend(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsRecommendSV#queryCmsExpertRecommend(com.zengshi.ecp.cms.dubbo.dto.CmsRecommendReqDTO)
     */
    @Override
    public CmsRecommendRespDTO queryCmsRecommend(CmsRecommendReqDTO dto) throws BusinessException {
        CmsRecommendRespDTO cmsRecommendRespDTO = new CmsRecommendRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsRecommend bo = cmsRecommendMapper.selectByPrimaryKey(dto.getId());
            cmsRecommendRespDTO = conversionObject(bo);
        }
        
        return cmsRecommendRespDTO;
    }
    
    /**
     * TODO 查询推荐列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsRecommendSV#queryCmsExpertRecommendList(com.zengshi.ecp.cms.dubbo.dto.CmsRecommendReqDTO)
     */
    @Override
    public List<CmsRecommendRespDTO> queryCmsRecommendList(CmsRecommendReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsRecommendCriteria cmsRecommendCriteria = new CmsRecommendCriteria();
        Criteria criteria = cmsRecommendCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getAuthorName())) {
            criteria.andAuthorNameLike("%" + dto.getAuthorName() + "%");
        }
        if (StringUtil.isNotBlank(dto.getOtherProduction())) {
            criteria.andOtherProductionLike("%" + dto.getOtherProduction() + "%");
        }
        if (StringUtil.isNotBlank(dto.getRecommendProduction())) {
            criteria.andRecommendProductionLike("%" + dto.getRecommendProduction() + "%");
        }
        if (StringUtil.isNotBlank(dto.getOtherLike())) {
            criteria.andOtherLikeLike("%" + dto.getOtherLike() + "%");
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if(dto.getRecommendType() !=null){
        	criteria.andRecommendTypeEqualTo(dto.getRecommendType());
        }
       
        cmsRecommendCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsRecommendRespDTO> cmsRecommendRespDTOList =  new ArrayList<CmsRecommendRespDTO>();
        List<CmsRecommend> cmsRecommendList = cmsRecommendMapper.selectByExample(cmsRecommendCriteria);
        if(CollectionUtils.isNotEmpty(cmsRecommendList)){
            for(CmsRecommend bo:cmsRecommendList){
                CmsRecommendRespDTO cmsExpertRecommendRespDTO = conversionObject(bo);
                cmsRecommendRespDTOList.add(cmsExpertRecommendRespDTO);
            }
        }
        return cmsRecommendRespDTOList;
    }


    /** 
     * TODO 查询推荐，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsRecommendSV#queryCmsExpertRecommendPage(com.zengshi.ecp.cms.dubbo.dto.CmsRecommendReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsRecommendRespDTO> queryCmsRecommendPage(CmsRecommendReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索推荐 */
        CmsRecommendCriteria cmsRecommendCriteria = new CmsRecommendCriteria();
        Criteria criteria = cmsRecommendCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getAuthorName())) {
            criteria.andAuthorNameLike("%" + dto.getAuthorName() + "%");
        }
        if (StringUtil.isNotBlank(dto.getOtherProduction())) {
            criteria.andOtherProductionLike("%" + dto.getOtherProduction() + "%");
        }
        if (StringUtil.isNotBlank(dto.getRecommendProduction())) {
            criteria.andRecommendProductionLike("%" + dto.getRecommendProduction() + "%");
        }
        if (StringUtil.isNotBlank(dto.getOtherLike())) {
            criteria.andOtherLikeLike("%" + dto.getOtherLike() + "%");
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if(StringUtil.isNotEmpty(dto.getRecommendType())){
        	criteria.andRecommendTypeEqualTo(dto.getRecommendType());
        }
        
        cmsRecommendCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        cmsRecommendCriteria.setLimitClauseCount(dto.getPageSize());
        cmsRecommendCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsRecommendCriteria,false,new PaginationCallback<CmsRecommend, CmsRecommendRespDTO>(){

            @Override
            public List<CmsRecommend> queryDB(BaseCriteria criteria) {
                return cmsRecommendMapper.selectByExample((CmsRecommendCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsRecommendMapper.countByExample((CmsRecommendCriteria)criteria);
            }

            @Override
            public CmsRecommendRespDTO warpReturnObject(CmsRecommend bo) {
                return  conversionObject(bo);
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
     * @author huangxm9 
     * @param bo 
     * @return 
     * @since JDK 1.6 
     */ 
    private CmsRecommendRespDTO conversionObject(CmsRecommend bo){
        CmsRecommendRespDTO dto = new CmsRecommendRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
      
        //1.遍历将编码转中文 
        String recommendTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_RECOMMEND_TYPE, dto.getRecommendType());
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setRecommendTypeZH(recommendTypeZH);
        dto.setStatusZH(statusZH);
        
        return dto;
    }
}
