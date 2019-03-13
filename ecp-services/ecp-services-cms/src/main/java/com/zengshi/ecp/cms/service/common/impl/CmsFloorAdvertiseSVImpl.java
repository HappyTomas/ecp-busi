package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsFloorAdvertiseMapper;
import com.zengshi.ecp.cms.dao.model.CmsFloorAdvertise;
import com.zengshi.ecp.cms.dao.model.CmsFloorAdvertiseCriteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAdvertiseSV;
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
@Service("cmsFloorAdvertiseSV")
public class CmsFloorAdvertiseSVImpl extends GeneralSQLSVImpl implements ICmsFloorAdvertiseSV {

    @Resource(name = "SEQ_CMS_FLOOR_ADVERTISE")
    private PaasSequence seqCmsFloorAdvertise;
    
    @Resource
    private CmsFloorAdvertiseMapper cmsFloorAdvertiseMapper;
    
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    @Resource
    private ICmsFloorSV cmsFloorSV;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAdvertiseSV#saveCmsFloorAdvertise(com.zengshi.ecp.cms.dao.model.CmsFloorAdvertise)
     */
    @Override
    public CmsFloorAdvertiseRespDTO addCmsFloorAdvertise(CmsFloorAdvertiseReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsFloorAdvertise bo = new CmsFloorAdvertise();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsFloorAdvertise.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsFloorAdvertiseMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsFloorAdvertiseRespDTO respDTO = new CmsFloorAdvertiseRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAdvertiseSV#updateCmsFloorAdvertise(com.zengshi.ecp.cms.dao.model.CmsFloorAdvertise)
     */
    @Override
    public CmsFloorAdvertiseRespDTO updateCmsFloorAdvertise(CmsFloorAdvertiseReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsFloorAdvertise bo = new CmsFloorAdvertise();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层属性数量的原子方法*/
        return this.updateCmsFloorAdvertise(bo);
    }
    
    /** 
     * updateCmsFloorAdvertise:(更新楼层属性数量的原子方法). <br/> 
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
    public CmsFloorAdvertiseRespDTO updateCmsFloorAdvertise(CmsFloorAdvertise bo) throws BusinessException {
        cmsFloorAdvertiseMapper.updateByPrimaryKeySelective(bo);
        /*3.将结果返回*/
        CmsFloorAdvertiseRespDTO respDTO = new CmsFloorAdvertiseRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAdvertiseSV#deleteCmsFloorAdvertise(java.lang.Long)
     */
    @Override
    public void deleteCmsFloorAdvertise(String id) throws BusinessException {
        CmsFloorAdvertise bo = new CmsFloorAdvertise();
        bo.setId(Long.valueOf(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsFloorAdvertise(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAdvertiseSV#deleteCmsFloorAdvertiseBatch(java.util.List)
     */
    @Override
    public void deleteCmsFloorAdvertiseBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsFloorAdvertise(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAdvertiseSV#changeStatusCmsFloorAdvertise(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsFloorAdvertise(String id, String status) throws BusinessException {
        CmsFloorAdvertise bo = new CmsFloorAdvertise();
        bo.setId(Long.valueOf(id));
        bo.setStatus(status);
        this.updateCmsFloorAdvertise(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAdvertiseSV#changeStatusCmsFloorAdvertiseBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsFloorAdvertiseBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsFloorAdvertise(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAdvertiseSV#queryCmsFloorAdvertise(com.zengshi.ecp.cms.dubbo.dto.CmsFloorAdvertiseReqDTO)
     */
    @Override
    public CmsFloorAdvertiseRespDTO queryCmsFloorAdvertise(CmsFloorAdvertiseReqDTO dto) throws BusinessException {
        CmsFloorAdvertiseRespDTO cmsFloorAdvertiseRespDTO = new CmsFloorAdvertiseRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsFloorAdvertise bo = cmsFloorAdvertiseMapper.selectByPrimaryKey(dto.getId());
            cmsFloorAdvertiseRespDTO = conversionObject(bo);
        }
        
        return cmsFloorAdvertiseRespDTO;
    }
    
    /**
     * TODO 查询楼层属性数量列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAdvertiseSV#queryCmsFloorAdvertiseList(com.zengshi.ecp.cms.dubbo.dto.CmsFloorAdvertiseReqDTO)
     */
    @Override
    public List<CmsFloorAdvertiseRespDTO> queryCmsFloorAdvertiseList(CmsFloorAdvertiseReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsFloorAdvertiseCriteria cmsFloorAdvertiseCriteria = new CmsFloorAdvertiseCriteria();
        com.zengshi.ecp.cms.dao.model.CmsFloorAdvertiseCriteria.Criteria criteria = cmsFloorAdvertiseCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
        if (dto.getFloorId() != null) {
            criteria.andFloorIdEqualTo(dto.getFloorId());
        }
        if (StringUtil.isNotBlank(dto.getAdvertiseTitle())) {
            criteria.andAdvertiseTitleLike("%"+dto.getAdvertiseTitle()+"%");
        }
        cmsFloorAdvertiseCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsFloorAdvertiseRespDTO> cmsFloorAdvertiseRespDTOList =  new ArrayList<CmsFloorAdvertiseRespDTO>();
        List<CmsFloorAdvertise> cmsFloorAdvertiseList = cmsFloorAdvertiseMapper.selectByExample(cmsFloorAdvertiseCriteria);
        if(CollectionUtils.isNotEmpty(cmsFloorAdvertiseList)){
            for(CmsFloorAdvertise bo :cmsFloorAdvertiseList){
                CmsFloorAdvertiseRespDTO cmsFloorAdvertiseRespDTO = conversionObject(bo);
                cmsFloorAdvertiseRespDTOList.add(cmsFloorAdvertiseRespDTO);
            }
        }
        
        return cmsFloorAdvertiseRespDTOList;
    }


    /** 
     * TODO 查询楼层属性数量，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorAdvertiseSV#queryCmsFloorAdvertisePage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorAdvertiseReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsFloorAdvertiseRespDTO> queryCmsFloorAdvertisePage(CmsFloorAdvertiseReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层属性数量 */
        CmsFloorAdvertiseCriteria cmsFloorAdvertiseCriteria = new CmsFloorAdvertiseCriteria();
        com.zengshi.ecp.cms.dao.model.CmsFloorAdvertiseCriteria.Criteria criteria = cmsFloorAdvertiseCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
        if (StringUtil.isNotEmpty(dto.getFloorId())) {
            criteria.andFloorIdEqualTo(dto.getFloorId());
        }
        if (StringUtil.isNotBlank(dto.getAdvertiseTitle())) {
            criteria.andAdvertiseTitleLike("%"+dto.getAdvertiseTitle()+"%");
        }
        
        cmsFloorAdvertiseCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        cmsFloorAdvertiseCriteria.setLimitClauseCount(dto.getPageSize());
        cmsFloorAdvertiseCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsFloorAdvertiseCriteria,false,new PaginationCallback<CmsFloorAdvertise, CmsFloorAdvertiseRespDTO>(){

            @Override
            public List<CmsFloorAdvertise> queryDB(BaseCriteria criteria) {
                return cmsFloorAdvertiseMapper.selectByExample((CmsFloorAdvertiseCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsFloorAdvertiseMapper.countByExample((CmsFloorAdvertiseCriteria)criteria);
            }

            @Override
            public CmsFloorAdvertiseRespDTO warpReturnObject(CmsFloorAdvertise bo) {
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
    private CmsFloorAdvertiseRespDTO conversionObject(CmsFloorAdvertise bo){
        CmsFloorAdvertiseRespDTO dto = new CmsFloorAdvertiseRespDTO();
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
        if (dto.getFloorId() != null) {
            CmsFloorReqDTO cmsFloorDTO = new CmsFloorReqDTO();
            cmsFloorDTO.setId(dto.getFloorId());
            CmsFloorRespDTO cmsFloorRespDTO = cmsFloorSV.queryCmsFloor(cmsFloorDTO);
            if(cmsFloorRespDTO != null){
                dto.setFloorName(cmsFloorRespDTO.getFloorName());
            }
        }
        
        //3.遍历将编码转中文 
        String linkTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_ADVERTISE_LINK_TYPE, dto.getLinkType());
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setLinkTypeZH(linkTypeZH);
        dto.setStatusZH(statusZH);
        return dto;
    }

}
