/** 
 * Project Name:ecp-services-sys 
 * File Name:MsgSendOperateByInsite.java 
 * Package Name:com.zengshi.ecp.sys.service.busi.impl 
 * Date:2016年3月15日上午11:00:47 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.busi.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.general.sys.mc.dto.McUserInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dao.model.BaseSmsCfg;
import com.zengshi.ecp.sys.dao.model.MsgSendDetail;
import com.zengshi.ecp.sys.dubbo.dto.MsgSmsReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSmsResDTO;
import com.zengshi.ecp.sys.dubbo.util.EcpSysCodeConstants;
import com.zengshi.ecp.sys.dubbo.util.SysStringTemplateUtil;
import com.zengshi.ecp.sys.service.busi.interfaces.IMsgSendOperateSV;
import com.zengshi.ecp.sys.service.common.interfaces.IBaseSmsCfgSV;
import com.zengshi.ecp.sys.service.common.interfaces.IMsgManageSV;
import com.zengshi.ecp.sys.sms.gateway.SmsGatewayBean;
import com.zengshi.ecp.sys.sms.impl.GateWayBeanBuilderSV;
import com.zengshi.ecp.sys.sms.interfaces.IMsgSmsOperateSV;
import com.zengshi.ecp.sys.sms.operator.SmsOperatorSendBean;
import com.zengshi.paas.utils.LogUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: 站内消息发送<br>
 * Date:2016年3月15日上午11:00:47  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Service("msgSendOperateBySms")
public class MsgSendOperateBySmsSVImpl implements IMsgSendOperateSV {
    
    private final static String MODULE = MsgSendOperateBySmsSVImpl.class.getName();
    
    @Resource(name="msgManageSV")
    private IMsgManageSV msgManageSV;
    
    @Resource(name="gateWayBeanBuilderSV")
    private GateWayBeanBuilderSV gateWayBeanBuilderSV;
    
    @Resource(name="baseSmsCfgSV")
    private IBaseSmsCfgSV baseSmsCfgSV;
    
    /*@Resource(name="smsOperatorMap")
    private Map<String,IMsgSmsOperateSV<SmsGatewayBean>> smsOperatorMap;*/


    /** 
     * 站内短信发送；
     * @see com.zengshi.ecp.sys.service.busi.interfaces.IMsgSendOperateSV#sendMsgOperate(com.zengshi.ecp.sys.dao.model.MsgSendDetail, com.zengshi.ecp.sys.dao.model.MsgInfo, com.zengshi.ecp.general.sys.mc.dto.McUserInfo) 
     */
    @Override
    public void sendMsgOperate(MsgSendDetail msgDetail, Map<String,String> attrMap, McUserInfo userInfo) throws BusinessException {
        
        MsgSmsResDTO msgSms = this.fetchMsgTemplate(msgDetail.getMsgCode());
        if(msgSms == null || StringUtils.isEmpty(msgSms.getSmsTemplate())){
            LogUtil.error(MODULE, "消息编码："+msgDetail.getMsgCode()+"；获取的手机短信消息模板为空；");
            ///异常；跳出；
            throw new BusinessException(EcpSysCodeConstants.SYS_MSG_SEND_SMS_TEMPLATE_NULL,new String[]{msgDetail.getMsgCode()});
        }
        ///生成发送消息；
        String msgContext = SysStringTemplateUtil.genString(msgSms.getSmsTemplate(), attrMap);
        //回填msgDetail
        msgDetail.setMsgMemo(msgContext);
        //设置手机号码；
        if(userInfo == null || StringUtils.isBlank(userInfo.getPhoneNo())){
            LogUtil.error(MODULE, "消息编码："+msgDetail.getMsgCode()+"；接收消息的用户"+userInfo.getUserId()+"对应的手机号码为空；无法发送短信");
            msgDetail.setSendTag("发送失败，未找到接收消息用户"+userInfo.getUserId()+"的手机号码信息");
            throw new BusinessException(EcpSysCodeConstants.SYS_MSG_SEND_SMS_PHONE_NULL,new String[]{userInfo.getUserId()+""});
        } else {
            msgDetail.setRecCode(userInfo.getPhoneNo());
        }
        
        //构造短信发送类
        SmsOperatorSendBean<SmsGatewayBean> sendBean = this.buildSendBean();
        sendBean.setContext(msgContext);
        sendBean.setPhoneNo(userInfo.getPhoneNo());
        
        //获取短信发送服务；
        IMsgSmsOperateSV<SmsGatewayBean> msgSmsOperateSV = this.gateWayBeanBuilderSV.getSmsOperateSV(sendBean.getGateWay().getGateway());
        
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
     * fetchMsgTemplate: 根据消息编码获取站内消息模板 <br/> 
     * 
     * @author yugn 
     * @param msgCode
     * @return 
     * @since JDK 1.6
     */
    private MsgSmsResDTO fetchMsgTemplate(String msgCode){
        
        MsgSmsReqDTO reqDTO = new MsgSmsReqDTO();
        reqDTO.setMsgCode(msgCode);
        
        return msgManageSV.findMsgSms(reqDTO);
    }
    
    /**
     * 
     * buildSendBean:(这里用一句话描述这个方法的作用). 构造请求参数的对象<br/> 
     * @author yugn 
     * @return 
     * @since JDK 1.6
     */
    private SmsOperatorSendBean<SmsGatewayBean> buildSendBean(){
        //后续这部分的SmsGatewayBean 建议写入缓存；
        BaseSmsCfg smsCfg = baseSmsCfgSV.fetchDefaultSmsCfg();

        SmsGatewayBean bean = gateWayBeanBuilderSV.buildGatewayBean(smsCfg.getSmsGateway(), smsCfg);
        
        SmsOperatorSendBean<SmsGatewayBean> sendBean =  new SmsOperatorSendBean<>(bean);
        
        return sendBean;
    }
    
    
    

}

