package com.zengshi.ecp.order.service.busi.impl.pay.audit;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONObject;

import com.zengshi.ecp.order.dubbo.dto.pay.BasePayResponse;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 鸿支付对账实体类<br>
 * Date:2015年11月14日下午3:09:13  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class HongpayAuditResponse extends BasePayResponse {

    private static final long serialVersionUID = 3983965335093108417L;
    
    private String checkDate;
    private Long totalNum;
    private Long totalTransAmount;
    private String fileContent;
    private List<HongpayAuditVO> hongpayAuditList;
    
    @Override
    public void buildSelf(Map<String, String> responseMap) throws Exception {
        String response = responseMap.get(PayStatus.FROM_AIP_PaymentResult);
        this.fileContent = response;
        StringReader r = new StringReader(response);
        BufferedReader bufferedReader= new BufferedReader(r);
        int line = 0;
        List<HongpayAuditVO> list = new ArrayList<HongpayAuditResponse.HongpayAuditVO>();
        while(bufferedReader.ready()){
            String str = bufferedReader.readLine();
            if(str!=null){
                String[] items = str.split("\\|",12); 
                if (line == 0) {// 首行为日终汇总
                    line++;
                    if(items.length<3){ 
                        this.checkDate = responseMap.get("checkDate");// 清算日期
                        this.totalNum = 0L;// 总记录数
                        this.totalTransAmount = 0L;// 总金额
                        hongpayAuditList = new ArrayList<HongpayAuditResponse.HongpayAuditVO>();
                        return;
                    }else{
                        this.checkDate = items[0];// 清算日期
                        this.totalNum = Long.valueOf(items[1]);// 总记录数
                        this.totalTransAmount = Long.valueOf(items[2]);// 总金额
                    } 
                }else{
                    //对账日期|支付渠道|合作方ID|合作方交易流水|支付平台交易流水|付款方卡号|付款方户名|付款方账户ID|交易类型|交易金额|交易结果|交易时间
                    line++;
                    String transDate = items[0];// 交易日期
                    String payType = items[1];// 支付渠道
                    String mercCode = items[2];// 合作方ID
                    String orderId = items[3];// 合作方交易流水号
                    String transNo = items[4];// 支付平台交易流水号
                    String bankCardId = items[5];// 付款方银行卡号
                    String bankCardName = items[6];// 付款方开户名
                    String payeerAccountId = items[7];// 付款方账户ID
                    String transType = items[8];// 交易类型 :1-代收  2-代付  3-转账  5-网银(B2C) 6-无卡支付 7-网银(B2B) 8-全民付  9-退款
                    String transAmount = items[9];// 交易金额
                    String payResult = items[10];// 交易结果：0（成功），1（失败）
                    String transTime = items[11];// 交易时间
                    HongpayAuditVO vo = new HongpayAuditVO();
                    vo.setTransDate(transDate);
                    vo.setPayType(payType);
                    vo.setMercCode(mercCode);
                    vo.setOrderId(orderId);
                    vo.setTransNo(transNo);
                    vo.setBankCardId(bankCardId);
                    vo.setBankCardName(bankCardName);
                    vo.setPayeerAccountId(payeerAccountId);
                    vo.setTransType(transType);
                    vo.setTransAmount(transAmount);
                    vo.setPayResult(payResult);
                    vo.setTransTime(transTime);
                    list.add(vo);
                } 
            }else{
                break;
            }
        }
        this.hongpayAuditList = list;
    }
    
    public class HongpayAuditVO{
        private String transDate;// 交易日期
        private String payType;// 支付渠道
        private String mercCode;// 合作方ID
        private String orderId;// 合作方交易流水号
        private String transNo;// 支付平台交易流水号
        private String bankCardId;// 付款方银行卡号
        private String bankCardName;// 付款方开户名
        private String payeerAccountId;// 付款方账户ID
        private String transType;// 交易类型
        private String transAmount;// 交易金额
        private String payResult;// 支付结果
        private String transTime;// 交易时间
        
        
        public String getTransDate() {
            return transDate;
        }


        public void setTransDate(String transDate) {
            this.transDate = transDate;
        }


        public String getPayType() {
            return payType;
        }


        public void setPayType(String payType) {
            this.payType = payType;
        }


        public String getMercCode() {
            return mercCode;
        }


        public void setMercCode(String mercCode) {
            this.mercCode = mercCode;
        }


        public String getOrderId() {
            return orderId;
        }


        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }


        public String getTransNo() {
            return transNo;
        }


        public void setTransNo(String transNo) {
            this.transNo = transNo;
        }


        public String getBankCardId() {
            return bankCardId;
        }


        public void setBankCardId(String bankCardId) {
            this.bankCardId = bankCardId;
        }


        public String getBankCardName() {
            return bankCardName;
        }


        public void setBankCardName(String bankCardName) {
            this.bankCardName = bankCardName;
        }


        public String getPayeerAccountId() {
            return payeerAccountId;
        }


        public void setPayeerAccountId(String payeerAccountId) {
            this.payeerAccountId = payeerAccountId;
        }


        public String getTransType() {
            return transType;
        }


        public void setTransType(String transType) {
            this.transType = transType;
        }


        public String getTransAmount() {
            return transAmount;
        }


        public void setTransAmount(String transAmount) {
            this.transAmount = transAmount;
        }


        public String getPayResult() {
            return payResult;
        }


        public void setPayResult(String payResult) {
            this.payResult = payResult;
        }


        public String getTransTime() {
            return transTime;
        }


        public void setTransTime(String transTime) {
            this.transTime = transTime;
        }

        @Override
        public String toString(){
            return JSONObject.fromObject(this).toString();
        }
        
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Long getTotalTransAmount() {
        return totalTransAmount;
    }

    public void setTotalTransAmount(Long totalTransAmount) {
        this.totalTransAmount = totalTransAmount;
    }

    public List<HongpayAuditVO> getHongpayAuditList() {
        return hongpayAuditList;
    }

    public void setHongpayAuditList(List<HongpayAuditVO> hongpayAuditList) {
        this.hongpayAuditList = hongpayAuditList;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }
    
}