package com.zengshi.ecp.busi.prom.auth.vo;

import java.util.List;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-20 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class AuthShopVO extends EcpBaseResponseVO{

    private static final long serialVersionUID = 1L;
    
    private List<ShopVO> shopVOList;//新增授权页面列表

    public List<ShopVO> getShopVOList() {
        return shopVOList;
    }

    public void setShopVOList(List<ShopVO> shopVOList) {
        this.shopVOList = shopVOList;
    }

}
