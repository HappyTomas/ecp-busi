package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.PromConstraint;
import com.zengshi.ecp.prom.dao.model.PromConstraintCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface PromConstraintMapper {
    Long countByExample(PromConstraintCriteria example) throws DataAccessException;

    int deleteByExample(PromConstraintCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(PromConstraint record) throws DataAccessException;

    int insertSelective(PromConstraint record) throws DataAccessException;

    List<PromConstraint> selectByExampleWithBLOBs(PromConstraintCriteria example) throws DataAccessException;

    List<PromConstraint> selectByExample(PromConstraintCriteria example) throws DataAccessException;

    PromConstraint selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") PromConstraint record, @Param("example") PromConstraintCriteria example) throws DataAccessException;

    int updateByExampleWithBLOBs(@Param("record") PromConstraint record, @Param("example") PromConstraintCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") PromConstraint record, @Param("example") PromConstraintCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(PromConstraint record) throws DataAccessException;

    int updateByPrimaryKeyWithBLOBs(PromConstraint record) throws DataAccessException;

    int updateByPrimaryKey(PromConstraint record) throws DataAccessException;
}