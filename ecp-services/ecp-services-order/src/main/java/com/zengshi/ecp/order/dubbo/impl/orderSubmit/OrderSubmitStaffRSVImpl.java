package com.zengshi.ecp.order.dubbo.impl.orderSubmit;

import java.util.Collection;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.ROrdSubmitProReq;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderSubmitProRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class OrderSubmitStaffRSVImpl implements IOrderSubmitProRSV {
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;

    @Override
    public void execute(ROrdSubmitProReq rOrdSubmitProReq) {
        if(CollectionUtils.isNotEmpty(rOrdSubmitProReq.getScoreExchangeReqDTOs().getList()) 
                || CollectionUtils.isNotEmpty(rOrdSubmitProReq.getTransactionContentReqDTOs().getList()) ){
            this.staffUnionRSV.saveStaffRelInfoForOrder(rOrdSubmitProReq.getScoreExchangeReqDTOs(),rOrdSubmitProReq.getTransactionContentReqDTOs());
        }
    }

    @Override
    public void rollback(ROrdSubmitProReq rOrdSubmitProReq) {
        this.staffUnionRSV.saveStaffRelInfoForOrderRollBack(rOrdSubmitProReq.getScoreExchangeReqDTOs(), rOrdSubmitProReq.getTransactionContentReqDTOs());
    }

}

