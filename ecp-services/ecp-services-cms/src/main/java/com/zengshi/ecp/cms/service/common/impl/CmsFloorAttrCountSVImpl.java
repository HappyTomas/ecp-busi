package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsFloorAttrCountMapper;
import com.zengshi.ecp.cms.dao.model.CmsFloorAttrCount;
import com.zengshi.ecp.cms.dao.model.CmsFloorAttrCountCriteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorAttrCountReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorAttrCountRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAttrCountSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV;
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
@Service("cmsFloorAttrCountSV")
public class CmsFloorAttrCountSVImpl extends GeneralSQLSVImpl implements ICmsFloorAttrCountSV {

    @Resource(name = "SEQ_CMS_FLOOR_ATTR_COUNT")
    private PaasSequence seqCmsFloorAttrCount;
    
    @Resource
    private CmsFloorAttrCountMapper cmsFloorAttrCountMapper;
    
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    @Resource
    private ICmsFloorSV cmsFloorSV;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAttrCountSV#saveCmsFloorAttrCount(com.zengshi.ecp.cms.dao.model.CmsFloorAttrCount)
     */
    @Override
    public CmsFloorAttrCountRespDTO addCmsFloorAttrCount(CmsFloorAttrCountReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsFloorAttrCount bo = new CmsFloorAttrCount();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsFloorAttrCount.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsFloorAttrCountMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsFloorAttrCountRespDTO respDTO = new CmsFloorAttrCountRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAttrCountSV#updateCmsFloorAttrCount(com.zengshi.ecp.cms.dao.model.CmsFloorAttrCount)
     */
    @Override
    public CmsFloorAttrCountRespDTO updateCmsFloorAttrCount(CmsFloorAttrCountReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsFloorAttrCount bo = new CmsFloorAttrCount();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层页签的原子方法*/
        return this.updateCmsFloorAttrCount(bo);
    }
    
    /** 
     * updateCmsFloorAttrCount:(更新楼层页签的原子方法). <br/> 
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
    public CmsFloorAttrCountRespDTO updateCmsFloorAttrCount(CmsFloorAttrCount bo) throws BusinessException {
        cmsFloorAttrCountMapper.updateByPrimaryKeySelective(bo);
        /*3.将结果返回*/
        CmsFloorAttrCountRespDTO respDTO = new CmsFloorAttrCountRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAttrCountSV#deleteCmsFloorAttrCount(java.lang.Long)
     */
    @Override
    public void deleteCmsFloorAttrCount(String id) throws BusinessException {
        CmsFloorAttrCount bo = new CmsFloorAttrCount();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsFloorAttrCount(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAttrCountSV#deleteCmsFloorAttrCountBatch(java.util.List)
     */
    @Override
    public void deleteCmsFloorAttrCountBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsFloorAttrCount(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAttrCountSV#changeStatusCmsFloorAttrCount(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsFloorAttrCount(String id, String status) throws BusinessException {
        CmsFloorAttrCount bo = new CmsFloorAttrCount();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsFloorAttrCount(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAttrCountSV#changeStatusCmsFloorAttrCountBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsFloorAttrCountBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsFloorAttrCount(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAttrCountSV#queryCmsFloorAttrCount(com.zengshi.ecp.cms.dubbo.dto.CmsFloorAttrCountReqDTO)
     */
    @Override
    public CmsFloorAttrCountRespDTO queryCmsFloorAttrCount(CmsFloorAttrCountReqDTO dto) throws BusinessException {
        CmsFloorAttrCountRespDTO cmsFloorAttrCountRespDTO = new CmsFloorAttrCountRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsFloorAttrCount bo = cmsFloorAttrCountMapper.selectByPrimaryKey(dto.getId());
            cmsFloorAttrCountRespDTO = conversionObject(bo);
        }
        
        return cmsFloorAttrCountRespDTO;
    }
    
    /**
     * TODO 查询楼层页签列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAttrCountSV#queryCmsFloorAttrCountList(com.zengshi.ecp.cms.dubbo.dto.CmsFloorAttrCountReqDTO)
     */
    @Override
    public List<CmsFloorAttrCountRespDTO> queryCmsFloorAttrCountList(CmsFloorAttrCountReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsFloorAttrCountCriteria cmsFloorAttrCountCriteria = new CmsFloorAttrCountCriteria();
        com.zengshi.ecp.cms.dao.model.CmsFloorAttrCountCriteria.Criteria criteria = cmsFloorAttrCountCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
        if (StringUtil.isNotEmpty(dto.getFloorId())) {
            criteria.andFloorIdEqualTo(dto.getFloorId());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtil.isNotBlank(dto.getFloorAttr())) {
            criteria.andFloorAttrEqualTo(dto.getFloorAttr());
        }
        cmsFloorAttrCountCriteria.setOrderByClause("CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsFloorAttrCountRespDTO> cmsFloorAttrCountRespDTOList =  new ArrayList<CmsFloorAttrCountRespDTO>();
        List<CmsFloorAttrCount> cmsFloorAttrCountList = cmsFloorAttrCountMapper.selectByExample(cmsFloorAttrCountCriteria);
        if(CollectionUtils.isNotEmpty(cmsFloorAttrCountList)){
            for(CmsFloorAttrCount bo :cmsFloorAttrCountList){
                CmsFloorAttrCountRespDTO cmsFloorAttrCountRespDTO = conversionObject(bo);
                cmsFloorAttrCountRespDTOList.add(cmsFloorAttrCountRespDTO);
            }
        }
        
        return cmsFloorAttrCountRespDTOList;
    }


    /** 
     * TODO 查询楼层页签，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAttrCountSV#queryCmsFloorAttrCountPage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorAttrCountReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsFloorAttrCountRespDTO> queryCmsFloorAttrCountPage(CmsFloorAttrCountReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层页签 */
        CmsFloorAttrCountCriteria cmsFloorAttrCountCriteria = new CmsFloorAttrCountCriteria();
        com.zengshi.ecp.cms.dao.model.CmsFloorAttrCountCriteria.Criteria criteria = cmsFloorAttrCountCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
        if (StringUtil.isNotEmpty(dto.getFloorId())) {
            criteria.andFloorIdEqualTo(dto.getFloorId());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtil.isNotBlank(dto.getFloorAttr())) {
            criteria.andFloorAttrEqualTo(dto.getFloorAttr());
        }
        
        cmsFloorAttrCountCriteria.setOrderByClause("CREATE_TIME DESC");
        cmsFloorAttrCountCriteria.setLimitClauseCount(dto.getPageSize());
        cmsFloorAttrCountCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsFloorAttrCountCriteria,false,new PaginationCallback<CmsFloorAttrCount, CmsFloorAttrCountRespDTO>(){

            @Override
            public List<CmsFloorAttrCount> queryDB(BaseCriteria criteria) {
                return cmsFloorAttrCountMapper.selectByExample((CmsFloorAttrCountCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsFloorAttrCountMapper.countByExample((CmsFloorAttrCountCriteria)criteria);
            }

            @Override
            public CmsFloorAttrCountRespDTO warpReturnObject(CmsFloorAttrCount bo) {
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
    private CmsFloorAttrCountRespDTO conversionObject(CmsFloorAttrCount bo){
        CmsFloorAttrCountRespDTO dto = new CmsFloorAttrCountRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        if (dto.getPlaceId()!=null) {
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
            if(cmsFloorRespDTO != null){
                dto.setFloorName(cmsFloorRespDTO.getFloorName());
            }
        }
        
        //3.遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String floorAttrZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_FLOOR_ATTR, dto.getFloorAttr());
        dto.setFloorAttrZH(floorAttrZH);
        return dto;
    }

}
