package com.zengshi.ecp.prom.dao.mapper.busi.manual;

import com.zengshi.ecp.prom.dao.model.PromStockLimit;
import org.springframework.dao.DataAccessException;

public interface PromStockLimitManualMapper {
    
    /**
     * 并发 时 验证扣库存
     * @param record
     * @return
     * @throws DataAccessException
     * @author huangjx
     */
    int updateCntBySelective(PromStockLimit record) throws DataAccessException;
    /**
     *  补偿机制 验证新增库存
     * @param record
     * @return
     * @throws DataAccessException
     * @author huangjx
     */
    int addCntBySelective(PromStockLimit record) throws DataAccessException;

}