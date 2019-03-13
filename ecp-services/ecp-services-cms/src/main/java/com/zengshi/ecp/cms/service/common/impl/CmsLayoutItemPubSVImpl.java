package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsLayoutItemPubMapper;
import com.zengshi.ecp.cms.dao.model.CmsLayoutItemPub;
import com.zengshi.ecp.cms.dao.model.CmsLayoutItemPubCriteria;
import com.zengshi.ecp.cms.dao.model.CmsLayoutItemPubCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPubSV;
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
@Service("cmsLayoutItemPubSV")
public class CmsLayoutItemPubSVImpl extends GeneralSQLSVImpl implements ICmsLayoutItemPubSV {

    @Resource(name = "SEQ_CMS_LAYOUT_ITEM_PUB")
    private PaasSequence seqCmsLayoutItemPub;
    @Resource
    private CmsLayoutItemPubMapper cmsLayoutItemPubMapper;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPubSV#saveCmsLayoutItemPub(com.zengshi.ecp.cms.dao.model.CmsLayoutItemPub)
     */
    @Override
    public CmsLayoutItemPubRespDTO addCmsLayoutItemPub(CmsLayoutItemPubReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsLayoutItemPub bo = new CmsLayoutItemPub();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsLayoutItemPub.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsLayoutItemPubMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsLayoutItemPubRespDTO respDTO = new CmsLayoutItemPubRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPubSV#updateCmsLayoutItemPub(com.zengshi.ecp.cms.dao.model.CmsLayoutItemPub)
     */
    @Override
    public CmsLayoutItemPubRespDTO updateCmsLayoutItemPub(CmsLayoutItemPubReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsLayoutItemPub bo = new CmsLayoutItemPub();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsLayoutItemPub(bo);
    }
    
    /** 
     * updateCmsLayoutItemPub:(更新楼层的原子方法). <br/> 
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
    public CmsLayoutItemPubRespDTO updateCmsLayoutItemPub(CmsLayoutItemPub bo) throws BusinessException {
        cmsLayoutItemPubMapper.updateByPrimaryKeySelective(bo);
        CmsLayoutItemPubRespDTO respDTO = new CmsLayoutItemPubRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPubSV#deleteCmsLayoutItemPub(java.lang.Long)
     */
    @Override
    public void deleteCmsLayoutItemPub(String id) throws BusinessException {
        CmsLayoutItemPub bo = new CmsLayoutItemPub();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsLayoutItemPub(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPubSV#deleteCmsLayoutItemPubBatch(java.util.List)
     */
    @Override
    public void deleteCmsLayoutItemPubBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsLayoutItemPub(id);
            }
        }
    }
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPubSV#deleteCmsLayoutItemPubByExample(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubReqDTO)
     */
    @Override
    public int deleteCmsLayoutItemPubByExample(CmsLayoutItemPubReqDTO dto) throws BusinessException {
        /*1.组装删除bo*/
        CmsLayoutItemPub bo = new CmsLayoutItemPub();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /* 2.查询条件 */
        CmsLayoutItemPubCriteria cmsLayoutItemPubCriteria = new CmsLayoutItemPubCriteria();
        Criteria criteria = cmsLayoutItemPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutId())) {
            criteria.andLayoutIdEqualTo(dto.getLayoutId());
        }
        if (StringUtil.isNotEmpty(dto.getItemId())) {
            criteria.andItemIdEqualTo(dto.getItemId());
        }
        if (StringUtil.isNotEmpty(dto.getModularId())) {
            criteria.andModularIdEqualTo(dto.getModularId());
        }
        if (StringUtil.isNotBlank(dto.getItemSize())) {
            criteria.andItemSizeEqualTo(dto.getItemSize());
        }
        if (StringUtil.isNotEmpty(dto.getItemNo())) {
            criteria.andItemNoEqualTo(dto.getItemNo());
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
        
        return cmsLayoutItemPubMapper.updateByExampleSelective(bo, cmsLayoutItemPubCriteria);
    }
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPubSV#changeStatusCmsLayoutItemPub(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsLayoutItemPub(String id, String status) throws BusinessException {
        CmsLayoutItemPub bo = new CmsLayoutItemPub();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsLayoutItemPub(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPubSV#changeStatusCmsLayoutItemPubBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsLayoutItemPubBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsLayoutItemPub(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPubSV#queryCmsLayoutItemPub(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubReqDTO)
     */
    @Override
    public CmsLayoutItemPubRespDTO queryCmsLayoutItemPub(CmsLayoutItemPubReqDTO dto) throws BusinessException {
        CmsLayoutItemPubRespDTO cmsLayoutItemPubRespDTO = new CmsLayoutItemPubRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsLayoutItemPub bo = cmsLayoutItemPubMapper.selectByPrimaryKey(dto.getId());
            cmsLayoutItemPubRespDTO = conversionObject(bo);
        }
        
        return cmsLayoutItemPubRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPubSV#queryCmsLayoutItemPubList(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubReqDTO)
     */
    @Override
    public List<CmsLayoutItemPubRespDTO> queryCmsLayoutItemPubList(CmsLayoutItemPubReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsLayoutItemPubCriteria cmsLayoutItemPubCriteria = new CmsLayoutItemPubCriteria();
        Criteria criteria = cmsLayoutItemPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPageId())) {
            criteria.andPageIdEqualTo(dto.getPageId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutId())) {
            criteria.andLayoutIdEqualTo(dto.getLayoutId());
        }
        if (StringUtil.isNotEmpty(dto.getItemId())) {
            criteria.andItemIdEqualTo(dto.getItemId());
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
        
        cmsLayoutItemPubCriteria.setOrderByClause("ITEM_NO ASC,CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsLayoutItemPubRespDTO> cmsLayoutItemPubRespDTOList =  new ArrayList<CmsLayoutItemPubRespDTO>();
        List<CmsLayoutItemPub> cmsLayoutItemPubList = cmsLayoutItemPubMapper.selectByExample(cmsLayoutItemPubCriteria);
        if(CollectionUtils.isNotEmpty(cmsLayoutItemPubList)){
            for(CmsLayoutItemPub bo :cmsLayoutItemPubList){
                CmsLayoutItemPubRespDTO cmsLayoutItemPubRespDTO = conversionObject(bo);
                cmsLayoutItemPubRespDTOList.add(cmsLayoutItemPubRespDTO);
            }
        }
        
        return cmsLayoutItemPubRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPubSV#queryCmsLayoutItemPubPage(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPubReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsLayoutItemPubRespDTO> queryCmsLayoutItemPubPage(CmsLayoutItemPubReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsLayoutItemPubCriteria cmsLayoutItemPubCriteria = new CmsLayoutItemPubCriteria();
        Criteria criteria = cmsLayoutItemPubCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutId())) {
            criteria.andLayoutIdEqualTo(dto.getLayoutId());
        }
        if (StringUtil.isNotEmpty(dto.getItemId())) {
            criteria.andItemIdEqualTo(dto.getItemId());
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
        
        cmsLayoutItemPubCriteria.setOrderByClause("ITEM_NO ASC,CREATE_TIME ASC");
        cmsLayoutItemPubCriteria.setLimitClauseCount(dto.getPageSize());
        cmsLayoutItemPubCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsLayoutItemPubCriteria,false,new PaginationCallback<CmsLayoutItemPub, CmsLayoutItemPubRespDTO>(){

            @Override
            public List<CmsLayoutItemPub> queryDB(BaseCriteria criteria) {
                return cmsLayoutItemPubMapper.selectByExample((CmsLayoutItemPubCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsLayoutItemPubMapper.countByExample((CmsLayoutItemPubCriteria)criteria);
            }

            @Override
            public CmsLayoutItemPubRespDTO warpReturnObject(CmsLayoutItemPub bo) {
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
    private CmsLayoutItemPubRespDTO conversionObject(CmsLayoutItemPub bo){
        CmsLayoutItemPubRespDTO dto = new CmsLayoutItemPubRespDTO();
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
