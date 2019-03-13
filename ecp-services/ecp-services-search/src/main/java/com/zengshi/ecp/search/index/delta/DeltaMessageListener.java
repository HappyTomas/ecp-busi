package com.zengshi.ecp.search.index.delta;

import com.zengshi.ecp.general.solr.util.DeltaUtils;
import com.zengshi.paas.message.Message;
import com.zengshi.paas.message.MessageListener;
import com.zengshi.paas.message.MessageStatus;
import com.zengshi.paas.utils.LogUtil;

/**
 * MQ增量方式实现
 * Title: ECP <br>
 * Project Name:ecp-services-search-server <br>
 * Description: <br>
 * Date:2016年4月1日上午11:02:58  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class DeltaMessageListener implements MessageListener {

    public void deltaImportIndex(String message){
        //消费代码里启用多线程异步处理，接收完消息，立即启用线程池异步去处理。导致无法保证消息的可靠处理，遇到刚好系统重启时会出现消息漏掉处理的问题。
        //先不改，后期直接切换到分布式事务保证。
        DeltaIndexManager.submit(message);
    }

    @Override
    public void receiveMessage(Message message, MessageStatus status) {

        if (DeltaUtils.TOPIC_DELTAINDEX_NAME.equals(message.getTopic())) {

            // 接收到增量消息
            LogUtil.info(DeltaIndexManager.MODULE, "【搜索引擎】接收到增量消息：" + (String) message.getMsg());

            this.deltaImportIndex((String) message.getMsg());
        }

    }

}
