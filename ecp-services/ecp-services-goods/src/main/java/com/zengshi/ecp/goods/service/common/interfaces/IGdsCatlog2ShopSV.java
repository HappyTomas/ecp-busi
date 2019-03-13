/** 
 * Project Name:ecp-services-goods-server 
 * File Name:IGdsCatlog2ShopSV.java 
 * Package Name:com.zengshi.ecp.goods.service.common.interfaces 
 * Date:2016-4-1下午2:49:25 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsCatlog2Shop;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongListRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: 目录与店铺关系维护服务<br>
 * Date:2016-4-1下午2:49:25  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsCatlog2ShopSV {
	
	/**
	 * 
	 * saveGdsCatlog2Shop:保存目录店铺关系. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author liyong7
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void saveGdsCatlog2Shop(GdsCatlog2ShopReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * batchSaveGdsCatlog2Shop:批量保存目录与店铺关联关系. <br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void batchSaveGdsCatlog2Shop(GdsCatlog2ShopReqDTO reqDTO) throws BusinessException;
	/**
	 * 
	 * deleteGdsCatlog2Shop:删除目录店铺关联关系(因该关系表为复合主键所以该操作为物理删除). <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void deleteGdsCatlog2ShopByPK(GdsCatlog2ShopReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * deleteGdsCatlog2Shop:根据条件删除目录店铺关联关系. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void deleteGdsCatlog2Shop(GdsCatlog2ShopReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * queryRelationByShopId:分页查询. <br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsCatlog2ShopRespDTO> queryRelation(GdsCatlog2ShopReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * deleteRelationByShopId:根据店铺ID删除店铺与目录关联关系,该操作会对缓存进行清理. <br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void deleteRelationByShopId(GdsCatlog2ShopReqDTO reqDTO) throws BusinessException;
	/**
	 * 
	 * deleteRelationByCatlogId：根据目录ID删除目录与店铺关联关系，该操作不对缓存进行操作. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void deleteRelationByCatlogId(GdsCatlog2ShopReqDTO reqDTO) throws BusinessException ;
	/**
	 * 
	 * queryRelationedGdsCatlogIdByShopId:根据店铺ID获取与之关联的目录ID列表. <br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public LongListRespDTO queryRelationedGdsCatlogIdByShopId(
			GdsCatlog2ShopReqDTO reqDTO) throws BusinessException;
	

}

