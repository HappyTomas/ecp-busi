/** 
 * Project Name:ecp-services-sys 
 * File Name:SmsActiveDTO.java 
 * Package Name:com.zengshi.ecp.sys.dubbo.dto 
 * Date:2016年3月19日下午6:18:31 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.dubbo.dto;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月19日下午6:18:31  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class SmsActiveDTO extends BaseSmsCfgReqDTO {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -7743624206044972671L;
    
    
    private String recPhoneno;
    
    private String sendMsg;


    public String getRecPhoneno() {
        return recPhoneno;
    }


    public void setRecPhoneno(String recPhoneno) {
        this.recPhoneno = recPhoneno;
    }


    public String getSendMsg() {
        return sendMsg;
    }


    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg;
    }
    
    

}

