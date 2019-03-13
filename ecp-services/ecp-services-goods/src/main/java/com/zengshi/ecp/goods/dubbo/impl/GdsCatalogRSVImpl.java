package com.zengshi.ecp.goods.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalogRSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatalogSV;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

public class GdsCatalogRSVImpl extends AbstractRSVImpl implements IGdsCatalogRSV {
	
	@Resource(name="gdsCatalogSV")
	private IGdsCatalogSV gdsCatalogSV;
	

	@Override
	public void saveGdsCatalog(GdsCatalogReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, true, "reqDTO");
		paramCheck(new Object[]{reqDTO.getCatlogName(),reqDTO.getCatlogCode()}, new String[]{"reqDTO.CatalogName","reqDTO.CatalogCode"});
		if(StringUtil.isBlank(reqDTO.getIfDefault())){
			reqDTO.setIfDefault(GdsConstants.Commons.STATUS_INVALID);
		}
		if(StringUtil.isBlank(reqDTO.getCatlogType())){
			reqDTO.setCatlogType(GdsConstants.GdsCatlog.CATLOG_TYPE_1);
		}
		// 设置状态为待审核 .
		reqDTO.setStatus(GdsConstants.Commons.STATUS_PENDING_AUDIT);
		gdsCatalogSV.saveGdsCatalog(reqDTO);
	}

	@Override
	public PageResponseDTO<GdsCatalogRespDTO> queryGdsCatalogRespDTOPaging(
			GdsCatalogReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		return gdsCatalogSV.queryGdsCatalogRespDTOPaging(reqDTO);
	}

	@Override
	public void editGdsCatalog(GdsCatalogReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		gdsCatalogSV.editGdsCatalog(reqDTO);
	}

	@Override
	public boolean queryExist(GdsCatalogReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		return gdsCatalogSV.queryExist(reqDTO);
	}

	@Override
	public void updateDefaultCatalog(GdsCatalogReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		gdsCatalogSV.updateDefaultCatalog(reqDTO);
	}

	@Override
	public void batchAudit(GdsCatalogReqDTO reqDTO) throws BusinessException {
		gdsCatalogSV.batchAudit(reqDTO);
	}

	@Override
	public void batchDelete(GdsCatalogReqDTO reqDTO) throws BusinessException {
		gdsCatalogSV.batchDelete(reqDTO);
	}

	@Override
	public void batchEnable(GdsCatalogReqDTO reqDTO) throws BusinessException {
		gdsCatalogSV.batchEnable(reqDTO);
	}

	@Override
	public void executeCleanDefaulCatalog(GdsCatalogReqDTO reqDTO)
			throws BusinessException {
		gdsCatalogSV.executeCleanDefaulCatalog(reqDTO);
	}

	@Override
	public boolean queryExistDefaultCatalog(GdsCatalogReqDTO reqDTO)
			throws BusinessException {
		return gdsCatalogSV.queryExistDefaultCatalog(reqDTO);
	}

	@Override
	public GdsCatalogRespDTO queryGdsCatalogByPK(GdsCatalogReqDTO reqDTO)
			throws BusinessException {
		return gdsCatalogSV.queryGdsCatalogByPK(reqDTO);
	}

	@Override
	public GdsCatalogRespDTO queryDefaultCatalog(BaseInfo baseInfo)
			throws BusinessException {
		return gdsCatalogSV.queryDefaultGdsCatalog();
	}
	
	

}

