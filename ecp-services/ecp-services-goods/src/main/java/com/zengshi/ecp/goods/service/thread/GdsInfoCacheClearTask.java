/** 
 * Project Name:xhs-services-extend-server 
 * File Name:GdsInfoCacheClearTask.java 
 * Package Name:com.zengshi.ecp.xhsextend.facade.impl.eventual.thread 
 * Date:2017年2月8日下午3:31:27 
 * Copyright (c) 2017, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.thread;

import java.util.List;

import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoManageSV;
import com.zengshi.paas.utils.LogUtil;

/**
 * Title: ECP <br>
 * Project Name:xhs-services-extend-server <br>
 * Description: 稿件信息缓存清理线程<br>
 * Date:2017年2月8日下午3:31:27  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.7 
 */
public class GdsInfoCacheClearTask implements Runnable {
    
    private static final String MODULE = GdsInfoCacheClearTask.class.getName();
    
    private List<Long> skuIds;
    
    private Long gdsId;
    
    private IGdsInfoManageSV gdsManageSV;
    
    private static final long SLEEP_MILLIS = 3000L;
    
    
    public GdsInfoCacheClearTask(List<Long> skuIds, Long gdsId, IGdsInfoManageSV gdsManageSV) {
        this.skuIds = skuIds;
        this.gdsId = gdsId;
        this.gdsManageSV = gdsManageSV;
    }


    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see java.lang.Runnable#run() 
     */
    @Override
    public void run() {
        try {
            Thread.sleep(SLEEP_MILLIS);
            gdsManageSV.delAllCache(gdsId, skuIds);
        } catch (InterruptedException e) {
            LogUtil.error(MODULE, "稿件信息异步清理线程执行异常!",e);
        }
    }

}

