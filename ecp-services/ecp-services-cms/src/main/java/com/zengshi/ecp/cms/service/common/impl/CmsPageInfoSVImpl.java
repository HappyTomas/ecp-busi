package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsPageInfoMapper;
import com.zengshi.ecp.cms.dao.model.CmsPageInfo;
import com.zengshi.ecp.cms.dao.model.CmsPageInfoCriteria;
import com.zengshi.ecp.cms.dao.model.CmsPageInfoCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutPreSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsLayoutTypeSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPreSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageInfoSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsSiteSV;
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
@Service("cmsPageInfoSV")
public class CmsPageInfoSVImpl extends GeneralSQLSVImpl implements ICmsPageInfoSV {
	
	private String SITE_URL_FRONT="/modularcommon?pageId=";//页面路径前缀

    @Resource(name = "SEQ_CMS_PAGE_INFO")
    private PaasSequence seqCmsPageInfo;
    @Resource
    private CmsPageInfoMapper cmsPageInfoMapper;
    @Resource
    private ICmsPageAttrPreSV cmsPageAttrPreSV;
    @Resource
    private ICmsLayoutPreSV cmsLayoutPreSV;
    @Resource
    private ICmsLayoutTypeSV cmsLayoutTypeSV;
    @Resource
    private ICmsSiteSV sv;
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageInfoSV#saveCmsPageInfo(com.zengshi.ecp.cms.dao.model.CmsPageInfo)
     */
    @Override
    public CmsPageInfoRespDTO addCmsPageInfo(CmsPageInfoReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPageInfo bo = new CmsPageInfo();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsPageInfo.nextValue();
        bo.setId(id);
        bo.setSiteUrl(SITE_URL_FRONT+id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsPageInfoMapper.insertSelective(bo);
        
        /*3.调用页面属性插入方法*/
        CmsPageAttrPreReqDTO pageAttrPreReqDTO = new CmsPageAttrPreReqDTO();
        pageAttrPreReqDTO.setPageId(id);
        pageAttrPreReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
        pageAttrPreReqDTO.setMatchingColour("0");//初始化为“默认色”
        pageAttrPreReqDTO.setBackgroupShowType(CmsConstants.BackgroupShowType.CMS_BACKGROUPSHOWTYPE_01);
        pageAttrPreReqDTO.setShowBackFlag(CmsConstants.IsNot.CMS_ISNOT_0);
        cmsPageAttrPreSV.addCmsPageAttrPre(pageAttrPreReqDTO);
        
        /*4.如果平台类型为移动端，则自动生成布局*/
        if(CmsConstants.PlatformType.CMS_PLATFORMTYPE_03.equals(dto.getPlatformType())){
            //根据页面类型获取该页面类型下的布局类型
            CmsLayoutTypeReqDTO layoutTypeReqDTO = new CmsLayoutTypeReqDTO();
            layoutTypeReqDTO.setPageTypeId(dto.getPageTypeId());
            List<CmsLayoutTypeRespDTO> layoutTypeRespDTOList = cmsLayoutTypeSV.queryCmsLayoutTypeList(layoutTypeReqDTO);
            if(CollectionUtils.isNotEmpty(layoutTypeRespDTOList) && layoutTypeRespDTOList.get(0) != null){
                CmsLayoutPreReqDTO layoutPreReqDTO = new CmsLayoutPreReqDTO();
                layoutPreReqDTO.setPageId(id);
                layoutPreReqDTO.setLayoutTypeId(layoutTypeRespDTOList.get(0).getId());
                layoutPreReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                layoutPreReqDTO.setShowOrder(1);
                cmsLayoutPreSV.addCmsLayoutPre(layoutPreReqDTO);
            }
        }
        
        /*5.将结果返回*/
        CmsPageInfoRespDTO respDTO = new CmsPageInfoRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageInfoSV#updateCmsPageInfo(com.zengshi.ecp.cms.dao.model.CmsPageInfo)
     */
    @Override
    public CmsPageInfoRespDTO updateCmsPageInfo(CmsPageInfoReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPageInfo bo = new CmsPageInfo();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsPageInfo(bo);
    }
    
    /** 
     * updateCmsPageInfo:(更新楼层的原子方法). <br/> 
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
    public CmsPageInfoRespDTO updateCmsPageInfo(CmsPageInfo bo) throws BusinessException {
        cmsPageInfoMapper.updateByPrimaryKeySelective(bo);
        CmsPageInfoRespDTO respDTO = new CmsPageInfoRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageInfoSV#deleteCmsPageInfo(java.lang.Long)
     */
    @Override
    public void deleteCmsPageInfo(String id) throws BusinessException {
        CmsPageInfo bo = new CmsPageInfo();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsPageInfo(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageInfoSV#deleteCmsPageInfoBatch(java.util.List)
     */
    @Override
    public void deleteCmsPageInfoBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsPageInfo(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageInfoSV#changeStatusCmsPageInfo(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPageInfo(String id, String status) throws BusinessException {
        CmsPageInfo bo = new CmsPageInfo();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsPageInfo(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageInfoSV#changeStatusCmsPageInfoBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPageInfoBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsPageInfo(id, status);
            }
        }
    }
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageInfoSV#changeStatusCmsPageInfo(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void doDefaultCmsPageInfo(String id, String isdefault) throws BusinessException {
        CmsPageInfo bo = new CmsPageInfo();
        bo.setId(Long.parseLong(id));
        this.updateCmsPageInfo(bo);
    }
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageInfoSV#changeStatusCmsPageInfoBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void doDefaultCmsPageInfoBatch(List<String> list, String isdefault)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.doDefaultCmsPageInfo(id, isdefault);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageInfoSV#queryCmsPageInfo(com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO)
     */
    @Override
    public CmsPageInfoRespDTO queryCmsPageInfo(CmsPageInfoReqDTO dto) throws BusinessException {
        CmsPageInfoRespDTO cmsPageInfoRespDTO = new CmsPageInfoRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsPageInfo bo = cmsPageInfoMapper.selectByPrimaryKey(dto.getId());
            cmsPageInfoRespDTO = conversionObject(bo);
        }
        
        return cmsPageInfoRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageInfoSV#queryCmsPageInfoList(com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO)
     */
    @Override
    public List<CmsPageInfoRespDTO> queryCmsPageInfoList(CmsPageInfoReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsPageInfoCriteria cmsPageInfoCriteria = new CmsPageInfoCriteria();
        Criteria criteria = cmsPageInfoCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if (StringUtil.isNotEmpty(dto.getShopId())) {
            criteria.andShopIdEqualTo(dto.getShopId());
        }
        if (StringUtil.isNotEmpty(dto.getPageTypeId())) {
            criteria.andPageTypeIdEqualTo(dto.getPageTypeId());
        }
        if (StringUtil.isNotEmpty(dto.getIsUseTemplate())) {
            criteria.andIsUseTemplateEqualTo(dto.getIsUseTemplate());
        }
        if (StringUtil.isNotBlank(dto.getPageName())) {
            criteria.andPageNameLike("%"+dto.getPageName()+"%");
        }
        //---------状态集合查询  begin----------//
        if (dto.getStatusSet() == null) {
            dto.setStatusSet(new HashSet<String>());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            dto.getStatusSet().add(dto.getStatus());
        }
        if (CollectionUtils.isNotEmpty(dto.getStatusSet())) {
            criteria.andStatusIn(new ArrayList<String>(dto.getStatusSet()));
        }
        //---------状态集合查询 end----------//
        if (StringUtil.isNotEmpty(dto.getPageTypeId())) {
            criteria.andPageTypeIdEqualTo(dto.getPageTypeId());
        }
        if (StringUtil.isNotBlank(dto.getPlatformType())) {
            criteria.andPlatformTypeEqualTo(dto.getPlatformType());
        }
        cmsPageInfoCriteria.setOrderByClause("CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsPageInfoRespDTO> cmsPageInfoRespDTOList =  new ArrayList<CmsPageInfoRespDTO>();
        List<CmsPageInfo> cmsPageInfoList = cmsPageInfoMapper.selectByExample(cmsPageInfoCriteria);
        if(CollectionUtils.isNotEmpty(cmsPageInfoList)){
            for(CmsPageInfo bo :cmsPageInfoList){
                CmsPageInfoRespDTO cmsPageInfoRespDTO = conversionObject(bo);
                cmsPageInfoRespDTOList.add(cmsPageInfoRespDTO);
            }
        }
        
        return cmsPageInfoRespDTOList;

    }

    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageInfoSV#queryCmsPageInfoPage(com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsPageInfoRespDTO> queryCmsPageInfoPage(CmsPageInfoReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsPageInfoCriteria cmsPageInfoCriteria = new CmsPageInfoCriteria();
        Criteria criteria = cmsPageInfoCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getSiteId())) {
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if (StringUtil.isNotEmpty(dto.getPageTypeId())) {
            criteria.andPageTypeIdEqualTo(dto.getPageTypeId());
        }
        if (StringUtil.isNotEmpty(dto.getShopId())) {
            criteria.andShopIdEqualTo(dto.getShopId());
        }
        if (StringUtil.isNotEmpty(dto.getIsUseTemplate())) {
            criteria.andIsUseTemplateEqualTo(dto.getIsUseTemplate());
        }
        if (StringUtil.isNotBlank(dto.getPageName())) {
            criteria.andPageNameLike("%"+dto.getPageName()+"%");
        }
        //---------状态集合查询  begin----------//
        if (dto.getStatusSet() == null) {
            dto.setStatusSet(new HashSet<String>());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            dto.getStatusSet().add(dto.getStatus());
        }
        if (CollectionUtils.isNotEmpty(dto.getStatusSet())) {
            criteria.andStatusIn(new ArrayList<String>(dto.getStatusSet()));
        }
        //---------状态集合查询 end----------//
        if (StringUtil.isNotEmpty(dto.getPageTypeId())) {
            criteria.andPageTypeIdEqualTo(dto.getPageTypeId());
        }
        if (StringUtil.isNotBlank(dto.getPlatformType())) {
            criteria.andPlatformTypeEqualTo(dto.getPlatformType());
        }
        cmsPageInfoCriteria.setOrderByClause("CREATE_TIME DESC");
        cmsPageInfoCriteria.setLimitClauseCount(dto.getPageSize());
        cmsPageInfoCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsPageInfoCriteria,false,new PaginationCallback<CmsPageInfo, CmsPageInfoRespDTO>(){

            @Override
            public List<CmsPageInfo> queryDB(BaseCriteria criteria) {
                return cmsPageInfoMapper.selectByExample((CmsPageInfoCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsPageInfoMapper.countByExample((CmsPageInfoCriteria)criteria);
            }

            @Override
            public CmsPageInfoRespDTO warpReturnObject(CmsPageInfo bo) {
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
    private CmsPageInfoRespDTO conversionObject(CmsPageInfo bo){
        CmsPageInfoRespDTO dto = new CmsPageInfoRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        CmsSiteReqDTO siteReqDTO = new CmsSiteReqDTO();
        siteReqDTO.setId(dto.getSiteId());
        CmsSiteRespDTO siteRespDTO= sv.queryCmsSite(siteReqDTO);
        dto.setSiteName(siteRespDTO.getSiteName());
        // 2.遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String isUseTemplateZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.PUBLIC_PARAM_ISNOT, dto.getIsUseTemplate());
        dto.setIsUseTemplateZH(isUseTemplateZH);
        String platformTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_PLATFORM_TYPE, dto.getPlatformType());
        dto.setPlatformTypeZH(platformTypeZH);
        
        return dto;
    }

}
