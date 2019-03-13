package com.zengshi.ecp.order.dubbo.dto;


import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrdMainsResponse extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 7752916158780295106L;

    private Long staffId;
        
    private List<ROrdMainResponse> ordMainList;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public List<ROrdMainResponse> getOrdMainList() {
        return ordMainList;
    }

    public void setOrdMainList(List<ROrdMainResponse> ordMainList) {
        this.ordMainList = ordMainList;
    }
}

