package com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.ArrayUtils;
import org.jsoup.Jsoup;

import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.busi.order.util.ParamsTool;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuPriceRespDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 获取商品详情工具类  （请勿修改出入参）<br>
 * Title: ECP <br>
 * Project Name:ecp-web-manage-core <br>
 * Description: <br>
 * Date:2016年7月14日上午9:36:37  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6
 */
public class CmsGoodsDetailUtil {
    private static String MODULE = CmsGoodsDetailUtil.class.getName();
    
    private final static int GDSDESCCONTENT_LENGTH = 100;//Integer.MAX_VALUE;
    
    private static IGdsInfoQueryRSV gdsInfoQueryRSV = ApplicationContextUtil.getBean("gdsInfoQueryRSV", IGdsInfoQueryRSV.class);
    
    private static IPromQueryRSV promQueryRSV = ApplicationContextUtil.getBean("promQueryRSV", IPromQueryRSV.class);
    
    private static IGdsCollectRSV gdsCollectRSV = ApplicationContextUtil.getBean("gdsCollectRSV", IGdsCollectRSV.class);
    
    private static IPromRSV promRSV = ApplicationContextUtil.getBean("promRSV", IPromRSV.class);
    /**
     * 
     * getGdsDetailList:(根据商品id列表获取商品详情对象列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param ids 商品id列表
     * @param reqDto 详情查询选项
     * @param standard 商品图片规格
     * @return 返回商品详情列表，商品详情对应信息未扩展 如需扩展请调用extendGdsInfo
     * @throws Exception 
     * @since JDK 1.6
     */
    public static List<GdsInfoDetailRespDTO> getGdsDetailList(List<Long> gdsIds,GdsInfoReqDTO reqDto) throws Exception{
        LogUtil.info(MODULE, "批量获取商品详情开始");
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespList = new ArrayList<GdsInfoDetailRespDTO>();
        
        //1 验证入参
        if(CollectionUtils.isEmpty(gdsIds)){
            LogUtil.info(MODULE, "批量获取商品详情结束:参数错误-----无商品id");
            return gdsInfoDetailRespList;
        }
        if(reqDto == null){
            reqDto = new  GdsInfoReqDTO();
        }
        String errGdsIds = "";
        //2开始查询
        for(Long id : gdsIds){
            if(StringUtil.isEmpty(id)){
                continue;
            }
            reqDto.setId(id);
            GdsInfoDetailRespDTO gdsDetail =null;
            try {
                gdsDetail = getGdsDetailByGdsId(reqDto);
            } catch (Exception e) {
                LogUtil.error(MODULE, "查询商品编码为："+ id +"出现异常");
                errGdsIds += id;
            }
            if(gdsDetail !=null && StringUtil.isNotEmpty(gdsDetail.getId())){
                gdsInfoDetailRespList.add(gdsDetail);
            }
        }
        if(StringUtil.isBlank(errGdsIds)){
            LogUtil.info(MODULE, "批量获取商品详情结束:成功");
        }else{
            LogUtil.info(MODULE, "批量获取商品详情结束:查询商品编码为："+errGdsIds+"出现异常");
            //throw new Exception("查询商品编码为："+errGdsIds+"出现异常");
        }
        
        return gdsInfoDetailRespList;
    }
    /**
     * 
     * getGdsDetailByGdsId:(根据商品id获取商品详情对象). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param ids 商品id列表
     * @param reqDto 详情查询选项
     * @param standard 商品图片规格
     * @return 返回商品详情，商品详情对应信息未扩展   如需扩展请调用extendGdsInfo
     * @since JDK 1.6
     */
    public static GdsInfoDetailRespDTO getGdsDetailByGdsId(GdsInfoReqDTO reqDto){
        LogUtil.info(MODULE, "获取商品详情开始");
        //1 验证入参
        if(reqDto == null || StringUtil.isEmpty(reqDto.getId())){
            LogUtil.info(MODULE, "获取商品详情结束:参数错误");
            return null;
        }
        if(null == reqDto.getPropIds()){
            reqDto.setPropIds(new ArrayList<Long>());
        }else{
            reqDto.setPropIds(new ArrayList<Long>(reqDto.getPropIds()));
        }
        
        //2开始查询
        GdsInfoDetailRespDTO gdsInfoDetailRespDTO = null;
        try {
            gdsInfoDetailRespDTO = gdsInfoQueryRSV.queryGdsInfoDetail(reqDto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询商品详情出异常",e);
            throw e;
        }
        LogUtil.info(MODULE, "获取商品详情结束:成功");
        return gdsInfoDetailRespDTO;
    }
    /**
     * 
     * extendGdsInfo:(根据需要扩展商品信息). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param gdsInfo
     * @param propIds
     * @param getGdsDesc
     * @param getMainPic
     * @param imgStandard
     * @return 返回商品详情，商品详情对应信息已扩展
     * @since JDK 1.6
     */
    public static GdsInfoDetailRespDTO extendGdsInfo(GdsInfoDetailRespDTO gdsInfo,List<Long> propIds,boolean getGdsDesc,boolean getMainPic,String imgStandard){
        LogUtil.info(MODULE, "扩展商品信息开始");
        if(null == gdsInfo){
            LogUtil.info(MODULE, "扩展商品详情结束:商品信息为空");
            return gdsInfo;
        }
        if(CollectionUtils.isEmpty(propIds) && false == getGdsDesc && false == getMainPic){
            LogUtil.info(MODULE, "扩展商品详情结束:无信息需要扩展");
            return gdsInfo;
        }
        if(null == imgStandard){
            imgStandard = "";
        }
        GdsSkuInfoRespDTO skuInfo = gdsInfo.getSkuInfo();
        
        //扩展图片
        if(getMainPic){
            GdsMediaRespDTO skuMediaRespDTO = null;
            GdsMediaRespDTO gdsMediaRespDTO = null;
            if(null != skuInfo){
                skuMediaRespDTO = skuInfo.getMainPic();
            }
            gdsMediaRespDTO = gdsInfo.getMainPic();

            if(null != skuMediaRespDTO && StringUtil.isNotBlank(skuMediaRespDTO.getMediaUuid())){
                gdsMediaRespDTO = skuMediaRespDTO;
                gdsInfo.setMainPic(gdsMediaRespDTO);
            }
            if(null == gdsMediaRespDTO){
                gdsMediaRespDTO = new GdsMediaRespDTO();
                gdsInfo.setMainPic(gdsMediaRespDTO);
            }
            if(null != skuInfo){
                skuInfo.setMainPic(gdsMediaRespDTO);
            }
            gdsMediaRespDTO.setURL(ParamsTool.getImageUrl(gdsMediaRespDTO.getMediaUuid(), imgStandard));
        }
        //扩展商品描述
        if(getGdsDesc){
            if(null != skuInfo && StringUtil.isNotBlank(skuInfo.getGdsDesc())){
                gdsInfo.setGdsDesc(skuInfo.getGdsDesc());
            }
            if(StringUtil.isNotBlank(gdsInfo.getGdsDesc())){
                byte[] content = null;
                try {
                    content = FileUtil.readFile(gdsInfo.getGdsDesc()); 
                } catch (Exception e) {
                    LogUtil.error(MODULE, "mogoDB读取商品描述异常", e);
                }
                String gdsDescContent = null;
                if (ArrayUtils.isNotEmpty(content)) {
                    gdsDescContent = Jsoup.parse(new String(content)).text();
                    if (gdsDescContent.length() > GDSDESCCONTENT_LENGTH) {
                        gdsDescContent = gdsDescContent.substring(0, GDSDESCCONTENT_LENGTH) + "...";
                    }
                }
                gdsInfo.setGdsDesc(gdsDescContent);
                if(null != skuInfo){
                    skuInfo.setGdsDesc(gdsDescContent);
                }
            }
        }
        // 根据ID读取内容简介内容
        if(CollectionUtils.isNotEmpty(propIds)){
            Map<String, GdsPropRespDTO> allPropMaps = gdsInfo.getAllPropMaps();
            Map<String, GdsPropRespDTO> skuAllPropMaps = null;
            if(null != skuInfo){
                skuAllPropMaps = skuInfo.getAllPropMaps();
            }
            
            if(null != skuAllPropMaps && !skuAllPropMaps.isEmpty()){
                allPropMaps = skuAllPropMaps;
                gdsInfo.setAllPropMaps(allPropMaps);
            }
            if( null != allPropMaps && propIds.contains(1020L) 
                && allPropMaps.get("1020") != null
                && allPropMaps.get("1020").getValues()!=null
                && allPropMaps.get("1020").getValues().get(0)!=null){
                GdsPropValueRespDTO prop1020 = allPropMaps.get("1020").getValues().get(0);
                byte[] editRecm = null;
                try {
                    editRecm = FileUtil.readFile(prop1020.getPropValue());
                } catch (Exception e) {
                    LogUtil.error(MODULE, "mogoDB读取内容简介异常", e);
                }
                String gdsEditRecm = null;
                if (ArrayUtils.isNotEmpty(editRecm)) {
                    gdsEditRecm = Jsoup.parse(new String(editRecm)).text();
                    if (gdsEditRecm.length() > GDSDESCCONTENT_LENGTH) {
                        gdsEditRecm = gdsEditRecm.substring(0, GDSDESCCONTENT_LENGTH) + "...";
                    }
                }
                prop1020.setPropValue(gdsEditRecm);
            }
        }
        LogUtil.info(MODULE, "扩展商品信息结束:成功");
        return gdsInfo;
    }
    /**
     * 
     * extendGdsInfo:(扩展单品信息). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param skuInfo
     * @param propIds
     * @param getGdsDesc
     * @param getMainPic
     * @param imgStandard
     * @return 返回单品详情，单品详情对应信息已扩展
     * @since JDK 1.6
     */
    public static GdsSkuInfoRespDTO extendSkuInfo(GdsSkuInfoRespDTO skuInfo,List<Long> propIds,boolean getGdsDesc,boolean getMainPic,String imgStandard){
        LogUtil.info(MODULE, "扩展单品信息开始");
        if(null == skuInfo){
            LogUtil.error(MODULE, "扩展单品详情结束:单品信息为空");
            return skuInfo;
        }
        if(CollectionUtils.isEmpty(propIds) && false == getGdsDesc && false == getMainPic){
            LogUtil.info(MODULE, "扩展单品详情结束:无信息需要扩展");
            return skuInfo;
        }
        GdsInfoDetailRespDTO gdsInfo = new GdsInfoDetailRespDTO();
        gdsInfo.setSkuInfo(skuInfo);
        gdsInfo.setAllPropMaps(skuInfo.getAllPropMaps());
        gdsInfo.setGdsDesc(skuInfo.getGdsDesc());
        gdsInfo.setMainPic(skuInfo.getMainPic());
        gdsInfo = extendGdsInfo(gdsInfo, propIds, getGdsDesc, getMainPic, imgStandard);
        LogUtil.info(MODULE, "扩展单品信息结束：正常");
        return gdsInfo.getSkuInfo();
    }
    /**
     * 
     * extendGdsInfoList:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @since JDK 1.6
     */
    public static List<GdsInfoDetailRespDTO> extendGdsInfoList(List<GdsInfoDetailRespDTO> gdsList,List<Long> propIds,boolean gdsDesc,boolean mainPic,String imgStandard){
        if(null == propIds){
            propIds = new ArrayList<Long>(); 
        }
        if(CollectionUtils.isNotEmpty(gdsList)){
            for(GdsInfoDetailRespDTO gdsInfo:gdsList){
                extendGdsInfo(gdsInfo, new ArrayList<Long>(propIds), gdsDesc, mainPic, imgStandard);
            }
        }
        return gdsList;
    }
    /**
     * 
     * getGdsPromPrice:(商品详情迭代促销价格). <br/> 
     * 
     * @author zhanbh 
     * @param gdsInfo
     * @param promId
     * @return 返回商品详情，商品详情已迭代促销价格
     * @since JDK 1.6
     */
    public static GdsInfoDetailRespDTO getGdsPromPrice(GdsInfoDetailRespDTO gdsInfo,String platformType,Long promId) {
        LogUtil.info(MODULE, "扩展商品促销价格开始");
        PromListRespDTO respDto = null;
        List<PromListRespDTO> list = null ;
        if(null == gdsInfo || null == gdsInfo.getSkuInfo() || StringUtil.isEmpty(gdsInfo.getId())){
            LogUtil.error(MODULE, "扩展商品促销价格结束：参数错误");
            return gdsInfo;
        }
        GdsSkuInfoRespDTO skuDto = gdsInfo.getSkuInfo();
        if(CmsConstants.PlatformType.CMS_PLATFORMTYPE_02.equalsIgnoreCase(platformType) ||  CmsConstants.PlatformType.CMS_PLATFORMTYPE_03.equalsIgnoreCase(platformType) ){
            Long appSpecPrice = skuDto.getAppSpecPrice();
            if(StringUtil.isEmpty(promId) && null != appSpecPrice && 0 < appSpecPrice){//有手机专享价
                skuDto.setDiscountPrice(appSpecPrice);
                LogUtil.error(MODULE, "扩展商品促销价格结束：有手机专享价");
                return gdsInfo;
            }
        }
        //1.查询促销
        try {
            list = getPromList(gdsInfo,platformType, promId);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取促销列表失败", e);
            return gdsInfo; 
        }
        //2.价格数据修改
        if(CollectionUtils.isNotEmpty(list)){
            respDto = list.get(0);
        }
        if(null == respDto){
            LogUtil.info(MODULE, "扩展商品促销价格结束：无促销信息");
            return gdsInfo; 
        }
        PromSkuPriceRespDTO promSkuPriceDto = null;
        if(null != respDto.getPromSkuPriceRespDTO()){
            promSkuPriceDto = respDto.getPromSkuPriceRespDTO();
        }else{
            promSkuPriceDto = new PromSkuPriceRespDTO();
        }
        BigDecimal promCaclPrice = promSkuPriceDto.getDiscountCaclPrice();
        BigDecimal promFinalPrice = promSkuPriceDto.getDiscountFinalPrice();
        Long discountPrice = skuDto.getDiscountPrice();
        if(StringUtil.isNotEmpty(discountPrice) && StringUtil.isNotEmpty(promFinalPrice) && 0L < promFinalPrice.longValue() &&  discountPrice != promFinalPrice.longValue()){
            skuDto.setDiscountPrice(promFinalPrice.longValue());
            if(StringUtil.isNotEmpty(promCaclPrice) && 0L < promCaclPrice.longValue()){
                gdsInfo.setGuidePrice(promCaclPrice.longValue());
                skuDto.setGuidePrice(promCaclPrice.longValue());
            }
        }
        LogUtil.info(MODULE, "扩展商品促销价格结束：正常");
        return gdsInfo;
    }
    /**
     * 
     * getSkuPromPrice:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param gdsInfo
     * @param platformType
     * @param promId
     * @return 
     * @since JDK 1.6
     */
    public static GdsSkuInfoRespDTO getSkuPromPrice(GdsSkuInfoRespDTO skuInfo,String platformType,Long promId) {
        LogUtil.info(MODULE, "扩展单品促销价格开始");
        if(null == skuInfo || StringUtil.isEmpty(skuInfo.getId())){
            LogUtil.error(MODULE, "扩展单品品促销价格结束：参数错误");
            return skuInfo;
        }
        GdsInfoDetailRespDTO gdsInfo = new GdsInfoDetailRespDTO();
        gdsInfo.setSkuInfo(skuInfo);
        gdsInfo.setId(skuInfo.getGdsId());
        gdsInfo.setShopId(skuInfo.getShopId());
        gdsInfo.setGdsName(skuInfo.getGdsName());
        gdsInfo = getGdsPromPrice(gdsInfo, platformType, promId);
        LogUtil.info(MODULE, "扩展单品促销价格结束：正常");
        return gdsInfo.getSkuInfo();
    }
    /**
     * 
     * getPromList:(获取商品的促销信息). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param gdsInfo
     * @param platformType
     * @param promId
     * @return 
     * @since JDK 1.6
     */
    public static List<PromListRespDTO> getPromList(GdsInfoDetailRespDTO gdsInfo,String platformType,Long promId){
        LogUtil.info(MODULE, "获取商品促销信息列表非solr开始");
        List<PromListRespDTO> list = null ;
        list = getPromListBase(gdsInfo, platformType, promId, false);
        LogUtil.info(MODULE, "获取商品促销信息列表非solr结束");
        return list;
    }
    /**
     * 
     * getPromListForSolr:(获取商品的促销信息 走solr). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param gdsInfo
     * @param platformType
     * @param promId
     * @return 
     * @since JDK 1.6
     */
    public static List<PromListRespDTO> getPromListForSolr(GdsInfoDetailRespDTO gdsInfo,String platformType,Long promId){
        LogUtil.info(MODULE, "获取商品促销信息列表非solr开始");
        List<PromListRespDTO> list = null ;
        list = getPromListBase(gdsInfo, platformType, promId, true);
        LogUtil.info(MODULE, "获取商品促销信息列表非solr结束");
        return list;
    }
    /**
     * 
     * getPromListForSolr:(获取商品的促销信息 走solr，没有指定促销id). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param gdsInfo
     * @param platformType
     * @return 
     * @since JDK 1.6
     */
    public static List<PromListRespDTO> getPromListForSolr(GdsInfoDetailRespDTO gdsInfo,String platformType){
        return getPromListForSolr(gdsInfo, platformType, null);
    }
    /**
     * 
     * getPromListBase:(获取促销信息完整方法). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param gdsInfo
     * @param platformType
     * @param promId
     * @param isSolr
     * @return 
     * @since JDK 1.6
     */
    public static List<PromListRespDTO> getPromListBase(GdsInfoDetailRespDTO gdsInfo,String platformType,Long promId,boolean isSolr){
        LogUtil.info(MODULE, "获取商品促销信息列表开始");
        List<PromListRespDTO> list = null ;
        if(null == gdsInfo || null == gdsInfo.getSkuInfo() || StringUtil.isEmpty(gdsInfo.getId())){
            LogUtil.error(MODULE, "获取商品促销信息列表结束：参数错误");
            return list;
        }
        GdsSkuInfoRespDTO skuDto = gdsInfo.getSkuInfo();
        String channelValue = CommonConstants.SOURCE.SOURCE_WEB;
        if(CmsConstants.PlatformType.CMS_PLATFORMTYPE_02.equalsIgnoreCase(platformType) ||  CmsConstants.PlatformType.CMS_PLATFORMTYPE_03.equalsIgnoreCase(platformType) ){
            channelValue = CommonConstants.SOURCE.SOURCE_APP;
        }
        //1.查询促销
        PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
        CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
        promRuleCheckDTO.setSiteId(promRuleCheckDTO.getCurrentSiteId());
        promRuleCheckDTO.setStaffId(custInfoReqDTO.getStaff().getId() + "");
        promRuleCheckDTO.setGdsId(gdsInfo.getId());
        promRuleCheckDTO.setChannelValue(channelValue);
        promRuleCheckDTO.setShopId(gdsInfo.getShopId());
        promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));
        promRuleCheckDTO.setSkuId(skuDto.getId());
        promRuleCheckDTO.setBasePrice(skuDto.getRealPrice());
        promRuleCheckDTO.setBuyPrice(skuDto.getDiscountPrice());
        promRuleCheckDTO.setGdsName(gdsInfo.getGdsName());
        if(StringUtil.isNotEmpty(promId)){
            promRuleCheckDTO.setPromId(promId);
        }
        try {
            if(isSolr){
                list = promRSV.listPromForSolr(promRuleCheckDTO);
            }else{
                list = promQueryRSV.listProm(promRuleCheckDTO);
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取促销列表失败", e);
            list =null;
            return list;
        }
        LogUtil.info(MODULE, "扩展商品促销价格结束：正常");
        return list;
    }
    /**
     * 
     * analysJsonObjToGdsCatg:(将行为分析返回的json转成商品分类). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param jsonResult
     * @return 
     * @since JDK 1.6
     */
    public static List<List<GdsCategoryRespDTO>> analysJsonObjToGdsCatg(JSONObject jsonResult) {
        LogUtil.info(MODULE, "将行为分析返回的json转成商品分类开始");
        //1. 入参判断
        if(jsonResult == null || jsonResult.isEmpty()){
            LogUtil.info(MODULE, "调用用户行为分析解析商品分类结束:json为空");
            return null;
        }
        if(!jsonResult.containsKey("catgList")){
            LogUtil.info(MODULE, "调用用户行为分析解析商品分类结束:不存在catgList");
            return null;
        }
        
        //2 解析json
        //LogUtil.info(MODULE, "调用用户行为分析解析商品分类:"+jsonResult);
        
        JSONArray jsonCatgArray = jsonResult.getJSONArray("catgList");
        if(jsonCatgArray ==null || jsonCatgArray.isEmpty()){
            LogUtil.info(MODULE, "调用用户行为分析解析商品分类结束:catgList为空");
            return null;  
        }
        List<List<GdsCategoryRespDTO>> catgLists= new ArrayList<List<GdsCategoryRespDTO>>();
        
        int jsonCount = jsonCatgArray.size();
        for(int i = 0;i<jsonCount;i++){
            JSONObject catgJson = jsonCatgArray.getJSONObject(i);
            if(catgJson!=null && !catgJson.isEmpty()){
                List<GdsCategoryRespDTO> catgList= new ArrayList<GdsCategoryRespDTO>();
                
                //获取三级分类
                for(int j = 1;j<=3;j++){
                    Integer index  = new Integer(j); 
                    if(catgJson.containsKey("catgId"+index.toString()) && catgJson.containsKey("catgName"+index.toString())){
                        GdsCategoryRespDTO catg = new GdsCategoryRespDTO();
                        catg.setCatgCode(catgJson.getString("catgId"+index.toString()));
                        catg.setCatgName(catgJson.getString("catgName"+index.toString()));
                        if(StringUtil.isNotBlank(catg.getCatgName()) && StringUtil.isNotBlank(catg.getCatgCode())){
                            catgList.add(catg);
                        }
                    }
                }
                
                catgLists.add(catgList);
            }
        }
        
        LogUtil.info(MODULE, "调用用户行为分析解析商品分类结束：成功");
        return catgLists;
    }
    /**
     * 
     * analysJsonObjToGdsDetialList:(将行为分析返回的json转成商品详情列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param jsonResult
     * @param props 需要的商品属性id
     * @param staffId 会根据staffid返回对应会员折扣价
     * @return 返回商品详情列表，商品详情对应信息未扩展 如需扩展请调用extendGdsInfo
     * @since JDK 1.6
     */
    public static List<GdsInfoDetailRespDTO> analysJsonObjToGdsDetialList(JSONObject jsonResult,List<Long> props,Long staffId){
        LogUtil.info(MODULE, "批量将行为分析返回的json转成商品详情列表开始");
        //1 验证入参
        if(jsonResult == null || jsonResult.isEmpty()){
            LogUtil.info(MODULE, "批量调用用户行为分析解析商品详情结束:json为空");
            return null;
        }
        if(!jsonResult.containsKey("goodsList")){
            LogUtil.info(MODULE, "批量调用用户行为分析解析商品分类结束:不存在goodsList");
            return null;
        }
        if(null == props){
            props = new ArrayList<Long>();
        }
        //2 解析json
        //LogUtil.info(MODULE, "调用用户行为分析解析商品详情:"+jsonResult);
        JSONArray jsonGoodsArray = jsonResult.getJSONArray("goodsList");
        if(jsonGoodsArray ==null || jsonGoodsArray.isEmpty()){
            LogUtil.info(MODULE, "批量调用用户行为分析解析商品分类结束:goodsList为空");
            return null;  
        }
        
        List<GdsInfoDetailRespDTO> gdsInfoDetailRespList = new ArrayList<GdsInfoDetailRespDTO>();
        int goodsCount = jsonGoodsArray.size();
        for(int i = 0;i<goodsCount;i++){
            JSONObject gdsJson = jsonGoodsArray.getJSONObject(i);
            GdsInfoDetailRespDTO gdsInfo = null;
            try {
                gdsInfo = analysJsonObjToGdsDetial(gdsJson, new ArrayList<Long>(props),staffId);
            } catch (Exception e) {
                LogUtil.error(MODULE, "将行为分析返回的json转成商品详情异常");
            }
            if(gdsInfo!=null && StringUtil.isNotEmpty(gdsInfo.getId())){
                gdsInfoDetailRespList.add(gdsInfo);
            }
        }
        LogUtil.info(MODULE, "批量将行为分析返回的json转成商品详情列表结束：完成");
        return gdsInfoDetailRespList;
    }
    /**
     * 
     * analysJsonObjToGdsDetialList:(将行为分析返回的json转成商品详情列表 没有指定staffId). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @author zhanbh 
     * @param jsonResult
     * @param props 需要的商品属性id
     * @return 
     * @since JDK 1.6
     */
    public static List<GdsInfoDetailRespDTO> analysJsonObjToGdsDetialList(JSONObject jsonResult,List<Long> props){
        return analysJsonObjToGdsDetialList(jsonResult, props, null);
    }
    /**
     * 
     * analysJsonObjToGdsDetial:(将行为分析返回的json转成商品详情). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param gdsJson
     * @param props 需要的商品属性id
     * @param staffId 根据staff对应的会员等级返回折扣价
     * @return 返回商品详情，商品详情对应信息未扩展 如需扩展请调用extendGdsInfo
     * @since JDK 1.6
     */
    public static GdsInfoDetailRespDTO analysJsonObjToGdsDetial(JSONObject gdsJson,List<Long> props,Long staffId){
        LogUtil.info(MODULE, "将行为分析返回的json转成商品详情开始");
        //1 验证入参
        if(gdsJson == null || gdsJson.isEmpty()){
            LogUtil.info(MODULE, "将行为分析返回的json转成商品详情结束：json为空");
            return null;
        }
        if(!gdsJson.containsKey("gdsId")){
            LogUtil.info(MODULE, "将行为分析返回的json转成商品详情结束：gdsId为空");
            return null;
        }
        if(null == props){
            props = new ArrayList<Long>();
        }
        //2 解析json
        GdsInfoDetailRespDTO gdsInfo = null;
        Long gdsId = gdsJson.getLong("gdsId");
        Long skuId = null;
        String gdsDetialUrl = null;
        if(gdsJson.containsKey("skuId")){
            skuId = gdsJson.getLong("skuId");
        }
        
        /**
         * 增加测试代码便于本地测试商品数据  zhanbh 2016.12.19 上现网需注释掉
         */
        /*
        Long testGdsId = getTestGdsId();
        if(null != testGdsId && 0 < testGdsId){
            gdsId = testGdsId;
            skuId = null;
        }*/
        /**
         * 测试代码end
         */
        
        if(StringUtil.isNotEmpty(gdsId)){
            gdsDetialUrl = "/gdsdetail/"+gdsId.toString()+"-";
            //获取商品基本信息与折扣价
            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
            GdsQueryOption[] gdsOptions = new GdsQueryOption[] { 
                    GdsQueryOption.BASIC
                    };
            SkuQueryOption[] skuOptions = new SkuQueryOption[] { 
                    SkuQueryOption.BASIC,SkuQueryOption.CAlDISCOUNT
                    };
            gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
            gdsInfoReqDTO.setSkuQuerys(skuOptions);
            gdsInfoReqDTO.setId(gdsId);
            if(null != staffId){
                gdsInfoReqDTO.setStaffId(staffId);
            }
            if(StringUtil.isNotEmpty(skuId)){
                gdsDetialUrl+=skuId.toString();
                gdsInfoReqDTO.setSkuId(skuId);
            }
            gdsInfo = getGdsDetailByGdsId(gdsInfoReqDTO);
            if(null == gdsInfo){
                gdsInfo = new GdsInfoDetailRespDTO();
                gdsInfo.setId(gdsId);
                if(gdsJson.containsKey("category")){
                    gdsInfo.setMainCatgs(gdsJson.getString("category"));
                }
                if(gdsJson.containsKey("guidePrice")){
                    gdsInfo.setGuidePrice(gdsJson.getLong("guidePrice"));
                }
                if(gdsJson.containsKey("gdsName")){
                    gdsInfo.setGdsName(gdsJson.getString("gdsName"));
                }
            }
            gdsInfo.setUrl(gdsDetialUrl);
        }
        
        //获取其他信息
        if(gdsInfo!=null && StringUtil.isNotEmpty(gdsInfo.getId())){
            GdsSkuInfoRespDTO skuInfo = gdsInfo.getSkuInfo();
            //获取主图
            GdsMediaRespDTO mainPic = new GdsMediaRespDTO();
            if(gdsJson.containsKey("mainPicId")){
                mainPic.setMediaUuid(gdsJson.getString("mainPicId"));
            }else if(gdsJson.containsKey("gdsPicId")){
                mainPic.setMediaUuid(gdsJson.getString("gdsPicId"));
            }
            //获取属性
            Map<String, GdsPropRespDTO> allPropMaps = null;
            if(gdsJson.containsKey("skuProps")){
                allPropMaps = analysJsonObjToPropMap(gdsJson.getJSONArray("skuProps"), props);
            }
            //设值
            gdsInfo.setMainPic(mainPic);
            gdsInfo.setAllPropMaps(allPropMaps);
            if(null != skuInfo){
                skuInfo.setMainPic(mainPic);
                skuInfo.setAllPropMaps(allPropMaps);
            }
        }
        LogUtil.info(MODULE, "将行为分析返回的json转成商品详情结束");
        return gdsInfo;
    }
    /**
     * 
     * analysJsonObjToGdsDetial:(将行为分析返回的json转成商品详情). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param gdsJson
     * @param props 需要的商品属性id
     * @return 
     * @since JDK 1.6
     */
    public static GdsInfoDetailRespDTO analysJsonObjToGdsDetial(JSONObject gdsJson,List<Long> props){
        return analysJsonObjToGdsDetial(gdsJson, props, null);
    }
    /**
     * 
     * analysJsonObjToPropMap:(将行为分析返回的json转成商品属性). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param jsonPropArray
     * @param props
     * @return 
     * @since JDK 1.6
     */
    public static Map<String, GdsPropRespDTO> analysJsonObjToPropMap(JSONArray jsonPropArray,List<Long> props){
        LogUtil.info(MODULE, "将行为分析返回的json转成商品属性开始");
        //1 验证入参
        if(CollectionUtils.isEmpty(jsonPropArray)){
            LogUtil.info(MODULE, "将行为分析返回的json转成商品属性结束:json为空");
            return null;
        }
        if(CollectionUtils.isEmpty(props)){
            LogUtil.info(MODULE, "将行为分析返回的json转成商品属性结束:要查找属性为空");
            return null;
        }
        props = new ArrayList<Long>(props);
        //2 解析json
        Map<String, GdsPropRespDTO> propMap = new HashMap<String,GdsPropRespDTO>();
        int jsonArraySize = jsonPropArray.size();
        for(int i = 0;i<jsonArraySize;i++){
            JSONObject propJson = jsonPropArray.getJSONObject(i);
            GdsPropRespDTO propRespDto = new GdsPropRespDTO();
            Long propId = null;
            if(propJson.containsKey("prop_id") && propJson.containsKey("prop_name") && propJson.containsKey("prop_value")){
                propId = propJson.getLong("prop_id");
            }
            if(StringUtil.isNotEmpty(propId) && props.indexOf(propId)!= -1){
                props.remove(propId);
                propRespDto.setId(propId);
                propRespDto.setPropName(propJson.getString("prop_name"));
                List<GdsPropValueRespDTO> propValueList = new ArrayList<GdsPropValueRespDTO>();
                GdsPropValueRespDTO propValue = new GdsPropValueRespDTO();
                propValue.setPropId(propId);
                propValue.setPropValue(propJson.getString("prop_value"));
                propValueList.add(propValue);
                propRespDto.setValues(propValueList);
                propMap.put(propId.toString(), propRespDto);
            }
            
            if(props.isEmpty()){
                break;
            }
        }
        LogUtil.info(MODULE, "将行为分析返回的json转成商品属性结束：成功");
        return propMap;
    }
    /**
     * 
     * checkCollect:(判断是否被收藏). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param gdsId
     * @return 已收藏返回收藏Dto  为收藏 返回null
     * @since JDK 1.6
     */
    public static GdsCollectRespDTO checkCollect(Long gdsId){
        LogUtil.info(MODULE, "判断是否被收藏开始");
        if(StringUtil.isEmpty(gdsId)){
            LogUtil.info(MODULE, "判断是否被收藏结束：商品id为空");
            return null;
        }
        GdsCollectReqDTO reqDTO = new GdsCollectReqDTO();
        BaseStaff staffInfo= reqDTO.getStaff();
        if(null == staffInfo  || StringUtil.isEmpty(staffInfo.getId())){
            LogUtil.info(MODULE, "判断是否被收藏结束：用户未登陆");
            return null;
        }
        
        reqDTO.setGdsId(gdsId);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        reqDTO.setPageNo(1);
        reqDTO.setPageSize(1);
        PageResponseDTO<GdsCollectRespDTO> result = gdsCollectRSV.queryGdsCollectRespDTOPagingByStaff(reqDTO);
        if(null != result){
            List<GdsCollectRespDTO> resultList = result.getResult();
            if(CollectionUtils.isNotEmpty(resultList)){
                GdsCollectRespDTO collectDto = result.getResult().get(0); 
                if(null != collectDto && StringUtil.isNotEmpty(collectDto.getId())){
                    LogUtil.info(MODULE, "判断是否被收藏结束:已收藏");
                    return result.getResult().get(0);
                }
            }
        }
        LogUtil.info(MODULE, "判断是否被收藏结束:未收藏");
        return null;
    }
    /**
     * 
     * isGdsOnShelves:(商品是否上架). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param gdsId
     * @return boolean  true：已上架     false：非上架
     * @since JDK 1.6
     */
    public static boolean isGdsOnShelves(Long gdsId){
        LogUtil.info(MODULE, "判断商品是否上架开始");
        if(StringUtil.isEmpty(gdsId)){
            LogUtil.info(MODULE, "判断商品是否上架结束：商品id为空");
            return false;
        }
        GdsInfoDetailRespDTO gdsInfoDetailRespDTO = null;
        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        GdsQueryOption[] gdsOptions = new GdsQueryOption[] {GdsQueryOption.BASIC};
        gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
        gdsInfoReqDTO.setId(gdsId);
        try {
            gdsInfoDetailRespDTO = gdsInfoQueryRSV.queryGdsInfoDetail(gdsInfoReqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询商品基础信息异常", e);
            return true;//异常默认为上架
        }
        
        if(gdsInfoDetailRespDTO !=null && GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES.equalsIgnoreCase(gdsInfoDetailRespDTO.getGdsStatus())){
            LogUtil.info(MODULE, "判断商品是否上架结束：上架");
            return true;
        }
        LogUtil.info(MODULE, "判断商品是否上架结束：非上架");
        return false;
    }
    /**
     * 
     * isNumeric:(判断字符串是否为纯数字). <br/> 
     * 
     * @author zhanbh
     * @param str
     * @return 
     * @since JDK 1.6
     */
    public static boolean isNumeric(String str){
        if(StringUtil.isNotEmpty(str)){
            Pattern pattern = Pattern.compile("[0-9]*"); 
            Matcher isNum = pattern.matcher(str);
            if( isNum.matches() ){
                return true; 
            }
        }
        return false; 
    }
    /**
     * 
     * getTestGds:(获取行为分析测试商品). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @since JDK 1.6
     */
    private static Long getTestGdsId(){
        BaseSysCfgRespDTO respDto = null;
        String gdsIdStr = null;
        Long gdsId = null;
        try {
            respDto = SysCfgUtil.fetchSysCfg("CMS_ANAL_TEST_GDS");
        } catch (Exception e) {
            LogUtil.error(MODULE, "读取T_BASE_SYS_CFG 配置表：CMS_ANAL_TEST_GDS异常");
        }
        if(null != respDto){
            gdsIdStr = respDto.getParaValue();
        }
        if(isNumeric(gdsIdStr)){
            gdsId =  Long.parseLong(gdsIdStr);
        }
        return gdsId;
    }
}
