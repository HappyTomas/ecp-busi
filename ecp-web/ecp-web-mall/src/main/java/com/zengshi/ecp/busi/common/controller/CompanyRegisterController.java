package com.zengshi.ecp.busi.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.common.vo.CompanyInfoVO;
import com.zengshi.ecp.busi.common.vo.RegisterVO;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthLoginRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustCheckRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.captcha.impl.CaptchaServlet;
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
 *        登陆控制
 */

@Controller
@RequestMapping(value = "/register/company")
public class CompanyRegisterController extends EcpBaseController {

    private static String MODULE = CompanyRegisterController.class.getName();

    @Resource
    private ICustManageRSV custManageRSV;
    
    @Resource
    private ICustCheckRSV custCheckRSV;
    
    @Resource
    private IAuthLoginRSV authLoginRSV;
    
    @RequestMapping()
    public String init(Model model) throws Exception {
    	BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("REGISTER_SMS_CHECK"); 
    	model.addAttribute("flag", baseSysCfgRespDTO.getParaValue());
        
        return "/common/regist/companyRegist";
    }
    
    @RequestMapping("/regist")
    @ResponseBody
    public EcpBaseResponseVO regist(HttpServletRequest request,RegisterVO registerVO,CompanyInfoVO companyInfoVO) throws Exception{
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        String CaptchaCode =  CaptchaServlet.getCaptchaCode(request);
        if(null==CaptchaCode||!CaptchaCode.equals(registerVO.getCaptchaCode())){
            vo.setResultFlag("fail");
            vo.setResultMsg("验证码输入错误，请重新输入!");
            return vo;
        }
        //是否开启短信验证
        BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("REGISTER_SMS_CHECK"); 
        if(StringUtil.isNotBlank(baseSysCfgRespDTO.getParaValue())&&"1".equals(baseSysCfgRespDTO.getParaValue())){
        String phoneCode =  ParamsTool.getPhoneCheckCode(request);
        String phoneAndCode = companyInfoVO.getLinkPhoneMsg() + registerVO.getPhoneCode();
        if (StringUtil.isBlank(phoneCode)) {
        	vo.setResultFlag("fail");
            vo.setResultMsg("短信验证码已过期，请重新获取!");
            return vo;
        }
        if(!phoneCode.equals(phoneAndCode)){
            vo.setResultFlag("fail");
            vo.setResultMsg("短信验证码输入错误，请重新输入!");
            return vo;
          }
        }
        CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
        ObjectCopyUtil.copyObjValue(registerVO, custInfoReqDTO, null, false);
        custInfoReqDTO.setCustType(StaffConstants.custInfo.CUST_TYPE_P);
        custInfoReqDTO.setSerialNumber(companyInfoVO.getLinkPhoneMsg());
       
        CustAuthstrReqDTO custAuthstrReqDTO = new CustAuthstrReqDTO();
        ObjectCopyUtil.copyObjValue(companyInfoVO, custAuthstrReqDTO, null, false);
        custAuthstrReqDTO.setStaffCode(registerVO.getStaffCode());
        //custAuthstrReqDTO.setStaffId(staff_id);
        custAuthstrReqDTO.setSerialNumber(companyInfoVO.getLinkPhoneMsg());
        authLoginRSV.registerCompany(custInfoReqDTO, custAuthstrReqDTO);
        vo.setResultFlag("ok");
        vo.setResultMsg("注册成功！点击确定进入首页");
        return vo;
    }

}
