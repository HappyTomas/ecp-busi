/** 
 * Project Name:ecp-services-demo 
 * File Name:ResourceTest.java 
 * Package Name:com.zengshi.ecp.test.client 
 * Date:2015-8-4下午4:22:12 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.test.client;

import java.util.Locale;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.resource.PaasReloadableResourceBundleMessageSource;
import com.zengshi.paas.utils.ResourceMsgUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-8-4下午4:22:12  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class ResourceTest extends EcpServicesTest{
    
    @Resource
    private  PaasReloadableResourceBundleMessageSource messageSource;
    
    @Test
    public void read(){
        System.out.println(messageSource.getMessage("test", null, Locale.CHINA));
        System.out.println(messageSource.getMessage("special.exception.other", null, Locale.CHINA));
    }

    /** 
     * 
     * @author yugn 
     * @param args 
     * @since JDK 1.6 
     */
    public static void main(String[] args) {
        
        System.out.println(ResourceMsgUtil.getMessage("special.exception.other", null));
        
        System.out.println(ResourceMsgUtil.getMessage("special.exception.other", null,Locale.CHINA));
        
        BusinessException err = new BusinessException("special.exception.other");
        
        System.out.println(err.getErrorMessage());
        
        System.out.println(ResourceMsgUtil.getMessage("test", null));
    }

}

