/** 
 * Project Name:ecp-services-sys 
 * File Name:SmsGatewayBean.java 
 * Package Name:com.zengshi.ecp.sys.sms.dto 
 * Date:2016年3月17日上午10:01:00 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.sms.gateway;

import java.io.Serializable;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: 短信网关的基本信息Bean <br>
 * Date:2016年3月17日上午10:01:00  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public abstract class SmsGatewayBean implements Serializable{
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -2484615545561443550L;
    
    private String gateway;

    private String account;
    
    private String authKey;
    
    private String url;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }
    
}

