package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * @author: cbl
 * @date: 2016/6/21.
 */
public class RBackDiscountReq extends BaseInfo {

    /**
     * orderId:订单号.
     * @since JDK 1.6
     */
    private String orderId;

    /**
     * backId:退货申请单.
     * @since JDK 1.6
     */
    private Long backId;

    private String procType;

    private String discountType;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId = backId;
    }

    public String getProcType() {
        return procType;
    }

    public void setProcType(String procType) {
        this.procType = procType;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }
}
