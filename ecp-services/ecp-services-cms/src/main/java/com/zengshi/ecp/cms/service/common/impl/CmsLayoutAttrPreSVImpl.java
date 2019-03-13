package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsLayoutAttrPreMapper;
import com.zengshi.ecp.cms.dao.model.CmsLayoutAttrPre;
import com.zengshi.ecp.cms.dao.model.CmsLayoutAttrPreCriteria;
import com.zengshi.ecp.cms.dao.model.CmsLayoutAttrPreCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPreRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPreSV;
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
@Service("cmsLayoutAttrPreSV")
public class CmsLayoutAttrPreSVImpl extends GeneralSQLSVImpl implements ICmsLayoutAttrPreSV {

    @Resource(name = "SEQ_CMS_LAYOUT_ATTR_PRE")
    private PaasSequence seqCmsLayoutAttrPre;
    @Resource
    private CmsLayoutAttrPreMapper cmsLayoutAttrPreMapper;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPreSV#saveCmsLayoutAttrPre(com.zengshi.ecp.cms.dao.model.CmsLayoutAttrPre)
     */
    @Override
    public CmsLayoutAttrPreRespDTO addCmsLayoutAttrPre(CmsLayoutAttrPreReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsLayoutAttrPre bo = new CmsLayoutAttrPre();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsLayoutAttrPre.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsLayoutAttrPreMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsLayoutAttrPreRespDTO respDTO = new CmsLayoutAttrPreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPreSV#updateCmsLayoutAttrPre(com.zengshi.ecp.cms.dao.model.CmsLayoutAttrPre)
     */
    @Override
    public CmsLayoutAttrPreRespDTO updateCmsLayoutAttrPre(CmsLayoutAttrPreReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsLayoutAttrPre bo = new CmsLayoutAttrPre();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsLayoutAttrPre(bo);
    }
    
    /** 
     * updateCmsLayoutAttrPre:(更新楼层的原子方法). <br/> 
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
    public CmsLayoutAttrPreRespDTO updateCmsLayoutAttrPre(CmsLayoutAttrPre bo) throws BusinessException {
        cmsLayoutAttrPreMapper.updateByPrimaryKeySelective(bo);
        CmsLayoutAttrPreRespDTO respDTO = new CmsLayoutAttrPreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPreSV#deleteCmsLayoutAttrPre(java.lang.Long)
     */
    @Override
    public void deleteCmsLayoutAttrPre(String id) throws BusinessException {
        CmsLayoutAttrPre bo = new CmsLayoutAttrPre();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsLayoutAttrPre(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPreSV#deleteCmsLayoutAttrPreBatch(java.util.List)
     */
    @Override
    public void deleteCmsLayoutAttrPreBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsLayoutAttrPre(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPreSV#changeStatusCmsLayoutAttrPre(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsLayoutAttrPre(String id, String status) throws BusinessException {
        CmsLayoutAttrPre bo = new CmsLayoutAttrPre();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsLayoutAttrPre(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPreSV#changeStatusCmsLayoutAttrPreBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsLayoutAttrPreBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsLayoutAttrPre(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPreSV#queryCmsLayoutAttrPre(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPreReqDTO)
     */
    @Override
    public CmsLayoutAttrPreRespDTO queryCmsLayoutAttrPre(CmsLayoutAttrPreReqDTO dto) throws BusinessException {
        CmsLayoutAttrPreRespDTO cmsLayoutAttrPreRespDTO = new CmsLayoutAttrPreRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsLayoutAttrPre bo = cmsLayoutAttrPreMapper.selectByPrimaryKey(dto.getId());
            cmsLayoutAttrPreRespDTO = conversionObject(bo);
        }
        
        return cmsLayoutAttrPreRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPreSV#queryCmsLayoutAttrPreList(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPreReqDTO)
     */
    @Override
    public List<CmsLayoutAttrPreRespDTO> queryCmsLayoutAttrPreList(CmsLayoutAttrPreReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsLayoutAttrPreCriteria cmsLayoutAttrPreCriteria = new CmsLayoutAttrPreCriteria();
        Criteria criteria = cmsLayoutAttrPreCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutId())) {
            criteria.andLayoutIdEqualTo(dto.getLayoutId());
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
        cmsLayoutAttrPreCriteria.setOrderByClause("CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsLayoutAttrPreRespDTO> cmsLayoutAttrPreRespDTOList =  new ArrayList<CmsLayoutAttrPreRespDTO>();
        List<CmsLayoutAttrPre> cmsLayoutAttrPreList = cmsLayoutAttrPreMapper.selectByExample(cmsLayoutAttrPreCriteria);
        if(CollectionUtils.isNotEmpty(cmsLayoutAttrPreList)){
            for(CmsLayoutAttrPre bo :cmsLayoutAttrPreList){
                CmsLayoutAttrPreRespDTO cmsLayoutAttrPreRespDTO = conversionObject(bo);
                cmsLayoutAttrPreRespDTOList.add(cmsLayoutAttrPreRespDTO);
            }
        }
        
        return cmsLayoutAttrPreRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPreSV#queryCmsLayoutAttrPrePage(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPreReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsLayoutAttrPreRespDTO> queryCmsLayoutAttrPrePage(CmsLayoutAttrPreReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsLayoutAttrPreCriteria cmsLayoutAttrPreCriteria = new CmsLayoutAttrPreCriteria();
        Criteria criteria = cmsLayoutAttrPreCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutId())) {
            criteria.andLayoutIdEqualTo(dto.getLayoutId());
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
        cmsLayoutAttrPreCriteria.setOrderByClause("CREATE_TIME ASC");
        cmsLayoutAttrPreCriteria.setLimitClauseCount(dto.getPageSize());
        cmsLayoutAttrPreCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsLayoutAttrPreCriteria,false,new PaginationCallback<CmsLayoutAttrPre, CmsLayoutAttrPreRespDTO>(){

            @Override
            public List<CmsLayoutAttrPre> queryDB(BaseCriteria criteria) {
                return cmsLayoutAttrPreMapper.selectByExample((CmsLayoutAttrPreCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsLayoutAttrPreMapper.countByExample((CmsLayoutAttrPreCriteria)criteria);
            }

            @Override
            public CmsLayoutAttrPreRespDTO warpReturnObject(CmsLayoutAttrPre bo) {
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
    private CmsLayoutAttrPreRespDTO conversionObject(CmsLayoutAttrPre bo){
        CmsLayoutAttrPreRespDTO dto = new CmsLayoutAttrPreRespDTO();
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
    public void deleteCmsLayoutAttrPreByExample(CmsLayoutAttrPreReqDTO dto) throws BusinessException {
        /* 1.根据条件检索楼层 */
        CmsLayoutAttrPreCriteria cmsLayoutAttrPreCriteria = new CmsLayoutAttrPreCriteria();
        Criteria criteria = cmsLayoutAttrPreCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutId())) {
            criteria.andLayoutIdEqualTo(dto.getLayoutId());
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
        CmsLayoutAttrPre bo = new CmsLayoutAttrPre();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        
        cmsLayoutAttrPreMapper.updateByExampleSelective(bo, cmsLayoutAttrPreCriteria);
    }

}
