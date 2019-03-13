package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsTemplateLayoutItemMapper;
import com.zengshi.ecp.cms.dao.model.CmsTemplateLayoutItem;
import com.zengshi.ecp.cms.dao.model.CmsTemplateLayoutItemCriteria;
import com.zengshi.ecp.cms.dao.model.CmsTemplateLayoutItemCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutItemSV;
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
@Service("cmsTemplateLayoutItemSV")
public class CmsTemplateLayoutItemSVImpl extends GeneralSQLSVImpl implements ICmsTemplateLayoutItemSV {

    @Resource(name = "SEQ_CMS_TEMPLATE_LAYOUT_ITEM")
    private PaasSequence seqCmsTemplateLayoutItem;
    @Resource
    private CmsTemplateLayoutItemMapper cmsTemplateLayoutItemMapper;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutItemSV#saveCmsTemplateLayoutItem(com.zengshi.ecp.cms.dao.model.CmsTemplateLayoutItem)
     */
    @Override
    public CmsTemplateLayoutItemRespDTO addCmsTemplateLayoutItem(CmsTemplateLayoutItemReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsTemplateLayoutItem bo = new CmsTemplateLayoutItem();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsTemplateLayoutItem.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsTemplateLayoutItemMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsTemplateLayoutItemRespDTO respDTO = new CmsTemplateLayoutItemRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutItemSV#updateCmsTemplateLayoutItem(com.zengshi.ecp.cms.dao.model.CmsTemplateLayoutItem)
     */
    @Override
    public CmsTemplateLayoutItemRespDTO updateCmsTemplateLayoutItem(CmsTemplateLayoutItemReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsTemplateLayoutItem bo = new CmsTemplateLayoutItem();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsTemplateLayoutItem(bo);
    }
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutItemSV#updateTemplateLayoutItemSensitive(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO)
     */
    @Override
    public CmsTemplateLayoutItemRespDTO updateTemplateLayoutItemSensitive(
            CmsTemplateLayoutItemReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsTemplateLayoutItem bo = new CmsTemplateLayoutItem();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsTemplateLayoutItemSensitive(bo);
    }
    /**
     * 
     * updateCmsTemplateLayoutItemSensitive:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author IME 
     * @param bo
     * @return 
     * @since JDK 1.6
     */
    private CmsTemplateLayoutItemRespDTO updateCmsTemplateLayoutItemSensitive(
            CmsTemplateLayoutItem bo) {
        cmsTemplateLayoutItemMapper.updateByPrimaryKey(bo);
        CmsTemplateLayoutItemRespDTO respDTO = new CmsTemplateLayoutItemRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutItemSV#updateTemplateLayoutItemBatch(java.util.List)
     */
    @Override
    public void updateTemplateLayoutItemBatch(List<CmsTemplateLayoutItemReqDTO> list)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            CmsTemplateLayoutItemReqDTO dto = list.get(i);
            if(dto != null && StringUtil.isNotBlank(dto.getStatus())){
                
                //1 更新布局表，更新状态及排序。
                CmsTemplateLayoutItem bo = new CmsTemplateLayoutItem();
                if(dto != null){
                    ObjectCopyUtil.copyObjValue(dto, bo, null, false);
                }
                bo.setUpdateStaff(dto.getStaff().getId());
                bo.setUpdateTime(DateUtil.getSysDate());
                this.updateCmsTemplateLayoutItem(bo);
            }
        }
    }
    /** 
     * updateCmsTemplateLayoutItem:(更新楼层的原子方法). <br/> 
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
    public CmsTemplateLayoutItemRespDTO updateCmsTemplateLayoutItem(CmsTemplateLayoutItem bo) throws BusinessException {
        cmsTemplateLayoutItemMapper.updateByPrimaryKeySelective(bo);
        CmsTemplateLayoutItemRespDTO respDTO = new CmsTemplateLayoutItemRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutItemSV#deleteCmsTemplateLayoutItem(java.lang.Long)
     */
    @Override
    public void deleteCmsTemplateLayoutItem(String id) throws BusinessException {
        CmsTemplateLayoutItem bo = new CmsTemplateLayoutItem();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsTemplateLayoutItem(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutItemSV#deleteCmsTemplateLayoutItemBatch(java.util.List)
     */
    @Override
    public void deleteCmsTemplateLayoutItemBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsTemplateLayoutItem(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutItemSV#changeStatusCmsTemplateLayoutItem(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsTemplateLayoutItem(String id, String status) throws BusinessException {
        CmsTemplateLayoutItem bo = new CmsTemplateLayoutItem();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsTemplateLayoutItem(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutItemSV#changeStatusCmsTemplateLayoutItemBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsTemplateLayoutItemBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsTemplateLayoutItem(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutItemSV#queryCmsTemplateLayoutItem(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO)
     */
    @Override
    public CmsTemplateLayoutItemRespDTO queryCmsTemplateLayoutItem(CmsTemplateLayoutItemReqDTO dto) throws BusinessException {
        CmsTemplateLayoutItemRespDTO cmsTemplateLayoutItemRespDTO = new CmsTemplateLayoutItemRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsTemplateLayoutItem bo = cmsTemplateLayoutItemMapper.selectByPrimaryKey(dto.getId());
            cmsTemplateLayoutItemRespDTO = conversionObject(bo);
        }
        
        return cmsTemplateLayoutItemRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutItemSV#queryCmsTemplateLayoutItemList(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO)
     */
    @Override
    public List<CmsTemplateLayoutItemRespDTO> queryCmsTemplateLayoutItemList(CmsTemplateLayoutItemReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsTemplateLayoutItemCriteria cmsTemplateLayoutItemCriteria = new CmsTemplateLayoutItemCriteria();
        Criteria criteria = cmsTemplateLayoutItemCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutId())) {
            criteria.andLayoutIdEqualTo(dto.getLayoutId());
        }
        if (StringUtil.isNotEmpty(dto.getModularId())) {
            criteria.andModularIdEqualTo(dto.getModularId());
        }
        if (StringUtil.isNotEmpty(dto.getTemplateId())) {
            criteria.andTemplateIdEqualTo(dto.getTemplateId());
        }
        if (StringUtil.isNotEmpty(dto.getItemSize())) {
            criteria.andItemSizeEqualTo(dto.getItemSize());
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
        cmsTemplateLayoutItemCriteria.setOrderByClause("ITEM_NO ASC,CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsTemplateLayoutItemRespDTO> cmsTemplateLayoutItemRespDTOList =  new ArrayList<CmsTemplateLayoutItemRespDTO>();
        List<CmsTemplateLayoutItem> cmsTemplateLayoutItemList = cmsTemplateLayoutItemMapper.selectByExample(cmsTemplateLayoutItemCriteria);
        if(CollectionUtils.isNotEmpty(cmsTemplateLayoutItemList)){
            for(CmsTemplateLayoutItem bo :cmsTemplateLayoutItemList){
                CmsTemplateLayoutItemRespDTO cmsTemplateLayoutItemRespDTO = conversionObject(bo);
                cmsTemplateLayoutItemRespDTOList.add(cmsTemplateLayoutItemRespDTO);
            }
        }
        
        return cmsTemplateLayoutItemRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateLayoutItemSV#queryCmsTemplateLayoutItemPage(com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLayoutItemReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsTemplateLayoutItemRespDTO> queryCmsTemplateLayoutItemPage(CmsTemplateLayoutItemReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsTemplateLayoutItemCriteria cmsTemplateLayoutItemCriteria = new CmsTemplateLayoutItemCriteria();
        Criteria criteria = cmsTemplateLayoutItemCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getLayoutId())) {
            criteria.andLayoutIdEqualTo(dto.getLayoutId());
        }
        if (StringUtil.isNotEmpty(dto.getModularId())) {
            criteria.andModularIdEqualTo(dto.getModularId());
        }
        if (StringUtil.isNotEmpty(dto.getTemplateId())) {
            criteria.andTemplateIdEqualTo(dto.getTemplateId());
        }
        if (StringUtil.isNotEmpty(dto.getItemSize())) {
            criteria.andItemSizeEqualTo(dto.getItemSize());
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
        cmsTemplateLayoutItemCriteria.setOrderByClause("ITEM_NO ASC,CREATE_TIME ASC");
        cmsTemplateLayoutItemCriteria.setLimitClauseCount(dto.getPageSize());
        cmsTemplateLayoutItemCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsTemplateLayoutItemCriteria,false,new PaginationCallback<CmsTemplateLayoutItem, CmsTemplateLayoutItemRespDTO>(){

            @Override
            public List<CmsTemplateLayoutItem> queryDB(BaseCriteria criteria) {
                return cmsTemplateLayoutItemMapper.selectByExample((CmsTemplateLayoutItemCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsTemplateLayoutItemMapper.countByExample((CmsTemplateLayoutItemCriteria)criteria);
            }

            @Override
            public CmsTemplateLayoutItemRespDTO warpReturnObject(CmsTemplateLayoutItem bo) {
                return conversionObject(bo);
            }
        
        });

    }
    
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPreSV#deleteCmsLayoutItemPreByExample(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO)
     */
    @Override
    public int deleteCmsTemplateLayoutItemByExample(CmsTemplateLayoutItemReqDTO dto) throws BusinessException {
        /*1.组装删除bo*/
        CmsTemplateLayoutItem bo = new CmsTemplateLayoutItem();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /* 2.查询条件 */
        CmsTemplateLayoutItemCriteria cmsLayoutItemPreCriteria = new CmsTemplateLayoutItemCriteria();
        Criteria criteria = cmsLayoutItemPreCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getTemplateId())) {
            criteria.andTemplateIdEqualTo(dto.getTemplateId());
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
        if (StringUtil.isNotEmpty(dto.getModularId())) {
            criteria.andModularIdEqualTo(dto.getModularId());
        }
        if (StringUtil.isNotBlank(dto.getItemSize())) {
            criteria.andItemSizeEqualTo(dto.getItemSize());
        }
        if (StringUtil.isNotEmpty(dto.getItemNo())) {
            criteria.andItemNoEqualTo(dto.getItemNo());
        }
        return cmsTemplateLayoutItemMapper.updateByExampleSelective(bo, cmsLayoutItemPreCriteria);
    }
    
    /** 
     * updateTemplateLayoutItemByExample:(根据条件更新结果). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param conditionDTO  查询条件DTO
     * @param resultDTO  结果DTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    @Override
    public int updateTemplateLayoutItemByExample(CmsTemplateLayoutItemReqDTO conditionDTO,CmsTemplateLayoutItemReqDTO resultDTO) throws BusinessException {
        /*1.组装删除bo*/
        CmsTemplateLayoutItem bo = new CmsTemplateLayoutItem();
        bo.setStatus(resultDTO.getStatus());
        bo.setUpdateStaff(conditionDTO.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /* 2.查询条件 */
        CmsTemplateLayoutItemCriteria cmsTemplateLayoutItemCriteria = new CmsTemplateLayoutItemCriteria();
        Criteria criteria = cmsTemplateLayoutItemCriteria.createCriteria();
        if (StringUtil.isNotEmpty(conditionDTO.getLayoutId())) {
            criteria.andLayoutIdEqualTo(conditionDTO.getLayoutId());
        }
        if (StringUtil.isNotEmpty(conditionDTO.getModularId())) {
            criteria.andModularIdEqualTo(conditionDTO.getModularId());
        }
        if (StringUtil.isNotEmpty(conditionDTO.getTemplateId())) {
            criteria.andTemplateIdEqualTo(conditionDTO.getTemplateId());
        }
        if (StringUtil.isNotEmpty(conditionDTO.getId())) {
            criteria.andIdEqualTo(conditionDTO.getId());
        }
        /*状态查询 begin*/
        if (conditionDTO.getStatusSet() == null) {
            conditionDTO.setStatusSet(new HashSet<String>());
        }
        if (StringUtil.isNotBlank(conditionDTO.getStatus())) {
            conditionDTO.getStatusSet().add(conditionDTO.getStatus());
        }
        if (CollectionUtils.isNotEmpty(conditionDTO.getStatusSet())) {
            criteria.andStatusIn(new ArrayList<String>(conditionDTO.getStatusSet()));
        }
        /*状态查询 end*/
        
        return cmsTemplateLayoutItemMapper.updateByExampleSelective(bo, cmsTemplateLayoutItemCriteria);
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
    private CmsTemplateLayoutItemRespDTO conversionObject(CmsTemplateLayoutItem bo){
        CmsTemplateLayoutItemRespDTO dto = new CmsTemplateLayoutItemRespDTO();
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
