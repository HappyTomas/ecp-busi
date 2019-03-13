package com.zengshi.ecp.order.dubbo.dto;


import java.util.List;

import com.zengshi.ecp.prom.dubbo.dto.CartPromRespDTO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RShowOrdCartsResponse extends BaseResponseDTO {
    private Long staffId;
    
    private List<ROrdCartResponse> ordCartList;
    
    //促销相关信息
    private CartPromRespDTO cartPromRespDTO;


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


    public List<ROrdCartResponse> getOrdCartList() {
        return ordCartList;
    }


    public void setOrdCartList(List<ROrdCartResponse> ordCartList) {
        this.ordCartList = ordCartList;
    }

    
}

