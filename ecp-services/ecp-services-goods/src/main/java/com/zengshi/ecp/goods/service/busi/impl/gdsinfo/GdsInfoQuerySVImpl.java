package com.zengshi.ecp.goods.service.busi.impl.gdsinfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsInfoMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsVerifyShopIdxMapper;
import com.zengshi.ecp.goods.dao.model.GdsGds2Prop;
import com.zengshi.ecp.goods.dao.model.GdsInfo;
import com.zengshi.ecp.goods.dao.model.GdsInfoCriteria;
import com.zengshi.ecp.goods.dao.model.GdsInfoShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsSku2Prop;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoGdsIdx;
import com.zengshi.ecp.goods.dao.model.GdsType;
import com.zengshi.ecp.goods.dao.model.GdsVerifyShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsVerifyShopIdxCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsVerifyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsVerifyRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.HomePageGdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.HomePageGdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsInfoShopIdxRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSkuInfoGdsIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2MediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2PropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceInfoResp;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsScoreExtSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsShipTempSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoQueryIDXSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsInfo2CatgSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsInfo2MediaSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsInfo2PropSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsSkuInfo2PropSV;
import com.zengshi.ecp.goods.service.busi.interfaces.price.IGdsPriceSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV;
import com.zengshi.ecp.order.dubbo.dto.RSalesChartRequest;
import com.zengshi.ecp.order.dubbo.dto.RSalesChartResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV;
import com.zengshi.ecp.server.auth.DataAuthType;
import com.zengshi.ecp.server.auth.DataAuthValid;
import com.zengshi.ecp.server.auth.DataObject;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthStaffRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.distribute.DistributeRuleAssist;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品查询服务<br>
 * Date:2015年8月29日下午5:41:28 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsInfoQuerySVImpl extends AbstractSVImpl implements IGdsInfoQuerySV {

    @Resource
    private GdsInfoMapper gdsInfoMapper;

    @Resource
    private GdsVerifyShopIdxMapper gdsVerifyShopIdxMapper;

    @Resource
    private IGdsCategorySV gdsCategorySV;

    @Resource
    private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;

    @Autowired(required = false)
    private IGdsPriceSV gdsPriceSV;

    @Resource
    private IGdsInfoQueryIDXSV gdsInfoQueryIDXSV;

    @Autowired(required = false)
    private IGdsShipTempSV gdsShipTempSV;
    
    @Resource
    private IGdsTypeSV gdsTypeSV;

    @Resource
    private IGdsScoreExtSV gdsScoreExtSV;

    @Resource
    private IGdsInfoQuerySV gdsInfoQuerySV;

    @Resource
    private IReportGoodPayedRSV reportGoodPayedRSV;

    @Resource
    private IAuthStaffRSV authStaffRSV;

    @Resource
    private IGdsInfo2PropSV gdsInfo2PropSV;

    @Resource
    private IGdsInfo2MediaSV gdsInfo2MediaSV;

    @Resource
    private IGdsInfo2CatgSV gdsInfo2CatgSV;

    @Resource
    private IGdsSkuInfo2PropSV gdsSkuInfo2PropSV;
    
    @Resource
    private IGdsTypeSV iGdsTypeSV;

    /**
     * 
     * queryGdsInfo:(通过复合条件查询商品信息). <br/>
     * 
     * @author linwb3
     * @param gdsInfo
     * @return
     * @since JDK 1.6
     */
    @Override
    public GdsInfoRespDTO queryGdsInfo(GdsInfoReqDTO reqDTO) throws BusinessException {
        GdsInfo gdsInfo = queryGdsInfoModel(reqDTO);
        if (gdsInfo != null) {
            GdsInfoRespDTO gdsInfoRespDTO = new GdsInfoRespDTO();
            copyGdsInfo2Resp(gdsInfo, gdsInfoRespDTO);
            setGdsStatusAndTypeName(gdsInfoRespDTO);
            return gdsInfoRespDTO;
        }
        return null;
    }

    /**
     * 
     * queryGdsInfo:(通过复合条件查询商品信息). <br/>
     * 
     * @author linwb3
     * @param gdsInfo
     * @return
     * @since JDK 1.6
     */
    @Override
    public GdsInfo queryGdsInfoModel(GdsInfoReqDTO gdsInfo) throws BusinessException {
        Long gdsId = gdsInfo.getId();
        String cacheKey = GdsConstants.GdsInfoCacheKey.GDS_CACHE_KEY_PREFIX + gdsId;
        GdsInfo obj = null;
        try {
            //Long data1=System.currentTimeMillis();
            obj = (GdsInfo) CacheUtil.getItem(cacheKey);
            //LogUtil.error("", "查询商品缓存耗时"+getTime(data1));
        } catch (Exception e) {
            LogUtil.error(MODULE, "get gdsInfo cache failed! please check  Cache Server!", e);
        }

        if (obj != null) {
            // 如果有查询状态，则匹配状态
            if (StringUtil.isNotEmpty(gdsInfo.getGdsStatus()) && gdsInfo.getGdsStatus().equals(obj.getGdsStatus())) {
                return obj;
            }
            else if (CollectionUtils.isNotEmpty(gdsInfo.getGdsStatusArr())){
                List<String> gdsStatuses=gdsInfo.getGdsStatusArr();
                for (String gdsStatus : gdsStatuses) {
                    if(gdsStatus.equals(obj.getGdsStatus())){
                        return obj;
                    }
                }
                return null;
            }
            else if (StringUtil.isEmpty(gdsInfo.getGdsStatus()) || CollectionUtils.isEmpty(gdsInfo.getGdsStatusArr())) {
                return obj;
            } 
            else {
                return null;
            }
        } else {
            //Long data1=System.currentTimeMillis();
            obj = queryGdsInfoModelFromDB(gdsInfo);
            //LogUtil.error("", "查询商品数据库耗时"+getTime(data1));
            if(obj==null){
                LogUtil.error(MODULE, "根据产品编码无法从产品信息主表查询到产品信息："+gdsInfo.getId());
                return obj;
            }
        	try {
                //Long data2=System.currentTimeMillis();
                CacheUtil.addItem(cacheKey, obj, GdsConstants.GdsInfoCacheKey.GDS_CACHE_TIME);
                //LogUtil.error("", "添加商品缓存耗时"+getTime(data2));
            } catch (Exception e) {
                LogUtil.error(MODULE, "add gdsInfo cache failed! please check  Cache Server!", e);
            }
            return obj;
        }

    }

    /**
     * 
     * queryGdsInfoModelFromDB:(查询商品信息 从数据库). <br/>
     * 
     * @author linwb3
     * @param gdsInfo
     * @return
     * @since JDK 1.6
     */
    @Override
    public GdsInfo queryGdsInfoModelFromDB(GdsInfoReqDTO gdsInfo) {
        GdsInfoCriteria gdsInfoCriteria = new GdsInfoCriteria();
        GdsInfoCriteria.Criteria criteria = gdsInfoCriteria.createCriteria();
        criteria.andIdEqualTo(gdsInfo.getId());
        if (StringUtil.isNotEmpty(gdsInfo.getGdsStatus())) {
            criteria.andGdsStatusEqualTo(gdsInfo.getGdsStatus());
        }
        if (StringUtil.isNotEmpty(gdsInfo.getGdsStatusArr())) {
            criteria.andGdsStatusIn(gdsInfo.getGdsStatusArr());
        }
        List<GdsInfo> gdsInfos = gdsInfoMapper.selectByExample(gdsInfoCriteria);
        if (CollectionUtils.isEmpty(gdsInfos)) {
            return null;
        }
        GdsInfo obj = gdsInfos.get(0);
        return obj;
    }

    /**
     * 
     * queryGdsInfoById:(通过编码查询商品信息). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    @Override
    public GdsInfoRespDTO queryGdsInfoById(Long gdsId, String... status) throws BusinessException {
        GdsInfoReqDTO reqDTO = new GdsInfoReqDTO();
        reqDTO.setId(gdsId);
        if (!ArrayUtils.isEmpty(status)) {
            reqDTO.setGdsStatusArr(GdsUtils.convertArrToList(status));
        }
        return queryGdsInfo(reqDTO);
    }

    /**
     * 
     * TODO 查询单个商品信息，根据入参选项，查询对应的信息.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV#queryGdsInfoByOption(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO,
     *      com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption[])
     */
    @Override
    public GdsInfoRespDTO queryGdsInfoByOption(GdsInfoReqDTO gdsInfo, GdsQueryOption... gdsQueryOptions) throws BusinessException {
        GdsInfoRespDTO gdsInfoRespDTO = null;
        Long gdsId = gdsInfo.getId();

        if (!ArrayUtils.isEmpty(gdsQueryOptions)) {
            // 如果不包含基本信息，自动补上基本信息
            int index = ArrayUtils.indexOf(gdsQueryOptions, GdsQueryOption.BASIC);
            if (index == ArrayUtils.INDEX_NOT_FOUND) {
                gdsInfoRespDTO = queryGdsInfoResp(gdsInfoRespDTO, gdsInfo, gdsId, GdsQueryOption.BASIC);
            } else if (index != 0) {
                gdsInfoRespDTO = queryGdsInfoResp(gdsInfoRespDTO, gdsInfo, gdsId, GdsQueryOption.BASIC);
                gdsQueryOptions[index] = null;
            }
            // 遍历商品选项枚举
            for (GdsQueryOption gdsQueryOption : gdsQueryOptions) {
                if (gdsQueryOption != null) {
                    // 查询商品信息
                    gdsInfoRespDTO = queryGdsInfoResp(gdsInfoRespDTO, gdsInfo, gdsId, gdsQueryOption);
                }
            }
        } else {
            // 查询商品信息
            gdsInfoRespDTO = queryGdsInfoResp(gdsInfoRespDTO, gdsInfo, gdsId, GdsQueryOption.BASIC);
        }
        return gdsInfoRespDTO;
    }

    /**
     * 
     * TODO 查询商品详情.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV#queryGdsInfoDetail(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO)
     */
    
    @Override
    public GdsInfoDetailRespDTO queryGdsInfoDetail(GdsInfoReqDTO gdsInfo) throws BusinessException {
        
        GdsInfoRespDTO gdsInfoRespDTO = null;
        //Long data1=System.currentTimeMillis();
        gdsInfoRespDTO = queryGdsInfoByOption(gdsInfo, gdsInfo.getGdsQueryOptions());
//        Long time=getTime(data1);
        //LogUtil.error("", "查询商品耗时"+time);
        if(gdsInfoRespDTO == null){
            return null;
        }
        //bean复制
         GdsInfoDetailRespDTO gdsInfoDetailRespDTO=copyGdsInfoResp2DetailResp(gdsInfoRespDTO);
        Long skuId = null;
        Long gdsId = gdsInfo.getId();
        String ifLadderPrice = gdsInfoRespDTO.getIfLadderPrice();
        if (GdsUtils.isEqualsValid(ifLadderPrice)) {
            // 阶梯价获取所有单品
            List<Long> skuIds = querySkuIdsGdsId(gdsId, GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
            List<GdsSkuInfoRespDTO> skuInfos = new ArrayList<GdsSkuInfoRespDTO>();
            for (Long id : skuIds) {
                GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
                skuInfoReqDTO.setId(id);
                skuInfoReqDTO.setGdsId(gdsId);
                skuInfoReqDTO.setStaffId(gdsInfo.getStaffId());
                skuInfoReqDTO.setStaff(gdsInfo.getStaff());
                skuInfoReqDTO.setPropIds(gdsInfo.getPropIds());
                GdsSkuInfoRespDTO gdsSkuInfoRespDTO = gdsSkuInfoQuerySV.querySkuInfoByOptions(skuInfoReqDTO, gdsInfo.getSkuQuerys());
                skuInfos.add(gdsSkuInfoRespDTO);
            }
            gdsInfoDetailRespDTO.setSkuInfos(skuInfos);

            // 获取价格
            GdsSku2PriceReqDTO sku2PriceReqDTO = new GdsSku2PriceReqDTO();
            sku2PriceReqDTO.setGdsId(gdsId);
            sku2PriceReqDTO.setrType(GdsConstants.GdsPrice.GDS_PRICE_RTYPE_GDS);
            sku2PriceReqDTO.setIfPrice(true);
            List<GdsSku2PriceRespDTO> prices = gdsPriceSV.queryGdsSkuPriceList(sku2PriceReqDTO);
            gdsInfoDetailRespDTO.setPrices(prices);
        }
        // 如果入参带有单品Id
        if (gdsInfo.getSkuId() != null && gdsInfo.getSkuId() != 0) {
            skuId = gdsInfo.getSkuId();
            // 如果入参带有属性，根据属性查询单品
        } else if (CollectionUtils.isNotEmpty(gdsInfo.getSkuProps())) {
            List<GdsSku2Prop> sku2PropPropIdxs = gdsSkuInfoQuerySV.querySkuInfoByProp(gdsInfo.getSkuProps(), gdsId, true);
            if (CollectionUtils.isNotEmpty(sku2PropPropIdxs)) {
                skuId = sku2PropPropIdxs.get(0).getSkuId();
            }

        } else {
            // 随机上架单品，取最近更新的一个
            //Long data3=System.currentTimeMillis();
            List<Long> skuIds = querySkuIdsGdsId(gdsId, GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
//            Long time1=getTime(data3);
            //LogUtil.error("", "查询最新上架单品"+time1);
            if (CollectionUtils.isNotEmpty(skuIds)) {
                skuId = skuIds.get(0);
            }
        }

        // 如果单品id还为null,则随机选一个已有的单品id

        if (skuId == null) {
            //Long data3=System.currentTimeMillis();
            List<Long> skuIds = querySkuIdsGdsId(gdsId,
                    new String[] { GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES, GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES, GdsConstants.GdsInfo.GDS_STATUS_DELETE });
//            Long time3=getTime(data3);
            //LogUtil.error("", "查询已有单品"+time3);
            if (CollectionUtils.isNotEmpty(skuIds)) {
                skuId = skuIds.get(0);
            }

        }
        if (skuId != null) {
            GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
            skuInfoReqDTO.setId(skuId);
            skuInfoReqDTO.setGdsId(gdsId);
            skuInfoReqDTO.setStaff(gdsInfo.getStaff());
            skuInfoReqDTO.setStaffId(gdsInfo.getStaffId());
            skuInfoReqDTO.setPropIds(gdsInfo.getPropIds());
            //Long data4=System.currentTimeMillis();
            GdsSkuInfoRespDTO gdsSkuInfoRespDTO = gdsSkuInfoQuerySV.querySkuInfoByOptions(skuInfoReqDTO, gdsInfo.getSkuQuerys());
            //LogUtil.error("", "查询单品信息"+getTime(data4));
            gdsInfoDetailRespDTO.setSkuInfo(gdsSkuInfoRespDTO);
            gdsInfoDetailRespDTO.setUrl(GdsUtils.getGdsUrl(gdsId, skuId, gdsInfoRespDTO.getCatlogId()));
        }

        GdsSku2PropReqDTO reqDTO = new GdsSku2PropReqDTO();
        reqDTO.setGdsId(gdsId);
        reqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
        reqDTO.setPropType(GdsConstants.GdsProp.PROP_TYPE_1);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        //Long data4=System.currentTimeMillis();
        List<GdsSku2Prop> sku2props = gdsSkuInfo2PropSV.queryGdsSkuInfo2Prop(reqDTO);
        //LogUtil.error("", "查询规格属性信息耗时:"+getTime(data4));

        List<GdsPropRespDTO> props = gdsSkuInfo2PropSV.convertSku2propTOProp(sku2props);
        if (CollectionUtils.isNotEmpty(props)) {
            List<GdsPropRespDTO> params = new ArrayList<GdsPropRespDTO>();
            for (GdsPropRespDTO prop : props) {
                params.add(prop);
            }
            gdsInfoDetailRespDTO.setParams(params);
        }
        return gdsInfoDetailRespDTO;
    }


    /**
     * 
     * TODO 查询单个商品信息，根据入参选项，查询对应的信息.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV#queryGdsInfoByOption(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO,
     *      com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption[],
     *      com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption[][])
     */
    @Override
    public GdsInfoRespDTO queryGdsInfoByOption(GdsInfoReqDTO gdsInfo, GdsQueryOption[] gdsQueryOptions, SkuQueryOption... skuQuerys) throws BusinessException {
        Long gdsId = gdsInfo.getId();
        GdsInfoRespDTO gdsInfoRespDTO = queryGdsInfoByOption(gdsInfo, gdsQueryOptions);

        if (gdsInfoRespDTO != null && skuQuerys != null && skuQuerys.length > 0) {
            List<GdsSkuInfoRespDTO> skus = new ArrayList<GdsSkuInfoRespDTO>();
            List<Long> skuIds = querySkuIdsGdsId(gdsId, GdsUtils.getNoDeleteStatusArr());
            for (Long skuId : skuIds) {
                GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
                skuInfoReqDTO.setId(skuId);
                skuInfoReqDTO.setGdsId(gdsId);
                /**
                 * add by gongxq 传入前店的参数，用于计算折扣价
                 */
                skuInfoReqDTO.setStaff(gdsInfo.getStaff());
                skuInfoReqDTO.setPropIds(gdsInfo.getPropIds());
                GdsSkuInfoRespDTO sku = gdsSkuInfoQuerySV.querySkuInfoByOptions(skuInfoReqDTO, skuQuerys);
                skus.add(sku);
            }
            if (gdsInfoRespDTO != null) {
                gdsInfoRespDTO.setSkus(skus);
            }
        }
        return gdsInfoRespDTO;
    }

    /**
     * 
     * TODO 查询商品列表根据查询条件，不分页. .
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV#queryGdsInfoList(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO,
     *      com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption[])
     */
    @Override
    public List<GdsInfoRespDTO> queryGdsInfoList(GdsInfoReqDTO gdsInfoReqDTO, GdsQueryOption... gdsQueryOptions) throws BusinessException {
        return queryGdsInfoList(gdsInfoReqDTO, gdsQueryOptions, null);
    }

    /**
     * 
     * TODO 查询商品列表根据查询条件，不分页.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV#queryGdsInfoList(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO,
     *      com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption[],
     *      com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption[])
     */
    @Override
    public List<GdsInfoRespDTO> queryGdsInfoList(GdsInfoReqDTO gdsInfoReqDTO, GdsQueryOption[] gdsQueryOptions, SkuQueryOption... skuQueryOptions) throws BusinessException {
        List<GdsInfoShopIdx> shopIdxs = gdsInfoQueryIDXSV.getGdsIdsByShopId(gdsInfoReqDTO);
        if (CollectionUtils.isEmpty(shopIdxs)) {
            return new ArrayList<GdsInfoRespDTO>();
        }
        List<GdsInfoRespDTO> gdsInfos = new ArrayList<GdsInfoRespDTO>();

        if (gdsInfoReqDTO.getFullInfo()) {
            for (GdsInfoShopIdx shopIdx : shopIdxs) {
                GdsInfoReqDTO gdsQuery = new GdsInfoReqDTO();
                gdsQuery.setId(shopIdx.getGdsId());
                GdsInfoRespDTO gdsInfoRespDTO = queryGdsInfoByOption(gdsQuery, gdsQueryOptions, skuQueryOptions);
                setGdsStatusAndTypeName(gdsInfoRespDTO);
                gdsInfos.add(gdsInfoRespDTO);
            }
        } else {
            for (GdsInfoShopIdx shopIdx : shopIdxs) {
                GdsInfoRespDTO gdsInfoRespDTO = new GdsInfoRespDTO();
                ObjectCopyUtil.copyObjValue(shopIdx, gdsInfoRespDTO, null, false);
                setGdsStatusAndTypeName(gdsInfoRespDTO);
                gdsInfos.add(gdsInfoRespDTO);
            }
        }
        return gdsInfos;
    }

    /**
     * 
     * TODO 分页查询商品信息列表.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV#queryGdsInfoListPage(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO,
     *      com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption[])
     */
    @Override
    public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListPage(GdsInfoReqDTO gdsInfoReqDTO, GdsQueryOption... gdsQueryOptions) throws BusinessException {
        return queryGdsInfoListPage(gdsInfoReqDTO, gdsQueryOptions, new SkuQueryOption[] {});
    }

    @Override
    public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListPageALLDB(GdsInfoReqDTO gdsInfoReqDTO, GdsQueryOption... gdsQueryOptions) {
        GdsInfoCriteria gdsinfoCriteria = new GdsInfoCriteria();
        GdsInfoCriteria.Criteria criteria = gdsinfoCriteria.createCriteria();
        if (gdsInfoReqDTO.getShopId() != null) {
            criteria.andShopIdEqualTo(gdsInfoReqDTO.getShopId());
        }
        if(null != gdsInfoReqDTO.getPutoffTimeBegin()){
            criteria.andPutoffTimeGreaterThanOrEqualTo(gdsInfoReqDTO.getPutoffTimeBegin());
        }
        if(null != gdsInfoReqDTO.getPutoffTimeBegin()){
            criteria.andPutoffTimeLessThanOrEqualTo(gdsInfoReqDTO.getPutoffTimeEnd());
        }
        if (StringUtil.isNotBlank(gdsInfoReqDTO.getGdsStatus())) {
            criteria.andGdsStatusEqualTo(gdsInfoReqDTO.getGdsStatus());
        }
        if (CollectionUtils.isNotEmpty(gdsInfoReqDTO.getCatalogIds())) {
            criteria.andCatlogIdIn(gdsInfoReqDTO.getCatalogIds());
        }
        if (gdsInfoReqDTO.getCatlogId() != null) {
            criteria.andCatlogIdEqualTo(gdsInfoReqDTO.getCatlogId());
        }
        if (CollectionUtils.isNotEmpty(gdsInfoReqDTO.getGdsStatusArr())) {
            criteria.andGdsStatusIn(gdsInfoReqDTO.getGdsStatusArr());
        }
        if (StringUtil.isNotBlank(gdsInfoReqDTO.getMainCatgs())) {
            criteria.andMainCatgsEqualTo(gdsInfoReqDTO.getMainCatgs());
        }
        if (StringUtil.isNotBlank(gdsInfoReqDTO.getIfScoreGds())) {
            criteria.andIfScoreGdsEqualTo(gdsInfoReqDTO.getIfScoreGds());
        }
        if (CollectionUtils.isNotEmpty(gdsInfoReqDTO.getCatgs())) {
            criteria.andMainCatgsIn(gdsInfoReqDTO.getCatgs());
        }
        if(CollectionUtils.isNotEmpty(gdsInfoReqDTO.getExcludeGdsTypes())){
            criteria.andGdsTypeIdNotIn(gdsInfoReqDTO.getExcludeGdsTypes());
        }
        if (gdsInfoReqDTO.getGdsTypeId() != null && gdsInfoReqDTO.getGdsTypeId().longValue() != 0L) {
            criteria.andGdsTypeIdEqualTo(gdsInfoReqDTO.getGdsTypeId());
        }
        gdsinfoCriteria.setLimitClauseCount(gdsInfoReqDTO.getPageSize());
        gdsinfoCriteria.setLimitClauseStart(gdsInfoReqDTO.getStartRowIndex());
        gdsinfoCriteria.setOrderByClause(" id asc ");
        DistributeRuleAssist.setTableIndex(gdsInfoReqDTO.getTableIndex());
        PageResponseDTO<GdsInfoRespDTO> pages = super.queryByPagination(gdsInfoReqDTO, gdsinfoCriteria, true, new PaginationCallback<GdsInfo, GdsInfoRespDTO>() {
            @Override
            public List<GdsInfo> queryDB(BaseCriteria criteria) {
                return gdsInfoMapper.selectByExample((GdsInfoCriteria) criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return gdsInfoMapper.countByExample((GdsInfoCriteria) criteria);
            }

            @Override
            public GdsInfoRespDTO warpReturnObject(GdsInfo t) {
                GdsInfoRespDTO dto = new GdsInfoRespDTO();
                ObjectCopyUtil.copyObjValue(t, dto, null, false);
                return dto;
            }

            @Override
            public List<Comparator<GdsInfo>> defineComparators() {
                List<Comparator<GdsInfo>> ls = new ArrayList<Comparator<GdsInfo>>();
                ls.add(new Comparator<GdsInfo>() {

                    @Override
                    public int compare(GdsInfo o1, GdsInfo o2) {
                        return o1.getId().compareTo(o2.getId());
                    }

                });
                return ls;
            }

        });

        DistributeRuleAssist.clearTableIndex();
        return pages;
    }

    /**
     * 
     * TODO 分页查询商品列表.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV#queryGdsInfoListPage(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO,
     *      com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption[],
     *      com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption[])
     */
    @Override
    public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListPage(GdsInfoReqDTO gdsInfoReqDTO, GdsQueryOption[] gdsQueryOptions, SkuQueryOption... skuQueryOptions) throws BusinessException {

        //Long data2=System.currentTimeMillis();
        PageResponseDTO<GdsInfoShopIdxRespDTO> shopIdxs = gdsInfoQueryIDXSV.getGdsIdsPageByShopId(gdsInfoReqDTO);
        //LogUtil.error("", "查询商品店铺索引耗时"+getTime(data2));

        List<GdsInfoRespDTO> gdsInfos = new ArrayList<GdsInfoRespDTO>();
        if (gdsInfoReqDTO.getFullInfo()) {
            if (CollectionUtils.isNotEmpty(shopIdxs.getResult())) {
                for (GdsInfoShopIdxRespDTO shopIdx : shopIdxs.getResult()) {
                    GdsInfoReqDTO gdsQuery = new GdsInfoReqDTO();
                    gdsQuery.setId(shopIdx.getGdsId());
                    gdsQuery.setPropIds(gdsInfoReqDTO.getPropIds());
                    gdsQuery.setPropTypes(gdsInfoReqDTO.getPropTypes());
                    gdsQuery.setPropInputTypes(gdsInfoReqDTO.getPropInputTypes());
                    gdsQuery.setPropValueTypes(gdsInfoReqDTO.getPropValueTypes());
                    //Long data3=System.currentTimeMillis();
                    GdsInfoRespDTO gdsInfoRespDTO = queryGdsInfoByOption(gdsQuery, gdsQueryOptions, skuQueryOptions);
                    //LogUtil.error("", "查询单条商品耗时"+getTime(data3));
                    gdsInfos.add(gdsInfoRespDTO);
                }
            }
        } else {
            if (CollectionUtils.isNotEmpty(shopIdxs.getResult())) {
                for (GdsInfoShopIdxRespDTO shopIdx : shopIdxs.getResult()) {
                    GdsInfoRespDTO gdsInfoRespDTO = copyShopIdx2GdsInfo(shopIdx);
//                    ObjectCopyUtil.copyObjValue(shopIdx, gdsInfoRespDTO, null, false);
                    gdsInfoRespDTO.setId(shopIdx.getGdsId());
                    setGdsStatusAndTypeName(gdsInfoRespDTO);
                    gdsInfos.add(gdsInfoRespDTO);
                }
            }
        }
        PageResponseDTO<GdsInfoRespDTO> resultPages = new PageResponseDTO<GdsInfoRespDTO>();
        resultPages.setCount(shopIdxs.getCount());
        resultPages.setPageCount(shopIdxs.getPageCount());
        resultPages.setPageSize(shopIdxs.getPageSize());
        resultPages.setPageNo(shopIdxs.getPageNo());
        resultPages.setResult(gdsInfos);
        return resultPages;

    }
    
    private GdsInfoRespDTO copyShopIdx2GdsInfo(GdsInfoShopIdxRespDTO t) {
        GdsInfoRespDTO dto = new GdsInfoRespDTO();
        dto.setShopId(t.getShopId());
        dto.setId(t.getGdsId());
        dto.setGdsName(t.getGdsName());
        dto.setGdsSubHead(t.getGdsSubHead());
        dto.setGuidePrice(t.getGuidePrice());
        dto.setGdsTypeId(t.getGdsTypeId());
        dto.setGdsStatus(t.getGdsStatus());
        dto.setGdsApprove(t.getGdsApprove());
        dto.setShopCatgs(t.getShopCatgs());
        dto.setPlatCatgs(t.getPlatCatgs());
        dto.setMainCatgs(t.getMainCatgs());
        dto.setCatlogId(t.getCatlogId());
        dto.setIfSendscore(t.getIfSendscore());
        dto.setIfSalealone(t.getIfSalealone());
        dto.setIfRecomm(t.getIfRecomm());
        dto.setIfNew(t.getIfNew());
        dto.setIfStocknotice(t.getIfStocknotice());
        dto.setIfFree(t.getIfFree());
        dto.setIfDisperseStock(t.getIfDisperseStock());
        dto.setIfEntityCode(t.getIfEntityCode());
        dto.setIfSeniorPrice(t.getIfSeniorPrice());
        dto.setIfScoreGds(t.getIfScoreGds());
        dto.setSortNo(t.getSortNo());
        dto.setCreateTime(t.getCreateTime());
        dto.setCreateStaff(t.getCreateStaff());
        dto.setUpdateStaff(t.getUpdateStaff());
        dto.setUpdateTime(t.getUpdateTime());
        dto.setIsbn(t.getIsbn());
        dto.setExt1(t.getExt1());
        dto.setExt2(t.getExt2());
        dto.setExt3(t.getExt3());
        dto.setExt4(t.getExt4());
        dto.setExt5(t.getExt5());
        return dto;
    }

    @DataAuthValid(authType = DataAuthType.BIND, funcCode = GdsConstants.GdsDataAuthFunc.GDS_INFO_SEARCH_DA1006, value = "gdsInfoReqDTO")
    @Override
    public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListPageWithAuth(@DataObject("gdsInfoReqDTO") GdsInfoReqDTO gdsInfoReqDTO, GdsQueryOption[] gdsQueryOptions, SkuQueryOption... skuQueryOptions)
            throws BusinessException {
        return this.queryGdsInfoListPage(gdsInfoReqDTO, gdsQueryOptions, skuQueryOptions);
    }

    /**
     * 查询对应分类的商品列表 TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV#queryGdsInfoListByCatgCode(com.zengshi.ecp.goods.dao.model.GdsCategory,
     *      boolean)
     */
    @Override
    public PageResponseDTO<GdsInfoRespDTO> queryGdsInfoListByCatgCode(GdsInfoReqDTO reqDto) throws BusinessException {
        return gdsInfoQueryIDXSV.queryGdsInfoByCatgCode(reqDto);
    }

    /**
     * 
     * TODO 通过商品id，查询出对应的单品id.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV#querySkuGdsIdxByGdsId(java.lang.Long)
     */
    @Override
    public List<Long> querySkuIdsGdsId(Long gdsId, String... gdsStatus) throws BusinessException {
        List<Long> skuIds = new ArrayList<Long>();
        GdsSkuInfoGdsIdxReqDTO reqDTO = new GdsSkuInfoGdsIdxReqDTO();
        reqDTO.setGdsId(gdsId);
        if (!ArrayUtils.isEmpty(gdsStatus)) {
            reqDTO.setGdsstatues(GdsUtils.convertArrToList(gdsStatus));
        }
        List<GdsSkuInfoGdsIdx> skuidxs = gdsInfoQueryIDXSV.querySkuGdsIdxs(reqDTO);
        if (CollectionUtils.isNotEmpty(skuidxs)) {
            for (GdsSkuInfoGdsIdx gdsSkuInfoGdsIdx : skuidxs) {
                skuIds.add(gdsSkuInfoGdsIdx.getSkuId());
            }
        }
        return skuIds;

    }

    /**
     * 
     * TODO 通过商品id，获取单品信息列表.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV#querySkuInfosByGdsId(java.lang.Long)
     */
    @Override
    public List<GdsSkuInfo> querySkuInfosByGdsId(Long gdsId, String... gdsStatus) throws BusinessException {
        GdsSkuInfoGdsIdxReqDTO reqDTO = new GdsSkuInfoGdsIdxReqDTO();
        reqDTO.setGdsId(gdsId);
        if (!ArrayUtils.isEmpty(gdsStatus)) {
            reqDTO.setGdsStatus(gdsStatus[0]);
        }
        List<Long> skuIds = querySkuIdsGdsId(gdsId, gdsStatus);
        List<GdsSkuInfo> skuInfos = new ArrayList<GdsSkuInfo>();
        if (CollectionUtils.isNotEmpty(skuIds)) {
            for (Long skuId : skuIds) {
                GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
                skuInfoReqDTO.setId(skuId);
                GdsSkuInfo skuInfo = gdsSkuInfoQuerySV.queryGdsSkuInfo(skuInfoReqDTO);
                skuInfos.add(skuInfo);
            }
        }
        return skuInfos;
    }

    /**
     * 
     * TODO 通过商品id，获取单品信息列表.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV#querySkuInfosByGdsId(java.lang.Long)
     */
    @Override
    public List<GdsSkuInfoRespDTO> querySkuInfoRespsByGdsId(Long gdsId, String... status) throws BusinessException {

        List<GdsSkuInfo> infos=querySkuInfosByGdsId(gdsId, status);
        if(CollectionUtils.isEmpty(infos)){
            return null;
        }
        List<GdsSkuInfoRespDTO> skus = new ArrayList<GdsSkuInfoRespDTO>();
        for (GdsSkuInfo gdsSkuInfo : infos) {
            GdsSkuInfoRespDTO resp=new GdsSkuInfoRespDTO();
            copySkuInfo2Resp(gdsSkuInfo, resp);
            skus.add(resp);
            setGdsSkuStatusAndTypeName(resp);
        }
        return skus;
    }

   

  
    /**
     * 
     * queryGdsInfoResp:(通过选项信息查询商品信息). <br/>
     * 
     * @author linwb3
     * @param gdsInfoRespDTO
     * @param gdsId
     * @param gdsQueryOption
     * @since JDK 1.6
     */
    private GdsInfoRespDTO queryGdsInfoResp(GdsInfoRespDTO gdsInfoRespDTO, GdsInfoReqDTO gdsInfoReqDTO, Long gdsId, GdsQueryOption gdsQueryOption) {

        // 如果非查询基础信息，并且查询结果为空，说明没有这个商品，返回null
        if (gdsInfoRespDTO == null && !GdsQueryOption.BASIC.equals(gdsQueryOption)) {
            return null;
        }

        switch (gdsQueryOption) {
        // 查询所有商品信息
        case ALL:
            gdsInfoRespDTO = setAll(gdsInfoRespDTO, gdsInfoReqDTO, gdsId);
            break;
        case EDIT:
            gdsInfoRespDTO = setEDIT(gdsInfoRespDTO, gdsInfoReqDTO, gdsId);
            break;
        // 查询商品基本信息
        case BASIC:
            gdsInfoRespDTO = setGdsBasic(gdsInfoRespDTO, gdsId);
            break;
        // 商品价格信息
        case PRICE:
            gdsInfoRespDTO = setGdsPrice(gdsInfoRespDTO, gdsId);
            break;
        // 查询商品分类信息
        case CATG:
            gdsInfoRespDTO = setGdsCatgs(gdsInfoRespDTO, gdsId);
            break;
        // 查询商品属性信息
        case PROP:
            gdsInfoRespDTO = setGdsProp(gdsInfoRespDTO, gdsInfoReqDTO, gdsId);
            break;
        // 查询商品主图信息
        case SCORE:
            setGdsScore(gdsInfoRespDTO, gdsId);
            break;
        case MAINPIC:
            gdsInfoRespDTO = setGdsMainPic(gdsInfoRespDTO, gdsId);
            break;
        // 查询运费模板信息
        case SHIPTEMPLATE:
            gdsInfoRespDTO = setGdsShipTemplate(gdsInfoRespDTO, gdsId);
            break;
        // 查询媒体信息
        case MEDIA:
            gdsInfoRespDTO = setGdsMedia(gdsInfoRespDTO, gdsId);
            break;
        case GDSTYPE:
            gdsInfoRespDTO = setGdsTypeInfo(gdsInfoRespDTO,gdsId);
            break;
        default:
            return gdsInfoRespDTO;
        }
        return gdsInfoRespDTO;
    }

    private GdsInfoRespDTO setEDIT(GdsInfoRespDTO gdsInfoRespDTO, GdsInfoReqDTO gdsInfoReqDTO, Long gdsId) {
        gdsInfoRespDTO = setGdsBasic(gdsInfoRespDTO, gdsId);
        gdsInfoRespDTO = setGdsShipTemplate(gdsInfoRespDTO, gdsId);
        gdsInfoRespDTO = setGdsCatgs(gdsInfoRespDTO, gdsId);
        gdsInfoRespDTO = setGdsPrice(gdsInfoRespDTO, gdsId);
        gdsInfoRespDTO = setGdsProp(gdsInfoRespDTO, gdsInfoReqDTO, gdsId);
        gdsInfoRespDTO = setGdsMedia(gdsInfoRespDTO, gdsId);
        return gdsInfoRespDTO;
    }

    private GdsInfoRespDTO setAll(GdsInfoRespDTO gdsInfoRespDTO, GdsInfoReqDTO gdsInfoReqDTO, Long gdsId) {
        gdsInfoRespDTO = setEDIT(gdsInfoRespDTO, gdsInfoReqDTO, gdsId);
        gdsInfoRespDTO = setGdsScore(gdsInfoRespDTO, gdsId);
        return gdsInfoRespDTO;
    }

    @Override
    public HomePageGdsInfoRespDTO getHomePageGdsInfo(HomePageGdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
        // 设置商品基本信息
        GdsInfoReqDTO reqDto = new GdsInfoReqDTO();
        reqDto.setId(gdsInfoReqDTO.getGdsId());
        reqDto.setPropIds(gdsInfoReqDTO.getPropIds());
        GdsInfoRespDTO gdsInfoRespDTO = queryGdsInfoByOption(reqDto, GdsQueryOption.BASIC,GdsQueryOption.MAINPIC,GdsQueryOption.PROP );
        if(gdsInfoRespDTO==null){
            return null;
        }
        HomePageGdsInfoRespDTO homePageGdsInfoRespDTO = new HomePageGdsInfoRespDTO();
        homePageGdsInfoRespDTO.setGdsInfoRespDTO(gdsInfoRespDTO);
        // 获取商品单品信息
        Long skuId = null;
        // 随机上架单品，取最近更新的一个
        List<Long> skuIds = querySkuIdsGdsId(gdsInfoReqDTO.getGdsId(), GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
        if (CollectionUtils.isNotEmpty(skuIds)) {
            skuId = skuIds.get(0);
        }
        if (skuId != null) {
            GdsSkuInfoReqDTO skuReq = new GdsSkuInfoReqDTO();
            skuReq.setId(skuId);
            skuReq.setStaff(gdsInfoReqDTO.getStaff());
            skuReq.setPropIds(gdsInfoReqDTO.getPropIds());
            GdsSkuInfoRespDTO gdsSkuInfoRespDTO = gdsSkuInfoQuerySV.querySkuInfoByOptions(skuReq, new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.CAlDISCOUNT });
            homePageGdsInfoRespDTO.setGdsSkuInfoRespDTO(gdsSkuInfoRespDTO);
            homePageGdsInfoRespDTO.setUrl(GdsUtils.getGdsUrl(gdsInfoReqDTO.getGdsId(), skuId, gdsInfoRespDTO.getCatlogId()));
        }else{
            return null;
        }
        return homePageGdsInfoRespDTO;

    }

    /**
     * 
     * setGdsMedia:(对对应商品的媒体(图片，视频)进行设值). <br/>
     * 
     * @author linwb3
     * @param gdsInfoRespDTO
     * @param gdsId
     * @since JDK 1.6
     */
    private GdsInfoRespDTO setGdsMedia(GdsInfoRespDTO gdsInfoRespDTO, Long gdsId) {
        List<GdsGds2MediaRespDTO> medias = gdsInfo2MediaSV.queryGds2MediasByGdsId(gdsId);
        gdsInfoRespDTO.setMedias(medias);
        return gdsInfoRespDTO;
    }
    
    /**
     * 
     * setGdsTypeInfo:(获取商品类型). <br/> 
     * 
     * @author gxq 
     * @param gdsTypeReqDTO
     * @return 
     * @since JDK 1.6
     */
    private GdsInfoRespDTO setGdsTypeInfo(GdsInfoRespDTO gdsInfoRespDTO,Long gdsId){
        GdsTypeReqDTO gdsTypeReqDTO = new GdsTypeReqDTO();
        if(!StringUtil.isEmpty(gdsInfoRespDTO.getGdsTypeId())){
            gdsTypeReqDTO.setId(gdsInfoRespDTO.getGdsTypeId());
        }else{
            GdsInfoRespDTO resp= queryGdsInfoById(gdsId);
            if(StringUtil.isNotEmpty(resp)){
                gdsTypeReqDTO.setId(resp.getGdsTypeId());
            }
        }
        if(StringUtil.isNotEmpty(gdsTypeReqDTO.getId())){
            try {
                GdsTypeRespDTO gdsTypeRespDTO = iGdsTypeSV.queryGdsTypeByPK(gdsTypeReqDTO);
                if(StringUtil.isNotEmpty(gdsTypeRespDTO)){
                    gdsInfoRespDTO.setGdsTypeRespDTO(gdsTypeRespDTO);
                }
            } catch (BusinessException e) {
                LogUtil.error(MODULE, "获取商品类型信息错误！", e);
            } catch (Exception e) {
                LogUtil.error(MODULE, "获取商品类型信息错误！", e);
            }
        }
        return gdsInfoRespDTO;
    }
    
    /**
     * 
     * setGdsMedia:(对对应商品的主图进行设值). <br/>
     * 
     * @author linwb3
     * @param gdsInfoRespDTO
     * @param gdsId
     * @since JDK 1.6
     */
    private GdsInfoRespDTO setGdsMainPic(GdsInfoRespDTO gdsInfoRespDTO, Long gdsId) {
        GdsMediaRespDTO mainPic = gdsInfo2MediaSV.queryGdsMainPicByGdsId(gdsId);
        gdsInfoRespDTO.setMainPic(mainPic);
        return gdsInfoRespDTO;
    }

    /**
     * 
     * setGdsMedia:(对对应商品的主图进行设值). <br/>
     * 
     * @author linwb3
     * @param gdsInfoRespDTO
     * @param gdsId
     * @since JDK 1.6
     */
    private GdsInfoRespDTO setGdsScore(GdsInfoRespDTO gdsInfoRespDTO, Long gdsId) {
        GdsScoreExtReqDTO gdsScoreExtReqDTO = new GdsScoreExtReqDTO();
        gdsScoreExtReqDTO.setGdsId(gdsId);
        gdsScoreExtReqDTO.setShopId(gdsInfoRespDTO.getShopId());
        List<GdsScoreExtRespDTO> scores = gdsScoreExtSV.queryGdsScoreExtByGds(gdsScoreExtReqDTO);
        gdsInfoRespDTO.setScores(scores);
        return gdsInfoRespDTO;

    }

    /**
     * 
     * setGdsShipTemplate:(设置运费模板). <br/>
     * 
     * @author linwb3
     * @param gdsInfoRespDTO
     * @param gdsId
     * @since JDK 1.6
     */
    private GdsInfoRespDTO setGdsShipTemplate(GdsInfoRespDTO gdsInfoRespDTO, Long gdsId) {
        if (gdsInfoRespDTO.getShipTemplateId() == null || gdsInfoRespDTO.getShipTemplateId().longValue() == 0) {
            gdsInfoRespDTO.setShipTemplateId(-1L);
        }

        if (gdsInfoRespDTO.getShipTemplateId() != null && gdsInfoRespDTO.getShipTemplateId() != -1) {
            GdsShiptempReqDTO reqDTO = new GdsShiptempReqDTO();
            reqDTO.setId(gdsInfoRespDTO.getShipTemplateId());
            String tempName= gdsShipTempSV.getGdsShipTempName(reqDTO);
            if (StringUtil.isNotBlank(tempName)) {
                gdsInfoRespDTO.setShipTemplateName(tempName);
            }
        }
        return gdsInfoRespDTO;
    }

    /**
     * 
     * setGdsProp:(设置商品属性). <br/>
     * 
     * @author linwb3
     * @param gdsInfoRespDTO
     * @param gdsId
     * @since JDK 1.6
     */
    private GdsInfoRespDTO setGdsProp(GdsInfoRespDTO gdsInfoRespDTO, GdsInfoReqDTO gdsInfoReqDTO, Long gdsId) {
        List<GdsPropRespDTO> props = getPropsByGdsId(gdsId,gdsInfoReqDTO);
        gdsInfoRespDTO.setProps(props);
        if (CollectionUtils.isNotEmpty(props)) {
            Map<String, GdsPropRespDTO> allPropMap = new HashMap<String, GdsPropRespDTO>();
            //专属富文本的属性集合  add by gongxq
            Map<String, GdsPropRespDTO> richTextPropMap = new HashMap<String, GdsPropRespDTO>();
            for (GdsPropRespDTO gdsPropRespDTO : props) {
                allPropMap.put(gdsPropRespDTO.getId() + "", gdsPropRespDTO);
                if(GdsConstants.GdsProp.GDS_PROP_VALUE_INPUT_TYPE_RICHTXT.equals(gdsPropRespDTO.getPropInputType())){
                    richTextPropMap.put(gdsPropRespDTO.getId() + "", gdsPropRespDTO);
                }
            }
            gdsInfoRespDTO.setAllPropMaps(allPropMap);
            gdsInfoRespDTO.setRichTextPropMap(richTextPropMap);
        }
        return gdsInfoRespDTO;
    }

    /**
     * 
     * setGdsBasic:(设置商品的基本信息). <br/>
     * 
     * @author linwb3
     * @param gdsInfoRespDTO
     * @param gdsId
     * @since JDK 1.6
     */
    private GdsInfoRespDTO setGdsBasic(GdsInfoRespDTO gdsInfoRespDTO, Long gdsId) {
        if(gdsInfoRespDTO == null ||gdsInfoRespDTO.getId()==null || gdsInfoRespDTO.getId()==0){
            gdsInfoRespDTO = queryGdsInfoById(gdsId);
        }
        return gdsInfoRespDTO;
    }

    /**
     * 
     * setGdsBasic:(设置商品级别的价格). <br/>
     * 
     * @author linwb3
     * @param gdsInfoRespDTO
     * @param gdsId
     * @since JDK 1.6
     */
    private GdsInfoRespDTO setGdsPrice(GdsInfoRespDTO gdsInfoRespDTO, Long gdsId) {
        if (GdsUtils.isEqualsValid(gdsInfoRespDTO.getIfLadderPrice())) {
            GdsSku2PriceReqDTO sku2PriceReqDTO = new GdsSku2PriceReqDTO();
            sku2PriceReqDTO.setGdsId(gdsId);
            sku2PriceReqDTO.setIfPrice(true);
            sku2PriceReqDTO.setrType(GdsConstants.GdsPrice.GDS_PRICE_RTYPE_GDS);
            List<GdsSku2PriceRespDTO> prices = gdsPriceSV.queryGdsSkuPriceList(sku2PriceReqDTO);

            List<GdsPriceInfoResp> gdsPriceInfoResps = null;
            if (CollectionUtils.isNotEmpty(prices)) {
                gdsPriceInfoResps = new ArrayList<GdsPriceInfoResp>();
                for (GdsSku2PriceRespDTO gdsSku2PriceRespDTO : prices) {
                    if (gdsSku2PriceRespDTO != null && gdsSku2PriceRespDTO.getPrice() != null) {
                        gdsPriceInfoResps.add(gdsSku2PriceRespDTO.getPrice());
                    }
                }
                Collections.sort(gdsPriceInfoResps);
            }
            gdsInfoRespDTO.setPrices(gdsPriceInfoResps);
        }
        return gdsInfoRespDTO;
    }

    /**
     * 
     * setGdsStatusAndTypeName:(设置商品状态名称，类型名称). <br/>
     * 
     * @author linwb3
     * @param gdsInfoRespDTO
     * @since JDK 1.6
     */
    private GdsInfoRespDTO setGdsStatusAndTypeName(GdsInfoRespDTO gdsInfoRespDTO) {
        // BaseParamUtil.fetchParamList(GdsConstants.GdsInfo.GDS_INFO_STATUS_KEY);
        
        //Long data1=System.currentTimeMillis();
        gdsInfoRespDTO.setGdsStatusName(BaseParamUtil.fetchParamValue(GdsConstants.GdsInfo.GDS_INFO_STATUS_KEY, gdsInfoRespDTO.getGdsStatus()));
        if (gdsInfoRespDTO.getGdsTypeId() != null) {
        	GdsType type=gdsTypeSV.queryGdsTypeModelByPKFromCache(gdsInfoRespDTO.getGdsTypeId());
        	if(type!=null){
        		gdsInfoRespDTO.setGdsTypeName(type.getTypeName());
        		gdsInfoRespDTO.setIfBuyMore(GdsUtils.isEqualsValid(type.getIfBuyonce()));
        		gdsInfoRespDTO.setIfNeedStock(GdsUtils.isEqualsValid(type.getIfNeedstock()));
        	}
        }
//        Long time33=getTime(data1);
        //LogUtil.error("", "查询参数名称耗时"+time33);

        gdsInfoRespDTO.setUrl(GdsUtils.getGdsUrl(gdsInfoRespDTO.getId(), null, gdsInfoRespDTO.getCatlogId()));
        
        GdsCategoryReqDTO reqDTO=new GdsCategoryReqDTO();
        reqDTO.setCatgCode(gdsInfoRespDTO.getMainCatgs());
        //Long data2=System.currentTimeMillis();
        GdsCategoryRespDTO category = gdsCategorySV.queryGdsCategoryByPK(reqDTO);
//        Long time44=getTime(data2);
        //LogUtil.error("", "查询商品分类名称耗时"+time44);
        if (category != null) {
            gdsInfoRespDTO.setMainCatgName(category.getCatgName());
        }
        return gdsInfoRespDTO;
    }

    /**
     * 
     * setGdsUrl:(设置URL). <br/>
     * 
     * @author linwb3
     * @param gdsInfoRespDTO
     * @since JDK 1.6
     */
    private GdsSkuInfoRespDTO setGdsUrl(GdsSkuInfoRespDTO gdsSkuInfoRespDTO) {
        gdsSkuInfoRespDTO.setUrl(GdsUtils.getGdsUrl(gdsSkuInfoRespDTO.getGdsId(), gdsSkuInfoRespDTO.getId(), gdsSkuInfoRespDTO.getCatlogId()));
        return gdsSkuInfoRespDTO;
    }

    private GdsSkuInfoRespDTO setGdsSkuStatusAndTypeName(GdsSkuInfoRespDTO gdsInfoRespDTO) {
        // BaseParamUtil.fetchParamList(GdsConstants.GdsInfo.GDS_INFO_STATUS_KEY);
        gdsInfoRespDTO.setGdsStatusName(BaseParamUtil.fetchParamValue(GdsConstants.GdsInfo.GDS_INFO_STATUS_KEY, gdsInfoRespDTO.getGdsStatus()));
        if (gdsInfoRespDTO.getGdsTypeId() != null) {
        	GdsType type=gdsTypeSV.queryGdsTypeModelByPKFromCache(gdsInfoRespDTO.getGdsTypeId());
        	if(type!=null){
        		gdsInfoRespDTO.setGdsTypeName(type.getTypeName());
        		gdsInfoRespDTO.setIfBuyMore(GdsUtils.isEqualsValid(type.getIfBuyonce()));
        		gdsInfoRespDTO.setIfNeedStock(GdsUtils.isEqualsValid(type.getIfNeedstock()));
        	}
        }
        setGdsUrl(gdsInfoRespDTO);

        
        GdsCategoryReqDTO reqDTO=new GdsCategoryReqDTO();
        reqDTO.setCatgCode(gdsInfoRespDTO.getMainCatgs());
        GdsCategoryRespDTO category = gdsCategorySV.queryGdsCategoryByPK(reqDTO);
        if (category != null) {
            gdsInfoRespDTO.setMainCatgName(category.getCatgName());
        }
        return gdsInfoRespDTO;
    }

    /**
     * 
     * setGdsCatgs:(查询对应商品的分类，并且将主分类，平台分类，店铺分类归类). <br/>
     * 
     * @author linwb3
     * @param gdsInfoRespDTO
     * @param gdsId
     * @since JDK 1.6
     */
    private GdsInfoRespDTO setGdsCatgs(GdsInfoRespDTO gdsInfoRespDTO, Long gdsId) {
        List<GdsGds2CatgRespDTO> gds2Catgs = gdsInfo2CatgSV.queryGds2CatgsByGdsId(gdsId);
        List<GdsCategoryRespDTO> platformCategory = new ArrayList<GdsCategoryRespDTO>();
        List<GdsCategoryRespDTO> shopCategory = new ArrayList<GdsCategoryRespDTO>();
        if (CollectionUtils.isNotEmpty(gds2Catgs)) {
            for (GdsGds2CatgRespDTO gds2Catg : gds2Catgs) {
                
                GdsCategoryReqDTO reqDTO=new GdsCategoryReqDTO();
                reqDTO.setCatgCode(gds2Catg.getCatgCode());
                GdsCategoryRespDTO gdsCategoryResp = gdsCategorySV.queryGdsCategoryByPK(reqDTO);
                if (gdsCategoryResp == null) {
                    continue;
                }
                if (GdsConstants.GdsCategory.CATG_TYPE_1.equals(gdsCategoryResp.getCatgType())) {
                    platformCategory.add(gdsCategoryResp);
                } else {
                    shopCategory.add(gdsCategoryResp);
                }
                // 设置主分类
                if (GdsConstants.GdsInfo.GDS_2_CATG_RTYPE_MAIN.equals(gds2Catg.getGds2catgType())) {
                    gdsInfoRespDTO.setMainCategory(gdsCategoryResp);
                }
            }
        }
        // 设置平台分类
        gdsInfoRespDTO.setPlatformCategory(platformCategory);
        // 设置店铺分类
        gdsInfoRespDTO.setShopCategory(shopCategory);
        return gdsInfoRespDTO;
    }

    /**
     * 
     * getGds2PropByGdsId:(获取商品对应的属性列表). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    @Override
    public List<GdsPropRespDTO> getPropsByGdsId(Long gdsId, GdsInfoReqDTO gdsInfoReqDTO) {

        GdsGds2PropReqDTO reqDTO = new GdsGds2PropReqDTO();
        reqDTO.setGdsId(gdsId);
        reqDTO.setPropIds(gdsInfoReqDTO.getPropIds());
        reqDTO.setPropTypes(gdsInfoReqDTO.getPropTypes());
        reqDTO.setPropValueTypes(gdsInfoReqDTO.getPropValueTypes());
        reqDTO.setPropInputTypes(gdsInfoReqDTO.getPropInputTypes());

        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        List<GdsGds2PropRespDTO> gds2Props = GdsUtils.doConvert(gdsInfo2PropSV.queryGdsInfo2Prop(reqDTO), GdsGds2PropRespDTO.class);
        if (CollectionUtils.isEmpty(gds2Props)) {
            return null;
        }

        // 创建映射Map，以属性ID为key，属性Resp对象为值
        Map<String, GdsPropRespDTO> propMaps = new HashMap<String, GdsPropRespDTO>();
        for (GdsGds2PropRespDTO gdsGds2Prop : gds2Props) {
            String propId = gdsGds2Prop.getPropId().toString();
            if (propMaps.containsKey(propId)) {
                // 构建新的value，添加到values列表中
                GdsPropRespDTO prop = propMaps.get(propId);
                List<GdsPropValueRespDTO> values = prop.getValues();
                if (CollectionUtils.isEmpty(values)) {
                    values = new ArrayList<GdsPropValueRespDTO>();
                }
                GdsPropValueRespDTO propValue = new GdsPropValueRespDTO();
                propValue.setId(gdsGds2Prop.getPropValueId());
                propValue.setPropValue(gdsGds2Prop.getPropValue());
                propValue.setMediaId(gdsGds2Prop.getPropMediaUuid());
                values.add(propValue);
                prop.setValues(values);

            } else {
                // 构建新的属性，并且构建value
                GdsPropRespDTO prop = new GdsPropRespDTO();
                prop.setId(gdsGds2Prop.getPropId());
                ObjectCopyUtil.copyObjValue(gdsGds2Prop, prop, null, false);

                List<GdsPropValueRespDTO> values = new ArrayList<GdsPropValueRespDTO>();
                GdsPropValueRespDTO propValue = new GdsPropValueRespDTO();
                propValue.setId(gdsGds2Prop.getPropValueId());
                propValue.setPropValue(gdsGds2Prop.getPropValue());
                propValue.setMediaId(gdsGds2Prop.getPropMediaUuid());
                values.add(propValue);
                prop.setValues(values);
                propMaps.put(propId, prop);
            }
        }
        return GdsUtils.convertMapToList(propMaps);
    }

   

    /**
     * 商品主表分页查询类
     * 
     * @author linwb3
     * @version GdsInfoQuerySVImpl
     * @since JDK 1.6
     */
    protected class GdsInfoRespPaginationCallback extends PaginationCallback<GdsInfo, GdsInfoRespDTO> {

        @Override
        public List<GdsInfo> queryDB(BaseCriteria criteria) {
            return gdsInfoMapper.selectByExample((GdsInfoCriteria) criteria);
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
            return gdsInfoMapper.countByExample((GdsInfoCriteria) criteria);
        }

        @Override
        public GdsInfoRespDTO warpReturnObject(GdsInfo t) {
            GdsInfoRespDTO dto = new GdsInfoRespDTO();
            ObjectCopyUtil.copyObjValue(t, dto, null, false);
            return dto;
        }

    }

    /**
     * 
     * isBelongToCategory:(判断商品是否属于某个分类). <br/>
     * 
     * @author linwb3
     * @param gds2CatgReqDTO
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public Boolean isBelongToCategory(GdsGds2CatgReqDTO gds2CatgReqDTO) throws BusinessException {
        GdsInfoReqDTO reqDto = new GdsInfoReqDTO();
        reqDto.setId(gds2CatgReqDTO.getGdsId());
        GdsInfoRespDTO gdsInfo = queryGdsInfoByOption(reqDto);
        String catgCode = gds2CatgReqDTO.getCatgCode();
        String gdsCatgs = gdsInfo.getMainCatgs();
        if (gdsCatgs != null && gdsCatgs.equals(catgCode)) {
            return true;
        } else {
            List<GdsCategoryRespDTO> categories = gdsCategorySV.queryCategoryTraceUpon(gdsCatgs);
            if (CollectionUtils.isNotEmpty(categories)) {
                for (GdsCategoryRespDTO category : categories) {
                    String catgCodelist = category.getCatgCode();
                    if (catgCodelist != null && catgCodelist.equals(catgCode)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 
     * queryGdsSkuInfoListByCatgRela:(查询相同分类的商品). <br/>
     * 
     * @author linwb3
     * @param gdsInfoReqDTO
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public List<GdsInfoDetailRespDTO> queryGdsSkuInfoListByCatgRela(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
        List<GdsInfoDetailRespDTO> detailRespDTOs = new ArrayList<GdsInfoDetailRespDTO>();
        // 根据分类编码，查询对应的商品编码列表
        List<Long> gdsIds = gdsInfoQueryIDXSV.getGdsIdsByCatg(gdsInfoReqDTO);
        if (CollectionUtils.isNotEmpty(gdsIds)) {
            for (Long gdsId : gdsIds) {
                GdsInfoReqDTO infoReqDTO = new GdsInfoReqDTO();
                infoReqDTO.setId(gdsId);
                GdsQueryOption[] gdsQueryOptions = null;
                SkuQueryOption[] skuQueryOptions = null;
                if (GdsUtils.isEqualsValid(gdsInfoReqDTO.getIfScoreGds())) {
                    gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.BASIC, GdsQueryOption.SCORE };
                    skuQueryOptions=new SkuQueryOption[] {SkuQueryOption.BASIC, SkuQueryOption.MAINPIC};
                } else {
                    gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.BASIC };
                    skuQueryOptions=new SkuQueryOption[] {SkuQueryOption.BASIC, SkuQueryOption.MAINPIC, SkuQueryOption.CAlDISCOUNT, SkuQueryOption.PROP};
                }
                infoReqDTO.setGdsQueryOptions(gdsQueryOptions);
                infoReqDTO.setSkuQuerys(skuQueryOptions);
                infoReqDTO.setPropIds(gdsInfoReqDTO.getPropIds());
                GdsInfoDetailRespDTO detailRespDTO = gdsInfoQuerySV.queryGdsInfoDetail(infoReqDTO);
                if (detailRespDTO.getSkuInfo() != null) {
                    detailRespDTOs.add(detailRespDTO);
                }
            }
        }
        return detailRespDTOs;
    }

    @Override
    public List<GdsInfoDetailRespDTO> queryGdsSkuInfoListByRank(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {
        List<GdsInfoDetailRespDTO> detailRespDTOs = new ArrayList<GdsInfoDetailRespDTO>();
        queryTopSaleGdsInfo(detailRespDTOs, 10, 10, gdsInfoReqDTO.getTopNum(), gdsInfoReqDTO.getCurrentSiteId());

        return detailRespDTOs;
    }

    private void queryTopSaleGdsInfo(List<GdsInfoDetailRespDTO> detailRespDTOs, int start, int size, int topNum, Long siteId) throws BusinessException {

        RSalesChartRequest arg0 = new RSalesChartRequest();
        arg0.setSiteId(siteId);
        arg0.setTopNum(start);
        RSalesChartResponse rSalesChartResponse = reportGoodPayedRSV.querySkuSalesChart(arg0);
        if (rSalesChartResponse.getSkuIds() != null && rSalesChartResponse.getSkuIds().size() > 0) {
            for (Long skuId : rSalesChartResponse.getSkuIds()) {

                GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
                gdsSkuInfoReqDTO.setId(skuId);
                GdsSkuInfo gdsSkuInfo = gdsSkuInfoQuerySV.queryGdsSkuInfo(gdsSkuInfoReqDTO);
                GdsInfoReqDTO infoReqDTO = new GdsInfoReqDTO();
                infoReqDTO.setId(gdsSkuInfo.getGdsId());

                GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.BASIC, GdsQueryOption.MAINPIC, GdsQueryOption.SCORE, GdsQueryOption.PRICE };
                SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.MAINPIC, SkuQueryOption.PRICE, SkuQueryOption.PROP };
                infoReqDTO.setGdsQueryOptions(gdsQueryOptions);
                infoReqDTO.setSkuQuerys(skuQueryOptions);
                GdsInfoDetailRespDTO detailRespDTO = gdsInfoQuerySV.queryGdsInfoDetail(infoReqDTO);
                if (detailRespDTO.getSkuInfo() != null) {
                    if (GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES.equals(detailRespDTO.getGdsStatus())) {
                        detailRespDTOs.add(detailRespDTO);
                    }
                }

                if (detailRespDTOs.size() == topNum) {

                    break;

                }
            }
            if (detailRespDTOs.size() < topNum) {
                if (start < 28) {

                    detailRespDTOs.clear();
                    queryTopSaleGdsInfo(detailRespDTOs, start + size, size, topNum, siteId);
                }
            }
        } else {
            return;
        }

    }

    @Override
    public PageResponseDTO<GdsVerifyRespDTO> queryGdsVerifyInfoPage(GdsVerifyReqDTO gdsVerifyReqDTO) throws BusinessException {
        PageResponseDTO<GdsVerifyRespDTO> resultPages = new PageResponseDTO<GdsVerifyRespDTO>();
        GdsVerifyShopIdxCriteria gdsVerifyShopIdxCriteria = new GdsVerifyShopIdxCriteria();
        initGdsVerifyParam(gdsVerifyShopIdxCriteria, gdsVerifyReqDTO);
        gdsVerifyShopIdxCriteria.setLimitClauseCount(gdsVerifyReqDTO.getPageSize());
        gdsVerifyShopIdxCriteria.setLimitClauseStart(gdsVerifyReqDTO.getStartRowIndex());
        gdsVerifyShopIdxCriteria.setOrderByClause(" operate_time desc ");// 默认按照操作时间倒排序

        PageResponseDTO<GdsVerifyRespDTO> pageInfo = super.queryByPagination(gdsVerifyReqDTO, gdsVerifyShopIdxCriteria, true, new PaginationCallback<GdsVerifyShopIdx, GdsVerifyRespDTO>() {
            @Override
            public List<GdsVerifyShopIdx> queryDB(BaseCriteria criteria) {
                return gdsVerifyShopIdxMapper.selectByExample((GdsVerifyShopIdxCriteria) criteria);
            }

            @Override
            public long queryTotal(BaseCriteria example) {
                return gdsVerifyShopIdxMapper.countByExample((GdsVerifyShopIdxCriteria) example);
            }

            @Override
            public GdsVerifyRespDTO warpReturnObject(GdsVerifyShopIdx t) {
                GdsVerifyRespDTO dto = new GdsVerifyRespDTO();
                ObjectCopyUtil.copyObjValue(t, dto, null, false);
                if (t.getOperateStaff() != null) {
                    AuthStaffResDTO authStaffResDTO = authStaffRSV.findAuthStaffById(t.getOperateStaff());

                    if (authStaffResDTO != null) {
                        dto.setOperateStaffCode(authStaffResDTO.getStaffCode());
                    }
                }

                if (t.getVerifyStaff() != null) {

                    AuthStaffResDTO authStaffResDTO = authStaffRSV.findAuthStaffById(t.getVerifyStaff());

                    if (authStaffResDTO != null) {
                        dto.setVerifyStaffCode(authStaffResDTO.getStaffCode());
                    }
                }
                return dto;
            }
        });
        List<GdsVerifyRespDTO> gdsVerifyList = new ArrayList<GdsVerifyRespDTO>();
        if (StringUtil.isNotEmpty(pageInfo) && StringUtil.isNotEmpty(pageInfo.getResult())) {
            GdsVerifyRespDTO resultDto = null;
            GdsInfoReqDTO gdsInfoReqDTO = null;
            for (GdsVerifyRespDTO dto : pageInfo.getResult()) {
                resultDto = new GdsVerifyRespDTO();
                gdsInfoReqDTO = new GdsInfoReqDTO();

                ObjectCopyUtil.copyObjValue(dto, resultDto, null, false);
                // 主要为了前店管理列表的框架
                resultDto.setId(dto.getGdsId());
                // 获取商品的一些基本信息
                gdsInfoReqDTO.setId(dto.getGdsId());
                GdsInfoRespDTO gdsInfoRespDto = queryGdsInfo(gdsInfoReqDTO);
                if (gdsInfoRespDto != null) {
                    resultDto.setCatgCode(gdsInfoRespDto.getMainCatgs());
                    resultDto.setGdsName(gdsInfoRespDto.getGdsName());
                    resultDto.setGuidePrice(gdsInfoRespDto.getGuidePrice());
                    resultDto.setIsbn(gdsInfoRespDto.getIsbn());
                    resultDto.setCatgName(gdsInfoRespDto.getMainCatgName());
                }
                GdsGds2PropReqDTO gdsGds2PropReqDTO = new GdsGds2PropReqDTO();
                List<Long> propIds = new ArrayList<Long>();
                // 出版日期:1005作者:1001
                propIds.add(1005L);
                propIds.add(1001L);
                gdsGds2PropReqDTO.setPropIds(propIds);
                gdsGds2PropReqDTO.setGdsId(dto.getGdsId());
                gdsGds2PropReqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
                List<GdsGds2Prop> listProp = gdsInfo2PropSV.queryGdsInfo2Prop(gdsGds2PropReqDTO);
                if (listProp != null && listProp.size() >= 1) {
                    int size = listProp.size();
                    for (int i = 0; i < size; i++) {
                        GdsGds2Prop propDto = listProp.get(i);
                        if (1005 == propDto.getPropId()) {
                            resultDto.setPublictionDate(propDto.getPropValue());
                        } else if (1001 == propDto.getPropId()) {
                            resultDto.setAuthor(propDto.getPropValue());
                        }
                    }
                }
                gdsVerifyList.add(resultDto);
            }
        }
        resultPages.setCount(pageInfo.getCount());
        resultPages.setPageCount(pageInfo.getPageCount());
        resultPages.setPageNo(pageInfo.getPageNo());
        resultPages.setPageSize(pageInfo.getPageSize());
        resultPages.setResult(gdsVerifyList);
        return resultPages;
    }

    private void initGdsVerifyParam(GdsVerifyShopIdxCriteria gdsVerifyShopIdxCriteria, GdsVerifyReqDTO gdsVerifyReqDTO) {
        GdsVerifyShopIdxCriteria.Criteria criteria = gdsVerifyShopIdxCriteria.createCriteria();
        if (!StringUtil.isEmpty(gdsVerifyReqDTO.getStatus())) {
            criteria.andStatusEqualTo(gdsVerifyReqDTO.getStatus());
        }
        if (!StringUtil.isEmpty(gdsVerifyReqDTO.getShopId())) {
            criteria.andShopIdEqualTo(gdsVerifyReqDTO.getShopId());
        }
        if (!StringUtil.isEmpty(gdsVerifyReqDTO.getGdsId())) {
            criteria.andGdsIdEqualTo(gdsVerifyReqDTO.getGdsId());
        }
        if (!StringUtil.isEmpty(gdsVerifyReqDTO.getSkuId())) {
            criteria.andSkuIdEqualTo(gdsVerifyReqDTO.getSkuId());
        }
        // 审核状态
        if (!StringUtil.isBlank(gdsVerifyReqDTO.getVerifyStatus())) {
            criteria.andVerifyStatusEqualTo(gdsVerifyReqDTO.getVerifyStatus());
        }
        // 商品名称
        if (!StringUtil.isBlank(gdsVerifyReqDTO.getGdsName())) {
            criteria.andGdsNameLike("%" + gdsVerifyReqDTO.getGdsName() + "%");
        }
        // ISBN
        if (!StringUtil.isBlank(gdsVerifyReqDTO.getIsbn())) {
            criteria.andIsbnEqualTo(gdsVerifyReqDTO.getIsbn());
        }
        // 平台分类，主分类
        if (!StringUtil.isBlank(gdsVerifyReqDTO.getCatgCode())) {
            criteria.andCatgCodeEqualTo(gdsVerifyReqDTO.getCatgCode());
        }
        // 查询开始时间
        if (gdsVerifyReqDTO.getBegCreateTime() != null) {
            criteria.andCreateTimeGreaterThanOrEqualTo(gdsVerifyReqDTO.getBegCreateTime());
        }
        // 查询结束时间
        if (gdsVerifyReqDTO.getEndCreateTime() != null) {
            criteria.andCreateTimeLessThanOrEqualTo(gdsVerifyReqDTO.getEndCreateTime());
        }

        // 商品类型
        if (gdsVerifyReqDTO.getGdsTypeId() != null) {
            criteria.andGdsTypeIdEqualTo(gdsVerifyReqDTO.getGdsTypeId());
        }
    }
    
    
    /**
     * 
     * copyGdsInfo2Resp:(将Info对象转为Resp对象). <br/> 
     * 
     * @author linwb3
     * @param gdsInfo
     * @param gdsInfoRespDTO 
     * @since JDK 1.6
     */
    private void copyGdsInfo2Resp(GdsInfo gdsInfo, GdsInfoRespDTO gdsInfoRespDTO) {
        gdsInfoRespDTO.setId(gdsInfo.getId());
        gdsInfoRespDTO.setGdsName(gdsInfo.getGdsName());
        gdsInfoRespDTO.setSnapId(gdsInfo.getSnapId());
        gdsInfoRespDTO.setGdsSubHead(gdsInfo.getGdsSubHead());
        gdsInfoRespDTO.setGdsDesc(gdsInfo.getGdsDesc());
        gdsInfoRespDTO.setGdsPartlist(gdsInfo.getGdsPartlist());
        gdsInfoRespDTO.setGdsTypeId(gdsInfo.getGdsTypeId());
        gdsInfoRespDTO.setGdsStatus(gdsInfo.getGdsStatus());
        gdsInfoRespDTO.setGdsApprove(gdsInfo.getGdsApprove());
        gdsInfoRespDTO.setGuidePrice(gdsInfo.getGuidePrice());
        gdsInfoRespDTO.setUrl(gdsInfo.getUrl());
        gdsInfoRespDTO.setTaxId(gdsInfo.getTaxId());
        gdsInfoRespDTO.setSortNo(gdsInfo.getSortNo());
        gdsInfoRespDTO.setShopId(gdsInfo.getShopId());
        gdsInfoRespDTO.setGdsLabel(gdsInfo.getGdsLabel());
        gdsInfoRespDTO.setPutoffTime(gdsInfo.getPutoffTime());
        gdsInfoRespDTO.setPutonTime(gdsInfo.getPutonTime());
        gdsInfoRespDTO.setPostTime(gdsInfo.getPostTime());
        gdsInfoRespDTO.setIfSendscore(gdsInfo.getIfSendscore());
        gdsInfoRespDTO.setIfSalealone(gdsInfo.getIfSalealone());
        gdsInfoRespDTO.setIfRecomm(gdsInfo.getIfRecomm());
        gdsInfoRespDTO.setIfNew(gdsInfo.getIfNew());
        gdsInfoRespDTO.setIfLadderPrice(gdsInfo.getIfLadderPrice());
        gdsInfoRespDTO.setIfFree(gdsInfo.getIfFree());
        gdsInfoRespDTO.setIfEntityCode(gdsInfo.getIfEntityCode());
        gdsInfoRespDTO.setIfSeniorPrice(gdsInfo.getIfSeniorPrice());
        gdsInfoRespDTO.setIfScoreGds(gdsInfo.getIfScoreGds());
        gdsInfoRespDTO.setShipTemplateId(gdsInfo.getShipTemplateId());
        gdsInfoRespDTO.setIfDisperseStock(gdsInfo.getIfDisperseStock());
        gdsInfoRespDTO.setSupplierId(gdsInfo.getSupplierId());
        gdsInfoRespDTO.setPlatCatgs(gdsInfo.getPlatCatgs());
        gdsInfoRespDTO.setShopCatgs(gdsInfo.getShopCatgs());
        gdsInfoRespDTO.setMainCatgs(gdsInfo.getMainCatgs());
        gdsInfoRespDTO.setCatlogId(gdsInfo.getCatlogId());
        gdsInfoRespDTO.setChangePropStr(gdsInfo.getChangePropStr());
        gdsInfoRespDTO.setIsbn(gdsInfo.getIsbn());
        gdsInfoRespDTO.setCreateStaff(gdsInfo.getCreateStaff());
        gdsInfoRespDTO.setCreateTime(gdsInfo.getCreateTime());
        gdsInfoRespDTO.setUpdateStaff(gdsInfo.getUpdateStaff());
        gdsInfoRespDTO.setUpdateTime(gdsInfo.getUpdateTime());
        gdsInfoRespDTO.setExt1(gdsInfo.getExt1());
        gdsInfoRespDTO.setExt2(gdsInfo.getExt2());
        gdsInfoRespDTO.setExt3(gdsInfo.getExt3());
        gdsInfoRespDTO.setExt4(gdsInfo.getExt4());
        gdsInfoRespDTO.setExt5(gdsInfo.getExt5());
    }
    
    private GdsInfoDetailRespDTO copyGdsInfoResp2DetailResp(GdsInfoRespDTO gdsInfo) {
        GdsInfoDetailRespDTO gdsInfoDetailRespDTO=new GdsInfoDetailRespDTO();
        gdsInfoDetailRespDTO.setId(gdsInfo.getId());
        gdsInfoDetailRespDTO.setGdsName(gdsInfo.getGdsName());
        gdsInfoDetailRespDTO.setSnapId(gdsInfo.getSnapId());
        gdsInfoDetailRespDTO.setGdsSubHead(gdsInfo.getGdsSubHead());
        gdsInfoDetailRespDTO.setGdsDesc(gdsInfo.getGdsDesc());
        gdsInfoDetailRespDTO.setGdsPartlist(gdsInfo.getGdsPartlist());
        gdsInfoDetailRespDTO.setGdsTypeId(gdsInfo.getGdsTypeId());
        gdsInfoDetailRespDTO.setGdsStatus(gdsInfo.getGdsStatus());
        gdsInfoDetailRespDTO.setGdsApprove(gdsInfo.getGdsApprove());
        gdsInfoDetailRespDTO.setGuidePrice(gdsInfo.getGuidePrice());
        gdsInfoDetailRespDTO.setUrl(gdsInfo.getUrl());
        gdsInfoDetailRespDTO.setTaxId(gdsInfo.getTaxId());
        gdsInfoDetailRespDTO.setSortNo(gdsInfo.getSortNo());
        gdsInfoDetailRespDTO.setShopId(gdsInfo.getShopId());
        gdsInfoDetailRespDTO.setGdsLabel(gdsInfo.getGdsLabel());
        gdsInfoDetailRespDTO.setPutoffTime(gdsInfo.getPutoffTime());
        gdsInfoDetailRespDTO.setPutonTime(gdsInfo.getPutonTime());
        gdsInfoDetailRespDTO.setPostTime(gdsInfo.getPostTime());
        gdsInfoDetailRespDTO.setIfSendscore(gdsInfo.getIfSendscore());
        gdsInfoDetailRespDTO.setIfSalealone(gdsInfo.getIfSalealone());
        gdsInfoDetailRespDTO.setIfRecomm(gdsInfo.getIfRecomm());
        gdsInfoDetailRespDTO.setIfNew(gdsInfo.getIfNew());
        gdsInfoDetailRespDTO.setIfLadderPrice(gdsInfo.getIfLadderPrice());
        gdsInfoDetailRespDTO.setIfFree(gdsInfo.getIfFree());
        gdsInfoDetailRespDTO.setIfEntityCode(gdsInfo.getIfEntityCode());
        gdsInfoDetailRespDTO.setIfSeniorPrice(gdsInfo.getIfSeniorPrice());
        gdsInfoDetailRespDTO.setIfScoreGds(gdsInfo.getIfScoreGds());
        gdsInfoDetailRespDTO.setShipTemplateId(gdsInfo.getShipTemplateId());
        gdsInfoDetailRespDTO.setIfDisperseStock(gdsInfo.getIfDisperseStock());
        gdsInfoDetailRespDTO.setSupplierId(gdsInfo.getSupplierId());
        gdsInfoDetailRespDTO.setMainCatgs(gdsInfo.getMainCatgs());
        gdsInfoDetailRespDTO.setCatlogId(gdsInfo.getCatlogId());
        gdsInfoDetailRespDTO.setIsbn(gdsInfo.getIsbn());
        gdsInfoDetailRespDTO.setCreateStaff(gdsInfo.getCreateStaff());
        gdsInfoDetailRespDTO.setCreateTime(gdsInfo.getCreateTime());
        gdsInfoDetailRespDTO.setUpdateStaff(gdsInfo.getUpdateStaff());
        gdsInfoDetailRespDTO.setUpdateTime(gdsInfo.getUpdateTime());
        gdsInfoDetailRespDTO.setScores(gdsInfo.getScores());
        gdsInfoDetailRespDTO.setMedias(gdsInfo.getMedias());
        gdsInfoDetailRespDTO.setAllPropMaps(gdsInfo.getAllPropMaps());
        gdsInfoDetailRespDTO.setProps(gdsInfo.getProps());
        gdsInfoDetailRespDTO.setIsbn(gdsInfo.getIsbn());
        gdsInfoDetailRespDTO.setSkuInfos(gdsInfo.getSkus());
        gdsInfoDetailRespDTO.setMainCategory(gdsInfo.getMainCategory());
        gdsInfoDetailRespDTO.setMainCatgName(gdsInfo.getMainCatgName());
        gdsInfoDetailRespDTO.setMainPic(gdsInfo.getMainPic());
        gdsInfoDetailRespDTO.setGdsTypeRespDTO(gdsInfo.getGdsTypeRespDTO());
        gdsInfoDetailRespDTO.setRichTextPropMap(gdsInfo.getRichTextPropMap());
        gdsInfoDetailRespDTO.setExt1(gdsInfo.getExt1());
        gdsInfoDetailRespDTO.setExt2(gdsInfo.getExt2());
        gdsInfoDetailRespDTO.setExt3(gdsInfo.getExt3());
        gdsInfoDetailRespDTO.setExt4(gdsInfo.getExt4());
        gdsInfoDetailRespDTO.setExt5(gdsInfo.getExt5());
        return gdsInfoDetailRespDTO;
    }
    
    /**
     * 
     * copySkuInfo2Resp:(将skuInfo对象复制到resp中). <br/> 
     * 
     * @author linwb3
     * @param skuInfo
     * @param skuInfoResp 
     * @since JDK 1.6
     */
    private void copySkuInfo2Resp(GdsSkuInfo skuInfo, GdsSkuInfoRespDTO skuInfoResp) {

        skuInfoResp.setId(skuInfo.getId());
        skuInfoResp.setGdsName(skuInfo.getGdsName());
        skuInfoResp.setGdsSubHead(skuInfo.getGdsSubHead());
        skuInfoResp.setGdsDesc(skuInfo.getGdsDesc());
        skuInfoResp.setGdsPartlist(skuInfo.getGdsPartlist());
        skuInfoResp.setGdsTypeId(skuInfo.getGdsTypeId());
        skuInfoResp.setGdsStatus(skuInfo.getGdsStatus());
        skuInfoResp.setGdsApprove(skuInfo.getGdsApprove());

        // 商品编码
        skuInfoResp.setGdsId(skuInfo.getGdsId());

        // 价格初始化
        skuInfoResp.setCommonPrice(skuInfo.getCommonPrice());
        skuInfoResp.setRealPrice(skuInfo.getCommonPrice());
        skuInfoResp.setDiscountPrice(skuInfo.getCommonPrice());
        if (skuInfo.getGuidePrice() != null) {
            skuInfoResp.setGuidePrice(skuInfo.getGuidePrice().longValue());
        }

        skuInfoResp.setSortNo(skuInfo.getSortNo());
        skuInfoResp.setShopId(skuInfo.getShopId());
        skuInfoResp.setGdsLabel(skuInfo.getGdsLabel());
        skuInfoResp.setPutoffTime(skuInfo.getPutoffTime());
        skuInfoResp.setPutonTime(skuInfo.getPutonTime());
        skuInfoResp.setPostTime(skuInfo.getPostTime());
        skuInfoResp.setIfSendscore(skuInfo.getIfSendscore());
        skuInfoResp.setIfSalealone(skuInfo.getIfSalealone());
        skuInfoResp.setIfRecomm(skuInfo.getIfRecomm());
        skuInfoResp.setIfNew(skuInfo.getIfNew());
        skuInfoResp.setIfLadderPrice(skuInfo.getIfLadderPrice());
        skuInfoResp.setIfFree(skuInfo.getIfFree());
        skuInfoResp.setIfEntityCode(skuInfo.getIfEntityCode());
        skuInfoResp.setIfSeniorPrice(skuInfo.getIfSeniorPrice());
        skuInfoResp.setIfScoreGds(skuInfo.getIfScoreGds());
        skuInfoResp.setShipTemplateId(skuInfo.getShipTemplateId());
        skuInfoResp.setIfDisperseStock(skuInfo.getIfDisperseStock());
        skuInfoResp.setSupplierId(skuInfo.getSupplierId());
        skuInfoResp.setPlatCatgs(skuInfo.getPlatCatgs());
        skuInfoResp.setShopCatgs(skuInfo.getShopCatgs());
        skuInfoResp.setMainCatgs(skuInfo.getMainCatgs());
        skuInfoResp.setCatlogId(skuInfo.getCatlogId());
        skuInfoResp.setIsbn(skuInfo.getIsbn());
        skuInfoResp.setCreateStaff(skuInfo.getCreateStaff());
        skuInfoResp.setCreateTime(skuInfo.getCreateTime());
        skuInfoResp.setUpdateStaff(skuInfo.getUpdateStaff());
        skuInfoResp.setUpdateTime(skuInfo.getUpdateTime());
        skuInfoResp.setAppSpecPrice(skuInfo.getAppSpecPrice());
        skuInfoResp.setExt1(skuInfo.getExt1());
        skuInfoResp.setExt2(skuInfo.getExt2());
        skuInfoResp.setExt3(skuInfo.getExt3());
        skuInfoResp.setExt4(skuInfo.getExt4());
        skuInfoResp.setExt5(skuInfo.getExt5());
    }
//    private Long getTime(Long data){
//        
//        return (System.currentTimeMillis()-data);
//    }
	@Override
	public Long queryGdsVerifyInfoCount(GdsVerifyReqDTO gdsVerifyReqDTO) throws Exception {
		// TODO Auto-generated method stub

        GdsVerifyShopIdxCriteria gdsVerifyShopIdxCriteria = new GdsVerifyShopIdxCriteria();
        initGdsVerifyParam(gdsVerifyShopIdxCriteria, gdsVerifyReqDTO);
       Long count = gdsVerifyShopIdxMapper.countByExample((GdsVerifyShopIdxCriteria) gdsVerifyShopIdxCriteria);

        return count;
     
	}
}
