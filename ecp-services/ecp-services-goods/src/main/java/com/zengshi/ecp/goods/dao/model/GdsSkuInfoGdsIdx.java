package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GdsSkuInfoGdsIdx implements Serializable {
    private Long gdsId;

    private Long skuId;

    private Long snapId;

    private Long shopId;

    private String skuProps;

    private String gdsName;

    private String gdsStatus;

    private Long gdsTypeId;

    private String gdsApprove;

    private String platCatgs;

    private String shopCatgs;

    private Long catlogId;

    private String mainCatgs;

    private String ifSendscore;

    private String ifSalealone;

    private String ifRecomm;

    private String ifNew;

    private String ifStocknotice;

    private String ifFree;

    private String ifDisperseStock;

    private String ifLadderPrice;

    private String ifEntityCode;

    private String ifSeniorPrice;

    private String ifScoreGds;

    private String gdsTypeCode;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String isbn;

    private static final long serialVersionUID = 1L;

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

    public Long getSnapId() {
        return snapId;
    }

    public void setSnapId(Long snapId) {
        this.snapId = snapId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getSkuProps() {
        return skuProps;
    }

    public void setSkuProps(String skuProps) {
        this.skuProps = skuProps == null ? null : skuProps.trim();
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
    }

    public String getGdsStatus() {
        return gdsStatus;
    }

    public void setGdsStatus(String gdsStatus) {
        this.gdsStatus = gdsStatus == null ? null : gdsStatus.trim();
    }

    public Long getGdsTypeId() {
        return gdsTypeId;
    }

    public void setGdsTypeId(Long gdsTypeId) {
        this.gdsTypeId = gdsTypeId;
    }

    public String getGdsApprove() {
        return gdsApprove;
    }

    public void setGdsApprove(String gdsApprove) {
        this.gdsApprove = gdsApprove == null ? null : gdsApprove.trim();
    }

    public String getPlatCatgs() {
        return platCatgs;
    }

    public void setPlatCatgs(String platCatgs) {
        this.platCatgs = platCatgs == null ? null : platCatgs.trim();
    }

    public String getShopCatgs() {
        return shopCatgs;
    }

    public void setShopCatgs(String shopCatgs) {
        this.shopCatgs = shopCatgs == null ? null : shopCatgs.trim();
    }

    public Long getCatlogId() {
        return catlogId;
    }

    public void setCatlogId(Long catlogId) {
        this.catlogId = catlogId;
    }

    public String getMainCatgs() {
        return mainCatgs;
    }

    public void setMainCatgs(String mainCatgs) {
        this.mainCatgs = mainCatgs == null ? null : mainCatgs.trim();
    }

    public String getIfSendscore() {
        return ifSendscore;
    }

    public void setIfSendscore(String ifSendscore) {
        this.ifSendscore = ifSendscore == null ? null : ifSendscore.trim();
    }

    public String getIfSalealone() {
        return ifSalealone;
    }

    public void setIfSalealone(String ifSalealone) {
        this.ifSalealone = ifSalealone == null ? null : ifSalealone.trim();
    }

    public String getIfRecomm() {
        return ifRecomm;
    }

    public void setIfRecomm(String ifRecomm) {
        this.ifRecomm = ifRecomm == null ? null : ifRecomm.trim();
    }

    public String getIfNew() {
        return ifNew;
    }

    public void setIfNew(String ifNew) {
        this.ifNew = ifNew == null ? null : ifNew.trim();
    }

    public String getIfStocknotice() {
        return ifStocknotice;
    }

    public void setIfStocknotice(String ifStocknotice) {
        this.ifStocknotice = ifStocknotice == null ? null : ifStocknotice.trim();
    }

    public String getIfFree() {
        return ifFree;
    }

    public void setIfFree(String ifFree) {
        this.ifFree = ifFree == null ? null : ifFree.trim();
    }

    public String getIfDisperseStock() {
        return ifDisperseStock;
    }

    public void setIfDisperseStock(String ifDisperseStock) {
        this.ifDisperseStock = ifDisperseStock == null ? null : ifDisperseStock.trim();
    }

    public String getIfLadderPrice() {
        return ifLadderPrice;
    }

    public void setIfLadderPrice(String ifLadderPrice) {
        this.ifLadderPrice = ifLadderPrice == null ? null : ifLadderPrice.trim();
    }

    public String getIfEntityCode() {
        return ifEntityCode;
    }

    public void setIfEntityCode(String ifEntityCode) {
        this.ifEntityCode = ifEntityCode == null ? null : ifEntityCode.trim();
    }

    public String getIfSeniorPrice() {
        return ifSeniorPrice;
    }

    public void setIfSeniorPrice(String ifSeniorPrice) {
        this.ifSeniorPrice = ifSeniorPrice == null ? null : ifSeniorPrice.trim();
    }

    public String getIfScoreGds() {
        return ifScoreGds;
    }

    public void setIfScoreGds(String ifScoreGds) {
        this.ifScoreGds = ifScoreGds == null ? null : ifScoreGds.trim();
    }

    public String getGdsTypeCode() {
        return gdsTypeCode;
    }

    public void setGdsTypeCode(String gdsTypeCode) {
        this.gdsTypeCode = gdsTypeCode == null ? null : gdsTypeCode.trim();
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", gdsId=").append(gdsId);
        sb.append(", skuId=").append(skuId);
        sb.append(", snapId=").append(snapId);
        sb.append(", shopId=").append(shopId);
        sb.append(", skuProps=").append(skuProps);
        sb.append(", gdsName=").append(gdsName);
        sb.append(", gdsStatus=").append(gdsStatus);
        sb.append(", gdsTypeId=").append(gdsTypeId);
        sb.append(", gdsApprove=").append(gdsApprove);
        sb.append(", platCatgs=").append(platCatgs);
        sb.append(", shopCatgs=").append(shopCatgs);
        sb.append(", catlogId=").append(catlogId);
        sb.append(", mainCatgs=").append(mainCatgs);
        sb.append(", ifSendscore=").append(ifSendscore);
        sb.append(", ifSalealone=").append(ifSalealone);
        sb.append(", ifRecomm=").append(ifRecomm);
        sb.append(", ifNew=").append(ifNew);
        sb.append(", ifStocknotice=").append(ifStocknotice);
        sb.append(", ifFree=").append(ifFree);
        sb.append(", ifDisperseStock=").append(ifDisperseStock);
        sb.append(", ifLadderPrice=").append(ifLadderPrice);
        sb.append(", ifEntityCode=").append(ifEntityCode);
        sb.append(", ifSeniorPrice=").append(ifSeniorPrice);
        sb.append(", ifScoreGds=").append(ifScoreGds);
        sb.append(", gdsTypeCode=").append(gdsTypeCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", isbn=").append(isbn);
        sb.append("]");
        return sb.toString();
    }
}
