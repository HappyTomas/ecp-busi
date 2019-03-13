/** 
 * Project Name:ecp-common-intefaces 
 * File Name:McParamsDTO.java 
 * Package Name:com.zengshi.ecp.general.sys.mc.dto 
 * Date:2016年3月5日下午4:54:22 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.general.sys.mc.dto;

import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: ECP <br>
 * Project Name:ecp-common-intefaces <br>
 * Description: 调用消息中心所使用的基础参数<br>
 * Date:2016年3月5日下午4:54:22  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class McParamsDTO extends BaseInfo {
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 6605314465055416329L;

    //消息的发起用户，可以为空，表示是系统消息；
    private Long fromUserId;
    
    //消息的接收用户Id，非空；
    private Long toUserId;
    
    //消息编码；消息模板编码；
    private String msgCode;
    
    //消息参数；
    private Map<String,String> msgParams;

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public Map<String, String> getMsgParams() {
        return msgParams;
    }

    public void setMsgParams(Map<String, String> msgParams) {
        this.msgParams = msgParams;
    }
    
    

}

