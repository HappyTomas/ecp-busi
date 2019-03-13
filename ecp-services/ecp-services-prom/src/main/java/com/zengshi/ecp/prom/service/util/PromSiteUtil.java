package com.zengshi.ecp.prom.service.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-prom <br>
 * Description: <br>
 * Date:2015年9月30日上午9:36:48 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version
 * @since JDK 1.7
 */
@Service("promSiteUtil")
public class PromSiteUtil {

    private static final String MODULE = PromSiteUtil.class.getName();
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
