package com.zengshi.ecp.busi.order.refund.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.order.HtmlEscape;
import com.zengshi.ecp.busi.order.RequestUtil;
import com.zengshi.ecp.busi.order.util.ParamsTool;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyResp;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdBackGdsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdPayRelRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPaymentRSV;
import com.zengshi.paas.utils.StringUtil; 

 

/**
 * 
 * Title: 在线退款结果展示（页面 0:成功 or 1:失败 or 2:交易现在处理）<br/>
 * 用于解析支付通道给我们的参数
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2017年1月17日上午10:48:03  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author panjs
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping("/refundresult")
public class RefundResultController extends EcpBaseController {
	public static final String module = RefundResultController.class.getName();
	private final static Logger logger = LoggerFactory.getLogger(RefundResultController.class);
	
	@Resource
    private IPaymentRSV paymentRSV;
	
	@Resource
    private IOrdDetailsRSV ordDetailsRSV;

    @Resource
    private IOrdPayRelRSV iOrdPayRelRSV;
   
    @Resource
    private IOrdBackGdsRSV ordBackGdsRSV;
    
	
	/**
	 * 鸿支付在线支付结果展示
	 */
	@RequestMapping(value = "/9002")
	public String hongpay(Model model,HttpServletRequest request,HttpServletResponse response) {
		return this.showResultPage(model,"9002", this.getParamMap(request),request,response);
	}

	/**
	 * 支付宝在线支付结果解析
	 */
	@RequestMapping(value = "/9003")
	public String alipay(Model model,HttpServletRequest request,HttpServletResponse response) {
		return this.showResultPage(model,"9003", this.getParamMap(request),request,response);
	}

	/**
	 * 农行在线支付结果解析
	 */
	@RequestMapping(value = "/9004")
	public String abcpay(Model model,HttpServletRequest request,HttpServletResponse response) {
		return this.showResultPage(model,"9004", this.getParamMap(request),request,response);
	}
	
    /**
     * 线上退款
     * showResultPage:(退款功或失败). <br/>  
     *
     * @author panjs
     * @param payWay
     * @param paramMap
     * @return
     * @since JDK 1.6
     */
    private String showResultPage(Model model,String payWay,Map<String, String> paramMap,HttpServletRequest request,HttpServletResponse response) {
        model.addAllAttributes(ParamsTool.params());
        String url="/order/ordback/backgds";
        try {
            logger.info(module, "req=" + paramMap);
            Long backId = Long.valueOf(paramMap.get("backId"));
            RBackApplyResp backApplyResp =  ordBackGdsRSV.queryOrdBackApplyByBackId(backId);
            if(StringUtil.isNotEmpty(backApplyResp)){
                String applyType = backApplyResp.getApplyType();
                if(StringUtil.isNotBlank(applyType) && applyType.equals("0")){
                    url = "/order/ordback/refund";
                }
            }
        } catch (Exception e) {
            logger.error(module, e.getMessage(),e);
        }
        return url;
    }

    /**
	 *    
	 * 从Request获取参数
	 * @author panjs 
	 * @return 
	 * @since JDK 1.6
	 */
    private Map<String,String> getParamMap(HttpServletRequest request){
        Map<String, String> m = new HashMap<String, String>();
        Iterator<String> keys = request.getParameterMap().keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            m.put(key, getParam(key,request));
        }
        return m;
    }
    
    /**
     * 从Request获取参数
     * @param name
     * @return
     */
    private String getParam(String name,HttpServletRequest request){
        if(RequestUtil.isAjaxRequest(request)){
            return request.getParameter(name);
        } else {
            return HtmlEscape.htmlEncode(request.getParameter(name));
        }
    }
    

}
