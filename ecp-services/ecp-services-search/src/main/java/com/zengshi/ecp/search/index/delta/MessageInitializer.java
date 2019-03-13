package com.zengshi.ecp.search.index.delta;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.search.dubbo.search.util.SearchUtils;
import com.zengshi.ecp.search.index.IndexException;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;

public abstract class MessageInitializer<T extends DeltaMessage> {

    private String message;

    private T deltaMessage;

    private Class<T> clazz;

    // 泛型擦除，通过参数化class方法取得，这是java里最正统的方法。
    public MessageInitializer(String message, Class<T> clazz) {
        this.message = message;
        this.clazz = clazz;
    }
    
    public MessageInitializer(T deltaMessage) {
        this.deltaMessage = deltaMessage;
    }

    public T getDeltaMessage() throws IndexException {
        if (deltaMessage == null) {
            try {
                this.deltaMessage = (T) JSON.parseObject(message, clazz);
            } catch (Exception e) {
                LogUtil.error(DeltaIndexManager.MODULE, "增量JSON消息转消息JAVA对象异常", e);
                throw new IndexException(SearchUtils.getExceptionMessage(e));
            }
        }
        return deltaMessage;
    }

}
