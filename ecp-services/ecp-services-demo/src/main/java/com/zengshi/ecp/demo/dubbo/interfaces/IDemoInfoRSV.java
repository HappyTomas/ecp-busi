/** 
 * Project Name:ecp-services-demo 
 * File Name:IDemoInfoRSV.java 
 * Package Name:com.zengshi.ecp.demo.dubbo.interfaces 
 * Date:2015-8-3下午3:57:16 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.demo.dubbo.interfaces;

import com.zengshi.ecp.demo.dubbo.dto.DemoInfoDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-8-3下午3:57:16  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IDemoInfoRSV {
    
    public void saveInfo(DemoInfoDTO info) throws BusinessException;
    
    public DemoInfoDTO fetchDemoInfoById(DemoInfoDTO info) throws BusinessException;
}

