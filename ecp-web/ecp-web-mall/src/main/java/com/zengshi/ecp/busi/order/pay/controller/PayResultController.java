package com.zengshi.ecp.busi.order.pay.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.order.HtmlEscape;
import com.zengshi.ecp.busi.order.RequestUtil;
import com.zengshi.ecp.busi.order.pay.vo.WeiXinVO;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.OrderPayStatusVO;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdPayRelRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPaymentRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.paas.utils.LogUtil; 

 

/**
 * 
 * Title: 在线支付结果展示（页面 0:成功 or 1:失败 or 2:交易现在处理）<br/>
 * 用于解析支付通道给我们的参数
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年10月19日下午8:27:13  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author panjs
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping("/payresult")
public class PayResultController extends EcpBaseController {
	public static final String module = PayResultController.class.getName();
	private final static Logger logger = LoggerFactory.getLogger(PayResultController.class);
	
	@Resource
    private IPaymentRSV paymentRSV;
	
	@Resource
    private IOrdDetailsRSV ordDetailsRSV;

    @Resource
    private IOrdPayRelRSV iOrdPayRelRSV;
    
	
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
     * 微信扫码在线支付结果解析
     */
    @RequestMapping(value = "/9007/{joinOrderId}")
    public String weixinpay(Model model,@PathVariable String joinOrderId,HttpServletRequest request,HttpServletResponse response) {
        LogUtil.info(module,"支付成功合并后的订单号:"+joinOrderId);
        
        try{
          //合并支付获取合并支付的订单
            ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
            rOrdPayRelReq.setJoinOrderid(joinOrderId);
            List<ROrdPayRelResp> ordList = new ArrayList<ROrdPayRelResp>();
            ordList = iOrdPayRelRSV.queryOrdPayRelByOption(rOrdPayRelReq);
            Timestamp time = new Timestamp(0l);
            Long siteId = 0l;
            String showOrderIds = "";
            String orderId = "";
            if(ordList != null && ordList.size() >=1){
                time = ordList.get(0).getCreateTime();
                ROrderDetailsRequest req = new ROrderDetailsRequest();
                req.setOrderId(ordList.get(0).getOrderId());
                ROrderDetailsResponse respDto = ordDetailsRSV.queryOrderDetails(req);
                if(respDto != null){
                    siteId = respDto.getsOrderDetailsMain().getSiteId();
                }
                orderId = ordList.get(0).getOrderId();
            }
            for(int i = 0;i<ordList.size();i++){
                ROrdPayRelResp rOrdPayRelResp = ordList.get(i);
                if(i == 0){
                    showOrderIds = rOrdPayRelResp.getOrderId();
                }else{
                    showOrderIds = showOrderIds + "," + rOrdPayRelResp.getOrderId();
                }
            }
            model.addAttribute("orderIdList", ordList);
            model.addAttribute("orderId", orderId);
            model.addAttribute("showOrderIds", showOrderIds);
            model.addAttribute("payTime", time);
            model.addAttribute("siteId", siteId);
        } catch (Exception e) {
            LogUtil.error(module, e.getMessage(), e);
            return "/order/pay/pay-failure";
        }
        return "/order/pay/pay-success";
    }
    
    /**
     * 
     * getPayStatus:(查询支付结果状态). <br/> 
     * 
     * @author panjs 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/getPayStatus")
    @ResponseBody
    public Map<String, Object> getPayStatus(Model model, WeiXinVO vo) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>(); 
        map.put("payFlag", "");
        try {
            String [] orderIds = vo.getOrderIds().split(",");  
            ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
            rOrdPayRelReq.setOrderId(orderIds[0]);
            rOrdPayRelReq.setPayFlag(OrdConstants.Order.ORDER_PAY_FLAG_1);
            List<ROrdPayRelResp> ordList = iOrdPayRelRSV.queryOrdPayRelByOption(rOrdPayRelReq);
            if(CollectionUtils.isNotEmpty(ordList)){ 
                map.put("payFlag", ordList.get(0).getPayFlag());
            }
        } catch (Exception e) {
            map.put("payFlag","");
            map.put("msg",e.getMessage());
        }
        return map;
    }
	
    /**
     * 线上支付
     * showResultPage:(支付成功或失败). <br/>  
     *
     * @author panjs
     * @param payWay
     * @param paramMap
     * @return
     * @since JDK 1.6
     */
    private String showResultPage(Model model,String payWay,Map<String, String> paramMap,HttpServletRequest request,HttpServletResponse response) {
        OrderPayStatusVO orderPayStatusVO = new OrderPayStatusVO();
        try {
            orderPayStatusVO = paymentRSV.parsePayStatus(payWay, paramMap);
            if(orderPayStatusVO.getFlag().equals("0")){
                //合并支付获取合并支付的订单
                ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
                rOrdPayRelReq.setJoinOrderid(orderPayStatusVO.getOrderId());
                List<ROrdPayRelResp> ordList = iOrdPayRelRSV.queryOrdPayRelByOption(rOrdPayRelReq);
                Timestamp time = new Timestamp(0l);
                Long siteId = 0l;
                String orderId = "";
                String showOrderIds = "";
                if(ordList != null && ordList.size() >=1){
                    time = ordList.get(0).getCreateTime();
                    ROrderDetailsRequest req = new ROrderDetailsRequest();
                    req.setOrderId(ordList.get(0).getOrderId());
                    ROrderDetailsResponse respDto = ordDetailsRSV.queryOrderDetails(req);
                    if(respDto != null){
                        siteId = respDto.getsOrderDetailsMain().getSiteId();
                    }
                    orderId = ordList.get(0).getOrderId();
                }else{
                	orderId = orderPayStatusVO.getOrderId();
                    ROrdPayRelResp rOrdPayRelResp = new ROrdPayRelResp();
                    rOrdPayRelResp.setOrderId(orderId);
                    ordList = new ArrayList<ROrdPayRelResp>();
                    ordList.add(rOrdPayRelResp);
                }
                for(int index = 0,size = ordList.size(); index < size; index++){
                	ROrdPayRelResp rOrdPayRelResp = ordList.get(index);
                	if(index == 0){
                		showOrderIds = rOrdPayRelResp.getOrderId();
                	}else{
                		showOrderIds = showOrderIds + "," + rOrdPayRelResp.getOrderId();
                	}
                }
                model.addAttribute("orderIdList", ordList);
                model.addAttribute("orderId", orderId);
                model.addAttribute("showOrderIds", showOrderIds);
                model.addAttribute("payTime", time);
                model.addAttribute("siteId", siteId);
            }else{
                if("9004".equals(payWay)){
                    response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                }
                return "/order/pay/pay-failure";
            }

        } catch (Exception e) {
            LogUtil.error(module, e.getMessage(),e);
            if("9004".equals(payWay)){
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            }
            return "/order/pay/pay-failure";
        }
        return "/order/pay/pay-success";
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
