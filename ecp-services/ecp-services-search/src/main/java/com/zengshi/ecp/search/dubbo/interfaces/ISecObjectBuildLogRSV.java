package com.zengshi.ecp.search.dubbo.interfaces;

import com.zengshi.ecp.search.dubbo.dto.SecObjectBuildLogReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server-xhs <br>
 * Description: <br>
 * Date:2016年1月20日下午8:00:17  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public interface ISecObjectBuildLogRSV {
    
    /**
     * 保存日志
     * @param secObjectBuildLogDTO
     * @return
     */
    public long saveSecObjectBuildLog(SecObjectBuildLogReqDTO secObjectBuildLogDTO) throws BusinessException;

}

