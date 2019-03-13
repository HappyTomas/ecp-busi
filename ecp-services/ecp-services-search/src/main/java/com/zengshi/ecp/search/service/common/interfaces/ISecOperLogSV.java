package com.zengshi.ecp.search.service.common.interfaces;

import com.zengshi.ecp.search.dubbo.dto.SecOperLogReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server-xhs <br>
 * Description: <br>
 * Date:2016年1月20日下午7:40:49  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public interface ISecOperLogSV {
    
    /**
     * 保存日志
     * @param secOperLogReqDTO
     * @return
     */
    public long saveSecOperLog(SecOperLogReqDTO secOperLogReqDTO) throws BusinessException;

}

