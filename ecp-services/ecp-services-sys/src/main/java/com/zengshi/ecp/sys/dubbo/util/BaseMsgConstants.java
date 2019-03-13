/** 
 * Project Name:ecp-services-sys 
 * File Name:EcpSysCodeConstants.java 
 * Package Name:com.zengshi.ecp.sys.dubbo.util 
 * Date:2015-8-18 14:07:04 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.dubbo.util;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-18 4:07:04  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class BaseMsgConstants {
    
    /**
     * 发送方式：站内短信
     */
    public static final String SEND_TYPE_INSITE = "10";
    
    /**
     * 发送方式：手机短信
     */
    public static final String SEND_TYPE_SMS = "20";
    
    /**
     * 邮件
     */
    public static final String SEND_TYPE_EMAIL = "30";
    
    
    /**
     * 站内消息读取状态；
     */
    // 00 :未读
    public static final String SYS_MSGINSITE_UNREAD = "00";
    //10 :已读
    public static final String SYS_MSGINSITE_READ = "10";
    
    /**
     * 管理员帐号
     */
    public static final long SYS_ADMIN_STAFF_ID = 1000 ;
    
    /**
     * 发送方式的启动标识
     */
    //启用
    public static final String SYS_MSG_SEND_ACTIVED = "1";
    
    /**
     * 待发送消息的处理标识；
     */
    //待处理；
    public static final String SYS_MSG_SEND_INFO_STATUS_WAIT = "00";
    //处理中
    public static final String SYS_MSG_SEND_INFO_STATUS_DONE = "10";
    //不需要处理（未启用）
    public static final String SYS_MSG_SEND_INFO_STATUS_UNDO = "21";
    
    /**
     * 明细消息的状态 t_msg_send_detail.send_status;
     */
    //初始状态
    public static final String SYS_MSG_SEND_DETAIL_STATUS_INIT = "00";
    //发送成功
    public static final String SYS_MSG_SEND_DETAIL_STATUS_SUCCESS = "10";
    //发送失败
    public static final String SYS_MSG_SEND_DETAIL_STATUS_ERROR = "20";
    
    
    public static final String SYS_MSG_SMS_CFG_KEY = "SMS_GATEWAY";
}

