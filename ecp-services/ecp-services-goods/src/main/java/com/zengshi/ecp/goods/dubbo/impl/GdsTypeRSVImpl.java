/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsTypeRSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.impl 
 * Date:2015年8月27日下午5:49:32 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.dubbo.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dao.model.GdsType;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsTypeRSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月27日下午5:49:32 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public class GdsTypeRSVImpl extends AbstractRSVImpl implements IGdsTypeRSV {

	@Resource(name = "gdsTypeSV")
	private IGdsTypeSV gdsTypeSV;

	/**
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsTypeRSV#createGdsType(com.zengshi.ecp.goods.dubbo.dto.GdsTypeReqDTO)
	 */
	@Override
	public void createGdsType(GdsTypeReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, true);
		paramNullCheck(reqDTO.getTypeName(), false, "reqDTO.typeName");
		paramNullCheck(reqDTO.getTypeCode(), false, "reqDTO.typeCode");
		if (gdsTypeSV.queryExist(reqDTO.getTypeName(), GdsConstants.Commons.STATUS_VALID)) {
			throw new BusinessException(GdsErrorConstants.GdsType.ERROR_GOODS_TYPE_200106);
		}
		int i = gdsTypeSV.saveGdsType(reqDTO);
		if (i <= 0) {
			LogUtil.error(MODULE, "返回影响行数小等于0,商品类型创建失败!");
			throw new BusinessException(GdsErrorConstants.GdsType.ERROR_GOODS_TYPE_200106);
		}

	}

	/**
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsTypeRSV#editGdsType(com.zengshi.ecp.goods.dubbo.dto.GdsTypeReqDTO)
	 */
	@Override
	public void editGdsType(GdsTypeReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, true);
		paramNullCheck(reqDTO.getTypeName(), false, "reqDTO.typeName");
		paramNullCheck(reqDTO.getTypeCode(), false, "reqDTO.typeCode");
		int i = gdsTypeSV.editGdsType(reqDTO);
		if (i <= 0) {
			throw new BusinessException(GdsErrorConstants.GdsType.ERROR_GOODS_TYPE_200101);
		}

		try {
			CacheUtil.delItem(IGdsTypeSV.KEY_GDSTYPELIST);
			if (reqDTO.getId() != null) {
				CacheUtil.delItem(GdsConstants.GdsType.GDS_TYPE_KEY_PREFIX + reqDTO.getId());
			}
		} catch (Exception e) {
			LogUtil.error(MODULE, "del gdsType cache failed");
		}
	}

	/**
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsTypeRSV#queryGdsTypeByPK(com.zengshi.ecp.goods.dubbo.dto.GdsTypeReqDTO)
	 */
	@Override
	public GdsTypeRespDTO queryGdsTypeByPK(LongReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, true);
		paramNullCheck(reqDTO.getId(), false, "reqDTO.id");
		GdsType record = gdsTypeSV.queryGdsTypeByPK(reqDTO.getId());
		if (null != record) {
			GdsTypeRespDTO respDTO = new GdsTypeRespDTO();
			ObjectCopyUtil.copyObjValue(record, respDTO, null, false);
			return respDTO;
		}
		return null;
	}

	/**
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsTypeRSV#queryGdsTypePaging(com.zengshi.ecp.goods.dubbo.dto.GdsTypeReqDTO)
	 */
	@Override
	public PageResponseDTO<GdsTypeRespDTO> queryGdsTypePaging(GdsTypeReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, true);
		return gdsTypeSV.queryGdsTypeRespDTOPaging(reqDTO);
	}

	/**
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsTypeRSV#queryAllGdsTypes(com.zengshi.ecp.goods.dubbo.dto.GdsTypeReqDTO)
	 */
	@Override
	public List<GdsTypeRespDTO> queryAllGdsTypes() throws BusinessException {
		return gdsTypeSV.queryGdsTypeList();
	}

	@Override
	public List<GdsTypeRespDTO> queryAllGdsTypesFromCache() throws BusinessException {

		return gdsTypeSV.queryGdsTypeListFromCache();

	}

	/**
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsTypeRSV#isExist(com.zengshi.ecp.goods.dubbo.dto.GdsTypeReqDTO)
	 */
	@Override
	public boolean isExist(GdsTypeReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, true);
		paramNullCheck(reqDTO.getTypeName(), false, "reqDTO.typeName");
		return gdsTypeSV.queryExist(reqDTO.getTypeName(), GdsConstants.Commons.STATUS_VALID);
	}

	@Override
	public boolean existChkBeforeEdit(GdsTypeReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, true);
		paramNullCheck(reqDTO.getTypeName(), false, "reqDTO.typeName");
		paramNullCheck(reqDTO.getId(), false, "reqDTO.id");
		return gdsTypeSV.queryExist(reqDTO.getTypeName(), Arrays.asList(new Long[] { reqDTO.getId() }), GdsConstants.Commons.STATUS_VALID);
	}

	@Override
	public void executeDisableGdsTypeByPK(LongReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, true);
		paramNullCheck(reqDTO.getId(), false, "reqDTO.id");
		gdsTypeSV.deleteGdsType(reqDTO.getId(), reqDTO.getStaff().getId());
		try {
			CacheUtil.delItem(IGdsTypeSV.KEY_GDSTYPELIST);
			CacheUtil.delItem(GdsConstants.GdsType.GDS_TYPE_KEY_PREFIX + reqDTO.getId());
		} catch (Exception e) {
			LogUtil.error(MODULE, "del gdsType cache failed");
		}

	}

	@Override
	public void executeEnableGdsTypeByPK(LongReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, true);
		paramNullCheck(reqDTO.getId(), false, "reqDTO.id");
		gdsTypeSV.executeEnableGdsType(reqDTO.getId(), reqDTO.getStaff().getId());
	}

	@Override
	public GdsTypeRespDTO queryGdsTypeByPKFromCache(LongReqDTO id) throws BusinessException {
		// TODO Auto-generated method stub
		paramNullCheck(id, true);
		paramNullCheck(id.getId(), false, "reqDTO.id");
		return gdsTypeSV.queryGdsTypeByPKFromCache(id.getId());
	}

}
