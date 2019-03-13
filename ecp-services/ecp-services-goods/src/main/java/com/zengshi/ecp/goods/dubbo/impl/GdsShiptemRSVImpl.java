package com.zengshi.ecp.goods.dubbo.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsShipmentCalInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShop2ShipmentReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsShiptemRSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsShipTempSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * Title: 运费模板服务 <br>
 * Project Name:com.zengshi.ecp.ecp-services-goods-server-1.1.6-RELEASE <br>
 * Description: <br>
 * Date:2016年3月29日上午10:20:01  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public class GdsShiptemRSVImpl extends AbstractRSVImpl implements IGdsShiptemRSV {

    @Autowired(required = false)
    private IGdsShipTempSV gdsShiptempSV;

    /**
     * 
     * TODO 新增保存运费模板（可选）.
     * 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsShiptemRSV#saveGdsShipTemp(com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO)
     */
    @Override
    public void saveGdsShipTemp(GdsShiptempReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, true, "reqDTO");
        paramNullCheck(reqDTO.getShipTemplateType(), "reqDTO.shipTemplateType");
        paramNullCheck(reqDTO.getShopId(), "reqDTO.shopId");
        gdsShiptempSV.saveGdsShipTemp(reqDTO);
    }

    /**
     * 
     * TODO 删除运费模板（逻辑删除）（可选）.
     * 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsShiptemRSV#delteGdsShipTemp(com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO)
     */
    @Override
    public long delteGdsShipTemp(GdsShiptempReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, true, "reqDTO");
        paramNullCheck(reqDTO.getId(), "reqDTO.id");
        return gdsShiptempSV.delteGdsShipTemp(reqDTO);
    }

    /**
     * 
     * TODO 查询运费模板列表（可选）.
     * 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsShiptemRSV#queryGdsShipTemp(com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO)
     */
    @Override
    public PageResponseDTO<GdsShiptempRespDTO> queryGdsShipTemp(GdsShiptempReqDTO reqDTO) throws BusinessException {
        return gdsShiptempSV.queryGdsShipTemp(reqDTO);
    }

    /**
     * 
     * TODO 获取单条运费模板记录信息（用于编辑、查看）（可选）.
     * 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsShiptemRSV#getSingleGdsShipTemp(com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO)
     */
    @Override
    public GdsShiptempRespDTO getSingleGdsShipTemp(GdsShiptempReqDTO reqDTO) throws BusinessException {
        return gdsShiptempSV.getSingleGdsShipTemp(reqDTO);
    }

    /**
     * 
     * TODO 简单描述该方法的实现功能（编辑保存运费模板）.
     * 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsShiptemRSV#editGdsShipTemp(com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO)
     */
    @Override
    public void editGdsShipTemp(GdsShiptempReqDTO reqDTO) throws BusinessException {
        try {
            gdsShiptempSV.editGdsShipTemp(reqDTO);
        } catch (Exception exception) {
            LogUtil.error(MODULE, "", exception);
            throw new BusinessException(GdsErrorConstants.GdsShipMent.ERROR_GOODS_SHIP_MENT_240000);
        }
    }

    @Override
    public Long calcShipExpense(GdsShipmentCalInfoReqDTO calInfoReqDTO) throws BusinessException {
        paramNullCheck(calInfoReqDTO, "cartsCommRequest");
        paramNullCheck(calInfoReqDTO.getGdsInfos(), "gdsInfos");
        paramNullCheck(calInfoReqDTO.getShopId(), "shopId");
        try {
            return gdsShiptempSV.calcShipExpense(calInfoReqDTO);
        } catch (BusinessException businessException) {
            LogUtil.error(MODULE, "", businessException);
            throw businessException;
        } catch (Exception exception) {
            LogUtil.error(MODULE, "", exception);
            throw new BusinessException(GdsErrorConstants.GdsShipMent.ERROR_GOODS_SHIP_MENT_240000);
        }
    }

    @Override
    public Map<Long, Long> calcShipExpenseByCarts(ROrdCartsCommRequest cartsCommRequest) throws BusinessException {
        paramNullCheck(cartsCommRequest, "cartsCommRequest");
        paramNullCheck(cartsCommRequest.getOrdCartsCommList(), "OrdCartsCommList");
        try {
            return gdsShiptempSV.calcShipExpenseByCarts(cartsCommRequest);
        } catch (BusinessException businessException) {
            LogUtil.error(MODULE, "", businessException);
            throw businessException;
        } catch (Exception exception) {
            LogUtil.error(MODULE, "", exception);
            throw new BusinessException(GdsErrorConstants.GdsShipMent.ERROR_GOODS_SHIP_MENT_240000);
        }
    }

    /**
     * 
     * TODO 查询店铺默认模板（可选）.
     * 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsShiptemRSV#queryShopDefaultShipMent(com.zengshi.ecp.goods.dubbo.dto.GdsShop2ShipmentReqDTO)
     */
    @Override
    public GdsShiptempRespDTO queryShopDefaultShipMent(GdsShop2ShipmentReqDTO gdsShop2ShipmentReqDTO) throws BusinessException {
        try {
            return gdsShiptempSV.queryShopDefaultShipMent(gdsShop2ShipmentReqDTO);

        } catch (BusinessException businessException) {
            throw businessException;

        } catch (Exception exception) {
            throw new BusinessException(GdsErrorConstants.GdsShipMent.ERROR_GOODS_SHIP_MENT_240000);
        }
    }

    /**
     * 
     * TODO 编辑店铺默认模板（可选）.
     * 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsShiptemRSV#editGdsShop2Shiptemp(com.zengshi.ecp.goods.dubbo.dto.GdsShop2ShipmentReqDTO)
     */
    @Override
    public void editGdsShop2Shiptemp(GdsShop2ShipmentReqDTO gdsShop2ShipmentReqDTO) throws BusinessException {
        try {
            gdsShiptempSV.editGdsShop2Shiptemp(gdsShop2ShipmentReqDTO);

        } catch (BusinessException businessException) {
            throw businessException;

        } catch (Exception exception) {
            throw new BusinessException(GdsErrorConstants.GdsShipMent.ERROR_GOODS_SHIP_MENT_240000);
        }
    }

    /**
     * 
     * TODO 新增店铺模板（可选）.
     * 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsShiptemRSV#addShopDefaultShipMent(com.zengshi.ecp.goods.dubbo.dto.GdsShop2ShipmentReqDTO)
     */
    @Override
    public void addShopDefaultShipMent(GdsShiptempReqDTO reqDTO) throws BusinessException {

        try {
            paramNullCheck(reqDTO, true, "reqDTO");
            paramNullCheck(reqDTO.getShipTemplateType(), "reqDTO.shipTemplateType");
            paramNullCheck(reqDTO.getShopId(), "reqDTO.shopId");
            gdsShiptempSV.addShopDefaultShipMent(reqDTO);

        } catch (BusinessException businessException) {
            throw businessException;

        } catch (Exception exception) {
            throw new BusinessException(GdsErrorConstants.GdsShipMent.ERROR_GOODS_SHIP_MENT_240000);
        }
    }

}
