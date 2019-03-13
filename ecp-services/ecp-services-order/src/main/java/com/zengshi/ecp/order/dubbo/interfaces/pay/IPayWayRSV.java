package com.zengshi.ecp.order.dubbo.interfaces.pay;


import java.util.List;

import com.zengshi.ecp.order.dubbo.dto.pay.PayWayRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayResponse;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付通道配置类<br>
 * Date:2015年10月8日上午11:21:41  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public interface IPayWayRSV {
   
    public List<PayWayResponse> getPayWay(PayWayRequest payWay) throws BusinessException;
    
    public PayWayResponse queryOnePayWay(PayWayRequest payWayRequest) throws BusinessException;
    
    public PageResponseDTO<PayWayResponse> queryPayWayPage(PayWayRequest payWayRequest) throws BusinessException;
    
    public void addPayWay(PayWayRequest payWayRequest) throws BusinessException;
    
    public void updatePayWay(PayWayRequest payWayRequest) throws BusinessException;
}

