package com.zengshi.ecp.order.dubbo.dto;


import java.util.List;
import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ROrdCartResponse extends BaseResponseDTO {
    private Long id;

    private String cartType;

    private Long staffId;

    private Long shopId;

    private String shopName;

    private Long promId;
    
//    private List<PromInfoDTO> ordPromList;
    
    
    private List<ROrdCartItemResponse> ordCartItemList;
    
    //运费信息
    private Long expressFee; 
    
    //组合商品信息
//    private Map<String,Map<Long,ROrdCartItemResponse>> groupMaps;
    //组合商品信息
    private List<List<ROrdCartItemResponse>> groupLists;
    
    private static final long serialVersionUID = 1L;

   

    public List<List<ROrdCartItemResponse>> getGroupLists() {
        return groupLists;
    }

    public void setGroupLists(List<List<ROrdCartItemResponse>> groupLists) {
        this.groupLists = groupLists;
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

    public List<ROrdCartItemResponse> getOrdCartItemList() {
        return ordCartItemList;
    }

    public void setOrdCartItemList(List<ROrdCartItemResponse> ordCartItemList) {
        this.ordCartItemList = ordCartItemList;
    }




    public Long getExpressFee() {
        return expressFee;
    }

    public void setExpressFee(Long expressFee) {
        this.expressFee = expressFee;
    }

}

