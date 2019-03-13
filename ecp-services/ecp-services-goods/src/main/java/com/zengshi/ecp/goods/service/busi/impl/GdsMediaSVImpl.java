/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsMediaSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.busi.impl 
 * Date:2015年8月14日上午9:41:22 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.service.busi.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsMediaLibMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsMediaMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsMediaShopIdxMapper;
import com.zengshi.ecp.goods.dao.model.GdsMedia;
import com.zengshi.ecp.goods.dao.model.GdsMediaLib;
import com.zengshi.ecp.goods.dao.model.GdsMediaShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsMediaShopIdxCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaLibReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsMediaShopIdxRespDTO;
import com.zengshi.ecp.goods.dubbo.util.MathUtil;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsMediaSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoIDXSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月14日上午9:41:22 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public class GdsMediaSVImpl extends AbstractSVImpl implements IGdsMediaSV {

	@Resource(name = "seq_gds_media")
	private PaasSequence seqGdsMedia;
	@Resource
	private GdsMediaMapper gdsMediaMapper;
	@Resource(name = "seq_gds_media_lib")
	private PaasSequence seqGdsMediaLib;
	@Resource
	private GdsMediaLibMapper gdsMediaLibMapper;
	@Resource
	private GdsMediaShopIdxMapper gdsMediaShopIdxMapper;
	@Resource
	private IGdsInfoIDXSV gdsInfoIDXSV;

	private static final String POST_LIBNAME = "的媒体库";
	private static final String ADD_TYPE = "add";
	private static final String REDUCE_TYPE = "reduce";

	@Override
	public void saveGdsMediaLib(GdsMediaLibReqDTO gdsMediaLibReqDTO)
			throws BusinessException {
		GdsMediaLib gdsMediaLib = new GdsMediaLib();
		gdsMediaLib.setId(seqGdsMediaLib.nextValue());
		gdsMediaLib.setLibName(gdsMediaLibReqDTO.getShopName() + POST_LIBNAME);
		gdsMediaLib.setShopId(gdsMediaLibReqDTO.getShopId());
		gdsMediaLib.setaLimit(Long
				.parseLong(SysCfgUtil.fetchSysCfg(
						GdsConstants.GdsMedia.MEDIA_KEY_LIMIT_GDS_AUDIO)
						.getParaValue()));
		gdsMediaLib.setaNow(0L);
		gdsMediaLib.setvLimit(Long
				.parseLong(SysCfgUtil.fetchSysCfg(
						GdsConstants.GdsMedia.MEDIA_KEY_LIMIT_GDS_VEDIO)
						.getParaValue()));
		gdsMediaLib.setvNow(0L);
		gdsMediaLib.setpLimit(Long.parseLong(SysCfgUtil.fetchSysCfg(
				GdsConstants.GdsMedia.MEDIA_KEY_LIMIT_GDS_PICTURE)
				.getParaValue()));
		gdsMediaLib.setpNow(0L);
		gdsMediaLib.setStatus(GdsConstants.Commons.STATUS_VALID);
		preInsert(gdsMediaLibReqDTO, gdsMediaLib);
		gdsMediaLibMapper.insert(gdsMediaLib);
	}

	@Override
	public GdsMediaLib selectGdsMediaLibByPk(GdsMediaLibReqDTO gdsMediaLibReqDTO)
			throws BusinessException {
		return gdsMediaLibMapper.selectByPrimaryKey(gdsMediaLibReqDTO.getId());
	}

	/**
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsMediaSV#saveGdsProp(com.zengshi.ecp.goods.dao.model.GdsMedia)
	 */
	@Override
	public GdsMedia saveGdsMedia(GdsMedia gdsMedia) throws BusinessException {
		Timestamp now = now();
		gdsMedia.setId(seqGdsMedia.nextValue());
		gdsMedia.setCreateTime(now);
		gdsMedia.setUpdateTime(now);


		// 媒体库扣减
		if (gdsMedia.getMediaLibId() != null
				&& gdsMedia.getMediaLibId().longValue() != 0L) {
			GdsMediaLib gdsMediaLib = gdsMediaLibMapper
					.selectByPrimaryKey(gdsMedia.getMediaLibId());
			if (gdsMediaLib != null) {
				updateMediaLib(gdsMedia, gdsMediaLib, ADD_TYPE);
				gdsMedia.setMediaLibId(gdsMediaLib.getId());
			}
		}
		
		gdsMediaMapper.insert(gdsMedia);
		gdsInfoIDXSV.addGdsMediaIDX(gdsMedia);

		return gdsMedia;
	}

	/**
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsMediaSV#queryGdsMediaById(java.lang.Long)
	 */
	@Override
	public GdsMedia queryGdsMediaById(Long id) throws BusinessException {
		return gdsMediaMapper.selectByPrimaryKey(id);
	}

	/**
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsMediaSV#editGdsMedia(com.zengshi.ecp.goods.dao.model.GdsMedia,
	 *      java.lang.Long)
	 */
	@Override
	public GdsMedia editGdsMedia(GdsMedia gdsMedia, Long updateStaff)
			throws BusinessException {

		// 原始媒体大小查询
		GdsMedia gm = gdsMediaMapper.selectByPrimaryKey(gdsMedia.getId());
		gdsMedia.setUpdateStaff(updateStaff);
		gdsMedia.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		gdsMediaMapper.updateByPrimaryKeySelective(gdsMedia);
		gdsInfoIDXSV.editGdsMediaIDX(gdsMedia);

		// 媒体库当前总大小编辑
		if (gdsMedia.getMediaLibId() != null
				&& gdsMedia.getMediaLibId().longValue() != 0L) {
			GdsMediaLib gdsMediaLib = gdsMediaLibMapper
					.selectByPrimaryKey(gdsMedia.getMediaLibId());
			if (gdsMediaLib != null) {
				updateMediaLib(gm, gdsMediaLib, REDUCE_TYPE);
				updateMediaLib(gdsMedia, gdsMediaLib, ADD_TYPE);
			}
		}
		return gdsMedia;
	}

	/**
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsMediaSV#disableGdsMedia(long,
	 *      java.lang.Long)
	 */
	@Override
	public void executeDisableGdsMedia(long id, Long updateStaff)
			throws BusinessException {
		GdsMedia gm = gdsMediaMapper.selectByPrimaryKey(id);
		if (gm != null) {
			gm.setStatus(GdsConstants.Commons.STATUS_INVALID);
			gdsMediaMapper.updateByPrimaryKeySelective(gm);
			gdsInfoIDXSV.delGdsMediaIDX(gm);

			// 媒体库扣减
			if (gm.getMediaLibId() != null
					&& gm.getMediaLibId().longValue() != 0L) {

				GdsMediaLib gdsMediaLib = gdsMediaLibMapper
						.selectByPrimaryKey(gm.getMediaLibId());
				if (gdsMediaLib != null) {
					updateMediaLib(gm, gdsMediaLib, REDUCE_TYPE);
				}
			}

		}
	}

	private void updateMediaLib(GdsMedia gm, GdsMediaLib gdsMediaLib,
			String type) {
		long originalGdsMediaSize = gm.getMediaSize().longValue();
		String mediatype = gm.getMediaType();
		updateMediaLibLimit(gdsMediaLib, type, originalGdsMediaSize, mediatype);
	}

	private void updateMediaLibLimit(GdsMediaLib gdsMediaLib, String type,
			long originalGdsMediaSize, String mediatype) {
		if (StringUtils.equals(mediatype,
				GdsConstants.GdsMedia.MEDIA_TYPE_AUDIO)) {
			long size = getSize(gdsMediaLib.getaNow(), originalGdsMediaSize,
					type);
			gdsMediaLib.setaNow(size);
		} else if (StringUtils.equals(mediatype,
				GdsConstants.GdsMedia.MEDIA_TYPE_PIC)) {
			long size = getSize(gdsMediaLib.getpNow(), originalGdsMediaSize,
					type);
			gdsMediaLib.setpNow(size);
		} else if (StringUtils.equals(mediatype,
				GdsConstants.GdsMedia.MEDIA_TYPE_VEDIO)) {
			long size = getSize(gdsMediaLib.getvNow(), originalGdsMediaSize,
					type);
			gdsMediaLib.setvNow(size);
		}
		gdsMediaLibMapper.updateByPrimaryKeySelective(gdsMediaLib);
	}

	private long getSize(long a, long b, String type) {
		if (ADD_TYPE.equals(type)) {
			return MathUtil.add(a, b);
		} else if (REDUCE_TYPE.equals(type)) {
			return MathUtil.reduce(a, b);
		}
		return MathUtil.add(a, b);
	}

	/**
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsMediaSV#executeEnableGdsMedia(long,
	 *      java.lang.Long)
	 */
	@Override
	public void executeEnableGdsMedia(long id, Long updateStaff)
			throws BusinessException {
		GdsMedia gm = gdsMediaMapper.selectByPrimaryKey(id);
		if (gm != null) {
			gm.setStatus(GdsConstants.Commons.STATUS_VALID);
			gdsMediaMapper.updateByPrimaryKeySelective(gm);
			gdsInfoIDXSV.delGdsMediaIDX(gm);
		}
	}

	@Override
	public PageResponseDTO<GdsMediaRespDTO> queryGdsMediaListPage(
			GdsMediaReqDTO gdsMediaReqDTO) {
		GdsMediaShopIdxCriteria shopIdxCriteria = initCriteria(gdsMediaReqDTO);
		shopIdxCriteria.setOrderByClause(" sort_no asc");
		PageResponseDTO<GdsMediaShopIdxRespDTO> mediaPageResponseDTO = super
				.queryByPagination(gdsMediaReqDTO, shopIdxCriteria, false,
						new GdsMediaShopIdxRespPaginationCallback());

		List<GdsMediaRespDTO> medias = new ArrayList<GdsMediaRespDTO>();
		if (CollectionUtils.isNotEmpty(mediaPageResponseDTO.getResult())) {
			for (GdsMediaShopIdxRespDTO shopIdx : mediaPageResponseDTO
					.getResult()) {
				GdsMediaRespDTO gdsMediaRespDTO = new GdsMediaRespDTO();
				gdsMediaRespDTO.setId(shopIdx.getId());
				gdsMediaRespDTO.setMediaUuid(shopIdx.getMediaUuid());
				gdsMediaRespDTO.setURL(ImageUtil.getImageUrl(shopIdx
						.getMediaUuid()));
				gdsMediaRespDTO.setMediaName(shopIdx.getMediaName());
				gdsMediaRespDTO.setMediaType(shopIdx.getMediaType());
				gdsMediaRespDTO.setPicCatgCode(shopIdx.getPicCatgCode());
				gdsMediaRespDTO.setShopId(shopIdx.getShopId());
				gdsMediaRespDTO.setSortNo(shopIdx.getSortNo());
				medias.add(gdsMediaRespDTO);
			}
		}

		PageResponseDTO<GdsMediaRespDTO> resultPages = new PageResponseDTO<GdsMediaRespDTO>();
		resultPages.setCount(mediaPageResponseDTO.getCount());
		resultPages.setPageCount(mediaPageResponseDTO.getPageCount());
		resultPages.setPageSize(mediaPageResponseDTO.getPageSize());
		resultPages.setPageNo(mediaPageResponseDTO.getPageNo());
		resultPages.setResult(medias);
		return resultPages;
	}

	private GdsMediaShopIdxCriteria initCriteria(GdsMediaReqDTO gdsMediaReqDTO) {
		GdsMediaShopIdxCriteria gdsMediaShopIdxCriteria = new GdsMediaShopIdxCriteria();
		GdsMediaShopIdxCriteria.Criteria criteria = gdsMediaShopIdxCriteria
				.createCriteria();
		gdsMediaShopIdxCriteria.setLimitClauseCount(gdsMediaReqDTO
				.getPageSize());
		gdsMediaShopIdxCriteria.setLimitClauseStart(gdsMediaReqDTO
				.getStartRowIndex());
		if (gdsMediaReqDTO.getShopId() != null) {
			criteria.andShopIdEqualTo(gdsMediaReqDTO.getShopId());
		}
		if (gdsMediaReqDTO.getId() != null) {
			criteria.andMediaIdEqualTo(gdsMediaReqDTO.getId());
		}
		if (StringUtil.isNotBlank(gdsMediaReqDTO.getPicCatgCode())) {
			criteria.andPicCatgCodeEqualTo(gdsMediaReqDTO.getPicCatgCode());
		}
		if (StringUtil.isNotBlank(gdsMediaReqDTO.getMediaName())) {
			criteria.andMediaNameLike("%" + gdsMediaReqDTO.getMediaName() + "%");
		}
		if (StringUtil.isNotBlank(gdsMediaReqDTO.getMediaType())) {
			criteria.andMediaTypeEqualTo(gdsMediaReqDTO.getMediaType());
		}
		if (StringUtil.isNotBlank(gdsMediaReqDTO.getStatus())) {
			criteria.andStatusEqualTo(gdsMediaReqDTO.getStatus());
		} else {
			criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
		}
		gdsMediaShopIdxCriteria.setOrderByClause("update_time desc");
		return gdsMediaShopIdxCriteria;

	}

	protected class GdsMediaShopIdxRespPaginationCallback extends
			PaginationCallback<GdsMediaShopIdx, GdsMediaShopIdxRespDTO> {

		@Override
		public List<GdsMediaShopIdx> queryDB(BaseCriteria criteria) {
			return gdsMediaShopIdxMapper
					.selectByExample((GdsMediaShopIdxCriteria) criteria);
		}

		@Override
		public long queryTotal(BaseCriteria criteria) {
			return gdsMediaShopIdxMapper
					.countByExample((GdsMediaShopIdxCriteria) criteria);
		}

		@Override
		public GdsMediaShopIdxRespDTO warpReturnObject(GdsMediaShopIdx t) {
			GdsMediaShopIdxRespDTO dto = new GdsMediaShopIdxRespDTO();
			ObjectCopyUtil.copyObjValue(t, dto, null, true);
			dto.setId(t.getMediaId());
			return dto;
		}

	}

}
