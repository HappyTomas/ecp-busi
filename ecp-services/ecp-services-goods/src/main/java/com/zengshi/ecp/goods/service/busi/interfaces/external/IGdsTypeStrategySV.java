package com.zengshi.ecp.goods.service.busi.interfaces.external;

import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.goods.dubbo.dto.AffirmStockDTO;
import com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoForGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品具体类型统一操作接口<br>
 * Date:2015年9月17日上午10:51:54  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public interface IGdsTypeStrategySV {
	
	
	/**
	 * 
	 * getStockAmount:(获取库存信息). <br/> 
	 * 
	 * @author linwb3 
	 * @return 
	 * @since JDK 1.6
	 */
	public StockInfoRespDTO getStockAmount(StockInfoForGdsReqDTO stockInfoForGdsReqDTO) throws BusinessException ;
	
	
	/**
	 * 
	 * addStockPreOccupy:(库存预占). <br/> 
	 * 
	 * @author linwb3
	 * @return 
	 * @since JDK 1.6
	 */
	public void addStockPreOccupy(ROrdCartItemCommRequest ordCartItemCommRequest) throws BusinessException ;
	
	/**
	 * 
	 * cancleStockPreOccupy:(库存预占取消). <br/> 
	 * 
	 * @author linwb3
	 * @return 
	 * @since JDK 1.6
	 */
	public void cancleStockPreOccupy(ROrdCartItemCommRequest cartItemCommRequest) throws BusinessException ;
	
	
	/**
	 * 
	 * deliverGds:(商品发货处理接口). <br/> 
	 * 
	 * @author linwb3
	 * @return 
	 * @since JDK 1.6
	 */
	public void deliverGds(DeliverySkuStcokReqDTO deliverySkuStcokReqDTO) throws BusinessException ;
	
	/**
	 * 
	 * confirmReceipt:(确认收货). <br/> 
	 * 
	 * @author linwb3
	 * @return 
	 * @since JDK 1.6
	 */
	public void confirmReceipt(AffirmStockDTO affirmStockDTO) throws BusinessException ;
	
	
}

