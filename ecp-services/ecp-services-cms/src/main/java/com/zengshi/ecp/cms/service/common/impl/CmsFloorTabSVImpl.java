package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsFloorTabMapper;
import com.zengshi.ecp.cms.dao.model.CmsFloorTab;
import com.zengshi.ecp.cms.dao.model.CmsFloorTabCriteria;
import com.zengshi.ecp.cms.dao.model.CmsFloorTabCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
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
@Service("cmsFloorTabSV")
public class CmsFloorTabSVImpl extends GeneralSQLSVImpl implements ICmsFloorTabSV {

    @Resource(name = "SEQ_CMS_FLOOR_TAB")
    private PaasSequence seqCmsFloorTab;
    
    @Resource
    private CmsFloorTabMapper cmsFloorTabMapper;
    
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    @Resource
    private ICmsFloorSV cmsFloorSV;


    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTabSV#saveCmsFloorTab(com.zengshi.ecp.cms.dao.model.CmsFloorTab)
     */
    @Override
    public CmsFloorTabRespDTO addCmsFloorTab(CmsFloorTabReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsFloorTab bo = new CmsFloorTab();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsFloorTab.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsFloorTabMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsFloorTabRespDTO respDTO = new CmsFloorTabRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTabSV#updateCmsFloorTab(com.zengshi.ecp.cms.dao.model.CmsFloorTab)
     */
    @Override
    public CmsFloorTabRespDTO updateCmsFloorTab(CmsFloorTabReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsFloorTab bo = new CmsFloorTab();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层页签的原子方法*/
        return this.updateCmsFloorTab(bo);
    }
    
    /** 
     * updateCmsFloorTab:(更新楼层页签的原子方法). <br/> 
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
    public CmsFloorTabRespDTO updateCmsFloorTab(CmsFloorTab bo) throws BusinessException {
        cmsFloorTabMapper.updateByPrimaryKeySelective(bo);
        /*3.将结果返回*/
        CmsFloorTabRespDTO respDTO = new CmsFloorTabRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTabSV#deleteCmsFloorTab(java.lang.Long)
     */
    @Override
    public void deleteCmsFloorTab(String id) throws BusinessException {
        CmsFloorTab bo = new CmsFloorTab();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsFloorTab(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTabSV#deleteCmsFloorTabBatch(java.util.List)
     */
    @Override
    public void deleteCmsFloorTabBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsFloorTab(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTabSV#changeStatusCmsFloorTab(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsFloorTab(String id, String status) throws BusinessException {
        CmsFloorTab bo = new CmsFloorTab();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsFloorTab(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTabSV#changeStatusCmsFloorTabBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsFloorTabBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsFloorTab(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTabSV#queryCmsFloorTab(com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO)
     */
    @Override
    public CmsFloorTabRespDTO queryCmsFloorTab(CmsFloorTabReqDTO dto) throws BusinessException {
        CmsFloorTabRespDTO cmsFloorTabRespDTO = new CmsFloorTabRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsFloorTab bo = cmsFloorTabMapper.selectByPrimaryKey(dto.getId());
            cmsFloorTabRespDTO = conversionObject(bo);
        }
        
        return cmsFloorTabRespDTO;
    }
    
    /**
     * TODO 查询楼层页签列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTabSV#queryCmsFloorTabList(com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO)
     */
    @Override
    public List<CmsFloorTabRespDTO> queryCmsFloorTabList(CmsFloorTabReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsFloorTabCriteria cmsFloorTabCriteria = new CmsFloorTabCriteria();
        Criteria criteria = cmsFloorTabCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
        if (StringUtil.isNotEmpty(dto.getFloorId())) {
            criteria.andFloorIdEqualTo(dto.getFloorId());
        }
        if (StringUtil.isNotBlank(dto.getTabName())) {
            criteria.andTabNameLike("%"+dto.getTabName()+"%");
        }
        if (StringUtil.isNotBlank(dto.getIsLink())) {
            criteria.andIsLinkEqualTo(dto.getIsLink());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        
        cmsFloorTabCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsFloorTabRespDTO> cmsFloorTabRespDTOList =  new ArrayList<CmsFloorTabRespDTO>();
        List<CmsFloorTab> cmsFloorTabList = cmsFloorTabMapper.selectByExample(cmsFloorTabCriteria);
        if(CollectionUtils.isNotEmpty(cmsFloorTabList)){
            for(CmsFloorTab bo :cmsFloorTabList){
                CmsFloorTabRespDTO cmsFloorTabRespDTO = conversionObject(bo);
                cmsFloorTabRespDTOList.add(cmsFloorTabRespDTO);
            }
        }
        
        return cmsFloorTabRespDTOList;
    }


    /** 
     * TODO 查询楼层页签，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorTabSV#queryCmsFloorTabPage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorTabReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsFloorTabRespDTO> queryCmsFloorTabPage(CmsFloorTabReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层页签 */
        CmsFloorTabCriteria cmsFloorTabCriteria = new CmsFloorTabCriteria();
        Criteria criteria = cmsFloorTabCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
        if (StringUtil.isNotEmpty(dto.getFloorId())) {
            criteria.andFloorIdEqualTo(dto.getFloorId());
        }
        if (StringUtil.isNotBlank(dto.getTabName())) {
            criteria.andTabNameLike("%"+dto.getTabName()+"%");
        }
        if (StringUtil.isNotBlank(dto.getIsLink())) {
            criteria.andIsLinkEqualTo(dto.getIsLink());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        
        cmsFloorTabCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        cmsFloorTabCriteria.setLimitClauseCount(dto.getPageSize());
        cmsFloorTabCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsFloorTabCriteria,false,new PaginationCallback<CmsFloorTab, CmsFloorTabRespDTO>(){

            @Override
            public List<CmsFloorTab> queryDB(BaseCriteria criteria) {
                return cmsFloorTabMapper.selectByExample((CmsFloorTabCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsFloorTabMapper.countByExample((CmsFloorTabCriteria)criteria);
            }

            @Override
            public CmsFloorTabRespDTO warpReturnObject(CmsFloorTab bo) {
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
    private CmsFloorTabRespDTO conversionObject(CmsFloorTab bo){
        CmsFloorTabRespDTO dto = new CmsFloorTabRespDTO();
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
            if(cmsFloorRespDTO != null){
                dto.setFloorName(cmsFloorRespDTO.getFloorName());
            }
        }
        
        //3.遍历将编码转中文 
        String isLinkZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.PUBLIC_PARAM_ISNOT, dto.getIsLink());
        dto.setIsLinkZH(isLinkZH);
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        
        return dto;
    }

}
