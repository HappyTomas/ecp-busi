package com.zengshi.ecp.busi.route.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.order.dubbo.dto.ROrdCountResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdEvaluationRequest;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdEvaluationRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctSumResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustLevelInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAcctManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustLevelInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.StringUtil;

@Controller
@RequestMapping(value = "/route")
public class RouteController extends EcpBaseController {

	private static final String Module = RouteController.class.getName();

	@Resource
	private ICustInfoRSV custInfoRSV;
	
    @Resource
    private ICustLevelInfoRSV custLevelInfoRSV;
    
    @Resource
    private IOrdMainRSV orderMainRSV;
    
    @Resource
    private IOrdEvaluationRSV ordEvaluationRSV;
    
    @Resource
    private IAcctManageRSV acctManageRSV;
    
    @Resource
    private ICustManageRSV custManageRSV;

	@RequestMapping()
	public String index(Model model,@RequestParam(value="openId",required=false) String openId,@RequestParam(value="url",required=false) String url,HttpServletRequest request) throws BusinessException {
		
		if(StringUtil.isNotBlank(openId)){
			request.getSession().setAttribute("openId", openId);
		}
		
		
		 return "redirect:" + url;
	}

}
