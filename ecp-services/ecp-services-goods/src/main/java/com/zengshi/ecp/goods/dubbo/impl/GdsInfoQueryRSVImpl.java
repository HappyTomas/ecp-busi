package com.zengshi.ecp.goods.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsVerifyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsVerifyRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.HomePageGdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.HomePageGdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2MediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2PropRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoQueryIDXSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsInfo2CatgSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsInfo2MediaSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsInfo2PropSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品查询Dubbo服务<br>
 * Date:2015年8月30日下午4:36:48 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsInfoQueryRSVImpl extends AbstractRSVImpl implements IGdsInfoQueryRSV {

	@Resource
	private IGdsInfoQuerySV gdsInfoQuerySV;

	@Resource
	private IGdsInfo2CatgSV gdsInfo2CatgSV;

	@Resource
	private IGdsInfo2MediaSV gdsInfo2MediaSV;

	@Resource
	private IGdsInfo2PropSV gdsInfo2PropSV;

	@Resource
	private IGdsInfoQueryIDXSV gdsInfoQueryIDXSV;

	@Override
	public GdsInfoRespDTO queryGdsInfo(GdsInfoReqDTO gdsInfo) {

		return gdsInfoQuerySV.queryGdsInfo(gdsInfo);
	}

	@Override
	public GdsInfoRespDTO queryGdsInfoByOption(GdsInfoReqDTO gdsInfo) throws BusinessException {

		paramNullCheck(gdsInfo, false, "gdsInfo");
		paramNullCheck(gdsInfo.getId(), false, "gdsInfo.id");
		GdsInfoRespDTO resp = null;
		try {
			resp = gdsInfoQuerySV.queryGdsInfoByOption(gdsInfo, gdsInfo.getGdsQueryOptions(), gdsInfo.getSkuQuerys());
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099);
		}
		return resp;
	}

	@Override
	public GdsInfoDetailRespDTO queryGdsInfoDetail(GdsInfoReqDTO gdsInfo) throws BusinessException {
		paramNullCheck(gdsInfo, false, "gdsInfo");
		paramNullCheck(gdsInfo.getId(), "gdsInfo.id");
		try {
			return gdsInfoQuerySV.queryGdsInfoDetail(gdsInfo);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099);
		}
	}

	@Override
	public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListByCatgCode(GdsInfoReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramNullCheck(reqDTO.getMainCatgs(), "reqDTO.mainCatgs");
		try {
			return gdsInfoQuerySV.queryGdsInfoListByCatgCode(reqDTO);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099);
		}
	}

	@Override
	public List<GdsGds2CatgRespDTO> queryGds2CatgsByGdsId(LongReqDTO gdsId) throws BusinessException {
		paramNullCheck(gdsId, "gdsId");
		paramNullCheck(gdsId.getId(), "gdsId.id");
		try {
			return gdsInfo2CatgSV.queryGds2CatgsByGdsId(gdsId.getId());
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099);
		}
	}

	@Override
	public List<GdsGds2PropRespDTO> queryGds2PropsByGdsId(LongReqDTO gdsId) throws BusinessException {
		paramNullCheck(gdsId, "gdsId");
		paramNullCheck(gdsId.getId(), "gdsId.id");
		try {
			return gdsInfo2PropSV.queryGds2PropsByGdsId(gdsId.getId());
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099);
		}
	}

	@Override
	public List<GdsGds2MediaRespDTO> queryGds2MediasByGdsId(LongReqDTO gdsId) throws BusinessException {
		paramNullCheck(gdsId, "gdsId");
		paramNullCheck(gdsId.getId(), "gdsId.id");
		try {
			return gdsInfo2MediaSV.queryGds2MediasByGdsId(gdsId.getId());
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099);
		}
	}

	@Override
	public List<GdsSkuInfoRespDTO> querySkuInfosByGdsId(GdsInfoReqDTO reqDto) throws BusinessException {
		paramNullCheck(reqDto, true, "reqDto");
		paramNullCheck(reqDto.getId(), "Id");
		List<GdsSkuInfoRespDTO> skuInfos = null;
		try {
			if (CollectionUtils.isNotEmpty(reqDto.getGdsStatusArr())) {
				skuInfos = gdsInfoQuerySV.querySkuInfoRespsByGdsId(reqDto.getId(),
						GdsUtils.convertListToArr(reqDto.getGdsStatusArr()));
			} else {
				skuInfos = gdsInfoQuerySV.querySkuInfoRespsByGdsId(reqDto.getId());
			}
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099);
		}
		return skuInfos;
	}

	@Override
	public List<GdsInfoRespDTO> queryGdsInfoList(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
		paramNullCheck(gdsInfoReqDTO, "gdsInfoReqDTO");
		paramNullCheck(gdsInfoReqDTO.getShopId(), "gdsInfoReqDTO.shopId");
		try {
			return gdsInfoQuerySV.queryGdsInfoList(gdsInfoReqDTO, gdsInfoReqDTO.getGdsQueryOptions(),
					gdsInfoReqDTO.getSkuQuerys());
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099);
		}
	}

	@Override
	public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListPage(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
		paramNullCheck(gdsInfoReqDTO, "gdsInfoReqDTO");
		paramNullCheck(gdsInfoReqDTO.getShopId(), "gdsInfoReqDTO.shopId");
		try {
			return gdsInfoQuerySV.queryGdsInfoListPage(gdsInfoReqDTO, gdsInfoReqDTO.getGdsQueryOptions(),
					gdsInfoReqDTO.getSkuQuerys());
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099);
		}

	}

	@Override
	public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListPageWithAuth(GdsInfoReqDTO gdsInfoReqDTO)
			throws BusinessException {
		paramNullCheck(gdsInfoReqDTO, "gdsInfoReqDTO");
		paramNullCheck(gdsInfoReqDTO.getShopId(), "gdsInfoReqDTO.shopId");
		try {
			return gdsInfoQuerySV.queryGdsInfoListPageWithAuth(gdsInfoReqDTO, gdsInfoReqDTO.getGdsQueryOptions(),
					gdsInfoReqDTO.getSkuQuerys());
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099);
		}
	}

	@Override
	public List<Long> querySkuIdsByGdsId(GdsInfoReqDTO reqDto) throws BusinessException {
		paramNullCheck(reqDto, true, "reqDto");
		paramNullCheck(reqDto.getId(), "Id");
		List<Long> skuIds = null;
		try {
			if (CollectionUtils.isNotEmpty(reqDto.getGdsStatusArr())) {
				skuIds = gdsInfoQuerySV.querySkuIdsGdsId(reqDto.getId(),
						GdsUtils.convertListToArr(reqDto.getGdsStatusArr()));
			} else {
				skuIds = gdsInfoQuerySV.querySkuIdsGdsId(reqDto.getId());
			}
			if (CollectionUtils.isNotEmpty(skuIds)) {
				List<Long> ids = new ArrayList<Long>();
				for (Long skuId : skuIds) {
					ids.add(skuId);
				}
				return ids;

			}
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099);
		}
		return null;
	}

	@Override
	public Boolean isBelongToCategory(GdsGds2CatgReqDTO reqDto) throws BusinessException {
		paramNullCheck(reqDto, true, "reqDto");
		try {
			return gdsInfoQuerySV.isBelongToCategory(reqDto);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099);
		}
	}

	@Override
	public List<GdsInfoDetailRespDTO> queryGdsSkuInfoListByCatgRela(GdsInfoReqDTO gdsInfoReqDTO)
			throws BusinessException {
		try {
			return gdsInfoQuerySV.queryGdsSkuInfoListByCatgRela(gdsInfoReqDTO);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099);
		}

	}

	public List<GdsInfoDetailRespDTO> queryGdsSkuInfoListByRank(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
		try {
			return gdsInfoQuerySV.queryGdsSkuInfoListByRank(gdsInfoReqDTO);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099);
		}
	}

	@Override
	public HomePageGdsInfoRespDTO getHomePageGdsInfo(HomePageGdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
		try {
			return gdsInfoQuerySV.getHomePageGdsInfo(gdsInfoReqDTO);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099);
		}
	}

	@Override
	public PageResponseDTO<GdsVerifyRespDTO> queryGdsVerifyInfoPage(GdsVerifyReqDTO gdsVerifyReqDTO)
			throws BusinessException {
		paramNullCheck(gdsVerifyReqDTO, true, "gdsVerifyReqDTO");
		paramNullCheck(gdsVerifyReqDTO.getShopId(), "gdsVerifyReqDTO.shopId");
		try {
			return gdsInfoQuerySV.queryGdsVerifyInfoPage(gdsVerifyReqDTO);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "queryGdsInfoByOption Exception", e);
			throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099);
		}
	}

	@Override
	public Long countGdsInfoByShopIDAndStatus(GdsInfoReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[] { reqDTO.getShopId(), reqDTO.getGdsStatus() },
				new String[] { "reqDTO.shopId", "reqDTO.gdsStatus" });
		GdsInfoReqDTO query = new GdsInfoReqDTO();
		query.setShopId(reqDTO.getShopId());
		query.setGdsStatus(reqDTO.getGdsStatus());
		return gdsInfoQueryIDXSV.countGdsInfoByShopIDAndStatus(reqDTO);
	}

	@Override
	public Long countGdsInfoByShopIDAndStatusArr(GdsInfoReqDTO reqDTO) throws BusinessException {
		paramNullCheck(reqDTO, "reqDTO");
		paramCheck(new Object[] { reqDTO.getShopId(), reqDTO.getGdsStatusArr() },
				new String[] { "reqDTO.shopId", "reqDTO.gdsStatusArr" });
		GdsInfoReqDTO query = new GdsInfoReqDTO();
		query.setShopId(reqDTO.getShopId());
		query.setGdsStatusArr(reqDTO.getGdsStatusArr());
		return gdsInfoQueryIDXSV.countGdsInfoByShopIDAndStatus(reqDTO);
	}

    

	@Override
	public Long queryGdsVerifyInfoCount(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException {
		// TODO Auto-generated method stub
		try {
			return gdsInfoQuerySV.queryGdsVerifyInfoCount(gdsVerifyReqDTO);
		} catch (BusinessException businessException) {

			throw businessException;
		} catch (Exception e) {
			// TODO: handle exception

			throw new BusinessException();
		}

	}

    @Override
    public List<GdsPropRespDTO> getPropsByGdsId(Long gdsId, GdsInfoReqDTO gdsInfoReqDTO) {
        if(null == gdsId){
            return null;
        }
        if(null == gdsInfoReqDTO){
            gdsInfoReqDTO = new GdsInfoReqDTO();
        }
        return gdsInfoQuerySV.getPropsByGdsId(gdsId, gdsInfoReqDTO);
    }


}
