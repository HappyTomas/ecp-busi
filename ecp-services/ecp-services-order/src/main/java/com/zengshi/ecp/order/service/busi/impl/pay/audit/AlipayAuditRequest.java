package com.zengshi.ecp.order.service.busi.impl.pay.audit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.zengshi.ecp.order.dubbo.dto.pay.BasePayRequest;
import com.zengshi.paas.utils.Md5Util;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付宝请求实体类<br>
 * Date:2015年10月29日下午2:38:21  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class AlipayAuditRequest extends BasePayRequest {

    private static final long serialVersionUID = 6791569774196644149L;
   
    private String service = "account.page.query";
    private String partner;
    private String _input_charset;
    private String sign_type ;
    private String sign ;
    private String page_no ;
    private String gmt_start_time ;
    private String gmt_end_time ;
    private String logon_id;
    private String iw_account_log_id ;
    private String trade_no;
    private String merchant_out_order_no;
    private String deposit_bank_no;
    private String page_size;
    private String trans_code ;

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
        result.put("page_no", page_no);
        result.put("gmt_start_time", gmt_start_time);
        result.put("gmt_end_time", gmt_end_time);
        result.put("logon_id", logon_id);
        result.put("iw_account_log_id", iw_account_log_id);
        result.put("trade_no", trade_no);
        result.put("merchant_out_order_no", merchant_out_order_no);
        result.put("deposit_bank_no", deposit_bank_no);
        result.put("page_size", page_size);
        result.put("trans_code", trans_code);
        
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
    
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }
    
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
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

    public String getPage_no() {
        return page_no;
    }

    public void setPage_no(String page_no) {
        this.page_no = page_no;
    }

    public String getGmt_start_time() {
        return gmt_start_time;
    }

    public void setGmt_start_time(String gmt_start_time) {
        this.gmt_start_time = gmt_start_time;
    }

    public String getGmt_end_time() {
        return gmt_end_time;
    }

    public void setGmt_end_time(String gmt_end_time) {
        this.gmt_end_time = gmt_end_time;
    }

    public String getLogon_id() {
        return logon_id;
    }

    public void setLogon_id(String logon_id) {
        this.logon_id = logon_id;
    }

    public String getIw_account_log_id() {
        return iw_account_log_id;
    }

    public void setIw_account_log_id(String iw_account_log_id) {
        this.iw_account_log_id = iw_account_log_id;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getMerchant_out_order_no() {
        return merchant_out_order_no;
    }

    public void setMerchant_out_order_no(String merchant_out_order_no) {
        this.merchant_out_order_no = merchant_out_order_no;
    }

    public String getDeposit_bank_no() {
        return deposit_bank_no;
    }

    public void setDeposit_bank_no(String deposit_bank_no) {
        this.deposit_bank_no = deposit_bank_no;
    }

    public String getPage_size() {
        return page_size;
    }

    public void setPage_size(String page_size) {
        this.page_size = page_size;
    }

    public String getTrans_code() {
        return trans_code;
    }

    public void setTrans_code(String trans_code) {
        this.trans_code = trans_code;
    }

    @Override
    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
    
}