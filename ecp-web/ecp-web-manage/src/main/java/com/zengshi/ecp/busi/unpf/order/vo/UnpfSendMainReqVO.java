package com.zengshi.ecp.busi.unpf.order.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author: cbl
 * @date: 2016/11/16.
 */
public class UnpfSendMainReqVO extends EcpBasePageReqVO {
    private String orderId;

    private Long shopId;

    private String outerId;

    private String deliveryType;

    private String expressId;

    private String expressNo;

    private String remark;

    private String isSendAll;

    private List<UnpfSendSubReqVO> unpfSendSubReqVOList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
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

    public String getIsSendAll() {
        return isSendAll;
    }

    public void setIsSendAll(String isSendAll) {
        this.isSendAll = isSendAll;
    }

    public List<UnpfSendSubReqVO> getUnpfSendSubReqVOList() {
        return unpfSendSubReqVOList;
    }

    public void setUnpfSendSubReqVOList(List<UnpfSendSubReqVO> unpfSendSubReqVOList) {
        this.unpfSendSubReqVOList = unpfSendSubReqVOList;
    }
}
