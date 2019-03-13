package com.zengshi.ecp.busi.search.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.search.dubbo.search.UncertainField;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
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
public class GoodSearchResult extends BaseResponseDTO{
    
    /** 
     * serialVersionUID:
     */ 
    private static final long serialVersionUID = 7299963184185039508L;
    
    private final static String SUFFIX_IMAGE_SIZE="_166x166!";
    
    private String id;
    
    private long firstSkuId;

    private String ifLadderPrice;
    
    private String mainPic;
    
    private String imageUrl;
    
    private long defaultPrice;
    
    private long guidePrice;
    
    private String gdsName;
    
    private String gdsNameSrc;
    
    private String gdsSubHead;
    
    private String gdsSubHeadSrc;
    
    private String shopName;
    
    private String shopId;
    
    private String updateTime;
    
    private long storage;
    
    private String storageDesc;
    
    private String gdsTypeId;
    
    //private String uncertainfield_prop;
    
    //private Map<String,UncertainField> uncertainFieldMap;
    
    private String mainCategoryCode;
    
    private String skuProps;
    
    private Long score;
    
    private String ifScoreGds;
    
    public void render(){
        
        //生成图片路径
        this.imageUrl=ImageUtil.getImageUrl(this.mainPic+SUFFIX_IMAGE_SIZE);
        
        //获取库存状态文本描述
        String statusCode=GdsUtils.checkScoreStcokStatus(storage);
        if(StringUtils.equals(statusCode, GdsConstants.GdsStock.STOCK_STATUS_LACK)){
            this.storageDesc="无货";
        }else{
            this.storageDesc="有货";
        }
        
        //不确定字段JSON字符串格式化
        //if(StringUtils.isNoneBlank(uncertainfield_prop)){
        //    List<UncertainField> uncertainFieldList=JSONArray.parseArray(this.uncertainfield_prop, UncertainField.class);
        //    if(CollectionUtils.isNotEmpty(uncertainFieldList)){
        //        uncertainFieldMap=new HashMap<String,UncertainField>();
        //        for(UncertainField ucertainField:uncertainFieldList){
        //            uncertainFieldMap.put(ucertainField.getName(), ucertainField);
        //        }
        //    }
        //}
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

    public String getGdsTypeId() {
        return gdsTypeId;
    }

    public void setGdsTypeId(String gdsTypeId) {
        this.gdsTypeId = gdsTypeId;
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

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getIfScoreGds() {
        return ifScoreGds;
    }

    public void setIfScoreGds(String ifScoreGds) {
        this.ifScoreGds = ifScoreGds;
    }

    public long getGuidePrice() {
        return guidePrice;
    }

    public void setGuidePrice(long guidePrice) {
        this.guidePrice = guidePrice;
    }
    
}
