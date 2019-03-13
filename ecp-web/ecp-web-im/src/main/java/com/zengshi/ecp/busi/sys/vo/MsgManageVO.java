/** 
 * Project Name:ecp-web-manage 
 * File Name:ExpressVO.java 
 * Package Name:com.zengshi.ecp.busi.sys.vo 
 * Date:2015-9-3下午9:13:59 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.sys.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015-9-3下午9:13:59  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class MsgManageVO implements Serializable {
    
    /** 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -2273821288879446062L;
    
    private String flag;//发送方式开发标识 
    
    private String msgCode;//模板编码
    
    private String sendType;//发送方式：站内短信、手机短信、邮件

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }
    
}

