package com.zengshi.ecp.cms.service.listener;


import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MessageUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月21日下午8:12:48  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6
 */
public class CmsGdsDelListenerServiceStart {
    
    private final static String MODULE = "CmsGdsDelListenerServiceStart";
 
    /** 
     * start:(启动监听商品域商品删除服务). <br/> 
     * TODO(当商品域删除商品时，CMS模块同步删除。).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh  
     * @since JDK 1.6 
     */ 
    public void start(){
        
        LogUtil.info(MODULE,"启动商品删除监听服务【"+CmsConstants.ParamConfig.TOPIC_GDSDEL+"】！");
        
        MessageUtil.consumer(CmsConstants.ParamConfig.TOPIC_GDSDEL, new CmsGdsDelMessageListener());
    }

}

