package com.zengshi.ecp.busi.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年10月9日上午9:27:02 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version
 * @since JDK 1.6
 * 
 *        用户信息设置
 *        邮箱  				/emails
 *        账户密码			/admin
 *        个人应用	        /applications
 *        等					/{xxxx}
 */

@Controller
@RequestMapping(value = "/settings")
public class SettingController extends EcpBaseController {

    private static String MODULE = SettingController.class.getName();

    @Autowired
    protected HttpSession session;

    @RequestMapping()
    public String init(Model model,HttpServletRequest request) {
        return "/common/settings/main";
    }

    @RequestMapping(value = "/emails")
    public void emails(HttpServletRequest request,HttpServletResponse response) throws Exception {
    	//TODO 
    }

    
}
