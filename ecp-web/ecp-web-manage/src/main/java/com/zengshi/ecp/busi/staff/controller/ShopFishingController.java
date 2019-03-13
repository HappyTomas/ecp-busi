package com.zengshi.ecp.busi.staff.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 第三方系统登录对接<br>
 * Date:2015年9月15日下午6:19:26  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/union/login")
public class ShopFishingController extends EcpBaseController {
    
    /**
     * 
     * ssoToMainPageForShopFishing:(从商城跳转到管理平台). <br/> 
     * 用于店铺装修时，跳转到管理平台，并以一个默认的店铺装修管理员的角色账号进行登录
     * @author huangxl 
     * @param model
     * @param request
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/shopfishing")
    public String ssoToMainPageForShopFishing(Model model,HttpServletRequest request,@RequestParam(value="shopId") Long shopId) throws Exception {
        
    	request.getSession().setAttribute("shopfishing_", shopId);//把店铺id放到session里面
    	String shopFishCode = BaseParamUtil.fetchParamValue("SHOP_FISHING_MANAGER", "STAFF_CODE");
    	String shopFishUrl = BaseParamUtil.fetchParamValue("SHOP_FISHING_URL", "URL_PATH");
    	//判断用户是否配置，如果没配置，则抛出异常
    	if (StringUtil.isBlank(shopFishCode)) {
    		throw new BusinessException("对不起，未配置店铺装修管理员用户。请联系管理员配置。");
    	}
    	shopFishUrl = URLEncoder.encode(shopFishUrl, "UTF-8");
    	/*1、参数：j_from=SSO，可绕过密码及验证码*/
        String url = "redirect:/j_spring_security_check?j_username=" + shopFishCode + "&j_from=SSO";
        LogUtil.debug("SsoController", "店铺装修管理员跳转管理平台成功。");
        
        return url + "&Referer=" + shopFishUrl;   
        
    }
}

