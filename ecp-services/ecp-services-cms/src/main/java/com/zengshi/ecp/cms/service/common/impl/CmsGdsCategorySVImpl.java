package com.zengshi.ecp.cms.service.common.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dao.mapper.common.CmsGdsCategoryMapper;
import com.zengshi.ecp.cms.dao.model.CmsGdsCategory;
import com.zengshi.ecp.cms.dao.model.CmsGdsCategoryCriteria;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceCategoryRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsGdsCategorySV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class CmsGdsCategorySVImpl extends GeneralSQLSVImpl implements ICmsGdsCategorySV{

    @Resource
    private CmsGdsCategoryMapper mapper;

    @Resource(name = "SEQ_CMS_GDS_CATEGORY")
    private PaasSequence seq;
    
    @Resource
    private ICmsPlaceCategoryRSV cmsPlaceCategoryRSV;
    
    @Override
    public CmsGdsCategoryRespDTO addCmsGdsCategory(CmsGdsCategoryReqDTO dto) throws BusinessException {
        /*1.将入参组装成bo*/
        CmsGdsCategory bo = new CmsGdsCategory();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        String id = String.valueOf(seq.nextValue());
//        if(StringUtil.isBlank(bo.getIfLeaf())){
//            bo.setIfLeaf("1");
//        }
        bo.setId(id);
        bo.setCreateTime(DateUtil.getSysDate());
        bo.setCreateStaff(dto.getStaff().getId());
        
        /*2.调dao层接口*/
        mapper.insertSelective(bo);
//        if(StringUtil.isNotBlank(bo.getCatgParent())){
//            CmsGdsCategoryReqDTO dtos = new CmsGdsCategoryReqDTO();
//            dto.setId(bo.getCatgParent());
//            dto.setIfLeaf("0");
//            this.updateCmsGdsCategory(dtos);
//        }
        
        /*3.将结果返回*/
        CmsGdsCategoryRespDTO respDTO = new CmsGdsCategoryRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        
        return respDTO;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsGdsCategorySV#deleteCmsGdsCategory(java.lang.String) 
     */
    @Override
    public void deleteCmsGdsCategory(String id) throws BusinessException {
        CmsGdsCategory bo = new CmsGdsCategory();
        bo.setId(id);
        bo.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
        mapper.updateByPrimaryKeySelective(bo);
//        mapper.deleteByPrimaryKey(id);
    }
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsAdvertiseSV#deleteCmsAdvertiseBatch(java.util.List)
     */
    @Override
    public void deleteCmsGdsCategoryBatch(List<String> list) throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.deleteCmsGdsCategory(id);
            }
        }
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsGdsCategorySV#updateCmsGdsCategory(com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO) 
     */
    @Override
    public CmsGdsCategoryRespDTO updateCmsGdsCategory(CmsGdsCategoryReqDTO dto) throws BusinessException {
    
        /*1.将入参组装成bo*/
        CmsGdsCategory bo = new CmsGdsCategory();
        if(dto != null){
            ObjectCopyUtil.copyObjValue(dto, bo, null, false);
        }
        bo.setUpdateStaff(dto.getStaff().getId());
        bo.setUpdateTime(DateUtil.getSysDate());
        
        /*2.更新内容位置方法*/
        mapper.updateByPrimaryKeySelective(bo);
        
        /*3.将结果返回*/
        CmsGdsCategoryRespDTO respDTO = new CmsGdsCategoryRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, respDTO, null, false);
        }
        
        return respDTO;
    }
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV#changeStatusCmsFloor(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsGdsCategory(String id, String status) throws BusinessException {
        CmsGdsCategory bo = new CmsGdsCategory();
        bo.setId(id);
        bo.setStatus(status);
        mapper.updateByPrimaryKeySelective(bo);
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorSV#changeStatusCmsFloorBatch(java.util.List,
     *      java.lang.String)
     */
    @Override
    public void changeStatusCmsGdsCategoryBatch(List<String> list, String status)
            throws BusinessException {
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i);
            if (StringUtil.isNotBlank(id)) {
                this.changeStatusCmsGdsCategory(id, status);
            }
        }
    }
    
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsGdsCategorySV#queryCmsGdsCategory
     * (com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO) 
     */
	@Override
	public CmsGdsCategoryRespDTO queryCmsGdsCategory(CmsGdsCategoryReqDTO dto)
			throws BusinessException {
		// TODO Auto-generated method stub
		CmsGdsCategoryRespDTO cmsGdsCategoryRespDTO = new CmsGdsCategoryRespDTO();
        if (StringUtil.isNotEmpty(dto.getId())) {
            /* 1.查询  */
        	 CmsGdsCategory bo = mapper.selectByPrimaryKey(dto.getId());
            cmsGdsCategoryRespDTO = conversionObject(bo);
        }
        return cmsGdsCategoryRespDTO;
	}
	/**
     * 
     * queryCmsGdsCategory:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param catgId   分类id
     * @param level  子分类层级
     * @param status 子分类状态
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	@Override
    public CmsGdsCategoryRespDTO queryCmsGdsCategory(CmsGdsCategoryReqDTO dto, Short level, String status)
            throws BusinessException {
	    //入参验证
	    if(dto == null || StringUtil.isBlank(dto.getId())){
	        return null;
	    }
	    if(level == null){
	        level = -1; 
	    }
	    if(StringUtil.isBlank(status)){ // 默认查发布的
	        status = CmsConstants.ParamStatus.CMS_PARAMSTATUS_1;
	    }
	    
	    //1 查询父分类
	    List<CmsGdsCategoryRespDTO> respDtoList = null;
	    respDtoList = this.queryCmsCategoryInfo(dto);
	    CmsGdsCategoryRespDTO parent = null;
	    if(CollectionUtils.isNotEmpty(respDtoList)){
	        parent = respDtoList.get(0);
	    }
	    
	    
	    
	    //2 查询指定层级的子分类   level=-1 则查全部子分类
	    LinkedList<CmsGdsCategoryRespDTO> list = new LinkedList<CmsGdsCategoryRespDTO>();
	    if(parent != null && level != 0){
	        Short initLevel = parent.getCatgLevel();
	        if(StringUtil.isEmpty(initLevel)){
	            throw new BusinessException("分类id为"+dto.getId()+"的分类层级字段CatgLevel为空，无法进行层级判断！");
	        }
	        list.addFirst(parent);
	        for(;list.size()>0;){
	            CmsGdsCategoryRespDTO catg = list.pop();
	            Short catgLevel = catg.getCatgLevel();
	            if(StringUtil.isEmpty(catgLevel)){
	                throw new BusinessException("分类id为"+catg.getId()+"的分类层级字段CatgLevel为空，无法进行层级判断！");
	            }
	            if(level<=-1 || (catgLevel - initLevel < level && catgLevel - initLevel >= 0)){
	                CmsGdsCategoryReqDTO sonReqDto = new CmsGdsCategoryReqDTO();
	                sonReqDto.setId(catg.getId());
	                catg.setStatus(status);
	                List<CmsGdsCategoryRespDTO> sonCatgList = this.queryCmsCategorySons(sonReqDto);
	                if(CollectionUtils.isNotEmpty(sonCatgList)){
	                    catg.setCmsChildCatg(sonCatgList);
	                    list.addAll(list.size(), sonCatgList);
	                }
	            }
	        }
	    }
        return parent;
    }
	/**
	 * queryCmsGdsCategoryInit:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author huangxm9
	 * @param dto
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	@Override
	public List<CmsGdsCategoryRespDTO> queryCmsGdsCategoryInit() throws BusinessException
	{
		CmsGdsCategoryCriteria cmsGdsCategoryCriteria = new CmsGdsCategoryCriteria();
	    CmsGdsCategoryCriteria.Criteria criteria = cmsGdsCategoryCriteria.createCriteria();
		CmsGdsCategoryReqDTO dto = new CmsGdsCategoryReqDTO();
		dto.setCatgLevel(Short.valueOf("1"));
		if(StringUtil.isNotEmpty(dto.getCatgLevel())){
			criteria.andCatgLevelEqualTo(dto.getCatgLevel());
		}
		if(StringUtil.isNotEmpty(dto.getSiteId())){
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
		//查询有效的
		dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
		if(StringUtil.isNotBlank(dto.getStatus())){
			criteria.andStatusEqualTo(dto.getStatus());
		}
		cmsGdsCategoryCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
		List<CmsGdsCategoryRespDTO> cmsGdsCategoryRespDTOList =  new ArrayList<CmsGdsCategoryRespDTO>();
        List<CmsGdsCategory> cmsGdsCategoryList = mapper.selectByExample(cmsGdsCategoryCriteria);
        if(CollectionUtils.isNotEmpty(cmsGdsCategoryList)){
            for(CmsGdsCategory bo :cmsGdsCategoryList){
                CmsGdsCategoryRespDTO cmsGdsCategoryRespDTO = conversionObject(bo);
                cmsGdsCategoryRespDTOList.add(cmsGdsCategoryRespDTO);
            }
        }
        
        return cmsGdsCategoryRespDTOList;
	}
	 /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsGdsCategorySV#queryCmsCategorySons
     * (com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO) 
     */
	@Override
	public List<CmsGdsCategoryRespDTO> queryCmsCategorySons(
			CmsGdsCategoryReqDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
//		CmsPlaceCategoryReqDTO cmsPlaceCategoryReqDTO = new CmsPlaceCategoryReqDTO();
//		cmsPlaceCategoryRSV.queryCmsPlaceCategory(cmsPlaceCategoryReqDTO.setCatgId(dto.getCatgId()));
		 /*1.组装查询条件*/
		dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        CmsGdsCategoryCriteria cmsGdsCategoryCriteria = new CmsGdsCategoryCriteria();
        CmsGdsCategoryCriteria.Criteria criteria = cmsGdsCategoryCriteria.createCriteria();
        if (StringUtil.isNotEmpty(dto.getId())) {
            criteria.andCatgParentEqualTo(dto.getId());
        }
        if(StringUtil.isNotEmpty(dto.getSiteId())&& 0L != dto.getSiteId()){
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            criteria.andStatusEqualTo(dto.getStatus());
        }
        cmsGdsCategoryCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        
        /* 2.根据条件检索楼层  */
        List<CmsGdsCategoryRespDTO> cmsGdsCategoryRespDTOList =  new ArrayList<CmsGdsCategoryRespDTO>();
        List<CmsGdsCategory> cmsGdsCategoryList = mapper.selectByExample(cmsGdsCategoryCriteria);
        if(CollectionUtils.isNotEmpty(cmsGdsCategoryList)){
            for(CmsGdsCategory bo :cmsGdsCategoryList){
                CmsGdsCategoryRespDTO cmsGdsCategoryRespDTO = conversionObject(bo);
                cmsGdsCategoryRespDTOList.add(cmsGdsCategoryRespDTO);
            }
        }
        
        return cmsGdsCategoryRespDTOList;
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
    private CmsGdsCategoryRespDTO conversionObject(CmsGdsCategory bo){
        CmsGdsCategoryRespDTO dto = new CmsGdsCategoryRespDTO();
        if(bo != null){
            ObjectCopyUtil.copyObjValue(bo, dto, null, false);
        }
        // 1 parenName
		if (StringUtil.isNotEmpty(dto.getCatgParent())) {
			CmsGdsCategoryReqDTO reqDTO = new CmsGdsCategoryReqDTO();
			reqDTO.setId(dto.getCatgParent());
			// 查到上级父节点名称避免多次循环调用。
			CmsGdsCategory bo2 = mapper.selectByPrimaryKey(reqDTO.getId());
			// CmsGdsCategoryRespDTO cmsGdsCategoryRespDTO =
			// this.queryCmsGdsCategory(reqDTO);
			if (bo2 != null) {
				dto.setCatgParentZH(bo2.getCatgName());
			}
		}
        // 2 根据模板服务，获取模板对应名称。
       
        // 3. 遍历将编码转中文 
        String statusZH = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.PUBLIC_PARAM_STATUS, dto.getStatus());
        dto.setStatusZH(statusZH);
        return dto;
    }
    
    /**
     * 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.cms.service.common.interfaces.ICmsGdsCategorySV#queryCmsCategoryInfo(com.zengshi.ecp.cms.dubbo.dto.CmsGdsCategoryReqDTO)
     */
    @Override
    public List<CmsGdsCategoryRespDTO> queryCmsCategoryInfo(CmsGdsCategoryReqDTO dto)
            throws BusinessException {
        List<CmsGdsCategoryRespDTO> resultList = new ArrayList<CmsGdsCategoryRespDTO>();
        CmsGdsCategoryCriteria cmsGdsCategoryCriteria = new CmsGdsCategoryCriteria();
        CmsGdsCategoryCriteria.Criteria criteria = cmsGdsCategoryCriteria.createCriteria();
        if(StringUtil.isNotEmpty(dto.getCatgLevel())){
            criteria.andCatgLevelEqualTo(dto.getCatgLevel());
        }
        if(StringUtil.isNotEmpty(dto.getSiteId())){
            criteria.andSiteIdEqualTo(dto.getSiteId());
        }
        if(StringUtil.isNotBlank(dto.getCatgName())){
            criteria.andCatgNameLike("%"+dto.getCatgName()+"%");
        }
        if(StringUtil.isNotBlank(dto.getId())){
            criteria.andIdEqualTo(dto.getId());
        }
        if(StringUtil.isNotBlank(dto.getStatus())){
        	criteria.andStatusEqualTo(dto.getStatus());
        }
        cmsGdsCategoryCriteria.setOrderByClause("SORT_NO ASC, CREATE_TIME DESC");
        List<CmsGdsCategory> cmsGdsCategoryList = mapper.selectByExample(cmsGdsCategoryCriteria);
        if(CollectionUtils.isNotEmpty(cmsGdsCategoryList)){
            for(CmsGdsCategory bo :cmsGdsCategoryList){
                CmsGdsCategoryRespDTO cmsGdsCategoryRespDTO = conversionObject(bo);
                resultList.add(cmsGdsCategoryRespDTO);
            }
        }
        return resultList;
    }

}
