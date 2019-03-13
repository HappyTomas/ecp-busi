package com.zengshi.ecp.aip.third.dubbo.dto.req;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class SkuStockThirdReqDTO  extends BaseInfo{
	
    private static final long serialVersionUID = 1L;
	
    //本系统skuId
    private Long skuId;
 
    //对方平台skuId
    private Long outSkuId;
    
    //数字id编码 外部系统
    private Long numIid;
    
   //本系统店铺编码
    private Long shopId;
    
	//同步类型
    private Long type;
    //库存量
    private Long quanties;
    
	public Long getOutSkuId() {
		return outSkuId;
	}

	public Long getNumIid() {
		return numIid;
	}

	public Long getShopId() {
		return shopId;
	}

	public Long getType() {
		return type;
	}

	public void setOutSkuId(Long outSkuId) {
		this.outSkuId = outSkuId;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
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
