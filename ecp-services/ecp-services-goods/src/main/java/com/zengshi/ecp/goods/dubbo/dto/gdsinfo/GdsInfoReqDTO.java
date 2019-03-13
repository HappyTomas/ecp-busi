package com.zengshi.ecp.goods.dubbo.dto.gdsinfo;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsInfoShopIdxCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PropReqDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;

public class GdsInfoReqDTO extends BaseInfo<GdsInfoShopIdxCriteria> {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商品Id
     */
    private Long id;
   
    /**
     * 查询表的分表下标
     */
    private int tableIndex;

    /**
     * 单品编码
     */
    private Long skuId;

    /**
     * 单品参数列表（用于根据属性id，属性值查询对应的商品）
     */
    private List<GdsSku2PropReqDTO> skuProps;

    /**
     * 是否需要更新商品图片
     */
    private boolean isUpdatePic = true;
    
    /**
	 * 价格排序
	 */
	private String priceSort;

    /**
     * 是否需要从属性分类配置表复制配置
     */
    private boolean isCopyPropFromConfiged = false;

    /**
     * 只查询对应目录的产品目录（查询用）
     */
    private Long catlogId;

    /**
     * 只查询对应目录的产品（查询用）
     */
    private List<Long> catalogIds;

    /**
     * 只查询对应属性Id的属性（查询用）
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
     * 企业编码
     */
    private Long companyId;

    /**
     * 如果有值 将会覆盖baseInfo
     */
    private Long staffId;

    /**
     * 快照编码
     */
    private Long snapId;

    /**
     * 商品名称
     */
    private String gdsName;

    /**
     * 商品主分类编码
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
     * 商品副标题
     */
    private String gdsSubHead;

    /**
     * 商品知道价
     */
    private Long guidePrice;

    /**
     * 商品描述
     */
    private String gdsDesc;

    /**
     * 包装清单
     */
    private String gdsPartlist;

    /**
     * 商品类型
     */
    private Long gdsTypeId;

    /**
     * 商品类型
     */
    private String gdsTypeCode;

    /**
     * 商品状态
     */
    private String gdsStatus;

    /**
     * 商品状态
     */
    private List<String> gdsStatusArr;

    /**
     * 商品审批状态
     */
    private String gdsApprove;

    /**
     * 商品路径
     */
    private String gdsUrl;

    /**
     * 排序
     */
    private Long sortNo;

    /**
     * 店铺编码
     */
    private Long shopId;

    /**
     * 税率编码
     */
    private Long taxId;

    /**
     * 商品标签
     */
    private String gdsLabel;

    /**
     * 自动上架时间
     */
    private Timestamp putonTime;
    /**
     * 自动上架时间条件开始时间
     */
    private Timestamp putonTimeBegin;
    /**
     * 自动上架时间条件结束时间
     */ 
    private Timestamp putonTimeEnd;

    /**
     * 自动下架时间
     */
    private Timestamp putoffTime;
    /**
     * 自动下架时间条件开始时间
     */
    private Timestamp putoffTimeBegin;
    /**
     * 自动下架时间条件结束时间
     */ 
    private Timestamp putoffTimeEnd;

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
     * 是否开启高级价格
     */
    private String ifSeniorPrice;

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
     * 商品查询参数
     */
    private GdsQueryOption[] gdsQueryOptions;

    /**
     * 单品查询参数
     */
    private SkuQueryOption[] skuQuerys;

    /**
     * 重要！！！ 是否需要查询单品表， 如果为false 直接查询索引表返回 展示 名称,类型,状态，归属店铺，商品类型，商品id，
     * 如果需要全部单品信息，设置为true，则查询索引表后，还会把商品表的数据load出来
     */
    private boolean fullInfo = true;

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
     * 操作id编码
     */
    private Long[] ids;

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
     * 是否需要更新运费模板 上下架时，不需要更新运费模板
     */
    private Boolean isUpdateShipTemplate;

    /**
     * 变更商品信息字符串
     */
    private String changePropStr;

    /**
     * 销量排行数量
     */
    private int topNum;

    /**
     * 唯一编码
     */
    private String isbn;

    /**
     * ISBN字段是否使用模糊查询
     */
    private boolean ifIsbnFuzzyQeury=true;
    
    /**
     * 站点ID条件。
     */
    private List<Long> siteIds;
    /**
     * 排除的商品类型.
     */
    private List<Long> excludeGdsTypes;

    /*
     * 新华社项目中的栏目
     */
    private String columnCatgs;
    /**
     * 产品名称匹配条件
     */
    private String gdsNameEqual;

    /**
     * 是否维护商品编码映射表（默认为true）
     */
    private boolean ifMaintainGdsInterfaceGds=true;
    
    
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
    
    
    private boolean ext1Null = false;;
    
    private boolean ext1NotNull = false;
    
    private boolean ext2Null = false;;
    
    private boolean ext2NotNull = false;
    
    
    private boolean ext3Null = false;;
    
    private boolean ext3NotNull = false;
    
    private boolean ext4Null = false;;
    
    private boolean ext4NotNull = false;
    
    
    private boolean ext5Null = false;;
    
    private boolean ext5NotNull = false;
    
    // 是否使用声明式事务.
    private boolean handTransaction = false;
    // 是否发送增量消息
    private boolean sendIdxMsg = true;
    
    

    public boolean isIfMaintainGdsInterfaceGds() {
        return ifMaintainGdsInterfaceGds;
    }

    public void setIfMaintainGdsInterfaceGds(boolean ifMaintainGdsInterfaceGds) {
        this.ifMaintainGdsInterfaceGds = ifMaintainGdsInterfaceGds;
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
        this.gdsStatus = gdsStatus == null ? null : gdsStatus.trim();
    }

    public String getGdsApprove() {
        return gdsApprove;
    }

    public void setGdsApprove(String gdsApprove) {
        this.gdsApprove = gdsApprove == null ? null : gdsApprove.trim();
    }

    public String getGdsUrl() {
        return gdsUrl;
    }

    public void setGdsUrl(String gdsUrl) {
        this.gdsUrl = gdsUrl == null ? null : gdsUrl.trim();
    }

    public Long getSortNo() {
        return sortNo;
    }

    public void setSortNo(Long sortNo) {
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

    public GdsQueryOption[] getGdsQueryOptions() {
        return gdsQueryOptions;
    }

    public void setGdsQueryOptions(GdsQueryOption[] gdsQueryOptions) {
        this.gdsQueryOptions = gdsQueryOptions;
    }

    public SkuQueryOption[] getSkuQuerys() {
        return skuQuerys;
    }

    public void setSkuQuerys(SkuQueryOption[] skuQuerys) {
        this.skuQuerys = skuQuerys;
    }

    public boolean getFullInfo() {
        return fullInfo;
    }

    public void setFullInfo(boolean fullInfo) {
        this.fullInfo = fullInfo;
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

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public String getIfSeniorPrice() {
        return ifSeniorPrice;
    }

    public void setIfSeniorPrice(String ifSeniorPrice) {
        this.ifSeniorPrice = ifSeniorPrice;
    }

    public String getMainCatgs() {
        return mainCatgs;
    }

    public void setMainCatgs(String mainCatgs) {
        this.mainCatgs = mainCatgs;
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
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

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public List<GdsSku2PropReqDTO> getSkuProps() {
        return skuProps;
    }

    public void setSkuProps(List<GdsSku2PropReqDTO> skuProps) {
        this.skuProps = skuProps;
    }

    public List<Long> getCatalogIds() {
        return catalogIds;
    }

    public void setCatalogIds(List<Long> catalogIds) {
        this.catalogIds = catalogIds;
    }

    public Boolean getIsUpdateShipTemplate() {
        return isUpdateShipTemplate;
    }

    public void setIsUpdateShipTemplate(Boolean isUpdateShipTemplate) {
        this.isUpdateShipTemplate = isUpdateShipTemplate;
    }

    public String getChangePropStr() {
        return changePropStr;
    }

    public void setChangePropStr(String changePropStr) {
        this.changePropStr = changePropStr;
    }

    public Long getCatlogId() {
        return catlogId;
    }

    public void setCatlogId(Long catlogId) {
        this.catlogId = catlogId;
    }

    public int getTopNum() {
        return topNum;
    }

    public void setTopNum(int topNum) {
        this.topNum = topNum;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isIfIsbnFuzzyQeury() {
        return ifIsbnFuzzyQeury;
    }

    public void setIfIsbnFuzzyQeury(boolean ifIsbnFuzzyQeury) {
        this.ifIsbnFuzzyQeury = ifIsbnFuzzyQeury;
    }

    public List<Long> getPropIds() {
        return propIds;
    }

    public void setPropIds(List<Long> propIds) {
        this.propIds = propIds;
    }

    public boolean isUpdatePic() {
        return isUpdatePic;
    }

    public void setUpdatePic(boolean isUpdatePic) {
        this.isUpdatePic = isUpdatePic;
    }

    public boolean isCopyPropFromConfiged() {
        return isCopyPropFromConfiged;
    }

    public void setCopyPropFromConfiged(boolean isCopyPropFromConfiged) {
        this.isCopyPropFromConfiged = isCopyPropFromConfiged;
    }

    public int getTableIndex() {
        return tableIndex;
    }

    public void setTableIndex(int tableIndex) {
        this.tableIndex = tableIndex;
    }

	public String getPriceSort() {
		return priceSort;
	}

	public void setPriceSort(String priceSort) {
		this.priceSort = priceSort;
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

    public List<Long> getSiteIds() {
        return siteIds;
    }

    public void setSiteIds(List<Long> siteIds) {
        this.siteIds = siteIds;
    }

    public List<Long> getExcludeGdsTypes() {
        return excludeGdsTypes;
    }

    public void setExcludeGdsTypes(List<Long> excludeGdsTypes) {
        this.excludeGdsTypes = excludeGdsTypes;
    }

	public String getColumnCatgs() {
		return columnCatgs;
	}

	public void setColumnCatgs(String columnCatgs) {
		this.columnCatgs = columnCatgs;
	}

    public String getGdsNameEqual() {
        return gdsNameEqual;
    }

    public void setGdsNameEqual(String gdsNameEqual) {
        this.gdsNameEqual = gdsNameEqual;
    }

    public Timestamp getPutoffTimeBegin() {
        return putoffTimeBegin;
    }

    public void setPutoffTimeBegin(Timestamp putoffTimeBegin) {
        this.putoffTimeBegin = putoffTimeBegin;
    }

    public Timestamp getPutoffTimeEnd() {
        return putoffTimeEnd;
    }

    public void setPutoffTimeEnd(Timestamp putoffTimeEnd) {
        this.putoffTimeEnd = putoffTimeEnd;
    }

	public Timestamp getPutonTimeBegin() {
		return putonTimeBegin;
	}

	public void setPutonTimeBegin(Timestamp putonTimeBegin) {
		this.putonTimeBegin = putonTimeBegin;
	}

	public Timestamp getPutonTimeEnd() {
		return putonTimeEnd;
	}

	public void setPutonTimeEnd(Timestamp putonTimeEnd) {
		this.putonTimeEnd = putonTimeEnd;
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

	public boolean isExt1Null() {
		return ext1Null;
	}

	public void setExt1Null(boolean ext1Null) {
		this.ext1Null = ext1Null;
	}

	public boolean isExt1NotNull() {
		return ext1NotNull;
	}

	public void setExt1NotNull(boolean ext1NotNull) {
		this.ext1NotNull = ext1NotNull;
	}

	public boolean isExt2Null() {
		return ext2Null;
	}

	public void setExt2Null(boolean ext2Null) {
		this.ext2Null = ext2Null;
	}

	public boolean isExt2NotNull() {
		return ext2NotNull;
	}

	public void setExt2NotNull(boolean ext2NotNull) {
		this.ext2NotNull = ext2NotNull;
	}

	public boolean isExt3Null() {
		return ext3Null;
	}

	public void setExt3Null(boolean ext3Null) {
		this.ext3Null = ext3Null;
	}

	public boolean isExt3NotNull() {
		return ext3NotNull;
	}

	public void setExt3NotNull(boolean ext3NotNull) {
		this.ext3NotNull = ext3NotNull;
	}

	public boolean isExt4Null() {
		return ext4Null;
	}

	public void setExt4Null(boolean ext4Null) {
		this.ext4Null = ext4Null;
	}

	public boolean isExt4NotNull() {
		return ext4NotNull;
	}

	public void setExt4NotNull(boolean ext4NotNull) {
		this.ext4NotNull = ext4NotNull;
	}

	public boolean isExt5Null() {
		return ext5Null;
	}

	public void setExt5Null(boolean ext5Null) {
		this.ext5Null = ext5Null;
	}

	public boolean isExt5NotNull() {
		return ext5NotNull;
	}

	public void setExt5NotNull(boolean ext5NotNull) {
		this.ext5NotNull = ext5NotNull;
	}

    public boolean isHandTransaction() {
        return handTransaction;
    }

    public void setHandTransaction(boolean handTransaction) {
        this.handTransaction = handTransaction;
    }

    public boolean isSendIdxMsg() {
        return sendIdxMsg;
    }

    public void setSendIdxMsg(boolean sendIdxMsg) {
        this.sendIdxMsg = sendIdxMsg;
    }

    
    
    
    
}
