/** 
 * Project Name:ecp-services-goods 
 * File Name:IGdsTypeRSV.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.interfaces 
 * Date:2015年8月27日下午5:43:39 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsType;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdslog.GdsLogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdslog.GdsLogRespDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.CacheUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月27日下午5:43:39  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author lincx
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsLogRSV {
    
    
    /**
     * 根据id获取商品日志
     * @param id
     * @return
     * @throws BusinessException
     * @throws  
     */
	public List<GdsLogRespDTO> queryGdsLogRespDTO(GdsLogReqDTO dto) throws BusinessException;

}

