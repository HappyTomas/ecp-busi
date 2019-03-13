package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class StockShopInfoIdx implements Serializable {
    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 商品分类ID
     */
    private String catgCode;

    /**
     * 商品类型ID
     */
    private Long typeId;

    /**
     * 商品编码
     */
    private Long gdsId;

    /**
     * 单品编码
     */
    private Long skuId;

    /**
     * 仓库类型
     */
    private String repType;

    /**
     * 库存类型
     */
    private String stockType;

    /**
     * 状态
     */
    private String status;

    /**
     * 库存ID
     */
    private Long stockId;

    /**
     * 
     */
    private Long availCount;

    /**
     * 零库存持续标识0-表示非持续零库存 1-表示持续零库存
     */
    private String zeroStockFlag;

    /**
     * 零库存持续开始时间,如果是零库存则该字段有值反之为空
     */
    private Timestamp zeroStockStarttime;

    /**
     * 工厂库存
     */
    private Long facStock;

    /**
     * 产品名称
     */
    private String gdsName;

    /**
     * 产品编号
     */
    private String productNo;

    /**
     * 分类族谱
     */
    private String catgPath;

    private static final long serialVersionUID = 1L;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getRepType() {
        return repType;
    }

    public void setRepType(String repType) {
        this.repType = repType == null ? null : repType.trim();
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType == null ? null : stockType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getAvailCount() {
        return availCount;
    }

    public void setAvailCount(Long availCount) {
        this.availCount = availCount;
    }

    public String getZeroStockFlag() {
        return zeroStockFlag;
    }

    public void setZeroStockFlag(String zeroStockFlag) {
        this.zeroStockFlag = zeroStockFlag == null ? null : zeroStockFlag.trim();
    }

    public Timestamp getZeroStockStarttime() {
        return zeroStockStarttime;
    }

    public void setZeroStockStarttime(Timestamp zeroStockStarttime) {
        this.zeroStockStarttime = zeroStockStarttime;
    }

    public Long getFacStock() {
        return facStock;
    }

    public void setFacStock(Long facStock) {
        this.facStock = facStock;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public String getCatgPath() {
        return catgPath;
    }

    public void setCatgPath(String catgPath) {
        this.catgPath = catgPath == null ? null : catgPath.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shopId=").append(shopId);
        sb.append(", catgCode=").append(catgCode);
        sb.append(", typeId=").append(typeId);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", skuId=").append(skuId);
        sb.append(", repType=").append(repType);
        sb.append(", stockType=").append(stockType);
        sb.append(", status=").append(status);
        sb.append(", stockId=").append(stockId);
        sb.append(", availCount=").append(availCount);
        sb.append(", zeroStockFlag=").append(zeroStockFlag);
        sb.append(", zeroStockStarttime=").append(zeroStockStarttime);
        sb.append(", facStock=").append(facStock);
        sb.append(", gdsName=").append(gdsName);
        sb.append(", productNo=").append(productNo);
        sb.append(", catgPath=").append(catgPath);
        sb.append("]");
        return sb.toString();
    }
}
