package com.zengshi.ecp.pay;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zengshi.ecp.order.dubbo.util.RSA;
import com.zengshi.ecp.order.service.busi.impl.pay.AlipayRefundRequest;
import com.zengshi.ecp.order.service.busi.impl.pay.AlipayRequest;
import com.zengshi.ecp.order.service.busi.impl.pay.AlipayResponse;
import com.alibaba.fastjson.JSON;

/** 
* Alipay Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 17, 2016</pre> 
* @version 1.0 
*/ 
public class AlipayTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: requestPayment(PaymentRequest request, Map<String, String> extendProps) 
* 
*/ 
@Test
public void testRequestPayment() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: paymentCallback(Map<String, String> responseResultMap) 
* 
*/ 
@Test
public void testPaymentCallback() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: refund(RPayRefundRequest request, Map<String, String> extendProps) 
* 
*/ 
@Test
public void testRefund() throws Exception {
    AlipayRefundRequest alipayRefundRequest = new AlipayRefundRequest();
    alipayRefundRequest.setPartner("2088121127353472");
    alipayRefundRequest.set_input_charset("UTF-8");
    alipayRefundRequest.setSign_type("MD5");
    alipayRefundRequest.setNotify_url("http://aip.pmph.com/route/alipayrefundnotify?ecp_staffId=178");
    alipayRefundRequest.setSeller_email("pmphmc@163.com");
    alipayRefundRequest.setSeller_user_id("2088121127353472");
    alipayRefundRequest.setRefund_date("2016-06-16 21:49:32");
    alipayRefundRequest.setBatch_no("20160616110017");
    alipayRefundRequest.setBatch_num("1");
    alipayRefundRequest.setDetail_data("2016-06-16 21:49:32");



    String sign;
    Map<String, String> signMap = AlipayRequest.paraFilter(alipayRefundRequest.toMap());
    String data = AlipayRequest.createLinkString(signMap);
    sign = alipayRefundRequest.buildSign(new String[] { data, "m6gpa5iuas1u5mhq4uo3rzgsznb7r2nn" });
    alipayRefundRequest.setSign(sign);
    System.out.print("............."+JSON.toJSONString(alipayRefundRequest));
} 
@Test
public void testWebNotify() throws Exception {
	String signPc="1b0e78c17c1612c7dc46e7d0bd73a24f";
	String requestPc="buyer_id=2088702330067822&trade_no=2016062421001004820203460106&use_coupon=N&notify_time=2016-06-24 15:15:06&subject=奥克斯电熨斗YD2029A家用手持蒸汽熨斗干湿两熨迷你电烫斗*1&is_total_fee_adjust=N&notify_type=trade_status_sync&out_trade_no=RW16062400002195&gmt_payment=2016-06-24 15:15:06&trade_status=TRADE_SUCCESS&discount=0.00&buyer_email=13950247731&gmt_create=2016-06-24 15:14:58&price=0.01&extra_common_param=2004&total_fee=0.01&quantity=1&seller_id=2088911144746843&notify_id=00e0319b29dcd015a9a4e274dea0ad3mbu&seller_email=wowopay@163.com&payment_type=1";
	Map<String, String> responseResultMap=new HashMap<String,String>();
	responseResultMap.put("buyer_id", "2088702330067822");
	responseResultMap.put("trade_no", "2016062421001004820203460106");	
	responseResultMap.put("use_coupon", "N");
	responseResultMap.put("notify_time", "2016-06-24 15:15:06");
	responseResultMap.put("subject", "奥克斯电熨斗YD2029A家用手持蒸汽熨斗干湿两熨迷你电烫斗*1");	
	responseResultMap.put("is_total_fee_adjust", "N");
	responseResultMap.put("notify_type", "trade_status_sync");
	responseResultMap.put("out_trade_no", "RW16062400002195");	
	responseResultMap.put("gmt_payment", "2016-06-24 15:15:06");
	responseResultMap.put("trade_status", "TRADE_SUCCESS");
	responseResultMap.put("discount", "0.00");	
	responseResultMap.put("buyer_email", "13950247731");
	responseResultMap.put("gmt_create", "2016-06-24 15:14:58");
	responseResultMap.put("price", "0.01");
	responseResultMap.put("extra_common_param", "2004");
	responseResultMap.put("total_fee", "0.01");
	responseResultMap.put("quantity", "1");
	responseResultMap.put("seller_id", "2088911144746843");
	responseResultMap.put("notify_id", "00e0319b29dcd015a9a4e274dea0ad3mbu");
	responseResultMap.put("seller_email", "wowopay@163.com");
	responseResultMap.put("payment_type", "1");
    //过滤空值、sign与sign_type参数
    Map<String, String> sParaNew = AlipayRequest.paraFilter(responseResultMap);
    String preSignStr = AlipayRequest.createLinkString(sParaNew);
    System.out.print("............."+preSignStr);
	String[] signParams=new String[]{preSignStr,"9s98xf9my8ejkm4zx0qesxfq83jyjzm7"};
    AlipayResponse response=new AlipayResponse();
    boolean 	flag=response.verifySign(signParams, signPc,"MD5");
    System.out.print("............."+flag);
} 
@Test
public void testAppNotify() throws Exception {
	String signApp="LLuGn1hQmgzEnxTWleaabfGgqvqKZnCJGcP/2X2R65o0oPn2Z2ETP9DoWsBkOpXkYlyT2OcYcp2EQfSiwhAV2vWzQ5Roo/VMB9wTR45ORE5XPUjg1VPvvI12L8l3B0XsLv5RIf5SWX+InhoF1FJBtziBdlzzDKqXCIAR/BDDRdU=";
	//String publicApp="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAl0KgAOiFT2iyCYIk2iDp5eKtQIZB4Tk1HYKC9MVLVQVN/YGQHeMwjcRFik+shoBkZpbsvowcwS1xl+Qq8l6ZWjPdWLSTNJzB3GLKXRFFQklSfd+bd9qnnP4Rq8hTdIJmRvu/Odte+qnk3+VFdDizTQ6SB0CQIqTmJgOC/0Tn8wIDAQAB";
	String publicApp="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	
	Map<String, String> responseResultMap=new HashMap<String,String>();
	responseResultMap.put("buyer_id", "2088702258211903");
	responseResultMap.put("trade_no", "2016062521001004900219860303");	
	responseResultMap.put("use_coupon", "N");
	responseResultMap.put("body", "null");
	responseResultMap.put("notify_time", "2016-06-25 17:51:19");
	responseResultMap.put("subject", "奥克斯电熨斗YD2029A家用手持蒸汽熨斗干湿两熨迷你电烫斗*1");	
	responseResultMap.put("is_total_fee_adjust", "N");
	responseResultMap.put("notify_type", "trade_status_sync");
	responseResultMap.put("out_trade_no", "RW16062500002348");	
	responseResultMap.put("gmt_payment", "2016-06-25 17:27:30");
	responseResultMap.put("trade_status", "TRADE_SUCCESS");
	responseResultMap.put("discount", "0.00");	
	responseResultMap.put("buyer_email", "13763801100");
	responseResultMap.put("gmt_create", "2016-06-25 17:27:29");
	responseResultMap.put("price", "0.01");
	responseResultMap.put("total_fee", "0.01");
	responseResultMap.put("quantity", "1");
	responseResultMap.put("seller_id", "2088911144746843");
	responseResultMap.put("notify_id", "3ad80ea4ca5edcdd6427d25bdb4dcbcmy2");
	responseResultMap.put("seller_email", "wowopay@163.com");
	responseResultMap.put("payment_type", "1");
    //过滤空值、sign与sign_type参数
    Map<String, String> sParaNew = AlipayRequest.paraFilter(responseResultMap);
    String preSignStr = AlipayRequest.createLinkString(sParaNew);
    System.out.print("............."+preSignStr);
	String[] signParams=new String[]{preSignStr,publicApp};
    String text = signParams[0];
    String key = signParams[1];
    boolean 	flag=RSA.verify(text, signApp, key, "utf-8");
    System.out.print("............."+flag);
} 
/** 
* 
* Method: refundCallback(Map<String, String> responseResultMap) 
* 
*/ 
@Test
public void testRefundCallback() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: parsePayStatus(Map<String, String> params) 
* 
*/ 
@Test
public void testParsePayStatus() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: check(Map<String, String> resultData) 
* 
*/ 
@Test
public void testCheck() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: dealCheck(PayShopCfg payShopCfg, Timestamp qsDate) 
* 
*/ 
@Test
public void testDealCheck() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: checkbyPage(PayShopCfg payShopCfg, Timestamp qsDate, int page) 
* 
*/ 
@Test
public void testCheckbyPage() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Alipay.getClass().getMethod("checkbyPage", PayShopCfg.class, Timestamp.class, int.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
