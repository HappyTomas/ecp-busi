package com.zengshi.ecp.busi.staff.controller;

import java.util.List;

import javax.annotation.Resource;

import org.bouncycastle.crypto.engines.RijndaelEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaff2RoleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaff2RoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.LoginResultResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthLoginRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthRelManageRSV;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

@Controller
@RequestMapping(value="/login")
public class AuthLoginController extends EcpBaseController {
    
    private static String MODULE = AuthLoginController.class.getName();
    
    @Resource
    private IAuthLoginRSV authLoginDubbo;
    
    @Resource
    private IAuthRelManageRSV authRelManageRSV;
    
    @RequestMapping()
    public String init(Model model)
    {
    	String logoSerial = "";//默认图片
		try {
			logoSerial = SysCfgUtil.fetchSysCfg("MANAGE_LOGIN_LOGO_SERIAL").getParaValue();
		} catch (Exception e) {
			LogUtil.error(MODULE, "请为logo配置规定文件=="+e.getMessage());
		}
    	String imageUrl = ImageUtil.getImageUrl(logoSerial);
    	model.addAttribute("loginLogoImageUrl", imageUrl);
        return "/staff/login/login-main";
    }
    
    @RequestMapping(value="workbench")
    public String hallo(Model model)
    {
    	CustInfoReqDTO req = new CustInfoReqDTO();
    	String logoSerial = "";//默认图片
		try {
			logoSerial = SysCfgUtil.fetchSysCfg("MANAGE_LOGO_FILE_SERIAL").getParaValue();
		} catch (Exception e) {
			LogUtil.error(MODULE, "请为logo配置规定文件=="+e.getMessage());
		}
    	String imageUrl = ImageUtil.getImageUrl(logoSerial);
    	String imAnalyUrl = BaseParamUtil.fetchParamValue("IM_ANALY_URL", "IM_ANALY_URL");//获取im客服绩效是否开启，如果是链接说明开启，如果是0则不开启
    	model.addAttribute("staffCode", req.getStaff().getStaffCode());
    	model.addAttribute("ifShowAuditGds",SysCfgUtil.fetchSysCfg("GDS_VERIFY_SWITCH").getParaValue());
    	model.addAttribute("ifShowVerifyEval",SysCfgUtil.fetchSysCfg("SWITCH_GDS_EVAL_AUDIT").getParaValue());
    	model.addAttribute("logoImageUrl", imageUrl);
    	model.addAttribute("imAnalyUrl", imAnalyUrl);
    	model.addAttribute("saleAnalyze", CmsCacheUtil.getCmsSiteCache(6l).getSiteUrl());
    	return "/common/dashboard/main-content";
    }
    @RequestMapping(value="/check")
    @ResponseBody
    public EcpBaseResponseVO logincheck(@RequestParam("username")String username,@RequestParam("password")String password)
    {
        LogUtil.info(MODULE, "=========  用户密码服务验证    开始 ========");
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        LogUtil.info(MODULE, "=========  用户:"+username+ " 密码："+ password +"开始 ========");
        LoginResultResDTO result = authLoginDubbo.checkLogin(username, password);
        if(result == null)
        {
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        }
        else {
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
        }
        
        LogUtil.info(MODULE, "=========  用户密码服务验证     结束 ========");
        return vo;
    }
    
}

