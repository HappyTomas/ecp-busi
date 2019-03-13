/** 
 * Project Name:ecp-common-intefaces 
 * File Name:IMcUserRSV.java 
 * Package Name:com.zengshi.ecp.general.sys.mc.interfaces 
 * Date:2016年3月5日下午5:08:33 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.general.sys.mc.interfaces;

import com.zengshi.ecp.general.sys.mc.dto.McUserInfo;
import com.zengshi.ecp.general.sys.mc.dto.McUserInfoReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-common-intefaces <br>
 * Description: <br>
 * Date:2016年3月5日下午5:08:33  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IMcUserRSV {
    
    /**
     * 
     * fetchUserInfoByUserId:
     *   根据用户Id，获取与用户发送消息相关的信息，包括：手机号码、email 等等； 
     * 
     * @author yugn 
     * @param userInfoReq  请求结果中，仅有一个 userId;
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    public McUserInfo fetchUserInfoByUserId(McUserInfoReqDTO userInfoReq) throws BusinessException;
    
    

}

