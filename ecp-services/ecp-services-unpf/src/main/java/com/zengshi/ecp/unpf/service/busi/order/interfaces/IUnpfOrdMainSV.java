package com.zengshi.ecp.unpf.service.busi.order.interfaces;

import java.util.List;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.OrderRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdMain;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdSubReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfQueryOrderReq;

/**
 * @author: cbl
 * @date: 2016/11/8.
 */
public interface IUnpfOrdMainSV extends IGeneralSQLSV {
    /**
     * 根据内部订单号或外部订单号和平台类型查询订单信息
     * @param rUnpfOrdMainReq
     * @return
     * @throws BusinessException
     */
    public RUnpfOrdMainResp queryUnpfOrdMain(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException;
    /**
     * 根据内部订单号或外部订单号和平台类型查询主订单信息
     * @param rUnpfOrdMainReq
     * @return
     * @throws BusinessException
     */
    public UnpfOrdMain queryUnpfOrdMainIn(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException;

    /**
     * 保存第三方订单
     * @param
     */
    public void saveUnpfOrdMain(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException;
    
    public void saveUnpfOrdMain(OrderReqDTO req) throws BusinessException;

    public void saveSimpleUnpfOrdMain(OrderReqDTO req) throws BusinessException;
    /**
     * 修改第三方订单
     */
    public void updateUnpfOrdMain(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException;

    /**
     * 分页查询订单信息
     * @param rUnpfOrdMainReq
     * @return
     * @throws BusinessException
     */
    public PageResponseDTO<RUnpfOrdMainResp> queryUnpfOrdMainByPage(RUnpfOrdMainReq rUnpfOrdMainReq,boolean isDeliver) throws BusinessException;

    /**
     * 订单转换
     * @param orderDetial
     * @param unpfOrdMainReq
     * @return
     */
	public List<RUnpfOrdSubReq> copyDetialToOrderMain(OrderRespDTO orderDetial,RUnpfOrdMainReq unpfOrdMainReq);
	
	/**
     * queryOrderId:导出文件查询订单. <br/>
     * @author jiangmr
     * @param rUnpfQueryOrderReq
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public List<UnpfOrdMain> queryUnpfOrdMainByThread(RUnpfQueryOrderReq rUnpfQueryOrderReq) throws BusinessException;
}
