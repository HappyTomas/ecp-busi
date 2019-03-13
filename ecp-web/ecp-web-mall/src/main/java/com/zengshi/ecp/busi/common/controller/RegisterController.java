package com.zengshi.ecp.busi.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.common.vo.RegisterVO;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
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
@RequestMapping(value = "/register")
public class RegisterController extends EcpBaseController {

    private static String MODULE = RegisterController.class.getName();

    @Resource
    private ICustManageRSV custManageRSV;
    
    @RequestMapping()
    public String init(Model model) throws Exception {
    	BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("REGISTER_SMS_CHECK"); 
    	model.addAttribute("flag", baseSysCfgRespDTO.getParaValue());
        return "/common/regist/regist";
    }
    
    @RequestMapping("/regist")
    @ResponseBody
    public EcpBaseResponseVO regist(HttpServletRequest request,RegisterVO registerVO) throws Exception{
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
        String phoneCode =  ParamsTool.getPhoneCheckCode(request);//校验码为phone + code
        String phoneAndCode = registerVO.getSerialNumber() + registerVO.getPhoneCode();
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
        custManageRSV.saveCustInfo(custInfoReqDTO);
        vo.setResultFlag("ok");
        vo.setResultMsg("注册成功！点击确定进入首页");
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
    public EcpBaseResponseVO check(RegisterVO registerVO) throws Exception{
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        //调用业务方法判断登录名是否存在
        boolean isExist = custManageRSV.checkCodeExist(registerVO.getStaffCode(), 0L, "1");
        if (isExist) {
        	vo.setResultFlag("fail");
        } else {
        	vo.setResultFlag("ok");
        }
        return vo;
    }

}
