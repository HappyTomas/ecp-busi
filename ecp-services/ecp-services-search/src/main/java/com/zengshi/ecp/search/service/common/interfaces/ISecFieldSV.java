package com.zengshi.ecp.search.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.search.dao.model.SecField;
import com.zengshi.ecp.search.dubbo.dto.SecFieldReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午5:00:33  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public interface ISecFieldSV extends IGeneralSQLSV{
    
    /**
     * 保存搜索数据对象字段
     * @param secFieldReqDTO
     * @return
     */
    public long saveSecField(SecFieldReqDTO secFieldReqDTO) throws BusinessException;
    
    /**
     * 删除搜索数据对象字段
     * @param secFieldReqDTO
     * @throws BusinessException
     */
    public void deleteSecField(SecFieldReqDTO secFieldReqDTO) throws BusinessException;
    
    /**
     * 更新搜索数据对象字段
     * @param secFieldReqDTO
     * @throws BusinessException
     */
    public void updateSecField(SecFieldReqDTO secFieldReqDTO) throws BusinessException;
    
    /**
     * 根据Id查询获取指定id的搜索数据对象字段
     * @param id
     * @return
     * @throws BusinessException
     */
    public SecField querySecFieldById(SecFieldReqDTO secFieldReqDTO) throws BusinessException;
    
    /**
     * 查询指定搜索数据对象的所有搜索数据对象字段列表
     * @param secObjectId
     * @return
     * @throws BusinessException
     */
    public List<SecField> querySecFieldByObjectId(SecFieldReqDTO secFieldReqDTO) throws BusinessException;
    
    /**
     * 删除指定搜索数据对象的所有搜索数据对象字段列表
     * @param secObjectId
     * @return
     * @throws BusinessException
     */
    public void deleteSecFieldByObjectId(SecFieldReqDTO secFieldReqDTO) throws BusinessException;
    
}

