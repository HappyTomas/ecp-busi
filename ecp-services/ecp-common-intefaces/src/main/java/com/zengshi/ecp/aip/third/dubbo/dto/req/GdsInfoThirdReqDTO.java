package com.zengshi.ecp.aip.third.dubbo.dto.req;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class GdsInfoThirdReqDTO  extends BaseInfo{
	
    private static final long serialVersionUID = 1L;
	
    private Long id;

    private Long snapId;

    private String gdsName;

    private String gdsSubHead;

    private Long guidePrice;

    private String gdsDesc;

    private String gdsPartlist;

    private Long gdsTypeId;

    private String gdsStatus;

    private String gdsApprove;

    private String url;

    private Integer sortNo;

    private Long shopId;

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

    private String ifSeniorPrice;

    private String ifScoreGds;

    private Long shipTemplateId;

    private Long supplierId;

    private String platCatgs;
    
    private String shopCatgs;

    private String mainCatgs;
    
    private String mainCatgsName;
    
    private Long catlogId;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String changePropStr;

    private String isbn;

    //单品列表
    private List<SkuInfoThirdReqDTO> skuInfos;
    
    private String province;//省份  优先商品库存仓库的区域  次之店铺区域  次之 默认同步设置区域 
    
    private String provinceName;//省份名称
    
    private String city;//城市  优先商品库存仓库的区域  次之店铺区域  次之 默认同步设置区域 
    
    private String cityName;//城市名称
    
    private String cid;//外部子分类id 即商品直接挂在这个分类上  需要通过mainCatgs转
    
    private String propStr;//属性值 pid:vid,pid:vid  格式要正确
    
    private String mainImageUrl;//商品主图url
    
    private String shipFeeType;//运费模式
    //可选值:seller（卖家承担）,buyer(买家承担);默认值:seller。卖家承担不用设置邮费和postage_id.买家承担的时候，必填邮费和postage_id 如果用户设置了运费模板会优先使用运费模板，否则要同步设置邮费（post_fee,express_fee,ems_fee）
    
    private String postageId;//外部系统运费模板id
    
    private String postFee;//邮费
    
    private String expressFee;//快递费
    
    private String emsFee;//ems邮费
    
    private String outCatgCode;//外部分类代码
    
    private String outCatgName;//外部分类名称
    
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


	public List<SkuInfoThirdReqDTO> getSkuInfos() {
		return skuInfos;
	}

	public void setSkuInfos(List<SkuInfoThirdReqDTO> skuInfos) {
		this.skuInfos = skuInfos;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getPropStr() {
		return propStr;
	}

	public void setPropStr(String propStr) {
		this.propStr = propStr;
	}

	public String getMainImageUrl() {
		return mainImageUrl;
	}

	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}

	public String getShipFeeType() {
		return shipFeeType;
	}

	public void setShipFeeType(String shipFeeType) {
		this.shipFeeType = shipFeeType;
	}

	public String getPostageId() {
		return postageId;
	}

	public void setPostageId(String postageId) {
		this.postageId = postageId;
	}

	public String getPostFee() {
		return postFee;
	}

	public void setPostFee(String postFee) {
		this.postFee = postFee;
	}

	public String getExpressFee() {
		return expressFee;
	}

	public void setExpressFee(String expressFee) {
		this.expressFee = expressFee;
	}

	public String getEmsFee() {
		return emsFee;
	}

	public void setEmsFee(String emsFee) {
		this.emsFee = emsFee;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getOutCatgCode() {
		return outCatgCode;
	}

	public void setOutCatgCode(String outCatgCode) {
		this.outCatgCode = outCatgCode;
	}

	public String getOutCatgName() {
		return outCatgName;
	}

	public void setOutCatgName(String outCatgName) {
		this.outCatgName = outCatgName;
	}

	public String getMainCatgsName() {
		return mainCatgsName;
	}

	public void setMainCatgsName(String mainCatgsName) {
		this.mainCatgsName = mainCatgsName;
	}
}
