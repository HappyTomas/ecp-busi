package com.zengshi.ecp.goods.service.busi.impl.gdsinfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSkuInfoMapper;
import com.zengshi.ecp.goods.dao.model.GdsSku2Prop;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoCriteria;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoShopIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsType;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.CalCatgCustDiscReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaCacheResp;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSku2PropPropIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSkuInfoShopIdxRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2MediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.GdsStockInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.GdsStockRepRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoForGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsCatgCustDiscSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsStockSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoQueryIDXSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsInfo2CatgSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsSkuInfo2MediaSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsSkuInfo2PropSV;
import com.zengshi.ecp.goods.service.busi.interfaces.price.IGdsPriceSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatalog2SiteSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatg2PropSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsTypeSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.distribute.DistributeRuleAssist;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 单品查询服务<br>
 * Date:2015年8月29日下午5:42:58 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsSkuInfoQuerySVImpl extends AbstractSVImpl implements IGdsSkuInfoQuerySV {

    @Resource
    private GdsSkuInfoMapper gdsSkuInfoMapper;

    @Resource
    private IGdsStockSV gdsStockSV;

    @Resource
    private IGdsInfoQueryIDXSV gdsInfoQueryIDXSV;

    @Resource
    private IGdsStockRSV gdsStockRSV;

    @Resource
    private IGdsCategorySV gdsCategorySV;

    @Resource
    private IGdsInfoQuerySV gdsInfoQuerySV;

    @Resource
    private IGdsInfo2CatgSV gdsInfo2CatgSV;

    @Resource
    private IGdsCatg2PropSV gdsCatg2PropSV;

    @Autowired(required = false)
    private IGdsPriceSV gdsPriceSV;

    @Resource
    private IGdsCatalog2SiteSV catalog2SiteSV;

    @Resource
    private IGdsCatgCustDiscSV gdsCatgCustDiscSV;

    @Resource
    private IGdsSkuInfo2PropSV gdsSkuInfo2PropSV;

    @Resource
    private IGdsSkuInfo2MediaSV gdsSkuInfo2MediaSV;
    
    @Resource
    private IGdsTypeSV gdsTypeSV;
    @Resource
    private ICustManageRSV custManageRSV;

    /**
     * 
     * TODO 通过单品信息查询单品.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV#queryGdsSkuInfo(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO)
     */
    @Override
    public GdsSkuInfoRespDTO queryGdsSkuInfoResp(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) throws BusinessException {
        GdsSkuInfo skuInfo = queryGdsSkuInfo(gdsSkuInfoReqDTO);
        GdsSkuInfoRespDTO gdsSkuInfoRespDTO = null;
        if (skuInfo != null) {
            gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
            ObjectCopyUtil.copyObjValue(skuInfo, gdsSkuInfoRespDTO, null, false);
        }
        return gdsSkuInfoRespDTO;
    }

    @Override
    public GdsSkuInfo queryGdsSkuInfo(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) throws BusinessException {

        Long skuId = gdsSkuInfoReqDTO.getId();
        String cacheKey = GdsConstants.GdsInfoCacheKey.SKU_CACHE_KEY_PREFIX + skuId;
        GdsSkuInfo obj = null;
        try {
            obj = (GdsSkuInfo) CacheUtil.getItem(cacheKey);
        } catch (Exception e) {
            LogUtil.error(MODULE, "get skuInfo cache failed! please check  Cache Server!", e);
        }

        if (obj != null) {
            // 如果有查询状态，则匹配状态
            if (StringUtil.isNotEmpty(gdsSkuInfoReqDTO.getGdsStatus()) && gdsSkuInfoReqDTO.getGdsStatus().equals(obj.getGdsStatus())) {
                return obj;
            }
            // 如果没有查询状态，直接返回
            else if (StringUtil.isEmpty(gdsSkuInfoReqDTO.getGdsStatus())) {
                return obj;
            } else {
                return null;
            }
        } else {
            obj = querySkuInfoFromDB(gdsSkuInfoReqDTO);
            if(obj==null){
                LogUtil.error(MODULE, "根据单品编码无法从单品信息主表查询到单品信息："+gdsSkuInfoReqDTO.getId());
                return obj;
            }
            try {
                CacheUtil.addItem(cacheKey, obj, GdsConstants.GdsInfoCacheKey.SKU_CACHE_TIME);
            } catch (Exception e) {
                LogUtil.error(MODULE, "add skuInfo cache failed! please check  Cache Server!", e);
            }
            return obj;
        }
    }

    /**
     * 
     * querySkuInfoFromDB:(查询单品信息 从单品表). <br/>
     * 
     * @author linwb3
     * @param gdsSkuInfoReqDTO
     * @return
     * @since JDK 1.6
     */
    @Override
    public GdsSkuInfo querySkuInfoFromDB(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) {
        GdsSkuInfo obj;
        GdsSkuInfoCriteria skuInfoCriteria = new GdsSkuInfoCriteria();
        GdsSkuInfoCriteria.Criteria criteria = skuInfoCriteria.createCriteria().andIdEqualTo(gdsSkuInfoReqDTO.getId());
        if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getGdsStatus())) {
            criteria.andGdsStatusEqualTo(gdsSkuInfoReqDTO.getGdsStatus());
        }
        List<GdsSkuInfo> gdsSkuInfos = gdsSkuInfoMapper.selectByExample(skuInfoCriteria);
        if (CollectionUtils.isEmpty(gdsSkuInfos)) {
            return null;
        }
        obj = gdsSkuInfos.get(0);
        return obj;
    }

    /**
     * 
     * TODO 通过单品选项查询单品信息（可选）.
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV#querySkuInfoByOptions(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO,
     *      com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption[])
     */
    @Override
    public GdsSkuInfoRespDTO querySkuInfoByOptions(GdsSkuInfoReqDTO skuInfoReqDTO, SkuQueryOption... skuQuerys) throws BusinessException {
        Long skuId = skuInfoReqDTO.getId();
        Long gdsId = skuInfoReqDTO.getGdsId();
        String status = skuInfoReqDTO.getStatus();

        // 必查基础信息
        //Long data5=System.currentTimeMillis();
        GdsSkuInfo skuInfo = queryGdsSkuInfo(skuInfoReqDTO);
        //LogUtil.error("", "查询单品基本信息耗时"+getTime(data5));
        if (skuInfo == null) {
            LogUtil.error(MODULE, "单品-店铺索引表分页后（内部再查询单品主表信息），返回的单品对象为空！原因可能是：内部根据单品编码查询单品信息返回对象为空（主表或缓存）！");
            return null;
        }
        GdsSkuInfoRespDTO skuInfoRespDTO = new GdsSkuInfoRespDTO();
        copySkuInfo2Resp(skuInfo, skuInfoRespDTO);
        // ObjectCopyUtil.copyObjValue(skuInfo, skuInfoRespDTO, null, false);
        gdsId = skuInfo.getGdsId();
        //Long data6=System.currentTimeMillis();
        setGdsTypeAndStatusName(skuInfoRespDTO);
        //LogUtil.error("", "查询单品类型名称分类名称耗时"+getTime(data6));

        if (skuQuerys != null && skuQuerys.length > 0) {
            for (SkuQueryOption skuQueryOption : skuQuerys) {
                if (skuQueryOption.equals(SkuQueryOption.BASIC)) {
                    continue;
                }
                querySkuInfoByOption(skuInfoReqDTO, skuInfoRespDTO, skuId, gdsId, status, skuQueryOption);
            }
        }
        return skuInfoRespDTO;
    }

    /**
     * 
     * querySkuInfoListPageALLDB:(全库逐表扫描单品信息). <br/>
     * 
     * @author linwb3
     * @param gdsInfoReqDTO
     * @return
     * @since JDK 1.6
     */
    @Override
    public PageResponseDTO<GdsSkuInfoRespDTO> querySkuInfoListPageALLDB(GdsSkuInfoReqDTO gdsInfoReqDTO) {
        GdsSkuInfoCriteria gdsinfoCriteria = new GdsSkuInfoCriteria();
        GdsSkuInfoCriteria.Criteria criteria = gdsinfoCriteria.createCriteria();
        if (gdsInfoReqDTO.getShopId() != null) {
            criteria.andShopIdEqualTo(gdsInfoReqDTO.getShopId());
        }
        if (StringUtil.isNotBlank(gdsInfoReqDTO.getGdsStatus())) {
            criteria.andGdsStatusEqualTo(gdsInfoReqDTO.getGdsStatus());
        }
        if (CollectionUtils.isNotEmpty(gdsInfoReqDTO.getGdsStatusArr())) {
            criteria.andGdsStatusIn(gdsInfoReqDTO.getGdsStatusArr());
        }
        if (StringUtil.isNotBlank(gdsInfoReqDTO.getIfScoreGds())) {
            criteria.andIfScoreGdsEqualTo(gdsInfoReqDTO.getIfScoreGds());
        }
        if (StringUtil.isNotBlank(gdsInfoReqDTO.getMainCatgs())) {
            criteria.andMainCatgsEqualTo(gdsInfoReqDTO.getMainCatgs());
        }
        if (CollectionUtils.isNotEmpty(gdsInfoReqDTO.getCatgs())) {
            criteria.andMainCatgsIn(gdsInfoReqDTO.getCatgs());
        }
        if (CollectionUtils.isNotEmpty(gdsInfoReqDTO.getCatalogIds())) {
            criteria.andCatlogIdIn(gdsInfoReqDTO.getCatalogIds());
        }
        if (gdsInfoReqDTO.getGdsTypeId() != null && gdsInfoReqDTO.getGdsTypeId().longValue() != 0L) {
            criteria.andGdsTypeIdEqualTo(gdsInfoReqDTO.getGdsTypeId());
        }

        gdsinfoCriteria.setLimitClauseCount(gdsInfoReqDTO.getPageSize());
        gdsinfoCriteria.setLimitClauseStart(gdsInfoReqDTO.getStartRowIndex());
        gdsinfoCriteria.setOrderByClause(" id asc ");
        DistributeRuleAssist.setTableIndex(gdsInfoReqDTO.getTableIndex());
        PageResponseDTO<GdsSkuInfoRespDTO> pages = super.queryByPagination(gdsInfoReqDTO, gdsinfoCriteria, true, new PaginationCallback<GdsSkuInfo, GdsSkuInfoRespDTO>() {
            @Override
            public List<GdsSkuInfo> queryDB(BaseCriteria criteria) {
                return gdsSkuInfoMapper.selectByExample((GdsSkuInfoCriteria) criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return gdsSkuInfoMapper.countByExample((GdsSkuInfoCriteria) criteria);
            }

            @Override
            public GdsSkuInfoRespDTO warpReturnObject(GdsSkuInfo t) {
                GdsSkuInfoRespDTO dto = new GdsSkuInfoRespDTO();
                ObjectCopyUtil.copyObjValue(t, dto, null, false);
                return dto;
            }

            @Override
            public List<Comparator<GdsSkuInfo>> defineComparators() {
                List<Comparator<GdsSkuInfo>> ls = new ArrayList<Comparator<GdsSkuInfo>>();
                ls.add(new Comparator<GdsSkuInfo>() {

                    @Override
                    public int compare(GdsSkuInfo o1, GdsSkuInfo o2) {
                        return o1.getId().compareTo(o2.getId());
                    }

                });
                return ls;
            }

        });
        DistributeRuleAssist.clearTableIndex();
        return pages;
    }

    @Override
    public List<GdsSkuInfoRespDTO> queryGdsSkuInfoListResp(GdsSkuInfoReqDTO gdsSkuInfoReqDTO, SkuQueryOption... skuQuerys) throws BusinessException {
        List<GdsSkuInfoShopIdx> pages = gdsInfoQueryIDXSV.getGdsSkuInfoByShopId(gdsSkuInfoReqDTO);
        boolean isFullInfo = gdsSkuInfoReqDTO.getFullInfo();
        List<GdsSkuInfoRespDTO> skus = new ArrayList<GdsSkuInfoRespDTO>();
        if (isFullInfo) {
            if (CollectionUtils.isNotEmpty(pages)) {
                for (GdsSkuInfoShopIdx gdsSkuInfoShopIdx : pages) {
                    GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
                    skuInfoReqDTO.setId(gdsSkuInfoShopIdx.getSkuId());
                    skuInfoReqDTO.setGdsId(gdsSkuInfoShopIdx.getGdsId());
                    GdsSkuInfoRespDTO skuInfoRespDTO = querySkuInfoByOptions(skuInfoReqDTO, skuQuerys);
                    skus.add(skuInfoRespDTO);
                }
            }
        } else {
            if (CollectionUtils.isNotEmpty(pages)) {
                for (GdsSkuInfoShopIdx gdsSkuInfoShopIdxRespDTO : pages) {
                    GdsSkuInfoRespDTO skuInfoRespDTO = new GdsSkuInfoRespDTO();
                    ObjectCopyUtil.copyObjValue(gdsSkuInfoShopIdxRespDTO, skuInfoRespDTO, null, false);
                    setGdsTypeAndStatusName(skuInfoRespDTO);
                    skus.add(skuInfoRespDTO);
                }
            }

        }
        return skus;
    }

    /**
     * 
     * TODO getGdsIdsPageByShopId:(通过查询条件查询对应的单品列表). <br/>
     * .
     * 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV#queryGdsSkuInfoPageListResp(com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO,
     *      com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption[])
     */
    @Override
    public PageResponseDTO<GdsSkuInfoRespDTO> queryGdsSkuInfoPageListResp(GdsSkuInfoReqDTO gdsSkuInfoReqDTO, SkuQueryOption... skuQuerys) throws BusinessException {

        // 分页查询
        PageResponseDTO<GdsSkuInfoShopIdxRespDTO> pages = gdsInfoQueryIDXSV.getGdsSkuInfoPageByShopId(gdsSkuInfoReqDTO);

        boolean isFullInfo = gdsSkuInfoReqDTO.getFullInfo();
        List<GdsSkuInfoRespDTO> skus = new ArrayList<GdsSkuInfoRespDTO>();

        if (isFullInfo) {
            if (CollectionUtils.isNotEmpty(pages.getResult())) {
                for (GdsSkuInfoShopIdxRespDTO gdsSkuInfoShopIdxRespDTO : pages.getResult()) {
                    GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
                    skuInfoReqDTO.setId(gdsSkuInfoShopIdxRespDTO.getSkuId());
                    skuInfoReqDTO.setGdsId(gdsSkuInfoShopIdxRespDTO.getGdsId());
                    GdsSkuInfoRespDTO skuInfoRespDTO = querySkuInfoByOptions(skuInfoReqDTO, skuQuerys);
                    skus.add(skuInfoRespDTO);
                }
            }
        } else {
            if (CollectionUtils.isNotEmpty(pages.getResult())) {
                for (GdsSkuInfoShopIdxRespDTO gdsSkuInfoShopIdxRespDTO : pages.getResult()) {
                    GdsSkuInfoRespDTO skuInfoReqDTO = new GdsSkuInfoRespDTO();
                    ObjectCopyUtil.copyObjValue(gdsSkuInfoShopIdxRespDTO, skuInfoReqDTO, null, false);
                    setGdsTypeAndStatusName(skuInfoReqDTO);
                    skus.add(skuInfoReqDTO);
                }
            }
        }
        // 转换列表
        PageResponseDTO<GdsSkuInfoRespDTO> resultPages = new PageResponseDTO<GdsSkuInfoRespDTO>();
        resultPages.setCount(pages.getCount());
        resultPages.setPageCount(pages.getPageCount());
        resultPages.setPageSize(pages.getPageSize());
        resultPages.setPageNo(pages.getPageNo());
        resultPages.setResult(skus);
        return resultPages;
    }

    private GdsMediaRespDTO getMainPicCache(Long skuId) {
        GdsMediaRespDTO gdsMediaRespDTO = null;
        try {
            GdsMediaCacheResp gdsMediaCacheResp = (GdsMediaCacheResp) CacheUtil.getItem(GdsConstants.GdsInfoCacheKey.SKU_MAINPIC_CACHE_KEY_PREFIX + skuId);
            if (gdsMediaCacheResp != null) {
                gdsMediaRespDTO = new GdsMediaRespDTO();
                ObjectCopyUtil.copyObjValue(gdsMediaCacheResp, gdsMediaRespDTO, null, false);
                 gdsMediaRespDTO.setURL(ImageUtil.getImageUrl(gdsMediaCacheResp.getMediaUuid() + "_200x200!"));
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "get gdsInfo mainPic cache failed! please check  Cache Server!", e);
        }
        return gdsMediaRespDTO;
    }

    private void querySkuInfoByOption(GdsSkuInfoReqDTO skuInfoReqDTO, GdsSkuInfoRespDTO skuInfoRespDTO, Long skuId, Long gdsId, String statusTemp, SkuQueryOption skuQueryOption) {
        skuInfoRespDTO.setId(skuId);
        String[] status = null;
        if (StringUtil.isNotBlank(statusTemp)) {
            status = new String[] { statusTemp };
        }
        switch (skuQueryOption) {
        // 查询单品所有信息
        case ALL:
            List<GdsSku2MediaRespDTO> medias = gdsSkuInfo2MediaSV.querySkuMediaBySkuId(skuId, gdsId);
            skuInfoRespDTO.setSku2MediaRespDTOs(medias);
            skuInfoRespDTO.setSku2PriceRespDTOs(getSkuPriceBySkuId(gdsId, skuId, status));
            setSkuProps(skuInfoRespDTO, skuId, skuInfoReqDTO, status);
            setSkuStocksBySkuId(skuInfoRespDTO, status);
            break;
        case CATG:
            setSkuCatgs(skuInfoRespDTO, gdsId);
            break;
        case MAINPIC:
            GdsMediaRespDTO mainPic = gdsSkuInfo2MediaSV.querySkuMainPicBySkuId(skuId, gdsId);
            skuInfoRespDTO.setMainPic(mainPic);
            break;
        // 查询单品所有媒体信息
        case MEDIA:
            //Long data7 = System.currentTimeMillis();
            List<GdsSku2MediaRespDTO> media = gdsSkuInfo2MediaSV.querySkuMediaBySkuId(skuId, gdsId);
            skuInfoRespDTO.setSku2MediaRespDTOs(media);
            //LogUtil.error("", "查询单品图片耗时" + getTime(data7));
            break;
        // 查询单品价格信息
        case PRICE:
            skuInfoRespDTO.setSku2PriceRespDTOs(getSkuPriceBySkuId(gdsId, skuId, status));
            break;
        // 查询单品属性信息
        case PROP:
            setSkuProps(skuInfoRespDTO, skuId, skuInfoReqDTO, status);
            break;
        // 查询单品库存信息
        case STOCK:
            //Long data3 = System.currentTimeMillis();
            setSkuStocksBySkuId(skuInfoRespDTO, status);
            //LogUtil.error("", "查询单品库存耗时" + getTime(data3));
            break;
        case SHOWPRICE:
            setSkuShowPrice(skuInfoReqDTO, skuInfoRespDTO);
            break;
        // 查询单品库存信息
        case SHOWSTOCK:
            //Long data5 = System.currentTimeMillis();
            setSkuShowStocksBySkuId(skuInfoRespDTO, skuInfoReqDTO, status);
            //LogUtil.error("", "查询单品库存耗时" + getTime(data5));
            break;
        // 计算单品的折扣价
        case CAlDISCOUNT:
            //Long data4 = System.currentTimeMillis();
            getDisCountPrice(skuInfoReqDTO, skuInfoRespDTO);
            //LogUtil.error("", "查询单品折扣价格耗时" + getTime(data4));
            break;
        default:
            break;
        }
    }

//    private String getTime(Long data) {
//
//        return (System.currentTimeMillis() - data) + "ms";
//    }

    private void setSkuShowPrice(GdsSkuInfoReqDTO skuInfoReqDTO, GdsSkuInfoRespDTO skuInfoRespDTO) {

        if (StringUtil.isBlank(skuInfoRespDTO.getIfDisperseStock()) || skuInfoRespDTO.getGdsId() == null) {
            GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
            gdsSkuInfoReqDTO.setId(skuInfoRespDTO.getId());
            GdsSkuInfo temp = queryGdsSkuInfo(gdsSkuInfoReqDTO);
            ObjectCopyUtil.copyObjValue(temp, skuInfoRespDTO, null, false);
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("skuId", skuInfoRespDTO.getId());
        params.put("gdsId", skuInfoRespDTO.getGdsId());
        params.put("staffId", skuInfoReqDTO.getStaff().getId());
        params.put("shopId", skuInfoRespDTO.getShopId());
        params.put("amount", 1L);
        params.put("ifLadderPrice", skuInfoRespDTO.getIfLadderPrice());
        params.put("siteId",skuInfoReqDTO.getCurrentSiteId());
        Long price = gdsPriceSV.caculatePrice(params);
        skuInfoRespDTO.setRealPrice(price);
    }

    /**
     * 
     * getSkuPriceBySkuId:(查询 单品/商品 --> 价格行). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @param skuId
     * @param status
     * @return
     * @since JDK 1.6
     */
    private List<GdsSku2PriceRespDTO> getSkuPriceBySkuId(Long gdsId, Long skuId, String... status) {
        GdsSku2PriceReqDTO sku2PriceReqDTO = new GdsSku2PriceReqDTO();
        sku2PriceReqDTO.setSkuId(skuId);
        sku2PriceReqDTO.setGdsId(gdsId);
        sku2PriceReqDTO.setrType(GdsConstants.GdsPrice.GDS_PRICE_RTYPE_SKU);
        if (!ArrayUtils.isEmpty(status) && StringUtil.isNotBlank(status[0])) {
            sku2PriceReqDTO.setStatus(status[0]);
        }
        sku2PriceReqDTO.setIfPrice(true);
        return gdsPriceSV.queryGdsSkuPriceList(sku2PriceReqDTO);
    }

    /**
     * 
     * setSkuProps:(查询单品相关属性). <br/>
     * 
     * @author linwb3
     * @param skuInfoRespDTO
     * @param skuId
     * @param status
     * @since JDK 1.6
     */
    private void setSkuProps(GdsSkuInfoRespDTO skuInfoRespDTO, Long skuId, GdsSkuInfoReqDTO gdsSkuInfoReqDTO, String... status) {
        if (skuInfoRespDTO.getGdsId() == null || skuInfoRespDTO.getId() == null) {
            throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210005);
        }
        // 查询单品所有有效的属性关系
        GdsSku2PropReqDTO reqDTO = new GdsSku2PropReqDTO();
        reqDTO.setGdsId(skuInfoRespDTO.getGdsId());
        reqDTO.setSkuId(skuId);
        reqDTO.setPropIds(gdsSkuInfoReqDTO.getPropIds());
        reqDTO.setPropTypes(gdsSkuInfoReqDTO.getPropTypes());
        reqDTO.setPropValueTypes(gdsSkuInfoReqDTO.getPropValueTypes());
        reqDTO.setPropInputTypes(gdsSkuInfoReqDTO.getPropInputTypes());
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);

        //Long data2 = System.currentTimeMillis();
        List<GdsSku2Prop> sku2props = gdsSkuInfo2PropSV.queryGdsSkuInfo2Prop(reqDTO);
        //LogUtil.error("", "查询单品属性个数:" + sku2props.size());
        //LogUtil.error("", "查询单品属性耗时" + getTime(data2));
        //Long data3 = System.currentTimeMillis();
        List<GdsPropRespDTO> props = gdsSkuInfo2PropSV.convertSku2propTOProp(sku2props);
        //LogUtil.error("", "转换单品属性耗时" + getTime(data3));

        if (CollectionUtils.isNotEmpty(props)) {
            List<GdsPropRespDTO> skuProps = new ArrayList<GdsPropRespDTO>();
            List<GdsPropRespDTO> gdsProps = new ArrayList<GdsPropRespDTO>();
            Map<String, GdsPropRespDTO> allPropMap = new HashMap<String, GdsPropRespDTO>();
            Map<String, GdsPropRespDTO> richTextPropMap = new HashMap<String, GdsPropRespDTO>();
            for (GdsPropRespDTO gdsPropRespDTO : props) {
                if (GdsConstants.GdsProp.PROP_TYPE_1.equals(gdsPropRespDTO.getPropType()) && GdsUtils.isEqualsInvalidOrNULL(gdsPropRespDTO.getIfBasic())) {
                    skuProps.add(gdsPropRespDTO);
                } else {
                    gdsProps.add(gdsPropRespDTO);
                }
                allPropMap.put(gdsPropRespDTO.getId() + "", gdsPropRespDTO);
                if(GdsConstants.GdsProp.GDS_PROP_VALUE_INPUT_TYPE_RICHTXT.equals(gdsPropRespDTO.getPropInputType())){
                    richTextPropMap.put(gdsPropRespDTO.getId() + "", gdsPropRespDTO);
                }
            }
            // 设置单品属性
            skuInfoRespDTO.setProps(skuProps);
            // 设置商品属性
            skuInfoRespDTO.setGdsProps(gdsProps);
            // 所有属性Map
            skuInfoRespDTO.setAllPropMaps(allPropMap);
            //富文本的属性集合
            skuInfoRespDTO.setRichTextPropMap(richTextPropMap);
        }

    }

    @Override
    public List<GdsSku2Prop> querySkuInfoByProp(List<GdsSku2PropReqDTO> props, Long gdsId, boolean distinct) {
        paramNullCheck(props, "query skuInfo by prop error! because the reqDTO is null!");
        GdsSku2PropReqDTO reqDTO = new GdsSku2PropReqDTO();
        reqDTO.setGdsId(gdsId);
        reqDTO.setPropType(GdsConstants.GdsProp.PROP_TYPE_1);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        List<GdsSku2Prop> result = gdsSkuInfo2PropSV.queryGdsSkuInfo2Prop(reqDTO);

        // 组装skuId--->属性列表 Map
        Map<Long, List<GdsSku2Prop>> skuMaps = new HashMap<Long, List<GdsSku2Prop>>();
        if (CollectionUtils.isNotEmpty(result)) {
            for (GdsSku2Prop gdsSku2Prop : result) {
                if (skuMaps.containsKey(gdsSku2Prop.getSkuId())) {
                    skuMaps.get(gdsSku2Prop.getSkuId()).add(gdsSku2Prop);
                } else {
                    List<GdsSku2Prop> sku2Props = new ArrayList<GdsSku2Prop>();
                    sku2Props.add(gdsSku2Prop);
                    skuMaps.put(gdsSku2Prop.getSkuId(), sku2Props);
                }
            }
        }

        // 遍历查询符合条件的SkuId
        Set<Long> keys = skuMaps.keySet();
        result = new ArrayList<GdsSku2Prop>();
        for (Long key : keys) {
            List<GdsSku2Prop> sku2Props = skuMaps.get(key);
            boolean isAdd = true;
            for (GdsSku2Prop gdsSku2Prop : sku2Props) {
                boolean in = false;
                for (GdsSku2PropReqDTO gdsSku2PropReqDTO : props) {
                    if (gdsSku2PropReqDTO.getPropId().longValue() == gdsSku2Prop.getPropId().longValue() && gdsSku2PropReqDTO.getPropValue().trim().equals(gdsSku2Prop.getPropValue().trim())) {
                        in = true;
                        break;
                    }
                }
                if (!in) {
                    isAdd = false;
                    break;
                }
            }
            if (isAdd) {
                result.add(sku2Props.get(0));
            }
        }
        return result;
    }

    @Override
    public PageResponseDTO<GdsSkuInfoRespDTO> queryGdsSkuInfoPagingByProp(GdsSku2PropPropIdxReqDTO reqDTO, SkuQueryOption... skuQuerys) throws BusinessException {
        return gdsInfoQueryIDXSV.getSkuInfoListByPropIdx(reqDTO, skuQuerys);
    }

    /**
     * 
     * getSkuStocksBySkuId:(通过单品id获取单品库存). <br/>
     * 
     * @author linwb3
     * @param skuId
     * @param status
     * @return
     * @since JDK 1.6
     */
    private void setSkuStocksBySkuId(GdsSkuInfoRespDTO skuInfoRespDTO, String... status) {
        if (skuInfoRespDTO.getGdsId() == null || skuInfoRespDTO.getId() == null) {
            throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210005);
        }
        if (GdsUtils.isEqualsValid(skuInfoRespDTO.getIfDisperseStock())) {
            try {
                StockInfoReqDTO stockInfoDTO = new StockInfoReqDTO();
                stockInfoDTO.setGdsId(skuInfoRespDTO.getGdsId());
                stockInfoDTO.setShopId(skuInfoRespDTO.getShopId());
                stockInfoDTO.setSkuId(skuInfoRespDTO.getId());
                List<GdsStockRepRespDTO> stockRepDTOs = gdsStockSV.queryGdsStockInfos(stockInfoDTO);
                skuInfoRespDTO.setStockRepDTOs(stockRepDTOs);
            } catch (Exception e) {
                LogUtil.error(MODULE, "查询库存信息异常", e);
            }
        } else {
            StockInfoForGdsReqDTO stockInfoForGdsDTO = new StockInfoForGdsReqDTO();
            stockInfoForGdsDTO.setGdsId(skuInfoRespDTO.getGdsId());
            stockInfoForGdsDTO.setShopId(skuInfoRespDTO.getShopId());
            stockInfoForGdsDTO.setSkuId(skuInfoRespDTO.getId());
            try {
                // 查询分仓库存
                StockInfoRespDTO stockInfoDTO = gdsStockSV.queryStockInfoByGds(stockInfoForGdsDTO);
                skuInfoRespDTO.setStockInfoRespDTO(stockInfoDTO);

                // 构造分仓库存 方便统一使用
                List<GdsStockRepRespDTO> stockRepDTOs = initStocks(stockInfoDTO);
                skuInfoRespDTO.setStockRepDTOs(stockRepDTOs);
            } catch (Exception e) {
                LogUtil.error(MODULE, "查询库存信息异常", e);
            }
        }
    }

    /**
     * 构造分仓库存 方便统一使用 initStocks:(这里用一句话描述这个方法的作用). <br/>
     * 
     * @author linwb3
     * @param stockInfoDTO
     * @return
     * @since JDK 1.6
     */
    private List<GdsStockRepRespDTO> initStocks(StockInfoRespDTO stockInfoDTO) {
        List<GdsStockRepRespDTO> stockRepDTOs = new ArrayList<GdsStockRepRespDTO>();
        GdsStockRepRespDTO stockRepRespDTO = new GdsStockRepRespDTO();
        stockRepRespDTO.setRepName("总仓");
        stockRepRespDTO.setRepType(GdsConstants.GdsStock.STOCK_REP_TYPE_PUBLIC);
        ObjectCopyUtil.copyObjValue(stockInfoDTO, stockRepRespDTO, null, false);
        stockRepDTOs.add(stockRepRespDTO);

        List<GdsStockInfoRespDTO> gdsStockInfoRespDTOs = new ArrayList<GdsStockInfoRespDTO>();
        GdsStockInfoRespDTO gdsStockInfoRespDTO = new GdsStockInfoRespDTO();
        ObjectCopyUtil.copyObjValue(stockInfoDTO, gdsStockInfoRespDTO, null, false);
        gdsStockInfoRespDTOs.add(gdsStockInfoRespDTO);
        stockRepRespDTO.setStockInfoDTOs(gdsStockInfoRespDTOs);
        return stockRepDTOs;
    }

    /**
     * 
     * getSkuStocksBySkuId:(通过单品id获取单品库存 详情页面显示). <br/>
     * 
     * @author linwb3
     * @param skuId
     * @param status
     * @return
     * @since JDK 1.6
     */
    private void setSkuShowStocksBySkuId(GdsSkuInfoRespDTO skuInfoRespDTO, GdsSkuInfoReqDTO gdsSkuInfoReqDTO, String... status) {
        if (StringUtil.isBlank(skuInfoRespDTO.getIfDisperseStock()) || skuInfoRespDTO.getGdsId() == null) {
            GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
            skuInfoReqDTO.setId(skuInfoRespDTO.getId());
            GdsSkuInfo temp = queryGdsSkuInfo(skuInfoReqDTO);
            ObjectCopyUtil.copyObjValue(temp, skuInfoRespDTO, null, false);
        }
        if (skuInfoRespDTO.getGdsId() == null || skuInfoRespDTO.getId() == null) {
            throw new BusinessException(GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210005);
        }

        if (GdsUtils.isEqualsValid(skuInfoRespDTO.getIfDisperseStock())) {
            try {
                StockInfoForGdsReqDTO stockInfoDTO = new StockInfoForGdsReqDTO();
                stockInfoDTO.setGdsId(skuInfoRespDTO.getGdsId());
                stockInfoDTO.setShopId(skuInfoRespDTO.getShopId());
                stockInfoDTO.setSkuId(skuInfoRespDTO.getId());
                stockInfoDTO.setAdaptCity(gdsSkuInfoReqDTO.getCityCode());
                stockInfoDTO.setAdaptCountry(gdsSkuInfoReqDTO.getCountryCode());
                stockInfoDTO.setAdaptProvince(gdsSkuInfoReqDTO.getProvinceCode());
                StockInfoRespDTO stockRespDTO = gdsStockRSV.queryStockInfoByGds(stockInfoDTO);
                if (stockRespDTO != null) {
                    skuInfoRespDTO.setStockInfoRespDTO(stockRespDTO);
                    skuInfoRespDTO.setRealAmount(stockRespDTO.getAvailCount());
                }
            } catch (Exception e) {
                LogUtil.error(MODULE, "查询库存信息异常", e);
                skuInfoRespDTO.setRealAmount(0L);
            }
        } else {
            StockInfoForGdsReqDTO stockInfoForGdsDTO = new StockInfoForGdsReqDTO();
            stockInfoForGdsDTO.setGdsId(skuInfoRespDTO.getGdsId());
            stockInfoForGdsDTO.setShopId(skuInfoRespDTO.getShopId());
            stockInfoForGdsDTO.setSkuId(skuInfoRespDTO.getId());
            try {
                StockInfoRespDTO stockInfoDTO = gdsStockSV.queryStockInfoByGds(stockInfoForGdsDTO);
                skuInfoRespDTO.setStockInfoRespDTO(stockInfoDTO);
                skuInfoRespDTO.setRealAmount(stockInfoDTO.getAvailCount());
            } catch (Exception e) {
                LogUtil.error(MODULE, "查询库存信息异常", e);
                skuInfoRespDTO.setRealAmount(0L);
            }
        }
    }

    private void setGdsTypeAndStatusName(GdsSkuInfoRespDTO skuInfoRespDTO) {
        skuInfoRespDTO.setGdsStatusName(BaseParamUtil.fetchParamValue(GdsConstants.GdsInfo.GDS_INFO_STATUS_KEY, skuInfoRespDTO.getGdsStatus()));
        if (skuInfoRespDTO.getGdsTypeId() != null) {
        	GdsType type=gdsTypeSV.queryGdsTypeModelByPKFromCache(skuInfoRespDTO.getGdsTypeId());
        	if(type!=null){
        		skuInfoRespDTO.setGdsTypeName(type.getTypeName());
        		skuInfoRespDTO.setIfBuyMore(GdsUtils.isEqualsValid(type.getIfBuyonce()));
        		skuInfoRespDTO.setIfNeedStock(GdsUtils.isEqualsValid(type.getIfNeedstock()));
        	}
        }
        setGdsUrl(skuInfoRespDTO);

        GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
        reqDTO.setCatgCode(skuInfoRespDTO.getMainCatgs());
        GdsCategoryRespDTO category = gdsCategorySV.queryGdsCategoryByPK(reqDTO);
        if (category != null) {
            skuInfoRespDTO.setMainCatgName(category.getCatgName());
        }
    }

    /*
     * 
     * setGdsUrl:(设置URL). <br/>
     * 
     * @author linwb3
     * 
     * @param gdsInfoRespDTO
     * 
     * @since JDK 1.6
     */
    private void setGdsUrl(GdsSkuInfoRespDTO skuInfoRespDTO) {
        skuInfoRespDTO.setUrl(GdsUtils.getGdsUrl(skuInfoRespDTO.getGdsId(), skuInfoRespDTO.getId(), skuInfoRespDTO.getCatlogId()));
    }

    /*
     * 
     * 初始化商品单品索引查询条件.
     * 
     * @author liyong7
     * 
     * @param c
     * 
     * @param reqDTO
     * 
     * @since JDK 1.6
     */
    /*
     * private void initCriteria(GdsSku2PropPropIdxManualCriteria.Criteria c,
     * GdsSku2PropPropIdxReqDTO reqDTO) throws ParseException {
     * 
     * if (StringUtil.isNotBlank(reqDTO.getIfBasic())) { c.andIfBasicEqualTo(reqDTO.getIfBasic()); }
     * 
     * if (StringUtil.isNotBlank(reqDTO.getIfCheck())) { c.andIfCheckEqualTo(reqDTO.getIfCheck()); }
     * 
     * if (StringUtil.isNotBlank(reqDTO.getIfHaveto())) {
     * c.andIfHavetoEqualTo(reqDTO.getIfHaveto()); }
     * 
     * if (StringUtil.isNotBlank(reqDTO.getPropName())) {
     * c.andPropNameEqualTo(reqDTO.getPropName()); } if
     * (StringUtil.isNotBlank(reqDTO.getPropType())) { c.andPropTypeEqualTo(reqDTO.getPropType()); }
     * if (StringUtil.isNotBlank(reqDTO.getPropValueType())) {
     * c.andPropValueTypeEqualTo(reqDTO.getPropValueType()); } if
     * (StringUtil.isNotBlank(reqDTO.getStatus())) { c.andStatusEqualTo(reqDTO.getStatus()); } else
     * { c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID); } if
     * (StringUtil.isNotBlank(reqDTO.getGdsStatus())) {
     * c.andGdsStatusEqualTo(reqDTO.getGdsStatus()); } if (null != reqDTO.getPropId()) {
     * c.andPropIdEqualTo(reqDTO.getPropId()); } if (null != reqDTO.getPropValueId()) {
     * c.andPropValueIdEqualTo(reqDTO.getPropValueId()); } if (null != reqDTO.getShopId()) {
     * c.andShopIdEqualTo(reqDTO.getShopId()); } if (null != reqDTO.getSkuId()) {
     * c.andSkuIdEqualTo(reqDTO.getSkuId()); }
     * 
     * if (null != reqDTO.getPropValueNullQuery() && true == reqDTO.getPropValueNullQuery()) {
     * c.andPropValueIsNull(); } else { if (StringUtil.isNotBlank(reqDTO.getPropValue())) {
     * c.andPropValueEqualTo(reqDTO.getPropValue()); }
     * 
     * if (null != reqDTO.getBeginTime()) {
     * c.andPropValueGreaterThanOrEqualTo(reqDTO.getBeginTime()); } if (null != reqDTO.getEndTime())
     * { c.andPropValueLessThanOrEqualTo(reqDTO.getEndTime()); } }
     * 
     * }
     */

    /**
     * 
     * setGdsCatgs:(查询对应商品的分类，并且将主分类，平台分类，店铺分类归类). <br/>
     * 
     * @author linwb3
     * @param gdsInfoRespDTO
     * @param gdsId
     * @since JDK 1.6
     */
    private void setSkuCatgs(GdsSkuInfoRespDTO gdsSkuInfoRespDTO, Long gdsId) {
        List<GdsGds2CatgRespDTO> gds2Catgs = gdsInfo2CatgSV.queryGds2CatgsByGdsId(gdsId);
        List<GdsCategoryRespDTO> platformCategory = new ArrayList<GdsCategoryRespDTO>();
        List<GdsCategoryRespDTO> shopCategory = new ArrayList<GdsCategoryRespDTO>();
        if (CollectionUtils.isNotEmpty(gds2Catgs)) {
            for (GdsGds2CatgRespDTO gds2Catg : gds2Catgs) {

                GdsCategoryReqDTO reqDTO = new GdsCategoryReqDTO();
                reqDTO.setCatgCode(gds2Catg.getCatgCode());
                GdsCategoryRespDTO gdsCategoryResp = gdsCategorySV.queryGdsCategoryByPK(reqDTO);
                if (gdsCategoryResp != null && GdsConstants.GdsCategory.CATG_TYPE_1.equals(gdsCategoryResp.getCatgType())) {
                    platformCategory.add(gdsCategoryResp);
                } else {
                    shopCategory.add(gdsCategoryResp);
                }
                // 设置主分类
                if (gdsCategoryResp != null && GdsConstants.GdsInfo.GDS_2_CATG_RTYPE_MAIN.equals(gds2Catg.getGds2catgType())) {
                    gdsSkuInfoRespDTO.setMainCategory(gdsCategoryResp);
                }
            }
        }
        // 设置平台分类
        gdsSkuInfoRespDTO.setPlatformCategory(platformCategory);
        // 设置店铺分类
        gdsSkuInfoRespDTO.setShopCategory(shopCategory);
    }

    @Override
    public Boolean isBelongToCategory(GdsGds2CatgReqDTO gds2CatgReqDTO) throws BusinessException {
        GdsSkuInfoReqDTO reqDto = new GdsSkuInfoReqDTO();
        reqDto.setId(gds2CatgReqDTO.getSkuId());
        GdsSkuInfoRespDTO skuInfo = querySkuInfoByOptions(reqDto);
        String catgCode = gds2CatgReqDTO.getCatgCode();
        String skuCatgs = skuInfo.getMainCatgs();
        if (skuCatgs != null && skuCatgs.equals(catgCode)) {
            return true;
        } else {
            List<GdsCategoryRespDTO> categories = gdsCategorySV.queryCategoryTraceUpon(skuCatgs);
            if (CollectionUtils.isNotEmpty(categories)) {
                for (GdsCategoryRespDTO category : categories) {
                    skuCatgs = category.getCatgCode();
                    if (skuCatgs != null && skuCatgs.equals(catgCode)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void getDisCountPrice(GdsSkuInfoReqDTO skuInfoReqDTO, GdsSkuInfoRespDTO skuInfoRespDTO) {
        Long price = skuInfoRespDTO.getRealPrice();
        if (price == null) {
            setSkuShowPrice(skuInfoReqDTO, skuInfoRespDTO);
        }
        price = skuInfoRespDTO.getRealPrice();
        CalCatgCustDiscReqDTO reqDto = new CalCatgCustDiscReqDTO();
        reqDto.setGdsId(skuInfoRespDTO.getGdsId());
        /**
         * 如果入参没有传session 的值，则这里不做折扣价处理。
         */
        String staffLevelCode = null;
        /*if(!StringUtil.isBlank(skuInfoReqDTO.getStaff().getStaffLevelCode())) {
            staffLevelCode = skuInfoReqDTO.getStaff().getStaffLevelCode();
            reqDto.setCustLevelCode(skuInfoReqDTO.getStaff().getStaffLevelCode());
            BigDecimal discount = gdsCatgCustDiscSV.calCatgCustDisc(reqDto);
            Long disCountPrice = GdsUtils.getDiscountPrice(price, discount);
            skuInfoRespDTO.setDiscountPrice(disCountPrice);
        } else {
            skuInfoRespDTO.setDiscountPrice(price);
        }*/
        
        if(null != skuInfoReqDTO.getStaffId()){
            CustInfoResDTO cust = custManageRSV.findCustInfoById(skuInfoReqDTO.getStaffId());
            if(null != cust){
                staffLevelCode = cust.getCustLevelCode();
            }
        }else if(!StringUtil.isBlank(skuInfoReqDTO.getStaff().getStaffLevelCode())) {
            staffLevelCode = skuInfoReqDTO.getStaff().getStaffLevelCode();
        }
        
        if(StringUtil.isNotBlank(staffLevelCode)){
            reqDto.setCustLevelCode(staffLevelCode);
            BigDecimal discount = gdsCatgCustDiscSV.calCatgCustDisc(reqDto);
            Long disCountPrice = GdsUtils.getDiscountPrice(price, discount);
            skuInfoRespDTO.setDiscountPrice(disCountPrice);
        }else{
            skuInfoRespDTO.setDiscountPrice(price);
        }
        
        
    }

    private GdsSkuInfoShopIdxCriteria initCriteria(GdsSkuInfoReqDTO gdsSkuInfoReqDTO) {
        GdsSkuInfoShopIdxCriteria gdsSkuInfoShopIdxCriteria = gdsSkuInfoReqDTO.getCriteria();// new
                                                                                             // GdsSkuInfoShopIdxCriteria();
        initAutormCriteriaWithAuth(gdsSkuInfoReqDTO, gdsSkuInfoShopIdxCriteria);

        /*
         * if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getIfScoreGds())) {
         * criteria.andIfScoreGdsEqualTo(gdsSkuInfoReqDTO.getIfScoreGds()); } if
         * (gdsSkuInfoReqDTO.getCurrentSiteId() != null) { GdsCatalog2SiteReqDTO reqDTO = new
         * GdsCatalog2SiteReqDTO(); reqDTO.setSiteId(gdsSkuInfoReqDTO.getCurrentSiteId());
         * 
         * List<GdsCatalogRespDTO> catalogRespDTOs = catalog2SiteSV.queryRelationBySiteId(reqDTO);
         * List<Long> cataLogIds = new ArrayList<>(); for (GdsCatalogRespDTO catalogRespDTO :
         * catalogRespDTOs) { cataLogIds.add(catalogRespDTO.getId()); } if (cataLogIds.size() > 0) {
         * criteria.andCatlogIdIn(cataLogIds); } }
         */
        gdsSkuInfoShopIdxCriteria.setOrderByClause("update_time desc");
        return gdsSkuInfoShopIdxCriteria;
    }

    private void initAutormCriteriaWithAuth(GdsSkuInfoReqDTO gdsSkuInfoReqDTO, GdsSkuInfoShopIdxCriteria gdsSkuInfoShopIdxCriteria) {
        List<GdsSkuInfoShopIdxCriteria.Criteria> lst = gdsSkuInfoShopIdxCriteria.getOredCriteria();
        if (CollectionUtils.isEmpty(lst)) {
            initAutormCriteria(gdsSkuInfoReqDTO, gdsSkuInfoShopIdxCriteria);
        } else {
            for (Iterator<GdsSkuInfoShopIdxCriteria.Criteria> iterator = lst.iterator(); iterator.hasNext();) {
                GdsSkuInfoShopIdxCriteria.Criteria criteria = iterator.next();
                criteria.andShopIdEqualTo(gdsSkuInfoReqDTO.getShopId());
                if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getGdsName())) {
                    criteria.andGdsNameLike("%" + gdsSkuInfoReqDTO.getGdsName() + "%");
                }

                if(StringUtil.isNotBlank(gdsSkuInfoReqDTO.getGdsNameEqual())){
                    criteria.andGdsNameEqualTo(gdsSkuInfoReqDTO.getGdsNameEqual());
                }
                if (CollectionUtils.isNotEmpty(gdsSkuInfoReqDTO.getGdsStatusArr())) {
                    criteria.andGdsStatusIn(gdsSkuInfoReqDTO.getGdsStatusArr());
                } else if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getGdsStatus())) {
                    criteria.andGdsStatusEqualTo(gdsSkuInfoReqDTO.getGdsStatus());
                }

                if (gdsSkuInfoReqDTO.getId() != null) {
                    criteria.andSkuIdEqualTo(gdsSkuInfoReqDTO.getId());
                }

                if (CollectionUtils.isNotEmpty(gdsSkuInfoReqDTO.getCatalogIds())) {
                    criteria.andCatlogIdIn(gdsSkuInfoReqDTO.getCatalogIds());
                }

                if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getIsbn())) {
                    criteria.andIsbnLike("%" + gdsSkuInfoReqDTO.getIsbn() + "%");
                }

                // 查询创建时间
                if (gdsSkuInfoReqDTO.getBegCreateTime() != null) {
                    criteria.andCreateTimeGreaterThanOrEqualTo(gdsSkuInfoReqDTO.getBegCreateTime());
                }
                if (gdsSkuInfoReqDTO.getEndCreateTime() != null) {
                    criteria.andCreateTimeLessThanOrEqualTo(gdsSkuInfoReqDTO.getEndCreateTime());
                }

                // 查询更新时间
                if (gdsSkuInfoReqDTO.getBegUpdateTime() != null) {
                    criteria.andUpdateTimeGreaterThanOrEqualTo(gdsSkuInfoReqDTO.getBegUpdateTime());
                }
                if (gdsSkuInfoReqDTO.getEndUpdateTime() != null) {
                    criteria.andCreateTimeLessThanOrEqualTo(gdsSkuInfoReqDTO.getEndUpdateTime());
                }

                if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getPlatCatgs())) {
                    criteria.andPlatCatgsLike("%<" + gdsSkuInfoReqDTO.getPlatCatgs() + ">%");
                }
                if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getShopCatgs())) {
                    criteria.andShopCatgsLike("%<" + gdsSkuInfoReqDTO.getShopCatgs() + ">%");
                }
                if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getIfRecomm())) {
                    criteria.andIfRecommEqualTo(gdsSkuInfoReqDTO.getIfRecomm());
                }
                if (gdsSkuInfoReqDTO.getGdsTypeId() != null && gdsSkuInfoReqDTO.getGdsTypeId() != 0) {
                    criteria.andGdsTypeIdEqualTo(gdsSkuInfoReqDTO.getGdsTypeId());
                }

                if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getIfScoreGds())) {
                    criteria.andIfScoreGdsEqualTo(gdsSkuInfoReqDTO.getIfScoreGds());
                }
                if (gdsSkuInfoReqDTO.getCurrentSiteId() != null) {
                    GdsCatalog2SiteReqDTO reqDTO = new GdsCatalog2SiteReqDTO();
                    reqDTO.setSiteId(gdsSkuInfoReqDTO.getCurrentSiteId());

                    List<GdsCatalogRespDTO> catalogRespDTOs = catalog2SiteSV.queryRelationBySiteId(reqDTO);
                    List<Long> cataLogIds = new ArrayList<>();
                    for (GdsCatalogRespDTO catalogRespDTO : catalogRespDTOs) {
                        cataLogIds.add(catalogRespDTO.getId());
                    }
                    if (cataLogIds.size() > 0) {
                        criteria.andCatlogIdIn(cataLogIds);
                    }
                }
            }

        }
    }

    private GdsSkuInfoShopIdxCriteria.Criteria initAutormCriteria(GdsSkuInfoReqDTO gdsSkuInfoReqDTO, GdsSkuInfoShopIdxCriteria gdsSkuInfoShopIdxCriteria) {
        GdsSkuInfoShopIdxCriteria.Criteria criteria = gdsSkuInfoShopIdxCriteria.createCriteria();
        criteria.andShopIdEqualTo(gdsSkuInfoReqDTO.getShopId());
        if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getGdsName())) {
            criteria.andGdsNameLike("%" + gdsSkuInfoReqDTO.getGdsName() + "%");
        }
        
        if(StringUtil.isNotBlank(gdsSkuInfoReqDTO.getGdsNameEqual())){
            criteria.andGdsNameEqualTo(gdsSkuInfoReqDTO.getGdsNameEqual());
        }

        if (CollectionUtils.isNotEmpty(gdsSkuInfoReqDTO.getGdsStatusArr())) {
            criteria.andGdsStatusIn(gdsSkuInfoReqDTO.getGdsStatusArr());
        } else if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getGdsStatus())) {
            criteria.andGdsStatusEqualTo(gdsSkuInfoReqDTO.getGdsStatus());
        }

        if (gdsSkuInfoReqDTO.getId() != null) {
            criteria.andSkuIdEqualTo(gdsSkuInfoReqDTO.getId());
        }

        if (CollectionUtils.isNotEmpty(gdsSkuInfoReqDTO.getCatalogIds())) {
            criteria.andCatlogIdIn(gdsSkuInfoReqDTO.getCatalogIds());
        }

        if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getIsbn())) {
            criteria.andIsbnLike("%" + gdsSkuInfoReqDTO.getIsbn() + "%");
        }

        // 查询创建时间
        if (gdsSkuInfoReqDTO.getBegCreateTime() != null) {
            criteria.andCreateTimeGreaterThanOrEqualTo(gdsSkuInfoReqDTO.getBegCreateTime());
        }
        if (gdsSkuInfoReqDTO.getEndCreateTime() != null) {
            criteria.andCreateTimeLessThanOrEqualTo(gdsSkuInfoReqDTO.getEndCreateTime());
        }

        // 查询更新时间
        if (gdsSkuInfoReqDTO.getBegUpdateTime() != null) {
            criteria.andUpdateTimeGreaterThanOrEqualTo(gdsSkuInfoReqDTO.getBegUpdateTime());
        }
        if (gdsSkuInfoReqDTO.getEndUpdateTime() != null) {
            criteria.andCreateTimeLessThanOrEqualTo(gdsSkuInfoReqDTO.getEndUpdateTime());
        }

        if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getPlatCatgs())) {
            criteria.andPlatCatgsLike("%<" + gdsSkuInfoReqDTO.getPlatCatgs() + ">%");
        }
        if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getShopCatgs())) {
            criteria.andShopCatgsLike("%<" + gdsSkuInfoReqDTO.getShopCatgs() + ">%");
        }
        if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getIfRecomm())) {
            criteria.andIfRecommEqualTo(gdsSkuInfoReqDTO.getIfRecomm());
        }
        if (gdsSkuInfoReqDTO.getGdsTypeId() != null && gdsSkuInfoReqDTO.getGdsTypeId() != 0) {
            criteria.andGdsTypeIdEqualTo(gdsSkuInfoReqDTO.getGdsTypeId());
        }
        if (StringUtil.isNotBlank(gdsSkuInfoReqDTO.getIfScoreGds())) {
            criteria.andIfScoreGdsEqualTo(gdsSkuInfoReqDTO.getIfScoreGds());
        }
        if (gdsSkuInfoReqDTO.getCurrentSiteId() != null) {
            GdsCatalog2SiteReqDTO reqDTO = new GdsCatalog2SiteReqDTO();
            reqDTO.setSiteId(gdsSkuInfoReqDTO.getCurrentSiteId());

            List<GdsCatalogRespDTO> catalogRespDTOs = catalog2SiteSV.queryRelationBySiteId(reqDTO);
            List<Long> cataLogIds = new ArrayList<>();
            for (GdsCatalogRespDTO catalogRespDTO : catalogRespDTOs) {
                cataLogIds.add(catalogRespDTO.getId());
            }
            if (cataLogIds.size() > 0) {
                criteria.andCatlogIdIn(cataLogIds);
            }
        }

        return criteria;
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
        skuInfoResp.setExt1(skuInfo.getExt1());
        skuInfoResp.setExt2(skuInfo.getExt2());

        // 商品编码
        skuInfoResp.setGdsId(skuInfo.getGdsId());

        // 价格初始化
        skuInfoResp.setCommonPrice(skuInfo.getCommonPrice());
        skuInfoResp.setRealPrice(skuInfo.getCommonPrice());
        skuInfoResp.setDiscountPrice(skuInfo.getCommonPrice());
        if (skuInfo.getGuidePrice() != null) {
            skuInfoResp.setGuidePrice(skuInfo.getGuidePrice().longValue());
        }

        skuInfoResp.setSkuProps(skuInfo.getSkuProps());
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
    }

	

}
