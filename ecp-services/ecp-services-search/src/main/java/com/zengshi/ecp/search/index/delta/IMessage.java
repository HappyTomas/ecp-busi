package com.zengshi.ecp.search.index.delta;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.search.index.IndexException;

public interface IMessage<T extends DeltaMessage> {
    
    T getDeltaMessage() throws IndexException;

}

