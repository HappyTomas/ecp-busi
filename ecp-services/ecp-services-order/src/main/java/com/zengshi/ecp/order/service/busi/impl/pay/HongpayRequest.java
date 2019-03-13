package com.zengshi.ecp.order.service.busi.impl.pay;
import java.util.HashMap;
import java.util.Map;

import com.zengshi.ecp.order.dubbo.dto.pay.BasePayRequest;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 鸿支付支付请求实体类<br>
 * Date:2015年10月29日下午2:38:21  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class HongpayRequest extends BasePayRequest {

    private static final long serialVersionUID = 6791569774196644149L;
   
    private String requestPacket;// 9002 只要传这个参数 AsiainfoPayRequestData.getRequestPacket方法返回 XML

    
   
    public String getRequestPacket() {
        return requestPacket;
    }

    public void setRequestPacket(String requestPacket) {
        this.requestPacket = requestPacket;
    }

    @Override
    public String buildSign(String[] signParams) {
//        String requestStr = signParams[0];
//        String path = signParams[1];
//        String pfxPwd = signParams[2];
//        String privateKeyPwd = signParams[3];
//        String sign = SignatureTools.sign(requestStr, "UTF-8", path, pfxPwd, privateKeyPwd);
//        return sign;
        return null;
    }
    
    @Override
    public Map<String, String> toMap() {
        Map<String,String> result =new HashMap<String,String>();
        result.put("requestPacket", requestPacket );
        return result;
    }

    @Override
    public String toString() {
        return "AsiainfoPayRequest [requestPacket =" + requestPacket  + "]";
    }
    
}