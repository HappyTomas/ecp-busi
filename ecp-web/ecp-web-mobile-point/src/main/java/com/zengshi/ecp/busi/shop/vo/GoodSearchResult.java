package com.zengshi.ecp.busi.shop.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

import com.zengshi.ecp.busi.search.SearchUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.search.dubbo.search.UncertainField;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.alibaba.fastjson.JSONArray;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年9月9日下午5:23:12 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class GoodSearchResult extends BaseResponseDTO {

    private final int GDSDESCCONTENT_LENGTH = 115;

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = 7299963184185039508L;

    private final static String SUFFIX_IMAGE_SIZE = "_220x220!";

    private String id;

    private long firstSkuId;

    private List<String> promotionType;

    private String ifLadderPrice;

    private String mainPic;

    private String imageUrl;

    private long defaultPrice;

    private long discountPrice;

    private String discount;

    // private String yunPrice;

    private String gdsName;

    private String gdsNameSrc;

    private String gdsSubHead;

    private String gdsSubHeadSrc;

    private String shopName;

    private String shopId;

    private long sales;

    private String updateTime;

    private long storage;

    private String storageDesc;

    /**
     * add by gongxq 库存状态，用于购物车按钮的判断
     */
    private String storageStatus;

    private String gdsDesc;

    private String gdsDescContent;

    private String gdsTypeId;

    //private String uncertainfield_prop;

    //private Map<String, UncertainField> uncertainFieldMap;

    private String uncertainfield_discountInfo;

    private String mainCategoryCode;
    
    private String mainCategoryValue; 

    private List<String> categoryPath;

    private String skuProps;

    private long guidePrice;

    private String isbn;

    private String publishDate;

    private String publisher;

    private List<String> author;

    private boolean ifNeedStockAmount;

    private boolean ifGdsTypeBuyonce;

    private String ifFree;

    public void render() {

        /*if(isbn!=null){
            isbn=isbn.trim();
        }
        if(publishDate!=null){
            publishDate=publishDate.trim();
        }
        if(publisher!=null){
            publisher=publisher.trim();
        }
        if(author!=null&&author.size()==1&&StringUtils.isBlank(author.get(0).trim())){
            author=null;
        }*/

        // 根据会员等级获取对应的折扣价和折扣率
        String staffLevelCode = StaffLocaleUtil.getStaff().getStaffLevelCode();
        String priceFieldName = SearchUtil.FIELD_DISCOUNTPRICE + staffLevelCode;
        String discountFieldName = SearchUtil.FIELD_DISCOUNT + staffLevelCode;

        if (StringUtils.isNotBlank(uncertainfield_discountInfo)) {
            List<UncertainField> uncertainFieldList = JSONArray.parseArray(this.uncertainfield_discountInfo, UncertainField.class);
            if (CollectionUtils.isNotEmpty(uncertainFieldList)) {
                for (UncertainField ucertainField : uncertainFieldList) {
                    if (StringUtils.equals(ucertainField.getName(), priceFieldName)) {
                        if (CollectionUtils.isNotEmpty(ucertainField.getValue())) {
                            this.discountPrice = Long.parseLong(ucertainField.getValue().get(0));
                        }
                    } else if (StringUtils.equals(ucertainField.getName(), discountFieldName)) {
                        if (CollectionUtils.isNotEmpty(ucertainField.getValue())) {
                            this.discount = ucertainField.getValue().get(0);
                        }
                    }
                }
            }
        }
        if (discountPrice == 0) {
            discountPrice = defaultPrice;
        }

        // this.yunPrice=MoneyUtil.convertCentToYuan(defaultPrice);
        if (this.discount != null && !this.discount.equals("")) {
            // 算出折扣
            this.discount = new BigDecimal(this.discount).divide(new BigDecimal(10d)).setScale(1, BigDecimal.ROUND_CEILING).toString();
        }
        // 生成图片路径
        this.imageUrl = ImageUtil.getImageUrl(this.mainPic + SUFFIX_IMAGE_SIZE);

        // 获取库存状态文本描述
        String statusCode = GdsUtils.checkStcokStatusDesc(storage);
        this.storageDesc = statusCode;
        this.storageStatus = GdsUtils.checkStcokStatus(storage);
        // 根据ID读取商品描述内容
        byte[] content = FileUtil.readFile(this.gdsDesc);
        if (ArrayUtils.isNotEmpty(content)) {
            this.gdsDescContent = Jsoup.parse(new String(content)).text();
            if (gdsDescContent.length() > GDSDESCCONTENT_LENGTH) {
                gdsDescContent = gdsDescContent.substring(0, GDSDESCCONTENT_LENGTH) + "...";
            }
        }

        // 不确定字段JSON字符串格式化
        //if (StringUtils.isNoneBlank(uncertainfield_prop)) {
        //    List<UncertainField> uncertainFieldList = JSONArray.parseArray(this.uncertainfield_prop, UncertainField.class);
        //    if (CollectionUtils.isNotEmpty(uncertainFieldList)) {
        //        uncertainFieldMap = new HashMap<String, UncertainField>();
        //        for (UncertainField ucertainField : uncertainFieldList) {
        //            uncertainFieldMap.put(ucertainField.getName(), ucertainField);
        //        }
        //    }
        //}

        IGdsInfoExternalRSV gdsInfoExternalRSV= ApplicationContextUtil.getBean("gdsInfoExternalRSV",
                IGdsInfoExternalRSV.class);
        LongReqDTO req=new LongReqDTO();
        req.setId(Long.parseLong(this.getGdsTypeId()));
        this.ifNeedStockAmount=gdsInfoExternalRSV.isNeedStockAmount(req);

        this.ifGdsTypeBuyonce=gdsInfoExternalRSV.isGdsTypeBuyMore(req);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getFirstSkuId() {
        return firstSkuId;
    }

    public void setFirstSkuId(long firstSkuId) {
        this.firstSkuId = firstSkuId;
    }

    public List<String> getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(List<String> promotionType) {
        this.promotionType = promotionType;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getIfLadderPrice() {
        return ifLadderPrice;
    }

    public void setIfLadderPrice(String ifLadderPrice) {
        this.ifLadderPrice = ifLadderPrice;
    }

    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public long getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(long defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }

    public String getGdsNameSrc() {
        return gdsNameSrc;
    }

    public void setGdsNameSrc(String gdsNameSrc) {
        this.gdsNameSrc = gdsNameSrc;
    }

    public String getGdsSubHead() {
        return gdsSubHead;
    }

    public void setGdsSubHead(String gdsSubHead) {
        this.gdsSubHead = gdsSubHead;
    }

    public String getGdsSubHeadSrc() {
        return gdsSubHeadSrc;
    }

    public void setGdsSubHeadSrc(String gdsSubHeadSrc) {
        this.gdsSubHeadSrc = gdsSubHeadSrc;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public long getSales() {
        return sales;
    }

    public void setSales(long sales) {
        this.sales = sales;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public long getStorage() {
        return storage;
    }

    public void setStorage(long storage) {
        this.storage = storage;
    }

    public String getStorageDesc() {
        return storageDesc;
    }

    public void setStorageDesc(String storageDesc) {
        this.storageDesc = storageDesc;
    }

    public String getGdsDesc() {
        return gdsDesc;
    }

    public void setGdsDesc(String gdsDesc) {
        this.gdsDesc = gdsDesc;
    }

    public String getGdsTypeId() {
        return gdsTypeId;
    }

    public void setGdsTypeId(String gdsTypeId) {
        this.gdsTypeId = gdsTypeId;
    }

    public String getGdsDescContent() {
        return gdsDescContent;
    }

    public void setGdsDescContent(String gdsDescContent) {
        this.gdsDescContent = gdsDescContent;
    }

    //public String getUncertainfield_prop() {
    //    return uncertainfield_prop;
    //}
    //
    //public void setUncertainfield_prop(String uncertainfield_prop) {
    //    this.uncertainfield_prop = uncertainfield_prop;
    //}
    //
    //public Map<String, UncertainField> getUncertainFieldMap() {
    //    return uncertainFieldMap;
    //}
    //
    //public void setUncertainFieldMap(Map<String, UncertainField> uncertainFieldMap) {
    //    this.uncertainFieldMap = uncertainFieldMap;
    //}

    public String getMainCategoryCode() {
        return mainCategoryCode;
    }

    public void setMainCategoryCode(String mainCategoryCode) {
        this.mainCategoryCode = mainCategoryCode;
    }

    public String getSkuProps() {
        return skuProps;
    }

    public void setSkuProps(String skuProps) {
        this.skuProps = skuProps;
    }

    // public String getYunPrice() {
    // return yunPrice;
    // }
    //
    // public void setYunPrice(String yunPrice) {
    // this.yunPrice = yunPrice;
    // }


    public List<String> getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(List<String> categoryPath) {
        this.categoryPath = categoryPath;
    }

    public long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(long discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getStorageStatus() {
        return storageStatus;
    }

    public void setStorageStatus(String storageStatus) {
        this.storageStatus = storageStatus;
    }

    public long getGuidePrice() {
        return guidePrice;
    }

    public void setGuidePrice(long guidePrice) {
        this.guidePrice = guidePrice;
    }

	public String getMainCategoryValue() {
		return mainCategoryValue;
	}

	public void setMainCategoryValue(String mainCategoryValue) {
		this.mainCategoryValue = mainCategoryValue;
	}

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean isIfNeedStockAmount() {
        return ifNeedStockAmount;
    }

    public void setIfNeedStockAmount(boolean ifNeedStockAmount) {
        this.ifNeedStockAmount = ifNeedStockAmount;
    }

    public boolean isIfGdsTypeBuyonce() {
        return ifGdsTypeBuyonce;
    }

    public void setIfGdsTypeBuyonce(boolean ifGdsTypeBuyonce) {
        this.ifGdsTypeBuyonce = ifGdsTypeBuyonce;
    }

    public String getIfFree() {
        return ifFree;
    }

    public void setIfFree(String ifFree) {
        this.ifFree = ifFree;
    }
}
