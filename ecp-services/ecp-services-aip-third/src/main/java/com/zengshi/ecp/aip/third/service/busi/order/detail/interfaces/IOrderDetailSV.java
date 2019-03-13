package com.zengshi.ecp.aip.third.service.busi.order.detail.interfaces;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.OrderRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


public interface IOrderDetailSV {

    /**
     * 
     * querySimpleOrderDetail:订单简单详情 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public OrderRespDTO querySimpleOrderDetail(OrderReqDTO orderReqDTO)throws BusinessException;

    /**
     * 
     * queryOrderDetail:订单详情 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public OrderRespDTO queryOrderDetail(OrderReqDTO orderReqDTO)throws BusinessException;
}

