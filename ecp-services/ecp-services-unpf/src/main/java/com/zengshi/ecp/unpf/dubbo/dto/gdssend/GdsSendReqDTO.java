package com.zengshi.ecp.unpf.dubbo.dto.gdssend;

import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class GdsSendReqDTO extends BaseInfo{
	
	 private static final long serialVersionUID = -1L;
	 
	 private Long gdsId;//商品编码
	 
	 private Long shopId;//店铺编码
	 
	 private String platType;//非空 推送平台
	 
	 private String ifThrow="1";//是否抛出错误  默认抛出
	 
	 private Long shopAuthId;//授权id
	 
	 public void check() throws BusinessException{
		 
		    if(gdsId==null){
		    	throw new BusinessException("unpf.100006");
		    }
		    if(shopId==null){
		    	throw new BusinessException("unpf.100007");
		    }
		    if(platType==null){
		    	throw new BusinessException("unpf.100009");
		    }
	 }

	public Long getGdsId() {
		return gdsId;
	}

	public Long getShopId() {
		return shopId;
	}

	public String getPlatType() {
		return platType;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
	}

	public String getIfThrow() {
		return ifThrow;
	}

	public void setIfThrow(String ifThrow) {
		this.ifThrow = ifThrow;
	}

	public Long getShopAuthId() {
		return shopAuthId;
	}

	public void setShopAuthId(Long shopAuthId) {
		this.shopAuthId = shopAuthId;
	}
}
