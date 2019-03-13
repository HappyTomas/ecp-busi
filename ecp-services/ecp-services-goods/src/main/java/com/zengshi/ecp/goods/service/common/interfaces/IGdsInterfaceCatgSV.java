package com.zengshi.ecp.goods.service.common.interfaces;

import com.zengshi.ecp.goods.dao.model.GdsInterfaceCatg;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.dataimport.GdsInterfaceCatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.dataimport.GdsInterfaceCatgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: 分类信息导入映射服务.<br>
 * Date:2015-10-27上午10:59:02  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public interface IGdsInterfaceCatgSV {
	
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
	 * queryPaing:分页查询<br/>
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsInterfaceCatgRespDTO> queryPaing(GdsInterfaceCatgReqDTO reqDTO)throws BusinessException;
	
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
	public GdsInterfaceCatg queryInterfaceCatgByOriginAndOriginCatgCode(
			GdsInterfaceCatgReqDTO reqDTO) throws BusinessException;
	
	
	
	public GdsInterfaceCatg queryInterfaceCatgByCatgCode(
			GdsInterfaceCatgReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * 根据分类编码查询分类与外系统映射关联关系. 
	 * 
	 * @author liyong7
	 * @param catgCode
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public GdsInterfaceCatg queryInterfaceCatgByCatgCode(String catgCode)throws BusinessException;
	
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
	 * deleteInterfaceCatg:根据catgCode删除分类映射数据。 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void deleteInterfaceCatgByCatgCode(GdsInterfaceCatgReqDTO reqDTO)
			throws BusinessException;

}

