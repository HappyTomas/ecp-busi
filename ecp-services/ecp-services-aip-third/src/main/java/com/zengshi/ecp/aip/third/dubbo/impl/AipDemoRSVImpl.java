/** 
 * Project Name:ecp-aip-services 
 * File Name:AipDemoRSVImpl.java 
 * Package Name:com.zengshi.ecp.aip.dubbo.impl 
 * Date:2015-10-21下午5:57:58 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.aip.third.dubbo.impl;

import com.zengshi.ecp.aip.third.dubbo.dto.req.AipDemoRequest;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IAipDemoRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-aip-services <br>
 * Description: <br>
 * Date:2015-10-21下午5:57:58  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class AipDemoRSVImpl implements IAipDemoRSV {

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.aip.third.dubbo.interfaces.IAipDemoRSV#demo(com.zengshi.ecp.server.front.dto.BaseInfo) 
     */
    @Override
    public String demo(AipDemoRequest info) throws BusinessException {
        
        
        return "Hello : "+ info.getName() + " By Dubbo Services";
    }

}

