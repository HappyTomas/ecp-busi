package com.zengshi.ecp.goods.service.busi.impl;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsCollectCntMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsCollectMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsCollectShopIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsCollectStaffIdxMapper;
import com.zengshi.ecp.goods.dao.model.*;
import com.zengshi.ecp.goods.dao.model.GdsCollectCriteria.Criteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsCollectCntSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsCollectSV;
import com.zengshi.ecp.goods.service.busi.interfaces.external.IGdsInfoExternalSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsIDXSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 收藏信息操作实现<br>
 * Date:2015年9月4日上午11:43:28 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsCollectSVImpl extends AbstractSVImpl implements IGdsCollectSV {

	@Resource(name = "seq_gds_collect")
	private PaasSequence seqGdsCollect;

	@Resource
	private GdsCollectMapper gdsCollectMapper;
	@Resource
	private GdsCollectShopIdxMapper gdsCollectShopIdxMapper;
	@Resource
	private GdsCollectCntMapper gdsCollectCntMapper;

	@Resource
	private IGdsIDXSV gdsIDXSV;

	@Resource
	private IGdsInfoExternalSV gdsInfoExternalSV;

	@Resource
	private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;
	@Resource
	private IGdsCollectCntSV gdsCollectCntSV;
	@Resource
	private GdsCollectStaffIdxMapper gdsCollectStaffIdxMapper;

	private static final String DEFAULT_ORDER_BY = "CREATE_TIME DESC";
	
	/**
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsCollectSV#addGdsCollect(com.zengshi.ecp.goods.dao.model.GdsCollect)
	 */
	@Override
	public Long addGdsCollect(GdsCollectReqDTO dto) throws BusinessException {
		GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
		skuInfoReqDTO.setId(dto.getSkuId());
		GdsSkuInfoRespDTO sku = gdsSkuInfoQuerySV.querySkuInfoByOptions(
				skuInfoReqDTO, new SkuQueryOption[] { SkuQueryOption.BASIC,SkuQueryOption.CAlDISCOUNT });
		if(sku==null || sku.getId()==null || sku.getId().longValue() == 0){
			throw new BusinessException(GdsErrorConstants.GdsCollect.ERROR_GOODS_COLLECT_251001);
		}
		
		// 收藏重复检验，用户维度收藏。重复不做信息覆盖，直接抛出异常。
		GdsCollectStaffIdxCriteria example = new GdsCollectStaffIdxCriteria();
		GdsCollectStaffIdxCriteria.Criteria criteria = example.createCriteria();
		criteria.andStaffIdEqualTo(dto.getStaff().getId());
		criteria.andGdsIdEqualTo(sku.getGdsId());
		criteria.andSkuIdEqualTo(dto.getSkuId());
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		List<GdsCollectStaffIdx> gdsCollectStaffIdxList = this.gdsIDXSV
				.selectByExample(example);
		if (CollectionUtils.isNotEmpty(gdsCollectStaffIdxList)) {
			throw new BusinessException(GdsErrorConstants.GdsCollect.ERROR_GOODS_COLLECT_251000);
		}


		// 添加主表收藏信息
		GdsCollect record = new GdsCollect();
		ObjectCopyUtil.copyObjValue(dto, record, null, false);
		ObjectCopyUtil.copyObjValue(sku, record, null, false);
		if(sku!=null){
			record.setGdsPrice(sku.getDiscountPrice());
		}
		
		record.setId(seqGdsCollect.nextValue());
		record.setStatus(GdsConstants.Commons.STATUS_VALID);
		record.setStaffId(dto.getStaff().getId());
		record.setStaffName(dto.getStaff().getStaffCode());
		record.setCollectionTime(now());
		preInsert(dto, record);
		gdsCollectMapper.insert(record);
        Long collectId = record.getId();
		// 添加索引表信息
		ObjectCopyUtil.copyObjValue(record, dto, null, false);
		dto.setId(record.getId());
		dto.setStatus(GdsConstants.Commons.STATUS_VALID);
		this.gdsIDXSV.saveGdsCollectIdx(dto);
		gdsCollectCntSV.executeGdsCollectCntInc(dto);
		return collectId;
	}

	@Override
	public int deleteGdsCollectByPK(GdsCollectReqDTO reqDTO)
			throws BusinessException {
		// 删除主表信息
		GdsCollect eval = new GdsCollect();
		preUpdate(reqDTO, eval);
		eval.setStatus(GdsConstants.Commons.STATUS_INVALID);

		GdsCollectCriteria criteria = new GdsCollectCriteria();
		Criteria c = criteria.createCriteria();
		c.andIdEqualTo(reqDTO.getId());
		c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

		int i = gdsCollectMapper.updateByExampleSelective(eval, criteria);

		// 删除索引表信息
		GdsCollect collect=queryGdsCollectModelByPK(reqDTO.getId());
		reqDTO.setGdsId(collect.getGdsId());
		reqDTO.setShopId(collect.getShopId());
		reqDTO.setStaffId(collect.getShopId());
		this.gdsIDXSV.deleteGdsCollectIdx(reqDTO);
		gdsCollectCntSV.executeGdsCollectCntDec(reqDTO);
		return i;
	}

	@Override
	public int editGdsCollect(GdsCollectReqDTO reqDTO) throws BusinessException {

		// 更新主表信息
		GdsCollect record = new GdsCollect();
		ObjectCopyUtil.copyObjValue(reqDTO, record, null, false);
		preUpdate(reqDTO, record);
		int i = gdsCollectMapper.updateByPrimaryKeySelective(record);

		GdsCollect collect=queryGdsCollectModelByPK(reqDTO.getId());
		reqDTO.setGdsId(collect.getGdsId());
		reqDTO.setShopId(collect.getShopId());
		reqDTO.setStaffId(collect.getShopId());
		// 更新索引表信息
		this.gdsIDXSV.updateGdsCollectIdx(reqDTO);

		return i;
	}

	@Override
	public GdsCollectRespDTO queryGdsCollectByPK(Long id)
			throws BusinessException {

		// 直接查主表，不走索引表。
		GdsCollect eval = queryGdsCollectModelByPK(id);
		if (null != eval) {
			GdsCollectRespDTO dto = new GdsCollectRespDTO();
			ObjectCopyUtil.copyObjValue(eval, dto, null, false);
			return dto;
		} else {
			return null;
		}
	}
	
	private GdsCollect queryGdsCollectModelByPK(Long id){
		GdsCollect eval = gdsCollectMapper.selectByPrimaryKey(id);
		return  eval;
	}

	@Override
	public GdsCollectRespDTO queryGdsCollectByPK(GdsCollectReqDTO reqDTO)
			throws BusinessException {
		return this.queryGdsCollectByPK(reqDTO.getId());
	}

	@Override
	public long countGdsCollectByShop(GdsCollectReqDTO dto)
			throws BusinessException {
		GdsCollectShopIdxCriteria gdsCollectShopIdxCriteria = new GdsCollectShopIdxCriteria();
		initShopCriteria(gdsCollectShopIdxCriteria.createCriteria(), dto);
		return this.gdsIDXSV.countByExample(gdsCollectShopIdxCriteria);
	}

	@Override
	public PageResponseDTO<GdsCollectRespDTO> queryGdsCollectRespDTOPagingByShop(
			GdsCollectReqDTO dto) throws BusinessException {
		GdsCollectCntCriteria gdsCollectCntCriteria = new GdsCollectCntCriteria();
		gdsCollectCntCriteria.setLimitClauseStart(dto.getStartRowIndex());
		gdsCollectCntCriteria.setLimitClauseCount(dto.getPageSize());
		gdsCollectCntCriteria.setOrderByClause("COLL_AMOUNT DESC, SKU_ID DESC");
		initGdsCollectCntCriteria(gdsCollectCntCriteria.createCriteria(), dto);
		return super.queryByPagination(dto, gdsCollectCntCriteria, true,
				new GdsCollectRespDTOPaginationByShopOrderByCollAmountCallback(dto));
	}

	/**
	 * 店铺视图，支持商品编码、商品名称检索
	 * 
	 * @param criteria
	 * @param dto
	 * @return
	 */
	private com.zengshi.ecp.goods.dao.model.GdsCollectShopIdxCriteria.Criteria initShopCriteria(
			com.zengshi.ecp.goods.dao.model.GdsCollectShopIdxCriteria.Criteria criteria,
			GdsCollectReqDTO dto) {

	    criteria.andShopIdEqualTo(dto.getShopId());
		if (StringUtil.isNotBlank(dto.getGdsName())) {
			criteria.andGdsNameLike("%" + dto.getGdsName() + "%");
		}
		if (dto.getGdsId() != null) {
			criteria.andGdsIdEqualTo(dto.getGdsId());
		}
		if (dto.getSkuId() != null) {
			criteria.andSkuIdEqualTo(dto.getSkuId());
		}
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		return criteria;
	}
	
	
	private GdsCollectCntCriteria.Criteria initGdsCollectCntCriteria(
			GdsCollectCntCriteria.Criteria c,
			GdsCollectReqDTO dto) {

	    c.andShopIdEqualTo(dto.getShopId());
		if (StringUtil.isNotBlank(dto.getGdsName())) {
			c.andGdsNameLike("%" + dto.getGdsName() + "%");
		}
		if (dto.getGdsId() != null) {
			c.andGdsIdEqualTo(dto.getGdsId());
		}
		if (dto.getSkuId() != null) {
			c.andSkuIdEqualTo(dto.getSkuId());
		}
		c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		c.andCollAmountGreaterThan(0L);
		return c;
	}
	
	

	protected class GdsCollectRespDTOPaginationByShopCallback extends
			PaginationCallback<GdsCollectShopIdx, GdsCollectRespDTO> {

		private GdsCollectReqDTO dto;

		public GdsCollectRespDTOPaginationByShopCallback(GdsCollectReqDTO dto) {
			this.dto = dto;
		}

		@Override
		public List<GdsCollectShopIdx> queryDB(BaseCriteria criteria) {
			GdsCollectShopIdxCriteria gdsCollectShopIdxCriteria = (GdsCollectShopIdxCriteria) criteria;

			// 需要返回用户收藏统计数，需要进行Distinct
			gdsCollectShopIdxCriteria.setDistinct(this.dto
					.isIncludeStaffCount());
			return gdsIDXSV.selectByExample(gdsCollectShopIdxCriteria);
		}

		@Override
		public long queryTotal(BaseCriteria criteria) {
			GdsCollectShopIdxCriteria gdsCollectShopIdxCriteria = (GdsCollectShopIdxCriteria) criteria;

			// 需要返回用户收藏统计数，需要进行Distinct
			gdsCollectShopIdxCriteria.setDistinct(this.dto
					.isIncludeStaffCount());
			return gdsIDXSV.countByExample(gdsCollectShopIdxCriteria);
		}

		@Override
		public GdsCollectRespDTO warpReturnObject(GdsCollectShopIdx t) {
			GdsCollectRespDTO respDto = new GdsCollectRespDTO();
			ObjectCopyUtil.copyObjValue(t, respDto, null, true);

			respDto.setId(t.getCollId());

			// 商品收藏价格,单品属性串设置,图片，库存
			setSkuInfo(respDto,t.getGdsId(), t.getSkuId());
			// 是否返回收藏用户数统计
			if (this.dto.isIncludeStaffCount()) {
				respDto.setCollectStaffCount(queryCollectStaffCount(
						t.getShopId(), t.getGdsId(), t.getSkuId()));
			}
			return respDto;
		}

	}
	
	
	/*protected class GdsCollectRespDTOPaginationByShopOrderByCollAmountCallback
			extends PaginationCallback<GdsCollectCnt, GdsCollectRespDTO> {

		private GdsCollectReqDTO dto;

		public GdsCollectRespDTOPaginationByShopOrderByCollAmountCallback(
				GdsCollectReqDTO dto) {
			this.dto = dto;
		}

		@Override
		public List<GdsCollectCnt> queryDB(BaseCriteria criteria) {
			return gdsCollectCntMapper.selectByExample((GdsCollectCntCriteria)criteria);
		}

		@Override
		public long queryTotal(BaseCriteria criteria) {
			return gdsCollectCntMapper.countByExample((GdsCollectCntCriteria)criteria);
		}

		@Override
		public GdsCollectRespDTO warpReturnObject(GdsCollectCnt t) {
			GdsCollectRespDTO respDto = new GdsCollectRespDTO();
			ObjectCopyUtil.copyObjValue(t, respDto, null, true);
			respDto.setCollectStaffCount(t.getCollAmount());
			GdsCollectShopIdxCriteria innerCriteria = new GdsCollectShopIdxCriteria();
			GdsCollectShopIdxCriteria.Criteria c = innerCriteria
					.createCriteria();
			c.andGdsIdEqualTo(t.getGdsId());
			c.andSkuIdEqualTo(t.getSkuId());
			if (null != this.dto.getShopId()) {
				c.andShopIdEqualTo(dto.getShopId());
			}
			c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
			List<GdsCollectShopIdx> l = gdsCollectShopIdxMapper
					.selectByExample(innerCriteria);
			if (CollectionUtils.isNotEmpty(l)) {
				GdsCollectShopIdx shopIdx = l.get(0);
				ObjectCopyUtil.copyObjValue(shopIdx, respDto, null, true);
				respDto.setId(shopIdx.getCollId());
				// 商品收藏价格,单品属性串设置,图片，库存
				setSkuInfo(respDto, t.getGdsId(), t.getSkuId());
			}
			return respDto;
		}

	}*/
	
	
	

	protected class GdsCollectRespDTOPaginationByShopOrderByCollAmountCallback extends
			PaginationCallback<GdsCollectCnt, GdsCollectRespDTO> {

		private GdsCollectReqDTO dto;
		
		private List<GdsCollectRespDTO> gcrdList = null;

		public GdsCollectRespDTOPaginationByShopOrderByCollAmountCallback(GdsCollectReqDTO dto) {
			this.dto = dto;
		}
		
		@Override
		public List<GdsCollectCnt> queryDB(BaseCriteria criteria) {
		    return gdsCollectCntMapper.selectByExample((GdsCollectCntCriteria)criteria);
		}

		@Override
		public long queryTotal(BaseCriteria criteria) {
			return gdsCollectCntMapper.countByExample((GdsCollectCntCriteria)criteria);
		}

		@Override
		public GdsCollectRespDTO warpReturnObject(GdsCollectCnt t) {
			
			GdsCollectRespDTO respDto = new GdsCollectRespDTO();
			ObjectCopyUtil.copyObjValue(t, respDto, null, true);
			respDto.setCollectStaffCount(t.getCollAmount());
			GdsCollectShopIdxCriteria innerCriteria = new GdsCollectShopIdxCriteria();
		    GdsCollectShopIdxCriteria.Criteria c = innerCriteria.createCriteria();
		    c.andGdsIdEqualTo(t.getGdsId());
		    c.andSkuIdEqualTo(t.getSkuId());
		    if(null != this.dto.getShopId()){
		      c.andShopIdEqualTo(dto.getShopId());
		    }
		    c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		    List<GdsCollectShopIdx> l = gdsCollectShopIdxMapper.selectByExample(innerCriteria);
		    if(CollectionUtils.isNotEmpty(l)){
		    	GdsCollectShopIdx shopIdx = l.get(0);
		    	ObjectCopyUtil.copyObjValue(shopIdx, respDto, null, true);
		    	respDto.setId(shopIdx.getCollId());
		    	// 商品收藏价格,单品属性串设置,图片，库存
				setSkuInfo(respDto, t.getGdsId(), t.getSkuId());
		    }
			return respDto;
		}

	}
	
	
	

	@Override
	public PageResponseDTO<GdsCollectRespDTO> queryGdsCollectRespDTOPagingByStaff(
			GdsCollectReqDTO dto) throws BusinessException {
		GdsCollectStaffIdxCriteria gdsCollectStaffIdxCriteria = new GdsCollectStaffIdxCriteria();
		gdsCollectStaffIdxCriteria.setLimitClauseStart(dto.getStartRowIndex());
		gdsCollectStaffIdxCriteria.setLimitClauseCount(dto.getPageSize());
		gdsCollectStaffIdxCriteria.setOrderByClause(DEFAULT_ORDER_BY);
		initStaffCriteria(gdsCollectStaffIdxCriteria.createCriteria(), dto);
		return super.queryByPagination(dto, gdsCollectStaffIdxCriteria, true,
				new GdsCollectRespDTOPaginationByStaffCallback());
	}

	/**
	 * 用户视图，支持商品名称检索
	 * 
	 * @param criteria
	 * @param dto
	 * @return
	 */
	private com.zengshi.ecp.goods.dao.model.GdsCollectStaffIdxCriteria.Criteria initStaffCriteria(
			com.zengshi.ecp.goods.dao.model.GdsCollectStaffIdxCriteria.Criteria criteria,
			GdsCollectReqDTO dto) {

	    criteria.andStaffIdEqualTo(dto.getStaffId());
		if (StringUtil.isNotBlank(dto.getGdsName())) {
			criteria.andGdsNameLike("%" + dto.getGdsName() + "%");
		}
		if(dto.getGdsId()!=null &&dto.getGdsId() != 0L){
		    criteria.andGdsIdEqualTo(dto.getGdsId());
		}
		criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		return criteria;
	}

	protected class GdsCollectRespDTOPaginationByStaffCallback extends
			PaginationCallback<GdsCollectStaffIdx, GdsCollectRespDTO> {

		@Override
		public List<GdsCollectStaffIdx> queryDB(BaseCriteria criteria) {
			return gdsIDXSV
					.selectByExample((GdsCollectStaffIdxCriteria) criteria);
		}

		@Override
		public long queryTotal(BaseCriteria criteria) {
			return gdsIDXSV
					.countByExample((GdsCollectStaffIdxCriteria) criteria);
		}

		@Override
		public GdsCollectRespDTO warpReturnObject(GdsCollectStaffIdx t) {
			GdsCollectRespDTO dto = new GdsCollectRespDTO();
			ObjectCopyUtil.copyObjValue(t, dto, null, true);
			dto.setId(t.getCollId());
			// 商品收藏价格,单品属性串设置,图片，库存
			setSkuInfo(dto,t.getGdsId(), t.getSkuId());
			
			// 是否返回收藏用户数统计
			if (t.getShopId()!=null && t.getGdsId()!=null && t.getSkuId()!=null) {
				dto.setCollectStaffCount(queryCollectStaffCount(t.getShopId(), t.getGdsId(), t.getSkuId()));
			}
			return dto;
		}

	}

	@Override
	public PageResponseDTO<GdsCollectRespDTO> queryGdsCollectRespDTOPagingByGds(
			GdsCollectReqDTO dto) throws BusinessException {
		GdsCollectGdsIdxCriteria gdsCollectGdsIdxCriteria = new GdsCollectGdsIdxCriteria();
		gdsCollectGdsIdxCriteria.setLimitClauseStart(dto.getStartRowIndex());
		gdsCollectGdsIdxCriteria.setLimitClauseCount(dto.getPageSize());
		gdsCollectGdsIdxCriteria.setOrderByClause(DEFAULT_ORDER_BY);
		initGdsCriteria(gdsCollectGdsIdxCriteria.createCriteria(), dto);
		return super.queryByPagination(dto, gdsCollectGdsIdxCriteria, true,
				new GdsCollectRespDTOPaginationByGdsCallback());
	}
	
	
	/**
	 * 商品视图，支持商品编码、商品名称检索
	 * 
	 * @param criteria
	 * @param dto
	 * @return
	 */
	private com.zengshi.ecp.goods.dao.model.GdsCollectGdsIdxCriteria.Criteria initGdsCriteria(
			com.zengshi.ecp.goods.dao.model.GdsCollectGdsIdxCriteria.Criteria criteria,
			GdsCollectReqDTO dto) {
	    
	    criteria.andGdsIdEqualTo(dto.getGdsId());
		if (StringUtil.isNotBlank(dto.getGdsName())) {
			criteria.andGdsNameLike("%" + dto.getGdsName() + "%");
		}
		if (dto.getSkuId() != null) {
			criteria.andSkuIdEqualTo(dto.getSkuId());
		}
		return criteria;
	}

	protected class GdsCollectRespDTOPaginationByGdsCallback extends
			PaginationCallback<GdsCollectGdsIdx, GdsCollectRespDTO> {

		@Override
		public List<GdsCollectGdsIdx> queryDB(BaseCriteria criteria) {
			return gdsIDXSV
					.selectByExample((GdsCollectGdsIdxCriteria) criteria);
		}

		@Override
		public long queryTotal(BaseCriteria criteria) {
			return gdsIDXSV.countByExample((GdsCollectGdsIdxCriteria) criteria);
		}

		@Override
		public GdsCollectRespDTO warpReturnObject(GdsCollectGdsIdx t) {
			GdsCollectRespDTO dto = new GdsCollectRespDTO();
			ObjectCopyUtil.copyObjValue(t, dto, null, true);
			dto.setId(t.getCollId());
			// 商品收藏价格,单品属性串设置,图片，库存
			setSkuInfo(dto,t.getGdsId(), t.getSkuId());
			return dto;
		}

	}

	private long queryCollectStaffCount(long shopId, long gdsId, long skuId) {
		GdsCollectReqDTO reqDto = new GdsCollectReqDTO();
		reqDto.setShopId(shopId);
		reqDto.setGdsId(gdsId);
		reqDto.setSkuId(skuId);
		return countGdsCollectByShop(reqDto);
	}

	/**
	 * 
	 * setSkuInfo:(设置单品信息). <br/> 
	 * 
	 * @author linwb3
	 * @param gdsCollectRespDTO
	 * @param gdsId
	 * @param skuId 
	 * @since JDK 1.6
	 */
	private void setSkuInfo(GdsCollectRespDTO gdsCollectRespDTO,long gdsId, long skuId) {
		GdsSkuInfoReqDTO skuInfo = new GdsSkuInfoReqDTO();
		skuInfo.setId(skuId);
		skuInfo.setGdsId(gdsId);
		GdsSkuInfoRespDTO gdsSkuInfoRespDTO = gdsSkuInfoQuerySV.querySkuInfoByOptions(skuInfo,
				new SkuQueryOption[]{SkuQueryOption.BASIC,SkuQueryOption.MAINPIC,SkuQueryOption.STOCK,SkuQueryOption.CAlDISCOUNT});
		gdsCollectRespDTO.setGuidePrice(gdsSkuInfoRespDTO.getGuidePrice());
		if (gdsSkuInfoRespDTO.getDiscountPrice()!=null &&gdsSkuInfoRespDTO.getDiscountPrice() != 0L) {	
			gdsCollectRespDTO.setNowPrice(gdsSkuInfoRespDTO.getDiscountPrice());
		}else{
			gdsCollectRespDTO.setNowPrice(0l);
		}
		if(gdsSkuInfoRespDTO.getMainPic()!=null){
			gdsCollectRespDTO.setSkuMainPic(gdsSkuInfoRespDTO.getMainPic().getMediaUuid());
		}

		Long stock=gdsSkuInfoRespDTO.calcAvalidStocks();
		gdsCollectRespDTO.setStock(stock);

		//虚拟商品直接设为充足
		boolean ifNeedStockAmount=gdsInfoExternalSV.isNeedStockAmount(gdsSkuInfoRespDTO.getGdsTypeId());
		if(!ifNeedStockAmount){
			gdsCollectRespDTO.setStockInfo("充足");
		}else{
			gdsCollectRespDTO.setStockInfo(GdsUtils.checkStcokStatusDesc(stock));
		}

		gdsCollectRespDTO.setGdsStatus(gdsSkuInfoRespDTO.getGdsStatus());
		gdsCollectRespDTO.setGdsUrl(GdsUtils.getGdsUrl(gdsId, skuId,gdsSkuInfoRespDTO.getCatlogId()));
		gdsCollectRespDTO.setSkuProps(gdsSkuInfoRespDTO.getSkuProps());
		gdsCollectRespDTO.setGdsTypeId(gdsSkuInfoRespDTO.getGdsTypeId());
	}

}
