package com.zengshi.ecp.search.facade.impl.eventual;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.search.facade.facade.interfaces.eventual.IDeltaTransactionMessageMainSV;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionCallback;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;
import com.distribute.tx.eventual.TransactionManager;

import javax.annotation.Resource;

public class DeltaTransactionMessageMainSVImpl implements IDeltaTransactionMessageMainSV {

    @Resource(name="transactionManagerDeltaIndex")
    private TransactionManager transactionManager;
    
    @Override
    public void sendDeltaMessage(DeltaMessage deltaMessage){
        
        final TransactionContext txMsg = new TransactionContext();
        txMsg.setContent(JSON.toJSONString(deltaMessage));
        txMsg.setName("topic-search-deltaIndex");
        
        transactionManager.startTransaction(txMsg, new TransactionCallback(){
            
            @Override
            public Object doInTransaction(TransactionStatus status) {

                //主事务啥也不用做，序列化消息发送在上面
                return null;
            }});
    }

}

