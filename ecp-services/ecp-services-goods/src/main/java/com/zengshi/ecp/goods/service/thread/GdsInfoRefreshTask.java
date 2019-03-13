package com.zengshi.ecp.goods.service.thread;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.util.GdsCacheUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:xhs-services-extend-server <br>
 * Description: 稿件信息消息服务，清缓存，发增量，发newsDone消息<br>
 * Date:2017年2月8日下午3:31:27  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.7 
 */
public class GdsInfoRefreshTask implements Runnable {
    
    private static final String MODULE = GdsInfoRefreshTask.class.getName();
    
    
    private List<GdsRefreshReq> refreshLst;
    
    
    public GdsInfoRefreshTask(List<GdsRefreshReq> refreshLst) {
       this.refreshLst = refreshLst;
    }


    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see java.lang.Runnable#run() 
     */
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            if(CollectionUtils.isNotEmpty(refreshLst)){
                for(GdsRefreshReq req : refreshLst){
                    LogUtil.info(MODULE, "开始刷新商品ID为:" + req.getGdsId() + "的缓存!");
                    // 删除商品主图缓存
                    GdsCacheUtil.delGdsInfoCache(req.getGdsId());
                    GdsCacheUtil.delGdsPicCache(req.getGdsId());
                    GdsCacheUtil.delCacheItem(GdsConstants.GdsInfoCacheKey.SKU_MAINPIC_CACHE_KEY_PREFIX + req.getSkuId());
                    GdsUtils.sendGdsIndexMsg(null, "T_GDS_INFO", MODULE, req.getGdsId(), req.getCatlogId());
                    LogUtil.info(MODULE, "成功刷新商品ID为:" + req.getGdsId() + "的缓存!");
                }
            }
        } catch (InterruptedException e) {
            LogUtil.error(MODULE, "稿件信息异步消息发送线程执行异常!商品ID：" +  refreshLst.toString() + "发起增量索引异常",e);
        }
    }

}

