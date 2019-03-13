package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdBackPic;
import com.zengshi.ecp.order.dubbo.dto.RBackPicResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;

public interface IOrdBackPicSV {
    public void saveOrdBackPic(OrdBackPic ordBackPic);
    
    public List<RBackPicResp> queryOrdBackPic(ROrderBackReq rOrderBackReq);
}

