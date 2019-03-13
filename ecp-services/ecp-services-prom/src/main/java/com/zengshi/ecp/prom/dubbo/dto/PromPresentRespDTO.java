package com.zengshi.ecp.prom.dubbo.dto;

import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-20 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromPresentRespDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    private Map<Long,PromPresentDTO> cartPromDTOMap;//购物车 key=购物车id
    
    private Map<Long,PromPresentDTO> cartPromItemDTOMap;//购物车明细 key=购物车明细id

    public Map<Long,PromPresentDTO> getCartPromDTOMap() {
        return cartPromDTOMap;
    }

    public void setCartPromDTOMap(Map<Long,PromPresentDTO> cartPromDTOMap) {
        this.cartPromDTOMap = cartPromDTOMap;
    }

    public Map<Long,PromPresentDTO> getCartPromItemDTOMap() {
        return cartPromItemDTOMap;
    }

    public void setCartPromItemDTOMap(Map<Long,PromPresentDTO> cartPromItemDTOMap) {
        this.cartPromItemDTOMap = cartPromItemDTOMap;
    }
 
 
}
