package com.zengshi.ecp.search.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月10日下午10:39:22  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public interface ISecConfigRSV{
    
    /**
     * 保存索引配置信息（不级联保存搜索数据对象和搜索数据对象字段）
     * @param secConfigReqDTO
     * @return
     */
    public long saveSecConfig(SecConfigReqDTO secConfigReqDTO) throws BusinessException;
    
    /**
     * 删除索引配置信息（级联删除搜索数据对象和搜索数据对象字段）
     * @param secConfigReqDTO
     * @throws BusinessException
     */
    public void deleteSecConfigAll(SecConfigReqDTO secConfigReqDTO) throws BusinessException;
    
    /**
     * 更新索引配置信息（不级联更新搜索数据对象和搜索数据对象字段）
     * @param secConfigReqDTO
     * @throws BusinessException
     */
    public void updateSecConfig(SecConfigReqDTO secConfigReqDTO) throws BusinessException;
    
    /**
     * 更新索引配置信息（选择性更新）
     * @param secConfigReqDTO
     * @throws BusinessException
     */
    public void updateSecConfigSelective(SecConfigReqDTO secConfigReqDTO) throws BusinessException;
    
    /**
     * 根据Id查询获取指定id的索引配置信息（不级联查询搜索数据对象和搜索数据对象字段）
     * @param id
     * @return
     * @throws BusinessException
     */
    public SecConfigRespDTO querySecConfigById(SecConfigReqDTO secConfigReqDTO) throws BusinessException;
    
    /**
     * 根据Id查询获取指定id的索引配置信息（级联查询搜索数据对象和搜索数据对象字段）
     * @param id
     * @return
     * @throws BusinessException
     */
    public SecConfigRespDTO querySecConfigByIdAll(SecConfigReqDTO secConfigReqDTO) throws BusinessException;
    
    /**
     * 查询所有索引配置信息（级联查询搜索数据对象和搜索数据对象字段）
     * @return
     * @throws BusinessException
     */
    public List<SecConfigRespDTO> listSecConfigAll(SecConfigReqDTO secConfigReqDTO) throws BusinessException;
    
    /**
     * 获取所有索引配置信息（不级联查询搜索数据对象和搜索数据对象字段）
     * @return
     * @throws BusinessException
     */
    public List<SecConfigRespDTO> listSecConfig(SecConfigReqDTO secConfigReqDTO) throws BusinessException;
    
    /**
     * 索引配置启用/禁用时配置名称判重
     * @param secConfigReqDTO
     * @return
     * @throws BusinessException
     */
    public boolean isDupConfigName(SecConfigReqDTO secConfigReqDTO) throws BusinessException;
    
    /**
     * 分页获取索引配置信息（不级联查询搜索数据对象和搜索数据对象字段）
     * @param secConfigReqDTO
     * @return
     * @throws BusinessException
     */
    public PageResponseDTO<SecConfigRespDTO> querySecConfigPage(SecConfigReqDTO secConfigReqDTO) throws BusinessException;

}

