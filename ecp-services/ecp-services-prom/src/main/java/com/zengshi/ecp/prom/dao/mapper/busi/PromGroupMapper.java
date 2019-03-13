package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromGroup;
import com.zengshi.ecp.prom.dao.model.PromGroupCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromGroupMapper {
    Long countByExample(PromGroupCriteria example) throws DataAccessException;

    int deleteByExample(PromGroupCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PromGroup record) throws DataAccessException;

    int insertSelective(PromGroup record) throws DataAccessException;

    List<PromGroup> selectByExample(PromGroupCriteria example) throws DataAccessException;

    PromGroup selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromGroup record, @Param("example") PromGroupCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromGroup record, @Param("example") PromGroupCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromGroup record) throws DataAccessException;

    int updateByPrimaryKey(PromGroup record) throws DataAccessException;
}