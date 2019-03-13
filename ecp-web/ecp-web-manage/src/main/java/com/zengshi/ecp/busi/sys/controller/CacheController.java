/** 
 * Project Name:ecp-web-manage 
 * File Name:CacheController.java 
 * Package Name:com.zengshi.ecp.busi.sys.controller 
 * Date:2015-9-2下午3:50:17 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.goods.vo.GdsManageVO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.coupon.dubbo.util.CoupCacheUtil;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsCacheUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsCatalogCacheUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsCategoryCacheUtil;
import com.zengshi.ecp.prom.dubbo.util.PromCacheUtil;
import com.zengshi.ecp.server.front.util.BaseAreaAdminUtil;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.util.StaffUtil;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 关于缓存的一系列处理<br>
 * Date:2015-9-2下午3:50:17  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Controller
@RequestMapping(value="/cache")
public class CacheController extends EcpBaseController {
    private static String MODULE = CacheController.class.getName();
    @Resource
    private IGdsInfoQueryRSV iGdsInfoQueryRSV;
    
    @RequestMapping(value="/init")
    public String init(){
        return "/cache/cache-init";
    }
    
    @RequestMapping(value="/clear/{paramType}")
    @ResponseBody
    public Map<String,Object> clearCache(@PathVariable("paramType") String paramType){
        
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("flag", "1");
        long size = 0L;
        if("BaseParam".equalsIgnoreCase(paramType)){
            size = this.clearBaseParamCache();
            
        } else if("SysParam".equalsIgnoreCase(paramType)){
            size = this.clearSysParamCache();
             
        } else if("AreaAdmin".equalsIgnoreCase(paramType)){
            size = this.clearAreaAdminCache();
            
        }else if("ShopCache".equalsIgnoreCase(paramType)){
            size = this.clearShopCache();
        }else if("CompanyCache".equalsIgnoreCase(paramType)){
            size = this.clearCompanyCache();
        }else if("PromTypeCache".equalsIgnoreCase(paramType)){
            size = this.clearPromTypeCache();
        }else if("PromTypeMsgCache".equalsIgnoreCase(paramType)){
            size = this.clearPromTypeMsgCache();
        }else if("CoupTypeCache".equalsIgnoreCase(paramType)){
            size = this.clearCoupTypeCache();
        }else if("CmsSiteCache".equalsIgnoreCase(paramType)){
            size = this.clearCmsSiteCache();
        }else if("ScoreConfigCache".equalsIgnoreCase(paramType)){
            size = this.clearScoreConfigCache();
        }else if("MenuCache".equalsIgnoreCase(paramType)){
            size = this.clearMenuCache();
        }else if("GdsCategoryCache".equalsIgnoreCase(paramType)){
            size = this.clearGdsCategory();
        }else if("GdsCatalog2SiteCache".equalsIgnoreCase(paramType)){
            size = this.clearGdsCatalog2Site();
        }else if("GdsCatalogCache".equalsIgnoreCase(paramType)){
            size = this.clearGdsCatalog();
        }else if("GdsTypeCache".equalsIgnoreCase(paramType)){
            size = this.clearGdsType();
        }else{
            map.put("flag", "0");
            map.put("msg", "需要清理的缓存类型不对：paramType:"+ paramType);
        }
        map.put("size", size);
        
        return map;
    }
  
    private long clearBaseParamCache(){
        return BaseParamUtil.clearCache();
    }
    
    private long clearSysParamCache(){
        return SysCfgUtil.clearCache();
    }
    
    private long clearAreaAdminCache(){
        return BaseAreaAdminUtil.clearCache();
    }
    
    private long clearShopCache(){
        return StaffUtil.clearShopCache();
    }
    
    private long clearCompanyCache(){
        return StaffUtil.clearCompanyCache();
    }
    private long clearScoreConfigCache(){
        return StaffUtil.clearScoreConfigCache();
    }
    private long clearPromTypeCache(){
        return PromCacheUtil.clearPromTypeCache();
    }
    private long clearPromTypeMsgCache(){
        return PromCacheUtil.clearPromTypeMsgCache();
    }
    private long clearCoupTypeCache(){
        return CoupCacheUtil.clearCoupTypeCache();
    }
    private long clearCmsSiteCache(){
        return CmsCacheUtil.clearCmsSiteCache();
    }
    private long clearMenuCache(){
        return StaffUtil.clearMenuCache();
    }
    /*
     * 
     * clearGdsCatalog2Site:清除站点与目录关联关系缓存. <br/> 
     * 
     * @author liyong7
     * @return 
     * @since JDK 1.6
     */
    private long clearGdsCatalog2Site(){
    	return GdsCatalogCacheUtil.clearGdsCatalog2SiteCache();
    }
    /*
     * 
     * clearGdsCatalog:清除目录缓存. <br/> 
     * 
     * @author liyong7
     * @return 
     * @since JDK 1.6
     */
    private long clearGdsCatalog(){
    	GdsCatalogCacheUtil.clearGdsDefaultCatalogCache();
    	GdsCatalogCacheUtil.GdsCatalog2Shop.clearAllGdsCatlog2ShopFromCache();
    	return GdsCatalogCacheUtil.clearGdsCatalogCache();
    	
    }

    private long clearGdsType(){
        CacheUtil.delItem("gdsTypeList");
        return -1l;

    }
    
    private long clearGdsCategory(){
    	return GdsCategoryCacheUtil.clearGdsCategoryCache();
    }
    
    
    /**
     * 
     * clearGdsCache:(根据商品编码清除单个商品的缓存). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @param gdsId
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/clearGds")
    @ResponseBody
    public Map<String,String> clearGdsCache(GdsManageVO vo){
        Map<String,String> map = new HashMap<>();
        try {
            GdsInfoReqDTO req=new GdsInfoReqDTO();
            if(StringUtil.isNotEmpty(vo.getGdsId())){
                delGdsInfoCache(vo.getGdsId());
                req.setId(vo.getGdsId());
            }else{
                map.put("result", "fail");
                map.put("msg", "商品编码为必填参数！");
                return map;
            }
            List<Long>  ids=iGdsInfoQueryRSV.querySkuIdsByGdsId(req);
            int size = 0;
            if(CollectionUtils.isNotEmpty(ids)){
                for (Long id : ids) {
                    delSkuInfoCache(id);
                    // 删除单品主图缓存
                    GdsCacheUtil.delCacheItem(GdsConstants.GdsInfoCacheKey.SKU_MAINPIC_CACHE_KEY_PREFIX + id);
                    size ++;
                }
            }else{
                map.put("result", "fail");
                map.put("msg", "商品编码查不到对应的商品！");
                return map;
            }
            map.put("size", size+"");
            map.put("result", "success");
        } catch (Exception e) {
            LogUtil.error(MODULE, "clean gdsInfo Cache Failed!",e);
            map.put("result", "fail");
        }
        map.put("msg", "");
        return map;
    }
    
    /**
     * 
     * clearAllGds:(清除所有商品缓存信息). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/clearAllGds")
    @ResponseBody
    public Map<String,String> clearAllGds(Model model){
        Map<String,String> map = new HashMap<>();
        List<String> keys=CacheUtil.keys("GDS_*");
        if(CollectionUtils.isNotEmpty(keys)){
            for (String key : keys) {
                try {
                    CacheUtil.delItem(key);
                } catch (Exception e) {
                    LogUtil.error(MODULE, "del gdsInfo cache failed!  key:"+key);
                    map.put("result", "fail");
                }
            }
            map.put("size", keys.size()+"");
            map.put("result", "success");
        }
        return map;
    }
    private void delSkuInfoCache(Long skuId) {
        try {
            CacheUtil.delItem(GdsConstants.GdsInfoCacheKey.SKU_CACHE_KEY_PREFIX + skuId);
        } catch (Exception e) {
            LogUtil.error(MODULE, "delete skuInfo cache failed! please check  Cache Server!", e);
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
}

