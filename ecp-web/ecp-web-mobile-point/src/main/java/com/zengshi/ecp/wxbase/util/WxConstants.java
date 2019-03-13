package com.zengshi.ecp.wxbase.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 
 * 微信常量
 * 
 * Title:  <br>
 * project:lacemall-web <br>
 * Date:2016年5月7日<br>
 * @author wangbh
 */
public class WxConstants {
	
	
	private static Logger log = LoggerFactory.getLogger(WxConstants.class.getName());

	public static int UPLOAD_MAX_SIZE = 52428800;
	public static String LACE_WX_URL;
	public static String ACCESS_TOKEN_URL;
	public static String APPID;
	public static String SECRET;
	//oauth2.0
	public static String USER_ACCESS_TOKEN;
	//验证时基本参数的key，cache用
	public static String ECP_WEIX;
	
	/**
	 * 系统版本 1为国内版  2为海外版
	 */
	public static String VERSION;
	static {
		Properties prop = new Properties();
		InputStream in = WxConstants.class.getResourceAsStream("/wx.properties");
		try {
			prop.load(in);
			WxConstants.LACE_WX_URL = prop.getProperty("lace_wx_url");
			WxConstants.ACCESS_TOKEN_URL = prop.getProperty("access_token");
			WxConstants.APPID = prop.getProperty("appid");
			WxConstants.SECRET = prop.getProperty("secret");
			WxConstants.USER_ACCESS_TOKEN = "USER_ACCESS_TOKEN";
			WxConstants.ECP_WEIX = "ECP_WEIX";
		} catch (IOException e) {
			log.error("init lace.properties failed!!", e);
		}
	}

}
