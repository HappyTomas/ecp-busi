package com.zengshi.ecp.goods.service.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * Title: ECP <br>
 * Project Name:xhs-services-extend-server <br>
 * Description: 线程池执行工具类<br>
 * Date:2017年3月31日下午11:40:34  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class ThreadPoolExecutorUtil {

    private static BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
    
    private static final ThreadPoolExecutor POOL = new ThreadPoolExecutor(20, 20, 1, TimeUnit.HOURS, queue, new ThreadPoolExecutor.CallerRunsPolicy());

    
    public static void commitTask(Runnable runnable){
        if(null != runnable){
            POOL.execute(runnable);
        }
    }
    
    
    public static Future<?> submitTask(Runnable runnable){
        if(null != runnable){
           return POOL.submit(runnable);
        }
        return null;
    }
    
   /* public static void shutdown(){
        POOL.shutdown();
    }*/
    
}

