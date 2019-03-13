package com.zengshi.ecp.prom.dao.mapper.busi.manual;

import com.zengshi.ecp.prom.dao.model.PromInfoCriteria;
import org.springframework.dao.DataAccessException;

public interface PromInfoManualMapper {
    //促销组 获得参与店铺数量 
    Long countShopIdByGoupyId(PromInfoCriteria example) throws DataAccessException;
}