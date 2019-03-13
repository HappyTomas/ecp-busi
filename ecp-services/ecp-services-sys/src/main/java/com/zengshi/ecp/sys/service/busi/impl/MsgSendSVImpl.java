/** 
 * Project Name:ecp-services-sys 
 * File Name:MsgSendSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.service.busi.impl 
 * Date:2016年3月15日上午11:44:26 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.busi.impl;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.general.sys.mc.dto.McUserInfo;
import com.zengshi.ecp.sys.dao.model.MsgSendDetail;
import com.zengshi.ecp.sys.dubbo.util.BaseMsgConstants;
import com.zengshi.ecp.sys.service.busi.interfaces.IMsgSendOperateSV;
import com.zengshi.ecp.sys.service.busi.interfaces.IMsgSendSV;
import com.zengshi.paas.utils.LogUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月15日上午11:44:26  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class MsgSendSVImpl implements IMsgSendSV {
    
    private static final String MODULE  = MsgSendSVImpl.class.getName();
    
    private Map<String, IMsgSendOperateSV> sendOperateMap ;

    public void setSendOperateMap(Map<String, IMsgSendOperateSV> sendOperateMap) {
        this.sendOperateMap = sendOperateMap;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.sys.service.busi.interfaces.IMsgSendSV#sendMsg(com.zengshi.ecp.sys.dao.model.MsgSendDetail, java.util.Map, com.zengshi.ecp.general.sys.mc.dto.McUserInfo) 
     */
    @Override
    public void sendMsg(MsgSendDetail msgDetail, Map<String, String> attrMap, McUserInfo userInfo) {
        
        if(sendOperateMap == null || sendOperateMap.isEmpty()){
            ///直接结束，未指定消息发送的处理方法；
            LogUtil.error(MODULE, "========未设定消息发送处理方法；");
            return ;
        }
        
        String sendType = msgDetail.getSendType();
        
        IMsgSendOperateSV sendOperateSv = this.sendOperateMap.get(sendType);
        if(sendOperateSv == null){
            LogUtil.error(MODULE, "========未设定消息发送处理方法；");
            return ;
        }
        
        //调用具体的发送规则；
        sendOperateSv.sendMsgOperate(msgDetail, attrMap, userInfo);
        
    }

}

