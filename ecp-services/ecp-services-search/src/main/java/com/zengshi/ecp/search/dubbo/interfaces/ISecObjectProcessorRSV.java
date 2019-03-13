package com.zengshi.ecp.search.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.search.dubbo.dto.SecObjectProcessorReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectProcessorRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server-xhs <br>
 * Description: <br>
 * Date:2016年3月10日上午9:32:15  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public interface ISecObjectProcessorRSV {
    
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
    public List<SecObjectProcessorRespDTO> listSecObjectProcessor(SecObjectProcessorReqDTO secObjectProcessorReqDTO) throws BusinessException;


}

