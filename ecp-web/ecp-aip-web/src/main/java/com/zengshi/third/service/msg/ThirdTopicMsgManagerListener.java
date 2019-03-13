package com.zengshi.third.service.msg;

import org.apache.commons.lang3.StringUtils;

import com.zengshi.paas.message.Message;
import com.zengshi.paas.message.MessageListener;
import com.zengshi.paas.message.MessageStatus;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.util.ThirdTopicUtil;

/**
 * MQ监听
 */
public class ThirdTopicMsgManagerListener implements MessageListener {

    public void deltaImportIndex(String message){
        ThirdTopicMsgManager.submit(message);        
    }

    @Override
    public void receiveMessage(Message message, MessageStatus status) {
    	if(message!=null){
	        if(StringUtils.isNotEmpty(message.getTopic())){
		        if (ThirdTopicUtil.THIRD_TOPIC_MESSAGE_NAME.equals(message.getTopic())) {
		            // 接收到消息
		            LogUtil.info(ThirdTopicMsgManagerListener.class.getName(), "【第三方平台推送-本系统接收消息】接收到消息：" + (String) message.getMsg());
		           
		            this.deltaImportIndex((String) message.getMsg());
		        }
	        }else{
	        	 LogUtil.info(ThirdTopicMsgManagerListener.class.getName(), "【第三方平台推送-本系统接收消息】接收到消息：message.getTopic()==null");
	        }
    	}else{
    		 LogUtil.info(ThirdTopicMsgManagerListener.class.getName(), "【第三方平台推送-本系统接收消息】接收到消息：message==null");
    	}
    }

}
