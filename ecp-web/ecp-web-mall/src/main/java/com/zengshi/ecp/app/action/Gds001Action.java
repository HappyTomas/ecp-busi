package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds001Req;
import com.zengshi.ecp.app.resp.Gds001Resp;
import com.zengshi.ecp.app.resp.gds.GdsDetailBaseInfo;
import com.zengshi.ecp.app.resp.gds.GdsPropBaseInfo;
import com.zengshi.ecp.app.resp.gds.GdsPropValueBaseInfo;
import com.zengshi.ecp.app.resp.gds.GdsSkuBaseInfo;
import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2MediaRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsMessageUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.order.dubbo.dto.RQueryGoodPayedRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.CipherUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 获取商品信息 Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月10日上午10:18:08 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
@Service("gds001")
@Action(bizcode = "gds001", userCheck = false)
@Scope("prototype")
public class Gds001Action extends AppBaseAction<Gds001Req, Gds001Resp> {
    
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;

    @Resource
    private IGdsInfoQueryRSV gdsInfoQueryRSV;

    @Resource
    private IShopInfoRSV shopInfoRSV;

    @Resource
    private IGdsCollectRSV gdsCollectRSV;

    @Resource
    private IReportGoodPayedRSV reportGoodPayedRSV;
    
    @Resource
    private IGdsInfoExternalRSV gdsInfoExternalRSV;

    private static final String MODULE = Gds001Action.class.getName();

    private static String KEY = "GDS_BROWSE_HIS";

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {

        Gds001Req gds001Req = this.request;
        Gds001Resp gds001Resp = this.response;
        GdsInfoReqDTO dto = new GdsInfoReqDTO();
        if (StringUtil.isNotEmpty(gds001Req.getGdsId())) {
            dto.setId(gds001Req.getGdsId());
        }
        if (StringUtil.isNotEmpty(gds001Req.getSkuId())) {
            dto.setSkuId(gds001Req.getSkuId());
        }
        
        
        GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] { GdsQueryOption.BASIC, GdsQueryOption.MEDIA, GdsQueryOption.PROP };
        SkuQueryOption[] skuQueryOptions = null;
        skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.SHOWSTOCK, SkuQueryOption.CAlDISCOUNT,SkuQueryOption.PROP };
        
        dto.setGdsQueryOptions(gdsQueryOptions);
        dto.setSkuQuerys(skuQueryOptions);

        List<String> propInputType = new ArrayList<String>();
        propInputType.add(GdsConstants.GdsProp.GDS_PROP_VALUE_INPUT_TYPE_RICHTXT);
        dto.setPropInputTypes(propInputType);

        GdsInfoDetailRespDTO resultDto = null;
        try {
            resultDto = gdsInfoQueryRSV.queryGdsInfoDetail(dto);
            
            if (resultDto != null && resultDto.getSkuInfo() != null) {
                // 计算分类折扣价格
                // dealDiscountPrice(resultDto);
                // 发送消息
                sendRecentlyBrowMsg(resultDto);
                
                boolean isVirtualProduct = gdsInfoExternalRSV.isVirtualProduct(new LongReqDTO(resultDto.getGdsTypeId()));
                resultDto.setVirtualProduct(isVirtualProduct);
            } else {
                resultDto = new GdsInfoDetailRespDTO();
                GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
                gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
                resultDto.setSkuInfo(gdsSkuInfoRespDTO);
                GdsDetailBaseInfo gdsDetailBaseInfo = new GdsDetailBaseInfo();
                ObjectCopyUtil.copyObjValue(resultDto, gdsDetailBaseInfo, null, false);
                GdsSkuBaseInfo gdsSkuBaseInfo = new GdsSkuBaseInfo();
                ObjectCopyUtil.copyObjValue(gdsSkuInfoRespDTO, gdsSkuBaseInfo, null, false);
                gdsDetailBaseInfo.setGdsSkuBaseInfo(gdsSkuBaseInfo);
                gds001Resp.setGdsDetailBaseInfo(gdsDetailBaseInfo);
            }

        } catch (BusinessException e) {
            if (resultDto == null || GdsErrorConstants.GdsInfo.ERROR_GOODS_GDSINFO_210005.equals(e.getErrorCode())) {
                resultDto = new GdsInfoDetailRespDTO();
                GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
                gdsSkuInfoRespDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
                resultDto.setSkuInfo(gdsSkuInfoRespDTO);

            }
            LogUtil.error(MODULE, "无法获取商品详情信息！", e);
        }
        String shopName = "";
        String stockStatus = "";
        String stockStatusDesc = "";
        if (StringUtil.isNotEmpty(resultDto)) {
            if (resultDto.getSkuInfo() != null) {
                stockStatus = GdsUtils.checkStcokStatus(resultDto.getSkuInfo().getRealAmount());
                stockStatusDesc = GdsUtils.checkStcokStatusDesc(resultDto.getSkuInfo().getRealAmount());
            }
            ShopInfoResDTO shopInfo = shopInfoRSV.findShopInfoByShopID(resultDto.getShopId());
            if (StringUtil.isNotEmpty(shopInfo)) {
                shopName = shopInfo.getShopName();
            } else {
                throw new BusinessException("web.gds.2000012");
            }
        }
        

        GdsDetailBaseInfo gdsDetailBaseInfo = new GdsDetailBaseInfo();
        ObjectCopyUtil.copyObjValue(resultDto, gdsDetailBaseInfo, null, false);
        GdsSkuBaseInfo gdsSkuBaseInfo = new GdsSkuBaseInfo();
        ObjectCopyUtil.copyObjValue(resultDto.getSkuInfo(), gdsSkuBaseInfo, null, false);
        
        // 设置基本信息描述URL地址。
        String baseInfoUrl = getBaseInfoUrl(gdsSkuBaseInfo.getGdsId(), gdsSkuBaseInfo.getId(),resultDto);
        gdsDetailBaseInfo.setBaseInfoUrl(baseInfoUrl);
        
        
        
        gdsDetailBaseInfo.setGdsSkuBaseInfo(gdsSkuBaseInfo);

        if (resultDto.getParams() != null) {
            List<GdsPropBaseInfo> params = new ArrayList<GdsPropBaseInfo>();
            for (GdsPropRespDTO gdsPropRespDTO : resultDto.getParams()) {
                GdsPropBaseInfo propBaseInfo = new GdsPropBaseInfo();
                ObjectCopyUtil.copyObjValue(gdsPropRespDTO, propBaseInfo, null, false);
                List<GdsPropValueBaseInfo> gdsPropValueBaseInfos = new ArrayList<GdsPropValueBaseInfo>();
                for (GdsPropValueRespDTO gdsPropValueRespDTO : gdsPropRespDTO.getValues()) {
                    GdsPropValueBaseInfo gdsPropValueBaseInfo = new GdsPropValueBaseInfo();
                    ObjectCopyUtil.copyObjValue(gdsPropValueRespDTO, gdsPropValueBaseInfo, null, false);
                    gdsPropValueBaseInfo.setPropId(gdsPropRespDTO.getId());
                    gdsPropValueBaseInfos.add(gdsPropValueBaseInfo);
                }
                propBaseInfo.setValues(gdsPropValueBaseInfos);
                params.add(propBaseInfo);
            }
            gdsDetailBaseInfo.setParams(params);
        }
        if (StringUtil.isBlank(gdsDetailBaseInfo.getContentInfoUrl())) {
        	if(gds001Req.getSkuId()==null || gds001Req.getSkuId()==0){
        		gdsDetailBaseInfo.setContentInfoUrl(getContentHtmlUrl(gds001Req.getGdsId().toString(),""));
        	}else{
        		gdsDetailBaseInfo.setContentInfoUrl(getContentHtmlUrl(gds001Req.getGdsId().toString(),gds001Req.getSkuId().toString()));
        	}
//        	gdsDetailBaseInfo.setContentInfoUrl(getContentHtmlUrl(gds001Req.getGdsId().toString(),gds001Req.getSkuId().toString()));
        }	
        if (StringUtil.isNotBlank(gdsDetailBaseInfo.getGdsDesc())) {
            gdsDetailBaseInfo.setGdsDesc(getHtmlUrl(gdsDetailBaseInfo.getGdsDesc()));
        } else {
            List<GdsPropRespDTO> props = resultDto.getProps();
            if (CollectionUtils.isNotEmpty(props)) {
                for (GdsPropRespDTO gdsPropRespDTO : props) {
                    if(GdsConstants.GdsProp.GDS_PROP_VALUE_INPUT_TYPE_RICHTXT.equals(gdsPropRespDTO.getPropInputType())){
                        List<GdsPropValueRespDTO> propValue=gdsPropRespDTO.getValues();
                        if (CollectionUtils.isNotEmpty(propValue)) {
                            gdsDetailBaseInfo.setGdsDesc(getHtmlUrl(propValue.get(0).getPropValue()));
                            if(StringUtil.isNotBlank(gdsDetailBaseInfo.getGdsDesc())){
                                break;
                            }
                        }
                    }
                }
            }
        }

        // 获取售买数量
        RQueryGoodPayedRequest goodPayedRequest = new RQueryGoodPayedRequest();
        goodPayedRequest.setSkuId(gdsDetailBaseInfo.getGdsSkuBaseInfo().getId());
        goodPayedRequest.setSiteId(1l);
        Long saleCount = reportGoodPayedRSV.querySumBuyNumBySkuId(goodPayedRequest);
        gdsDetailBaseInfo.setSaleCount(saleCount);

        // 获取商品是否被浏览
        GdsCollectReqDTO reqDTO = new GdsCollectReqDTO();
        reqDTO.setGdsId(this.request.getGdsId());
        reqDTO.setSkuId(this.request.getSkuId());
        reqDTO.setStaffId(reqDTO.getStaff().getId());

        PageResponseDTO<GdsCollectRespDTO> pageResponseDTO = gdsCollectRSV.queryGdsCollectRespDTOPagingByStaff(reqDTO);
        if (pageResponseDTO.getCount() > 0) {
            gdsDetailBaseInfo.setIfBrowse(true);
            gdsDetailBaseInfo.setCollectId(pageResponseDTO.getResult().get(0).getId());
        } else {
            gdsDetailBaseInfo.setIfBrowse(false);

        }

        // 获取库存阈值的配置参数
        gdsDetailBaseInfo.setShopName(shopName);
        gdsDetailBaseInfo.setStockStatus(stockStatus);
        gdsDetailBaseInfo.setStockStatusDesc(stockStatusDesc);
        
        // 中等图片规格.
        String middleImgSpec = "";
        // 中图宽高不为空.
        if(null != gds001Req.getMiddleImgHeight() && null != gds001Req.getMiddleImgWidth()){
            middleImgSpec = gds001Req.getMiddleImgHeight() + "x" + gds001Req.getMiddleImgWidth() + "!";
        }else{
            middleImgSpec = "700x700!";
        }
        
        if (resultDto.getMainPic() != null) {
            gdsDetailBaseInfo.setMainPicUrl(new  AiToolUtil().genImageUrl(resultDto.getMainPic().getMediaUuid(), middleImgSpec));
        } else {
            gdsDetailBaseInfo.setMainPicUrl(new  AiToolUtil().genImageUrl("", middleImgSpec));
        }

        List<String> imageUrlList = new ArrayList<String>();
       
        if (resultDto.getMedias() != null && resultDto.getMedias().size() > 0) {
            for (GdsGds2MediaRespDTO media : resultDto.getMedias()) {
                if (media.getMediaUuid() != null) {
                    if(imageUrlList.size() >= 5){
                        break;
                    }
                    String mediaUrl = new  AiToolUtil().genImageUrl(media.getMediaUuid(), middleImgSpec);
                    imageUrlList.add(mediaUrl);
                    
                }

            }

        }
        
        //设置缺货阈值数量
        Long num=0L;
        try {
            num=Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_LACK_THRESHOLD).getParaValue());
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取库存缺货阈值失败",e);
        }
        String shareUrl = "";
        if(resultDto != null && resultDto.getSkuInfo() != null){
        	shareUrl = getShareUrl(gds001Req.getGdsId().toString(),resultDto.getSkuInfo().getId().toString());
        }
        gdsDetailBaseInfo.setStockLackNum(num);
        gdsDetailBaseInfo.setShareUrl(shareUrl);
        gdsDetailBaseInfo.setImageUrlList(imageUrlList);
        gds001Resp.setGdsDetailBaseInfo(gdsDetailBaseInfo);
    }

    public void sendRecentlyBrowMsg(GdsInfoDetailRespDTO resultDto) {
        GdsBrowseHisReqDTO gdsBrowseHisReqDTO = new GdsBrowseHisReqDTO();
        try {
            // GDS_BROWSE_HIS_用户ID_单品ID
            String key = KEY + "_" + gdsBrowseHisReqDTO.getStaff().getId() + "_" + resultDto.getSkuInfo().getId();
            if (CacheUtil.getItem(key) == null) {
                // 不存在则发消息
                gdsBrowseHisReqDTO.setGdsId(resultDto.getSkuInfo().getGdsId());
                gdsBrowseHisReqDTO.setSkuId(resultDto.getSkuInfo().getId());
                gdsBrowseHisReqDTO.setShopId(resultDto.getShopId());
                gdsBrowseHisReqDTO.setStaffId(gdsBrowseHisReqDTO.getStaff().getId());
                gdsBrowseHisReqDTO.setBrowsePrice(resultDto.getSkuInfo().getRealPrice());
                GdsMessageUtil.sendGdsBrowseMessage(gdsBrowseHisReqDTO);
                // 缓存到redis
                CacheUtil.addItem(key, key, 1 * 60 * 60 * 6);
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "发送消息失败！", e);
        } catch (Exception e) {
            LogUtil.error(MODULE, "发送消息失败！", e);
        }
    }
    
    private String getBaseInfoUrl(Long gdsId,Long skuId,GdsInfoDetailRespDTO gdsInfoDetailRespDTO){
        if(null != gdsId && null != skuId && CollectionUtils.isNotEmpty(gdsInfoDetailRespDTO.getSkuInfo().getGdsProps())){
            
            if(!existBaseInfo(gdsInfoDetailRespDTO.getSkuInfo().getGdsProps())){
                return "";
            }
            CmsSiteRespDTO cms = CmsCacheUtil.getCmsSiteCache(1L);
            if (cms != null && StringUtil.isNotBlank(cms.getSiteUrl())) {
                String baseUrl = cms.getSiteUrl();
                boolean b = baseUrl.endsWith("/");
                if(b){
                    baseUrl = baseUrl.substring(0, baseUrl.length()-1);
                }
                return baseUrl + "/gdsdetail/html/baseinfo/" + gdsId + "-"+skuId;
            }  
        }
        return "";
    }
    
    
    private boolean existBaseInfo(List<GdsPropRespDTO> gdsPropRespDTOLst){
        boolean exist = false;
        for(GdsPropRespDTO resp : gdsPropRespDTOLst){
            if(GdsConstants.Commons.STATUS_VALID.equals(resp.getIfBasic())){
                List<GdsPropValueRespDTO> values = resp.getValues();
                if(CollectionUtils.isNotEmpty(values)){
                    for(GdsPropValueRespDTO value : values){
                        if(StringUtil.isNotBlank(value.getPropValue())){
                            exist = true;
                            break;
                        }
                    }
                }
            }
        }
        return exist;
    }

    private String getHtmlUrl(String vfsId) {
        if (StringUtil.isBlank(vfsId)) {
            return "";
        }
        CmsSiteRespDTO cms = CmsCacheUtil.getCmsSiteCache(1L);
        if (cms != null && StringUtil.isNotBlank(cms.getSiteUrl())) {
            String baseUrl = cms.getSiteUrl();
            boolean b = baseUrl.endsWith("/");
            if(b){
                baseUrl = baseUrl.substring(0, baseUrl.length()-1);
            }
            return baseUrl + "/gdsdetail/html/" + vfsId;
        }
        return "";
    }
    
    private String getContentHtmlUrl(String gdsId,String skuId) {
        if (StringUtil.isBlank(gdsId)) {
            return "";
        }
        CmsSiteRespDTO cms = CmsCacheUtil.getCmsSiteCache(1L);
        if (cms != null && StringUtil.isNotBlank(cms.getSiteUrl())) {
            String baseUrl = cms.getSiteUrl();
            boolean b = baseUrl.endsWith("/");
            if(b){
                baseUrl = baseUrl.substring(0, baseUrl.length()-1);
            }
            return baseUrl + "/gdsdetail/html/contentinfo/" + gdsId+"-"+skuId;
        }
        return "";
    }
    
    private String getShareUrl(String gdsId,String skuId) {
    	String shareUrl="";
		String pcUrl = "";
		CmsSiteRespDTO cms = CmsCacheUtil.getCmsSiteCache(1L);
        if (cms != null && StringUtil.isNotBlank(cms.getSiteUrl())) {
        	pcUrl = cms.getSiteUrl();
            boolean c = pcUrl.endsWith("/");
            if(c){
            	pcUrl = pcUrl.substring(0, pcUrl.length()-1);
            }
//            return baseUrl + "/gdsdetail/html/" + gdsIdAndSkuId;
        }
        shareUrl = pcUrl+"/gdsdetail/share?gdsId="+gdsId+"&skuId="+skuId+"&staffId="+CipherUtil.encrypt(String.valueOf(StaffLocaleUtil.getStaff().getId()));
        return shareUrl;
	}
}
