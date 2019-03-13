package com.zengshi.ecp.prom.service.common.msg.interfaces;

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
public interface IPromMsgSV{

    /**
     * 提醒信息
     * 
     * @param id
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromTypeMsgResponseDTO queryPromMsgById(Long id) throws BusinessException;

    /**
     * 提醒信息
     * 
     * @param promTypeCode
     * @param status
     * @param position
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromTypeMsgResponseDTO queryPromMsgByCode(String promTypeCode, String status,
            String position) throws BusinessException;

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
