package com.zengshi.ecp.goods.service.busi.impl.validation;

import java.util.Arrays;
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
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoForGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.impl.AbstractRSVImpl;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年9月28日上午10:30:25 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class GdsOrdSubChkSVImpl extends AbstractRSVImpl {

    @Resource
    private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;

    @Resource
    private IGdsStockSV gdsStockSV;
    
    @Resource
    private IGdsTypeSV gdsTypeSV;

    @Resource
    private ICustInfoRSV custInfoRSV;

    @Resource
    private IGdsCategorySV gdsCategorySV;

    public ROrdCartsChkResponse checkOrdSub(ROrdCartsCommRequest arg0) throws BusinessException {

        ROrdCartsChkResponse rOrdCartsChkResponse = new ROrdCartsChkResponse();

        // 默认验证通过
        rOrdCartsChkResponse.setStatus(true);

        if (CollectionUtils.isEmpty(arg0.getOrdCartsCommList())) {
            // 购物车实例列表为空不做处理，返回true
            rOrdCartsChkResponse.setMsg("购物车实例列表为空！");
            return rOrdCartsChkResponse;
        }
        for (ROrdCartCommRequest rOrdCartChkRequest : arg0.getOrdCartsCommList()) {

            // 购物车单品列表为空不做处理
            if (!CollectionUtils.isEmpty(rOrdCartChkRequest.getOrdCartItemCommList())) {
                for (ROrdCartItemCommRequest rOrdCartItemChkRequest : rOrdCartChkRequest.getOrdCartItemCommList()) {

                    // 先校验商品是否属于特定分类且购买用户是否为公共账户
                    Long staffId = arg0.getStaffId();
                    CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
                    custInfoReqDTO.setId(staffId);
                    CustInfoResDTO custInfoResDTO = custInfoRSV.getCustInfoById(custInfoReqDTO);
                    // 如果账号是公共账户
                    if (custInfoResDTO!=null && StringUtil.isNotBlank(custInfoResDTO.getCustType()) &&  custInfoResDTO.getCustType().equals(StaffConstants.custInfo.CUST_TYPE_PUBLIC)) {
                        GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
                        skuInfoReqDTO.setId(rOrdCartItemChkRequest.getSkuId());
                        GdsSkuInfoRespDTO gdsSkuInfoRespDTO = gdsSkuInfoQuerySV.querySkuInfoByOptions(skuInfoReqDTO, new SkuQueryOption[] { SkuQueryOption.BASIC });
                        String mainCatg = gdsSkuInfoRespDTO.getMainCatgs();
                        if (mainCatg != null && !mainCatg.equals("")) {
                            List<GdsCategoryRespDTO> categoryRespDTOs = gdsCategorySV.queryCategoryTraceUpon(mainCatg);
                            for (GdsCategoryRespDTO categoryRespDTO : categoryRespDTOs) {
                                if (Arrays.asList(GdsConstants.GdsCheckInfo.CHECKCATG_ARRAY).contains(categoryRespDTO.getCatgCode())) {
                                    rOrdCartsChkResponse.setStatus(false);
                                    rOrdCartsChkResponse.setMsg("购买者" + staffId + "为公共账户，不能购买试卷或者培训班类商品！");
                                    return rOrdCartsChkResponse;
                                }
                            }
                        }
                    }

                    // 判断商品状态是不是上架状态
                    GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
                    gdsSkuInfoReqDTO.setId(rOrdCartItemChkRequest.getSkuId());
                    GdsSkuInfo gdsSkuInfo = gdsSkuInfoQuerySV.queryGdsSkuInfo(gdsSkuInfoReqDTO);

                    if (gdsSkuInfo == null) {
                        rOrdCartsChkResponse.setStatus(false);
                        rOrdCartsChkResponse.setMsg("单品不存在：" + rOrdCartItemChkRequest.getSkuId());
                        return rOrdCartsChkResponse;
                    }

                    if (!GdsUtils.isOnShelves(gdsSkuInfo.getGdsStatus())) {
                        rOrdCartsChkResponse.setStatus(false);
                        rOrdCartsChkResponse.setMsg("单品未上架：" + gdsSkuInfo.getGdsName());
                        return rOrdCartsChkResponse;
                    }

                    // 不需要库存的商品不判断库存数量
                    GdsTypeRespDTO type=gdsTypeSV.queryGdsTypeByPKFromCache(gdsSkuInfo.getGdsTypeId());
                    if(type!=null && GdsUtils.isEqualsInvalidOrNULL(type.getIfNeedstock())){
                        return rOrdCartsChkResponse;
                    }
                    // 判断商品库存跟购买数量
                    // 分仓模式
                    if (GdsUtils.isEqualsValid(gdsSkuInfo.getIfDisperseStock())) {
                        // 区域参数校验,gdsStockSV不做校验
                        rOrdCartsChkResponse.setStatus(false);
                        rOrdCartsChkResponse.setMsg("分仓模式下，缺少区域判断参数：" + gdsSkuInfo.getGdsName());
                        return rOrdCartsChkResponse;

                    }
                    if ("1".equals(rOrdCartItemChkRequest.getPrnFlag())) {
                        rOrdCartsChkResponse.setStatus(true);
                        rOrdCartsChkResponse.setMsg("数字版商品");
                        return rOrdCartsChkResponse;
                    }
                    StockInfoForGdsReqDTO stockInfoForGdsDTO = new StockInfoForGdsReqDTO();
                    stockInfoForGdsDTO.setShopId(rOrdCartItemChkRequest.getShopId());
                    stockInfoForGdsDTO.setGdsId(rOrdCartItemChkRequest.getGdsId());
                    stockInfoForGdsDTO.setSkuId(rOrdCartItemChkRequest.getSkuId());
                    try {
                        StockInfoRespDTO stockInfoRespDTO = gdsStockSV.queryStockInfoByGds(stockInfoForGdsDTO);
                    	Long count = 0L;
                        if (GdsUtils.isEqualsInvalidOrNULL(gdsSkuInfo.getIfScoreGds())) {
                            // 加入阈值判断
                            count = Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_LACK_THRESHOLD).getParaValue());

                        } else {
                            // 积分商品的阈值暂时先不做处理，后续有需求再叠加
                            count = Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_SCORE_LACK_THRESHOLD).getParaValue());
                        }

                        if (stockInfoRespDTO.getAvailCount() < (rOrdCartItemChkRequest.getOrderAmount() + count)) {
                            rOrdCartsChkResponse.setStatus(false);
                            rOrdCartsChkResponse.setMsg("【" + gdsSkuInfo.getGdsName() + "】" + "购买量已超过实际库存，请调整");
                            return rOrdCartsChkResponse;
                        }
                        rOrdCartsChkResponse.setMsg("购物车实例列表为空！");

                    } catch (Exception e) {
                        LogUtil.error(MODULE, "", e);
                        throw new BusinessException(GdsErrorConstants.GdsStock.ERROR_GOODS_STOCK_235024);
                    }

                }
            }
        }
        return rOrdCartsChkResponse;
    }

}
