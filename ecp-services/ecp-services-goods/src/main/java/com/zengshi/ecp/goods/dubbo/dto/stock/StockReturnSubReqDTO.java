package com.zengshi.ecp.goods.dubbo.dto.stock;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class StockReturnSubReqDTO extends BaseInfo {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = -8182705072346967789L;

    private String orderId;

    private String subOrderId;
    //退货批次号
    private Long backId;

    private Long skuId;
    //退货数量
    private Long returnNum;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(String subOrderId) {
        this.subOrderId = subOrderId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(Long returnNum) {
        this.returnNum = returnNum;
    }

    public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId = backId;
    }
}
