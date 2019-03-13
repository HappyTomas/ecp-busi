package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class StockInfoHis implements Serializable {
    /**
     * 单品库存历史编码
     */
    private Long id;

    /**
     * 单品库存量ID
     */
    private Long stockId;

    /**
     * 商户编码
     */
    private Long shopId;

    /**
     * 买家企业编码
     */
    private Long companyId;

    /**
     * 仓库编码
     */
    private Long repCode;

    /**
     * 商品分类编码
     */
    private String catgCode;

    /**
     * 商品类型编码
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
     * 实际库存总数量=可用库存总数量+预占库存总数量
     */
    private Long realCount;

    /**
     * 预占库存总数量
     */
    private Long preOccupyCount;

    /**
     * 可用库存总数量
     */
    private Long availCount;

    /**
     * 已发货库存总数量
     */
    private Long sendCount;

    /**
     * 缺货库存阈值
     */
    private Long lackCount;

    /**
     * 存量过低阈值
     */
    private Long warningCount;

    /**
     * 工厂库存
     */
    private Long facStock;

    /**
     * 是否启用存量过低通知卖家
     */
    private String isUsewarning;

    /**
     * 是否缺货
     */
    private String isOver;

    /**
     * 仓库类型(00：渠道商仓库；01:共仓，02：分仓)
     */
    private String repType;

    /**
     * 库存类型（1：共用，2：分区域）
     */
    private String stockType;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 创建人
     */
    private Long createStaff;

    /**
     * 修改时间
     */
    private Timestamp updateTime;

    /**
     * 修改人
     */
    private Long updateStaff;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
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

    public Long getRepCode() {
        return repCode;
    }

    public void setRepCode(Long repCode) {
        this.repCode = repCode;
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

    public Long getRealCount() {
        return realCount;
    }

    public void setRealCount(Long realCount) {
        this.realCount = realCount;
    }

    public Long getPreOccupyCount() {
        return preOccupyCount;
    }

    public void setPreOccupyCount(Long preOccupyCount) {
        this.preOccupyCount = preOccupyCount;
    }

    public Long getAvailCount() {
        return availCount;
    }

    public void setAvailCount(Long availCount) {
        this.availCount = availCount;
    }

    public Long getSendCount() {
        return sendCount;
    }

    public void setSendCount(Long sendCount) {
        this.sendCount = sendCount;
    }

    public Long getLackCount() {
        return lackCount;
    }

    public void setLackCount(Long lackCount) {
        this.lackCount = lackCount;
    }

    public Long getWarningCount() {
        return warningCount;
    }

    public void setWarningCount(Long warningCount) {
        this.warningCount = warningCount;
    }

    public Long getFacStock() {
        return facStock;
    }

    public void setFacStock(Long facStock) {
        this.facStock = facStock;
    }

    public String getIsUsewarning() {
        return isUsewarning;
    }

    public void setIsUsewarning(String isUsewarning) {
        this.isUsewarning = isUsewarning == null ? null : isUsewarning.trim();
    }

    public String getIsOver() {
        return isOver;
    }

    public void setIsOver(String isOver) {
        this.isOver = isOver == null ? null : isOver.trim();
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stockId=").append(stockId);
        sb.append(", shopId=").append(shopId);
        sb.append(", companyId=").append(companyId);
        sb.append(", repCode=").append(repCode);
        sb.append(", catgCode=").append(catgCode);
        sb.append(", typeId=").append(typeId);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", skuId=").append(skuId);
        sb.append(", realCount=").append(realCount);
        sb.append(", preOccupyCount=").append(preOccupyCount);
        sb.append(", availCount=").append(availCount);
        sb.append(", sendCount=").append(sendCount);
        sb.append(", lackCount=").append(lackCount);
        sb.append(", warningCount=").append(warningCount);
        sb.append(", facStock=").append(facStock);
        sb.append(", isUsewarning=").append(isUsewarning);
        sb.append(", isOver=").append(isOver);
        sb.append(", repType=").append(repType);
        sb.append(", stockType=").append(stockType);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
