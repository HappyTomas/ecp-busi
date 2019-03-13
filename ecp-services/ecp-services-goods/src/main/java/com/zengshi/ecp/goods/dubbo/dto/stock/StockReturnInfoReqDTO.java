package com.zengshi.ecp.goods.dubbo.dto.stock;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class StockReturnInfoReqDTO extends BaseInfo{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -5331042360951272745L;

    private List<StockReturnSubReqDTO> returnSubReqDTOs = new ArrayList<StockReturnSubReqDTO>();
       
    private Long staffId;

    public List<StockReturnSubReqDTO> getReturnSubReqDTOs() {
        return returnSubReqDTOs;
    }

    public void setReturnSubReqDTOs(List<StockReturnSubReqDTO> returnSubReqDTOs) {
        this.returnSubReqDTOs = returnSubReqDTOs;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
    
    
}

