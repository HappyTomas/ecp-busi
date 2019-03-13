package com.zengshi.ecp.search.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.search.dao.model.SecFieldProcessor;
import com.zengshi.ecp.search.dubbo.dto.SecFieldProcessorReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午5:00:36  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public interface ISecFieldProcessorSV extends IGeneralSQLSV{
    
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
    public List<SecFieldProcessor> listSecFieldProcessor(SecFieldProcessorReqDTO secFieldProcessorReqDTO) throws BusinessException;

}

