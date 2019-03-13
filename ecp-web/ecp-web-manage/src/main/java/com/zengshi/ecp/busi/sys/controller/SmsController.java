/** 
 * Project Name:ecp-web-manage 
 * File Name:SmsController.java 
 * Package Name:com.zengshi.ecp.busi.sys.controller 
 * Date:2016年3月19日下午2:56:31 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.sys.dubbo.dto.BaseSmsCfgReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.SmsActiveDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.ISmsActiveRSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2016年3月19日下午2:56:31  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Controller
@RequestMapping(value="/sms")
public class SmsController  extends EcpBaseController{
    
    private static String MODULE = SmsController.class.getName();
    
    @Resource(name="smsActiveRSV")
    private ISmsActiveRSV smsActiveRSV;
    
    @RequestMapping(value="/edit")
    public ModelAndView init(){
        BaseInfo info = new BaseInfo();
        ModelAndView model = new ModelAndView();
        BaseSmsCfgReqDTO dto = this.smsActiveRSV.fetchSmsCfg(info);
        model.addObject("baseSmsCfg", dto);
        model.setViewName("/msg/sms-set-edit");
        return model;
    }
    
    @RequestMapping(value="/test")
    @ResponseBody
    public Map<String,String> smsTest(SmsActiveDTO reqDto){
        
        Map<String,String> map = new HashMap<>();
        
        if(reqDto == null || StringUtils.isEmpty(reqDto.getGateway())
                || StringUtils.isEmpty(reqDto.getRecPhoneno())){
            map.put("flag", "0");
            map.put("msg", "验证的参数为空，请重新输入!");
            return map;
        }
        try{
            smsActiveRSV.sendSmsVerifyMsg(reqDto);
            map.put("flag", "1");
        } catch(Exception err){
            map.put("flag", "-1");
            map.put("msg", HtmlUtils.htmlEscape(err.getMessage()));
        }
        
        return map;
    }
    
    /**
     * 
     * saveCfg: 保存配置信息<br/> 
     * @author yugn 
     * @param reqDto
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/save")
    @ResponseBody
    public Map<String,String> saveCfg(SmsActiveDTO reqDto){
        
        Map<String,String> map = new HashMap<>();
        
        if(reqDto == null || StringUtils.isEmpty(reqDto.getGateway())){
            map.put("flag", "0");
            map.put("msg", "验证的参数为空，请重新输入!");
        } else {
            try{
                this.smsActiveRSV.saveSmsCfg(reqDto);
                map.put("flag", "1");
            } catch(Exception err){
                map.put("flag", "-1");
                map.put("msg", err.getMessage());
            }
        }
        
        return map;
    }
}

