package com.zengshi.ecp.order.service.busi.impl.pay;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Project Name:ecp-services-order-server
 * File Name:WeChatScanCodePay.java
 * Package Name:com.zengshi.ecp.order.service.impl.pay
 * Date:2016年4月13日下午7:29:05
 * Copyright (c) 2016, ZengShi All Rights Reserved.
 */

import com.zengshi.ecp.aip.dubbo.interfaces.IOrderQueryRSV;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dao.model.PayRefundResult;
import com.zengshi.ecp.order.dao.model.PayResult;
import com.zengshi.ecp.order.dao.model.PayShopCfg;
import com.zengshi.ecp.order.dao.model.PayWay;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.order.dubbo.dto.pay.OrderPayStatusVO;
import com.zengshi.ecp.order.dubbo.dto.pay.PayIntfNotifyLogDTO;
import com.zengshi.ecp.order.dubbo.dto.pay.PayIntfReqLogDTO;
import com.zengshi.ecp.order.dubbo.dto.pay.PayParamVO;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRefundSuccInfo;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRequestDTO;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRequestData;
import com.zengshi.ecp.order.dubbo.dto.pay.PaySuccInfo;
import com.zengshi.ecp.order.dubbo.dto.pay.PaymentRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayRefundRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayRefundResponse;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.dubbo.util.PayHelper;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayServiceMsgCode;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPayRelSV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.json.JSON;
import com.db.sequence.Sequence;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 微信扫码支付<br>
 * Date:2016年4月13日下午7:29:05 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 *
 * @author chenjf6
 * @version
 * @since JDK 1.7
 */
public class WeiXinPay extends DefaultPayWay {
	public static final String module = WeiXinPay.class.getName();
	public final static String payWay = PayWayEnum.getPayWayByImplClass(WeiXinPay.class);
	private final static String TRAED_TYPE = "NATIVE";
	public final static String SUCC_FINISHED = "SUCCESS";

	@Resource(name = "seq_pay_refund")
	private Sequence seqPayRefund;
	@Resource
	private IOrdPayRelSV iOrdPayRelSV;
	@Resource
	private IOrderQueryRSV orderQueryRSV;

	@Override
	public PayRequestData requestPayment(PaymentRequest request, Map<String, String> extendProps) throws Exception {
		LogUtil.info(module, "----------*wechat scan code requestPayment*---没有乱码-------");
		PayParamVO vo = buildPayParamVO(request.getOrderId(), payWay);
		PayHelper.validatePayParams(vo);
		// 如果本系统生成支付信息,先不把重要信息返回到页面，只把请求的URL,订单编号返回页面
		PayRequestData pr = new PayRequestData();
		WeChatPayRequest weChatRe = new WeChatPayRequest();

		String notify_url = vo.getPayWay().getPayNotifyUrl();// 通知地址，接收微信支付异步通知回调地址
		String payMercCode = vo.getPayShopCfg().getMercCode();// 平台商户号
		String pay_private_key = vo.getPayShopCfg().getCerName();// 支付私钥
		// 微信支付的openid
		String out_trade_no = vo.getOrderId();
		String subject = "人卫智慧商城-图书购买";
/*		String subject = "";
		// 2、订单明细
		ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
		rOrdPayRelReq.setJoinOrderid(request.getOrderId());
		List<ROrdPayRelResp> list = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
		StringBuffer sb = new StringBuffer();
		if (list != null && list.size() >= 1) {
			for (ROrdPayRelResp resp : list) {
				List<OrdSub> OrdSubList = getordSubByOrderId(resp.getOrderId());
				if (OrdSubList != null && OrdSubList.size() > 0) {
					for (OrdSub ordSub : OrdSubList) {
						if (sb.length() >= 64) {
							break;
						} else {
							sb.append(ordSub.getGdsName()).append("*").append(ordSub.getOrderAmount()).append(",");
						}
					}
					LogUtil.info(module, "----------为了防止微信body长度问题,加上截取限制-------");
					if(sb.length()>=100){
						subject=sb.toString().substring(0, 100);
					}else{
						subject = sb.toString().substring(0, sb.length() - 1);
					}
				}
			}
		}*/
		weChatRe.setAppid(pay_private_key);
		weChatRe.setMch_id(payMercCode);
		weChatRe.setNonce_str(UUID.randomUUID().toString().replace("-", ""));
		weChatRe.setBody(subject);
		weChatRe.setOut_trade_no(out_trade_no);
		weChatRe.setNotify_url(notify_url);
		weChatRe.setTotal_fee(Integer.valueOf(vo.getPayment() + ""));
		weChatRe.setTrade_type(TRAED_TYPE);
		weChatRe.setProduct_id(vo.getShopId()+"");
		weChatRe.setSpbill_create_ip(extendProps.get("clientIp"));// APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
		//weChatRe.setSpbill_create_ip("124.207.3.67");//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
		Map<String, String> map = WeChatPayRequest.paraFilter(weChatRe.toMap());
		String data = WeChatPayRequest.createLinkString(map);
		String signKey = vo.getPayShopCfg().getKeyName();
		String sign = weChatRe.buildSign(new String[] { data, signKey });
		weChatRe.setSign(sign);
		String actionUrl = vo.getPayWay().getPayActionUrl();
		pr.setActionUrl(actionUrl);

		Map<String, String> WeixinJSBridgeMap = new HashMap<String, String>();
		WeixinJSBridgeMap.put("appId", map.get("appid"));
		WeixinJSBridgeMap.put("timeStamp", String.valueOf(new Date().getTime()));
		WeixinJSBridgeMap.put("nonceStr", UUID.randomUUID().toString().replace("-", ""));
		WeixinJSBridgeMap.put("signType", "MD5");

		postData2WeChat(pr.getActionUrl(), weChatRe, map); // 向微信提交数据

		WeixinJSBridgeMap.put("package", "prepay_id=" + map.get("prepay_id"));
		String WeixinJSBridgeData = WeChatPayRequest.createLinkString(WeixinJSBridgeMap);
		String WeixinJSBridgeSign = weChatRe.buildSign(new String[] { WeixinJSBridgeData, signKey });
		WeixinJSBridgeMap.put("paySign", WeixinJSBridgeSign);
		WeixinJSBridgeMap.put("trade_type", map.get("trade_type"));
		WeixinJSBridgeMap.put("prepay_id", map.get("prepay_id"));
		WeixinJSBridgeMap.put("code_url", map.get("code_url"));
		WeixinJSBridgeMap.put("partner_id", payMercCode);
		// LogUtil.info(module, "----------*weChat pay return
		// ---没有乱码------------------");
		LogUtil.info(module, "----------*weChat pay return requestPayment ---没有乱码-------" + PayHelper.toJsonStr(map));
		// 保存日志
		PayRequestDTO payRequestDTO = new PayRequestDTO();

		payRequestDTO.setPayWay(payWay);
		payRequestDTO.setOrderId(request.getOrderId());
		long staffId = 0L;
		if (request.getStaff() != null) {
			staffId = request.getStaff().getId();
		}
		payRequestDTO.setStaffId(staffId);
		payRequestDTO.setShopId(vo.getShopId());
		payRequestDTO.setPayment(vo.getPayment());
		payRequestDTO.setCreateStaff(staffId);
		addPayRequest(payRequestDTO);
		PayIntfReqLogDTO log = new PayIntfReqLogDTO();
		log.setPayWay(payWay);
		log.setOrderId(request.getOrderId());
		log.setStaffId(staffId);
		log.setRequestTime(DateUtil.getSysDate());
		log.setRequestMsg(formatPayRequest(weChatRe));
		addPayLog(log);
		pr.setCharset(vo.getPayWay().getCharSet());
		pr.setFormData(WeixinJSBridgeMap);
		return pr;
	}

	public static String createXml(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		sb.append("<XML>");
		if (params == null || params.size() <= 0) {
			return sb.toString();
		}
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			// if(key.equals("body")){
			// value = URLEncoder.encode(value,"UTF-8");
			// }
			sb.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
		}
		return sb.append("</XML>").toString();
	}

	/**
	 * @Title: getHttpsClient
	 * @Description:https请求配置（一）
	 * @author: luocan
	 * @Create: 2014年9月3日 上午11:16:08
	 * @Modify: 2014年9月3日 上午11:16:08
	 * @param:
	 * @return:
	 */
	public static DefaultHttpClient getHttpsClient() {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			DefaultHttpClient client = new DefaultHttpClient();
			ctx.init(null, new TrustManager[] { tm }, null);
			// SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER为必须，否则报错
			SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = client.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			// 设置要使用的端口，默认是443
			sr.register(new Scheme("https", 443, ssf));
			return client;
		} catch (Exception ex) {
			return null;
		}
	}

	public static Map<String, String> doXmlPost(String url, String charset, Map<String, String> dataMap)
			throws Exception {
		String contents = String.format("请求URL :%s，请求参数: %s。", url, dataMap);
		Map<String, String> resultMap = new HashMap<String, String>();
		String result = "";
		HttpClient httpclient = new DefaultHttpClient();
		// http请求和https请求判断
		String header = url.substring(0, 5);
		if (header.equalsIgnoreCase("https")) {
			httpclient = getHttpsClient();
		}
		// 请求超时1分钟
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		// 读取超时1分钟
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
		// url空格及其他特殊字符转换，get请求不需要对?及&符号进行转换
		url = url.replaceAll(" ", "%20");
		HttpPost post = new HttpPost(url);

		String xmlData = "";
		try {
			xmlData = createXml(dataMap);
			// Construct a string entity
			StringEntity entity = new StringEntity(xmlData);
			// Set XML entity
			post.setEntity(entity);
			// Set content type of request header
			post.setHeader("Content-Type", "text/xml;charset=" + charset);
			// Execute request and get the response
			HttpResponse response = httpclient.execute(post);
			// Response Header - StatusLine - status code
			StatusLine StatusLine = response.getStatusLine();
			// resultMap.put("ProtocolVersion",
			// response.getProtocolVersion().toString());// 协议版本
			resultMap.put("StatusCode", String.valueOf(StatusLine.getStatusCode()));// 返回状态码
			resultMap.put("ReasonPhrase", StatusLine.getReasonPhrase());// 返回原因短语
			result = EntityUtils.toString(entity);
			resultMap.put("respResult", result);
			EntityUtils.consume(entity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw e;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			post.releaseConnection();
		}
		return resultMap;

	}

	private void postData2WeChat(String postUrl, WeChatPayRequest weChatRe, Map<String, String> ret) throws Exception {
		Map<String, String> weChatReMap = weChatRe.paraFilter(weChatRe.toMap());
		String content = orderQueryRSV.postData2WeChat(postUrl, weChatReMap);
		parseXml2Map(content, ret);
	}

	private void postDataWeChatRefund(String postUrl, WeChatPayRequest weChatRe, Map<String, String> ret,String fileId) throws Exception {
        Map<String, String> weChatReMap = weChatRe.paraFilter(weChatRe.toMap());
        String content = orderQueryRSV.postDataWeChatRefund(postUrl, weChatReMap,fileId);
        parseXml2Map(content, ret);
	}

	private static void parseXml2Map(String xml, Map<String, String> map) throws DocumentException {
		LogUtil.info(module, "----------*weChat return xml---没有乱码-------" + xml);
		Document doc = DocumentHelper.parseText(xml);
		Element root = doc.getRootElement();
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			map.put(e.getName(), e.getText());
		}
		LogUtil.info(module, "----------*weChat return xml---没有乱码-------end" + map);
	}

	@Override
	public OrderPayStatusVO parsePayStatus(Map<String, String> params) throws Exception {
		LogUtil.error(module, "quert weChat pay status req=" + params);
		OrderPayStatusVO vo = new OrderPayStatusVO();
		vo.setOrderId(params.get("out_trade_no"));
		// 先查看返回的key是不是正确，如果不正确说明是不可靠的支付通知信息
		// Map<String, String> ret = paymentCallback(params);
		if (SUCC_FINISHED.equals(params
				.get("return_code"))/* && ret.get("flag").equals("true") */) {
			vo.setFlag(OrderPayStatusVO.SUCCESS);
		} else {
			vo.setFlag(OrderPayStatusVO.FAILURE);
		}
		LogUtil.info(module, "----------微信支付成功标识符，但还未进行异步回调等处理-----------" + params.get("return_code"));
		vo.setPayTime(new Timestamp(System.currentTimeMillis()));
		return vo;
	}

	@Override
	public Map<String, String> paymentCallback(Map<String, String> responseResultMap) throws Exception {
		LogUtil.info(module, "微信扫码支付回调开始" + PayHelper.toJsonStr(responseResultMap));
		Map<String, String> result = new HashMap<String, String>();
		PayIntfNotifyLogDTO log = new PayIntfNotifyLogDTO();
		Timestamp requestTime = DateUtil.getSysDate();
		String payNotifyMSG = PayHelper.toJsonStr(responseResultMap);
		String orderId = "";
		String tradeNo = "";
		long staffId = OrdConstants.Common.DEFAULT_STAFFID;
		Timestamp responseTime = requestTime;
		// 转换结果格式
		WeChatPayResponse response = new WeChatPayResponse();
		response.buildSelf(responseResultMap);
		orderId = response.getOut_trade_no();
		OrdMain ordMain = getOrderMainBean(orderId);
		PayWay payWayBean = getPayWayBean(payWay);
		// 过滤空值、sign与sign_type参数
		Map<String, String> sParaNew = WeChatPayResponse.paraFilter(responseResultMap);
		// 获取待签名字符串
		String preSignStr = WeChatPayResponse.createLinkString(sParaNew);
 
		// 店铺ID
        long shopId = 0l;
		// 获取合并支付的开关 1-表示开启  0-表示关闭
        BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
        if(OrdConstants.OrdPayRel.PAY_MERGE_ENABLE.equals(payMergeSysCfg.getParaValue())){
            shopId = OrdConstants.OrdPayRel.PAY_WAY_MERGE_CFG_ID;
        }else{
            // 查询订单对应的店铺
            ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
            rOrdPayRelReq.setJoinOrderid(orderId);
            List<ROrdPayRelResp> orderList = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
            if(orderList == null || orderList.size() == 0){
                throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310003);
            }
            ROrdPayRelResp ordPayRelResp = orderList.get(0);
            shopId = ordPayRelResp.getShopId();
        }
        PayShopCfg payShopCfg = getPayShopCfgBean(shopId, payWay); 
        if(payShopCfg == null){
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310009);
        }
		String[] signParams = new String[] { preSignStr, payShopCfg.getKeyName() };
		// 解释微信支付返回的信息是否合法
		WeChatPayRequest weChat = new WeChatPayRequest();
		String sign = weChat.buildSign(signParams);
		if (sign.equals(response.getSign())) {// 说明正确的合法支付
		    if (response.getReturn_code().equals(OrdConstants.PayWayWeChat.RET_VALUE)) {	
				PaySuccInfo paySuccInfo = new PaySuccInfo();
				paySuccInfo.setOrderId(response.getOut_trade_no());
				paySuccInfo.setPayWay(payWay);
				paySuccInfo.setStaffId(staffId);
				paySuccInfo.setPayTransNo(response.getTransaction_id());
				paySuccInfo.setPayment(Long.parseLong(response.getTotal_fee()));
				paySuccInfo.setPayeeAcct(response.getOpenid());
				paySuccInfo.setPayeeName(response.getOpenid());
				// paySuccInfo.setPayWayName(payWayName);
				// paySuccInfo.setShopId(shopId);
				paySuccInfo.setPayType(OrdConstants.PayStatus.PAY_TYPE_01);
				saveHandlePaySucc(paySuccInfo);
				result.put(OrdConstants.PayStatus.TO_AIP_PARAM_FLAG, OrdConstants.PayStatus.SUCCESS);
				result.put(OrdConstants.PayStatus.TO_AIP_PARAM_MESSAGE, "success");
			} else {
				result.put(OrdConstants.PayStatus.TO_AIP_PARAM_FLAG, OrdConstants.PayStatus.FAILURE);
				result.put(OrdConstants.PayStatus.TO_AIP_PARAM_MESSAGE, "微信支付失败！");
			}
		} else {
			result.put(OrdConstants.PayStatus.TO_AIP_PARAM_FLAG, OrdConstants.PayStatus.FAILURE);
			result.put(OrdConstants.PayStatus.TO_AIP_PARAM_MESSAGE, "验签失败！");
		}
		responseTime = DateUtil.getSysDate();
		log.setPayWay(payWay);
		log.setOrderId(orderId);
		log.setStaffId(staffId);
		log.setPaywayRequestNo(tradeNo);
		log.setRequestTime(requestTime);
		log.setRequestMsg(payNotifyMSG);
		log.setResponseTime(responseTime);
		log.setResponseMsg(JSON.json(result));
		addPayResponseLog(log);
		LogUtil.info(module, "订单" + orderId + "微信扫码支付回调结束" + PayHelper.toJsonStr(result));
		return result;
	}

	@Override
	public RPayRefundResponse refund(RPayRefundRequest request, Map<String, String> extendProps) throws Exception {
		LogUtil.info(module, "----------*wechat scan code requestPayment*---没有乱码-------");
		PayWay payWayBean = getPayWayBean(payWay);
		OrdMain ordMainBean = getOrderMainBean(request.getOrderId());
		if (ordMainBean == null) {
			throw new BusinessException(MsgConstants.PayServiceMsgCode.PAY_SERVER_310003);
		} 
		long shopId = 0l;
        // 获取合并支付的开关 1-表示开启  0-表示关闭
        BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
        if(OrdConstants.OrdPayRel.PAY_MERGE_ENABLE.equals(payMergeSysCfg.getParaValue())){
            shopId = OrdConstants.OrdPayRel.PAY_WAY_MERGE_CFG_ID;
        }else{
            shopId = ordMainBean.getShopId();
        }
        PayShopCfg payShopCfg = getPayShopCfgBean(shopId, payWay);
        if(payShopCfg == null){
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310009);
        }
		if (ordMainBean.getRealMoney().longValue() < request.getRefundAmount().longValue()) {
			throw new BusinessException(MsgConstants.PayServiceMsgCode.PAY_SERVER_310016);
		}
		List<PayResult> payResultList = payResultSV.getPayResultByOrderId(ordMainBean.getPayTranNo());
		if (payResultList.size() < 1) {
			throw new BusinessException(MsgConstants.PayServiceMsgCode.PAY_SERVER_310017);
		}
		String appid = payShopCfg.getCerName();//支付私钥
		String mch_id = payShopCfg.getMercCode();//平台商户号;
		String nonce_str = UUID.randomUUID().toString().replace("-", "");
		String transaction_id = payResultList.get(0).getPayTransNo();
		String out_refund_no = DateUtil.getDateString("yyyyMMdd") + request.getBackId().longValue();// 不能超过8+24位
		long total_fee = payResultList.get(0).getPayment();
		long refund_fee = request.getRefundAmount();
		// String op_user_id = request.getOperId();
		WeChatPayRequest weChat = new WeChatPayRequest();
		weChat.setAppid(appid);
		weChat.setMch_id(mch_id);
		weChat.setNonce_str(nonce_str);
		weChat.setTransaction_id(transaction_id);
		weChat.setOut_refund_no(out_refund_no);
		weChat.setTotal_fee(Integer.parseInt(total_fee + ""));// 订单总金额
		weChat.setRefund_fee(refund_fee + "");// 退款金额
		weChat.setOp_user_id(mch_id);

		Map<String, String> map = WeChatPayRequest.paraFilter(weChat.toMap());
		String signData = WeChatPayRequest.createLinkString(map);
		String sign = weChat.buildSign(new String[] { signData, payShopCfg.getKeyName() });// 获取sign数据
		weChat.setSign(sign);
		Timestamp requestTime = DateUtil.getSysDate();
		String requestMsg = PayHelper.toJsonStr(weChat.toMap());
		PayIntfReqLogDTO log = new PayIntfReqLogDTO();
		log.setPayWay(payWay);
		log.setOrderId(request.getOrderId());
		log.setTypeCode(OrdConstants.PayStatus.PAY_LOG_TYPE_CODE_02);
		log.setStaffId(request.getStaff().getId());
		log.setRequestTime(requestTime);
		log.setRequestMsg(requestMsg);
		payIntfReqLogSV.addPayIntfReqLog(log);
		// 插入退款结果表，如果已有就更新即可
		PayRefundResult payRefundResult = payRefundResultSV.getPayRefundResultByPaywayBackId(payWay,
				request.getBackId());
		if (payRefundResult == null) {
			payRefundResult = new PayRefundResult();
			payRefundResult.setBackId(request.getBackId());
			payRefundResult.setOrderId(request.getOrderId());
			payRefundResult.setPayWay(payWay);
			payRefundResult.setRefundTransNo(out_refund_no);
			payRefundResult.setRefundAmount(request.getRefundAmount());
			payRefundResult.setRefundStatus(OrdConstants.PayStatus.PAY_REFUND_RESULT_STATUS_1);
			payRefundResult.setRefundDesc("线上退款");
			payRefundResult.setRefundTime(DateUtil.getSysDate());
			payRefundResultSV.addPayRefundResult(payRefundResult);
		} else {
			payRefundResult.setRefundTime(DateUtil.getSysDate());
			payRefundResultSV.updateRefund(payRefundResult);
		}
		Map<String, String> retmap = new HashMap<String, String>();
		postDataWeChatRefund(payWayBean.getPayRefundUrl(), weChat, retmap,payWayBean.getPayVerifyCert());// 向微信提交数据
		RPayRefundResponse response = new RPayRefundResponse();
		response.setRefundMethod(OrdConstants.PayStatus.PAY_REFUND_METHOD_01);
		if(retmap.get("return_code").equals("FAIL")){
		    response.setFlag(false);
		    response.setMessage(String.valueOf(retmap.get("return_msg")));
		    return response;
		}
		if(retmap.get("result_code").equals("FAIL")){
            response.setFlag(false);
            if(StringUtil.isNotEmpty(retmap.get("err_code"))){
                response.setMessage(String.valueOf(retmap.get("err_code_des")));
            }
            return response;
        }
		retmap = refundCallback(retmap);// 解释返回报文
		
		if (retmap.get(OrdConstants.PayStatus.TO_AIP_PARAM_FLAG).equals(OrdConstants.PayStatus.SUCCESS)) {
			response.setFlag(true);
			response.setMessage("退款成功！");
		} else {
			response.setFlag(false);
			response.setMessage(String.valueOf(retmap.get(OrdConstants.PayStatus.TO_AIP_PARAM_MESSAGE)));
		} 
		PayRequestData pr = new PayRequestData();
		pr.setFormData(weChat.toMap());
		String actionUrl = payWayBean.getPayRefundUrl();
		pr.setActionUrl(actionUrl);
		String charset = payWayBean.getCharSet();
		pr.setCharset(charset);
		response.setPayRequestData(pr);
		pr.setMethod(PayRequestData.METHOD_POST);
		return response;
	}

	@Override
	public Map<String, String> refundCallback(Map<String, String> resultData) throws Exception {
		LogUtil.info(module, "微信扫码支付退款回调开始" + PayHelper.toJsonStr(resultData));

		Map<String, String> result = new HashMap<String, String>();
		PayIntfNotifyLogDTO log = new PayIntfNotifyLogDTO();
		Timestamp requestTime = DateUtil.getSysDate();
		String payNotifyMSG = PayHelper.toJsonStr(resultData);
		String orderId = "";
		String refundTradeNo = "";
		Long staffId = OrdConstants.Common.DEFAULT_STAFFID;
		Timestamp responseTime = requestTime;
		try {
			// 转换结果格式
			WeChatPayResponse response = new WeChatPayResponse();
			response.buildSelf(resultData);
			refundTradeNo = response.getOut_refund_no();
			if (response.getOp_user_id() != null) {
				staffId = Long.valueOf(response.getOp_user_id());
			}
			String backIdStr = refundTradeNo.substring(8);
			Long backId = Long.valueOf(backIdStr);
			PayRefundResult payRefundResult = payRefundResultSV.getPayRefundResultByPaywayBackId(payWay, backId);
			if (payRefundResult == null) {
				throw new BusinessException(MsgConstants.PayServiceMsgCode.PAY_SERVER_310019);
			}
			orderId = payRefundResult.getOrderId();
			OrdMain ordMain = getOrderMainBean(orderId);
			// 过滤空值、sign与sign_type参数
			Map<String, String> sParaNew = WeChatPayResponse.paraFilter(resultData);
			// 获取待签名字符串
			String preSignStr = WeChatPayResponse.createLinkString(sParaNew);
			long shopId = 0l;
            // 获取合并支付的开关 1-表示开启  0-表示关闭
            BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
            if(OrdConstants.OrdPayRel.PAY_MERGE_ENABLE.equals(payMergeSysCfg.getParaValue())){
                shopId = OrdConstants.OrdPayRel.PAY_WAY_MERGE_CFG_ID;
            }else{
                shopId = ordMain.getShopId();
            }
            PayShopCfg payShopCfg = getPayShopCfgBean(shopId, payWay);
            if(payShopCfg == null){
                throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310009);
            }
			String[] signParams = new String[] { preSignStr, payShopCfg.getKeyName() };
			// 解释微信支付返回的信息是否合法
			WeChatPayRequest weChat = new WeChatPayRequest();
			if (response.getSign().equals(weChat.buildSign(signParams))) {
				// 请在这里加上商户的业务逻辑程序代码
				PayRefundSuccInfo payRefundSuccInfo = new PayRefundSuccInfo();
				payRefundSuccInfo.setBackId(payRefundResult.getBackId());
				payRefundSuccInfo.setOrderId(orderId);
				payRefundSuccInfo.setPayWay(payWay);
				payRefundSuccInfo.setStaffId(staffId);
				payRefundSuccInfo.setRefundTransNo(response.getRefund_id());
				payRefundSuccInfo.setPayment(Long.parseLong(response.getRefund_fee()));
				// payRefundSuccInfo.setPayeeName(payeeName);
				// payRefundSuccInfo.setPayeeAcct(payeeAcct);
				if (resultData.get(OrdConstants.PayWayWeChat.RET_KEY).equals(OrdConstants.PayWayWeChat.RET_VALUE)) {
					payRefundSuccInfo.setFlag(OrdConstants.PayStatus.PAY_REFUND_RESULT_STATUS_2);
				} else {
					payRefundSuccInfo.setFlag(OrdConstants.PayStatus.PAY_REFUND_RESULT_STATUS_3);
				}
				saveRefundCallback(payRefundSuccInfo);
				result.put(OrdConstants.PayStatus.TO_AIP_PARAM_FLAG, OrdConstants.PayStatus.SUCCESS);
				result.put(OrdConstants.PayStatus.TO_AIP_PARAM_MESSAGE, "success");
			} else {
				result.put(OrdConstants.PayStatus.TO_AIP_PARAM_FLAG, OrdConstants.PayStatus.FAILURE);
				result.put(OrdConstants.PayStatus.TO_AIP_PARAM_MESSAGE, "验签失败！");
			}
		} catch (Exception e) {
			LogUtil.error(module, "微信扫码支付退款回调异常" + PayHelper.toJsonStr(result), e);
			result.put(OrdConstants.PayStatus.TO_AIP_PARAM_FLAG, OrdConstants.PayStatus.FAILURE);
			result.put(OrdConstants.PayStatus.TO_AIP_PARAM_MESSAGE, "微信扫码支付退款回调异常！");
		}
		responseTime = DateUtil.getSysDate();
		log.setPayWay(payWay);
		log.setOrderId(orderId);
		log.setStaffId(staffId);
		log.setPaywayRequestNo(refundTradeNo);
		log.setTypeCode(OrdConstants.PayStatus.PAY_NOTIFYLOG_TYPE_CODE_02);
		log.setRequestTime(requestTime);
		log.setRequestMsg(payNotifyMSG);
		log.setResponseTime(responseTime);
		log.setResponseMsg(JSON.json(result));
		payIntfNotifyLogSV.addPayNotifyLog(log);
		LogUtil.info(module, "订单" + orderId + "微信扫码支付退款回调结束" + PayHelper.toJsonStr(result));
		return result;
	}

	@Override
	public void saveRefundCallback(PayRefundSuccInfo payRefundSuccInfo) throws Exception {
		super.saveRefundCallback(payRefundSuccInfo);
	} 
}
