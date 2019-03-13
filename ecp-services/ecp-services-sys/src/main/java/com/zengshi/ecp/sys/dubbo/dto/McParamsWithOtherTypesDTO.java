/** 
 * Project Name:ecp-services-sys 
 * File Name:McParamsWithPhoneDTO.java 
 * Package Name:com.zengshi.ecp.sys.dubbo.dto 
 * Date:2016年3月30日下午8:45:18 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.dubbo.dto;

import java.util.HashMap;
import java.util.Map;

import com.zengshi.ecp.general.sys.mc.dto.McParamsDTO;
import com.zengshi.ecp.sys.dubbo.util.McParamsOtherConstants;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月30日下午8:45:18  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class McParamsWithOtherTypesDTO extends McParamsDTO {
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1241931958893273420L;
    
   
    private Map<String,String> otherSendsMap ;


    public Map<String, String> getOtherSendsMap() {
        return otherSendsMap;
    }


    public void setOtherSendsMap(Map<String, String> otherSendsMap) {
        this.otherSendsMap = otherSendsMap;
    }
    
    /**
     * 
     * phoneNo: 设置接收消息的手机号码<br/> 
     * 
     * @author yugn 
     * @param phoneNo 
     * @since JDK 1.6
     */
    public void phoneNo(String phoneNo){
        if(this.otherSendsMap == null){
            this.otherSendsMap = new HashMap<>();
        }
        this.otherSendsMap.put(McParamsOtherConstants.PHONE_PARAM_KEY, phoneNo);
    }
    
    /**
     * 
     * phoneNo: 获取接收消息的手机号码
     * 
     * @author yugn 
     * @return 
     * @since JDK 1.6
     */
    public String phoneNo(){
        if(this.otherSendsMap == null){
            return "";
        } else {
            return this.otherSendsMap.get(McParamsOtherConstants.PHONE_PARAM_KEY);
        }
    }
    
    /**
     * 
     * email: 设置接收消息的email<br/> 
     * 
     * @author yugn 
     * @param email 
     * @since JDK 1.6
     */
    public void email(String email){
        if(this.otherSendsMap == null){
            this.otherSendsMap = new HashMap<>();
        }
        this.otherSendsMap.put(McParamsOtherConstants.MAIL_PARAM_KEY, email);
    }
    
    /**
     * 
     * email: 获取接收消息的email<br/> 
     * 
     * @author yugn 
     * @return 
     * @since JDK 1.6
     */
    public String email(){
        if(this.otherSendsMap == null){
            return "";
        } else {
            return this.otherSendsMap.get(McParamsOtherConstants.MAIL_PARAM_KEY);
        }
    }
    
    
}

