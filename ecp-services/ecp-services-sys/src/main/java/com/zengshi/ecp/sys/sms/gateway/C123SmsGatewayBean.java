/** 
 * Project Name:ecp-services-sys 
 * File Name:C123SmsSendDTO.java 
 * Package Name:com.zengshi.ecp.sys.sms.dto 
 * Date:2016年3月16日下午10:47:31 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.sms.gateway;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.sms.operator.SmsOperatorSendBean;
import com.zengshi.paas.utils.DateUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: C123的短信网关信息<br>
 * Date:2016年3月16日下午10:47:31  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class C123SmsGatewayBean extends SmsGatewayBean {
    
   /* ac  用户账号    发送短信的帐号（员工编号@企业编号）
    authkey 认证密钥    32位认证密钥（大写），企业管理员可以在软件或后台管理中获取
    cgid    通道组编号   使用的通道组编号，在软件或后台管理可以查看可用的通道组
    csid    签名编号    预设的企业签名编号，请使用自己企业已通过审核的签名编号
    c   短信内容    发送内容(总长度应在1000个汉字以内)要使用UTF-8格式，如果不是请先转为UTF-8字符，再进行URL字符标准化转码。
    {URL字符编码说明:返回字符串，此字符串中除了-_.之外的所有非字母数字字符都将被替换成百分号（%）后跟两位十六进制数，空格则编码为加号（+）}
    ASP：server.URLEncode("短信内容")
    PHP：urlencode("短信内容")
    JAVA：java.net.URLEncoder.encode("短信内容")
    m   发送号码    手机号码，如多个以英文逗号分隔（建议1000个及以内的手机号码，,超过系统上限将会返回参数错误）。 如: 15102110086,151****0086
    t   发送时间    可选参数，无则立即发送，有则须在当前时间的一分钟之后，否则返回参数错误。格式:yyyyMMddHHmmss 如:20130721182038*/
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 5715385706587167581L;
    
    //通道组编号
    private String cgid;
    
    //签名编号
    private String csid;


    public String getCgid() {
        return cgid;
    }

    public void setCgid(String cgid) {
        this.cgid = cgid;
    }

    public String getCsid() {
        return csid;
    }

    public void setCsid(String csid) {
        this.csid = csid;
    }
    
    
    private static final String ACTION_STR = "action=sendOnce";
    
    public static String buildSendOpearteQueryString(SmsOperatorSendBean<C123SmsGatewayBean> send) throws BusinessException{
        StringBuffer sbuf = new StringBuffer(ACTION_STR);
        sbuf.append("&ac=").append(send.getGateWay().getAccount());
        sbuf.append("&authkey=").append(send.getGateWay().getAuthKey());
        sbuf.append("&cgid=").append(send.getGateWay().getCgid());
        sbuf.append("&csid=").append(send.getGateWay().getCgid());
        
        sbuf.append("&m=").append(send.getPhoneNo());
        
        try {
            sbuf.append("&c=").append(URLEncoder.encode(send.getContext(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException("SYS.SMS.C123.ERR");
        }
        
        if(send.getSendTime() == null || send.getSendTime().before(DateUtil.getSysDate())){
            
        } else {
            sbuf.append("&t=").append(DateFormatUtils.format(send.getSendTime(), "yyyyMMddHHmmss"));
        }
        
        return sbuf.toString();
    }
    
    
    

}

