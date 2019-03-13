package com.zengshi.ecp.goods.dubbo.dto;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsCatgCustDiscBatchDelReqDTO extends BaseInfo  {

    /** 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    private List<GdsCatgCustDiscReqDTO>  catgCustDiscReqDTOs = new ArrayList<GdsCatgCustDiscReqDTO>();
    
    private long staffId;

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public List<GdsCatgCustDiscReqDTO> getCatgCustDiscReqDTOs() {
        return catgCustDiscReqDTOs;
    }

    public void setCatgCustDiscReqDTOs(List<GdsCatgCustDiscReqDTO> catgCustDiscReqDTOs) {
        this.catgCustDiscReqDTOs = catgCustDiscReqDTOs;
    }
}

