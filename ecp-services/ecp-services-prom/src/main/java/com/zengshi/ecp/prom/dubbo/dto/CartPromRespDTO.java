package com.zengshi.ecp.prom.dubbo.dto;

import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-1 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CartPromRespDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    private Map<Long,CartPromDTO> cartPromDTOMap;//购物车 key=购物车id
    
    private Map<Long,CartPromItemDTO> cartPromItemDTOMap;//购物车明细 key=购物车明细id

    public Map<Long,CartPromDTO> getCartPromDTOMap() {
        return cartPromDTOMap;
    }

    public void setCartPromDTOMap(Map<Long,CartPromDTO> cartPromDTOMap) {
        this.cartPromDTOMap = cartPromDTOMap;
    }

    public Map<Long,CartPromItemDTO> getCartPromItemDTOMap() {
        return cartPromItemDTOMap;
    }

    public void setCartPromItemDTOMap(Map<Long,CartPromItemDTO> cartPromItemDTOMap) {
        this.cartPromItemDTOMap = cartPromItemDTOMap;
    }
 
}
