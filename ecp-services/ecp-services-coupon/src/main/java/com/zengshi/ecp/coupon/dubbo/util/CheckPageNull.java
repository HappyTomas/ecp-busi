package com.zengshi.ecp.coupon.dubbo.util;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-26 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CheckPageNull {
 
    /**
     * 验证page 是否为空
     * @param page
     * @return
     * @author huangjx
     */
    public static boolean checkPageNull(PageResponseDTO<?> page) {
        if(page!=null){
             if(!CollectionUtils.isEmpty(page.getResult())){
                 return Boolean.TRUE;
             }
        }
        return Boolean.FALSE;

    }

}
