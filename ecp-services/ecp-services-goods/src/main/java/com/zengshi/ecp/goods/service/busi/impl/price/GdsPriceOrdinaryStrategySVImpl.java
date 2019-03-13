package com.zengshi.ecp.goods.service

.busi.impl.price;

import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsPriceMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSkuInfoMapper;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceInfoResp;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.price.IGdsSkuPiceStrategySV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品普通价格策略实现<br>
 * Date:2015年8月29日下午5:42:26 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsPriceOrdinaryStrategySVImpl extends AbstractSVImpl implements IGdsSkuPiceStrategySV {

    @Resource(name = "seq_gds_price")
    private PaasSequence seqGdsPrice;

    @Resource
    private GdsPriceMapper gdsPriceMapper;

    @Resource
    private GdsSkuInfoMapper skuInfoMapper;

    @Resource
    private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;

    @Override
    public GdsPriceInfoResp getPrice(Long id, Map<String, Object> params) throws BusinessException {
        if (params.get("skuId") == null) {
            return null;
        }
        Long skuId = (Long) params.get("skuId");
        GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
        gdsSkuInfoReqDTO.setId(skuId);
        GdsSkuInfo gdsSkuInfo = gdsSkuInfoQuerySV.queryGdsSkuInfo(gdsSkuInfoReqDTO);
        Long price = 0L;
        if (gdsSkuInfo != null) {
            price = gdsSkuInfo.getCommonPrice();
        }
        GdsPriceRespDTO resp = new GdsPriceRespDTO();
        resp.setPrice(price);
        resp.setPriceTypeCode(GdsConstants.GdsInfo.SKU_PRICE_TYPE_ORDINARY);
        // 普通价格Id 同SkuId
        resp.setId(skuId);
        return resp;
    }

    @Override
    public Long calculatePrice(Map<String, Object> params) throws BusinessException {
        if (params.get("skuId") == null) {
            return 0L;
        }
        Long skuId = (Long) params.get("skuId");
        GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
        gdsSkuInfoReqDTO.setId(skuId);
        GdsSkuInfo gdsSkuInfo = gdsSkuInfoQuerySV.queryGdsSkuInfo(gdsSkuInfoReqDTO);
        if (gdsSkuInfo != null) {
            return gdsSkuInfo.getCommonPrice();
        }
        return 0L;
    }

    @Override
    public ROrdCartsChkResponse validatePrice(Map<String, Object> params) throws BusinessException {
        ROrdCartsChkResponse resp = new ROrdCartsChkResponse();
        resp.setStatus(true);
        return resp;
    }

    @Override
    public Long savePrice(BaseInfo baseInfo, Map<String, Object> params) throws BusinessException {
        if (params.get("skuId") == null) {
            return 0L;
        }
        GdsSku2PriceReqDTO gdsSku2PriceReqDTO = (GdsSku2PriceReqDTO) baseInfo;
        GdsPriceReqDTO gdsPriceReqDTO = (GdsPriceReqDTO) gdsSku2PriceReqDTO.getPrice();
        Long skuId = (Long) params.get("skuId");
        Long price = gdsPriceReqDTO.getPrice();
        GdsSkuInfoCriteria criteria = new GdsSkuInfoCriteria();
        criteria.createCriteria().andIdEqualTo(skuId);
        GdsSkuInfo skuInfo = new GdsSkuInfo();
        skuInfo.setId(skuId);
        skuInfo.setCommonPrice(price);
        skuInfoMapper.updateByExample(skuInfo, criteria);
        return skuId;
    }

    @Override
    public void editPrice(BaseInfo baseInfo, Map<String, Object> params) throws BusinessException {
        if (params.get("skuId") == null) {
            return;
        }
        GdsSku2PriceReqDTO gdsSku2PriceReqDTO = (GdsSku2PriceReqDTO) baseInfo;
        GdsPriceReqDTO gdsPriceReqDTO = (GdsPriceReqDTO) gdsSku2PriceReqDTO.getPrice();
        Long skuId = (Long) params.get("skuId");
        Long price = gdsPriceReqDTO.getPrice();
        GdsSkuInfoCriteria criteria = new GdsSkuInfoCriteria();
        criteria.createCriteria().andIdEqualTo(skuId);
        GdsSkuInfo skuInfo = new GdsSkuInfo();
        skuInfo.setId(skuId);
        skuInfo.setCommonPrice(price);
        skuInfoMapper.updateByExample(skuInfo, criteria);
    }

    @Override
    public void delPrice(BaseInfo baseInfo, Map<String, Object> params) throws BusinessException {
        if(params.get("skuId") == null){
            return;
        }
        Long skuId = (Long) params.get("skuId");
        GdsSkuInfoCriteria criteria = new GdsSkuInfoCriteria();
        criteria.createCriteria().andIdEqualTo(skuId);
        GdsSkuInfo skuInfo = new GdsSkuInfo();
        skuInfo.setId(skuId);
        skuInfo.setCommonPrice(0L);
        skuInfoMapper.updateByExample(skuInfo, criteria);
    }

}
