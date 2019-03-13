package com.zengshi.ecp.unpf.service.busi.order.interfaces;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderShipReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdDeliveryBatchReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdDeliveryBatchResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfSendMainReq;

import java.util.List;

/**
 * @author: cbl
 * @date: 2016/11/8.
 */
public interface IUnpfOrdDeliverBatchSV {

    /**
     *  根据内部订单号查询发货批次信息或外部订单号和平台类型查询发货批次信息
     * @param rUnpfOrdMainReq
     * @return
     * @throws BusinessException
     */
    public List<RUnpfOrdDeliveryBatchResp> queryUnpfOrdDeliveryBatch(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException;


    /**
     * 保存发货批次信息
     * @param rUnpfSendMainReq
     * @throws BusinessException
     */
    public Long saveUnpfOrdDeliveryBatch(RUnpfSendMainReq rUnpfSendMainReq) throws BusinessException;

    /**
     * 更新发货批次信息
     * @param rUnpfOrdDeliveryBatchReq
     * @throws BusinessException
     */
    public void updateUnpfOrdDeliveryBatch(RUnpfOrdDeliveryBatchReq rUnpfOrdDeliveryBatchReq) throws BusinessException;

    
    /**
     * 第三方发货
     * @param orderShipReq
     * @return
     * @throws BusinessException
     */
    public Boolean deliverGoods(OrderShipReqDTO orderShipReq) throws BusinessException;
}
