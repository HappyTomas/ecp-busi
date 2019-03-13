package com.zengshi.ecp.goods.service.busi.interfaces;

import java.util.Map;

import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dubbo.dto.GdsShipmentCalInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShop2ShipmentReqDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品运费模板接口<br>
 * Date:2015年8月30日下午3:26:02 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author gongxq
 * @version
 * @since JDK 1.6
 */
public interface IGdsShipTempSV extends IGeneralSQLSV {
	/**
	 * 
	 * saveGdsShipTemp:(保存运费模板). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author gxq
	 * @param reqDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	long saveGdsShipTemp(GdsShiptempReqDTO reqDTO) throws BusinessException;

	/**
	 * 
	 * delteGdsShipTemp:(删除运费模板（逻辑删除）). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author gxq
	 * @param reqDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	long delteGdsShipTemp(GdsShiptempReqDTO reqDTO) throws BusinessException;

	/**
	 * 
	 * queryGdsShipTemp:(查询运费模板列表). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author gxq
	 * @param gdsShipTempRspDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	PageResponseDTO<GdsShiptempRespDTO> queryGdsShipTemp(
			GdsShiptempReqDTO reqDTO) throws BusinessException;

	/**
	 * getSingleGdsShipTemp:(获取编辑的单条模板记录的信息). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * 
	 * @author gxq
	 * @param reqDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	GdsShiptempRespDTO getSingleGdsShipTemp(GdsShiptempReqDTO reqDTO)
			throws BusinessException;

	/**
	 * 
	 * editGdsShipTemp:(编辑保存运费模板). <br/>
	 * 
	 * @author gxq
	 * @param reqDTO
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	void editGdsShipTemp(GdsShiptempReqDTO reqDTO) throws BusinessException;

	/**
	 * 计算每个店铺的运费
	 * 
	 * @param calInfoReqDTO
	 * @return
	 * @throws BusinessException
	 */
	public Long calcShipExpense(GdsShipmentCalInfoReqDTO calInfoReqDTO)
			throws BusinessException;

	/**
	 * 计算所有店铺的运费
	 * 
	 * @param cartsCommRequest
	 * @return
	 * @throws BusinessException
	 */
	public Map<Long, Long> calcShipExpenseByCarts(
			ROrdCartsCommRequest cartsCommRequest) throws BusinessException;

	/**
	 * 获取店铺默认运费模板
	 * 
	 * @param gdsShop2ShipmentReqDTO
	 * @return
	 * @throws Exception
	 */
	public GdsShiptempRespDTO queryShopDefaultShipMent(
			GdsShop2ShipmentReqDTO gdsShop2ShipmentReqDTO) throws Exception;

	/**
	 * 编辑店铺默认模板
	 * 
	 * @param gdsShop2ShipmentReqDTO
	 * @throws Exception
	 */
	public void editGdsShop2Shiptemp(
			GdsShop2ShipmentReqDTO gdsShop2ShipmentReqDTO) throws Exception;

	/**
	 * 新增店铺默认模板关系
	 * 
	 * @param gdsShop2ShipmentReqDTO
	 * @throws Exception
	 */
	public void addGdsShop2Shiptemp(
			GdsShop2ShipmentReqDTO gdsShop2ShipmentReqDTO) throws Exception;
	/**
     * 新增店铺默认模板
     * 
     * @param reqDTO
     * @throws Exception
     */
    public void addShopDefaultShipMent(GdsShiptempReqDTO reqDTO) throws Exception;

    /**
     * 
     * getGdsShipTempName:(查询运费模板名称). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public String getGdsShipTempName(GdsShiptempReqDTO reqDTO) throws BusinessException;

}
