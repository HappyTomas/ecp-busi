package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsFloorGdsMapper;
import com.zengshi.ecp.cms.dao.model.CmsFloorGds;
import com.zengshi.ecp.cms.dao.model.CmsFloorGdsCriteria;
import com.zengshi.ecp.cms.dao.model.CmsFloorGdsCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorGdsSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTabSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV;
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
@Service("cmsFloorGdsSV")
public class CmsFloorGdsSVImpl extends GeneralSQLSVImpl implements ICmsFloorGdsSV {

    @Resource(name = "SEQ_CMS_FLOOR_GDS")
    private PaasSequence seqCmsFloorGds;
    
    @Resource
    private CmsFloorGdsMapper cmsFloorGdsMapper;
    
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    @Resource
    private ICmsFloorSV cmsFloorSV;
    
    @Resource
    private ICmsFloorTabSV cmsFloorTabSV;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorGdsSV#saveCmsFloorGds(com.zengshi.ecp.cms.dao.model.CmsFloorGds)
     */
    @Override
    public CmsFloorGdsRespDTO addCmsFloorGds(CmsFloorGdsReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsFloorGds bo = new CmsFloorGds();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsFloorGds.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsFloorGdsMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsFloorGdsRespDTO respDTO = new CmsFloorGdsRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorGdsSV#saveCmsFloorGds(com.zengshi.ecp.cms.dao.model.CmsFloorGds)
     */
    @Override
    public List<CmsFloorGdsRespDTO> addCmsFloorGdsBatch(List<CmsFloorGdsReqDTO> list) throws BusinessException {
		List<CmsFloorGdsRespDTO> cmsFloorGdsRespDTOs = new ArrayList<CmsFloorGdsRespDTO>();
		for (int i = 0; i < list.size(); i++) {
			cmsFloorGdsRespDTOs.add(this.addCmsFloorGds(list.get(i)));
		}
		return cmsFloorGdsRespDTOs;
    }
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorGdsSV#updateCmsFloorGds(com.zengshi.ecp.cms.dao.model.CmsFloorGds)
     */
    @Override
    public List<CmsFloorGdsRespDTO> updateCmsFloorGdsBatch(List<CmsFloorGdsReqDTO> list) throws BusinessException {
       List<CmsFloorGdsRespDTO> cmsFloorGdsRespDTOs = new ArrayList<CmsFloorGdsRespDTO>();
       for(int i=0;i<list.size();i++){
    	   cmsFloorGdsRespDTOs.add(this.queryCmsFloorGds(list.get(i)));
       }
       return cmsFloorGdsRespDTOs;
    }
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorGdsSV#updateCmsFloorGds(com.zengshi.ecp.cms.dao.model.CmsFloorGds)
     */
    @Override
    public CmsFloorGdsRespDTO updateCmsFloorGds(CmsFloorGdsReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsFloorGds bo = new CmsFloorGds();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层商品的原子方法*/
        return this.updateCmsFloorGds(bo);
    }
    
    /** 
     * updateCmsFloorGds:(更新楼层商品的原子方法). <br/> 
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
    public CmsFloorGdsRespDTO updateCmsFloorGds(CmsFloorGds bo) throws BusinessException {
        cmsFloorGdsMapper.updateByPrimaryKeySelective(bo);
        /*3.将结果返回*/
        CmsFloorGdsRespDTO respDTO = new CmsFloorGdsRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /** 
     * updateCmsFloorGdsByExample:(更新楼层商品的原子方法). <br/> 
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
    public CmsFloorGdsRespDTO updateCmsFloorGdsByExample(CmsFloorGdsReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsFloorGds bo = new CmsFloorGds();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /* 2.根据条件检索楼层商品 */
        CmsFloorGdsCriteria cmsFloorGdsCriteria = new CmsFloorGdsCriteria();
        Criteria criteria = cmsFloorGdsCriteria.createCriteria();
        if (StringUtil.isNotEmpty(bo.getGdsId())) {
            criteria.andGdsIdEqualTo(bo.getGdsId());
        }
        cmsFloorGdsMapper.updateByExampleSelective(bo, cmsFloorGdsCriteria);
        
        /*3.将结果返回*/
        CmsFloorGdsRespDTO respDTO = new CmsFloorGdsRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorGdsSV#deleteCmsFloorGds(java.lang.Long)
     */
    @Override
    public void deleteCmsFloorGds(String id) throws BusinessException {
        CmsFloorGds bo = new CmsFloorGds();
        bo.setId(Long.valueOf(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsFloorGds(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorGdsSV#deleteCmsFloorGdsBatch(java.util.List)
     */
    @Override
    public void deleteCmsFloorGdsBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsFloorGds(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorGdsSV#changeStatusCmsFloorGds(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsFloorGds(String id, String status) throws BusinessException {
        CmsFloorGds bo = new CmsFloorGds();
        bo.setId(Long.valueOf(id));
        bo.setStatus(status);
        this.updateCmsFloorGds(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorGdsSV#changeStatusCmsFloorGdsBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsFloorGdsBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsFloorGds(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorGdsSV#queryCmsFloorGds(com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO)
     */
    @Override
    public CmsFloorGdsRespDTO queryCmsFloorGds(CmsFloorGdsReqDTO dto) throws BusinessException {
        CmsFloorGdsRespDTO cmsFloorGdsRespDTO = new CmsFloorGdsRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsFloorGds bo = cmsFloorGdsMapper.selectByPrimaryKey(dto.getId());
            cmsFloorGdsRespDTO = conversionObject(bo);
        }
        
        return cmsFloorGdsRespDTO;
    }
    
    /**
     * TODO 查询楼层商品列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorGdsSV#queryCmsFloorGdsList(com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO)
     */
    @Override
    public List<CmsFloorGdsRespDTO> queryCmsFloorGdsList(CmsFloorGdsReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsFloorGdsCriteria cmsFloorGdsCriteria = new CmsFloorGdsCriteria();
        com.zengshi.ecp.cms.dao.model.CmsFloorGdsCriteria.Criteria criteria = cmsFloorGdsCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
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
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
        if (StringUtil.isNotEmpty(dto.getFloorId())) {
            criteria.andFloorIdEqualTo(dto.getFloorId());
        }
        if (StringUtil.isNotEmpty(dto.getTabId())) {
            criteria.andTabIdEqualTo(dto.getTabId());
        }
        if (StringUtil.isNotEmpty(dto.getIsProm())) {
            criteria.andIsPromEqualTo(dto.getIsProm());
        }
        cmsFloorGdsCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsFloorGdsRespDTO> cmsFloorGdsRespDTOList =  new ArrayList<CmsFloorGdsRespDTO>();
        List<CmsFloorGds> cmsFloorGdsList = cmsFloorGdsMapper.selectByExample(cmsFloorGdsCriteria);
        if(CollectionUtils.isNotEmpty(cmsFloorGdsList)){
            for(CmsFloorGds bo :cmsFloorGdsList){
                CmsFloorGdsRespDTO cmsFloorGdsRespDTO = conversionObject(bo);
                cmsFloorGdsRespDTOList.add(cmsFloorGdsRespDTO);
            }
        }
        
        return cmsFloorGdsRespDTOList;
    }


    /** 
     * TODO 查询楼层商品，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorGdsSV#queryCmsFloorGdsPage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsFloorGdsRespDTO> queryCmsFloorGdsPage(CmsFloorGdsReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层商品 */
        CmsFloorGdsCriteria cmsFloorGdsCriteria = new CmsFloorGdsCriteria();
        com.zengshi.ecp.cms.dao.model.CmsFloorGdsCriteria.Criteria criteria = cmsFloorGdsCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
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
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
        if (StringUtil.isNotEmpty(dto.getFloorId())) {
            criteria.andFloorIdEqualTo(dto.getFloorId());
        }
        if (StringUtil.isNotEmpty(dto.getTabId())) {
            criteria.andTabIdEqualTo(dto.getTabId());
        }
        if (StringUtil.isNotEmpty(dto.getIsProm())) {
            criteria.andIsPromEqualTo(dto.getIsProm());
        }
        cmsFloorGdsCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        cmsFloorGdsCriteria.setLimitClauseCount(dto.getPageSize());
        cmsFloorGdsCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsFloorGdsCriteria,false,new PaginationCallback<CmsFloorGds, CmsFloorGdsRespDTO>(){

            @Override
            public List<CmsFloorGds> queryDB(BaseCriteria criteria) {
                return cmsFloorGdsMapper.selectByExample((CmsFloorGdsCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsFloorGdsMapper.countByExample((CmsFloorGdsCriteria)criteria);
            }

            @Override
            public CmsFloorGdsRespDTO warpReturnObject(CmsFloorGds bo) {
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
    private CmsFloorGdsRespDTO conversionObject(CmsFloorGds bo){
        CmsFloorGdsRespDTO dto = new CmsFloorGdsRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            CmsPlaceReqDTO cmsPlaceDTO = new CmsPlaceReqDTO();
            cmsPlaceDTO.setId(dto.getPlaceId());
            CmsPlaceRespDTO cmsPlaceRespDTO = cmsPlaceSV.queryCmsPlace(cmsPlaceDTO);
            if (cmsPlaceRespDTO != null) {
                dto.setPlaceName(cmsPlaceRespDTO.getPlaceName());
            }
        }
        // 2 根据内容位置ID获取楼层信息
        if (StringUtil.isNotEmpty(dto.getFloorId())) {
            CmsFloorReqDTO cmsFloorDTO = new CmsFloorReqDTO();
            cmsFloorDTO.setId(dto.getFloorId());
            CmsFloorRespDTO cmsFloorRespDTO = cmsFloorSV.queryCmsFloor(cmsFloorDTO);
            if (cmsFloorRespDTO != null) {
                dto.setFloorName(cmsFloorRespDTO.getFloorName());
            }
        }
        // 3 根据内容位置ID获取楼层页签
        if (StringUtil.isNotEmpty(dto.getTabId())) {
            CmsFloorTabReqDTO cmsFloorTabReqDTO = new CmsFloorTabReqDTO();
            cmsFloorTabReqDTO.setId(dto.getTabId());
            CmsFloorTabRespDTO cmsFloorTabRespDTO = cmsFloorTabSV.queryCmsFloorTab(cmsFloorTabReqDTO);
            if (cmsFloorTabRespDTO != null) {
                dto.setTabName(cmsFloorTabRespDTO.getTabName());
            }
        }
        
        //4.遍历将编码转中文 
        //String isLinkZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.PUBLIC_PARAM_ISNOT, cmsFloorGdsRespDTO.getIsLink());
        //cmsFloorGdsRespDTO.setIsLinkZH(isLinkZH);
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        return dto;
    }

    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorGdsSV#deleteCmsFloorGdsForProm(com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO)
     */
    @Override
    public int deleteCmsFloorGdsForProm(CmsFloorGdsReqDTO dto)
            throws BusinessException {
        /*1.将入参组装成bo*/
        CmsFloorGds bo = new CmsFloorGds();
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /* 2.根据条件检索楼层商品 */
        CmsFloorGdsCriteria cmsFloorGdsCriteria = new CmsFloorGdsCriteria();
        Criteria criteria = cmsFloorGdsCriteria.createCriteria();
        
        if(dto.getGdsIds()==null){
            dto.setGdsIds(new ArrayList<Long>());
        }
        if (StringUtil.isNotEmpty(dto.getGdsId())) {
            dto.getGdsIds().add(dto.getGdsId());
        }
        
        if(CollectionUtils.isNotEmpty(dto.getGdsIds())){
            criteria.andGdsIdIn(dto.getGdsIds());
        }
        if (StringUtil.isNotEmpty(dto.getPromId())) {
            criteria.andPromIdEqualTo(dto.getPromId());
        }
        if (StringUtil.isNotEmpty(dto.getIsProm())) {
            criteria.andIsPromEqualTo(dto.getIsProm());
        }
        
        
        return cmsFloorGdsMapper.updateByExampleSelective(bo, cmsFloorGdsCriteria);
    }

}
