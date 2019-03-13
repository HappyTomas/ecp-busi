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
public class MsgTemplateVO implements Serializable {
    
    /** 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -2273821288879446062L;
    
    private String msgCode;//模板编码
    
    private String siteFlag;
    
    private String smsFlag;
    
    private String mailFlag;
    
    private String msgSiteTemplate;//站内短信模板内容
    
    private String smsTemplate;//手机短信模板内容
    
    private String mailTitleTemplate;//邮件模板标题
    
    private String mailBodyTemplate;//邮件模板内容

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getSiteFlag() {
        return siteFlag;
    }

    public void setSiteFlag(String siteFlag) {
        this.siteFlag = siteFlag;
    }

    public String getSmsFlag() {
        return smsFlag;
    }

    public void setSmsFlag(String smsFlag) {
        this.smsFlag = smsFlag;
    }

    public String getMailFlag() {
        return mailFlag;
    }

    public void setMailFlag(String mailFlag) {
        this.mailFlag = mailFlag;
    }

    public String getMsgSiteTemplate() {
        return msgSiteTemplate;
    }

    public void setMsgSiteTemplate(String msgSiteTemplate) {
        this.msgSiteTemplate = msgSiteTemplate;
    }

    public String getSmsTemplate() {
        return smsTemplate;
    }

    public void setSmsTemplate(String smsTemplate) {
        this.smsTemplate = smsTemplate;
    }

    public String getMailTitleTemplate() {
        return mailTitleTemplate;
    }

    public void setMailTitleTemplate(String mailTitleTemplate) {
        this.mailTitleTemplate = mailTitleTemplate;
    }

    public String getMailBodyTemplate() {
        return mailBodyTemplate;
    }

    public void setMailBodyTemplate(String mailBodyTemplate) {
        this.mailBodyTemplate = mailBodyTemplate;
    }

}

