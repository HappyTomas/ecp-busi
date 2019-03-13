/** 
 * Project Name:ecp-services-goods-server 
 * File Name:IGdsEvalTransactionSV.java 
 * Package Name:com.zengshi.ecp.goods.facade.interfaces.eventual 
 * Date:2015-10-16上午9:47:31 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.facade.interfaces.eventual;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-10-16上午9:47:31  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsEvalMainTransaction {
	
	/**
	 * 
	 * 新增商品回复.
	 * 
	 * @author liyong7
	 * @param reqDTO  content,gdsId,orderId,orderSubId,shopId,staffId 为必填字段,不允许为空.
	 * @return 
	 * @since JDK 1.6
	 */
	public GdsEvalRespDTO addEval(final GdsEvalReqDTO reqDTO);
	
	/**
	 * 
	 * executeAuditNotify:执行评价审核. <br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO 
	 * @since JDK 1.6
	 */
	public void executeEvalAudit(GdsEvalReqDTO reqDTOLst);
	
}

