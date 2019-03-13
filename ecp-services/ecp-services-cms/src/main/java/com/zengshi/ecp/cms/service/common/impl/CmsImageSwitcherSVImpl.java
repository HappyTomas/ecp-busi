package com.zengshi.ecp.cms.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dao.mapper.common.CmsImageSwitcherMapper;
import com.zengshi.ecp.cms.dao.model.CmsImageSwitcher;
import com.zengshi.ecp.cms.dao.model.CmsImageSwitcherCriteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsImageSwitcherReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsImageSwitcherRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsImageSwitcherSV;
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

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-cms-server <br>
 * Description: <br>
 * Date:2016年3月8日上午10:13:04  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public class CmsImageSwitcherSVImpl extends GeneralSQLSVImpl implements ICmsImageSwitcherSV {
    
    @Resource(name = "SEQ_CMS_IMAGE_SWITCHER")
    private PaasSequence seq;
    
    @Resource
    private CmsImageSwitcherMapper cmsImageSwitcherMapper;
    
    @Resource
    private ICmsPlaceSV cmsPlaceSV;
    
    @Resource
    private ICmsSiteSV cmsSiteSV;
    
    @Resource
    private ICmsTemplateSV cmsTemplateSV;
    

    @Override
    public PageResponseDTO<CmsImageSwitcherRespDTO> queryCmsImageSwitcherPage(
            CmsImageSwitcherReqDTO dto) throws BusinessException {
        
        CmsImageSwitcherCriteria c = new CmsImageSwitcherCriteria();
        com.zengshi.ecp.cms.dao.model.CmsImageSwitcherCriteria.Criteria criteria = c.createCriteria();
        
        if(null!=dto.getSiteId()&&0!=dto.getSiteId()){
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if(null!=dto.getPlaceId()&&0!=dto.getPlaceId()){
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
        if(null!=dto.getTemplateId()&&0!=dto.getTemplateId()){
            criteria.andTemplateIdEqualTo(dto.getTemplateId());
        }
        
        if(StringUtil.isNotBlank(dto.getImName())){
            criteria.andImNameLike("%"+dto.getImName()+"%");
        }
        
        if(StringUtil.isNotBlank(dto.getStatus())){
            criteria.andStatusEqualTo(dto.getStatus());
        }
      
        c.setOrderByClause("SORT_NO ASC,CREATE_TIME DESC");
        c.setLimitClauseCount(dto.getPageSize());
        c.setLimitClauseStart(dto.getStartRowIndex());
        
        
        return super.queryByPagination(dto, c, false, new PaginationCallback<CmsImageSwitcher, CmsImageSwitcherRespDTO>() {

            @Override
            public List<CmsImageSwitcher> queryDB(BaseCriteria criteria) {
                return cmsImageSwitcherMapper.selectByExample((CmsImageSwitcherCriteria) criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsImageSwitcherMapper.countByExample((CmsImageSwitcherCriteria)criteria);
            }

            @Override
            public CmsImageSwitcherRespDTO warpReturnObject(CmsImageSwitcher bo) {
                CmsImageSwitcherRespDTO dto = new CmsImageSwitcherRespDTO();
                if(bo != null){
                    ObjectCopyUtil.copyObjValue(bo, dto, null, false);
                }
                toStringName(dto,bo);
                String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
                dto.setStatusZH(statusZH);
                return dto;
            }
            
        });
    }


    @Override
    public void saveCmsImageSwitcher(CmsImageSwitcherReqDTO dto) throws BusinessException {
        if(null==dto.getId()||dto.getId()==0){
        CmsImageSwitcher cmsImageSwitcher = new CmsImageSwitcher();
        ObjectCopyUtil.copyObjValue(dto, cmsImageSwitcher, null, false);
        cmsImageSwitcher.setCreateStaff(dto.getStaff().getId());
        cmsImageSwitcher.setCreateTime(DateUtil.getSysDate());
        cmsImageSwitcher.setId(seq.nextValue());
        if(null==cmsImageSwitcher.getSortNo()||0==cmsImageSwitcher.getSortNo()){
        	cmsImageSwitcher.setSortNo(1);
        }
        cmsImageSwitcherMapper.insertSelective(cmsImageSwitcher);
        }else{
            updateCmsImageSwitcher(dto);
        }
    }


    @Override
    public void updateCmsImageSwitcher(CmsImageSwitcherReqDTO dto) throws BusinessException {
        
        CmsImageSwitcher cmsImageSwitcher = new CmsImageSwitcher();
        ObjectCopyUtil.copyObjValue(dto, cmsImageSwitcher, null, false);
        cmsImageSwitcher.setUpdateStaff(dto.getStaff().getId());
        cmsImageSwitcher.setUpdateTime(DateUtil.getSysDate());
        cmsImageSwitcherMapper.updateByPrimaryKeySelective(cmsImageSwitcher);
    }


    @Override
    public CmsImageSwitcherRespDTO selectCmsImageSwitcher(CmsImageSwitcherReqDTO dto)
            throws BusinessException {
        
        CmsImageSwitcher cmsImageSwitcher = new CmsImageSwitcher();
        cmsImageSwitcher = cmsImageSwitcherMapper.selectByPrimaryKey(dto.getId());
        CmsImageSwitcherRespDTO cmsImageSwitcherRespDTO = new CmsImageSwitcherRespDTO();
        ObjectCopyUtil.copyObjValue(cmsImageSwitcher, cmsImageSwitcherRespDTO, null, false);
        toStringName(cmsImageSwitcherRespDTO,cmsImageSwitcher);
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, cmsImageSwitcherRespDTO.getStatus());
        cmsImageSwitcherRespDTO.setStatusZH(statusZH);
        return cmsImageSwitcherRespDTO;
    }

    public void toStringName(CmsImageSwitcherRespDTO dto,CmsImageSwitcher bo){
        // 1 查询内容位置服务，获取内容位置对应的名称
        if (dto.getPlaceId()!=null) {
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
        
    }

}
