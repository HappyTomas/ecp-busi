package com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo;

import java.io.Serializable;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-manage <br>
 * Description: 店铺首页组件入参对象<br>
 * Date:2016年6月7日下午3:04:11  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class ModularShopAdvertiseReqVO implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -7829199558298075492L;
    //内容位置id
    private String placeId;
    //店铺id
    private Long shopId; 
    //广告展示数量
    private String showAmount;
    //内容位置的宽度。定义取到广告图片的宽度
    private String placeWidth;
    //内容位置的高度。定义取到广告图片的高度
    private String placeHeight;
    //广告状态
    private String status;
    
    public String getPlaceId() {
        return placeId;
    }
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    public String getShowAmount() {
        return showAmount;
    }
    public void setShowAmount(String showAmount) {
        this.showAmount = showAmount;
    }
    public String getPlaceWidth() {
        return placeWidth;
    }
    public void setPlaceWidth(String placeWidth) {
        this.placeWidth = placeWidth;
    }
    public String getPlaceHeight() {
        return placeHeight;
    }
    public void setPlaceHeight(String placeHeight) {
        this.placeHeight = placeHeight;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    

}

