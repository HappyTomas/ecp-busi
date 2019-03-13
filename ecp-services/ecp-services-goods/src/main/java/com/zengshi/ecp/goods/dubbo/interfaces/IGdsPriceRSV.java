package com.zengshi.ecp.goods.dubbo.interfaces;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.CartItemPriceInfo;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceCalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceCalRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceTypeRespDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description:商品价格dubbo服务接口 <br>
 * Date:2015年8月31日下午3:20:45 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsPriceRSV {

    /**
     * 
     * queryAllPriceType:(查询所有价格类型). <br/>
     * 
     * @author linwb3
     * @param info
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public List<GdsPriceTypeRespDTO> queryAllPriceType(BaseInfo info) throws BusinessException;

    /**
     * 
     * caculate:(计算购物车价格). <br/>
     * 
     * @author linwb3
     * @param reqDto
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public Map<Long, CartItemPriceInfo> caculate(ROrdCartsCommRequest reqDto) throws BusinessException;

    /**
     * 
     * caculate:(计算单品价格). <br/>
     * 
     * @author linwb3
     * @param reqDto
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public GdsPriceCalRespDTO caculate(GdsPriceCalReqDTO reqDto) throws BusinessException;

    /**
     * 
     * getDeaultPrice:(获取默认价格). <br/>
     * 
     * @author linwb3
     * @param skuInfoReqDTO
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public Long getDeaultPrice(GdsSkuInfoReqDTO skuInfoReqDTO) throws BusinessException;

}
