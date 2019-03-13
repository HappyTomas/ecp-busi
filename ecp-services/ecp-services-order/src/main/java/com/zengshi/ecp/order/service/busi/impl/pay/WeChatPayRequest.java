/**
 * Project Name:ecp-services-order-server 
 * File Name:ＷeChatScanCodeRequest.java 
 * Package Name:com.zengshi.ecp.order.service.impl.pay 
 * Date:2016年4月13日下午7:31:46 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 *
 */
package com.zengshi.ecp.order.service.busi.impl.pay;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.order.dubbo.dto.pay.BasePayRequest;
import com.zengshi.paas.utils.Md5Util;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: <br>
 * Date:2016年4月13日下午7:31:46  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 *
 * @author wangxq6
 * @version
 * @since JDK 1.7 
 */
public class WeChatPayRequest extends BasePayRequest{

    private static final long serialVersionUID = -8565846289359913205L;
    /**
     * 公众账号ID
     */
    private String appid;
    /**
     * 商户号
     */
    private String mch_id;
    /**
     * 随机字符串
     */
    private String nonce_str;
    /**
     * 签名
     */
    private String sign;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 总金额
     */
    private int total_fee;
    /**
     * 终端IP
     */
    private String spbill_create_ip;
    /**
     * 通知地址
     */
    private String notify_url;
    /**
     * 交易类型
     */
    private String trade_type;
    
    /**
     * 商品ID
     */
    private String product_id;
    
    /**
     * 支付流水号
     */
    private String transaction_id;
    /**
     * 用户退款单号
     */
    private String out_refund_no;
    /**
     * 退款金额
     */
    private String refund_fee;
    
    /**
     * 退款操作工号
     */
    private String openid;
    
    /**
     * 退款操作工号
     */
    private String op_user_id;
    
    public String getAppid() {
        return appid;
    }
    /**
     *
     * setAppid:(openid). <br/>
     *
     * @author chenjf6
     * @param appid
     * @since JDK 1.7
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getMch_id() {
        return mch_id;
    }
    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }
    public String getNonce_str() {
        return nonce_str;
    }
    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getOut_trade_no() {
        return out_trade_no;
    }
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
    public int getTotal_fee() {
        return total_fee;
    }
    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }
    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }
    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }
    public String getNotify_url() {
        return notify_url;
    }
    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }
    public String getTrade_type() {
        return trade_type;
    }
    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
    public String getProduct_id() {
        return product_id;
    }
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    @Override
    public String buildSign(String[] signParams) {
        String text = signParams[0];
        String key = signParams[1];
        text = text + "&key="+key;
        return Md5Util.encode(text).toUpperCase();
    }
    @Override
    public Map<String, String> toMap() {
        Map<String,String> result =new HashMap<String,String>();
        result.put("appid", appid);
        result.put("mch_id", mch_id);
        result.put("nonce_str", nonce_str);
        result.put("body", body);
        result.put("out_trade_no", out_trade_no);
        result.put("notify_url", notify_url);
        result.put("total_fee", total_fee+"");
        result.put("spbill_create_ip", spbill_create_ip);
        result.put("trade_type", trade_type);
        result.put("sign", sign);
        result.put("transaction_id", transaction_id);
        result.put("out_refund_no", out_refund_no);
        result.put("refund_fee", refund_fee);
        result.put("openid", openid);
        result.put("op_user_id", op_user_id);

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
            if (value == null || value.equals("")||value.equals("0")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }
    public static String createLinkString(Map<String, String> params) throws UnsupportedEncodingException {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            //if(key.equals("body")){
            // 	value = URLEncoder.encode(value,"UTF-8");
            // }
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
    public static String createXml(Map<String, String> params)throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        sb.append("<XML>");
        if (params == null || params.size() <= 0) {
            return sb.toString();
        }
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            //if(key.equals("body")){
            //	value = URLEncoder.encode(value,"UTF-8");
            //}
            sb.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
        }
        return sb.append("</XML>").toString();
    }
    public String getTransaction_id() {
        return transaction_id;
    }
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
    public String getOut_refund_no() {
        return out_refund_no;
    }
    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }
    public String getRefund_fee() {
        return refund_fee;
    }
    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getOp_user_id() {
        return op_user_id;
    }
    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }

}

