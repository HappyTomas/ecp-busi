package com.zengshi.ecp.order.dao.mapper.busi.manual;

import com.zengshi.ecp.order.dao.model.OrdEntityChangeCriteria;
import org.springframework.dao.DataAccessException;

public interface OrdEntityChangeGroupMapper {
    
    Long countEntityCodeAf(OrdEntityChangeCriteria example) throws DataAccessException;
    
    Long countEntityCodeBf(OrdEntityChangeCriteria example) throws DataAccessException;
}