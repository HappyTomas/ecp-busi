package com.zengshi.ecp.im.queue;

import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by jiangysh on 2016/9/7.
 */
public class ExtendQueue<T extends QueueItem> extends PriorityBlockingQueue<QueueItem> implements Observer{
    private final static String MESSAGE_TOPIC_PREFIX="im_session_topic_";//消息topic前缀
    private Map<String,ExtendQueue<T>> queueMap=new ConcurrentHashMap<String,ExtendQueue<T>>();
    private String group;//分组名称

    public ExtendQueue(){
        super();
    }

    public ExtendQueue(String group){
        super();
        this.group=group;
    }

    /**
     * 添加队列
     * @param group
     * @param queue
     */
    public void addQueue(String group,ExtendQueue<T> queue){
        queueMap.put(group,queue);
    }
    /**
     *入分组队列
     * @param item
     */
    public void gput(T item){
        if(null==item){
            return;
        }
        synchronized (queueMap){
            if(queueMap.containsKey(group)){
                queueMap.get(group).put(item);
            }else{
                this.put(item);
                queueMap.put(group,this);
            }
        }
    }

    /**
     * 出分组队列
     * @param group
     * @return
     */
    public T gpoll(String group){
        if(queueMap.containsKey(group)){
            return (T)queueMap.get(group).poll();
        }
        return null;
    }

    protected String getTopic(){
        if(StringUtils.hasText(this.group)){
            return MESSAGE_TOPIC_PREFIX+this.group;
        }else{
            return MESSAGE_TOPIC_PREFIX;
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
