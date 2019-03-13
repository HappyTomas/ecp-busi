/** 
 * Project Name:ecp-services-sys 
 * File Name:GateWayBeanBuilderSV.java 
 * Package Name:com.zengshi.ecp.sys.sms.impl 
 * Date:2016年3月17日上午11:06:36 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.sms.impl;

import java.util.Map;

import com.zengshi.ecp.sys.dao.model.BaseSmsCfg;
import com.zengshi.ecp.sys.sms.gateway.SmsGatewayBean;
import com.zengshi.ecp.sys.sms.interfaces.IGateWayBeanBuilderSV;
import com.zengshi.ecp.sys.sms.interfaces.IMsgSmsOperateSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月17日上午11:06:36  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */

public class GateWayBeanBuilderSV {
    
    private Map<String,IGateWayBeanBuilderSV<SmsGatewayBean>> builderMap;
   
    
    public void setBuilderMap(Map<String, IGateWayBeanBuilderSV<SmsGatewayBean>> builderMap) {
        this.builderMap = builderMap;
    }


    public IGateWayBeanBuilderSV<SmsGatewayBean> getInstance(String gateway){
        return this.builderMap.get(gateway);
    }
    
    /**
     * 
     * buildGatewayBean: 根据 BaseSmsCfg 和 gateway 构建一个GateWayBean<br/> 
     * 
     * @author yugn 
     * @param gateway
     * @param smsCfg
     * @return 
     * @since JDK 1.6
     */
    public SmsGatewayBean buildGatewayBean(String gateway,BaseSmsCfg smsCfg){
        IGateWayBeanBuilderSV<SmsGatewayBean> sv = this.getInstance(gateway);
        return sv.parse(smsCfg);
    }
    
    /**
     * 短信操作类
     */
    private Map<String,IMsgSmsOperateSV<SmsGatewayBean>> smsOperatorMap;


    public void setSmsOperatorMap(Map<String, IMsgSmsOperateSV<SmsGatewayBean>> smsOperatorMap) {
        this.smsOperatorMap = smsOperatorMap;
    }
    
    public IMsgSmsOperateSV<SmsGatewayBean> getSmsOperateSV(String gateway){
        return this.smsOperatorMap.get(gateway);
    }

}

