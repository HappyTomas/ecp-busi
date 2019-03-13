package com.zengshi.ecp.busi.shop.shopIndex.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年9月14日下午5:05:04 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version @param <T>
 * @since JDK 1.6
 */
public class ShopIndexVO extends EcpBasePageReqVO {

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = -2004460624500515441L;
    
    private Long shopId;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
    
    
}
