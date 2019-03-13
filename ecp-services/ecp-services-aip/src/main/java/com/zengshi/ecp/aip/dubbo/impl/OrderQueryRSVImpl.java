package com.zengshi.ecp.aip.dubbo.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.aip.dao.mapper.common.AipSvLogMapper;
import com.zengshi.ecp.aip.dao.model.AipSvLogWithBLOBs;
import com.zengshi.ecp.aip.dubbo.interfaces.IOrderQueryRSV;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.aip.util.HttpRequestUtil;
import com.zengshi.aip.util.MySecureProtocolSocketFactory;
import com.alibaba.fastjson.JSON;

/**
 * @Title: OrderQueryServiceImpl.java
 * @Package: com.zengshi.aip.provider.eapy.impl
 * @Description: 单笔订单查询实现类
 * @Comment:
 * @author: luocan
 * @CreateDate: 2014年8月28日 上午11:16:05
 */
public class OrderQueryRSVImpl implements IOrderQueryRSV {
    public static final String module = OrderQueryRSVImpl.class.getName();
	private final static Logger logger = LoggerFactory.getLogger(OrderQueryRSVImpl.class);

	@Autowired
	AipSvLogMapper aipSvLogDAO;


    /**
     * @Title: getResultData
     * @Description:模拟post请求 鸿支付请求参数放在Params中
     * @author:
     * @Create:
     * @Modify:
     * @param:idCode:身份识别， 用来识别被请求方， url：请求url，  charset:请求字符集， dataMap：请求参数
     * @return:
     * @throws
     */
    public Map<String, String> getHongResultData(String idCode, String charset,String url, Map<String, String> dataMap) throws Exception {
        Map<String, String> resultMap = new HashMap<String, String>();
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setParameter("http.protocol.content-charset", HTTP.UTF_8);
        httpClient.getParams().setParameter("Content-Encoding",HTTP.UTF_8);
        httpClient.getParams().setParameter("; charset=", HTTP.UTF_8);
        httpClient.getParams().setParameter("US-ASCII", HTTP.UTF_8);
        Protocol myhttps = new Protocol("https", new MySecureProtocolSocketFactory(), 443);
        Protocol.registerProtocol("https", myhttps);

        PostMethod postMethod = new PostMethod(url);
        postMethod.setParameter("requestPacket", dataMap.get("requestPacket"));
        int statusCode = 0;
        try {
            statusCode = httpClient.executeMethod(postMethod);
            if(statusCode == HttpStatus.SC_OK){
                byte[] responseBody = postMethod.getResponseBody();
                String refundResXml = new String(responseBody, HTTP.UTF_8);
                logger.error(JSON.toJSONString(refundResXml));
                resultMap.put("StatusCode",String.valueOf(statusCode));
                resultMap.put("respResult", refundResXml);
            } else {
                resultMap = null;
                logger.error("鸿支付请求异常");
                throw new Exception("鸿支付请求异常");
            }
        } catch (IOException ie) {
            logger.error("请求超时或异常", ie);
            throw ie;
        } catch (Exception e){
            throw e;
        }

        return resultMap;
    }

    @Override
    public Map<String, String> getWeChatResultData(String payWay, String payQueryUrl, String charset, Map<String, String> params) throws Exception {
        Map<String, String> returnMap = new HashMap<String, String>();
        // 数据库插入对象声明及初始化
        String contents = null;
        String formats = String.format("请求URL :%s，通道编码  ：%s， 字符集  ：%s，请求参数: %s。", payQueryUrl, payWay, charset, params);
        AipSvLogWithBLOBs tAipSvLogWithBLOBs = new AipSvLogWithBLOBs();
        Timestamp requestTime = new Timestamp(new Date().getTime());
        tAipSvLogWithBLOBs.setAppKey("OrderQueryService" + payWay);
        tAipSvLogWithBLOBs.setServiceName("OrderQueryService");
        tAipSvLogWithBLOBs.setRequestTime(requestTime);
        try {
            // 各参数均不能为空
            if (payWay == null || charset == null || payQueryUrl == null
                    || params == null) {
                contents = "支付通道编码payWay、字符集charset、请求payQueryUrl、或请求参数params为null";
                tAipSvLogWithBLOBs.setRequest(contents + formats);
                logger.info(contents);
                throw new Exception(contents);
            }
            logger.info("单笔订单查询开始！Begin to getqueryPaymentResult! ");
            contents = formats;
            logger.info(contents);
            // 数据库插入对象声明及初始化
            tAipSvLogWithBLOBs.setRequest(contents);
            try {
                returnMap = HttpRequestUtil.doXmlPost(payQueryUrl, charset, params);
                // 获取返回状态码
                String StatusCode = returnMap.get("StatusCode");
                // 获取原因短语
                String ReasonPhrase = returnMap.get("ReasonPhrase");
                logger.info("StatusCode= " + StatusCode + "，ReasonPhrase="
                        + ReasonPhrase);
                if (StatusCode != null) {
                    if (StatusCode.equalsIgnoreCase("200")) {
                        returnMap.put("flag", "0");
                        returnMap.put("returnMsg", "success! StatusCode= "+ StatusCode);
                    } else {
                        returnMap.put("flag", "1");
                        returnMap.put("returnMsg", "fail! StatusCode= "+ StatusCode + "，原因为：ReasonPhrase="+ ReasonPhrase);
                    }
                } else {
                    returnMap.put("flag", "1");
                    returnMap.put("returnMsg", "fail! 获取返回状态失败，主机不存在或返回结果获取失败！");
                    throw new Exception("获取返回状态失败，主机不存在或返回结果获取失败！");

                }
                // 将请求得到的结果复制到指定key值得value中
                returnMap.put("PaymentResult", returnMap.get("respResult"));
            } catch (Exception e) {
                logger.error("请求超时或异常", e);
                throw e;
            }
        } catch (Exception e) {
            logger.error("请求超时或异常", e);
            Writer result = new StringWriter();
            PrintWriter printWriter = new PrintWriter(result);
            e.printStackTrace(printWriter);
            // 将异常信息记录至日志表中
            returnMap.put("exception", result.toString());
            throw e;
        } finally {
            Timestamp responseTime = new Timestamp(new Date().getTime());
            tAipSvLogWithBLOBs.setResponseTime(responseTime);
            tAipSvLogWithBLOBs.setResponse(returnMap.toString());
//            aipSvLogDAO.insert(tAipSvLogWithBLOBs);
        }

        return returnMap;

    }


    /**
	 * @Title: queryPaymentResult
	 * @Description:根据URL和参数发送HTTP请求并获取返回结果
	 * @author: luocan
	 * @Create: 2014年8月28日 上午11:16:06
	 * @Modify: 2014年8月28日 上午11:16:06
	 * @param: payWay:支付通道编码， charset：字符集，payQueryUrl：请求URL，params：请求参数
	 * @return:
	 */
	@Override
	public Map<String, String> queryPaymentResult(String payWay,String charset, String payQueryUrl, Map<String, String> params)
			throws Exception {
		Map<String, String> returnMap = new HashMap<String, String>();
		// 数据库插入对象声明及初始化
		String contents = null;
		String formats = String.format("请求URL :%s，通道编码  ：%s， 字符集  ：%s，请求参数: %s。", payQueryUrl, payWay, charset, params);
		AipSvLogWithBLOBs tAipSvLogWithBLOBs = new AipSvLogWithBLOBs();
		Timestamp requestTime = new Timestamp(new Date().getTime());
		tAipSvLogWithBLOBs.setAppKey("OrderQueryService" + payWay);
		tAipSvLogWithBLOBs.setServiceName("OrderQueryService");
		tAipSvLogWithBLOBs.setRequestTime(requestTime);
		try {
			// 各参数均不能为空
			if (payWay == null || charset == null || payQueryUrl == null
					|| params == null) {
				contents = "支付通道编码payWay、字符集charset、请求payQueryUrl、或请求参数params为null";
				tAipSvLogWithBLOBs.setRequest(contents + formats);
				logger.info(contents);
				throw new Exception(contents);
			}
			logger.info("单笔订单查询开始！Begin to queryPaymentResult! ");
			contents = formats;
			logger.info(contents);
			// 数据库插入对象声明及初始化
			tAipSvLogWithBLOBs.setRequest(contents);
			try {
                returnMap = HttpRequestUtil.getResultData(payWay, payQueryUrl,charset, params);
//                returnMap = HttpRequestUtil.getResultData(payWay, payQueryUrl,charset, params);
				// 获取返回状态码
				String StatusCode = returnMap.get("StatusCode");
				// 获取原因短语
				String respResult = returnMap.get("respResult");
				logger.info("StatusCode= " + StatusCode + "，respResult="
						+ respResult);
				if (StatusCode != null) {
					if (StatusCode.equalsIgnoreCase("200")) {
						returnMap.put("flag", "0");
						returnMap.put("returnMsg", "success! StatusCode= "+ StatusCode);
					} else {
						returnMap.put("flag", "1");
						returnMap.put("returnMsg", "fail! StatusCode= "+ StatusCode + "，原因为：respResult="+ respResult);
					}
				} else {
					returnMap.put("flag", "1");
					returnMap.put("returnMsg", "fail! 获取返回状态失败，主机不存在或返回结果获取失败！");
					throw new Exception("获取返回状态失败，主机不存在或返回结果获取失败！");

				}
				// 将请求得到的结果复制到指定key值得value中
				returnMap.put("PaymentResult", returnMap.get("respResult"));
			} catch (Exception e) {
				logger.error("请求超时或异常", e);
				throw e;
			}
		} catch (Exception e) {
			logger.error("请求超时或异常", e);
			Writer result = new StringWriter();
			PrintWriter printWriter = new PrintWriter(result);
			e.printStackTrace(printWriter);
			// 将异常信息记录至日志表中
			returnMap.put("exception", result.toString());
			throw e;
		} finally {
			Timestamp responseTime = new Timestamp(new Date().getTime());
			tAipSvLogWithBLOBs.setResponseTime(responseTime);
			tAipSvLogWithBLOBs.setResponse(returnMap.toString());
//			aipSvLogDAO.insert(tAipSvLogWithBLOBs);
		}

		return returnMap;
	}

    /**
     * 扩展订单查询接口
     * @Title: queryPaymentResult
     * @Description:
     * @author: liangdl5
     * @Create: 2015年7月2日 上午11:11:27
     * @Modify: 2015年7月2日 上午11:11:27
     * @param:payWay:支付通道编码， charset：字符集，payQueryUrl：请求URL，params：请求参数
     * @param:features:其他特性参数
     * @return:
     * @throws Exception 
     */
    @Override
    public Map<String, String> queryPaymentResult(String payWay,
            String charset, String payQueryUrl, Map<String, String> params,
            Map<String, String> features) throws Exception {
        Map<String, String> returnMap = new HashMap<String, String>();
        // 数据库插入对象声明及初始化
        String contents = null;
        String formats = String.format("请求URL :%s，通道编码  ：%s， 字符集  ：%s，请求参数: %s。", payQueryUrl, payWay, charset, params);
        AipSvLogWithBLOBs tAipSvLogWithBLOBs = new AipSvLogWithBLOBs();
        Timestamp requestTime = new Timestamp(new Date().getTime());
        tAipSvLogWithBLOBs.setAppKey("OrderQueryService" + payWay);
        tAipSvLogWithBLOBs.setServiceName("OrderQueryService");
        tAipSvLogWithBLOBs.setRequestTime(requestTime);
        try {
            // 各参数均不能为空
            if (payWay == null || charset == null || payQueryUrl == null
                    || params == null) {
                contents = "支付通道编码payWay、字符集charset、请求payQueryUrl、或请求参数params为null";
                tAipSvLogWithBLOBs.setRequest(contents + formats);
                logger.info(contents);
                throw new Exception(contents);
            }
            logger.info("单笔订单查询开始！Begin to queryPaymentResult! ");
            contents = formats;
            logger.info(contents);
            // 数据库插入对象声明及初始化
            tAipSvLogWithBLOBs.setRequest(contents);
            try {
                returnMap = HttpRequestUtil.getResultData(payWay, payQueryUrl,charset, params, features);
                // 获取返回状态码
                String StatusCode = returnMap.get("StatusCode");
                // 获取原因短语
                String ReasonPhrase = returnMap.get("ReasonPhrase");
                logger.info("StatusCode= " + StatusCode + "，ReasonPhrase="
                        + ReasonPhrase);
                if (StatusCode != null) {
                    if (StatusCode.equalsIgnoreCase("200")) {
                        returnMap.put("flag", "0");
                        returnMap.put("returnMsg", "success! StatusCode= "+ StatusCode);
                    } else {
                        returnMap.put("flag", "1");
                        returnMap.put("returnMsg", "fail! StatusCode= "+ StatusCode + "，原因为：ReasonPhrase="+ ReasonPhrase);
                    }
                } else {
                    returnMap.put("flag", "1");
                    returnMap.put("returnMsg", "fail! 获取返回状态失败，主机不存在或返回结果获取失败！");
                    throw new Exception("获取返回状态失败，主机不存在或返回结果获取失败！");

                }
                // 将请求得到的结果复制到指定key值得value中
                returnMap.put("PaymentResult", returnMap.get("respResult"));
            } catch (Exception e) {
                logger.error("请求超时或异常", e);
                throw e;
            }
        } catch (Exception e) {
            logger.error("请求超时或异常", e);
            Writer result = new StringWriter();
            PrintWriter printWriter = new PrintWriter(result);
            e.printStackTrace(printWriter);
            // 将异常信息记录至日志表中
            returnMap.put("exception", result.toString());
            throw e;
        } finally {
            Timestamp responseTime = new Timestamp(new Date().getTime());
            tAipSvLogWithBLOBs.setResponseTime(responseTime);
            tAipSvLogWithBLOBs.setResponse(returnMap.toString());
//            aipSvLogDAO.insert(tAipSvLogWithBLOBs);
        }

        return returnMap;
    }
    
    @Override
    public Map<String, String> getqueryPaymentResult(String payWay, String charset,
            String payQueryUrl, Map<String, String> params) throws Exception {
        Map<String, String> returnMap = new HashMap<String, String>();
        // 数据库插入对象声明及初始化
        String contents = null;
        String formats = String.format("请求URL :%s，通道编码  ：%s， 字符集  ：%s，请求参数: %s。", payQueryUrl, payWay, charset, params);
        AipSvLogWithBLOBs tAipSvLogWithBLOBs = new AipSvLogWithBLOBs();
        Timestamp requestTime = new Timestamp(new Date().getTime());
        tAipSvLogWithBLOBs.setAppKey("OrderQueryService" + payWay);
        tAipSvLogWithBLOBs.setServiceName("OrderQueryService");
        tAipSvLogWithBLOBs.setRequestTime(requestTime);
        try {
            // 各参数均不能为空
            if (payWay == null || charset == null || payQueryUrl == null
                    || params == null) {
                contents = "支付通道编码payWay、字符集charset、请求payQueryUrl、或请求参数params为null";
                tAipSvLogWithBLOBs.setRequest(contents + formats);
                logger.info(contents);
                throw new Exception(contents);
            }
            logger.info("单笔订单查询开始！Begin to getqueryPaymentResult! ");
            contents = formats;
            logger.info(contents);
            // 数据库插入对象声明及初始化
            tAipSvLogWithBLOBs.setRequest(contents);
            try {
                returnMap = HttpRequestUtil.doGet(payWay, payQueryUrl, charset, params);
                // 获取返回状态码
                String StatusCode = returnMap.get("StatusCode");
                // 获取原因短语
                String ReasonPhrase = returnMap.get("ReasonPhrase");
                logger.info("StatusCode= " + StatusCode + "，ReasonPhrase="
                        + ReasonPhrase);
                if (StatusCode != null) {
                    if (StatusCode.equalsIgnoreCase("200")) {
                        returnMap.put("flag", "0");
                        returnMap.put("returnMsg", "success! StatusCode= "+ StatusCode);
                    } else {
                        returnMap.put("flag", "1");
                        returnMap.put("returnMsg", "fail! StatusCode= "+ StatusCode + "，原因为：ReasonPhrase="+ ReasonPhrase);
                    }
                } else {
                    returnMap.put("flag", "1");
                    returnMap.put("returnMsg", "fail! 获取返回状态失败，主机不存在或返回结果获取失败！");
                    throw new Exception("获取返回状态失败，主机不存在或返回结果获取失败！");

                }
                // 将请求得到的结果复制到指定key值得value中
                returnMap.put("PaymentResult", returnMap.get("respResult"));
            } catch (Exception e) {
                logger.error("请求超时或异常", e);
                throw e;
            }
        } catch (Exception e) {
            logger.error("请求超时或异常", e);
            Writer result = new StringWriter();
            PrintWriter printWriter = new PrintWriter(result);
            e.printStackTrace(printWriter);
            // 将异常信息记录至日志表中
            returnMap.put("exception", result.toString());
            throw e;
        } finally {
            Timestamp responseTime = new Timestamp(new Date().getTime());
            tAipSvLogWithBLOBs.setResponseTime(responseTime);
            tAipSvLogWithBLOBs.setResponse(returnMap.toString());
//            aipSvLogDAO.insert(tAipSvLogWithBLOBs);
        }

        return returnMap;
    }

	@Override
	public String postData2WeChat(String postUrl, Map<String, String> map)
			throws Exception {
        String xmlData;
        PrintWriter out = null;
        BufferedReader in = null;
        String content = "";
        try {
            xmlData = createXml(map);
            URL url = new URL(postUrl);
            //if("https".equalsIgnoreCase(url.getProtocol())){
            ignoreSsl();
            //}
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestProperty("Charsert", "UTF-8");
            out = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            out.print(xmlData);
            // flush输出流的缓冲
            LogUtil.info(module, "----------*weChat pay xmlDatal---没有乱码 end-------" + xmlData);
            out.flush();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                content += line;
            }
            LogUtil.info(module, "----------*weChat pay return 微信返回结果content---没有乱码-------" + content);
            LogUtil.info(module, "----------*weChat pay return content---没有乱码-------" + content);
            return content;
        } catch (Exception e) {
            LogUtil.error(module, "微信支付提交信息异常", e);
            throw new IOException(e);
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }

        }
    
	}
	
	
	@Override
    public String postDataWeChatRefund(String postUrl, Map<String, String> map,String fileId)
            throws Exception {
        String xmlData;
        String content = "";
        char [] mchId = String.valueOf(map.get("mch_id")).toCharArray();
        try {
            xmlData = createXml(map); 
            ignoreSsl();  
            /*--------4.读取证书文件,这一段是直接从微信支付平台提供的demo中copy的，所以一般不需要修改------*/
            //指定读取证书格式为PKCS12
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            //读取本机存放的PKCS12证书文件
            //FileInputStream instream = new FileInputStream(new File("F:\\apiclient_cert.p12"));
            InputStream instream = FileUtil.readFile2ips(fileId);
            try {
                //指定PKCS12的密码(商户ID)
                keyStore.load(instream, mchId);
            } catch (Exception e) {
                LogUtil.error(module, "微信退款读取证书异常", e);
                throw new IOException(e);
            }finally {
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
            LogUtil.error(module, "微信退款提交信息异常", e);
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

    //模拟浏览器GET提交
    private static HttpGet getGetMethod(String url) {
        HttpGet pmethod = new HttpGet(url);
        // 设置响应头信息
        pmethod.addHeader("Connection", "keep-alive");
        pmethod.addHeader("Cache-Control", "max-age=0");
        pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
        pmethod.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/;q=0.8");
        return pmethod;
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
            //	value = URLEncoder.encode(value,"UTF-8");
            //}
            sb.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
        }
        return sb.append("</XML>").toString();
    }	
    private static void ignoreSsl() throws Exception {
        HostnameVerifier hv = new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        };
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }    
    private static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }    
    static class miTM implements TrustManager, X509TrustManager {

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }

        public void checkClientTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }

    }
}
