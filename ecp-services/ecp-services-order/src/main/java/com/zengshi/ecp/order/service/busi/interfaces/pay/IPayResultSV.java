package com.zengshi.ecp.order.service.busi.interfaces.pay;

import java.util.List;

import com.zengshi.ecp.order.dao.model.PayResult;
import com.zengshi.ecp.order.dubbo.dto.pay.PayResultDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: <br>
 * Date:2015年10月8日下午2:58:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public interface IPayResultSV {
    /**
     * 
     * addPayResult:插入支付结果表. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payResult 
     * @since JDK 1.6
     */
    public void addPayResult(PayResultDTO payResult);
    
    /**
     * 
     * getPayResultByOrderId:根据订单号查询支付结果信息. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param OrderId
     * @param payWay
     * @param transNo
     * @return 
     * @since JDK 1.6
     */
    public List<PayResult> getPayResultByOrderId(String orderId);
}