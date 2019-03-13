package com.zengshi.ecp.goods.dao.mapper.common.manual;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.goods.dao.model.GdsPropCriteria;

public interface GdsPropManualMapper {
    /**
     * 
     * 根据查询条件查询属性主键序列。 
     * 
     * @author liyong7
     * @param criteria
     * @return
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    List<Long> queryPropIdsByExample(GdsPropCriteria criteria) throws DataAccessException;
}
