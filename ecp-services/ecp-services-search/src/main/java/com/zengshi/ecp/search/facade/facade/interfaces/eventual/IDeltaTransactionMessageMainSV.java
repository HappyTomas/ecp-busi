package com.zengshi.ecp.search.facade.facade.interfaces.eventual;

import com.zengshi.ecp.general.solr.message.DeltaMessage;

public interface IDeltaTransactionMessageMainSV {
    public void sendDeltaMessage(DeltaMessage deltaMessage);
}

