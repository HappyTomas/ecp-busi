package com.zengshi.ecp.goods.service.busi.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;

/**
 * 
 * Title: 商品快照服务<br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年3月24日下午3:23:18  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public interface IGdsSkuSnapSV {

	
	public Long addGdsSkuSnapInfo(GdsSkuInfoReqDTO gdsSkuInfoReqDTO )throws Exception;
	
}

