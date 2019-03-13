/** 
 * Project Name:ecp-web-manage
 * File Name:DemoController.java 
 * Package Name:com.zengshi.ecp.busi.demo.controller 
 * Date:2015-8-5下午2:51:38 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.prom.promchk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage<br>
 * Description: <br>
 * Date:2015-8-14下午2:51:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version  
 * @since JDK 1.7 
 */

@Controller
@RequestMapping(value="/promchk")
public class PromPromChkController extends EcpBaseController {
    /**
     * 促销审核(店铺提交普通促销(非主题促销)，需要平台人员审核 )
     */
    private static String MODULE = PromPromChkController.class.getName();
    
    /**
     * 
     * init:页面初始化
     * 
     * @author huangjx 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping()
    public String init(){
        return "/demo/demo-init";
    }
     

}


