package com.zengshi.ecp.sys.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.BaseEmailReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.BaseEmailResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgDefineReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgDefineResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgInfoReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgMailReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgMailResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSendReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSiteReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSiteResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSmsReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSmsResDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IMsgManageRSV;
import com.zengshi.ecp.sys.service.common.interfaces.IMsgManageSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: dubbo层：消息中心服务接口实现类<br>
 * Date:2016-2-24下午5:40:06  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class MsgManageRSVImpl implements IMsgManageRSV{
    
    @Resource
    private IMsgManageSV msgManageSV;

    @Override
    public int saveBaseEmail(BaseEmailReqDTO req) throws BusinessException {
        return msgManageSV.saveBaseEmail(req);
    }

    @Override
    public PageResponseDTO<MsgDefineResDTO> listMsgDefine(MsgDefineReqDTO req)
            throws BusinessException {
        return msgManageSV.listMsgDefine(req);
    }

    @Override
    public int saveMsgSend(MsgSendReqDTO req) throws BusinessException {
        return msgManageSV.saveMsgSend(req);
    }

    @Override
    public int updateMsgSend(MsgSendReqDTO req) throws BusinessException {
        return msgManageSV.updateMsgSend(req);
    }

    @Override
    public int updateMsgSms(MsgSmsReqDTO req) throws BusinessException {
        return msgManageSV.updateMsgSms(req);
    }

    @Override
    public int updateMsgSite(MsgSiteReqDTO req) throws BusinessException {
        return msgManageSV.updateMsgSite(req);
    }

    @Override
    public int updateMsgEmail(MsgMailReqDTO req) throws BusinessException {
        return msgManageSV.updateMsgEmail(req);
    }

    @Override
    public MsgSiteResDTO findMsgSite(MsgSiteReqDTO req) throws BusinessException {
        return msgManageSV.findMsgSite(req);
    }

    @Override
    public MsgSmsResDTO findMsgSms(MsgSmsReqDTO req) throws BusinessException {
        return msgManageSV.findMsgSms(req);
    }

    @Override
    public MsgMailResDTO findMsgMail(MsgMailReqDTO req) throws BusinessException {
        return msgManageSV.findMsgMail(req);
    }

    @Override
    public String findMsgSend(MsgSendReqDTO req) throws BusinessException {
        return msgManageSV.findMsgSend(req);
    }

    @Override
    public BaseEmailResDTO findBaseEmail() throws BusinessException {
        return msgManageSV.findBaseEmail();
    }

}

