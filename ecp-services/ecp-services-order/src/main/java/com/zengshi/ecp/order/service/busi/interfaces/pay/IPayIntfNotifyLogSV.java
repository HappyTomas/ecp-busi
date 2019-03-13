package com.zengshi.ecp.order.service.busi.interfaces.pay;

import com.zengshi.ecp.order.dubbo.dto.pay.PayIntfNotifyLogDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: <br>
 * Date:2015年10月8日下午2:58:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public interface IPayIntfNotifyLogSV {
    
    public void addPayNotifyLog(PayIntfNotifyLogDTO log);
}