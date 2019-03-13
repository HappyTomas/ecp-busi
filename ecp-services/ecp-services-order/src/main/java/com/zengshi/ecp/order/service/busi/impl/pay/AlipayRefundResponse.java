package com.zengshi.ecp.order.service.busi.impl.pay;
import java.util.Map;

import com.zengshi.ecp.order.dubbo.dto.pay.BasePayResponse;
import com.zengshi.paas.utils.Md5Util;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付宝退款通知实体类<br>
 * Date:2015年11月14日下午3:09:13  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class AlipayRefundResponse extends BasePayResponse {

    private static final long serialVersionUID = 3983965335093108417L;
    
    private String notify_time;
    private String notify_type;
    private String notify_id;
    private String sign_type;
    private String sign ;
    private String batch_no  ;
    private String success_num ;
    private String result_details;
    private String result_details_transNo;
    private String result_details_refudnAmount;
    private String result_details_refundSatuts;
    private Long ecp_staffId;
    
    @Override
    public void buildSelf(Map<String, String> responseMap) throws Exception {
        this.setNotify_time(responseMap.get("notify_time"));
        this.setNotify_type(responseMap.get("notify_type"));
        this.setNotify_id(responseMap.get("notify_id"));
        this.setSign_type(responseMap.get("sign_type"));
        this.setSign(responseMap.get("sign"));
        this.setBatch_no(responseMap.get("batch_no"));
        this.setSuccess_num(responseMap.get("success_num"));
        this.setResult_details(responseMap.get("result_details"));
        String details = responseMap.get("result_details");
        String[] items = details.split("\\^");
        if(items.length==3){
            this.setResult_details_transNo(items[0]);
            this.setResult_details_refudnAmount(items[1]);
            this.setResult_details_refundSatuts(items[2]);
        }else if(items.length==7){
            
        }
        try{
            this.setEcp_staffId(Long.parseLong(responseMap.get("ecp_staffId")));
        }catch(Exception e){
        }
    }
    
    @Override
    public boolean verifySign(String[] signParams, String checkValue) throws Exception {
        String text = signParams[0];
        String key = signParams[1];
        text = text+key;
        //获得签名验证结果
        return Md5Util.isPasswordValid(checkValue, text);
    }


    public String getNotify_time() {
        return notify_time;
    }


    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }


    public String getNotify_type() {
        return notify_type;
    }


    public void setNotify_type(String notify_type) {
        this.notify_type = notify_type;
    }


    public String getNotify_id() {
        return notify_id;
    }


    public void setNotify_id(String notify_id) {
        this.notify_id = notify_id;
    }


    public String getSign_type() {
        return sign_type;
    }


    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }


    public String getSign() {
        return sign;
    }


    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public String getSuccess_num() {
        return success_num;
    }

    public void setSuccess_num(String success_num) {
        this.success_num = success_num;
    }

    public String getResult_details() {
        return result_details;
    }

    public void setResult_details(String result_details) {
        this.result_details = result_details;
    }

    public String getResult_details_transNo() {
        return result_details_transNo;
    }

    public void setResult_details_transNo(String result_details_transNo) {
        this.result_details_transNo = result_details_transNo;
    }

    public String getResult_details_refudnAmount() {
        return result_details_refudnAmount;
    }

    public void setResult_details_refudnAmount(String result_details_refudnAmount) {
        this.result_details_refudnAmount = result_details_refudnAmount;
    }

    public String getResult_details_refundSatuts() {
        return result_details_refundSatuts;
    }

    public void setResult_details_refundSatuts(String result_details_refundSatuts) {
        this.result_details_refundSatuts = result_details_refundSatuts;
    }

    public Long getEcp_staffId() {
        return ecp_staffId;
    }

    public void setEcp_staffId(Long ecp_staffId) {
        this.ecp_staffId = ecp_staffId;
    }
}