package com.zengshi.ecp.goods.dubbo.dto.stock;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class StockPreOccupyBatchReqDTO extends BaseInfo{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 3758464165905870025L;

    List<StockPreOccupyReqDTO> stockPreOccupyDTOs = new ArrayList<StockPreOccupyReqDTO>();

    private Long shopId;

    private Long staffId;

    public List<StockPreOccupyReqDTO> getStockPreOccupyDTOs() {
        return stockPreOccupyDTOs;
    }

    public void setStockPreOccupyDTOs(List<StockPreOccupyReqDTO> stockPreOccupyDTOs) {
        this.stockPreOccupyDTOs = stockPreOccupyDTOs;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}
