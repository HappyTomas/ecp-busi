package com.zengshi.ecp.order.dubbo.impl.orderSubmit;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.ROrdSubmitProReq;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderSubmitProRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;

public class OrderSubmitPromRSVImpl implements IOrderSubmitProRSV {
    
    @Resource
    private IPromRSV promRSV;

    @Override
    public void execute(ROrdSubmitProReq rOrdSubmitProReq) {
        if(rOrdSubmitProReq.isProm()){
            this.promRSV.promOrdSave(rOrdSubmitProReq.getrOrdCartsCommRequest());
        }
    }

    @Override
    public void rollback(ROrdSubmitProReq rOrdSubmitProReq) {
        if(rOrdSubmitProReq.isProm()){
            this.promRSV.promOrdSaveRollBack(rOrdSubmitProReq.getrOrdCartsCommRequest());
        }
    }

}

