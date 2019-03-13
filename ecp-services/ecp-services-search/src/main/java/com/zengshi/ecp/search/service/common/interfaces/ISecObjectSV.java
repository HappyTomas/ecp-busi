package com.zengshi.ecp.search.service.common.interfaces;

import com.zengshi.ecp.search.dubbo.dto.SecConfig2ObjectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午5:00:22  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public interface ISecObjectSV extends IGeneralSQLSV{
    
    /**
     * 保存搜索数据对象（级联保存搜索数据对象字段）
     * @param secObjectReqDTO
     * @return
     */
    public long saveSecObjectAll(SecObjectReqDTO secObjectReqDTO) throws BusinessException;
    
    /**
     * 删除搜索数据对象（级联删除搜索数据对象字段）
     * @param secObjectReqDTO
     * @throws BusinessException
     */
    public void deleteSecObjectAll(SecObjectReqDTO secObjectReqDTO) throws BusinessException;
    
    /**
     * 更新搜索数据对象（级联更新搜索数据对象字段）
     * @param secObjectReqDTO
     * @throws BusinessException
     */
    public void updateSecObjectAll(SecObjectReqDTO secObjectReqDTO) throws BusinessException;
    
    /**
     * 根据Id查询获取指定id的搜索数据对象（不级联查询搜索数据对象字段）
     * @param id
     * @return
     * @throws BusinessException
     */
    public SecObjectRespDTO querySecObjectById(SecObjectReqDTO secObjectReqDTO) throws BusinessException;
    
    /**
     * 根据Id查询获取指定id的搜索数据对象（级联查询搜索数据对象字段）
     * @param id
     * @return
     * @throws BusinessException
     */
    public SecObjectRespDTO querySecObjectByIdAll(SecObjectReqDTO secObjectReqDTO) throws BusinessException;
    
    /**
     * 分页获取搜索数据对象信息（不级联查询搜索数据对象字段）
     * @param secObjectReqDTO
     * @return
     * @throws BusinessException
     */
    public PageResponseDTO<SecObjectRespDTO> querySecObjectPage(SecConfig2ObjectReqDTO secConfig2ObjectReqDTO) throws BusinessException;
    
}

