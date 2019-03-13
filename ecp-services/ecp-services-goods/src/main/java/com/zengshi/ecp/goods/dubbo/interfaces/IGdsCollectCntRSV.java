package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongRespDTO;

/**
 * 
 * Title: 目录站点关联关系dubbo服务. <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-10-20下午10:58:35  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public interface IGdsCollectCntRSV {
	
	/**
	 * 
	 * executeCount:统计收藏总量. <br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @return 
	 * @since JDK 1.6
	 */
	public LongRespDTO executeCount(GdsCollectReqDTO reqDTO);
	

}

