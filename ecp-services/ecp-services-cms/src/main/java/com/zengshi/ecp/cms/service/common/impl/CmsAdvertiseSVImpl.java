package com.zengshi.ecp.cms.service.common.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsAdvertiseMapper;
import com.zengshi.ecp.cms.dao.model.CmsAdvertise;
import com.zengshi.ecp.cms.dao.model.CmsAdvertiseCriteria;
import com.zengshi.ecp.cms.dao.model.CmsAdvertiseCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsAdvertiseSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
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
@Service("cmsAdvertiseSV")
public class CmsAdvertiseSVImpl extends GeneralSQLSVImpl implements ICmsAdvertiseSV {

    @Resource(name = "SEQ_CMS_ADVERTISE")
    private PaasSequence seqCmsAdvertise;
    
    @Resource
    private CmsAdvertiseMapper cmsAdvertiseMapper;
    
    @Resource
    private ICmsPlaceSV cmsPlaceSV;

    @Resource
    private ICmsSiteSV cmsSiteSV;
    
    @Resource
    private ICmsTemplateSV cmsTemplateSV;
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAdvertiseSV#addCmsAdvertise(com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO) 
     */
    @Override
    public CmsAdvertiseRespDTO addCmsAdvertise(CmsAdvertiseReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsAdvertise bo = new CmsAdvertise();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsAdvertise.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsAdvertiseMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsAdvertiseRespDTO respDTO = new CmsAdvertiseRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAdvertiseSV#updateCmsAdvertise(com.zengshi.ecp.cms.dao.model.CmsAdvertise)
     */
    @Override
    public CmsAdvertiseRespDTO updateCmsAdvertise(CmsAdvertiseReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsAdvertise bo = new CmsAdvertise();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新广告的原子方法*/
        return this.updateCmsAdvertise(bo);
    }
    
    /** 
     * updateCmsAdvertise:(更新广告的原子方法). <br/> 
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
    public CmsAdvertiseRespDTO updateCmsAdvertise(CmsAdvertise bo) throws BusinessException {
        cmsAdvertiseMapper.updateByPrimaryKeySelective(bo);
        /*3.将结果返回*/
        CmsAdvertiseRespDTO respDTO = new CmsAdvertiseRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAdvertiseSV#deleteCmsAdvertise(java.lang.Long)
     */
    @Override
    public void deleteCmsAdvertise(String id) throws BusinessException {
        CmsAdvertise bo = new CmsAdvertise();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsAdvertise(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAdvertiseSV#deleteCmsAdvertiseBatch(java.util.List)
     */
    @Override
    public void deleteCmsAdvertiseBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsAdvertise(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAdvertiseSV#changeStatusCmsAdvertise(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsAdvertise(String id, String status) throws BusinessException {
        CmsAdvertise bo = new CmsAdvertise();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsAdvertise(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAdvertiseSV#changeStatusCmsAdvertiseBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsAdvertiseBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsAdvertise(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAdvertiseSV#queryCmsAdvertise(com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO)
     */
    @Override
    public CmsAdvertiseRespDTO queryCmsAdvertise(CmsAdvertiseReqDTO dto) throws BusinessException {
        CmsAdvertiseRespDTO cmsAdvertiseRespDTO = new CmsAdvertiseRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsAdvertise bo = cmsAdvertiseMapper.selectByPrimaryKey(dto.getId());
            cmsAdvertiseRespDTO = conversionObject(bo);
        }
        
        return cmsAdvertiseRespDTO;
    }
    
    /**
     * TODO 查询广告列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAdvertiseSV#queryCmsAdvertiseList(com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO)
     */
    @Override
    public List<CmsAdvertiseRespDTO> queryCmsAdvertiseList(CmsAdvertiseReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsAdvertiseCriteria cmsAdvertiseCriteria = new CmsAdvertiseCriteria();
        Criteria criteria = cmsAdvertiseCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getLinkType())) {
            criteria.andLinkTypeEqualTo(dto.getLinkType());
        }
        if (StringUtil.isNotBlank(dto.getLinkUrl())) {
            criteria.andLinkUrlLike("%" + dto.getLinkUrl() + "%");
        }
        if (StringUtil.isNotBlank(dto.getAdvertiseTitle())) {
            criteria.andAdvertiseTitleLike("%" + dto.getAdvertiseTitle() + "%");
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtil.isNotBlank(dto.getSubSystem())) {
            criteria.andSubSystemEqualTo(dto.getSubSystem());
        }
        if (StringUtil.isNotEmpty(dto.getShopId())) {
            criteria.andShopIdEqualTo(dto.getShopId());
        }
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if (StringUtil.isNotEmpty(dto.getTemplateId())) {
            criteria.andTemplateIdEqualTo(dto.getTemplateId());
        }
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
        //发布时间
        if(StringUtil.isNotEmpty(dto.getStartPubTime())&&StringUtil.isNotEmpty(dto.getEndPubTime())){
            String startPubTimeStr = DateUtil.getDateString(dto.getStartPubTime(), "yyyy-MM-dd")+" 00:00:00";
            String endPubTimeStr = DateUtil.getDateString(dto.getEndPubTime(), "yyyy-MM-dd")+" 23:59:59";
            criteria.andPubTimeBetween(Timestamp.valueOf(startPubTimeStr), Timestamp.valueOf(endPubTimeStr));
        }else if(StringUtil.isNotEmpty(dto.getStartPubTime())){
            String startPubTimeStr = DateUtil.getDateString(dto.getStartPubTime(), "yyyy-MM-dd")+" 00:00:00";
            criteria.andPubTimeGreaterThanOrEqualTo(Timestamp.valueOf(startPubTimeStr)); 
        }else if(StringUtil.isNotEmpty(dto.getEndPubTime())) {
            String endPubTimeStr = DateUtil.getDateString(dto.getEndPubTime(), "yyyy-MM-dd")+" 23:59:59";
            criteria.andPubTimeLessThanOrEqualTo(Timestamp.valueOf(endPubTimeStr));
        }
        //失效时间    
        if(StringUtil.isNotEmpty(dto.getStartLostTime())&&StringUtil.isNotEmpty(dto.getEndLostTime())){
            String startLostTimeStr = DateUtil.getDateString(dto.getStartLostTime(), "yyyy-MM-dd")+" 00:00:00";
            String endLostTimeStr = DateUtil.getDateString(dto.getEndLostTime(), "yyyy-MM-dd")+" 23:59:59";
            criteria.andLostTimeBetween(Timestamp.valueOf(startLostTimeStr), Timestamp.valueOf(endLostTimeStr));
        }else if (StringUtil.isNotEmpty(dto.getStartLostTime())) {
            String startLostTimeStr = DateUtil.getDateString(dto.getStartLostTime(), "yyyy-MM-dd")+" 00:00:00";
            criteria.andLostTimeGreaterThanOrEqualTo(Timestamp.valueOf(startLostTimeStr));
        }else if (StringUtil.isNotEmpty(dto.getEndLostTime())) {
            String endLostTimeStr = DateUtil.getDateString(dto.getEndLostTime(), "yyyy-MM-dd")+" 23:59:59";
            criteria.andLostTimeLessThanOrEqualTo(Timestamp.valueOf(endLostTimeStr));
        }
        if(StringUtil.isNotEmpty(dto.getThisTime())){
            criteria.andPubTimeLessThanOrEqualTo(dto.getThisTime()); 
            criteria.andLostTimeGreaterThanOrEqualTo(dto.getThisTime());
        }
        if (StringUtil.isNotEmpty(dto.getPlatformType())) {
            criteria.andPlatformTypeEqualTo(dto.getPlatformType());
        }
        cmsAdvertiseCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsAdvertiseRespDTO> cmsAdvertiseRespDTOList =  new ArrayList<CmsAdvertiseRespDTO>();
        List<CmsAdvertise> cmsAdvertiseList = cmsAdvertiseMapper.selectByExample(cmsAdvertiseCriteria);
        if(CollectionUtils.isNotEmpty(cmsAdvertiseList)){
            for(CmsAdvertise bo:cmsAdvertiseList){
                CmsAdvertiseRespDTO cmsAdvertiseRespDTO = conversionObject(bo);
                cmsAdvertiseRespDTOList.add(cmsAdvertiseRespDTO);
            }
        }
        return cmsAdvertiseRespDTOList;
    }


    /** 
     * TODO 查询广告，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAdvertiseSV#queryCmsAdvertisePage(com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsAdvertiseRespDTO> queryCmsAdvertisePage(CmsAdvertiseReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索广告 */
        CmsAdvertiseCriteria cmsAdvertiseCriteria = new CmsAdvertiseCriteria();
        Criteria criteria = cmsAdvertiseCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getLinkType())) {
            criteria.andLinkTypeEqualTo(dto.getLinkType());
        }
        if (StringUtil.isNotBlank(dto.getLinkUrl())) {
            criteria.andLinkUrlLike("%" + dto.getLinkUrl() + "%");
        }
        if (StringUtil.isNotBlank(dto.getAdvertiseTitle())) {
            criteria.andAdvertiseTitleLike("%" + dto.getAdvertiseTitle() + "%");
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        if (StringUtil.isNotBlank(dto.getSubSystem())) {
            criteria.andSubSystemEqualTo(dto.getSubSystem());
        }
        if (StringUtil.isNotEmpty(dto.getShopId())) {
            criteria.andShopIdEqualTo(dto.getShopId());
        }
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if (StringUtil.isNotEmpty(dto.getTemplateId())) {
            criteria.andTemplateIdEqualTo(dto.getTemplateId());
        }
        //发布时间
        if(StringUtil.isNotEmpty(dto.getStartPubTime())&&StringUtil.isNotEmpty(dto.getEndPubTime())){
            String startPubTimeStr = DateUtil.getDateString(dto.getStartPubTime(), "yyyy-MM-dd")+" 00:00:00";
            String endPubTimeStr = DateUtil.getDateString(dto.getEndPubTime(), "yyyy-MM-dd")+" 23:59:59";
            criteria.andPubTimeBetween(Timestamp.valueOf(startPubTimeStr), Timestamp.valueOf(endPubTimeStr));
        }else if(StringUtil.isNotEmpty(dto.getStartPubTime())){
            String startPubTimeStr = DateUtil.getDateString(dto.getStartPubTime(), "yyyy-MM-dd")+" 00:00:00";
            criteria.andPubTimeGreaterThanOrEqualTo(Timestamp.valueOf(startPubTimeStr)); 
        }else if(StringUtil.isNotEmpty(dto.getEndPubTime())) {
            String endPubTimeStr = DateUtil.getDateString(dto.getEndPubTime(), "yyyy-MM-dd")+" 23:59:59";
            criteria.andPubTimeLessThanOrEqualTo(Timestamp.valueOf(endPubTimeStr));
        }
        //失效时间    
        if(StringUtil.isNotEmpty(dto.getStartLostTime())&&StringUtil.isNotEmpty(dto.getEndLostTime())){
            String startLostTimeStr = DateUtil.getDateString(dto.getStartLostTime(), "yyyy-MM-dd")+" 00:00:00";
            String endLostTimeStr = DateUtil.getDateString(dto.getEndLostTime(), "yyyy-MM-dd")+" 23:59:59";
            criteria.andLostTimeBetween(Timestamp.valueOf(startLostTimeStr), Timestamp.valueOf(endLostTimeStr));
        }else if (StringUtil.isNotEmpty(dto.getStartLostTime())) {
            String startLostTimeStr = DateUtil.getDateString(dto.getStartLostTime(), "yyyy-MM-dd")+" 00:00:00";
            criteria.andLostTimeGreaterThanOrEqualTo(Timestamp.valueOf(startLostTimeStr));
        }else if (StringUtil.isNotEmpty(dto.getEndLostTime())) {
            String endLostTimeStr = DateUtil.getDateString(dto.getEndLostTime(), "yyyy-MM-dd")+" 23:59:59";
            criteria.andLostTimeLessThanOrEqualTo(Timestamp.valueOf(endLostTimeStr));
        }
        if(StringUtil.isNotEmpty(dto.getThisTime())){
            criteria.andPubTimeLessThanOrEqualTo(dto.getThisTime()); 
            criteria.andLostTimeGreaterThanOrEqualTo(dto.getThisTime());
        }
        if (StringUtil.isNotEmpty(dto.getPlatformType())) {
            criteria.andPlatformTypeEqualTo(dto.getPlatformType());
        }
        cmsAdvertiseCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        cmsAdvertiseCriteria.setLimitClauseCount(dto.getPageSize());
        cmsAdvertiseCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsAdvertiseCriteria,false,new PaginationCallback<CmsAdvertise, CmsAdvertiseRespDTO>(){

            @Override
            public List<CmsAdvertise> queryDB(BaseCriteria criteria) {
                return cmsAdvertiseMapper.selectByExample((CmsAdvertiseCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsAdvertiseMapper.countByExample((CmsAdvertiseCriteria)criteria);
            }

            @Override
            public CmsAdvertiseRespDTO warpReturnObject(CmsAdvertise bo) {
                return  conversionObject(bo);
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
    private CmsAdvertiseRespDTO conversionObject(CmsAdvertise bo){
        CmsAdvertiseRespDTO dto = new CmsAdvertiseRespDTO();
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
        // 2 查询站点服务，获取站点对应的名称
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            CmsSiteReqDTO reqDTO = new CmsSiteReqDTO();
            reqDTO.setId(bo.getSiteId());
            CmsSiteRespDTO respDTO = cmsSiteSV.queryCmsSite(reqDTO);
            if(respDTO != null){
                dto.setSiteName(respDTO.getSiteName());
            }
        }
        
        // 3 查询模板服务，获取模板对应的名称
        if (StringUtil.isNotEmpty(dto.getTemplateId())) {
            CmsTemplateReqDTO reqDTO = new CmsTemplateReqDTO();
            reqDTO.setId(bo.getTemplateId());
            CmsTemplateRespDTO respDTO = cmsTemplateSV.queryCmsTemplate(reqDTO);
            if(respDTO != null){
                dto.setTemplateName(respDTO.getTemplateName());
            }
        }
        
        //4.遍历将编码转中文 
        String linkTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_ADVERTISE_LINK_TYPE, dto.getLinkType());
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        String subSystemZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.SYS_SUB_SYSTEM, dto.getSubSystem());
        String platformTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_PLATFORM_TYPE, dto.getPlatformType());
        dto.setLinkTypeZH(linkTypeZH);
        dto.setStatusZH(statusZH);
        dto.setSubSystemZH(subSystemZH);
        dto.setPlatformTypeZH(platformTypeZH);
        
        return dto;
    }

}
