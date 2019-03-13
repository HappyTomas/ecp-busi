package com.zengshi.ecp.unpf.dubbo.dto.stock;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class GdsStockReqDTO  extends BaseInfo{
	
    private static final long serialVersionUID = 1L;
    //平台类型
    private String platType;
    //本系统店铺编码
    private Long shopId;//必须字段
    //本系统商品id
    private Long gdsId;//必须字段
    //商品数量   
    private Long quanties;//必须字段
    //单品库存列表
    private List<SkuStockReqDTO> skuInfos;//如果需要更新单品 该字段必填

	public Long getGdsId() {
		return gdsId;
	}

	public List<SkuStockReqDTO> getSkuInfos() {
		return skuInfos;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

	public void setSkuInfos(List<SkuStockReqDTO> skuInfos) {
		this.skuInfos = skuInfos;
	}
	public Long getQuanties() {
		return quanties;
	}

	public void setQuanties(Long quanties) {
		this.quanties = quanties;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getPlatType() {
		return platType;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
	}

}
