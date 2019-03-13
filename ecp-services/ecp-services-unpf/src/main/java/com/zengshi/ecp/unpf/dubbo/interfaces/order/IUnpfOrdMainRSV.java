package com.zengshi.ecp.unpf.dubbo.interfaces.order;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainResp;

/**
 * @author: cbl
 * @date: 2016/11/8.
 */
public interface IUnpfOrdMainRSV {
    /**
     * 根据内部订单号或外部订单号和平台类型查询订单信息
     * @param rUnpfOrdMainReq
     * @return
     * @throws BusinessException
     */
    public RUnpfOrdMainResp queryUnpfOrdMain(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException;

    /**
     * 分页查询第三方订单信息
     * @param rUnpfOrdMainReq
     * @return
     * @throws BusinessException
     */
    public PageResponseDTO<RUnpfOrdMainResp> queryUnpfOrdMainByPage(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException;
}
