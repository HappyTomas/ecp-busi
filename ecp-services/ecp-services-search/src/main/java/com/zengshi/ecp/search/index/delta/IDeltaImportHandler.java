package com.zengshi.ecp.search.index.delta;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.search.index.IndexException;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IDeltaImportHandler<T extends DeltaMessage> extends IMessage<T>{
    
    String getDeltaType() throws IndexException;
    
    void deltaDbImport(T t) throws BusinessException, IndexException;
    
    @Deprecated
    void deltaCustomImport(T t) throws BusinessException, IndexException;

}

