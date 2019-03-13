package com.zengshi.ecp.goods.service.busi.impl.validation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoForGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV;
import com.zengshi.ecp.order.dubbo.dto.ROrdSubStaffIdxReq;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdSubRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年9月25日下午12:25:08 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class GdsOrdCartsChkSVImpl {

    @Resource
    private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;

    @Resource
    private IGdsStockSV gdsStockSV;
    
    @Resource
    private IGdsTypeSV gdsTypeSV;
    
    @Resource
    private IGdsInfoExternalRSV gdsInfoExternalRSV;
    
    @Resource
    private IOrdSubRSV ordSubRSV;

    public ROrdCartsChkResponse checkOrdCart(ROrdCartsCommRequest info) throws BusinessException {
        ROrdCartsChkResponse rOrdCartsChkResponse = new ROrdCartsChkResponse();

        // 默认验证通过
        rOrdCartsChkResponse.setStatus(true);

        if (CollectionUtils.isEmpty(info.getOrdCartsCommList())) {
            // 购物车实例列表为空不做处理，返回true
            rOrdCartsChkResponse.setMsg("购物车实例列表为空！");
            return rOrdCartsChkResponse;
        }

        for (ROrdCartCommRequest rOrdCartCommRequest : info.getOrdCartsCommList()) {

            // 购物车单品列表为空不做处理
            if (!CollectionUtils.isEmpty(rOrdCartCommRequest.getOrdCartItemCommList())) {
                for (ROrdCartItemCommRequest rOrdCartItemCommRequest : rOrdCartCommRequest
                        .getOrdCartItemCommList()) {

                    // 判断商品状态是不是上架状态
                    GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
                    gdsSkuInfoReqDTO.setId(rOrdCartItemCommRequest.getSkuId());
                    GdsSkuInfo gdsSkuInfo = gdsSkuInfoQuerySV.queryGdsSkuInfo(gdsSkuInfoReqDTO);

                    if (gdsSkuInfo == null) {
                        rOrdCartsChkResponse.setStatus(false);
                        rOrdCartsChkResponse.setMsg("单品不存在：" + rOrdCartItemCommRequest.getSkuId());
                        return rOrdCartsChkResponse;
                    }

                    if (!GdsUtils.isOnShelves(gdsSkuInfo.getGdsStatus())) {
                        rOrdCartsChkResponse.setStatus(false);
                        rOrdCartsChkResponse.setMsg("单品未上架：" + gdsSkuInfo.getGdsName());
                        return rOrdCartsChkResponse;
                    }

                    // 不需要库存的商品不判断库存数量
                    GdsTypeRespDTO type=gdsTypeSV.queryGdsTypeByPKFromCache(gdsSkuInfo.getGdsTypeId());
                    
                    // 添加是否只允许购买一次判断.
                    LongReqDTO longReqDTO = new LongReqDTO();
                    longReqDTO.setId(type.getId());
                    if(!gdsInfoExternalRSV.isGdsTypeBuyMore(longReqDTO)){
                        ROrdSubStaffIdxReq rordSubStaffIdxReq = new ROrdSubStaffIdxReq();
                        rordSubStaffIdxReq.setStaffId(rordSubStaffIdxReq.getStaff().getId());
                        rordSubStaffIdxReq.setSkuId(gdsSkuInfo.getId());
                        List<com.zengshi.ecp.order.dubbo.dto.ROrdSubStaffIdxResp> results = ordSubRSV
                                .queryOrderSubByStaffIdAndSkuid(rordSubStaffIdxReq);
                        if (!CollectionUtils.isEmpty(results)) {
                            rOrdCartsChkResponse.setStatus(false);
                            rOrdCartsChkResponse.setMsg(gdsSkuInfo.getGdsName() + " 只允许购买一次!"); 
                            return rOrdCartsChkResponse;
                        } 
                    }
                    
                    
                    if(type!=null && GdsUtils.isEqualsInvalidOrNULL(type.getIfNeedstock())){
                        return rOrdCartsChkResponse;
                    }
                    // 判断商品库存跟购买数量
                    // 分仓模式
                    if (GdsUtils.isEqualsValid(gdsSkuInfo.getIfDisperseStock())) {

                        //区域参数校验,gdsStockSV不做校验
                        rOrdCartsChkResponse.setStatus(false);
                        rOrdCartsChkResponse.setMsg("分仓模式下，缺少区域判断参数：" + gdsSkuInfo.getGdsName());
                        return rOrdCartsChkResponse;

                    }
                    if("1".equals(rOrdCartItemCommRequest.getPrnFlag() )){
                        return rOrdCartsChkResponse;
                    }
                    StockInfoForGdsReqDTO stockInfoForGdsDTO = new StockInfoForGdsReqDTO();
                    stockInfoForGdsDTO.setShopId(rOrdCartItemCommRequest.getShopId());
                    stockInfoForGdsDTO.setGdsId(rOrdCartItemCommRequest.getGdsId());
                    stockInfoForGdsDTO.setSkuId(rOrdCartItemCommRequest.getSkuId());

                    try {
                        StockInfoRespDTO stockInfoRespDTO = gdsStockSV
                                .queryStockInfoByGds(stockInfoForGdsDTO);
                        Long count = 0L;
                        if(GdsUtils.isEqualsInvalidOrNULL(gdsSkuInfo.getIfScoreGds())){
                            //加入阈值判断
                             count = Long.parseLong(SysCfgUtil.fetchSysCfg(
                                    GdsConstants.GdsStock.STOCK_LACK_THRESHOLD).getParaValue());
                       
                        }else{
                            //积分商品的阈值暂时先不做处理，后续有需求再叠加
                            count = Long.parseLong(SysCfgUtil.fetchSysCfg(
                                    GdsConstants.GdsStock.STOCK_SCORE_LACK_THRESHOLD).getParaValue());
                        }
                   
                        if (stockInfoRespDTO.getAvailCount() < (rOrdCartItemCommRequest
                                .getOrderAmount() + count)) {
                            rOrdCartsChkResponse.setStatus(false);
                            rOrdCartsChkResponse.setMsg("【"+gdsSkuInfo.getGdsName()+"】"+"购买量已超过实际库存，请调整");
                            return rOrdCartsChkResponse;
                        }
                    } catch (Exception e) {
                        throw new BusinessException(
                                GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235024);
                    }

                }
            }
        }

        return rOrdCartsChkResponse;
    }
}
