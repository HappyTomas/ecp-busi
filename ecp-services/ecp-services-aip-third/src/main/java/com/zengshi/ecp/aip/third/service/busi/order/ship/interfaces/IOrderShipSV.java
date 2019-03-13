package com.zengshi.ecp.aip.third.service.busi.order.ship.interfaces;

import java.util.Map;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderShipReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


public interface IOrderShipSV {

    /**
     * 订单发货
     *
     * @param req
     * @throws BusinessException
     * @author l2iu
     */
    Map<String, Object> ship(OrderShipReqDTO req) throws BusinessException;
}

