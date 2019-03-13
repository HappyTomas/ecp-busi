package com.zengshi.ecp.order.dubbo.dto.pay;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class PayWayResponse extends BaseResponseDTO {
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    
    private String id;

   
    private String payWayName;

    /**
     * 支付通道类型
     */
    private String payWayType;

    private String payAcctType;

    private String payImage;

    private String payLogo;

    private String charSet;

    private String payActionUrl;

    private String payQueryUrl;

    private String payRefundUrl;

    private String bindUrl;

    private String bindTransferUrl;

    private String payReturnUrl;

    private String payNotifyUrl;

    private String payBindNotifyUrl;

    private String payRefundNotifyUrl;

    private String payErrorUrl;

    private String signType;

    private String payMercCode;

    private String payPrivateKey;

    private String payPrivateUser;

    private String payPrivatePasswd;

    private String payVerifyCert;

    private String propertyFile;

    private String logFileUrl;

    private Short showOrder;

    private String useFlag;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private String payAuditUrl;

    private String payRefundMethod;
    
    /**
     * 支付通道列表
     */
    private List<PayWayItem> payWayList;
    
    private String resultFlag;
    
    private String resultMsg;

    public String getId() {
        return id;
    }

    public String getPayWayName() {
        return payWayName;
    }

    public String getPayWayType() {
        return payWayType;
    }

    public String getPayAcctType() {
        return payAcctType;
    }

    public String getPayImage() {
        return payImage;
    }

    public String getPayLogo() {
        return payLogo;
    }

    public String getCharSet() {
        return charSet;
    }

    public String getPayActionUrl() {
        return payActionUrl;
    }

    public String getPayQueryUrl() {
        return payQueryUrl;
    }

    public String getPayRefundUrl() {
        return payRefundUrl;
    }

    public String getBindUrl() {
        return bindUrl;
    }

    public String getBindTransferUrl() {
        return bindTransferUrl;
    }

    public String getPayReturnUrl() {
        return payReturnUrl;
    }

    public String getPayNotifyUrl() {
        return payNotifyUrl;
    }

    public String getPayBindNotifyUrl() {
        return payBindNotifyUrl;
    }

    public String getPayRefundNotifyUrl() {
        return payRefundNotifyUrl;
    }

    public String getPayErrorUrl() {
        return payErrorUrl;
    }

    public String getSignType() {
        return signType;
    }

    public String getPayMercCode() {
        return payMercCode;
    }

    public String getPayPrivateKey() {
        return payPrivateKey;
    }

    public String getPayPrivateUser() {
        return payPrivateUser;
    }

    public String getPayPrivatePasswd() {
        return payPrivatePasswd;
    }

    public String getPayVerifyCert() {
        return payVerifyCert;
    }

    public String getPropertyFile() {
        return propertyFile;
    }

    public String getLogFileUrl() {
        return logFileUrl;
    }

    public Short getShowOrder() {
        return showOrder;
    }

    public String getUseFlag() {
        return useFlag;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public String getPayAuditUrl() {
        return payAuditUrl;
    }

    public String getPayRefundMethod() {
        return payRefundMethod;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName;
    }

    public void setPayWayType(String payWayType) {
        this.payWayType = payWayType;
    }

    public void setPayAcctType(String payAcctType) {
        this.payAcctType = payAcctType;
    }

    public void setPayImage(String payImage) {
        this.payImage = payImage;
    }

    public void setPayLogo(String payLogo) {
        this.payLogo = payLogo;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    public void setPayActionUrl(String payActionUrl) {
        this.payActionUrl = payActionUrl;
    }

    public void setPayQueryUrl(String payQueryUrl) {
        this.payQueryUrl = payQueryUrl;
    }

    public void setPayRefundUrl(String payRefundUrl) {
        this.payRefundUrl = payRefundUrl;
    }

    public void setBindUrl(String bindUrl) {
        this.bindUrl = bindUrl;
    }

    public void setBindTransferUrl(String bindTransferUrl) {
        this.bindTransferUrl = bindTransferUrl;
    }

    public void setPayReturnUrl(String payReturnUrl) {
        this.payReturnUrl = payReturnUrl;
    }

    public void setPayNotifyUrl(String payNotifyUrl) {
        this.payNotifyUrl = payNotifyUrl;
    }

    public void setPayBindNotifyUrl(String payBindNotifyUrl) {
        this.payBindNotifyUrl = payBindNotifyUrl;
    }

    public void setPayRefundNotifyUrl(String payRefundNotifyUrl) {
        this.payRefundNotifyUrl = payRefundNotifyUrl;
    }

    public void setPayErrorUrl(String payErrorUrl) {
        this.payErrorUrl = payErrorUrl;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public void setPayMercCode(String payMercCode) {
        this.payMercCode = payMercCode;
    }

    public void setPayPrivateKey(String payPrivateKey) {
        this.payPrivateKey = payPrivateKey;
    }

    public void setPayPrivateUser(String payPrivateUser) {
        this.payPrivateUser = payPrivateUser;
    }

    public void setPayPrivatePasswd(String payPrivatePasswd) {
        this.payPrivatePasswd = payPrivatePasswd;
    }

    public void setPayVerifyCert(String payVerifyCert) {
        this.payVerifyCert = payVerifyCert;
    }

    public void setPropertyFile(String propertyFile) {
        this.propertyFile = propertyFile;
    }

    public void setLogFileUrl(String logFileUrl) {
        this.logFileUrl = logFileUrl;
    }

    public void setShowOrder(Short showOrder) {
        this.showOrder = showOrder;
    }

    public void setUseFlag(String useFlag) {
        this.useFlag = useFlag;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public void setPayAuditUrl(String payAuditUrl) {
        this.payAuditUrl = payAuditUrl;
    }

    public void setPayRefundMethod(String payRefundMethod) {
        this.payRefundMethod = payRefundMethod;
    }

    public List<PayWayItem> getPayWayList() {
        return payWayList;
    }

    public void setPayWayList(List<PayWayItem> payWayList) {
        this.payWayList = payWayList;
    }

    public String getResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(String resultFlag) {
        this.resultFlag = resultFlag;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

   
}