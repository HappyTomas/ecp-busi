package com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsSku2Prop;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsVerifyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSku2PropPropIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PropReqDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 单品管理数据查询接口，主要负责单品数据的查询<br>
 * Date:2015年8月22日下午6:04:05 <br>
 * Copyright (c)throws BusinessException 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsSkuInfoQuerySV extends IGeneralSQLSV {

	/**
	 * 
	 * queryGdsInfo:(查询单个单品信息)throws BusinessException. <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @return
	 * @since JDK 1.6
	 */
	public GdsSkuInfoRespDTO queryGdsSkuInfoResp(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) throws BusinessException;

	/**
	 * 
	 * queryGdsInfo:(查询单个单品信息)throws BusinessException. <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @return
	 * @since JDK 1.6
	 */
	public GdsSkuInfo queryGdsSkuInfo(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) throws BusinessException;

	/**
	 * 
	 * querySkuInfoByOptions:(根据查询查询对应的单品信息)throws BusinessException. <br/>
	 * 
	 * @author linwb3
	 * @param skuInfoReqDTO
	 * @param skuQuerys
	 * @return
	 * @since JDK 1.6
	 */
	public GdsSkuInfoRespDTO querySkuInfoByOptions(GdsSkuInfoReqDTO skuInfoReqDTO, SkuQueryOption... skuQuerys) throws BusinessException;

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
	 * queryGdsInfo:(查询单品列表 不分页)throws BusinessException. <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @return
	 * @since JDK 1.6
	 */
	public List<GdsSkuInfoRespDTO> queryGdsSkuInfoListResp(GdsSkuInfoReqDTO gdsSkuInfoReqDTO, SkuQueryOption... skuQuerys) throws BusinessException;

	/**
	 * 
	 * queryGdsInfo:(查询单品列表 分页)throws BusinessException. <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @return
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsSkuInfoRespDTO> queryGdsSkuInfoPageListResp(GdsSkuInfoReqDTO gdsSkuInfoReqDTO, SkuQueryOption... skuQuerys) throws BusinessException;


	/**
	 * 
	 * querySkuInfoByProp:(通过属性查询单品列表). <br/>
	 * 
	 * @author linwb3
	 * @param props
	 * @return
	 * @since JDK 1.6
	 */
	public List<GdsSku2Prop> querySkuInfoByProp(List<GdsSku2PropReqDTO> props, Long gdsId, boolean distinct);



	/**
	 * 
	 * querySkuInfoFromDB:(查询单品信息 从DB). <br/>
	 * 
	 * @author linwb3
	 * @param gdsSkuInfoReqDTO
	 * @return
	 * @since JDK 1.6
	 */
	public GdsSkuInfo querySkuInfoFromDB(GdsSkuInfoReqDTO gdsSkuInfoReqDTO);

	/**
	 * 
	 * 根据属性值查询条件分页查询信息.
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsSkuInfoRespDTO> queryGdsSkuInfoPagingByProp(GdsSku2PropPropIdxReqDTO reqDTO, SkuQueryOption... skuQuerys) throws BusinessException;



	/**
	 * 
	 * querySkuInfoListPageALLDB:(全库逐表扫描单品信息). <br/> 
	 * 
	 * @author linwb3 
	 * @param gdsInfoReqDTO
	 * @return 
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsSkuInfoRespDTO> querySkuInfoListPageALLDB(GdsSkuInfoReqDTO gdsInfoReqDTO);

	
}
