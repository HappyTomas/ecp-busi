package com.zengshi.ecp.search.index.delta;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.index.IndexException;

public abstract class AbstractObjectInspector<T extends DeltaMessage> extends MessageInitializer<T>
        implements IObjectInspector<T> {

    protected final static String MODULE = "增量扩展检查器接口";

    private SecObjectRespDTO secObjectRespDTO;

    public SecObjectRespDTO getSecObjectRespDTO() {
        return this.secObjectRespDTO;
    }

    public AbstractObjectInspector(SecObjectRespDTO secObjectRespDTO, String message,Class<T> clazz)
            throws IndexException {
        super(message, clazz);
        this.secObjectRespDTO = secObjectRespDTO;
    }
    
    public AbstractObjectInspector(SecObjectRespDTO secObjectRespDTO, T t)
            throws IndexException {
        super(t);
        this.secObjectRespDTO = secObjectRespDTO;
    }

}
