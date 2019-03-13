/** 
 * Project Name:ecp-services-sys 
 * File Name:MsgSendAspect.java 
 * Package Name:com.zengshi.ecp.sys.aspect 
 * Date:2016年3月15日下午2:46:55 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.aspect;

import org.aspectj.lang.JoinPoint;

import com.zengshi.ecp.sys.dao.model.MsgSendDetail;
import com.zengshi.ecp.sys.service.busi.interfaces.IMsgDetailInfoSV;
import com.zengshi.paas.utils.LogUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月15日下午2:46:55  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class MsgSendAspect {
    
    private static final String MODULE = MsgSendAspect.class.getName();
    
    private IMsgDetailInfoSV msgDetailInfoSV;
    
    
    public void setMsgDetailInfoSV(IMsgDetailInfoSV msgDetailInfoSV) {
        this.msgDetailInfoSV = msgDetailInfoSV;
    }

    /**
     * 
     * doWhenSendException: 发送失败之后<br/> 
     * 
     * @author yugn 
     * @param joinPoint
     * @param ex 
     * @since JDK 1.6
     */
    public void doWhenSendException(JoinPoint joinPoint, Exception ex){
        Object[] args = joinPoint.getArgs();
        LogUtil.info(MODULE, "----------> 调用消息发送失败",ex);
        
        MsgSendDetail detail = (MsgSendDetail)args[0];

        detail.setSendTag(ex.getMessage());
        msgDetailInfoSV.updateMsgDetailErrorByDetail(detail);
        
    }
    
    /**
     * 
     * doWhenSendSuccess: 在处理成功之后<br/> 
     * 
     * @author yugn 
     * @param joinPoint
     * @param result 
     * @since JDK 1.6
     */
    public void doWhenSendSuccess(JoinPoint joinPoint, Object result){
        Object[] args = joinPoint.getArgs();
        
        MsgSendDetail detail = (MsgSendDetail)args[0];
        //设置状态为成功
        msgDetailInfoSV.updateMsgDetailSuccessByDetail(detail);
        LogUtil.info(MODULE, "----------> 调用消息发送成功");
        
    }

}

