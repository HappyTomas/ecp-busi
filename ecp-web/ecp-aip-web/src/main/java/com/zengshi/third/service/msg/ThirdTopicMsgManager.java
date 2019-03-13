package com.zengshi.third.service.msg;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.interfaces.msgSyc.IUnpfMsgSycRSV;
import com.zengshi.model.TaobaoOrderReq;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.third.service.msgHandler.interfaces.IThirdMsgHandlerSV;
import com.zengshi.third.service.thread.task.ThirdMsgRunnable;
import com.alibaba.fastjson.JSON;

@Service("thirdTopicMsgManager")
public class ThirdTopicMsgManager {
    
    public final static String MODULE = ThirdTopicMsgManager.class.getName();
    
    /**
     * 使用无界队列尽量避免消息不被处理情况，初始化队列长度为200
     */
    private static int capacity=200;
    
    private static BlockingQueue<Runnable> queue;
    
    private static int nthreads=5;

    private static ReentrantLock rlock = new ReentrantLock();
    
    private static ExecutorService pool;
    
    private static IUnpfMsgSycRSV unpfMsgSycRSV;
    
    private static HashMap<String,IThirdMsgHandlerSV> taobaoMsgMap;
    
    public  IUnpfMsgSycRSV getUnpfMsgSycRSV() {
		return unpfMsgSycRSV;
	}

	public  HashMap<String, IThirdMsgHandlerSV> getTaobaoMsgMap() {
		return taobaoMsgMap;
	}
	
	@Resource(name="unpfMsgSycRSV")
	public  void setUnpfMsgSycRSV(IUnpfMsgSycRSV unpfMsgSycRSV) {
		ThirdTopicMsgManager.unpfMsgSycRSV = unpfMsgSycRSV;
	}
	
	@Resource(name="taobaoMsgMap")
	public  void setTaobaoMsgMap(
			HashMap<String, IThirdMsgHandlerSV> taobaoMsgMap) {
		ThirdTopicMsgManager.taobaoMsgMap = taobaoMsgMap;
	}
	//获得各个平台sv配置
	public static IThirdMsgHandlerSV getSV(String topic)throws BusinessException{
			
			IThirdMsgHandlerSV sv=null;
	    	
	    	if(CollectionUtils.isEmpty(taobaoMsgMap)){
	    		return sv;
	    	}
	    	if(!taobaoMsgMap.containsKey(topic)){
	    		return sv;
	    	}else{
	    		sv=taobaoMsgMap.get(topic);
	    	}
	    	return sv;
		}
		
    private static ExecutorService getPool() {

        try {
            rlock.lock();
            if (pool == null) {
                queue = new LinkedBlockingQueue<Runnable>(capacity);
                pool = new ThreadPoolExecutor(nthreads, nthreads, 0L, TimeUnit.MILLISECONDS, queue,new ThirdTopicThreadFactory("第三方平台消息"),new DeltaIndexRejectPolicy());
            }
        }finally {
            rlock.unlock();
        }

        return pool;

    }
    
    /**
     * 任务提交，需要处理队列处理能力超出异常
     * @param runnable
     * @throws BusinessException
     */
    public static void submit(String message) throws BusinessException{
    	 TaobaoOrderReq orderMsgReq = JSON.parseObject(message,
					TaobaoOrderReq.class);
    	IThirdMsgHandlerSV sv=getSV(orderMsgReq.getTopic());
        getPool().execute(new ThirdMsgRunnable(message,sv,unpfMsgSycRSV));
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

