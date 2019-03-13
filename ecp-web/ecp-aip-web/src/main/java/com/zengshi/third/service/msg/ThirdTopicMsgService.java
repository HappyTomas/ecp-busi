package com.zengshi.third.service.msg;

import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MessageUtil;
import com.zengshi.util.ThirdTopicUtil;

public class ThirdTopicMsgService {
    
    private final static String MODULE = "【第三方平台消息】ThirdTopicMsgService";
    
    public void start(){
        
        LogUtil.info(MODULE,"启动第三方平台消息引擎【"+ThirdTopicUtil.THIRD_TOPIC_MESSAGE_NAME+"】订单队列监听服务！");
        MessageUtil.consumer(ThirdTopicUtil.THIRD_TOPIC_MESSAGE_NAME, new ThirdTopicMsgManagerListener());
        
    }
    
}

