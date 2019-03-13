package com.zengshi.ecp.goods.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsCatalog2SiteMapper;
import com.zengshi.ecp.goods.dao.model.GdsCatalog2Site;
import com.zengshi.ecp.goods.dao.model.GdsCatalog2SiteCriteria;
import com.zengshi.ecp.goods.dao.model.GdsCatlog2Shop;
import com.zengshi.ecp.goods.dao.model.GdsCatlog2ShopCriteria;
import com.zengshi.ecp.goods.dao.model.GdsCatalog2SiteCriteria.Criteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongListRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsCatalogCacheUtil;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatalog2SiteSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatalogSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: 目录站点关联关系服务实现. <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-10-20下午4:26:19 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public class GdsCatalog2SiteSVImpl extends AbstractSVImpl implements IGdsCatalog2SiteSV {
	@Resource
	private GdsCatalog2SiteMapper gdsCatalog2SiteMapper;
	@Resource
	private IGdsCatalogSV gdsCatalogSV;

	@Override
	public void createCatalog2Site(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException {
		paramCheck(new Object[] { reqDTO.getSiteId(), reqDTO.getValidCatlogIds() },
				new String[] { "reqDTO.siteId,reqDTO.validCatlogIds" });
		List<GdsCatalog2Site> records = buildValidRecord(reqDTO);
		for (GdsCatalog2Site record : records) {
			if (!queryIsExist(record.getSiteId(), record.getCatlogId())) {
				gdsCatalog2SiteMapper.insert(record);
			}
		}
	}

	@Override
	public void updateCatalog2Site(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramNullCheck(reqDTO.getSiteId(), "reqDTO.siteId");

		List<Long> invalidIds = reqDTO.getInvalidCatlogIds();

		// 无效关系清除.
		if (CollectionUtils.isNotEmpty(invalidIds)) {
			GdsCatalog2Site record = new GdsCatalog2Site();
			record.setStatus(GdsConstants.Commons.STATUS_INVALID);
			preUpdate(reqDTO, record);
			GdsCatalog2SiteCriteria example = new GdsCatalog2SiteCriteria();
			Criteria c = example.createCriteria();
			c.andSiteIdEqualTo(reqDTO.getSiteId());
			if (1 == invalidIds.size()) {
				c.andCatlogIdEqualTo(invalidIds.get(0));
			} else {
				c.andCatlogIdIn(invalidIds);
			}
			c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
			gdsCatalog2SiteMapper.updateByExampleSelective(record, example);
		}
		if (CollectionUtils.isNotEmpty(reqDTO.getValidCatlogIds())) {
			createCatalog2Site(reqDTO);
		}
	}

	@Override
	public PageResponseDTO<GdsCatalogRespDTO> queryCatalogPaging(GdsCatalog2SiteReqDTO reqDTO)
			throws BusinessException {
		GdsCatalog2SiteCriteria criteria = new GdsCatalog2SiteCriteria();
		Criteria c = criteria.createCriteria();
		c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		initCriteria(c, reqDTO);
		return super.queryByPagination(reqDTO, criteria, false, new GdsCatalog2SitePaginationCallback());

	}

	@Override
	public boolean queryIsExist(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO);
		paramCheck(new Object[] { reqDTO.getSiteId(), reqDTO.getCatlogId() },
				new String[] { "reqDTO.siteId,reqDTO.catlogId" });
		GdsCatalog2SiteCriteria criteria = new GdsCatalog2SiteCriteria();
		Criteria c = criteria.createCriteria();
		c.andSiteIdEqualTo(reqDTO.getSiteId());
		c.andCatlogIdEqualTo(reqDTO.getCatlogId());
		c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		return gdsCatalog2SiteMapper.countByExample(criteria) > 0;
	}

	@Override
	public boolean queryIsExist(Long siteId, Long catlogId) throws BusinessException {
		paramCheck(new Object[] { siteId, catlogId }, new String[] { "siteId,catlogId" });
		GdsCatalog2SiteCriteria criteria = new GdsCatalog2SiteCriteria();
		Criteria c = criteria.createCriteria();
		c.andSiteIdEqualTo(siteId);
		c.andCatlogIdEqualTo(catlogId);
		c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		return gdsCatalog2SiteMapper.countByExample(criteria) > 0;
	}

	@Override
	public List<GdsCatalogRespDTO> queryRelationBySiteId(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO);
		paramNullCheck(reqDTO.getSiteId(), "reqDTO.siteId");
		List<GdsCatalogRespDTO> lst = new ArrayList<GdsCatalogRespDTO>();
		// GdsCatalogCacheUtil.clearGdsCatalog2SiteCache();
		// 根据站点ID从缓存获取站点与目录关联关系.
		LongListRespDTO cacheResp = getRelationFromCacheBySiteId(reqDTO.getSiteId());
		if (null != cacheResp) {
			if (CollectionUtils.isNotEmpty(cacheResp.getLst())) {
				GdsCatalogReqDTO query = new GdsCatalogReqDTO();
				for (Long catlogId : cacheResp.getLst()) {
					query.setId(catlogId);
					GdsCatalogRespDTO respDTO = gdsCatalogSV.queryGdsCatalogByPK(query);
					if (null != respDTO) {
						if (StringUtil.isEmpty(reqDTO.getCatalogStatus())) {
							lst.add(respDTO);
						} else {
							if (reqDTO.getCatalogStatus().equals(respDTO.getStatus())) {
								lst.add(respDTO);
							}
						}
					}
				}

			}
		} else {
			GdsCatalog2SiteCriteria criteria = new GdsCatalog2SiteCriteria();
			GdsCatalog2SiteCriteria.Criteria c = criteria.createCriteria();
			initCriteria(c, reqDTO);
			List<GdsCatalog2Site> result = gdsCatalog2SiteMapper.selectByExample(criteria);
			cacheResp = new LongListRespDTO();
			if (CollectionUtils.isNotEmpty(result)) {
				for (GdsCatalog2Site record : result) {
					GdsCatalogReqDTO queryCondition = new GdsCatalogReqDTO();
					queryCondition.setId(record.getCatlogId());
					GdsCatalogRespDTO respDTO = gdsCatalogSV.queryGdsCatalogByPK(queryCondition);
					if (respDTO != null) {
						if (StringUtil.isEmpty(reqDTO.getCatalogStatus())) {
							lst.add(respDTO);
							cacheResp.addElement(respDTO.getId());
						} else {
							if (reqDTO.getCatalogStatus().equals(respDTO.getStatus())) {
								lst.add(respDTO);
								cacheResp.addElement(respDTO.getId());
							}
						}
					}
				}
			}
			cacheRelation(reqDTO.getSiteId(), cacheResp);
		}
		return lst;
	}

	@Override
	public boolean queryIsRelationWithSite(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO);
		paramNullCheck(reqDTO.getCatlogId(), "reqDTO.catlogId");
		GdsCatalog2SiteCriteria criteria = new GdsCatalog2SiteCriteria();
		GdsCatalog2SiteCriteria.Criteria c = criteria.createCriteria();
		initCriteria(c, reqDTO);
		return gdsCatalog2SiteMapper.countByExample(criteria) > 0;
	}

	private void cacheRelation(Long siteId, LongListRespDTO respDTO) {
		LogUtil.info(MODULE, "===========开始缓存站点与目录关联关系=========");
		if (null != respDTO) {
			try {
				String json = JSONObject.toJSONString(respDTO);
				CacheUtil.addItem(GdsConstants.CacheKey.CACHE_KEY_CATALOG2SITE_BY_SITEID + siteId, json);
			} catch (Exception e) {
				LogUtil.error(MODULE, "缓存与站点关联目录关系遇到异常!", e);
			}
		}
		LogUtil.info(MODULE, "===========结束缓存站点与目录关联关系=========");
	}

	private LongListRespDTO getRelationFromCacheBySiteId(Long siteId) {
		LogUtil.info(MODULE, "===========开始根据站点ID[siteId]从缓存获取站点与目录关联关系=========");
		LongListRespDTO resp = null;
		if (null != siteId) {
			try {
				String json = String
						.valueOf(CacheUtil.getItem(GdsConstants.CacheKey.CACHE_KEY_CATALOG2SITE_BY_SITEID + siteId));
				if (!"null".equals(json) && StringUtil.isNotBlank(json)) {
					resp = JSONObject.parseObject(json, LongListRespDTO.class);
				}
			} catch (Exception e) {
				LogUtil.error(MODULE, "缓存与站点关联目录关系遇到异常!", e);
			}
		}
		LogUtil.info(MODULE, "===========结束根据站点ID[siteId]从缓存获取站点与目录关联关系=========");
		return resp;
	}

	/**
	 * 
	 * Title: 目录站点分类关联分页查询. <br>
	 * Project Name:ecp-services-goods-server <br>
	 * Description: <br>
	 * Date:2015-10-20下午5:11:08 <br>
	 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
	 * 
	 * @author liyong7
	 * @version GdsCatalog2SiteSVImpl
	 * @since JDK 1.6
	 */
	protected class GdsCatalog2SitePaginationCallback extends PaginationCallback<GdsCatalog2Site, GdsCatalogRespDTO> {

		@Override
		public List<GdsCatalog2Site> queryDB(BaseCriteria criteria) {
			return gdsCatalog2SiteMapper.selectByExample((GdsCatalog2SiteCriteria) criteria);
		}

		@Override
		public long queryTotal(BaseCriteria criteria) {
			return gdsCatalog2SiteMapper.countByExample((GdsCatalog2SiteCriteria) criteria);
		}

		@Override
		public GdsCatalogRespDTO warpReturnObject(GdsCatalog2Site t) {
			GdsCatalogReqDTO queryCondition = new GdsCatalogReqDTO();
			queryCondition.setId(t.getCatlogId());
			return gdsCatalogSV.queryGdsCatalogByPK(queryCondition);
		}

	}

	private List<GdsCatalog2Site> buildValidRecord(GdsCatalog2SiteReqDTO reqDTO) {
		List<GdsCatalog2Site> lst = new ArrayList<GdsCatalog2Site>();
		for (Long id : reqDTO.getValidCatlogIds()) {
			GdsCatalog2Site record = new GdsCatalog2Site();
			record.setSiteId(reqDTO.getSiteId());
			record.setCatlogId(id);
			record.setStatus(GdsConstants.Commons.STATUS_VALID);
			preInsert(reqDTO, record);
			lst.add(record);
		}
		return lst;
	}

	/**
	 * 
	 * 初始化查询条件.
	 * 
	 * @author liyong7
	 * @param c
	 * @param reqDTO
	 * @since JDK 1.6
	 */
	private void initCriteria(Criteria c, GdsCatalog2SiteReqDTO reqDTO) {

		if (null == reqDTO)
			return;
		if (null != reqDTO.getSiteId()) {
			c.andSiteIdEqualTo(reqDTO.getSiteId());
		}
		if (null != reqDTO.getCatlogId()) {
			c.andCatlogIdEqualTo(reqDTO.getCatlogId());
		}
		c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

	}

	public List<GdsCatalog2SiteRespDTO> querySiteListByCataLogId(GdsCatalog2SiteReqDTO reqDTO) throws Exception {

		GdsCatalog2SiteCriteria catalog2SiteCriteria = new GdsCatalog2SiteCriteria();
		GdsCatalog2SiteCriteria.Criteria criteria = catalog2SiteCriteria.createCriteria();
		criteria.andCatlogIdEqualTo(reqDTO.getCatlogId());
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		List<GdsCatalog2Site> gdsCatalog2Sites = gdsCatalog2SiteMapper.selectByExample(catalog2SiteCriteria);
		List<GdsCatalog2SiteRespDTO> catalog2SiteRespDTOs = new ArrayList<GdsCatalog2SiteRespDTO>();
		if (!CollectionUtils.isEmpty(gdsCatalog2Sites)) {
			for (GdsCatalog2Site gdsCatalog2Site : gdsCatalog2Sites) {
				GdsCatalog2SiteRespDTO catalog2SiteRespDTO = new GdsCatalog2SiteRespDTO();

				ObjectCopyUtil.copyObjValue(gdsCatalog2Site, catalog2SiteRespDTO, null, false);
				catalog2SiteRespDTOs.add(catalog2SiteRespDTO);
			}

		}
		return catalog2SiteRespDTOs;

	}

	@Override
	public PageResponseDTO<GdsCatalog2SiteRespDTO> queryPrdRelBySiteId(GdsCatalog2SiteReqDTO reqDTO) throws Exception {
		// TODO Auto-generated method stub
		GdsCatalog2SiteCriteria criteria = new GdsCatalog2SiteCriteria();
		criteria.setLimitClauseStart(reqDTO.getStartRowIndex());
		criteria.setLimitClauseCount(reqDTO.getPageSize());
		GdsCatalog2SiteCriteria.Criteria c = criteria.createCriteria();
		if (null != reqDTO.getSiteId()) {
			c.andSiteIdEqualTo(reqDTO.getSiteId());
		}
		if (null != reqDTO.getCatlogId()) {
			c.andCatlogIdEqualTo(reqDTO.getCatlogId());
		}
		return super.queryByPagination(reqDTO, criteria, false, new GdsCatalog2SiteCallback());
	}

	protected class GdsCatalog2SiteCallback
			extends PaginationCallback<GdsCatalog2Site, GdsCatalog2SiteRespDTO> {

		@Override
		public List<GdsCatalog2Site> queryDB(BaseCriteria criteria) {
			// TODO Auto-generated method stub
			return gdsCatalog2SiteMapper.selectByExample((GdsCatalog2SiteCriteria)criteria);
		}

		@Override
		public long queryTotal(BaseCriteria criteria) {
			// TODO Auto-generated method stub
			return gdsCatalog2SiteMapper.countByExample((GdsCatalog2SiteCriteria)criteria);
		}

		@Override
		public GdsCatalog2SiteRespDTO warpReturnObject(GdsCatalog2Site t) {
			// TODO Auto-generated method stub
			GdsCatalog2SiteRespDTO respDTO = new GdsCatalog2SiteRespDTO();
            ObjectCopyUtil.copyObjValue(t, respDTO, null, true);
            return respDTO;
		}
		
	}

	@Override
	public void batchSaveGdsCatlog2Site(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException {
		// TODO Auto-generated method stub
		GdsCatalog2SiteReqDTO del = new GdsCatalog2SiteReqDTO();
		del.setSiteId(reqDTO.getSiteId());
		this.deleteGdsCatlog2Site(del);
		for(Long catlogId : reqDTO.getCatlogIds()){
			GdsCatalog2SiteReqDTO save = new GdsCatalog2SiteReqDTO();
			save.setSiteId(reqDTO.getSiteId());
			save.setCatlogId(catlogId);
			save.setStatus(GdsConstants.Commons.STATUS_VALID);
			
			this.saveGdsCatlog2Site(save);
		}
	}

	@Override
	public void deleteGdsCatlog2Site(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException {
		// TODO Auto-generated method stub
		GdsCatalog2SiteCriteria example = new GdsCatalog2SiteCriteria();
		GdsCatalog2SiteCriteria.Criteria criteria = example.createCriteria();
		initCriteria(criteria, reqDTO);
		gdsCatalog2SiteMapper.deleteByExample(example);
		
		if(null != reqDTO.getSiteId()){
			GdsCatalogCacheUtil.GdsCatalog2Site.removeRelationFromCacheBySiteId(reqDTO.getSiteId());
		}
	}

	@Override
	public void saveGdsCatlog2Site(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException {
		// TODO Auto-generated method stub
		GdsCatalog2Site record = new GdsCatalog2Site();
		ObjectCopyUtil.copyObjValue(reqDTO, record, null, false);
		if(StringUtil.isBlank(reqDTO.getStatus())){
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);			
		}
		preInsert(reqDTO, record);
		gdsCatalog2SiteMapper.insert(record);
	}
	
}
