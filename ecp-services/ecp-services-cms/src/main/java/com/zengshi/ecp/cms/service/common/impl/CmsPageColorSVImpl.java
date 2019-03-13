package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dao.mapper.common.CmsPageColorMapper;
import com.zengshi.ecp.cms.dao.model.CmsPageColor;
import com.zengshi.ecp.cms.dao.model.CmsPageColorCriteria;
import com.zengshi.ecp.cms.dao.model.CmsPageColorCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageColorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageColorRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageAttrPreSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPageColorSV;
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
@Service("cmsPageColorSV")
public class CmsPageColorSVImpl extends GeneralSQLSVImpl implements ICmsPageColorSV {
	

    @Resource(name = "SEQ_CMS_PAGE_COLOR")
    private PaasSequence seqCmsPageColor;
    @Resource
    private CmsPageColorMapper cmsPageColorMapper;
    @Resource
    private ICmsPageAttrPreSV cmsPageAttrPreSV;
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageColorSV#saveCmsPageColor(com.zengshi.ecp.cms.dao.model.CmsPageColor)
     */
    @Override
    public CmsPageColorRespDTO addCmsPageColor(CmsPageColorReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPageColor bo = new CmsPageColor();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seqCmsPageColor.nextValue();
        bo.setId(id);
        if(StringUtil.isBlank(dto.getStatus())){
        	bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
        }
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        cmsPageColorMapper.insertSelective(bo);
        
//        /*3.调用页面属性插入方法*/
//        CmsPageAttrPreReqDTO pageAttrPreReqDTO = new CmsPageAttrPreReqDTO();
//        pageAttrPreReqDTO.setPageId(id);
//        pageAttrPreReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
//        pageAttrPreReqDTO.setBackgroupShowType(CmsConstants.BackgroupShowType.CMS_BACKGROUPSHOWTYPE_01);
//        pageAttrPreReqDTO.setShowBackFlag(CmsConstants.IsNot.CMS_ISNOT_0);
//        cmsPageAttrPreSV.addCmsPageAttrPre(pageAttrPreReqDTO);
        
        /*3.将结果返回*/
        CmsPageColorRespDTO respDTO = new CmsPageColorRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageColorSV#updateCmsPageColor(com.zengshi.ecp.cms.dao.model.CmsPageColor)
     */
    @Override
    public CmsPageColorRespDTO updateCmsPageColor(CmsPageColorReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsPageColor bo = new CmsPageColor();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新楼层的原子方法*/
        return this.updateCmsPageColor(bo);
    }
    
    /** 
     * updateCmsPageColor:(更新楼层的原子方法). <br/> 
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
    public CmsPageColorRespDTO updateCmsPageColor(CmsPageColor bo) throws BusinessException {
        cmsPageColorMapper.updateByPrimaryKeySelective(bo);
        CmsPageColorRespDTO respDTO = new CmsPageColorRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        return respDTO;
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageColorSV#deleteCmsPageColor(java.lang.Long)
     */
    @Override
    public void deleteCmsPageColor(String id) throws BusinessException {
        CmsPageColor bo = new CmsPageColor();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        this.updateCmsPageColor(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageColorSV#deleteCmsPageColorBatch(java.util.List)
     */
    @Override
    public void deleteCmsPageColorBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsPageColor(id);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageColorSV#changeStatusCmsPageColor(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPageColor(String id, String status) throws BusinessException {
        CmsPageColor bo = new CmsPageColor();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        this.updateCmsPageColor(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageColorSV#changeStatusCmsPageColorBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPageColorBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsPageColor(id, status);
            }
        }
    }
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageColorSV#changeStatusCmsPageColor(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void doDefaultCmsPageColor(String id, String isdefault) throws BusinessException {
        CmsPageColor bo = new CmsPageColor();
        bo.setId(Long.parseLong(id));
        this.updateCmsPageColor(bo);
    }
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageColorSV#changeStatusCmsPageColorBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void doDefaultCmsPageColorBatch(List<String> list, String isdefault)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.doDefaultCmsPageColor(id, isdefault);
            }
        }
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageColorSV#queryCmsPageColor(com.zengshi.ecp.cms.dubbo.dto.CmsPageColorReqDTO)
     */
    @Override
    public CmsPageColorRespDTO queryCmsPageColor(CmsPageColorReqDTO dto) throws BusinessException {
        CmsPageColorRespDTO cmsPageColorRespDTO = new CmsPageColorRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsPageColor bo = cmsPageColorMapper.selectByPrimaryKey(dto.getId());
            cmsPageColorRespDTO = conversionObject(bo);
        }
        
        return cmsPageColorRespDTO;
    }
    
    /**
     * TODO 查询楼层列表，无分页（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageColorSV#queryCmsPageColorList(com.zengshi.ecp.cms.dubbo.dto.CmsPageColorReqDTO)
     */
    @Override
    public List<CmsPageColorRespDTO> queryCmsPageColorList(CmsPageColorReqDTO dto) throws BusinessException {

        /*1.组装查询条件*/
        CmsPageColorCriteria cmsPageColorCriteria = new CmsPageColorCriteria();
        Criteria criteria = cmsPageColorCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getColorName())) {
            criteria.andColorNameLike(dto.getColorName());
        }
        if (StringUtil.isNotBlank(dto.getColorStyle())) {
            criteria.andColorStyleLike("%"+dto.getColorStyle()+"%");
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
//        if (StringUtil.isNotEmpty(dto.getPageTypeId())) {
//            criteria.andPageTypeIdEqualTo(dto.getPageTypeId());
//        }
//        if (StringUtil.isNotBlank(dto.getPlatformType())) {
//            criteria.andPlatformTypeEqualTo(dto.getPlatformType());
//        }
        cmsPageColorCriteria.setOrderByClause("CREATE_TIME DESC");
        
        /*2.迭代查询结果*/
        List<CmsPageColorRespDTO> cmsPageColorRespDTOList =  new ArrayList<CmsPageColorRespDTO>();
        List<CmsPageColor> cmsPageColorList = cmsPageColorMapper.selectByExample(cmsPageColorCriteria);
        if(CollectionUtils.isNotEmpty(cmsPageColorList)){
            for(CmsPageColor bo :cmsPageColorList){
                CmsPageColorRespDTO cmsPageColorRespDTO = conversionObject(bo);
                cmsPageColorRespDTOList.add(cmsPageColorRespDTO);
            }
        }
        
        return cmsPageColorRespDTOList;

    }


    /** 
     * TODO 查询楼层，分页（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPageColorSV#queryCmsPageColorPage(com.zengshi.ecp.cms.dubbo.dto.CmsPageColorReqDTO) 
     */
    @Override
    public PageResponseDTO<CmsPageColorRespDTO> queryCmsPageColorPage(CmsPageColorReqDTO dto)
            throws BusinessException {

        /* 1.根据条件检索楼层 */
        CmsPageColorCriteria cmsPageColorCriteria = new CmsPageColorCriteria();
        Criteria criteria = cmsPageColorCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getColorName())) {
            criteria.andColorNameLike("%"+dto.getColorName()+"%");
        }
        if (StringUtil.isNotBlank(dto.getColorStyle())) {
            criteria.andColorStyleLike("%"+dto.getColorStyle()+"%");
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
//        if (StringUtil.isNotEmpty(dto.getPageTypeId())) {
//            criteria.andPageTypeIdEqualTo(dto.getPageTypeId());
//        }
//        if (StringUtil.isNotBlank(dto.getPlatformType())) {
//            criteria.andPlatformTypeEqualTo(dto.getPlatformType());
//        }
        cmsPageColorCriteria.setOrderByClause("CREATE_TIME DESC");
        cmsPageColorCriteria.setLimitClauseCount(dto.getPageSize());
        cmsPageColorCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsPageColorCriteria,false,new PaginationCallback<CmsPageColor, CmsPageColorRespDTO>(){

            @Override
            public List<CmsPageColor> queryDB(BaseCriteria criteria) {
                return cmsPageColorMapper.selectByExample((CmsPageColorCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return cmsPageColorMapper.countByExample((CmsPageColorCriteria)criteria);
            }

            @Override
            public CmsPageColorRespDTO warpReturnObject(CmsPageColor bo) {
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
    private CmsPageColorRespDTO conversionObject(CmsPageColor bo){
        CmsPageColorRespDTO dto = new CmsPageColorRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        
        // 1 查询内容位置服务，获取内容位置对应的名称
        
        // 2.遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        String colorStyleZh =BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_COLOR_STYLE, dto.getColorStyle());
        dto.setColorStyleZH(colorStyleZh);
        String isUseTemplateZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.PUBLIC_PARAM_ISNOT, dto.getIsUseTemplateZH());
        dto.setIsUseTemplateZH(isUseTemplateZH);
        String platformTypeZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_PLATFORM_TYPE,dto.getPlatformTypeZH());
        dto.setPlatformTypeZH(platformTypeZH);
        
        return dto;
    }

}
