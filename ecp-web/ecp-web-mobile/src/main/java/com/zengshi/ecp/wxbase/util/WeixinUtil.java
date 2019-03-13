package com.zengshi.ecp.wxbase.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.zengshi.ecp.sys.dubbo.dto.WeixMenuReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.WeixMenuResDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IWeixMenuRSV;
import com.zengshi.ecp.system.util.ConstantTool;
import com.zengshi.ecp.wxbase.pojo.AccessToken;
import com.zengshi.ecp.wxbase.pojo.Button;
import com.zengshi.ecp.wxbase.pojo.CommonButton;
import com.zengshi.ecp.wxbase.pojo.ComplexButton;
import com.zengshi.ecp.wxbase.pojo.Menu;
import com.zengshi.ecp.wxbase.pojo.ViewButton;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.StringUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 公众平台通用接口工具类
 * 
 * @author wangbh
 * 
 */
public class WeixinUtil {

	private static final String MODULE = WeixinUtil.class.getName();

	// 获取access_token的接口地址（GET） 限200（次/天）
	public static String access_token_url;

	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	public static String access_menu_del_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	public static String jsapi_ticket = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	public static String getAppid() {
		return appid;
	}

	public static void setAppid(String appid) {
		WeixinUtil.appid = appid;
	}

	public static String getSecret() {
		return secret;
	}

	public static void setSecret(String secret) {
		WeixinUtil.secret = secret;
	}

	public static String appid = "";

	public static String secret = "";

	public String getAccess_token_url() {
		return access_token_url;
	}

	public void setAccess_token_url(String access_token_url) {
		WeixinUtil.access_token_url = access_token_url;
	}

	
    private static IWeixMenuRSV iWeixMenuRSV;
    
    @Resource
    public void setiWeixMenuRSV(IWeixMenuRSV iWeixMenuRSV){
    	WeixinUtil.iWeixMenuRSV = iWeixMenuRSV;
    }
	/*
	 * private static IWeixMenuSV iWeixMenuSV;
	 * 
	 * @Resource(name="iWeixMenuSV") public void setiWeixMenuSV(IWeixMenuSV
	 * iWeixMenuSV) { WeixinUtil.iWeixMenuSV = iWeixMenuSV; }
	 */

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getAccessToken() {
		AccessToken accessToken = null;
		JSONObject jsonObject = null;
		jsonObject = (JSONObject) CacheUtil.getItem("ECP_ACCESS_TOKEN");
		// jsonObject = (JSONObject) getCache("ECP_ACCESS_TOKEN",7200);
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				e.printStackTrace();
				System.out.println("获取token失败 errcode:{} errmsg:{}"
						+ jsonObject.getInt("errcode") + ":"
						+ jsonObject.getString("errmsg"));
				// 获取token失败
			}
		} else {
			jsonObject = getToken();
			// 获取的token放到缓存中，设置时长
			// addCache("ECP_ACCESS_TOKEN", jsonObject, 7200);
			CacheUtil.addItem("ECP_ACCESS_TOKEN", jsonObject, 7200);
			if (null != jsonObject) {
				try {
					accessToken = new AccessToken();
					accessToken.setToken(jsonObject.getString("access_token"));
					accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				} catch (JSONException e) {
					accessToken = null;
					e.printStackTrace();
					System.out.println("获取token失败 errcode:{} errmsg:{}"
							+ jsonObject.getInt("errcode") + ":"
							+ jsonObject.getString("errmsg"));
					// 获取token失败
				}
			}

		}
		return accessToken;
	}

	public static JSONObject getToken() {
		JSONObject jsonObject = null;
		String requestUrl = access_token_url.replace("APPID", appid).replace(
				"APPSECRET", secret);
		jsonObject = httpRequest(requestUrl, "GET", null);
		return jsonObject;
	}

	/**
	 * 缓存新增
	 * 
	 * @param key
	 * @param value
	 */
	public static void addCache(String key, Object object, int value) {

		Cache<Object, Object> cache = CacheBuilder.newBuilder()
				.maximumSize(1000).expireAfterWrite(value, TimeUnit.SECONDS)
				.build(); // look Ma, no CacheLoader
		cache.put(key, object);
	}

	public static Object getCache(String key, int value) {
		Cache<Object, Object> cache = CacheBuilder.newBuilder()
				.maximumSize(1000).expireAfterWrite(value, TimeUnit.SECONDS)
				.build(); // look Ma, no CacheLoadere
		Object object = new Object();
		try {
			object = cache.get(key, new Callable<Object>() {
				@Override
				public Object call() {
					return getToken();
				}
			});
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @param accessToken
	 *            有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(String accessToken) {
		int result = 0;
		Menu menu = new Menu();

		menu = getMenu();
		/*
		 * try { menu = iWeixMenuSV.getWeixMenu(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				System.out.println("创建菜单失败 errcode:{} errmsg:{}"
						+ jsonObject.getInt("errcode") + ":"
						+ jsonObject.getString("errmsg"));
			}
		}

		return result;
	}

	/**
	 * 删除菜单
	 * 
	 * @param accessToken
	 * @return
	 */
	public static int delMenu(String accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = access_menu_del_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = "";
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				System.out.println("创建菜单失败 errcode:{} errmsg:{}"
						+ jsonObject.getInt("errcode") + ":"
						+ jsonObject.getString("errmsg"));
			}
		}

		return result;
	}

	/**
	 * 获取 ticket
	 * 
	 * @return
	 */
	public static String getjsTicket() {

		String ticket = (String) CacheUtil.getItem("ECP_WEIX_TICKET");
		if (StringUtil.isBlank(ticket)) {
			AccessToken at = WeixinUtil.getAccessToken();
			String url = jsapi_ticket.replace("ACCESS_TOKEN", at.getToken());
			JSONObject jsonObject = httpRequest(url, "GET", null);
			if (null != jsonObject) {
				if (0 != jsonObject.getInt("errcode")) {
					System.out.println("创建菜单失败 errcode:{} errmsg:{}"
							+ jsonObject.getInt("errcode") + ":"
							+ jsonObject.getString("errmsg"));
				}
			}
			CacheUtil
					.addItem("ECP_WEIX_TICKET", jsonObject.getString("ticket"),7200);
			ticket = jsonObject.getString("ticket");
		} else {
			return ticket;
		}

		return ticket;
	}

	/**
	 * 获取weixjs api 使用票据
	 * 
	 * @param accessToken
	 * @param urls
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getJsSignature(String urls)
			throws NoSuchAlgorithmException {
		String ticket = getjsTicket();
		String echostr = ConstantTool.getWeixParam(WxConstants.ECP_WEIX
				+ "echostr");
		String timestamp = ConstantTool.getWeixParam(WxConstants.ECP_WEIX
				+ "timestamp");
		String str = "jsapi_ticket=" + ticket + "&noncestr=" + echostr
				+ "&timestamp=" + timestamp + "&url=" + urls;
		String tmpStr = SHA1(str);
		return tmpStr;
	}
	
	
	/**
	 * 获取weixjs api 使用票据(三个参数)
	 * 
	 * @param 
	 * @param urls
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static Map<String, String> getAllBase(String urls)
			throws NoSuchAlgorithmException {
		Map<String,String> map = new HashMap<String,String>();
		String ticket = getjsTicket();
		String echostr = ConstantTool.getWeixParam(WxConstants.ECP_WEIX
				+ "echostr");
		String timestamp = ConstantTool.getWeixParam(WxConstants.ECP_WEIX
				+ "timestamp");
//		String str = "jsapi_ticket=" + ticket + "&timestamp=" + timestamp+ "&noncestr=" + echostr + "&url=" + urls;
		String str = "jsapi_ticket=" + ticket +"&noncestr=" + echostr + "&timestamp=" + timestamp+  "&url=" + urls;
		String tmpStr = SHA1(str);
		map.put("echostr", echostr);
		map.put("timestamp", timestamp);
		map.put("signature", tmpStr);
		return map;
	}


	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	private static Menu getMenu() {
		Menu menu = new Menu();
		WeixMenuReqDTO menuReqDTO = new WeixMenuReqDTO();
		List<WeixMenuResDTO> list = iWeixMenuRSV.getWeixMenu(menuReqDTO);
		List<WeixMenuResDTO> listp = new ArrayList<WeixMenuResDTO>();
		List<WeixMenuResDTO> listn = new ArrayList<WeixMenuResDTO>();
		for (WeixMenuResDTO weixMenu : list) {
			if (weixMenu.getParentButtonId() != null
					&& weixMenu.getParentButtonId() == 0) {
				listp.add(weixMenu);
				continue;
			}
			listn.add(weixMenu);
		}
		Button[] bup = new Button[listp.size()];
		int i = 0;
		for (WeixMenuResDTO weixMenu : listp) {
			menuReqDTO.setParentButtonId(weixMenu.getId());
			List<WeixMenuResDTO> list1 = iWeixMenuRSV.getWeixMenu(menuReqDTO);
			ComplexButton mainBtn = new ComplexButton();
			Button[] bu = new Button[list1.size()];

			if (null==weixMenu.getButtonType()||"".equals(weixMenu.getButtonType())) {
				for (int j = 0; j < list1.size(); j++) {
					WeixMenuResDTO weixMenu2 = list1.get(j);
			
						if(null!=weixMenu2.getButtonType()&&"click".equals(weixMenu2.getButtonType())){
							CommonButton button = new CommonButton();
							button.setName(weixMenu2.getButtonTitle());
							button.setType(weixMenu2.getButtonType());
							button.setKey(String.valueOf(weixMenu2.getId()));
							bu[j] = button;
							mainBtn.setName(weixMenu.getButtonTitle());
							mainBtn.setSub_button(bu);
						}else if(null!=weixMenu2.getButtonType()&&"view".equals(weixMenu2.getButtonType())){
							ViewButton button = new ViewButton();
							button.setName(weixMenu2.getButtonTitle());
							button.setType(weixMenu2.getButtonType());
							button.setUrl(weixMenu2.getButtonUrl());
							bu[j] = button;
							mainBtn.setName(weixMenu.getButtonTitle());
							mainBtn.setSub_button(bu);
						}
						
				}
				bup[i] = mainBtn;
			} else {
				if(null!=weixMenu.getButtonType()&&"click".equals(weixMenu.getButtonType())){
					CommonButton button = new CommonButton();
					button.setName(weixMenu.getButtonTitle());
					button.setType(weixMenu.getButtonType());
					button.setKey(String.valueOf(weixMenu.getId()));
					bup[i] = button;
				}else if(null!=weixMenu.getButtonType()&&"view".equals(weixMenu.getButtonType())){
					ViewButton button = new ViewButton();
					button.setName(weixMenu.getButtonTitle());
					button.setType(weixMenu.getButtonType());
					button.setUrl(weixMenu.getButtonUrl());
					bup[i] = button;
				}
				
			}
		
			i++;
			menu.setButton(bup);
		}

		return menu;
	}

}
