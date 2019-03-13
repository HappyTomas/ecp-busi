package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsLayoutPreMapper;
import com.zengshi.ecp.cms.dao.model.CmsLayoutPre;
import com.zengshi.ecp.cms.dao.model.CmsLayoutPreCriteria;
import com.zengshi.ecp.cms.dao.model.CmsLayoutPreCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutAttrPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreReqDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants.ParamStatus;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsItemPropPreSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutAttrPreSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutItemPreSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPreSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPicHotPreSV;
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
@Service("cmsLayoutPreSV")
public class CmsLayoutPreSVImpl extends GeneralSQLSVImpl implements ICmsLayoutPreSV {

    @Resource(name = "SEQ_CMS_LAYOUT_PRE")
    private PaasSequence seqCmsLayoutPre;
    @Resource
    private CmsLayoutPreMapper cmsLayoutPreMapper;
    @Resource
    private ICmsLayoutItemPreSV layoutItemPreSV;
    @Resource
    private ICmsLayoutAttrPreSV layoutAttrPreSV;
    @Resource
    private ICmsItemPropPreSV itemPropPreSV;
    @Resource
    private ICmsPicHotPreSV picHotPreSV;
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPreSV#saveCmsLayoutPre(com.zengshi.ecp.cms.dao.model.CmsLayoutPre)
     */
    @Override
    public CmsLayoutPreRespDTO addCmsLayoutPre(CmsLayoutPreReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsLayoutPre bo = new CmsLayoutPre();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsLayoutPre.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsLayoutPreMapper.insertSelective(bo);
        
        /*3.调布局项dao层接口 */
        CmsLayoutPreRespDTO respDTO = new CmsLayoutPreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        if(CollectionUtils.isNotEmpty(dto.getLayoutItemPreList())){
            List<CmsLayoutItemPreRespDTO> items = new ArrayList<CmsLayoutItemPreRespDTO>();
            for(CmsLayoutItemPreReqDTO itemDTO : dto.getLayoutItemPreList()){
                itemDTO.setLayoutId(id);
                items.add(layoutItemPreSV.addCmsLayoutItemPre(itemDTO));
            }
            respDTO.setLayoutItemPreRespDTOList(items);
        }
        
        /*4.将结果返回*/
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPreSV#updateCmsLayoutPre(com.zengshi.ecp.cms.dao.model.CmsLayoutPre)
     */
    @Override
    public CmsLayoutPreRespDTO updateCmsLayoutPre(CmsLayoutPreReqDTO dto) throws BusinessException {
        /*1.更新布局表*/
        CmsLayoutPre bo = new CmsLayoutPre();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        CmsLayoutPreRespDTO layoutPreRespDTO = this.updateCmsLayoutPre(bo);
        
        if(CollectionUtils.isNotEmpty(dto.getLayoutItemPreList())){
            /*2.根据布局ID将其附属表失效*/
            updateCmsLayoutPreAndOther(dto);
            
            /*3.新增相应布局项*/
            List<CmsLayoutItemPreRespDTO> items = new ArrayList<CmsLayoutItemPreRespDTO>();
            for(CmsLayoutItemPreReqDTO layoutItemPreReqDTO : dto.getLayoutItemPreList()){
                if(layoutItemPreReqDTO != null){
                    layoutItemPreReqDTO.setLayoutId(dto.getId());
                    layoutItemPreReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
                    items.add(layoutItemPreSV.addCmsLayoutItemPre(layoutItemPreReqDTO));
                }
            }
            layoutPreRespDTO.setLayoutItemPreRespDTOList(items);
        }
        return layoutPreRespDTO;
    }
    
    /** 
     * updateCmsLayoutPre:(更新布局类型的原子方法). <br/> 
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
    public CmsLayoutPreRespDTO updateCmsLayoutPre(CmsLayoutPre bo) throws BusinessException {
        cmsLayoutPreMapper.updateByPrimaryKeySelective(bo);
        CmsLayoutPreRespDTO respDTO = new CmsLayoutPreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPreSV#deleteCmsLayoutPre(java.lang.Long)
     */
    @Override
    public void deleteCmsLayoutPre(String id) throws BusinessException {
        CmsLayoutPre bo = new CmsLayoutPre();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsLayoutPre(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPreSV#deleteCmsLayoutPreBatch(java.util.List)
     */
    @Override
    public void deleteCmsLayoutPreBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsLayoutPre(id);
            }
        }
    }
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPreSV#deleteCmsLayoutPreByExample(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreReqDTO)
     */
    @Override
    public int deleteCmsLayoutPreByExample(CmsLayoutPreReqDTO dto) throws BusinessException {
        /*1.组装删除bo*/
        CmsLayoutPre bo = new CmsLayoutPre();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /* 2.查询条件 */
        CmsLayoutPreCriteria cmsLayoutPreCriteria = new CmsLayoutPreCriteria();
        Criteria criteria = cmsLayoutPreCriteria.createCriteria();
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
        if (StringUtil.isNotEmpty(dto.getLayoutTypeId())) {
            criteria.andLayoutTypeIdEqualTo(dto.getLayoutTypeId());
        }
        if (StringUtil.isNotEmpty(dto.getShowOrder())) {
            criteria.andShowOrderEqualTo(dto.getShowOrder());
        }
        return cmsLayoutPreMapper.updateByExampleSelective(bo, cmsLayoutPreCriteria);
    }
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPreSV#changeStatusCmsLayoutPre(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsLayoutPre(String id, String status) throws BusinessException {
        CmsLayoutPre bo = new CmsLayoutPre();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsLayoutPre(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPreSV#changeStatusCmsLayoutPreBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsLayoutPreBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsLayoutPre(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPreSV#queryCmsLayoutPre(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreReqDTO)
     */
    @Override
    public CmsLayoutPreRespDTO queryCmsLayoutPre(CmsLayoutPreReqDTO dto) throws BusinessException {
        CmsLayoutPreRespDTO cmsLayoutPreRespDTO = new CmsLayoutPreRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsLayoutPre bo = cmsLayoutPreMapper.selectByPrimaryKey(dto.getId());
            cmsLayoutPreRespDTO = conversionObject(bo);
        }
        
        return cmsLayoutPreRespDTO;
    }
    
    /**
     * TODO 查询布局类型列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPreSV#queryCmsLayoutPreList(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreReqDTO)
     */
    @Override
    public List<CmsLayoutPreRespDTO> queryCmsLayoutPreList(CmsLayoutPreReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsLayoutPreCriteria cmsLayoutPreCriteria = new CmsLayoutPreCriteria();
        Criteria criteria = cmsLayoutPreCriteria.createCriteria();
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
        if (StringUtil.isNotEmpty(dto.getLayoutTypeId())) {
            criteria.andLayoutTypeIdEqualTo(dto.getLayoutTypeId());
        }
        cmsLayoutPreCriteria.setOrderByClause("SHOW_ORDER ASC,CREATE_TIME ASC");
        
        /*2.迭代查询结果*/
        List<CmsLayoutPreRespDTO> cmsLayoutPreRespDTOList =  new ArrayList<CmsLayoutPreRespDTO>();
        List<CmsLayoutPre> cmsLayoutPreList = cmsLayoutPreMapper.selectByExample(cmsLayoutPreCriteria);
        if(CollectionUtils.isNotEmpty(cmsLayoutPreList)){
            for(CmsLayoutPre bo :cmsLayoutPreList){
                CmsLayoutPreRespDTO cmsLayoutPreRespDTO = conversionObject(bo);
                cmsLayoutPreRespDTOList.add(cmsLayoutPreRespDTO);
            }
        }
        
        return cmsLayoutPreRespDTOList;

    }


    /** 
     * TODO 查询布局类型，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPreSV#queryCmsLayoutPrePage(com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsLayoutPreRespDTO> queryCmsLayoutPrePage(CmsLayoutPreReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索布局类型 */
        CmsLayoutPreCriteria cmsLayoutPreCriteria = new CmsLayoutPreCriteria();
        Criteria criteria = cmsLayoutPreCriteria.createCriteria();
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
        if (StringUtil.isNotEmpty(dto.getLayoutTypeId())) {
            criteria.andLayoutTypeIdEqualTo(dto.getLayoutTypeId());
        }
        cmsLayoutPreCriteria.setOrderByClause("SHOW_ORDER ASC,CREATE_TIME ASC");
        cmsLayoutPreCriteria.setLimitClauseCount(dto.getPageSize());
        cmsLayoutPreCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsLayoutPreCriteria,false,new PaginationCallback<CmsLayoutPre, CmsLayoutPreRespDTO>(){

            @Override
            public List<CmsLayoutPre> queryDB(BaseCriteria criteria) {
                return cmsLayoutPreMapper.selectByExample((CmsLayoutPreCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsLayoutPreMapper.countByExample((CmsLayoutPreCriteria)criteria);
            }

            @Override
            public CmsLayoutPreRespDTO warpReturnObject(CmsLayoutPre bo) {
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
    private CmsLayoutPreRespDTO conversionObject(CmsLayoutPre bo){
        CmsLayoutPreRespDTO dto = new CmsLayoutPreRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        
        // 2.遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        
        return dto;
    }
    
    
    /** 
     * updateCmsLayoutPreAndOther. <br/> 
     * TODO(根据布局ID更新布局及附表).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param layoutPreReqDTO
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void updateCmsLayoutPreAndOther(CmsLayoutPreReqDTO layoutPreReqDTO) throws BusinessException {
        
        //2 将布局属性失效
        CmsLayoutAttrPreReqDTO layoutAttrPreReqDTO = new CmsLayoutAttrPreReqDTO();
        layoutAttrPreReqDTO.setLayoutId(layoutPreReqDTO.getId());
        layoutAttrPreSV.deleteCmsLayoutAttrPreByExample(layoutAttrPreReqDTO);
        
        //3 根据布局ID查询布局项，3.1）将布局项失效  4）根据布局项ID查询布局项与属性关系，4.1）将布局项与属性关系失效  4.2）根据关系ID查询热点表
        CmsLayoutItemPreReqDTO layoutItemPreReqDTO = new CmsLayoutItemPreReqDTO();
        layoutItemPreReqDTO.setLayoutId(layoutPreReqDTO.getId());
        layoutItemPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        List<CmsLayoutItemPreRespDTO> layoutItemPreRespDTOList = layoutItemPreSV.queryCmsLayoutItemPreList(layoutItemPreReqDTO);
        //3.1 将布局项失效
        layoutItemPreSV.deleteCmsLayoutItemPreByExample(layoutItemPreReqDTO);
        if(CollectionUtils.isNotEmpty(layoutItemPreRespDTOList)){
            for(CmsLayoutItemPreRespDTO layoutItemPreRespDTO : layoutItemPreRespDTOList){
                //4 根据布局项ID查询布局项与属性关系，4.1）将布局项与属性关系失效  4.2）根据关系ID查询热点表
                CmsItemPropPreReqDTO itemPropPreReqDTO = new CmsItemPropPreReqDTO();
                itemPropPreReqDTO.setItemId(layoutItemPreRespDTO.getId());
                itemPropPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
                List<CmsItemPropPreRespDTO> itemPropPreRespDTOList = itemPropPreSV.queryCmsItemPropPreList(itemPropPreReqDTO);
                //4.1将布局项与属性关系表失效
                itemPropPreSV.deleteCmsItemPropPreByExample(itemPropPreReqDTO);
                if(CollectionUtils.isNotEmpty(itemPropPreRespDTOList)){
                    for(CmsItemPropPreRespDTO itemPropPreRespDTO : itemPropPreRespDTOList){
                        //4.2将热点表失效
                        CmsPicHotPreReqDTO picHotPreReqDTO = new CmsPicHotPreReqDTO();
                        picHotPreReqDTO.setItemPropId(itemPropPreRespDTO.getId());
                        picHotPreReqDTO.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
                        picHotPreSV.deleteCmsPicHotPreByExample(picHotPreReqDTO);
                    }
                }
            }
        }
    }
    
    /** 
     * updateCmsLayoutPreBatch. <br/> 
     * 批量更新布局(删除布局、对布局重新排序)
     * 当状态为2时：
     * TODO(1、更新布局).<br/> 
     * TODO(2、根据布局更新布局属性).<br/> 
     * TODO(3、根据布局更新布局项).<br/> 
     * TODO(4、根据布局更新布局项与属性关系).<br/> 
     * TODO(5、根据布局项与属性关系ID热点表).<br/>
     * 当状态为0时：
     * TODO(1、更新布局，更新排序。).<br/> 
     * @author jiangzh 
     * @param bo
     * @throws BusinessException 
     * @since JDK 1.6 
     */ 
    public void updateCmsLayoutPreBatch(List<CmsLayoutPreReqDTO> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            CmsLayoutPreReqDTO dto = list.get(i);
            if(dto != null && StringUtil.isNotBlank(dto.getStatus())){
                
                //1 更新布局表，更新状态及排序。
                CmsLayoutPre bo = new CmsLayoutPre();
                if(dto != null){
                    ObjectCopyUtil.copyObjValue(dto, bo, null, false);
                }
                bo.setUpdateStaff(dto.getStaff().getId());
                bo.setUpdateTime(DateUtil.getSysDate());
                this.updateCmsLayoutPre(bo);
                
                //2 当状态为2时，将布局失效，
                if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2.equals(dto.getStatus())){//删除
                    updateCmsLayoutPreAndOther(dto);
                }
            }
        }
    }

}
