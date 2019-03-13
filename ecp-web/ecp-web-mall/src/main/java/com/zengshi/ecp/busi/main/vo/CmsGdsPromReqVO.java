/** 
 * Project Name:ecp-web-manage Maven Webapp 
 * File Name:CmsInfoVO.java 
 * Package Name:com.zengshi.ecp.busi.cms.vo 
 * Date:2015-8-14下午3:14:04 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.main.vo;

import java.io.Serializable;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月2日上午9:38:16  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6
 */
public class CmsGdsPromReqVO extends EcpBasePageReqVO implements Serializable {
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3062630265000779347L;
    /**
     * 商品编码
     */
    private Long gdsId;
    /**
     * 单品编码
     */
    private Long skuId;
    /**
     *店铺id 
     */
    private Long shopId;
    /**
     * 指导价
     */
    private Long realPrice;
    
    /**
     * 购买价 （分类折扣后的价格）
     */
    private Long discountPrice;
    
    /**
     * 商品名称
     */
    private String gdsName;
    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 促销id
     */
    private Long promId;
    
    public Long getGdsId() {
        return gdsId;
    }
    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }
    public Long getSkuId() {
        return skuId;
    }
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    public Long getRealPrice() {
        return realPrice;
    }
    public void setRealPrice(Long realPrice) {
        this.realPrice = realPrice;
    }
    public Long getDiscountPrice() {
        return discountPrice;
    }
    public void setDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
    }
    public String getGdsName() {
        return gdsName;
    }
    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public Long getPromId() {
        return promId;
    }
    public void setPromId(Long promId) {
        this.promId = promId;
    }
    
}

