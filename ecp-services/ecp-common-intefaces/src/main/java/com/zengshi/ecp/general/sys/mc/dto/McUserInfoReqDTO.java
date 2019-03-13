/** 
 * Project Name:ecp-common-intefaces 
 * File Name:McParamsDTO.java 
 * Package Name:com.zengshi.ecp.general.sys.mc.dto 
 * Date:2016年3月5日下午4:54:22 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.general.sys.mc.dto;

import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: ECP <br>
 * Project Name:ecp-common-intefaces <br>
 * Description: 调用消息中心所使用的基础参数<br>
 * Date:2016年3月5日下午4:54:22  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class McUserInfoReqDTO extends BaseInfo{
    
    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "McUserInfoReqDTO [userId=" + userId + "]";
    }
    
}

