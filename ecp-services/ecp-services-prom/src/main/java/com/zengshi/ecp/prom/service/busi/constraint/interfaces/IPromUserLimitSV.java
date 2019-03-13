package com.zengshi.ecp.prom.service.busi.constraint.interfaces;

import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromUserLimitDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-29 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromUserLimitSV extends IGeneralSQLSV{
 
    
    /**
     * 查询
     * @param promUserLimitDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromUserLimitDTO query(PromUserLimitDTO promUserLimitDTO)
            throws BusinessException;
    
    /**
     * 新增
     * @param promUserLimitDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public void insert(PromUserLimitDTO promUserLimitDTO)
            throws BusinessException;
    /**
     * 减少
     * @param promUserLimitDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public int update(PromUserLimitDTO promUserLimitDTO)
            throws BusinessException;
}
