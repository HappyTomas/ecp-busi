/** 
 * Project Name:ecp-services-goods-server 
 * File Name:IGdsInterfaceCatgRSV.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.interfaces 
 * Date:2015-10-27上午10:39:40 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.dataimport.GdsInterfaceCatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.dataimport.GdsInterfaceCatgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title:分类信息导入映射服务 <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-10-27上午10:39:40  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsInterfaceCatgRSV {
	
	/**
	 * 
	 * 根据分类原始编码查询ECP对应分类信息. 
	 * 
	 * @author liyong7
	 * @param reqDTO origin,originCatgCode为传传参数.
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public GdsCategoryRespDTO queryCategoryByOriginCatgCode(GdsInterfaceCatgReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * 保存外系统分类与ECP分类关联关系.
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void saveGdsInterfaceCatg(GdsInterfaceCatgReqDTO reqDTO) throws BusinessException;
	/**
	 * 
	 * 根据原始分类编码与系统来源查询分类与原始分类信息对照信息.
	 * 
	 * @author liyong7
	 * @param reqDTO origin,originCatgCode必传参数.
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public GdsInterfaceCatgRespDTO queryInterfaceCatgByOriginAndOriginCatgCode(
			GdsInterfaceCatgReqDTO reqDTO) throws BusinessException;
	
	
	
	public GdsInterfaceCatgRespDTO queryInterfaceCatgByCatgCode(
			GdsInterfaceCatgReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * 更新数据导入分类关联关系。 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void updateInterfaceCatg(GdsInterfaceCatgReqDTO reqDTO)throws BusinessException;
	/**
	 * 
	 * queryPaging:分类映射关系表分页查询。 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsInterfaceCatgRespDTO> queryPaging(GdsInterfaceCatgReqDTO reqDTO) throws BusinessException;

}

