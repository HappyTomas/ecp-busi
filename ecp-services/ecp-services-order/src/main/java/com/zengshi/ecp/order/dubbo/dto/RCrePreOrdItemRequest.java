package com.zengshi.ecp.order.dubbo.dto;


import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RCrePreOrdItemRequest extends BaseInfo {
    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 8980290874729152776L;

    //鼓舞车id
    private Long cartItemId;
    
    //明细促销id
    private Long promId;
    



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

