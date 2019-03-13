package com.zengshi.ecp.prom.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromMsgRSV;
import com.zengshi.ecp.prom.service.common.msg.interfaces.IPromMsgSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-31 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromMsgRSVImpl implements IPromMsgRSV {
    
    @Resource
    private  IPromMsgSV promMsgSV;
    
    /**
     * 提醒信息 列表
     * 
     * @param promTypeMsgDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromTypeMsgResponseDTO> queryPromMsgList(PromTypeMsgDTO promTypeMsgDTO)
            throws BusinessException{
        return promMsgSV.queryPromMsgList(promTypeMsgDTO);
    }
}
