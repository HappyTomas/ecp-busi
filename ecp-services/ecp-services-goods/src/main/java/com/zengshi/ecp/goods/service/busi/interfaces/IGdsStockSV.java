package com.zengshi.ecp.goods.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dao.model.StockCompanyRepIdx;
import com.zengshi.ecp.goods.dao.model.StockInfo;
import com.zengshi.ecp.goods.dao.model.StockOptRecord;
import com.zengshi.ecp.goods.dao.model.StockRep;
import com.zengshi.ecp.goods.dao.model.StockRepAdapt;
import com.zengshi.ecp.goods.dao.model.StockShopRepIdx;
import com.zengshi.ecp.goods.dubbo.dto.AffirmStockDTO;
import com.zengshi.ecp.goods.dubbo.dto.AffirmStockMainDTO;
import com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokMainReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.GdsStockRepRespDTO;
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
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: 库存操作SV <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015-8-10上午9:50:39 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
public interface IGdsStockSV extends IGeneralSQLSV {
	/**
	 * 
	 * addStockRep:(新增仓库). <br/>
	 * 
	 * @author zjh
	 * @param stockRep
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public StockRep addStockRep(StockRepMainReqDTO stockRepMainDto)
			throws BusinessException;

	/**
	 * 
	 * addStockShopRepIdx:(新增店铺仓库索引记录). <br/>
	 * 
	 * @author zjh
	 * @param shopRepIdx
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void addStockShopRepIdx(StockShopRepIdx shopRepIdx)
			throws BusinessException;

	/**
	 * 
	 * addStockCompanyRepIdx:(新增企业仓库索引表). <br/>
	 * 
	 * @author zjh
	 * @param companyRepIdx
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void addStockCompanyRepIdx(StockCompanyRepIdx companyRepIdx)
			throws BusinessException;

	/**
	 * 
	 * addStockRepAdapt:(新增仓库适用范围). <br/>
	 * 
	 * @author zjh
	 * @param stockRepAdaptDTO
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void addStockRepAdapt(StockRepAdaptReqDTO stockRepAdaptDTO)
			throws BusinessException;

	/**
	 * 
	 * queryStockRepInfoByShopId:(分页查询店铺下仓库信息). <br/>
	 * 
	 * @author zjh
	 * @param stockRepDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public PageResponseDTO<StockRepPageRespDTO> queryStockRepInfoByShopId(
			StockRepReqDTO stockRepDTO) throws BusinessException;

	/**
	 * 
	 * queryStockRepAdaptProvinceByShopId:(获取店铺仓库覆盖的省份列表). <br/>
	 * 
	 * @author zjh
	 * @param stockRepAdaptDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public List<StockRepAdaptRespDTO> queryStockRepAdaptProvinceByShopId(
			StockRepAdaptReqDTO stockRepAdaptDTO) throws BusinessException;

	/**
	 * 
	 * queryStockRepAdaptCityByProvince:(获取店铺在某个省份下覆盖的地市列表). <br/>
	 * 
	 * @author zjh
	 * @param stockRepAdaptDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public List<StockRepAdaptRespDTO> queryStockRepAdaptCityByProvince(
			StockRepAdaptReqDTO stockRepAdaptDTO) throws BusinessException;

	/**
	 * 
	 * editStockRepAdaptInfo:(失效仓库适用范围). <br/>
	 * 
	 * @author zjh
	 * @param stockRepMainDto
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void editStockRepAdaptInfo(StockRepMainReqDTO stockRepMainDto)
			throws BusinessException;

	// public List<StockRepAdaptDTO>
	// queryStockRepAdaptProvinceByRepCode(StockRepAdaptDTO
	// stockRepAdaptDTO) throws BusinessException ;
	/**
	 * 
	 * isExistCityLevel:(判断仓库适用范围是否到地市一级). <br/>
	 * 
	 * @author zjh
	 * @param stockRepAdapt
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Boolean isExistCityLevel(StockRepAdaptRespDTO stockRepAdapt)
			throws BusinessException;

	/**
	 * 
	 * tagProvinceLevel:(标记仓库在某个省份下的覆盖情况：1店铺仓库是否覆盖到地市一级2当前仓库是否有覆盖). <br/>
	 * 
	 * @author zjh
	 * @param stockRepAdaptDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public StockRepAdaptMainDTO tagProvinceLevel(
			StockRepAdaptReqDTO stockRepAdaptDTO) throws BusinessException;

	/**
	 * 
	 * isExistAdapt:(判断仓库在该区域是否有覆盖). <br/>
	 * 
	 * @author zjh
	 * @param stockRepAdapt
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Boolean isExistAdapt(StockRepAdapt stockRepAdapt)
			throws BusinessException;

	/**
	 * 
	 * isExistAdaptOtherRep:(判断是否在某个省份下有其他的仓库覆盖). <br/>
	 * 
	 * @author zjh
	 * @param stockRepAdapt
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Boolean isExistAdaptOtherRep(StockRepAdapt stockRepAdapt)
			throws BusinessException;

	/**
	 * 
	 * editStockRep:(编辑仓库). <br/>
	 * 
	 * @author zjh
	 * @param stockRepMainDto
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void editStockRep(StockRepMainReqDTO stockRepMainDto)
			throws BusinessException;

	/**
	 * 
	 * deleteStockRep:(删除仓库). <br/>
	 * 
	 * @author zjh
	 * @param stockRepDTO
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void deleteStockRep(StockRepReqDTO stockRepDTO)
			throws BusinessException;

	/**
	 * 
	 * addStockRepHis:(新增仓库历史记录). <br/>
	 * 
	 * @author zjh
	 * @param stockRep
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void addStockRepHis(StockRep stockRep) throws BusinessException;

	/**
	 * 
	 * addStockRepAdaptHis:(新增仓库适用范围历史记录). <br/>
	 * 
	 * @author zjh
	 * @param stockRepAdapt
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void addStockRepAdaptHis(StockRepAdapt stockRepAdapt)
			throws BusinessException;

	/**
	 * 
	 * deleteStockRepAdapt:(删除仓库适用范围). <br/>
	 * 
	 * @author zjh
	 * @param stockRepAdapt
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void deleteStockRepAdapt(StockRepAdapt stockRepAdapt)
			throws BusinessException;

	/**
	 * 
	 * deleteStockInfo:(删除库存记录). <br/>
	 * 
	 * @author zjh
	 * @param stockInfo
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void deleteStockInfo(StockInfoReqDTO stockInfo)
			throws BusinessException;

	/**
	 * 
	 * queryStockPageInfo:(条件获取库存列表). <br/>
	 * 
	 * @author zjh
	 * @param stockInfoDto
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public PageResponseDTO<StockInfoPageRespDTO> queryStockPageInfo(
			StockInfoReqDTO stockInfoDto) throws BusinessException;

	public Long countSkus(
			StockInfoReqDTO stockInfoDto) throws BusinessException;

	/**
	 * 
	 * addStockInfo:(新增库存). <br/>
	 * 
	 * @author zjh
	 * @param stockInfoDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public StockInfoReqDTO addStockInfo(StockInfoReqDTO stockInfoDTO)
			throws BusinessException;

	/**
	 * 
	 * updateStockInfo:(更新库存). <br/>
	 * 
	 * @author zjh
	 * @param stockInfoDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Long updateStockInfo(StockInfoReqDTO stockInfoDTO)
			throws BusinessException;

	/**
	 * 
	 * addStockInfoHis:(新增库存历史记录). <br/>
	 * 
	 * @author zjh
	 * @param stockInfo
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void addStockInfoHis(StockInfo stockInfo) throws BusinessException;

	/**
	 * 
	 * addStockOptRecord:(新增库存操作记录). <br/>
	 * 
	 * @author zjh
	 * @param stockOptRecord
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void addStockOptRecord(StockOptRecord stockOptRecord)
			throws BusinessException;

	/**
	 * 
	 * addStockPreOccupy:(新增库存预占记录). <br/>
	 * 
	 * @author zjh
	 * @param ROrdCartItemCommRequest
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void addStockPreOccupy(ROrdCartItemCommRequest cartItemCommRequest)
			throws BusinessException;

	/**
	 * 
	 * deleteStockPreOccupy:(取消库存预占记录). <br/>
	 * 
	 * @author zjh
	 * @param delPreOccupyDTO
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void deleteStockPreOccupy(ROrdCartCommRequest cartCommRequest)
			throws BusinessException;

	/**
	 * 
	 * updateDeliverySkuStcok:(子订单发货). <br/>
	 * 
	 * @author zjh
	 * @param deliverySkuStcokDTO
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void updateDeliverySkuStcokForSub(
			DeliverySkuStcokReqDTO deliverySkuStcokDTO)
			throws BusinessException;

	public void updateDeliverySkuStcok(
			DeliverySkuStcokMainReqDTO deliverySkuStcokMainDTO)
			throws BusinessException;

	/**
	 * 
	 * updateAffirmReceiptStock:(子订单确认收货). <br/>
	 * 
	 * @author zjh
	 * @param affirmStockDTO
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void updateAffirmReceiptStockForSub(AffirmStockDTO affirmStockDTO)
			throws BusinessException;

	/**
	 * 
	 * updateAffirmReceiptStock:(确认收货). <br/>
	 * 
	 * @author zjh
	 * @param affirmStockMainDTO
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void updateAffirmReceiptStock(AffirmStockMainDTO affirmStockMainDTO)
			throws BusinessException;

	/**
	 * 
	 * addStockInfoForInput:(商品录入新增库存). <br/>
	 * 
	 * @author zjh
	 * @param stockInfoDTO
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void addStockInfoForInput(StockInfoReqDTO stockInfoDTO)
			throws BusinessException;

	/**
	 * 
	 * queryStockInfoByGds:(获取单品库存信息). <br/>
	 * 
	 * @author zjh
	 * @param stockInfoForGdsDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public StockInfoRespDTO queryStockInfoByGds(
			StockInfoForGdsReqDTO stockInfoForGdsDTO) throws BusinessException;

	/**
	 * 
	 * queryStockRepInfoById:(根据id获取仓库信息). <br/>
	 * 
	 * @author zjh
	 * @param stockRepDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public StockRepRespDTO queryStockRepInfoById(StockRepReqDTO stockRepDTO)
			throws BusinessException;

	/**
	 * 
	 * queryShopRepInfoForGdsInput:(商品录入时，库存录入列表初始化). <br/>
	 * 
	 * @author zjh
	 * @param stockRepDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public List<StockRepRespDTO> queryShopRepInfoForGdsInput(
			StockRepReqDTO stockRepDTO) throws BusinessException;

	/**
	 * 
	 * queryShopGdsStockInfoForAct:(为活动查询商品或者单品的可用库存总数). <br/>
	 * 
	 * @author zjh
	 * @param stockInfoDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	// public Long queryShopGdsStockInfoForAct(StockInfoReqDTO stockInfoDTO)
	// throws BusinessException;

	/**
	 * 
	 * queryGdsStockInfos:(分仓时，获取单品库存信息). <br/>
	 * 查询时shopid和skuid必传
	 * 
	 * @author zjh
	 * @param stockInfoDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public List<GdsStockRepRespDTO> queryGdsStockInfos(
			StockInfoReqDTO stockInfoDTO) throws BusinessException;

	/**
	 * 
	 * addBatchStockPreOccupy:(新增预占). <br/>
	 * 
	 * 
	 * @author zjh
	 * @param cartCommRequest
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void addBatchStockPreOccupy(ROrdCartCommRequest cartCommRequest)
			throws BusinessException;

	/**
	 * 
	 * deleteBatchStockPreOccupy:(订单提交回退库存预占服务). <br/>
	 * 
	 * @author zjh
	 * @param cartsCommRequest
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void deleteBatchStockPreOccupy(ROrdCartsCommRequest cartsCommRequest)
			throws BusinessException;

	/**
	 * 
	 * addBatchOrderStockPreOccupy:(订单提交新增预占). <br/>
	 * 
	 * @author zjh
	 * @param batchOrderDTO
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void addBatchOrderStockPreOccupy(
			ROrdCartsCommRequest cartsCommRequest) throws BusinessException;

	/**
	 * 查询单品所有库存信息
	 * 
	 * @param stockInfoForGdsDTO
	 * @return
	 * @throws BusinessException
	 */
	public List<StockInfoRespDTO> queryStockInfosBySkuId(
			StockInfoForGdsReqDTO stockInfoForGdsDTO) throws BusinessException;

	/**
	 * 删除单品库存及相关信息
	 * 
	 * @param stockInfoReqDTO
	 * @throws BusinessException
	 */

	public void deleteStockInfoBySkuInfo(StockInfoReqDTO stockInfoReqDTO)
			throws BusinessException;

	/**
	 * 
	 * deleteSubStockPreOccupy:(取消库存预占 明细级别). <br/>
	 * 
	 * @author linwb3
	 * @param cartItemCommRequest
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void deleteSubStockPreOccupy(
			ROrdCartItemCommRequest cartItemCommRequest)
			throws BusinessException;

	/**
	 * 
	 * updateStockInfoByFixedVal:(同步ERP接口). <br/>
	 * 
	 * @author zhangjh6
	 * @param stockInfoReqDTO
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public void updateStockInfoByFixedVal(StockInfoReqDTO stockInfoReqDTO)
			throws Exception;

	/**
	 * 
	 * syncStockCatgInfoByGdsTurn:(同步库存的分类数据). <br/>
	 * 
	 * @author zjh
	 * @param infoReqDTO
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public void syncStockCatgInfoByGdsTurn(StockInfoReqDTO infoReqDTO)
			throws Exception;

	/**
	 * 
	 * returnStockInfo:(退货). <br/>
	 * 
	 * 
	 * @author zjh
	 * @param returnInfoReqDTO
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public void returnStockInfo(StockReturnInfoReqDTO returnInfoReqDTO)
			throws Exception;

	/**
	 * 
	 * statisticStockLack:(统计缺货数量). <br/>
	 * 
	 * 
	 * @author zjh
	 * @param returnInfoReqDTO
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public Long statisticStockLack(StockInfoReqDTO stockInfoReqDTO)
			throws Exception;
	/**
	 * 
	 * queryStockInfoById:根据库存ID查询库存信息. <br/> 
	 * 
	 * @author liyong7
	 * @param stockId
	 * @return
	 * @throws Exception 
	 * @since JDK 1.6
	 */
	public StockInfoRespDTO queryStockInfoById(Long stockId)throws BusinessException;
}
