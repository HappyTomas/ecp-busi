package com.zengshi.ecp.busi.order.pay.controller;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.order.util.HtmlEscape;
import com.zengshi.ecp.busi.order.util.RequestUtil;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.OrderPayStatusVO;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdPayRelRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPaymentRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustWechatRelRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import org.apache.commons.httpclient.HttpStatus;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;


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
 
	@Resource
    private IPaymentRSV paymentRSV;

    @Resource
    private ICustWechatRelRSV custWechatRelRSV;
	
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
     * 微信在线支付结果解析
     */
    @RequestMapping(value = "/9006")
    public String weChatScanCode(Model model,HttpServletRequest request,HttpServletResponse response) {
        String orderId = getParam("orderId",request);
        LogUtil.info(module,"支付成功订单号:"+orderId);
        try{
            //joinOrderId 是丢给支付通道做流水单号使用的，是支付通道回传回来的。
            Long staffId = new BaseInfo().getStaff().getId();

            ROrdPayRelResp ordPayRelResp = new ROrdPayRelResp();
            Object o = CacheUtil.getItem(OrdConstants.PayWayWeChat.REDIS_PREFIX+staffId);
            //暂时放入redis，微信成功展示页面信息
            if(o!=null)ordPayRelResp = (ROrdPayRelResp)o;

            //用完删掉
            CacheUtil.delItem(OrdConstants.PayWayWeChat.REDIS_PREFIX+staffId);
            String joinOrderId = ordPayRelResp.getJoinOrderid();
            Long total_fee = ordPayRelResp.getRealMoney();
            Timestamp time = new Timestamp(new Date().getTime());

            model.addAttribute("orderId", joinOrderId);
            model.addAttribute("realMoney", total_fee);

            model.addAttribute("siteId", SiteLocaleUtil.getSite());
            model.addAttribute("payTime", time);
        } catch (Exception e) {
            LogUtil.error(module, e.getMessage(), e);
            return "/order/pay/pay-failure";
        }
        return "/order/pay/pay-success";
    }

    private Map<String, String> getParamMapWeChat(HttpServletRequest request) {
        BufferedReader in = null;
        String content = "";
        Map<String, String> m = new HashMap<String, String>();
        try {
            in = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                content += line;
            }
            Document doc = DocumentHelper.parseText(content);
            Element root = doc.getRootElement();
            for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
                Element e = (Element) iterator.next();
                m.put(e.getName(), e.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return m;
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
                if(ordList !=null && ordList.size() >=1){
                    time = ordList.get(0).getCreateTime();
                    ROrderDetailsRequest req = new ROrderDetailsRequest();
                    req.setOrderId(ordList.get(0).getOrderId());
                    ROrderDetailsResponse respDto = ordDetailsRSV.queryOrderDetails(req);
                    if(respDto != null){
                        siteId = respDto.getsOrderDetailsMain().getSiteId();
                    }
                    model.addAttribute("orderIdList", ordList);
                    model.addAttribute("orderId", ordList.get(0).getOrderId());
                }else{
                    ROrdPayRelResp rOrdPayRelResp = new ROrdPayRelResp();
                    rOrdPayRelResp.setOrderId(orderPayStatusVO.getOrderId());
                    model.addAttribute("orderIdList", new ArrayList<ROrdPayRelResp>().add(rOrdPayRelResp));
                    model.addAttribute("orderId", orderPayStatusVO.getOrderId());
                }

                model.addAttribute("payTime", time);
                model.addAttribute("siteId", siteId);
            }else{
                if("9004".equals(payWay)){
                    response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                }
                return "/order/pay/pay-failure";
            }

            //通知微信
            if("9006".equals(payWay)){
                responseWechat(orderPayStatusVO.getFlag(),response);
            }

        } catch (Exception e) {
            LogUtil.error(module, e.getMessage(), e);
            if("9004".equals(payWay)){
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            }
            return "/order/pay/pay-failure";
        }
        return "/order/pay/pay-success";
    }

    //微信回调应答
    private void responseWechat(String flag, HttpServletResponse response) throws IOException {
        String xml = "<xml><return_code>";
        if(flag.equals("0")){
            xml+="<![CDATA[SUCCESS]]>";
        }else{
            xml+="<![CDATA[FAIL]]>";
        }
        xml+="</return_code></xml>";
        PrintWriter out = response.getWriter();
        out.write(xml);
        out.flush();
        out.close();
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
