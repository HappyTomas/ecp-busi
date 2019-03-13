package com.zengshi.ecp.search.dubbo.impl;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.search.dubbo.interfaces.IDeltaTransactionMessageRSV;
import com.zengshi.ecp.search.facade.facade.interfaces.eventual.IDeltaTransactionMessageMainSV;

import javax.annotation.Resource;

public class DeltaTransactionMessageRSVImpl implements IDeltaTransactionMessageRSV {

    @Resource
    private IDeltaTransactionMessageMainSV deltaTransactionMessageMainSV;
    
    @Override
    public void sendDeltaMessage(DeltaMessage deltaMessage){
        
        this.deltaTransactionMessageMainSV.sendDeltaMessage(deltaMessage);
    }

}

