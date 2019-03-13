package com.zengshi.ecp.goods.dubbo.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dao.model.GdsMedia;
import com.zengshi.ecp.goods.dao.model.GdsMediaLib;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaLibReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsMediaRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsMediaSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description:媒体操作dubbo服务<br>
 * Date:2015年9月2日下午9:27:49 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsMediaRSVImpl extends AbstractRSVImpl implements IGdsMediaRSV {

	@Resource
	private IGdsMediaSV gdsMediaSV;

	@Override
	public void saveGdsMediaLib(GdsMediaLibReqDTO gdsMediaLibReqDTO)
			throws BusinessException {
		paramNullCheck(gdsMediaLibReqDTO, "gdsMediaLibReqDTO");
		paramNullCheck(gdsMediaLibReqDTO.getShopId(),
				"gdsMediaLibReqDTO.shopId");
		paramNullCheck(gdsMediaLibReqDTO.getShopName(),
				"gdsMediaLibReqDTO.shopName");
		this.gdsMediaSV.saveGdsMediaLib(gdsMediaLibReqDTO);
	}

	@Override
	public void saveGdsMedia(GdsMediaReqDTO gdsMediaReqDTO)
			throws BusinessException {
		paramNullCheck(gdsMediaReqDTO, "gdsMediaReqDTO");
		paramNullCheck(gdsMediaReqDTO.getMediaUuid(),
				"gdsMediaReqDTO.mediaUuid");
		paramNullCheck(gdsMediaReqDTO.getShopId(), "gdsMediaReqDTO.shopId");
		// paramNullCheck(gdsMediaReqDTO.getMediaLibId(),
		// "gdsMediaReqDTO.mediaLibId");
		// gdsMediaReqDTO.setMediaLibId(1L);
		// 媒体库校验
		if (gdsMediaReqDTO.getMediaLibId() != null
				&& gdsMediaReqDTO.getMediaLibId().longValue() != 0L) {
			GdsMediaLibReqDTO gdsMediaLibReqDTO = new GdsMediaLibReqDTO();
			gdsMediaLibReqDTO.setId(gdsMediaReqDTO.getMediaLibId());
			GdsMediaLib gdsMediaLib = this.gdsMediaSV
					.selectGdsMediaLibByPk(gdsMediaLibReqDTO);
			GdsMediaLibReqDTO mlrd = new GdsMediaLibReqDTO();
			ObjectCopyUtil.copyObjValue(gdsMediaLib, mlrd, null, false);
			if (gdsMediaLib != null) {
				GdsUtils.mediaCapacityValidate(gdsMediaReqDTO, mlrd);
			}
		}
		GdsMedia gdsMedia = new GdsMedia();
		ObjectCopyUtil.copyObjValue(gdsMediaReqDTO, gdsMedia, null, false);
		gdsMedia.setCreateStaff(gdsMediaReqDTO.getStaff().getId());
		gdsMedia.setUpdateStaff(gdsMediaReqDTO.getStaff().getId());
		gdsMediaSV.saveGdsMedia(gdsMedia);
	}

	@Override
	public GdsMediaRespDTO queryGdsMediaById(GdsMediaReqDTO gdsMediaReqDTO)
			throws BusinessException {
		paramNullCheck(gdsMediaReqDTO, "gdsMediaReqDTO");
		paramNullCheck(gdsMediaReqDTO.getId(), "gdsMediaReqDTO.id");
		GdsMediaRespDTO gdsMediaRespDTO = new GdsMediaRespDTO();
		GdsMedia gdsMedia = gdsMediaSV
				.queryGdsMediaById(gdsMediaReqDTO.getId());
		ObjectCopyUtil.copyObjValue(gdsMedia, gdsMediaRespDTO, null, false);
		return gdsMediaRespDTO;
	}

	@Override
	public void editGdsMedia(GdsMediaReqDTO gdsMediaReqDTO)
			throws BusinessException {
		paramNullCheck(gdsMediaReqDTO, "gdsMediaReqDTO");
		paramNullCheck(gdsMediaReqDTO.getId(), "gdsMediaReqDTO.id");
		paramNullCheck(gdsMediaReqDTO.getShopId(), "gdsMediaReqDTO.shopId");
		// paramNullCheck(gdsMediaReqDTO.getMediaLibId(),
		// "gdsMediaReqDTO.mediaLibId");

		// 媒体库校验
		if (gdsMediaReqDTO.getMediaLibId() != null
				&& gdsMediaReqDTO.getMediaLibId().longValue() != 0L) {
			GdsMediaLibReqDTO gdsMediaLibReqDTO = new GdsMediaLibReqDTO();
			gdsMediaLibReqDTO.setId(gdsMediaReqDTO.getMediaLibId());
			GdsMediaLib gdsMediaLib = this.gdsMediaSV
					.selectGdsMediaLibByPk(gdsMediaLibReqDTO);
			if (gdsMediaLib == null) {
				throw new BusinessException(
						GdsErrorConstants.GdsMedia.ERROR_GOODS_MEDIA_200250);
			}
			// 原始媒体大小信息重新从库中查询
			GdsMedia originalGdsMedia = gdsMediaSV
					.queryGdsMediaById(gdsMediaReqDTO.getId());

			if (originalGdsMedia == null) {
				throw new BusinessException(
						GdsErrorConstants.GdsMedia.ERROR_GOODS_MEDIA_200251);
			}
			GdsMediaReqDTO originalGdsMediaReqDTO = new GdsMediaReqDTO();
			ObjectCopyUtil.copyObjValue(originalGdsMedia, originalGdsMediaReqDTO, null, false);
			
			GdsMediaLibReqDTO gdsMediaLibReqDTO2 = new GdsMediaLibReqDTO();
			ObjectCopyUtil.copyObjValue(gdsMediaLib, gdsMediaLibReqDTO2, null, false);
			GdsUtils.mediaCapacityValidate(originalGdsMediaReqDTO, gdsMediaReqDTO,
			        gdsMediaLibReqDTO2);
		}
		GdsMedia gdsMedia = new GdsMedia();
		ObjectCopyUtil.copyObjValue(gdsMediaReqDTO, gdsMedia, null, false);
		gdsMediaSV.editGdsMedia(gdsMedia, gdsMediaReqDTO.getStaff().getId());
	}

	@Override
	public void disableGdsMedia(GdsMediaReqDTO gdsMediaReqDTO)
			throws BusinessException {
		paramNullCheck(gdsMediaReqDTO, "gdsMediaReqDTO");
		paramNullCheck(gdsMediaReqDTO.getId(), "gdsMediaReqDTO.id");
		// paramNullCheck(gdsMediaReqDTO.getMediaLibId(),
		// "gdsMediaReqDTO.mediaLibId");

		gdsMediaSV.executeDisableGdsMedia(gdsMediaReqDTO.getId(),
				gdsMediaReqDTO.getStaff().getId());
	}

	@Override
	public PageResponseDTO<GdsMediaRespDTO> queryGdsInfoListPage(
			GdsMediaReqDTO gdsMediaReqDTO) {
		paramNullCheck(gdsMediaReqDTO, "gdsMediaReqDTO");
		paramNullCheck(gdsMediaReqDTO.getShopId(), "gdsMediaReqDTO.shopId");
		return gdsMediaSV.queryGdsMediaListPage(gdsMediaReqDTO);
	}
	
	
}
