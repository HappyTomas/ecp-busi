package com.zengshi.ecp.busi.order.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.order.OrdConstant;
import com.zengshi.ecp.order.dubbo.dto.ROrdExpressDetailsResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderExpressRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Created by wang on 16/8/5.
 */
@Controller
@RequestMapping(value="/order")
public class OrdController extends EcpBaseController{

    private static final String MODULE = OrdController.class.getName();

    @Resource
    private IOrdMainRSV ordMainRSV;

    @Resource
    private IOrdDetailsRSV ordDetailsRSV;
    
    @Resource
    private IOrderExpressRSV orderExpressRSV;

    // 订单详情
    /**
     *
     * @param model
     * @param orderid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/detail")
    public String otherDomainQueryOrderDetails(Model model, @RequestParam("orderId") String orderid) {

        ROrderDetailsRequest rdto = new ROrderDetailsRequest();
        if (StringUtil.isBlank(orderid)) {
            throw new BusinessException("order.orderid.null.error");
        }
        rdto.setOrderId(orderid);
        rdto.setOper("01");
        ROrderDetailsResponse resp = new ROrderDetailsResponse();

        if(!ordMainRSV.queryChkStatus(rdto).isStatus()){

            return "redirect:/homepage";
        }
        resp = ordDetailsRSV.queryOrderDetails(rdto);

        // 订单id
        model.addAttribute("orderId", orderid);
        // 主订单相关字段
        model.addAttribute("ordMain", resp.getsOrderDetailsMain());
        // 子订单相关字段
        model.addAttribute("ordSubs", resp.getsOrderDetailsSubs());
        // 订单优惠相关字段
        model.addAttribute("discount", resp.getsOrderDetailsDiscount());
        // 订单跟踪相关字段
        model.addAttribute("track", resp.getsOrderDetailsTracks());
        // 订单收货地址相关字段
        model.addAttribute("addr", resp.getsOrderDetailsAddr());
        // 订单普通发票相关字段
        model.addAttribute("invoiceComm", resp.getsOrderDetailsComm());
        // 订单增值税发票相关字段
        model.addAttribute("invoiceTax", resp.getsOrderDetailsTax());
        //物流信息相关字段
        model.addAttribute("deliverys", resp.getsOrderDetailsDeliverys());
        //赠品信息相关
        model.addAttribute("gift", resp.getsOrderDetailsGifts());

        if(OrdConstants.CustomerRequestStatus.REQUEST_STATUS_PAY.equals(resp.getsOrderDetailsMain().getStatus())){
            String b = BaseParamUtil.fetchParamValue("ORD_PAY_LIMIT","0");
            int limitHour = Integer.valueOf(b); 
            long hour = limitHour * 3600000 -(DateUtil.getSysDate().getTime() - resp.getsOrderDetailsMain().getOrderTime().getTime());
            long hh = hour/3600000;  
            long minutes = hour/60000-hh*60;  
            model.addAttribute("hour",hh);
            model.addAttribute("minutes",minutes);
        }
        

        return "/order/detail/order-detail";
    }
    
    /**
     * 查询订单物流信息
     * @param orderId
     * @return
     */
    @RequestMapping(value="/queryExpress/{orderId}")
    public String queryOrderExpress(Model model,@PathVariable String orderId){
    	 List<ROrdExpressDetailsResp> list  = orderExpressRSV.queryOrderExpressDetailList(orderId);        
    	 model.addAttribute("ordExpressDetailsResp", list);
    	 return "/order/detail/order-express";
    }
    
    /**
     * 查询app访问订单物流信息
     * @param orderId
     * @return
     */
    @RequestMapping(value="/queryAppExpress/{sessionKey}/{orderId}")
    public String queryOrderExpress(Model model,@PathVariable String sessionKey,@PathVariable String orderId){
    	try{
    		 String redisKey = CacheUtil.getItem(OrdConstant.ORDER_SESSION_KEY_PREFIX+sessionKey).toString();
			 if(!redisKey.equals(sessionKey)){				 
				 throw new BusinessException("error"); 
			 }
			 CacheUtil.delItem(OrdConstant.ORDER_SESSION_KEY_PREFIX+sessionKey);
			 List<ROrdExpressDetailsResp> list  = orderExpressRSV.queryOrderExpressDetailList(orderId);   
			 if(list==null||list.size()<1){
				 LogUtil.info(MODULE, "没有该订单发货数据!");
		    	 throw new Exception("没有该订单发货数据!"); 	  
			 }
			 model.addAttribute("ordExpressDetailsResp", list);
			 return "/order/detail/ord-express-body";
    	}catch(BusinessException e){
    		LogUtil.info(MODULE, "非法访问!");
    		return "/order/detail/order-express-error";	    
    	}catch(Exception e){
    		 LogUtil.info(MODULE, "获取物流信息失败");
    		 return "/order/detail/order-express-error";    	 
    	}
    }

}
