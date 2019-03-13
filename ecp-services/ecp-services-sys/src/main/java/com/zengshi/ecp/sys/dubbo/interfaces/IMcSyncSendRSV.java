/** 
 * Project Name:ecp-services-sys 
 * File Name:IMcSyncSendRSV.java 
 * Package Name:com.zengshi.ecp.sys.dubbo.interfaces 
 * Date:2016年3月11日下午3:39:08 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.dubbo.interfaces;

import com.zengshi.ecp.general.sys.mc.dto.McParamsDTO;
import com.zengshi.ecp.general.sys.mc.dto.McUserInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.McParamsWithOtherTypesDTO;


/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月11日下午3:39:08  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IMcSyncSendRSV {
    
    /**
     * 
     * sendMsg: 发送信息<br/> 
     * 
     * @author yugn 
     * @param msgParams
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void sendMsg(McParamsDTO msgParams) throws BusinessException;
    
    
    /**
     * 
     * sendMsgBySpecial: 给指定号码发送消息 <br/> 
     * 
     * @author yugn 
     * @param msgParams 带指定号码的入参；
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void sendMsgBySpecial(McParamsWithOtherTypesDTO msgParams) throws BusinessException;

}

