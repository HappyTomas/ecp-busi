package com.zengshi.ecp.app.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Pay004Req;
import com.zengshi.ecp.app.resp.Pay004Resp;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRequestData;
import com.zengshi.ecp.order.dubbo.dto.pay.PaymentRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdPayRelRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPaymentRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

import scala.actors.threadpool.Arrays;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 支付请求<br>
 * Date:2016年3月5日上午10:33:14  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
@Service("pay004")
@Action(bizcode = "pay004", userCheck = true)
@Scope("prototype")
public class Pay004Action extends AppBaseAction<Pay004Req, Pay004Resp> {
    
    @Resource
    private IPaymentRSV paymentRSV;
    
    @Resource
    private IOrdMainRSV ordMainRSV;
    
    @Resource
    private IOrdPayRelRSV ordPayRelRSV;
    
    private static final String MODULE = Pay004Action.class.getName();

    @SuppressWarnings("unchecked")
	@Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
    	
    	// 多个订单合成一个订单去支付
    	ROrdPayRelReq reqDTO = new ROrdPayRelReq();
    	String orderId = this.request.getOrderId();
    	if(StringUtil.isBlank(orderId)){
    		LogUtil.error(MODULE, "[发起支付请求失败]异常信息: 订单ID参数为空");
    		this.response.setStatus(false);
    		this.response.setMsg("[发起支付请求失败]异常信息: 订单ID参数为空");
            return;
    	}
    	String[] orderIdArry = orderId.split(",");
        reqDTO.setOrderIdList(Arrays.asList(orderIdArry));
        ROrdPayRelResp respDto = ordPayRelRSV.saveOrdPayRel(reqDTO);
        if(respDto == null || StringUtil.isBlank(respDto.getJoinOrderid())){
        	LogUtil.error(MODULE, "[发起支付请求失败]异常信息: 保存合并支付订单表失败");
        	this.response.setStatus(false);
        	this.response.setMsg("[发起支付请求失败]异常信息: 保存合并支付订单表失败");
            return;
        }
    	
        PaymentRequest paymentdto = new PaymentRequest();
        paymentdto.setOrderId(respDto.getJoinOrderid());
        paymentdto.setPayWay(this.request.getPayWay());
        try{
            ROrderDetailsRequest rdor = new ROrderDetailsRequest();
            rdor.setOrderId(respDto.getJoinOrderid());
            rdor.setOper("01");
            ROrdCartsChkResponse resp = ordMainRSV.queryOrdOperChk(rdor);         
            this.response.setStatus(resp.isStatus());
            this.response.setMsg(resp.getMsg());
            if(resp.isStatus()==true){
                Map<String, String> extendProps = new HashMap<String, String>();
                extendProps.put("clientIp", this.request.getClientIp());
                extendProps.put("appflag","1");
                PayRequestData payRequestData = paymentRSV.requestPayment(paymentdto, extendProps);
                this.response.setActionUrl(payRequestData.getActionUrl());
                this.response.setAppActionUrl(payRequestData.getAppActionUrl());
                this.response.setMethod(payRequestData.getMethod());
                this.response.setCharset(payRequestData.getCharset());
                this.response.setFormData(payRequestData.getFormData());
                this.response.setStatus(true);
                this.response.setCerPassword(payRequestData.getCerPassword());
                this.response.setJoinOrdId(respDto.getJoinOrderid());
                this.response.setMsg("发起支付请求成功!");
            }
        }catch(Exception e){
            LogUtil.error(MODULE, "[发起支付请求失败]异常信息:" + e.getMessage(),e);
            this.response.setStatus(false);
            this.response.setMsg("[发起支付请求失败]异常信息:" + e.getMessage());
        } 
    }

}
