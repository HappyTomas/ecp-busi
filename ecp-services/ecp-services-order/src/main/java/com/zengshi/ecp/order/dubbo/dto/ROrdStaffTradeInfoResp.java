package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ROrdStaffTradeInfoResp extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -310230139096668415L;
    
    private List<OrdMainShopUal> staffIds;

    public List<OrdMainShopUal> getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(List<OrdMainShopUal> staffIds) {
        this.staffIds = staffIds;
    }
    
    

}

