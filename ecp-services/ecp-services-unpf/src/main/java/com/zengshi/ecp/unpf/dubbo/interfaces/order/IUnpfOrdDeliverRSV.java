package com.zengshi.ecp.unpf.dubbo.interfaces.order;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfSendMainReq;

/**
 * @author: cbl
 * @date: 2016/11/8.
 */
public interface IUnpfOrdDeliverRSV {
    /**
     * 分页查询第三方订单信息
     * @param rUnpfOrdMainReq
     * @return
     * @throws BusinessException
     */
    public PageResponseDTO<RUnpfOrdMainResp> queryUnpfOrdDeliverByPage(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException;

    /**
     * 确认发货
     * @param rUnpfSendMainReq
     * @throws BusinessException
     */
    public void orderDeliver(RUnpfSendMainReq rUnpfSendMainReq) throws BusinessException;
}
