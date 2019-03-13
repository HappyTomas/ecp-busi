package com.zengshi.ecp.order.service.busi.interfaces.pay;

import java.util.List;

import com.zengshi.ecp.order.dao.model.PayRefundResult;

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
public interface IPayRefundResultSV {
    /**
     * 
     * addPayRefundResult:添加退款信息. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payResult 
     * @since JDK 1.6
     */
    public void addPayRefundResult(PayRefundResult payResult);
    
    /**
     * 
     * getPayRefundResultByOrderId:根据订单id获取退款信息. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param orderId
     * @return 
     * @since JDK 1.6
     */
    public List<PayRefundResult> getPayRefundResultByOrderId(String orderId);
    
    /**
     * 
     * getPayRefundResultByOrderTransNo:根据支付通道，退款编号获取退款信息. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param orderId
     * @return 
     * @since JDK 1.6
     */
    public PayRefundResult getPayRefundResultByPaywayBackId(String payway,Long backId);
    
    /**
     * 
     * updateRefundStatus:更新退款状态. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payRefundResult
     * @param OldStatus
     * @param newStatus
     * @return 
     * @since JDK 1.6
     */
    public int updateRefund(PayRefundResult payRefundResult);
}