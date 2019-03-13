package com.zengshi.ecp.busi.shop.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-mobile <br>
 * Description: <br>
 * Date:2016年8月18日下午2:48:43  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
public class ShopMainVO extends EcpBasePageReqVO {

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = -2004460624500515441L;
    /**
     * 店铺id
     */
    private Long shopId;
    /**
     * 数量
     */
    private int count;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
}
