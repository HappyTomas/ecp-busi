package com.zengshi.ecp.search.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.search.dubbo.dto.SecOperLogReqDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecOperLogRSV;
import com.zengshi.ecp.search.service.common.interfaces.ISecOperLogSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server-xhs <br>
 * Description: <br>
 * Date:2016年1月20日下午8:05:37  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecOperLogRSVImpl implements ISecOperLogRSV {
    
    @Resource
    public ISecOperLogSV secOperLogSV;

    @Override
    public long saveSecOperLog(SecOperLogReqDTO secOperLogReqDTO) throws BusinessException {
        return secOperLogSV.saveSecOperLog(secOperLogReqDTO);
    }

}

