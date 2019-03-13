package com.zengshi.ecp.unpf.dubbo.dto.order;

import com.zengshi.ecp.server.front.dto.BaseInfo;

import java.util.List;

/**
 * @author: cbl
 * @date: 2016/11/16.
 */
public class RUnpfSendMainReq extends BaseInfo {

    private String orderId;

    private Long shopId;

    private String outerId;

    private String deliveryType;

    private String expressId;

    private String expressNo;

    private String remark;

    /**
     * importNo:本次发货批次号.
     * @since JDK 1.6
     */
    private Long batchId;

    private boolean isSendAll;

    private List<RUnpfSendSubReq> unpfSendSubReqList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getExpressId() {
        return expressId;
    }

    public void setExpressId(String expressId) {
        this.expressId = expressId;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<RUnpfSendSubReq> getUnpfSendSubReqList() {
        return unpfSendSubReqList;
    }

    public void setUnpfSendSubReqList(List<RUnpfSendSubReq> unpfSendSubReqList) {
        this.unpfSendSubReqList = unpfSendSubReqList;
    }

    public boolean isSendAll() {
        return isSendAll;
    }

    public void setSendAll(boolean sendAll) {
        isSendAll = sendAll;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }
}
