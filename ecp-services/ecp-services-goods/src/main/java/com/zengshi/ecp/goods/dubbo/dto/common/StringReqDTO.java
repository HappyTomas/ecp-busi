/** 
 * Project Name:ecp-services-goods 
 * File Name:StringReqDTO.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.dto.common 
 * Date:2015年8月28日上午9:54:56 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.dto.common;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * 单个字符串DTO请求 <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月28日上午9:54:56  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class StringReqDTO extends BaseInfo {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 6234664240686258808L;

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
}

