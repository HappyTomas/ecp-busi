package com.zengshi.ecp.busi.buyer.controller;

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
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.StringUtil;

@Controller
@RequestMapping(value = "/member")
public class MemberController extends EcpBaseController {

	private static final String Module = MemberController.class.getName();

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

	@RequestMapping(value = "/index")
	public String index(Model model,HttpServletRequest request,@RequestParam(value="siteFlag",required=false) String siteFlag) throws BusinessException {
		
		CustInfoReqDTO dto = new CustInfoReqDTO();
		dto.setId(dto.getStaff().getId());
		CustInfoResDTO custInfoResDTO = custInfoRSV.getCustInfoById(dto);

		dto.setCustLevelCode(custInfoResDTO.getCustLevelCode());
		dto.setCustGrowValue(custInfoResDTO.getCustGrowValue());
		CustLevelInfoResDTO dto2 = custLevelInfoRSV.queryValueGap(dto);
		
		  /*1、获取入参*/
        RQueryOrderRequest orderReq = new RQueryOrderRequest();
        orderReq.setStaffId(orderReq.getStaff().getId());
        orderReq.setCurrentSiteId(orderReq.getCurrentSiteId());
        orderReq.setSiteId(orderReq.getCurrentSiteId());
        
        /*2、调用业务查询接口，获取待付款、待发货、待收货订单数量*/
        ROrdCountResponse ordCountRes = orderMainRSV.queryOrderCountByStaff(orderReq);
        
        /*3、调用业务查询接口，获取待评价订单数量*/
        ROrdEvaluationRequest ordEva = new ROrdEvaluationRequest();
        ordEva.setStaffId(ordEva.getStaff().getId());
        Long comment = ordEvaluationRSV.queryEvaluationWaitCount(ordEva);
        
        /*4、调用接口，查询资金账户总金额*/
        AcctInfoReqDTO acct = new AcctInfoReqDTO();
        acct.setStaffId(acct.getStaff().getId());
        acct.setAdaptType(StaffConstants.Acct.ADAPT_TYPE_SHOP);
        AcctSumResDTO sum = acctManageRSV.queryAcctSumRelatedShops(acct);
        
        //获取当前站点及url链接
        String currSiteId = "1";//当前站点id，默认是1
        //
        if (StringUtil.isNotBlank(siteFlag)) {
        	request.getSession().setAttribute("currSiteId", siteFlag);
        }
        if (StringUtil.isNotEmpty(request.getSession().getAttribute("currSiteId"))) {
        	currSiteId = String.valueOf(request.getSession().getAttribute("currSiteId"));
        }
        //当前站点，映射的url  微信的url是通过参数配置，因为微信、web、app等数据要保持在同一个站点，所以只能通过映射来实现
        String mallSiteUrl = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_SITE_MAPPING, "1");
        String mallPointSiteUrl = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_SITE_MAPPING, "2");
		model.addAttribute("ordCountRes", ordCountRes);
		model.addAttribute("comment", comment);
		model.addAttribute("acctSum", sum);
		model.addAttribute("gaplevel", dto2);
		model.addAttribute("member", custInfoResDTO);
		model.addAttribute("currSiteId", currSiteId);
		model.addAttribute("mallSiteUrl", mallSiteUrl);
		model.addAttribute("mallPointSiteUrl", mallPointSiteUrl);
		return "/buyer/member";
	}

}
