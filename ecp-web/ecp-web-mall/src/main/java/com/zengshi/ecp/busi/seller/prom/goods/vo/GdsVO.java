package com.zengshi.ecp.busi.seller.prom.goods.vo;

import java.util.Date;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-20 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class GdsVO extends BaseResponseDTO{


    private static final long serialVersionUID = 1L;
   
    private String shopId;
    private String shopName;
    private String gdsId;
    private String gdsName;
    private String skuId;
    private String skuName;
    private String skuProps;//单品属性
    private String gdsTypeId;//类别  实体商品 虚拟商品
    private String gdsTypeName;//类别
    private String skuSubHead;//副标题
    private String skuGuidePrice;//指导价
    private String skuTypeId;//类别
    private String skuApprove;//审核状态
    private String skuApproveName;//审核状态
    private String skuUrl;// 链接地址
    private String taxId;// 税费
    private String appPrice;// app价格
    private Long stockCnt;// 库存量
    private Long promCnt;// 促销数量
    private String priceType;//秒杀价类型
    private Long priceValue;//秒杀价值
    private Long everyTimeCnt;// 促销每次赠品赠送数量
    private String platCatgs;//归属平台分类编码
    private String shopCatgs;//归属店铺平台分类编码
    private Boolean isNeedStock;//是否需要库存
    private Long buyCnt;
    
    private List<StockVO> stockList;
    
    /**
     * 单品id
     */
    private Long id;

    /**
     * 当前快照id
     */
    private Long snapId;

    /**
     * 单品库存真实值
     */
    private Long realAmount;

    /**
     * 当前查询条件默认价格
     */
    private Long realPrice;
    
    /**
     * 单品副标题（与商品同值）
     */
    private String gdsSubHead;

    /**
     * 单品指导价 与商品同值
     */
    private Long guidePrice;

    /**
     * 单品描述（与商品同值）
     */
    private String gdsDesc;

    /**
     * 包装清单（与商品同值）
     */
    private String gdsPartlist;

    
    /**
     * 商品类型（与商品同值）
     */
    private String gdsTypeCode;
    
    
    /**
     * 单品状态
     */
    private String gdsStatus;
    

    /**
     * 单品状态名称
     */
    private String gdsStatusName;
 

    /**
     * 单品URL
     */
    private String url;

    /**
     * 排序
     */
    private Integer sortNo;

    /**
     * 单品标签（与商品同值）
     */
    private String gdsLabel;

    /**
     * 自动上架时间
     */
    private Date putonTime;

    /**
     * 自动下架时间
     */
    private Date putoffTime;

    /**
     * 发货日期（预留）
     */
    private Long postTime;

    
    /**
     * 是否赠送积分
     */
    private String ifSendscore;

    /**
     * 是否单独销售
     */
    private String ifSalealone;

    /**
     * 是否推荐
     */
    private String ifRecomm;

    /**
     * 是否新品
     */
    private String ifNew;

    /**
     * 是否开启存量过低提醒
     */
    private String ifStocknotice;

    /**
     * 是否免邮
     */
    private String ifFree;

    /**
     * 是否开启分仓库存
     */
    private String ifDisperseStock;

    /**
     * 是否阶梯价
     */
    private String ifLadderPrice;

    /**
     * 实体编码策略
     */
    private String ifEntityCode;

    /**
     * 运费模板编码
     */
    private Long shipTemplateId;

    /**
     * 供货商
     */
    private Long supplierId;
    
    /**
     * 单品主分类编码
     */
    private String mainCatgs;

    /**
     * 单品主分类名称
     */
    private String mainCatgName;


    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getGdsId() {
        return gdsId;
    }

    public void setGdsId(String gdsId) {
        this.gdsId = gdsId;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuProps() {
        return skuProps;
    }

    public void setSkuProps(String skuProps) {
        this.skuProps = skuProps;
    }

    public String getGdsTypeId() {
        return gdsTypeId;
    }

    public void setGdsTypeId(String gdsTypeId) {
        this.gdsTypeId = gdsTypeId;
    }

    public String getGdsTypeName() {
        return gdsTypeName;
    }

    public void setGdsTypeName(String gdsTypeName) {
        this.gdsTypeName = gdsTypeName;
    }

    public String getSkuSubHead() {
        return skuSubHead;
    }

    public void setSkuSubHead(String skuSubHead) {
        this.skuSubHead = skuSubHead;
    }

    public String getSkuGuidePrice() {
        return skuGuidePrice;
    }

    public void setSkuGuidePrice(String skuGuidePrice) {
        this.skuGuidePrice = skuGuidePrice;
    }

    public String getSkuTypeId() {
        return skuTypeId;
    }

    public void setSkuTypeId(String skuTypeId) {
        this.skuTypeId = skuTypeId;
    }


    public String getSkuApprove() {
        return skuApprove;
    }

    public void setSkuApprove(String skuApprove) {
        this.skuApprove = skuApprove;
    }

    public String getSkuApproveName() {
        return skuApproveName;
    }

    public void setSkuApproveName(String skuApproveName) {
        this.skuApproveName = skuApproveName;
    }

    public String getSkuUrl() {
        return skuUrl;
    }

    public void setSkuUrl(String skuUrl) {
        this.skuUrl = skuUrl;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getAppPrice() {
        return appPrice;
    }

    public void setAppPrice(String appPrice) {
        this.appPrice = appPrice;
    }

    public Long getStockCnt() {
        return stockCnt;
    }

    public void setStockCnt(Long stockCnt) {
        this.stockCnt = stockCnt;
    }

    public Long getPromCnt() {
        return promCnt;
    }

    public void setPromCnt(Long promCnt) {
        this.promCnt = promCnt;
    }

    public String getPlatCatgs() {
        return platCatgs;
    }

    public void setPlatCatgs(String platCatgs) {
        this.platCatgs = platCatgs;
    }

    public String getShopCatgs() {
        return shopCatgs;
    }

    public void setShopCatgs(String shopCatgs) {
        this.shopCatgs = shopCatgs;
    }

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

    public Long getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(Long realAmount) {
        this.realAmount = realAmount;
    }

    public Long getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Long realPrice) {
        this.realPrice = realPrice;
    }

    public String getGdsSubHead() {
        return gdsSubHead;
    }

    public void setGdsSubHead(String gdsSubHead) {
        this.gdsSubHead = gdsSubHead;
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
        this.gdsDesc = gdsDesc;
    }

    public String getGdsPartlist() {
        return gdsPartlist;
    }

    public void setGdsPartlist(String gdsPartlist) {
        this.gdsPartlist = gdsPartlist;
    }

    public String getGdsTypeCode() {
        return gdsTypeCode;
    }

    public void setGdsTypeCode(String gdsTypeCode) {
        this.gdsTypeCode = gdsTypeCode;
    }

    public String getGdsStatus() {
        return gdsStatus;
    }

    public void setGdsStatus(String gdsStatus) {
        this.gdsStatus = gdsStatus;
    }

    public String getGdsStatusName() {
        return gdsStatusName;
    }

    public void setGdsStatusName(String gdsStatusName) {
        this.gdsStatusName = gdsStatusName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getGdsLabel() {
        return gdsLabel;
    }

    public void setGdsLabel(String gdsLabel) {
        this.gdsLabel = gdsLabel;
    }

    public Date getPutonTime() {
        return putonTime;
    }

    public void setPutonTime(Date putonTime) {
        this.putonTime = putonTime;
    }

    public Date getPutoffTime() {
        return putoffTime;
    }

    public void setPutoffTime(Date putoffTime) {
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
        this.ifSendscore = ifSendscore;
    }

    public String getIfSalealone() {
        return ifSalealone;
    }

    public void setIfSalealone(String ifSalealone) {
        this.ifSalealone = ifSalealone;
    }

    public String getIfRecomm() {
        return ifRecomm;
    }

    public void setIfRecomm(String ifRecomm) {
        this.ifRecomm = ifRecomm;
    }

    public String getIfNew() {
        return ifNew;
    }

    public void setIfNew(String ifNew) {
        this.ifNew = ifNew;
    }

    public String getIfStocknotice() {
        return ifStocknotice;
    }

    public void setIfStocknotice(String ifStocknotice) {
        this.ifStocknotice = ifStocknotice;
    }

    public String getIfFree() {
        return ifFree;
    }

    public void setIfFree(String ifFree) {
        this.ifFree = ifFree;
    }

    public String getIfDisperseStock() {
        return ifDisperseStock;
    }

    public void setIfDisperseStock(String ifDisperseStock) {
        this.ifDisperseStock = ifDisperseStock;
    }

    public String getIfLadderPrice() {
        return ifLadderPrice;
    }

    public void setIfLadderPrice(String ifLadderPrice) {
        this.ifLadderPrice = ifLadderPrice;
    }

    public String getIfEntityCode() {
        return ifEntityCode;
    }

    public void setIfEntityCode(String ifEntityCode) {
        this.ifEntityCode = ifEntityCode;
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

    public Long getEveryTimeCnt() {
        return everyTimeCnt;
    }

    public void setEveryTimeCnt(Long everyTimeCnt) {
        this.everyTimeCnt = everyTimeCnt;
    }

    public List<StockVO> getStockList() {
        return stockList;
    }

    public void setStockList(List<StockVO> stockList) {
        this.stockList = stockList;
    }

    public String getMainCatgs() {
        return mainCatgs;
    }

    public void setMainCatgs(String mainCatgs) {
        this.mainCatgs = mainCatgs;
    }

    public String getMainCatgName() {
        return mainCatgName;
    }

    public void setMainCatgName(String mainCatgName) {
        this.mainCatgName = mainCatgName;
    }

    public Long getBuyCnt() {
        return buyCnt;
    }

    public void setBuyCnt(Long buyCnt) {
        this.buyCnt = buyCnt;
    }

	public Boolean getIsNeedStock() {
		return isNeedStock;
	}

	public void setIsNeedStock(Boolean isNeedStock) {
		this.isNeedStock = isNeedStock;
	}

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public Long getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(Long priceValue) {
        this.priceValue = priceValue;
    }
 
     
    
}
