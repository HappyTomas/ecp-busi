package com.zengshi.ecp.order.service.busi.impl.pay.audit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.zengshi.ecp.order.dubbo.dto.pay.BasePayResponse;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 农行对账实体类<br>
 * Date:2015年11月14日下午3:09:13  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class ABCPayAuditResponse extends BasePayResponse {
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    private Long numOfPayments;
    private Long sumOfPayAmount;
    private String fileContent;
    private List<ABCPayAuditVO> ABCPayAuditList;
    
    @Override
    public void buildSelf(Map<String, String> responseMap) throws Exception {
        List<ABCPayAuditVO> list = new ArrayList<ABCPayAuditResponse.ABCPayAuditVO>();
        this.fileContent = responseMap.get("fileContent");
        // 每笔交易明细为字符串类型，使用“|”分隔不同的字段，使用“^^”分隔不同的记录。字段信息如下：
        // 商户号|交易类型|订单编号|交易时间|交易金额|商户账号|商户动账金额|客户账号|账户类型|商户回佣手续费|商户分期手续费|会计日期|主机流水号|9014流水号|原订单号
        String detailRecords = responseMap.get("detailRecords");
        String[] details = detailRecords.split("\\^\\^");
        if(details.length>1){
            for (int i=1;i<details.length;i++) {
                String detail= details[i];
                String[] items = detail.split("\\|", 15);
                String merc_code = items[0];
                String transType = items[1];
                String orderId = items[2];
                String transTime = items[3];
                String payment = items[4];
                String merchAcctId = items[5];
                String merchAcctMoney = items[6];
                String payeeAcct = items[7];
                String acctType = items[8];
                String fee = items[9];
                String stagingFee = items[10];
                String accountingDate = items[11];
                String transNo = items[12];
                String transNo9014 = items[13];
                String oldOrderId = items[14];
                ABCPayAuditVO vo = new ABCPayAuditVO();
                vo.setMerc_code(merc_code);
                vo.setTransType(transType);
                vo.setOrderId(orderId);
                vo.setTransTime(transTime);
                vo.setPayment(payment);
                vo.setMerchAcctId(merchAcctId);
                vo.setMerchAcctMoney(merchAcctMoney);
                vo.setPayeeAcct(payeeAcct);
                vo.setAcctType(acctType);
                vo.setFee(fee);
                vo.setStagingFee(stagingFee);
                vo.setAccountingDate(accountingDate);
                vo.setTransNo(transNo);
                vo.setTransNo9014(transNo9014);
                vo.setOldOrderId(oldOrderId);
                list.add(vo);
            }
        }
        
        this.ABCPayAuditList=list;
    }
    
    
    public class ABCPayAuditVO{
        private String merc_code;
        private String transType;
        private String orderId;
        private String transTime;
        private String payment;
        private String merchAcctId;
        private String merchAcctMoney;
        private String PayeeAcct;
        private String AcctType;
        private String fee;
        private String stagingFee;
        private String AccountingDate;
        private String transNo;
        private String transNo9014;
        private String oldOrderId;
        
        public String getMerc_code() {
            return merc_code;
        }

        public void setMerc_code(String merc_code) {
            this.merc_code = merc_code;
        }

        public String getTransType() {
            return transType;
        }

        public void setTransType(String transType) {
            this.transType = transType;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getTransTime() {
            return transTime;
        }

        public void setTransTime(String transTime) {
            this.transTime = transTime;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String getMerchAcctId() {
            return merchAcctId;
        }

        public void setMerchAcctId(String merchAcctId) {
            this.merchAcctId = merchAcctId;
        }

        public String getMerchAcctMoney() {
            return merchAcctMoney;
        }

        public void setMerchAcctMoney(String merchAcctMoney) {
            this.merchAcctMoney = merchAcctMoney;
        }

        public String getPayeeAcct() {
            return PayeeAcct;
        }

        public void setPayeeAcct(String payeeAcct) {
            PayeeAcct = payeeAcct;
        }

        public String getAcctType() {
            return AcctType;
        }

        public void setAcctType(String acctType) {
            AcctType = acctType;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getStagingFee() {
            return stagingFee;
        }

        public void setStagingFee(String stagingFee) {
            this.stagingFee = stagingFee;
        }

        public String getAccountingDate() {
            return AccountingDate;
        }

        public void setAccountingDate(String accountingDate) {
            AccountingDate = accountingDate;
        }

        public String getTransNo() {
            return transNo;
        }

        public void setTransNo(String transNo) {
            this.transNo = transNo;
        }

        public String getTransNo9014() {
            return transNo9014;
        }

        public void setTransNo9014(String transNo9014) {
            this.transNo9014 = transNo9014;
        }

        public String getOldOrderId() {
            return oldOrderId;
        }

        public void setOldOrderId(String oldOrderId) {
            this.oldOrderId = oldOrderId;
        }

        @Override
        public String toString(){
            return JSONObject.fromObject(this).toString();
        }
    }


    public Long getNumOfPayments() {
        return numOfPayments;
    }


    public void setNumOfPayments(Long numOfPayments) {
        this.numOfPayments = numOfPayments;
    }


    public Long getSumOfPayAmount() {
        return sumOfPayAmount;
    }


    public void setSumOfPayAmount(Long sumOfPayAmount) {
        this.sumOfPayAmount = sumOfPayAmount;
    }


    public List<ABCPayAuditVO> getABCPayAuditList() {
        return ABCPayAuditList;
    }


    public void setABCPayAuditList(List<ABCPayAuditVO> aBCPayAuditList) {
        ABCPayAuditList = aBCPayAuditList;
    }


    public String getFileContent() {
        return fileContent;
    }


    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }
    
}