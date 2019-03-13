package com.zengshi.ecp.goods.dubbo.dto.stock;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class StockPreOccupyRespDTO extends BaseResponseDTO {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = -2250459348596111485L;

    private Long count;

    private Long stockId;

    private Long staffId;

    private Long ShopId;
    
    private String orderId; 
    
    private String subOrder;
    
    private Long repCode;
    
    private Long skuId;
    //是否是虚拟商品
    private Boolean ifVirtualGds;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getShopId() {
        return ShopId;
    }

    public void setShopId(Long shopId) {
        ShopId = shopId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSubOrder() {
        return subOrder;
    }

    public void setSubOrder(String subOrder) {
        this.subOrder = subOrder;
    }

    public Long getRepCode() {
        return repCode;
    }

    public void setRepCode(Long repCode) {
        this.repCode = repCode;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Boolean getIfVirtualGds() {
        return ifVirtualGds;
    }

    public void setIfVirtualGds(Boolean ifVirtualGds) {
        this.ifVirtualGds = ifVirtualGds;
    }

}
