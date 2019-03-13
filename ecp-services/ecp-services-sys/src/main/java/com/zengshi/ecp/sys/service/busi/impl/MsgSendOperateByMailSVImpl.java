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
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.general.sys.mc.dto.McUserInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dao.model.MsgSendDetail;
import com.zengshi.ecp.sys.dubbo.dto.BaseEmailResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgMailReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgMailResDTO;
import com.zengshi.ecp.sys.dubbo.util.EcpSysCodeConstants;
import com.zengshi.ecp.sys.dubbo.util.SysStringTemplateUtil;
import com.zengshi.ecp.sys.service.busi.interfaces.IMsgSendOperateSV;
import com.zengshi.ecp.sys.service.common.interfaces.IMsgManageSV;
import com.zengshi.paas.mail.impl.MailManager;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-sys-server <br>
 * Description: 邮件发送<br>
 * Date:2016-4-6下午3:34:38  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
@Service("msgSendOperateByMail")
public class MsgSendOperateByMailSVImpl implements IMsgSendOperateSV {
    
    private final static String MODULE = MsgSendOperateByMailSVImpl.class.getName();
    
    @Resource(name="msgManageSV")
    private IMsgManageSV msgManageSV;
    
    /** 
     * 邮件发送；
     * @see com.zengshi.ecp.sys.service.busi.interfaces.IMsgSendOperateSV#sendMsgOperate(com.zengshi.ecp.sys.dao.model.MsgSendDetail, com.zengshi.ecp.sys.dao.model.MsgInfo, com.zengshi.ecp.general.sys.mc.dto.McUserInfo) 
     */
    @Override
    public void sendMsgOperate(MsgSendDetail msgDetail, Map<String,String> attrMap, McUserInfo userInfo) throws BusinessException {
        
        /*1、根据消息编码，获取邮件模板，不存在则抛出异常*/
        MsgMailResDTO msgMail = this.fetchMsgTemplate(msgDetail.getMsgCode());
        if(msgMail == null || StringUtils.isEmpty(msgMail.getMailTitleTemplate()) || StringUtil.isBlank(msgMail.getMailBodyTemplate())){
            LogUtil.error(MODULE, "消息编码：" + msgDetail.getMsgCode() + "；获取的邮件标题或内容模板为空；");
            //异常；跳出；
            throw new BusinessException(EcpSysCodeConstants.SYS_MSG_SEND_MAIL_TEMPLATE_NULL,new String[]{msgDetail.getMsgCode()});
        }
        
        /*2、根据模板及入参，组装标题与内容*/
        String msgTitle = SysStringTemplateUtil.genString(msgMail.getMailTitleTemplate(), attrMap);
        String msgContext = SysStringTemplateUtil.genString(msgMail.getMailBodyTemplate(), attrMap);
        
        msgDetail.setMsgMemo(msgContext);
        
        /*3、设置邮箱，没有邮箱，则抛出异常*/
        if(userInfo == null || StringUtils.isBlank(userInfo.getEmail())){
            LogUtil.error(MODULE, "消息编码：" + msgDetail.getMsgCode() + "；接收消息的用户" + userInfo.getUserId() + "对应的邮箱为空；无法发送邮件");
            msgDetail.setSendTag("发送失败，未找到接收消息用户" + userInfo.getUserId() + "的邮箱信息");
            throw new BusinessException(EcpSysCodeConstants.SYS_MSG_SEND_SMS_EMAIL_NULL,new String[]{userInfo.getUserId() + ""});
        } else {
            msgDetail.setRecCode(userInfo.getEmail());
        }
        
        /*4、获取邮件服务器的相关信息*/
        BaseEmailResDTO baseEmail = msgManageSV.findBaseEmail();
        
        if (baseEmail == null) {
            LogUtil.error(MODULE, "邮件发送失败，未找到邮件服务器的相关配置");
            msgDetail.setSendTag("邮件发送失败，未找到邮件服务器的相关配置");
            throw new BusinessException("邮件发送失败，未找到邮件服务器的相关配置");
        }
        MailManager mail = new MailManager();
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(baseEmail.getSmtpServer());//邮件服务器
        sender.setPort(baseEmail.getSmtpPort());
        sender.setProtocol(EcpSysCodeConstants.SYS_MSG_EMAIL_PROTOCAL);
        sender.setUsername(baseEmail.getSmtpUser());
        sender.setPassword(baseEmail.getSmtpPassword());
        
        /*5、设置 邮件服务器相关信息，发送邮件*/
        mail.setMailSender(sender);
        try {
            //邮件发送
            mail.sendMail(baseEmail.getSendEmail(),userInfo.getEmail(), msgTitle, msgContext);
        } catch (Exception e) {
            String errMsg = "发送失败;" + e.getMessage();
            LogUtil.error(MODULE, errMsg);
            throw new BusinessException(EcpSysCodeConstants.SYS_MSG_SEND_EMAIL_SEND_FAILURE,new String[]{errMsg});
        }
    }
    
    
    /**
     * 
     * fetchMsgTemplate: 根据消息编码获取邮件模板 <br/> 
     * 
     * @author yugn 
     * @param msgCode
     * @return 
     * @since JDK 1.6
     */
    private MsgMailResDTO fetchMsgTemplate(String msgCode){
        
        MsgMailReqDTO reqDTO = new MsgMailReqDTO();
        reqDTO.setMsgCode(msgCode);
        
        return msgManageSV.findMsgMail(reqDTO);
    }
    
}

