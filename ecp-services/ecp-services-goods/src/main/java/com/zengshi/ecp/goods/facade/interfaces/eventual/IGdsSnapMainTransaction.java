package com.zengshi.ecp.goods.facade.interfaces.eventual;

import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;

public interface IGdsSnapMainTransaction {

    
    public void executeSkuOnShelves(final GdsSkuInfoReqDTO gdsSkuInfoReqDTO );
}

