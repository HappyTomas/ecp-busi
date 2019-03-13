package com.zengshi.ecp.unpf.dubbo.dto.stock;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class SkuStockReqDTO  extends BaseInfo{
	
    private static final long serialVersionUID = 1L;
	
    //本系统skuId
    private Long skuId;//非空
 
	//同步类型
    private Long type;//可空
    //库存量
    private Long quanties;//非空
    
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getQuanties() {
		return quanties;
	}

	public void setQuanties(Long quanties) {
		this.quanties = quanties;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
}
