package com.zengshi.ecp.busi.goods.vo;

import javax.validation.constraints.NotNull;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class GdsSkuInfo extends EcpBasePageReqVO{
	
    private static final long serialVersionUID = -3357489957794724974L;
    
    private Long skuId;
    /**
     * 店铺ID
     */
    private Long shopId;
    /**
     * 商品ID
     */
    private Long gdsId;
    
    private String platCatgs;
    
    private String gdsName;
    
    public Long getSkuId() {
        return skuId;
    }
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    public Long getGdsId() {
        return gdsId;
    }
    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

	public String getPlatCatgs() {
		return platCatgs;
	}
	public void setPlatCatgs(String platCatgs) {
		this.platCatgs = platCatgs;
	}
	public String getGdsName() {
		return gdsName;
	}
	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}
    
    

}
