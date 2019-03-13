package com.zengshi.ecp.order.dao.mapper.busi.manual;

import com.zengshi.ecp.order.dao.model.OrdDiscountCriteria;
import org.springframework.dao.DataAccessException;

public interface OrdDiscountUalMapper {
    Long sumDiscountPriceByOrderId(OrdDiscountCriteria example) throws DataAccessException;
}