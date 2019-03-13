package com.zengshi.ecp.prom.service.busi.sku.impl;

import java.util.concurrent.CountDownLatch;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dao.model.PromStockLimit;
import com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuSV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-12-11 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CopyPromSkuTaskSVImpl implements Runnable {

    private static final String MODULE = CopyPromSkuTaskSVImpl.class.getName();

    
    private final PromSku promSku;
    
    private final PromStockLimit promStockLimit;
    
    private final CountDownLatch countDownLatch ;
    
    private IPromSkuSV promSkuSV=  EcpFrameContextHolder.getBean("promSkuSV", IPromSkuSV.class);
    
    public static final int cacheSeconds = 86400;//秒  86400秒=24小时
    
    public CopyPromSkuTaskSVImpl(PromSku promSku,PromStockLimit promStockLimit,CountDownLatch countDownLatch)

    {
        this.promSku=promSku;
        this.promStockLimit=promStockLimit;
        this.countDownLatch=countDownLatch;

    }

    public void run()

    {
        try

        {
            promSkuSV.savePromSku(promSku);
            promSkuSV.savePromStockLimit(promStockLimit);
        }
        catch (Exception ex)
        {
          LogUtil.error(MODULE, "CopyPromSkuTaskSVImpl传入参数="+JSON.toJSONString(promSku));
          LogUtil.error(MODULE, ex.toString());
        }finally{
            if(countDownLatch!=null){
                countDownLatch.countDown();
            }
        }
    }
}
