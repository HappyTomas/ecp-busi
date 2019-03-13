package com.zengshi.ecp.sys.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dao.model.MsgSend;
import com.zengshi.ecp.sys.dubbo.dto.BaseEmailReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.BaseEmailResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgDefineReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgDefineResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgInfoReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgMailReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgMailResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSendReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSendResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSiteReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSiteResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSmsReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSmsResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 消息中心管理服务接口<br>
 * Date:2016-2-24上午11:32:46  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public interface IMsgManageSV {
    
    /**
     * 
     * saveBaseEmail:(保存邮件服务配置). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int saveBaseEmail(BaseEmailReqDTO req) throws BusinessException;
    
    /**
     * 
     * listMsgDefine:(查询消息模板列表). <br/> 
     * 
     * @author huangxl5
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<MsgDefineResDTO> listMsgDefine(MsgDefineReqDTO req) throws BusinessException;
    
    /**
     * 
     * saveMsgSend:(保存消息发送方式开关信息). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int saveMsgSend(MsgSendReqDTO req) throws BusinessException;
    
    /**
     * 
     * updateMsgSend:(保存消息发送方式开关信息). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updateMsgSend(MsgSendReqDTO req) throws BusinessException;
    
    /**
     * 
     * updateMsgSms:(更新手机短信模板表信息). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updateMsgSms(MsgSmsReqDTO req) throws BusinessException;
    
    /**
     * 
     * updateMsgSite:(更新站内短信模板表信息). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updateMsgSite(MsgSiteReqDTO req) throws BusinessException;
    
    /**
     * 
     * udpateMsgEmail:(更新邮件模板表信息). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updateMsgEmail(MsgMailReqDTO req) throws BusinessException;
    
    /**
     * 
     * findMsgSite:(查询站内短信模板). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public MsgSiteResDTO findMsgSite(MsgSiteReqDTO req) throws BusinessException;
    
    /**
     * 
     * findMsgSms:(查询手机短信模板). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public MsgSmsResDTO findMsgSms(MsgSmsReqDTO req) throws BusinessException;
    
    /**
     * 
     * findMsgMail:(查询邮件模板). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public MsgMailResDTO findMsgMail(MsgMailReqDTO req) throws BusinessException;
    
    /**
     * 
     * findMsgSend:(查询发送方式开关). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public String findMsgSend(MsgSendReqDTO req) throws BusinessException;
    
    /**
     * 
     * findBaseEmail:(查询邮件服务配置). <br/> 
     * 
     * @author huangxl5
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public BaseEmailResDTO findBaseEmail() throws BusinessException;
    
    
    /**
     * 
     * fetchMsgSendByMsgCode: 根据消息编码，获取该消息对应的发送方式信息列表 <br/> 
     * 
     * @author yugn 
     * @param msgCode
     * @throws Exception 
     * @since JDK 1.6
     */
    public List<MsgSend> fetchMsgSendByMsgCode(String msgCode) throws Exception;
    
    
    /**
     * 
     * fetchActivedMsgSendByMsgCode: 根据消息编码 ,获取该消息对应的 有效（启用的） 的 发送方式信息列表<br/> 
     * 
     * @author yugn 
     * @param msgCode
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    public List<MsgSend> fetchActivedMsgSendByMsgCode(String msgCode) throws Exception;
}

