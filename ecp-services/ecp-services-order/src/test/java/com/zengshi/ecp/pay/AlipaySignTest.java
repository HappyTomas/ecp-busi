package com.zengshi.ecp.pay;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.zengshi.ecp.order.dubbo.util.RSA;
import com.zengshi.ecp.order.service.busi.impl.pay.AlipayRequest;
import com.zengshi.paas.utils.CipherUtil;
import com.alibaba.fastjson.JSONObject;

public class AlipaySignTest{
	
	@Test
	public void testAppNotify() throws Exception {
		String signApp="UFkFL7mdmoiN87awz7XZY2pBlPs11VTecaCtIRJ3u2X9h8fAF0SBlFD3KD+3Na3F0xFfoptcaipz54lLXg1saQcZEg7V91wIs+emvU1DoVr3im6RGmU9VOYPCb1PxEbLAu+pso8d4hjXA6wJE5Yrioie+sPoPA8yyR1FtMeqbfE=";
		String publicApp="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDEiXG/NLuQvv+K4vYM8atZ59siYZbs8N/2EgVD VeBlYMYwOZOmlXQkquTeTD43pkf2exEbFYp+eObDOvrwXv/syoDE0PYoyLBGK/46w6NpEtMWzLXc sgC6TnJox0UbT0iGgZh+LjroL24BE0QbCeTEtlpIZykNbGsxCF4Yi7+pRwIDAQAB";
		Map<String, String> responseResultMap=new HashMap<String,String>();
		responseResultMap.put("buyer_id", "2088502917106940");
		responseResultMap.put("trade_no", "2016100821001004940233858297");	
		responseResultMap.put("use_coupon", "N");
		responseResultMap.put("body", "新鲜柠檬*1");
		responseResultMap.put("notify_time", "2016-10-08 17:53:44");
		responseResultMap.put("subject", "新鲜柠檬*1");
		responseResultMap.put("sign_type", "RSA");
		responseResultMap.put("is_total_fee_adjust", "Y");
		responseResultMap.put("notify_type", "trade_status_sync");
		responseResultMap.put("out_trade_no", "RW16100800003334");	
		responseResultMap.put("gmt_payment", "2016-10-08 17:53:44");
		responseResultMap.put("trade_status", "TRADE_SUCCESS");
		responseResultMap.put("discount", "0.00");	
		responseResultMap.put("buyer_email", "15659108767");
		responseResultMap.put("gmt_create", "2016-10-08 17:53:43");
		responseResultMap.put("price", "0.01");
		responseResultMap.put("total_fee", "0.01");
		responseResultMap.put("quantity", "1");
		responseResultMap.put("seller_id", "2088911144746843");
		responseResultMap.put("notify_id", "12ad1b1a2e5ce21e97f46f5d748cc72n96");
		responseResultMap.put("seller_email", "wowopay@163.com");
		responseResultMap.put("payment_type", "1");
	    //过滤空值、sign与sign_type参数
	    Map<String, String> sParaNew = AlipayRequest.paraFilter(responseResultMap);
	    String preSignStr = AlipayRequest.createLinkString(sParaNew);
	    System.out.println("............."+preSignStr);
		String[] signParams=new String[]{preSignStr,publicApp};
	    String text = signParams[0];
	    String key = signParams[1];
	    boolean flag=RSA.verify(text, signApp, key, "utf-8");
	    System.out.println("............."+flag);
	}
	
	@Test
	public void testAppNotify2() throws Exception {
		String signApp="dGk/oNJOMcw1qigpHS68r6GiGtg0SzGuzQUKFqHLImlw1VgWUJExJgAm64A5+A0oIQnY2A9hmCuWEBemDQN46Ewe6K/ajnxzFmwz8ALaK6Ge+dzVRaVZ+lO/WYtjCE2ezsFOKEuq+S6M4wzF0OUZhFLb3Ruuv4dsIhvoMVtzk/E=";
		String publicApp="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
		Map<String, String> responseResultMap=new HashMap<String,String>();
		responseResultMap.put("buyer_id", "2088702258211903");
		responseResultMap.put("trade_no", "2016071121001004900262107108");	
		responseResultMap.put("use_coupon", "N");
		responseResultMap.put("body", "null");
		responseResultMap.put("notify_time", "2016-07-11 10:08:43");
		responseResultMap.put("subject", "孕妇装夏季纯棉孕妇T恤短袖韩版2016宽松夏装上衣中长款打底衫潮*1");
		responseResultMap.put("sign_type", "RSA");
		responseResultMap.put("is_total_fee_adjust", "N");
		responseResultMap.put("notify_type", "trade_status_sync");
		responseResultMap.put("out_trade_no", "RW16071100002499");	
		responseResultMap.put("gmt_payment", "2016-07-11 10:08:43");
		responseResultMap.put("trade_status", "TRADE_SUCCESS");
		responseResultMap.put("discount", "0.00");	
		responseResultMap.put("buyer_email", "13763801100");
		responseResultMap.put("gmt_create", "2016-07-11 10:08:42");
		responseResultMap.put("price", "0.01");
		responseResultMap.put("total_fee", "0.01");
		responseResultMap.put("quantity", "1");
		responseResultMap.put("seller_id", "2088911144746843");
		responseResultMap.put("notify_id", "3f115b9a0153597068897783349f883my2");
		responseResultMap.put("seller_email", "wowopay@163.com");
		responseResultMap.put("payment_type", "1");
	    //过滤空值、sign与sign_type参数
	    Map<String, String> sParaNew = AlipayRequest.paraFilter(responseResultMap);
	    String preSignStr = AlipayRequest.createLinkString(sParaNew);
	    System.out.println("............."+preSignStr);
		String[] signParams=new String[]{preSignStr,publicApp};
	    String text = signParams[0];
	    String key = signParams[1];
	    boolean flag=RSA.verify(text, signApp, key, "utf-8");
	    System.out.println("............."+flag);
	}
	
	
	@Test
	public void testAppNotify3() throws Exception {
		String signApp="Im+z82dHZL8D9kcYarjiayAPHcmRa3AHWdJIYq6Ozrsj9qlNwAzMO5P85H/E3nKkrxn2CMVmF5M50K99FefCrHwXfwKiz/WwxD+WBmDi5ejPhSP8sg8VWIJWF0lD7h5PBTAakuaA/eUFEwG4LB+++hmDeWr/3qG+WoCOJCv5p4k=";
		String publicApp="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
		//String publicApp="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAl0KgAOiFT2iyCYIk2iDp5eKtQIZB4Tk1HYKC9MVLVQVN/YGQHeMwjcRFik+shoBkZpbsvowcwS1xl+Qq8l6ZWjPdWLSTNJzB3GLKXRFFQklSfd+bd9qnnP4Rq8hTdIJmRvu/Odte+qnk3+VFdDizTQ6SB0CQIqTmJgOC/0Tn8wIDAQAB";
		//String publicApp = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQClLOJZzJ9JFbmidelaQHNlZIyQ73qhYfhfByfX9+vFYEd+TAOcxxaoP43QesQ/34n3wFXmlHxXWuhbsU0+yaBKTYf3sBvGTugrmVp03o8sfIfyvcgDEas6FTQS9ff+gHW2Z+/9RhQw1vAA5OFDJaFCAsC5hbxZHXaSBQCNye9PSQIDAQAB";
		//String publicApp = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQClLOJZzJ9JFbmidelaQHNlZIyQ73qhYfhfByfX9+vFYEd+TAOcxxaoP43QesQ/34n3wFXmlHxXWuhbsU0+yaBKTYf3sBvGTugrmVp03o8sfIfyvcgDEas6FTQS9ff+gHW2Z+/9RhQw1vAA5OFDJaFCAsC5hbxZHXaSBQCNye9PSQIDAQAB";
		//String publicApp = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
		Map<String, String> responseResultMap=new HashMap<String,String>();
		responseResultMap.put("buyer_id", "2088702258211903");
		responseResultMap.put("trade_no", "2016070821001004900264682978");	
		responseResultMap.put("use_coupon", "N");
		responseResultMap.put("body", "null");
		responseResultMap.put("notify_time", "2016-07-08 09:28:40");
		responseResultMap.put("subject", "奥克斯电熨斗YD2029A家用手持蒸汽熨斗干湿两熨迷你电烫斗*1");
		responseResultMap.put("sign_type", "RSA");
		responseResultMap.put("is_total_fee_adjust", "N");
		responseResultMap.put("notify_type", "trade_status_sync");
		responseResultMap.put("out_trade_no", "RW16070800002469");	
		responseResultMap.put("gmt_payment", "2016-07-08 09:04:00");
		responseResultMap.put("trade_status", "TRADE_SUCCESS");
		responseResultMap.put("discount", "0.00");	
		responseResultMap.put("buyer_email", "13763801100");
		responseResultMap.put("gmt_create", "2016-07-08 09:03:58");
		responseResultMap.put("price", "0.01");
		responseResultMap.put("total_fee", "0.01");
		responseResultMap.put("quantity", "1");
		responseResultMap.put("seller_id", "2088911144746843");
		responseResultMap.put("notify_id", "2fad0b150bdea845dedf7b5b87189c9my2");
		responseResultMap.put("seller_email", "wowopay@163.com");
		responseResultMap.put("payment_type", "1");
		
		 //过滤空值、sign与sign_type参数
	    Map<String, String> sParaNew = AlipayRequest.paraFilter(responseResultMap);
	    String preSignStr = AlipayRequest.createLinkString(sParaNew);
	    System.out.println("............."+preSignStr);
	    boolean flag=RSA.verify(preSignStr, signApp, publicApp, "utf-8");
	    System.out.println("............."+flag);

	    

	}
	
	 /*** 
     * 读取*.cer公钥证书文件， 获取公钥证书信息 
     * @author xgh 
     */
	@Test
    public void testReadX509CerFile() throws Exception{
		
		File file = new File("e:\\aipay_public.cer");  
        InputStream in = new FileInputStream(file);  
		   CertificateFactory cf = CertificateFactory.getInstance("X.509");
		   X509Certificate clientCert = (X509Certificate)cf.generateCertificate(in);
		   RSAPublicKey pk = (RSAPublicKey) clientCert.getPublicKey();
		   sun.security.rsa.RSAPublicKeyImpl ppk = (sun.security.rsa.RSAPublicKeyImpl)pk;
		   /*System.out.println(ppk.getModulus());
		   System.out.println(ppk.getPublicExponent());//65537
		   System.out.println(ppk.getAlgorithm());
		   System.out.println(ppk.getFormat());
		   System.out.println(ppk.getAlgorithmId());
		   System.out.println(ppk.getModulus().toString(16));
		   System.out.println(ppk.getPublicExponent().toString(16));//10001
		   System.out.println(new BigInteger(ppk.getEncoded()).toString(16));*/
		   
		   System.out.println(ppk.getModulus().toString(16));
		   
        /*try {  
            // 读取证书文件  
  
            File file = new File("e:\\aipay_public.cer");  
            InputStream inStream = new FileInputStream(file);  
            // 创建X509工厂类  
            CertificateFactory cf = CertificateFactory.getInstance("X.509");  
            //CertificateFactory cf = CertificateFactory.getInstance("X509");  
            // 创建证书对象  
            X509Certificate oCert = (X509Certificate) cf  
                    .generateCertificate(inStream);  
            inStream.close();  
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");  
            String info = null;  
            // 获得证书版本  
            info = String.valueOf(oCert.getVersion());  
            System.out.println("证书版本:" + info);  
            // 获得证书序列号  
            info = oCert.getSerialNumber().toString(16);  
            System.out.println("证书序列号:" + info);  
            // 获得证书有效期  
            Date beforedate = oCert.getNotBefore();  
            info = dateformat.format(beforedate);  
            System.out.println("证书生效日期:" + info);  
            Date afterdate = oCert.getNotAfter();  
            info = dateformat.format(afterdate);  
            System.out.println("证书失效日期:" + info);  
            // 获得证书主体信息  
            info = oCert.getSubjectDN().getName();  
            System.out.println("证书拥有者:" + info);  
            // 获得证书颁发者信息  
            info = oCert.getIssuerDN().getName();  
            System.out.println("证书颁发者:" + info);  
            // 获得证书签名算法名称  
            info = oCert.getSigAlgName();  
            System.out.println("证书签名算法:" + info);  
            // 获得证书签名公钥
            System.out.println("证书签名公钥:" + getKeyString(oCert.getPublicKey()));  
  
        } catch (Exception e) {  
            System.out.println("解析证书出错！");  
            e.printStackTrace();  
        }  */
    }
	
	 /**
     * 得到密钥字符串（经过base64编码）
     * @return
     */
    public static String getKeyString(Key key) throws Exception {
          byte[] keyBytes = key.getEncoded();
          String s = "";//(new BASE64Encoder()).encode(keyBytes);
          return s;
    }
    
    @Test
    public void testXml() throws Exception{
    	Map<String, String> result = new HashMap<String, String>();
    	result.put("xml", "<?xml version=\"1.0\" encoding=\"UTF-8\"?><AIPAYTRADE>100001</PARTNER_ID></xml>");
    	System.out.println(JSONObject.toJSONString(result));
    }
	
	public static void main(String[] args) {
		System.out.println(CipherUtil.decrypt("416892054ac19c6578c95d5d1dfc7ec2d51c8ddfa60ba0ca"));
	}


}
