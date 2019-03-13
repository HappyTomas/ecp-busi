package com.zengshi.ecp.pay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import com.zengshi.ecp.aip.dubbo.interfaces.IOrderQueryRSV;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.PayRefundResult;
import com.zengshi.ecp.order.dao.model.PayShopCfg;
import com.zengshi.ecp.order.dubbo.dto.pay.PayIntfNotifyLogDTO;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRefundSuccInfo;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRequestData;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayRefundResponse;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.dubbo.util.PayHelper;
import com.zengshi.ecp.order.service.busi.impl.pay.DefaultPayWay;
import com.zengshi.ecp.order.service.busi.impl.pay.PayWayEnum;
import com.zengshi.ecp.order.service.busi.impl.pay.WeChatPay;
import com.zengshi.ecp.order.service.busi.impl.pay.WeChatPayRequest;
import com.zengshi.ecp.order.service.busi.impl.pay.WeChatPayResponse;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;

public class WeChatPayTest extends DefaultPayWay{ 
    public static final String module = WeChatPayTest.class.getName();
    public final static String payWay = PayWayEnum.getPayWayByImplClass(WeChatPay.class);
    @Resource
    private IOrderQueryRSV orderQueryRSV; 
 

/** 
* 
* Method: refund(RPayRefundRequest request, Map<String, String> extendProps) 
* 
*/ 
@Test
public void testRefund() throws Exception {
    LogUtil.info(module, "----------*wechat scan code requestPayment*---没有乱码-------");
     
    String appId= "wx8b358f3aead72646";
    String mch_id= "1359167302";
    String nonce_str = UUID.randomUUID().toString().replace("-", ""); 
    // String out_refund_no = this.seqPayRefund.nextValue()+"";
    String out_refund_no = DateUtil.getDateString("yyyyMMdd") + "1234567";// 不能超过8+24位
    long total_fee = 1;
    long refund_fee = 1;
    String url="https://api.mch.weixin.qq.com/secapi/pay/refund";
    // String op_user_id = request.getOperId();
    WeChatPayRequest weChat = new WeChatPayRequest();
    weChat.setAppid(appId);
    weChat.setMch_id(mch_id);
    weChat.setNonce_str(nonce_str);
    weChat.setTransaction_id("1123");
    weChat.setOut_refund_no(out_refund_no);
    weChat.setTotal_fee(Integer.parseInt(total_fee + ""));// 订单总金额
    weChat.setRefund_fee(refund_fee + "");// 退款金额
    // 暂时还未开始开发退货退款的代码，so 先把这个屏蔽了

    weChat.setOp_user_id(mch_id);

    Map<String, String> map = WeChatPayRequest.paraFilter(weChat.toMap());
    String signData = WeChatPayRequest.createLinkString(map);
    String sign = weChat.buildSign(new String[] { signData, "P2gGEtIJ1i5UxOZCiI3FUwSBzWBoYleU" });// 获取sign数据
    weChat.setSign(sign);
    Timestamp requestTime = DateUtil.getSysDate();
    String requestMsg = PayHelper.toJsonStr(weChat.toMap());
     
    Map<String, String> retmap = new HashMap<String, String>();
    postData2WeChat(url, weChat, retmap);// 向微信提交数据
    retmap = refundCallback(retmap);// 解释返回报文
    RPayRefundResponse response = new RPayRefundResponse();
    if (retmap.get(OrdConstants.PayStatus.TO_AIP_PARAM_FLAG).equals(OrdConstants.PayStatus.SUCCESS)) {
        response.setFlag(true);
    } else {
        response.setFlag(false);
    }
    PayRequestData pr = new PayRequestData();
    pr.setFormData(weChat.toMap());
    String actionUrl = url;
    pr.setActionUrl(actionUrl);
    String charset = "utf-8";
    pr.setCharset(charset);
    response.setPayRequestData(pr);
    response.setRefundMethod(OrdConstants.PayStatus.PAY_REFUND_METHOD_01);
    pr.setMethod(PayRequestData.METHOD_POST); 
 
} 

private void postData2WeChat(String postUrl, WeChatPayRequest weChatRe, Map<String, String> ret) throws Exception {
    Map<String, String> weChatReMap = weChatRe.paraFilter(weChatRe.toMap());
    String content = postDataWeChatRefund(postUrl, weChatReMap);
    parseXml2Map(content, ret);
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
        //if(key.equals("body")){
        //  value = URLEncoder.encode(value,"UTF-8");
        //}
        sb.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
    }
    return sb.append("</XML>").toString();
}   

public String postDataWeChatRefund(String postUrl, Map<String, String> map)
        throws Exception {
    String xmlData;
    String content = "";
    char [] mchId = String.valueOf(map.get("mch_id")).toCharArray();
    try {
        xmlData = createXml(map); 
        /*--------4.读取证书文件,这一段是直接从微信支付平台提供的demo中copy的，所以一般不需要修改------*/
        //指定读取证书格式为PKCS12
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        //读取本机存放的PKCS12证书文件
        String fileName = FileUtil.getFileName("58423f59237f77060950f30b"); 
       // FileInputStream instream = new FileInputStream(new File("F:\\apiclient_cert.p12"));
        InputStream instream = FileUtil.readFile2ips("58423f59237f77060950f30b");
        try {
            //指定PKCS12的密码(商户ID)
            keyStore.load(instream, mchId);
        } catch (Exception e) {
            LogUtil.error(module, "微信支付提交信息异常", e);
            throw new IOException(e);
        } finally {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom()
        .loadKeyMaterial(keyStore, mchId).build();
        //指定TLS版本
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
        sslcontext,new String[] { "TLSv1" },null,
        SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        //设置httpclient的SSLSocketFactory
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        
        /*------5.发送数据到微信的退款接口------- */
        HttpPost httpost= getPostMethod(postUrl);
        httpost.setEntity(new StringEntity(xmlData, "UTF-8"));
        HttpResponse weixinResponse = httpclient.execute(httpost);
        content = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
        return content;
    } catch (Exception e) {
        LogUtil.error(module, "微信支付提交信息异常", e);
        throw new IOException(e);
    } finally { 

    }

}

//模拟浏览器post提交
private static HttpPost getPostMethod(String url) {
    HttpPost pmethod = new HttpPost(url); // 设置响应头信息
    pmethod.addHeader("Connection", "keep-alive");
    pmethod.addHeader("Accept", "*/*");
    pmethod.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
    pmethod.addHeader("Host", "api.mch.weixin.qq.com");
    pmethod.addHeader("X-Requested-With", "XMLHttpRequest");
    pmethod.addHeader("Cache-Control", "max-age=0");
    pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
    return pmethod;
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
public Map<String, String> refundCallback(Map<String, String> resultData) throws Exception {
    LogUtil.info(module, "微信支付退款回调开始" + PayHelper.toJsonStr(resultData));

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
        refundTradeNo = response.getRefund_id();
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
        PayShopCfg payShopCfg = getPayShopCfgBean(ordMain.getShopId(), payWay);
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
        LogUtil.error(module, "微信支付退款回调异常" + PayHelper.toJsonStr(result), e);
        result.put(OrdConstants.PayStatus.TO_AIP_PARAM_FLAG, OrdConstants.PayStatus.FAILURE);
        result.put(OrdConstants.PayStatus.TO_AIP_PARAM_MESSAGE, "微信支付退款回调异常！");
    }
    responseTime = DateUtil.getSysDate(); 
    LogUtil.info(module, "订单" + orderId + "微信支付退款回调结束" + PayHelper.toJsonStr(result));
    return result;
} 
 
} 

