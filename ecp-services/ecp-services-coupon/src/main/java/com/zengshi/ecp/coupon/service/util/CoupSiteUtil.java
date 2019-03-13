package com.zengshi.ecp.coupon.service.util;

import java.util.HashMap;
import java.util.Map;

import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-24 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CoupSiteUtil {

    private static final String MODULE = CoupSiteUtil.class.getName();
    /**
     * 获取站点缓存
     * @param siteId
     * @return
     * @author huangjx
     */
    public static  CmsSiteRespDTO getSiteInfo(Long siteId) {
        CmsSiteRespDTO  resp = new CmsSiteRespDTO();
        LogUtil.info(MODULE, "========开始获取站点缓存：" + resp);
        try {
            Map map = (HashMap) CacheUtil
                    .getItem(CmsConstants.ParamConfig.CMS_SITE_CACHE);
            if(map!=null){
                Object obj= map.get(siteId);
                if(obj!=null){
                    resp=(CmsSiteRespDTO)obj;
                }
            }
            LogUtil.info(MODULE, "========结束获取站点缓存：" + resp);
        } catch (Exception e) {
            LogUtil.error(MODULE, "========获取站点缓存报错：" + resp);
        }
        if(resp==null){
            resp = new CmsSiteRespDTO();
        }
        return resp;
    }
}
