package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.butterfly.app.model.IBody;

public class Ord102Resp extends IBody {
    /** 
     * staffId:当前用户编码. 
     * @since JDK 1.6 
     */ 
    private Long staffId;
    
    /** 
     * ordCartList:购物车商品信息. 
     * @since JDK 1.6 
     */ 
    private List<Ord10201Resp> ordCartList;
    

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public List<Ord10201Resp> getOrdCartList() {
        return ordCartList;
    }

    public void setOrdCartList(List<Ord10201Resp> ordCartList) {
        this.ordCartList = ordCartList;
    }

}

