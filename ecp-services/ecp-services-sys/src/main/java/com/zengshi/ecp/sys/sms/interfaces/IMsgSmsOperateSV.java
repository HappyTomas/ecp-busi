/** 
 * Project Name:ecp-services-sys 
 * File Name:IMsgSmsSend.java 
 * Package Name:com.zengshi.ecp.sys.sms.interfaces 
 * Date:2016年3月16日下午2:34:41 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.sms.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.sms.gateway.SmsGatewayBean;
import com.zengshi.ecp.sys.sms.operator.SmsOperatorSendBean;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月16日下午2:34:41  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IMsgSmsOperateSV<T extends SmsGatewayBean> {
    
    
    /**
     * 
     * sendSms: 具体的消息发送<br/> 
     * 
     * @author yugn 
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void sendSms(SmsOperatorSendBean<T> sendBean) throws BusinessException;
    
}

