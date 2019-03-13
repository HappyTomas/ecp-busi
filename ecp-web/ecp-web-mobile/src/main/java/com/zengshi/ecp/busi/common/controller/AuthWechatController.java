package com.zengshi.ecp.busi.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustWechatRelRSV;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.ecp.wxbase.util.WeixinUtil;
import com.zengshi.ecp.wxbase.util.WxConstants;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.CipherUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

import net.sf.json.JSONObject;

/**
 * 
 * @author lwy
 * @version
 * @since JDK 1.6
 * 
 *  授权中转页面
 */

@Controller
@RequestMapping(value = "/authWechat")
public class AuthWechatController extends EcpBaseController {

    private static String MODULE = AuthWechatController.class.getName();

    @Resource
    private ICustWechatRelRSV custWechatRelRSV;
 

    @RequestMapping(value = "/authRedirect")
    public String authRedirect(Model model,HttpServletRequest request,@RequestParam String code,@RequestParam String state){      
    	String oauth2url = CipherUtil.decrypt(state);
    	String openId = loadOpenId(code);
    	if(StringUtil.isNotBlank(openId)){
    		model.addAttribute("openId", openId);      	
    		request.getSession().setAttribute("openId", openId);
    		CacheUtil.addItem(ParamsTool.getCookSessionId(request)+"_openid",openId);
    	}
        return "redirect:"+oauth2url;
    }

    private String loadOpenId(String code){
		String openId=null;
		if(StringUtil.isNotBlank(code)){
			String access_token_url = WxConstants.ACCESS_TOKEN_URL;
			access_token_url = access_token_url.replace("APPID", WxConstants.APPID);
			access_token_url = access_token_url.replace("SECRET", WxConstants.SECRET);
			access_token_url = access_token_url.replace("CODE", code);
			JSONObject jsonObject = WeixinUtil.httpRequest(access_token_url, "GET", null);
			LogUtil.info(MODULE, jsonObject.toString());
			openId = (String) jsonObject.get("openid");//微信的openId		
		}
		return openId;

    }
    
}
