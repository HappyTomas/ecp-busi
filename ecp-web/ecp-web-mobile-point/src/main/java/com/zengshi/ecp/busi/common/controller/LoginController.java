package com.zengshi.ecp.busi.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.util.WebContextUtil;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustWechatRelReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustWechatRelRespDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthLoginRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthStaffRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustWechatRelRSV;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.ecp.wxbase.util.WeixinUtil;
import com.zengshi.ecp.wxbase.util.WxConstants;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.core.config.Application;
import com.zengshi.butterfly.core.web.WebConstants;
import com.alibaba.dubbo.common.utils.CollectionUtils;

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
 *        用户登录
 */

@Controller
@RequestMapping(value = "/login")
public class LoginController extends EcpBaseController {

    private static String MODULE = LoginController.class.getName();

    @Autowired
    protected HttpSession session;
    
    @Resource
    private IAuthStaffRSV authStaffRSV;
    
    @Resource
    private IAuthLoginRSV authLoginRSV;
    
    @Resource
    private ICustManageRSV custManageRSV;
    
    @Resource
    private ICustWechatRelRSV custWechatRelRSV;

    @RequestMapping()
    public String init(Model model,HttpServletRequest request) {
        model.addAttribute("Referer", request.getParameter("Referer"));
        String openId = request.getParameter("openId");
        if(StringUtil.isNotBlank(openId)){
        	 model.addAttribute("openId", openId);
        	 request.getSession().setAttribute("openId", openId);	
        }else{
        	String code = request.getParameter("code");
    		if(StringUtil.isNotBlank(code)){
    			String access_token_url = WxConstants.ACCESS_TOKEN_URL;
    			access_token_url = access_token_url.replace("APPID", WxConstants.APPID);
    			access_token_url = access_token_url.replace("SECRET", WxConstants.SECRET);
    			access_token_url = access_token_url.replace("CODE", code);
    			JSONObject jsonObject = WeixinUtil.httpRequest(access_token_url, "GET", null);
    			LogUtil.info(MODULE, jsonObject.toString());
    			openId = (String) jsonObject.get("openid");//微信的openId
    			//String access_token = jsonObject.getString("access_token");
    			
    		}
    		if(StringUtil.isNotBlank(openId)){
    		 model.addAttribute("openId", openId);
        	 request.getSession().setAttribute("openId", openId);	
    		}
        }
        
        return "/common/login/main-login";
    }

    @RequestMapping(value = "/page")
    public String page(HttpServletRequest request) throws Exception {
        String Referer =   request.getParameter("Referer");
        LogUtil.debug(MODULE, "==============Referer参数:"+Referer+"==================");
        String url = Application.getValue(WebConstants.URL_LOGIN_PAGE);//登录页面
        String requestURL = request.getRequestURL().toString();
        String servletPath = request.getServletPath();
        if(StringUtil.isNotBlank(servletPath)){//得到当前访问站点
        	requestURL = requestURL.substring(0, requestURL.indexOf(servletPath));
        }else{
        	requestURL = requestURL.substring(0, requestURL.length());//去掉最后一个字符"/"
        }
        
        if(StringUtil.isNotBlank(Referer)){
            if(url.indexOf("?")>0){
                return "redirect:"+ url+"&Referer="+requestURL+Referer;
            }else{
                return "redirect:"+ url+"?Referer="+requestURL+Referer;
            }
        }else{
            return "redirect:" + url;
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response) throws Exception {
        WebContextUtil.logout(request, response);
        String url = Application.getValue(WebConstants.URL_LOGOUT_PAGE);
        return "redirect:" + url;
    }

    @RequestMapping(value = "/register")
    public String register(HttpServletRequest request) throws Exception {
        String url = Application.getValue(ParamsTool.Page.REGISTER_PAGE);
        return "redirect:" + url;
    }

    @RequestMapping(value = "/lostpassword")
    public String lostpassword(HttpServletRequest request) throws Exception {
        String url = Application.getValue(ParamsTool.Page.LOSTPASSWORD_PAGE);
        return "redirect:" + url;
    }

    @RequestMapping(value = "/updatepassword")
    public String updatepassword(HttpServletRequest request) throws Exception {
        String url = Application.getValue(ParamsTool.Page.UPDATEPASSWORD_PAGE);
        return "redirect:" + url;
    }
    
    /**
     * 
     * check:(用户登录密码校验). <br/> 
     * 用户状态跟密码校验通过后，会把session里面存入openId
     * @author huangxl5 
     * @param request
     * @param registerVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/check")
    @ResponseBody
    public EcpBaseResponseVO check(HttpServletRequest request,@RequestParam("j_username") String j_username,@RequestParam("j_password") String j_password,@RequestParam("openId") String openId) throws Exception{
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag("fail");
        vo.setResultMsg("用户名或密码错误。");
        String id = (String) request.getSession().getAttribute("openId");
        String j_openid = openId;
       // String j_openid =(String) request.getAttribute("openId");
        if (j_openid == null) {
        	vo.setResultMsg("微信openid缺失。");
        } else {
        	CustInfoReqDTO custReq = new CustInfoReqDTO();
        	custReq.setStaffCode(j_username);
        	CustInfoResDTO custRes = custManageRSV.findCustInfo(custReq);
        	//如果登录成功
            if (custRes != null && "1".equals(custRes.getStatus())) {
            	AuthStaffResDTO authRes = authStaffRSV.findAuthStaffById(custRes.getId());
            	if (authLoginRSV.checkPassword(j_password, authRes.getStaffPasswd())) {
            		//查询是否已绑定商城的会员 
            		CustWechatRelReqDTO wechatReq = new CustWechatRelReqDTO();
            		wechatReq.setStaffCode(custRes.getStaffCode());
                	wechatReq.setStaffId(custRes.getId());
                	wechatReq.setPageNo(1);
                	wechatReq.setPageSize(1);
                	PageResponseDTO<CustWechatRelRespDTO> page = custWechatRelRSV.findCustWechatRel(wechatReq);
                	//如果没有绑定，则进行绑定
                	if (page == null || CollectionUtils.isEmpty(page.getResult())) {
                    	wechatReq.setWechatId(String.valueOf(j_openid));
                    	wechatReq.setCreateStaff(custRes.getId());
                    	custWechatRelRSV.saveCustWechatRel(wechatReq);
                	} else {
                		CustWechatRelRespDTO resp = page.getResult().get(0);
                		//判断绑定的微信账号是否跟当前的一致，如果不一致，给出相应提示，不让登录
                		if (!resp.getWechatId().equals(String.valueOf(j_openid))) {
                			vo.setResultMsg("该用户" + custRes.getStaffCode() + "已绑定其他微信账号，无法登录。");
                			return vo;
                		}
                	}
                	vo.setResultFlag("ok");
            	}
            }
        }
        
        return vo;
    }
    
    /**
     * 
     * unbind:(解除绑定). <br/> 
     * 跳转到登录页面，清除session里面的openId
     * @author huangxl5 
     * @param request
     * @param openId
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/unbind")
    @ResponseBody
    public EcpBaseResponseVO unbind(HttpServletRequest request) throws Exception{
    	EcpBaseResponseVO vo = new EcpBaseResponseVO();
    	CustWechatRelReqDTO req = new CustWechatRelReqDTO();
    	req.setStaffId(req.getStaff().getId());
    	custWechatRelRSV.deleteCustWechatRelBy(req);
    	//request.removeAttribute("openId");
    	request.removeAttribute("openIdForLogin");
    	vo.setResultFlag("ok");
        return vo;
    }
    
}
