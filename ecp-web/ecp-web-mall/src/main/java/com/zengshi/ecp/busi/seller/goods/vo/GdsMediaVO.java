package com.zengshi.ecp.busi.seller.goods.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 商品录入中，用于获取媒体库列表用的<br>
 * Date:2015年9月4日下午8:55:39  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class GdsMediaVO extends EcpBasePageReqVO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 3047972791260687614L;
    
    private String picCatgCode;//图片分类编码
    
    private String mediaName;//图片名称
    
    private String mediaType;//媒体类型 1为图片，2为视频 3为音频
    
    private Long shopId; //店铺编码
    
    public String getPicCatgCode() {
        return picCatgCode;
    }
    public void setPicCatgCode(String picCatgCode) {
        this.picCatgCode = picCatgCode;
    }
    public String getMediaName() {
        return mediaName;
    }
    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }
    public String getMediaType() {
        return mediaType;
    }
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    
}

