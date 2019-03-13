package com.zengshi.ecp.prom.dao.mapper.busi.manual;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.prom.dao.model.PromUserLimit;

public interface PromUserLimitManualMapper {
    
    /**
     * 并发 时 验证扣
     * @param record
     * @return
     * @throws DataAccessException
     * @author huangjx
     */
    int updateCntBySelective(PromUserLimit record) throws DataAccessException;
    /**
     *  补偿机制 验证新增
     * @param record
     * @return
     * @throws DataAccessException
     * @author huangjx
     */
    int addCntBySelective(PromUserLimit record) throws DataAccessException;

}