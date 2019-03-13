package com.zengshi.ecp.aip.third.dubbo.dto.req;

import com.zengshi.ecp.server.front.dto.BaseInfo;

import java.util.Date;

/**
 * Created by guojingman on 2017/2/22.
 * 获取有赞订单接口请求参数对象
 */
public class YouzanOrderReq extends BaseInfo {
    private String appId;
    private String appSecret;
    //交易状态更新开始时间
    private Date startUpdate;
    //交易状态更新结束时间
    private Date endUpdate;
    /**
     * 一次只能查询一种状态。 可选值：
     * TRADE_NO_CREATE_PAY（没有创建支付交易）
     * WAIT_BUYER_PAY（等待买家付款）
     * WAIT_GROUP（等待成团，即：买家已付款，等待成团）
     * WAIT_SELLER_SEND_GOODS（等待卖家发货，即：买家已付款）
     * WAIT_BUYER_CONFIRM_GOODS（等待买家确认收货，即：卖家已发货）
     * TRADE_BUYER_SIGNED（买家已签收）
     * TRADE_CLOSED（付款以后用户退款成功，交易自动关闭）
     * ALL_WAIT_PAY（包含：WAIT_BUYER_PAY、TRADE_NO_CREATE_PAY）
     * ALL_CLOSED（所有关闭订单）
     */
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEndUpdate() {
        return endUpdate;
    }

    public void setEndUpdate(Date endUpdate) {
        this.endUpdate = endUpdate;
    }

    public Date getStartUpdate() {
        return startUpdate;
    }

    public void setStartUpdate(Date startUpdate) {
        this.startUpdate = startUpdate;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }


}
