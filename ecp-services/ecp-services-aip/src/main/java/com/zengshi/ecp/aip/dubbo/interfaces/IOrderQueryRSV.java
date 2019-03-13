package com.zengshi.ecp.aip.dubbo.interfaces;

import java.util.Map;

/**
 * @Title: OrderQueryService.java
 * @Package: com.zengshi.aip.provider.mapp
 * @Description: 单笔订单实时查询服务接口
 * @Comment:
 * @author: luocan
 * @CreateDate: 2014年8月28日 上午11:05:27
 */
public interface IOrderQueryRSV {
	/**
	 * 
	 * @Title: queryPaymentResult
	 * @Description:根据URL和参数发送HTTP post请求并获取返回结果,url中参数如果有？和&符号需要自己转义
	 * @author: luocan
	 * @Create: 2014年8月28日 上午11:11:27
	 * @Modify: 2014年8月28日 上午11:11:27
	 * @param:payWay:支付通道编码， charset：字符集，payQueryUrl：请求URL，params：请求参数
	 * @return:
	 * @throws Exception 
	 */
	public Map<String, String> queryPaymentResult(String payWay,
			String charset, String payQueryUrl, Map<String, String> params) throws Exception;
	
	/**
     * 扩展订单查询接口
     * @Title: queryPaymentResult
     * @Description:根据URL和参数发送HTTP post请求并获取返回结果，url中参数如果有？和&符号需要自己转义
     * @author: liangdl5
     * @Create: 2015年7月2日 上午11:11:27
     * @Modify: 2015年7月2日 上午11:11:27
     * @param:payWay:支付通道编码， charset：字符集，payQueryUrl：请求URL，params：请求参数
     * @param:features:其他特性参数
     * @return:
     * @throws Exception 
     */
    public Map<String, String> queryPaymentResult(String payWay,
            String charset, String payQueryUrl, Map<String, String> params, Map<String, String> features) throws Exception;
    
    /**
     * 
     * getqueryPaymentResult:get方式请求. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payWay
     * @param charset
     * @param payQueryUrl
     * @param params
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    public Map<String, String> getqueryPaymentResult(String payWay,
            String charset, String payQueryUrl, Map<String, String> params) throws Exception;

	/**
	 * 鸿支付请求
	 * @param idCode
	 * @param url
	 * @param charset
	 * @param dataMap
	 * @return
     * @throws Exception
     */
	public Map<String, String> getHongResultData(String idCode, String url, String charset, Map<String, String> dataMap) throws Exception;

	/**
	 * 微信支付预请求
	 * @param idCode
	 * @param url
	 * @param charset
	 * @param dataMap
	 * @return
     * @throws Exception
     */
	public Map<String, String> getWeChatResultData(String payWay, String payQueryUrl, String charset, Map<String, String> params) throws Exception;

	
	/**
	 * 微信支付预请求
	 * @param idCode
	 * @param url
	 * @param charset
	 * @param dataMap
	 * @return
     * @throws Exception
     */
	public String postData2WeChat(String postUrl, Map<String, String> weChatReMap) throws Exception;	
	
	/**
     * 微信退款请求
     * @param idCode
     * @param url
     * @param charset
     * @param dataMap
     * @return
     * @throws Exception
     */
	public String postDataWeChatRefund(String postUrl, Map<String, String> map,String fileId) throws Exception;
}
