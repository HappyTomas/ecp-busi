/** 
 * Project Name:ecp-services-sys 
 * File Name:SmsOperateBean.java 
 * Package Name:com.zengshi.ecp.sys.sms.dto 
 * Date:2016年3月17日上午9:48:21 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.sms.operator;

import com.zengshi.ecp.sys.sms.gateway.SmsGatewayBean;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: 抽象类，表示与短信网关相关的操作对象的Bean<br>
 * Date:2016年3月17日上午9:48:21  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public abstract class SmsOperatorBean<T extends SmsGatewayBean> {
    
    private T gateWay;

    public T getGateWay() {
        return gateWay;
    }

    public SmsOperatorBean(T gateWay) {
        this.gateWay = gateWay;
    }
    
}

