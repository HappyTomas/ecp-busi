package com.zengshi.ecp.order.service.busi.interfaces.pay;
/***********************************************************************
 * Module:  IPayPlatform.java
 * Author:  cliff
 * Purpose: Defines the Interface IPayPlatform
 ***********************************************************************/

import java.util.Map;

import com.zengshi.ecp.order.dubbo.dto.pay.BindRequestData;

/** 支付平台接口 */
public interface IPayPlatform extends IPayWay {
   /** 绑定或解绑定银行卡请求 写请求日志、请求报文生成、调用AIP（模拟表单提交）
    * 
    * @param chnlId */
	BindRequestData bindBankCard(Long staffId,Map<String,String> extendProps) throws Exception;
   /** 绑卡回调 -写返回日志、验签、解析报文、调用后续处理流程
    * 
    * @param responseMessage */
	Map<String,String>  bindBankCardCallback(Map<String,String> responseMap) throws Exception;
   /** 绑卡转移
    * 
    * @param oldChnlId 
    * @param newChnlId */
	void shiftBindBankCard(Long OldStaffId, Long newStaffId) throws Exception;

   /** 确认收货
    * 
    * @param orderId 
    * @param provinceCode */
	Map<String,String> delivery(String orderId,Map<String,String> extendProps) throws Exception;
   
   /** 确认发货
    * 
    * @param orderId 
    * @param provinceCode */
	Map<String,String> shipments(String orderId,Map<String,String> extendProps) throws Exception;
}