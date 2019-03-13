package com.zengshi.ecp.unpf.service.busi.order.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdSub;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdSubReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdSubResp;

import java.util.List;

/**
 * @author: cbl
 * @date: 2016/11/8.
 */
public interface IUnpfOrdSubSV {

    /**
     *  根据内部订单号查询子订单或外部订单号和平台类型查询子订单
     * @param rUnpfOrdMainReq
     * @return
     * @throws BusinessException
     */
    public List<RUnpfOrdSubResp> queryUnpfOrdSub(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException;

    /**
     * 保存子订单
     * @param rUnpfOrdSubReq
     * @throws BusinessException
     */
    public void saveUnpfOrdSub(RUnpfOrdSubReq rUnpfOrdSubReq) throws BusinessException;

    /**
     * 更新子订单信息
     * @param rUnpfOrdSubReq
     * @throws BusinessException
     */
    public void updateUnpfOrdSub(RUnpfOrdSubReq rUnpfOrdSubReq) throws BusinessException;

    /**
     * 根据内部子订单号查询子订单或外部子订单号和平台类型查询子订单
     * @return
     * @throws BusinessException
     */
    public RUnpfOrdSubResp queryUnpfOrdSubIn(RUnpfOrdSubReq rUnpfOrdSubReq) throws BusinessException;
}
