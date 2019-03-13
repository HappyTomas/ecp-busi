/**
 * Copyright 2017 aTool.org
 */
package com.zengshi.model.youzan;

/**
 * 有赞推送接口数据对象
 */
public class YouzanPushEntity {

    private String app_id;
    //订单id，唯一
    private String id;
    private int kdt_id;
    //店名
    private String kdt_name;
    private int mode;
    private String msg;
    private int sendCount;
    //防伪签名 ：MD5(appid+msg+appSecrect)
    private String sign;

    /**
     * 交易状态。取值范围：
     * TRADE_NO_CREATE_PAY (没有创建支付交易)
     * WAIT_BUYER_PAY (等待买家付款)
     * WAIT_PAY_RETURN (等待支付确认)
     * WAIT_GROUP（等待成团，即：买家已付款，等待成团）
     * WAIT_SELLER_SEND_GOODS (等待卖家发货，即：买家已付款)
     * WAIT_BUYER_CONFIRM_GOODS (等待买家确认收货，即：卖家已发货)
     * TRADE_BUYER_SIGNED (买家已签收)
     * TRADE_CLOSED (付款以后用户退款成功，交易自动关闭)
     * TRADE_CLOSED_BY_USER (付款以前，卖家或买家主动关闭交易)
     */
    private String status;
    private boolean test;
    private String type;
    private int version;

    public int getSendCount() {
        return sendCount;
    }

    public void setSendCount(int sendCount) {
        this.sendCount = sendCount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    public String getMsg() {
        return msg;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public int getKdt_id() {
        return kdt_id;
    }

    public void setKdt_id(int kdt_id) {
        this.kdt_id = kdt_id;
    }

    public String getKdt_name() {
        return kdt_name;
    }

    public void setKdt_name(String kdt_name) {
        this.kdt_name = kdt_name;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isTest() {
        return test;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public boolean getTest() {
        return test;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "YouzanPushEntity{" +
                "app_id='" + app_id + '\'' +
                ", id='" + id + '\'' +
                ", kdt_id=" + kdt_id +
                ", kdt_name='" + kdt_name + '\'' +
                ", mode=" + mode +
                ", msg='" + msg + '\'' +
                ", sendCount=" + sendCount +
                ", sign='" + sign + '\'' +
                ", status='" + status + '\'' +
                ", test=" + test +
                ", type='" + type + '\'' +
                ", version=" + version +
                '}';
    }
}