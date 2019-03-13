package com.zengshi.ecp.busi.seller.order.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.SellerResDTO;
import com.zengshi.ecp.system.filter.SellerLocaleUtil;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.StringUtil;



@Controller
@RequestMapping(value = "/seller/order/orderdetail")
public class OrderdetailController {
    
    @Resource
    private IOrdMainRSV ordMainRSV;
    
    @Resource
    private IOrdDetailsRSV ordDetailsRSV;

    @RequestMapping(value = "/detail/{orderId}")
    public String queryOrderDetailForSeller(Model model, @PathVariable("orderId") String orderid){
        
        
        ROrderDetailsRequest rdto = new ROrderDetailsRequest();
        if (StringUtil.isBlank(orderid)) {
            throw new BusinessException("order.orderid.null.error");
        }
        rdto.setOrderId(orderid);
        rdto.setOper("00");   //判断订单号是否属于登录店铺
        SellerResDTO sellerResDTO = SellerLocaleUtil.getSeller();
        ROrderDetailsResponse resp = null;
        if(!ordMainRSV.queryShopChkStatus(rdto, sellerResDTO.getShoplist()).isStatus()){

            return "redirect:/homepage";
        }
        resp = ordDetailsRSV.queryOrderDetails(rdto);

        // 订单id
        model.addAttribute("orderId", orderid);
        // 主订单相关字段
        model.addAttribute("sOrderDetailsMain", resp.getsOrderDetailsMain());
        // 子订单相关字段
        model.addAttribute("sOrderDetailsSubs", resp.getsOrderDetailsSubs());
        // 订单优惠相关字段
        model.addAttribute("sOrderDetailsDiscount", resp.getsOrderDetailsDiscount());
        // 订单跟踪相关字段
        model.addAttribute("sOrderDetailsTracks", resp.getsOrderDetailsTracks());
        // 订单收货地址相关字段
        model.addAttribute("sOrderDetailsAddr", resp.getsOrderDetailsAddr());
        // 订单普通发票相关字段
        model.addAttribute("sOrderDetailsComm", resp.getsOrderDetailsComm());
        // 订单增值税发票相关字段
        model.addAttribute("sOrderDetailsTax", resp.getsOrderDetailsTax());
        //物流信息相关字段
        model.addAttribute("sOrderDetailsDeliverys", resp.getsOrderDetailsDeliverys());
        
        // 进度条相关
        Map<String, Integer> status = ParamsTool.getStatusMap();
        List<String> statuslist = ParamsTool.getStatusList();
        model.addAttribute("status", status);
        model.addAttribute("statuslist", statuslist);

        return "/order/detail/order-detail";
    }
}

