package com.zengshi.ecp.search.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.search.dao.model.SecArgs;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server-xhs <br>
 * Description: <br>
 * Date:2016年1月20日下午7:39:30  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public interface ISecArgsSV {
    
    /**
     * 获取所有配置参数信息
     * @return
     * @throws BusinessException
     */
    public List<SecArgs> listSecArgs() throws BusinessException;

}

