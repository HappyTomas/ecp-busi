package com.zengshi.ecp.order.dubbo.dto;


import java.util.List;

import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupOrdCheckRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromRespDTO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RPreOrdMainsResponse extends BaseResponseDTO {

    private Long staffId;
        
    private List<RPreOrdMainResponse> preOrdMainList;

    //优惠券信息
    private CoupOrdCheckRespDTO coupOrdCheckRespDTO;
    
    /** 
     * cartPromRespDTO:促销相关信息. 
     * @since JDK 1.6 
     */ 
    private CartPromRespDTO  cartPromRespDTO;
    

    private static final long serialVersionUID = 1L;


    public CartPromRespDTO getCartPromRespDTO() {
        return cartPromRespDTO;
    }


    public void setCartPromRespDTO(CartPromRespDTO cartPromRespDTO) {
        this.cartPromRespDTO = cartPromRespDTO;
    }


    public Long getStaffId() {
        return staffId;
    }


    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }


    public List<RPreOrdMainResponse> getPreOrdMainList() {
        return preOrdMainList;
    }


    public void setPreOrdMainList(List<RPreOrdMainResponse> preOrdMainList) {
        this.preOrdMainList = preOrdMainList;
    }

    public CoupOrdCheckRespDTO getCoupOrdCheckRespDTO() {
        return coupOrdCheckRespDTO;
    }

    public void setCoupOrdCheckRespDTO(CoupOrdCheckRespDTO coupOrdCheckRespDTO) {
        this.coupOrdCheckRespDTO = coupOrdCheckRespDTO;
    }
}

