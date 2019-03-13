package com.zengshi.ecp.goods.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.stock.StockPreOccupyReqDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class DelPreOccupyReqDTO extends BaseInfo{
/** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -5890288051103150423L;
private List<StockPreOccupyReqDTO> preOccupyDTOs;
private Long staffId;
public List<StockPreOccupyReqDTO> getPreOccupyDTOs() {
    return preOccupyDTOs;
}
public void setPreOccupyDTOs(List<StockPreOccupyReqDTO> preOccupyDTOs) {
    this.preOccupyDTOs = preOccupyDTOs;
}
public Long getStaffId() {
    return staffId;
}
public void setStaffId(Long staffId) {
    this.staffId = staffId;
}

}

