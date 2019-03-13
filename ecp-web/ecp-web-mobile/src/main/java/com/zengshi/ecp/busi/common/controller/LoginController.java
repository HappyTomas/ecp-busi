package com.zengshi.ecp.busi.common.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.web.util.WebUtil;
import net.sf.json.JSONObject;

import org.drools.core.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.util.WebContextUtil;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustWechatRelReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustWechatRelRespDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthLoginRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthStaffRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustWechatRelRSV;
import com.zengshi.ecp.system.util.CookieUtil;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.ecp.wxbase.util.WeixinUtil;
import com.zengshi.ecp.wxbase.util.WxConstants;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.CipherUtil;
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
    
    private static String AUTH_WECHAT_URL ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WxConstants.APPID;
    		

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
    public String init(Model model,HttpServletRequest request,HttpServletResponse response) {

    	String referer = request.getParameter("Referer");   	
    	String openId =  request.getParameter("openId");
    	
    	if(StringUtil.isBlank(openId)){
			openId = (String) request.getSession().getAttribute("openId");
		}
    	
        if(StringUtil.isBlank(openId)){
        	 openId = (String) CacheUtil.getItem(request.getSession().getId()+"_openid");
        }
        
        if(StringUtil.isBlank(openId)){
        	openId = this.getCookieOpenId(request);
        }
    
        if(StringUtil.isNotBlank(openId)){       	
        	 request.getSession().setAttribute("openId", openId);	
        	 CacheUtil.addItem(session.getId()+"_openid",openId,1800);
        }
     
        LogUtil.info(MODULE, "-------------sessionID:"+request.getSession().getId());
        //过滤掉退出登陆的url
        if(StringUtil.isNotBlank(referer)&&referer.indexOf("/j_spring_security_logout")!=-1){
        	referer = "";
        }
        LogUtil.info(MODULE, "----------openId:"+openId);    
        if(StringUtil.isBlank(openId)){    //openId为空，做一次微信auth2.0授权   	
        	String redurl = "/login";
        	if(StringUtil.isNotBlank(referer)){
        		redurl = redurl+"?Referer="+referer;
        	}
        	redurl = CipherUtil.encrypt(redurl);
        	String param = "&redirect_uri="+BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_SITE_MAPPING,"1")+"authWechat/authRedirect&response_type=code&scope=snsapi_userinfo&state="+redurl+"#wechat_redirect";
        	String url=AUTH_WECHAT_URL+param;
            return "redirect:"+url;
        }
        //判断是否绑定过账号
        Map<String,Object> resultMap = checkLogin(openId);

        Boolean hadLogin  = (Boolean) resultMap.get("result");
   
        if(hadLogin){
        	 AuthStaffResDTO staff = (AuthStaffResDTO) resultMap.get("staff");
        	 String url = "redirect:/j_spring_security_check?j_username=" + staff.getStaffCode()+"&j_from=SSO";
        	 //LogUtil.info(MODULE, "----------SSO登陆url:"+url);
		     ///这里要加上非空校验的；
		     if(StringUtil.isBlank(referer)){
		         //为空，默认进入用户商城主页
		    	 url = url + "&Referer=/homepage";
		     } else {
		         try {
					url = url + "&Referer=" + URLEncoder.encode (referer, "UTF-8" );
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					LogUtil.info(MODULE, "URL转码失败");
				}
		     }
        	 return url;
        }else{
        	model.addAttribute("openId", openId);
            model.addAttribute("Referer", referer);
        	return "/common/login/main-login";
        }
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
    	String url = Application.getValue(WebConstants.URL_LOGOUT_PAGE);
        WebContextUtil.logout(request, response);       
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
        if (StringUtil.isBlank(j_openid)) {//j_openid == null
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
            		//
                	wechatReq.setStaffId(custRes.getId());
                	wechatReq.setPageNo(1);
                	wechatReq.setPageSize(1);
                	PageResponseDTO<CustWechatRelRespDTO> page = custWechatRelRSV.findCustWechatRel(wechatReq);
                	//如果没有绑定，则进行绑定
                	if (page == null || CollectionUtils.isEmpty(page.getResult())) {                		
                		CustWechatRelRespDTO wechatRespDTO = custWechatRelRSV.findCustWechatRelByWechatId(j_openid);
                    	if (wechatRespDTO != null && !wechatRespDTO.getStaffCode().equals(custRes.getStaffCode())) {
                    		vo.setResultMsg("该用户" + custRes.getStaffCode() + "已绑定其他微信账号，无法登录。");
                			return vo;
                    	}
                    	wechatReq.setStaffCode(custRes.getStaffCode());
                		wechatReq.setWechatId(String.valueOf(j_openid));
                    	wechatReq.setCreateStaff(custRes.getId());
                    	custWechatRelRSV.saveCustWechatRel(wechatReq);
                	} else {
                		request.getSession().setAttribute("openId",j_openid);//放置到session
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
    public EcpBaseResponseVO unbind(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String openId = (String) request.getSession().getAttribute("openId");
    	EcpBaseResponseVO vo = new EcpBaseResponseVO();
    	CustWechatRelReqDTO req = new CustWechatRelReqDTO();
    	req.setStaffId(req.getStaff().getId());
    	custWechatRelRSV.deleteCustWechatRelBy(req);
    	//request.removeAttribute("openId");
    	request.removeAttribute("openIdForLogin");
    	WebContextUtil.logout(request, response);

    	//生成新的session存储openId,避免退出登陆后需要重新授权
    	request.getSession(true).setAttribute("openId", openId);  
    	LogUtil.info(MODULE, request.getSession(true).getId());
    	LogUtil.info(MODULE, ""+request.getSession(true).getAttribute("openId"));
    		
    	vo.setResultFlag("ok");
        return vo;
    }
    
    //判断该openId是否绑定过
    private Map<String,Object> checkLogin(String openId){
    	Map<String,Object> map = new HashMap<String,Object>();
    	CustWechatRelReqDTO wechatReq = new CustWechatRelReqDTO();
		wechatReq.setWechatId(openId);
    	wechatReq.setPageNo(1);
    	wechatReq.setPageSize(1);
    	PageResponseDTO<CustWechatRelRespDTO> page = custWechatRelRSV.findCustWechatRel(wechatReq);
    	//如果没有绑定，则进行绑定
    	if (page == null || CollectionUtils.isEmpty(page.getResult())) {  
    		map.put("result", false);
        	return map;
    	}      	
    	//获取用户信息
    	map.put("result", true);
    	AuthStaffResDTO staff = authStaffRSV.findAuthStaffById(page.getResult().get(0).getStaffId());
    	map.put("staff", staff);
    	return map;
    }
    
    /**
     * 获取浏览器cookies中保存的openId
     * @param request
     * @return
     */
    public String getCookieOpenId(HttpServletRequest request){
    	 String openId = "";
    	 Cookie[] cookies = request.getCookies();
    	 for(Cookie cookie:cookies){
    		 if(cookie.getName().equals("_openId")){
    			 openId = cookie.getValue();
    			 break;
    		 }
    	 }
    	return openId;
    }

}
