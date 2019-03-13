/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsCatg2PropSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.common.impl 
 * Date:2015年8月17日下午2:15:30 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.service.common.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.StringUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsCategoryMapper;
import com.zengshi.ecp.goods.dao.mapper.common.GdsCatg2PropMapper;
import com.zengshi.ecp.goods.dao.mapper.common.GdsPropMapper;
import com.zengshi.ecp.goods.dao.mapper.common.manual.GdsCatg2PropManualMapper;
import com.zengshi.ecp.goods.dao.model.GdsCategory;
import com.zengshi.ecp.goods.dao.model.GdsCategoryCriteria;
import com.zengshi.ecp.goods.dao.model.GdsCatg2Prop;
import com.zengshi.ecp.goods.dao.model.GdsCatg2PropCriteria;
import com.zengshi.ecp.goods.dao.model.GdsCatg2PropCriteria.Criteria;
import com.zengshi.ecp.goods.dao.model.GdsProp;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatg2PropSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropValueSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 商品分类与商品属性关联服务接口。 <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月17日下午2:15:30 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public class GdsCatg2PropSVImpl extends AbstractSVImpl implements
		IGdsCatg2PropSV {

	@Resource
	private GdsCatg2PropMapper gdsCatg2PropMapper;

	@Resource
	private GdsCatg2PropManualMapper gdsCatg2PropManualMapper;

	@Resource
	private GdsPropMapper gdsPropMapper;

	@Resource
	private IGdsPropSV gdsPropSV;

	@Resource
	private IGdsPropValueSV gdsPropValueSV;
	@Resource
	private IGdsCategorySV gdsCategorySV;
	@Resource
	private GdsCategoryMapper gdsCategoryMapper;

	private static final String DEFAULT_ORDER_BY = "CREATE_TIME DESC";

	/**
	 * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsCatg2PropSV#saveGdsCatg2Prop(com.zengshi.ecp.goods.dao.model.GdsCatg2Prop)
	 */
	@Override
	public GdsCatg2Prop saveGdsCatg2Prop(GdsCatg2Prop gdsCatg2Prop)
			throws BusinessException {
		Timestamp now = now();
		gdsCatg2Prop.setCreateTime(now);
		gdsCatg2Prop.setUpdateTime(now);
		gdsCatg2PropMapper.insert(gdsCatg2Prop);
		return gdsCatg2Prop;
	}

	@Override
	public PageResponseDTO<GdsCatg2PropRespDTO> queryConfigedGdsCatg2PropRespDTOPaging(
			GdsCatg2PropReqDTO dto) throws BusinessException {
		GdsCatg2PropCriteria criteria = new GdsCatg2PropCriteria();
		if(dto!=null){
		    criteria.setLimitClauseStart(dto.getStartRowIndex());
		    criteria.setLimitClauseCount(dto.getPageSize());
		}
		criteria.setOrderByClause(DEFAULT_ORDER_BY);
		Criteria c = criteria.createCriteria();
		// 属性名查询条件.
		if (dto!=null && StringUtil.isNotBlank(dto.getPropName())) {
			GdsPropReqDTO propReqDTO = new GdsPropReqDTO();
			propReqDTO.setPropName(dto.getPropName());
			propReqDTO.setPageSize(Integer.MAX_VALUE);
			List<Long> propIds = gdsPropSV.queryPropIds(propReqDTO);

			if (!CollectionUtils.isEmpty(propIds)) {
				if (propIds.size() == 1) {
					c.andPropIdEqualTo(propIds.get(0));
				} else {
					c.andPropIdIn(propIds);
				}
			} else {
				c.andPropIdEqualTo(-99999L);
			}
		}
		if (dto != null) {
			initCriteria(c, dto);
		}
		return super.queryByPagination(dto, criteria, false,
				new GdsCatg2PropRespDTOPaginationCallback());
	}

	/**
	 * 商品属性之属性值分页查询回调类。 Title: ECP <br>
	 * Project Name:ecp-services-goods <br>
	 * Description: <br>
	 * Date:2015年8月17日下午3:36:33 <br>
	 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
	 * 
	 * @author liyong7
	 * @version GdsTypeSVImpl
	 * @since JDK 1.6
	 */
	protected class GdsCatg2PropRespDTOPaginationCallback extends
			PaginationCallback<GdsCatg2Prop, GdsCatg2PropRespDTO> {

		@Override
		public List<GdsCatg2Prop> queryDB(BaseCriteria criteria) {
			return gdsCatg2PropMapper
					.selectByExample((GdsCatg2PropCriteria) criteria);
		}

		@Override
		public long queryTotal(BaseCriteria criteria) {
			return gdsCatg2PropMapper
					.countByExample((GdsCatg2PropCriteria) criteria);
		}

		@Override
		public GdsCatg2PropRespDTO warpReturnObject(GdsCatg2Prop t) {
			GdsCatg2PropRespDTO dto = new GdsCatg2PropRespDTO();
			ObjectCopyUtil.copyObjValue(t, dto, null, true);
			GdsProp gdsProp = gdsPropMapper.selectByPrimaryKey(t.getPropId());
			ObjectCopyUtil.copyObjValue(gdsProp, dto, null, true);
			dto.setIfAbleEdit(t.getIfAbleEdit());
			dto.setPropName(gdsProp.getPropName());
			dto.setPropValueTypeName(BaseParamUtil.fetchParamValue(
					"GDS_PROP_VALUE_TYPE", gdsProp.getPropValueType()));
			dto.setPropTypeName(BaseParamUtil.fetchParamValue("GDS_PROP_TYPE",
					gdsProp.getPropType()));
			dto.setStatus(BaseParamUtil.fetchParamValue("PUBLIC_PARAM_STATUS",
					gdsProp.getStatus()));
			return dto;
		}

	}

	/*
	 * @Override public PageResponseDTO<GdsCatg2PropRespDTO>
	 * queryUnconfigedGdsCatg2PropRespDTOPaging( GdsCatg2PropReqDTO dto) throws
	 * BusinessException { List<Long> propIds =
	 * queryConfigedPropIds(dto.getCatgCode(),
	 * GdsConstants.Commons.STATUS_VALID); GdsCatg2PropCriteria criteria = new
	 * GdsCatg2PropCriteria();
	 * criteria.setLimitClauseStart(dto.getStartRowIndex());
	 * criteria.setLimitClauseCount(dto.getPageSize());
	 * criteria.setOrderByClause(DEFAULT_ORDER_BY);
	 * 
	 * Criteria c = criteria.createCriteria(); if (dto != null) { c =
	 * initCriteria(c, dto); } if (!propIds.isEmpty()) { if (c == null) { c =
	 * criteria.createCriteria(); } c.andPropIdNotIn(propIds); }
	 * 
	 * return super.queryByPagination(dto, criteria, false, new
	 * GdsCatg2PropRespDTOPaginationCallback()); }
	 */

	@Override
	public void batchAddGdsCatg2Props(String catgCode, List<Long> propIds,
			Long createStaff, boolean skipWhenExist) throws BusinessException {
		StringBuffer errorMsg = new StringBuffer();

		if (!StringUtils.hasText(catgCode)) {
			errorMsg.append("catgCode,");
		}
		if (propIds == null || propIds.isEmpty()) {
			errorMsg.append("propIds,");
		}
		if (errorMsg.length() > 0) {
			errorMsg.deleteCharAt(errorMsg.length() - 1);
			throw new BusinessException(
					GdsErrorConstants.Commons.ERROR_GOODS_200100,
					new String[] { errorMsg.toString() });
		}
		Timestamp now = now();
		for (Long propId : propIds) {
			if (queryExist(catgCode, propId, GdsConstants.Commons.STATUS_VALID)) {
				if (skipWhenExist) {
					continue;
				} else {
					throw new BusinessException(
							GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200308,
							new String[] { propId.toString() });
				}
			}
			GdsCatg2Prop obj = new GdsCatg2Prop();
			obj.setCatgCode(catgCode);
			obj.setCreateStaff(createStaff);
			obj.setCreateTime(now);
			obj.setUpdateStaff(createStaff);
			obj.setUpdateTime(now);
			obj.setIfBasic(GdsConstants.GdsCatg2Prop.IS_BASIC_0);
			obj.setIfHaveto(GdsConstants.GdsCatg2Prop.IS_REQUIRE_0);
			obj.setIfSearch(GdsConstants.GdsCatg2Prop.IS_SEARCH_0);
			obj.setIfGdsInput(GdsConstants.GdsCatg2Prop.IS_GDS_INPUT_0);
			obj.setPropId(propId);
			obj.setIfAbleEdit(GdsConstants.Commons.STATUS_INVALID);
			obj.setStatus(GdsConstants.Commons.STATUS_VALID);
			gdsCatg2PropMapper.insert(obj);
		}

	}

	@Override
	public int batchDeleteCatg2Prop(String catgCode, List<Long> propIds,
			Long updateStaff) {
		/*return gdsCatg2PropManualMapper.batchDeleteCatg2Prop(catgCode, propIds,
				updateStaff, now());*/
		if(CollectionUtils.isNotEmpty(propIds) && StringUtil.isNotBlank(catgCode)){
			GdsCatg2Prop record = new GdsCatg2Prop();
			record.setUpdateTime(now());
			record.setUpdateStaff(updateStaff);
			record.setStatus(GdsConstants.Commons.STATUS_INVALID);
			
			GdsCatg2PropCriteria example = new GdsCatg2PropCriteria();
			GdsCatg2PropCriteria.Criteria c = example.createCriteria();
			c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
			c.andIfAbleEditNotEqualTo(GdsConstants.Commons.STATUS_VALID);
			c.andCatgCodeEqualTo(catgCode);
			c.andPropIdIn(propIds);
			
			return gdsCatg2PropMapper.updateByExampleSelective(record, example);
		}
		return 0;
	}
	
	@Override
	public Long queryUneditable(String catgCode, List<Long> propIds) {
		/*return gdsCatg2PropManualMapper.batchDeleteCatg2Prop(catgCode, propIds,
				updateStaff, now());*/
		if(CollectionUtils.isNotEmpty(propIds) && StringUtil.isNotBlank(catgCode)){
			GdsCatg2PropCriteria example = new GdsCatg2PropCriteria();
			GdsCatg2PropCriteria.Criteria c = example.createCriteria();
			c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
			c.andIfAbleEditEqualTo(GdsConstants.Commons.STATUS_VALID);
			c.andCatgCodeEqualTo(catgCode);
			c.andPropIdIn(propIds);
			
			return gdsCatg2PropMapper.countByExample(example);
		}
		return 0L;
	}
	
	

	@Override
	public List<Long> queryConfigedPropIds(String catgCode, String... status)
			throws BusinessException {
		return gdsCatg2PropManualMapper.queryConfigedPropIds(catgCode, status);
	}

	@Override
	public boolean queryExist(String catgCode, Long propId, String... status)
			throws BusinessException {
		GdsCatg2PropCriteria criteria = new GdsCatg2PropCriteria();
		Criteria c = criteria.createCriteria();
		c.andCatgCodeEqualTo(catgCode);
		c.andPropIdEqualTo(propId);
		initStatusCriteria(c, status);
		return gdsCatg2PropMapper.countByExample(criteria) > 0;

	}

	@Override
	public boolean executeIsBaseConfig(String catgCode, Long propId,
			String isBasic, Long updateStaff) throws BusinessException {
		StringBuffer errorMsg = new StringBuffer();
		if (!StringUtils.hasText(catgCode)) {
			errorMsg.append("catgCode,");
		}

		if (propId == null) {
			errorMsg.append("propId,");
		}

		if (!StringUtils.hasText(isBasic)) {
			errorMsg.append("isBasic,");
		}

		if (errorMsg.length() > 0) {
			errorMsg.deleteCharAt(errorMsg.length() - 1);
			throw new BusinessException(
					GdsErrorConstants.Commons.ERROR_GOODS_200100,
					new String[] { errorMsg.toString() });
		}
		int i = -1;
		switch (isBasic) {
		case GdsConstants.GdsCatg2Prop.IS_BASIC_0:
		case GdsConstants.GdsCatg2Prop.IS_BASIC_1:
			GdsCatg2PropReqDTO dto = createConfigCondition(catgCode, propId,
					isBasic, null, null, updateStaff);
			i = gdsCatg2PropManualMapper.executeIsBasicConfig(dto);
			break;
		default:
			throw new BusinessException(
					GdsErrorConstants.Commons.ERROR_GOODS_200098,
					new String[] { "isBasic" });
		}

		return i > 0;
	}

	@Override
	public boolean executeIsRequireConfig(String catgCode, Long propId,
			String isRequired, Long updateStaff) throws BusinessException {
		StringBuffer errorMsg = new StringBuffer();
		if (!StringUtils.hasText(catgCode)) {
			errorMsg.append("catgCode,");
		}

		if (propId == null) {
			errorMsg.append("propId,");
		}

		if (!StringUtils.hasText(isRequired)) {
			errorMsg.append("isRequired,");
		}

		if (errorMsg.length() > 0) {
			errorMsg.deleteCharAt(errorMsg.length() - 1);
			throw new BusinessException(
					GdsErrorConstants.Commons.ERROR_GOODS_200100,
					new String[] { errorMsg.toString() });
		}

		int i = -1;
		switch (isRequired) {
		case GdsConstants.GdsCatg2Prop.IS_REQUIRE_0:
		case GdsConstants.GdsCatg2Prop.IS_REQUIRE_1:
			GdsCatg2PropReqDTO dto = createConfigCondition(catgCode, propId,
					null, isRequired, null, updateStaff);
			i = gdsCatg2PropManualMapper.executeIsRequireConfig(dto);
			break;
		default:
			throw new BusinessException(
					GdsErrorConstants.Commons.ERROR_GOODS_200098,
					new String[] { "isRequired" });
		}

		return i > 0;
	}

	@Override
	public boolean executeIsSearchConfig(String catgCode, Long propId,
			String isSearch, Long updateStaff) throws BusinessException {
		StringBuffer errorMsg = new StringBuffer();
		if (!StringUtils.hasText(catgCode)) {
			errorMsg.append("catgCode,");
		}

		if (propId == null) {
			errorMsg.append("propId,");
		}

		if (!StringUtils.hasText(isSearch)) {
			errorMsg.append("isSearch,");
		}

		if (errorMsg.length() > 0) {
			errorMsg.deleteCharAt(errorMsg.length() - 1);
			throw new BusinessException(
					GdsErrorConstants.Commons.ERROR_GOODS_200100,
					new String[] { errorMsg.toString() });
		}

		int i = -1;
		switch (isSearch) {
		case GdsConstants.GdsCatg2Prop.IS_SEARCH_0:
		case GdsConstants.GdsCatg2Prop.IS_SEARCH_1:
			GdsCatg2PropReqDTO dto = createConfigCondition(catgCode, propId,
					null, null, isSearch, updateStaff);
			i = gdsCatg2PropManualMapper.executeIsSearchConfig(dto);
			break;
		default:
			throw new BusinessException(
					GdsErrorConstants.Commons.ERROR_GOODS_200098,
					new String[] { "isSearch" });
		}

		return i > 0;
	}

	@Override
	public GdsCatg2PropRelationRespDTO queryCategoryProps(
			GdsCatg2PropReqDTO reqDTO) throws BusinessException {
		GdsCatg2PropRelationRespDTO relationRespDTO = new GdsCatg2PropRelationRespDTO();
		List<GdsPropRespDTO> basics = new ArrayList<GdsPropRespDTO>();
		List<GdsPropRespDTO> specs = new ArrayList<GdsPropRespDTO>();
		List<GdsPropRespDTO> params = new ArrayList<GdsPropRespDTO>();
		List<GdsPropRespDTO> others = new ArrayList<GdsPropRespDTO>();
		List<GdsPropRespDTO> editoParam = new ArrayList<GdsPropRespDTO>();
		List<GdsPropRespDTO> fileParam = new ArrayList<GdsPropRespDTO>();
		List<GdsCatg2Prop> gdsCatg2Props = null;
		/**
		 * 是否包含父分类的属性
		 */
		if (reqDTO.getIfContainTopProp()) {
			gdsCatg2Props = queryConfigedPropsWithParents(reqDTO);
		} else {
			gdsCatg2Props = queryConfigedProps(reqDTO);
		}

		/**
		 * 查询对应的分类信息
		 */
		if (!CollectionUtils.isEmpty(gdsCatg2Props)) {
			for (GdsCatg2Prop gdsCatg2Prop : gdsCatg2Props) {
				GdsPropRespDTO gdsPropRespDTO = new GdsPropRespDTO();
				GdsProp prop = gdsPropSV.queryGdsPropByPK(gdsCatg2Prop
						.getPropId());
				if(prop == null){
					continue;
				}
				ObjectCopyUtil.copyObjValue(gdsCatg2Prop, gdsPropRespDTO, null,
						false);
				ObjectCopyUtil.copyObjValue(prop, gdsPropRespDTO, null, false);
				gdsPropRespDTO
						.setValues(gdsPropValueSV.queryPropValuesByPropId(
								prop.getId(),
								new String[] { GdsConstants.Commons.STATUS_VALID }));
				if (GdsConstants.GdsProp.GDS_PROP_VALUE_INPUT_TYPE_RICHTXT
						.equals(gdsPropRespDTO.getPropInputType())) {
				    //富文本
					editoParam.add(gdsPropRespDTO);
				} else if (GdsConstants.GdsProp.GDS_PROP_VALUE_INPUT_TYPE_FILE
				        .equals(gdsPropRespDTO.getPropInputType())){
				    //文件
				    fileParam.add(gdsPropRespDTO);
				} else if (GdsUtils.isEqualsValid(gdsCatg2Prop.getIfBasic())) {
					basics.add(gdsPropRespDTO);
				} else if (GdsConstants.GdsProp.PROP_TYPE_1.equals(prop
						.getPropType())) {
					specs.add(gdsPropRespDTO);
				} else if (GdsConstants.GdsProp.PROP_TYPE_2.equals(prop
						.getPropType())) {
					params.add(gdsPropRespDTO);
				} else if (GdsConstants.GdsProp.PROP_TYPE_3.equals(prop
						.getPropType())) {
					others.add(gdsPropRespDTO);
				}
			}
		}
		relationRespDTO.setBasics(basics);
		relationRespDTO.setOthers(others);
		relationRespDTO.setParams(params);
		relationRespDTO.setSpecs(specs);
		relationRespDTO.setEditoParam(editoParam);
		relationRespDTO.setFileParam(fileParam);
		return relationRespDTO;
	}

	@Override
	public GdsCatg2PropRelationRespDTO queryCategoryPropsByCondition(
			GdsCatg2PropReqDTO reqDTO) throws BusinessException {
		String catgCode = reqDTO.getCatgCode();
		GdsCategory gdsCategory = gdsCategorySV.queryGdsCategoryById(catgCode);
		if (null == gdsCategory
				|| GdsUtils.isEqualsInvalid(gdsCategory.getStatus())) {
			return null;
		}
		GdsCatg2PropRelationRespDTO relationRespDTO = new GdsCatg2PropRelationRespDTO();
		List<GdsPropRespDTO> searchProps = new ArrayList<GdsPropRespDTO>();
		// 查询出分类已配置属性关联关系.
		PageResponseDTO<GdsCatg2PropRespDTO> page = queryConfigedGdsCatg2PropRespDTOPaging(reqDTO);
		List<GdsCatg2PropRespDTO> catg2PropRespDTOs = page.getResult();
		if (!CollectionUtils.isEmpty(catg2PropRespDTOs)) {
			for (GdsCatg2PropRespDTO gdsCatg2PropRespDTO : catg2PropRespDTOs) {
				GdsProp prop = gdsPropSV.queryGdsPropByPK(
						gdsCatg2PropRespDTO.getPropId(), true);
				if (null == prop) {
					continue;
				}
				GdsPropRespDTO gdsPropRespDTO = new GdsPropRespDTO();
				ObjectCopyUtil.copyObjValue(prop, gdsPropRespDTO, null, true);
				gdsPropRespDTO.setValues(gdsPropValueSV
						.queryPropValuesByPropId(prop.getId(),
								GdsConstants.Commons.STATUS_VALID));
				searchProps.add(gdsPropRespDTO);
			}
		}
		relationRespDTO.setSearchParams(searchProps);
		return relationRespDTO;
	}

	@Override
	public boolean queryIsInUse(GdsCatg2PropReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO);
		paramNullCheck(reqDTO.getPropId(), "reqDTO.propId");
		GdsCatg2PropCriteria criteria = new GdsCatg2PropCriteria();
		Criteria c = criteria.createCriteria();
		initCriteria(c, reqDTO);
		List<String> catgCodes = gdsCatg2PropManualMapper
				.queryRelationCatgCodesByExample(criteria);
		if (CollectionUtils.isEmpty(catgCodes)) {
			return false;
		}

		GdsCategoryCriteria gdsCatgCriteria = new GdsCategoryCriteria();
		GdsCategoryCriteria.Criteria c2 = gdsCatgCriteria.createCriteria();
		c2.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		if (1 == catgCodes.size()) {
			c2.andCatgCodeEqualTo(catgCodes.get(0));
		} else {
			c2.andCatgCodeIn(catgCodes);
		}

		return gdsCategoryMapper.countByExample(gdsCatgCriteria) > 0;

	}

	@Override
	public boolean executePropConfig(GdsCatg2PropReqDTO reqDTO)
			throws BusinessException {

		paramNullCheck(reqDTO);
		paramCheck(new Object[] { reqDTO.getCatgCode(), reqDTO.getPropId() },
				new String[] { "catgCode", "propId" });
		configValueChk(reqDTO);

		GdsCatg2Prop record = new GdsCatg2Prop();
		ObjectCopyUtil.copyObjValue(reqDTO, record, null, true);
		preUpdate(reqDTO, record);

		GdsCatg2PropCriteria criteria = new GdsCatg2PropCriteria();
		Criteria c = criteria.createCriteria();
		c.andCatgCodeEqualTo(reqDTO.getCatgCode());
		c.andPropIdEqualTo(reqDTO.getPropId());
		c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

		return gdsCatg2PropMapper.updateByExampleSelective(record, criteria) > 0;
	}

	@Override
	public List<String> queryRelationCatgCodesByExample(
			GdsCatg2PropReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO);
		paramNullCheck(reqDTO.getPropId(), "reqDTO.propId");
		GdsCatg2PropCriteria criteria = new GdsCatg2PropCriteria();
		Criteria c = criteria.createCriteria();
		initCriteria(c, reqDTO);
		if (null != reqDTO.getPropId()) {
			c.andPropIdEqualTo(reqDTO.getPropId());
		}
		if (CollectionUtils.isNotEmpty(reqDTO.getPropIds())) {
			if (1 == reqDTO.getPropIds().size()) {
				c.andPropIdEqualTo(reqDTO.getPropId());
			} else {
				c.andPropIdIn(reqDTO.getPropIds());
			}
		}
		return gdsCatg2PropManualMapper
				.queryRelationCatgCodesByExample(criteria);
	}

	/*
	 * 创建是否基础属性配置条件.<br/>
	 * 
	 * @author liyong7
	 * 
	 * @param catgCode
	 * 
	 * @param propId
	 * 
	 * @param isBasic
	 * 
	 * @param isRequired
	 * 
	 * @param updateStaff
	 * 
	 * @return
	 * 
	 * @since JDK 1.6
	 */
	private GdsCatg2PropReqDTO createConfigCondition(String catgCode,
			Long propId, String isBasic, String isRequired, String isSearch,
			Long updateStaff) {
		GdsCatg2PropReqDTO dto = new GdsCatg2PropReqDTO();
		dto.setIfBasic(isBasic);
		dto.setIfHaveto(isRequired);
		dto.setIfSearch(isSearch);
		dto.setUpdateStaff(updateStaff);
		dto.setUpdateTime(now());
		dto.setStatus(GdsConstants.Commons.STATUS_VALID);
		dto.setCatgCode(catgCode);
		dto.setPropId(propId);
		return dto;
	}

	/*
	 * 初始化分页查询条件. <br/>
	 * 
	 * @author liyong7
	 * 
	 * @param criteria
	 * 
	 * @param dto
	 * 
	 * @since JDK 1.6
	 */
	private Criteria initCriteria(Criteria c, GdsCatg2PropReqDTO dto) {
		if (StringUtils.hasText(dto.getCatgCode())) {
			c.andCatgCodeEqualTo(dto.getCatgCode());
		}
		if (StringUtils.hasText(dto.getStatus())) {
			c.andStatusEqualTo(dto.getStatus());
		}
		if (StringUtils.hasText(dto.getIfBasic())) {
			c.andIfBasicEqualTo(dto.getIfBasic());
		}
		if (StringUtils.hasText(dto.getIfHaveto())) {
			c.andIfHavetoEqualTo(dto.getIfHaveto());
		}
		if (StringUtils.hasText(dto.getIfSearch())) {
			c.andIfSearchEqualTo(dto.getIfSearch());
		}
		if (null != dto.getPropId()) {
			c.andPropIdEqualTo(dto.getPropId());
		}
		return c;
	}

	/**
	 * 查询配置的所有属性 queryConfigedProps:(这里用一句话描述这个方法的作用). <br/>
	 * 
	 * @author linwb3
	 * @param catgCode
	 * @param status
	 * @return
	 * @since JDK 1.6
	 */
	@Override
	public List<GdsCatg2Prop> queryConfigedProps(GdsCatg2PropReqDTO reqDTO)
			throws BusinessException {
		GdsCatg2PropCriteria catg2PropCriteria = new GdsCatg2PropCriteria();

		if (!reqDTO.isIfGdsInputQuery()) {
			GdsCatg2PropCriteria.Criteria criteria = catg2PropCriteria
					.createCriteria();
			initCriteria(reqDTO, criteria);
		} else {
			GdsCatg2PropCriteria.Criteria criteria = catg2PropCriteria
					.createCriteria();
			criteria.andIfGdsInputIsNull();
			initCriteria(reqDTO, criteria);

			GdsCatg2PropCriteria.Criteria criteria2 = catg2PropCriteria
					.createCriteria();
			criteria.andIfGdsInputEqualTo(GdsConstants.Commons.STATUS_VALID);
			initCriteria(reqDTO, criteria2);
			catg2PropCriteria.or(criteria2);
		}

		return gdsCatg2PropMapper.selectByExample(catg2PropCriteria);
	}

	private void initCriteria(GdsCatg2PropReqDTO reqDTO,
			GdsCatg2PropCriteria.Criteria criteria) {
		criteria.andCatgCodeEqualTo(reqDTO.getCatgCode());
		if (StringUtil.isNotBlank(reqDTO.getIfBasic())) {
			criteria.andIfBasicEqualTo(reqDTO.getIfBasic());
		}
		if (reqDTO.getPropId() != null) {
			criteria.andPropIdEqualTo(reqDTO.getPropId());
		}
		if (reqDTO.getIfSearch() != null) {
			criteria.andIfSearchEqualTo(reqDTO.getIfSearch());
		}
		if (reqDTO.isIfGdsInputQuery()) {
			// 如果是录入，不需要过滤次状态
		} else if (reqDTO.getIfGdsInput() != null) {
			criteria.andIfGdsInputEqualTo(reqDTO.getIfGdsInput());
		}
		if (reqDTO.getIfHaveto() != null) {
			criteria.andIfHavetoEqualTo(reqDTO.getIfHaveto());
		}
		if (StringUtil.isBlank(reqDTO.getStatus())) {
			criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		} else {
			criteria.andStatusEqualTo(reqDTO.getStatus());
		}

	}

	/**
	 * 
	 * queryConfigedPropsWithParents:(查询对应属性配置的属性以及顶级父类配置的属性 如果本机和父级重复配置，以本级为准). <br/>
	 * 
	 * @author linwb3
	 * @param catgCode
	 * @param propId
	 * @param ifBastic
	 * @param status
	 * @return
	 * @since JDK 1.6
	 */
	@Override
	public List<GdsCatg2Prop> queryConfigedPropsWithParents(
			GdsCatg2PropReqDTO reqDTO) throws BusinessException {
		String catgCode = reqDTO.getCatgCode();
		List<GdsCatg2Prop> gdsCatg2Props = queryConfigedProps(reqDTO);
		if (CollectionUtils.isEmpty(gdsCatg2Props)) {
			gdsCatg2Props = new ArrayList<GdsCatg2Prop>();
		}
		Map<Long, String> maps = new HashMap<Long, String>();
		for (GdsCatg2Prop gdsCatg2Prop : gdsCatg2Props) {
			maps.put(gdsCatg2Prop.getPropId(), "");
		}

		/**
		 * 是否包含父分类的属性
		 */
		List<GdsCategoryRespDTO> roots = gdsCategorySV
				.queryCategoryTraceUpon(catgCode);
		if (CollectionUtils.isNotEmpty(roots)) {
			for (int i = roots.size()-1; i >= 0; i--) {
				GdsCategoryRespDTO root = roots.get(i);
				String rootCatgCode = root.getCatgCode();
				reqDTO.setCatgCode(rootCatgCode);
				List<GdsCatg2Prop> rootCatg2Props = queryConfigedProps(reqDTO);
				if (CollectionUtils.isNotEmpty(rootCatg2Props)) {
					for (GdsCatg2Prop gdsCatg2Prop : rootCatg2Props) {
						if (!maps.containsKey(gdsCatg2Prop.getPropId())) {
							gdsCatg2Props.add(gdsCatg2Prop);
							maps.put(gdsCatg2Prop.getPropId(), "");
						}
					}
				}
			}

		}
		return gdsCatg2Props;
	}

	private void configValueChk(GdsCatg2PropReqDTO reqDTO) {
		StringBuilder msg = new StringBuilder();

		if (StringUtil.isNotBlank(reqDTO.getIfBasic())) {
			if (!GdsConstants.Commons.STATUS_VALID.equals(reqDTO.getIfBasic())
					&& !GdsConstants.Commons.STATUS_INVALID.equals(reqDTO
							.getIfBasic())) {
				msg.append("ifBasic,");
			}
		}

		if (StringUtil.isNotBlank(reqDTO.getIfGdsInput())) {
			if (!GdsConstants.Commons.STATUS_VALID.equals(reqDTO
					.getIfGdsInput())
					&& !GdsConstants.Commons.STATUS_INVALID.equals(reqDTO
							.getIfGdsInput())) {
				msg.append("ifGdsInput,");
			}
		}

		if (StringUtil.isNotBlank(reqDTO.getIfHaveto())) {
			if (!GdsConstants.Commons.STATUS_VALID.equals(reqDTO.getIfHaveto())
					&& !GdsConstants.Commons.STATUS_INVALID.equals(reqDTO
							.getIfHaveto())) {
				msg.append("ifHaveto,");
			}
		}

		if (StringUtil.isNotBlank(reqDTO.getIfSearch())) {
			if (!GdsConstants.Commons.STATUS_VALID.equals(reqDTO.getIfSearch())
					&& !GdsConstants.Commons.STATUS_INVALID.equals(reqDTO
							.getIfSearch())) {
				msg.append("ifSearch,");
			}
		}

		if (msg.length() > 0) {
			msg.deleteCharAt(msg.length() - 1);
			throw new BusinessException(
					GdsErrorConstants.Commons.ERROR_GOODS_200098,
					new String[] { msg.toString() });
		}

	}

}
