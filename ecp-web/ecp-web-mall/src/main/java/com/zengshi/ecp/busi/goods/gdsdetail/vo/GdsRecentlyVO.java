package com.zengshi.ecp.busi.goods.gdsdetail.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class GdsRecentlyVO extends EcpBasePageReqVO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -8472804359734914668L;
    
    private String gdsSize;//展示的商品数量
    
    private Long gdsId;//商品编码
    
    public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	private Long shopId;

    public String getGdsSize() {
        return gdsSize;
    }

    public void setGdsSize(String gdsSize) {
        this.gdsSize = gdsSize;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }
    
}

