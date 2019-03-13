package com.zengshi.ecp.busi.order.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

import java.util.List;

public class RCrePreOrdReqVO extends EcpBasePageReqVO {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    private Long staffId;
    private Long cartId;
    private Long promId;
    private List<RCrePreOrdItemReqVO> cartItemIdList;
    public Long getStaffId() {
        return staffId;
    }
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
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
    public List<RCrePreOrdItemReqVO> getCartItemIdList() {
        return cartItemIdList;
    }
    public void setCartItemIdList(List<RCrePreOrdItemReqVO> cartItemIdList) {
        this.cartItemIdList = cartItemIdList;
    }


}

