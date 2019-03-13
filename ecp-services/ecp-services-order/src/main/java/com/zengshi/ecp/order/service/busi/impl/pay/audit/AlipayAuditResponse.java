package com.zengshi.ecp.order.service.busi.impl.pay.audit;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.zengshi.ecp.order.dubbo.dto.pay.BasePayResponse;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.paas.utils.Md5Util;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付宝对账实体类<br>
 * Date:2015年11月14日下午3:09:13  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class AlipayAuditResponse extends BasePayResponse {

    private static final long serialVersionUID = 3983965335093108417L;
    
    private boolean is_succes;
    private String sign;
    private String sign_type;
    private Response response = new Response();
    private String signData;
    private String error;
    /**
     * 支付宝对账响应报文，如果是多个页面就把每次请求返回的报文拼接起来
     */
    private String xmlStr;
    
    @Override
    public void buildSelf(Map<String, String> responseMap) throws Exception {
        String responseStr = responseMap.get(PayStatus.FROM_AIP_PaymentResult);
        Document document = DocumentHelper.parseText(responseStr);
        Element alipayWrapper = (Element) document.getRootElement().selectSingleNode("/alipay");
        if(alipayWrapper!=null){
            String is_succes = alipayWrapper.elementText("is_success"); 
            String sign = alipayWrapper.elementText("sign"); 
            String sign_type = alipayWrapper.elementText("sign_type");
            if("T".equals(is_succes)){
                this.is_succes = true;
            }else{
                this.is_succes = false;
            }
            this.sign = sign;
            this.sign_type = sign_type;
        }else{
            return;
        }
        
        if(this.is_succes){
            Element responseWrapper = (Element) document.getRootElement().selectSingleNode("//alipay/response/account_page_query_result");
            if(responseWrapper!=null){
                String hasNextPage = responseWrapper.elementText("has_next_page"); 
                String pageNo = responseWrapper.elementText("page_no");
                String pageSize = responseWrapper.elementText("page_size");
                Element accountLogList = responseWrapper.element("account_log_list");
                OutputFormat format = new OutputFormat();     // expand <xxx /> to <xxx></xxx>
//                format.setExpandEmptyElements(true);   //一定要设置     
                StringWriter accountLogListContent = new StringWriter();
                XMLWriter writer = new XMLWriter(accountLogListContent, format);
                writer.write(accountLogList);
                String payload = String.format("account_log_list=%s&has_next_page=%s&page_no=%s&page_size=%s",accountLogListContent, hasNextPage, pageNo, pageSize);     
                this.signData = payload;
                
                Response response = new Response();
                if("T".equals(hasNextPage)){
                    response.setHas_next_page(true);
                }else{
                    response.setHas_next_page(false);
                }
                response.setPage_no(pageNo);
                response.setPage_size(pageSize);
                List<AccountQueryAccountLogVO> account_log_list = new ArrayList<AlipayAuditResponse.AccountQueryAccountLogVO>();
                List<Element> list = (List<Element>)accountLogList.elements("AccountQueryAccountLogVO");
                for(Element accountQueryAccountLogVO:list){
                    AccountQueryAccountLogVO vo = new AccountQueryAccountLogVO();
                    String balance = accountQueryAccountLogVO.elementText("balance");
                    String bank_account_name = accountQueryAccountLogVO.elementText("bank_account_name");
                    String bank_account_no = accountQueryAccountLogVO.elementText("bank_account_no");
                    String bank_name = accountQueryAccountLogVO.elementText("bank_name");
                    String buyer_account = accountQueryAccountLogVO.elementText("buyer_account");
                    String currency = accountQueryAccountLogVO.elementText("currency");
                    String deposit_bank_no = accountQueryAccountLogVO.elementText("deposit_bank_no");
                    String goods_title = accountQueryAccountLogVO.elementText("goods_title");
                    String income = accountQueryAccountLogVO.elementText("income");
                    String iw_account_log_id = accountQueryAccountLogVO.elementText("iw_account_log_id");
                    String memo = accountQueryAccountLogVO.elementText("memo");
                    String merchant_out_order_no = accountQueryAccountLogVO.elementText("merchant_out_order_no");
                    String other_account_email = accountQueryAccountLogVO.elementText("other_account_email");
                    String other_account_fullname = accountQueryAccountLogVO.elementText("other_account_fullname");
                    String other_user_id = accountQueryAccountLogVO.elementText("other_user_id");
                    String outcome = accountQueryAccountLogVO.elementText("outcome");
                    String partner_id = accountQueryAccountLogVO.elementText("partner_id");
                    String seller_account = accountQueryAccountLogVO.elementText("seller_account");
                    String seller_fullname = accountQueryAccountLogVO.elementText("seller_fullname");
                    String service_fee = accountQueryAccountLogVO.elementText("service_fee");
                    String service_fee_ratio = accountQueryAccountLogVO.elementText("service_fee_ratio");
                    String total_fee = accountQueryAccountLogVO.elementText("total_fee");
                    String trade_no = accountQueryAccountLogVO.elementText("trade_no");
                    String trade_refund_amount = accountQueryAccountLogVO.elementText("trade_refund_amount");
                    String trans_account = accountQueryAccountLogVO.elementText("trans_account");
                    String trans_code_msg = accountQueryAccountLogVO.elementText("trans_code_msg");
                    String trans_date = accountQueryAccountLogVO.elementText("trans_date");
                    String trans_out_order_no = accountQueryAccountLogVO.elementText("trans_out_order_no");
                    String sub_trans_code_msg = accountQueryAccountLogVO.elementText("sub_trans_code_msg");
                    String sign_product_name = accountQueryAccountLogVO.elementText("sign_product_name");
                    String rate = accountQueryAccountLogVO.elementText("rate");
                    vo.setBalance(balance);
                    vo.setBank_account_name(bank_account_name);
                    vo.setBank_name(bank_name);
                    vo.setBank_account_no(bank_account_no);
                    vo.setBuyer_account(buyer_account);
                    vo.setCurrency(currency);
                    vo.setDeposit_bank_no(deposit_bank_no);
                    vo.setGoods_title(goods_title);
                    vo.setIncome(income);
                    vo.setIw_account_log_id(iw_account_log_id);
                    vo.setMemo(memo);
                    vo.setMerchant_out_order_no(merchant_out_order_no);
                    vo.setOther_account_email(other_account_email);
                    vo.setOther_account_fullname(other_account_fullname);
                    vo.setOther_user_id(other_user_id);
                    vo.setOutcome(outcome);
                    vo.setPartner_id(partner_id);
                    vo.setRate(rate);
                    vo.setSeller_account(seller_account);
                    vo.setSeller_fullname(seller_fullname);
                    vo.setService_fee(service_fee);
                    vo.setService_fee_ratio(service_fee_ratio);
                    vo.setSign_product_name(sign_product_name);
                    vo.setSub_trans_code_msg(sub_trans_code_msg);
                    vo.setTotal_fee(total_fee);
                    vo.setTrade_no(trade_no);
                    vo.setTrade_refund_amount(trade_refund_amount);
                    vo.setTrans_account(trans_account);
                    vo.setTrans_code_msg(trans_code_msg);
                    vo.setTrans_date(trans_date);
                    vo.setTrans_out_order_no(trans_out_order_no);
                    account_log_list.add(vo);
                }
                response.setAccount_log_list(account_log_list);
                this.response = response;
            }
        }else{
            String error = alipayWrapper.elementText("error");
            this.error = error;
        }
    }
    
    @Override
    public boolean verifySign(String[] signParams, String checkValue) throws Exception {
        String key = signParams[0];
        String text = this.signData+key;
        //获得签名验证结果
        return Md5Util.isPasswordValid(checkValue, text);
    }
    
    public class Response{
        private boolean has_next_page;
        private String page_no;
        private String page_size;
        private List<AccountQueryAccountLogVO> account_log_list;
        public boolean getHas_next_page() {
            return has_next_page;
        }
        public void setHas_next_page(boolean has_next_page) {
            this.has_next_page = has_next_page;
        }
        public String getPage_no() {
            return page_no;
        }
        public void setPage_no(String page_no) {
            this.page_no = page_no;
        }
        public String getPage_size() {
            return page_size;
        }
        public void setPage_size(String page_size) {
            this.page_size = page_size;
        }
        public List<AccountQueryAccountLogVO> getAccount_log_list() {
            return account_log_list;
        }
        public void setAccount_log_list(List<AccountQueryAccountLogVO> account_log_list) {
            this.account_log_list = account_log_list;
        }
        
    }
    
    public class AccountQueryAccountLogVO{
        private String balance;//余额 
        private String bank_account_name;//银行账户名 字 
        private String bank_account_no;//银行账号 
        private String bank_name;//银行名称
        private String buyer_account;//买家支付宝 人民币资金 账号 
        private String currency;//货币代码
        private String deposit_bank_no;//充值网银流 水号 
        private String goods_title;//商品名称 
        private String income;//收入金额 
        private String iw_account_log_id;//账务序列号 
        private String memo;//备注 
        private String merchant_out_order_no;//商户订单号 
        private String other_account_email;//账务对方邮 箱 
        private String other_account_fullname;//账务对方全 称 
        private String other_user_id;//账务对方支 付宝用户号 
        private String outcome;//支出金额 
        private String partner_id;//合作者身份 ID 
        private String seller_account;//卖家支付宝 人民币资金 账号 
        private String seller_fullname;//卖家姓名 
        private String service_fee;//交易服务费
        private String service_fee_ratio;//交易服务费 率
        private String total_fee;//交易总金额 
        private String trade_no;//支付宝交易 号 
        private String trade_refund_amount;//累积退款金 额 
        private String trans_account;//账务本方支 付宝人民币 资金账号 
        private String trans_code_msg;//业务类型
        private String trans_date ;//交易付款时间
        private String trans_out_order_no;//订单号 
        private String sub_trans_code_msg;//子业务类型
        private String sign_product_name;//签约产品 
        private String rate;//费率
        public String getBalance() {
            return balance;
        }
        public void setBalance(String balance) {
            this.balance = balance;
        }
        public String getBank_account_name() {
            return bank_account_name;
        }
        public void setBank_account_name(String bank_account_name) {
            this.bank_account_name = bank_account_name;
        }
        public String getBank_account_no() {
            return bank_account_no;
        }
        public void setBank_account_no(String bank_account_no) {
            this.bank_account_no = bank_account_no;
        }
        public String getBank_name() {
            return bank_name;
        }
        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }
        public String getBuyer_account() {
            return buyer_account;
        }
        public void setBuyer_account(String buyer_account) {
            this.buyer_account = buyer_account;
        }
        public String getCurrency() {
            return currency;
        }
        public void setCurrency(String currency) {
            this.currency = currency;
        }
        public String getDeposit_bank_no() {
            return deposit_bank_no;
        }
        public void setDeposit_bank_no(String deposit_bank_no) {
            this.deposit_bank_no = deposit_bank_no;
        }
        public String getGoods_title() {
            return goods_title;
        }
        public void setGoods_title(String goods_title) {
            this.goods_title = goods_title;
        }
        public String getIncome() {
            return income;
        }
        public void setIncome(String income) {
            this.income = income;
        }
        public String getIw_account_log_id() {
            return iw_account_log_id;
        }
        public void setIw_account_log_id(String iw_account_log_id) {
            this.iw_account_log_id = iw_account_log_id;
        }
        public String getMemo() {
            return memo;
        }
        public void setMemo(String memo) {
            this.memo = memo;
        }
        public String getMerchant_out_order_no() {
            return merchant_out_order_no;
        }
        public void setMerchant_out_order_no(String merchant_out_order_no) {
            this.merchant_out_order_no = merchant_out_order_no;
        }
        public String getOther_account_email() {
            return other_account_email;
        }
        public void setOther_account_email(String other_account_email) {
            this.other_account_email = other_account_email;
        }
        public String getOther_account_fullname() {
            return other_account_fullname;
        }
        public void setOther_account_fullname(String other_account_fullname) {
            this.other_account_fullname = other_account_fullname;
        }
        public String getOther_user_id() {
            return other_user_id;
        }
        public void setOther_user_id(String other_user_id) {
            this.other_user_id = other_user_id;
        }
        public String getOutcome() {
            return outcome;
        }
        public void setOutcome(String outcome) {
            this.outcome = outcome;
        }
        public String getPartner_id() {
            return partner_id;
        }
        public void setPartner_id(String partner_id) {
            this.partner_id = partner_id;
        }
        public String getSeller_account() {
            return seller_account;
        }
        public void setSeller_account(String seller_account) {
            this.seller_account = seller_account;
        }
        public String getSeller_fullname() {
            return seller_fullname;
        }
        public void setSeller_fullname(String seller_fullname) {
            this.seller_fullname = seller_fullname;
        }
        public String getService_fee() {
            return service_fee;
        }
        public void setService_fee(String service_fee) {
            this.service_fee = service_fee;
        }
        public String getService_fee_ratio() {
            return service_fee_ratio;
        }
        public void setService_fee_ratio(String service_fee_ratio) {
            this.service_fee_ratio = service_fee_ratio;
        }
        public String getTotal_fee() {
            return total_fee;
        }
        public void setTotal_fee(String total_fee) {
            this.total_fee = total_fee;
        }
        public String getTrade_no() {
            return trade_no;
        }
        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }
        public String getTrade_refund_amount() {
            return trade_refund_amount;
        }
        public void setTrade_refund_amount(String trade_refund_amount) {
            this.trade_refund_amount = trade_refund_amount;
        }
        public String getTrans_account() {
            return trans_account;
        }
        public void setTrans_account(String trans_account) {
            this.trans_account = trans_account;
        }
        public String getTrans_code_msg() {
            return trans_code_msg;
        }
        public void setTrans_code_msg(String trans_code_msg) {
            this.trans_code_msg = trans_code_msg;
        }
        public String getTrans_date() {
            return trans_date;
        }
        public void setTrans_date(String trans_date) {
            this.trans_date = trans_date;
        }
        public String getTrans_out_order_no() {
            return trans_out_order_no;
        }
        public void setTrans_out_order_no(String trans_out_order_no) {
            this.trans_out_order_no = trans_out_order_no;
        }
        public String getSub_trans_code_msg() {
            return sub_trans_code_msg;
        }
        public void setSub_trans_code_msg(String sub_trans_code_msg) {
            this.sub_trans_code_msg = sub_trans_code_msg;
        }
        public String getSign_product_name() {
            return sign_product_name;
        }
        public void setSign_product_name(String sign_product_name) {
            this.sign_product_name = sign_product_name;
        }
        public String getRate() {
            return rate;
        }
        public void setRate(String rate) {
            this.rate = rate;
        }
        
        @Override
        public String toString(){
            return JSONObject.fromObject(this).toString();
        }
        
    }

    public boolean getIs_succes() {
        return is_succes;
    }

    public void setIs_succes(boolean is_succes) {
        this.is_succes = is_succes;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getSignData() {
        return signData;
    }

    public void setSignData(String signData) {
        this.signData = signData;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getXmlStr() {
        return xmlStr;
    }

    public void setXmlStr(String xmlStr) {
        this.xmlStr = xmlStr;
    }

}