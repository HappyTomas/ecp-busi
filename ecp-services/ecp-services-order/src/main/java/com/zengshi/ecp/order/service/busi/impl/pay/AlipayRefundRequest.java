package com.zengshi.ecp.order.service.busi.impl.pay;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.zengshi.ecp.order.dubbo.dto.pay.BasePayRequest;
import com.zengshi.paas.utils.Md5Util;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付宝退款请求实体类<br>
 * Date:2015年10月29日下午2:38:21  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class AlipayRefundRequest extends BasePayRequest {

    private static final long serialVersionUID = 6791569774196644149L;
   
    private String service = "refund_fastpay_by_platform_pwd";
    private String partner;
    private String _input_charset;
    private String sign_type ;
    private String sign ;
    private String notify_url ;
    private String seller_email  ;
    private String seller_user_id ;
    private String refund_date;
    private String batch_no  ;
    private String batch_num;
    private String detail_data ;
    
   

    @Override
    public String buildSign(String[] signParams) {
        String text = signParams[0];
        String key = signParams[1];
        text = text + key;
        return Md5Util.encode(text);
    }
    
    @Override
    public Map<String, String> toMap() {
        Map<String,String> result =new HashMap<String,String>();
        result.put("service", service);
        result.put("partner", partner);
        result.put("_input_charset", _input_charset);
        result.put("sign_type", sign_type);
        result.put("sign", sign);
        result.put("notify_url", notify_url);
        result.put("seller_email", seller_email);
        result.put("seller_user_id", seller_user_id);
        result.put("refund_date", refund_date);
        result.put("batch_no", batch_no);
        result.put("batch_num", batch_num);
        result.put("detail_data", detail_data);
        
        Map<String,String> map =new HashMap<String,String>();
        for (String key : result.keySet()) {
            String value = result.get(key);
            if (value == null || value.equals("")) {
                continue;
            }
            map.put(key, value);
        }
        return map;
    }
    
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String get_input_charset() {
        return _input_charset;
    }

    public void set_input_charset(String _input_charset) {
        this._input_charset = _input_charset;
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

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getSeller_email() {
        return seller_email;
    }

    public void setSeller_email(String seller_email) {
        this.seller_email = seller_email;
    }

    public String getSeller_user_id() {
        return seller_user_id;
    }

    public void setSeller_user_id(String seller_user_id) {
        this.seller_user_id = seller_user_id;
    }

    public String getRefund_date() {
        return refund_date;
    }

    public void setRefund_date(String refund_date) {
        this.refund_date = refund_date;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public String getBatch_num() {
        return batch_num;
    }

    public void setBatch_num(String batch_num) {
        this.batch_num = batch_num;
    }

    public String getDetail_data() {
        return detail_data;
    }

    public void setDetail_data(String detail_data) {
        this.detail_data = detail_data;
    }

    @Override
    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
    
}