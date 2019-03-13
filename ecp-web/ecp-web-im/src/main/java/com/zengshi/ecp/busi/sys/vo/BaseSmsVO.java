/** 
 * Project Name:ecp-web-manage 
 * File Name:BaseSmsVO.java 
 * Package Name:com.zengshi.ecp.busi.sys.vo 
 * Date:2016年3月19日下午3:00:08 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.sys.vo;

import java.io.Serializable;
import java.util.Map;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2016年3月19日下午3:00:08  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class BaseSmsVO implements Serializable {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -7243431844591456374L;
    
    //
    private String url;
    
    //网关
    private String gateway;
    
    //帐号
    private String account;
    
    //验证码
    private String authKey;
    
    ///测试，待接收的手机号码
    private String recPhone;
    
    private Map<String,String> otherParams;

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

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

    public Map<String, String> getOtherParams() {
        return otherParams;
    }

    public void setOtherParams(Map<String, String> otherParams) {
        this.otherParams = otherParams;
    }
    
    

    public String getRecPhone() {
        return recPhone;
    }

    public void setRecPhone(String recPhone) {
        this.recPhone = recPhone;
    }

    @Override
    public String toString() {
        return "BaseSmsVO [gateway=" + gateway + ", account=" + account + ", authKey=" + authKey
                + ", recPhone=" + recPhone + ", otherParams=" + otherParams + "]";
    }
    
    

}

