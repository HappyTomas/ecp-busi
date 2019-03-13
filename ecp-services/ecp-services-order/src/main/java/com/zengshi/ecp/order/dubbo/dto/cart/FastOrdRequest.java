package com.zengshi.ecp.order.dubbo.dto.cart;

import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemRequest;
import com.zengshi.ecp.server.front.dto.BaseInfo;

import java.util.List;

/**
 * Created by wang on 16/5/10.
 */
public class FastOrdRequest extends BaseInfo {
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

    private String sourceKey;

    private Long currentSiteId;

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

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    @Override
    public Long getCurrentSiteId() {
        return currentSiteId;
    }

    @Override
    public void setCurrentSiteId(Long currentSiteId) {
        this.currentSiteId = currentSiteId;
    }
}
