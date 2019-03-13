/** 
 * Project Name:ecp-aip-services 
 * File Name:IAipDemoRSV.java 
 * Package Name:com.zengshi.ecp.aip.dubbo.interfaces 
 * Date:2015-10-21下午5:56:47 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.aip.dubbo.interfaces;

import com.zengshi.ecp.aip.dubbo.dto.AipDemoRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-aip-services <br>
 * Description: <br>
 * Date:2015-10-21下午5:56:47  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IAipDemoRSV  {
    
    public String demo(AipDemoRequest info) throws BusinessException;

}

