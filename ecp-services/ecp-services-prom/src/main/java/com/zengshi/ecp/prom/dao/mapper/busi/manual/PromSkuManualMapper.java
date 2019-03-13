package com.zengshi.ecp.prom.dao.mapper.busi.manual;

import com.zengshi.ecp.prom.dao.model.PromSku;
import org.springframework.dao.DataAccessException;

public interface PromSkuManualMapper {
    int updateByPromIdSelective(PromSku record) throws DataAccessException;
 
}