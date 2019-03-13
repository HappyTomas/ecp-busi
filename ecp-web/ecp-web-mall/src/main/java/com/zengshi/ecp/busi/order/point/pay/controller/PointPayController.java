package com.zengshi.ecp.busi.order.point.pay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.order.OrdConstant;
import com.zengshi.ecp.busi.order.pay.vo.PaymentVO;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRequestData;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.PaymentRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdOfflineRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPayWayRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPaymentRSV;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.LogUtil;

@Controller
@RequestMapping(value="/point/pay")
public class PointPayController extends EcpBaseController {

    private static final String MODULE = PointPayController.class.getName();
    
    @Resource
    private IOrdOfflineRSV ordOfflineRSV;
    
    @Resource
    private IPayWayRSV payWayRSV;
    
    @Resource
    private IPaymentRSV paymentRSV; 
    
    @Resource 
    private IOrdMainRSV ordMainRSV;
   
    @Resource
    private IOrdDetailsRSV ordDetailsRSV;
    
    /**
     * 线上支付
     * way:(订单提交成功页面). <br/> 
     * 
     * @author wangxq 
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/way")
    public String way(Model model,HttpServletRequest request){
        Long staffId = new BaseInfo().getStaff().getId();
        HttpSession session = request.getSession();
        List<ROrdMainResponse> orderList = (List<ROrdMainResponse>)session.getAttribute(OrdConstant.OnlineOrd.ORDER_ONLINE_KEY+staffId);
        if(orderList == null || orderList.size() == 0){
            throw new BusinessException("session超时");
        }
        ROrdMainResponse orderMain = orderList.get(0);
        model.addAttribute("orderId", orderMain.getId());
        model.addAttribute("realMoney", orderMain.getRealMoney());
        if(orderMain.getOrderMoney() == 0){
            return "/order/point/pay/pay-way2";
        }else{
            PayWayRequest  waydto = new PayWayRequest();
            waydto.setShopId(orderMain.getShopId()); 
            List<PayWayResponse> wayList = new ArrayList<PayWayResponse>();
            try{
                wayList = payWayRSV.getPayWay(waydto);
                 
            }catch(Exception e){
                LogUtil.error(MODULE, e.getMessage(),e);
            }
            model.addAttribute("wayList", wayList);
            return "/order/point/pay/pay-way";
        }
    }  
    
    /**
     * 线上支付
     * way:(订单提交成功页面). <br/> 
     * 
     * @author panjs 
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/queryWay")
    public String queryWay(Model model, @RequestParam("orderId") String orderId, @RequestParam("shopId") String shopId){
        ROrderDetailsRequest rdto = new ROrderDetailsRequest();
        ROrderDetailsResponse resp = new ROrderDetailsResponse(); 
        List<PayWayResponse> wayList = new ArrayList<PayWayResponse>();
        try{
            rdto.setOrderId(orderId);
            resp = ordDetailsRSV.queryOrderDetails(rdto);
            PayWayRequest  waydto = new PayWayRequest();
            waydto.setShopId(Long.valueOf(shopId));  
            wayList = payWayRSV.getPayWay(waydto);
        }catch(Exception e){
            LogUtil.error(MODULE, e.getMessage(),e);
        }
        model.addAttribute("orderId", orderId);
        model.addAttribute("realMoney", resp.getsOrderDetailsMain().getRealMoney());
        java.util.Date now = new Date();
        long l=now.getTime()-resp.getsOrderDetailsMain().getOrderTime().getTime();
        long day=l/(24*60*60*1000);
        long hour=24-(l/(60*60*1000)-day*24);
        model.addAttribute("wayList", wayList);
        model.addAttribute("hour", hour);
        return "/order/point/pay/pay-way3";
    }
    
    /**
     * 线上支付
     * way:(订单列表). <br/> 
     * 
     * @author wangxq 
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/orderList")
    public String orderList(Model model,HttpServletRequest request){
        Long staffId = new BaseInfo().getStaff().getId();
        HttpSession session = request.getSession();
        List<ROrdMainResponse> orderList = (List<ROrdMainResponse>)session.getAttribute(OrdConstant.OnlineOrd.ORDER_ONLINE_KEY+staffId);
        if(orderList == null || orderList.size() == 0){
            throw new BusinessException("session超时");
        }
        int realMoney=0;
        for(int i=0;i<orderList.size();i++){
            ROrdMainResponse resp = orderList.get(i);
            realMoney+=resp.getRealMoney();
        }
        if(realMoney == 0){
            return "/order/point/pay/pay-way2";
        }else{
            model.addAttribute("orderList",orderList);
            return "/order/point/pay/pay-orderList";
        }
        
    }
    
    /**
     *  线上支付
     * requestPayment(返回表单URL、表单数据、字符集)
     * @author panjs 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/requestPayment")
    @ResponseBody
    public Map<String, Object> requestPayment(Model model,PaymentVO vo,HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>(); 
        ROrdCartsChkResponse resp = new ROrdCartsChkResponse();
        PaymentRequest paymentdto = new PaymentRequest();
        paymentdto.setOrderId(vo.getOrderId());
        paymentdto.setPayWay(vo.getPayWay());
        try{
            ROrderDetailsRequest rdor = new ROrderDetailsRequest();
            rdor.setOrderId(vo.getOrderId());
            rdor.setOper(vo.getOper());
            resp = ordMainRSV.queryOrdOperChk(rdor); 
            map.put("flag",resp.isStatus());
            map.put("msg",resp.getMsg());
            if(resp.isStatus()==true){
                Map<String, String> extendProps = new HashMap<String, String>();
                extendProps.put("clientIp", ParamsTool.getClientAddr(request));
                PayRequestData payRequestData = paymentRSV.requestPayment(paymentdto, extendProps);
                map.put("actionUrl", payRequestData.getActionUrl());
                map.put("method", payRequestData.getMethod());
                map.put("charset", payRequestData.getCharset());
                map.put("formData", payRequestData.getFormData());
                map.put("flag", true);
                map.put("msg", "发起支付请求成功!");
            }
        }catch(Exception e){
            LogUtil.error(MODULE, "[发起支付请求失败]异常信息:" + e.getMessage(),e);
            map.put("flag", false);
            map.put("msg", "[发起支付请求失败]异常信息:" + e.getMessage());
        } 
        return map;
    } 
    
    /** 
     * check:(验证付款). <br/>  
     * @author panjs 
     * @param model
     * @param orderid
     * @param oper
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/check")
    public Map<String, Object> check(Model model, @RequestParam("orderId") String orderid, @RequestParam("oper") String oper) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        ROrdCartsChkResponse resp = new ROrdCartsChkResponse();
        try {
            ROrderDetailsRequest rdor = new ROrderDetailsRequest(); 
            rdor.setOrderId(orderid);
            rdor.setOper(oper);
            resp = ordMainRSV.queryOrdOperChk(rdor); 
            map.put("flag",resp.isStatus());
            map.put("msg",resp.getMsg());
        } catch (Exception e) {
            LogUtil.error(MODULE, "异常信息:" + e.getMessage(),e);
            map.put("flag", false);
            map.put("msg",e.getMessage());
        }
        return map;
    }

}

