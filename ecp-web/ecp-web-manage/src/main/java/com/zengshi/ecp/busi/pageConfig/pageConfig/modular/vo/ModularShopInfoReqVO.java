package com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo;

import java.io.Serializable;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-manage <br>
 * Description: 店铺首页组件请求入参参数。<br>
 * Date:2016年6月6日下午9:51:05  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class ModularShopInfoReqVO implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 4191028275266059229L;
    private String ifShopFullName;//是否显示店铺全称
    
    private String ifLinkPerson;//是否显示联系人
    
    private String ifLinkPhone;//是否显示联系方式
    
    private String ifGoodsBaby;//是否显示宝贝
    
    private String ifRate;//是否显示好评率
    
    private String ifSales;//是否显示销量
    
    private String ifCollectShop;//是否显示收藏店铺
    
    private Long shopId;//店铺id

    public String getIfShopFullName() {
        return ifShopFullName;
    }

    public void setIfShopFullName(String ifShopFullName) {
        this.ifShopFullName = ifShopFullName;
    }

    public String getIfLinkPerson() {
        return ifLinkPerson;
    }

    public void setIfLinkPerson(String ifLinkPerson) {
        this.ifLinkPerson = ifLinkPerson;
    }

    public String getIfLinkPhone() {
        return ifLinkPhone;
    }

    public void setIfLinkPhone(String ifLinkPhone) {
        this.ifLinkPhone = ifLinkPhone;
    }

    public String getIfGoodsBaby() {
        return ifGoodsBaby;
    }

    public void setIfGoodsBaby(String ifGoodsBaby) {
        this.ifGoodsBaby = ifGoodsBaby;
    }

    public String getIfRate() {
        return ifRate;
    }

    public void setIfRate(String ifRate) {
        this.ifRate = ifRate;
    }

    public String getIfSales() {
        return ifSales;
    }

    public void setIfSales(String ifSales) {
        this.ifSales = ifSales;
    }

    public String getIfCollectShop() {
        return ifCollectShop;
    }

    public void setIfCollectShop(String ifCollectShop) {
        this.ifCollectShop = ifCollectShop;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    
    
    
}

