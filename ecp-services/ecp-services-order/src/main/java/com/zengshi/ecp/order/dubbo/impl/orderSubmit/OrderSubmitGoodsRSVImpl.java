package com.zengshi.ecp.order.dubbo.impl.orderSubmit;

import javax.annotation.Resource;

import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.order.dubbo.dto.ROrdSubmitProReq;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderSubmitProRSV;

public class OrderSubmitGoodsRSVImpl implements IOrderSubmitProRSV {
    
    @Resource
    private IGdsInfoExternalRSV gdsInfoExternalRSV;

    @Override
    public void execute(ROrdSubmitProReq rOrdSubmitProReq) {
        this.gdsInfoExternalRSV.BatchAddStockPreOccupy(rOrdSubmitProReq.getrOrdCartsCommRequest());
    }

    @Override
    public void rollback(ROrdSubmitProReq rOrdSubmitProReq) {
      //库存补尝
        this.gdsInfoExternalRSV.batchCancleStockPreOccupy(rOrdSubmitProReq.getrOrdCartsCommRequest());
    }

}

