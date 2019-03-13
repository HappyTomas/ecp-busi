package com.zengshi.ecp.search.index;

import com.zengshi.ecp.general.solr.util.DeltaUtils;
import com.zengshi.ecp.search.index.delta.DeltaMessageListener;
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
public class DeltaIndexService {
    
    private final static String MODULE = "【搜索引擎】DeltaIndexService";
    
    public void start(){
        
        LogUtil.info(MODULE,"启动搜索引擎【"+DeltaUtils.TOPIC_DELTAINDEX_NAME+"】增量队列监听服务！");
        MessageUtil.consumer(DeltaUtils.TOPIC_DELTAINDEX_NAME, new DeltaMessageListener());
        
    }
    
}

