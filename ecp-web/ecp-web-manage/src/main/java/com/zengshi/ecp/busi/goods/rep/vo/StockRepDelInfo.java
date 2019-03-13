package com.zengshi.ecp.busi.goods.rep.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class StockRepDelInfo extends BaseInfo implements Serializable {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = -4565790183965095230L;

    // 删除的仓库id
    @NotNull(message="{goods.StockRepDelInfo.id.null.error}")
    private Long id;
    @NotNull(message="{goods.StockRepDelInfo.shopId.null.error}")
    private Long shopId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "StockRepDelInfo [id=" + id + ", shopId=" + shopId + "]";
    }
}
