package com.zengshi.ecp.goods.service.listener;


import com.zengshi.ecp.goods.dubbo.util.GdsMessageUtil;
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
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class StockCatgChangeListenerServiceStart {
    
    private final static String MODULE = "StockCatgChangeListenerServiceStart";
 
    public void start(){
        
        LogUtil.info(MODULE,"启动异步更改库存商品分类服务【"+GdsMessageUtil.TOPIC_STOCKCATG_CHANGE+"】监听服务！");
        
        MessageUtil.consumer(GdsMessageUtil.TOPIC_STOCKCATG_CHANGE, new StockCatgChangeMessageListener());
    }

}

