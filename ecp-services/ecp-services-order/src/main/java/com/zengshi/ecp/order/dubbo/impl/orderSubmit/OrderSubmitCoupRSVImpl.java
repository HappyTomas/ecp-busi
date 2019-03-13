package com.zengshi.ecp.order.dubbo.impl.orderSubmit;

import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.order.dubbo.dto.ROrdSubmitProReq;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderSubmitProRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;

import javax.annotation.Resource;

public class OrderSubmitCoupRSVImpl implements IOrderSubmitProRSV {
    
    @Resource
    private ICoupDetailRSV coupDetailRSV;

    @Override
    public void execute(ROrdSubmitProReq rOrdSubmitProReq) {
        if(rOrdSubmitProReq.isCoup()){
            this.coupDetailRSV.countCoupOrdSku(rOrdSubmitProReq.getrOrdCartsCommRequest());
        }
    }

    @Override
    public void rollback(ROrdSubmitProReq rOrdSubmitProReq) {
        if(rOrdSubmitProReq.isProm()){
            this.coupDetailRSV.deleteOrdCoup(rOrdSubmitProReq.getrOrdCartsCommRequest());
        }
    }

}

