package com.zengshi.ecp.order.service.busi.impl.pay;
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
public class AlipayRequest extends BasePayRequest {

    private static final long serialVersionUID = 6791569774196644149L;
   
    private String service = "create_direct_pay_by_user";
    private String partner;
    private String _input_charset;
    private String sign_type ;
    private String sign ;
    private String notify_url ;
    private String return_url ;
    private String error_notify_url ;
    private String out_trade_no;
    private String subject ;
    private String payment_type;
    private String total_fee;
    private String seller_id;
    private String buyer_id;
    private String seller_email ;
    private String buyer_email;
    private String seller_account_name;
    private String buyer_account_nam;
    private String price;
    private String quantity;
    private String body ;
    private String show_url;
    private String paymethod;
    private String enable_paymethod;
    private String need_ctu_check;
    private String royalty_type;
    private String royalty_parameters ;
    private String anti_phishing_key;
    private String exter_invoke_ip;
    private String extra_common_param;
    private String extend_param ;
    private String it_b_pay;
    private String default_login ;
    private String product_type ;
    private String token ;
    private String item_orders_info  ;
    private String sign_id_ext ;
    private String sign_name_ext  ;
    private String qr_pay_mode ;
    
   

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
        result.put("return_url", return_url);
        result.put("error_notify_url", error_notify_url);
        result.put("out_trade_no", out_trade_no);
        result.put("subject", subject);
        result.put("payment_type", payment_type);
        result.put("total_fee", total_fee);
        result.put("seller_id", seller_id);
        result.put("buyer_id", buyer_id);
        result.put("seller_email", seller_email);
        result.put("buyer_email", buyer_email);
        result.put("seller_account_name", seller_account_name);
        result.put("buyer_account_nam", buyer_account_nam);
        result.put("price", price);
        result.put("quantity", quantity);
        result.put("body", body);
        result.put("show_url", show_url);
        result.put("paymethod", paymethod);
        result.put("enable_paymethod", enable_paymethod);
        result.put("need_ctu_check", need_ctu_check);
        result.put("royalty_type", royalty_type);
        result.put("royalty_parameters", royalty_parameters);
        result.put("anti_phishing_key", anti_phishing_key);
        result.put("exter_invoke_ip", exter_invoke_ip);
        result.put("extra_common_param", extra_common_param);
        result.put("extend_param", extend_param);
        result.put("it_b_pay", it_b_pay);
        result.put("default_login", default_login);
        result.put("product_type", product_type);
        result.put("token", token);
        result.put("item_orders_info", item_orders_info);
        result.put("sign_id_ext", sign_id_ext);
        result.put("sign_name_ext", sign_name_ext);
        result.put("qr_pay_mode", qr_pay_mode);
        
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

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getError_notify_url() {
        return error_notify_url;
    }

    public void setError_notify_url(String error_notify_url) {
        this.error_notify_url = error_notify_url;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getSeller_email() {
        return seller_email;
    }

    public void setSeller_email(String seller_email) {
        this.seller_email = seller_email;
    }

    public String getBuyer_email() {
        return buyer_email;
    }

    public void setBuyer_email(String buyer_email) {
        this.buyer_email = buyer_email;
    }

    public String getSeller_account_name() {
        return seller_account_name;
    }

    public void setSeller_account_name(String seller_account_name) {
        this.seller_account_name = seller_account_name;
    }

    public String getBuyer_account_nam() {
        return buyer_account_nam;
    }

    public void setBuyer_account_nam(String buyer_account_nam) {
        this.buyer_account_nam = buyer_account_nam;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getShow_url() {
        return show_url;
    }

    public void setShow_url(String show_url) {
        this.show_url = show_url;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public String getEnable_paymethod() {
        return enable_paymethod;
    }

    public void setEnable_paymethod(String enable_paymethod) {
        this.enable_paymethod = enable_paymethod;
    }

    public String getNeed_ctu_check() {
        return need_ctu_check;
    }

    public void setNeed_ctu_check(String need_ctu_check) {
        this.need_ctu_check = need_ctu_check;
    }

    public String getRoyalty_type() {
        return royalty_type;
    }

    public void setRoyalty_type(String royalty_type) {
        this.royalty_type = royalty_type;
    }

    public String getRoyalty_parameters() {
        return royalty_parameters;
    }

    public void setRoyalty_parameters(String royalty_parameters) {
        this.royalty_parameters = royalty_parameters;
    }

    public String getAnti_phishing_key() {
        return anti_phishing_key;
    }

    public void setAnti_phishing_key(String anti_phishing_key) {
        this.anti_phishing_key = anti_phishing_key;
    }

    public String getExter_invoke_ip() {
        return exter_invoke_ip;
    }

    public void setExter_invoke_ip(String exter_invoke_ip) {
        this.exter_invoke_ip = exter_invoke_ip;
    }

    public String getExtra_common_param() {
        return extra_common_param;
    }

    public void setExtra_common_param(String extra_common_param) {
        this.extra_common_param = extra_common_param;
    }

    public String getExtend_param() {
        return extend_param;
    }

    public void setExtend_param(String extend_param) {
        this.extend_param = extend_param;
    }

    public String getIt_b_pay() {
        return it_b_pay;
    }

    public void setIt_b_pay(String it_b_pay) {
        this.it_b_pay = it_b_pay;
    }

    public String getDefault_login() {
        return default_login;
    }

    public void setDefault_login(String default_login) {
        this.default_login = default_login;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getItem_orders_info() {
        return item_orders_info;
    }

    public void setItem_orders_info(String item_orders_info) {
        this.item_orders_info = item_orders_info;
    }

    public String getSign_id_ext() {
        return sign_id_ext;
    }

    public void setSign_id_ext(String sign_id_ext) {
        this.sign_id_ext = sign_id_ext;
    }

    public String getSign_name_ext() {
        return sign_name_ext;
    }

    public void setSign_name_ext(String sign_name_ext) {
        this.sign_name_ext = sign_name_ext;
    }

    public String getQr_pay_mode() {
        return qr_pay_mode;
    }

    public void setQr_pay_mode(String qr_pay_mode) {
        this.qr_pay_mode = qr_pay_mode;
    }

    @Override
    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
    
}