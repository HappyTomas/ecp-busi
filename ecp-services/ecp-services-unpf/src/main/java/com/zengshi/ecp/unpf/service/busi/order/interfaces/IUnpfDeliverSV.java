package com.zengshi.ecp.unpf.service.busi.order.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfSendMainReq;

/**
 * @author: cbl
 * @date: 2016/11/16.
 */
public interface IUnpfDeliverSV {

    /**
     * 确认发货主事物
     * @param rUnpfSendMainReq
     * @throws BusinessException
     */
    public void saveOrderDeliver(RUnpfSendMainReq rUnpfSendMainReq) throws BusinessException;
}
