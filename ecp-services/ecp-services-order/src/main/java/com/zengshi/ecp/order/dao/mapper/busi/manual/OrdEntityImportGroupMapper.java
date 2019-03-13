package com.zengshi.ecp.order.dao.mapper.busi.manual;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.order.dao.model.OrdEntityImportCriteria;
import com.zengshi.ecp.order.dao.model.OrdEntityImportGroup;

public interface OrdEntityImportGroupMapper {

    List<OrdEntityImportGroup> selectByExampleGroupByOrderAndSub(OrdEntityImportCriteria example) throws DataAccessException;
    
    Long countEntityCode(OrdEntityImportCriteria example) throws DataAccessException;
}