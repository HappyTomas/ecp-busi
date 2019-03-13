package com.zengshi.ecp.search.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.search.dubbo.dto.SecFieldProcessorReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldProcessorRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server-xhs <br>
 * Description: <br>
 * Date:2016年3月10日上午9:32:00  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public interface ISecFieldProcessorRSV {
    
    /**
     * 保存字段处理器
     * @param secFieldProcessorReqDTO
     * @return
     */
    public String saveSecFieldProcessor(SecFieldProcessorReqDTO secFieldProcessorReqDTO) throws BusinessException;
    
    /**
     * 获取所有字段处理器
     * @return
     * @throws BusinessException
     */
    public List<SecFieldProcessorRespDTO> listSecFieldProcessor(SecFieldProcessorReqDTO secFieldProcessorReqDTO) throws BusinessException;


}

