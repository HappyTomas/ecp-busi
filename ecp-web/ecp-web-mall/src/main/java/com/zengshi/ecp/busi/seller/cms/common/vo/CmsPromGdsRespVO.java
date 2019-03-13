package com.zengshi.ecp.busi.seller.cms.common.vo;

import java.io.Serializable;
import java.util.Date;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CmsPromGdsRespVO  extends  BaseResponseDTO  implements Serializable {
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 2805532377712615091L;

    private Long siteId;//站点编码
    
    private Long promId;//促销id
    
    private Long gdsId;//商品编码
    
    private String gdsName;//商品名称
       
    private Long skuId;//单品编码
    
    private String skuName;//单品名称
    
    private String catgCode;//分类信息
    
    private String isbn;//isbn
    
    private Long shopId;//店铺编码
    
    private Date startTime;//生效开始时间
    
    private Date endTime;//生效截止时间
    
    private String keyWord;//促销关键字
    
    private String promTheme;//促销名称
    
    private String promTypeCode;//促销类别代码
    
    private String promType;//促销类别
    /**
     * 店铺名称
     */
    private String ShopName;
    /**
     * 商品路径
     */
    private String url;
    /**
     * 商品图片路径
     */
    private String imageUrl;
    /**
     * 作者
     */
    private String prop1001;
    /**
     * ISBN
     */
    private String prop1002;
    /**
     * 出版日期
     */
    private String prop1005;
    /**
     * 版次
     */
    private String prop1010;

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
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
        this.gdsName = gdsName;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getPromTheme() {
        return promTheme;
    }

    public void setPromTheme(String promTheme) {
        this.promTheme = promTheme;
    }

    public String getPromTypeCode() {
        return promTypeCode;
    }

    public void setPromTypeCode(String promTypeCode) {
        this.promTypeCode = promTypeCode;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProp1001() {
        return prop1001;
    }

    public void setProp1001(String prop1001) {
        this.prop1001 = prop1001;
    }

    public String getProp1002() {
        return prop1002;
    }

    public void setProp1002(String prop1002) {
        this.prop1002 = prop1002;
    }

    public String getProp1005() {
        return prop1005;
    }

    public void setProp1005(String prop1005) {
        this.prop1005 = prop1005;
    }

    public String getProp1010() {
        return prop1010;
    }

    public void setProp1010(String prop1010) {
        this.prop1010 = prop1010;
    }

    public String getPromType() {
        return promType;
    }

    public void setPromType(String promType) {
        this.promType = promType;
    }
    
    
}

