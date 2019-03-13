package com.zengshi.ecp.general.solr.util;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.paas.utils.MessageUtil;
import com.alibaba.fastjson.JSON;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月14日下午3:45:40 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class DeltaUtils {

    public static final String TOPIC_DELTAINDEX_NAME = "deltaIndexTopic";

    /**
     * 各工程的paasContext.xml文件要添加messageSender,messageConsumer依赖注入
     * 
     * @param deltaMessage
     *            增量消息对象
     */
    @Deprecated
    public static void sendDeltaMessage(DeltaMessage deltaMessage) {

        if (deltaMessage == null) {
            return;
        }

        String message=JSON.toJSONString(deltaMessage);
        
        MessageUtil.send(message, TOPIC_DELTAINDEX_NAME);
    }

    public enum EDeltaType {

        DBOBJECT("1"),

        CUSTOM("2");

        private String type;

        public String getType() {
            return type;
        }

        EDeltaType(String type) {
            this.type = type;
        }

    }

    public enum EOperType {

        /**
         * 新增或更新
         */
        UPDATE("update"),

        /**
         * 删除
         */
        DELETE("delete"),

        /**
         * 重建
         */
        REFULL("refull"),

        /**
         * 分类
         */
        CATG("catg");

        // ADD("add");

        private String type;

        public String getType() {
            return type;
        }

        EOperType(String type) {
            this.type = type;
        }

    }

}
