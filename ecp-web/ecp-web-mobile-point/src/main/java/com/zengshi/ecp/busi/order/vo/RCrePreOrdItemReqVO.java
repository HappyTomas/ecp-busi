package com.zengshi.ecp.busi.order.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class RCrePreOrdItemReqVO extends EcpBasePageReqVO {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 3816858344283583300L;
    
    private Long cartId;

    private Long cartItemId;

    private Long promId;
    
    private String itemCheck;
    
    //商品是否是上架状态
    private boolean gdsStatus;

    public Long getCartItemId() {
        return this.cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getPromId() {
        return this.promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public String getItemCheck() {
		return itemCheck;
	}

	public void setItemCheck(String itemCheck) {
		this.itemCheck = itemCheck;
	}
    
	public boolean isGdsStatus() {
        return gdsStatus;
    }

    public void setGdsStatus(boolean gdsStatus) {
        this.gdsStatus = gdsStatus;
    }
    
}

