package com.zengshi.ecp.goods.dubbo.dto.stock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StockRepAdaptMainDTO implements Serializable{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 7314061456441572099L;
    private List<StockRepAdaptRespDTO> stockRepAdaptDTOs = new ArrayList<StockRepAdaptRespDTO>();

    public List<StockRepAdaptRespDTO> getStockRepAdaptDTOs() {
        return stockRepAdaptDTOs;
    }

    public void setStockRepAdaptDTOs(List<StockRepAdaptRespDTO> stockRepAdaptDTOs) {
        this.stockRepAdaptDTOs = stockRepAdaptDTOs;
    }

}

