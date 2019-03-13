package com.zengshi.ecp.goods.service.busi.impl.gdsinfo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSkuInfoMapper;
import com.zengshi.ecp.goods.dao.model.GdsCatg2Prop;
import com.zengshi.ecp.goods.dao.model.GdsSku2Prop;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2MediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsCacheUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.facade.interfaces.eventual.IGdsSnapMainTransaction;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoManageSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoManageSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoIDXSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsSkuInfo2MediaSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsSkuInfo2PropSV;
import com.zengshi.ecp.goods.service.busi.interfaces.price.IGdsPriceSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatg2PropSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropSV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 单品业务操作<br>
 * Date:2015年8月29日下午5:42:45 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsSkuInfoManageSVImpl extends AbstractSVImpl implements IGdsSkuInfoManageSV {

    /**
     * 单品序列服务
     */
    @Resource(name = "seq_gds_sku_info")
    private PaasSequence seqGdsSkuInfo;

    /**
     * 单品主表操作Mapper
     */
    @Resource
    private GdsSkuInfoMapper gdsSkuInfoMapper;

    /**
     * 商品索引表操作SV
     */
    @Resource
    private IGdsInfoIDXSV gdsInfoIDXSV;

    /**
     * 商品价格SV
     */
    @Autowired(required = false)
    private IGdsPriceSV gdsPriceSV;

    /**
     * 商品查询SV
     */
    @Resource
    private IGdsInfoQuerySV gdsInfoQuerySV;

    /**
     * 单品查询SV
     */
    @Resource
    private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;

    /**
     * 商品操作SV
     */
    @Autowired(required = false)
    private IGdsInfoManageSV gdsInfoManageSV;

    /**
     * 商品库存SV
     */
    @Resource
    private IGdsStockSV gdsStockSV;

    /**
     * 商品，分类关系SV
     */
    @Resource
    private IGdsCatg2PropSV gdsCatg2PropSV;

    /**
     * 店铺信息SV
     */
    @Resource
    private IShopInfoRSV shopInfoRSV;

    /**
     * 商品属性SV
     */
    @Resource
    private IGdsPropSV gdsPropSV;

    /**
     * 单品图片关系操作SV
     */
    @Resource
    private IGdsSkuInfo2MediaSV gdsSkuInfo2MediaSV;

    /**
     * 单品属性关系操作SV
     */
    @Resource
    private IGdsSkuInfo2PropSV gdsSkuInfo2PropSV;

    /**
     * 商品快照子事务操作
     */
    @Resource(name = "gdsSnapMainTransaction")
    private IGdsSnapMainTransaction gdsSnapMainTransaction;

    /**
     * 
     * TODO 单品保存.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoManageSV#saveGdsSkuInfo(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO)
     */
    @Override
    public Long saveGdsSkuInfo(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) throws BusinessException {
        Long staffId = gdsSkuInfoReqDTO.getStaff().getId();
        // 保存单品信息
        gdsSkuInfoReqDTO.setSkuProps(getSkuProps(gdsSkuInfoReqDTO.getSku2PropReqDTOs()));
        GdsSkuInfo skuInfo = saveSkuInfo(gdsSkuInfoReqDTO);
        Long skuId = skuInfo.getId();
        Long gdsId = skuInfo.getGdsId();
        Long shopId = skuInfo.getShopId();
        // 初始化配置信息
        initSkuIfParam(gdsSkuInfoReqDTO);
        // 保存单品库存
        addGdsSkuInfoStock(gdsSkuInfoReqDTO, skuInfo, skuId);

        // 保存单品属性
        List<GdsSku2PropReqDTO> props = gdsSkuInfoReqDTO.getSku2PropReqDTOs();
        if (CollectionUtils.isNotEmpty(props)) {
            for (GdsSku2PropReqDTO gdsSku2PropReqDTO : props) {
                gdsSku2PropReqDTO.setSkuId(skuId);
                gdsSku2PropReqDTO.setGdsId(gdsId);
                gdsSku2PropReqDTO.setShopId(shopId);
                // 如果是否基本属性为空，则从商品分类属性关系表查询对应的配置
                if (StringUtil.isBlank(gdsSku2PropReqDTO.getIfBasic())) {
                    GdsCatg2PropReqDTO reqDTO = new GdsCatg2PropReqDTO();
                    reqDTO.setCatgCode(gdsSkuInfoReqDTO.getMainCatgs());
                    reqDTO.setPropId(gdsSku2PropReqDTO.getPropId());
                    List<GdsCatg2Prop> catgs = gdsCatg2PropSV.queryConfigedProps(reqDTO);
                    if (CollectionUtils.isNotEmpty(catgs)) {
                        GdsCatg2Prop catg2Prop = catgs.get(0);
                        gdsSku2PropReqDTO.setIfMust(catg2Prop.getIfHaveto());
                        gdsSku2PropReqDTO.setIfBasic(catg2Prop.getIfBasic());
                    }
                } else {
                    gdsSku2PropReqDTO.setIfBasic(gdsSku2PropReqDTO.getIfBasic());
                }
                // 如果没有传类型 则默认为规格属性
                if (StringUtil.isBlank(gdsSku2PropReqDTO.getPropType())) {
                    gdsSku2PropReqDTO.setPropType(GdsConstants.GdsProp.PROP_VALUE_TYPE_1);
                }
                if(StringUtil.isNotBlank(gdsSkuInfoReqDTO.getExt1())){
                	gdsSku2PropReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_OFFLINE);
                }else {
                	gdsSku2PropReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
				}
                gdsSku2PropReqDTO.setStaff(GdsUtils.getStaff(staffId));
                gdsSkuInfo2PropSV.saveSku2Prop(gdsSku2PropReqDTO);
            }
        }

        // 保存单品属性--（继承商品属性）
        List<GdsSku2PropReqDTO> gdsProps = gdsSkuInfoReqDTO.getGdsProps();
        if (CollectionUtils.isNotEmpty(gdsProps)) {
            for (GdsSku2PropReqDTO gdsSku2PropReqDTO : gdsProps) {
                gdsSku2PropReqDTO.setSkuId(skuId);
                gdsSku2PropReqDTO.setGdsId(gdsId);
                gdsSku2PropReqDTO.setShopId(shopId);
                if(StringUtil.isNotBlank(gdsSkuInfoReqDTO.getExt1())){
                	gdsSku2PropReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_OFFLINE);
                }else {
                	gdsSku2PropReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
				}
                gdsSku2PropReqDTO.setStaff(GdsUtils.getStaff(staffId));
                gdsSkuInfo2PropSV.saveSku2Prop(gdsSku2PropReqDTO);
            }
        }

        // 保存单品索引表
        List<GdsSku2PropReqDTO> allProps = new ArrayList<GdsSku2PropReqDTO>();
        if (CollectionUtils.isNotEmpty(props)) {
            allProps.addAll(props);
        }
        if (CollectionUtils.isNotEmpty(gdsProps)) {
            allProps.addAll(gdsProps);
        }
        gdsInfoIDXSV.addSkuInfoIDX(skuInfo, allProps);

        // 保存单品价格
        List<GdsSku2PriceReqDTO> prices = gdsSkuInfoReqDTO.getSku2PriceReqDTOs();
        if (CollectionUtils.isNotEmpty(prices)) {
            for (GdsSku2PriceReqDTO gdsSku2PriceReqDTO : prices) {
                gdsSku2PriceReqDTO.setStaff(gdsSkuInfoReqDTO.getStaff());
                gdsSku2PriceReqDTO.setSkuId(skuId);
                gdsSku2PriceReqDTO.setGdsId(skuInfo.getGdsId());
                gdsSku2PriceReqDTO.setShopId(skuInfo.getShopId());
                gdsSku2PriceReqDTO.setrType(GdsConstants.GdsPrice.GDS_PRICE_RTYPE_SKU);
                if (StringUtil.isEmpty(gdsSku2PriceReqDTO.getPriceTypeCode())) {
                    gdsSku2PriceReqDTO.setPriceTypeCode(GdsConstants.GdsInfo.SKU_PRICE_TYPE_ORDINARY);
                }
                
                //如果不是普通价格则保存到价格表中
                if(!gdsSku2PriceReqDTO.getPriceTypeCode().equals(GdsConstants.GdsInfo.SKU_PRICE_TYPE_ORDINARY)){
                    gdsPriceSV.saveGdsSkuPrice(gdsSku2PriceReqDTO);
                }
            }
        }

        // 保存单品图片
        List<GdsSku2MediaReqDTO> medias = gdsSkuInfoReqDTO.getSku2MediaReqDTOs();
        if (CollectionUtils.isNotEmpty(medias)) {
            for (GdsSku2MediaReqDTO sku2MediaReqDTO : medias) {
                sku2MediaReqDTO.setSkuId(skuId);
                sku2MediaReqDTO.setGdsId(gdsId);
                sku2MediaReqDTO.setShopId(shopId);
                sku2MediaReqDTO.setStaff(GdsUtils.getStaff(staffId));
                gdsSkuInfo2MediaSV.saveSku2Media(sku2MediaReqDTO);

            }
        }

        return skuId;
    }

    /**
     * 
     * TODO 编辑单品信息.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoManageSV#editGdsSkuInfo(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO)
     */
    @Override
    public Long editGdsSkuInfoAndReference(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) throws BusinessException {
        // 编辑单品信息
        GdsSkuInfo skuInfo = new GdsSkuInfo();
        // 获取单品属性串
        skuInfo.setSkuProps(getSkuProps(gdsSkuInfoReqDTO.getSku2PropReqDTOs()));
        gdsSkuInfoReqDTO.setSkuProps(getSkuProps(gdsSkuInfoReqDTO.getSku2PropReqDTOs()));
        ObjectCopyUtil.copyObjValue(gdsSkuInfoReqDTO, skuInfo, null, false);

        Long skuId = gdsSkuInfoReqDTO.getId();
        Long gdsId = gdsSkuInfoReqDTO.getGdsId();
        Long shopId = gdsSkuInfoReqDTO.getShopId();
        Long staffId = gdsSkuInfoReqDTO.getStaff().getId();
        // 如果单品id不存在，则为新增
        if (skuId == null) {
            return saveGdsSkuInfo(gdsSkuInfoReqDTO);
        }

        // 编辑单品信息
        GdsSkuInfo now = editSkuInfo(gdsSkuInfoReqDTO);

        // 编辑单品属性
        List<GdsSku2PropReqDTO> props = gdsSkuInfoReqDTO.getSku2PropReqDTOs();
        List<GdsSku2PropReqDTO> gdsProps = gdsSkuInfoReqDTO.getGdsProps();
        List<GdsSku2Prop> beforeProps = gdsSkuInfo2PropSV.getSkuPropsModelBySkuId(gdsId, skuId, null);
        compareProps(gdsProps, props, beforeProps);

        if (CollectionUtils.isNotEmpty(props) || CollectionUtils.isNotEmpty(gdsProps)) {
            // 删除属性
            delSku2Prop(skuId, gdsId);
        }

        if (CollectionUtils.isNotEmpty(props)) {
            for (int i = 0; i < props.size(); i++) {
                GdsSku2PropReqDTO gdsSku2PropReqDTO = props.get(i);
                gdsSku2PropReqDTO.setSkuId(skuId);
                gdsSku2PropReqDTO.setGdsId(gdsId);
                gdsSku2PropReqDTO.setShopId(shopId);
                gdsSku2PropReqDTO.setGdsStatus(now.getGdsStatus());
                gdsSku2PropReqDTO.setStaff(GdsUtils.getStaff(staffId));
                gdsSkuInfo2PropSV.saveSku2Prop(gdsSku2PropReqDTO);
            }
        }

        if (CollectionUtils.isNotEmpty(gdsProps)) {
            for (GdsSku2PropReqDTO gdsSku2PropReqDTO : gdsProps) {
                gdsSku2PropReqDTO.setSkuId(skuId);
                gdsSku2PropReqDTO.setGdsId(gdsId);
                gdsSku2PropReqDTO.setShopId(shopId);
                gdsSku2PropReqDTO.setGdsStatus(now.getGdsStatus());
                gdsSku2PropReqDTO.setStaff(GdsUtils.getStaff(staffId));
                gdsSkuInfo2PropSV.saveSku2Prop(gdsSku2PropReqDTO);
            }
        }
        // 编辑单品价格
        List<GdsSku2PriceReqDTO> prices = gdsSkuInfoReqDTO.getSku2PriceReqDTOs();
        if (CollectionUtils.isNotEmpty(prices)) {
			for (GdsSku2PriceReqDTO gdsSku2PriceReqDTO : prices) {
				if (StringUtil.isBlank(gdsSku2PriceReqDTO.getPriceTypeCode())
						|| gdsSku2PriceReqDTO.getPriceTypeCode().equals(
								GdsConstants.GdsInfo.SKU_PRICE_TYPE_ORDINARY)) {
					
					//不处理

				} else {
					gdsSku2PriceReqDTO.setSkuId(skuId);
					gdsSku2PriceReqDTO.setGdsId(gdsId);
					gdsSku2PriceReqDTO.setShopId(shopId);
					gdsSku2PriceReqDTO
							.setrType(GdsConstants.GdsPrice.GDS_PRICE_RTYPE_SKU);
					gdsPriceSV.delAllPrice(gdsSku2PriceReqDTO);
					gdsPriceSV.saveGdsSkuPrice(gdsSku2PriceReqDTO);
				}
            }
        }

        // 编辑单品图片
        List<GdsSku2MediaReqDTO> medias = gdsSkuInfoReqDTO.getSku2MediaReqDTOs();
        if (CollectionUtils.isNotEmpty(medias)) {
            delSku2Media(skuId, gdsId);
            for (GdsSku2MediaReqDTO sku2MediaReqDTO : medias) {
                sku2MediaReqDTO.setSkuId(skuId);
                sku2MediaReqDTO.setGdsId(gdsId);
                sku2MediaReqDTO.setShopId(shopId);
                sku2MediaReqDTO.setStaff(GdsUtils.getStaff(staffId));
                gdsSkuInfo2MediaSV.saveSku2Media(sku2MediaReqDTO);
            }
        }

        // 编辑单品索引表
        List<GdsSku2PropReqDTO> allProps = new ArrayList<GdsSku2PropReqDTO>();
        if (CollectionUtils.isNotEmpty(props)) {
            allProps.addAll(props);
        }
        if (CollectionUtils.isNotEmpty(gdsProps)) {
            allProps.addAll(gdsProps);
        }
        // 保存商品状态
        for (GdsSku2PropReqDTO gdsSku2PropReqDTO : allProps) {
            gdsSku2PropReqDTO.setGdsStatus(now.getGdsStatus());
        }
        gdsInfoIDXSV.editSkuInfoIDX(null, allProps);

        // 删除单品主图缓存
        GdsCacheUtil.delCacheItem(GdsConstants.GdsInfoCacheKey.SKU_MAINPIC_CACHE_KEY_PREFIX + skuId);

        return skuId;
    }

    private void delSku2Media(Long skuId, Long gdsId) {
        GdsSku2MediaReqDTO reqDTO = new GdsSku2MediaReqDTO();
        reqDTO.setSkuId(skuId);
        reqDTO.setGdsId(gdsId);
        gdsSkuInfo2MediaSV.delSku2Media(reqDTO);
    }

    /**
     * 
     * TODO 编辑单品信息.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoManageSV#editGdsSkuInfo(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO)
     */
    @Override
    public Long editGdsSkuInfo(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) throws BusinessException {
        // 编辑单品信息
        editSkuInfo(gdsSkuInfoReqDTO);
        return gdsSkuInfoReqDTO.getId();
    }

    /**
     * 
     * TODO 删除单品信息以及关联单品信息.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoManageSV#delGdsSkuInfo(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO)
     */
    @Override
    public GdsSkuInfo deleteGdsSkuInfo(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) throws BusinessException {
        Long skuId = gdsSkuInfoReqDTO.getId();
        Long gdsId = gdsSkuInfoReqDTO.getGdsId();
        GdsSkuInfo skuInfo = new GdsSkuInfo();
        ObjectCopyUtil.copyObjValue(gdsSkuInfoReqDTO, skuInfo, null, true);

        // 删除单品信息
        delSkuInfo(gdsSkuInfoReqDTO, skuId);
        // 删除单品价格
        delSku2Price(gdsSkuInfoReqDTO, skuId);
        // 删除单品属性
        delSku2Prop(skuId, gdsId);
        // 删除单品媒体
        delSku2Media(skuId, gdsId);
        // 删除单品索引表
        List<GdsSku2Prop> sku2prop = gdsSkuInfo2PropSV.getSkuPropsModelBySkuId(gdsId, skuId, null);
        List<GdsSku2PropReqDTO> allProps = GdsUtils.doConvert(sku2prop, GdsSku2PropReqDTO.class);
        if (CollectionUtils.isNotEmpty(allProps)) {
            for (GdsSku2PropReqDTO gdsSku2PropReqDTO : allProps) {
                gdsSku2PropReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
            }
        }
        GdsSkuInfoReqDTO query = new GdsSkuInfoReqDTO();
        query.setId(skuId);
        GdsSkuInfo before = gdsSkuInfoQuerySV.queryGdsSkuInfo(query);
        if (null != before) {
        	before.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
        }
        ObjectCopyUtil.copyObjValue(skuInfo, before, null, false);
        gdsInfoIDXSV.editSkuInfoIDX(before, allProps);
        delSkuInfoCache(skuId);
        return before;
    }

    @Override
    public GdsSkuInfo executeSkuShelves(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) throws BusinessException {
        GdsSkuInfoReqDTO req = new GdsSkuInfoReqDTO();
        req.setId(gdsSkuInfoReqDTO.getId());
        GdsSkuInfoRespDTO skuInfo = gdsSkuInfoQuerySV.querySkuInfoByOptions(req);

        String GdsStatus = gdsSkuInfoReqDTO.getGdsStatus();
        Long gdsId = skuInfo.getGdsId();
        Long skuId = skuInfo.getId();
        Long shopId = skuInfo.getShopId();
        Long catlogId = skuInfo.getCatlogId();

        if (gdsId == null || gdsId.longValue() == 0L) {

            gdsId = skuInfo.getGdsId();
            shopId = skuInfo.getShopId();
        }

        boolean isUpdate = true;
        if (GdsUtils.isOnShelves(GdsStatus)) {
            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
            gdsInfoReqDTO.setId(gdsId);
            gdsInfoReqDTO.setGdsStatus(GdsStatus);
            gdsInfoReqDTO.setShopId(shopId);
            gdsInfoReqDTO.setStaff(gdsSkuInfoReqDTO.getStaff());
            gdsInfoReqDTO.setIsUpdateShipTemplate(false);
            gdsInfoManageSV.editGdsInfo(gdsInfoReqDTO);
        } else if (GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES.equals(GdsStatus)) {
            List<GdsSkuInfo> skuIds = gdsInfoQuerySV.querySkuInfosByGdsId(gdsId);
            if (CollectionUtils.isNotEmpty(skuIds)) {
                for (GdsSkuInfo sku : skuIds) {
                    if (skuId.longValue() != sku.getId().longValue() && GdsUtils.isOnShelves(sku.getGdsStatus())) {
                        isUpdate = false;
                    }
                }

                if (isUpdate) {
                    GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                    gdsInfoReqDTO.setId(gdsId);
                    gdsInfoReqDTO.setGdsStatus(GdsStatus);
                    gdsInfoReqDTO.setShopId(shopId);
                    gdsInfoReqDTO.setStaff(gdsSkuInfoReqDTO.getStaff());
                    gdsInfoReqDTO.setIsUpdateShipTemplate(false);
                    gdsInfoManageSV.editGdsInfo(gdsInfoReqDTO);
                    // 完全下架，则删除
                    GdsUtils.sendGdsIndexMsg(GdsStatus, "T_GDS_INFO", MODULE, gdsId, catlogId);
                }
            }
        }
        GdsSkuInfo skuObj = executeSkuShelvesOnly(gdsSkuInfoReqDTO);
        if (GdsUtils.isOnShelves(GdsStatus)) {
            // 上架，则更新索引
            GdsUtils.sendGdsIndexMsg(GdsStatus, "T_GDS_INFO", MODULE, gdsId, catlogId);
        } else if (!isUpdate) {
            // 不完全下架，则更新
            GdsUtils.sendGdsIndexMsg(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES, "T_GDS_INFO", MODULE, gdsId, catlogId);
        }
        // 删除商品缓存
        delGdsInfoCache(gdsId);
        // 删除单品缓存
        delSkuInfoCache(skuId);
        return skuObj;
    }

    /**
     * 
     * TODO 单品上下架元方法.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoManageSV#executeSkuShelvesOnly(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO)
     */
    @Override
    public GdsSkuInfo executeSkuShelvesOnly(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) throws BusinessException {
        Long staffId = gdsSkuInfoReqDTO.getStaff().getId();
        GdsSkuInfo skuInfo = new GdsSkuInfo();
        skuInfo.setId(gdsSkuInfoReqDTO.getId());
        skuInfo.setGdsStatus(gdsSkuInfoReqDTO.getGdsStatus());
        skuInfo.setGdsId(gdsSkuInfoReqDTO.getGdsId());
        skuInfo.setShopId(gdsSkuInfoReqDTO.getShopId());

        if (gdsSkuInfoReqDTO.getGdsApprove() != null) {
            skuInfo.setGdsApprove(gdsSkuInfoReqDTO.getGdsApprove());
        }

        GdsSkuInfoReqDTO query = new GdsSkuInfoReqDTO();
        query.setId(gdsSkuInfoReqDTO.getId());
        GdsSkuInfo before = gdsSkuInfoQuerySV.querySkuInfoFromDB(query);
        if (GdsUtils.isOnShelves(gdsSkuInfoReqDTO.getGdsStatus())) {
            // 上架校验
        	BaseSysCfgRespDTO cfg=SysCfgUtil.fetchSysCfg(GdsConstants.GdsInfo.IF_CHECK_ONSHEVLES);
        	if(cfg == null || GdsUtils.isEqualsValid(cfg.getParaValue())){
                onShelvesCheck(gdsSkuInfoReqDTO, before);
        	}
        }

        skuInfo = editSkuInfo(skuInfo, before, gdsSkuInfoReqDTO.getStaff().getId());

        // 更新属性关系（更新状态）
        List<GdsSku2Prop> propResp = gdsSkuInfo2PropSV.getSkuPropsModelBySkuId(before.getGdsId(), before.getId(), null);
        List<GdsSku2PropReqDTO> propReq = GdsUtils.doConvert(propResp, GdsSku2PropReqDTO.class);
        if (CollectionUtils.isNotEmpty(propReq)) {
            for (GdsSku2PropReqDTO gdsSku2PropReqDTO : propReq) {
                gdsSku2PropReqDTO.setGdsStatus(gdsSkuInfoReqDTO.getGdsStatus());
                gdsSku2PropReqDTO.setStaff(GdsUtils.getStaff(staffId));
                gdsSku2PropReqDTO.setSkuId(skuInfo.getId());
                gdsSku2PropReqDTO.setGdsId(skuInfo.getGdsId());
                gdsSkuInfo2PropSV.updateSku2PropGdsStatus(gdsSku2PropReqDTO);
            }
            gdsInfoIDXSV.editSkuInfoIDX(null, propReq);
        }

        if (GdsUtils.isOnShelves(gdsSkuInfoReqDTO.getGdsStatus())) {
            // 上架所有快照保存
            gdsSnapMainTransaction.executeSkuOnShelves(gdsSkuInfoReqDTO);
        }
        return skuInfo;
    }

    /**
     * 
     * onShelvesCheck:(上架校验). <br/>
     * 
     * @author linwb3
     * @param gdsSkuInfoReqDTO
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public void onShelvesCheck(GdsSkuInfoReqDTO gdsSkuInfoReqDTO, GdsSkuInfo before) throws BusinessException {

        // if(GdsUtils.isOnShelves(before.getGdsStatus())){
        // throw new BusinessException(
        // GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210012,
        // new String[] {before.getId().toString(), "已经是上架状态，不需要再上架" });
        // }

        // 删除商品不能上架
        if (GdsUtils.isDelete(before.getGdsStatus())) {
            throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210006, new String[] { before.getId().toString(), "该商品已删除，不能上架" });
        }

        // 校验店铺状态
        ShopInfoResDTO shop = shopInfoRSV.findShopInfoByShopID(before.getShopId());
        if (shop == null) {
            throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210006, new String[] { before.getId().toString(), "找不到对应商品的店铺" });
        } else {
            if (GdsUtils.isEqualsInvalid(shop.getShopStatus())) {
                throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210006, new String[] { before.getId().toString(), "店铺已失效，不能上架" });
            }
        }

        // 积分商品上架不校验价格
        if (GdsUtils.isEqualsInvalidOrNULL(before.getIfScoreGds())) {
        	if(before.getCommonPrice() == null ||before.getCommonPrice() <= 0){
        		
            Long skuId = gdsSkuInfoReqDTO.getId();
            GdsSku2PriceReqDTO gdsSku2PriceReqDTO = new GdsSku2PriceReqDTO();
            gdsSku2PriceReqDTO.setSkuId(skuId);
            gdsSku2PriceReqDTO.setGdsId(gdsSkuInfoReqDTO.getGdsId());
            gdsSku2PriceReqDTO.setIfPrice(true);
            List<GdsSku2PriceRespDTO> priceRelations = gdsPriceSV.queryGdsSkuPriceList(gdsSku2PriceReqDTO);
            if (CollectionUtils.isEmpty(priceRelations)) {
                throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210006, new String[] { skuId.toString(), "没有配置价格，无法上架" });
            } else {
                int count = 0;
                for (GdsSku2PriceRespDTO gdsSku2PriceRespDTO : priceRelations) {
                	
                    Long price = gdsSku2PriceRespDTO.getPrice().getPrice();
                    if (price != null && price.longValue() != 0) {
                        count++;
                    }
                }
                if (count == 0) {
                    throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210006, new String[] { skuId.toString(), "配置的价格为0，无法上架" });
                }
            }
        }
        }

    }

    private String getSkuProps(List<GdsSku2PropReqDTO> props) {
        StringBuffer skuProps = new StringBuffer();
        if (CollectionUtils.isNotEmpty(props)) {
            for (int i = 0; i < props.size(); i++) {
                GdsSku2PropReqDTO gdsSku2PropReqDTO = props.get(i);
                if (i == props.size() - 1) {
                    skuProps.append(gdsSku2PropReqDTO.getPropValue());
                } else {
                    skuProps.append(gdsSku2PropReqDTO.getPropValue()).append(GdsConstants.GdsInfo.SKU_PROP_STR_SPILT);
                }
            }
        }
        return skuProps.toString();
    }

    /**
     * 
     * saveSkuInfo:(保存单品信息). <br/>
     * 
     * @author linwb3
     * @param gdsSkuInfoReqDTO
     * @return
     * @since JDK 1.6
     */
    private GdsSkuInfo saveSkuInfo(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) {
        Long skuId = seqGdsSkuInfo.nextValue();
        GdsSkuInfo gdsSkuInfo = new GdsSkuInfo();
        ObjectCopyUtil.copyObjValue(gdsSkuInfoReqDTO, gdsSkuInfo, null, false);
        gdsSkuInfo.setId(skuId);
        gdsSkuInfo.setCreateTime(DateUtil.getSysDate());
        gdsSkuInfo.setCreateStaff(gdsSkuInfoReqDTO.getStaff().getId());
        gdsSkuInfo.setUpdateTime(DateUtil.getSysDate());
        gdsSkuInfo.setUpdateStaff(gdsSkuInfoReqDTO.getStaff().getId());
        if(StringUtil.isNotBlank(gdsSkuInfoReqDTO.getExt1())){
        	 gdsSkuInfo.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_OFFLINE);
        }else {
        	 gdsSkuInfo.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
		}
        gdsSkuInfo.setGdsApprove(GdsConstants.GdsInfo.GDS_APPROVE_PASS);
        gdsSkuInfoMapper.insertSelective(gdsSkuInfo);
        return gdsSkuInfo;
    }

    /**
     * delSkuInfo:(逻辑删除单品信息表内容). <br/>
     * 
     * @author linwb3
     * @param gdsSkuInfoReqDTO
     * @param skuId
     * @since JDK 1.6
     */
    private void delSkuInfo(GdsSkuInfoReqDTO gdsSkuInfoReqDTO, Long skuId) {
        GdsSkuInfo gdsSkuInfo = new GdsSkuInfo();
        gdsSkuInfo.setId(skuId);
        gdsSkuInfo.setUpdateStaff(gdsSkuInfoReqDTO.getStaff().getId());
        gdsSkuInfo.setUpdateTime(DateUtil.getSysDate());
        gdsSkuInfo.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
        gdsSkuInfoMapper.updateByPrimaryKeySelective(gdsSkuInfo);
    }

    /**
     * 
     * delSku2Prop:(逻辑删除单品属性关系信息). <br/>
     * 
     * @author linwb3
     * @param skuId
     * @since JDK 1.6
     */
    private void delSku2Prop(Long skuId, Long gdsId) {
        GdsSku2PropReqDTO reqDTO = new GdsSku2PropReqDTO();
        reqDTO.setGdsId(gdsId);
        reqDTO.setSkuId(skuId);
        gdsSkuInfo2PropSV.delSku2Prop(reqDTO);
    }

    /**
     * 
     * delSku2Price:(逻辑删除单品价格关系). <br/>
     * 
     * @author linwb3
     * @param skuId
     * @since JDK 1.6
     */
    private void delSku2Price(GdsSkuInfoReqDTO gdsSkuInfoReqDTO, Long skuId) {
        // 删除单品价格关系
        GdsSku2PriceReqDTO priceReq = new GdsSku2PriceReqDTO();
        priceReq.setSkuId(skuId);
        priceReq.setPriceType(GdsConstants.GdsPrice.GDS_PRICE_RTYPE_SKU);
        gdsPriceSV.delAllPrice(priceReq);
    }

    /**
     * 
     * saveSkuInfo:(保存单品信息). <br/>
     * 
     * @author linwb3
     * @param gdsSkuInfoReqDTO
     * @return
     * @since JDK 1.6
     */
    private GdsSkuInfo editSkuInfo(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) {
        GdsSkuInfo gdsSkuInfo = new GdsSkuInfo();
        ObjectCopyUtil.copyObjValue(gdsSkuInfoReqDTO, gdsSkuInfo, null, false);
        return editSkuInfo(gdsSkuInfo, null, gdsSkuInfoReqDTO.getStaff().getId());
    }

    /**
     * 
     * saveSkuInfo:(保存单品信息). <br/>
     * 
     * @author linwb3
     * @param gdsSkuInfoReqDTO
     * @return
     * @since JDK 1.6
     */
    private GdsSkuInfo editSkuInfo(GdsSkuInfo gdsSkuInfo, GdsSkuInfo before, Long staffId) {
        Long skuId = gdsSkuInfo.getId();
        gdsSkuInfo.setId(gdsSkuInfo.getId());
        gdsSkuInfo.setUpdateTime(DateUtil.getSysDate());
        gdsSkuInfo.setUpdateStaff(staffId);
        gdsSkuInfoMapper.updateByPrimaryKeySelective(gdsSkuInfo);

        // 查询旧数据，用新数据刷新旧数据，然后更新缓存
        GdsSkuInfoReqDTO gdsSkuInfoReq = new GdsSkuInfoReqDTO();
        gdsSkuInfoReq.setId(skuId);
        if (before == null) {
            before = gdsSkuInfoQuerySV.querySkuInfoFromDB(gdsSkuInfoReq);
        }
        ObjectCopyUtil.copyObjValue(gdsSkuInfo, before, null, false);
        
        // 更新单品主表索引表
        gdsInfoIDXSV.editSkuInfoIDX(before, null);
        return before;
    }

    /**
     * 
     * addGdsSkuInfoStock:(单品库存录入). <br/>
     * 
     * @author linwb3
     * @param gdsSkuInfoReqDTO
     * @param skuInfo
     * @param skuId
     * @since JDK 1.6
     */
    private void addGdsSkuInfoStock(GdsSkuInfoReqDTO gdsSkuInfoReqDTO, GdsSkuInfo skuInfo, Long skuId) {
        if (!GdsUtils.isEqualsInvalidOrNULL(gdsSkuInfoReqDTO.getIfDisperseStock())) {
            List<StockInfoReqDTO> stocks = gdsSkuInfoReqDTO.getStocks();
            for (StockInfoReqDTO stockInfoDTO : stocks) {
                // 分仓库存
                stockInfoDTO.setSkuId(skuId);
                stockInfoDTO.setShopId(skuInfo.getShopId());
                stockInfoDTO.setGdsId(skuInfo.getGdsId());
                stockInfoDTO.setCompanyId(gdsSkuInfoReqDTO.getCompanyId());
                stockInfoDTO.setCatgCode(skuInfo.getMainCatgs());
                stockInfoDTO.setStockType(stockInfoDTO.getStockType());
                stockInfoDTO.setRepType(GdsConstants.GdsStock.STOCK_REP_TYPE_SEPERATE);
                stockInfoDTO.setStaff(gdsSkuInfoReqDTO.getStaff());
                stockInfoDTO.setStaffId(gdsSkuInfoReqDTO.getStaff().getId());
                stockInfoDTO.setStockRepAdapts(stockInfoDTO.getStockRepAdapts());
                stockInfoDTO.setTypeId(gdsSkuInfoReqDTO.getGdsTypeId());
                // 商品名称
                stockInfoDTO.setGdsName(gdsSkuInfoReqDTO.getGdsName());
                // 产品编号
                stockInfoDTO.setProductNo(gdsSkuInfoReqDTO.getIsbn());
                // 分类族谱.
                stockInfoDTO.setCatgPath(gdsSkuInfoReqDTO.getPlatCatgs());
                if (stockInfoDTO.getTurnCount() == null) {
                    stockInfoDTO.setTurnCount(0L);
                }
                if (stockInfoDTO.getRealCount() == null) {
                    stockInfoDTO.setRealCount(0L);
                }
                if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getExt1())) {
                    stockInfoDTO.setExt1(gdsSkuInfoReqDTO.getExt1());
                }
                try {
                    gdsStockSV.addStockInfoForInput(stockInfoDTO);
                } catch (Exception e) {
                    throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210004);
                }

            }
        } else {
            StockInfoReqDTO stockInfoDTO = new StockInfoReqDTO();
            stockInfoDTO.setSkuId(skuId);
            stockInfoDTO.setShopId(skuInfo.getShopId());
            stockInfoDTO.setGdsId(skuInfo.getGdsId());
            stockInfoDTO.setCompanyId(gdsSkuInfoReqDTO.getCompanyId());
            stockInfoDTO.setCatgCode(skuInfo.getMainCatgs());
            stockInfoDTO.setStockType(GdsConstants.GdsStock.STOCK_INFO_TYPE_PUBLIC);
            stockInfoDTO.setRepType(GdsConstants.GdsStock.STOCK_REP_TYPE_PUBLIC);
            stockInfoDTO.setTurnCount(gdsSkuInfoReqDTO.getRealCount());
            stockInfoDTO.setRealCount(gdsSkuInfoReqDTO.getRealCount());
            stockInfoDTO.setStaff(gdsSkuInfoReqDTO.getStaff());
            stockInfoDTO.setStaffId(gdsSkuInfoReqDTO.getStaff().getId());
            stockInfoDTO.setTypeId(gdsSkuInfoReqDTO.getGdsTypeId());
            if (stockInfoDTO.getTurnCount() == null) {
                stockInfoDTO.setTurnCount(0L);
            }
            if (stockInfoDTO.getRealCount() == null) {
                stockInfoDTO.setRealCount(0L);
            }
            // 商品名称
            stockInfoDTO.setGdsName(gdsSkuInfoReqDTO.getGdsName());
            // 产品编号
            stockInfoDTO.setProductNo(gdsSkuInfoReqDTO.getIsbn());
            // 分类族谱.
            stockInfoDTO.setCatgPath(gdsSkuInfoReqDTO.getPlatCatgs());
            if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getExt1())) {
                stockInfoDTO.setExt1(gdsSkuInfoReqDTO.getExt1());
            }
            try {
                gdsStockSV.addStockInfoForInput(stockInfoDTO);
            } catch (Exception e) {
                LogUtil.error(MODULE, "商品录入库存异常", e);
                throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210004);
            }
        }
    }

    /**
     * 
     * compareProps:(比较属性，如果本次属性没有并且之前有，则继续保留). <br/>
     * 
     * @author linwb3
     * @param props
     * @param skuParams
     * @param beforeProps
     * @since JDK 1.6
     */
    private void compareProps(List<GdsSku2PropReqDTO> props, List<GdsSku2PropReqDTO> skuParams, List<GdsSku2Prop> beforeProps) {

        if (CollectionUtils.isNotEmpty(beforeProps)) {
            // 遍历之前的所有属性
            for (GdsSku2Prop gdsGds2Prop : beforeProps) {

                // 默认当前属性在本次编辑的传入属性中不存在
                boolean in = false;
                Long propId = gdsGds2Prop.getPropId();

                // 规格属性编辑必须删除，所以不做比较
                if (GdsConstants.GdsProp.PROP_TYPE_1.equals(gdsGds2Prop.getPropType())) {
                    continue;
                }

                // 判断属性是否存在于当前的商品属性
                if (CollectionUtils.isNotEmpty(props)) {
                    for (GdsSku2PropReqDTO gdsGds2PropReqDTO : props) {
                        Long nowPropId = gdsGds2PropReqDTO.getPropId();
                        // 如果存在相同属性id,则认为 该属性属于本次编辑范围，不做保留
                        if (nowPropId.longValue() == propId.longValue()) {
                            in = true;
                            continue;
                        }
                    }
                    if (in) {
                        continue;
                    }
                }

                if (CollectionUtils.isNotEmpty(skuParams)) {

                    // 判断属性是否存在于当前的单品属性
                    for (GdsSku2PropReqDTO gdsGds2PropReqDTO : skuParams) {
                        Long nowPropId = gdsGds2PropReqDTO.getPropId();
                        // 如果存在相同属性id,则认为 该属性属于本次编辑范围，不做保留
                        if (nowPropId.longValue() == propId.longValue()) {
                            in = true;
                            continue;
                        }
                    }

                }

                if (in) {
                    continue;
                } else {
                    // 如果属性不存在于本次编辑的属性范围内，则直接保留
                    GdsSku2PropReqDTO addInfo = new GdsSku2PropReqDTO();
                    ObjectCopyUtil.copyObjValue(gdsGds2Prop, addInfo, null, false);
                    props.add(addInfo);
                }

            }
        }
    }

    private void delGdsInfoCache(Long gdsId) {
        // 删除商品主图缓存
        try {
            CacheUtil.delItem(GdsConstants.GdsInfoCacheKey.GDS_MAINPIC_CACHE_KEY_PREFIX + gdsId);
        } catch (Exception e) {
            LogUtil.error(MODULE, "del gdsInfo main pic cache failed! ! please check  Cache Server!", e);
        }
        // 删除商品信息缓存
        try {
            CacheUtil.delItem(GdsConstants.GdsInfoCacheKey.GDS_CACHE_KEY_PREFIX + gdsId);
        } catch (Exception e) {
            LogUtil.error(MODULE, "edit gdsInfo cache failed! please check  Cache Server!", e);
        }
    }

    private void delSkuInfoCache(Long skuId) {
        try {
            CacheUtil.delItem(GdsConstants.GdsInfoCacheKey.SKU_CACHE_KEY_PREFIX + skuId);
        } catch (Exception e) {
            LogUtil.error(MODULE, "delete skuInfo cache failed! please check  Cache Server!", e);
        }
    }

    private GdsSkuInfoReqDTO initSkuIfParam(GdsSkuInfoReqDTO skuInfoReq) {

        if (StringUtil.isBlank(skuInfoReq.getIfDisperseStock())) {
            skuInfoReq.setIfDisperseStock(GdsConstants.Commons.STATUS_INVALID);
        }
        if (StringUtil.isBlank(skuInfoReq.getIfEntityCode())) {
            skuInfoReq.setIfEntityCode(GdsConstants.Commons.STATUS_INVALID);
        }
        if (StringUtil.isBlank(skuInfoReq.getIfFree())) {
            skuInfoReq.setIfFree(GdsConstants.Commons.STATUS_INVALID);
        }
        if (StringUtil.isBlank(skuInfoReq.getIfLadderPrice())) {
            skuInfoReq.setIfLadderPrice(GdsConstants.Commons.STATUS_INVALID);
        }
        if (StringUtil.isBlank(skuInfoReq.getIfNew())) {
            skuInfoReq.setIfNew(GdsConstants.Commons.STATUS_INVALID);
        }
        if (StringUtil.isBlank(skuInfoReq.getIfRecomm())) {
            skuInfoReq.setIfRecomm(GdsConstants.Commons.STATUS_INVALID);
        }
        if (StringUtil.isBlank(skuInfoReq.getIfSalealone())) {
            skuInfoReq.setIfSalealone(GdsConstants.Commons.STATUS_INVALID);
        }
        if (StringUtil.isBlank(skuInfoReq.getIfScoreGds())) {
            skuInfoReq.setIfScoreGds(GdsConstants.Commons.STATUS_INVALID);
        }
        return skuInfoReq;
    }

}
