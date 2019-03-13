package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PayWay implements Serializable {
    /**
     * 支付通道编码 9999:线下支付....
     */
    private String id;

    /**
     * 支付通道名称
     */
    private String payWayName;

    /**
     * 支付通道类型 01：支付平台，02：银行卡支付，03：信用卡支付，04：供应链贷款支付（沃金融），05：线下支付
     */
    private String payWayType;

    /**
     * 支持账户类型 01：个人账户，02：对公账户
     */
    private String payAcctType;

    /**
     * 支付图标
     */
    private String payImage;

    /**
     * 水印图标
     */
    private String payLogo;

    /**
     * 编码格式
     */
    private String charSet;

    /**
     * 支付提交地址
     */
    private String payActionUrl;

    /**
     * 查询接口地址 走AIP
     */
    private String payQueryUrl;

    /**
     * 退款接口地址 走AIP
     */
    private String payRefundUrl;

    /**
     * 银行卡绑定地址 页面跳转
     */
    private String bindUrl;

    /**
     * 绑卡转移接口地址 体验渠道转本网渠道，支付平台要迁移银行卡绑定信息。
走AIP，为空的时候表示接口未启用
     */
    private String bindTransferUrl;

    /**
     * 支付返回地址（支付通道将控制权还给沃易购web）
     */
    private String payReturnUrl;

    /**
     * 支付结果通知接收地址（支付通道将支付结果以后台通知方式给沃易购，走AIP）
     */
    private String payNotifyUrl;

    /**
     * 渠道商绑卡结果通知接收地址（支付通道将渠道商绑卡或者签约信息返以后台方式通知给沃易购，走AIP）
     */
    private String payBindNotifyUrl;

    /**
     * 退款通知接收地址（支付通道后台通知沃易购退款结果）
     */
    private String payRefundNotifyUrl;

    /**
     * 报错返回地址
     */
    private String payErrorUrl;

    /**
     * 签名方式(01:证书文件，02：properties资源文件，03：密钥字符串)
     */
    private String signType;

    /**
     * 平台商户号
     */
    private String payMercCode;

    /**
     * 
     */
    private String payPrivateKey;

    /**
     * 平台私钥库用户
     */
    private String payPrivateUser;

    /**
     * 平台私钥密码，密码字符串
     */
    private String payPrivatePasswd;

    /**
     * 
     */
    private String payVerifyCert;

    /**
     * 
     */
    private String propertyFile;

    /**
     * 
     */
    private String logFileUrl;

    /**
     * 展示顺序
     */
    private Short showOrder;

    /**
     * 是否启用
     */
    private String useFlag;

    /**
     * 创建人
     */
    private String createStaff;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新人
     */
    private String updateStaff;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 对账请求地址
     */
    private String payAuditUrl;

    /**
     * 退款方式，01--不经页面跳转，02--经页面跳转到支付公司页面进行操作
     */
    private String payRefundMethod;

    /**
     * 
     */
    private String payAppActionUrl;
    
    /**
     * 退款返回地址
     */
    private String refundReturnUrl;

    private static final long serialVersionUID = 1L;

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

    public String getPayErrorUrl() {
        return payErrorUrl;
    }

    public void setPayErrorUrl(String payErrorUrl) {
        this.payErrorUrl = payErrorUrl == null ? null : payErrorUrl.trim();
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

    public String getLogFileUrl() {
        return logFileUrl;
    }

    public void setLogFileUrl(String logFileUrl) {
        this.logFileUrl = logFileUrl == null ? null : logFileUrl.trim();
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

    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff == null ? null : updateStaff.trim();
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getPayAuditUrl() {
        return payAuditUrl;
    }

    public void setPayAuditUrl(String payAuditUrl) {
        this.payAuditUrl = payAuditUrl == null ? null : payAuditUrl.trim();
    }

    public String getPayRefundMethod() {
        return payRefundMethod;
    }

    public void setPayRefundMethod(String payRefundMethod) {
        this.payRefundMethod = payRefundMethod == null ? null : payRefundMethod.trim();
    }

    public String getPayAppActionUrl() {
        return payAppActionUrl;
    }

    public void setPayAppActionUrl(String payAppActionUrl) {
        this.payAppActionUrl = payAppActionUrl == null ? null : payAppActionUrl.trim();
    }
    
    public String getRefundReturnUrl() {
        return refundReturnUrl;
    }

    public void setRefundReturnUrl(String refundReturnUrl) {
        this.refundReturnUrl = refundReturnUrl == null ? null : refundReturnUrl.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", payWayName=").append(payWayName);
        sb.append(", payWayType=").append(payWayType);
        sb.append(", payAcctType=").append(payAcctType);
        sb.append(", payImage=").append(payImage);
        sb.append(", payLogo=").append(payLogo);
        sb.append(", charSet=").append(charSet);
        sb.append(", payActionUrl=").append(payActionUrl);
        sb.append(", payQueryUrl=").append(payQueryUrl);
        sb.append(", payRefundUrl=").append(payRefundUrl);
        sb.append(", bindUrl=").append(bindUrl);
        sb.append(", bindTransferUrl=").append(bindTransferUrl);
        sb.append(", payReturnUrl=").append(payReturnUrl);
        sb.append(", payNotifyUrl=").append(payNotifyUrl);
        sb.append(", payBindNotifyUrl=").append(payBindNotifyUrl);
        sb.append(", payRefundNotifyUrl=").append(payRefundNotifyUrl);
        sb.append(", payErrorUrl=").append(payErrorUrl);
        sb.append(", signType=").append(signType);
        sb.append(", payMercCode=").append(payMercCode);
        sb.append(", payPrivateKey=").append(payPrivateKey);
        sb.append(", payPrivateUser=").append(payPrivateUser);
        sb.append(", payPrivatePasswd=").append(payPrivatePasswd);
        sb.append(", payVerifyCert=").append(payVerifyCert);
        sb.append(", propertyFile=").append(propertyFile);
        sb.append(", logFileUrl=").append(logFileUrl);
        sb.append(", showOrder=").append(showOrder);
        sb.append(", useFlag=").append(useFlag);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", payAuditUrl=").append(payAuditUrl);
        sb.append(", payRefundMethod=").append(payRefundMethod);
        sb.append(", payAppActionUrl=").append(payAppActionUrl);
        sb.append(", refundReturnUrl=").append(refundReturnUrl);
        sb.append("]");
        return sb.toString();
    }
}