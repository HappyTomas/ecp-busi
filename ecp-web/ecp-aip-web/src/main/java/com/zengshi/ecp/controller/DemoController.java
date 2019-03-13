/** 
 * Project Name:ecp-aip-web 
 * File Name:DemoController.java 
 * Package Name:com.zengshi.ecp.controller 
 * Date:2015-10-18上午10:54:01 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.aip.dubbo.dto.AipDemoRequest;
import com.zengshi.ecp.aip.dubbo.interfaces.IAipDemoRSV;
import com.zengshi.aip.security.DefaultServiceCheckChain;
import com.zengshi.butterfly.core.annotation.Security;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.web.BaseController;
import com.zengshi.controller.aip.util.Escape;

/**
 * Title: ECP <br>
 * Project Name:ecp-aip-web <br>
 * Description: <br>
 * Date:2015-10-18上午10:54:01  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Controller
public class DemoController extends BaseController{
    
    @RequestMapping(value="/rest" ,params="method=com.test.hello")
    @Security(mustLogin=true,authorCheckType=DefaultServiceCheckChain.class)
    @ResponseBody
    public Map<String,String> demo(String name) throws BusinessException{
        Map<String,String> map = new HashMap<>();
        map.put("msg", "Hello ," + name);
        return map;
    }
    
    
    @Resource
    private IAipDemoRSV aipDemoRSV; 
    
    @RequestMapping(value="/rest" ,params="method=com.test.hello.services")
    @Security(mustLogin=true,authorCheckType=DefaultServiceCheckChain.class)
    @ResponseBody
    public Map<String,String> demoService(String name) throws BusinessException{
        
        AipDemoRequest req = new AipDemoRequest();
        req.setName(name);
        String value = aipDemoRSV.demo(req);
        Map<String,String> map = new HashMap<>();
        map.put("msg", value);
        return map;
    }
}

