/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsTypeImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.common.impl 
 * Date:2015年8月11日上午11:03:29 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.service.common.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.util.StringUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsTypeMapper;
import com.zengshi.ecp.goods.dao.model.GdsType;
import com.zengshi.ecp.goods.dao.model.GdsTypeCriteria;
import com.zengshi.ecp.goods.dao.model.GdsTypeCriteria.Criteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.fastjson.JSON;

public class GdsTypeSVImpl extends AbstractSVImpl implements IGdsTypeSV {

	@Resource
	private GdsTypeMapper gdsTypeMapper;
	@Resource(name = "seq_gds_type")
	private PaasSequence seqGdsType;
	private static final String DEFAULT_ORDER_BY = "SORT_NO ASC";

	@Override
	public int saveGdsType(GdsTypeReqDTO reqDTO) throws BusinessException {
		GdsType record = new GdsType();
		ObjectCopyUtil.copyObjValue(reqDTO, record, null, false);
		preInsert(reqDTO, record);
		record.setStatus(GdsConstants.Commons.STATUS_VALID);
		return saveGdsType(record);
	}

	@Override
	public int editGdsType(GdsTypeReqDTO reqDTO) throws BusinessException {
		GdsType record = new GdsType();
		ObjectCopyUtil.copyObjValue(reqDTO, record, null, false);
		preUpdate(reqDTO, record);
		record.setStatus(GdsConstants.Commons.STATUS_VALID);
		int i = gdsTypeMapper.updateByPrimaryKey(record);
		CacheUtil.delItem(KEY_GDSTYPELIST);
		CacheUtil.delItem(GdsConstants.GdsType.GDS_TYPE_KEY_PREFIX + record.getId());
		return i;
	}

	/**
	 * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV#saveGdsType(com.zengshi.ecp.goods.dubbo.dto.GdsTypeDTO)
	 */
	@Override
	public int saveGdsType(GdsType gdsType) throws BusinessException {
		gdsType.setId(seqGdsType.nextValue());
		int i = gdsTypeMapper.insert(gdsType);
		CacheUtil.delItem(KEY_GDSTYPELIST);
		return i;
	}

	@Override
	public GdsTypeRespDTO queryGdsTypeByPK(GdsTypeReqDTO reqDTO) throws BusinessException {
		GdsType gdsType = queryGdsTypeByPK(reqDTO.getId());
		if (null != gdsType) {
			GdsTypeRespDTO respDTO = new GdsTypeRespDTO();
			copyGdsType2Resp(gdsType, respDTO);
			return respDTO;
		}
		return null;
	}

	/**
	 * 
	 * TODO 根据价格类型获取价格编码.
	 * 
	 * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV#queryTypeCodeById(java.lang.Long)
	 */
	@Override
	public String queryTypeCodeById(Long id) throws BusinessException {
		Object object = CacheUtil.getItem(GdsConstants.GdsType.GDS_TYPE_KEY_PREFIX + id);
		if (object == null) {
			GdsType gdsType = gdsTypeMapper.selectByPrimaryKey(id);
			if (gdsType != null) {
				CacheUtil.addItem(GdsConstants.GdsType.GDS_TYPE_KEY_PREFIX + id, gdsType);
				return gdsType.getTypeCode();
			}
		}else{
			return ((GdsType) object).getTypeCode();
		}
		return null;
	}
	@Override
	public GdsType queryGdsTypeModelByPKFromCache(Long id) throws BusinessException {
		Object object = CacheUtil.getItem(GdsConstants.GdsType.GDS_TYPE_KEY_PREFIX + id);
		if (object == null) {
			GdsType gdsType = gdsTypeMapper.selectByPrimaryKey(id);
			if (gdsType != null) {
				CacheUtil.addItem(GdsConstants.GdsType.GDS_TYPE_KEY_PREFIX + id, gdsType);
			}
			return gdsType;
		}
		return (GdsType) object;
	}

	@Override
	public GdsTypeRespDTO queryGdsTypeByPKFromCache(Long id) throws BusinessException {
		GdsTypeRespDTO resp = null;
		GdsType gdsType = queryGdsTypeModelByPKFromCache(id);
		if (gdsType != null) {
			resp = new GdsTypeRespDTO();
			copyGdsType2Resp(gdsType, resp);
			return resp;
		}
		return null;
	}

	/**
	 * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV#queryGdsTypeById(long)
	 */
	@Override
	public GdsType queryGdsTypeByPK(Long id) throws BusinessException {
		GdsType gdsType = gdsTypeMapper.selectByPrimaryKey(id);
		return gdsType;
	}

	@Override
	public GdsType queryGdsTypeByPK(Long id, String... status) throws BusinessException {
		GdsTypeCriteria gdsTypeCriteria = new GdsTypeCriteria();

		GdsTypeCriteria.Criteria criteria = gdsTypeCriteria.createCriteria();
		criteria.andIdEqualTo(id);
		if (!ArrayUtils.isEmpty(status)) {
			criteria.andStatusEqualTo(status[0]);
		} else {
			criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		}
		List<GdsType> gdsType = gdsTypeMapper.selectByExample(gdsTypeCriteria);
		if (CollectionUtils.isNotEmpty(gdsType)) {
			return gdsType.get(0);
		}
		return null;
	}

	@Override
	public GdsType queryGdsTypeByCode(String code, String... status) throws BusinessException {
		GdsTypeCriteria gdsTypeCriteria = new GdsTypeCriteria();

		GdsTypeCriteria.Criteria criteria = gdsTypeCriteria.createCriteria();
		criteria.andTypeCodeEqualTo(code);
		if (!ArrayUtils.isEmpty(status) && status[0] != null) {
			criteria.andStatusEqualTo(status[0]);
		} else {
			criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		}
		List<GdsType> gdsType = gdsTypeMapper.selectByExample(gdsTypeCriteria);
		if (CollectionUtils.isNotEmpty(gdsType)) {
			return gdsType.get(0);
		}
		return null;
	}

	/**
	 * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV#queryGdsTypeList(com.zengshi.ecp.goods.dubbo.dto.GdsTypeDTO)
	 */
	@Override
	public List<GdsTypeRespDTO> queryGdsTypeList() throws BusinessException {
		GdsTypeCriteria criteria = new GdsTypeCriteria();
		criteria.setOrderByClause(DEFAULT_ORDER_BY);
		Criteria c = criteria.createCriteria();
		c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		List<GdsType> gdsTypes = gdsTypeMapper.selectByExample(criteria);
		List<GdsTypeRespDTO> types = new ArrayList<GdsTypeRespDTO>();
		if (!CollectionUtils.isEmpty(gdsTypes)) {
			for (GdsType gdsType : gdsTypes) {
				GdsTypeRespDTO type = new GdsTypeRespDTO();
				ObjectCopyUtil.copyObjValue(gdsType, type, null, false);
				types.add(type);
			}
		} else {
			return null;
		}
		return types;
	}

	@Override
	public List<GdsTypeRespDTO> queryGdsTypeListFromCache() throws BusinessException {
		if (CacheUtil.exists(KEY_GDSTYPELIST)) {
			List<String> list = CacheUtil.getItemFromList(KEY_GDSTYPELIST);
			List<GdsTypeRespDTO> retlist = new ArrayList<GdsTypeRespDTO>();
			for (String s : list) {
				if (org.apache.commons.lang3.StringUtils.isNotBlank(s)) {
					GdsTypeRespDTO gdsTypeRespDTO = JSON.parseObject(s, GdsTypeRespDTO.class);
					retlist.add(gdsTypeRespDTO);
				}
			}
			Collections.sort(retlist);
			return retlist;
		} else {
			// 缓存项不存在就查询并放入缓存
			List<GdsTypeRespDTO> list = this.queryGdsTypeList();
			if (CollectionUtils.isNotEmpty(list)) {
				for (GdsTypeRespDTO gdsTypeRespDTO : list) {
					CacheUtil.addItemToList(KEY_GDSTYPELIST, JSON.toJSONString(gdsTypeRespDTO));
				}
			} else {
				// 空串标志
				CacheUtil.addItemToList(KEY_GDSTYPELIST, "");
			}
			return list;
		}
	}

	/**
	 * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV#editGdsType(com.zengshi.ecp.goods.dao.model.GdsType)
	 */
	@Override
	public int editGdsType(GdsType gdsType, Long updateStaff) throws BusinessException {
		gdsType.setUpdateStaff(updateStaff);
		gdsType.setUpdateTime(now());
		int i = gdsTypeMapper.updateByPrimaryKeySelective(gdsType);
		CacheUtil.delItem(KEY_GDSTYPELIST);
		CacheUtil.delItem(GdsConstants.GdsType.GDS_TYPE_KEY_PREFIX + gdsType.getId());
		return i;
	}

	/**
	 * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV#isExist(java.lang.String,
	 *      java.lang.String[])
	 */
	@Override
	public boolean queryExist(String typeName, String... status) throws BusinessException {
		return queryExist(typeName, null, status);
	}

	@Override
	public boolean queryExist(String typeName, List<Long> excludeIds, String... status) throws BusinessException {
		if (!StringUtils.hasText(typeName)) {
			throw new BusinessException(GdsErrorConstants.GdsType.ERROR_GOODS_TYPE_200101, new String[] { "typeName" });
		}
		GdsTypeCriteria criteria = new GdsTypeCriteria();
		Criteria c = criteria.createCriteria();
		c.andTypeNameEqualTo(typeName);
		initStatusCriteria(c, status);
		if (!CollectionUtils.isEmpty(excludeIds))
			switch (excludeIds.size()) {
			case 1:
				c.andIdNotEqualTo(excludeIds.get(0));
				break;
			default:
				c.andIdNotIn(excludeIds);
				break;
			}
		return gdsTypeMapper.countByExample(criteria) > 0;
	}

	/**
	 * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV#disableGdsType(java.lang.Long)
	 */
	@Override
	public int deleteGdsType(long id, Long updateStaff) throws BusinessException {

		try {
			GdsType gt = gdsTypeMapper.selectByPrimaryKey(id);
			if (gt == null) {
				throw new BusinessException(GdsErrorConstants.GdsType.ERROR_GOODS_TYPE_200103);
			} else {
				gt.setStatus(GdsConstants.Commons.STATUS_INVALID);
				if (null != updateStaff) {
					gt.setUpdateStaff(updateStaff);
				}
				gt.setUpdateTime(now());
				int i = gdsTypeMapper.updateByPrimaryKeySelective(gt);
				CacheUtil.delItem(KEY_GDSTYPELIST);
				CacheUtil.delItem(GdsConstants.GdsType.GDS_TYPE_KEY_PREFIX + id);
				return i;
			}
		} catch (DataAccessException e) {
			LogUtil.error(MODULE, "禁用商品分类出现异常", e);
			throw new BusinessException(GdsErrorConstants.GdsType.ERROR_GOODS_TYPE_200105);
		}
	}

	@Override
	public int executeEnableGdsType(Long id, Long updateStaff) throws BusinessException {
		try {
			GdsType gt = gdsTypeMapper.selectByPrimaryKey(id);
			if (gt == null) {
				throw new BusinessException(GdsErrorConstants.GdsType.ERROR_GOODS_TYPE_200103);
			} else {
				gt.setStatus(GdsConstants.Commons.STATUS_VALID);
				if (null != updateStaff) {
					gt.setUpdateStaff(updateStaff);
				}
				gt.setUpdateTime(now());
				int i = gdsTypeMapper.updateByPrimaryKeySelective(gt);
				CacheUtil.delItem(KEY_GDSTYPELIST);
				CacheUtil.delItem(GdsConstants.GdsType.GDS_TYPE_KEY_PREFIX + id);
				return i;
			}
		} catch (DataAccessException e) {
			LogUtil.error(MODULE, "启用商品分类出现异常", e);
			throw new BusinessException(GdsErrorConstants.GdsType.ERROR_GOODS_TYPE_200108);
		}
	}

	@Override
	public PageResponseDTO<GdsTypeRespDTO> queryGdsTypeRespDTOPaging(GdsTypeReqDTO dto) throws BusinessException {

		GdsTypeCriteria criteria = new GdsTypeCriteria();
		if (dto != null) {
			criteria.setLimitClauseStart(dto.getStartRowIndex());
			criteria.setLimitClauseCount(dto.getPageSize());
		}
		criteria.setOrderByClause(DEFAULT_ORDER_BY);
		if (dto != null) {
			initCriteria(criteria, dto);
		}
		return super.queryByPagination(dto, criteria, false, new GdsTypeRespDTOPaginationCallback());
	}

	/**
	 * 商品类型分页查询回调实现类。 Title: ECP <br>
	 * Project Name:ecp-services-goods <br>
	 * Description: <br>
	 * Date:2015年8月17日下午3:36:33 <br>
	 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
	 * 
	 * @author liyong7
	 * @version GdsTypeSVImpl
	 * @since JDK 1.6
	 */
	protected class GdsTypeRespDTOPaginationCallback extends PaginationCallback<GdsType, GdsTypeRespDTO> {

		@Override
		public List<GdsType> queryDB(BaseCriteria criteria) {
			return gdsTypeMapper.selectByExample((GdsTypeCriteria) criteria);
		}

		@Override
		public long queryTotal(BaseCriteria criteria) {
			return gdsTypeMapper.countByExample((GdsTypeCriteria) criteria);
		}

		@Override
		public GdsTypeRespDTO warpReturnObject(GdsType t) {
			GdsTypeRespDTO dto = new GdsTypeRespDTO();
			ObjectCopyUtil.copyObjValue(t, dto, null, true);
			return dto;
		}

	}

	/*
	 * 根据请求DTO设置查询条件. <br/>
	 * 
	 * @author liyong7
	 * 
	 * @param criteria
	 * 
	 * @param dto
	 * 
	 * @since JDK 1.6
	 */
	private void initCriteria(GdsTypeCriteria criteria, GdsTypeReqDTO dto) {
		Criteria c = criteria.createCriteria();
		if (StringUtils.hasText(dto.getTypeCode())) {
			c.andTypeCodeLike("%" + dto.getTypeCode() + "%");
		}
		if (StringUtils.hasText(dto.getTypeName())) {
			c.andTypeNameLike("%" + dto.getTypeName() + "%");
		}
		if (StringUtils.hasText(dto.getStatus())) {
			c.andStatusEqualTo(dto.getStatus());
		}
	}

	/**
	 * 
	 * copyGdsInfo2Resp:(将Info对象转为Resp对象). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @param gdsInfoRespDTO
	 * @since JDK 1.6
	 */
	private void copyGdsType2Resp(GdsType gdsType, GdsTypeRespDTO gdsTypeRespDTO) {
		gdsTypeRespDTO.setId(gdsType.getId());
		gdsTypeRespDTO.setIfBuyonce(gdsType.getIfBuyonce());
		gdsTypeRespDTO.setIfEntitycode(gdsType.getIfEntitycode());
		gdsTypeRespDTO.setIfFree(gdsType.getIfFree());
		gdsTypeRespDTO.setIfNeedstock(gdsType.getIfNeedstock());
		gdsTypeRespDTO.setSortNo(gdsType.getSortNo());
		gdsTypeRespDTO.setStatus(gdsType.getStatus());
		gdsTypeRespDTO.setTypeCode(gdsType.getTypeCode());
		gdsTypeRespDTO.setTypeName(gdsType.getTypeName());
		gdsTypeRespDTO.setTypeDesc(gdsType.getTypeDesc());
		gdsTypeRespDTO.setCreateStaff(gdsType.getCreateStaff());
		gdsTypeRespDTO.setCreateTime(gdsType.getCreateTime());
		gdsTypeRespDTO.setUpdateStaff(gdsType.getUpdateStaff());
		gdsTypeRespDTO.setUpdateTime(gdsType.getUpdateTime());
	}

}
