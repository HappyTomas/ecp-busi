package com.zengshi.ecp.busi.pageConfig.pageConfig.modular.vo;

import java.io.Serializable;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-manage <br>
 * Description: 组件的商品热销请求入参对象<br>
 * Date:2016年6月12日下午5:32:46  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class ModularShopGdsHotReqVO implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 3235609724122450044L;
    
    private Long shopId;//店铺id
    
    private String count;//显示的数量

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
    

}

