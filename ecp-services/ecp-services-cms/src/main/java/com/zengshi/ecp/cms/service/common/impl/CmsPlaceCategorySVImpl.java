package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dao.mapper.common.CmsPlaceCategoryMapper;
import com.zengshi.ecp.cms.dao.model.CmsPlaceCategory;
import com.zengshi.ecp.cms.dao.model.CmsPlaceCategoryCriteria;
import com.zengshi.ecp.cms.dao.model.CmsPlaceCategoryCriteria.Criteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceCategoryReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceCategoryRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsGdsCategorySV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceCategorySV;
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

public class CmsPlaceCategorySVImpl extends GeneralSQLSVImpl implements ICmsPlaceCategorySV {

	@Resource
	private CmsPlaceCategoryMapper mapper;

	@Resource(name = "SEQ_CMS_PLACE_CATEGORY")
	private PaasSequence seq;

	@Resource
	private ICmsPlaceSV cmsPlaceSV;

	@Resource
	private ICmsSiteSV cmsSiteSV;

	@Resource
	private ICmsTemplateSV cmsTemplateSV;
	
	@Resource
	private ICmsGdsCategorySV cmsGdsCategorySV;
	/** 
     * addCmsPlaceCategory:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7 
     */ 
	@Override
    public CmsPlaceCategoryRespDTO addCmsPlaceCategory(CmsPlaceCategoryReqDTO dto) throws BusinessException
    {
    	 /*1.将入参组装成bo*/
        CmsPlaceCategory bo = new CmsPlaceCategory();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        long id = seq.nextValue();
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        mapper.insertSelective(bo);
        
        /*3.将结果返回*/
        CmsPlaceCategoryRespDTO respDTO = new CmsPlaceCategoryRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        
        return respDTO;
    }
    /** 
     * queryCmsPlaceCategoryList:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param dto
     * @return CmsPlaceCategoryRespDTO
     * @throws BusinessException 
     * @since JDK 1.7 
     */ 
	@Override
    public CmsPlaceCategoryRespDTO updateCmsPlaceCategory(CmsPlaceCategoryReqDTO dto) throws BusinessException
    {
    	/*1.将入参组装成bo*/
        CmsPlaceCategory bo = new CmsPlaceCategory();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新内容位置方法*/
        mapper.updateByPrimaryKeySelective(bo);
        
        /*3.将结果返回*/
        CmsPlaceCategoryRespDTO respDTO = new CmsPlaceCategoryRespDTO();
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
    public void changeStatusCmsPlaceCategory(String id, String status) throws BusinessException {
        CmsPlaceCategory bo = new CmsPlaceCategory();
        bo.setId(Long.parseLong(id));
        bo.setStatus(status);
        mapper.updateByPrimaryKeySelective(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceCategorySV#changeStatusCmsPlaceCategoryBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsPlaceCategoryBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsPlaceCategory(id, status);
            }
        }
    }

	 /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceCategorySV#deleteCmsPlaceCategory(java.lang.String) 
     */
    @Override
    public void deleteCmsPlaceCategory(String id) throws BusinessException {
        CmsPlaceCategory bo = new CmsPlaceCategory();
        bo.setId(Long.parseLong(id));
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
        mapper.updateByPrimaryKeySelective(bo);
//        mapper.deleteByPrimaryKey(Long.parseLong(id));
    }
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAdvertiseSV#deleteCmsPlaceCategoryBatch(java.util.List)
     */
    @Override
    public void deleteCmsPlaceCategoryBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsPlaceCategory(id);
            }
        }
    }
    /** 
     * queryCmsPlaceCategoryList:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7 
     */ 
	@Override
    public CmsPlaceCategoryRespDTO queryCmsPlaceCategoryById(CmsPlaceCategoryReqDTO dto) throws BusinessException
    {
    	CmsPlaceCategoryRespDTO cmsPlaceCategoryRespDTO = new CmsPlaceCategoryRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
            CmsPlaceCategory bo = mapper.selectByPrimaryKey(dto.getId());
            cmsPlaceCategoryRespDTO = conversionObject(bo);
        }
        return cmsPlaceCategoryRespDTO;
    }
    
    /** 
     * queryCmsPlaceCategoryPage:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7 
     */ 
	@Override
    public PageResponseDTO<CmsPlaceCategoryRespDTO> queryCmsPlaceCategoryPage(CmsPlaceCategoryReqDTO dto) throws BusinessException
    {
    	 /* 1.根据条件检索内容位置*/
        CmsPlaceCategoryCriteria cmsPlaceCategoryCriteria = new CmsPlaceCategoryCriteria();
        Criteria criteria = cmsPlaceCategoryCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getSiteId())){
        	criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if(StringUtil.isNotEmpty(dto.getTemplateId()))
        {
        	criteria.andTemplateIdEqualTo(dto.getTemplateId());
        }
        if(StringUtil.isNotEmpty(dto.getPlaceId())){
        	criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
        if (StringUtil.isNotBlank(dto.getCatgId())) {
            criteria.andCatgIdEqualTo(dto.getCatgId());
        }
        if (StringUtil.isNotBlank(dto.getStatus())){
        	criteria.andStatusEqualTo(dto.getStatus());
        }
        cmsPlaceCategoryCriteria.setOrderByClause(" CREATE_TIME DESC");
        cmsPlaceCategoryCriteria.setLimitClauseCount(dto.getPageSize());
        cmsPlaceCategoryCriteria.setLimitClauseStart(dto.getStartRowIndex());
        
        return super.queryByPagination(dto,cmsPlaceCategoryCriteria,false,new PaginationCallback<CmsPlaceCategory, CmsPlaceCategoryRespDTO>(){

            @Override
            public List<CmsPlaceCategory> queryDB(BaseCriteria criteria) {
                return mapper.selectByExample((CmsPlaceCategoryCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return mapper.countByExample((CmsPlaceCategoryCriteria)criteria);
            }

            @Override
            public CmsPlaceCategoryRespDTO warpReturnObject(CmsPlaceCategory bo) {
                return conversionObject(bo);
            }
        
        });
    }
    
	@Override
	public  List<CmsPlaceCategoryRespDTO> queryCmsPlaceCategory(
			CmsPlaceCategoryReqDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
		/*1.组装查询条件*/
        CmsPlaceCategoryCriteria cmsPlaceCategoryCriteria = new CmsPlaceCategoryCriteria();
        CmsPlaceCategoryCriteria.Criteria criteria = cmsPlaceCategoryCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (StringUtil.isNotEmpty(dto.getSiteId())){
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if(StringUtil.isNotEmpty(dto.getTemplateId()))
        {
            criteria.andTemplateIdEqualTo(dto.getTemplateId());
        }
        if(StringUtil.isNotEmpty(dto.getPlaceId())){
            criteria.andPlaceIdEqualTo(dto.getPlaceId());
        }
        if (StringUtil.isNotBlank(dto.getCatgId())) {
            criteria.andCatgIdEqualTo(dto.getCatgId());
        }
        if (StringUtil.isNotBlank(dto.getStatus())){
        	criteria.andStatusEqualTo(dto.getStatus());
        }
        cmsPlaceCategoryCriteria.setOrderByClause(" CREATE_TIME DESC");
        
        /* 2.根据条件检索楼层  */
        List<CmsPlaceCategoryRespDTO> cmsPlaceCategoryRespDTOList =  new ArrayList<CmsPlaceCategoryRespDTO>();
        List<CmsPlaceCategory> cmsPlaceCategoryList = mapper.selectByExample(cmsPlaceCategoryCriteria);
        if(CollectionUtils.isNotEmpty(cmsPlaceCategoryList)){
        	for(CmsPlaceCategory bo : cmsPlaceCategoryList){
        		CmsPlaceCategoryRespDTO cmsPlaceCategoryRespDTO = conversionObject(bo);
        		cmsPlaceCategoryRespDTOList.add(cmsPlaceCategoryRespDTO);
        	}
        }
        return cmsPlaceCategoryRespDTOList;
	}

	 /** 
     * conversionObject:(将bo转换成DTO，同时翻译有关字段). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9
     * @param bo 
     * @return 
     * @since JDK 1.6 
     */ 
    private CmsPlaceCategoryRespDTO conversionObject(CmsPlaceCategory bo){
    	CmsPlaceCategoryRespDTO dto = new CmsPlaceCategoryRespDTO();
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
        
        // 4 查询商品分类服务，获取商品分类对应的名称
        if (StringUtil.isNotEmpty(dto.getCatgId())) {
            CmsGdsCategoryReqDTO reqDTO = new CmsGdsCategoryReqDTO();
            reqDTO.setId(bo.getCatgId());
            CmsGdsCategoryRespDTO respDTO = cmsGdsCategorySV.queryCmsGdsCategory(reqDTO);
            if(respDTO != null){
                dto.setCatgName(respDTO.getCatgName());
            }
        }
        //5  字段翻译汉子
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        return dto;
    }
}
