/** 
 * Project Name:ecp-web-mall 
 * File Name:AddToCartVO.java 
 * Package Name:com.zengshi.ecp.busi.search.vo 
 * Date:2017年10月13日下午3:14:11 
 * Copyright (c) 2017, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.search.vo;

import java.io.Serializable;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 加入购物车按钮.<br>
 * Date:2017年10月13日下午3:14:11  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class AddToCartButtonVO implements Serializable {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    
    
    /**
     * 加入购物车提示
     */
    private String addToCartPromp;
    
    /**
     * 是否允许点击加入购物车
     */
    private boolean addToCartEnable;

    public String getAddToCartPromp() {
        return addToCartPromp;
    }

    public void setAddToCartPromp(String addToCartPromp) {
        this.addToCartPromp = addToCartPromp;
    }

    public boolean isAddToCartEnable() {
        return addToCartEnable;
    }

    public void setAddToCartEnable(boolean addToCartEnable) {
        this.addToCartEnable = addToCartEnable;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AddToCartButtonVO [addToCartPromp=");
        builder.append(addToCartPromp);
        builder.append(", addToCartEnable=");
        builder.append(addToCartEnable);
        builder.append("]");
        return builder.toString();
    }
    
    
    
    
    
    

}

