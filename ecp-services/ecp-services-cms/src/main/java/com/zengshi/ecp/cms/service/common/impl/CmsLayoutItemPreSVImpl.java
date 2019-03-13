package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsLayoutItemPreMapper;
import com.zengshi.ecp.cms.dao.model.CmsLayoutItemPre;
import com.zengshi.ecp.cms.dao.model.CmsLayoutItemPreCriteria;
import com.zengshi.ecp.cms.dao.model.CmsLayoutItemPreCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreReqDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants.ParamStatus;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPreSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPreSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPreSV;
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
@Service("cmsLayoutItemPreSV")
public class CmsLayoutItemPreSVImpl extends GeneralSQLSVImpl implements ICmsLayoutItemPreSV {

    @Resource(name = "SEQ_CMS_LAYOUT_ITEM_PRE")
    private PaasSequence seqCmsLayoutItemPre;
    @Resource
    private CmsLayoutItemPreMapper cmsLayoutItemPreMapper;
    @Resource
    private ICmsItemPropPreSV itemPropPreSV;
    @Resource
    private ICmsPicHotPreSV picHotPreSV;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPreSV#saveCmsLayoutItemPre(com.zengshi.ecp.cms.dao.model.CmsLayoutItemPre)
     */
    @Override
    public CmsLayoutItemPreRespDTO addCmsLayoutItemPre(CmsLayoutItemPreReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsLayoutItemPre bo = new CmsLayoutItemPre();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsLayoutItemPre.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsLayoutItemPreMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsLayoutItemPreRespDTO respDTO = new CmsLayoutItemPreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }

        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPreSV#updateCmsLayoutItemPre(com.zengshi.ecp.cms.dao.model.CmsLayoutItemPre)
     */
    @Override
    public CmsLayoutItemPreRespDTO updateCmsLayoutItemPre(CmsLayoutItemPreReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsLayoutItemPre bo = new CmsLayoutItemPre();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        if(StringUtil.isNotEmpty(dto.getModularId())){//如果是更换布局项中的模块 则失效对应的属性关系表
            CmsLayoutItemPreRespDTO oldItem = this.queryCmsLayoutItemPre(dto);
            if(oldItem!=null && StringUtil.isNotEmpty(oldItem.getModularId()) 
               && oldItem.getModularId().longValue() != dto.getModularId().longValue()){
                this.updateCmsLayoutItemPreAndOther(dto);
            }
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsLayoutItemPre(bo);
    }
    
    /** 
     * updateCmsLayoutPreAndOther. <br/> 
     * TODO(根据布局项ID更新布局项及附表).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param layoutPreReqDTO
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void updateCmsLayoutItemPreAndOther(CmsLayoutItemPreReqDTO layoutItemPreReqDTO) throws BusinessException {
        
        //1 根据布局项ID查询布局项与属性关系，4.1）将布局项与属性关系失效  4.2）根据关系ID查询热点表
        CmsItemPropPreReqDTO itemPropPreReqDTO = new CmsItemPropPreReqDTO();
        itemPropPreReqDTO.setItemId(layoutItemPreReqDTO.getId());
        itemPropPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        List<CmsItemPropPreRespDTO> itemPropPreRespDTOList = itemPropPreSV.queryCmsItemPropPreList(itemPropPreReqDTO);
        //2 将布局项与属性关系表失效
        itemPropPreSV.deleteCmsItemPropPreByExample(itemPropPreReqDTO);
        if(CollectionUtils.isNotEmpty(itemPropPreRespDTOList)){
            for(CmsItemPropPreRespDTO itemPropPreRespDTO : itemPropPreRespDTOList){
                //3 将热点表失效
                CmsPicHotPreReqDTO picHotPreReqDTO = new CmsPicHotPreReqDTO();
                picHotPreReqDTO.setItemPropId(itemPropPreRespDTO.getId());
                picHotPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
                picHotPreSV.deleteCmsPicHotPreByExample(picHotPreReqDTO);
            }
        }
    }
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPreSV#updateCmsLayoutItemPreBatch(java.util.List)
     */
    @Override
    public void updateCmsLayoutItemPreBatch(List<CmsLayoutItemPreReqDTO> list)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            CmsLayoutItemPreReqDTO dto = list.get(i);
            if(dto != null && StringUtil.isNotBlank(dto.getStatus())){
                
                //1 更新布局表，更新状态及排序。
                CmsLayoutItemPre bo = new CmsLayoutItemPre();
                if(dto != null){
                    ObjectCopyUtil.copyObjValue(dto, bo, null, false);
                }
                bo.setUpdateStaff(dto.getStaff().getId());
                bo.setUpdateTime(DateUtil.getSysDate());
                this.updateCmsLayoutItemPre(bo);
                
                //2 当状态为2时，将布局失效，
                if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2.equals(dto.getStatus())){//删除
                    updateCmsLayoutItemPreAndOther(dto);
                }
            }
        }
    }
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPreSV#updateCmsLayoutItemPre(com.zengshi.ecp.cms.dao.model.CmsLayoutItemPre)
     */
    @Override
    public CmsLayoutItemPreRespDTO updateCmsLayoutItemPreSensitive(CmsLayoutItemPreReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsLayoutItemPre bo = new CmsLayoutItemPre();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsLayoutItemPreSensitive(bo);
    }
    
    /** 
     * updateCmsLayoutItemPre:(更新楼层的原子方法). <br/> 
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
    public CmsLayoutItemPreRespDTO updateCmsLayoutItemPre(CmsLayoutItemPre bo) throws BusinessException {
        cmsLayoutItemPreMapper.updateByPrimaryKeySelective(bo);
        CmsLayoutItemPreRespDTO respDTO = new CmsLayoutItemPreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }
    /** 
     * updateCmsLayoutItemPre:(更新楼层的原子方法). <br/> 
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
    public CmsLayoutItemPreRespDTO updateCmsLayoutItemPreSensitive(CmsLayoutItemPre bo) throws BusinessException {
        cmsLayoutItemPreMapper.updateByPrimaryKey(bo);
        CmsLayoutItemPreRespDTO respDTO = new CmsLayoutItemPreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPreSV#deleteCmsLayoutItemPre(java.lang.Long)
     */
    @Override
    public void deleteCmsLayoutItemPre(String id) throws BusinessException {
        CmsLayoutItemPre bo = new CmsLayoutItemPre();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsLayoutItemPre(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPreSV#deleteCmsLayoutItemPreBatch(java.util.List)
     */
    @Override
    public void deleteCmsLayoutItemPreBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsLayoutItemPre(id);
            }
        }
    }
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPreSV#deleteCmsLayoutItemPreByExample(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO)
     */
    @Override
    public int deleteCmsLayoutItemPreByExample(CmsLayoutItemPreReqDTO dto) throws BusinessException {
        /*1.组装删除bo*/
        CmsLayoutItemPre bo = new CmsLayoutItemPre();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /* 2.查询条件 */
        CmsLayoutItemPreCriteria cmsLayoutItemPreCriteria = new CmsLayoutItemPreCriteria();
        Criteria criteria = cmsLayoutItemPreCriteria.createCriteria();
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
        if (StringUtil.isNotEmpty(dto.getModularId())) {
            criteria.andModularIdEqualTo(dto.getModularId());
        }
        if (StringUtil.isNotBlank(dto.getItemSize())) {
            criteria.andItemSizeEqualTo(dto.getItemSize());
        }
        if (StringUtil.isNotEmpty(dto.getItemNo())) {
            criteria.andItemNoEqualTo(dto.getItemNo());
        }
        return cmsLayoutItemPreMapper.updateByExampleSelective(bo, cmsLayoutItemPreCriteria);
    }
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPreSV#changeStatusCmsLayoutItemPre(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsLayoutItemPre(String id, String status) throws BusinessException {
        CmsLayoutItemPre bo = new CmsLayoutItemPre();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsLayoutItemPre(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPreSV#changeStatusCmsLayoutItemPreBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsLayoutItemPreBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsLayoutItemPre(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPreSV#queryCmsLayoutItemPre(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO)
     */
    @Override
    public CmsLayoutItemPreRespDTO queryCmsLayoutItemPre(CmsLayoutItemPreReqDTO dto) throws BusinessException {
        CmsLayoutItemPreRespDTO cmsLayoutItemPreRespDTO = new CmsLayoutItemPreRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsLayoutItemPre bo = cmsLayoutItemPreMapper.selectByPrimaryKey(dto.getId());
            cmsLayoutItemPreRespDTO = conversionObject(bo);
        }
        
        return cmsLayoutItemPreRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPreSV#queryCmsLayoutItemPreList(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO)
     */
    @Override
    public List<CmsLayoutItemPreRespDTO> queryCmsLayoutItemPreList(CmsLayoutItemPreReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsLayoutItemPreCriteria cmsLayoutItemPreCriteria = new CmsLayoutItemPreCriteria();
        Criteria criteria = cmsLayoutItemPreCriteria.createCriteria();
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
        cmsLayoutItemPreCriteria.setOrderByClause("ITEM_NO ASC,CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsLayoutItemPreRespDTO> cmsLayoutItemPreRespDTOList =  new ArrayList<CmsLayoutItemPreRespDTO>();
        List<CmsLayoutItemPre> cmsLayoutItemPreList = cmsLayoutItemPreMapper.selectByExample(cmsLayoutItemPreCriteria);
        if(CollectionUtils.isNotEmpty(cmsLayoutItemPreList)){
            for(CmsLayoutItemPre bo :cmsLayoutItemPreList){
                CmsLayoutItemPreRespDTO cmsLayoutItemPreRespDTO = conversionObject(bo);
                cmsLayoutItemPreRespDTOList.add(cmsLayoutItemPreRespDTO);
            }
        }
        
        return cmsLayoutItemPreRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPreSV#queryCmsLayoutItemPrePage(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsLayoutItemPreRespDTO> queryCmsLayoutItemPrePage(CmsLayoutItemPreReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsLayoutItemPreCriteria cmsLayoutItemPreCriteria = new CmsLayoutItemPreCriteria();
        Criteria criteria = cmsLayoutItemPreCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
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
        cmsLayoutItemPreCriteria.setOrderByClause("ITEM_NO ASC,CREATE_TIME ASC");
        cmsLayoutItemPreCriteria.setLimitClauseCount(dto.getPageSize());
        cmsLayoutItemPreCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsLayoutItemPreCriteria,false,new PaginationCallback<CmsLayoutItemPre, CmsLayoutItemPreRespDTO>(){

            @Override
            public List<CmsLayoutItemPre> queryDB(BaseCriteria criteria) {
                return cmsLayoutItemPreMapper.selectByExample((CmsLayoutItemPreCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsLayoutItemPreMapper.countByExample((CmsLayoutItemPreCriteria)criteria);
            }

            @Override
            public CmsLayoutItemPreRespDTO warpReturnObject(CmsLayoutItemPre bo) {
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
    private CmsLayoutItemPreRespDTO conversionObject(CmsLayoutItemPre bo){
        CmsLayoutItemPreRespDTO dto = new CmsLayoutItemPreRespDTO();
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
