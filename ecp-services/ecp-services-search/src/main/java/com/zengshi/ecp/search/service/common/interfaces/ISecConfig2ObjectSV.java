package com.zengshi.ecp.search.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.search.dao.model.SecConfig2Object;
import com.zengshi.ecp.search.dubbo.dto.SecConfig2ObjectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfig2ObjectRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午5:00:52  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public interface ISecConfig2ObjectSV extends IGeneralSQLSV{
    
    /**
     * 保存索引配置-数据对象映射信息
     * @param secConfig2ObjectReqDTO
     * @return
     */
    public void saveSecConfig2Object(SecConfig2ObjectReqDTO secConfig2ObjectReqDTO) throws BusinessException;
    
    /**
     * 删除指定索引配置的所有数据对象映射信息
     * @param secConfig2ObjectReqDTO
     * @throws BusinessException
     */
    public void deleteSecConfig2ObjectBySecConfigId(SecConfig2ObjectReqDTO secConfig2ObjectReqDTO) throws BusinessException;
    
    /**
     * 删除索引配置的指定数据对象映射信息
     * @param secConfig2ObjectReqDTO
     * @throws BusinessException
     */
    public void deleteSecConfig2Object(SecConfig2ObjectReqDTO secConfig2ObjectReqDTO) throws BusinessException;
    
    /**
     * 查询指定索引配置的所有数据对象映射列表
     * @param secConfig2ObjectReqDTO
     * @return
     * @throws BusinessException
     */
    public List<SecConfig2Object> querySecConfig2ObjectByConfigId(SecConfig2ObjectReqDTO secConfig2ObjectReqDTO) throws BusinessException;
    
    /**
     * 分页获取搜索数据对象映射列表
     * @param secConfig2ObjectReqDTO
     * @return
     * @throws BusinessException
     */
    public PageResponseDTO<SecConfig2ObjectRespDTO> querySecConfig2ObjectPage(SecConfig2ObjectReqDTO secConfig2ObjectReqDTO) throws BusinessException;
    
}

