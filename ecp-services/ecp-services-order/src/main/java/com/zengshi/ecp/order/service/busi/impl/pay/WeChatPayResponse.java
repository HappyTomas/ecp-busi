/**
 * Project Name:ecp-services-order-server 
 * File Name:ＷeChatScanCodeResponse.java 
 * Package Name:com.zengshi.ecp.order.service.impl.pay 
 * Date:2016年4月13日下午7:59:55 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 *
 */
package com.zengshi.ecp.order.service.busi.impl.pay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zengshi.ecp.order.dubbo.dto.pay.BasePayResponse;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 微信扫码响应报文<br>
 * Date:2016年4月13日下午7:59:55  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 *
 * @author chenjf6
 * @version
 * @since JDK 1.7 
 */
public class WeChatPayResponse extends BasePayResponse{

    private static final long serialVersionUID = -7888758629351445527L;
    /**
     * 返回状态码
     */
    private String return_code;
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
     * 用户标识
     */
    private String openid;
    /**
     * 交易类型
     */
    private String trade_type;
    /**
     * 付款银行
     */
    private String bank_type;
    /**
     * 总金额
     */
    private String total_fee;
    /**
     * 现金支付金额
     */
    private String cash_fee;
    /**
     * 微信支付订单号
     */
    private String transaction_id;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    
    /**
     * 退款交易流水号
     */
    private String out_refund_no;
    
    /**
     * 支付完成时间
     */
    private String time_end;
    /**
     * 返回信息
     */
    private String return_msg;
    /**
     * 微信退款单号
     */
    private String refund_id;
    /**
     * 退款金额
     */
    private String refund_fee;
    /**
     * 操作员
     */
    private String op_user_id;
    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getAppid() {
        return appid;
    }

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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(String cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
    
    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public static String createLinkString(Map<String, String> params)  {

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

    @Override
    public void buildSelf(Map<String, String> responseMap) throws Exception {
        this.setReturn_code(responseMap.get("return_code"));
        this.setAppid(responseMap.get("appid"));
        this.setMch_id(responseMap.get("mch_id"));
        this.setSign(responseMap.get("sign"));
        this.setOut_trade_no(responseMap.get("out_trade_no"));
        this.setOpenid(responseMap.get("openid"));
        this.setTrade_type(responseMap.get("trade_type"));
        this.setBank_type(responseMap.get("bank_type"));
        this.setTotal_fee(responseMap.get("total_fee"));
        this.setCash_fee(responseMap.get("cash_fee"));
        this.setTransaction_id(responseMap.get("transaction_id"));
        this.setOut_trade_no(responseMap.get("out_trade_no"));
        this.setOut_refund_no(responseMap.get("out_refund_no"));
        this.setTime_end(responseMap.get("out_trade_no"));
        this.setReturn_msg(responseMap.get("return_msg"));
        this.setRefund_id(responseMap.get("refund_id"));
        this.setRefund_fee(responseMap.get("refund_fee"));
        this.setOp_user_id(responseMap.get("op_user_id"));
    }
    
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getOp_user_id() {
        return op_user_id;
    }

    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }

}

