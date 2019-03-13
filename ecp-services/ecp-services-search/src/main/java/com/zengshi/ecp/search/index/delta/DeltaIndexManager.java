package com.zengshi.ecp.search.index.delta;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.search.index.SearchThreadFactory;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

public class DeltaIndexManager {
    
    public final static String MODULE = "【搜索引擎】增量索引";
    
    /**
     * 使用无界队列尽量避免消息不被处理情况，初始化队列长度为200
     */
    private static int capacity=200;
    
    private static BlockingQueue<Runnable> queue;
    
    private static int nthreads=5;

    private static ReentrantLock rlock = new ReentrantLock();
    
    private static ExecutorService pool;
    
    private static ExecutorService getPool() {

        try {
            rlock.lock();
            if (pool == null) {
                queue = new LinkedBlockingQueue<Runnable>(capacity);
                pool = new ThreadPoolExecutor(nthreads, nthreads, 0L, TimeUnit.MILLISECONDS, queue,new SearchThreadFactory("增量索引"),new DeltaIndexRejectPolicy());
            }
        }finally {
            rlock.unlock();
        }

        return pool;

    }
    
    /**
     * 增量任务提交，需要处理队列处理能力超出异常
     * @param runnable
     * @throws BusinessException
     */
    public static void submit(String message) throws BusinessException{
        getPool().execute(new DeltaIndexRunnable1(message));
    }
    
    public static void submit(DeltaMessage message) throws BusinessException{
        getPool().execute(new DeltaIndexRunnable2(message));
    }
     
    public static void shutdown() {
        if (pool != null) {
            if (!pool.isShutdown() || !pool.isTerminated()) {
                pool.shutdownNow();
                try {
                    pool.awaitTermination(5, TimeUnit.MINUTES);
                } catch (InterruptedException e) {
                    LogUtil.error(MODULE, "awaitTermination终止线程池异常", e);
                }
            }
        }
    }

    public static class DeltaIndexRejectPolicy implements RejectedExecutionHandler {
        /**
         * Creates an {@code DeltaIndexRejectPolicy}.
         */
        public DeltaIndexRejectPolicy() { }

        /**
         * Always throws RejectedExecutionException.
         *
         * @param r the runnable task requested to be executed
         * @param e the executor attempting to execute this task
         * @throws RejectedExecutionException always.
         */
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) throws BusinessException{
            
            //CallerRunsPolicy
            //A handler for rejected tasks that runs the rejected task directly in the calling thread of the execute method, 
            //unless the executor has been shut down, in which case the task is discarded.
            if (!e.isShutdown()) {
                
                LogUtil.info(MODULE, "增量线程池队列已满！使用主线程执行增量任务");
                r.run();
            }
            
            //AbortPolicy
//            throw new BusinessException(
//                    SearchMessageKeyContants.Error.KEY_ERROR_IMPORT_DELTA, new String[] {
//                            "", "增量线程池队列已满！" });
        }
    }
}

