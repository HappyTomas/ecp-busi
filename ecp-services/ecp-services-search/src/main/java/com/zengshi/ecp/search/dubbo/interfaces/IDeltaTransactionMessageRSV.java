package com.zengshi.ecp.search.dubbo.interfaces;

import com.zengshi.ecp.general.solr.message.DeltaMessage;

public interface IDeltaTransactionMessageRSV {
    public void sendDeltaMessage(DeltaMessage deltaMessage);
}

