package com.zengshi.ecp.unpf.service.busi.order.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdDeliveryBatch;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdDeliverDetailsResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdDeliveryBatchReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfSendMainReq;

import java.util.List;

/**
 * @author: cbl
 * @date: 2016/11/8.
 */
public interface IUnpfOrdDeliverDetailsSV {
    /**
     * 保存发货明细
     * @throws BusinessException
     */
    public void saveUnpfOrdDeliverDetails(RUnpfSendMainReq rUnpfSendMainReq, UnpfOrdDeliveryBatch unpfOrdDeliveryBatch) throws BusinessException;

    /**
     * 查询发货明细
     * @throws BusinessException
     */
    public List<RUnpfOrdDeliverDetailsResp> queryUnpfOrdDeliverDetails(RUnpfOrdDeliveryBatchReq rUnpfOrdDeliveryBatchReq) throws BusinessException;
}
