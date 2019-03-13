package com.zengshi.ecp.goods.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatalog2SiteSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-10-20下午11:01:52  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class GdsCatalog2SiteRSVImpl extends AbstractRSVImpl implements
		IGdsCatalog2SiteRSV {
	
	@Resource
	private IGdsCatalog2SiteSV gdsCatalog2SiteSV;

	@Override
	public void createCatalog2Site(GdsCatalog2SiteReqDTO reqDTO)
			throws BusinessException {
		gdsCatalog2SiteSV.createCatalog2Site(reqDTO);
	}

	@Override
	public void updateCatalog2Site(GdsCatalog2SiteReqDTO reqDTO)
			throws BusinessException {
		gdsCatalog2SiteSV.updateCatalog2Site(reqDTO);
	}

	@Override
	public PageResponseDTO<GdsCatalogRespDTO> queryCatalogPaging(
			GdsCatalog2SiteReqDTO reqDTO) throws BusinessException {
		return gdsCatalog2SiteSV.queryCatalogPaging(reqDTO);
	}

	@Override
	public boolean queryIsExist(GdsCatalog2SiteReqDTO reqDTO)
			throws BusinessException {
		return gdsCatalog2SiteSV.queryIsExist(reqDTO);
	}

	@Override
	public List<GdsCatalogRespDTO> queryRelationBySiteId(
			GdsCatalog2SiteReqDTO reqDTO) throws BusinessException {
		return gdsCatalog2SiteSV.queryRelationBySiteId(reqDTO);
	}

	@Override
	public boolean queryIsRelationWithSite(GdsCatalog2SiteReqDTO reqDTO)
			throws BusinessException {
		return gdsCatalog2SiteSV.queryIsRelationWithSite(reqDTO);
	}

	@Override
	public List<GdsCatalog2SiteRespDTO> querySiteListByCataLogId(
			GdsCatalog2SiteReqDTO reqDTO) throws BusinessException {
		// TODO Auto-generated method stub
		try{
			return gdsCatalog2SiteSV.querySiteListByCataLogId(reqDTO);
		}catch(BusinessException e1){
			throw e1;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException(GdsErrorConstants.GdsCatlog.ERROR_GOODS_CATLOG_200603);
		}
	}

	@Override
	public PageResponseDTO<GdsCatalog2SiteRespDTO> queryPrdRelBySiteId(GdsCatalog2SiteReqDTO reqDTO) {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getSiteId()}, 
				   new String[]{"reqDTO.siteId"});
		reqDTO.setCatlogId(null);
		try {
			return gdsCatalog2SiteSV.queryPrdRelBySiteId(reqDTO);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			throw e1;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException(GdsErrorConstants.GdsCatlog.ERROR_GOODS_CATLOG_200603);
		}
	}

	@Override
	public void batchSaveGdsCatlog2Site(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException {
		// TODO Auto-generated method stub
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getSiteId(),reqDTO.getCatlogIds()}, 
				   new String[]{"reqDTO.siteId","reqDTO.catlogIds"});
		gdsCatalog2SiteSV.batchSaveGdsCatlog2Site(reqDTO);
	}

	@Override
	public void deleteRelationBySiteId(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException {
		// TODO Auto-generated method stub
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getSiteId()}, 
				   new String[]{"reqDTO.siteId"});
		reqDTO.setCatlogId(null);
		gdsCatalog2SiteSV.deleteGdsCatlog2Site(reqDTO);
	}
	
	

}

