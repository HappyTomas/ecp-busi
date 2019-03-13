package com.zengshi.aip.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: HttpClientUtil.java
 * @Package: com.zengshi.aip.util
 * @Description: HTTP请求实现类
 * @Comment:
 * @author: luocan
 * @CreateDate: 2014年8月28日 上午11:27:20
 */
@SuppressWarnings("deprecation")
public class HttpRequestUtil {
	private final static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

	/**
	 * @Title: getResultData
	 * @Description:模拟post请求
	 * @author: luocan
	 * @Create: 2014年8月28日 下午2:24:36
	 * @Modify: 2014年8月28日 下午2:24:36
	 * @param:idCode:身份识别， 用来识别被请求方， url：请求url，  charset:请求字符集， dataMap：请求参数
	 * @return:
	 * @throws IOException 
	 */
	public static Map<String, String> getResultData(String idCode, String url, String charset, Map<String, String> dataMap) throws IOException {
		String contents =String.format("请求URL :%s，请求参数: %s。", url, dataMap);
		logger.info(contents);
		Map<String, String> resultMap = new HashMap<String, String>();
		String result = "";
		HttpClient httpclient = new DefaultHttpClient();
		//http请求和https请求判断
		String header=url.substring(0, 5);
		if (header.equalsIgnoreCase("https")) {
			httpclient = getHttpsClient();
		} 
		// 请求超时1分钟
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000); 
		// 读取超时1分钟
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
		//url空格及其他特殊字符转换
//		url = url.replaceAll("\\?", "%3F");
//		url = url.replaceAll("&", "%26");
		url = url.replaceAll(" ", "%20");
		HttpPost httpPost = new HttpPost(url);
		//组织参数
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			if (dataMap != null && !dataMap.isEmpty()) {
				for (String key : dataMap.keySet()) {
					logger.info(key + ":" + dataMap.get(key));
					params.add(new BasicNameValuePair(key, dataMap.get(key)));
				}
			}
			logger.info("----params-----：" + JSON.toJSONString(params));
			httpPost.setEntity(new UrlEncodedFormEntity(params, charset));
//			httpPost.setEntity(new StringEntity(dataMap.get("requestPacket"), ContentType.create("text/xml", Consts.UTF_8)));
			logger.info("----httpPost-----：" + JSON.toJSONString(httpPost));
			HttpResponse response = httpclient.execute(httpPost);
			// 获取返回的StatusLine
			StatusLine StatusLine = response.getStatusLine();
			logger.info("响应状态：" + StatusLine);
			//resultMap.put("ProtocolVersion", response.getProtocolVersion().toString());// 协议版本
			resultMap.put("StatusCode",String.valueOf(StatusLine.getStatusCode()));// 返回状态码
			resultMap.put("ReasonPhrase", StatusLine.getReasonPhrase());// 返回原因短语
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, charset);
			resultMap.put("respResult", result);
			logger.info("响应结果： " + result);
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
			httpPost.releaseConnection();
		}
		return resultMap;
	}
	/**
	 * @Title: getResultData
	 * @Description:模拟post请求 鸿支付请求参数放在Params中
	 * @author:
	 * @Create:
	 * @Modify:
	 * @param:idCode:身份识别， 用来识别被请求方， url：请求url，  charset:请求字符集， dataMap：请求参数
	 * @return:
	 * @throws IOException
	 */
	public static Map<String, String> getHongResultData(String idCode, String url, String charset, Map<String, String> dataMap) throws IOException {
		String contents =String.format("请求URL :%s，请求参数: %s。", url, dataMap);
		logger.info(contents);
		Map<String, String> resultMap = new HashMap<String, String>();
		String result = "";
		HttpClient httpclient = new DefaultHttpClient();
		//http请求和https请求判断
		String header=url.substring(0, 5);
		if (header.equalsIgnoreCase("https")) {
			httpclient = getHttpsClient();
		}
		// 请求超时1分钟
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		// 读取超时1分钟
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
		//url空格及其他特殊字符转换
//		url = url.replaceAll("\\?", "%3F");
//		url = url.replaceAll("&", "%26");
		url = url.replaceAll(" ", "%20");
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
		httpclient.getParams().setParameter("http.protocol.content-charset",HTTP.UTF_8);
		httpclient.getParams().setParameter("Content-Encoding",HTTP.UTF_8);
		httpclient.getParams().setParameter("; charset=", HTTP.UTF_8);
		httpclient.getParams().setParameter("US-ASCII", HTTP.UTF_8);
		Protocol myhttps = new Protocol("https", new MySecureProtocolSocketFactory (), 443);
//MySecureProtocolSocketFactory请参考附录A
		Protocol.registerProtocol("https", myhttps);

		//组织参数
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			if (dataMap != null && !dataMap.isEmpty()) {
				for (String key : dataMap.keySet()) {
					logger.info(key + ":" + dataMap.get(key));
					params.add(new BasicNameValuePair(key, dataMap.get(key)));
				}
			}
			logger.info("----params-----：" + JSON.toJSONString(params));
			httpPost.setEntity(new UrlEncodedFormEntity(params, charset));
//			httpPost.setEntity(new StringEntity(dataMap.get("requestPacket"), ContentType.create("text/xml", Consts.UTF_8)));
			logger.info("----httpPost-----：" + JSON.toJSONString(httpPost));
			HttpResponse response = httpclient.execute(httpPost);
			// 获取返回的StatusLine
			StatusLine StatusLine = response.getStatusLine();
			logger.info("响应状态：" + StatusLine);
			//resultMap.put("ProtocolVersion", response.getProtocolVersion().toString());// 协议版本
			resultMap.put("StatusCode",String.valueOf(StatusLine.getStatusCode()));// 返回状态码
			resultMap.put("ReasonPhrase", StatusLine.getReasonPhrase());// 返回原因短语
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, charset);
			resultMap.put("respResult", result);
			logger.info("响应结果： " + result);
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
			httpPost.releaseConnection();
		}
		return resultMap;
	}
	/**
     * @Title: getResultData
     * @Description:模拟post请求
     * @author: liangdl5
     * @Create: 2015年6月28日 下午2:24:36
     * @Modify: 2015年6月28日 下午2:24:36
     * @param:idCode:身份识别， 用来识别被请求方， url：请求url，  charset:请求字符集， dataMap：请求参数
     * @param:features 其他特性参数
     * @return:
     * @throws IOException 
     */
    public static Map<String, String> getResultData(String idCode, String url, String charset, 
            Map<String, String> dataMap, Map<String, String> features) throws IOException {
        String contents =String.format("请求URL :%s，请求参数: %s。", url, dataMap);
        logger.info(contents);
        Map<String, String> resultMap = new HashMap<String, String>();
        String result = "";
        HttpClient httpclient = new DefaultHttpClient();
        //http请求和https请求判断
        String header=url.substring(0, 5);
        if (header.equalsIgnoreCase("https")) {
            httpclient = getHttpsClient();
        } 
        int CONNECTION_TIMEOUT = 60000; // 请求超时1分钟
        int SO_TIMEOUT = 60000; // 读取超时1分钟
        if (features != null && features.get("CONNECTION_TIMEOUT") != null) {
            CONNECTION_TIMEOUT = Integer.parseInt(features.get("CONNECTION_TIMEOUT").toString());
            logger.info("HTTP请求超时时间CONNECTION_TIMEOUT被设置为：" + CONNECTION_TIMEOUT);
        }
        if (features != null && features.get("SO_TIMEOUT") != null) {
            SO_TIMEOUT = Integer.parseInt(features.get("SO_TIMEOUT").toString());
            logger.info("HTTP读取超时时间SO_TIMEOUT被设置为：" + SO_TIMEOUT);
        }
        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECTION_TIMEOUT); 
        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);
        //url空格及其他特殊字符转换
//        url = url.replaceAll("\\?", "%3F");
//        url = url.replaceAll("&", "%26");
        url = url.replaceAll(" ", "%20");
        HttpPost httpPost = new HttpPost(url);
        //组织参数
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            if (dataMap != null && !dataMap.isEmpty()) {
                for (String key : dataMap.keySet()) {
                    logger.info(key + ":" + dataMap.get(key));
                    params.add(new BasicNameValuePair(key, dataMap.get(key)));
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params, charset));
            HttpResponse response = httpclient.execute(httpPost);
            // 获取返回的StatusLine
            StatusLine StatusLine = response.getStatusLine();
            logger.info("响应状态：" + StatusLine);
            //resultMap.put("ProtocolVersion", response.getProtocolVersion().toString());// 协议版本
            resultMap.put("StatusCode",String.valueOf(StatusLine.getStatusCode()));// 返回状态码
            resultMap.put("ReasonPhrase", StatusLine.getReasonPhrase());// 返回原因短语
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, charset);
            resultMap.put("respResult", result);
            logger.info("响应结果： " + result);
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
            httpPost.releaseConnection();
        }
        return resultMap;
    }


	public static String createXml(Map<String, String> params)throws UnsupportedEncodingException {
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


	public static Map<String, String> doXmlPost(String url, String charset, Map<String, String> dataMap) throws Exception{
		String contents =String.format("请求URL :%s，请求参数: %s。", url, dataMap);
		logger.info(contents);
		Map<String, String> resultMap = new HashMap<String, String>();
		String result = "";
		HttpClient httpclient = new DefaultHttpClient();
		//http请求和https请求判断
		String header=url.substring(0, 5);
		if (header.equalsIgnoreCase("https")) {
			httpclient = getHttpsClient();
		}
		// 请求超时1分钟
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		// 读取超时1分钟
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
		//url空格及其他特殊字符转换，get请求不需要对?及&符号进行转换
		url = url.replaceAll(" ", "%20");
		HttpPost post = new HttpPost(url);

		String xmlData = "";
		try{
			xmlData = createXml(dataMap);
			// Construct a string entity
			StringEntity entity = new StringEntity(xmlData);
			// Set XML entity
			post.setEntity(entity);
			// Set content type of request header
			post.setHeader("Content-Type", "text/xml;charset="+charset);
			// Execute request and get the response
			HttpResponse response = httpclient.execute(post);
			// Response Header - StatusLine - status code
			StatusLine StatusLine = response.getStatusLine();
			logger.info("响应状态：" + StatusLine);
			//resultMap.put("ProtocolVersion", response.getProtocolVersion().toString());// 协议版本
			resultMap.put("StatusCode",String.valueOf(StatusLine.getStatusCode()));// 返回状态码
			resultMap.put("ReasonPhrase", StatusLine.getReasonPhrase());// 返回原因短语
			result = EntityUtils.toString(entity);
			resultMap.put("respResult", result);
			logger.info("响应结果： " + result);
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

	/**
	 * @Title: doGet
	 * @Description:模拟get请求
	 * @author: luocan
	 * @Create: 2014年11月29日 下午3:12:21
	 * @Modify: 2014年11月29日 下午3:12:21
	 * @param:
	 * @return:
	 */
	public static Map<String, String> doGet(String idCode, String url, String charset, Map<String, String> dataMap) throws Exception {
		String contents =String.format("请求URL :%s，请求参数: %s。", url, dataMap);
		logger.info(contents);
		Map<String, String> resultMap = new HashMap<String, String>();
		String result = "";
		HttpClient httpclient = new DefaultHttpClient();
		//http请求和https请求判断
		String header=url.substring(0, 5);
		if (header.equalsIgnoreCase("https")) {
			httpclient = getHttpsClient();
		}  
		// 请求超时1分钟
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000); 
		// 读取超时1分钟
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
		//url空格及其他特殊字符转换，get请求不需要对?及&符号进行转换
		url = url.replaceAll(" ", "%20");
		HttpGet gets=new HttpGet(url);
		//组织参数
		try {
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			if (dataMap != null && !dataMap.isEmpty()) {
				for (String key : dataMap.keySet()) {
					logger.info(key + ":" + dataMap.get(key));
					params.add(new BasicNameValuePair(key, dataMap.get(key)));
				}
			}
			
	          String str = EntityUtils.toString(new UrlEncodedFormEntity(params, charset));  
	          gets.setURI(new URI(gets.getURI().toString() + "?" + str));  
	            // 发送请求 
			HttpResponse response = httpclient.execute(gets);
			// 获取返回的StatusLine
			StatusLine StatusLine = response.getStatusLine();
			logger.info("响应状态：" + StatusLine);
			//resultMap.put("ProtocolVersion", response.getProtocolVersion().toString());// 协议版本
			resultMap.put("StatusCode",String.valueOf(StatusLine.getStatusCode()));// 返回状态码
			resultMap.put("ReasonPhrase", StatusLine.getReasonPhrase());// 返回原因短语
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
			resultMap.put("respResult", result);
			logger.info("响应结果： " + result);
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
			gets.releaseConnection();
		}
		return resultMap;
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
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
				public X509Certificate[] getAcceptedIssuers() { return null; }
			};
			DefaultHttpClient client = new DefaultHttpClient();
			ctx.init(null, new TrustManager[] { tm }, null);
			//SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER为必须，否则报错
			SSLSocketFactory ssf = new SSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = client.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			// 设置要使用的端口，默认是443
			sr.register(new Scheme("https", 443, ssf));
			return client;
		} catch (Exception ex) {
			return null;
		}
	}
	/**
	 * @Title: getHttpsClient
	 * @Description:https请求配置（二），留待备用
	 * @author: luocan
	 * @Create: 2014年9月2日 下午9:56:53
	 * @Modify: 2014年9月2日 下午9:56:53
	 * @param:
	 * @return:
	 */
    public static HttpClient wrapClient(HttpClient base) {
        try {
        	//TLS是SSL的继承者
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("https", 443, ssf));
            ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
            return new DefaultHttpClient(mgr, base.getParams());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
	/**
	 * @Title: getDocument
	 * @Description:将文件流转化为Document
	 * @author: luocan
	 * @Create: 2014年8月20日 上午11:05:06
	 * @Modify: 2014年8月20日 上午11:05:06
	 * @param:
	 * @return:
	 */
	/*public Document getDocument(InputStream inputStream) {
		Document tDocument = null;
		SAXReader tSAXReader = new SAXReader();
		BufferedInputStream tBufferedInputStream = new BufferedInputStream(
				inputStream);
		try {
			tDocument = tSAXReader.read(tBufferedInputStream);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return tDocument;
	}*/

	/**
	 * @Title: main
	 * @Description:
	 * @author: luocan
	 * @Create: 2014年8月28日 上午11:27:20
	 * @Modify: 2014年8月28日 上午11:27:20
	 * @param:
	 * @return:
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		//测试post请求
/*		Map<String, String> params = new HashMap<String,String>();
		//params.put("reqWofinance", "<?xml version=\"1.0\" encoding=\"UTF-8\"?><AIPAYTRADE><INFO><PARTNER_ID>100001</PARTNER_ID><SIGNED_MSG>a16c7f44ad9559bb4e436a7d63c77b3c875716b26671bce184c54898dc0bcc5919549fc03caff8eef32b62b8ffae9785b450fd5394f0b4ced697e2c3cc1c517a9d46132241df44b51657cc8739fe3ba3bd5068a8996d7b1e6e3d2a00d8e3127fedd66635829b9a12cd8e15d55a815a8c35c9cbbc1ce6950d7dfc102e0aa8970973ae19762144d2ed52449b8fcc10046b029db83c72761a5326c7ed4d71e9286a17f2e41d87ec4faeeacb47e77da739f56084111497206fa1a134fb362ef3418a58c5bf20f8b4a020696f41b0fba5666a65a77b188f2ec9ca996933c8a0a38976dff211c352d0f067ee4c9c3ab24ed45a2714c1b3462a18bf25ec99f558c73844</SIGNED_MSG></INFO><BODY><PARTNER_ACCT_ID>59a4332</PARTNER_ACCT_ID><BANK_NO>105</BANK_NO><CARD_NO>6259650052010912</CARD_NO><CARD_NAME>麦联叨</CARD_NAME><BANK_PHONE>18601179629</BANK_PHONE><BIND_FLAG>1</BIND_FLAG><BIND_TIME>2014-09-06 21:20:10</BIND_TIME><ACCT_RESERVED_FIELD1>59</ACCT_RESERVED_FIELD1><ACCT_RESERVED_FIELD2></ACCT_RESERVED_FIELD2><ACCT_RESERVED_FIELD3></ACCT_RESERVED_FIELD3></BODY></AIPAYTRADE>");
		params.put("reqWofinance", "<?xml version=\"1.0\" encoding=\"UTF-8\"?><tdgService><reqHeader><serviceId>ezves.ts0001</serviceId><systemId>901</systemId><traceDate>20140924</traceDate><traceTime>110745</traceTime><traceSerial>901201409241107</traceSerial></reqHeader><reqBody><msgType>0100</msgType><cusType>01</cusType><tranCode>16100001</tranCode><launcherId>901</launcherId><reqDate>20140924</reqDate><reqTime>110745</reqTime><resSysId></resSysId><resDate></resDate><resTime></resTime><reqTraceNo>901201409241107</reqTraceNo><resTraceNo></resTraceNo><retCode>0000000</retCode><errorMsg></errorMsg><reserveField></reserveField><platSeqNo>90120140924110701</platSeqNo><tranType>01</tranType><cbiCusName>黄琼</cbiCusName><cbiCerty>15</cbiCerty><cbiCerno>431321199901230035</cbiCerno><frName>琼琼</frName><frId>431321199901230035</frId><linkName>琼</linkName><linkhmTel>18559948882</linkhmTel><cusAddr>南宁市联通大厦</cusAddr><linkEmail>332607384@qq.com</linkEmail><branch>104</branch><loanAmt>0000000666666.88</loanAmt><cusNote>申请中行贷款</cusNote><qdNums>1</qdNums><appChanNo>59a2388</appChanNo><naZoneCode>450100</naZoneCode><reqList><chanNo>59a2388</chanNo><ownName>黄琼</ownName><licId>A123456789</licId><licType>0</licType><agntAddr>民族大道附近</agntAddr><frdbName>琼小三</frdbName><frdbId>431321199901230035</frdbId><agntName>南宁市特殊渠道测试申请名称</agntName><chaZoneCode>450103</chaZoneCode><crtTime>20140112</crtTime><cucLimit>0000000888888</cucLimit><yjkhh>中国银行</yjkhh><yjNm>琼小三</yjNm><yjAcc>6229012345676890</yjAcc><qdjlNm>小三</qdjlNm><qdjlTel>15980150285</qdjlTel><commNum>1</commNum><comms><comType>01</comType><commth>201409</commth><comAmt>0000000050000</comAmt></comms><orderNum>1</orderNum><orders><ordType>101</ordType><ordmth>201409</ordmth><ordCnt>30</ordCnt><ordAmt>0000000049876</ordAmt></orders><mxjg>555555</mxjg><pmzb>66</pmzb><sixyjyc>0000000777777</sixyjyc><tewyjyc>0000099999999</tewyjyc></reqList></reqBody></tdgService>");
		System.out.println(getResultData("104","http://121.31.41.51:14421/route/yxpaynotify","utf-8",params));
*/
		
		//测试get请求
//		Map<String, String> params1 = new HashMap<String,String>();
		//params.put("reqWofinance", "<?xml version=\"1.0\" encoding=\"UTF-8\"?><AIPAYTRADE><INFO><PARTNER_ID>100001</PARTNER_ID><SIGNED_MSG>a16c7f44ad9559bb4e436a7d63c77b3c875716b26671bce184c54898dc0bcc5919549fc03caff8eef32b62b8ffae9785b450fd5394f0b4ced697e2c3cc1c517a9d46132241df44b51657cc8739fe3ba3bd5068a8996d7b1e6e3d2a00d8e3127fedd66635829b9a12cd8e15d55a815a8c35c9cbbc1ce6950d7dfc102e0aa8970973ae19762144d2ed52449b8fcc10046b029db83c72761a5326c7ed4d71e9286a17f2e41d87ec4faeeacb47e77da739f56084111497206fa1a134fb362ef3418a58c5bf20f8b4a020696f41b0fba5666a65a77b188f2ec9ca996933c8a0a38976dff211c352d0f067ee4c9c3ab24ed45a2714c1b3462a18bf25ec99f558c73844</SIGNED_MSG></INFO><BODY><PARTNER_ACCT_ID>59a4332</PARTNER_ACCT_ID><BANK_NO>105</BANK_NO><CARD_NO>6259650052010912</CARD_NO><CARD_NAME>麦联叨</CARD_NAME><BANK_PHONE>18601179629</BANK_PHONE><BIND_FLAG>1</BIND_FLAG><BIND_TIME>2014-09-06 21:20:10</BIND_TIME><ACCT_RESERVED_FIELD1>59</ACCT_RESERVED_FIELD1><ACCT_RESERVED_FIELD2></ACCT_RESERVED_FIELD2><ACCT_RESERVED_FIELD3></ACCT_RESERVED_FIELD3></BODY></AIPAYTRADE>");
//		params1.put("access_token", "1313");
//		System.out.println(doGet("104","https://api.weixin.qq.com/cgi-bin/menu/get","utf-8",params1));
		
	    Map<String, String> request = new HashMap<String,String>();
		request.put("sign", "058c937e44e69fa4247ae8d9886afc36");
		request.put("page_size", "1");
		request.put("_input_charset", "UTF-8");
		request.put("gmt_end_time", "2015-12-03 23:59:59");
        request.put("sign_type", "MD5");
        request.put("service", "account.page.query");
        request.put("partner", "2088121127353472");
        request.put("page_no", "1");
//        request.put("gmt_start_time", "2015-12-03 00:00:00");
        Map<String, String> result = HttpRequestUtil.getResultData("9003", "https://mapi.alipay.com/gateway.do?_input_charset=UTF-8&gmt_start_time=2015-12-03 00:00:00", "UTF-8", request);
        System.out.println(result);
	}

}
