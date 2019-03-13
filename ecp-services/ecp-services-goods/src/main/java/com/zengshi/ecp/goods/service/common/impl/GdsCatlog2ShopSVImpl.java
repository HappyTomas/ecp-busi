/** 
 * Project Name:ecp-services-goods-server 
 * File Name:GdsCatlog2ShopSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.common.impl 
 * Date:2016-4-1下午3:51:13 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsCatlog2ShopMapper;
import com.zengshi.ecp.goods.dao.model.GdsCatlog2Shop;
import com.zengshi.ecp.goods.dao.model.GdsCatlog2ShopCriteria;
import com.zengshi.ecp.goods.dao.model.GdsCatlog2ShopKey;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongListRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsCatalogCacheUtil;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatlog2ShopSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description:<br>
 * Date:2016-4-1下午3:51:13  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsCatlog2ShopSVImpl extends AbstractSVImpl implements IGdsCatlog2ShopSV {
	
	@Resource
	private GdsCatlog2ShopMapper gdsCatlog2ShopMapper;

	/** 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsCatlog2ShopSV#saveGdsCatlog2Shop(com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopReqDTO) 
	 */
	@Override
	public void saveGdsCatlog2Shop(GdsCatlog2ShopReqDTO reqDTO)
			throws BusinessException {
		GdsCatlog2Shop record = new GdsCatlog2Shop();
		ObjectCopyUtil.copyObjValue(reqDTO, record, null, false);
		if(StringUtil.isBlank(reqDTO.getStatus())){
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);			
		}
		preInsert(reqDTO, record);
		gdsCatlog2ShopMapper.insert(record);
	}
	
	

	@Override
	public void batchSaveGdsCatlog2Shop(GdsCatlog2ShopReqDTO reqDTO) throws BusinessException {
		GdsCatlog2ShopReqDTO del = new GdsCatlog2ShopReqDTO();
		del.setShopId(reqDTO.getShopId());
		this.deleteGdsCatlog2Shop(del);
		for(Long catlogId : reqDTO.getCatlogIds()){
			GdsCatlog2ShopReqDTO save = new GdsCatlog2ShopReqDTO();
			save.setShopId(reqDTO.getShopId());
			save.setCatlogId(catlogId);
			save.setStatus(GdsConstants.Commons.STATUS_VALID);
			this.saveGdsCatlog2Shop(save);
		}
	}



	/** 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsCatlog2ShopSV#deleteGdsCatlog2Shop(com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopReqDTO) 
	 */
	@Override
	public void deleteGdsCatlog2ShopByPK(GdsCatlog2ShopReqDTO reqDTO)
			throws BusinessException {
	    GdsCatlog2ShopKey key = new GdsCatlog2ShopKey();
	    key.setCatlogId(reqDTO.getCatlogId());
	    key.setShopId(reqDTO.getShopId());
	    
	    gdsCatlog2ShopMapper.deleteByPrimaryKey(key);
	    GdsCatalogCacheUtil.GdsCatalog2Shop.removeRelationFromCacheByShopId(reqDTO.getShopId());
	}
	@Override
	public void deleteGdsCatlog2Shop(GdsCatlog2ShopReqDTO reqDTO)
			throws BusinessException {
		GdsCatlog2ShopCriteria example = new GdsCatlog2ShopCriteria();
		GdsCatlog2ShopCriteria.Criteria criteria = example.createCriteria();
		initCriteria(criteria, reqDTO);
		gdsCatlog2ShopMapper.deleteByExample(example);
		
		if(null != reqDTO.getShopId()){
			GdsCatalogCacheUtil.GdsCatalog2Shop.removeRelationFromCacheByShopId(reqDTO.getShopId());
		}
		
	}

	/** 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.service.common.interfaces.IGdsCatlog2ShopSV#queryRelationByShopId(com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopReqDTO) 
	 */
	@Override
	public PageResponseDTO<GdsCatlog2ShopRespDTO> queryRelation(
			GdsCatlog2ShopReqDTO reqDTO) throws BusinessException {
		GdsCatlog2ShopCriteria criteria = new GdsCatlog2ShopCriteria();
        criteria.setLimitClauseStart(reqDTO.getStartRowIndex());
        criteria.setLimitClauseCount(reqDTO.getPageSize());
        GdsCatlog2ShopCriteria.Criteria c = criteria.createCriteria();
        if(null != reqDTO.getShopId()){
            c.andShopIdEqualTo(reqDTO.getShopId());
        }
        if(null != reqDTO.getCatlogId()){
        	c.andCatlogIdEqualTo(reqDTO.getCatlogId());
        }
        return super.queryByPagination(reqDTO, criteria, false, new GdsCatlog2ShopPaginationCallback());
	}
	
	
	@Override
	public void deleteRelationByShopId(GdsCatlog2ShopReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getShopId()}, 
				   new String[]{"reqDTO.shopId"});
		reqDTO.setCatlogId(null);
		this.deleteGdsCatlog2Shop(reqDTO);
		GdsCatalogCacheUtil.GdsCatalog2Shop.removeRelationFromCacheByShopId(reqDTO.getShopId());
	}
	
	

	@Override
	public LongListRespDTO queryRelationedGdsCatlogIdByShopId(
			GdsCatlog2ShopReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getShopId()}, 
				   new String[]{"reqDTO.shopId"});
		
		LongListRespDTO resp = GdsCatalogCacheUtil.GdsCatalog2Shop.getCatalogIdsByShopIdFromCache(reqDTO.getShopId());
		if(null != resp){
			return resp;
		}else{
			reqDTO.setCatlogId(null);
			reqDTO.setPageSize(Integer.MAX_VALUE);
			PageResponseDTO<GdsCatlog2ShopRespDTO> page = queryRelation(reqDTO);
			if(CollectionUtils.isNotEmpty(page.getResult())){
				resp = new LongListRespDTO();
				for(GdsCatlog2ShopRespDTO respDTO : page.getResult()){
					resp.addElement(respDTO.getCatlogId());
				}
				GdsCatalogCacheUtil.GdsCatalog2Shop.cacheCatalogIds(reqDTO.getShopId(), resp);
			}
		}
		return resp;
	}



	@Override
	public void deleteRelationByCatlogId(GdsCatlog2ShopReqDTO reqDTO)
			throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[]{reqDTO.getCatlogId()}, 
				   new String[]{"reqDTO.catlogId"});
		reqDTO.setShopId(null);
		this.deleteGdsCatlog2Shop(reqDTO);
	}
	
	
	protected class GdsCatlog2ShopPaginationCallback extends PaginationCallback<GdsCatlog2Shop, GdsCatlog2ShopRespDTO>{

        @Override
        public List<GdsCatlog2Shop> queryDB(BaseCriteria criteria) {
           return gdsCatlog2ShopMapper.selectByExample((GdsCatlog2ShopCriteria)criteria);
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
           return gdsCatlog2ShopMapper.countByExample((GdsCatlog2ShopCriteria)criteria);
        }

        @Override
        public GdsCatlog2ShopRespDTO warpReturnObject(GdsCatlog2Shop t) {
        	GdsCatlog2ShopRespDTO respDTO = new GdsCatlog2ShopRespDTO();
            ObjectCopyUtil.copyObjValue(t, respDTO, null, true);
            return respDTO;
        }
    }
	
	
	private void initCriteria(GdsCatlog2ShopCriteria.Criteria criteria, GdsCatlog2ShopReqDTO reqDTO){
		if(null != reqDTO.getCatlogId()){
			criteria.andCatlogIdEqualTo(reqDTO.getCatlogId());
		}
		if(null != reqDTO.getShopId()){
			criteria.andShopIdEqualTo(reqDTO.getShopId());
		}
	}

}

