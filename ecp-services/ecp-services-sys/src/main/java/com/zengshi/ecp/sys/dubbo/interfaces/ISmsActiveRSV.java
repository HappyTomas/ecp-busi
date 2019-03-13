/** 
 * Project Name:ecp-services-sys 
 * File Name:ISmsActiveRSV.java 
 * Package Name:com.zengshi.ecp.sys.dubbo.interfaces 
 * Date:2016年3月19日下午5:55:14 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.dubbo.interfaces;

import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.BaseSmsCfgReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.SmsActiveDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: 短信相关的一些操作方式<br>
 * Date:2016年3月19日下午5:55:14  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface ISmsActiveRSV {
    
    /**
     * 
     * sendSmsVerifyMsg: 发送验证短信<br/> 
     * 
     * @author yugn 
     * @param activeDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void sendSmsVerifyMsg (SmsActiveDTO activeDto) throws BusinessException;
    
    /**
     * 
     * saveSmsCfg: 保存配置信息 <br/> 
     * 
     * @author yugn 
     * @param activeDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void saveSmsCfg(SmsActiveDTO activeDto) throws BusinessException;
    
    
    /**
     * 
     * fetchSmsCfg: 获取已有的配置信息 <br/> 
     * 
     * @author yugn 
     * @param info
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public BaseSmsCfgReqDTO fetchSmsCfg(BaseInfo info) throws BusinessException;

}

