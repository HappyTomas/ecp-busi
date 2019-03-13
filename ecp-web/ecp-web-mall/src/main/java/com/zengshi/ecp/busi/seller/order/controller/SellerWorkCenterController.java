package com.zengshi.ecp.busi.seller.order.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.busi.order.vo.ROrderBackReqVO;
import com.zengshi.ecp.busi.order.vo.RQueryOrderReqVO;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderSummaryResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.RShopOrderResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdBackGdsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdBackMoneyRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdManageRSV;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016-5-4上午9:54:28  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 卖家工作台服务接口
 */
@Controller
@RequestMapping(value="/seller/workcenter")
public class SellerWorkCenterController {
    
    private final static String MODULE = SellerWorkCenterController.class.getName();
    
    @Resource 
    private IOrdMainRSV ordMainRSV;
    @Resource
    private IOrdBackMoneyRSV ordBackMoneyRSV;
    @Resource
    private IOrdBackGdsRSV ordBackGdsRSV;   
    @Resource
    private IOrdManageRSV ordManageRSV;
    
    
    /**
     * 
     * payList:(获取待付款数量). <br/> 
     * 
     * @author PJieWin 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="payList")
    @ResponseBody
    public Long payList(Model model,@RequestParam Long shopId) throws Exception{
        RQueryOrderRequest rQueryOrderRequest = new RQueryOrderRequest();
        rQueryOrderRequest.setShopId(shopId);
        rQueryOrderRequest.setSysType("00");
        rQueryOrderRequest.setStatus(OrdConstants.CustomerRequestStatus.REQUEST_STATUS_PAY);
        //默认查询一年的记录
        rQueryOrderRequest.setBegDate(new Timestamp(DateUtils.addYears(new Date(), -1).getTime()));
        rQueryOrderRequest.setEndDate(new Timestamp(DateUtils.addYears(new Date(), 0).getTime()));

        ROrderSummaryResponse  t = ordManageRSV.queryOrderSummaryData(rQueryOrderRequest); 
        /*
        RQueryOrderReqVO orderReqVo = new RQueryOrderReqVO();
        orderReqVo.setShopId(shopId);
        orderReqVo.setPageSize(-1);
        orderReqVo.setPageNumber(-1);
        LogUtil.debug(MODULE, orderReqVo.toString());
        //后场服务所需要的DTO；
        RQueryOrderRequest rdor = orderReqVo.toBaseInfo(RQueryOrderRequest.class);
        String status = OrdConstants.CustomerRequestStatus.REQUEST_STATUS_PAY;
        rdor.setShopId(orderReqVo.getShopId());
        rdor.setSysType("00");
        rdor.setStatus(status); // 
        
        ObjectCopyUtil.copyObjValue(orderReqVo, rdor, "", false);
        PageResponseDTO<RShopOrderResponse> rdors = this.ordMainRSV.queryOrderByShopId(rdor);
        if(rdors==null){
            rdors = new PageResponseDTO<RShopOrderResponse>();
            rdors.setPageSize(1);
        }
        */
        return t.getOrderCount();  
    }
    
    /**
     * 
     * sendList:(获取待发货数量). <br/> 
     * 
     * @author PJieWin 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="sendList")
    @ResponseBody
    public Long sendList(Model model,@RequestParam Long shopId) throws Exception{
        RQueryOrderReqVO orderReqVo = new RQueryOrderReqVO();
        orderReqVo.setShopId(shopId);
        orderReqVo.setPageSize(0);
        orderReqVo.setPageNumber(0);
        LogUtil.debug(MODULE, orderReqVo.toString());
        //后场服务所需要的DTO；
        RQueryOrderRequest rdor = orderReqVo.toBaseInfo(RQueryOrderRequest.class);
        
        String status = OrdConstants.ShopRequestStatus.REQUEST_STATUS_SEND;
        rdor.setShopId(orderReqVo.getShopId());
        rdor.setSysType("00");
        rdor.setStatus(status); // 
        
        ObjectCopyUtil.copyObjValue(orderReqVo, rdor, "", false);
        PageResponseDTO<RShopOrderResponse> rdors = this.ordMainRSV.queryOrderByShopId(rdor);
        if(rdors==null){
            rdors = new PageResponseDTO<RShopOrderResponse>();
            rdors.setPageSize(1);
        }
        return rdors.getCount();
    }
    /**
     * 
     * moneyList:(获取退款数量). <br/> 
     * 
     * @author PJieWin 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="moneyList")
    @ResponseBody
    public Long moneyList(Model model,@RequestParam Long shopId) throws Exception{
        ROrderBackReqVO backReqVo = new ROrderBackReqVO();
        backReqVo.setShopId(shopId);
        backReqVo.setPageSize(0);
        backReqVo.setPageNumber(0);
         LogUtil.debug(MODULE, backReqVo.toString());
         //后场服务所需要的DTO；
         ROrderBackReq dto = backReqVo.toBaseInfo(ROrderBackReq.class);
         ObjectCopyUtil.copyObjValue(backReqVo, dto, "", false);
         dto.setStatus("00");
         PageResponseDTO<ROrderBackResp> resp = ordBackMoneyRSV.queryBackMoneyByShop(dto);
         if(resp==null){
             resp = new PageResponseDTO<ROrderBackResp>();
             resp.setPageSize(1);
         } 
        return resp.getCount();
    }
    /**
     * 
     * returnList:(获取退货数量). <br/> 
     * 
     * @author PJieWin 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="returnList")
    @ResponseBody
    public Long returnList(Model model,@RequestParam Long shopId) throws Exception{
        ROrderBackReqVO backReqVo = new ROrderBackReqVO();
        backReqVo.setShopId(shopId);
        backReqVo.setPageSize(0);
        backReqVo.setPageNumber(0);
         LogUtil.debug(MODULE, backReqVo.toString());
         //后场服务所需要的DTO；
         ROrderBackReq dto = backReqVo.toBaseInfo(ROrderBackReq.class);
         ObjectCopyUtil.copyObjValue(backReqVo, dto, "", false);
         dto.setStatus("00");
         PageResponseDTO<ROrderBackResp> resp = ordBackGdsRSV.queryBackGdsByShop(dto);
         if(resp==null){
             resp = new PageResponseDTO<ROrderBackResp>();
            resp.setPageSize(1);
         }
         
        return resp.getCount();
    }
    
}

