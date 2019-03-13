package com.zengshi.ecp.goods.dubbo.interfaces;

import java.util.Map;

import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dubbo.dto.GdsShipmentCalInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShop2ShipmentReqDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsShiptemRSV {
    /**
     * 
     * saveGdsShipTemp:(新增保存运费模板). <br/>
     * 
     * @author gxq
     * @param reqDTO
     * @throws BusinessException
     * @since JDK 1.6
     */
    void saveGdsShipTemp(GdsShiptempReqDTO reqDTO) throws BusinessException;

    /**
     * 
     * delteGdsShipTemp:(删除运费模板（逻辑删除）). <br/>
     * 
     * @author gxq
     * @param reqDTO
     * @throws BusinessException
     * @since JDK 1.6
     */
    long delteGdsShipTemp(GdsShiptempReqDTO reqDTO) throws BusinessException;

    /**
     * 
     * queryGdsShipTemp:(获取运费模板列表). <br/>
     * 
     * @author gxq
     * @param reqDTO
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    PageResponseDTO<GdsShiptempRespDTO> queryGdsShipTemp(GdsShiptempReqDTO reqDTO)
            throws BusinessException;

    /**
     * 
     * getSingleGdsShipTemp:(获取单条运费模板记录信息（用于编辑、查看）). <br/>
     * 
     * @author gxq
     * @param reqDTO
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    GdsShiptempRespDTO getSingleGdsShipTemp(GdsShiptempReqDTO reqDTO) throws BusinessException;

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
     * 
     * calcShipExpense:(计算商品运费). <br/>
     * 
     * @author zjh
     * @param calInfoReqDTO
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public Long calcShipExpense(GdsShipmentCalInfoReqDTO calInfoReqDTO) throws BusinessException;

    /**
     * 计算一批订单运费
     * 
     * @param cartsCommRequest
     * @return
     * @throws Exception
     */
    public Map<Long, Long> calcShipExpenseByCarts(ROrdCartsCommRequest cartsCommRequest)
            throws BusinessException;

    /**
     * 
     * queryShopDefaultShipMent:(获取店铺默认运费模板). <br/>
     * 
     * @author zjh
     * @param gdsShop2ShipmentReqDTO
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public GdsShiptempRespDTO queryShopDefaultShipMent(GdsShop2ShipmentReqDTO gdsShop2ShipmentReqDTO)
            throws BusinessException;

    /**
     * 
     * editGdsShop2Shiptemp:(编辑店铺默认模板). <br/>
     * 
     * @author zjh
     * @param gdsShop2ShipmentReqDTO
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void editGdsShop2Shiptemp(GdsShop2ShipmentReqDTO gdsShop2ShipmentReqDTO)
            throws BusinessException;

    /**
     * 
     * addShopDefaultShipMent:(新增店铺默认模板). <br/>
     * 
     * @author zjh
     * @param reqDTO
     * @throws BusinessException
     * @since JDK 1.6
     */
    public void addShopDefaultShipMent(GdsShiptempReqDTO reqDTO) throws BusinessException;

}
