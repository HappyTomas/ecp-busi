package com.zengshi.ecp.goods.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import com.zengshi.ecp.goods.dao.mapper.common.GdsPriceTypeMapper;
import com.zengshi.ecp.goods.dao.model.GdsPriceType;
import com.zengshi.ecp.goods.dao.model.GdsPriceTypeCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPriceTypeSV;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 价格类型服务实现<br>
 * Date:2015年9月25日上午10:10:38 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsPriceTypeSVImpl extends AbstractSVImpl implements
		IGdsPriceTypeSV {

	@Resource
	private GdsPriceTypeMapper gdsPriceTypeMapper;

	/**
	 * 通过编码获取价格类型 TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsPriceTypeSV#getPriceType(java.lang.String)
	 */
	@Override
	public GdsPriceType getPriceType(String priceTypeCode) {

		GdsPriceTypeCriteria criteria = new GdsPriceTypeCriteria();
		criteria.createCriteria().andPriceTypeCodeEqualTo(priceTypeCode);
		List<GdsPriceType> gdsPriceTypes = gdsPriceTypeMapper
				.selectByExample(criteria);
		if (CollectionUtils.isNotEmpty(gdsPriceTypes)) {
			return gdsPriceTypes.get(0);
		}
		return null;
	}

	/**
	 * 通过主键获取价格类型 TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsPriceTypeSV#getPriceType(java.lang.Long)
	 */
	@Override
	public GdsPriceType getPriceType(Long id) {
		return gdsPriceTypeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 获取所有有效的价格类型 TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsPriceTypeSV#queryAllPriceType()
	 */
	@Override
	public List<GdsPriceType> queryAllPriceType(String status) {
		GdsPriceTypeCriteria gdsPriceTypeCriteria = new GdsPriceTypeCriteria();
		GdsPriceTypeCriteria.Criteria criteria = gdsPriceTypeCriteria
				.createCriteria();
		if (StringUtil.isNotBlank(status)) {
			criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		}
		return gdsPriceTypeMapper.selectByExample(gdsPriceTypeCriteria);
	}
	
	
	
	/**
	 * 获取所有有效的价格类型 TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsPriceTypeSV#queryAllPriceType()
	 */
	@Override
	public List<GdsPriceType> queryAllPriceType(String[] extralCode,String status) {
		GdsPriceTypeCriteria gdsPriceTypeCriteria = new GdsPriceTypeCriteria();
		GdsPriceTypeCriteria.Criteria criteria = gdsPriceTypeCriteria
				.createCriteria();
		if (StringUtil.isNotBlank(status)) {
			criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		}
		
		if(!ArrayUtils.isEmpty(extralCode)){
			List<String> codes=new ArrayList<String>();
			for (int i = 0; i < extralCode.length; i++) {
				codes.add(extralCode[i]);
			}
			criteria.andPriceTypeCodeNotIn(codes);
		}
		return gdsPriceTypeMapper.selectByExample(gdsPriceTypeCriteria);
	}

}
