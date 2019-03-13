package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dubbo.dto.RConfirmDeliveRequest;

public interface IOrdDeliveryChkSV {
    /** 
     * chkDerliverBathcInput:确认发货入参逻辑较验. <br/> 
     * @author cbl 
     * @param rConfirmDeliveRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public void chkDeliverBathcInput(RConfirmDeliveRequest rConfirmDeliveRequest);
}

