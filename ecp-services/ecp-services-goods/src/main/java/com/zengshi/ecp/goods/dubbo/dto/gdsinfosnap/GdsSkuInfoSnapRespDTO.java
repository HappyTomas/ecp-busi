package com.zengshi.ecp.goods.dubbo.dto.gdsinfosnap;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
public class GdsSkuInfoSnapRespDTO extends BaseResponseDTO    {
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -230551482603175568L;

	private Long snapId;

    private Long skuId;

    private String skuProps;

    private Long gdsId;

    private String skuName;

    private String skuSubHead;

    private BigDecimal skuGuidePrice;

    private String skuDesc;

    private String skuPartlist;

    private Long skuTypeId;

    private String skuStatus;

    private String skuApprove;

    private String skuUrl;

    private Integer sortNo;

    private Long  shopId;

    private Long taxId;

    private String gdsLabel;

    private Timestamp putonTime;

    private Timestamp putoffTime;

    private Long postTime;

    private String ifSendscore;

    private String ifSalealone;

    private String ifRecomm;

    private String ifNew;

    private String ifStocknotice;

    private String ifFree;

    private String ifDisperseStock;

    private String ifLadderPrice;

    private String ifEntityCode;

    private Long shipTemplateId;

    private Long supplierId;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;


    public Long getSnapId() {
        return snapId;
    }

    public void setSnapId(Long snapId) {
        this.snapId = snapId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuProps() {
        return skuProps;
    }

    public void setSkuProps(String skuProps) {
        this.skuProps = skuProps == null ? null : skuProps.trim();
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    public String getSkuSubHead() {
        return skuSubHead;
    }

    public void setSkuSubHead(String skuSubHead) {
        this.skuSubHead = skuSubHead == null ? null : skuSubHead.trim();
    }

    public BigDecimal getSkuGuidePrice() {
        return skuGuidePrice;
    }

    public void setSkuGuidePrice(BigDecimal skuGuidePrice) {
        this.skuGuidePrice = skuGuidePrice;
    }

    public String getSkuDesc() {
        return skuDesc;
    }

    public void setSkuDesc(String skuDesc) {
        this.skuDesc = skuDesc == null ? null : skuDesc.trim();
    }

    public String getSkuPartlist() {
        return skuPartlist;
    }

    public void setSkuPartlist(String skuPartlist) {
        this.skuPartlist = skuPartlist == null ? null : skuPartlist.trim();
    }

    public Long getSkuTypeId() {
        return skuTypeId;
    }

    public void setSkuTypeId(Long skuTypeId) {
        this.skuTypeId = skuTypeId;
    }

    public String getSkuStatus() {
        return skuStatus;
    }

    public void setSkuStatus(String skuStatus) {
        this.skuStatus = skuStatus == null ? null : skuStatus.trim();
    }

    public String getSkuApprove() {
        return skuApprove;
    }

    public void setSkuApprove(String skuApprove) {
        this.skuApprove = skuApprove == null ? null : skuApprove.trim();
    }

    public String getSkuUrl() {
        return skuUrl;
    }

    public void setSkuUrl(String skuUrl) {
        this.skuUrl = skuUrl == null ? null : skuUrl.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Long  getShopId() {
        return shopId;
    }

    public void setShopId(Long  shopId) {
        this.shopId = shopId;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public String getGdsLabel() {
        return gdsLabel;
    }

    public void setGdsLabel(String gdsLabel) {
        this.gdsLabel = gdsLabel == null ? null : gdsLabel.trim();
    }

    public Timestamp getPutonTime() {
        return putonTime;
    }

    public void setPutonTime(Timestamp putonTime) {
        this.putonTime = putonTime;
    }

    public Timestamp getPutoffTime() {
        return putoffTime;
    }

    public void setPutoffTime(Timestamp putoffTime) {
        this.putoffTime = putoffTime;
    }

    public Long getPostTime() {
        return postTime;
    }

    public void setPostTime(Long postTime) {
        this.postTime = postTime;
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

    public Long getShipTemplateId() {
        return shipTemplateId;
    }

    public void setShipTemplateId(Long shipTemplateId) {
        this.shipTemplateId = shipTemplateId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
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
        sb.append(", snapId=").append(snapId);
        sb.append(", skuId=").append(skuId);
        sb.append(", skuProps=").append(skuProps);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", skuName=").append(skuName);
        sb.append(", skuSubHead=").append(skuSubHead);
        sb.append(", skuGuidePrice=").append(skuGuidePrice);
        sb.append(", skuDesc=").append(skuDesc);
        sb.append(", skuPartlist=").append(skuPartlist);
        sb.append(", skuTypeId=").append(skuTypeId);
        sb.append(", skuStatus=").append(skuStatus);
        sb.append(", skuApprove=").append(skuApprove);
        sb.append(", skuUrl=").append(skuUrl);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", shopId=").append(shopId);
        sb.append(", taxId=").append(taxId);
        sb.append(", gdsLabel=").append(gdsLabel);
        sb.append(", putonTime=").append(putonTime);
        sb.append(", putoffTime=").append(putoffTime);
        sb.append(", postTime=").append(postTime);
        sb.append(", ifSendscore=").append(ifSendscore);
        sb.append(", ifSalealone=").append(ifSalealone);
        sb.append(", ifRecomm=").append(ifRecomm);
        sb.append(", ifNew=").append(ifNew);
        sb.append(", ifStocknotice=").append(ifStocknotice);
        sb.append(", ifFree=").append(ifFree);
        sb.append(", ifDisperseStock=").append(ifDisperseStock);
        sb.append(", ifLadderPrice=").append(ifLadderPrice);
        sb.append(", ifEntityCode=").append(ifEntityCode);
        sb.append(", shipTemplateId=").append(shipTemplateId);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
