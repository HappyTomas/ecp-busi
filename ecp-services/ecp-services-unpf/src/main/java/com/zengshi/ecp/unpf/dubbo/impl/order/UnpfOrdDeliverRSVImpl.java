package com.zengshi.ecp.unpf.dubbo.impl.order;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfSendMainReq;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IUnpfOrdDeliverRSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfDeliverSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdMainSV;

import javax.annotation.Resource;

/**
 * @author: cbl
 * @date: 2016/11/8.
 */
public class UnpfOrdDeliverRSVImpl implements IUnpfOrdDeliverRSV {

    @Resource
    private IUnpfOrdMainSV unpfOrdMainSV;

    @Resource
    private IUnpfDeliverSV unpfDeliverSV;

    @Override
    public PageResponseDTO<RUnpfOrdMainResp> queryUnpfOrdDeliverByPage(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException {
        return this.unpfOrdMainSV.queryUnpfOrdMainByPage(rUnpfOrdMainReq,true);
    }

    @Override
    public void orderDeliver(RUnpfSendMainReq rUnpfSendMainReq) throws BusinessException {
        this.unpfDeliverSV.saveOrderDeliver(rUnpfSendMainReq);
    }
}
