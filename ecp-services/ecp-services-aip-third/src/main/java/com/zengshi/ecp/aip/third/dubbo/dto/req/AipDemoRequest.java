/** 
 * Project Name:ecp-aip-services 
 * File Name:AipDemoRequest.java 
 * Package Name:com.zengshi.ecp.aip.dubbo.dto 
 * Date:2015-10-21下午5:58:27 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.aip.third.dubbo.dto.req;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: ECP <br>
 * Project Name:ecp-aip-services <br>
 * Description: <br>
 * Date:2015-10-21下午5:58:27  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class AipDemoRequest extends BaseInfo {
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1548264676335882048L;
    
    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

}

