package com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo;

import java.io.Serializable;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-manage <br>
 * Description: cms模块开发商品分类入参vo<br>
 * Date:2016年6月7日下午2:17:43  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class ModularShopCategoryReqVO implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -9101527772004680258L;
    //商品分类的一个分类节点id
    private String gdsCategory;
    //标题
    private String title;
    //店铺名称
    private Long shopId;
    
    public String getGdsCategory() {
        return gdsCategory;
    }
    public void setGdsCategory(String gdsCategory) {
        this.gdsCategory = gdsCategory;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    
    
}

