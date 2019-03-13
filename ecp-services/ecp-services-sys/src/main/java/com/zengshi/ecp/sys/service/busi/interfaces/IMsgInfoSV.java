/** 
 * Project Name:ecp-services-sys 
 * File Name:IMsgInfoSV.java 
 * Package Name:com.zengshi.ecp.sys.service.busi.interfaces 
 * Date:2016年3月11日下午5:15:18 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.general.sys.mc.dto.McParamsDTO;
import com.zengshi.ecp.sys.dao.model.MsgInfo;
import com.zengshi.ecp.sys.dao.model.MsgSend;


/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月11日下午5:15:18  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IMsgInfoSV {
    
    /**
     * 
     * initInsertMsgInfo: 初始化待发送消息写入 <br/> 
     * 
     * @author yugn 
     * @param paramsDTO
     * @throws Exception 
     * @since JDK 1.6
     */
    public MsgInfo initInsertMsgInfo(McParamsDTO paramsDTO) throws Exception;
    
    /**
     * 
     * updateMsgInfo2DoingById: 将状态修改为处理中 <br/> 
     * 
     * @author yugn 
     * @param msgInfoId
     * @return
     * @since JDK 1.6
     */
    public int updateMsgInfo2DoneById(long msgInfoId);
    
    
    
    /**
     * 
     * updateMsgInfo2UnDoById: 根据消息ID，将该条待处理消息状态修改为 不需要处理（未激活）<br/> 
     * 
     * @author yugn 
     * @param msgInfoId
     * @return
     * @since JDK 1.6
     */
    public int updateMsgInfo2UnDoById(long msgInfoId);
    
    /**
     * 
     * fetchMsgInfoById:根据消息ID，获取信息 <br/> 
     * 
     * @author yugn 
     * @param msgInfoId
     * @return
     * @since JDK 1.6
     */
    public MsgInfo fetchMsgInfoById(long msgInfoId);
}

