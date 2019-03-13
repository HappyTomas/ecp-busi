package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdBackGift;
import com.zengshi.ecp.order.dubbo.dto.RBackGiftResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;

public interface IOrdBackGiftSV {
    public void saveOrdBackGift(OrdBackGift ordBackGift);
    
    public List<RBackGiftResp> queryOrdBackGift(ROrderBackReq rOrderBackReq);
}

