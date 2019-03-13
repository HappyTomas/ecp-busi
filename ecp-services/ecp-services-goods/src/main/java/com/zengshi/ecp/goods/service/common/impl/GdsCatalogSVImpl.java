package com.zengshi.ecp.goods.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsCatalogMapper;
import com.zengshi.ecp.goods.dao.model.GdsCatalog;
import com.zengshi.ecp.goods.dao.model.GdsCatalogCriteria;
import com.zengshi.ecp.goods.dao.model.GdsCatalogCriteria.Criteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsCatalogCacheUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatalogSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-9-18下午7:10:09  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class GdsCatalogSVImpl extends AbstractSVImpl implements IGdsCatalogSV {
	
	@Resource(name="seq_gds_catlog")
    private PaasSequence seqGdsCatlog;
	@Resource
	private GdsCatalogMapper GdsCatalogMapper;
	
	private static final String DEFAULT_ORDER_BY = "CREATE_TIME DESC";
	// 审核状态列表.
    private static final List<String> AUDIT_STATUS = new ArrayList<String>();
    static{
    	// 审核不通过.
    	AUDIT_STATUS.add(GdsConstants.Commons.STATUS_AUDIT_NOT_THROUGH);
    	// 审核通过.
    	AUDIT_STATUS.add(GdsConstants.Commons.STATUS_VALID);
    }
	
	

	@Override
	public void saveGdsCatalog(GdsCatalogReqDTO reqDTO) throws BusinessException {
		paramCheck(new Object[]{reqDTO},new String[]{"reqDTO"});
		paramCheck(new Object[]{reqDTO.getCatlogName(),reqDTO.getCatlogCode()}, new String[]{"reqDTO.CatalogName","reqDTO.CatalogCode"});
		GdsCatalog record = GdsUtils.doObjConvert(reqDTO, GdsCatalog.class, null, null);
		// 设置非默认目录.
		record.setIfDefault(GdsConstants.Commons.STATUS_INVALID);
		preInsert(reqDTO, record);
		record.setId(seqGdsCatlog.nextValue());
		if(isAuditSwitchOpen()){
			record.setStatus(GdsConstants.Commons.STATUS_PENDING_AUDIT);
		}else{
			record.setStatus(GdsConstants.Commons.STATUS_VALID);
		}
		
		GdsCatalogMapper.insert(record);
	}

	@Override
	public PageResponseDTO<GdsCatalogRespDTO> queryGdsCatalogRespDTOPaging(
			GdsCatalogReqDTO reqDTO) throws BusinessException {
		GdsCatalogCriteria criteria = new GdsCatalogCriteria();
        criteria.setLimitClauseStart(reqDTO.getStartRowIndex());
        criteria.setLimitClauseCount(reqDTO.getPageSize());
        criteria.setOrderByClause(DEFAULT_ORDER_BY);
        Criteria c = criteria.createCriteria();
        initCriteria(c,reqDTO);
        return super.queryByPagination(reqDTO, criteria, false, new GdsCatalogRespDTOPaginationCallback());
	}

	@Override
	public void editGdsCatalog(GdsCatalogReqDTO reqDTO)
			throws BusinessException {
		paramCheck(new Object[]{reqDTO},new String[]{"reqDTO"});
		paramCheck(new Object[]{reqDTO.getId(),reqDTO.getCatlogName(),reqDTO.getCatlogCode()}, new String[]{"reqDTO.id","reqDTO.CatalogName","reqDTO.CatalogCode"});
		GdsCatalog record = GdsUtils.doObjConvert(reqDTO, GdsCatalog.class, null, null);
		preUpdate(reqDTO, record);
		GdsCatalogMapper.updateByPrimaryKeySelective(record);
	    GdsCatalogCacheUtil.removeCatalog(reqDTO.getId());
	}

	@Override
	public boolean queryExist(GdsCatalogReqDTO reqDTO) throws BusinessException {
		paramCheck(new Object[]{reqDTO},new String[]{"reqDTO"});
		if(StringUtil.isBlank(reqDTO.getCatlogName()) && StringUtil.isBlank(reqDTO.getCatlogCode())){
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{"reqDTO.CatalogCode,reqDTO.CatalogName"});
		}
		GdsCatalogCriteria criteria = new GdsCatalogCriteria();
		Criteria c = criteria.createCriteria();
		if(StringUtil.isNotBlank(reqDTO.getCatlogName())){
			c.andCatlogNameEqualTo(reqDTO.getCatlogName());
		}
		if(StringUtil.isNotBlank(reqDTO.getCatlogCode())){
			c.andCatlogCodeEqualTo(reqDTO.getCatlogCode());
		}
		if(StringUtil.isNotBlank(reqDTO.getStatus())){
			c.andStatusEqualTo(reqDTO.getStatus());
		}
		
		if(CollectionUtils.isNotEmpty(reqDTO.getExcludeIds())){
			if(1 == reqDTO.getExcludeIds().size()){
				c.andIdNotEqualTo(reqDTO.getExcludeIds().get(0));
			}else{
				c.andIdNotIn(reqDTO.getExcludeIds());
			}
		}
		
		return GdsCatalogMapper.countByExample(criteria) >  0;
	}


	@Override
	public void batchAudit(GdsCatalogReqDTO reqDTO) throws BusinessException {
		paramCheck(new Object[]{reqDTO},new String[]{"reqDTO"});
		paramCheck(new Object[]{reqDTO.getAuditStatus(),reqDTO.getIds()}, new String[]{"reqDTO.auditStatus","reqDTO.ids"});
		if(!AUDIT_STATUS.contains(reqDTO.getAuditStatus())){
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200098,new String[]{"reqDTO.auditStatus"});
		}
		GdsCatalogCriteria criteria  = new GdsCatalogCriteria();
		Criteria c = criteria.createCriteria();
		// 设置条件为待审核状态目录.
		c.andStatusEqualTo(GdsConstants.Commons.STATUS_PENDING_AUDIT);
		// 设置待操作目录ID.
		if(reqDTO.getIds().size() == 1){
			c.andIdEqualTo(reqDTO.getIds().get(0));
		}else{
			c.andIdIn(reqDTO.getIds());
		}
		GdsCatalog record = new GdsCatalog();
		preUpdate(reqDTO, record);
		record.setStatus(reqDTO.getAuditStatus());
		GdsCatalogMapper.updateByExampleSelective(record, criteria);
		for(Long id : reqDTO.getIds()){
			 GdsCatalogCacheUtil.removeCatalog(id);
		}
	}
	
	@Override
	public void batchDelete(GdsCatalogReqDTO reqDTO) throws BusinessException {
		
		paramCheck(new Object[]{reqDTO},new String[]{"reqDTO"});
		paramCheck(new Object[]{reqDTO.getIds()}, new String[]{"reqDTO.ids"});
		GdsCatalogCriteria criteria  = new GdsCatalogCriteria();
		Criteria c = criteria.createCriteria();
		// 设置条件为待审核状态目录.
		c.andStatusNotEqualTo(GdsConstants.Commons.STATUS_INVALID);
		// 设置待操作目录ID.
		if(reqDTO.getIds().size() == 1){
			c.andIdEqualTo(reqDTO.getIds().get(0));
		}else{
			c.andIdIn(reqDTO.getIds());
		}
		GdsCatalog record = new GdsCatalog();
		preUpdate(reqDTO, record);
		record.setStatus(GdsConstants.Commons.STATUS_INVALID);
		GdsCatalogMapper.updateByExampleSelective(record, criteria);
		for(Long id : reqDTO.getIds()){
			 GdsCatalogCacheUtil.removeCatalog(id);
		}
	}

	@Override
	public void batchEnable(GdsCatalogReqDTO reqDTO) throws BusinessException {
		paramCheck(new Object[]{reqDTO},new String[]{"reqDTO"});
		paramCheck(new Object[]{reqDTO.getIds()}, new String[]{"reqDTO.ids"});
		GdsCatalogCriteria criteria  = new GdsCatalogCriteria();
		Criteria c = criteria.createCriteria();
		// 设置条件为待审核状态目录.
		c.andStatusNotEqualTo(GdsConstants.Commons.STATUS_VALID);
		// 设置待操作目录ID.
		if(reqDTO.getIds().size() == 1){
			c.andIdEqualTo(reqDTO.getIds().get(0));
		}else{
			c.andIdIn(reqDTO.getIds());
		}
		GdsCatalog record = new GdsCatalog();
		preUpdate(reqDTO, record);
		record.setStatus(GdsConstants.Commons.STATUS_VALID);
		GdsCatalogMapper.updateByExampleSelective(record, criteria);
		for(Long id : reqDTO.getIds()){
			 GdsCatalogCacheUtil.removeCatalog(id);
		}
	}

	@Override
	public void updateDefaultCatalog(GdsCatalogReqDTO reqDTO) throws BusinessException{
		paramCheck(new Object[]{reqDTO},new String[]{"reqDTO"});
		paramCheck(new Object[]{reqDTO.getId()}, new String[]{"reqDTO.id"});
		GdsCatalog record = GdsCatalogMapper.selectByPrimaryKey(reqDTO.getId());
		if(!GdsUtils.isEqualsValid(record.getStatus())){
			throw new BusinessException(GdsErrorConstants.GdsCatlog.ERROR_GOODS_CATLOG_200600);
		}
		// 清除原有默认设置.
		executeCleanDefaulCatalog(reqDTO);
		record.setIfDefault(GdsConstants.Commons.STATUS_VALID);
		preUpdate(reqDTO, record);
		
		GdsCatalogMapper.updateByPrimaryKeySelective(record);
		
	}
	
	
	@Override
	public void executeCleanDefaulCatalog(GdsCatalogReqDTO reqDTO) throws BusinessException {
		paramCheck(new Object[]{reqDTO},new String[]{"reqDTO"});
		GdsCatalogCriteria criteria  = new GdsCatalogCriteria();
		Criteria c = criteria.createCriteria();
		// 设置更新条件为默认目录.
		c.andIfDefaultEqualTo(GdsConstants.Commons.STATUS_VALID);
		c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		GdsCatalog record = new GdsCatalog();
		record.setIfDefault(GdsConstants.Commons.STATUS_INVALID);
		preUpdate(reqDTO, record);
		// 清除默认目录.
		GdsCatalogMapper.updateByExampleSelective(record, criteria);
		GdsCatalogCacheUtil.removeDefaultCatalogFromCache();
	}
	
	 @Override
	public boolean queryExistDefaultCatalog(GdsCatalogReqDTO reqDTO)
			throws BusinessException {
		    paramCheck(new Object[]{reqDTO},new String[]{"reqDTO"});
			GdsCatalogCriteria criteria = new GdsCatalogCriteria();
			Criteria c = criteria.createCriteria();
			c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
			c.andIfDefaultEqualTo(GdsConstants.Commons.STATUS_VALID);
			return GdsCatalogMapper.countByExample(criteria) >  0;
	}
	 

	@Override
	public GdsCatalogRespDTO queryGdsCatalogByPK(GdsCatalogReqDTO reqDTO)
			throws BusinessException {
		paramCheck(new Object[]{reqDTO},new String[]{"reqDTO"});
		paramCheck(new Object[]{reqDTO.getId()}, new String[]{"reqDTO.id"});
		
		GdsCatalogRespDTO respDTO = null;
		respDTO = GdsCatalogCacheUtil.getCatalogByFromCacheById(reqDTO.getId());
		
		if(null == respDTO){
			GdsCatalog record = GdsCatalogMapper.selectByPrimaryKey(reqDTO.getId());
			if(null != record){
				// return GdsUtils.doObjConvert(record, GdsCatalogRespDTO.class, null, null);
				respDTO = new GdsCatalogRespDTO();
				ObjectCopyUtil.copyObjValue(record, respDTO, null, true);
				GdsCatalogCacheUtil.cacheCataLog(respDTO);
			}
		}
		return respDTO;
	}

	@Override
	public GdsCatalogRespDTO queryDefaultGdsCatalog() throws BusinessException {
		
		 GdsCatalogRespDTO resp = null;
		 
		 resp = GdsCatalogCacheUtil.getGdsDefaultCatalogFromCache();
		 
		 if(null == resp){
			 GdsCatalogCriteria criteria = new GdsCatalogCriteria();
		     GdsCatalogCriteria.Criteria c = criteria.createCriteria();
		     c.andIfDefaultEqualTo(GdsConstants.Commons.STATUS_VALID);
		     c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		     List<GdsCatalog> lst = GdsCatalogMapper.selectByExample(criteria);
		     if(CollectionUtils.isNotEmpty(lst)){
		    	 if(1 < lst.size()){
		    		 throw new BusinessException(GdsErrorConstants.GdsCatlog.ERROR_GOODS_CATLOG_200601);
		    	 }else{
		    		 resp = new GdsCatalogRespDTO();
		    		 ObjectCopyUtil.copyObjValue(lst.get(0), resp, null, false);
		    		 GdsCatalogCacheUtil.cacheDefaultCatalog(resp);
		    	 }
		     } 
		 }
		 return resp;
	}

	/*
     * 审核开关是否开启.
     */
    private boolean isAuditSwitchOpen(){
    	return SysCfgUtil.checkSysCfgValue(GdsConstants.SysCfgConstants.SWITCH_GDS_CATLOG_AUDIT, GdsConstants.Commons.STATUS_VALID);
    }

    
    
	/**
     * 
     * 标签分页查询回调实现类.<br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月20日上午9:22:15  <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsLabelSVImpl 
     * @since JDK 1.6
     */
    protected class GdsCatalogRespDTOPaginationCallback extends PaginationCallback<GdsCatalog, GdsCatalogRespDTO>{

        @Override
        public List<GdsCatalog> queryDB(BaseCriteria criteria) {
           return GdsCatalogMapper.selectByExample((GdsCatalogCriteria)criteria);
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
           return GdsCatalogMapper.countByExample((GdsCatalogCriteria)criteria);
        }

        @Override
        public GdsCatalogRespDTO warpReturnObject(GdsCatalog t) {
        	GdsCatalogRespDTO dto = new GdsCatalogRespDTO();
            ObjectCopyUtil.copyObjValue(t, dto, null, true);
            dto.setStatusName(
            BaseParamUtil.fetchParamValue(GdsConstants.BaseParamConstants.GDS_CATLOG_STATUS,
                    t.getStatus())
                    );
            return dto;
        }
        
    }

	private void initCriteria(Criteria c,GdsCatalogReqDTO reqDTO){
		if(StringUtil.isNotBlank(reqDTO.getCatlogType())){
			c.andCatlogTypeEqualTo(reqDTO.getCatlogType());
		}
		if(StringUtil.isNotBlank(reqDTO.getCatlogCode())){
			c.andCatlogCodeEqualTo(reqDTO.getCatlogCode());
		}
		if(StringUtil.isNotBlank(reqDTO.getCatlogName())){
            c.andCatlogNameLike("%"+reqDTO.getCatlogName()+"%");			
		}
		if(StringUtil.isNotBlank(reqDTO.getStatus())){
			c.andStatusEqualTo(reqDTO.getStatus());
		}
		if(StringUtil.isNotBlank(reqDTO.getIfDefault())){
			c.andIfDefaultEqualTo(reqDTO.getIfDefault());
		}
		if(null != reqDTO.getId()){
			c.andIdEqualTo(reqDTO.getId());
		}
	}
}

