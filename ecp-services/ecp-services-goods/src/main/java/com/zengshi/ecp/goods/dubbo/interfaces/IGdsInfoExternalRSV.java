package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dao.model.GdsType;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.AffirmStockDTO;
import com.zengshi.ecp.goods.dubbo.dto.AffirmStockMainDTO;
import com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokMainReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.DeliverySkuStcokReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoForGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品类型策略抽象统一对外服务<br>
 * Date:2015年10月3日上午2:29:10  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public interface IGdsInfoExternalRSV {
	
	/**
	 * 
	 * getStockAmount:(获取库存信息). <br/>
	 * 
	 * @author linwb3
	 * @return
	 * @since JDK 1.6
	 */
	public StockInfoRespDTO getStockAmount(
			StockInfoForGdsReqDTO stockInfoForGdsReqDTO)
			throws BusinessException;

	/**
	 * 
	 * addStockPreOccupy:(库存预占). <br/>
	 * 
	 * @author linwb3
	 * @return
	 * @since JDK 1.6
	 */
	public void addStockPreOccupy(ROrdCartItemCommRequest ordCartItemCommRequest)
			throws BusinessException;

	/**
	 * 
	 * BatchAddStockPreOccupy:(库存批量预占). <br/>
	 * 
	 * @author linwb3
	 * @return
	 * @since JDK 1.6
	 */
	public void BatchAddStockPreOccupy(ROrdCartsCommRequest cartsCommRequest)
			throws BusinessException;

	/**
	 * 
	 * cancleStockPreOccupy:(库存预占取消). <br/>
	 * 
	 * @author linwb3
	 * @return
	 * @since JDK 1.6
	 */
	public void cancleStockPreOccupy(ROrdCartItemCommRequest cartItemCommRequest)
			throws BusinessException;

	/**
	 * 
	 * batchCancleStockPreOccupy:(库存批量预占取消). <br/>
	 * 
	 * @author linwb3
	 * @return
	 * @since JDK 1.6
	 */
	public void batchCancleStockPreOccupy(ROrdCartsCommRequest cartsCommRequest)
			throws BusinessException;

	/**
	 * 
	 * deliverGds:(商品发货处理接口 明细级别). <br/>
	 * 
	 * @author linwb3
	 * @return
	 * @since JDK 1.6
	 */
	public void deliverGds(DeliverySkuStcokReqDTO deliverySkuStcokReqDTO)
			throws BusinessException;

	/**
	 * 
	 * deliverGds:(商品发货处理接口 订单级发货). <br/>
	 * 
	 * @author linwb3
	 * @return
	 * @since JDK 1.6
	 */
	public void deliverOrderGds(
			DeliverySkuStcokMainReqDTO deliverySkuStcokMainReqDTO)
			throws BusinessException;

	/**
	 * 
	 * confirmReceipt:(确认收货). <br/>
	 * 
	 * @author linwb3
	 * @return
	 * @since JDK 1.6
	 */
	public void confirmReceipt(AffirmStockDTO affirmStockDTO)
			throws BusinessException;

	/**
	 * 
	 * confirmReceipt:(确认收货 批量 订单所有明细). <br/>
	 * 
	 * @author linwb3
	 * @return
	 * @since JDK 1.6
	 */
	public void batchConfirmReceipt(AffirmStockMainDTO affirmStockMainDTO)
			throws BusinessException;

	/**
	 * 
	 * isNeedStockAmount:(是否需要库存). <br/>
	 * 
	 * @author linwb3
	 * @return
	 * @since JDK 1.6
	 */
	public boolean isNeedStockAmount(LongReqDTO id) throws BusinessException;

	/**
	 * 
	 * isGdsTypeFreightFree:(商品类型 是否免邮). <br/>
	 * 
	 * @author linwb3
	 * @return
	 * @since JDK 1.6
	 */
	public boolean isGdsTypeFreightFree(LongReqDTO id) throws BusinessException;
	
	/**
     * 
     * isGdsTypeBuyonce:是否只允许购买一次. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author liyong7
     * @param id
     * @return 如果只允许购买一次返回true,允许购买多次返回false.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean isGdsTypeBuyMore(LongReqDTO id) throws BusinessException;
    
    /**
     * 
     * isVirtualGds:是否虚拟商品. <br/> 
     * 
     * @author liyong7
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean isVirtualProduct(LongReqDTO id)throws BusinessException;

}
