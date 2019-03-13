package com.zengshi.ecp.unpf.dubbo.dto.gdssend;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class GdsSendRespDTO  extends BaseResponseDTO {
	
	 private static final long serialVersionUID = -1L;
	 
	 private String ifResult="1";//结果 1 成功 0失败

	 private String msg;//结果描述  成功为空 失败返回数据
	 
	 private Long gdsId;//商品编码
	 
	 private Long shopId;//店铺编码
	 
	 private String platType;//平台类型
	 
	 private Long shopAuthId;//授权id
	 
	public String getIfResult() {
		return ifResult;
	}

	public void setIfResult(String ifResult) {
		this.ifResult = ifResult;
	}

	public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPlatType() {
		return platType;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
	}

	public Long getShopAuthId() {
		return shopAuthId;
	}

	public void setShopAuthId(Long shopAuthId) {
		this.shopAuthId = shopAuthId;
	}
	  
}
