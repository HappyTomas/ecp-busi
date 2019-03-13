package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsPageAttrPreMapper;
import com.zengshi.ecp.cms.dao.model.CmsPageAttrPre;
import com.zengshi.ecp.cms.dao.model.CmsPageAttrPreCriteria;
import com.zengshi.ecp.cms.dao.model.CmsPageAttrPreCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPreRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPreSV;
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
@Service("cmsPageAttrPreSV")
public class CmsPageAttrPreSVImpl extends GeneralSQLSVImpl implements ICmsPageAttrPreSV {

    @Resource(name = "SEQ_CMS_PAGE_ATTR_PRE")
    private PaasSequence seqCmsPageAttrPre;
    @Resource
    private CmsPageAttrPreMapper cmsPageAttrPreMapper;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPreSV#saveCmsPageAttrPre(com.zengshi.ecp.cms.dao.model.CmsPageAttrPre)
     */
    @Override
    public CmsPageAttrPreRespDTO addCmsPageAttrPre(CmsPageAttrPreReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPageAttrPre bo = new CmsPageAttrPre();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsPageAttrPre.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsPageAttrPreMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsPageAttrPreRespDTO respDTO = new CmsPageAttrPreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPreSV#updateCmsPageAttrPre(com.zengshi.ecp.cms.dao.model.CmsPageAttrPre)
     */
    @Override
    public CmsPageAttrPreRespDTO updateCmsPageAttrPre(CmsPageAttrPreReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPageAttrPre bo = new CmsPageAttrPre();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsPageAttrPre(bo);
    }
    
    /** 
     * updateCmsPageAttrPre:(更新楼层的原子方法). <br/> 
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
    public CmsPageAttrPreRespDTO updateCmsPageAttrPre(CmsPageAttrPre bo) throws BusinessException {
        cmsPageAttrPreMapper.updateByPrimaryKeySelective(bo);
        CmsPageAttrPreRespDTO respDTO = new CmsPageAttrPreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPreSV#deleteCmsPageAttrPre(java.lang.Long)
     */
    @Override
    public void deleteCmsPageAttrPre(String id) throws BusinessException {
        CmsPageAttrPre bo = new CmsPageAttrPre();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsPageAttrPre(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPreSV#deleteCmsPageAttrPreBatch(java.util.List)
     */
    @Override
    public void deleteCmsPageAttrPreBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsPageAttrPre(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPreSV#changeStatusCmsPageAttrPre(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPageAttrPre(String id, String status) throws BusinessException {
        CmsPageAttrPre bo = new CmsPageAttrPre();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsPageAttrPre(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPreSV#changeStatusCmsPageAttrPreBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPageAttrPreBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsPageAttrPre(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPreSV#queryCmsPageAttrPre(com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPreReqDTO)
     */
    @Override
    public CmsPageAttrPreRespDTO queryCmsPageAttrPre(CmsPageAttrPreReqDTO dto) throws BusinessException {
        CmsPageAttrPreRespDTO cmsAttrPreRespDTO = new CmsPageAttrPreRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsPageAttrPre bo = cmsPageAttrPreMapper.selectByPrimaryKey(dto.getId());
            cmsAttrPreRespDTO = conversionObject(bo);
        }
        
        return cmsAttrPreRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPreSV#queryCmsPageAttrPreList(com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPreReqDTO)
     */
    @Override
    public List<CmsPageAttrPreRespDTO> queryCmsPageAttrPreList(CmsPageAttrPreReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsPageAttrPreCriteria cmsAttrPreCriteria = new CmsPageAttrPreCriteria();
        Criteria criteria = cmsAttrPreCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
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
        cmsAttrPreCriteria.setOrderByClause("CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsPageAttrPreRespDTO> cmsAttrPreRespDTOList =  new ArrayList<CmsPageAttrPreRespDTO>();
        List<CmsPageAttrPre> cmsAttrPreList = cmsPageAttrPreMapper.selectByExample(cmsAttrPreCriteria);
        if(CollectionUtils.isNotEmpty(cmsAttrPreList)){
            for(CmsPageAttrPre bo :cmsAttrPreList){
                CmsPageAttrPreRespDTO cmsAttrPreRespDTO = conversionObject(bo);
                cmsAttrPreRespDTOList.add(cmsAttrPreRespDTO);
            }
        }
        
        return cmsAttrPreRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPreSV#queryCmsPageAttrPrePage(com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPreReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsPageAttrPreRespDTO> queryCmsPageAttrPrePage(CmsPageAttrPreReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsPageAttrPreCriteria cmsAttrPreCriteria = new CmsPageAttrPreCriteria();
        Criteria criteria = cmsAttrPreCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
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
        cmsAttrPreCriteria.setOrderByClause("CREATE_TIME ASC");
        cmsAttrPreCriteria.setLimitClauseCount(dto.getPageSize());
        cmsAttrPreCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsAttrPreCriteria,false,new PaginationCallback<CmsPageAttrPre, CmsPageAttrPreRespDTO>(){

            @Override
            public List<CmsPageAttrPre> queryDB(BaseCriteria criteria) {
                return cmsPageAttrPreMapper.selectByExample((CmsPageAttrPreCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsPageAttrPreMapper.countByExample((CmsPageAttrPreCriteria)criteria);
            }

            @Override
            public CmsPageAttrPreRespDTO warpReturnObject(CmsPageAttrPre bo) {
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
    private CmsPageAttrPreRespDTO conversionObject(CmsPageAttrPre bo){
        CmsPageAttrPreRespDTO dto = new CmsPageAttrPreRespDTO();
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
    public int deleteCmsPageAttrPreByExample(CmsPageAttrPreReqDTO dto) throws BusinessException {
        /*1.组装删除bo*/
        CmsPageAttrPre bo = new CmsPageAttrPre();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /* 2.查询条件 */
        CmsPageAttrPreCriteria cmsPageAttrPreCriteria = new CmsPageAttrPreCriteria();
        Criteria criteria = cmsPageAttrPreCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
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
        return cmsPageAttrPreMapper.updateByExampleSelective(bo, cmsPageAttrPreCriteria);
    }
    

}
