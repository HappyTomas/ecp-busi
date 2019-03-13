package com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsInfo;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsVerifyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsVerifyRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.HomePageGdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.HomePageGdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.server.auth.DataObject;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品管理数据查询接口，主要负责商品数据的查询<br>
 * Date:2015年8月22日下午6:04:05 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsInfoQuerySV extends IGeneralSQLSV {

	/**
	 * 
	 * queryGdsInfoModel:(查询商品主表信息). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public GdsInfo queryGdsInfoModel(GdsInfoReqDTO gdsInfo) throws BusinessException;

	/**
	 * 
	 * queryGdsInfo:(查询单个商品信息). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @return
	 * @since JDK 1.6
	 */
	public GdsInfoRespDTO queryGdsInfo(GdsInfoReqDTO gdsInfo) throws BusinessException;

	/**
	 * 
	 * queryGdsInfo:(查询单个商品信息). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @return
	 * @since JDK 1.6
	 */
	public GdsInfoRespDTO queryGdsInfoById(Long gdsId, String... status) throws BusinessException;

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
	public GdsInfoRespDTO queryGdsInfoByOption(GdsInfoReqDTO gdsInfo, GdsQueryOption... gdsQueryOptions) throws BusinessException;

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
	 * queryGdsInfoByOption:(查询商品，通过选项列表表达需要查询的项目). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @param options
	 * @return
	 * @since JDK 1.6
	 */
	public GdsInfoRespDTO queryGdsInfoByOption(GdsInfoReqDTO gdsInfo, GdsQueryOption[] gdsQueryOptions, SkuQueryOption... skuQuerys) throws BusinessException;

	/**
	 * 
	 * queryGdsInfoByOption:(查询商品，详情页面读取商品信息). <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @param options
	 * @return
	 * @since JDK 1.6
	 */
	public GdsInfoDetailRespDTO queryGdsInfoDetail(GdsInfoReqDTO gdsInfo) throws BusinessException;

	/**
	 * 
	 * queryGdsInfoListByCatgCode:(查询对应分类的商品信息列表). <br/>
	 * 店铺维度查询
	 * 
	 * @author linwb3
	 * @param gdsCategory
	 * @return
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListByCatgCode(GdsInfoReqDTO reqDto) throws BusinessException;

	/**
	 * 
	 * queryGdsInfoList:(根据条件查询对应的商品列表，不分页). <br/>
	 * 店铺维度查询
	 * 
	 * @author linwb3
	 * @param gdsCategory
	 * @param isContainSub
	 * @return
	 * @since JDK 1.6
	 */
	public List<GdsInfoRespDTO> queryGdsInfoList(GdsInfoReqDTO gdsInfoReqDTO, GdsQueryOption... gdsOptions) throws BusinessException;

	/**
	 * 
	 * queryGdsInfoList:(根据条件查询对应的商品列表，不分页). <br/>
	 * 店铺维度查询
	 * 
	 * @author linwb3
	 * @param gdsCategory
	 * @param isContainSub
	 * @return
	 * @since JDK 1.6
	 */
	public List<GdsInfoRespDTO> queryGdsInfoList(GdsInfoReqDTO gdsInfoReqDTO, GdsQueryOption[] gdsQueryOptions, SkuQueryOption... skuQueryOptions) throws BusinessException;

	/**
	 * 
	 * queryGdsInfoListPage:(根据条件查询对应的商品列表，分页). <br/>
	 * 店铺维度查询
	 * 
	 * @author linwb3
	 * @param gdsCategory
	 * @param isContainSub
	 * @return
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListPage(GdsInfoReqDTO gdsInfoReqDTO, GdsQueryOption... gdsOptions) throws BusinessException;

	/**
	 * 
	 * queryGdsInfoListPage:(根据条件查询对应的商品列表，分页). <br/>
	 * 
	 * @author linwb3
	 * @param gdsCategory
	 * @param isContainSub
	 * @return
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListPage(GdsInfoReqDTO gdsInfoReqDTO, GdsQueryOption[] gdsQueryOptions, SkuQueryOption... skuQueryOptions) throws BusinessException;

	/**
	 * 
	 * queryGdsInfoListPage:(根据条件查询对应的商品列表，分页). <br/>
	 * 
	 * @author linwb3
	 * @param gdsCategory
	 * @param isContainSub
	 * @return
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListPageALLDB(GdsInfoReqDTO gdsInfoReqDTO, GdsQueryOption... gdsQueryOption) throws BusinessException;



	/**
	 * 
	 * querySkuInfoRespsByGdsId:(查询商品对应的单品列表). <br/>
	 * 
	 * @author linwb3
	 * @param gdsId
	 * @return
	 * @since JDK 1.6
	 */
	public List<GdsSkuInfoRespDTO> querySkuInfoRespsByGdsId(Long gdsId, String... status) throws BusinessException;

	/**
	 * 
	 * queryGdsCatgsByGdsId:(查询商品对应的单品列表). <br/>
	 * 
	 * @author linwb3
	 * @param gdsId
	 * @return
	 * @since JDK 1.6
	 */
	public List<GdsSkuInfo> querySkuInfosByGdsId(Long gdsId, String... gdsStatus) throws BusinessException;



	/**
	 * 
	 * queryGdsInfoModelFromDB:(查询商品信息 从数据库) <br/>
	 * 
	 * @author linwb3
	 * @param gdsInfo
	 * @return
	 * @since JDK 1.6
	 */
	public GdsInfo queryGdsInfoModelFromDB(GdsInfoReqDTO gdsInfo);


	public List<GdsInfoDetailRespDTO> queryGdsSkuInfoListByCatgRela(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException;

	public List<GdsInfoDetailRespDTO> queryGdsSkuInfoListByRank(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException;

	public HomePageGdsInfoRespDTO getHomePageGdsInfo(HomePageGdsInfoReqDTO gdsInfoReqDTO) throws BusinessException;
	/**
	 * 
	 * queryGdsVerifyInfoPage:(查询商品操作审核信息分页列表). <br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author gxq 
	 * @param gdsInfoReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsVerifyRespDTO> queryGdsVerifyInfoPage(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException;
	
	
	/**
	 * 
	 * queryGdsInfoListPageWithAuth:带权限查询<br/>
	 * 
	 * @author liyong7
	 * @param gdsInfoReqDTO
	 * @param gdsQueryOptions
	 * @param skuQueryOptions
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListPageWithAuth(@DataObject("gdsInfoReqDTO") GdsInfoReqDTO gdsInfoReqDTO,
            GdsQueryOption[] gdsQueryOptions, SkuQueryOption... skuQueryOptions)
            throws BusinessException;

	public List<Long> querySkuIdsGdsId(Long gdsId,String...gdsStatus) throws BusinessException;

	
	/**
	 * 根据状态获取审核记录数
	 * @param gdsVerifyReqDTO
	 * @return
	 * @throws Exception
	 */
	public Long  queryGdsVerifyInfoCount(GdsVerifyReqDTO gdsVerifyReqDTO)throws Exception;

	/**
	 * 
	 * getPropsByGdsId:(获取商品对应的属性列表). <br/> 
	 * 
	 * @author zhanbh 
	 * @param gdsId
	 * @param gdsInfoReqDTO
	 * @return 
	 * @since JDK 1.6
	 */
    List<GdsPropRespDTO> getPropsByGdsId(Long gdsId, GdsInfoReqDTO gdsInfoReqDTO);

}
