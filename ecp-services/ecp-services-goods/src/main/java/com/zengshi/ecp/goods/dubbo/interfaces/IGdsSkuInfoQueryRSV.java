package com.zengshi.ecp.goods.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSku2PropPropIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 单品查询dubbo服务接口<br>
 * Date:2015年8月30日下午4:37:41 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsSkuInfoQueryRSV {

	/**
	 * 
	 * queryGdsInfo:(查询单个单品信息). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @return
	 * @since JDK 1.6
	 */
	public GdsSkuInfoRespDTO queryGdsSkuInfoResp(
			GdsSkuInfoReqDTO gdsSkuInfoReqDTO) throws BusinessException;

	/**
	 * 
	 * querySkuInfoByOptions:(根据查询查询对应的单品信息). <br/>
	 * 
	 * @author linwb3
	 * @param skuInfoReqDTO
	 * @param skuQuerys
	 * @return
	 * @since JDK 1.6
	 */
	public GdsSkuInfoRespDTO querySkuInfoByOptions(
			GdsSkuInfoReqDTO skuInfoReqDTO) throws BusinessException;

	
	/**
	 * 
	 * queryGdsInfoByOption:(查询商品，通过选项列表表达需要查询的项目). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @param options
	 * @return
	 * @since JDK 1.6
	 */
	public Boolean isBelongToCategory(GdsGds2CatgReqDTO gds2CatgReqDTO) throws BusinessException;

	/**
	 * 
	 * queryGdsInfo:(查询单品列表 不分页). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @return
	 * @since JDK 1.6
	 */
	public List<GdsSkuInfoRespDTO> queryGdsSkuInfoList(
			GdsSkuInfoReqDTO gdsSkuInfoReqDTO) throws BusinessException;

	/**
	 * 
	 * queryGdsInfo:(查询单品列表 分页). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @return
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsSkuInfoRespDTO> queryGdsSkuInfoListPage(
			GdsSkuInfoReqDTO gdsSkuInfoReqDTO) throws BusinessException;
	
	/**
	 * 
	 * 根据单品与属性索引表条件查询单品信息.
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @param skuQuerys
	 * @return 如果options为空则默认options为:SkuQueryOption.MAINPIC,SkuQueryOption.PRICE,SkuQueryOption.BASIC
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsSkuInfoRespDTO> queryGdsSkuInfoPaging(
			GdsSku2PropPropIdxReqDTO reqDTO) throws BusinessException;

	public PageResponseDTO<GdsSkuInfoRespDTO> querySkuInfoListPageALLDB(GdsSkuInfoReqDTO gdsInfoReqDTO);

}
