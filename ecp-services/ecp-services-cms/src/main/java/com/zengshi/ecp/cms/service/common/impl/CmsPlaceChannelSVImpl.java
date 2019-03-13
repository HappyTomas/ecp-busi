package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsPlaceChannelMapper;
import com.zengshi.ecp.cms.dao.model.CmsPlaceChannel;
import com.zengshi.ecp.cms.dao.model.CmsPlaceChannelCriteria;
import com.zengshi.ecp.cms.dao.model.CmsPlaceChannelCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelResDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceChannelRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsChannelSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceChannelSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsTemplateSV;
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
 * Project Name:ecp-services-cms-server <br>
 * Description: <br>
 * Date:2015年11月21日下午1:05:26  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
@Service("cmsPlaceChannelSV")
public class CmsPlaceChannelSVImpl extends GeneralSQLSVImpl implements ICmsPlaceChannelSV{

    @Resource
    private CmsPlaceChannelMapper cmsPlaceChannelMapper;
    
    @Resource(name="SEQ_CMS_PLACE_CHANNEL")
    private PaasSequence seqCmsPlaceChannel;
    
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    @Resource
    private ICmsSiteSV cmsSiteSV;
    
    @Resource
    private ICmsTemplateSV cmsTemplateSV;
    
    @Resource
    private ICmsChannelSV cmsChannelSV;
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceChannelSV#saveCmsPlaceChannel(com.zengshi.ecp.cms.dao.model.CmsPlaceChannel)
     */
    @Override
    public CmsPlaceChannelRespDTO addCmsPlaceChannel(CmsPlaceChannelReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPlaceChannel bo = new CmsPlaceChannel();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsPlaceChannel.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsPlaceChannelMapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsPlaceChannelRespDTO respDTO = new CmsPlaceChannelRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceChannelSV#updateCmsPlaceChannel(com.zengshi.ecp.cms.dao.model.CmsPlaceChannel)
     */
    @Override
    public CmsPlaceChannelRespDTO updateCmsPlaceChannel(CmsPlaceChannelReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPlaceChannel bo = new CmsPlaceChannel();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsPlaceChannel(bo);
    }
    
    /** 
     * updateCmsPlaceChannel:(更新楼层的原子方法). <br/> 
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
    public CmsPlaceChannelRespDTO updateCmsPlaceChannel(CmsPlaceChannel bo) throws BusinessException {
        cmsPlaceChannelMapper.updateByPrimaryKeySelective(bo);
        CmsPlaceChannelRespDTO respDTO = new CmsPlaceChannelRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAdvertiseSV#changeStatusCmsAdvertise(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPlaceChannel(String id, String status) throws BusinessException {
        CmsPlaceChannel bo = new CmsPlaceChannel();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsPlaceChannel(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceChannelSV#changeStatusCmsPlaceChannelBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPlaceChannelBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsPlaceChannel(id, status);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceChannelSV#deleteCmsPlaceChannel(java.lang.Long)
     */
    @Override
    public void deleteCmsPlaceChannel(String id) throws BusinessException {
        CmsPlaceChannel bo = new CmsPlaceChannel();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsPlaceChannel(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceChannelSV#deleteCmsPlaceChannelBatch(java.util.List)
     */
    @Override
    public void deleteCmsPlaceChannelBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsPlaceChannel(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceChannelSV#queryCmsPlaceChannel(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceChannelReqDTO)
     */
    @Override
    public CmsPlaceChannelRespDTO queryCmsPlaceChannel(CmsPlaceChannelReqDTO dto) throws BusinessException {
        CmsPlaceChannelRespDTO CmsPlaceChannelRespDTO = new CmsPlaceChannelRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsPlaceChannel bo = cmsPlaceChannelMapper.selectByPrimaryKey(dto.getId());
            CmsPlaceChannelRespDTO = conversionObject(bo);
        }
        
        return CmsPlaceChannelRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceChannelSV#queryCmsPlaceChannelList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceChannelReqDTO)
     */
    @Override
    public List<CmsPlaceChannelRespDTO> queryCmsPlaceChannelList(CmsPlaceChannelReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsPlaceChannelCriteria cmsPlaceChannelCriteria = new CmsPlaceChannelCriteria();
        Criteria criteria = cmsPlaceChannelCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
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
        if (StringUtil.isNotEmpty(dto.getChannelId())) {
            criteria.andChannelIdEqualTo(dto.getChannelId());
        }
        if (StringUtil.isNotBlank(dto.getStatus())){
        	criteria.andStatusEqualTo(dto.getStatus());
        }
        cmsPlaceChannelCriteria.setOrderByClause(" CREATE_TIME DESC,ID ASC");
        
        /*2.迭代查询结果*/
        List<CmsPlaceChannelRespDTO> cmsPlaceChannelRespDTOList =  new ArrayList<CmsPlaceChannelRespDTO>();
        List<CmsPlaceChannel> cmsPlaceChannelList = cmsPlaceChannelMapper.selectByExample(cmsPlaceChannelCriteria);
        if(CollectionUtils.isNotEmpty(cmsPlaceChannelList)){
            for(CmsPlaceChannel bo :cmsPlaceChannelList){
                CmsPlaceChannelRespDTO CmsPlaceChannelRespDTO = conversionObject(bo);
                cmsPlaceChannelRespDTOList.add(CmsPlaceChannelRespDTO);
            }
        }
        
        return cmsPlaceChannelRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceChannelSV#queryCmsPlaceChannelPage(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceChannelReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsPlaceChannelRespDTO> queryCmsPlaceChannelPage(CmsPlaceChannelReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsPlaceChannelCriteria cmsPlaceChannelCriteria = new CmsPlaceChannelCriteria();
        Criteria criteria = cmsPlaceChannelCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
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
        if (StringUtil.isNotEmpty(dto.getChannelId())) {
            criteria.andChannelIdEqualTo(dto.getChannelId());
        }
        if (StringUtil.isNotBlank(dto.getStatus())){
        	criteria.andStatusEqualTo(dto.getStatus());
        }
        cmsPlaceChannelCriteria.setOrderByClause(" CREATE_TIME DESC,ID ASC");
        cmsPlaceChannelCriteria.setLimitClauseCount(dto.getPageSize());
        cmsPlaceChannelCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsPlaceChannelCriteria,false,new PaginationCallback<CmsPlaceChannel, CmsPlaceChannelRespDTO>(){

            @Override
            public List<CmsPlaceChannel> queryDB(BaseCriteria criteria) {
                return cmsPlaceChannelMapper.selectByExample((CmsPlaceChannelCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsPlaceChannelMapper.countByExample((CmsPlaceChannelCriteria)criteria);
            }

            @Override
            public CmsPlaceChannelRespDTO warpReturnObject(CmsPlaceChannel bo) {
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
    private CmsPlaceChannelRespDTO conversionObject(CmsPlaceChannel bo){
        CmsPlaceChannelRespDTO dto = new CmsPlaceChannelRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        // 1 查询站点服务，获取站点对应的名称
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            CmsSiteReqDTO reqDTO = new CmsSiteReqDTO();
            reqDTO.setId(bo.getSiteId());
            CmsSiteRespDTO respDTO = cmsSiteSV.queryCmsSite(reqDTO);
            if(respDTO != null){
                dto.setSiteName(respDTO.getSiteName());
            }
        }
        
        // 2 查询模板服务，获取模板对应的名称
        if (StringUtil.isNotEmpty(dto.getTemplateId())) {
            CmsTemplateReqDTO reqDTO = new CmsTemplateReqDTO();
            reqDTO.setId(bo.getTemplateId());
            CmsTemplateRespDTO respDTO = cmsTemplateSV.queryCmsTemplate(reqDTO);
            if(respDTO != null){
                dto.setTemplateName(respDTO.getTemplateName());
            }
        }
        
        // 3 查询内容位置服务，获取内容位置对应的名称
        if (StringUtil.isNotEmpty(dto.getPlaceId())) {
            CmsPlaceReqDTO cmsPlaceDTO = new CmsPlaceReqDTO();
            cmsPlaceDTO.setId(bo.getPlaceId());
            CmsPlaceRespDTO respDTO = cmsPlaceSV.queryCmsPlace(cmsPlaceDTO);
            if(respDTO != null){
                dto.setPlaceName(respDTO.getPlaceName());
            }
        }
        
        // 4 查询栏目服务，获取栏目的名称
        if (StringUtil.isNotEmpty(dto.getChannelId())) {
            CmsChannelReqDTO reqDTO = new CmsChannelReqDTO();
            reqDTO.setId(bo.getChannelId());
            CmsChannelResDTO respDTO = cmsChannelSV.find(reqDTO);
            if(respDTO != null){
                dto.setChannelName(respDTO.getChannelName());
            }
        }
        //5  字段翻译汉子
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        return dto;
    }
    
}

