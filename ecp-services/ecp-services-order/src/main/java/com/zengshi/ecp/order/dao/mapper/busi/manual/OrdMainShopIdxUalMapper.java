package com.zengshi.ecp.order.dao.mapper.busi.manual;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.order.dubbo.dto.OrdMainShopUal;
import com.zengshi.ecp.order.dubbo.dto.ROrdStaffTradeInfoReq;

public interface OrdMainShopIdxUalMapper {

    List<OrdMainShopUal> selectStaffTradeTimesByExample(ROrdStaffTradeInfoReq example) throws DataAccessException;
}