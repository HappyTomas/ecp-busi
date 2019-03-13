package com.zengshi.ecp.aip.third.dubbo.dto.resp;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class ShopTopicListRespDTO extends BaseResponseDTO {

	private static final long serialVersionUID = 1L;

	private List<ShopTopicRespDTO> shopTopicRespDTOs;

	public List<ShopTopicRespDTO> getShopTopicRespDTOs() {
		return shopTopicRespDTOs;
	}

	public void setShopTopicRespDTOs(List<ShopTopicRespDTO> shopTopicRespDTOs) {
		this.shopTopicRespDTOs = shopTopicRespDTOs;
	}
	 

}

