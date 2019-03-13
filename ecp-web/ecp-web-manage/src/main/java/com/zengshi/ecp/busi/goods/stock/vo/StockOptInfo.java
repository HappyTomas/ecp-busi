package com.zengshi.ecp.busi.goods.stock.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class StockOptInfo extends BaseInfo{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 7331369930013443378L;

    @NotBlank(message="{goods.StockOptInfo.turnType.null.error}")
    private String turnType;  
    @NotNull(message="{goods.StockOptInfo.stockId.null.error}")
    private Long stockId;
    @NotNull(message="{goods.StockOptInfo.turnCount.null.error}")
    private Long turnCount;
    @NotNull(message="{goods.StockOptInfo.shopId.null.error}")
    private Long shopId;
    @NotNull(message="{goods.StockOptInfo.gdsId.null.error}")
    private Long gdsId;

    public String getTurnType() {
        return turnType;
    }

    public void setTurnType(String turnType) {
        this.turnType = turnType;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(Long turnCount) {
        this.turnCount = turnCount;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "StockOptInfo [turnType=" + turnType + ", stockId=" + stockId + ", turnCount="
                + turnCount + ", shopId=" + shopId + "]";
    }

	public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}
    
}

