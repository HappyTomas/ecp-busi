/** 
 * Project Name:ecp-services-sys 
 * File Name:IMsgDetailInfoSV.java 
 * Package Name:com.zengshi.ecp.sys.service.busi.interfaces 
 * Date:2016年3月14日下午8:55:21 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.sys.dao.model.MsgInfo;
import com.zengshi.ecp.sys.dao.model.MsgSend;
import com.zengshi.ecp.sys.dao.model.MsgSendDetail;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月14日下午8:55:21  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IMsgDetailInfoSV {
    
    /**
     * 
     * saveMsgDetailByInfo:根据待发送消息和消息发送方式信息，生成发送消息明细记录； <br/>  
     * 
     * @author yugn 
     * @param msgSends
     * @param msgInfo
     * @return 
     * @since JDK 1.6
     */
    public List<MsgSendDetail> saveMsgDetailByInfo(List<MsgSend> msgSends, MsgInfo msgInfo);
    
    /**
     * 
     * updateMsgDetailSuccessById: 根据具体的明细消息ID，更新发送成功状态<br/> 
     * 
     * @author yugn 
     * @param msgDetailInfoId
     * @return 
     * @since JDK 1.6
     */
    //public int updateMsgDetailSuccessById(Long msgDetailInfoId);
    
    /**
     * 
     * updateMsgDetailSuccessByDetail: 根据消息明细信息，更新对应的消息的发送状态<br/> 
     * 
     * @author yugn 
     * @param msgDetailInfo
     * @return 
     * @since JDK 1.6
     */
    public int updateMsgDetailSuccessByDetail(MsgSendDetail msgDetailInfo);
    
    /**
     * 
     * updateMsgDetailErrorByDetail:设置接收的异常信息<br/> 
     * 
     * @author yugn 
     * @param msgDetailInfo
     * @return 
     * @since JDK 1.6
     */
    public int updateMsgDetailErrorByDetail(MsgSendDetail msgDetailInfo);
}

