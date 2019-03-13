package com.zengshi.ecp.goods.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dao.model.StockInfo;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.AffirmStockMainDTO;
import com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokMainReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoForGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoPageRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepAdaptMainDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepAdaptReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepAdaptRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepMainReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepPageRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockReturnInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.unpf.dubbo.dto.stock.GdsStockReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.stock.SkuStockReqDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.stock.IUnpfStockRSV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015-9-2下午4:05:45 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
public class GdsStockRSVImpl implements IGdsStockRSV {
	@Resource(name = "gdsStockSV")
	IGdsStockSV gdsStockSV;

	
	@Resource
    private IUnpfStockRSV unpfStockRSV;
	@Resource
	private IGdsInfoQueryRSV gdsInfoQueryRSV;
	
	private static String MODULE = GdsStockRSVImpl.class.getName();

	/**
	 * 
	 * TODO 新增仓库（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#addStockRep(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepMainReqDTO)
	 */
	@Override
	public void addStockRep(StockRepMainReqDTO stockRepMainDTO)
			throws BusinessException {
		try {
			if (stockRepMainDTO == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);

			}

			StockRepReqDTO stockRepDTO = stockRepMainDTO.getStockRepDTO();
			if (stockRepDTO == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "stockRepDTO" });

			}

			if (stockRepDTO.getRepName() == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "repName" });

			}

			if (stockRepDTO.getCodeType() == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "codeType" });

			}

			if (stockRepDTO.getRepType() == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "repType" });

			}

			gdsStockSV.addStockRep(stockRepMainDTO);
		} catch (BusinessException e) {
			throw e;

		} catch (Exception e) {
			LogUtil.error(MODULE, "新增仓库失败", e);

			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235009);
		}

	}

	/**
	 * 
	 * TODO 获取店铺仓库列表（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#queryStockRepInfoByShopId(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepReqDTO)
	 */
	@Override
	public PageResponseDTO<StockRepPageRespDTO> queryStockRepInfoByShopId(
			StockRepReqDTO stockRepDTO) throws BusinessException {

		try {
			if (stockRepDTO == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);

			}
			if (stockRepDTO.getShopId() == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "shopId" });
			}

			return gdsStockSV.queryStockRepInfoByShopId(stockRepDTO);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "获取店铺仓库列表失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235010);
		}
	}

	/**
	 * 
	 * TODO 获取店铺仓库覆盖省份列表（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#queryStockRepAdaptProvinceByShopId(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepAdaptReqDTO)
	 */
	@Override
	public List<StockRepAdaptRespDTO> queryStockRepAdaptProvinceByShopId(
			StockRepAdaptReqDTO stockRepAdaptDTO) throws BusinessException {

		try {
			if (stockRepAdaptDTO == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);
			}
			if (stockRepAdaptDTO.getShopId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "shopId" });
			}

			return gdsStockSV
					.queryStockRepAdaptProvinceByShopId(stockRepAdaptDTO);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "获取店铺仓库覆盖省份列表失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235011);
		}

	}

	/**
	 * 
	 * TODO 获取店铺在某个省份下覆盖的地市列表（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#queryStockRepAdaptCityByProvince(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepAdaptReqDTO)
	 */
	@Override
	public List<StockRepAdaptRespDTO> queryStockRepAdaptCityByProvince(
			StockRepAdaptReqDTO stockRepAdaptDTO) throws BusinessException {

		try {
			if (stockRepAdaptDTO == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);
			}
			if (stockRepAdaptDTO.getShopId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "shopId" });
			}
			if (stockRepAdaptDTO.getAdaptProvince() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "adaptProvince" });
			}
			return gdsStockSV
					.queryStockRepAdaptCityByProvince(stockRepAdaptDTO);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "获取店铺在某个省份下覆盖的地市列表失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235012);
		}
	}

	/**
	 * 
	 * TODO 编辑仓库（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#editStockRep(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepMainReqDTO)
	 */
	@Override
	public void editStockRep(StockRepMainReqDTO stockRepMainDto)
			throws BusinessException {

		try {
			if (stockRepMainDto == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);
			}
			if (stockRepMainDto.getStaffId() == 0L) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "staffId" });

			}
			if (stockRepMainDto.getStockRepDTO() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "stockRepDTO" });
			}
			StockRepReqDTO stockRepDTO = stockRepMainDto.getStockRepDTO();

			if (stockRepDTO.getId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "stockRepDTO.Id" });
			}
			if (stockRepDTO.getRepName() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "stockRepDTO.repName" });
			}

			if (stockRepDTO.getShopId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "stockRepDTO.shopId" });
			}
			gdsStockSV.editStockRep(stockRepMainDto);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "编辑仓库失败", e);

			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235013);
		}
	}

	/**
	 * 
	 * TODO 失效仓库（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#deleteStockRep(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepReqDTO)
	 */
	@Override
	public void deleteStockRep(StockRepReqDTO stockRepDTO)
			throws BusinessException {

		try {
			if (stockRepDTO == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);
			}
			if (stockRepDTO.getId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "id" });
			}
			if (stockRepDTO.getCompanyId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "companyId" });
			}

			if (stockRepDTO.getShopId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "shopId" });
			}
			gdsStockSV.deleteStockRep(stockRepDTO);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "失效仓库失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235014);
		}
	}

	/**
	 * 
	 * TODO 删除库存信息（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#deleteStockInfo(com.zengshi.ecp.goods.dao.model.StockInfo)
	 */
	@Override
	public void deleteStockInfo(StockInfoReqDTO stockInfoDTO)
			throws BusinessException {

		try {
			if (stockInfoDTO == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);

			}
			if (stockInfoDTO.getId() == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "id" });
			}
			if (stockInfoDTO.getShopId() == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "shopId" });
			}
			if (stockInfoDTO.getCompanyId() == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "companyId" });
			}
			gdsStockSV.deleteStockInfo(stockInfoDTO);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "删除库存信息失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235015);
		}
	}

	@Override
	public Long countSkus(StockInfoReqDTO stockInfoDto) throws BusinessException {
		try {
			if (stockInfoDto == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);
			}
			return gdsStockSV.countSkus(stockInfoDto);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "条件获取单品总数失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235016);
		}
	}

	/**
	 * 
	 * TODO 条件获取库存列表（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#queryStockPageInfo(com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO)
	 */
	@Override
	public PageResponseDTO<StockInfoPageRespDTO> queryStockPageInfo(
			StockInfoReqDTO stockInfoDto) throws BusinessException {

		try {
			if (stockInfoDto == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);
			}
			if (stockInfoDto.getShopId() == null
					|| stockInfoDto.getShopId() == 0L) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "shopId" });
			}
			return gdsStockSV.queryStockPageInfo(stockInfoDto);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "条件获取库存列表失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235016);
		}
	}

	/**
	 * 
	 * TODO 变更库存（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#updateStockInfo(com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO)
	 */
	@Override
	public Long updateStockInfo(StockInfoReqDTO stockInfoDTO)
			throws BusinessException {

		try {
			if (stockInfoDTO == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);
			}

			if (stockInfoDTO.getId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "id" });
			}

			if (stockInfoDTO.getTurnType() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "turnType" });
			}

			if (stockInfoDTO.getTurnCount() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "turnCount" });
			}

			if (stockInfoDTO.getStaffId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "staffId" });
			}
			Long cnt = gdsStockSV.updateStockInfo(stockInfoDTO);
			
			// 库存变更发送库存变更消息。
			StockInfoRespDTO realStock = gdsStockSV.queryStockInfoById(stockInfoDTO.getId());
			// 推送库存变更通知。
            notifyStockAlteration(realStock);
			return cnt;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "变更库存失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235018);
		}
	}


	/**
	 * 
	 * TODO 下单新增库存预占（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#addStockPreOccupy(com.zengshi.ecp.goods.dubbo.dto.StockPreOccupyReqDTO)
	 */
	@Override
	public void addStockPreOccupy(ROrdCartItemCommRequest cartItemCommRequest)
			throws BusinessException {
		try {
			if (cartItemCommRequest == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);
			}

			if (cartItemCommRequest.getShopId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "shopId" });
			}
			if (cartItemCommRequest.getSkuId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "skuId" });
			}
			if (cartItemCommRequest.getOrderAmount() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "count" });
			}
			if (cartItemCommRequest.getOrderId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "orderId" });
			}
			if (cartItemCommRequest.getOrderSubId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "subOrder" });
			}
			if (cartItemCommRequest.getRepCode() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "repCode" });
			}
			if (cartItemCommRequest.getStaffId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "staffId" });
			}
			if (cartItemCommRequest.getStockId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "stockId" });
			}
			gdsStockSV.addStockPreOccupy(cartItemCommRequest);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "下单新增库存预占失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235019);
		}
	}

	/**
	 * 
	 * TODO 取消订单失效库存预占（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#deleteStockPreOccupy(com.zengshi.ecp.goods.dubbo.dto.DelPreOccupyReqDTO)
	 */
	@Override
	public void deleteStockPreOccupy(ROrdCartCommRequest cartCommRequest)
			throws BusinessException {

		try {
			if (cartCommRequest == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);
			}
			if (cartCommRequest.getOrdCartItemCommList() == null
					|| cartCommRequest.getOrdCartItemCommList().size() == 0) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "OrdCartItemCommList" });
			}
			if (cartCommRequest.getStaffId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "staffId" });
			}
			gdsStockSV.deleteStockPreOccupy(cartCommRequest);
			
			// 遍历，以子订单级取消
	       /* for (ROrdCartItemCommRequest cartItemCommRequest : cartCommRequest.getOrdCartItemCommList()) {
	            // 库存变更发送库存变更消息。
	            StockInfoRespDTO realStock = gdsStockSV.queryStockInfoById(cartItemCommRequest.getStockId());
	            notifyStockAlteration(realStock);
	        }*/
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "取消订单失效库存预占失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235020);
		}
	}

	/**
	 * 
	 * TODO 发货（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#updateDeliverySkuStcok(com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokDTO)
	 */
	@Override
	public void updateDeliverySkuStcok(
			DeliverySkuStcokMainReqDTO deliverySkuStcokMainDTO)
			throws BusinessException {

		try {
			if (deliverySkuStcokMainDTO == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);
			}

			if (deliverySkuStcokMainDTO.getDeliverySkuStcokDTOs() == null
					|| deliverySkuStcokMainDTO.getDeliverySkuStcokDTOs().size() == 0) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "deliverySkuStcokDTOs" });
			}

			gdsStockSV.updateDeliverySkuStcok(deliverySkuStcokMainDTO);
			
			/*for (DeliverySkuStcokReqDTO deliverySkuStcokDTO : deliverySkuStcokMainDTO.getDeliverySkuStcokDTOs()) {
			    // 库存变更发送库存变更消息。
	            StockInfoRespDTO realStock = gdsStockSV.queryStockInfoById(deliverySkuStcokDTO.getStockId());
	            notifyStockAlteration(realStock);
	        }*/
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "发货失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235021);
		}
	}

	/**
	 * 
	 * TODO 确认收货（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#updateAffirmReceiptStock(com.zengshi.ecp.goods.dubbo.dto.AffirmStockDTO)
	 */
	@Override
	public void updateAffirmReceiptStock(AffirmStockMainDTO affirmStockMainDTO)
			throws BusinessException {

		try {
			if (affirmStockMainDTO == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);
			}

			if (affirmStockMainDTO.getStaffId() == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "staffId" });
			}

			if (affirmStockMainDTO.getShopId() == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "shopId" });
			}

			if (affirmStockMainDTO.getCompanyId() == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "companyId" });
			}
			gdsStockSV.updateAffirmReceiptStock(affirmStockMainDTO);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "确认收货失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235022);
		}
	}

	/**
	 * 
	 * TODO 商品录入库存新增（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#addStockInfoForInput(com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO)
	 */
	@Override
	public void addStockInfoForInput(StockInfoReqDTO stockInfoDTO)
			throws BusinessException {

		try {
			if (stockInfoDTO == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);
			}
			if (stockInfoDTO.getCatgCode() == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "catgCode" });
			}
			if (stockInfoDTO.getCompanyId() == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "companyId" });
			}
			if (stockInfoDTO.getTypeId() == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "typeId" });
			}
			if (stockInfoDTO.getGdsId() == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "gdsId" });
			}

			if (stockInfoDTO.getSkuId() == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "skuId" });
			}
			if (stockInfoDTO.getShopId() == null) {

				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "shopId" });
			}

			if (stockInfoDTO.getStaffId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "staffId" });

			}
			if (stockInfoDTO.getRepCode() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "repCode" });
			}
			if (stockInfoDTO.getRepType() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "repType" });
			}
			if (stockInfoDTO.getTurnCount() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "turnCount" });
			}
			if (stockInfoDTO.getStockType() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "stockType" });
			}

			gdsStockSV.addStockInfoForInput(stockInfoDTO);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "确商品录入库存新增失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235023);
		}
	}

	/**
	 * 
	 * TODO 获取单品库存信息（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#queryStockInfoByGds(com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoForGdsReqDTO)
	 */
	@Override
	public StockInfoRespDTO queryStockInfoByGds(
			StockInfoForGdsReqDTO stockInfoForGdsDTO) throws BusinessException {

		try {
			if (stockInfoForGdsDTO == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);
			}

			if (stockInfoForGdsDTO.getShopId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "shopId" });
			}
			if (stockInfoForGdsDTO.getGdsId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "gdsId" });
			}
			if (stockInfoForGdsDTO.getSkuId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "skuId" });
			}
			// 获取是否开启区域化库存状态
			BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil
					.fetchSysCfg("IF_OPEN_REGIONAL_STOCK");
			if (baseSysCfgRespDTO.getParaValue() != null
					&& "1".equals(baseSysCfgRespDTO.getParaValue())) {

				// if (stockInfoForGdsDTO.getAdaptCountry() == null) {
				// throw new
				// BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,
				// new String[] { "adaptCountry" });
				// }
				// if (stockInfoForGdsDTO.getAdaptProvince() == null) {
				// throw new
				// BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,
				// new String[] { "adaptProvince" });
				// }
				// if (stockInfoForGdsDTO.getAdaptCity() == null) {
				// throw new
				// BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,
				// new String[] { "adaptCity" });
				// }

			}

			return gdsStockSV.queryStockInfoByGds(stockInfoForGdsDTO);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "获取单品库存信息失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235024);
		}
	}

	/**
	 * 
	 * TODO 打标识，标示仓库已有适用范围（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#tagProvinceLevel(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepAdaptReqDTO)
	 */
	@Override
	public StockRepAdaptMainDTO tagProvinceLevel(
			StockRepAdaptReqDTO stockRepAdaptDTO) throws BusinessException {
		try {
			if (stockRepAdaptDTO == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);
			}
			if (stockRepAdaptDTO.getShopId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "shopId" });
			}
			return gdsStockSV.tagProvinceLevel(stockRepAdaptDTO);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "打标识，标示仓库已有适用范围失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235026);
		}
	}

	/**
	 * 
	 * TODO 根据id查询仓库信息（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#queryStockRepInfoById(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepReqDTO)
	 */
	@Override
	public StockRepRespDTO queryStockRepInfoById(StockRepReqDTO stockRepDTO)
			throws BusinessException {
		try {

			if (stockRepDTO == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200097);
			}
			if (stockRepDTO.getId() == null) {
				throw new BusinessException(
						GdsErrorConstants.Commons.ERROR_GOODS_200100,
						new String[] { "id" });
			}
			return gdsStockSV.queryStockRepInfoById(stockRepDTO);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "获取仓库信息失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235027);
		}
	}

	// /**
	// *
	// * TODO 为活动查询商品或者单品可用库存总数（可选）.
	// *
	// * @see
	// com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#queryShopGdsStockInfoForAct(com.zengshi.ecp.goods.dubbo.dto.StockInfoReqDTO)
	// */
	// @Override
	// public Long queryShopGdsStockInfoForAct(StockInfoReqDTO stockInfoDTO)
	// throws BusinessException {
	//
	// try {
	//
	// if (stockInfoDTO == null) {
	// throw new
	// BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200097);
	// }
	// if (stockInfoDTO.getShopId() == null) {
	// throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,
	// new String[] { "shopId" });
	// }
	// if (stockInfoDTO.getGdsId() == null) {
	// throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,
	// new String[] { "gdsId" });
	// }
	// return gdsStockSV.queryShopGdsStockInfoForAct(stockInfoDTO);
	// } catch (BusinessException e) {
	// throw e;
	// } catch (Exception e) {
	// LogUtil.error(MODULE, "查询活动商品库存信息失败！", e);
	// throw new
	// BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235027);
	// }
	// }

	/**
	 * 
	 * TODO 商品录入时，库存录入列表初始（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#queryShopRepInfoForGdsInput(com.zengshi.ecp.goods.dubbo.dto.stock.StockRepReqDTO)
	 */
	@Override
	public List<StockRepRespDTO> queryShopRepInfoForGdsInput(
			StockRepReqDTO stockRepDTO) throws BusinessException {
		try {
			return gdsStockSV.queryShopRepInfoForGdsInput(stockRepDTO);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "获取仓库信息失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235027);
		}
	}

	/**
	 * 
	 * TODO 新增预占（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#addBatchStockPreOccupy()
	 */
	@Override
	public void addBatchStockPreOccupy(ROrdCartCommRequest cartCommRequest)
			throws BusinessException {
		try {
			gdsStockSV.addBatchStockPreOccupy(cartCommRequest);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "下单新增库存预占失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235019);
		}
	}

	/**
	 * 
	 * deleteBatchStockPreOccupy:(订单提交失败，库存回退服务). <br/>
	 * 
	 * @author zjh
	 * @param delPreOccupyBatchDTO
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	@Override
	public void deleteBatchStockPreOccupy(ROrdCartsCommRequest cartsCommRequest)
			throws BusinessException {
		try {
			gdsStockSV.deleteBatchStockPreOccupy(cartsCommRequest);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "下订单失败时，回退库存预占失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235029);
		}

	}

	/**
	 * 
	 * TODO 下订单，新增预占（可选）.
	 * 
	 * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV#addBatchOrderStockPreOccupy(com.zengshi.ecp.goods.dubbo.dto.StockPreOccupyBatchOrderReqDTO)
	 */
	@Override
	public void addBatchOrderStockPreOccupy(
			ROrdCartsCommRequest cartsCommRequest) throws BusinessException {
		if (cartsCommRequest == null) {
			throw new BusinessException(
					GdsErrorConstants.Commons.ERROR_GOODS_200097);
		}
		if (cartsCommRequest.getStaffId() == null) {
			throw new BusinessException(
					GdsErrorConstants.Commons.ERROR_GOODS_200100,
					new String[] { "staffId" });
		}
		try {
			gdsStockSV.addBatchOrderStockPreOccupy(cartsCommRequest);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "下订单失败时，回退库存预占失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235029);
		}

	}

	@Override
	public void returnStockInfo(StockReturnInfoReqDTO returnInfoReqDTO)
			throws BusinessException {
		try {
			gdsStockSV.returnStockInfo(returnInfoReqDTO);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "退货失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235029);
		}
	}

	@Override
	public Long statisticStockLack(StockInfoReqDTO stockInfoReqDTO)
			throws BusinessException {
		try {
			return gdsStockSV.statisticStockLack(stockInfoReqDTO);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LogUtil.error(MODULE, "获取缺货商品总数失败", e);
			throw new BusinessException(
					GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235033);
		}
	}
	
	
	/** 
     * notifyStockAlteration:(这里用一句话描述这个方法的作用). <br/> 
     * 
     * @author liyong7
     * @param realStock 
     * @since JDK 1.6 
     */ 
    private void notifyStockAlteration(StockInfoRespDTO realStock) {
        // 商品数据获得
        GdsInfoReqDTO gdsInfo = new GdsInfoReqDTO();
        gdsInfo.setId(realStock.getGdsId());
        gdsInfo.setShopId(realStock.getShopId());
        GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[1];
        SkuQueryOption[] skuQuerys = new SkuQueryOption[1];
        gdsQueryOptions[0] = GdsQueryOption.ALL;
        skuQuerys[0] = SkuQueryOption.ALL;
        gdsInfo.setGdsQueryOptions(gdsQueryOptions);
        gdsInfo.setSkuQuerys(skuQuerys);
        GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfoByOption(gdsInfo);
        List<SkuStockReqDTO> stockReqDTOLst = null;
        long quantity = 0;
        if (gdsInfoRespDTO==null || gdsInfoRespDTO.getId()==null) {
            LogUtil.error(MODULE, "根据产品编码gdsId="+realStock.getGdsId()+"查找不到对应产品信息!");
            throw new BusinessException("根据产品编码gdsId="+realStock.getGdsId()+"查找不到对应产品信息!");
        }else{
            if (CollectionUtils.isNotEmpty(gdsInfoRespDTO.getSkus())) {
                stockReqDTOLst = new ArrayList<>();
                for(GdsSkuInfoRespDTO sku : gdsInfoRespDTO.getSkus()){
                    quantity += sku.getRealAmount();
                    SkuStockReqDTO skuStockReqDTO = new SkuStockReqDTO();
                    skuStockReqDTO.setSkuId(sku.getId());
                    skuStockReqDTO.setQuanties(sku.getRealAmount());
                    stockReqDTOLst.add(skuStockReqDTO);
                }
                
                GdsStockReqDTO stockReq = new GdsStockReqDTO();
                stockReq.setGdsId(realStock.getGdsId());
                stockReq.setShopId(realStock.getShopId());
                stockReq.setQuanties(quantity);
                stockReq.setSkuInfos(stockReqDTOLst);
                unpfStockRSV.updateStock(stockReq);
            }
        }
    }
}
