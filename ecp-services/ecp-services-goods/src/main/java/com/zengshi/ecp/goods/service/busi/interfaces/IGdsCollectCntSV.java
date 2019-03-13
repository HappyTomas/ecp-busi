package com.zengshi.ecp.goods.service.busi.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品收藏操作接口<br>
 * Date:2015年9月4日上午11:39:32  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public interface IGdsCollectCntSV {

	/**
	 * 
	 * 增加收藏量。<br/>
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void addGdsCollectCnt(GdsCollectReqDTO reqDTO) throws BusinessException;
	
	
    /**
     * 
     * executeGdsCollectCntInc:收藏量加一. 
     * 
     * @author liyong7
     * @param reqDTO 
     * @since JDK 1.6
     */
	public void executeGdsCollectCntInc(GdsCollectReqDTO reqDTO);
	/**
	 * 
	 * executeGdsCollectCntDec:收藏量减一.
	 * 
	 * @author liyong7
	 * @param reqDTO 
	 * @since JDK 1.6
	 */
	public void executeGdsCollectCntDec(GdsCollectReqDTO reqDTO);
	
	
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
