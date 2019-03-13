package com.zengshi.ecp.search.dubbo.impl;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.search.dubbo.interfaces.ISecDeltaIndexRSV;
import com.zengshi.ecp.search.index.delta.DeltaIndexManager;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 线程队列增量方式实现
 * Title: ECP <br>
 * Project Name:ecp-services-search-server <br>
 * Description: <br>
 * Date:2016年4月1日上午11:03:19  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecDeltaIndexRSVImpl implements ISecDeltaIndexRSV {

    @Override
    public void deltaIndex(DeltaMessage deltaMessage) throws BusinessException{
        
        DeltaIndexManager.submit(deltaMessage);
        
    }

}

