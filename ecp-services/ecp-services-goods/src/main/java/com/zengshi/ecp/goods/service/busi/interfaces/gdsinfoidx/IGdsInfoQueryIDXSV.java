package com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsInfoShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoGdsIdx;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoShopIdx;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsInfoShopIdxRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSku2PropPropIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSkuInfoGdsIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSkuInfoShopIdxRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: 商品，单品索引查询接口 <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年3月24日下午3:30:16  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public interface IGdsInfoQueryIDXSV {

    /**
     * 
     * querySkuGdsIdxs:(根据单品商品索引，查找单品列表). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<GdsSkuInfoGdsIdx> querySkuGdsIdxs(GdsSkuInfoGdsIdxReqDTO reqDTO) throws BusinessException;

    public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoByCatgCode(GdsInfoReqDTO reqDTO);

    public List<Long> getGdsIdsByCatg(GdsInfoReqDTO reqDTO);

    public PageResponseDTO<GdsInfoShopIdxRespDTO> getGdsIdsPageByShopId(GdsInfoReqDTO gdsInfoReqDTO);

    public List<GdsInfoShopIdx> getGdsIdsByShopId(GdsInfoReqDTO gdsInfoReqDTO);

    public List<GdsSkuInfoShopIdx> getGdsSkuInfoByShopId(GdsSkuInfoReqDTO gdsSkuInfoReqDTO);

    public PageResponseDTO<GdsSkuInfoShopIdxRespDTO> getGdsSkuInfoPageByShopId(GdsSkuInfoReqDTO gdsSkuInfoReqDTO);

    public PageResponseDTO<GdsSkuInfoRespDTO> getSkuInfoListByPropIdx(GdsSku2PropPropIdxReqDTO reqDTO, SkuQueryOption[] skuQuerys);
    
    /**
     * 
     * countGdsInfoByShopIDAndStatus:根据店铺ID与商品状态统计商品数量. <br/> 
     * 
     * @author liyong7
     * @param reqDTO shopId,gdsStatus为必传参数
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public Long countGdsInfoByShopIDAndStatus(GdsInfoReqDTO reqDTO) throws BusinessException;
}

