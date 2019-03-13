package com.zengshi.ecp.search.index.delta;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;

public interface IObjectInspector<T extends DeltaMessage> extends IMessage<T>{
    
    boolean inspecte(SecObjectRespDTO secObjectRespDTO,T t);

}

