/** 
 * Project Name:ecp-services-goods-server 
 * File Name:IGdsCatalogRSV.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.interfaces 
 * Date:2015-9-21上午9:25:05 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongListRespDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description:目录店铺关联服务 <br>
 * Date:2016-4-5上午9:27:49  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public interface IGdsCatlog2ShopRSV {
	
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
	 * batchSaveGdsCatlog2Shop:批量保存目录店铺关联关系. <br/> 
	 * 
	 * @author liyong7
	 * @param reqDTOLst
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
	 * queryRelationByShopId:根据店铺ID查询出关联关系. <br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsCatlog2ShopRespDTO> queryRelationByShopId(GdsCatlog2ShopReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * queryRelationByCatlogId:根据目录ID查询关联关系. <br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsCatlog2ShopRespDTO> queryRelationByCatlogId(GdsCatlog2ShopReqDTO reqDTO) throws BusinessException;
	/**
	 * 
	 * deleteRelationByShopId:根据店铺ID删除关联关系. <br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void deleteRelationByShopId(GdsCatlog2ShopReqDTO reqDTO) throws BusinessException;
	/**
	 * 
	 * deleteRelationByCatlogId:根据目录ID删除关联关系. <br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void deleteRelationByCatlogId(GdsCatlog2ShopReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * queryGdsCatlog2ShopRespDTOByShopId:根据店铺ID查询出目录店铺关联关系(优先从缓存获取). <br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @return LongListRespDTO 返回值需要作null判断.
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public LongListRespDTO queryGdsCatlog2ShopRespDTOByShopId(GdsCatlog2ShopReqDTO reqDTO) throws BusinessException;
}

