package com.zengshi.ecp.goods.dubbo.dto.stock;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class StockPreOccupyBatchOrderReqDTO extends BaseInfo{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3724364086906072642L;

    private Long staffId;
    
    private String orderId;

    private List<StockPreOccupyBatchReqDTO> preOccupyBatchDTOs = new ArrayList<StockPreOccupyBatchReqDTO>();

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public List<StockPreOccupyBatchReqDTO> getPreOccupyBatchDTOs() {
        return preOccupyBatchDTOs;
    }

    public void setPreOccupyBatchDTOs(List<StockPreOccupyBatchReqDTO> preOccupyBatchDTOs) {
        this.preOccupyBatchDTOs = preOccupyBatchDTOs;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}

