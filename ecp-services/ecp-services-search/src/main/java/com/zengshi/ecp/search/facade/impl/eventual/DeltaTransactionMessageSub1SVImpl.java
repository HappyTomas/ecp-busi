package com.zengshi.ecp.search.facade.impl.eventual;

import com.zengshi.ecp.search.facade.facade.interfaces.eventual.IDeltaTransactionMessageSub1SV;
import com.zengshi.ecp.search.index.delta.DeltaIndexManager;
import com.distribute.tx.common.TransactionStatus;
import net.sf.json.JSONObject;

public class DeltaTransactionMessageSub1SVImpl implements IDeltaTransactionMessageSub1SV {
    
    @Override
    public void joinTransaction(JSONObject message, TransactionStatus status, String transactionName) {

        //使用线程异步方式处理索引增量
        DeltaIndexManager.submit(message.toString());
        
    }

}

