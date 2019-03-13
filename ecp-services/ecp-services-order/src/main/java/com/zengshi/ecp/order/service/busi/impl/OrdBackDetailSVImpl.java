package com.zengshi.ecp.order.service.busi.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdBackDetailMapper;
import com.zengshi.ecp.order.dao.model.OrdBackDetail;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackDetailSV;
import com.db.sequence.Sequence;

public class OrdBackDetailSVImpl implements IOrdBackDetailSV {
    
    @Resource
    private OrdBackDetailMapper ordBackDetailMapper;
    
    @Resource(name = "seq_ord_back_detail")
    private Sequence seqOrdBackDetail;

    private static final String MODULE = OrdBackDetailSVImpl.class.getName();

    @Override
    public void saveOrdBackDetail(OrdBackDetail ordBackDetail) {
    }

}

