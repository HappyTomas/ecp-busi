/** 
 * Project Name:ecp-services-sys 
 * File Name:IMsgSendSV.java 
 * Package Name:com.zengshi.ecp.sys.service.busi.interfaces 
 * Date:2016年3月15日上午11:42:51 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.busi.interfaces;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.general.sys.mc.dto.McUserInfo;
import com.zengshi.ecp.sys.dao.model.MsgSendDetail;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月15日上午11:42:51  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IMsgSendSV {
    
    public void sendMsg(MsgSendDetail msgDetail, Map<String,String> attrMap, McUserInfo userInfo);
    

}

