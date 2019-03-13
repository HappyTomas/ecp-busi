package com.zengshi.ecp.goods.dubbo.dto.gdsinfo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2MediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.GdsStockRepRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoRespDTO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 单品返回 DTO<br>
 * Date:2015年10月9日下午2:17:48 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsSkuInfoRespDTO extends BaseResponseDTO {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 1L;

    /**
     * 单品id
     */
    private Long id;

    /**
     * 当前快照id
     */
    private Long snapId;

    /**
     * ISBN
     */
    private String isbn;

    /**
     * 单品属性串
     */
    private String skuProps;

    /**
     * 商品编码
     */
    private Long gdsId;

    /**
     * 单品名称（与商品同值）
     */
    private String gdsName;

    /**
     * 单品主分类编码
     */
    private String mainCatgs;

    /**
     * 单品主分类名称
     */
    private String mainCatgName;

    /**
     * 目录Id
     */
    private Long catlogId;

    /**
     * 默认价格
     */
    private Long commonPrice = 0L;

    /**
     * 主分类
     */
    private GdsCategoryRespDTO mainCategory;

    /**
     * 平台分类
     */
    private List<GdsCategoryRespDTO> platformCategory;

    /**
     * 店铺分类
     */
    private List<GdsCategoryRespDTO> shopCategory;

    /**
     * 单品主图
     */
    private GdsMediaRespDTO mainPic;

    /**
     * 单品图片
     */
    private List<GdsSku2MediaRespDTO> sku2MediaRespDTOs;

    /**
     * 单品价格
     */
    private List<GdsSku2PriceRespDTO> sku2PriceRespDTOs;

    /**
     * 单品对应属性
     */
    private List<GdsPropRespDTO> props;

    /**
     * 单品所有属性Map key为属性id 当查询条件中带有 skuQueryOption.PROP时有值
     */
    private Map<String, GdsPropRespDTO> allPropMaps;

    /**
     * 单品对应商品属性
     */
    private List<GdsPropRespDTO> gdsProps;

    /**
     * 单品库存真实值 (详情用)
     */
    private Long realAmount = 0L;

    /**
     * 当前查询条件默认价格
     */
    private Long realPrice = 0L;

    /**
     * 当前查询条件默认价格
     */
    private Long discountPrice = 0L;

    /**
     * 单品副标题（与商品同值）
     */
    private String gdsSubHead;

    /**
     * 单品指导价 与商品同值
     */
    private Long guidePrice = 0L;

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
    private Long gdsTypeId;

    /**
     * 商品类型（与商品同值）
     */
    private String gdsTypeCode;

    /**
     * 商品类型名称（与商品同值）
     */
    private String gdsTypeName;
    /**
     * 是否需要库存
     */
    private boolean ifNeedStock;
    /**
     * 是否允许购买多次
     */
    private boolean ifBuyMore;

    /**
     * 单品状态
     */
    private String gdsStatus;

    /**
     * 单品状态名称
     */
    private String gdsStatusName;

    /**
     * 单品审批状态（与商品同值）
     */
    private String skuApprove;

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
     * 店铺分类
     */
    private String shopCatgs;

    /**
     * 平台分类
     */
    private String platCatgs;

    /**
     * 总仓模式下的库存信息
     */
    private StockInfoRespDTO stockInfoDTO;

    /**
     * 分仓模式下的库存信息
     */
    private List<GdsStockRepRespDTO> stockRepDTOs;

    private String gdsApprove;
    /**
     * 富文本属性集合。
     */
    private Map<String,GdsPropRespDTO> richTextPropMap;
    
    //手机专享价
    private Long appSpecPrice;
    /**
     * 请求来源。用于区分发起请求的来源比如WEB，还是APP
     * 1.商城  2.APP 3.其它
     */
    private String fromSource;
    
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

    public GdsMediaRespDTO getMainPic() {
        return mainPic;
    }

    public void setMainPic(GdsMediaRespDTO mainPic) {
        this.mainPic = mainPic;
    }

    public List<GdsSku2MediaRespDTO> getSku2MediaRespDTOs() {
        return sku2MediaRespDTOs;
    }

    public void setSku2MediaRespDTOs(List<GdsSku2MediaRespDTO> sku2MediaRespDTOs) {
        this.sku2MediaRespDTOs = sku2MediaRespDTOs;
    }

    public List<GdsPropRespDTO> getProps() {
        return props;
    }

    public void setProps(List<GdsPropRespDTO> props) {
        this.props = props;
    }

    public List<GdsSku2PriceRespDTO> getSku2PriceRespDTOs() {
        return sku2PriceRespDTOs;
    }

    public void setSku2PriceRespDTOs(List<GdsSku2PriceRespDTO> sku2PriceRespDTOs) {
        this.sku2PriceRespDTOs = sku2PriceRespDTOs;
    }

    public String getGdsTypeName() {
        return gdsTypeName;
    }

    public void setGdsTypeName(String gdsTypeName) {
        this.gdsTypeName = gdsTypeName;
    }

    public Long getRealAmount() {
        Long stocks = 0L;
        if (CollectionUtils.isNotEmpty(stockRepDTOs)) {
            for (GdsStockRepRespDTO dto : stockRepDTOs) {
                stocks += dto.calcAvalidStocks();
            }
        }
        if (stocks == 0L && this.stockInfoDTO != null) {
            stocks = this.stockInfoDTO.getAvailCount();
        }
        return stocks;
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

    public String getGdsStatus() {
        return gdsStatus;
    }

    public void setGdsStatus(String gdsStatus) {
        this.gdsStatus = gdsStatus == null ? null : gdsStatus.trim();
    }

    public String getSkuApprove() {
        return skuApprove;
    }

    public void setSkuApprove(String skuApprove) {
        this.skuApprove = skuApprove == null ? null : skuApprove.trim();
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

    public String getGdsTypeCode() {
        return gdsTypeCode;
    }

    public void setGdsTypeCode(String gdsTypeCode) {
        this.gdsTypeCode = gdsTypeCode;
    }

    public String getGdsStatusName() {
        return gdsStatusName;
    }

    public void setGdsStatusName(String gdsStatusName) {
        this.gdsStatusName = gdsStatusName;
    }

    public String getShopCatgs() {
        return shopCatgs;
    }

    public void setShopCatgs(String shopCatgs) {
        this.shopCatgs = shopCatgs;
    }

    public String getPlatCatgs() {
        return platCatgs;
    }

    public void setPlatCatgs(String platCatgs) {
        this.platCatgs = platCatgs;
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

    public StockInfoRespDTO getStockInfoRespDTO() {
        return stockInfoDTO;
    }

    public void setStockInfoRespDTO(StockInfoRespDTO stockInfoDTO) {
        this.stockInfoDTO = stockInfoDTO;
    }

    public List<GdsStockRepRespDTO> getStockRepDTOs() {
        return stockRepDTOs;
    }

    public void setStockRepDTOs(List<GdsStockRepRespDTO> stockRepDTOs) {
        this.stockRepDTOs = stockRepDTOs;
    }

    public Long getGdsTypeId() {
        return gdsTypeId;
    }

    public void setGdsTypeId(Long gdsTypeId) {
        this.gdsTypeId = gdsTypeId;
    }

    public String getMainCatgName() {
        return mainCatgName;
    }

    public void setMainCatgName(String mainCatgName) {
        this.mainCatgName = mainCatgName;
    }

    public StockInfoRespDTO getStockInfoDTO() {
        return stockInfoDTO;
    }

    public void setStockInfoDTO(StockInfoRespDTO stockInfoDTO) {
        this.stockInfoDTO = stockInfoDTO;
    }

    public List<GdsPropRespDTO> getGdsProps() {
        return gdsProps;
    }

    public void setGdsProps(List<GdsPropRespDTO> gdsProps) {
        this.gdsProps = gdsProps;
    }

    public Map<String, GdsPropRespDTO> getAllPropMaps() {
        return allPropMaps;
    }

    public void setAllPropMaps(Map<String, GdsPropRespDTO> allPropMaps) {
        this.allPropMaps = allPropMaps;
    }

    public GdsCategoryRespDTO getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(GdsCategoryRespDTO mainCategory) {
        this.mainCategory = mainCategory;
    }

    public List<GdsCategoryRespDTO> getPlatformCategory() {
        return platformCategory;
    }

    public void setPlatformCategory(List<GdsCategoryRespDTO> platformCategory) {
        this.platformCategory = platformCategory;
    }

    public List<GdsCategoryRespDTO> getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(List<GdsCategoryRespDTO> shopCategory) {
        this.shopCategory = shopCategory;
    }

    public Long calcAvalidStocks() {
        Long stocks = 0L;
        if (CollectionUtils.isNotEmpty(stockRepDTOs)) {
            for (GdsStockRepRespDTO dto : stockRepDTOs) {
                stocks += dto.calcAvalidStocks();
            }
        }
        if (stocks == 0L && this.stockInfoDTO != null) {
            stocks = this.stockInfoDTO.getAvailCount();
        }
        return stocks;
    }

    public Long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
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
    
    public Long getCommonPrice() {
        return commonPrice;
    }

    public void setCommonPrice(Long commonPrice) {
        this.commonPrice = commonPrice;
    }

    public String getGdsApprove() {
        return gdsApprove;
    }

    public void setGdsApprove(String gdsApprove) {
        this.gdsApprove = gdsApprove;
    }

	public boolean isIfNeedStock() {
		return ifNeedStock;
	}

	public void setIfNeedStock(boolean ifNeedStock) {
		this.ifNeedStock = ifNeedStock;
	}

	public boolean isIfBuyMore() {
		return ifBuyMore;
	}

	public void setIfBuyMore(boolean ifBuyMore) {
		this.ifBuyMore = ifBuyMore;
	}

    public Map<String, GdsPropRespDTO> getRichTextPropMap() {
        return richTextPropMap;
    }

    public void setRichTextPropMap(Map<String, GdsPropRespDTO> richTextPropMap) {
        this.richTextPropMap = richTextPropMap;
    }

	public Long getAppSpecPrice() {
		return appSpecPrice;
	}

	public void setAppSpecPrice(Long appSpecPrice) {
		this.appSpecPrice = appSpecPrice;
	}

    public String getFromSource() {
        return fromSource;
    }

    public void setFromSource(String fromSource) {
        this.fromSource = fromSource;
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
