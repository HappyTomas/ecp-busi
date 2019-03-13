package com.zengshi.ecp.search.dubbo.interfaces;

import com.zengshi.ecp.search.dubbo.dto.IndexReusltRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月22日上午10:19:21  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public interface ISecConfigPlanRSV {
    
    /**
     * 创建集合
     * @param secConfigId
     */
    String createCollection(SecConfigReqDTO secConfigReqDTO) throws BusinessException;
    
    /**
     * 删除集合
     * @param secConfigId
     */
    String deleteCollection(SecConfigReqDTO secConfigReqDTO) throws BusinessException;
    
    /**
     * 清除索引数据
     * 
     * @param secConfigId
     * @throws BusinessException
     */
    void cleanIndex(SecConfigReqDTO secConfigReqDTO) throws BusinessException;
    
    /**
     * 重建索引
     * 
     * @param secConfigId
     * @param flag
     * @throws BusinessException
     */
    IndexReusltRespDTO reFullImportIndex(SecConfigReqDTO secConfigReqDTO, boolean flag) throws BusinessException;

}

