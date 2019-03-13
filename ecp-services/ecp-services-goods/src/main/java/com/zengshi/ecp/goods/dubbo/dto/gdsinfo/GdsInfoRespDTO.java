package com.zengshi.ecp.goods.dubbo.dto.gdsinfo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2MediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceInfoResp;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * 商品信息-出参
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月27日下午9:01:36  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public class GdsInfoRespDTO extends BaseResponseDTO    {
	
	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 1L;

	/**
	 * 商品编码
	 */
	private Long id;
	
	/**
	 * 单品编码 (新增时返回  新增的单品id列表)
	 */
	private List<Long> skuIds;

	/**
	 * 快照编码
	 */
    private Long snapId;

    /**
     * 商品名称
     */
    private String gdsName;
    
    /**
     * 商品分类属性
     */
    private List<GdsPropRespDTO> props;
    
    /**
     * 参数属性
     */
    private List<GdsPropRespDTO> params;
    
    /**
     * add by gongxq 富文本属性（用于商品录入）
     */
    private List<GdsPropRespDTO> editoParam;
    
    /**
     * 商品所有属性Map key为属性id  当查询条件中带有 gdsQueryOption.PROP时有值
     */
    private Map<String,GdsPropRespDTO> allPropMaps;
    
    /**
     * 单品列表
     */
    private List<GdsSkuInfoRespDTO> skus;
    
    /**
     * 商品图片
     */
    private List<GdsGds2MediaRespDTO> medias;
    
    /**
     * 价格列表
     */
    private List<GdsPriceInfoResp> prices;
    
    /**
     * 商品积分
     */
    private List<GdsScoreExtRespDTO> scores;
    
	/**
	 * 商品主分类编码
	 */
	private String mainCatgs;
    
	/**
	 * 商品主分类名称
	 */
	private String mainCatgName;
    /**
     * 主分类
     */
    private GdsCategoryRespDTO mainCategory;
    /**
     * 平台分类
     */
    private List<GdsCategoryRespDTO> platformCategory;
    
    /**
     * 平台分类
     */
    private String platCatgs;
    
    /**
     * 店铺分类
     */
    private String shopCatgs;
    
    /**
     * 店铺分类
     */
    private List<GdsCategoryRespDTO> shopCategory;
    
    /**
     * 运费模板
     */
    private GdsShiptempRespDTO shiptemp;

    /**
     * 商品副标题
     */
    private String gdsSubHead;
    
    /**
     * 商品指导价
     */
    private Long guidePrice = 0L;
    
    /**
     * 商品归属目录
     */
    private Long catlogId;
    
    /**
     * 商品主图
     */
    private GdsMediaRespDTO mainPic;

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
     * 商品类型名称
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
     * 商品状态
     */
    private String gdsStatus;
    
    /**
     * 商品状态名称
     */
    private String gdsStatusName;

    /**
     * 商品审批状态
     */
    private String gdsApprove;
    
    /**
     * 商品对应线下状态商品审批状态
     */
    private String gdsOfflineApprove;

    /**
     * 商品路径
     */
    private String url;

    /**
     * 排序
     */
    private Integer sortNo;

    /**
     * 店铺编码
     */
    private Long  shopId;

    /**
     * 税率编码
     */
    private Long taxId;
    
    /**
     * 出版日期
     */
    private String gdsPublishTime;
    
    /**
     * 作者
     */
    private String gdsAuthor;

    /**
     * 商品标签
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
     * 商品状态名称
     */
    private String shipTemplateName;

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
     * 店铺状态
     */
    private String shopStatus;
    /**
     * 变更商品信息
     */
    private String changePropStr;
    /**
     * isbn号
     */
    private String isbn;
    /**
     * 商品审核开关
     * add by gxq. 1：开启;0：关闭
     */
    private String verifySwitch;
    /**
     * 商品类型
     */
    private GdsTypeRespDTO gdsTypeRespDTO;
    /**
     * 富文本属性集合。
     */
    private Map<String,GdsPropRespDTO> richTextPropMap;

    /**
     * 操作类型字段，用于商品导入接口返回操作类型信息
     */
    private String operType;
    
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

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
    }

    public List<GdsPropRespDTO> getProps() {
		return props;
	}

	public void setProps(List<GdsPropRespDTO> props) {
		this.props = props;
	}


	public List<GdsSkuInfoRespDTO> getSkus() {
		return skus;
	}

	public void setSkus(List<GdsSkuInfoRespDTO> skus) {
		this.skus = skus;
	}

	public List<GdsGds2MediaRespDTO> getMedias() {
		return medias;
	}

	public void setMedias(List<GdsGds2MediaRespDTO> medias) {
		this.medias = medias;
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

	public GdsShiptempRespDTO getShiptemp() {
		return shiptemp;
	}

	public void setShiptemp(GdsShiptempRespDTO shiptemp) {
		this.shiptemp = shiptemp;
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

    public GdsMediaRespDTO getMainPic() {
		return mainPic;
	}

	public void setMainPic(GdsMediaRespDTO mainPic) {
		this.mainPic = mainPic;
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

    public String getGdsOfflineApprove() {
		return gdsOfflineApprove;
	}

	public void setGdsOfflineApprove(String gdsOfflineApprove) {
		this.gdsOfflineApprove = gdsOfflineApprove;
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

    public GdsCategoryRespDTO getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(GdsCategoryRespDTO mainCategory) {
		this.mainCategory = mainCategory;
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

	public String getShipTemplateName() {
		return shipTemplateName;
	}

	public void setShipTemplateName(String shipTemplateName) {
		this.shipTemplateName = shipTemplateName;
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

	public List<GdsPriceInfoResp> getPrices() {
		return prices;
	}

	public void setPrices(List<GdsPriceInfoResp> prices) {
		this.prices = prices;
	}

	public Long getGdsTypeId() {
		return gdsTypeId;
	}

	public void setGdsTypeId(Long gdsTypeId) {
		this.gdsTypeId = gdsTypeId;
	}

	public List<GdsPropRespDTO> getParams() {
		return params;
	}

	public void setParams(List<GdsPropRespDTO> params) {
		this.params = params;
	}

	public String getMainCatgName() {
		return mainCatgName;
	}

	public void setMainCatgName(String mainCatgName) {
		this.mainCatgName = mainCatgName;
	}

	public Map<String, GdsPropRespDTO> getAllPropMaps() {
		return allPropMaps;
	}

	public void setAllPropMaps(Map<String, GdsPropRespDTO> allPropMaps) {
		this.allPropMaps = allPropMaps;
	}

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    public List<GdsPropRespDTO> getEditoParam() {
        return editoParam;
    }

    public List<Long> getSkuIds() {
		return skuIds;
	}

	public void setSkuIds(List<Long> skuIds) {
		this.skuIds = skuIds;
	}

	public void setEditoParam(List<GdsPropRespDTO> editoParam) {
        this.editoParam = editoParam;
    }

	public List<GdsScoreExtRespDTO> getScores() {
		return scores;
	}

	public void setScores(List<GdsScoreExtRespDTO> scores) {
		this.scores = scores;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGdsPublishTime() {
        return gdsPublishTime;
    }

    public void setGdsPublishTime(String gdsPublishTime) {
        this.gdsPublishTime = gdsPublishTime;
    }

    public String getGdsAuthor() {
        return gdsAuthor;
    }

    public void setGdsAuthor(String gdsAuthor) {
        this.gdsAuthor = gdsAuthor;
    }

    public String getVerifySwitch() {
        return verifySwitch;
    }

    public void setVerifySwitch(String verifySwitch) {
        this.verifySwitch = verifySwitch;
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

	public boolean isIfNeedStock() {
		return ifNeedStock;
	}

	public void setIfNeedStock(boolean ifNeedStock) {
		this.ifNeedStock = ifNeedStock;
	}

	public boolean getIfBuyMore() {
		return ifBuyMore;
	}

	public void setIfBuyMore(boolean ifBuyMore) {
		this.ifBuyMore = ifBuyMore;
	}

    public GdsTypeRespDTO getGdsTypeRespDTO() {
        return gdsTypeRespDTO;
    }

    public void setGdsTypeRespDTO(GdsTypeRespDTO gdsTypeRespDTO) {
        this.gdsTypeRespDTO = gdsTypeRespDTO;
    }

    public Map<String, GdsPropRespDTO> getRichTextPropMap() {
        return richTextPropMap;
    }

    public void setRichTextPropMap(Map<String, GdsPropRespDTO> richTextPropMap) {
        this.richTextPropMap = richTextPropMap;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
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
