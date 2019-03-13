package com.zengshi.ecp.busi.buyer.vo;

import java.io.Serializable;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class FavGoodsVO extends EcpBasePageReqVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8583134450100151606L;

	private Long id;
	
	private Long skuId;

	private String gdsName;// 商品名称

	private String operateId;// 多选的ID串
	
	private int page;

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getGdsName() {
		return gdsName;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
	
}
