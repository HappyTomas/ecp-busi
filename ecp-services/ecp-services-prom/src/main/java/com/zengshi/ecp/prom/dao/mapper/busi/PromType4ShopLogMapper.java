package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromType4ShopLog;
import com.zengshi.ecp.prom.dao.model.PromType4ShopLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromType4ShopLogMapper {
    Long countByExample(PromType4ShopLogCriteria example) throws DataAccessException;

    int deleteByExample(PromType4ShopLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PromType4ShopLog record) throws DataAccessException;

    int insertSelective(PromType4ShopLog record) throws DataAccessException;

    List<PromType4ShopLog> selectByExample(PromType4ShopLogCriteria example) throws DataAccessException;

    PromType4ShopLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromType4ShopLog record, @Param("example") PromType4ShopLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromType4ShopLog record, @Param("example") PromType4ShopLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromType4ShopLog record) throws DataAccessException;

    int updateByPrimaryKey(PromType4ShopLog record) throws DataAccessException;
}