package com.zengshi.ecp.prom.dao.mapper.busi.manual;

import com.zengshi.ecp.prom.dao.model.PromGift;
import org.springframework.dao.DataAccessException;

public interface PromGiftManualMapper {
    
    /**
     * 并发 时 验证扣赠品库存
     * @param record
     * @return
     * @throws DataAccessException
     * @author huangjx
     */
    int updateCntBySelective(PromGift record) throws DataAccessException;
    /**
     *  补偿机制 验证新增赠品库存
     * @param record
     * @return
     * @throws DataAccessException
     * @author huangjx
     */
    int addCntBySelective(PromGift record) throws DataAccessException;

}