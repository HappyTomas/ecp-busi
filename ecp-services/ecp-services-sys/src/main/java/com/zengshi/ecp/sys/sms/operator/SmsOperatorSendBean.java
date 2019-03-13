/** 
 * Project Name:ecp-services-sys 
 * File Name:SmsSendDTO.java 
 * Package Name:com.zengshi.ecp.sys.sms.dto 
 * Date:2016年3月16日下午9:34:49 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.sms.operator;

import java.io.Serializable;
import java.util.Date;

import com.zengshi.ecp.sys.sms.gateway.SmsGatewayBean;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月16日下午9:34:49  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @param <T>
 * @since JDK 1.6 
 */
public class SmsOperatorSendBean<T extends SmsGatewayBean> extends SmsOperatorBean<T> implements Serializable{
    

    public SmsOperatorSendBean(T gateWay) {
        super(gateWay);
    }

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -7115407775016558747L;

    private String phoneNo;
    
    private String context;
    
    private Date sendTime;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "SmsSendDTO [phoneNo=" + phoneNo + ", context=" + context + ", sendTime=" + sendTime
                + "]";
    }
    

}

