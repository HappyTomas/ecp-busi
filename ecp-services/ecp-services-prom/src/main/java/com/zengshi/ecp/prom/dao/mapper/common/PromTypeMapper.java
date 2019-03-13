package com.zengshi.ecp.prom.dao.mapper.common;

import com.zengshi.ecp.prom.dao.model.PromType;
import com.zengshi.ecp.prom.dao.model.PromTypeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromTypeMapper {
    Long countByExample(PromTypeCriteria example) throws DataAccessException;

    int deleteByExample(PromTypeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String promTypeCode) throws DataAccessException;

    int insert(PromType record) throws DataAccessException;

    int insertSelective(PromType record) throws DataAccessException;

    List<PromType> selectByExample(PromTypeCriteria example) throws DataAccessException;

    PromType selectByPrimaryKey(String promTypeCode) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromType record, @Param("example") PromTypeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromType record, @Param("example") PromTypeCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromType record) throws DataAccessException;

    int updateByPrimaryKey(PromType record) throws DataAccessException;
}