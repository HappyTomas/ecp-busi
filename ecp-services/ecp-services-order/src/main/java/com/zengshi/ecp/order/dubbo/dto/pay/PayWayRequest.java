package com.zengshi.ecp.order.dubbo.dto.pay;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class PayWayRequest extends BaseInfo {
    private String id;

    private String payWayName;

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


    private static final long serialVersionUID = 1L;
    
    /**
     * 店铺编码
     */
    private long shopId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPayWayName() {
        return payWayName;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName == null ? null : payWayName.trim();
    }

    public String getPayWayType() {
        return payWayType;
    }

    public void setPayWayType(String payWayType) {
        this.payWayType = payWayType == null ? null : payWayType.trim();
    }

    public String getPayAcctType() {
        return payAcctType;
    }

    public void setPayAcctType(String payAcctType) {
        this.payAcctType = payAcctType == null ? null : payAcctType.trim();
    }

    public String getPayImage() {
        return payImage;
    }

    public void setPayImage(String payImage) {
        this.payImage = payImage == null ? null : payImage.trim();
    }

    public String getPayLogo() {
        return payLogo;
    }

    public void setPayLogo(String payLogo) {
        this.payLogo = payLogo == null ? null : payLogo.trim();
    }

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet == null ? null : charSet.trim();
    }

    public String getPayActionUrl() {
        return payActionUrl;
    }

    public void setPayActionUrl(String payActionUrl) {
        this.payActionUrl = payActionUrl == null ? null : payActionUrl.trim();
    }

    public String getPayQueryUrl() {
        return payQueryUrl;
    }

    public void setPayQueryUrl(String payQueryUrl) {
        this.payQueryUrl = payQueryUrl == null ? null : payQueryUrl.trim();
    }

    public String getPayRefundUrl() {
        return payRefundUrl;
    }

    public void setPayRefundUrl(String payRefundUrl) {
        this.payRefundUrl = payRefundUrl == null ? null : payRefundUrl.trim();
    }

    public String getBindUrl() {
        return bindUrl;
    }

    public void setBindUrl(String bindUrl) {
        this.bindUrl = bindUrl == null ? null : bindUrl.trim();
    }

    public String getBindTransferUrl() {
        return bindTransferUrl;
    }

    public void setBindTransferUrl(String bindTransferUrl) {
        this.bindTransferUrl = bindTransferUrl == null ? null : bindTransferUrl.trim();
    }

    public String getPayReturnUrl() {
        return payReturnUrl;
    }

    public void setPayReturnUrl(String payReturnUrl) {
        this.payReturnUrl = payReturnUrl == null ? null : payReturnUrl.trim();
    }

    public String getPayNotifyUrl() {
        return payNotifyUrl;
    }

    public void setPayNotifyUrl(String payNotifyUrl) {
        this.payNotifyUrl = payNotifyUrl == null ? null : payNotifyUrl.trim();
    }

    public String getPayBindNotifyUrl() {
        return payBindNotifyUrl;
    }

    public void setPayBindNotifyUrl(String payBindNotifyUrl) {
        this.payBindNotifyUrl = payBindNotifyUrl == null ? null : payBindNotifyUrl.trim();
    }

    public String getPayRefundNotifyUrl() {
        return payRefundNotifyUrl;
    }

    public void setPayRefundNotifyUrl(String payRefundNotifyUrl) {
        this.payRefundNotifyUrl = payRefundNotifyUrl == null ? null : payRefundNotifyUrl.trim();
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType == null ? null : signType.trim();
    }

    public String getPayMercCode() {
        return payMercCode;
    }

    public void setPayMercCode(String payMercCode) {
        this.payMercCode = payMercCode == null ? null : payMercCode.trim();
    }

    public String getPayPrivateKey() {
        return payPrivateKey;
    }

    public void setPayPrivateKey(String payPrivateKey) {
        this.payPrivateKey = payPrivateKey == null ? null : payPrivateKey.trim();
    }

    public String getPayPrivateUser() {
        return payPrivateUser;
    }

    public void setPayPrivateUser(String payPrivateUser) {
        this.payPrivateUser = payPrivateUser == null ? null : payPrivateUser.trim();
    }

    public String getPayPrivatePasswd() {
        return payPrivatePasswd;
    }

    public void setPayPrivatePasswd(String payPrivatePasswd) {
        this.payPrivatePasswd = payPrivatePasswd == null ? null : payPrivatePasswd.trim();
    }

    public String getPayVerifyCert() {
        return payVerifyCert;
    }

    public void setPayVerifyCert(String payVerifyCert) {
        this.payVerifyCert = payVerifyCert == null ? null : payVerifyCert.trim();
    }

    public String getPropertyFile() {
        return propertyFile;
    }

    public void setPropertyFile(String propertyFile) {
        this.propertyFile = propertyFile == null ? null : propertyFile.trim();
    }

    public Short getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Short showOrder) {
        this.showOrder = showOrder;
    }

    public String getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(String useFlag) {
        this.useFlag = useFlag == null ? null : useFlag.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }


    public String getPayErrorUrl() {
        return payErrorUrl;
    }

    public void setPayErrorUrl(String payErrorUrl) {
        this.payErrorUrl = payErrorUrl;
    }

    public String getLogFileUrl() {
        return logFileUrl;
    }

    public void setLogFileUrl(String logFileUrl) {
        this.logFileUrl = logFileUrl;
    }

    public String getPayAuditUrl() {
        return payAuditUrl;
    }

    public void setPayAuditUrl(String payAuditUrl) {
        this.payAuditUrl = payAuditUrl;
    }

    public String getPayRefundMethod() {
        return payRefundMethod;
    }

    public void setPayRefundMethod(String payRefundMethod) {
        this.payRefundMethod = payRefundMethod;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }
}