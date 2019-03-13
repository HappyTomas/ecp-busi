/** 
 * Project Name:ecp-services-sys 
 * File Name:IMsgSendOperateSV.java 
 * Package Name:com.zengshi.ecp.sys.service.busi.interfaces 
 * Date:2016年3月15日上午10:52:35 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.busi.interfaces;

import java.util.Map;

import com.zengshi.ecp.general.sys.mc.dto.McUserInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dao.model.MsgSendDetail;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: 具体的消息发送动作<br>
 * Date:2016年3月15日上午10:52:35  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IMsgSendOperateSV {
    
    /**
     * 
     * sendMsgOperate: <br/> 
     * 
     * @author yugn 
     * @param msgDetail 消息信息；
     * @param attrMap 消息参数；
     * @param userInfo  用户信息；
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void sendMsgOperate(MsgSendDetail msgDetail, Map<String,String> attrMap, McUserInfo userInfo) throws BusinessException;

}

