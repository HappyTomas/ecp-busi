package com.zengshi.ecp.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zengshi.ecp.base.controller.EcpBaseController;

@Controller
@RequestMapping("/app/pay")
public class PayTransferController extends EcpBaseController{
    
    private static Logger logger = LoggerFactory.getLogger(PayTransferController.class);
    
    @RequestMapping(value = "/transfer",produces="text/html;charset=UTF-8",method={RequestMethod.GET})
    public String appPayTransfer(Model model, @RequestParam("orderId") String orderId,@RequestParam("payWay") String payWay) {
        model.addAttribute("orderId", orderId);
        model.addAttribute("payWay", payWay);
        return "/app/order/pay-transfer";
    }
}

