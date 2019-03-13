package com.zengshi.ecp.goods.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsVerifyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsVerifyRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.HomePageGdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.HomePageGdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2MediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2PropRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品查询dubbo服务接口<br>
 * Date:2015年8月30日下午4:37:06 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsInfoQueryRSV {
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
     * queryGdsInfoByOption:(查询商品，通过选项列表表达需要查询的项目). <br/>
     * 
     * @author linwb3
     * @param gdsInfo
     * @param options
     * @return
     * @since JDK 1.6
     */
    public GdsInfoRespDTO queryGdsInfoByOption(GdsInfoReqDTO gdsInfo) throws BusinessException;

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
     * queryGdsInfoByOption:(查询商品，通过选项列表表达需要查询的项目， 商品详情). <br/>
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
     * 
     * @author linwb3
     * @param gdsCategory
     * @param isContainSub
     * @return
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListByCatgCode(GdsInfoReqDTO reqDto)
            throws BusinessException;

    /**
     * 
     * queryGdsCatgsByGdsId:(查询商品对应的分类关系列表). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    public List<GdsGds2CatgRespDTO> queryGds2CatgsByGdsId(LongReqDTO gdsId)
            throws BusinessException;

    /**
     * 
     * queryGdsCatgsByGdsId:(查询商品对应的属性列表). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    public List<GdsGds2PropRespDTO> queryGds2PropsByGdsId(LongReqDTO gdsId)
            throws BusinessException;

    /**
     * 
     * queryGdsCatgsByGdsId:(查询商品对应媒体图片关系列表). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    public List<GdsGds2MediaRespDTO> queryGds2MediasByGdsId(LongReqDTO gdsId)
            throws BusinessException;

    /**
     * 
     * querySkuInfosByGdsId:(查询商品对应的单品列表). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    public List<GdsSkuInfoRespDTO> querySkuInfosByGdsId(GdsInfoReqDTO reqDto)
            throws BusinessException;

    /**
     * 
     * querySkuInfosByGdsId:(查询商品对应的单品列表). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    public List<Long> querySkuIdsByGdsId(GdsInfoReqDTO reqDto) throws BusinessException;

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
    public List<GdsInfoRespDTO> queryGdsInfoList(GdsInfoReqDTO gdsInfoReqDTO)
            throws BusinessException;

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
    public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListPage(GdsInfoReqDTO gdsInfoReqDTO)
            throws BusinessException;

    public List<GdsInfoDetailRespDTO> queryGdsSkuInfoListByCatgRela(GdsInfoReqDTO gdsInfoReqDTO)
            throws BusinessException;

    public List<GdsInfoDetailRespDTO> queryGdsSkuInfoListByRank(GdsInfoReqDTO gdsInfoReqDTO)
            throws BusinessException;
    /**
     * 
     * getHomePageGdsInfo:(首页商品信息服务). <br/>
     * 
     * @author zjh 
     * @param gdsInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public HomePageGdsInfoRespDTO getHomePageGdsInfo(HomePageGdsInfoReqDTO gdsInfoReqDTO) throws BusinessException;
    
    /**
     * 
     * queryGdsVerifyInfoPage:(查询商品操作审核信息分页列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * 
     * @author gxq 
     * @param gdsInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsVerifyRespDTO> queryGdsVerifyInfoPage(GdsVerifyReqDTO GdsVerifyReqDTO) throws BusinessException;
    
    /**
     * 
     * queryGdsInfoListPageWithAuth:带权限商品信息查询. <br/> 
     * 
     * @author liyong7
     * @param gdsInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListPageWithAuth(
			GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException;
    
    /**
     * 
     * countGdsInfoByShopIDAndStatus:根据店铺ID与商品状诚统计数量. <br/> 
     * 
     * @author liyong7
     * @param reqDTO shopId,gdsStatus为必传参数
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Long countGdsInfoByShopIDAndStatus(GdsInfoReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * countGdsInfoByShopIDAndStatus:根据店铺ID与商品状诚统计数量. <br/> 
     * 
     * @author liyong7
     * @param reqDTO shopId,gdsStatus为必传参数
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Long countGdsInfoByShopIDAndStatusArr(GdsInfoReqDTO reqDTO) throws BusinessException;

    /**
     * queryGdsVerifyInfoCount
     * @author zjh
     * @param gdsVerifyReqDTO
     * @return
     * @throws BusinessException
     */
    public Long queryGdsVerifyInfoCount(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException; 
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
