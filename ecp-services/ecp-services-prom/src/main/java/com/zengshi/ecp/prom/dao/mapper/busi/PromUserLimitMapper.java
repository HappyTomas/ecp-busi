package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromUserLimit;
import com.zengshi.ecp.prom.dao.model.PromUserLimitCriteria;
import com.zengshi.ecp.prom.dao.model.PromUserLimitKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromUserLimitMapper {
    Long countByExample(PromUserLimitCriteria example) throws DataAccessException;

    int deleteByExample(PromUserLimitCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(PromUserLimitKey key) throws DataAccessException;

    int insert(PromUserLimit record) throws DataAccessException;

    int insertSelective(PromUserLimit record) throws DataAccessException;

    List<PromUserLimit> selectByExample(PromUserLimitCriteria example) throws DataAccessException;

    PromUserLimit selectByPrimaryKey(PromUserLimitKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromUserLimit record, @Param("example") PromUserLimitCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromUserLimit record, @Param("example") PromUserLimitCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromUserLimit record) throws DataAccessException;

    int updateByPrimaryKey(PromUserLimit record) throws DataAccessException;
}