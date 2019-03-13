package com.zengshi.ecp.goods.dubbo.dto.gdsinfo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import com.zengshi.ecp.goods.dao.model.GdsSkuInfoShopIdxCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2MediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * 单品信息入参DTO Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月25日下午5:16:13 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsSkuInfoReqDTO extends BaseInfo<GdsSkuInfoShopIdxCriteria> {
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 1L;

    /**
     * 单品Id
     */
    private Long id;
    /**
     * 快照Id
     */
    private Long snapId;

    /**
     * 单品属性串
     */
    private String skuProps;

    /**
     * 商品编码
     */
    private Long gdsId;

    /**
     * 单品名称（同商品名称）
     */
    private String gdsName;

    /**
     * 需要查询的目录编码列表 （查询用）
     */
    private List<Long> catalogIds;
    
    /**
     * 需要查询的属性列表（查询用）
     */
    private List<Long> propIds;
    
    /**
     * 只查询对应属性类型（查询用）
     */
    private List<String> propTypes;
    
    /**
     * 只查询对应属性值类型（查询用）
     */
    private List<String> propValueTypes;

    /**
     * 只查询对应属性属性类型（查询用）
     */
    private List<String> propInputTypes;

    
    /**
     * 单品主分类编码
     */
    private String mainCatgs;
    
    /**
     * 商品分类编码列表（查询）
     */
    private List<String> catgs;

    /**
     * 平台分类
     */
    private String platCatgs;

    /**
     * 店铺分类
     */
    private String shopCatgs;

    /**
     * 只查询对应目录的产品
     */
    private Long catlogId;
    
    /**
     * 唯一编码
     */
    private String isbn;

    /**
     * 单品图片
     */
    private List<GdsSku2MediaReqDTO> sku2MediaReqDTOs;

    /**
     * 单品价格
     */
    private List<GdsSku2PriceReqDTO> sku2PriceReqDTOs;

    /**
     * 单品属性
     */
    private List<GdsSku2PropReqDTO> sku2PropReqDTOs;
    
    /**
     * 商品属性
     */
    private List<GdsSku2PropReqDTO> gdsProps;

    /**
     * 单品库存
     */
    private List<StockInfoReqDTO> stocks;

    /**
     * 单品库存共仓模式，初始化库存
     */
    private Long realCount;

    /**
     * 分仓模式下的库存信息
     */
    private List<StockInfoReqDTO> stockInfos;

    /**
     * 单品查询枚举
     */
    private SkuQueryOption[] skuQuery;

    /**
     * 重要！！！ 是否需要查询单品表，如果为false 直接查询索引表返回 展示名称,单品属性串,类型,状态，归属店铺，商品类型,单品id，商品id
     * 如果需要全部单品信息，设置为true，则查询索引表后，还会把单品表的数据load出来
     */
    private boolean fullInfo = true;

    /**
     * 属信息状态
     */
    private String status;


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
    private BigDecimal guidePrice;

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
     * 商品类型编码
     */
    private Long gdsTypeId;

    /**
     * 商品类型（与商品同值）
     */
    private String gdsTypeName;

    /**
     * 单品状态
     */
    private String gdsStatus;
    
    /**
     * 单品状态
     */
    private List<String> gdsStatusArr;

    /**
     * 单品状态名称
     */
    private String gdsStatusName;

    /**
     * 单品审批状态（与商品同值）
     */
    private String gdsApprove;

    /**
     * 单品URL
     */
    private String url;

    /**
     * 排序
     */
    private Integer sortNo;

    /**
     * 店铺编码（与商品同值）
     */
    private Long shopId;

    /**
     * 税费编码（与商品同值）
     */
    private Long taxId;

    /**
     * 单品标签（与商品同值）
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
     * 发货日期（预留）
     */
    private Long postTime;
    
    /**
     * 是否积分商品
     */
    private String ifScoreGds;

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
     * 是否开启高级价格
     */
    private String ifSeniorPrice;

    /**
     * 运费模板编码
     */
    private Long shipTemplateId;

    /**
     * 供货商
     */
    private Long supplierId;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 创建工号
     */
    private Long createStaff;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 更新工号
     */
    private Long updateStaff;

    /**
     * 归属公司编码
     */
    private Long companyId;

    /**
     * 创建时间开始
     */
    private Timestamp begCreateTime;

    /**
     * 创建时间结束
     */
    private Timestamp endCreateTime;

    /**
     * 更新时间开始
     */
    private Timestamp begUpdateTime;

    /**
     * 更新时间结束
     */
    private Timestamp endUpdateTime;

    /**
     * 国家编码
     */
    private String countryCode;

    /**
     * 省份编码
     */
    private String provinceCode;

    /**
     * 地市编码
     */
    private String cityCode;
    
    
    /**
     * 分表下标
     */
    private Integer tableIndex;
    
    //普通价格
    private Long commonPrice;

    //排序字段规则，字段名和字段排序使用半角逗号间隔
    private List<String> sortRule;
    
    private List<Long> siteIds;
    /**
     * 产品名称匹配条件
     */
    private String gdsNameEqual;

    private Long appSpecPrice;
    
    private Long staffId;
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
    
    public Long getCommonPrice() {
        return commonPrice;
    }

    public void setCommonPrice(Long commonPrice) {
        this.commonPrice = commonPrice;
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

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
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

    public List<GdsSku2MediaReqDTO> getSku2MediaReqDTOs() {
        return sku2MediaReqDTOs;
    }

    public void setSku2MediaReqDTOs(List<GdsSku2MediaReqDTO> sku2MediaReqDTOs) {
        this.sku2MediaReqDTOs = sku2MediaReqDTOs;
    }

    public List<GdsSku2PriceReqDTO> getSku2PriceReqDTOs() {
        return sku2PriceReqDTOs;
    }

    public void setSku2PriceReqDTOs(List<GdsSku2PriceReqDTO> sku2PriceReqDTOs) {
        this.sku2PriceReqDTOs = sku2PriceReqDTOs;
    }

    public List<GdsSku2PropReqDTO> getSku2PropReqDTOs() {
        return sku2PropReqDTOs;
    }

    public void setSku2PropReqDTOs(List<GdsSku2PropReqDTO> sku2PropReqDTOs) {
        this.sku2PropReqDTOs = sku2PropReqDTOs;
    }

    public List<StockInfoReqDTO> getStocks() {
        return stocks;
    }

    public void setStocks(List<StockInfoReqDTO> stocks) {
        this.stocks = stocks;
    }

    public SkuQueryOption[] getSkuQuery() {
        return skuQuery;
    }

    public void setSkuQuery(SkuQueryOption[] skuQuery) {
        this.skuQuery = skuQuery;
    }

    public boolean getFullInfo() {
        return fullInfo;
    }

    public void setFullInfo(boolean fullInfo) {
        this.fullInfo = fullInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        this.gdsSubHead = gdsSubHead == null ? null : gdsSubHead.trim();
    }

    public BigDecimal getGuidePrice() {
        return guidePrice;
    }

    public void setGuidePrice(BigDecimal guidePrice) {
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

    public String getIfScoreGds() {
        return ifScoreGds;
    }

    public void setIfScoreGds(String ifScoreGds) {
        this.ifScoreGds = ifScoreGds;
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
        this.ifStocknotice = ifStocknotice == null ? null : ifStocknotice
                .trim();
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
        this.ifDisperseStock = ifDisperseStock == null ? null : ifDisperseStock
                .trim();
    }

    public String getIfLadderPrice() {
        return ifLadderPrice;
    }

    public void setIfLadderPrice(String ifLadderPrice) {
        this.ifLadderPrice = ifLadderPrice == null ? null : ifLadderPrice
                .trim();
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

    public String getGdsTypeCode() {
        return gdsTypeCode;
    }

    public void setGdsTypeCode(String gdsTypeCode) {
        this.gdsTypeCode = gdsTypeCode;
    }

    public String getGdsTypeName() {
        return gdsTypeName;
    }

    public void setGdsTypeName(String gdsTypeName) {
        this.gdsTypeName = gdsTypeName;
    }

    public String getGdsStatusName() {
        return gdsStatusName;
    }

    public void setGdsStatusName(String gdsStatusName) {
        this.gdsStatusName = gdsStatusName;
    }

    public String getIfSeniorPrice() {
        return ifSeniorPrice;
    }

    public void setIfSeniorPrice(String ifSeniorPrice) {
        this.ifSeniorPrice = ifSeniorPrice;
    }

    public Long getRealCount() {
        return realCount;
    }

    public void setRealCount(Long realCount) {
        this.realCount = realCount;
    }

    public String getMainCatgs() {
        return mainCatgs;
    }

    public void setMainCatgs(String mainCatgs) {
        this.mainCatgs = mainCatgs;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<StockInfoReqDTO> getStockInfos() {
        return stockInfos;
    }

    public void setStockInfos(List<StockInfoReqDTO> stockInfos) {
        this.stockInfos = stockInfos;
    }

    public Timestamp getBegCreateTime() {
        return begCreateTime;
    }

    public void setBegCreateTime(Timestamp begCreateTime) {
        this.begCreateTime = begCreateTime;
    }

    public Timestamp getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Timestamp endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public Timestamp getBegUpdateTime() {
        return begUpdateTime;
    }

    public void setBegUpdateTime(Timestamp begUpdateTime) {
        this.begUpdateTime = begUpdateTime;
    }

    public Timestamp getEndUpdateTime() {
        return endUpdateTime;
    }

    public void setEndUpdateTime(Timestamp endUpdateTime) {
        this.endUpdateTime = endUpdateTime;
    }

    public Long getGdsTypeId() {
        return gdsTypeId;
    }

    public void setGdsTypeId(Long gdsTypeId) {
        this.gdsTypeId = gdsTypeId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public List<String> getGdsStatusArr() {
        return gdsStatusArr;
    }

    public void setGdsStatusArr(List<String> gdsStatusArr) {
        this.gdsStatusArr = gdsStatusArr;
    }

    public List<GdsSku2PropReqDTO> getGdsProps() {
        return gdsProps;
    }

    public void setGdsProps(List<GdsSku2PropReqDTO> gdsProps) {
        this.gdsProps = gdsProps;
    }

    public List<Long> getCatalogIds() {
        return catalogIds;
    }

    public void setCatalogIds(List<Long> catalogIds) {
        this.catalogIds = catalogIds;
    }

    public Long getCatlogId() {
        return catlogId;
    }

    public void setCatlogId(Long catlogId) {
        this.catlogId = catlogId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Long> getPropIds() {
        return propIds;
    }

    public void setPropIds(List<Long> propIds) {
        this.propIds = propIds;
    }

    public Integer getTableIndex() {
        return tableIndex;
    }

    public void setTableIndex(Integer tableIndex) {
        this.tableIndex = tableIndex;
    }

    public List<String> getCatgs() {
        return catgs;
    }

    public void setCatgs(List<String> catgs) {
        this.catgs = catgs;
    }

    public List<String> getPropTypes() {
        return propTypes;
    }

    public void setPropTypes(List<String> propTypes) {
        this.propTypes = propTypes;
    }

    public List<String> getPropValueTypes() {
        return propValueTypes;
    }

    public void setPropValueTypes(List<String> propValueTypes) {
        this.propValueTypes = propValueTypes;
    }

    public List<String> getPropInputTypes() {
        return propInputTypes;
    }

    public void setPropInputTypes(List<String> propInputTypes) {
        this.propInputTypes = propInputTypes;
    }

    public List<String> getSortRule() {
        return sortRule;
    }

    public void setSortRule(List<String> sortRule) {
        this.sortRule = sortRule;
    }

    public List<Long> getSiteIds() {
        return siteIds;
    }

    public void setSiteIds(List<Long> siteIds) {
        this.siteIds = siteIds;
    }

    public String getGdsNameEqual() {
        return gdsNameEqual;
    }

    public void setGdsNameEqual(String gdsNameEqual) {
        this.gdsNameEqual = gdsNameEqual;
    }

	public Long getAppSpecPrice() {
		return appSpecPrice;
	}

	public void setAppSpecPrice(Long appSpecPrice) {
		this.appSpecPrice = appSpecPrice;
	}

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
	
    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4;
    }

    public String getExt5() {
        return ext5;
    }

    public void setExt5(String ext5) {
        this.ext5 = ext5;
    }
    
    
}
