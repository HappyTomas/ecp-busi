package com.zengshi.third.service.msg.impl.taobao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.model.TaobaoOrderReq;
import com.zengshi.model.ThirdMsgReq;
import com.zengshi.paas.utils.MessageUtil;
import com.zengshi.third.service.msg.interfaces.IThirdGetMsgHandlerSV;
import com.zengshi.util.ThirdTopicUtil;
import com.alibaba.fastjson.JSON;
import com.taobao.api.internal.tmc.Message;
import com.taobao.api.internal.tmc.MessageHandler;
import com.taobao.api.internal.tmc.TmcClient;
import com.taobao.api.internal.tmc.MessageStatus;

public class TaobaoGetMsgSVImpl implements IThirdGetMsgHandlerSV {

	private final static Logger logger = LoggerFactory
			.getLogger(TaobaoGetMsgSVImpl.class);

	// 淘宝 & tmall 监听开始
	@Override
	public void init(final ThirdMsgReq thirdMsgReq) throws BusinessException {
		try {
			TmcClient client = new TmcClient(thirdMsgReq.getAppkey(),
					thirdMsgReq.getAppscret(), "default"); // 关于default参考消息分组说明
			// 已经有存在 返回
			if (client.isOnline()) {
				return;
			}
			client.setMessageHandler(new MessageHandler() {
				public void onMessage(Message message, MessageStatus status) {
					logger.info("onMessage--------消息Topic"+message.getTopic());
					logger.info("onMessage--------消息Content"+message.getContent());
					try {

						TaobaoOrderReq entity=new TaobaoOrderReq();
						entity.setShopId(thirdMsgReq.getShopId());
						entity.setPlatType(thirdMsgReq.getPlatType());
						entity.setContent(message.getContent());
						entity.setId(message.getId());
						entity.setOutgoingTime(message.getOutgoingTime());
						entity.setPubAppKey(message.getPubAppKey());
						entity.setPubTime(message.getPubTime());
						entity.setTopic(message.getTopic());
						entity.setShopAuthId(thirdMsgReq.getShopAuthId());
						//消息推送
						 MessageUtil.send(JSON.toJSONString(entity), ThirdTopicUtil.THIRD_TOPIC_MESSAGE_NAME);
						
					} catch (Exception e) {
						logger.error("appkey:" + thirdMsgReq.getAppkey()
								+ "消息地址：" + thirdMsgReq.getTopicUrl()
								+ "taobao接收处理服务报错了" + e.toString());
						status.fail(); // 消息处理失败回滚，服务端需要重发
						// 重试注意：不是所有的异常都需要系统重试。
						// 对于字段不全、主键冲突问题，导致写DB异常，不可重试，否则消息会一直重发
						// 对于，由于网络问题，权限问题导致的失败，可重试。
						// 重试时间 5分钟不等，不要滥用，否则会引起雪崩
					}
				
				}
			});
			// client.connect("ws://mc.api.taobao.com"); //
			// 消息环境地址：ws://mc.api.tbsandbox.com/
			try {
				client.connect(thirdMsgReq.getTopicUrl());
			} catch (Exception e) {
				logger.error("appkey:" + thirdMsgReq.getAppkey() + "消息地址："
						+ thirdMsgReq.getTopicUrl() + "taobao connection报错了"
						+ e.toString());
			} // 消息环境地址：ws://mc.api.tbsandbox.com/
		} catch (Exception e) {
			logger.error("appkey:" + thirdMsgReq.getAppkey() + "消息地址："
					+ thirdMsgReq.getTopicUrl() + "taobao init报错了"
					+ e.toString());
		}
	}

	@Override
	public void destroy(final ThirdMsgReq thirdMsgReq) throws BusinessException {
		try {
			TmcClient client = new TmcClient(thirdMsgReq.getAppkey(),
					thirdMsgReq.getAppscret(), "default");
			if(client!=null){
				if (client.isOnline()) {
					client.close();
				}
				client.close();
			}
			
		} catch (Exception ex) {
			logger.error("appkey:" + thirdMsgReq.getAppkey() + "消息地址："
					+ thirdMsgReq.getTopicUrl() + "taobao close client报错了"
					+ ex.toString());
		}

	}
	
//测试实现
	public void test( ThirdMsgReq thirdMsgReq,Message message, MessageStatus status){
		logger.info("onMessage--------消息Topic"+message.getTopic());
		logger.info("onMessage--------消息Content"+message.getContent());
		try {

			TaobaoOrderReq entity=new TaobaoOrderReq();
			entity.setShopId(thirdMsgReq.getShopId());
			entity.setPlatType(thirdMsgReq.getPlatType());
			entity.setContent(message.getContent());
			entity.setId(message.getId());
			entity.setOutgoingTime(message.getOutgoingTime());
			entity.setPubAppKey(message.getPubAppKey());
			entity.setPubTime(message.getPubTime());
			entity.setTopic(message.getTopic());
			entity.setShopAuthId(thirdMsgReq.getShopAuthId());
			//消息推送
			 MessageUtil.send(JSON.toJSONString(entity), ThirdTopicUtil.THIRD_TOPIC_MESSAGE_NAME);
			
		} catch (Exception e) {
			logger.error("appkey:" + thirdMsgReq.getAppkey()
					+ "消息地址：" + thirdMsgReq.getTopicUrl()
					+ "taobao接收处理服务报错了" + e.toString());
		}
	
	}
}
