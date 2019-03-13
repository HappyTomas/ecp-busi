package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdBackTrack;
import com.zengshi.ecp.order.dubbo.dto.RBackTrackResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;

public interface IOrdBackTrackSV {
    public void saveOrdBackTrack(OrdBackTrack ordBackTrack);
    
    public List<RBackTrackResp> queryOrdBackTrack(ROrderBackReq rOrderBackReq);
}

