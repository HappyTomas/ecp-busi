package com.zengshi.ecp.busi.order.pay.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.OrdConstant;
import com.zengshi.ecp.busi.order.pay.vo.PaymentVO;
import com.zengshi.ecp.busi.order.pay.vo.WeiXinVO;
import com.zengshi.ecp.busi.order.vo.ROrderDetailsReqVO;
import com.zengshi.ecp.busi.order.vo.RSumbitMainsReqVO;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainsResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsMain;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRequestData;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.PaymentRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdOfflineRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdPayRelRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPayWayRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPaymentRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

import net.sf.json.JSONObject;
import scala.actors.threadpool.Arrays;

@Controller
@RequestMapping(value="/pay")
public class PayController extends EcpBaseController {

    private static final String MODULE = PayController.class.getName();
    
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
    
    @Resource
    private IOrdPayRelRSV ordPayRelRSV;
    
    /**
     * 线上支付
     * way:(订单提交成功页面). <br/> 
     * 
     * @author wangxq 
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/way")
    public String way(Model model,HttpServletRequest request,PaymentVO vo){
        
        String showOrderId="";
        List<ROrdMainResponse> orderList = (List<ROrdMainResponse>)CacheUtil.getItem(OrdConstant.OnlineOrd.ORDER_ONLINE_KEY+vo.getOnlineKey());
       
        if(orderList == null || orderList.size() == 0){
            throw new BusinessException("session超时");
        }
        long mergeTotalRealMoney = 0;
        long shopId = 0;
        //是否有多个订单。如果只有一个订单不需要选择，会直接跳转到选择支付方式，因此在这里做一步判断
        if(!StringUtil.isBlank(vo.getOrderIds())){
            String[] orderIds = vo.getOrderIds().split(",");
            for(int i = 0; i<orderIds.length;i++){
                for(ROrdMainResponse resp : orderList){
                    if(orderIds[i].equals(resp.getId())){
                        mergeTotalRealMoney += resp.getRealMoney();
                        if(i==0){
                        	showOrderId=resp.getId();	
                        }else{
                            showOrderId=showOrderId+","+resp.getId();	
                        }
                    }
                }
            }
        }else{
            for(int index = 0,size = orderList.size(); index < size; index++){
            	ROrdMainResponse ordMainResponse = orderList.get(index);
            	if(index == 0){
	            	shopId = ordMainResponse.getShopId();
	            	showOrderId = ordMainResponse.getId();
            	}
                mergeTotalRealMoney += ordMainResponse.getRealMoney();
            }
        }
        model.addAttribute("showOrderIds", showOrderId);       
        model.addAttribute("realMoney", mergeTotalRealMoney);
        if(mergeTotalRealMoney == 0){
            return "/order/pay/pay-way2";
        }else{ 
        	// 获取合并支付的开关 1-表示开启  0-表示关闭
            BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
            if(OrdConstants.OrdPayRel.PAY_MERGE_ENABLE.equals(payMergeSysCfg.getParaValue())){
            	shopId = OrdConstants.OrdPayRel.PAY_WAY_MERGE_CFG_ID;
            } 
            PayWayRequest waydto = new PayWayRequest();
            waydto.setShopId(shopId);
            List<PayWayResponse> wayList = new ArrayList<PayWayResponse>();
            try{
                wayList = payWayRSV.getPayWay(waydto);
            }catch(Exception e){
                LogUtil.error(MODULE, e.getMessage(),e);
            }
            model.addAttribute("wayList", wayList);
            return "/order/pay/pay-way";    
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
    public String queryWay(Model model, @RequestParam("orderIds") String orderIds){
    	if(!StringUtil.isBlank(orderIds)){
        	ROrderDetailsRequest rdto = new ROrderDetailsRequest();
            List<PayWayResponse> wayList = new ArrayList<PayWayResponse>();
            long mergeTotalRealMoney = 0;
            long shopId = 0l;
            // 查询订单总金额
            String showOrderIds = "";
        	String[] orderIdArry = orderIds.split(",");
        	long orderTime = 0l;
        	try{
        		for(int index = 0; index < orderIdArry.length; index ++){
        			rdto.setOrderId(orderIdArry[index]);
        			ROrderDetailsResponse orderDetailsResponse = ordDetailsRSV.queryOrderDetails(rdto);
        			SOrderDetailsMain ordMain = orderDetailsResponse.getsOrderDetailsMain();
        			if(index == 0){
                    	shopId = ordMain.getShopId();
                    	showOrderIds = ordMain.getId();	
                    	orderTime = ordMain.getOrderTime().getTime();
                    }else{
                    	showOrderIds = showOrderIds + "," + ordMain.getId();	
                    }
        			mergeTotalRealMoney += ordMain.getRealMoney();
            	}
            }catch(Exception e){
                LogUtil.error(MODULE, e.getMessage(),e);
            }
        	// 获取合并支付的开关 1-表示开启  0-表示关闭
            BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
            if(OrdConstants.OrdPayRel.PAY_MERGE_ENABLE.equals(payMergeSysCfg.getParaValue())){
            	shopId = OrdConstants.OrdPayRel.PAY_WAY_MERGE_CFG_ID;
            }
            PayWayRequest waydto = new PayWayRequest();
            waydto.setShopId(shopId);
            wayList = payWayRSV.getPayWay(waydto);
            model.addAttribute("showOrderIds", showOrderIds);       
            model.addAttribute("realMoney", mergeTotalRealMoney);
            java.util.Date now = new Date();
            long l = now.getTime() - orderTime;
            long day = l / (24 * 60 * 60 * 1000);
            long hour = 24 - (l / (60 * 60 * 1000) - day * 24);
            model.addAttribute("wayList", wayList);
            model.addAttribute("hour", hour);
        }
        return "/order/pay/pay-way3";
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
    @RequestMapping(value="/orderList/{onlineKey}")
    public String orderList(Model model,@PathVariable String onlineKey,HttpServletRequest request){
        List<ROrdMainResponse> orderList = (List<ROrdMainResponse>)CacheUtil.getItem(OrdConstant.OnlineOrd.ORDER_ONLINE_KEY+onlineKey);      
        if(orderList == null || orderList.size() == 0){
            throw new BusinessException("session超时");
        }
        int realMoney=0;
        for(int i=0;i<orderList.size();i++){
            ROrdMainResponse resp = orderList.get(i);
            realMoney+=resp.getRealMoney();
        }
        if(realMoney == 0){
            return "/order/pay/pay-way2";
        }else{
            model.addAttribute("orderList",orderList);
            return "/order/pay/pay-orderList";
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
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/requestPayment")
    @ResponseBody
    public Map<String, Object> requestPayment(Model model,PaymentVO vo,HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>(); 
        // 多个订单合成一个订单去支付
    	ROrdPayRelReq reqDTO = new ROrdPayRelReq();
    	String orderIds = vo.getOrderIds();
    	if(StringUtil.isBlank(orderIds)){
    		LogUtil.error(MODULE, "[发起支付请求失败]异常信息: 订单ID参数为空");
            map.put("flag", false);
            map.put("msg", "[发起支付请求失败]异常信息: 订单ID参数为空");
            return map;
    	}
    	String[] orderIdArry = orderIds.split(",");
        reqDTO.setOrderIdList(Arrays.asList(orderIdArry));
        ROrdPayRelResp respDto = ordPayRelRSV.saveOrdPayRel(reqDTO);
        if(respDto == null || StringUtil.isBlank(respDto.getJoinOrderid())){
        	LogUtil.error(MODULE, "[发起支付请求失败]异常信息: 保存合并支付订单表失败");
            map.put("flag", false);
            map.put("msg", "[发起支付请求失败]异常信息: 保存合并支付订单表失败");
            return map;
        }
        ROrdCartsChkResponse resp = new ROrdCartsChkResponse();
        PaymentRequest paymentdto = new PaymentRequest();
        paymentdto.setOrderId(respDto.getJoinOrderid());
        paymentdto.setPayWay(vo.getPayWay());
        try{
            ROrderDetailsRequest rdor = new ROrderDetailsRequest();
            rdor.setOrderId(respDto.getJoinOrderid());
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
                if(vo.getPayWay().equals(OrdConstants.PAY_WAY.WeiXin)){
                    payRequestData.getFormData().put("orderIds", orderIds);
                    payRequestData.getFormData().put("mergeTotalRealMoney", vo.getMergeTotalRealMoney()+"");
                    payRequestData.getFormData().put("joinOrderId", respDto.getJoinOrderid());
                }
                map.put("formData", payRequestData.getFormData());
                map.put("flag", true);
                map.put("msg", "发起支付请求成功!");
            }
        }catch(Exception e){
            LogUtil.error(MODULE, "[发起支付请求失败]异常信息:" + e.getMessage(),e);
            map.put("flag", false);
            map.put("msg", "[发起支付请求失败]异常信息:" + e.getMessage());
        } 
        LogUtil.info(MODULE, "发请支付请求返回的数据:" + JSONObject.fromObject(map).toString());
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
    public Map<String, Object> check(Model model, @RequestParam("orderIds") String orderids, @RequestParam("oper") String oper) throws Exception{
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag",true);
        map.put("orderIds", orderids);
        map.put("msg","success");
        return map;
    }
    
    /**
     *  微信支付页面
     * payWeiXin(返回二维码URL)
     * @author panjs 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/payWeiXin")
    public String payWeiXin(Model model, WeiXinVO vo){
        model.addAttribute("code_url", vo.getCode_url());
        ROrderDetailsRequest rdto = new ROrderDetailsRequest();
        String [] orderIds = vo.getOrderIds().split(",");
        rdto.setOrderId(orderIds[0]);
        ROrderDetailsResponse resp = new ROrderDetailsResponse();
        resp = ordDetailsRSV.queryOrderDetails(rdto);
        // 订单id
        model.addAttribute("orderIds", vo.getOrderIds());
        model.addAttribute("joinOrderId", vo.getJoinOrderId());
        model.addAttribute("realMoney", vo.getMergeTotalRealMoney());
        model.addAttribute("orderIdCount", orderIds.length);
        // 主订单相关字段
        model.addAttribute("sOrderDetailsMain", resp.getsOrderDetailsMain());
        // 子订单相关字段
        model.addAttribute("sOrderDetailsSubs", resp.getsOrderDetailsSubs());
        return "/order/pay/pay-weixin";
    } 

}

