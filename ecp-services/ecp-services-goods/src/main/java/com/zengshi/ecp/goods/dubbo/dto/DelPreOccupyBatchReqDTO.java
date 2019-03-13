package com.zengshi.ecp.goods.dubbo.dto;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class DelPreOccupyBatchReqDTO extends BaseInfo{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 40079231375419755L;

    private List<DelPreOccupyReqDTO> delPreOccupyDTOs = new ArrayList<DelPreOccupyReqDTO>();

    private Long staffId;

    public List<DelPreOccupyReqDTO> getDelPreOccupyDTOs() {
        return delPreOccupyDTOs;
    }

    public void setDelPreOccupyDTOs(List<DelPreOccupyReqDTO> delPreOccupyDTOs) {
        this.delPreOccupyDTOs = delPreOccupyDTOs;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

}
