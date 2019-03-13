package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsFloorLabelMapper;
import com.zengshi.ecp.cms.dao.model.CmsFloorLabel;
import com.zengshi.ecp.cms.dao.model.CmsFloorLabelCriteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorLabelSV;
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
@Service("cmsFloorLabelSV")
public class CmsFloorLabelSVImpl extends GeneralSQLSVImpl implements ICmsFloorLabelSV {

    @Resource(name = "SEQ_CMS_FLOOR_LABEL")
    private PaasSequence seqCmsFloorLabel;
    
    @Resource
    private CmsFloorLabelMapper cmsFloorLabelMapper;
    
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    @Resource
    private ICmsFloorSV cmsFloorSV;

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorLabelSV#saveCmsFloorLabel(com.zengshi.ecp.cms.dao.model.CmsFloorLabel)
     */
    @Override
    public CmsFloorLabelRespDTO addCmsFloorLabel(CmsFloorLabelReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsFloorLabel bo = new CmsFloorLabel();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsFloorLabel.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsFloorLabelMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsFloorLabelRespDTO respDTO = new CmsFloorLabelRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorLabelSV#updateCmsFloorLabel(com.zengshi.ecp.cms.dao.model.CmsFloorLabel)
     */
    @Override
    public CmsFloorLabelRespDTO updateCmsFloorLabel(CmsFloorLabelReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsFloorLabel bo = new CmsFloorLabel();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsFloorLabel(bo);
    }
    
    /** 
     * updateCmsFloorLabel:(更新楼层的原子方法). <br/> 
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
    public CmsFloorLabelRespDTO updateCmsFloorLabel(CmsFloorLabel bo) throws BusinessException {
        cmsFloorLabelMapper.updateByPrimaryKeySelective(bo);
        CmsFloorLabelRespDTO respDTO = new CmsFloorLabelRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorLabelSV#deleteCmsFloorLabel(java.lang.Long)
     */
    @Override
    public void deleteCmsFloorLabel(String id) throws BusinessException {
        CmsFloorLabel bo = new CmsFloorLabel();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsFloorLabel(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorLabelSV#deleteCmsFloorLabelBatch(java.util.List)
     */
    @Override
    public void deleteCmsFloorLabelBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsFloorLabel(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorLabelSV#changeStatusCmsFloorLabel(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsFloorLabel(String id, String status) throws BusinessException {
        CmsFloorLabel bo = new CmsFloorLabel();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsFloorLabel(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorLabelSV#changeStatusCmsFloorLabelBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsFloorLabelBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsFloorLabel(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorLabelSV#queryCmsFloorLabel(com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelReqDTO)
     */
    @Override
    public CmsFloorLabelRespDTO queryCmsFloorLabel(CmsFloorLabelReqDTO dto) throws BusinessException {
        CmsFloorLabelRespDTO cmsFloorLabelRespDTO = new CmsFloorLabelRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsFloorLabel bo = cmsFloorLabelMapper.selectByPrimaryKey(dto.getId());
            cmsFloorLabelRespDTO = conversionObject(bo);
        }
        
        return cmsFloorLabelRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorLabelSV#queryCmsFloorLabelList(com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelReqDTO)
     */
    @Override
    public List<CmsFloorLabelRespDTO> queryCmsFloorLabelList(CmsFloorLabelReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsFloorLabelCriteria cmsFloorLabelCriteria = new CmsFloorLabelCriteria();
        com.zengshi.ecp.cms.dao.model.CmsFloorLabelCriteria.Criteria criteria = cmsFloorLabelCriteria.createCriteria();
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
        if (StringUtil.isNotBlank(dto.getLabelName())) {
            criteria.andLabelNameLike("%"+dto.getLabelName()+"%");
        }
        cmsFloorLabelCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsFloorLabelRespDTO> cmsFloorLabelRespDTOList =  new ArrayList<CmsFloorLabelRespDTO>();
        List<CmsFloorLabel> cmsFloorLabelList = cmsFloorLabelMapper.selectByExample(cmsFloorLabelCriteria);
        if(CollectionUtils.isNotEmpty(cmsFloorLabelList)){
            for(CmsFloorLabel bo :cmsFloorLabelList){
                CmsFloorLabelRespDTO cmsFloorLabelRespDTO = conversionObject(bo);
                cmsFloorLabelRespDTOList.add(cmsFloorLabelRespDTO);
            }
        }
        
        return cmsFloorLabelRespDTOList;
    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorLabelSV#queryCmsFloorLabelPage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorLabelReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsFloorLabelRespDTO> queryCmsFloorLabelPage(CmsFloorLabelReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsFloorLabelCriteria cmsFloorLabelCriteria = new CmsFloorLabelCriteria();
        com.zengshi.ecp.cms.dao.model.CmsFloorLabelCriteria.Criteria criteria = cmsFloorLabelCriteria.createCriteria();
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
        if (StringUtil.isNotBlank(dto.getLabelName())) {
            criteria.andLabelNameLike("%"+dto.getLabelName()+"%");
        }
        
        cmsFloorLabelCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        cmsFloorLabelCriteria.setLimitClauseCount(dto.getPageSize());
        cmsFloorLabelCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsFloorLabelCriteria,false,new PaginationCallback<CmsFloorLabel, CmsFloorLabelRespDTO>(){

            @Override
            public List<CmsFloorLabel> queryDB(BaseCriteria criteria) {
                return cmsFloorLabelMapper.selectByExample((CmsFloorLabelCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsFloorLabelMapper.countByExample((CmsFloorLabelCriteria)criteria);
            }

            @Override
            public CmsFloorLabelRespDTO warpReturnObject(CmsFloorLabel bo) {
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
    private CmsFloorLabelRespDTO conversionObject(CmsFloorLabel bo){
        CmsFloorLabelRespDTO dto = new CmsFloorLabelRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            CmsPlaceReqDTO cmsPlaceDTO = new CmsPlaceReqDTO();
            cmsPlaceDTO.setId(bo.getPlaceId());
            CmsPlaceRespDTO cmsPlaceRespDTO = cmsPlaceSV.queryCmsPlace(cmsPlaceDTO);
            if(cmsPlaceRespDTO != null){
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
        
        //3.遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        
        return dto;
    }

}
