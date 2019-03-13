package com.zengshi.ecp.order.dubbo.dto;


import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrdCartRequest extends BaseInfo {
    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 55904414308399820L;

    private Long id;

    private String cartType;

    private Long staffId;

    private Long shopId;

    private String shopName;

    private Long promId;
    
    private String source;
    
    private String promSubmitType;
        
    private List<ROrdCartItemRequest> ordCartItemList;


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCartType() {
        return cartType;
    }

    public void setCartType(String cartType) {
        this.cartType = cartType == null ? null : cartType.trim();
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    public List<ROrdCartItemRequest> getOrdCartItemList() {
        return ordCartItemList;
    }

    public void setOrdCartItemList(List<ROrdCartItemRequest> ordCartItemList) {
        this.ordCartItemList = ordCartItemList;
    }

	public String getPromSubmitType() {
		return promSubmitType;
	}

	public void setPromSubmitType(String promSubmitType) {
		this.promSubmitType = promSubmitType;
	}



    
    
}

