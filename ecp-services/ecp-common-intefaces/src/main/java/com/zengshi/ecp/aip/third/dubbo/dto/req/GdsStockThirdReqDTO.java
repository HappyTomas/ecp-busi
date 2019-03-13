package com.zengshi.ecp.aip.third.dubbo.dto.req;

import java.util.List;

public class GdsStockThirdReqDTO  extends BaseShopAuthReqDTO{
	
    private static final long serialVersionUID = 1L;
	
    //本系统商品id
    private Long gdsId;
    
    //外系统商品id
    private Long outGdsId; //必须字段
    
    //商品数量   
    private Long quanties;//必须字段
    
    //更新类型
    private Long type;

    //单品库存列表
    private List<SkuStockThirdReqDTO> skuInfos;//如果需要更新 该字段必填

	public Long getGdsId() {
		return gdsId;
	}

	public Long getType() {
		return type;
	}

	public List<SkuStockThirdReqDTO> getSkuInfos() {
		return skuInfos;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public void setSkuInfos(List<SkuStockThirdReqDTO> skuInfos) {
		this.skuInfos = skuInfos;
	}

	public Long getOutGdsId() {
		return outGdsId;
	}

	public void setOutGdsId(Long outGdsId) {
		this.outGdsId = outGdsId;
	}

	public Long getQuanties() {
		return quanties;
	}

	public void setQuanties(Long quanties) {
		this.quanties = quanties;
	}

}
