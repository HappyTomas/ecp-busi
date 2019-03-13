package com.zengshi.ecp.unpf.dubbo.impl.order;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainResp;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IUnpfOrdMainRSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdMainSV;

import javax.annotation.Resource;

/**
 * @author: cbl
 * @date: 2016/11/8.
 */
public class UnpfOrdMainRSVImpl implements IUnpfOrdMainRSV {

    @Resource
    private IUnpfOrdMainSV unpfOrdMainSV;

    @Override
    public RUnpfOrdMainResp queryUnpfOrdMain(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException {
        return this.unpfOrdMainSV.queryUnpfOrdMain(rUnpfOrdMainReq);
    }

    @Override
    public PageResponseDTO<RUnpfOrdMainResp> queryUnpfOrdMainByPage(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException {
        return this.unpfOrdMainSV.queryUnpfOrdMainByPage(rUnpfOrdMainReq,false);
    }
}
