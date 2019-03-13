package com.zengshi.ecp.goods.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dubbo.dto.AffirmStockMainDTO;
import com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokMainReqDTO;
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

/**
 * 
 * Title: 商品库存dubbo服务<br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015-8-17下午8:38:23 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
public interface IGdsStockRSV {
    /**
     * 
     * addStockRep:(新增仓库). <br/>
     * 
     * @author zjh
     * @param stockRep
     * @throws BusinessBusinessException
     * @since JDK 1.6
     */
    public void addStockRep(StockRepMainReqDTO stockRepMainDto) throws BusinessException;

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
    public PageResponseDTO<StockRepPageRespDTO> queryStockRepInfoByShopId(StockRepReqDTO stockRepDTO)
            throws BusinessException;

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
    public List<StockRepAdaptRespDTO> queryStockRepAdaptCityByProvince(StockRepAdaptReqDTO stockRepAdaptDTO)
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
    public void editStockRep(StockRepMainReqDTO stockRepMainDto) throws BusinessException;

    /**
     * 
     * deleteStockRep:(删除仓库). <br/>
     * 
     * @author zjh
     * @param stockRepDTO
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void deleteStockRep(StockRepReqDTO stockRepDTO) throws BusinessException;

    /**
     * 
     * deleteStockInfo:(删除库存记录). <br/>
     * 
     * @author zjh
     * @param stockInfo
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void deleteStockInfo(StockInfoReqDTO stockInfoDTO) throws BusinessException;

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
    public PageResponseDTO<StockInfoPageRespDTO> queryStockPageInfo(StockInfoReqDTO stockInfoDto)
            throws BusinessException;

    public Long countSkus(StockInfoReqDTO stockInfoDto)
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
    public Long updateStockInfo(StockInfoReqDTO stockInfoDTO) throws BusinessException;

    /**
     * 
     * addStockPreOccupy:(新增库存预占记录). <br/>
     * 
     * @author zjh
     * @param stockPreOccupyDTO
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void addStockPreOccupy(ROrdCartItemCommRequest cartItemCommRequest) throws BusinessException;

    /**
     * 
     * deleteStockPreOccupy:(取消库存预占记录). <br/>
     * 
     * @author zjh
     * @param delPreOccupyDTO
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void deleteStockPreOccupy(ROrdCartCommRequest cartCommRequest) throws BusinessException;

    /**
     * 
     * updateDeliverySkuStcok:(发货). <br/>
     * 
     * @author zjh
     * @param deliverySkuStcokDTO
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void updateDeliverySkuStcok(DeliverySkuStcokMainReqDTO deliverySkuStcokMainDTO)
            throws BusinessException;

    /**
     * 
     * updateAffirmReceiptStock:(确认收货). <br/>
     * 
     * @author zjh
     * @param affirmStockDTO
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void updateAffirmReceiptStock(AffirmStockMainDTO affirmStockMainDTO) throws BusinessException;

    /**
     * 
     * addStockInfoForInput:(商品录入新增库存). <br/>
     * 
     * @author zjh
     * @param stockInfoDTO
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void addStockInfoForInput(StockInfoReqDTO stockInfoDTO) throws BusinessException;

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
    public StockInfoRespDTO queryStockInfoByGds(StockInfoForGdsReqDTO stockInfoForGdsDTO)
            throws BusinessException;

    /**
     * 
     * tagProvinceLevel:(打标识，标示仓库已有适用范围). <br/>
     * 
     * @author zjh
     * @param stockRepAdaptDTO
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public StockRepAdaptMainDTO tagProvinceLevel(StockRepAdaptReqDTO stockRepAdaptDTO)
            throws BusinessException;

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
    public StockRepRespDTO queryStockRepInfoById(StockRepReqDTO stockRepDTO) throws BusinessException;

    /**
     * 
     * queryShopGdsStockInfoForAct:(为活动查询商品或者单品可用库存总数). <br/>
     * 
     * @author zjh
     * @param stockInfoDTO
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
//    public Long queryShopGdsStockInfoForAct(StockInfoReqDTO stockInfoDTO) throws BusinessException;

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
    public List<StockRepRespDTO> queryShopRepInfoForGdsInput(StockRepReqDTO stockRepDTO) throws BusinessException; 

    /**
     * 
     * addBatchStockPreOccupy:(新增预占). <br/> 
     * 
     * @author zjh 
     * @param stockPreOccupyBatchDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void addBatchStockPreOccupy(
			ROrdCartCommRequest cartCommRequest)throws BusinessException;

/**
 * 
 * deleteBatchStockPreOccupy:(订单提交失败，库存回退服务). <br/> 
 * 
 * @author zjh 
 * @param delPreOccupyBatchDTO
 * @throws BusinessException 
 * @since JDK 1.6
 */
    public void deleteBatchStockPreOccupy(
			ROrdCartsCommRequest cartsCommRequest)throws BusinessException;

    /**
     * 
     * addBatchOrderStockPreOccupy:(下订单，新增预占). <br/> 
     * 
     * @author zjh 
     * @param batchOrderDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void addBatchOrderStockPreOccupy(
			ROrdCartsCommRequest cartsCommRequest) throws BusinessException;
/**
 * 
 * returnStockInfo:(退货). <br/> 
 * 
 * @author zjh 
 * @param returnInfoReqDTO
 * @throws BusinessException 
 * @since JDK 1.6
 */
    public void returnStockInfo(StockReturnInfoReqDTO returnInfoReqDTO) throws BusinessException;
    
    /**
     * 
     * statisticStockLack:(汇总缺货商品总数). <br/> 
     * 
     * @author zjh 
     * @param stockInfoReqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */    
    public Long statisticStockLack(StockInfoReqDTO stockInfoReqDTO)
			throws BusinessException;
    
}
