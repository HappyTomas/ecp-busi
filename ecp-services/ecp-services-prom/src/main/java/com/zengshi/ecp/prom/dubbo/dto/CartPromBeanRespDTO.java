package com.zengshi.ecp.prom.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-16 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CartPromBeanRespDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    private CartPromDTO cartPromDTO;//店铺级别基本信息
    
    private CartPromItemDTO cartPromItemDTO;//购物车明细基本信息

    public CartPromDTO getCartPromDTO() {
        return cartPromDTO;
    }

    public void setCartPromDTO(CartPromDTO cartPromDTO) {
        this.cartPromDTO = cartPromDTO;
    }

    public CartPromItemDTO getCartPromItemDTO() {
        return cartPromItemDTO;
    }

    public void setCartPromItemDTO(CartPromItemDTO cartPromItemDTO) {
        this.cartPromItemDTO = cartPromItemDTO;
    }
     
}
