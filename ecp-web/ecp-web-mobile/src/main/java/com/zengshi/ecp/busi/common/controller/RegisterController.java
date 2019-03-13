package com.zengshi.ecp.busi.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.drools.core.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.common.vo.RegisterVO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustWechatRelReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustWechatRelRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.ecp.wxbase.util.WxConstants;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.CipherUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

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
 *        用户注册
 */

@Controller
@RequestMapping(value = "/register")
public class RegisterController extends EcpBaseController {

	private static String MODULE = RegisterController.class.getName();

	private static String AUTH_WECHAT_URL ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WxConstants.APPID;
	    
	@Resource
	private ICustManageRSV custManageRSV;

	@Resource
	private ICustWechatRelRSV custWechatRelRSV;

	@RequestMapping()
	public String init(HttpServletRequest request,Model model) throws Exception {
		String openId = (String) request.getSession().getAttribute("openId");
		
		if(StringUtils.isEmpty(openId)){
			openId = request.getParameter("openId");
		}
		//若openId为空，则进入授权
		if(StringUtil.isBlank(openId)){
			String red_url = "/register";
			red_url = CipherUtil.encrypt(red_url);
	    	String param = "&redirect_uri="+BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_SITE_MAPPING,"1")+"authWechat/authRedirect&response_type=code&scope=snsapi_base&state="+red_url+"#wechat_redirect";
	    	String url=AUTH_WECHAT_URL+param;
	        LogUtil.info(MODULE, "LoginController:-------------"+url);
	        return "redirect:"+url;
        }
		model.addAttribute("openId", openId);
		//查询短信开发是否开启，1、开启；0、关闭
		BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("REGISTER_SMS_CHECK"); 
    	model.addAttribute("flag", baseSysCfgRespDTO.getParaValue());
		return "/common/regist/regist";
	}

	@RequestMapping("/regist")
	@ResponseBody
	public EcpBaseResponseVO regist(HttpServletRequest request,
			RegisterVO registerVO) throws Exception {
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		//查询短信开发是否开启，1、开启；0、关闭
				BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("REGISTER_SMS_CHECK"); 
		if ("1".equals(baseSysCfgRespDTO.getParaValue())) {
			String phoneCode = ParamsTool.getPhoneCheckCode(request);// 校验码为phone + code
			String phoneAndCode = registerVO.getSerialNumber() + registerVO.getPhoneCode();
			if (StringUtil.isBlank(phoneCode)) {
				vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
				vo.setResultMsg("短信验证码已过期，请重新获取!");
				return vo;
			}
			if (!phoneCode.equals(phoneAndCode)) {
				vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
				vo.setResultMsg("短信验证码输入错误，请重新输入!");
				return vo;
			}
		}
		String openId = (String) request.getSession().getAttribute("openId");
		LogUtil.info(MODULE, "xxx---openid----:"+openId);
		if (openId == null) {
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			//vo.setResultMsg("微信openid缺失。");
			vo.setResultMsg(WxConstants.APPID);
			return vo;
		}
		CacheUtil.addItem("CACHE_WECHAT_OPENID_"+registerVO.getStaffCode(), openId, 3600);
		CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
		ObjectCopyUtil.copyObjValue(registerVO, custInfoReqDTO, null, false);
		custInfoReqDTO.setCustType(StaffConstants.custInfo.CUST_TYPE_P);
		custManageRSV.saveCustInfo(custInfoReqDTO);
		
		CustInfoReqDTO custReq = new CustInfoReqDTO();
		custReq.setStaffCode(registerVO.getStaffCode());
		CustInfoResDTO custRes = custManageRSV.findCustInfo(custReq);
		// 如果登录成功
		if (custRes != null && "1".equals(custRes.getStatus())) {
			CustWechatRelReqDTO wechatReq = new CustWechatRelReqDTO();
			wechatReq.setWechatId(String.valueOf(openId));
			wechatReq.setStaffCode(custRes.getStaffCode());
			wechatReq.setStaffId(custRes.getId());
			wechatReq.setCreateStaff(custRes.getId());
			custWechatRelRSV.saveCustWechatRel(wechatReq);
		}
		vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		vo.setResultMsg("注册成功！");
		return vo;
	}

	/**
	 * 
	 * check:(校验登录用户名是否已经存在). <br/>
	 * 
	 * @author huangxl
	 * @param request
	 * @param registerVO
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	@RequestMapping("/staffcode/check")
	@ResponseBody
	public EcpBaseResponseVO check(RegisterVO registerVO) throws Exception {
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		// 调用业务方法判断登录名是否存在
		boolean isExist = custManageRSV.checkCodeExist(
				registerVO.getStaffCode(), 0L, "1");
		if (isExist) {
			vo.setResultFlag("fail");
		} else {
			vo.setResultFlag("ok");
		}
		return vo;
	}

}
