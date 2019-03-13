package com.zengshi.ecp.search.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.search.dubbo.dto.SecObjectBuildLogReqDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecObjectBuildLogRSV;
import com.zengshi.ecp.search.service.common.interfaces.ISecObjectBuildLogSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server-xhs <br>
 * Description: <br>
 * Date:2016年1月20日下午8:05:29  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecObjectBuildLogRSVImpl implements ISecObjectBuildLogRSV {
    
    @Resource
    public ISecObjectBuildLogSV secObjectBuildLogSV;

    @Override
    public long saveSecObjectBuildLog(SecObjectBuildLogReqDTO secObjectBuildLogDTO)
            throws BusinessException {
        return secObjectBuildLogSV.saveSecObjectBuildLog(secObjectBuildLogDTO);
    }

}

