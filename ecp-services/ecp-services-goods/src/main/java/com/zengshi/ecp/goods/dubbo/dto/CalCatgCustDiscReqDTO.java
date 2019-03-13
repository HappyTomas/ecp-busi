package com.zengshi.ecp.goods.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class CalCatgCustDiscReqDTO extends BaseInfo{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 3380447413593569952L;

    /**
     * 商品编码
     */
    private Long gdsId;

    /**
     * 店铺编码
     */
    private Long shopId;

    /**
     * 客户编码
     */
    private Long custNo;
    
    /**
     * 当前用户等级编码
     */
    private String custLevelCode;

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

    public Long getCustNo() {
        return custNo;
    }

    public void setCustNo(Long custNo) {
        this.custNo = custNo;
    }

	public String getCustLevelCode() {
		return custLevelCode;
	}

	public void setCustLevelCode(String custLevelCode) {
		this.custLevelCode = custLevelCode;
	}


}

