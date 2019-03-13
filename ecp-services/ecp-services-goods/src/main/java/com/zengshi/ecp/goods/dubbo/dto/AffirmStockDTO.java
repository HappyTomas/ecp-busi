package com.zengshi.ecp.goods.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class AffirmStockDTO extends BaseInfo {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 7840217792635479502L;

    // 主订单id
    private String orderId;

    // 子订单id
    private String subOrderId;

    // 单品id
    private Long skuId;

    // 确认收货数量
    private Long affirmCount;

    // 库存量id
    private Long stockId;

    // 操作人id
    private Long staffId;

    // 商品分类id
    private String catgCode;

    // 商品Id
    private Long gdsId;

    // 商品类型id
    private Long TypeId;

//    // 是否是串号商品
//    private Boolean ifSerial;

    // 买家库存ID
    private Long buyStockId;

    // 买家仓库编码
    private Long repCode;

    // 操作编码
    private Long optId;

    // 店铺id
    private Long shopId;

    // 企业id
    private Long companyId;

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

    public Long getAffirmCount() {
        return affirmCount;
    }

    public void setAffirmCount(Long affirmCount) {
        this.affirmCount = affirmCount;
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

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getTypeId() {
        return TypeId;
    }

    public void setTypeId(Long typeId) {
        TypeId = typeId;
    }

//    public Boolean getIfSerial() {
//        return ifSerial;
//    }
//
//    public void setIfSerial(Boolean ifSerial) {
//        this.ifSerial = ifSerial;
//    }

    public Long getBuyStockId() {
        return buyStockId;
    }

    public void setBuyStockId(Long buyStockId) {
        this.buyStockId = buyStockId;
    }

    public Long getRepCode() {
        return repCode;
    }

    public void setRepCode(Long repCode) {
        this.repCode = repCode;
    }

    public Long getOptId() {
        return optId;
    }

    public void setOptId(Long optId) {
        this.optId = optId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
