package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GdsInfo implements Serializable {
    /**
     * 商品编码
     */
    private Long id;

    /**
     * 商品当前快照ID
     */
    private Long snapId;

    /**
     * 
     */
    private String gdsName;

    /**
     * 
     */
    private String gdsSubHead;

    /**
     * 商品参考价
     */
    private Long guidePrice;

    /**
     * 商品描述
     */
    private String gdsDesc;

    /**
     * 商品包装清单
     */
    private String gdsPartlist;

    /**
     * 商品类型
     */
    private Long gdsTypeId;

    /**
     * 商品状态0待上架 11已上架 22已下架 99删除
     */
    private String gdsStatus;

    /**
     * 商品审批状态  0 待审批   11 审批中   22审批通过  99审批不通过
     */
    private String gdsApprove;

    /**
     * 
     */
    private String url;

    /**
     * 商品排序
     */
    private Integer sortNo;

    /**
     * 商品归属店铺ID
     */
    private Long shopId;

    /**
     * 商品税费
     */
    private Long taxId;

    /**
     * 商品标签，是选择多个标签后，取标签名称按照逗号隔开保存
     */
    private String gdsLabel;

    /**
     * 自动上架时间
     */
    private Timestamp putonTime;

    /**
     * 自动下架时间
     */
    private Timestamp putoffTime;

    /**
     * 发货日期，单位天数，在单位天数内，如果未发货订单自动关闭
     */
    private Long postTime;

    /**
     * 是否允许商品参加积分赠送，积分具体规则在平台配置
     */
    private String ifSendscore;

    /**
     * 不允许单独销售的产品，只能作为赠品或者组合搭配品使用，不能单独上架
     */
    private String ifSalealone;

    /**
     * 该商品是否为推荐商品，店铺推荐专栏用
     */
    private String ifRecomm;

    /**
     * 该商品是否为新品，店铺新品专栏用
     */
    private String ifNew;

    /**
     * 是否启用库存存量过低提醒，0不提醒，默认  1提醒
     */
    private String ifStocknotice;

    /**
     * 是否免邮
     */
    private String ifFree;

    /**
     * 是否启用分仓库存 0不启用，1启用
     */
    private String ifDisperseStock;

    /**
     * 是否阶梯价0普通价格，1阶梯价
     */
    private String ifLadderPrice;

    /**
     * 1不需要录入串号 2需要录入串号   3仅在发货是录入串号，所谓串号即单品的实体编码一个串号对应一个实物单品
     */
    private String ifEntityCode;

    /**
     * 是否高级价格
     */
    private String ifSeniorPrice;

    /**
     * 是否积分商品
     */
    private String ifScoreGds;

    /**
     * 商品运费模板ID   -1为未配置
     */
    private Long shipTemplateId;

    /**
     * 供货商ID
     */
    private Long supplierId;

    /**
     * 
     */
    private String platCatgs;

    /**
     * 
     */
    private String shopCatgs;

    /**
     * 商品归属主分类
     */
    private String mainCatgs;

    /**
     * 商品归属目录
     */
    private Long catlogId;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 创建人
     */
    private Long createStaff;

    /**
     * 最后更新时间
     */
    private Timestamp updateTime;

    /**
     * 最后更新人
     */
    private Long updateStaff;

    /**
     * 商品变更信息串
     */
    private String changePropStr;

    /**
     * 
     */
    private String isbn;

    /**
     * 扩展字段1
     */
    private String ext1;

    /**
     * 扩展字段2
     */
    private String ext2;

    /**
     * 扩展字段3
     */
    private String ext3;

    /**
     * 扩展字段4
     */
    private String ext4;

    /**
     * 扩展字段5
     */
    private String ext5;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSnapId() {
        return snapId;
    }

    public void setSnapId(Long snapId) {
        this.snapId = snapId;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
    }

    public String getGdsSubHead() {
        return gdsSubHead;
    }

    public void setGdsSubHead(String gdsSubHead) {
        this.gdsSubHead = gdsSubHead == null ? null : gdsSubHead.trim();
    }

    public Long getGuidePrice() {
        return guidePrice;
    }

    public void setGuidePrice(Long guidePrice) {
        this.guidePrice = guidePrice;
    }

    public String getGdsDesc() {
        return gdsDesc;
    }

    public void setGdsDesc(String gdsDesc) {
        this.gdsDesc = gdsDesc == null ? null : gdsDesc.trim();
    }

    public String getGdsPartlist() {
        return gdsPartlist;
    }

    public void setGdsPartlist(String gdsPartlist) {
        this.gdsPartlist = gdsPartlist == null ? null : gdsPartlist.trim();
    }

    public Long getGdsTypeId() {
        return gdsTypeId;
    }

    public void setGdsTypeId(Long gdsTypeId) {
        this.gdsTypeId = gdsTypeId;
    }

    public String getGdsStatus() {
        return gdsStatus;
    }

    public void setGdsStatus(String gdsStatus) {
        this.gdsStatus = gdsStatus == null ? null : gdsStatus.trim();
    }

    public String getGdsApprove() {
        return gdsApprove;
    }

    public void setGdsApprove(String gdsApprove) {
        this.gdsApprove = gdsApprove == null ? null : gdsApprove.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
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

    public String getMainCatgs() {
        return mainCatgs;
    }

    public void setMainCatgs(String mainCatgs) {
        this.mainCatgs = mainCatgs == null ? null : mainCatgs.trim();
    }

    public Long getCatlogId() {
        return catlogId;
    }

    public void setCatlogId(Long catlogId) {
        this.catlogId = catlogId;
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

    public String getChangePropStr() {
        return changePropStr;
    }

    public void setChangePropStr(String changePropStr) {
        this.changePropStr = changePropStr == null ? null : changePropStr.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4 == null ? null : ext4.trim();
    }

    public String getExt5() {
        return ext5;
    }

    public void setExt5(String ext5) {
        this.ext5 = ext5 == null ? null : ext5.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", snapId=").append(snapId);
        sb.append(", gdsName=").append(gdsName);
        sb.append(", gdsSubHead=").append(gdsSubHead);
        sb.append(", guidePrice=").append(guidePrice);
        sb.append(", gdsDesc=").append(gdsDesc);
        sb.append(", gdsPartlist=").append(gdsPartlist);
        sb.append(", gdsTypeId=").append(gdsTypeId);
        sb.append(", gdsStatus=").append(gdsStatus);
        sb.append(", gdsApprove=").append(gdsApprove);
        sb.append(", url=").append(url);
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
        sb.append(", ifSeniorPrice=").append(ifSeniorPrice);
        sb.append(", ifScoreGds=").append(ifScoreGds);
        sb.append(", shipTemplateId=").append(shipTemplateId);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", platCatgs=").append(platCatgs);
        sb.append(", shopCatgs=").append(shopCatgs);
        sb.append(", mainCatgs=").append(mainCatgs);
        sb.append(", catlogId=").append(catlogId);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", changePropStr=").append(changePropStr);
        sb.append(", isbn=").append(isbn);
        sb.append(", ext1=").append(ext1);
        sb.append(", ext2=").append(ext2);
        sb.append(", ext3=").append(ext3);
        sb.append(", ext4=").append(ext4);
        sb.append(", ext5=").append(ext5);
        sb.append("]");
        return sb.toString();
    }
}
