package com.zengshi.ecp.goods.service.busi.impl.price;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsGds2PropMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSku2PriceMapper;
import com.zengshi.ecp.goods.dao.model.GdsGds2Prop;
import com.zengshi.ecp.goods.dao.model.GdsGds2PropCriteria;
import com.zengshi.ecp.goods.dao.model.GdsInfo;
import com.zengshi.ecp.goods.dao.model.GdsPriceType;
import com.zengshi.ecp.goods.dao.model.GdsSku2Price;
import com.zengshi.ecp.goods.dao.model.GdsSku2PriceCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.dto.CalCatgCustDiscReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.CartItemPriceInfo;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceCalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceCalRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceInfoResp;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsCatgCustDiscSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsScoreExtSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.price.IGdsPriceSV;
import com.zengshi.ecp.goods.service.busi.interfaces.price.IGdsSkuPiceStrategySV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPriceTypeSV;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 单品价格服务<br>
 * Date:2015年8月29日下午5:43:09 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsPriceSVImpl extends AbstractSVImpl implements IGdsPriceSV {
    public static final String MOUDLE = GdsPriceSVImpl.class.getName();

    @Resource
    private GdsSku2PriceMapper gdsSku2PriceMapper;

    @Resource
    private IGdsPriceTypeSV gdsPriceTypeSV;

    @Resource
    protected IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;

    @Resource
    protected IGdsInfoQuerySV gdsInfoQuerySV;

    @Resource
    private IGdsScoreExtSV gdsScoreExtSV;

    @Resource
    protected IGdsCatgCustDiscSV catgCustDiscSV;

    @Resource
    protected GdsGds2PropMapper gds2PropMapper;

    @Override
    public void saveGdsSkuPrice(GdsSku2PriceReqDTO sku2PriceReqDTO) throws BusinessException {
        paramNullCheck(sku2PriceReqDTO, "sku2PriceReqDTO");
        // 普通价格不再保存商品价格关系，直接在单品表操作
        if (sku2PriceReqDTO.getPriceTypeCode().equals(GdsConstants.GdsInfo.SKU_PRICE_TYPE_ORDINARY)) {
            return;
        }
        // 保存价格
        String priceTypeCode = getPriceTypeCode(sku2PriceReqDTO);
        sku2PriceReqDTO.setPriceTypeCode(priceTypeCode);
        IGdsSkuPiceStrategySV gdsSkuPiceStrategySV = getPriceStrategySV(priceTypeCode);
        Map<String, Object> params=null;
        if(sku2PriceReqDTO.getParams()!=null){
            params=sku2PriceReqDTO.getParams();
        }else{
            params=new HashMap<String, Object>();
        }
        if(!params.containsKey("skuId")){
            params.put("skuId", sku2PriceReqDTO.getSkuId());
        }
        if(!params.containsKey("gdsId")){
            params.put("gdsId", sku2PriceReqDTO.getGdsId());
        }
        Long priceId = gdsSkuPiceStrategySV.savePrice(sku2PriceReqDTO,params);
        // 保存价格关系
        GdsSku2Price gdsSku2Price = new GdsSku2Price();
        ObjectCopyUtil.copyObjValue(sku2PriceReqDTO, gdsSku2Price, null, false);
        gdsSku2Price.setStatus(GdsConstants.Commons.STATUS_VALID);
        gdsSku2Price.setCreateStaff(sku2PriceReqDTO.getStaff().getId());
        gdsSku2Price.setCreateTime(DateUtil.getSysDate());
        gdsSku2Price.setUpdateStaff(sku2PriceReqDTO.getStaff().getId());
        gdsSku2Price.setUpdateTime(DateUtil.getSysDate());
        gdsSku2Price.setPriceId(priceId);
        gdsSku2PriceMapper.insert(gdsSku2Price);

    }

    @Override
    public void editGdsSkuPrice(GdsSku2PriceReqDTO sku2PriceReqDTO) throws BusinessException {
        String priceTypeCode = getPriceTypeCode(sku2PriceReqDTO);
        IGdsSkuPiceStrategySV gdsSkuPiceStrategySV = getPriceStrategySV(priceTypeCode);
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("skuId", sku2PriceReqDTO.getSkuId());
        params.put("gdsId", sku2PriceReqDTO.getGdsId());
        gdsSkuPiceStrategySV.editPrice(sku2PriceReqDTO, params);
    }

    @Override
    public void delGdsSkuPrice(GdsSku2PriceReqDTO sku2PriceReqDTO) throws BusinessException {
        String priceTypeCode = getPriceTypeCode(sku2PriceReqDTO);
        IGdsSkuPiceStrategySV gdsSkuPiceStrategySV = getPriceStrategySV(priceTypeCode);
        gdsSkuPiceStrategySV.delPrice(sku2PriceReqDTO, sku2PriceReqDTO.getParams());
    }

    /**
     * 通过价格主键获取价格 TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.price.IGdsPriceSV#queryGdsSkuPriceByPK(com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceReqDTO)
     */
    public GdsPriceInfoResp queryGdsSkuPriceByPK(GdsSku2PriceReqDTO sku2PriceReqDTO) throws BusinessException {
        paramNullCheck(sku2PriceReqDTO, "sku2PriceReqDTO");
        String priceTypeCode = getPriceTypeCode(sku2PriceReqDTO);
        IGdsSkuPiceStrategySV gdsSkuPiceStrategySV = getPriceStrategySV(priceTypeCode);
        return gdsSkuPiceStrategySV.getPrice(sku2PriceReqDTO.getPriceId(), null);
    }

    /**
     * 
     * queryGdsSkuPriceList:(查询所有价格关系以及价格). <br/>
     * 
     * @author linwb3
     * @param sku2PriceReqDTO
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public List<GdsSku2PriceRespDTO> queryGdsSkuPriceList(GdsSku2PriceReqDTO sku2PriceReqDTO) throws BusinessException {
        List<GdsSku2Price> sku2Prices = queryGdsSkuPriceModelList(sku2PriceReqDTO);
        // 查询具体价格信息
        boolean ifPrice = sku2PriceReqDTO.getIfPrice();
        List<GdsSku2PriceRespDTO> skus = null;
        skus = queryPriceInfo(ifPrice, sku2Prices, skus);
        return skus;
    }

    @Override
    public List<GdsSku2Price> queryGdsSkuPriceModelList(GdsSku2PriceReqDTO sku2PriceReqDTO) {
        String status = sku2PriceReqDTO.getStatus();
        Long skuId = sku2PriceReqDTO.getSkuId();
        Long gdsId = sku2PriceReqDTO.getGdsId();
        if ((gdsId == null || gdsId.longValue() == 0) && skuId != null) {
            GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
            gdsSkuInfoReqDTO.setId(skuId);
            GdsSkuInfoRespDTO skuInfo = gdsSkuInfoQuerySV.querySkuInfoByOptions(gdsSkuInfoReqDTO);
            gdsId = skuInfo.getGdsId();
        }
        // 查询单品/商品 价格关系
        GdsSku2PriceCriteria sku2PriceCriteria = initQueryGdsOrSkuPriceCriteria(sku2PriceReqDTO, status, skuId, gdsId);
        List<GdsSku2Price> sku2Prices = gdsSku2PriceMapper.selectByExample(sku2PriceCriteria);
        return sku2Prices;
    }

    /**
     * 
     * caculatePrice:(价格计算服务). <br/>
     * 
     * @author linwb3
     * @param reqDto
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public Map<Long, CartItemPriceInfo> caculatePrice(ROrdCartsCommRequest reqDtos) throws BusinessException {
        Map<Long, CartItemPriceInfo> allCart = new HashMap<Long, CartItemPriceInfo>();
        // 获取来源。1.商城  2.APP 3.其它
        String source = reqDtos.getSource();
        
        if (reqDtos != null && CollectionUtils.isNotEmpty(reqDtos.getOrdCartsCommList())) {
            for (ROrdCartCommRequest reqDto : reqDtos.getOrdCartsCommList()) {
                List<ROrdCartItemCommRequest> ordCartItem = reqDto.getOrdCartItemCommList();
                // 按照商品分组
                Map<Long, List<ROrdCartItemCommRequest>> gdsGroup = new HashMap<Long, List<ROrdCartItemCommRequest>>();
                sortGds(ordCartItem, gdsGroup);
                Set<Long> gdsIds = gdsGroup.keySet();
                Long staffId = reqDto.getStaffId();
                for (Long gdsId : gdsIds) {
                    List<ROrdCartItemCommRequest> carts = gdsGroup.get(gdsId);
                    boolean ifDigitProduct = false;
                    // 判断当前商品是否是数字印刷版
                    if ("1".equals(carts.get(0).getPrnFlag())) {
                        ifDigitProduct = true;
                    }
                    GdsInfoReqDTO reqDTO = new GdsInfoReqDTO();
                    reqDTO.setId(gdsId);
                    GdsInfo gdsInfo = gdsInfoQuerySV.queryGdsInfoModel(reqDTO);
                    if (gdsInfo == null) {
                        throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210005);
                    }

                    // 阶梯价计算
                    if (GdsUtils.isEqualsValid(gdsInfo.getIfLadderPrice())) {
                        Map<String, Object> params = new HashMap<String, Object>();
                        long count = 0;
                        for (ROrdCartItemCommRequest cart : carts) {
                            count = count + cart.getOrderAmount();
                        }
                        IGdsSkuPiceStrategySV gdsSkuPiceStrategySV = getPriceStrategySV(GdsConstants.GdsInfo.SKU_PRICE_TYPE_LADDER);
                        params.put("gdsId", gdsId);
                        params.put("amount", count);
                        Long price = gdsSkuPiceStrategySV.calculatePrice(params);

                        for (ROrdCartItemCommRequest rOrdCartItemCommRequest : carts) {
                            CartItemPriceInfo cartItemPriceInfo = new CartItemPriceInfo();
                            cartItemPriceInfo.setBasePrice(price);
                            cartItemPriceInfo.setBuyPrice(price);
                            cartItemPriceInfo.setItemId(rOrdCartItemCommRequest.getId());
                            cartItemPriceInfo.setGdsId(rOrdCartItemCommRequest.getGdsId());
                            cartItemPriceInfo.setSkuId(rOrdCartItemCommRequest.getSkuId());
                            allCart.put(rOrdCartItemCommRequest.getSkuId(), cartItemPriceInfo);
                        }
                        // 积分商品计算
                    } else if (GdsUtils.isEqualsValid(gdsInfo.getIfScoreGds())) {

                        for (ROrdCartItemCommRequest rOrdCartItemCommRequest : carts) {
                            Map<String, Object> params = new HashMap<String, Object>();
                            params.put("gdsId", rOrdCartItemCommRequest.getGdsId());
                            params.put("amount", rOrdCartItemCommRequest.getOrderAmount());
                            params.put("gdsScorePriceId", rOrdCartItemCommRequest.getScoreTypeId());
                            CartItemPriceInfo itemPriceInfo = getScoreGdsPrice(params);

                            CartItemPriceInfo cartItemPriceInfo = new CartItemPriceInfo();
                            cartItemPriceInfo.setBasePrice(itemPriceInfo.getBasePrice());
                            cartItemPriceInfo.setBuyPrice(itemPriceInfo.getBuyPrice());
                            cartItemPriceInfo.setScore(itemPriceInfo.getScore());
                            cartItemPriceInfo.setItemId(rOrdCartItemCommRequest.getId());
                            cartItemPriceInfo.setGdsId(rOrdCartItemCommRequest.getGdsId());
                            cartItemPriceInfo.setSkuId(rOrdCartItemCommRequest.getSkuId());
                            allCart.put(rOrdCartItemCommRequest.getSkuId(), cartItemPriceInfo);
                        }

                    } else {
                        // 普通价格计算
                        List<GdsPriceTypeRespDTO> gdsPriceTypeRespDTOs = queryAllPriceType();
                        for (ROrdCartItemCommRequest rOrdCartItemCommRequest : carts) {
                            Map<String, Object> params = new HashMap<String, Object>();
                            params.put("staffId", staffId);
                            params.put("skuId", rOrdCartItemCommRequest.getSkuId());
                            params.put("gdsId", rOrdCartItemCommRequest.getGdsId());
                            params.put("amount", rOrdCartItemCommRequest.getOrderAmount());
                            Long price = 0L;
                            if (ifDigitProduct) {// 如果是数字印刷版,取属性表的数字版价格
                                GdsGds2PropCriteria example = new GdsGds2PropCriteria();
                                GdsGds2PropCriteria.Criteria criteria = example.createCriteria();
                                criteria.andPropIdEqualTo(GdsConstants.GdsProp.GDS_DIGITAL_PRODUCT_PRICE_PROP_ID);
                                criteria.andGdsIdEqualTo(gdsId);
                                criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
                                List<GdsGds2Prop> gdsGds2Props = gds2PropMapper.selectByExample(example);
                                if (gdsGds2Props != null && gdsGds2Props.size() != 0) {
                                    String propStr = gdsGds2Props.get(0).getPropValue();
                                    if (propStr == null) {
                                        LogUtil.error(MOUDLE, "数字印刷版的价格没配置");
                                        ;
                                    }
                                    price = Long.parseLong(propStr);

                                }

                            } else {
                                price = getOptimalPrice(gdsPriceTypeRespDTOs, params);
                            }
                            Long buyPrice = 0L;
                            CalCatgCustDiscReqDTO calCatgCustDiscReqDTO = new CalCatgCustDiscReqDTO();
                            calCatgCustDiscReqDTO.setGdsId(gdsInfo.getId());
                            calCatgCustDiscReqDTO.setCustNo(staffId);
                            BigDecimal discount = catgCustDiscSV.calCatgCustDisc(calCatgCustDiscReqDTO);
                            // 如果是数字印刷版，则取原始价，不参加折扣
                            if (ifDigitProduct) {
                                buyPrice = price;
                            } else {
                                buyPrice = GdsUtils.getDiscountPrice(price, discount);
                            }
                            if (buyPrice == 0) {
                                buyPrice = price;
                            }
                           
                            if(isFromApp(source)){
                                GdsSkuInfoReqDTO skuInfoQuery = new GdsSkuInfoReqDTO();
                                skuInfoQuery.setGdsId(rOrdCartItemCommRequest.getGdsId());
                                skuInfoQuery.setId(rOrdCartItemCommRequest.getSkuId());
                                GdsSkuInfoRespDTO skuInfo = gdsSkuInfoQuerySV.querySkuInfoByOptions(skuInfoQuery, GdsOption.SkuQueryOption.BASIC);
                                // 获取手机专享价。
                                Long appSpecPrice = (null != skuInfo ? skuInfo.getAppSpecPrice() : null); 
                                if(null != appSpecPrice && !appSpecPrice.equals(0L)){
                                    price = appSpecPrice;
                                    buyPrice = price;
                                }
                            }

                            CartItemPriceInfo cartItemPriceInfo = new CartItemPriceInfo();
                            cartItemPriceInfo.setBasePrice(price);
                            cartItemPriceInfo.setBuyPrice(buyPrice);
                            cartItemPriceInfo.setItemId(rOrdCartItemCommRequest.getId());
                            cartItemPriceInfo.setGdsId(rOrdCartItemCommRequest.getGdsId());
                            cartItemPriceInfo.setSkuId(rOrdCartItemCommRequest.getSkuId());
                            allCart.put(rOrdCartItemCommRequest.getSkuId(), cartItemPriceInfo);
                        }
                    }
                }
            }
        }
        return allCart;
    }

    /**
     * 
     * validatePrice:(价格校验服务). <br/>
     * 
     * @author linwb3
     * @param reqDto
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public ROrdCartsChkResponse validatePrice(ROrdCartsCommRequest reqDtos) throws BusinessException {
        ROrdCartsChkResponse cartsChkResponse = null;
        if (reqDtos != null && CollectionUtils.isNotEmpty(reqDtos.getOrdCartsCommList())) {
            for (ROrdCartCommRequest reqDto : reqDtos.getOrdCartsCommList()) {
                List<ROrdCartItemCommRequest> ordCartItem = reqDto.getOrdCartItemCommList();
                // 按照商品分组
                Map<Long, List<ROrdCartItemCommRequest>> gdsGroup = new HashMap<Long, List<ROrdCartItemCommRequest>>();
                sortGds(ordCartItem, gdsGroup);
                Set<Long> gdsIds = gdsGroup.keySet();
                Long staffId = reqDto.getStaffId();
                for (Long gdsId : gdsIds) {
                    List<ROrdCartItemCommRequest> carts = gdsGroup.get(gdsId);
                    GdsInfoReqDTO reqDTO = new GdsInfoReqDTO();
                    reqDTO.setId(gdsId);
                    GdsInfo gdsInfo = gdsInfoQuerySV.queryGdsInfoModel(reqDTO);
                    if (gdsInfo == null) {
                        throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210005);
                    }

                    if (GdsUtils.isEqualsValid(gdsInfo.getIfLadderPrice())) {
                        Map<String, Object> params = new HashMap<String, Object>();
                        long count = 0;
                        for (ROrdCartItemCommRequest cart : carts) {
                            count = count + cart.getOrderAmount();
                        }
                        IGdsSkuPiceStrategySV gdsSkuPiceStrategySV = getPriceStrategySV(GdsConstants.GdsInfo.SKU_PRICE_TYPE_LADDER);

                        params.put("gdsId", gdsId);
                        params.put("amount", count);
                        cartsChkResponse = gdsSkuPiceStrategySV.validatePrice(params);
                        if (!cartsChkResponse.isStatus()) {
                            return cartsChkResponse;
                        }
                    } else {
                        List<GdsPriceTypeRespDTO> gdsPriceTypeRespDTOs = queryAllPriceType();
                        for (ROrdCartItemCommRequest rOrdCartItemCommRequest : carts) {
                            Map<String, Object> params = new HashMap<String, Object>();
                            params.put("staffId", staffId);
                            params.put("skuId", rOrdCartItemCommRequest.getSkuId());
                            params.put("gdsId", rOrdCartItemCommRequest.getGdsId());
                            params.put("amount", rOrdCartItemCommRequest.getOrderAmount());
                            cartsChkResponse = getOptimalPriceValidate(gdsPriceTypeRespDTOs, params);
                            if (!cartsChkResponse.isStatus()) {
                                return cartsChkResponse;
                            }
                        }
                    }
                }
            }
        }
        return cartsChkResponse;
    }

    /**
     * 
     * caculatePrice:(价格计算). <br/>
     * 
     * @author linwb3
     * @param reqDto
     * @throws BusinessException
     * @since JDK 1.6
     */
    public Long caculatePrice(Map<String, Object> params) throws BusinessException {
        List<GdsPriceTypeRespDTO> priceTypes = queryAllPriceType();
        Long gdsId = (Long) params.get("gdsId");

        String IfLadderPrice = (String) params.get("ifLadderPrice");
        if (StringUtil.isBlank(IfLadderPrice)) {
            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
            gdsInfoReqDTO.setId(gdsId);
            GdsInfo gdsInfo = gdsInfoQuerySV.queryGdsInfoModel(gdsInfoReqDTO);
            IfLadderPrice = gdsInfo.getIfLadderPrice();
        }
        if (GdsUtils.isEqualsValid(IfLadderPrice)) {
            IGdsSkuPiceStrategySV gdsSkuPiceStrategySV = getPriceStrategySV(GdsConstants.GdsInfo.SKU_PRICE_TYPE_LADDER);
            Long price = gdsSkuPiceStrategySV.calculatePrice(params);
            return price;
        } else {
            Long price = getOptimalPrice(priceTypes, params);
            return price;
        }
    }

    /**
     * 
     * caculatePrice:(价格计算服务). <br/>
     * 
     * @author linwb3
     * @param reqDto
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public Long getDeaultPrice(GdsSkuInfoReqDTO skuInfoReqDTO) throws BusinessException {
        Long skuId = skuInfoReqDTO.getId();
        Long gdsId = skuInfoReqDTO.getGdsId();
        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setId(gdsId);
        GdsInfo gdsInfo = gdsInfoQuerySV.queryGdsInfoModel(gdsInfoReqDTO);
        if (gdsInfo == null) {
            throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210005);
        }

        if (GdsUtils.isEqualsValid(gdsInfo.getIfLadderPrice())) {
            Map<String, Object> params = new HashMap<String, Object>();
            long count = 0;
            IGdsSkuPiceStrategySV gdsSkuPiceStrategySV = getPriceStrategySV(GdsConstants.GdsInfo.SKU_PRICE_TYPE_LADDER);
            params.put("gdsId", gdsId);
            params.put("total", count);
            Long price = gdsSkuPiceStrategySV.calculatePrice(params);
            return price;
        } else {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("skuId", skuId);
            params.put("gdsId", gdsId);
            params.put("amount", 1);
            Long price = getOptimalPrice(null, params);
            return price;
        }
    }

    /**
     * 
     * queryAllPriceType:(获取所有价格类型). <br/>
     * 
     * @author linwb3
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public List<GdsPriceTypeRespDTO> queryAllPriceType() throws BusinessException {
        return GdsUtils.doConvert(gdsPriceTypeSV.queryAllPriceType(new String[] { GdsConstants.GdsInfo.SKU_PRICE_TYPE_LADDER }, GdsConstants.Commons.STATUS_VALID), GdsPriceTypeRespDTO.class);
    }

    /**
     * 
     * delAllPrice:(删除单品或者商品所有价格). <br/>
     * 
     * @author linwb3
     * @param sku2PriceReqDTO
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public void delAllPrice(GdsSku2PriceReqDTO sku2PriceReqDTO) throws BusinessException {
        sku2PriceReqDTO.setIfPrice(false);
        List<GdsSku2PriceRespDTO> gdssku2prices = queryGdsSkuPriceList(sku2PriceReqDTO);
        delGdsOrSku2PriceInfo(sku2PriceReqDTO);
        if (CollectionUtils.isNotEmpty(gdssku2prices)) {
            for (GdsSku2PriceRespDTO gdsSku2PriceRespDTO : gdssku2prices) {
                GdsSku2PriceReqDTO gdsSku2PriceReqDTO = new GdsSku2PriceReqDTO();
                gdsSku2PriceReqDTO.setPriceTypeId(gdsSku2PriceRespDTO.getPriceTypeId());
                String priceTypeCode = getPriceTypeCode(gdsSku2PriceReqDTO);
                IGdsSkuPiceStrategySV gdsSkuPiceStrategySV = getPriceStrategySV(priceTypeCode);
                GdsSku2PriceReqDTO delReq = new GdsSku2PriceReqDTO();
                delReq.setPriceId(gdsSku2PriceRespDTO.getPriceId());
                
                
                Map<String, Object> params=new HashMap<String, Object>();
                params.put("skuId", gdsSku2PriceRespDTO.getSkuId());
                params.put("gdsId", gdsSku2PriceRespDTO.getGdsId());
                gdsSkuPiceStrategySV.delPrice(delReq, params);
            }
        }
    }

    /**
     * 
     * getPriceTypeCode:(获取价格类型编码). <br/>
     * 
     * @author linwb3
     * @param sku2PriceReqDTO
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    private String getPriceTypeCode(GdsSku2PriceReqDTO sku2PriceReqDTO) throws BusinessException {
        Long priceTypeId = sku2PriceReqDTO.getPriceTypeId();
        String priceTypeCode = null;
        if (priceTypeId != null) {
            GdsPriceType gdsPriceType = gdsPriceTypeSV.getPriceType(priceTypeId);
            if (gdsPriceType != null) {
                sku2PriceReqDTO.setPriceTypeId(gdsPriceType.getId());
                priceTypeCode = gdsPriceType.getPriceTypeCode();
            }
        }

        if (StringUtil.isNotBlank(sku2PriceReqDTO.getPriceTypeCode()) && StringUtil.isBlank(priceTypeCode)) {
            GdsPriceType gdsPriceType = gdsPriceTypeSV.getPriceType(sku2PriceReqDTO.getPriceTypeCode());
            if (gdsPriceType != null) {
                sku2PriceReqDTO.setPriceTypeId(gdsPriceType.getId());
                priceTypeCode = gdsPriceType.getPriceTypeCode();
            }
        }
        paramNullCheck(priceTypeCode, "priceTypeCode is null");
        return priceTypeCode;
    }

    private void initGdsCriteria(String status, Long gdsId, GdsSku2PriceCriteria.Criteria skuCriteria) {
        skuCriteria.andGdsIdEqualTo(gdsId);
        skuCriteria.andRTypeEqualTo(GdsConstants.GdsPrice.GDS_PRICE_RTYPE_GDS);
        if (StringUtil.isNotBlank(status)) {
            skuCriteria.andStatusEqualTo(status);
        } else {
            skuCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        }
    }

    private void initSkuCriteria(String status, Long gdsId, Long skuId, GdsSku2PriceCriteria.Criteria skuCriteria) {
        skuCriteria.andSkuIdEqualTo(skuId);
        skuCriteria.andGdsIdEqualTo(gdsId);
        skuCriteria.andRTypeEqualTo(GdsConstants.GdsPrice.GDS_PRICE_RTYPE_SKU);
        if (StringUtil.isNotBlank(status)) {
            skuCriteria.andStatusEqualTo(status);
        } else {
            skuCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        }
    }

    /**
     * 获取 getOptimalPrice:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author linwb3
     * @param gdsPriceTypeRespDTOs
     * @param params
     * @return
     * @since JDK 1.6
     */
    protected Long getOptimalPrice(List<GdsPriceTypeRespDTO> gdsPriceTypeRespDTOs, Map<String, Object> params) {

        if (CollectionUtils.isNotEmpty(gdsPriceTypeRespDTOs)) {
            IGdsSkuPiceStrategySV gdsSkuPiceStrategySV = getOptimalStrategySV(gdsPriceTypeRespDTOs);
            if (gdsSkuPiceStrategySV != null) {
                Long price = gdsSkuPiceStrategySV.calculatePrice(params);
                if (price == null || price.longValue() == 0L) {
                    return getOptimalPrice(gdsPriceTypeRespDTOs, params);
                } else {
                    return price;
                }
            } else {
                gdsSkuPiceStrategySV = getDefaultPriceStrategySV();
                return gdsSkuPiceStrategySV.calculatePrice(params);
            }
        } else {
            IGdsSkuPiceStrategySV gdsSkuPiceStrategySV = getDefaultPriceStrategySV();
            return gdsSkuPiceStrategySV.calculatePrice(params);
        }
    }

    protected CartItemPriceInfo getScoreGdsPrice(Map<String, Object> params) throws BusinessException {
        try {
            Long gdsScorePriceId = (Long) params.get("gdsScorePriceId");
            Long gdsId = (Long) params.get("gdsId");
            Long amount = (Long) params.get("amount");
            GdsScoreExtReqDTO gdsScoreExtReqDTO = new GdsScoreExtReqDTO();
            gdsScoreExtReqDTO.setId(gdsScorePriceId);
            gdsScoreExtReqDTO.setGdsId(gdsId);
            GdsScoreExtRespDTO extRespDTO = gdsScoreExtSV.queryGdsScoreExtById(gdsScoreExtReqDTO);
            Long score = 0L;
            Long price = 0L;
            if (GdsConstants.GdsScorePrice.SCORE_TYPE_SCORE_PURE.equals(extRespDTO.getScoreType())) {
                score = extRespDTO.getScore();
            } else if (GdsConstants.GdsScorePrice.SCORE_TYPE_SCORE_MIX.equals(extRespDTO.getScoreType())) {
                score = extRespDTO.getScore();
                price = extRespDTO.getPrice();
            } else if (GdsConstants.GdsScorePrice.SCORE_TYPE_CASH_PURE.equals(extRespDTO.getScoreType())) {
                price = extRespDTO.getPrice();
            } else {

                throw new BusinessException(GdsErrorConstants.GdsScoreExt.ERROR_GOODS_SCORE_EXT_240505);
            }
            CartItemPriceInfo cartItemPriceInfo = new CartItemPriceInfo();
            cartItemPriceInfo.setBasePrice(price);
            cartItemPriceInfo.setBuyPrice(price);
            cartItemPriceInfo.setScore(score);
            return cartItemPriceInfo;
        } catch (Exception e) {
            // TODO: handle exception
            throw new BusinessException(GdsErrorConstants.GdsScoreExt.ERROR_GOODS_SCORE_EXT_240506);
        }
    }

    /**
     * 获取 getOptimalPrice:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author linwb3
     * @param gdsPriceTypeRespDTOs
     * @param params
     * @return
     * @since JDK 1.6
     */
    private ROrdCartsChkResponse getOptimalPriceValidate(List<GdsPriceTypeRespDTO> gdsPriceTypeRespDTOs, Map<String, Object> params) {
        if (CollectionUtils.isNotEmpty(gdsPriceTypeRespDTOs)) {
            IGdsSkuPiceStrategySV gdsSkuPiceStrategySV = getOptimalStrategySV(gdsPriceTypeRespDTOs);
            if (gdsSkuPiceStrategySV != null) {
                Long price = gdsSkuPiceStrategySV.calculatePrice(params);
                if (price == null || price.longValue() == 0L) {
                    return getOptimalPriceValidate(gdsPriceTypeRespDTOs, params);
                } else {
                    return gdsSkuPiceStrategySV.validatePrice(params);
                }
            } else {
                gdsSkuPiceStrategySV = getDefaultPriceStrategySV();
                return gdsSkuPiceStrategySV.validatePrice(params);
            }
        } else {
            IGdsSkuPiceStrategySV gdsSkuPiceStrategySV = getDefaultPriceStrategySV();
            return gdsSkuPiceStrategySV.validatePrice(params);
        }
    }

    /**
     * 
     * getOptimalStrategySV:(获取优先级最高的价格策略服务 以小为大). <br/>
     * 
     * @author linwb3
     * @param gdsPriceTypeRespDTOs
     * @return
     * @since JDK 1.6
     */
    private IGdsSkuPiceStrategySV getOptimalStrategySV(List<GdsPriceTypeRespDTO> gdsPriceTypeRespDTOs) {
        IGdsSkuPiceStrategySV gdsSkuPiceStrategySV = null;
        if (CollectionUtils.isNotEmpty(gdsPriceTypeRespDTOs)) {
            GdsPriceTypeRespDTO gdsPriceTypeRespDTO = null;
            int index = 0;
            for (int i = 0; i < gdsPriceTypeRespDTOs.size(); i++) {
                if (i == 0) {
                    gdsPriceTypeRespDTO = gdsPriceTypeRespDTOs.get(i);
                    index = i;
                } else {
                    Integer priority = gdsPriceTypeRespDTOs.get(i).getPriority();
                    if (priority < gdsPriceTypeRespDTO.getPriority()) {
                        gdsPriceTypeRespDTO = gdsPriceTypeRespDTOs.get(i);
                        index = i;
                    }
                }
            }
            gdsPriceTypeRespDTOs.remove(index);
            try {
                gdsSkuPiceStrategySV = getPriceStrategySV(gdsPriceTypeRespDTO.getPriceTypeCode());
            } catch (BusinessException e) {
                LogUtil.info(MODULE, "获取不到价格策略服务", e);
                return getOptimalStrategySV(gdsPriceTypeRespDTOs);
            }
        }

        return gdsSkuPiceStrategySV;
    }

    /**
     * 
     * getDefaultPriceStrategySV:(获取默认的价格策略). <br/>
     * 
     * @author linwb3
     * @return
     * @since JDK 1.6
     */
    private IGdsSkuPiceStrategySV getDefaultPriceStrategySV() {
        return getPriceStrategySV(GdsConstants.GdsInfo.SKU_PRICE_TYPE_ORDINARY);
    }

    /**
     * 
     * getPriceStrategySV:(获取价格策略实现SV). <br/>
     * 
     * @author linwb3
     * @param priceTypeCode
     * @return
     * @since JDK 1.6
     */
    protected IGdsSkuPiceStrategySV getPriceStrategySV(String priceTypeCode) throws BusinessException {
        String strategyName = priceTypeCode + "StrategySV";
        checkStrategyName(strategyName);

        IGdsSkuPiceStrategySV gdsSkuPiceStrategySV = null;
        try {
            gdsSkuPiceStrategySV = EcpFrameContextHolder.getBean(strategyName, IGdsSkuPiceStrategySV.class);
        } catch (Exception e) {
            LogUtil.info(MODULE, "获取不到价格策略服务", e);
            throw new BusinessException(GdsErrorConstants.GdsPrice.ERROR_GOODS_PRICE_210708, new String[] {});
        }
        return gdsSkuPiceStrategySV;

    }

    /**
     * 
     * delGdsOrSku2PriceInfo:(删除商品/单品 价格关系信息). <br/>
     * 
     * @author linwb3
     * @param gdsSku2PriceReqDTO
     * @since JDK 1.6
     */
    private void delGdsOrSku2PriceInfo(GdsSku2PriceReqDTO gdsSku2PriceReqDTO) {

        GdsSku2PriceCriteria sku2PriceCriteria = new GdsSku2PriceCriteria();
        GdsSku2PriceCriteria.Criteria criteria = sku2PriceCriteria.createCriteria();

        if (GdsConstants.GdsPrice.GDS_PRICE_RTYPE_GDS.equals(gdsSku2PriceReqDTO.getrType())) {
            criteria.andRTypeEqualTo(GdsConstants.GdsPrice.GDS_PRICE_RTYPE_GDS);
            criteria.andGdsIdEqualTo(gdsSku2PriceReqDTO.getGdsId());

        } else {
            criteria.andRTypeEqualTo(GdsConstants.GdsPrice.GDS_PRICE_RTYPE_SKU);
            criteria.andSkuIdEqualTo(gdsSku2PriceReqDTO.getSkuId());
        }

        GdsSku2Price sku2Price = new GdsSku2Price();
        sku2Price.setStatus(GdsConstants.Commons.STATUS_INVALID);
        gdsSku2PriceMapper.updateByExampleSelective(sku2Price, sku2PriceCriteria);

    }

    /**
     * 
     * sortGds:(对购物车明细按照商品名称分组). <br/>
     * 
     * @author linwb3
     * @param ordCartItem
     * @param gdsGroup
     * @since JDK 1.6
     */
    protected void sortGds(List<ROrdCartItemCommRequest> ordCartItem, Map<Long, List<ROrdCartItemCommRequest>> gdsGroup) {
        for (ROrdCartItemCommRequest rOrdCartItemCommRequest : ordCartItem) {
            Long gdsId = rOrdCartItemCommRequest.getGdsId();
            if (gdsGroup.containsKey(rOrdCartItemCommRequest.getGdsId())) {
                List<ROrdCartItemCommRequest> arr = gdsGroup.get(gdsId);
                arr.add(rOrdCartItemCommRequest);
            } else {
                List<ROrdCartItemCommRequest> arr = new ArrayList<ROrdCartItemCommRequest>();
                arr.add(rOrdCartItemCommRequest);
                gdsGroup.put(gdsId, arr);
            }
        }
    }

    /**
     * 
     * checkStrategyName:(检查策略名称，如果策略不存在，则抛出异常). <br/>
     * 
     * @author linwb3
     * @param strategyName
     * @since JDK 1.6
     */
    private void checkStrategyName(String strategyName) {
        if (StringUtil.isEmpty(strategyName) || "nullStrategySV".equals(strategyName)) {
            throw new BusinessException(GdsErrorConstants.GdsPrice.ERROR_GOODS_PRICE_210706);
        }
    }

    /**
     * 
     * initQueryGdsOrSkuPriceCriteria:(初始化查询单品/商品价格关系查询条件). <br/>
     * 
     * @author linwb3
     * @param sku2PriceReqDTO
     * @param status
     * @param skuId
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    private GdsSku2PriceCriteria initQueryGdsOrSkuPriceCriteria(GdsSku2PriceReqDTO sku2PriceReqDTO, String status, Long skuId, Long gdsId) {
        GdsSku2PriceCriteria sku2PriceCriteria = new GdsSku2PriceCriteria();
        GdsSku2PriceCriteria.Criteria skuCriteria = sku2PriceCriteria.createCriteria();
        if (GdsConstants.GdsPrice.GDS_PRICE_RTYPE_GDS.equals(sku2PriceReqDTO.getrType())) {
            initGdsCriteria(status, gdsId, skuCriteria);
        } else if (GdsConstants.GdsPrice.GDS_PRICE_RTYPE_SKU.equals(sku2PriceReqDTO.getrType())) {
            initSkuCriteria(status, gdsId, skuId, skuCriteria);
        } else {
            initGdsCriteria(status, gdsId, skuCriteria);
            if (skuId != null) {
                GdsSku2PriceCriteria.Criteria criteria = sku2PriceCriteria.createCriteria();
                initSkuCriteria(status, gdsId, skuId, criteria);
                sku2PriceCriteria.or(criteria);
            }
        }
        return sku2PriceCriteria;
    }

    /**
     * 
     * queryPriceInfo:(查询具体的价格信息). <br/>
     * 
     * @author linwb3
     * @param ifPrice
     *            是否查询具体的价格信息，如果为false，则只返回价格关系
     * @param sku2Prices
     * @param skus
     * @return
     * @since JDK 1.6
     */
    private List<GdsSku2PriceRespDTO> queryPriceInfo(boolean ifPrice, List<GdsSku2Price> sku2Prices, List<GdsSku2PriceRespDTO> skus) throws BusinessException {
        if (CollectionUtils.isNotEmpty(sku2Prices)) {
            skus = GdsUtils.doConvert(sku2Prices, GdsSku2PriceRespDTO.class);
            if (!ifPrice) {
                return skus;
            }
            for (GdsSku2PriceRespDTO gdsSku2PriceRespDTO : skus) {
                GdsSku2PriceReqDTO gdsSku2PriceReqDTO = new GdsSku2PriceReqDTO();
                gdsSku2PriceReqDTO.setPriceTypeId(gdsSku2PriceRespDTO.getPriceTypeId());
                String priceTypeCode = getPriceTypeCode(gdsSku2PriceReqDTO);
                IGdsSkuPiceStrategySV gdsSkuPiceStrategySV = getPriceStrategySV(priceTypeCode);
                Map<String, Object> params=new HashMap<String, Object>();
                params.put("skuId", gdsSku2PriceRespDTO.getSkuId());
                params.put("gdsId", gdsSku2PriceRespDTO.getGdsId());
                GdsPriceInfoResp price = gdsSkuPiceStrategySV.getPrice(gdsSku2PriceRespDTO.getPriceId(), params);
                gdsSku2PriceRespDTO.setPrice(price);
            }
        }
        return skus;
    }

    @Override
    public GdsPriceCalRespDTO caculatePrice(GdsPriceCalReqDTO reqDto) throws BusinessException {

        GdsPriceCalRespDTO resp = new GdsPriceCalRespDTO();

        Long staffId = reqDto.getStaff().getId();
        Long gdsId = reqDto.getGdsId();
        Long skuId = reqDto.getSkuId();
        Long amount = reqDto.getAmount();
        String prnFlag = reqDto.getPrnFlag();
        String scoreTypeId = reqDto.getScoreTypeId();

        boolean ifDigitProduct = false;
        // 判断当前商品是否是数字印刷版
        if ("1".equals(prnFlag)) {
            ifDigitProduct = true;
        }

        GdsInfoReqDTO reqDTO = new GdsInfoReqDTO();
        reqDTO.setId(gdsId);
        GdsInfo gdsInfo = gdsInfoQuerySV.queryGdsInfoModel(reqDTO);
        if (gdsInfo == null) {
            throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210005);
        }

        // 阶梯价计算
        if (GdsUtils.isEqualsValid(gdsInfo.getIfLadderPrice())) {
            Map<String, Object> params = new HashMap<String, Object>();
            long count = amount;
            IGdsSkuPiceStrategySV gdsSkuPiceStrategySV = getPriceStrategySV(GdsConstants.GdsInfo.SKU_PRICE_TYPE_LADDER);
            params.put("gdsId", gdsId);
            params.put("amount", count);
            Long price = gdsSkuPiceStrategySV.calculatePrice(params);
            resp.setBasePrice(price);
            resp.setBuyPrice(price);

            // 积分商品计算
        } else if (GdsUtils.isEqualsValid(gdsInfo.getIfScoreGds())) {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("gdsId", gdsId);
            params.put("amount", amount);
            params.put("gdsScorePriceId", scoreTypeId);
            CartItemPriceInfo itemPriceInfo = getScoreGdsPrice(params);

            resp.setBasePrice(itemPriceInfo.getBasePrice());
            resp.setBuyPrice(itemPriceInfo.getBuyPrice());

        } else {
            // 普通价格计算
            List<GdsPriceTypeRespDTO> gdsPriceTypeRespDTOs = queryAllPriceType();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("staffId", staffId);
            params.put("skuId", skuId);
            params.put("gdsId", gdsId);
            params.put("amount", amount);
            Long price = 0L;
            if (ifDigitProduct) {// 如果是数字印刷版,取属性表的数字版价格
                GdsGds2PropCriteria example = new GdsGds2PropCriteria();
                GdsGds2PropCriteria.Criteria criteria = example.createCriteria();
                criteria.andPropIdEqualTo(GdsConstants.GdsProp.GDS_DIGITAL_PRODUCT_PRICE_PROP_ID);
                criteria.andGdsIdEqualTo(gdsId);
                criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
                List<GdsGds2Prop> gdsGds2Props = gds2PropMapper.selectByExample(example);
                if (gdsGds2Props != null && gdsGds2Props.size() != 0) {
                    String propStr = gdsGds2Props.get(0).getPropValue();
                    if (propStr == null) {
                        LogUtil.error(MOUDLE, "数字印刷版的价格没配置");
                        ;
                    }
                    price = Long.parseLong(propStr);
                }

            } else {
                price = getOptimalPrice(gdsPriceTypeRespDTOs, params);
            }
            Long buyPrice = 0L;
            CalCatgCustDiscReqDTO calCatgCustDiscReqDTO = new CalCatgCustDiscReqDTO();
            calCatgCustDiscReqDTO.setGdsId(gdsInfo.getId());
            calCatgCustDiscReqDTO.setCustNo(staffId);
            BigDecimal discount = catgCustDiscSV.calCatgCustDisc(calCatgCustDiscReqDTO);
            // 如果是数字印刷版，则取原始价，不参加折扣
            if (ifDigitProduct) {
                buyPrice = price;
            } else {
                buyPrice = GdsUtils.getDiscountPrice(price, discount);
            }
            if (buyPrice == 0) {
                buyPrice = price;
            }
            if(isFromApp(reqDto.getFromSource())){
                GdsSkuInfoReqDTO skuInfoQuery = new GdsSkuInfoReqDTO();
                skuInfoQuery.setGdsId(reqDto.getGdsId());
                skuInfoQuery.setId(reqDto.getSkuId());
                GdsSkuInfoRespDTO skuInfo = gdsSkuInfoQuerySV.querySkuInfoByOptions(skuInfoQuery, GdsOption.SkuQueryOption.BASIC);
                // 获取手机专享价。
                Long appSpecPrice = (null != skuInfo ? skuInfo.getAppSpecPrice() : null); 
                if(null != appSpecPrice && !appSpecPrice.equals(0L)){
                    price = appSpecPrice;
                    buyPrice = price;
                }
            }
            resp.setBasePrice(price);
            resp.setBuyPrice(buyPrice);
        }

        return null;
    }

    
    /**
     * 
     * isFromApp:来源是否是手机端. <br/> 
     * 
     * @author liyong7
     * @param source
     * @return 
     * @since JDK 1.6
     */
    private boolean isFromApp(String source){
          return CommonConstants.SOURCE.SOURCE_APP.equals(source) || CommonConstants.SOURCE.SOURCE_OTH.equals(source);        
    }
}
