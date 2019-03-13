/** 
 * Project Name:ecp-services-sys 
 * File Name:SmsActiveRSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.dubbo.impl 
 * Date:2016年3月19日下午6:20:53 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.dubbo.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dao.model.BaseSmsCfg;
import com.zengshi.ecp.sys.dubbo.dto.BaseSmsCfgReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.SmsActiveDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.ISmsActiveRSV;
import com.zengshi.ecp.sys.dubbo.util.EcpSysCodeConstants;
import com.zengshi.ecp.sys.service.common.interfaces.IBaseSmsCfgSV;
import com.zengshi.ecp.sys.sms.gateway.SmsGatewayBean;
import com.zengshi.ecp.sys.sms.impl.GateWayBeanBuilderSV;
import com.zengshi.ecp.sys.sms.interfaces.IMsgSmsOperateSV;
import com.zengshi.ecp.sys.sms.operator.SmsOperatorSendBean;
import com.zengshi.paas.utils.LogUtil;

import net.sf.json.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月19日下午6:20:53  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class SmsActiveRSVImpl implements ISmsActiveRSV {
    
    private static final String MODULE = SmsActiveRSVImpl.class.getName();
    
    @Autowired
    private GateWayBeanBuilderSV gateWayBeanBuilderSV;

    /** 
     * 发送验证短信；即在配置短信网关的时候，发送的内容；
     * @see com.zengshi.ecp.sys.dubbo.interfaces.ISmsActiveRSV#sendSms(com.zengshi.ecp.sys.dubbo.dto.SmsActiveDTO) 
     */
    @Override
    public void sendSmsVerifyMsg (SmsActiveDTO activeDto) throws BusinessException {
        
        if(activeDto == null || StringUtils.isEmpty(activeDto.getGateway())){
            throw new BusinessException("sys.msg.send.param.null");
        }
        
        BaseSmsCfg smsCfg = this.buildSmsCfgFromAcitveDTO(activeDto);
        SmsGatewayBean gatewayBean = gateWayBeanBuilderSV.buildGatewayBean(activeDto.getGateway(), smsCfg);
        
        SmsOperatorSendBean<SmsGatewayBean> sendBean =  new SmsOperatorSendBean<>(gatewayBean);
        //发送内容；
        if(StringUtils.isEmpty(activeDto.getSendMsg())){
            sendBean.setContext("您现在是在验证短信网关！");
        } else {
            sendBean.setContext(activeDto.getSendMsg());
        }
        
        sendBean.setPhoneNo(activeDto.getRecPhoneno());
        
        //获取短信发送服务；
        IMsgSmsOperateSV<SmsGatewayBean> msgSmsOperateSV = this.gateWayBeanBuilderSV.getSmsOperateSV(activeDto.getGateway());
        //调用短信发送
        try{
            msgSmsOperateSV.sendSms(sendBean);
        } catch(Exception err){ 
            String errMsg = "发送失败;"+err.getMessage();
            LogUtil.error(MODULE, errMsg);
            throw new BusinessException(EcpSysCodeConstants.SYS_MSG_SEND_SMS_SEND_FAILURE,new String[]{errMsg});
        } 
    }
    
    
    
    
    /**
     * 
     * buildSmsCfgFromAcitveDTO: 构造一个简单的BaseSmsCfg <br/> 
     * 
     * @author yugn 
     * @param activeDto
     * @return 
     * @since JDK 1.6
     */
    private BaseSmsCfg buildSmsCfgFromAcitveDTO(SmsActiveDTO activeDto){
        
        BaseSmsCfg smsCfg = new BaseSmsCfg();
        smsCfg.setAccount(activeDto.getAccount());
        smsCfg.setAuthKey(activeDto.getAuthKey());
        smsCfg.setAccount(activeDto.getAccount());
        smsCfg.setSmsGateway(activeDto.getGateway());
        smsCfg.setUrl(activeDto.getUrl());
        if(activeDto.getOthParams() == null || activeDto.getOthParams().isEmpty()){
            smsCfg.setOthParams("");
        } else {
            smsCfg.setOthParams(JSONObject.fromObject(activeDto.getOthParams()).toString());
        }
        
        
        return smsCfg;
    }
    
    @Autowired
    private IBaseSmsCfgSV baseSmsCfgSV;

    @Override
    public void saveSmsCfg(SmsActiveDTO activeDto) throws BusinessException {
        
        if(activeDto == null || StringUtils.isEmpty(activeDto.getGateway())){
            throw new BusinessException("sys.msg.send.param.null");
        }
        
        BaseSmsCfg cfg = this.buildSmsCfgFromAcitveDTO(activeDto);
        
        this.baseSmsCfgSV.saveSmsCfg(cfg, activeDto.getStaff().getId());

    }




    @Override
    public BaseSmsCfgReqDTO fetchSmsCfg(BaseInfo info) throws BusinessException {
        BaseSmsCfg smsCfg = this.baseSmsCfgSV.fetchDefaultSmsCfg();
        
        
        BaseSmsCfgReqDTO dto = new BaseSmsCfgReqDTO();
        dto.setAccount(smsCfg.getAccount());
        dto.setAuthKey(smsCfg.getAuthKey());
        dto.setGateway(smsCfg.getSmsGateway());
        dto.setUrl(smsCfg.getUrl());
        //dto.setOthParams();
        if(StringUtils.isEmpty(smsCfg.getOthParams())){
            
        } else {
            JSONObject json = JSONObject.fromObject(smsCfg.getOthParams());
            Map<String, String> map = new HashMap<>();
            for(Iterator iterator = json.keys();iterator.hasNext();){
                String key = (String)iterator.next();
                String value = json.getString(key);
                map.put(key, value);
            }
            dto.setOthParams(map);
        }
        
        
        return dto;
    }
    
    

}

