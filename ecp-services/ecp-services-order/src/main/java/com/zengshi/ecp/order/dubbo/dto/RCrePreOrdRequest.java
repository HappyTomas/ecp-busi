package com.zengshi.ecp.order.dubbo.dto;


import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RCrePreOrdRequest extends BaseInfo {
    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 8072077676956397734L;

    //买家id
    private Long staffId;
    
    //鼓舞车id
    private Long cartId;
    
    //促销id
    private Long promId;
    
    
    //购物车明细id列表
    private List<RCrePreOrdItemRequest> cartItemIdList;


    public Long getStaffId() {
        return staffId;
    }


    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }




    public List<RCrePreOrdItemRequest> getCartItemIdList() {
        return cartItemIdList;
    }


    public void setCartItemIdList(List<RCrePreOrdItemRequest> cartItemIdList) {
        this.cartItemIdList = cartItemIdList;
    }


    public Long getCartId() {
        return cartId;
    }


    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }


    public Long getPromId() {
        return promId;
    }


    public void setPromId(Long promId) {
        this.promId = promId;
    }


    
}

