package com.zengshi.ecp.busi.order.vo;

import com.zengshi.ecp.order.dubbo.dto.RPreOrdSubResponse;

public class RPreOrdSubResponseVO extends RPreOrdSubResponse{
	/** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3180299335045804364L;

	private String gdsName    ;
	private String skuHisIs   ;
	private String skuInfo    ;
	private String groupType  ;
	private String gioupDetail;
	private String bugPrice   ;
	private String picUrl     ;
	private String gdsUrl     ;
	public String getPicUrl() {
        return picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public String getGdsUrl() {
        return gdsUrl;
    }
    public void setGdsUrl(String gdsUrl) {
        this.gdsUrl = gdsUrl;
    }

	public String getGdsName() {
		return gdsName;
	}
	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}
	public String getSkuHisIs() {
		return skuHisIs;
	}
	public void setSkuHisIs(String skuHisIs) {
		this.skuHisIs = skuHisIs;
	}
	public String getSkuInfo() {
		return skuInfo;
	}
	public void setSkuInfo(String skuInfo) {
		this.skuInfo = skuInfo;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getGioupDetail() {
		return gioupDetail;
	}
	public void setGioupDetail(String gioupDetail) {
		this.gioupDetail = gioupDetail;
	}
	public String getBugPrice() {
		return bugPrice;
	}
	public void setBugPrice(String bugPrice) {
		this.bugPrice = bugPrice;
	}

}
