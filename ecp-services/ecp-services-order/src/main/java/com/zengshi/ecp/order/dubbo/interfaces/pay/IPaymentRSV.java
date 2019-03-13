package com.zengshi.ecp.order.dubbo.interfaces.pay;
import java.util.Map;

import com.zengshi.ecp.order.dubbo.dto.pay.BindRequestData;
import com.zengshi.ecp.order.dubbo.dto.pay.OrderPayStatusVO;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRequestData;
import com.zengshi.ecp.order.dubbo.dto.pay.PaymentRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayRefundRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayRefundResponse;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.exception.GenericException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: <br>
 * Date:2015年10月20日下午3:57:23  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public interface IPaymentRSV {
    /**
     * 
     * requestPayment:发起支付请求. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param request
     * @param extendProps
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
   PayRequestData requestPayment(PaymentRequest request,Map<String,String> extendProps) throws BusinessException;
   /**
    * 
    * paymentCallback:支付回调（AIP调用）,具体参数待AIP确定. <br/> 
    * TODO(这里描述这个方法适用条件 – 可选).<br/> 
    * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
    * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
    * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
    * 
    * @author weijq
    * @param payWay
    * @param responseResultMap
    * @return
    * @throws BusinessException 
    * @since JDK 1.6
    */
   Map<String,String> paymentCallback(String payWay, Map<String,String> responseResultMap) throws BusinessException;
   /** 查询支付结果
    * 
    * @param payWay 
    * @param orderId */
   Map<String,String> queryPaymentResult(String payWay, String orderId) throws BusinessException;
   /** 发起订单退款请求
    * 
    * @param payWay 
    * @param orderId */
   RPayRefundResponse refund(RPayRefundRequest request,Map<String,String> extendProps) throws BusinessException;
   /** 订单退款请求结果回调
    * 
    * @param payWay 
    * @param resultData */
   Map<String,String> refundCallback(String payWay, Map<String,String> resultData) throws BusinessException;
   /** 发起绑卡请求
    * 
    * @param payWay 
    * @param chnlId 
    * @param extendProps */
   BindRequestData bindBankCard(String payWay, String chnlId,Map<String,String> extendProps) throws BusinessException;
   /** 绑卡回调
    * 
    * @param payWay 
    * @param responseMessge */
   Map<String,String> bindBankCardCallback(String payWay, Map<String,String> responseMap) throws BusinessException;
   /** 绑卡转移
    * 
    * @param payWay 
    * @param oldChnlId 老渠道编码
    * @param newChnlId 新渠道编码 */
   void shiftBindBankCard(String payWay, String oldChnlId, String newChnlId)throws BusinessException;
   
   /** 解析订单支付状态报文
    * 
    * @param payWay 支付通道编码
    * @param params 通道所有返回参数
    * @return
    * @throws BusinessException
    * @throws GenericException
    */
   OrderPayStatusVO parsePayStatus(String payWay,Map<String,String> params)throws BusinessException;
   
   /** 获取绑卡信息
    * 
    * @param StaffInfoVO 
    * @param provinceCode */
//   List<PayBindInfo> getBindCardInfo(String chnlId,String provinceCode) throws BusinessException,GenericException;
   
   /** 发起订单全额退款请求
    * 
    * @param payWay 
    * @param orderId */
   Map<String,String> delivery(String payWay, String orderId,String provinceCode,Map<String,String> extendProps) throws BusinessException;
   
   /** 发起订单全额退款请求
    * 
    * @param payWay 
    * @param orderId */
   Map<String,String> shipments(String payWay, String orderId,Map<String,String> extendProps) throws BusinessException;
   
   /** 发起订单全额退款请求
    * 
    * @param payWay 
    * @param orderId */
   Map<String,String> check(String payWay, Map<String,String> extendProps) throws BusinessException;

}