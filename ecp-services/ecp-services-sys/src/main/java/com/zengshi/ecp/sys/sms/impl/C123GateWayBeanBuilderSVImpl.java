/** 
 * Project Name:ecp-services-sys 
 * File Name:C123GateWayBeanBuilderSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.sms.impl 
 * Date:2016年3月17日上午11:15:34 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.sms.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.sys.dao.model.BaseSmsCfg;
import com.zengshi.ecp.sys.sms.gateway.C123SmsGatewayBean;
import com.zengshi.ecp.sys.sms.interfaces.IGateWayBeanBuilderSV;
import com.zengshi.paas.utils.LogUtil;

import net.sf.json.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月17日上午11:15:34  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Service("c123GateWayBeanBuilderSV")
public class C123GateWayBeanBuilderSVImpl implements IGateWayBeanBuilderSV<C123SmsGatewayBean> {
    
    private static final String MODULE = C123GateWayBeanBuilderSVImpl.class.getName();
    
    private static final String GATEWAY_CSID = "csid";
    
    private static final String GATEWAY_CGID = "cgid";
    
    @Override
    public C123SmsGatewayBean parse(BaseSmsCfg smsCfg) {
        
        if(smsCfg == null){
            LogUtil.error(MODULE, "C123 短信网关设置的参数为空；请确认通讯接口及参数==========");
            return null;
        }
        
        C123SmsGatewayBean bean = new C123SmsGatewayBean();
        bean.setGateway(smsCfg.getSmsGateway());
        bean.setAccount(smsCfg.getAccount());
        bean.setAuthKey(smsCfg.getAuthKey());
        bean.setUrl(smsCfg.getUrl());
        String othParams = smsCfg.getOthParams();
        if(StringUtils.isEmpty(othParams)){
            ///
            LogUtil.error(MODULE, "C123 短信网关未设置 csid ,cgid 参数；请确认通讯接口及参数==========");
        } else {
            JSONObject params = JSONObject.fromObject(othParams);
            if(params.containsKey(GATEWAY_CSID)){
                bean.setCsid(params.getString(GATEWAY_CSID));
            }
            if(params.containsKey(GATEWAY_CGID)){
                bean.setCgid(params.getString(GATEWAY_CGID));
            }
        }
        
        
        return bean;
        
    }

    
}

