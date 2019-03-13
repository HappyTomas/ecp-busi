package com.zengshi.ecp.prom.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromMsgRSV {

    /**
     * 提醒信息 列表
     * 
     * @param promTypeMsgDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromTypeMsgResponseDTO> queryPromMsgList(PromTypeMsgDTO promTypeMsgDTO)
            throws BusinessException; 
}
