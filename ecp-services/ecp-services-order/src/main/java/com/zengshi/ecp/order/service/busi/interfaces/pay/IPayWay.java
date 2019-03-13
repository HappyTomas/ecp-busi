package com.zengshi.ecp.order.service.busi.interfaces.pay;
import java.util.Map;

import com.zengshi.ecp.order.dubbo.dto.pay.OrderPayStatusVO;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRequestData;
import com.zengshi.ecp.order.dubbo.dto.pay.PaymentRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayRefundRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayRefundResponse;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付通道接口<br>
 * Date:2015年10月20日下午4:18:05  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public interface IPayWay {
    /**
     * 
     * requestPayment:发起支付请求，写请求日志、（请求报文生成）、调用AIP（模拟表单提交）. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param request
     * @param extendProps
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
	PayRequestData requestPayment(PaymentRequest request ,Map<String,String> extendProps) throws Exception;
	
	/**
	 * 
	 * paymentCallback:支付回调 写返回日志、（验签、解析报文）、调用后续处理流程. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author weijq
	 * @param responseResultMap
	 * @return
	 * @throws Exception 
	 * @since JDK 1.6
	 */
   Map<String,String> paymentCallback(Map<String,String> responseResultMap) throws Exception;
   
   /**
    * 
    * queryPaymentResult:查询支付结果（同步或异步）. <br/> 
    * TODO(这里描述这个方法适用条件 – 可选).<br/> 
    * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
    * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
    * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
    * 
    * @author weijq
    * @param orderId
    * @return
    * @throws Exception 
    * @since JDK 1.6
    */
   Map<String,String> queryPaymentResult(String orderId) throws Exception;
   
   /**
    * 
    * refund:发起订单退款请求. <br/> 
    * TODO(这里描述这个方法适用条件 – 可选).<br/> 
    * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
    * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
    * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
    * 
    * @author weijq
    * @param backId
    * @param orderId
    * @param refundAmount
    * @param extendProps
    * @return
    * @throws Exception 
    * @since JDK 1.6
    */
   RPayRefundResponse refund(RPayRefundRequest request,Map<String,String> extendProps) throws Exception;
   
   /**
    * 
    * refundCallback:订单退款结果回调. <br/> 
    * TODO(这里描述这个方法适用条件 – 可选).<br/> 
    * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
    * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
    * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
    * 
    * @author weijq
    * @param resultData
    * @return
    * @throws Exception 
    * @since JDK 1.6
    */
   Map<String,String> refundCallback(Map<String,String> resultData) throws Exception;

   /**
    * 
    * parsePayStatus:解析订单支付状态报文. <br/> 
    * TODO(这里描述这个方法适用条件 – 可选).<br/> 
    * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
    * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
    * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
    * 
    * @author weijq
    * @param params
    * @return
    * @throws Exception 
    * @since JDK 1.6
    */
   OrderPayStatusVO parsePayStatus(Map<String, String> params)throws Exception;
   
   /**
    * 
    * check:对账接口. <br/> 
    * TODO(这里描述这个方法适用条件 – 可选).<br/> 
    * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
    * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
    * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
    * 
    * @author weijq
    * @param resultData
    * @return
    * @throws Exception 
    * @since JDK 1.6
    */
   Map<String,String> check(Map<String,String> resultData) throws Exception;
}