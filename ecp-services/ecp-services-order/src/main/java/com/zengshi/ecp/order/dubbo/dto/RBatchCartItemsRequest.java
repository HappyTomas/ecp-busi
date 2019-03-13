package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RBatchCartItemsRequest extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -8592442764692051227L;

    /** 
     * cartId:购物车明细ID. 
     * @since JDK 1.6 
     */ 
    private Long cartItemId;
    
    /** 
     * cartId:购物车ID. 
     * @since JDK 1.6 
     */ 
    private Long cartId;
    
    /** 
     * promId:促销ID. 
     * @since JDK 1.6 
     */ 
    private Long promId;


    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }
    
}

