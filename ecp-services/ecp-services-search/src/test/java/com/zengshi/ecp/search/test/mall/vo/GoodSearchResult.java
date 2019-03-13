package com.zengshi.ecp.search.test.mall.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.search.dubbo.search.UncertainField;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall-xhs <br>
 * Description: <br>
 * Date:2015年12月3日下午6:15:30  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class GoodSearchResult extends BaseResponseDTO {

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = 7299963184185039508L;

    private final int GDSDESCCONTENT_LENGTH = 115;

    private final static String SUFFIX_IMAGE_SIZE = "_220x220!";

    private String id;
    
    private String mainPic;
    
    private String mainPicIUrl;
    
    private long defaultPrice;
    
    private String gdsName;

    private String gdsSubHead;
    
    private String shopName;

    private String shopId;
    
    private long guidePrice;
    
    private String mainCategoryCode;
    
    //private String categories;
    
    private String topCategoryCode;
    
    //private String propertycodes;
    
    //private String propertyValues;
    
    private long sales;
    
    private String mainCategoryValue;
    
    private String topCategoryValue;
    
    private String topCategoryCodeAndValue;
    
    private String mainCategoryCodeAndValue;
    
    private String gdsTypeId;
    
    private String gdsDesc;
    
    private String gdsTypeName;
    
//    private String gdsDescContent;
    
    private long storage;
    
    private String storageDesc;

    /**
     * add by gongxq 库存状态，用于购物车按钮的判断
     */
    private String storageStatus;
    
    private String formatUpdateTime;
    
    private Date updateDate;
    
    private Date writeDate;
    
    private String uncertainfield_prop;

    private Map<String, UncertainField> uncertainFieldMap;

    private long firstSkuId;
    
    private String skuProps;
    
    private long catlogId;
    
    private long gdsNameBoost;
    
    private List<String> area;
    
    private List<String> people;
    
    private String source;

    public void render() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic;
    }

    public String getMainPicIUrl() {
        return mainPicIUrl;
    }

    public void setMainPicIUrl(String mainPicIUrl) {
        this.mainPicIUrl = mainPicIUrl;
    }

    public long getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(long defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }

    public String getGdsSubHead() {
        return gdsSubHead;
    }

    public void setGdsSubHead(String gdsSubHead) {
        this.gdsSubHead = gdsSubHead;
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

    public long getGuidePrice() {
        return guidePrice;
    }

    public void setGuidePrice(long guidePrice) {
        this.guidePrice = guidePrice;
    }

    public String getMainCategoryCode() {
        return mainCategoryCode;
    }

    public void setMainCategoryCode(String mainCategoryCode) {
        this.mainCategoryCode = mainCategoryCode;
    }

    public String getTopCategoryCode() {
        return topCategoryCode;
    }

    public void setTopCategoryCode(String topCategoryCode) {
        this.topCategoryCode = topCategoryCode;
    }

    public long getSales() {
        return sales;
    }

    public void setSales(long sales) {
        this.sales = sales;
    }

    public String getMainCategoryValue() {
        return mainCategoryValue;
    }

    public void setMainCategoryValue(String mainCategoryValue) {
        this.mainCategoryValue = mainCategoryValue;
    }

    public String getTopCategoryValue() {
        return topCategoryValue;
    }

    public void setTopCategoryValue(String topCategoryValue) {
        this.topCategoryValue = topCategoryValue;
    }

    public String getTopCategoryCodeAndValue() {
        return topCategoryCodeAndValue;
    }

    public void setTopCategoryCodeAndValue(String topCategoryCodeAndValue) {
        this.topCategoryCodeAndValue = topCategoryCodeAndValue;
    }

    public String getMainCategoryCodeAndValue() {
        return mainCategoryCodeAndValue;
    }

    public void setMainCategoryCodeAndValue(String mainCategoryCodeAndValue) {
        this.mainCategoryCodeAndValue = mainCategoryCodeAndValue;
    }

    public String getGdsTypeId() {
        return gdsTypeId;
    }

    public void setGdsTypeId(String gdsTypeId) {
        this.gdsTypeId = gdsTypeId;
    }

    public String getGdsDesc() {
        return gdsDesc;
    }

    public void setGdsDesc(String gdsDesc) {
        this.gdsDesc = gdsDesc;
    }

    public String getGdsTypeName() {
        return gdsTypeName;
    }

    public void setGdsTypeName(String gdsTypeName) {
        this.gdsTypeName = gdsTypeName;
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

    public String getStorageStatus() {
        return storageStatus;
    }

    public void setStorageStatus(String storageStatus) {
        this.storageStatus = storageStatus;
    }
    
    public String getFormatUpdateTime() {
        return formatUpdateTime;
    }

    public void setFormatUpdateTime(String formatUpdateTime) {
        this.formatUpdateTime = formatUpdateTime;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public String getUncertainfield_prop() {
        return uncertainfield_prop;
    }

    public void setUncertainfield_prop(String uncertainfield_prop) {
        this.uncertainfield_prop = uncertainfield_prop;
    }

    public Map<String, UncertainField> getUncertainFieldMap() {
        return uncertainFieldMap;
    }

    public void setUncertainFieldMap(Map<String, UncertainField> uncertainFieldMap) {
        this.uncertainFieldMap = uncertainFieldMap;
    }

    public long getFirstSkuId() {
        return firstSkuId;
    }

    public void setFirstSkuId(long firstSkuId) {
        this.firstSkuId = firstSkuId;
    }

    public String getSkuProps() {
        return skuProps;
    }

    public void setSkuProps(String skuProps) {
        this.skuProps = skuProps;
    }

    public long getCatlogId() {
        return catlogId;
    }

    public void setCatlogId(long catlogId) {
        this.catlogId = catlogId;
    }

    public long getGdsNameBoost() {
        return gdsNameBoost;
    }

    public void setGdsNameBoost(long gdsNameBoost) {
        this.gdsNameBoost = gdsNameBoost;
    }

    public List<String> getArea() {
        return area;
    }

    public void setArea(List<String> area) {
        this.area = area;
    }

    public List<String> getPeople() {
        return people;
    }

    public void setPeople(List<String> people) {
        this.people = people;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "GoodSearchResult [id=" + id + ", mainPic=" + mainPic + ", mainPicIUrl="
                + mainPicIUrl + ", defaultPrice=" + defaultPrice + ", gdsName=" + gdsName
                + ", gdsSubHead=" + gdsSubHead + ", shopName=" + shopName + ", shopId=" + shopId
                + ", guidePrice=" + guidePrice + ", mainCategoryCode=" + mainCategoryCode
                + ", topCategoryCode=" + topCategoryCode + ", sales=" + sales
                + ", mainCategoryValue=" + mainCategoryValue + ", topCategoryValue="
                + topCategoryValue + ", topCategoryCodeAndValue=" + topCategoryCodeAndValue
                + ", mainCategoryCodeAndValue=" + mainCategoryCodeAndValue + ", gdsTypeId="
                + gdsTypeId + ", gdsDesc=" + gdsDesc + ", gdsTypeName=" + gdsTypeName
                + ", storage=" + storage + ", storageDesc=" + storageDesc + ", storageStatus="
                + storageStatus + ", formatUpdateTime=" + formatUpdateTime + ", updateDate="
                + updateDate + ", writeDate=" + writeDate + ", uncertainfield_prop="
                + uncertainfield_prop + ", uncertainFieldMap=" + uncertainFieldMap
                + ", firstSkuId=" + firstSkuId + ", skuProps=" + skuProps + ", catlogId="
                + catlogId + ", gdsNameBoost=" + gdsNameBoost + ", area=" + area + ", people="
                + people + ", source=" + source + "]";
    }

}
