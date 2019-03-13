package com.zengshi.ecp.demo.dao.mapper.common;

import com.zengshi.ecp.demo.dao.model.DemoCfg;
import com.zengshi.ecp.demo.dao.model.DemoCfgCriteria;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface DemoCfgMapper {
    Long countByExample(DemoCfgCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(BigDecimal id) throws DataAccessException;

    int insert(DemoCfg record) throws DataAccessException;

    int insertSelective(DemoCfg record) throws DataAccessException;

    List<DemoCfg> selectByExample(DemoCfgCriteria example) throws DataAccessException;

    DemoCfg selectByPrimaryKey(BigDecimal id) throws DataAccessException;

    int updateByPrimaryKeySelective(DemoCfg record) throws DataAccessException;

    int updateByPrimaryKey(DemoCfg record) throws DataAccessException;
}
