package com.zengshi.ecp.search.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.search.dao.model.SecObjectProcessor;
import com.zengshi.ecp.search.dubbo.dto.SecObjectProcessorReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午5:00:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public interface ISecObjectProcessorSV extends IGeneralSQLSV{
    
    /**
     * 保存搜索数据对象处理器
     * @param secObjectProcessorReqDTO
     * @return
     */
    public String saveSecObjectProcessor(SecObjectProcessorReqDTO secObjectProcessorReqDTO) throws BusinessException;
    
    /**
     * 获取所有搜索数据对象处理器
     * @return
     * @throws BusinessException
     */
    public List<SecObjectProcessor> listSecObjectProcessor(SecObjectProcessorReqDTO secObjectProcessorReqDTO) throws BusinessException;

}

