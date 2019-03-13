package com.zengshi.ecp.busi.goods.rep.vo;

import javax.validation.constraints.NotNull;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class StockRepInfo extends EcpBasePageReqVO {
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -355101557174492520L;
 
    private String repName;

    private String status;
    @NotNull(message="{goods.StockRepInfo.shopId.null.error}")
    private Long shopId;

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "StockRepInfo [repName=" + repName + ", status=" + status + ", shopId=" + shopId
                + "]";
    }

}
