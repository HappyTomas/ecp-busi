package com.zengshi.ecp.search.dubbo.interfaces;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface ISecDeltaIndexRSV {
    
    void deltaIndex(DeltaMessage deltaMessage) throws BusinessException;

}

